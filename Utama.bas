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
'	These global variables will be declared once when the Application starts.
'	These variables can be accessed from all modules.
	Dim damn As String
	Dim sial As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	'Dim aa As DRMLib
	Private container 		As AHPageContainer
	Private pager 			As AHViewPager
	Private tabs 			As AHViewPagerTabs
	Private FILL_PARENT 	As Int : FILL_PARENT = -1
	Private WRAP_CONTENT 	As Int : WRAP_CONTENT = -2
	Private pMainAtas 		As Panel
	Private pMenuMain 		As Panel
	Private BDUtama			As BetterDialogs
	Private ivMenu 			As ImageView
	Private ivSearch 		As ImageView
	Private ivMainLogo	 	As ImageView
	Private pLogo			As Panel
	Private lblNamaFilm 	As Label
	Private AB				As ClsActionBar
	Private HSV				As HorizontalScrollView
	Private lblUser			As Label	
	Private pMenu 			As Panel
	Private iTampil1 		As Int=0
	Private iTampil2 		As Int=0
	Private pUtama			As Panel	
	Private lvUtama 		As ListView
	Private lvCateFilm 		As ListView
	Private pIsiTab 		As Panel
	Private ivClose			As ImageView
	Private ivMovies 		As ImageView
	Private ivBack 			As ImageView
	Private mpList 			As Map
	Private streExt 		As StringFunctions
	Private ctoast			As ToastMessageShow
	Private etEmail 		As EditText
	Private etPass 			As EditText
	Private lstCate 		As List
	Private bReg			As Button
	Private DirFile			As String
	Private Namafile		As String
	Private boundary 		As String
	Private iAwalCate		As Boolean=True
	Private nilai			As String
	Private mapPager		As Map
	Private mapGrid			As Map
	Private picture			As Map
	Private ivUser 			As ImageView
	Private lblAction 		As Label
	Private lblLast 		As Label
	Private Params			As BD_CustomDlgParams
	Private BD				As BetterDialogs
	Private wvPay 			As WebView
	Private ivPremium 		As ImageView
	Private Label1 			As Label
	Private lblLg 			As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:

	Activity.LoadLayout("mainpage")
	
	mapPager.Initialize
	mapGrid.Initialize
	
	Fungsi.SetDirProg("")
	Fungsi.DataConfig.FolderDBS = Fungsi.DirDBS
	Fungsi.BacaFileConfig	
	
	picture.Initialize
	
	If Fungsi.DataConfig.Username <> "Guest" Then
		lblUser.Text = Fungsi.DataConfig.Username
	Else
		lblUser.Text = "Login Here"
	End If

	If Fungsi.CacahJSON(File.ReadString(File.DirAssets, "config.Json"), "NextArray") = False Then 'Cacah JSON yang ada didalam file urutan.json
		Msgbox2("Load init failed !", "MNC Playmedia", "OK", "", "", LoadBitmap(File.DirAssets, "Warning.png"))
		ExitApplication
	End If
	
	lblUser.Text = damn
	Label1.Text = sial
	
	'ivUser.Background = sial
	
	#Region Set Background
	ivMenu.Background = Fungsi.GetDrawable("menu_icon")
	ivSearch.Background = Fungsi.GetDrawable("search_icon")
	ivMainLogo.Background = Fungsi.GetDrawable("logo_top")
	#End Region
 	
'	CreatePanel(lblNamaFilm.Text)
	#End Region	
	
'	init_menu
'	DoEvents
'	init_catefilm
'	DoEvents
	init_category
	DoEvents
	init_ListCategory
	DoEvents	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'return true if you want To consume the event
	If KeyCode = 4 Then
		If BDUtama.Msgbox("Moviebay", "Do you want to exit ?", "Yes", "No", "", Fungsi.GetDrawable("ic_action_warning")) = DialogResponse.POSITIVE Then
			ExitApplication
		End If
		
		Return True
	End If		
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub CreatePanel(MenuType As List)
	Dim iJumPanel		As Int=0
	DoEvents
	Dim strPisah()		As String
	
	pMenuMain.RemoveAllViews
	pIsiTab.RemoveAllViews
	
	#Region Cek Menu
	
	iJumPanel = MenuType.Size
	
	#End Region
