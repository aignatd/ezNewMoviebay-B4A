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
	Private cu As ContactUtils 
	Private index As Object
	Private Contacts As List
End Sub

Sub Globals
	Dim sv As ViewSearch
	Private Panel1 As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
sv.Initialize(Me,"sv")
	Panel1.Initialize("")
	Dim items As List
	items.Initialize
	If FirstTime Then
		cu.Initialize
		Contacts.Initialize
		ProgressDialogShow("Building contacts index...")
		For Each c As cuContact In cu.FindAllContacts(True)
			DoEvents
			Dim it As Item
			it.Title = c.DisplayName
			it.SearchText = c.DisplayName.ToLowerCase
			For Each Phone As cuPhone In cu.GetPhones(c.Id)
				it.Text = Phone.Number
				it.SearchText = it.SearchText & " " & CleanPhone(Phone.Number)
				it.Value = c
			Next
			Contacts.Add(it)
		Next
 		index = sv.SetItems(Contacts)
		ProgressDialogHide
	Else
	'	sv.SetIndex(index, Contacts) 'reuse the data we collected
	End If
	sv.AddToParent(Activity, 10dip, 10dip, 100%x - 10dip, 300dip)
End Sub

Sub CleanPhone(n As String) As String
	Dim sb As StringBuilder
	sb.Initialize
	For i = 0 To n.Length - 1
		Dim ascValue As Int = Asc(n.CharAt(i))
		If ascValue >= 48 AND ascValue <= 57 Then
			sb.Append(n.CharAt(i))
		End If
	Next
	Return sb.ToString
End Sub

Sub sv_ItemClick (Contact As Object)
	Dim c As cuContact = Contact
	Log(c)
End Sub

Sub Activity_Resume
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


