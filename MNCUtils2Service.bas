Type=Service
Version=4
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region Module Attributes
	#StartAtBoot: False
#End Region

'HttpUtils2 version 2.01
'Service module
Sub Process_Globals
	Private hc 			As HttpClient
	Private TaskIdToJob As Map
	Public TempFolder 	As String
	Private taskCounter As Int
	Private tmpMap 		As Map
End Sub

Sub Service_Create
	TempFolder = File.DirInternalCache
	If hc.IsInitialized = False Then hc.Initialize("hc")
'	hc.SetHttpParameter("http.protocol.handle-redirects", False)
	TaskIdToJob.Initialize
	tmpMap.Initialize
End Sub

Sub Service_Start (StartingIntent As Intent)
	
End Sub

Sub Service_Destroy

End Sub

Public Sub SubmitJob(job As MNChttpJob) As Int
	taskCounter = taskCounter + 1
	TaskIdToJob.Put(taskCounter, job)

	If job.Username <> "" AND job.Password <> "" Then
		hc.ExecuteCredentials(job.GetRequest, taskCounter, job.Username, job.Password)
	Else
		hc.Execute(job.GetRequest, taskCounter)
	End If
	
	hc.SetHttpParameter("http.protocol.handle-redirects", False)
	
	Return taskCounter
End Sub

Sub hc_ResponseSuccess (Response As HttpResponse, TaskId As Int)
	Dim z() 	As String
	Dim z1() 	As String
	Dim lstCookie As List

	tmpMap.Clear
	tmpMap = Response.GetHeaders
	
	If tmpMap.ContainsKey("Set-Cookie") Then
		lstCookie = tmpMap.Get("Set-Cookie")
		z = Regex.Split(";", lstCookie.Get(0))
		z1 = Regex.Split("=", z(0))
		tmpMap.Put("JSESSIONID", z1(1))
	End If

	If File.Exists(TempFolder, TaskId) Then File.Delete(TempFolder, TaskId)
	Response.GetAsynchronously("response", File.OpenOutput(TempFolder, TaskId, False), True, TaskId)
End Sub

Sub Response_StreamFinish (Success As Boolean, TaskId As Int)
	If Success Then
		CompleteJob(TaskId, Success, "")
	Else
		CompleteJob(TaskId, Success, LastException.Message)
	End If
End Sub

Sub hc_ResponseError (Response As HttpResponse, Reason As String, StatusCode As Int, TaskId As Int)
	Dim i 		As Int = 0
	Dim k 		As Int = 0
	Dim z() 	As String
	Dim z1() 	As String
	Dim lstCookie As List

	If Response <> Null Then
		tmpMap.Clear
		tmpMap = Response.GetHeaders
		tmpMap.Put("StatusCode", StatusCode)

		If tmpMap.ContainsKey("Set-Cookie") Then
			lstCookie = tmpMap.Get("Set-Cookie")
			z = Regex.Split(";", lstCookie.Get(0))
			z1 = Regex.Split("=", z(0))
			tmpMap.Put("JSESSIONID", z1(1))
		End If

		Try
			Log(Response.GetString("UTF8"))
		Catch
			Log("Failed to read error message.")
		End Try
		
    If Floor(StatusCode / 100) = 3 Then
			i = 1
    End If
	End If

	If i = 0 Then
		CompleteJob(TaskId, False, Reason)
	Else
		CompleteJob(TaskId, True, "")
	End If
End Sub

Sub CompleteJob(TaskId As Int, success As Boolean, errorMessage As String)
	Dim job As MNChttpJob
	
	Try
		job = TaskIdToJob.Get(TaskId)
		TaskIdToJob.Remove(TaskId)
		job.success = success
		job.errorMessage = errorMessage
		job.Complete(TaskId)
		job.ResMap = tmpMap
		If job.ResMap.Get("Cookies") = "Hapus" Then ClearCookies
	Catch
		Log(errorMessage)
	End Try
End Sub

Sub ClearCookies
	Dim r As Reflector
	r.Target = hc
	r.Target = r.GetField("client")
	r.Target = r.RunMethod("getCookieStore")
	r.RunMethod("clear")
End Sub
