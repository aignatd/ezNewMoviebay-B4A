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

	Dim i			As Int
	Dim a			As Object
	Dim gambar 		As Map
	Private pm 		As PackageManager
	Private ups 	As Intent
	Private ipack	As Int
	'Dim Cok 		As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity  is created.
	'These variables can only be accessed from this module.
	
	Dim ivMovies 		As ImageView
	Private Params		As BD_CustomDlgParams
	Private BD			As BetterDialogs
	Private pShare		As Panel
	Private ivShare 	As ImageView
	Dim iShare 			As Int=1
	Private ivPlay 		As ImageView
	Private wvOrder 	As WebView
	Private lblCerita   As Label
	Private lblFilm 	As Label
	Private lblCate 	As Label
	Private lblDirec 	As Label
	Private lblNama 	As Label
	Private lblGenre 	As Label
	
	Private lblJudul 	 As Label
	Private lblTitle 	 As Label
	Private lblCast		 As Label
	Private lblNmCast 	 As Label
	Private lblTake 	 As Label
	Private lblAllSyn 	 As Label
	Private ivMovie 	 As ImageView
	Private lblAtas 	 As Label
	Private lblBawah 	 As Label
	Private lblTrailer 	 As Label
	Private ivTrailer 	 As ImageView
	Private lblComment 	 As Label
	Private ivComment 	 As ImageView
	Private etDesc 		 As EditText
	Private lblComments  As Label
	Private bShare 		 As Button
	Private bCancel 	 As Button
	Private pUtama 		 As Panel
	Private ivCancel 	 As ImageView
	Private ivAction 	 As ImageView
	Private lblAction 	 As Label
	Private lblNmWrite 	 As Label
	Private lblNmWriter  As Label
	Private ivComments 	 As ImageView

End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")

	
	Activity.LoadLayout("Content")
	

	
	ivMovies.Bitmap = a
'	ivMovie.Bitmap = a
'	'Log(gambar)
	#Region director	
	Dim Cate0 As Map	
	Cate0.Initialize
	gambar.Get("contentDirectors")
	'Log(gambar.Get("contentDirectors"))
		Dim Cate1 As List = gambar.Get("contentDirectors") 		
			For Each Cate2 As Map In Cate1
				Cate0 = Cate2.Get("idDirector")
			Next
				Cate0.Get("name")
				lblDirec.Text = Cate0.Get("name")
				'Log(Cate0.Get("name"))
	#End Region
	#Region Genre
	Dim Genre0 As Map
	Genre0.Initialize
	gambar.Get("contentGenres")
	'Log(gambar.Get("contentGenres"))
		Dim Genre1 As List = gambar.Get("contentGenres")
			For Each Gender2 As Map In Genre1
				Genre0 = Gender2.Get("idGenre")
				'Log(Gender2.Get("idGenre"))
			Next
				Genre0.Get("name")
				'Log(Gender0.Get("name"))
				lblGenre.Text = Genre0.Get("name")
	#End Region
	#Region Cast
	Dim Cast0 As Map
	Cast0.Initialize
	gambar.Get("contentCasts")
		Dim Cast1 As List = gambar.Get("contentCasts")
			For Each Cast2 As Map In Cast1
				Cast0 = Cast2.Get("idCast")
				'Log(Gender2.Get("idGenre"))
			Next
				Cast0.Get("name")
				'Log(Gender0.Get("name"))
				lblNmCast.Text = Cast0.Get("name")
	#End Region
	#Region Writer
	Dim Writer0 As Map
	Writer0.Initialize
	gambar.Get("contentProducers")
	'Log(gambar.Get("contentProducers"))
		Dim Writer1 As List = gambar.Get("contentProducers")
			For Each Writer2 As Map In Writer1
				Writer0 = Writer2.Get("idProducer")
				'Log(Gender2.Get("idGenre"))
			Next
				Writer0.Get("name")
				'Log(Gender0.Get("name"))
				lblNmWriter.Text = Writer0.Get("name")	
	#End Region
	Log(gambar)
	For x2=0 To gambar.Size-1
		Select gambar.GetKeyAt(x2)
						Case "synopsis"
							lblCerita.Text = gambar.Get("synopsis")
						Case "type"
							lblBawah.Text = gambar.Get("type")
						Case "title"
							lblAtas.Text = gambar.Get("title")
 							lblTitle.Text = gambar.Get("title")
				End Select	

			Next
	
