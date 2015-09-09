Type=StaticCode
Version=4
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

	Type FormatConfig(aid As String, rsn As String, imei As String, _
	sTab As String, MacAddr As String, FolderDBS As String, _
	Username As String, Password As String, Token As String, sStorage As String, FolderMedia As String)
	
	Type FileData (Dir As String, FileName As String, KeyName As String, ContentType As String)

	Dim	mpList					As Map
	Dim	DirDBS					As String
	Dim	DirMedia				As String
	Dim DataConfig				As FormatConfig
	Dim raf						As RandomAccessFile

	Dim szAndroidID 			As String
	Dim szRefSerial 			As String
	Dim szIMEI					As String
	Dim szIMSI					As String
	Dim szManufacturer			As String
	Dim szModel					As String
	Dim	szProduct				As String
	Dim	szSubsID				As String

	Dim APIver 					As Int
	Dim refl 					As Reflector
	Dim myPhone 				As Phone
	Dim thisPhone 				As PhoneId
	Dim mKoneksi 				As Map
	Dim boundary 				As String
	
	Dim CateMap 				As Map
	Dim MoviesMap	 			As Map
	Dim ChannelMap 				As Map
	Dim SeriesMap 				As Map
	Dim TrailerMap				As Map
End Sub

Sub SetDirProg(FolderBaru As String)
	If File.ExternalWritable Then DirDBS = File.DirDefaultExternal Else DirDBS = File.DirInternal
	If File.ExternalWritable Then DirMedia = File.DirRootExternal & "/DCIM/Camera" Else DirMedia = File.DirInternal

	If (File.IsDirectory(DirDBS, FolderBaru) <> True) AND (FolderBaru.Trim <> "") Then
		File.MakeDir(DirDBS, FolderBaru)
	End If

	DirDBS = DirDBS & "/" & FolderBaru & "/"
	mpList.Initialize
	mpList.Remove("DirDBS")
	mpList.Remove("DirMedia")
	mpList.Put("DirDBS", DirDBS)
	mpList.Put("DirMedia", DirMedia)
End Sub

Sub SetFileConfig As Boolean
	If File.Exists(DirDBS, "setting") = False Then
		Dim myWifi 		As ABWifi
		Dim strMAC		As String

		If myWifi.ABLoadWifi() = True Then
			Try
				strMAC = myWifi.ABGetCurrentWifiInfo().MacAddress
				strMAC = strMAC.Replace(":", "-")
			Catch
				strMAC = "00-00-00-00-00-00"
			End Try
  	Else
			strMAC = "00-00-00-00-00-00"
  	End If
	
		DataConfig.aid  				= szAndroidID
		DataConfig.rsn  				= szRefSerial
		DataConfig.imei 				= szIMEI
		DataConfig.Username	 			= "Guest"
		DataConfig.Password	 			= "None"
		DataConfig.MacAddr 				= strMAC
		DataConfig.sTab					= "Video"
		DataConfig.FolderDBS			= DirDBS
		DataConfig.Token				= "None"		
		DataConfig.sStorage				= "Storage"
		DataConfig.FolderMedia			= DirDBS
		Return TulisFileConfig(DataConfig)
	Else
		'File.Delete(DirDBS, "setting")
		Return True
	End If
End Sub

Sub TulisFileConfig(IsiConfig As FormatConfig) As Boolean	
	Dim mpTemp		As Map
	
	mpTemp.Initialize
	mpTemp.Clear
	mpTemp.Put("DataConfig", IsiConfig)
	
	Try
		File.Delete(DirDBS, "setting")
		raf.Initialize(DirDBS, "setting", False)
		raf.WriteEncryptedObject(mpTemp, szAndroidID & szRefSerial & szIMEI, raf.CurrentPosition)
		raf.Close
	Catch
		Msgbox2("Init data failed", "ezSchool", "OK", "", "", LoadBitmap(File.DirAssets, "Warning.png"))
		Return False
	End Try
	
	Return True
End Sub

