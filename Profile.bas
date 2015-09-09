Type=Activity
Version=4
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: false
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private wvOnline As WebView
	Private lvMenu   As ListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("idprofile")
	'wvOnline.LoadURL("http://202.147.192.113/www.moviebay.com/user/profile")
	'wvOnline.ZoomEnabled = False

	lvMenu.AddTwoLinesAndBitmap("My Dashboard", "Profile", LoadBitmap(File.DirAssets,("dashboard.png")))
	lvMenu.AddTwoLinesAndBitmap("Edit Profile", "Profile", LoadBitmap(File.DirAssets,("edit_profile.png")))
	lvMenu.AddTwoLinesAndBitmap("Transaction History", "Profile", LoadBitmap(File.DirAssets,("transaction.png")))
			
	init_Menu
	DoEvents

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
Sub init_Menu

'Dim TimeFont As Typeface
'	TimeFont = Typeface.LoadFromAssets ("gotham-light.ttf")
'	
'	lvMenu.Clear
'	Fungsi.SetDivider(lvMenu, Colors.Transparent, 1dip)
'	
'	lvMenu.FastScrollEnabled = True
'	lvMenu.TwoLinesLayout.SecondLabel.TextColor = Colors.Magenta
'	
'	lvMenu.AddTwoLinesAndBitmap("My Dashboard", "", Null)
'	lvMenu.AddTwoLinesAndBitmap("Edit Profile", "", Null)
'	lvMenu.AddTwoLinesAndBitmap("Top Movies", "", Null)
'
'
'	lvMenu.TwoLinesAndBitmap.SecondLabel.Visible = False
'	lvMenu.TwoLinesAndBitmap.ImageView.Gravity = Gravity.CENTER
' 
''	lvSearch.TwoLinesAndBitmap.ItemHeight = 60dip
'	lvMenu.TwoLinesAndBitmap.Label.Typeface = TimeFont
'	lvMenu.TwoLinesAndBitmap.Label.TextColor = Colors.White
''	lvSearch.TwoLinesAndBitmap.Label.Height = lvSearch.TwoLinesAndBitmap.ImageView.Height
''	'lvSearch.TwoLinesAndBitmap.Label.Gravity = Gravity.CENTER_VERTICAL
''	lvSearch.TwoLinesAndBitmap.Label.Left = lvSearch.TwoLinesAndBitmap.ImageView.Width + 7dip
''	lvSearch.Height = lvSearch.Size * 60dip 
'  

End Sub

Sub lvMenu_ItemClick (Position As Int, Value As Object)
	If Value = "My Dashboard" Then
	StartActivity("ForgotPass")
	'Msgbox("ini profile", "profile")
	Else If Value = "Edit Profile" Then
	Msgbox("ini profile", "profile")	
	Else If Value = "Transaction History" Then
	Msgbox("ini profile", "profile")	
	End If
End Sub