End Sub

Sub Activity_Resume
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
Sub ivBack_Click
	Activity.Finish
End Sub
Sub ivShare_Click
	StartActivity("Share")
End Sub
Sub ivPlay_Click			

	
	For x2=0 To gambar.Size-1
		Select gambar.GetKeyAt(x2)
			Case "pathVideo"
			If gambar.Get("pathVideo") = Null Then
				Msgbox2("Video not found !", "Moviebay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))
			Else
				#Region Awal aplikasi mulai
				If File.Exists(Fungsi.DirDBS, "nexplayersdklite.apk") = False Then
					ProgressDialogShow2("Please wait...", False)
					DoEvents
					File.Copy(File.DirAssets, "nexplayersdklite.apk", Fungsi.DirDBS, "nexplayersdklite.apk")
					ProgressDialogHide
					DoEvents
				End If

	  		Dim pacList As List = pm.GetInstalledPackages
				ipack = pacList.IndexOf("com.nexstreaming.app.nexplayersamplelitevm")
		
				If ipack < 0 Then
					ups.Initialize(ups.ACTION_VIEW, "file://" & File.Combine(Fungsi.DirDBS, "nexplayersdklite.apk"))
					ups.SetType("application/vnd.android.package-archive")
					StartActivity(ups)
				End If 
				#End Region

				ups.Initialize(ups.ACTION_MAIN, "")
 				ups = pm.GetApplicationIntent("com.nexstreaming.app.nexplayersamplelitevm")
				Log(gambar.Get("pathVideo"))
				ups.PutExtra("LinkURL", gambar.Get("pathVideo"))
 				ups.PutExtra("Company", "PT-Infokom")
 				ups.PutExtra("VCASAddr", "202.80.222.36:80")

				If ups.IsInitialized Then StartActivity(ups)
 			'	StartActivity(ups)
			End If
		End Select
	Next   
End Sub

Sub ivMovies_Click
 'ivPlay_Click	
End Sub

Sub lblTrailer_Click
For x2=0 To gambar.Size-1
		Select gambar.GetKeyAt(x2)
			Case "pathVideo"
			If gambar.Get("pathVideo") = Null Then
				Msgbox2("Video not found !", "Moviebay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))
			Else
'				Pemutar.URLChannel = gambar.Get("pathVideo")
				ups.Initialize(ups.ACTION_MAIN, "")
 				ups = pm.GetApplicationIntent("com.nexstreaming.app.nexplayersamplelitevm")
				ups.PutExtra("LinkURL", gambar.Get("http://media.mncplaymedia.net/hls2/animalplanet/animalplanet.m3u8"))
 				ups.PutExtra("Company", "PT-Infokom")
 				ups.PutExtra("VCASAddr", "202.80.222.36:80")				
				If ups.IsInitialized Then StartActivity(ups)
 				StartActivity(ups)
'				StartActivity("Pemutar")
			End If
		End Select
	Next
End Sub
Sub ivTrailer_Click
	lblTrailer_Click
End Sub
Sub ivCancel_Click
	StartActivity("Content")
End Sub
Sub lblComments_Click
	Private pnlBody As Panel
	pnlBody.Initialize("")
	pnlBody.Color = Colors.Transparent
	pnlBody.LoadLayout("comments")
	
	etDesc.Background = Fungsi.GetDrawable ("pass_input_")
	
	Params.Initialize
	Params.DialogBody = pnlBody
	Params.BodyWidth = pUtama.Width
	Params.BodyHeight = pUtama.Height

	BD.CustomDialog(Params, "CD2")		
End Sub
Sub ivComments_Click
	lblComments_Click
End Sub