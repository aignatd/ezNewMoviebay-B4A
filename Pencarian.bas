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
	Private et As EditText
	Private lv As ListView
	Private MIN_LIMIT, MAX_LIMIT As Int
	MIN_LIMIT = 1
	MAX_LIMIT = 4 'doesn't limit the words length. Only the index.
	Private mCallback As Object
	Private mEventName As String
	Private etSearch As EditText
	Private ListView1 As ListView
	Private pSearch As Panel
End Sub

'Initializes the object and sets the module and sub that will handle the ItemClick event
Public Sub Initialize (Callback As Object, EventName As String)
	
	et.Initialize("et")
	'pSearch.Initialize("")
	etSearch.Initialize("etSearch")
	'Remove the suggestions bar
	etSearch.InputType = Bit.OR(et.INPUT_TYPE_TEXT, 0x00080000)
	lv.Initialize("lv")
	ListView1.Initialize("ListView1")
	ListView1.SingleLineLayout.ItemHeight = 20dip
	ListView1.SingleLineLayout.Label.TextSize = 8
	ListView1.Visible = True
	prefixList.Initialize
	substringList.Initialize
	mCallback = Callback
	mEventName = EventName
End Sub

'Adds the view to the parent. The parent can be an Activity or Panel.
Public Sub AddToParent(Parent As Panel, Left As Int, Top As Int, Width As Int, Height As Int)
	
	Parent.AddView(et, Left, Top, Width, 60dip)
	Parent.AddView(lv, Left, Top + et.Height, Width, Height - et.Height)
End Sub

Private Sub lv_ItemClick (Position As Int, Value As Object)
	et.Text = Value
	et.SelectionStart = et.Text.Length
	Dim IME As IME
	IME.HideKeyboard
	lv.Visible = False
	If SubExists(mCallback, mEventName & "_ItemClick") Then
		CallSub2(mCallback, mEventName & "_ItemClick", Value)
	End If
End Sub

Private Sub et_TextChanged (Old As String, New As String)
	lv.Clear
	If lv.Visible = False Then lv.Visible = True
	If New.Length < MIN_LIMIT Then Return
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

Private Sub AddItemsToList(li As List, full As String)
	If li.IsInitialized = False Then Return
	For i = 0 To li.Size - 1
		Dim item As String
		item = li.Get(i)
		If full.Length > MAX_LIMIT AND item.ToLowerCase.Contains(full) = False Then
			Continue
		End If
		lv.AddSingleLine(li.Get(i))
	Next
End Sub

'Builds the index and returns an object which you can store as a process global variable
'in order to avoid rebuilding the index when the device orientation changes.
Public Sub SetItems(Items As List) As Object
	Dim startTime As Long 
	startTime = DateTime.Now
	ProgressDialogShow2("Building index...", False)
	Dim noDuplicates As Map
	noDuplicates.Initialize
	prefixList.Clear
	substringList.Clear
	Dim m As Map
	Dim li As List
	For i = 0 To Items.Size - 1
		If i Mod 100 = 0 Then DoEvents
		Dim item As String
		item = Items.Get(i)
		item = item.ToLowerCase
		noDuplicates.Clear
		For start = 0 To item.Length
			Dim count As Int
			count = MIN_LIMIT
			Do While count <= MAX_LIMIT AND start + count <= item.Length
				Dim str As String
				str = item.SubString2(start, start + count)
				If noDuplicates.ContainsKey(str) = False Then 
					noDuplicates.Put(str, "")
					If start = 0 Then m = prefixList Else m = substringList
					li = m.Get(str)
					If li.IsInitialized = False Then
						li.Initialize
						m.Put(str, li)
					End If
					li.Add(Items.Get(i)) 'Preserve the original case
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
Public Sub SetIndex(Index As Object)
	Dim obj() As Object
	obj = Index
	prefixList = obj(0)
	substringList = obj(1)
End Sub