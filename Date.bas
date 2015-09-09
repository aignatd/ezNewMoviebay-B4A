Type=Activity
Version=4
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	
	Dim Spinner1 As Spinner
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	Spinner1.Initialize("spnr")
    Dim year As Int
    year = DateTime.GetYear(DateTime.Now) 'find the current year
    Dim i As Long
    DateTime.DateFormat = "dd/MM/yyyy"
    i = DateTime.Now
    Do While DateTime.GetYear(i) = year
    Spinner1.Add(DateTime.Date(i))
        i = DateTime.Add(i, 0, 0, 1)
    Loop
    Activity.AddView(Spinner1, 10dip, 10dip, 200dip, 70dip)

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


