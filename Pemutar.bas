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
	Public URLChannel	As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private pVideo 				As Panel
	Private tVideo				As Timer
	Private ViMediaControl		As Vitamio_MediaController
	Private ViVideoView 		As Vitamio_VideoView
	Private pPhone				As Phone
'	Private GifPlayer 			As MovieViewControl
	Private PE					As PhoneEvents
	Private asas				As String
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")

	Activity.LoadLayout("VideoPlay")


	ViVideoView.Initialize("ViVideoView")
	ViMediaControl.Initialize("ViMediaControl")
	
	pPhone.SetScreenOrientation(0)
'	GifPlayer.Load(File.DirAssets, "hourglass.gif", True)
'	GifPlayer.Paused = False
'	GifPlayer.Visible = False
	
	Dim PhoneId As PhoneId
    PE.InitializeWithPhoneState("PE",PhoneId)
	
	pVideo.RemoveAllViews
	pVideo.AddView(ViVideoView, 0, 0, pVideo.Width, pVideo.Height)			

	ViVideoView.SetVideoPath(URLChannel)
	PlayVideo	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub PlayVideo
	If ViVideoView.CheckVitamioLibs Then
		'GifPlayer.Visible = True
		
		ViMediaControl.Show2(3000)
		ViVideoView.SetMediaController(ViMediaControl)
		ViVideoView.SetVideoLayout(ViVideoView.VIDEO_LAYOUT_STRETCH, 0)
		ViVideoView.Start

		tVideo.Initialize("tVideo", 45000)
		tVideo.Enabled = True
	End If
End Sub

Sub tVideo_Tick
	tVideo.Enabled = False
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
	tVideo.Enabled = False
End Sub

Sub ViVideoView_Complete
End Sub

Sub ViVideoView_Error(MEDIA_ERROR As Int) As Boolean
	tVideo.Enabled = False
	ViVideoView.StopPlayback
	Msgbox2("Code of error : " & MEDIA_ERROR, "Warning" , "OK" , "", "", LoadBitmap(File.DirAssets, "warning.png"))
	Activity.Finish
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
		Activity.Finish
	Else
		Return True
	End If
End Sub