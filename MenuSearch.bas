Type=Activity
Version=4
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region Module Attributes
	#FullScreen: False
	#IncludeTitle: False
#End Region

'Activity module
Sub Process_Globals
	Dim index As Object
End Sub

Sub Globals
'	Dim sv As Pencarian
'	Private pSearch As Panel
'	Private lvSearch As ListView
	'Private etSearch As EditText
	Private lvSearch As ListView
	Private ivClose  As ImageView
End Sub

Sub Activity_Create(FirstTime As Boolean)

	Activity.LoadLayout("Search")
	
	init_Search
	DoEvents

'	sv.Initialize(Me, "pSearch")
'	sv.AddToParent(Activity, 0, 0, 300dip, 300dip)
'	
'	
'	If FirstTime Then
'		Dim cities As List
'		cities = File.ReadList(File.DirAssets, "Cities.txt")
'		'As an optimization we store the index as a process global variable
'		'and only build it once.
'		index = sv.SetItems(cities)
'	Else
'		sv.SetIndex(index)
'	End If
End Sub

Sub sv_ItemClick(Value As String)
'	Msgbox("Chosen value: " & Value, "")
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
Sub init_menu

	Dim TimeFont As Typeface
	TimeFont = Typeface.LoadFromAssets ("gotham-light.ttf")
	
	lvSearch.Clear
	Fungsi.SetDivider(lvSearch, Colors.Transparent, 1dip)
	
	lvSearch.FastScrollEnabled = True
	lvSearch.TwoLinesLayout.SecondLabel.TextColor = Colors.Magenta
	
	lvSearch.AddTwoLinesAndBitmap("New Release", "", Null)
'	lvSearch.AddTwoLinesAndBitmap("Special Event", "", Null)
'	lvSearch.AddTwoLinesAndBitmap("Top Movies", "", Null)
'	lvSearch.AddTwoLinesAndBitmap("Action", "", Null)
'	lvSearch.AddTwoLinesAndBitmap("Drama", "", Null)
'	lvSearch.AddTwoLinesAndBitmap("Horror", "", Null)
'	lvSearch.AddTwoLinesAndBitmap("Ted 2", "", Null)
'	lvSearch.AddTwoLinesAndBitmap("SmackDown", "", Null)
'	lvSearch.AddTwoLinesAndBitmap("Raw", "", Null)
'	lvSearch.AddTwoLinesAndBitmap("Despicable me", "", Null)
'	lvSearch.AddTwoLinesAndBitmap("AXN", "", Null)
'	lvSearch.AddTwoLinesAndBitmap("AFC Cup", "", Null)
'	lvSearch.AddTwoLinesAndBitmap("Uefa Champions League", "", Null)
'	lvSearch.AddTwoLinesAndBitmap("Concacaf", "", Null)

	lvSearch.TwoLinesAndBitmap.SecondLabel.Visible = False
	lvSearch.TwoLinesAndBitmap.ImageView.Gravity = Gravity.CENTER
 
'	lvSearch.TwoLinesAndBitmap.ItemHeight = 60dip
	lvSearch.TwoLinesAndBitmap.Label.Typeface = TimeFont
	lvSearch.TwoLinesAndBitmap.Label.TextColor = Colors.White
'	lvSearch.TwoLinesAndBitmap.Label.Height = lvSearch.TwoLinesAndBitmap.ImageView.Height
'	'lvSearch.TwoLinesAndBitmap.Label.Gravity = Gravity.CENTER_VERTICAL
'	lvSearch.TwoLinesAndBitmap.Label.Left = lvSearch.TwoLinesAndBitmap.ImageView.Width + 7dip
'	lvSearch.Height = lvSearch.Size * 60dip 
  

End Sub
Sub lvSearch_ItemClick (Position As Int, Value As Object)
	
	Dim HomeJob As MNChttpJob
	Dim	strEnc	As String
	Dim Gen		As JSONGenerator
	Dim mPage	As Map	
	Dim coba 	As String=Value
	Dim dipisah()   As String
	Dim asli 		As String = Value

		
	'Log(Value)	
	mPage.Initialize
	mPage.Clear
	'mPage.Put(Fungsi.mpList.Get("Variabel"), coba.Replace(" ", "-").ToLowerCase)
	Gen.Initialize(mPage)
	HomeJob.Initialize("SearchPage", Me)
	'Log(Gen.ToPrettyString(2))  
	Try 
		'Log (Fungsi.mpList.Get("SearchURL"))
		HomeJob.Tag = Position '"SearchPage"
		strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(2))
		If strEnc = "Enkripsi Failed" Then Return
		HomeJob.PostString(Fungsi.mpList.Get("SearchURL"), "teks=" & strEnc)
		HomeJob.GetRequest.SetHeader("Cache-Control", "no-cache, must-revalidate")
		HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60
	Catch
		Msgbox2("Cetgory Failure !", "Moviebay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))
	End Try
End Sub
Sub init_Search

		Dim HomeJob As MNChttpJob
		Dim	strEnc	As String
		Dim Gen		As JSONGenerator
		Dim mcate	As Map
		
		mcate.Initialize
		mcate.Clear
		
		'mcate.Put(Fungsi.mpList.Get("sliding"), "1")
		Gen.Initialize(mcate)
'		Log(Gen.ToPrettyString(2))
		HomeJob.Initialize("SearchPage", Me)

		Try
			Log(Fungsi.mpList.Get("SearchURL"))
			HomeJob.Tag = "SearchPage"
			strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(2))
			If strEnc = "Enkripsi Failed" Then Return
'			Log(strEnc)
			HomeJob.PostString(Fungsi.mpList.Get ("SearchURL"), "teks=" & strEnc)
			HomeJob.GetRequest.SetHeader("Cache-Control", "no-cache, must-revalidate")
			HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60
		Catch
			Msgbox2("Cetgory Failure !", "Moviebay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))
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
	
			#Region Search

			Case "SearchPage"
				Dim cari1,cari2 As List
			 	lvSearch.Clear
			 
			 	Log(cari1)
			 	cari1.Initialize
			 	cari1 = mresult.Get("Data") 
			 
			 	cari2.Initialize
			 
				For Each colCategory As Map In cari1
						lvSearch.AddSingleLine(colCategory.Get("title"))
					'Exit
				Next
				Dim TimeFont1 As Typeface
				
			'	lvCateFilm.TwoLinesAndBitmap.Label.TextColor = Colors.White
				lvSearch.SingleLineLayout.Label.Gravity = Gravity.LEFT  
				'Fungsi.SetDivider(lvSearch, Colors.Transparent, 1dip)

				lvSearch.FastScrollEnabled = False
					
'				If lvSear.Size > 0 Then
'					lblNamaFilm.Text = lvCateFilm.GetItem(0)
'					lvCateFilm_ItemClick(0, lvCateFilm.GetItem(0) & "#Awal")
'				End If

				
			#End Region
End Select
		End If
	End If
End Sub
Sub ivClose_Click
'	Activity.Finish
End Sub