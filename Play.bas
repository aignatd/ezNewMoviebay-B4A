Type=Activity
Version=4
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@

#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: False
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private ctoast				As ToastMessageShow
	Private ViMediaControl		As Vitamio_MediaController
	Private ViVideoView 		As Vitamio_VideoView
	Private PhPlayer			As Phone
	Private pVideo 				As Panel
	Private tVideo				As Timer
	Private GifPlayer 			As MovieViewControl
	Private BD					As BetterDialogs
	Private Params				As BD_CustomDlgParams
	Private ivYes 				As ImageView
	Private ivNo 				As ImageView
	Private ivInfo 				As ImageView
	Private iTekan				As Int=0
	Private ivMenu 				As ImageView
	
	Private Spasi				As Int
	Private Lebar				As Int
	Private Tinggi				As Int
	Private Durasi				As Int
	Private jeday				As Int
	Private Device				As Int
	Private pcMNCShop			As PhoneCalls
	Private bCall 				As Button
	Private bBeli 				As Button
	Private etNama 				As EditText
	Private etEmail 			As EditText
	Private etTelp 				As EditText
	Private pPesan 				As Panel
	
	Private mDaftar				As Map

	Private etName 				As EditText
	Private etMail 				As EditText
	Private etHp 				As EditText
	Private bReset 				As Button
	Private bCacl 				As Button
	Private wvOrder 			As WebView
	Private tmrLoad 			As Timer
	Private wve 				As WebViewExtras
	Private iInitWeb			As Int=0
	Private wvs					As WebViewXtender
	Private lblProg 			As Label
	Private Page				As String
	Private PE					As PhoneEvents
	Private pInfo 				As Panel
	Private ivLogo 				As ImageView
	Private ivWeb 				As ImageView
	Private ivCall 				As ImageView
	
	Dim APIver 					As Int
	Dim szAndroidID 			As String
	Dim szRefSerial 			As String
	Dim szIMEI					As String
	Dim szIMSI					As String
	Dim szManufacturer			As String
	Dim szModel					As String
	Dim	szProduct				As String
	Dim	szSubsID				As String
	Dim refl 					As Reflector
	Dim myPhone 				As Phone
	Dim thisPhone 				As PhoneId
	Private bSMS 				As Button
	Private smsMNCshop 			As PhoneSms
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	#Region Deteksi screen layar gadget
	If GetDeviceLayoutValues.ApproximateScreenSize < 5 Then
  	 'phones
   	If 100%x > 100%y Then
			Spasi = -22
			Lebar = 45%x
			Tinggi = 35%y
			Durasi = 800
			jeday = 0.2%y
		Else
			Spasi = -17
			Lebar = 45%x
			Tinggi = 35%y
			Durasi = 800
			jeday = 0.5%y
		End If
		
		Device = 0
	Else
  	 'tablets
		Spasi = -75
		Lebar = 50%x
		Tinggi = 40%y
		Durasi = 700
		jeday = 0.5%y

		Device = 1
	End If
	#End Region

	Activity.LoadLayout("VideoPlay")
	
'	InfoDevice
'	If szManufacturer.ToLowerCase <> "asus" Then
'		Msgbox2("unofficial device for this application !", "MNC Shop", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))
'		ExitApplication
'	End If
'	
'	If szModel.ToLowerCase.StartsWith("asus") = False Then 
'		Msgbox2("unofficial device for this application !", "MNC Shop", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))
'		ExitApplication
'	End If

	
	Activity.AddMenuItem2("Low Quality", "Low", LoadBitmap(File.DirAssets, "128_ic.png"))
	Activity.AddMenuItem2("Medium Quality", "Medium", LoadBitmap(File.DirAssets, "256_ic.png"))
	Activity.AddMenuItem2("High Quality", "High", LoadBitmap(File.DirAssets, "512_ic.png"))
	Activity.AddMenuItem2("Very High Quality", "Very", LoadBitmap(File.DirAssets, "1024_ic.png"))
	Activity.AddMenuItem2("Exit Application", "Exit", LoadBitmap(File.DirAssets, "exit_menutop.png"))

	ctoast.Initialize("")
	ctoast.Location = ctoast.Gravity.CENTER_BOTTOM
	ctoast.IconSize48x48
	ctoast.TextLocation = ctoast.Gravity.TLeft
	ctoast.TextSize = 16
	ctoast.Duration = 3000
	ctoast.RoundedRect = 8
	ctoast.Height = 10
	
	ViVideoView.Initialize("ViVideoView")
	ViMediaControl.Initialize("ViMediaControl")

	PhPlayer.SetScreenOrientation(0)
	GifPlayer.Load(File.DirAssets, "hourglass.gif", True)
	GifPlayer.Paused = False
	GifPlayer.Visible = False
		
	Dim PhoneId As PhoneId
    PE.InitializeWithPhoneState("PE",PhoneId)
	
	pVideo.RemoveAllViews
	pVideo.AddView(ViVideoView, 0, 0, pVideo.Width, pVideo.Height)			

	ViVideoView.SetVideoPath("http://livetv.mncplaymedia.com/cdn/iptv/Tvod/001/channel2000037/512.m3u8")
	PlayVideo