'	
'	#Region Cek Menu Yang Dipilih
'	Select MenuType.ToLowerCase
'	Case "channel"
'		iJumPanel = Fungsi.ChannelMap.Size
'	Case "movies"
'		iJumPanel = Fungsi.MoviesMap.Size
'	Case "series"
'		iJumPanel = Fungsi.SeriesMap.Size
'	Case "trailer"
'		iJumPanel = Fungsi.TrailerMap.Size
'	End Select
'	#End Region
	
	#Region Buat Panel Kategori Berdasarkan Menu Yang Dipilih
	container.Initialize

	mapPager.Clear
	mapGrid.Clear
	
	For i=0 To iJumPanel-1		
		Dim pIsiFilm 	As Panel
		Dim gvPanel 	As PhotoGridView
		
		pIsiFilm.Initialize("")		
		gvPanel.Initialize("")	
		
		container.AddPage(pIsiFilm, MenuType.Get(i))
		mapPager.Put(i, pIsiFilm)
		mapGrid.Put(i, gvPanel)
		DoEvents
	Next
	
	pager.Initialize(container, "Pager")
	
	#End Region	
'	
	#Region Tampilkan Panel Kategori Berdasarkan Menu Yang Dipilih
	'Now we have a container with our panels just add it to the pager object
	
	'As we want to show the tabs page indicator, we initialize it
	tabs.Initialize(pager)
	tabs.TextSize = 14
	tabs.TabPaddingBottom = 11dip
	tabs.TabPaddingTop = 13dip
	tabs.LineHeight = 3dip
	tabs.UpperCaseTitle = True
	tabs.LineColorCenter = Colors.White
	tabs.Color = Colors.ARGB(232, 152, 20, 33)
	pIsiTab.AddView(tabs, 0, 0, pIsiTab.Width, pIsiTab.Height)
	pMenuMain.AddView(pager, 15dip, 15dip, pMenuMain.Width - 15dip, pMenuMain.Height - 15dip)

	If MenuType.Size > 0 Then
		pager.CurrentPage = 0
		pager.GotoPage(0, True)
		Pager_PageChanged(0)
	End If
	#End Region 
End Sub

Sub Video_ItemClick (Position As Int, Value As Object)
		Dim HomeJob 	As MNChttpJob
		Dim	strEnc		As String
		Dim Gen			As JSONGenerator
		Dim mcate		As Map
		Dim dipisah()   As String
		
'		mcate.Initialize
'		mcate.Clear
'		
'		mcate.Put(Fungsi.mpList.Get("Variabel"), Value)
'		Gen.Initialize(mcate)
''		Log(Gen.ToPrettyString(2))
'		HomeJob.Initialize("SlideCate", Me)
'	
'		Try
'			HomeJob.Tag =  Position
'			strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(2))
'			If strEnc = "Enkripsi Failed" Then Return
''			Log(strEnc)
'			HomeJob.PostString(Fungsi.mpList.Get ("SlideURL"), "teks=" & strEnc)
'			HomeJob.GetRequest.SetHeader("Cache-Control", "no-cache, must-revalidate")
'			HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60
'		Catch
'			Msgbox2("Cetgory Failure !", "Moviebay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))
'		End Try
	
	Dim A As Object
'	Log (Position)
'	Log ("ini isi value" & Value)
	Content.A = Value
	Content.i = Position
	Content.gambar = picture.Get(Position)

	StartActivity("Content")

	
End Sub
Sub init_menu
	