Sub InfoDevice	
	'First Determine the version
	APIver = refl.GetStaticField("android.os.Build$VERSION", "SDK_INT")
	If APIver < 9 Then
		szRefSerial = "n/a"
	Else
		szRefSerial = refl.GetStaticField("android.os.Build", "SERIAL")
	End If
	
	szAndroidID = myPhone.GetSettings("android_id")
	szManufacturer = myPhone.Manufacturer
	szModel = myPhone.Model
	szProduct = myPhone.Product
	szIMEI = thisPhone.GetDeviceId
	szIMSI = thisPhone.GetSimSerialNumber
	szSubsID = thisPhone.GetSubscriberId
End Sub

Sub BacaFileConfig As Boolean
	Dim mpTemp	As Map
		
	mpTemp.Initialize
	mpTemp.Clear
	
	InfoDevice

	If File.Exists(DirDBS, "setting") = False Then
		If SetFileConfig = False Then ExitApplication
	End If	

	Try
		raf.Initialize(DirDBS, "setting", False)
		mpTemp = raf.ReadEncryptedObject(szAndroidID & szRefSerial & szIMEI, raf.CurrentPosition)
		raf.Flush
		raf.Close
	Catch
		Msgbox2("Invalid Application !", "ezSchool", "OK", "", "", LoadBitmap(File.DirAssets, "Warning.png"))
		If File.Exists(DirDBS, "setting") Then File.Delete(DirDBS, "setting")
		Return False
	End Try

	DataConfig = mpTemp.Get("DataConfig")	
	Return True
End Sub

Sub DisplaySize(SizeOct As Double) As String
	Dim txtUnits(4) As String
	txtUnits = Array As String("bytes", "Kb", "Mb", "Gb")
	Dim Unité As Int
	Unité = 0
	Do While SizeOct > 1024
		Unité = Unité + 1
		SizeOct = SizeOct / 1024
	Loop
	If SizeOct <> Floor(SizeOct) Then
		Return NumberFormat(SizeOct, 1, 1) & " " & txtUnits(Unité)
	Else
		Return SizeOct & " " & txtUnits(Unité)
	End If
End Sub

Sub GetParentPanel(MyView As View, MyPanel As Panel) As View
	Dim r As Reflector
	Dim v, Parent As View

	r.Target = MyView
	v = r.Target
	Parent = r.RunMethod("getParent")

	Do While Parent <> MyPanel
		r.Target = Parent
		v = r.Target
		Parent = r.RunMethod("getParent")
	Loop
	
	Return v
End Sub

Sub GetDrawable(Name As String) As Object
	 Dim r As Reflector
	 Dim package As String
	 Dim id As Int
	 package = r.GetStaticField("anywheresoftware.b4a.BA", "packageName")
	 id = r.GetStaticField(package & ".R$drawable", Name)
	 r.Target = r.GetContext
	 r.Target = r.RunMethod("getResources")
	 Return r.RunMethod2("getDrawable", id, "java.lang.int")
End Sub

Sub CreateStateListDrawable(bmp As Bitmap,cmd As View )
  Dim C As Canvas
  Dim r1 As Rect
  Dim r2 As Rect
  Dim sld As StateListDrawable
  Dim Padding As Int
  Padding = 0
  'Create a temporary button and draw on it
  C.Initialize(cmd)
  r1.Initialize(0,0,cmd.Width,cmd.Height)
  r2.Initialize(Padding,Padding,cmd.Width-Padding,cmd.height-Padding)
  sld.Initialize

  C.DrawColor(Colors.RGB(58, 162, 203))
  C.DrawBitmap(bmp,Null,r2)
  cmd.Invalidate
  sld.AddState(sld.State_Pressed,cmd.Background)

  Dim bmd As BitmapDrawable
  bmd.Initialize(bmp)
  sld.AddState(sld.State_Enabled, bmd)

  cmd.Background = sld
End Sub

Sub CreateScaledBitmap(Original As Bitmap, Width As Int, Height As Int) As Bitmap
	Dim r As Reflector
	Dim b As Bitmap
	b = r.RunStaticMethod("android.graphics.Bitmap", "createScaledBitmap", _
			Array As Object(Original, Width, Height, True), _
			Array As String("android.graphics.Bitmap", "java.lang.int", "java.lang.int", "java.lang.boolean"))
	Return b
End Sub

Sub GetFullName(FullPath As String) As String
	Return FullPath.SubString(FullPath.LastIndexOf("/")+1)