End Sub

Sub Low_Click
	ViVideoView.SetVideoPath("http://livetv.mncplaymedia.com/cdn/iptv/Tvod/001/channel2000037/512.m3u8")
	PlayVideo
End Sub

Sub Medium_Click
	ViVideoView.SetVideoPath("http://livetv.mncplaymedia.com/cdn/iptv/Tvod/001/channel2000037/512.m3u8")
	PlayVideo
End Sub

Sub High_Click
	ViVideoView.SetVideoPath("http://livetv.mncplaymedia.com/cdn/iptv/Tvod/001/channel2000037/512.m3u8")
	PlayVideo
End Sub

Sub Very_Click
	ViVideoView.SetVideoPath("http://livetv.mncplaymedia.com/cdn/iptv/Tvod/001/channel2000037/512.m3u8")
	PlayVideo
End Sub

Sub Exit_Click
	Activity_KeyPress(4)
End Sub

Sub Activity_Resume
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	If UserClosed = False Then
		ViVideoView.StopPlayback
		ViVideoView.Suspend
	End If
End Sub

Sub PlayVideo
'	If ViVideoView.CheckVitamioLibs Then
'		GifPlayer.Visible = True
'		
'		ViMediaControl.Show2(3000)
'		ViVideoView.SetMediaController(ViMediaControl)
'		ViVideoView.SetVideoLayout(ViVideoView.VIDEO_LAYOUT_STRETCH, 0)
'		ViVideoView.Start
'
'		tVideo.Initialize("tVideo", 45000)
'		tVideo.Enabled = True
'	End If
End Sub

Sub ViMediaControl_Hidden
	tVideo.Enabled = False
End Sub

Sub ViMediaControl_Shown
	tVideo.Enabled = False
End Sub

Sub ViVideoView_BitmapSubtitleUpdated(Bitmap1() As Byte, Width As Int, Height As Int)
End Sub

Sub ViVideoView_Buffering(Percent As Int)
	GifPlayer.Visible = False
	tVideo.Enabled = False
End Sub

Sub ViVideoView_Complete
End Sub

Sub ViVideoView_Error(MEDIA_ERROR As Int) As Boolean
	GifPlayer.Visible = False
	tVideo.Enabled = False
	ViVideoView.StopPlayback
	ctoast.Show3("Error code : " & MEDIA_ERROR, LoadBitmap(File.DirAssets, "warning.png"))
End Sub

Sub VitaVideoView_Info(What As Int, Extra As Int) As Boolean
End Sub

Sub ViVideoView_Prepared
	tVideo.Enabled = False
End Sub

Sub ViVideoView_SeekComplete
	
End Sub

Sub ViVideoView_TextSubtitleUpdated(SubText As String)
	
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'return true if you want to consume the event
	If KeyCode = 4 Then ' Back Key
		Dim Params		As BD_CustomDlgParams
		Dim btnOK 		As Button
		Dim btnCancel 	As Button	
		Dim pnlBody 	As Panel
		Dim itest		As Int
				
		pnlBody.Initialize("")

		If Device = 1 Then
			pnlBody.LoadLayout("ivTablet")
		Else
			pnlBody.LoadLayout("IvSmart")
		End If

		Dim btnOK As Button
		btnOK.Initialize("")
		btnOK.SetBackgroundImage(LoadBitmap(File.DirAssets, "bgbutton(1).png"))
		btnOK.TextSize = 12
	'	btnOK.Text = "<Font Color='White'>O</Font>K"
		btnOK.Text = "OK"
		btnOK.Typeface = Typeface.DEFAULT
		btnOK.TextColor = Colors.White
		

		Dim btnCancel As Button
		btnCancel.Initialize("")
		btnCancel.SetBackgroundImage(LoadBitmap(File.DirAssets, "bgbutton(1).png"))
		btnCancel.TextSize = 12
	'	btnCancel.Text = "<Font Color='Whi'>C</Font>ancel"
		btnCancel.Text = "CANCEL"
		btnCancel.Typeface = Typeface.DEFAULT
		btnCancel.TextColor = Colors.White

		Params.Initialize
		Params.DialogBody = pnlBody
		Params.BodyWidth = pInfo.Width
		Params.BodyHeight = pInfo.Height
		Params.PositiveButton = btnOK
		Params.CancelButton = btnCancel

		If BD.CustomDialog(Params, "CD2")	= DialogResponse.POSITIVE Then
			ExitApplication
		Else
			Return True
		End If
	End If
	
