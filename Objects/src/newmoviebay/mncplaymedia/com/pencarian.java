package newmoviebay.mncplaymedia.com;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class pencarian extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "newmoviebay.mncplaymedia.com.pencarian");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            if (BA.isShellModeRuntimeCheck(ba)) {
			    ba.raiseEvent2(null, true, "CREATE", true, "newmoviebay.mncplaymedia.com.pencarian",
                    ba);
                return;
		    }
        }
        ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.collections.Map _prefixlist = null;
public anywheresoftware.b4a.objects.collections.Map _substringlist = null;
public anywheresoftware.b4a.objects.EditTextWrapper _et = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lv = null;
public int _min_limit = 0;
public int _max_limit = 0;
public Object _mcallback = null;
public String _meventname = "";
public anywheresoftware.b4a.objects.EditTextWrapper _etsearch = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _psearch = null;
public b4a.example.dateutils _dateutils = null;
public newmoviebay.mncplaymedia.com.main _main = null;
public newmoviebay.mncplaymedia.com.utama _utama = null;
public newmoviebay.mncplaymedia.com.content _content = null;
public newmoviebay.mncplaymedia.com.register _register = null;
public newmoviebay.mncplaymedia.com.menusearch _menusearch = null;
public newmoviebay.mncplaymedia.com.fungsi _fungsi = null;
public newmoviebay.mncplaymedia.com.profile _profile = null;
public newmoviebay.mncplaymedia.com.forgotpass _forgotpass = null;
public newmoviebay.mncplaymedia.com.share _share = null;
public newmoviebay.mncplaymedia.com.mncutils2service _mncutils2service = null;
public newmoviebay.mncplaymedia.com.pemutar _pemutar = null;
public newmoviebay.mncplaymedia.com.webview _webview = null;
public newmoviebay.mncplaymedia.com.play _play = null;
public newmoviebay.mncplaymedia.com.result _result = null;
public newmoviebay.mncplaymedia.com.date _date = null;
  public Object[] GetGlobals() {
		return new Object[] {"Content",Debug.moduleToString(newmoviebay.mncplaymedia.com.content.class),"Date",Debug.moduleToString(newmoviebay.mncplaymedia.com.date.class),"DateUtils",_dateutils,"et",_et,"etSearch",_etsearch,"ForgotPass",Debug.moduleToString(newmoviebay.mncplaymedia.com.forgotpass.class),"Fungsi",Debug.moduleToString(newmoviebay.mncplaymedia.com.fungsi.class),"ListView1",_listview1,"lv",_lv,"Main",Debug.moduleToString(newmoviebay.mncplaymedia.com.main.class),"MAX_LIMIT",_max_limit,"mCallback",_mcallback,"MenuSearch",Debug.moduleToString(newmoviebay.mncplaymedia.com.menusearch.class),"mEventName",_meventname,"MIN_LIMIT",_min_limit,"MNCUtils2Service",Debug.moduleToString(newmoviebay.mncplaymedia.com.mncutils2service.class),"Pemutar",Debug.moduleToString(newmoviebay.mncplaymedia.com.pemutar.class),"Play",Debug.moduleToString(newmoviebay.mncplaymedia.com.play.class),"prefixList",_prefixlist,"Profile",Debug.moduleToString(newmoviebay.mncplaymedia.com.profile.class),"pSearch",_psearch,"Register",Debug.moduleToString(newmoviebay.mncplaymedia.com.register.class),"Result",Debug.moduleToString(newmoviebay.mncplaymedia.com.result.class),"Share",Debug.moduleToString(newmoviebay.mncplaymedia.com.share.class),"substringList",_substringlist,"Utama",Debug.moduleToString(newmoviebay.mncplaymedia.com.utama.class),"WebView",Debug.moduleToString(newmoviebay.mncplaymedia.com.webview.class)};
}
public String  _additemstolist(anywheresoftware.b4a.objects.collections.List _li,String _full) throws Exception{
try {
		Debug.PushSubsStack("AddItemsToList (pencarian) ","pencarian",14,ba,this,71);
int _i = 0;
String _item = "";
Debug.locals.put("li", _li);
Debug.locals.put("full", _full);
 BA.debugLineNum = 71;BA.debugLine="Private Sub AddItemsToList(li As List, full As String)";
Debug.ShouldStop(64);
 BA.debugLineNum = 72;BA.debugLine="If li.IsInitialized = False Then Return";
Debug.ShouldStop(128);
if (_li.IsInitialized()==__c.False) { 
if (true) return "";};
 BA.debugLineNum = 73;BA.debugLine="For i = 0 To li.Size - 1";
Debug.ShouldStop(256);
{
final int step58 = 1;
final int limit58 = (int) (_li.getSize()-1);
for (_i = (int) (0); (step58 > 0 && _i <= limit58) || (step58 < 0 && _i >= limit58); _i = ((int)(0 + _i + step58))) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 74;BA.debugLine="Dim item As String";
Debug.ShouldStop(512);
_item = "";Debug.locals.put("item", _item);
 BA.debugLineNum = 75;BA.debugLine="item = li.Get(i)";
Debug.ShouldStop(1024);
_item = BA.ObjectToString(_li.Get(_i));Debug.locals.put("item", _item);
 BA.debugLineNum = 76;BA.debugLine="If full.Length > MAX_LIMIT AND item.ToLowerCase.Contains(full) = False Then";
Debug.ShouldStop(2048);
if (_full.length()>_max_limit && _item.toLowerCase().contains(_full)==__c.False) { 
 BA.debugLineNum = 77;BA.debugLine="Continue";
Debug.ShouldStop(4096);
if (true) continue;
 };
 BA.debugLineNum = 79;BA.debugLine="lv.AddSingleLine(li.Get(i))";
Debug.ShouldStop(16384);
_lv.AddSingleLine(BA.ObjectToString(_li.Get(_i)));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 81;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _addtoparent(anywheresoftware.b4a.objects.PanelWrapper _parent,int _left,int _top,int _width,int _height) throws Exception{
try {
		Debug.PushSubsStack("AddToParent (pencarian) ","pencarian",14,ba,this,39);
Debug.locals.put("Parent", _parent);
Debug.locals.put("Left", _left);
Debug.locals.put("Top", _top);
Debug.locals.put("Width", _width);
Debug.locals.put("Height", _height);
 BA.debugLineNum = 39;BA.debugLine="Public Sub AddToParent(Parent As Panel, Left As Int, Top As Int, Width As Int, Height As Int)";
Debug.ShouldStop(64);
 BA.debugLineNum = 41;BA.debugLine="Parent.AddView(et, Left, Top, Width, 60dip)";
Debug.ShouldStop(256);
_parent.AddView((android.view.View)(_et.getObject()),_left,_top,_width,__c.DipToCurrent((int) (60)));
 BA.debugLineNum = 42;BA.debugLine="Parent.AddView(lv, Left, Top + et.Height, Width, Height - et.Height)";
Debug.ShouldStop(512);
_parent.AddView((android.view.View)(_lv.getObject()),_left,(int) (_top+_et.getHeight()),_width,(int) (_height-_et.getHeight()));
 BA.debugLineNum = 43;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 5;BA.debugLine="Private prefixList As Map";
_prefixlist = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 6;BA.debugLine="Private substringList As Map";
_substringlist = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 7;BA.debugLine="Private et As EditText";
_et = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 8;BA.debugLine="Private lv As ListView";
_lv = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 9;BA.debugLine="Private MIN_LIMIT, MAX_LIMIT As Int";
_min_limit = 0;
_max_limit = 0;
 //BA.debugLineNum = 10;BA.debugLine="MIN_LIMIT = 1";
_min_limit = (int) (1);
 //BA.debugLineNum = 11;BA.debugLine="MAX_LIMIT = 4 'doesn't limit the words length. Only the index.";
_max_limit = (int) (4);
 //BA.debugLineNum = 12;BA.debugLine="Private mCallback As Object";
_mcallback = new Object();
 //BA.debugLineNum = 13;BA.debugLine="Private mEventName As String";
_meventname = "";
 //BA.debugLineNum = 14;BA.debugLine="Private etSearch As EditText";
_etsearch = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private ListView1 As ListView";
_listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private pSearch As Panel";
_psearch = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return "";
}
public String  _et_textchanged(String _old,String _new) throws Exception{
try {
		Debug.PushSubsStack("et_TextChanged (pencarian) ","pencarian",14,ba,this,56);
String _str1 = "";
String _str2 = "";
Debug.locals.put("Old", _old);
Debug.locals.put("New", _new);
 BA.debugLineNum = 56;BA.debugLine="Private Sub et_TextChanged (Old As String, New As String)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 57;BA.debugLine="lv.Clear";
Debug.ShouldStop(16777216);
_lv.Clear();
 BA.debugLineNum = 58;BA.debugLine="If lv.Visible = False Then lv.Visible = True";
Debug.ShouldStop(33554432);
if (_lv.getVisible()==__c.False) { 
_lv.setVisible(__c.True);};
 BA.debugLineNum = 59;BA.debugLine="If New.Length < MIN_LIMIT Then Return";
Debug.ShouldStop(67108864);
if (_new.length()<_min_limit) { 
if (true) return "";};
 BA.debugLineNum = 60;BA.debugLine="Dim str1, str2 As String";
Debug.ShouldStop(134217728);
_str1 = "";Debug.locals.put("str1", _str1);
_str2 = "";Debug.locals.put("str2", _str2);
 BA.debugLineNum = 61;BA.debugLine="str1 = New.ToLowerCase";
Debug.ShouldStop(268435456);
_str1 = _new.toLowerCase();Debug.locals.put("str1", _str1);
 BA.debugLineNum = 62;BA.debugLine="If str1.Length > MAX_LIMIT Then";
Debug.ShouldStop(536870912);
if (_str1.length()>_max_limit) { 
 BA.debugLineNum = 63;BA.debugLine="str2 = str1.SubString2(0, MAX_LIMIT)";
Debug.ShouldStop(1073741824);
_str2 = _str1.substring((int) (0),_max_limit);Debug.locals.put("str2", _str2);
 }else {
 BA.debugLineNum = 65;BA.debugLine="str2 = str1";
Debug.ShouldStop(1);
_str2 = _str1;Debug.locals.put("str2", _str2);
 };
 BA.debugLineNum = 67;BA.debugLine="AddItemsToList(prefixList.Get(str2), str1)";
Debug.ShouldStop(4);
_additemstolist((anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_prefixlist.Get((Object)(_str2)))),_str1);
 BA.debugLineNum = 68;BA.debugLine="AddItemsToList(substringList.Get(str2), str1)";
Debug.ShouldStop(8);
_additemstolist((anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_substringlist.Get((Object)(_str2)))),_str1);
 BA.debugLineNum = 69;BA.debugLine="End Sub";
Debug.ShouldStop(16);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _initialize(anywheresoftware.b4a.BA _ba,Object _callback,String _eventname) throws Exception{
innerInitialize(_ba);
try {
		Debug.PushSubsStack("Initialize (pencarian) ","pencarian",14,ba,this,20);
Debug.locals.put("ba", _ba);
Debug.locals.put("Callback", _callback);
Debug.locals.put("EventName", _eventname);
 BA.debugLineNum = 20;BA.debugLine="Public Sub Initialize (Callback As Object, EventName As String)";
Debug.ShouldStop(524288);
 BA.debugLineNum = 22;BA.debugLine="et.Initialize(\"et\")";
Debug.ShouldStop(2097152);
_et.Initialize(ba,"et");
 BA.debugLineNum = 24;BA.debugLine="etSearch.Initialize(\"etSearch\")";
Debug.ShouldStop(8388608);
_etsearch.Initialize(ba,"etSearch");
 BA.debugLineNum = 26;BA.debugLine="etSearch.InputType = Bit.OR(et.INPUT_TYPE_TEXT, 0x00080000)";
Debug.ShouldStop(33554432);
_etsearch.setInputType(__c.Bit.Or(_et.INPUT_TYPE_TEXT,(int) (0x00080000)));
 BA.debugLineNum = 27;BA.debugLine="lv.Initialize(\"lv\")";
Debug.ShouldStop(67108864);
_lv.Initialize(ba,"lv");
 BA.debugLineNum = 28;BA.debugLine="ListView1.Initialize(\"ListView1\")";
Debug.ShouldStop(134217728);
_listview1.Initialize(ba,"ListView1");
 BA.debugLineNum = 29;BA.debugLine="ListView1.SingleLineLayout.ItemHeight = 20dip";
Debug.ShouldStop(268435456);
_listview1.getSingleLineLayout().setItemHeight(__c.DipToCurrent((int) (20)));
 BA.debugLineNum = 30;BA.debugLine="ListView1.SingleLineLayout.Label.TextSize = 8";
Debug.ShouldStop(536870912);
_listview1.getSingleLineLayout().Label.setTextSize((float) (8));
 BA.debugLineNum = 31;BA.debugLine="ListView1.Visible = True";
Debug.ShouldStop(1073741824);
_listview1.setVisible(__c.True);
 BA.debugLineNum = 32;BA.debugLine="prefixList.Initialize";
Debug.ShouldStop(-2147483648);
_prefixlist.Initialize();
 BA.debugLineNum = 33;BA.debugLine="substringList.Initialize";
Debug.ShouldStop(1);
_substringlist.Initialize();
 BA.debugLineNum = 34;BA.debugLine="mCallback = Callback";
Debug.ShouldStop(2);
_mcallback = _callback;
 BA.debugLineNum = 35;BA.debugLine="mEventName = EventName";
Debug.ShouldStop(4);
_meventname = _eventname;
 BA.debugLineNum = 36;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _lv_itemclick(int _position,Object _value) throws Exception{
try {
		Debug.PushSubsStack("lv_ItemClick (pencarian) ","pencarian",14,ba,this,45);
anywheresoftware.b4a.objects.IME _ime = null;
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 45;BA.debugLine="Private Sub lv_ItemClick (Position As Int, Value As Object)";
Debug.ShouldStop(4096);
 BA.debugLineNum = 46;BA.debugLine="et.Text = Value";
Debug.ShouldStop(8192);
_et.setText(_value);
 BA.debugLineNum = 47;BA.debugLine="et.SelectionStart = et.Text.Length";
Debug.ShouldStop(16384);
_et.setSelectionStart(_et.getText().length());
 BA.debugLineNum = 48;BA.debugLine="Dim IME As IME";
Debug.ShouldStop(32768);
_ime = new anywheresoftware.b4a.objects.IME();Debug.locals.put("IME", _ime);
 BA.debugLineNum = 49;BA.debugLine="IME.HideKeyboard";
Debug.ShouldStop(65536);
_ime.HideKeyboard(ba);
 BA.debugLineNum = 50;BA.debugLine="lv.Visible = False";
Debug.ShouldStop(131072);
_lv.setVisible(__c.False);
 BA.debugLineNum = 51;BA.debugLine="If SubExists(mCallback, mEventName & \"_ItemClick\") Then";
Debug.ShouldStop(262144);
if (__c.SubExists(ba,_mcallback,_meventname+"_ItemClick")) { 
 BA.debugLineNum = 52;BA.debugLine="CallSub2(mCallback, mEventName & \"_ItemClick\", Value)";
Debug.ShouldStop(524288);
__c.CallSubNew2(ba,_mcallback,_meventname+"_ItemClick",_value);
 };
 BA.debugLineNum = 54;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _setindex(Object _index) throws Exception{
try {
		Debug.PushSubsStack("SetIndex (pencarian) ","pencarian",14,ba,this,127);
Object[] _obj = null;
Debug.locals.put("Index", _index);
 BA.debugLineNum = 127;BA.debugLine="Public Sub SetIndex(Index As Object)";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 128;BA.debugLine="Dim obj() As Object";
Debug.ShouldStop(-2147483648);
_obj = new Object[(int) (0)];
{
int d0 = _obj.length;
for (int i0 = 0;i0 < d0;i0++) {
_obj[i0] = new Object();
}
}
;Debug.locals.put("obj", _obj);
 BA.debugLineNum = 129;BA.debugLine="obj = Index";
Debug.ShouldStop(1);
_obj = (Object[])(_index);Debug.locals.put("obj", _obj);
 BA.debugLineNum = 130;BA.debugLine="prefixList = obj(0)";
Debug.ShouldStop(2);
_prefixlist.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_obj[(int) (0)]));
 BA.debugLineNum = 131;BA.debugLine="substringList = obj(1)";
Debug.ShouldStop(4);
_substringlist.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_obj[(int) (1)]));
 BA.debugLineNum = 132;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public Object  _setitems(anywheresoftware.b4a.objects.collections.List _items) throws Exception{
try {
		Debug.PushSubsStack("SetItems (pencarian) ","pencarian",14,ba,this,85);
long _starttime = 0L;
anywheresoftware.b4a.objects.collections.Map _noduplicates = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.collections.List _li = null;
int _i = 0;
String _item = "";
int _start = 0;
int _count = 0;
String _str = "";
Debug.locals.put("Items", _items);
 BA.debugLineNum = 85;BA.debugLine="Public Sub SetItems(Items As List) As Object";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 86;BA.debugLine="Dim startTime As Long";
Debug.ShouldStop(2097152);
_starttime = 0L;Debug.locals.put("startTime", _starttime);
 BA.debugLineNum = 87;BA.debugLine="startTime = DateTime.Now";
Debug.ShouldStop(4194304);
_starttime = __c.DateTime.getNow();Debug.locals.put("startTime", _starttime);
 BA.debugLineNum = 88;BA.debugLine="ProgressDialogShow2(\"Building index...\", False)";
Debug.ShouldStop(8388608);
__c.ProgressDialogShow2(ba,"Building index...",__c.False);
 BA.debugLineNum = 89;BA.debugLine="Dim noDuplicates As Map";
Debug.ShouldStop(16777216);
_noduplicates = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("noDuplicates", _noduplicates);
 BA.debugLineNum = 90;BA.debugLine="noDuplicates.Initialize";
Debug.ShouldStop(33554432);
_noduplicates.Initialize();
 BA.debugLineNum = 91;BA.debugLine="prefixList.Clear";
Debug.ShouldStop(67108864);
_prefixlist.Clear();
 BA.debugLineNum = 92;BA.debugLine="substringList.Clear";
Debug.ShouldStop(134217728);
_substringlist.Clear();
 BA.debugLineNum = 93;BA.debugLine="Dim m As Map";
Debug.ShouldStop(268435456);
_m = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("m", _m);
 BA.debugLineNum = 94;BA.debugLine="Dim li As List";
Debug.ShouldStop(536870912);
_li = new anywheresoftware.b4a.objects.collections.List();Debug.locals.put("li", _li);
 BA.debugLineNum = 95;BA.debugLine="For i = 0 To Items.Size - 1";
Debug.ShouldStop(1073741824);
{
final int step77 = 1;
final int limit77 = (int) (_items.getSize()-1);
for (_i = (int) (0); (step77 > 0 && _i <= limit77) || (step77 < 0 && _i >= limit77); _i = ((int)(0 + _i + step77))) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 96;BA.debugLine="If i Mod 100 = 0 Then DoEvents";
Debug.ShouldStop(-2147483648);
if (_i%100==0) { 
__c.DoEvents();};
 BA.debugLineNum = 97;BA.debugLine="Dim item As String";
Debug.ShouldStop(1);
_item = "";Debug.locals.put("item", _item);
 BA.debugLineNum = 98;BA.debugLine="item = Items.Get(i)";
Debug.ShouldStop(2);
_item = BA.ObjectToString(_items.Get(_i));Debug.locals.put("item", _item);
 BA.debugLineNum = 99;BA.debugLine="item = item.ToLowerCase";
Debug.ShouldStop(4);
_item = _item.toLowerCase();Debug.locals.put("item", _item);
 BA.debugLineNum = 100;BA.debugLine="noDuplicates.Clear";
Debug.ShouldStop(8);
_noduplicates.Clear();
 BA.debugLineNum = 101;BA.debugLine="For start = 0 To item.Length";
Debug.ShouldStop(16);
{
final int step83 = 1;
final int limit83 = _item.length();
for (_start = (int) (0); (step83 > 0 && _start <= limit83) || (step83 < 0 && _start >= limit83); _start = ((int)(0 + _start + step83))) {
Debug.locals.put("start", _start);
 BA.debugLineNum = 102;BA.debugLine="Dim count As Int";
Debug.ShouldStop(32);
_count = 0;Debug.locals.put("count", _count);
 BA.debugLineNum = 103;BA.debugLine="count = MIN_LIMIT";
Debug.ShouldStop(64);
_count = _min_limit;Debug.locals.put("count", _count);
 BA.debugLineNum = 104;BA.debugLine="Do While count <= MAX_LIMIT AND start + count <= item.Length";
Debug.ShouldStop(128);
while (_count<=_max_limit && _start+_count<=_item.length()) {
 BA.debugLineNum = 105;BA.debugLine="Dim str As String";
Debug.ShouldStop(256);
_str = "";Debug.locals.put("str", _str);
 BA.debugLineNum = 106;BA.debugLine="str = item.SubString2(start, start + count)";
Debug.ShouldStop(512);
_str = _item.substring(_start,(int) (_start+_count));Debug.locals.put("str", _str);
 BA.debugLineNum = 107;BA.debugLine="If noDuplicates.ContainsKey(str) = False Then";
Debug.ShouldStop(1024);
if (_noduplicates.ContainsKey((Object)(_str))==__c.False) { 
 BA.debugLineNum = 108;BA.debugLine="noDuplicates.Put(str, \"\")";
Debug.ShouldStop(2048);
_noduplicates.Put((Object)(_str),(Object)(""));
 BA.debugLineNum = 109;BA.debugLine="If start = 0 Then m = prefixList Else m = substringList";
Debug.ShouldStop(4096);
if (_start==0) { 
_m = _prefixlist;Debug.locals.put("m", _m);}
else {
_m = _substringlist;Debug.locals.put("m", _m);};
 BA.debugLineNum = 110;BA.debugLine="li = m.Get(str)";
Debug.ShouldStop(8192);
_li.setObject((java.util.List)(_m.Get((Object)(_str))));
 BA.debugLineNum = 111;BA.debugLine="If li.IsInitialized = False Then";
Debug.ShouldStop(16384);
if (_li.IsInitialized()==__c.False) { 
 BA.debugLineNum = 112;BA.debugLine="li.Initialize";
Debug.ShouldStop(32768);
_li.Initialize();
 BA.debugLineNum = 113;BA.debugLine="m.Put(str, li)";
Debug.ShouldStop(65536);
_m.Put((Object)(_str),(Object)(_li.getObject()));
 };
 BA.debugLineNum = 115;BA.debugLine="li.Add(Items.Get(i)) 'Preserve the original case";
Debug.ShouldStop(262144);
_li.Add(_items.Get(_i));
 };
 BA.debugLineNum = 117;BA.debugLine="count = count + 1";
Debug.ShouldStop(1048576);
_count = (int) (_count+1);Debug.locals.put("count", _count);
 }
;
 }
}Debug.locals.put("start", _start);
;
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 121;BA.debugLine="ProgressDialogHide";
Debug.ShouldStop(16777216);
__c.ProgressDialogHide();
 BA.debugLineNum = 122;BA.debugLine="Log(\"Index time: \" & (DateTime.Now - startTime) & \" ms (\" & Items.Size & \" Items)\")";
Debug.ShouldStop(33554432);
__c.Log("Index time: "+BA.NumberToString((__c.DateTime.getNow()-_starttime))+" ms ("+BA.NumberToString(_items.getSize())+" Items)");
 BA.debugLineNum = 123;BA.debugLine="Return Array As Object(prefixList, substringList)";
Debug.ShouldStop(67108864);
if (true) return (Object)(new Object[]{(Object)(_prefixlist.getObject()),(Object)(_substringlist.getObject())});
 BA.debugLineNum = 124;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
return null;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
