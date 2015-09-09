package newmoviebay.mncplaymedia.com;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class anotherdatepicker extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "newmoviebay.mncplaymedia.com.anotherdatepicker");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            if (BA.isShellModeRuntimeCheck(ba)) {
			    ba.raiseEvent2(null, true, "CREATE", true, "newmoviebay.mncplaymedia.com.anotherdatepicker",
                    ba);
                return;
		    }
        }
        ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.PanelWrapper _holder = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _cvs = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _cvsbackground = null;
public anywheresoftware.b4a.objects.PanelWrapper _dayspanel = null;
public int _month = 0;
public int _year = 0;
public anywheresoftware.b4a.objects.SpinnerWrapper _months = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _years = null;
public float _boxw = 0f;
public float _boxh = 0f;
public float _vcorrection = 0f;
public int _action_up = 0;
public int _action_move = 0;
public int _action_down = 0;
public int _tempselectedday = 0;
public anywheresoftware.b4a.objects.PanelWrapper _dayspanelbackground = null;
public int _dayofweekoffset = 0;
public int _daysinmonth = 0;
public int _tempselectedcolor = 0;
public int _selectedcolor = 0;
public anywheresoftware.b4a.objects.LabelWrapper _lblselectedday = null;
public long _selecteddate = 0L;
public anywheresoftware.b4a.objects.LabelWrapper _targetlabel = null;
public int _selectedyear = 0;
public int _selectedmonth = 0;
public int _selectedday = 0;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label7 = null;
public anywheresoftware.b4a.objects.LabelWrapper[] _daysnames = null;
public Object _mtarget = null;
public String _meventname = "";
public boolean _waitforaddtoactivity = false;
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
		return new Object[] {"ACTION_DOWN",_action_down,"ACTION_MOVE",_action_move,"ACTION_UP",_action_up,"boxH",_boxh,"boxW",_boxw,"Content",Debug.moduleToString(newmoviebay.mncplaymedia.com.content.class),"cvs",_cvs,"cvsBackground",_cvsbackground,"Date",Debug.moduleToString(newmoviebay.mncplaymedia.com.date.class),"DateUtils",_dateutils,"dayOfWeekOffset",_dayofweekoffset,"daysInMonth",_daysinmonth,"daysNames",_daysnames,"DaysPanel",_dayspanel,"DaysPanelBackground",_dayspanelbackground,"ForgotPass",Debug.moduleToString(newmoviebay.mncplaymedia.com.forgotpass.class),"Fungsi",Debug.moduleToString(newmoviebay.mncplaymedia.com.fungsi.class),"holder",_holder,"Label1",_label1,"Label2",_label2,"Label3",_label3,"Label4",_label4,"Label5",_label5,"Label6",_label6,"Label7",_label7,"lblSelectedDay",_lblselectedday,"Main",Debug.moduleToString(newmoviebay.mncplaymedia.com.main.class),"MenuSearch",Debug.moduleToString(newmoviebay.mncplaymedia.com.menusearch.class),"mEventName",_meventname,"MNCUtils2Service",Debug.moduleToString(newmoviebay.mncplaymedia.com.mncutils2service.class),"month",_month,"Months",_months,"mTarget",_mtarget,"Pemutar",Debug.moduleToString(newmoviebay.mncplaymedia.com.pemutar.class),"Play",Debug.moduleToString(newmoviebay.mncplaymedia.com.play.class),"Profile",Debug.moduleToString(newmoviebay.mncplaymedia.com.profile.class),"Register",Debug.moduleToString(newmoviebay.mncplaymedia.com.register.class),"Result",Debug.moduleToString(newmoviebay.mncplaymedia.com.result.class),"selectedColor",_selectedcolor,"selectedDate",_selecteddate,"selectedDay",_selectedday,"selectedMonth",_selectedmonth,"selectedYear",_selectedyear,"Share",Debug.moduleToString(newmoviebay.mncplaymedia.com.share.class),"targetLabel",_targetlabel,"tempSelectedColor",_tempselectedcolor,"tempSelectedDay",_tempselectedday,"Utama",Debug.moduleToString(newmoviebay.mncplaymedia.com.utama.class),"vCorrection",_vcorrection,"waitForAddToActivity",_waitforaddtoactivity,"WebView",Debug.moduleToString(newmoviebay.mncplaymedia.com.webview.class),"year",_year,"Years",_years};
}
public String  _addtoactivity(anywheresoftware.b4a.objects.ActivityWrapper _act,anywheresoftware.b4a.objects.LabelWrapper _lbl) throws Exception{
try {
		Debug.PushSubsStack("AddToActivity (anotherdatepicker) ","anotherdatepicker",9,ba,this,63);
anywheresoftware.b4a.objects.EditTextWrapper _et = null;
int _y = 0;
String _m = "";
int _i = 0;
String _d = "";
Debug.locals.put("ACT", _act);
Debug.locals.put("Lbl", _lbl);
 BA.debugLineNum = 63;BA.debugLine="Public Sub AddToActivity(ACT As Activity, Lbl As Label)";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 64;BA.debugLine="waitForAddToActivity = False";
Debug.ShouldStop(-2147483648);
_waitforaddtoactivity = __c.False;
 BA.debugLineNum = 65;BA.debugLine="holder.Initialize(\"holder\")";
Debug.ShouldStop(1);
_holder.Initialize(ba,"holder");
 BA.debugLineNum = 66;BA.debugLine="holder.Visible = False";
Debug.ShouldStop(2);
_holder.setVisible(__c.False);
 BA.debugLineNum = 67;BA.debugLine="holder.Color = Colors.Transparent";
Debug.ShouldStop(4);
_holder.setColor(__c.Colors.Transparent);
 BA.debugLineNum = 68;BA.debugLine="ACT.AddView(holder, 0, 0, 100%x, 100%y)";
Debug.ShouldStop(8);
_act.AddView((android.view.View)(_holder.getObject()),(int) (0),(int) (0),__c.PerXToCurrent((float) (100),ba),__c.PerYToCurrent((float) (100),ba));
 BA.debugLineNum = 69;BA.debugLine="holder.LoadLayout(\"DatePicker\")";
Debug.ShouldStop(16);
_holder.LoadLayout("DatePicker",ba);
 BA.debugLineNum = 70;BA.debugLine="daysNames = Array As Label(Label1, Label2, Label3, Label4, Label5, Label6, Label7)";
Debug.ShouldStop(32);
_daysnames = new anywheresoftware.b4a.objects.LabelWrapper[]{_label1,_label2,_label3,_label4,_label5,_label6,_label7};
 BA.debugLineNum = 71;BA.debugLine="Dim et As EditText";
Debug.ShouldStop(64);
_et = new anywheresoftware.b4a.objects.EditTextWrapper();Debug.locals.put("et", _et);
 BA.debugLineNum = 72;BA.debugLine="et.Initialize(\"\")";
Debug.ShouldStop(128);
_et.Initialize(ba,"");
 BA.debugLineNum = 73;BA.debugLine="Lbl.BACKGROUND = et.BACKGROUND";
Debug.ShouldStop(256);
_lbl.setBackground(_et.getBackground());
 BA.debugLineNum = 74;BA.debugLine="cvs.Initialize(DaysPanel)";
Debug.ShouldStop(512);
_cvs.Initialize((android.view.View)(_dayspanel.getObject()));
 BA.debugLineNum = 75;BA.debugLine="cvsBackground.Initialize(DaysPanelBackground)";
Debug.ShouldStop(1024);
_cvsbackground.Initialize((android.view.View)(_dayspanelbackground.getObject()));
 BA.debugLineNum = 76;BA.debugLine="month = DateTime.GetMonth(DateTime.Now)";
Debug.ShouldStop(2048);
_month = __c.DateTime.GetMonth(__c.DateTime.getNow());
 BA.debugLineNum = 77;BA.debugLine="year = DateTime.GetYear(DateTime.Now)";
Debug.ShouldStop(4096);
_year = __c.DateTime.GetYear(__c.DateTime.getNow());
 BA.debugLineNum = 78;BA.debugLine="For y = 1900 To 2100";
Debug.ShouldStop(8192);
{
final int step64 = 1;
final int limit64 = (int) (2100);
for (_y = (int) (1900); (step64 > 0 && _y <= limit64) || (step64 < 0 && _y >= limit64); _y = ((int)(0 + _y + step64))) {
Debug.locals.put("y", _y);
 BA.debugLineNum = 79;BA.debugLine="Years.Add(y)";
Debug.ShouldStop(16384);
_years.Add(BA.NumberToString(_y));
 }
}Debug.locals.put("y", _y);
;
 BA.debugLineNum = 81;BA.debugLine="For Each m As String In DateUtils.GetMonthsNames";
Debug.ShouldStop(65536);
final anywheresoftware.b4a.BA.IterableList group67 = _dateutils._getmonthsnames(ba);
final int groupLen67 = group67.getSize();
for (int index67 = 0;index67 < groupLen67 ;index67++){
_m = BA.ObjectToString(group67.Get(index67));Debug.locals.put("m", _m);
Debug.locals.put("m", _m);
 BA.debugLineNum = 82;BA.debugLine="Months.Add(m)";
Debug.ShouldStop(131072);
_months.Add(_m);
 }
Debug.locals.put("m", _m);
;
 BA.debugLineNum = 84;BA.debugLine="Dim i As Int";
Debug.ShouldStop(524288);
_i = 0;Debug.locals.put("i", _i);
 BA.debugLineNum = 85;BA.debugLine="For Each D As String In DateUtils.GetDaysNames";
Debug.ShouldStop(1048576);
final anywheresoftware.b4a.BA.IterableList group71 = _dateutils._getdaysnames(ba);
final int groupLen71 = group71.getSize();
for (int index71 = 0;index71 < groupLen71 ;index71++){
_d = BA.ObjectToString(group71.Get(index71));Debug.locals.put("d", _d);
Debug.locals.put("d", _d);
 BA.debugLineNum = 86;BA.debugLine="daysNames(i).Text = D.SubString2(0, 2)";
Debug.ShouldStop(2097152);
_daysnames[_i].setText((Object)(_d.substring((int) (0),(int) (2))));
 BA.debugLineNum = 87;BA.debugLine="i = i + 1";
Debug.ShouldStop(4194304);
_i = (int) (_i+1);Debug.locals.put("i", _i);
 }
Debug.locals.put("d", _d);
;
 BA.debugLineNum = 89;BA.debugLine="SetDate(DateTime.Now, False)";
Debug.ShouldStop(16777216);
_setdate(__c.DateTime.getNow(),__c.False);
 BA.debugLineNum = 90;BA.debugLine="vCorrection = cvs.MeasureStringHeight(\"1\", Typeface.DEFAULT_BOLD, Label1.TextSize) / 2";
Debug.ShouldStop(33554432);
_vcorrection = (float) (_cvs.MeasureStringHeight("1",__c.Typeface.DEFAULT_BOLD,_label1.getTextSize())/(double)2);
 BA.debugLineNum = 91;BA.debugLine="boxW = cvs.Bitmap.Width / 7";
Debug.ShouldStop(67108864);
_boxw = (float) (_cvs.getBitmap().getWidth()/(double)7);
 BA.debugLineNum = 92;BA.debugLine="boxH = cvs.Bitmap.Height / 6";
Debug.ShouldStop(134217728);
_boxh = (float) (_cvs.getBitmap().getHeight()/(double)6);
 BA.debugLineNum = 93;BA.debugLine="lblSelectedDay.Visible = False";
Debug.ShouldStop(268435456);
_lblselectedday.setVisible(__c.False);
 BA.debugLineNum = 94;BA.debugLine="DrawDays";
Debug.ShouldStop(536870912);
_drawdays();
 BA.debugLineNum = 95;BA.debugLine="targetLabel = Lbl";
Debug.ShouldStop(1073741824);
_targetlabel = _lbl;
 BA.debugLineNum = 96;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _btncancel_click() throws Exception{
try {
		Debug.PushSubsStack("btnCancel_Click (anotherdatepicker) ","anotherdatepicker",9,ba,this,198);
 BA.debugLineNum = 198;BA.debugLine="Public Sub btnCancel_Click";
Debug.ShouldStop(32);
 BA.debugLineNum = 199;BA.debugLine="CallSub3(mTarget, mEventName & \"_Closed\", True, GetDate)";
Debug.ShouldStop(64);
__c.CallSubNew3(ba,_mtarget,_meventname+"_Closed",(Object)(__c.True),(Object)(_getdate()));
 BA.debugLineNum = 200;BA.debugLine="Hide";
Debug.ShouldStop(128);
_hide();
 BA.debugLineNum = 201;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _btntoday_click() throws Exception{
try {
		Debug.PushSubsStack("btnToday_Click (anotherdatepicker) ","anotherdatepicker",9,ba,this,190);
 BA.debugLineNum = 190;BA.debugLine="Private Sub btnToday_Click";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 191;BA.debugLine="SetDate(DateTime.Now, True)";
Debug.ShouldStop(1073741824);
_setdate(__c.DateTime.getNow(),__c.True);
 BA.debugLineNum = 192;BA.debugLine="Log(\"before\")";
Debug.ShouldStop(-2147483648);
__c.Log("before");
 BA.debugLineNum = 193;BA.debugLine="CallSub3(mTarget, mEventName & \"_Closed\", False, GetDate)";
Debug.ShouldStop(1);
__c.CallSubNew3(ba,_mtarget,_meventname+"_Closed",(Object)(__c.False),(Object)(_getdate()));
 BA.debugLineNum = 194;BA.debugLine="Log(\"after\")";
Debug.ShouldStop(2);
__c.Log("after");
 BA.debugLineNum = 195;BA.debugLine="Hide";
Debug.ShouldStop(4);
_hide();
 BA.debugLineNum = 196;BA.debugLine="End Sub";
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
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 4;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 5;BA.debugLine="Private holder As Panel";
_holder = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 6;BA.debugLine="Private cvs, cvsBackground As Canvas";
_cvs = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
_cvsbackground = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 7;BA.debugLine="Private DaysPanel As Panel";
_dayspanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 8;BA.debugLine="Private month, year As Int";
_month = 0;
_year = 0;
 //BA.debugLineNum = 9;BA.debugLine="Private Months As Spinner";
_months = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 10;BA.debugLine="Private Years As Spinner";
_years = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 11;BA.debugLine="Private boxW, boxH As Float";
_boxw = 0f;
_boxh = 0f;
 //BA.debugLineNum = 12;BA.debugLine="Private vCorrection As Float";
_vcorrection = 0f;
 //BA.debugLineNum = 13;BA.debugLine="Private ACTION_UP = 1, ACTION_MOVE = 2, ACTION_DOWN = 0 As Int";
_action_up = (int) (1);
_action_move = (int) (2);
_action_down = (int) (0);
 //BA.debugLineNum = 14;BA.debugLine="Private tempSelectedDay As Int";
_tempselectedday = 0;
 //BA.debugLineNum = 15;BA.debugLine="Private DaysPanelBackground As Panel";
_dayspanelbackground = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private dayOfWeekOffset As Int";
_dayofweekoffset = 0;
 //BA.debugLineNum = 17;BA.debugLine="Private daysInMonth As Int";
_daysinmonth = 0;
 //BA.debugLineNum = 18;BA.debugLine="Private tempSelectedColor As Int = Colors.Cyan";
_tempselectedcolor = __c.Colors.Cyan;
 //BA.debugLineNum = 19;BA.debugLine="Private selectedColor As Int = Colors.RGB(144, 176, 248)";
_selectedcolor = __c.Colors.RGB((int) (144),(int) (176),(int) (248));
 //BA.debugLineNum = 20;BA.debugLine="Private lblSelectedDay As Label";
_lblselectedday = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private selectedDate As Long";
_selecteddate = 0L;
 //BA.debugLineNum = 22;BA.debugLine="Private targetLabel As Label";
_targetlabel = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private selectedYear, selectedMonth, selectedDay As Int";
_selectedyear = 0;
_selectedmonth = 0;
_selectedday = 0;
 //BA.debugLineNum = 24;BA.debugLine="Private Label1 As Label";
_label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private Label2 As Label";
_label2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private Label3 As Label";
_label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private Label4 As Label";
_label4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private Label5 As Label";
_label5 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private Label6 As Label";
_label6 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private Label7 As Label";
_label7 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private daysNames() As Label";
_daysnames = new anywheresoftware.b4a.objects.LabelWrapper[(int) (0)];
{
int d0 = _daysnames.length;
for (int i0 = 0;i0 < d0;i0++) {
_daysnames[i0] = new anywheresoftware.b4a.objects.LabelWrapper();
}
}
;
 //BA.debugLineNum = 32;BA.debugLine="Private mTarget As Object";
_mtarget = new Object();
 //BA.debugLineNum = 33;BA.debugLine="Private mEventName As String";
_meventname = "";
 //BA.debugLineNum = 34;BA.debugLine="Private waitForAddToActivity As Boolean";
_waitforaddtoactivity = false;
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
return "";
}
public String  _dayspanel_touch(int _action,float _x,float _y) throws Exception{
try {
		Debug.PushSubsStack("DaysPanel_Touch (anotherdatepicker) ","anotherdatepicker",9,ba,this,158);
int _boxx = 0;
int _boxy = 0;
int _newselectedday = 0;
boolean _validday = false;
Debug.locals.put("ACTION", _action);
Debug.locals.put("X", _x);
Debug.locals.put("Y", _y);
 BA.debugLineNum = 158;BA.debugLine="Private Sub DaysPanel_Touch (ACTION As Int, X As Float, Y As Float)";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 159;BA.debugLine="Dim boxX = X / boxW, boxY = Y / boxH As Int";
Debug.ShouldStop(1073741824);
_boxx = (int) (_x/(double)_boxw);Debug.locals.put("boxX", _boxx);Debug.locals.put("boxX", _boxx);
_boxy = (int) (_y/(double)_boxh);Debug.locals.put("boxY", _boxy);Debug.locals.put("boxY", _boxy);
 BA.debugLineNum = 160;BA.debugLine="Dim newSelectedDay As Int = boxY * 7 + boxX + 1 - dayOfWeekOffset";
Debug.ShouldStop(-2147483648);
_newselectedday = (int) (_boxy*7+_boxx+1-_dayofweekoffset);Debug.locals.put("newSelectedDay", _newselectedday);Debug.locals.put("newSelectedDay", _newselectedday);
 BA.debugLineNum = 161;BA.debugLine="Dim validDay As Boolean = newSelectedDay > 0 AND newSelectedDay <= daysInMonth";
Debug.ShouldStop(1);
_validday = _newselectedday>0 && _newselectedday<=_daysinmonth;Debug.locals.put("validDay", _validday);Debug.locals.put("validDay", _validday);
 BA.debugLineNum = 162;BA.debugLine="If ACTION = ACTION_DOWN OR ACTION = ACTION_MOVE Then";
Debug.ShouldStop(2);
if (_action==_action_down || _action==_action_move) { 
 BA.debugLineNum = 163;BA.debugLine="If newSelectedDay = tempSelectedDay Then Return";
Debug.ShouldStop(4);
if (_newselectedday==_tempselectedday) { 
if (true) return "";};
 BA.debugLineNum = 164;BA.debugLine="cvsBackground.DrawColor(Colors.Transparent) 'clear background";
Debug.ShouldStop(8);
_cvsbackground.DrawColor(__c.Colors.Transparent);
 BA.debugLineNum = 165;BA.debugLine="tempSelectedDay = newSelectedDay";
Debug.ShouldStop(16);
_tempselectedday = _newselectedday;
 BA.debugLineNum = 166;BA.debugLine="If validDay Then";
Debug.ShouldStop(32);
if (_validday) { 
 BA.debugLineNum = 167;BA.debugLine="DrawBox(cvsBackground, tempSelectedColor, boxX, boxY)";
Debug.ShouldStop(64);
_drawbox(_cvsbackground,_tempselectedcolor,_boxx,_boxy);
 BA.debugLineNum = 168;BA.debugLine="lblSelectedDay.Text = newSelectedDay";
Debug.ShouldStop(128);
_lblselectedday.setText((Object)(_newselectedday));
 BA.debugLineNum = 169;BA.debugLine="lblSelectedDay.Visible = True";
Debug.ShouldStop(256);
_lblselectedday.setVisible(__c.True);
 }else {
 BA.debugLineNum = 171;BA.debugLine="lblSelectedDay.Visible = False";
Debug.ShouldStop(1024);
_lblselectedday.setVisible(__c.False);
 };
 }else 
{ BA.debugLineNum = 173;BA.debugLine="Else If ACTION = ACTION_UP Then";
Debug.ShouldStop(4096);
if (_action==_action_up) { 
 BA.debugLineNum = 174;BA.debugLine="lblSelectedDay.Visible = False";
Debug.ShouldStop(8192);
_lblselectedday.setVisible(__c.False);
 BA.debugLineNum = 175;BA.debugLine="cvsBackground.DrawColor(Colors.Transparent)";
Debug.ShouldStop(16384);
_cvsbackground.DrawColor(__c.Colors.Transparent);
 BA.debugLineNum = 176;BA.debugLine="If validDay Then";
Debug.ShouldStop(32768);
if (_validday) { 
 BA.debugLineNum = 177;BA.debugLine="SelectDay(newSelectedDay, True)";
Debug.ShouldStop(65536);
_selectday(_newselectedday,__c.True);
 BA.debugLineNum = 178;BA.debugLine="CallSub3(mTarget, mEventName & \"_Closed\", False, GetDate)";
Debug.ShouldStop(131072);
__c.CallSubNew3(ba,_mtarget,_meventname+"_Closed",(Object)(__c.False),(Object)(_getdate()));
 BA.debugLineNum = 179;BA.debugLine="Hide";
Debug.ShouldStop(262144);
_hide();
 };
 }};
 BA.debugLineNum = 182;BA.debugLine="DaysPanelBackground.Invalidate";
Debug.ShouldStop(2097152);
_dayspanelbackground.Invalidate();
 BA.debugLineNum = 183;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _designercreateview(anywheresoftware.b4a.objects.PanelWrapper _base,anywheresoftware.b4a.objects.LabelWrapper _lbl,anywheresoftware.b4a.objects.collections.Map _props) throws Exception{
try {
		Debug.PushSubsStack("DesignerCreateView (anotherdatepicker) ","anotherdatepicker",9,ba,this,43);
anywheresoftware.b4a.objects.LabelWrapper _newlbl = null;
anywheresoftware.b4a.objects.ActivityWrapper _act = null;
Debug.locals.put("base", _base);
Debug.locals.put("lbl", _lbl);
Debug.locals.put("props", _props);
 BA.debugLineNum = 43;BA.debugLine="Public Sub DesignerCreateView(base As Panel, lbl As Label, props As Map)";
Debug.ShouldStop(1024);
 BA.debugLineNum = 45;BA.debugLine="Dim newLbl As Label";
Debug.ShouldStop(4096);
_newlbl = new anywheresoftware.b4a.objects.LabelWrapper();Debug.locals.put("newLbl", _newlbl);
 BA.debugLineNum = 46;BA.debugLine="newLbl.Initialize(\"lbl\")";
Debug.ShouldStop(8192);
_newlbl.Initialize(ba,"lbl");
 BA.debugLineNum = 47;BA.debugLine="newLbl.TextSize = lbl.TextSize";
Debug.ShouldStop(16384);
_newlbl.setTextSize(_lbl.getTextSize());
 BA.debugLineNum = 48;BA.debugLine="newLbl.TextColor = lbl.TextColor";
Debug.ShouldStop(32768);
_newlbl.setTextColor(_lbl.getTextColor());
 BA.debugLineNum = 49;BA.debugLine="base.AddView(newLbl, 0, 0, base.Width, base.Height)";
Debug.ShouldStop(65536);
_base.AddView((android.view.View)(_newlbl.getObject()),(int) (0),(int) (0),_base.getWidth(),_base.getHeight());
 BA.debugLineNum = 50;BA.debugLine="Dim act As Activity = props.Get(\"activity\")";
Debug.ShouldStop(131072);
_act = new anywheresoftware.b4a.objects.ActivityWrapper();
_act.setObject((anywheresoftware.b4a.BALayout)(_props.Get((Object)("activity"))));Debug.locals.put("act", _act);
 BA.debugLineNum = 51;BA.debugLine="waitForAddToActivity = True";
Debug.ShouldStop(262144);
_waitforaddtoactivity = __c.True;
 BA.debugLineNum = 54;BA.debugLine="CallSubDelayed3(Me, \"AddToActivity\",act , newLbl)";
Debug.ShouldStop(2097152);
__c.CallSubDelayed3(ba,this,"AddToActivity",(Object)(_act),(Object)(_newlbl));
 BA.debugLineNum = 55;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _drawbox(anywheresoftware.b4a.objects.drawable.CanvasWrapper _c,int _clr,int _x,int _y) throws Exception{
try {
		Debug.PushSubsStack("DrawBox (anotherdatepicker) ","anotherdatepicker",9,ba,this,152);
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _r = null;
Debug.locals.put("c", _c);
Debug.locals.put("clr", _clr);
Debug.locals.put("x", _x);
Debug.locals.put("y", _y);
 BA.debugLineNum = 152;BA.debugLine="Private Sub DrawBox(c As Canvas, clr As Int, x As Int, y As Int)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 153;BA.debugLine="Dim r As Rect";
Debug.ShouldStop(16777216);
_r = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();Debug.locals.put("r", _r);
 BA.debugLineNum = 154;BA.debugLine="r.Initialize(x * boxW, y * boxH, (x + 1) * boxW, (y + 1) * boxH)";
Debug.ShouldStop(33554432);
_r.Initialize((int) (_x*_boxw),(int) (_y*_boxh),(int) ((_x+1)*_boxw),(int) ((_y+1)*_boxh));
 BA.debugLineNum = 155;BA.debugLine="c.DrawRect(r, clr, True, 0)";
Debug.ShouldStop(67108864);
_c.DrawRect((android.graphics.Rect)(_r.getObject()),_clr,__c.True,(float) (0));
 BA.debugLineNum = 156;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _drawdays() throws Exception{
try {
		Debug.PushSubsStack("DrawDays (anotherdatepicker) ","anotherdatepicker",9,ba,this,115);
long _firstday = 0L;
int _day = 0;
int _row = 0;
 BA.debugLineNum = 115;BA.debugLine="Private Sub DrawDays";
Debug.ShouldStop(262144);
 BA.debugLineNum = 116;BA.debugLine="cvsBackground.DrawColor(Colors.Transparent)";
Debug.ShouldStop(524288);
_cvsbackground.DrawColor(__c.Colors.Transparent);
 BA.debugLineNum = 117;BA.debugLine="cvs.DrawColor(Colors.Transparent)";
Debug.ShouldStop(1048576);
_cvs.DrawColor(__c.Colors.Transparent);
 BA.debugLineNum = 118;BA.debugLine="Dim firstDay As Long = DateUtils.SetDate(year, month, 1) - 1";
Debug.ShouldStop(2097152);
_firstday = (long) (_dateutils._setdate(ba,_year,_month,(int) (1))-1);Debug.locals.put("firstDay", _firstday);Debug.locals.put("firstDay", _firstday);
 BA.debugLineNum = 119;BA.debugLine="dayOfWeekOffset = DateTime.GetDayOfWeek(firstDay) Mod 7";
Debug.ShouldStop(4194304);
_dayofweekoffset = (int) (__c.DateTime.GetDayOfWeek(_firstday)%7);
 BA.debugLineNum = 120;BA.debugLine="daysInMonth = DateUtils.NumberOfDaysInMonth(month, year)";
Debug.ShouldStop(8388608);
_daysinmonth = _dateutils._numberofdaysinmonth(ba,_month,_year);
 BA.debugLineNum = 121;BA.debugLine="If year = selectedYear AND month = selectedMonth Then";
Debug.ShouldStop(16777216);
if (_year==_selectedyear && _month==_selectedmonth) { 
 BA.debugLineNum = 123;BA.debugLine="DrawBox(cvs, selectedColor, (selectedDay - 1 + dayOfWeekOffset) Mod 7, _ 			(selectedDay - 1 + dayOfWeekOffset) / 7)";
Debug.ShouldStop(67108864);
_drawbox(_cvs,_selectedcolor,(int) ((_selectedday-1+_dayofweekoffset)%7),(int) ((_selectedday-1+_dayofweekoffset)/(double)7));
 };
 BA.debugLineNum = 126;BA.debugLine="For day = 1 To daysInMonth";
Debug.ShouldStop(536870912);
{
final int step106 = 1;
final int limit106 = _daysinmonth;
for (_day = (int) (1); (step106 > 0 && _day <= limit106) || (step106 < 0 && _day >= limit106); _day = ((int)(0 + _day + step106))) {
Debug.locals.put("day", _day);
 BA.debugLineNum = 127;BA.debugLine="Dim row As Int = (day - 1 + dayOfWeekOffset) / 7";
Debug.ShouldStop(1073741824);
_row = (int) ((_day-1+_dayofweekoffset)/(double)7);Debug.locals.put("row", _row);Debug.locals.put("row", _row);
 BA.debugLineNum = 128;BA.debugLine="cvs.DrawText(day, (((dayOfWeekOffset + day - 1) Mod 7) + 0.5) * boxW, _ 			(row + 0.5)* boxH + vCorrection, Typeface.DEFAULT_BOLD, Label1.TextSize, Colors.Black, \"CENTER\")";
Debug.ShouldStop(-2147483648);
_cvs.DrawText(ba,BA.NumberToString(_day),(float) ((((_dayofweekoffset+_day-1)%7)+0.5)*_boxw),(float) ((_row+0.5)*_boxh+_vcorrection),__c.Typeface.DEFAULT_BOLD,_label1.getTextSize(),__c.Colors.Black,BA.getEnumFromString(android.graphics.Paint.Align.class,"CENTER"));
 }
}Debug.locals.put("day", _day);
;
 BA.debugLineNum = 137;BA.debugLine="DaysPanel.Invalidate";
Debug.ShouldStop(256);
_dayspanel.Invalidate();
 BA.debugLineNum = 138;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public long  _getdate() throws Exception{
try {
		Debug.PushSubsStack("GetDate (anotherdatepicker) ","anotherdatepicker",9,ba,this,98);
 BA.debugLineNum = 98;BA.debugLine="Public Sub GetDate As Long";
Debug.ShouldStop(2);
 BA.debugLineNum = 99;BA.debugLine="Return selectedDate";
Debug.ShouldStop(4);
if (true) return _selecteddate;
 BA.debugLineNum = 100;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return 0L;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _hide() throws Exception{
try {
		Debug.PushSubsStack("Hide (anotherdatepicker) ","anotherdatepicker",9,ba,this,148);
 BA.debugLineNum = 148;BA.debugLine="Public Sub Hide";
Debug.ShouldStop(524288);
 BA.debugLineNum = 149;BA.debugLine="holder.Visible = False";
Debug.ShouldStop(1048576);
_holder.setVisible(__c.False);
 BA.debugLineNum = 150;BA.debugLine="End Sub";
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
public String  _holder_click() throws Exception{
try {
		Debug.PushSubsStack("holder_Click (anotherdatepicker) ","anotherdatepicker",9,ba,this,215);
 BA.debugLineNum = 215;BA.debugLine="Private Sub holder_Click";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 216;BA.debugLine="btnCancel_Click";
Debug.ShouldStop(8388608);
_btncancel_click();
 BA.debugLineNum = 217;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _initialize(anywheresoftware.b4a.BA _ba,Object _target,String _eventname) throws Exception{
innerInitialize(_ba);
try {
		Debug.PushSubsStack("Initialize (anotherdatepicker) ","anotherdatepicker",9,ba,this,38);
Debug.locals.put("ba", _ba);
Debug.locals.put("Target", _target);
Debug.locals.put("EventName", _eventname);
 BA.debugLineNum = 38;BA.debugLine="Public Sub Initialize (Target As Object, EventName As String)";
Debug.ShouldStop(32);
 BA.debugLineNum = 39;BA.debugLine="mTarget = Target";
Debug.ShouldStop(64);
_mtarget = _target;
 BA.debugLineNum = 40;BA.debugLine="mEventName = EventName";
Debug.ShouldStop(128);
_meventname = _eventname;
 BA.debugLineNum = 41;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public boolean  _isvisible() throws Exception{
try {
		Debug.PushSubsStack("IsVisible (anotherdatepicker) ","anotherdatepicker",9,ba,this,212);
 BA.debugLineNum = 212;BA.debugLine="Public Sub IsVisible As Boolean";
Debug.ShouldStop(524288);
 BA.debugLineNum = 213;BA.debugLine="Return holder.Visible";
Debug.ShouldStop(1048576);
if (true) return _holder.getVisible();
 BA.debugLineNum = 214;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return false;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _lbl_click() throws Exception{
try {
		Debug.PushSubsStack("lbl_Click (anotherdatepicker) ","anotherdatepicker",9,ba,this,57);
 BA.debugLineNum = 57;BA.debugLine="Private Sub lbl_Click";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 58;BA.debugLine="Show";
Debug.ShouldStop(33554432);
_show();
 BA.debugLineNum = 59;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _months_itemclick(int _position,Object _value) throws Exception{
try {
		Debug.PushSubsStack("Months_ItemClick (anotherdatepicker) ","anotherdatepicker",9,ba,this,203);
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 203;BA.debugLine="Private Sub Months_ItemClick (Position As Int, Value As Object)";
Debug.ShouldStop(1024);
 BA.debugLineNum = 204;BA.debugLine="month = Position + 1";
Debug.ShouldStop(2048);
_month = (int) (_position+1);
 BA.debugLineNum = 205;BA.debugLine="DrawDays";
Debug.ShouldStop(4096);
_drawdays();
 BA.debugLineNum = 206;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _selectday(int _day,boolean _updatelabel) throws Exception{
try {
		Debug.PushSubsStack("SelectDay (anotherdatepicker) ","anotherdatepicker",9,ba,this,140);
Debug.locals.put("day", _day);
Debug.locals.put("UpdateLabel", _updatelabel);
 BA.debugLineNum = 140;BA.debugLine="Private Sub SelectDay(day As Int, UpdateLabel As Boolean)";
Debug.ShouldStop(2048);
 BA.debugLineNum = 141;BA.debugLine="selectedDate = DateUtils.SetDate(year, month, day)";
Debug.ShouldStop(4096);
_selecteddate = _dateutils._setdate(ba,_year,_month,_day);
 BA.debugLineNum = 142;BA.debugLine="selectedDay = day";
Debug.ShouldStop(8192);
_selectedday = _day;
 BA.debugLineNum = 143;BA.debugLine="selectedMonth = month";
Debug.ShouldStop(16384);
_selectedmonth = _month;
 BA.debugLineNum = 144;BA.debugLine="selectedYear = year";
Debug.ShouldStop(32768);
_selectedyear = _year;
 BA.debugLineNum = 145;BA.debugLine="If UpdateLabel Then targetLabel.Text = DateTime.Date(selectedDate)";
Debug.ShouldStop(65536);
if (_updatelabel) { 
_targetlabel.setText((Object)(__c.DateTime.Date(_selecteddate)));};
 BA.debugLineNum = 146;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _setdate(long _date,boolean _updatelabel) throws Exception{
try {
		Debug.PushSubsStack("SetDate (anotherdatepicker) ","anotherdatepicker",9,ba,this,103);
Debug.locals.put("Date", _date);
Debug.locals.put("UpdateLabel", _updatelabel);
 BA.debugLineNum = 103;BA.debugLine="Public Sub SetDate(Date As Long, UpdateLabel As Boolean)";
Debug.ShouldStop(64);
 BA.debugLineNum = 104;BA.debugLine="If waitForAddToActivity Then";
Debug.ShouldStop(128);
if (_waitforaddtoactivity) { 
 BA.debugLineNum = 105;BA.debugLine="CallSubDelayed3(Me, \"SetDate\", Date, UpdateLabel)";
Debug.ShouldStop(256);
__c.CallSubDelayed3(ba,this,"SetDate",(Object)(_date),(Object)(_updatelabel));
 BA.debugLineNum = 106;BA.debugLine="Return";
Debug.ShouldStop(512);
if (true) return "";
 };
 BA.debugLineNum = 108;BA.debugLine="year = DateTime.GetYear(Date)";
Debug.ShouldStop(2048);
_year = __c.DateTime.GetYear(_date);
 BA.debugLineNum = 109;BA.debugLine="month = DateTime.GetMonth(Date)";
Debug.ShouldStop(4096);
_month = __c.DateTime.GetMonth(_date);
 BA.debugLineNum = 110;BA.debugLine="SelectDay(DateTime.GetDayOfMonth(Date), UpdateLabel)";
Debug.ShouldStop(8192);
_selectday(__c.DateTime.GetDayOfMonth(_date),_updatelabel);
 BA.debugLineNum = 111;BA.debugLine="Years.SelectedIndex = year - 1900";
Debug.ShouldStop(16384);
_years.setSelectedIndex((int) (_year-1900));
 BA.debugLineNum = 112;BA.debugLine="Months.SelectedIndex = month - 1";
Debug.ShouldStop(32768);
_months.setSelectedIndex((int) (_month-1));
 BA.debugLineNum = 113;BA.debugLine="End Sub";
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
public String  _show() throws Exception{
try {
		Debug.PushSubsStack("Show (anotherdatepicker) ","anotherdatepicker",9,ba,this,185);
 BA.debugLineNum = 185;BA.debugLine="Public Sub Show";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 186;BA.debugLine="holder.Visible = True";
Debug.ShouldStop(33554432);
_holder.setVisible(__c.True);
 BA.debugLineNum = 187;BA.debugLine="DrawDays";
Debug.ShouldStop(67108864);
_drawdays();
 BA.debugLineNum = 188;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _years_itemclick(int _position,Object _value) throws Exception{
try {
		Debug.PushSubsStack("Years_ItemClick (anotherdatepicker) ","anotherdatepicker",9,ba,this,208);
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 208;BA.debugLine="Private Sub Years_ItemClick (Position As Int, Value As Object)";
Debug.ShouldStop(32768);
 BA.debugLineNum = 209;BA.debugLine="year = Value";
Debug.ShouldStop(65536);
_year = (int)(BA.ObjectToNumber(_value));
 BA.debugLineNum = 210;BA.debugLine="DrawDays";
Debug.ShouldStop(131072);
_drawdays();
 BA.debugLineNum = 211;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return "";
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
if (BA.fastSubCompare(sub, "ADDTOACTIVITY"))
	return _addtoactivity((anywheresoftware.b4a.objects.ActivityWrapper) args[0], (anywheresoftware.b4a.objects.LabelWrapper) args[1]);
if (BA.fastSubCompare(sub, "SETDATE"))
	return _setdate(((Number)args[0]).longValue(), (Boolean) args[1]);
return BA.SubDelegator.SubNotFound;
}
}