End Sub

Sub GetFileName(FullPath As String) As String
	Dim strTemp As String
  strTemp = GetFullName(FullPath)
	Return strTemp.SubString2(0, strTemp.LastIndexOf("."))
End Sub

Sub GetFileExt(FullPath As String) As String
  Return FullPath.SubString(FullPath.LastIndexOf(".")+1)
End Sub

Sub GetParentPath(Path As String) As String
	Dim Path1 As String 
	Dim L 		 As Int
	If Path = "/" Then
	  Return "/"
	End If
	L = Path.LastIndexOf("/")
	If L = Path.Length - 1 Then
	  'Strip the last slash
	  Path1 = Path.SubString2(0,L)
	Else
	  Path1 = Path
	End If
	L = Path.LastIndexOf("/")
	If L = 0 Then
	  L = 1
	End If
	Return Path1.SubString2(0,L)
End Sub

Public Sub AdjustBitmap(bmp As Bitmap, Width As Int, Height As Int) As Bitmap
	Dim RatioBmp, RatioIV As Float
	RatioBmp = bmp.Width / bmp.Height
	RatioIV = Width / Height
	Dim Diviseur As Float
	If RatioIV > RatioBmp Then
		Diviseur = bmp.Height / Height
		bmp = CreateScaledBitmap(bmp, Round(bmp.Width / Diviseur / Density), Round(Height / Density))
	Else
		Diviseur = bmp.Width / Width
		bmp = CreateScaledBitmap(bmp, Round(Width / Density), Round(bmp.Height / Diviseur / Density))
	End If
	Return bmp
End Sub

Sub AdjustImageView(Imv As ImageView, Img As Bitmap)
  Private bmp As Bitmap = Img

  Dim Delta, Height, Width As Int
  If bmp.Width / bmp.Height > Imv.Width / Imv.Height Then
      Height = bmp.Height / bmp.Width * Imv.Width
      Delta = (Imv.Height - Height) / 2
      Imv.Height = Height
      Imv.Top = Imv.Top + Delta
  Else
      Width = bmp.Width / bmp.Height * Imv.Height
      Delta = (Imv.Width - Width) / 2
      Imv.Width = Width
      Imv.Left = Imv.Left + Delta
  End If
  Imv.Gravity = Gravity.FILL
  Imv.Bitmap = bmp
End Sub

Sub ProsesEnkripsi(Data As String) As String
	Dim peKey 		As String
	Dim pestrEnc 	As String
	Dim pesutils 	As StringUtils

	Try
		peKey = Keygen
		pestrEnc = Wrap(Data, peKey)
		pestrEnc = pesutils.EncodeBase64(pestrEnc.GetBytes("UTF8"))
'		pestrEnc = pesutils.EncodeUrl(pestrEnc, "UTF8")
	Catch
		Msgbox2("Process Failed !", "IKR Playbox", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))			
		Return "Enkripsi Failed"
	End Try
	
	Return pestrEnc
End Sub

Sub Keygen() As String
	Dim sr As SecureRandom
	Dim gkey, lCode, rHead, charArray As String
	Dim i, length As Int
	
	charArray = "zyxwvutsrqponmlkjihgfedcba0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	
	length = Rnd(10, 15)
	lCode = Bit.ToHexString(length)

	For i = 0 To 2
		rHead = rHead&charArray.CharAt(Rnd(0,61))
	Next

	For i = 0 To length-1
		gkey = gkey&charArray.CharAt(Rnd(0,61))
	Next

	gkey = rHead&lCode&gkey
	
	Return gkey
End Sub

Sub Wrap(Text As String, vkey As String) As String
	Dim trueKey, encrypted, wrapped As String
	Dim len As Int
	
	len = Bit.ParseInt(vkey.CharAt(3),16)
	trueKey = vkey.SubString2(4,4+len)
	encrypted = EnkripDekrip("AES", trueKey, "MD5", Text, "Encode")
	wrapped = vkey & encrypted

	Return wrapped
End Sub

Sub EnkripDekrip(JenisEnkripsi As String, KataKunci As String, JenisHash As String, IsiData As String, PilihMetode As String) As String
	Dim kg 					As KeyGenerator
	Dim md 					As MessageDigest
	Dim C 					As Cipher
	Dim hasilnya() 			As Byte
	Dim	bc					As ByteConverter
  	Dim su 					As StringUtils
	
	kg.Initialize(JenisEnkripsi)
	kg.KeyFromBytes(md.GetMessageDigest(KataKunci.GetBytes("UTF8"), JenisHash))
	C.Initialize(JenisEnkripsi)
	
	Try
		If PilihMetode = "Enkripsi" OR PilihMetode = "Encode" Then
			hasilnya = C.Encrypt(IsiData.GetBytes("UTF8"), kg.Key, False)
		Else
		If PilihMetode = "Decode" Then
			hasilnya = su.DecodeBase64(IsiData)
			hasilnya = C.Decrypt(hasilnya, kg.Key, False)
		Else
			hasilnya = bc.HexToBytes(IsiData)
			hasilnya = C.Decrypt(hasilnya, kg.Key, False)
		End If
		End If
	Catch
		ProgressDialogHide
		DoEvents
		Return False
	End Try
	
	If PilihMetode = "Enkripsi" Then
		Return bc.HexFromBytes(hasilnya)
	Else
	If PilihMetode = "Encode" Then
		Return su.EncodeBase64(hasilnya)
	Else
		Return bc.StringFromBytes(hasilnya, "UTF8")
	End If
	End If
End Sub

Sub CacahJSON(DataJSON As String, Status As String) As Boolean
	Dim parser 	As JSONParser	
	
	CateMap.Initialize
	CateMap.Clear
	MoviesMap.Initialize
	MoviesMap.Clear
	ChannelMap.Initialize
	ChannelMap.Clear
	SeriesMap.Initialize
	SeriesMap.Clear
	TrailerMap.Initialize
	TrailerMap.Clear
	

	Try	
		parser.Initialize(DataJSON)
	Catch
		Msgbox2("Process Failure !", "IKR Playbox", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))			
		Return False
	End Try
	
	Select Status
		Case "NextArray"
			Try
				Dim root As List = parser.NextArray
			Catch
				Return False
			End Try

			
			For Each colroot As Map In root
 				Dim IPAddr0 As String = colroot.Get("IPAddr0") 
 				Dim IPAddr1 As String = colroot.Get("IPAddr1") 
 				Dim IPAddr2 As String = colroot.Get("IPAddr2") 
 				Dim IPAddr3 As String = colroot.Get("IPAddr3") 
 				Dim IPAddr4 As String = colroot.Get("IPAddr4") 
 				Dim Port0	As String = colroot.Get("Port0")
 				Dim Port1	As String = colroot.Get("Port1")
		
				Dim Authentication As List = colroot.Get("Authentication") 
				For Each colAuthentication As Map In Authentication
   				mpList.Put("ugc_username", colAuthentication.Get("ugc_username"))
   				mpList.Put("ugc_password", colAuthentication.Get("ugc_password"))
   				mpList.Put("Username", colAuthentication.Get("Username"))
   				mpList.Put("Password", colAuthentication.Get("Password"))
				Next

				Dim MenuList As List = colroot.Get("MenuCategory") 
				For Each colMenu As Map In MenuList
   				CateMap.Put("Cate0", colMenu.Get("Cate0"))
   				CateMap.Put("Cate1", colMenu.Get("Cate1"))
   				CateMap.Put("Cate2", colMenu.Get("Cate2"))
				Next

				Dim MovieList As List = colroot.Get("MenuMovies") 
				For Each colMovies As Map In MovieList
   				MoviesMap.Put("Movies0", colMovies.Get("Movies0"))
   				MoviesMap.Put("Movies1", colMovies.Get("Movies1"))
   				MoviesMap.Put("Movies2", colMovies.Get("Movies2"))
				MoviesMap.Put("Movies3", colMovies.Get("Movies3"))
				Next
				
				Dim TrailerList As List = colroot.Get("MenuTrailer")
				For Each colTrailer As Map In TrailerList
				TrailerMap.Put("Trailer0", colTrailer.Get("Trailer0"))
				Next

				Dim ChannelList As List = colroot.Get("MenuChannel") 
				For Each colChannel As Map In ChannelList
   				ChannelMap.Put("Channel0", colChannel.Get("Channel0"))
'   			ChannelMap.Put("Channel1", colChannel.Get("Channel1"))
'   			ChannelMap.Put("Channel2", colChannel.Get("Channel2"))
'   			ChannelMap.Put("Channel3", colChannel.Get("Channel3"))
'   			ChannelMap.Put("Channel4", colChannel.Get("Channel4"))
'   			ChannelMap.Put("Channel5", colChannel.Get("Channel5"))
				Next

				Dim SeriesList As List = colroot.Get("MenuSeries") 
				For Each colSeries As Map In SeriesList
   				SeriesMap.Put("Series0", colSeries.Get("Series0"))
' 				SeriesMap.Put("Series1", colSeries.Get("Series1"))
'   				SeriesMap.Put("Series2", colSeries.Get("Series2"))
'   				SeriesMap.Put("Series3", colSeries.Get("Series3"))
'   				SeriesMap.Put("Series4", colSeries.Get("Series4"))
				Next

				Dim RegisterList As List = colroot.Get("Register") 
				For Each colRegister As Map In RegisterList
				mpList.Put("RegisterURL", colRegister.Get("RegisterURL"))
   				mpList.Put("firstname", colRegister.Get("firstname"))
   				mpList.Put("lastname", colRegister.Get("lastname"))
   				mpList.Put("regemail", colRegister.Get("regemail"))
   				mpList.Put("handphone", colRegister.Get("handphone"))
   				mpList.Put("regpass", colRegister.Get("regpass"))
   				mpList.Put("gender", colRegister.Get("gender"))
				mpList.Put("homephone", colRegister.Get("homephone"))
				mpList.Put("id_city", colRegister.Get("id_city"))
				mpList.Put("zipcode", colRegister.Get("zipcode"))
				Next
				
				Dim LoginList As List = colroot.Get("Login") 
				For Each colLogin As Map In LoginList
				mpList.Put("LoginURL", colLogin.Get("LoginURL"))
   				mpList.Put("username", colLogin.Get("username"))
				mpList.Put("loginpass", colLogin.Get("loginpass"))
				Next
				
				Dim CategoryList As List = colroot.Get("Category")
				For Each colCategory As Map In CategoryList
				mpList.Put("CateURL", colCategory.Get("CateURL"))
				mpList.Put("sliding", colCategory.Get("sliding"))
				Next
				
				Dim PilihCateList As List = colroot.Get("PilihCategory")
				For Each colPilihCate As Map In PilihCateList
				mpList.Put("ListCateURL", colPilihCate.Get("ListCateURL"))
				mpList.Put("slidng1", colPilihCate.Get("sliding1"))
				Next
				
				Dim MoviesList As List = colroot.Get("CateMovies")
				For Each colCateMovies As Map In MoviesList
				mpList.Put("MoviesURL", colCateMovies.Get("MoviesURL"))
				mpList.Put("slug", colCateMovies.Get("slug"))
				Next
				
				Dim SlideCateList As List = colroot.Get("SlideCate")
				For Each colSlideCate As Map In SlideCateList
				mpList.Put("SlideURL", colSlideCate.Get("SlideURL"))
				mpList.Put("Variabel", colSlideCate.Get("Variabel"))
				Next
				
				Dim slugList As List = colroot.Get("SlugPager")
				For Each colSlugPage As Map In slugList
				mpList.Put("SlugURL", colSlugPage.Get("SlugURL"))
				mpList.Put("Variabel", colSlugPage.Get("Variabel"))
				mpList.Put("Slug", colSlugPage.Get("Slug"))
				Next	
				
				Dim slugSearch As List = colroot.Get("SearchPage")
				For Each colcari As Map In slugSearch
				mpList.Put("SearchURL", colcari.Get("SearchURL"))
				Next
				
				
				
'				Dim Home As List = colroot.Get("MNCUGCAPI")
'				For Each colHome As Map In Home
'					Dim Category As List = colHome.Get("USERUGCLOGIN") 
' 					For Each colCategory As Map In Category
'   					mpList.Put("LoginURL", colCategory.Get("LoginURL"))
'   					mpList.Put("LoginAct", colCategory.Get("LoginAct"))
'					Next
'				
'					Dim cust_type As List = colHome.Get("VIDEOINFOUGC") 
' 					For Each colcust_type As Map In cust_type
'   					mpList.Put("InfoURL", colcust_type.Get("InfoURL"))
'   					mpList.Put("InfoAct", colcust_type.Get("InfoAct"))
'   					mpList.Put("title", colcust_type.Get("title"))
'   					mpList.Put("cat_id", colcust_type.Get("cat_id"))
'   					mpList.Put("description", colcust_type.Get("description"))
'   					mpList.Put("tags", colcust_type.Get("tags"))
'					Next				
'
'					Dim unreg As List = colHome.Get("CATEGORYUGC") 
' 					For Each colcust_type As Map In unreg
'   					mpList.Put("CateURL", colcust_type.Get("CateURL"))
'   					mpList.Put("CateAct", colcust_type.Get("CateAct"))
'					Next				
'
'					Dim logout As List = colHome.Get("VIDEOFILEUGC") 
' 					For Each colcust_type As Map In logout
'   					mpList.Put("FileURL", colcust_type.Get("FileURL"))
'   					mpList.Put("FileAct", colcust_type.Get("FileAct"))
'   					mpList.Put("Session_Upload", colcust_type.Get("Session_Upload"))
'   					mpList.Put("VideoUpload", colcust_type.Get("VideoUpload"))
'					Next				
'				Next
			Next
			
			mKoneksi = colroot
	End Select

	Return True
End Sub

Sub UnWrap(Text As String) As String
	Dim truekey, unwrapped As String
	Dim len As Int
	
	len = Bit.ParseInt(Text.CharAt(3),16)
	truekey = Text.SubString2(4,4+len)
	unwrapped = EnkripDekrip("AES", truekey, "MD5", Text.SubString(4+len), "Decode")
	
	Return unwrapped
End Sub

Sub CreatePostRequest(NameValues As Map, Files As List) As OutputStream
	boundary = "---------------------------1461124740692"
	Dim stream As OutputStream
	stream.InitializeToBytesArray(20)
	Dim EOL As String
	EOL = Chr(13) & Chr(10) 'CRLF constant matches Android end of line character which is chr(10).
	Dim b() As Byte
	If NameValues <> Null AND NameValues.IsInitialized Then
		'Write the name/value pairs
		Dim key, value As String
		For i = 0 To NameValues.Size - 1
			key = NameValues.GetKeyAt(i)
			value = NameValues.GetValueAt(i)
			b = ("--" & boundary & EOL & "Content-Disposition: form-data; name=" _ 
				& QUOTE & key & QUOTE & EOL & EOL & value & EOL).GetBytes("UTF8")
			stream.WriteBytes(b, 0, b.Length)
		Next
	End If
	If Files <> Null AND Files.IsInitialized Then
		'write the files
		Dim FD As FileData
		For i = 0 To Files.Size - 1
			FD = Files.Get(i)
			b = ("--" & boundary & EOL & "Content-Disposition: form-data; name=" _ 
				& QUOTE & FD.KeyName & QUOTE & "; filename=" & QUOTE & FD.FileName & QUOTE _
				& EOL & "Content-Type: "  & FD.ContentType & EOL & EOL).GetBytes("UTF8")
			stream.WriteBytes(b, 0, b.Length)
			Dim In As InputStream
			In = File.OpenInput(FD.Dir, FD.FileName)
			File.Copy2(In, stream) 'read the file and write it to the stream
			b = EOL.GetBytes("UTF8")
			stream.WriteBytes(b, 0, b.Length)
		Next
	End If
	b = (EOL & "--" & boundary & "--" & EOL).GetBytes("UTF8")
	stream.WriteBytes(b, 0, b.Length)
	Return stream
End Sub

Sub SetDivider(lv As ListView, Color As Int, Height As Int)
   Dim r As Reflector
   r.Target = lv
   Dim CD As ColorDrawable
   CD.Initialize(Color, 0)
   r.RunMethod4("setDivider", Array As Object(CD), Array As String("android.graphics.drawable.Drawable"))
   r.RunMethod2("setDividerHeight", Height, "java.lang.int")
End Sub