'	Dim TimeFont As Typeface
'	TimeFont = Typeface.LoadFromAssets ("gotham-light.ttf")
'	
'	lvUtama.Clear
'	Fungsi.SetDivider(lvUtama, Colors.Transparent, 1dip)
'	
'	lvUtama.FastScrollEnabled = False
'	lvUtama.TwoLinesLayout.SecondLabel.TextColor = Colors.Black
'	
''	lvUtama.AddTwoLinesAndBitmap("New Release", "", Null)
''	lvUtama.AddTwoLinesAndBitmap("Top Movies", "", Null)
''	lvUtama.AddTwoLinesAndBitmap("TV Series", "", Null)
'	lvUtama.TwoLinesAndBitmap.SecondLabel.Visible = False
'	lvUtama.TwoLinesAndBitmap.ImageView.Gravity = Gravity.CENTER
'	lvUtama.TwoLinesAndBitmap.ImageView.Height = 60dip
'	lvUtama.TwoLinesAndBitmap.ImageView.Width = 35dip
'	lvUtama.TwoLinesAndBitmap.ItemHeight = 60dip
'	lvUtama.TwoLinesAndBitmap.Label.Typeface = TimeFont
'	lvUtama.TwoLinesAndBitmap.Label.TextColor = Colors.White
'	lvUtama.TwoLinesAndBitmap.Label.Height = lvUtama.TwoLinesAndBitmap.ImageView.Height
'	lvUtama.TwoLinesAndBitmap.Label.Gravity = Gravity.CENTER_VERTICAL
'	lvUtama.TwoLinesAndBitmap.Label.Left = lvUtama.TwoLinesAndBitmap.ImageView.Width + 7dip
'	lvUtama.Height = lvUtama.Size * 60dip 

End Sub
Sub init_category

		Dim HomeJob As MNChttpJob
		Dim	strEnc	As String
		Dim Gen		As JSONGenerator
		Dim mcate	As Map
		
		mcate.Initialize
		mcate.Clear
		
		mcate.Put(Fungsi.mpList.Get("sliding"), "1")
		Gen.Initialize(mcate)
'		Log(Gen.ToPrettyString(2))
		HomeJob.Initialize("Category", Me)

		Try
			HomeJob.Tag = "Category"
			strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(2))
			If strEnc = "Enkripsi Failed" Then Return
'			Log(strEnc)
			HomeJob.PostString(Fungsi.mpList.Get ("CateURL"), "teks=" & strEnc)
			HomeJob.GetRequest.SetHeader("Cache-Control", "no-cache, must-revalidate")
			HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60
		Catch
			Msgbox2("Cetgory Failure !", "Moviebay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))
		End Try
End Sub
Sub Cek_Menu
	If iTampil1=0 Then
		iTampil1 = 1
		pUtama.SetLayoutAnimated(500, 0%x, 0, 180%x, 100%y)
	Else
		iTampil1 = 0
		pUtama.SetLayoutAnimated(500, -80%x, 0, 180%x, 100%y)
	End If
End Sub

Sub ivMenu_Click 
	Cek_Menu	 
End Sub

Sub lblNamaFilm_Click
	If iTampil2 = 0 Then
		iTampil2 = 1
		lvCateFilm.SetLayoutAnimated(120, pMainAtas.Left, pMainAtas.Height, pMainAtas.Width, 100%y - pMainAtas.Height)
	Else
		iTampil2 = 0
		lvCateFilm.SetLayoutAnimated(120, pMainAtas.Left, pMainAtas.Height, pMenuMain.Width, 0)
	End If		
End Sub

Sub ivDropDown_Click
	lblNamaFilm_Click	
End Sub

Sub init_catefilm	
	
'	Dim TimeFont1 As Typeface
'	
'	TimeFont1 = Typeface.LoadFromAssets ("gotham-medium.ttf")
'
'	lvCateFilm.Clear
'	Fungsi.SetDivider(lvCateFilm, Colors.Transparent, 1dip)
'	
'	lvCateFilm.FastScrollEnabled = False
'	
'	lvCateFilm.AddSingleLine("CHANNEL")
'	lvCateFilm.AddSingleLine("MOVIES")
'	lvCateFilm.AddSingleLine("SERIES")
''	lvCateFilm.AddSingleLine("KIDS")
'	lvCateFilm.SingleLineLayout.Label.TextColor = Colors.White
'	lvCateFilm.SingleLineLayout.Label.Gravity = Gravity.CENTER
'	lvCateFilm.SingleLineLayout.Label.TextSize = 14
'	lvCateFilm.SingleLineLayout.ItemHeight = 50dip
'	lvCateFilm.SingleLineLayout.Label.Typeface = TimeFont1
'	lvCateFilm.SingleLineLayout.Label.Left = 20
End Sub

Sub lvCateFilm_ItemClick (Position As Int, Value As Object)
	Dim HomeJob 	As MNChttpJob 
	Dim	strEnc		As String
	Dim Gen			As JSONGenerator
	Dim mCate		As Map	
	Dim dipisah()   As String
	Dim asli 		As String = Value

	dipisah = Regex.Split("#", asli)

	If asli.Contains("Awal") = False Then
		lblNamaFilm_Click
	End If
	
	'Log()
	mCate.Initialize 
	mCate.Clear
	mCate.Put(Fungsi.mpList.Get("slug"),"slug")
	Gen.Initialize(mCate)
	HomeJob.Initialize("CateMovies", Me)
	
	Try
		'Log(Fungsi.mpList.Get("MoviesURL"))
		HomeJob.Tag = dipisah(0)
		strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(2))
		If strEnc = "Enkripsi Failed" Then Return
		HomeJob.PostString(Fungsi.mpList.Get("MoviesURL"), "teks=" & strEnc)
		HomeJob.GetRequest.SetHeader("Cache-Control", "no-cache, must-revalidate")
		HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60
		lblNamaFilm.Text = dipisah(0)
	Catch
		Msgbox2("Category Failure !", "Moviebay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))
	End Try
End Sub
Sub init_ListCategory

	Dim HomeJob As MNChttpJob
	Dim	strEnc	As String
	Dim Gen		As JSONGenerator
	Dim mList	As Map	
	
	mList.Initialize
	mList.Clear
	mList.Put(Fungsi.mpList.Get("sliding"), "0") 
	Gen.Initialize(mList)
'	Log(Gen.ToPrettyString(2))
	HomeJob.Initialize("PilihCategory", Me)

		Try
			HomeJob.Tag = "PilihCategory"
			strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(2))
			If strEnc = "Enkripsi Failed" Then Return
