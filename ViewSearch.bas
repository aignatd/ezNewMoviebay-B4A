Type=Class
Version=4
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'version 1.00
'Class module
Sub Class_Globals
	Private prefixList As Map
	Private substringList As Map
'	Private et As EditText
'	Private lv As ListView
	Private MIN_LIMIT, MAX_LIMIT As Int
	MIN_LIMIT = 1
	MAX_LIMIT = 4 'doesn't limit the words length. Only the index.
	Private mCallback As Object
	Private mEventName As String
	Private allItems As List
	Private IME As IME
	Type Item (SearchText As String, Title As String, Text As String, Value As Object)
	Private EditText1 As EditText
	Private ListView1 As ListView
End Sub

'Initializes the object and sets the module and sub that will handle the ItemClick event
Public Sub Initialize (Callback As Object, EventName As String)
	IME.Initialize("")
	EditText1.Initialize("EditText1")
	'Remove the suggestions bar
	EditText1.InputType = Bit.OR(EditText1.INPUT_TYPE_TEXT, 0x00080000)
	ListView1.Initialize("ListView1")
	ListView1.SingleLineLayout.ItemHeight = 50dip
	ListView1.SingleLineLayout.Label.TextSize = 14
	ListView1.Visible = False
	prefixList.Initialize
	substringList.Initialize
	mCallback = Callback
	mEventName = EventName
End Sub

'Adds the view to the parent. The parent can be an Activity or Panel.
Public Sub AddToParent(Parent As Panel, Left As Int, Top As Int, Width As Int, Height As Int)
	Parent.AddView(EditText1, 30dip, Top, Width, 60dip)
	Parent.AddView(ListView1, Left, Top + EditText1.Height, Width, Height - EditText1.Height)
'	et_TextChanged("", "")
	EditText1_TextChanged("","")
End Sub

Private Sub lv_ItemClick (Position As Int, Value As Object)
'	Dim it As Item = Value
'	EditText1.Text = it.Title
'	EditText1.SelectionStart = et.Text.Length
'	IME.HideKeyboard
'	ListView1.Visible = False
'	If SubExists(mCallback, mEventName & "_ItemClick") Then
'		CallSub2(mCallback, mEventName & "_ItemClick", it.Value)
'	End If
End Sub

Private Sub et_TextChanged (Old As String, New As String)
'	ListView1.Clear
'	If ListView1.Visible = False Then ListView1.Visible = True
'	If New.Length < MIN_LIMIT Then 
'		AddItemsToList(allItems, "")
'		Return
'	End If
'	Dim str1, str2 As String
'	str1 = New.ToLowerCase
'	If str1.Length > MAX_LIMIT Then
'		str2 = str1.SubString2(0, MAX_LIMIT)
'	Else
'		str2 = str1
'	End If
'	AddItemsToList(prefixList.Get(str2), str1)
'	AddItemsToList(substringList.Get(str2), str1)
End Sub

Private Sub AddItemsToList(li As List, full As String)
	If li.IsInitialized = False Then Return
	For Each it As Item In li
		If full.Length > MAX_LIMIT AND it.SearchText.Contains(full) = False Then
			Continue
		End If
		ListView1.AddTwoLines2(it.Title, it.Text, it)
	Next
End Sub

'Builds the index and returns an object which you can store as a process global variable
'in order to avoid rebuilding the index when the device orientation changes.
Public Sub SetItems(Items As List) As Object
	allItems = Items
	AddItemsToList(allItems, "")
	Dim startTime As Long 
	startTime = DateTime.Now
	'ProgressDialogShow2("Building index...", False)
	Dim noDuplicates As Map
	noDuplicates.Initialize
	prefixList.Clear
	substringList.Clear
	Dim m As Map
	Dim li As List
	For Each it As Item In Items
		noDuplicates.Clear
		For start = 0 To it.SearchText.Length
			Dim count As Int
			count = MIN_LIMIT
			Do While count <= MAX_LIMIT AND start + count <= it.SearchText.Length
				Dim str As String
				str = it.SearchText.SubString2(start, start + count)
				If noDuplicates.ContainsKey(str) = False Then 
					noDuplicates.Put(str, "")
					If start = 0 Then m = prefixList Else m = substringList
					li = m.Get(str)
					If li.IsInitialized = False Then
						li.Initialize
						m.Put(str, li)
					End If
					li.Add(it) 'Preserve the original case
				End If
				count = count + 1
			Loop
		Next
	Next
	ProgressDialogHide
	Log("Index time: " & (DateTime.Now - startTime) & " ms (" & Items.Size & " Items)")
	Return Array As Object(prefixList, substringList)
End Sub

'Sets the index from the previously stored index.
Public Sub SetIndex(Index As Object, Items As List)
	Dim obj() As Object
	obj = Index
	prefixList = obj(0)
	substringList = obj(1)
	allItems = Items
	AddItemsToList(allItems, "")
End Sub
Sub EditText1_TextChanged (Old As String, New As String)
	ListView1.Clear
	If ListView1.Visible = False Then ListView1.Visible = True
	If New.Length < MIN_LIMIT Then 
		AddItemsToList(allItems, "")
		Return
	End If
	Dim str1, str2 As String
	str1 = New.ToLowerCase
	If str1.Length > MAX_LIMIT Then
		str2 = str1.SubString2(0, MAX_LIMIT)
	Else
		str2 = str1
	End If
	AddItemsToList(prefixList.Get(str2), str1)
	AddItemsToList(substringList.Get(str2), str1)	
End Sub
Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim it As Item = Value
	EditText1.Text = it.Title
	EditText1.SelectionStart = EditText1.Text.Length
	IME.HideKeyboard
	ListView1.Visible = False
	If SubExists(mCallback, mEventName & "_ItemClick") Then
		CallSub2(mCallback, mEventName & "_ItemClick", it.Value)
	End If	
End Sub