'	If (KeyCode = 4) OR (KeyCode = 82) Then ' Back Key
'		ivMenu_Click
'		Return True		
'	End If
End Sub

Sub tVideo_Tick
	ctoast.Show3("Offline Connection or Server Down", LoadBitmap(File.DirAssets, "warning.png"))
	tVideo.Enabled = False
	GifPlayer.Visible = False
End Sub

Sub ivYes_Click
	BD.CloseDialog(DialogResponse.POSITIVE)
	ExitApplication
End Sub

Sub ivNo_Click
	BD.CloseDialog(DialogResponse.CANCEL)
	Return
End Sub

Sub ivMenu_Click
	ViMediaControl.Show2(3000)
End Sub

Sub ivInfo_Click
'	Dim p As PhoneIntents
'
'	BD.CloseDialog(DialogResponse.CANCEL)
'	StartActivity(p.OpenBrowser("http://www.mncshop.co.id/"))		
'
'	Return
End Sub

Sub bCall_Click
	'StartActivity(pcMNCShop.Call("021 500-887"))
	Dim i As Intent
	i.Initialize(i.ACTION_VIEW, "tel:021 500-887")
	StartActivity(i)
	
End Sub

Sub PE_PhoneStateChanged (State As String, IncomingNumber As String, Intent As Intent)
    If State = "OFFHOOK" Then StartActivity(bCall_Click)
End Sub

Sub bBeli_Click
	'StartActivity(Order)	
	'Page
	StartActivity("Webview")
'	Private pnlBody As Panel
'
'	pnlBody.Initialize("")
'	pnlBody.Color = Colors.Transparent
'	pnlBody.LoadLayout("pesan1")
'	
'	'ivClose.Background = GetDrawable ("cancel_btn_wide")
'	
'	Params.Initialize
'	Params.DialogBody = pnlBody
'	Params.BodyWidth = pPesan.Width
'	Params.BodyHeight = pPesan.Height
'
'	BD.CustomDialog(Params, "CD2")
End Sub
Sub bCancel_Click
	BD.CloseDialog(DialogResponse.CANCEL)	
End Sub
'Sub bReset_Click
'	If BD.Msgbox("MNC Shop", "Are you sure to clear input ?", "Yes", "No", "", LoadBitmap(File.DirAssets, "warning.png")) = DialogResponse.POSITIVE Then
'		etName.Text = ""
'		etMail.Text = ""
'		etHp.Text = ""
'	End If
'End Sub
Sub bcacl_Click
	
End Sub
Sub ivLogo_Click
'	Dim p As PhoneIntents
'
'	BD.CloseDialog(DialogResponse.CANCEL)
'	StartActivity(p.OpenBrowser("http://www.mncshop.co.id/"))		
'
'	Return
End Sub
Sub ivWeb_Click
	Dim p As PhoneIntents

	BD.CloseDialog(DialogResponse.CANCEL)
	StartActivity(p.OpenBrowser("http://www.mncshop.co.id/"))		

	Return
End Sub
Sub ivCall_Click
	Dim i As Intent
		i.Initialize(i.ACTION_VIEW, "tel:021 500-887")
	StartActivity(i)
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
	Log(szAndroidID)
	Log(szManufacturer)
	Log(szModel)
	Log(szProduct)
	Log(szIMEI)
	Log(szIMSI)
	Log(szSubsID)
End Sub
Sub bSMS_Click
   Dim i As Intent
 	i.Initialize(i.ACTION_VIEW, "sms:089517807753")
 	i.PutExtra("sms_body", "")
   StartActivity(i)
End Sub