'			Log(strEnc)
			HomeJob.PostString(Fungsi.mpList.Get ("ListCateURL"), "teks=" & strEnc)
			HomeJob.GetRequest.SetHeader("Cache-Control", "no-cache, must-revalidate")
			HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60
		Catch
			Msgbox2("Cetgory Failure !", "Moviebay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))
		End Try
End Sub 

Sub ivSearch_Click
	StartActivity("MenuSearch")
End Sub

Sub  lvUtama_ItemClick (Position As Int, Value As Object)
'	Cek_Menu	
	Dim HomeJob As MNChttpJob
	Dim	strEnc	As String
	Dim Gen		As JSONGenerator
	Dim mPage	As Map	
	Dim coba 	As String=Value
	Dim dipisah()   As String
	Dim asli 		As String = Value

	Cek_Menu
	
	'Log(Value)	
	mPage.Initialize
	mPage.Clear
	mPage.Put(Fungsi.mpList.Get("Variabel"), coba.Replace(" ", "-").ToLowerCase)
	Gen.Initialize(mPage)
	HomeJob.Initialize("MenuKiri", Me)
	'Log(Gen.ToPrettyString(2))  
	Try
		Log (Fungsi.mpList.Get("SlugURL"))
		HomeJob.Tag = Position
		strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(2))
		If strEnc = "Enkripsi Failed" Then Return
		HomeJob.PostString(Fungsi.mpList.Get("SlugURL"), "teks=" & strEnc)
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
			Msgbox2("Offline Connection or Server Down", "MNC Video Record", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))
		Else
			If sf.InString(MNChttp.ErrorMessage, "Not Found") < 0 Then
