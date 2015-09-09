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
	Dim sv As View
End Sub

Sub Activity_Create(FirstTime As Boolean)
	sv.Initialize(Me, "sv")
	sv.AddToParent(Activity, 0, 0, 500dip, 300dip)
	
	If FirstTime Then
		Dim cities As List
		cities = File.ReadList(File.DirAssets, "Cities.txt")
		'As an optimization we store the index as a process global variable
		'and only build it once.
		index = sv.SetItems(cities)
	Else
		sv.SetIndex(index)
	End If
End Sub

Sub sv_ItemClick(Value As String)
	Msgbox("Chosen value: " & Value, "")
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub