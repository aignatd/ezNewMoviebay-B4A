Type=Activity
Version=4
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.


	Private ivMov 		As ImageView
	Private etPass 		As EditText
	Private etCfpass	As EditText
	Private lblPass 	As Label
	Private etFirst 	As EditText
	Private etLast 		As EditText
	Private etMail 		As EditText
	Private etPhone 	As EditText
	Private etGender 	As EditText
	Private etBirth 	As EditText
	Private etConfirm 	As EditText
	Private ivBackrg 	As ImageView
	Private ck1 		As CheckBox
	Private bReg 		As Button
	Private BDLogin		As BetterDialogs
	Private spGender 	As Spinner
	Private ivReg 		As ImageView
	Private spBirth 	As Spinner
	Private lblCek 		As Label
	Private ckBox 		As CheckBox
	Private lstCate 	As List
	Private coba 		As Map
	Private cvBirth 	As AnotherDatePicker
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	
	#Region Set Default Folder
	Fungsi.SetDirProg("")
	Fungsi.BacaFileConfig	
	#End Region
	
	#Region Cek File Config.json
	If File.Exists(File.DirAssets, "config.json") Then
		If Fungsi.CacahJSON(File.ReadString(File.DirAssets, "config.json"), "NextArray") = False Then
			BDLogin.Msgbox("Error", "Reading config failed !", "OK", "", "", Fungsi.GetDrawable("ic_action_warning"))
			ExitApplication
		End If
	Else
		BDLogin.Msgbox("Error", "Load config failed !", "OK", "", "", Fungsi.GetDrawable("ic_action_warning"))
		ExitApplication
	End If
	#End Region
	
	Activity.LoadLayout("NewReg")
    
	 #Region Set Background
	Dim cdBack 	As ColorDrawable	
	cdBack.Initialize(Colors.RGB(132, 31, 40), 5)	
	etFirst.Background = cdBack
	etLast.Background = cdBack
	etMail.Background = cdBack
	etPhone.Background = cdBack
	'spGender.Background = cdBack

	etPass.Background = cdBack
	etConfirm.Background = cdBack	
	