'				ctoast.Show4("Error", "Unknown Error", LoadBitmap(File.DirAssets, "warning.png"))
				Msgbox2("Unknown Error", "Moviebay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))
			End If
		End If

 		mpList.Put("Koneksi", "Offline")
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

		If mresult.Get("Result") <> "0" Then
			Msgbox2(mresult.Get("Message"), "Moviebay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))			
		Else
'			mpList.Put("Koneksi", "Online")
			MNChttp.Release

			Select MNChttp.JobName			

			#Region Category
			'Log(mresult)
			Case "Category"
			Dim a1,b1 As List
			
			'Log(a1)
			a1.Initialize
			a1 = mresult.Get("Data")
			
			b1.Initialize			
			
			For Each colChannel As Map In a1
				b1 = colChannel.Get("mSubcategories")
				Exit
			Next
				
			Dim TimeFont As Typeface
			TimeFont = Typeface.LoadFromAssets ("gotham-light.ttf")
	
			lvUtama.Clear
			Fungsi.SetDivider(lvUtama, Colors.Transparent, 1dip)
	
			lvUtama.FastScrollEnabled = False
			lvUtama.TwoLinesLayout.SecondLabel.TextColor = Colors.Black
			
			
			For Each colname As Map In b1
				lvUtama.AddTwoLinesAndBitmap(colname.Get("name"),"",Null)
			Next
				
			lvUtama.TwoLinesAndBitmap.SecondLabel.Visible = False
			lvUtama.TwoLinesAndBitmap.ImageView.Gravity = Gravity.CENTER
			lvUtama.TwoLinesAndBitmap.ImageView.Height = 60dip
			lvUtama.TwoLinesAndBitmap.ImageView.Width = 35dip
			lvUtama.TwoLinesAndBitmap.ItemHeight = 60dip
			lvUtama.TwoLinesAndBitmap.Label.Typeface = TimeFont
			lvUtama.TwoLinesAndBitmap.Label.TextColor = Colors.White
			lvUtama.TwoLinesAndBitmap.Label.Height = lvUtama.TwoLinesAndBitmap.ImageView.Height
			lvUtama.TwoLinesAndBitmap.Label.Gravity = Gravity.CENTER_VERTICAL
			lvUtama.TwoLinesAndBitmap.Label.Left = lvUtama.TwoLinesAndBitmap.ImageView.Width + 7dip
			lvUtama.Height = lvUtama.Size * 60dip 
			
			#End Region
			#Region PilihCategory
			'Log(mresult)
			 Case "PilihCategory"			 
			 	Dim Pilih1,pilih2 As List
			 	lvCateFilm.Clear
			 
			 	'Log(Pilih1)
			 	Pilih1.Initialize
			 	Pilih1 = mresult.Get("Data") 
			 
			 	pilih2.Initialize
			 
				For Each colCategory As Map In Pilih1
					lvCateFilm.AddSingleLine(colCategory.Get("name"))
					
				Next
				
				Dim TimeFont1 As Typeface
		
			'	TimeFont1 = Typeface.LoadFromAssets ("gotham-medium.ttf")			
			'	lvCateFilm.TwoLinesAndBitmap.Label.TextColor = Colors.White
				lvCateFilm.SingleLineLayout.Label.Gravity = Gravity.CENTER  
			'	lvCateFilm.TwoLinesAndBitmap.Label.Left = lvCateFilm.TwoLinesAndBitmap.ImageView.Width + 7dip
				Fungsi.SetDivider(lvCateFilm, Colors.Transparent, 1dip)

				lvCateFilm.FastScrollEnabled = False
				

'				'lvCateFilm.SingleLineLayout.Label.Gravity = Gravity.CENTER_HORIZONTAL
'				'lvCateFilm.SingleLineLayout.Label.TextSize = 14
'				'lvCateFilm.SingleLineLayout.ItemHeight = 50dip
'				lvCateFilm.SingleLineLayout.Label.Typeface = TimeFont1
'				
			
				If lvCateFilm.Size > 0 Then
					lblNamaFilm.Text = lvCateFilm.GetItem(0)
					lvCateFilm_ItemClick(0, lvCateFilm.GetItem(0) & "#Awal")
				End If
			 

			#End Region
			#Region CateMovies
			Case "CateMovies"
				Dim Cate1,Cate2 As List
			 	Dim Cate3 As String
			 	Cate1.Initialize
			 	Cate1 = mresult.Get("Data")
			 
			 	Cate2.Initialize
			 
				For Each colFilm As Map In Cate1
					Cate2 = colFilm.Get("mSubcategories")
					Cate3 = colFilm.Get("name")
					If MNChttp.Tag = colFilm.Get("name") Then
						nilai = colFilm.Get("name")
						Exit
					End If
				Next
			
				Dim CateSlug As List
				
				CateSlug.Initialize
			
				For Each colname As Map In Cate2
					CateSlug.Add(colname.Get("name"))
				Next
			
				CreatePanel(CateSlug)
			#End Region
			#Region Slide Gambar
			Case "SlideCate"	
		'	Log(mresult)
				Dim Slide1,slide2 As List
	'			Dim Slide3 As String
			 
				Slide1.Initialize
				Slide1 = mresult.Get("Data")
			 
				slide2.Initialize
			 
				For Each colSlide As Map In Slide1
'				slide2 = colSlide.Get("contentSubcategories")
'				Slide3 = colSlide.Get("thumb_poster")
'				Log(colSlide.Get("mProductDetails"))
'	'			Cate2 = colFilm.Get("mSubcategories")
'	'			Cate3 = colFilm.Get("name")
	'			Log(colSlide.Get("slug"))
	'			Exit
				Next
			#End Region
			#Region  Pager_PageChanged
			Case "SlugPager"
'			Log(mresult)
				Dim Page1 	As Map
				Dim Page2 	As List
				Dim SubCate As String
				Private x1,x2	As Int

				Page1.Initialize
				Page1 = mresult.Get("Data")
			
				Page2.Initialize

				SubCate = Page1.Get("subcategory")
				Page2 = Page1.Get("data")
				
				Dim PlugPanel 		As Panel = mapPager.GetValueAt(MNChttp.Tag)
				Dim PlugGrid  		As PhotoGridView = mapGrid.GetValueAt(MNChttp.Tag)
				Dim strPisah()		As String

				PlugPanel.RemoveAllViews

				PlugGrid.Initialize("Video")	
				PlugGrid.CacheInMemory = True
				PlugGrid.CacheOnDisk = True
				PlugGrid.CompressQuality = 90
				PlugGrid.NumColumns = 2
				PlugGrid.RoundedBitmap = True
				'PlugGrid.HorizontalSpacing = 5dip
		  		'PlugGrid.VerticalSpacing = 10dip
		  		PlugGrid.Gravity = Gravity.FILL
				PlugGrid.ScaleType = PlugGrid.ScaleType.Fit_Center
		  		PlugGrid.ItemWidth = -50dip
				'PlugGrid.ItemWidth = 20%x
				PlugGrid.ItemHeight = 30dip
				PlugGrid.HorizontalSpacing = 1%x
				PlugGrid.VerticalSpacing = 1%y
				PlugGrid.Enabled = True
				PlugGrid.ProgressBarIndeterminate = False
				PlugGrid.ProgressBarVisible = True 

				
			'	Log(Page2)
				Dim Contentvideo As Map
				Dim i 			 As Int=0
				
				Contentvideo.Initialize
				Contentvideo.Clear
				
				picture.Clear
				For Each colHal As Map In Page2
			'	Log(colHal)
					For x2=0 To colHal.Size-1
						Select colHal.GetKeyAt(x2)
						Case "slug"
							PlugGrid.AddImageFromWeb("http://i.moviebay.com/content_images/" & colHal.Get("slug") & "/poster.jpg")
							'Log(Fungsi.GetParentPath(gvPanel.GetItem(0)))
						End Select
'						If colHal.Size-1 Then 
'						picture.Put(i, Contentvideo)
'						i= i + 1 
'						End If

					Next
						picture.Put(i, colHal)
						i = i + 1
						'For i = i + 1 To 0 Step - 1
						'Contentvideo.Clear
						'PlugGrid.Tag = Contentvideo
					
				Next
				'Next				
				PlugPanel.AddView(PlugGrid, 0, 0, pMenuMain.Width - 30dip, pMenuMain.Height - 15dip)
'				pMenuMain.SetBackgroundImage(LoadBitmapSample(" ",Fungsi.GetFullName(PlugGrid.GetItem(0)),32,32))
				
			
			#End Region
			 #Region Menu kiri
			Case "MenuKiri"
			Log(mresult)
				Dim New 	As Map
				Dim Top 	As List
				Dim SubKiri As String
				Private k1,k2	As Int

				New.Initialize
				New = mresult.Get("Data")
			
				Top.Initialize

				SubKiri = New.Get("subcategory")
				Top = New.Get("data")
				
				Dim PlugPanel As Panel = mapPager.GetValueAt(MNChttp.Tag)
				Dim PlugGrid  As PhotoGridView = mapGrid.GetValueAt(MNChttp.Tag)
				Dim strPisah()		As String

				PlugPanel.RemoveAllViews
			
				PlugGrid.Initialize("Video")	
				PlugGrid.CacheInMemory = True
				PlugGrid.CacheOnDisk = True
				PlugGrid.CompressQuality = 90
				PlugGrid.NumColumns = 2
				PlugGrid.RoundedBitmap = False
				PlugGrid.HorizontalSpacing = 0
		  		PlugGrid.VerticalSpacing = 15dip
		  		PlugGrid.Gravity = Gravity.CENTER
				PlugGrid.ScaleType = PlugGrid.ScaleType.Fit_Center
		  		PlugGrid.ItemWidth = 20%x
		  		PlugGrid.ItemHeight = 60%y
				PlugGrid.Enabled = True
				PlugGrid.ProgressBarIndeterminate = False
				PlugGrid.ProgressBarVisible = True 
				
				
			'	Log(Page2)
				Dim Contentvideo As Map
				Dim i 			 As Int=0
				
				Contentvideo.Initialize
				Contentvideo.Clear
				
				picture.Clear
				For Each colKiri As Map In Top
				'Log(colHal)
					For x2=0 To colKiri.Size-1
						Select colKiri.GetKeyAt(x2)
						Case "slug"
							PlugGrid.AddImageFromWeb("http://i.moviebay.com/content_images/" & colKiri.Get("slug") & "/poster.jpg")
'							Contentvideo.Put("slug", "http://202.147.192.113/moviebay/backend/content_images/" & colHal.Get("slug") & "/poster.jpg")
						End Select


					Next
						picture.Put(i, colKiri)
						i = i + 1
						'For i = i + 1 To 0 Step - 1
						'Contentvideo.Clear
						'PlugGrid.Tag = Contentvideo
					
				Next
							
				PlugPanel.AddView(PlugGrid, 0, 0, pMenuMain.Width - 30dip, pMenuMain.Height - 15dip)				
			#End Region
			End Select
		End If
	End If	
End Sub

Sub Init_slug (Movies As String)

	Dim HomeJob As MNChttpJob
	Dim	strEnc	As String
	Dim Gen		As JSONGenerator
	Dim mCate	As Map	
	
	mCate.Initialize
	mCate.Clear
'	mCate.Put(Fungsi.mpList.Get("slug"),"slug")
	Gen.Initialize(mCate)
	'Log(Gen.ToPrettyString(2))
	HomeJob.Initialize("SlugMovies", Me)
	
		Try
		
			HomeJob.Tag = "SlugMovies"
			strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(2))
			If strEnc = "Enkripsi Failed" Then Return
			HomeJob.PostString(Fungsi.mpList.Get("SlugURL"), "teks=" & strEnc)
			HomeJob.GetRequest.SetHeader("Cache-Control", "no-cache, must-revalidate")
			HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60
		Catch
			Msgbox2("Cetgory Failure !", "Moviebay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))
		End Try
End Sub

Sub Pager_PageChanged (Position As Int)
	Dim HomeJob As MNChttpJob
	Dim	strEnc	As String
	Dim Gen		As JSONGenerator
	Dim mPage	As Map	
		
	mPage.Initialize
	mPage.Clear
	mPage.Put(Fungsi.mpList.Get("Variabel"), container.GetTitle(Position).ToLowerCase)
	Gen.Initialize(mPage)
	HomeJob.Initialize("SlugPager", Me)
	
	Try
		HomeJob.Tag = Position
		strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(2))
		If strEnc = "Enkripsi Failed" Then Return
		HomeJob.PostString(Fungsi.mpList.Get("SlugURL"), "teks=" & strEnc)
		HomeJob.GetRequest.SetHeader("Cache-Control", "no-cache, must-revalidate")
		HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60
	Catch
		Msgbox2("Cetgory Failure !", "Moviebay", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))
	End Try
End Sub
Sub lblUser_Click
	StartActivity("Profile")
End Sub
Sub lblLg_Click
	'StartActivity("Profil")
End Sub
Sub pMenu_Click
	Cek_Menu
End Sub