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
	Dim cok As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	
	
	
	
	Private lvShare As ListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")

	Activity.LoadLayout("Share")

	lvShare.AddTwoLinesAndBitmap("Facebook", "Moviebay Shared", LoadBitmap(File.DirAssets,("facebook.png")))
	lvShare.AddTwoLinesAndBitmap("Twitter", "Moviebay Shared", LoadBitmap(File.DirAssets,("twitter-icon.png")))
	'lvShare.AddTwoLinesAndBitmap("Transaction History", "Profile", LoadBitmap(File.DirAssets,("transaction.png")))

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub ivBackShare_Click
	Activity.Finish
End Sub
'Sub ivTwitter_Click
'
'
'	Dim r As Reflector
'   Dim f As Object
'   f = r.CreateObject2("java.io.File", Array As Object("file:///sdcard/the-raid.jpg"), Array As String("java.lang.String"))
'   Dim share As Intent
'   share.Initialize(share.ACTION_SEND,"")
'   share.SetType("image/jpeg")
'   share.PutExtra("android.intent.extra.STREAM", r.RunStaticMethod("android.net.Uri", "fromFile", _
'      Array As Object(f), Array As String("java.io.File")))
'   share.WrapAsIntentChooser("Share Photo:)")
'   StartActivity(share)
'
''	Dim i As Intent
'' 	i.Initialize(i.ACTION_VIEW, "http://www.twitter.com/")
'' 	'i.PutExtra("sms_body", "")
''   StartActivity(i)
'End Sub
'
'Sub ivFacebook_Click
'
'	Dim i As Intent
'   		i.Initialize(i.ACTION_SEND, "")
'   		i.SetType("text/plain")
'   		i.PutExtra("android.intent.extra.TEXT", "some text")
'   		i.WrapAsIntentChooser("title")
'   StartActivity(i)
''	Dim i As Intent
'' 	i.Initialize(i.ACTION_VIEW, "http://www.facebook.com/")
'' 	'i.PutExtra("sms_body", "")
''   StartActivity(i)
'End Sub

Sub lvShare_ItemClick (Position As Int, Value As Object)
	If Value = "Facebook" Then
	Dim Url As String
     Url = "http://www.moviebay.com/user/profile"
	
	 Dim i As Intent
     i.Initialize(i.ACTION_VIEW, "http://m.facebook.com/sharer.php?u=" & Url)
     i.SetType("text/plain")
     i.PutExtra("android.intent.extra.TEXT", "")
     i.WrapAsIntentChooser("Open Browser")
     StartActivity(i)
	
'		Dim i As Intent
'	   		i.Initialize(i.ACTION_SEND, "")
'	   		i.SetType("text/plain")
'	   		i.PutExtra("android.intent.extra.TEXT", "some text")
'	   		i.WrapAsIntentChooser("Shared")	
'		StartActivity(i)
		
	Else If Value = "Twitter" Then
	Dim Url As String
     Url = "http://www.moviebay.com/site"
	
	 Dim i As Intent
     i.Initialize(i.ACTION_VIEW, "http://m.twitter.com/sharer.php?u=" & Url)
     i.SetType("text/plain")
     i.PutExtra("android.intent.extra.TEXT", "")
     i.WrapAsIntentChooser("Open Browser")
     StartActivity(i)
	End If
End Sub