'''	etPhone.Background = cdBack

'	
'	'spGender.Initialize("")
'	'spGender.AddAll(lstCate)
	spGender.Add("")
	spGender.Add("Male")
	spGender.Add("Female")
	
'' 	spBirth.Add("")
''	spGender.Background = cdBack
'
''	etCfpass.Background = cdBack
''	ivMov.Background = Fungsi.GetDrawable("logo_login")
	#End Region

End Sub
Sub Activity_KeyPress (KeyCode As Int) As Boolean
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		If cvBirth.IsVisible Then
			cvBirth.btnCancel_Click 'emulate a click on Cancel button
			Return True
		End If
	End If
	Return False
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
Sub ivBackrg_Click
	Activity.Finish
End Sub
Sub  ck1_CheckedChange(Checked As Boolean)
	If Checked = True Then bReg.Enabled = True Else bReg.Enabled = False
	'ck1.Checked = True
	'ck1.Enabled = False
End Sub
Sub bReg_Click
	Dim HomeJob 	As MNChttpJob
	Dim	strEnc		As String
	Dim Gen 		As JSONGenerator
	Dim mregister 	As Map
	
	
	mregister.Initialize
	mregister.Clear
	mregister.Put(Fungsi.mpList.Get("firstname"), etFirst.text)
	mregister.Put(Fungsi.mpList.Get("lastname"), etLast.text)
	mregister.Put(Fungsi.mpList.Get("regemail"), etMail.Text)
	mregister.Put(Fungsi.mpList.Get("handphone"), etPhone.Text)
	mregister.Put(Fungsi.mpList.Get("regpass"), etPass.text)
	mregister.Put(Fungsi.mpList.Get("gender"),  spGender.SelectedItem)
	mregister.Put(Fungsi.mpList.Get("homephone"), "0")
	mregister.Put(Fungsi.mpList.Get("id_city"), "3173") 
	mregister.Put(Fungsi.mpList.Get("zipcode"), "13720")
	Gen.Initialize(mregister)
'	Log(Gen.ToPrettyString(18))
	
		
	HomeJob.Initialize("Register", Me)
		
		
		Try
			HomeJob.Tag = "Register"
			strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(18))
			If strEnc = "Enkripsi Failed" Then Return
			HomeJob.PostString(Fungsi.mpList.Get("RegisterURL"), "teks=" & strEnc)
			HomeJob.GetRequest.SetHeader("Cache-Control", "no-cache, must-revalidate")
			HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60
		Catch
			Msgbox2("Register Failure !", "Moviebay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))
		End Try
		
End Sub
Sub JobDone (MNChttp As MNChttpJob)

	Dim sf			As StringFunctions
	Dim sTmp 		As String
	Dim parser 		As JSONParser	
	Dim mresult		As Map
	Dim bTmp()		As Byte
	Dim bcTmp		As ByteConverter
	Dim su 			As StringUtils


	#Region HTTP Gagal
	If MNChttp.Success = False Then
		If (sf.InString(MNChttp.ErrorMessage, "timed out") > 0) OR (sf.InString(MNChttp.ErrorMessage, "refused") > 0) OR _
			 (sf.InString(MNChttp.ErrorMessage, "Unable to resolve host") > 0) OR (sf.InString(MNChttp.ErrorMessage, "UnknownHostException") > 0) OR _
			 (sf.InString(MNChttp.ErrorMessage, "FileNotFound") > 0) Then
'			ctoast.Show4("Error", "Offline Connection or Server Down", LoadBitmap(File.DirAssets, "warning.png"))
			Msgbox2("Offline Connection or Server Down", "Moviebay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))
		Else
			If sf.InString(MNChttp.ErrorMessage, "Not Found") < 0 Then
'				ctoast.Show4("Error", "Unknown Error", LoadBitmap(File.DirAssets, "warning.png"))
				Msgbox2("Unknown Error", "Moviebay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))
			End If
		End If

		Fungsi.mpList.Put("Koneksi", "Offline")
		MNChttp.Release
	#End Region
	#Region HTTP Sukses
	Else
		Try
			bTmp = su.DecodeBase64(MNChttp.GetString)
			sTmp = bcTmp.StringFromBytes(bTmp, "UTF8")
			parser.Initialize(Fungsi.UnWrap(sTmp))
			MNChttp.Release
		Catch
			Msgbox2("Process Failure !", "Moviebay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))			
			Return
		End Try
		
		Try
			mresult.Initialize
			mresult = parser.NextObject
		Catch
			Msgbox2("Process Failure !", "Moviebay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))			
			Return
		End Try
'		Log(mresult)
		If mresult.Get("Result") <> "0" Then
			Msgbox2(mresult.Get("Message"), "Moviebay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))			
		Else
			Fungsi.mpList.Put("Koneksi", "Online")
			MNChttp.Release

			Select MNChttp.JobName			
			#Region Register
			Case "Register"
		'	Log(MNChttp.GetString)
			Msgbox2(mresult.Get("Message"), "Movibay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))			
			#End Region
			
			#Region Login
			Case "Login"
			Log(mresult.Get("Message"))
			Msgbox2(mresult.Get("Message"), "Movibay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))	
			#End Region
			
			If lstCate.IsInitialized = False Then lstCate.Initialize
			lstCate.Clear
				
			
			End Select
		End If
	End If
	
End Sub
Sub spGender_ItemClick (Position As Int, Value As Object)
	
	If Value = "Male" Then
		'Msgbox("Male","Your Gender")
	Else If Value = "Female" Then
		'Msgbox("Female","Your Gender")
	Else
	
	End If

End Sub
Sub spBirth_ItemClick (Position As Int, Value As Object )
   
End Sub
Sub ckBox_CheckedChange(Checked As Boolean)
	'	If Checked = True Then bReg.Enabled = True Else bReg.Enabled = False
End Sub
Sub cvBirth_Closed (Cancelled As Boolean, Date As Long)
	'Log("Cancelled = " & Cancelled & ", Date = " & DateUtils.TicksToString(Date))
End Sub