package newmoviebay.mncplaymedia.com;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class clsactionbar extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "newmoviebay.mncplaymedia.com.clsactionbar");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            if (BA.isShellModeRuntimeCheck(ba)) {
			    ba.raiseEvent2(null, true, "CREATE", true, "newmoviebay.mncplaymedia.com.clsactionbar",
                    ba);
                return;
		    }
        }
        ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.LabelWrapper _title = null;
public anywheresoftware.b4a.objects.PanelWrapper _actionbar = null;
public anywheresoftware.b4a.objects.PanelWrapper _abparent = null;
public boolean _abdarktheme = false;
public anywheresoftware.b4a.objects.collections.List _ableftstack = null;
public anywheresoftware.b4a.objects.collections.List _abrightstack = null;
public Object _abmodule = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _abcanvas = null;
public byte _abdividerwidth = (byte)0;
public int _absamewidthforall = 0;
public boolean _abiconaswideastext = false;
public Object _abpresseddrawable = null;
public Object _abselecteddrawable = null;
public Object _abdefaultdividerdrawable = null;
public Object _abdividerdrawable = null;
public int _abstartx = 0;
public anywheresoftware.b4a.objects.PanelWrapper _abplaceholder = null;
public anywheresoftware.b4a.objects.ConcreteViewWrapper _abdraggedbtn = null;
public Object _abviewtoblock = null;
public String _abonafterdropsub = "";
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
		return new Object[] {"abCanvas",_abcanvas,"abDarkTheme",_abdarktheme,"abDefaultDividerDrawable",_abdefaultdividerdrawable,"abDividerDrawable",_abdividerdrawable,"abDividerWidth",_abdividerwidth,"abDraggedBtn",_abdraggedbtn,"abIconAsWideAsText",_abiconaswideastext,"abLeftStack",_ableftstack,"abModule",_abmodule,"abOnAfterDropSub",_abonafterdropsub,"abParent",_abparent,"abPlaceHolder",_abplaceholder,"abPressedDrawable",_abpresseddrawable,"abRightStack",_abrightstack,"abSameWidthForAll",_absamewidthforall,"abSelectedDrawable",_abselecteddrawable,"abStartX",_abstartx,"abViewToBlock",_abviewtoblock,"ActionBar",_actionbar,"Content",Debug.moduleToString(newmoviebay.mncplaymedia.com.content.class),"Date",Debug.moduleToString(newmoviebay.mncplaymedia.com.date.class),"DateUtils",_dateutils,"ForgotPass",Debug.moduleToString(newmoviebay.mncplaymedia.com.forgotpass.class),"Fungsi",Debug.moduleToString(newmoviebay.mncplaymedia.com.fungsi.class),"Main",Debug.moduleToString(newmoviebay.mncplaymedia.com.main.class),"MenuSearch",Debug.moduleToString(newmoviebay.mncplaymedia.com.menusearch.class),"MNCUtils2Service",Debug.moduleToString(newmoviebay.mncplaymedia.com.mncutils2service.class),"Pemutar",Debug.moduleToString(newmoviebay.mncplaymedia.com.pemutar.class),"Play",Debug.moduleToString(newmoviebay.mncplaymedia.com.play.class),"Profile",Debug.moduleToString(newmoviebay.mncplaymedia.com.profile.class),"Register",Debug.moduleToString(newmoviebay.mncplaymedia.com.register.class),"Result",Debug.moduleToString(newmoviebay.mncplaymedia.com.result.class),"Share",Debug.moduleToString(newmoviebay.mncplaymedia.com.share.class),"Title",_title,"Utama",Debug.moduleToString(newmoviebay.mncplaymedia.com.utama.class),"WebView",Debug.moduleToString(newmoviebay.mncplaymedia.com.webview.class)};
}
public static class _typaction{
public boolean IsInitialized;
public Object Module;
public String OnClickSub;
public String OnLongClickSub;
public Object Tag;
public void Initialize() {
IsInitialized = true;
Module = new Object();
OnClickSub = "";
OnLongClickSub = "";
Tag = new Object();
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public String  _abortdraganddrop() throws Exception{
try {
		Debug.PushSubsStack("AbortDragAndDrop (clsactionbar) ","clsactionbar",11,ba,this,456);
int _p = 0;
 BA.debugLineNum = 456;BA.debugLine="Public Sub AbortDragAndDrop";
Debug.ShouldStop(128);
 BA.debugLineNum = 457;BA.debugLine="If abPlaceHolder.IsInitialized Then";
Debug.ShouldStop(256);
if (_abplaceholder.IsInitialized()) { 
 BA.debugLineNum = 458;BA.debugLine="Dim P As Int";
Debug.ShouldStop(512);
_p = 0;Debug.locals.put("P", _p);
 BA.debugLineNum = 459;BA.debugLine="P = LeftPosition(abPlaceHolder)";
Debug.ShouldStop(1024);
_p = _leftposition(_abplaceholder);Debug.locals.put("P", _p);
 BA.debugLineNum = 460;BA.debugLine="If P >= 0 Then";
Debug.ShouldStop(2048);
if (_p>=0) { 
 BA.debugLineNum = 461;BA.debugLine="abLeftStack.Set(P, abDraggedBtn)";
Debug.ShouldStop(4096);
_ableftstack.Set(_p,(Object)(_abdraggedbtn.getObject()));
 BA.debugLineNum = 462;BA.debugLine="UpdateSignOfTag(abDraggedBtn, False)";
Debug.ShouldStop(8192);
_updatesignoftag((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_abdraggedbtn.getObject())),__c.False);
 }else {
 BA.debugLineNum = 464;BA.debugLine="P = RightPosition(abPlaceHolder)";
Debug.ShouldStop(32768);
_p = _rightposition(_abplaceholder);Debug.locals.put("P", _p);
 BA.debugLineNum = 465;BA.debugLine="If P >= 0 Then";
Debug.ShouldStop(65536);
if (_p>=0) { 
 BA.debugLineNum = 466;BA.debugLine="abRightStack.Set(P, abDraggedBtn)";
Debug.ShouldStop(131072);
_abrightstack.Set(_p,(Object)(_abdraggedbtn.getObject()));
 BA.debugLineNum = 467;BA.debugLine="UpdateSignOfTag(abDraggedBtn, True)";
Debug.ShouldStop(262144);
_updatesignoftag((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_abdraggedbtn.getObject())),__c.True);
 };
 };
 BA.debugLineNum = 470;BA.debugLine="abDraggedBtn.Background = abPlaceHolder.Background";
Debug.ShouldStop(2097152);
_abdraggedbtn.setBackground(_abplaceholder.getBackground());
 BA.debugLineNum = 471;BA.debugLine="abPlaceHolder.RemoveView";
Debug.ShouldStop(4194304);
_abplaceholder.RemoveView();
 BA.debugLineNum = 472;BA.debugLine="abPlaceHolder = Null";
Debug.ShouldStop(8388608);
_abplaceholder.setObject((android.view.ViewGroup)(__c.Null));
 BA.debugLineNum = 473;BA.debugLine="ReorderViews";
Debug.ShouldStop(16777216);
_reorderviews();
 };
 BA.debugLineNum = 475;BA.debugLine="End Sub";
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
public anywheresoftware.b4a.objects.ConcreteViewWrapper  _addbutton(Object _icon,String _text,byte _textposition,short _buttonposition,String _onclicksub,String _onlongclicksub) throws Exception{
try {
		Debug.PushSubsStack("AddButton (clsactionbar) ","clsactionbar",11,ba,this,223);
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
anywheresoftware.b4a.objects.ImageViewWrapper _iv = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
newmoviebay.mncplaymedia.com.clsactionbar._typaction _action = null;
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
Debug.locals.put("Icon", _icon);
Debug.locals.put("Text", _text);
Debug.locals.put("TextPosition", _textposition);
Debug.locals.put("ButtonPosition", _buttonposition);
Debug.locals.put("OnClickSub", _onclicksub);
Debug.locals.put("OnLongClickSub", _onlongclicksub);
 BA.debugLineNum = 223;BA.debugLine="Public Sub AddButton(Icon As Object, Text As String, TextPosition As Byte, ButtonPosition As Short, OnClickSub As String, OnLongClickSub As String) As View";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 224;BA.debugLine="If Icon = Null AND Text = \"\" Then";
Debug.ShouldStop(-2147483648);
if (_icon== null && (_text).equals("")) { 
 BA.debugLineNum = 225;BA.debugLine="Log(\"Icon and Text are both null\")";
Debug.ShouldStop(1);
__c.Log("Icon and Text are both null");
 BA.debugLineNum = 226;BA.debugLine="Return Null";
Debug.ShouldStop(2);
if (true) return (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(__c.Null));
 };
 BA.debugLineNum = 228;BA.debugLine="If TextPosition < 1 OR TextPosition > 7 Then";
Debug.ShouldStop(8);
if (_textposition<1 || _textposition>7) { 
 BA.debugLineNum = 229;BA.debugLine="Log(\"Invalid TextPosition\")";
Debug.ShouldStop(16);
__c.Log("Invalid TextPosition");
 BA.debugLineNum = 230;BA.debugLine="Return Null";
Debug.ShouldStop(32);
if (true) return (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(__c.Null));
 };
 BA.debugLineNum = 233;BA.debugLine="Dim pnl As Panel";
Debug.ShouldStop(256);
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();Debug.locals.put("pnl", _pnl);
 BA.debugLineNum = 234;BA.debugLine="pnl.Initialize(\"Button\")";
Debug.ShouldStop(512);
_pnl.Initialize(ba,"Button");
 BA.debugLineNum = 236;BA.debugLine="Dim IV As ImageView";
Debug.ShouldStop(2048);
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();Debug.locals.put("IV", _iv);
 BA.debugLineNum = 237;BA.debugLine="If Icon <> Null Then";
Debug.ShouldStop(4096);
if (_icon!= null) { 
 BA.debugLineNum = 238;BA.debugLine="IV.Initialize(\"\")";
Debug.ShouldStop(8192);
_iv.Initialize(ba,"");
 BA.debugLineNum = 239;BA.debugLine="If Icon Is Bitmap Then";
Debug.ShouldStop(16384);
if (_icon instanceof android.graphics.Bitmap) { 
 BA.debugLineNum = 240;BA.debugLine="IV.Bitmap = Icon";
Debug.ShouldStop(32768);
_iv.setBitmap((android.graphics.Bitmap)(_icon));
 BA.debugLineNum = 241;BA.debugLine="IV.Gravity = Gravity.FILL";
Debug.ShouldStop(65536);
_iv.setGravity(__c.Gravity.FILL);
 }else {
 BA.debugLineNum = 243;BA.debugLine="IV.Background = Icon";
Debug.ShouldStop(262144);
_iv.setBackground((android.graphics.drawable.Drawable)(_icon));
 };
 BA.debugLineNum = 245;BA.debugLine="pnl.AddView(IV, 0, 0, 0, 0)";
Debug.ShouldStop(1048576);
_pnl.AddView((android.view.View)(_iv.getObject()),(int) (0),(int) (0),(int) (0),(int) (0));
 };
 BA.debugLineNum = 248;BA.debugLine="Dim lbl As Label";
Debug.ShouldStop(8388608);
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 249;BA.debugLine="lbl.Initialize(\"\")";
Debug.ShouldStop(16777216);
_lbl.Initialize(ba,"");
 BA.debugLineNum = 250;BA.debugLine="lbl.Gravity = Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL";
Debug.ShouldStop(33554432);
_lbl.setGravity((int) (__c.Gravity.CENTER_HORIZONTAL+__c.Gravity.CENTER_VERTICAL));
 BA.debugLineNum = 251;BA.debugLine="lbl.Text = Text";
Debug.ShouldStop(67108864);
_lbl.setText((Object)(_text));
 BA.debugLineNum = 252;BA.debugLine="If abDarkTheme Then";
Debug.ShouldStop(134217728);
if (_abdarktheme) { 
 BA.debugLineNum = 253;BA.debugLine="lbl.TextColor = Colors.White";
Debug.ShouldStop(268435456);
_lbl.setTextColor(__c.Colors.White);
 }else {
 BA.debugLineNum = 255;BA.debugLine="lbl.TextColor = Colors.Black";
Debug.ShouldStop(1073741824);
_lbl.setTextColor(__c.Colors.Black);
 };
 BA.debugLineNum = 257;BA.debugLine="lbl.TextSize = Round(ActionBar.Height / Density / 2.8)";
Debug.ShouldStop(1);
_lbl.setTextSize((float) (__c.Round(_actionbar.getHeight()/(double)__c.Density/(double)2.8)));
 BA.debugLineNum = 258;BA.debugLine="lbl.Typeface = Typeface.DEFAULT_BOLD";
Debug.ShouldStop(2);
_lbl.setTypeface(__c.Typeface.DEFAULT_BOLD);
 BA.debugLineNum = 259;BA.debugLine="If ButtonPosition < 0 Then";
Debug.ShouldStop(4);
if (_buttonposition<0) { 
 BA.debugLineNum = 260;BA.debugLine="lbl.Tag = -TextPosition";
Debug.ShouldStop(8);
_lbl.setTag((Object)(-_textposition));
 }else {
 BA.debugLineNum = 262;BA.debugLine="lbl.Tag = TextPosition";
Debug.ShouldStop(32);
_lbl.setTag((Object)(_textposition));
 };
 BA.debugLineNum = 264;BA.debugLine="pnl.AddView(lbl, 0, 0, 0, 0)";
Debug.ShouldStop(128);
_pnl.AddView((android.view.View)(_lbl.getObject()),(int) (0),(int) (0),(int) (0),(int) (0));
 BA.debugLineNum = 266;BA.debugLine="ActionBar.AddView(pnl, 0, 0, ResizeButton(IV, lbl, TextPosition, ButtonPosition < 0, abSameWidthForAll), ActionBar.Height)";
Debug.ShouldStop(512);
_actionbar.AddView((android.view.View)(_pnl.getObject()),(int) (0),(int) (0),_resizebutton(_iv,_lbl,_textposition,_buttonposition<0,_absamewidthforall),_actionbar.getHeight());
 BA.debugLineNum = 267;BA.debugLine="UpdateBackground(pnl, ButtonPosition < 0)";
Debug.ShouldStop(1024);
_updatebackground(_pnl,_buttonposition<0);
 BA.debugLineNum = 269;BA.debugLine="If ButtonPosition < 0 Then";
Debug.ShouldStop(4096);
if (_buttonposition<0) { 
 BA.debugLineNum = 270;BA.debugLine="abRightStack.InsertAt(Abs(ButtonPosition) - 1, pnl)";
Debug.ShouldStop(8192);
_abrightstack.InsertAt((int) (__c.Abs(_buttonposition)-1),(Object)(_pnl.getObject()));
 }else 
{ BA.debugLineNum = 271;BA.debugLine="Else If ButtonPosition > 0 Then";
Debug.ShouldStop(16384);
if (_buttonposition>0) { 
 BA.debugLineNum = 272;BA.debugLine="abLeftStack.InsertAt(ButtonPosition - 1, pnl)";
Debug.ShouldStop(32768);
_ableftstack.InsertAt((int) (_buttonposition-1),(Object)(_pnl.getObject()));
 }else {
 BA.debugLineNum = 274;BA.debugLine="abLeftStack.Add(pnl)";
Debug.ShouldStop(131072);
_ableftstack.Add((Object)(_pnl.getObject()));
 }};
 BA.debugLineNum = 276;BA.debugLine="If abSameWidthForAll > 0 Then";
Debug.ShouldStop(524288);
if (_absamewidthforall>0) { 
 BA.debugLineNum = 277;BA.debugLine="SameWidthForAll(True)";
Debug.ShouldStop(1048576);
_samewidthforall(__c.True);
 }else {
 BA.debugLineNum = 279;BA.debugLine="ReorderViews";
Debug.ShouldStop(4194304);
_reorderviews();
 };
 BA.debugLineNum = 282;BA.debugLine="Dim Action As typAction";
Debug.ShouldStop(33554432);
_action = new newmoviebay.mncplaymedia.com.clsactionbar._typaction();Debug.locals.put("Action", _action);
 BA.debugLineNum = 283;BA.debugLine="Action.Initialize";
Debug.ShouldStop(67108864);
_action.Initialize();
 BA.debugLineNum = 284;BA.debugLine="Action.Module = abModule";
Debug.ShouldStop(134217728);
_action.Module = _abmodule;Debug.locals.put("Action", _action);
 BA.debugLineNum = 285;BA.debugLine="Action.OnClickSub = OnClickSub";
Debug.ShouldStop(268435456);
_action.OnClickSub = _onclicksub;Debug.locals.put("Action", _action);
 BA.debugLineNum = 286;BA.debugLine="Action.OnLongClickSub = OnLongClickSub";
Debug.ShouldStop(536870912);
_action.OnLongClickSub = _onlongclicksub;Debug.locals.put("Action", _action);
 BA.debugLineNum = 287;BA.debugLine="pnl.Tag = Action";
Debug.ShouldStop(1073741824);
_pnl.setTag((Object)(_action));
 BA.debugLineNum = 288;BA.debugLine="Dim r As Reflector";
Debug.ShouldStop(-2147483648);
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();Debug.locals.put("r", _r);
 BA.debugLineNum = 289;BA.debugLine="r.Target = pnl";
Debug.ShouldStop(1);
_r.Target = (Object)(_pnl.getObject());Debug.locals.put("r", _r);
 BA.debugLineNum = 290;BA.debugLine="r.SetOnTouchListener(\"Btn_Touch\")";
Debug.ShouldStop(2);
_r.SetOnTouchListener(ba,"Btn_Touch");
 BA.debugLineNum = 291;BA.debugLine="If OnLongClickSub = \"\" Then r.SetOnLongClickListener(\"\")";
Debug.ShouldStop(4);
if ((_onlongclicksub).equals("")) { 
_r.SetOnLongClickListener(ba,"");};
 BA.debugLineNum = 292;BA.debugLine="Return pnl";
Debug.ShouldStop(8);
if (true) return (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_pnl.getObject()));
 BA.debugLineNum = 293;BA.debugLine="End Sub";
Debug.ShouldStop(16);
return null;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public anywheresoftware.b4a.objects.PanelWrapper  _aspanel() throws Exception{
try {
		Debug.PushSubsStack("AsPanel (clsactionbar) ","clsactionbar",11,ba,this,654);
 BA.debugLineNum = 654;BA.debugLine="Public Sub AsPanel As Panel";
Debug.ShouldStop(8192);
 BA.debugLineNum = 655;BA.debugLine="Return ActionBar";
Debug.ShouldStop(16384);
if (true) return _actionbar;
 BA.debugLineNum = 656;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
return null;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public boolean  _btn_touch(Object _viewtag,int _action,float _x,float _y,Object _motionevent) throws Exception{
try {
		Debug.PushSubsStack("Btn_Touch (clsactionbar) ","clsactionbar",11,ba,this,477);
anywheresoftware.b4a.objects.ConcreteViewWrapper _touchedbtn = null;
newmoviebay.mncplaymedia.com.clsactionbar._typaction _btnaction = null;
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
anywheresoftware.b4a.objects.ConcreteViewWrapper _btn = null;
int _i = 0;
Debug.locals.put("ViewTag", _viewtag);
Debug.locals.put("Action", _action);
Debug.locals.put("X", _x);
Debug.locals.put("Y", _y);
Debug.locals.put("MotionEvent", _motionevent);
 BA.debugLineNum = 477;BA.debugLine="Private Sub Btn_Touch(ViewTag As Object, Action As Int, X As Float, Y As Float, MotionEvent As Object) As Boolean";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 478;BA.debugLine="If Action = 0 Then		'= DOWN";
Debug.ShouldStop(536870912);
if (_action==0) { 
 BA.debugLineNum = 479;BA.debugLine="abStartX = X";
Debug.ShouldStop(1073741824);
_abstartx = (int) (_x);
 BA.debugLineNum = 480;BA.debugLine="Dim TouchedBtn As View = Sender";
Debug.ShouldStop(-2147483648);
_touchedbtn = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
_touchedbtn.setObject((android.view.View)(__c.Sender(ba)));Debug.locals.put("TouchedBtn", _touchedbtn);
 BA.debugLineNum = 481;BA.debugLine="TouchedBtn.BringToFront";
Debug.ShouldStop(1);
_touchedbtn.BringToFront();
 }else 
{ BA.debugLineNum = 482;BA.debugLine="Else If Action = 1 Then	'= UP";
Debug.ShouldStop(2);
if (_action==1) { 
 BA.debugLineNum = 483;BA.debugLine="If abPlaceHolder.IsInitialized Then";
Debug.ShouldStop(4);
if (_abplaceholder.IsInitialized()) { 
 BA.debugLineNum = 484;BA.debugLine="AbortDragAndDrop";
Debug.ShouldStop(8);
_abortdraganddrop();
 BA.debugLineNum = 485;BA.debugLine="Dim BtnAction As typAction";
Debug.ShouldStop(16);
_btnaction = new newmoviebay.mncplaymedia.com.clsactionbar._typaction();Debug.locals.put("BtnAction", _btnaction);
 BA.debugLineNum = 486;BA.debugLine="BtnAction = abDraggedBtn.Tag";
Debug.ShouldStop(32);
_btnaction = (newmoviebay.mncplaymedia.com.clsactionbar._typaction)(_abdraggedbtn.getTag());Debug.locals.put("BtnAction", _btnaction);
 BA.debugLineNum = 487;BA.debugLine="If SubExists(BtnAction.Module, abOnAfterDropSub) Then CallSub3(BtnAction.Module, abOnAfterDropSub, Me, abDraggedBtn)";
Debug.ShouldStop(64);
if (__c.SubExists(ba,_btnaction.Module,_abonafterdropsub)) { 
__c.CallSubNew3(ba,_btnaction.Module,_abonafterdropsub,this,(Object)(_abdraggedbtn));};
 };
 }else 
{ BA.debugLineNum = 489;BA.debugLine="Else If Action = 2 Then '= MOVE";
Debug.ShouldStop(256);
if (_action==2) { 
 BA.debugLineNum = 490;BA.debugLine="If abPlaceHolder.IsInitialized Then";
Debug.ShouldStop(512);
if (_abplaceholder.IsInitialized()) { 
 BA.debugLineNum = 492;BA.debugLine="If abViewToBlock <> Null Then";
Debug.ShouldStop(2048);
if (_abviewtoblock!= null) { 
 BA.debugLineNum = 493;BA.debugLine="Dim r As Reflector";
Debug.ShouldStop(4096);
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();Debug.locals.put("r", _r);
 BA.debugLineNum = 494;BA.debugLine="r.Target = abViewToBlock";
Debug.ShouldStop(8192);
_r.Target = _abviewtoblock;Debug.locals.put("r", _r);
 BA.debugLineNum = 495;BA.debugLine="r.RunMethod2(\"requestDisallowInterceptTouchEvent\", True, \"java.lang.boolean\")";
Debug.ShouldStop(16384);
_r.RunMethod2("requestDisallowInterceptTouchEvent",BA.ObjectToString(__c.True),"java.lang.boolean");
 };
 BA.debugLineNum = 499;BA.debugLine="abDraggedBtn.Left = Min(Max(0, abDraggedBtn.Left + X - abStartX), ActionBar.Width - abDraggedBtn.Width)";
Debug.ShouldStop(262144);
_abdraggedbtn.setLeft((int) (__c.Min(__c.Max(0,_abdraggedbtn.getLeft()+_x-_abstartx),_actionbar.getWidth()-_abdraggedbtn.getWidth())));
 BA.debugLineNum = 500;BA.debugLine="Dim Btn As View";
Debug.ShouldStop(524288);
_btn = new anywheresoftware.b4a.objects.ConcreteViewWrapper();Debug.locals.put("Btn", _btn);
 BA.debugLineNum = 501;BA.debugLine="For i = 0 To GetLeftCount - 1";
Debug.ShouldStop(1048576);
{
final int step412 = 1;
final int limit412 = (int) (_getleftcount()-1);
for (_i = (int) (0); (step412 > 0 && _i <= limit412) || (step412 < 0 && _i >= limit412); _i = ((int)(0 + _i + step412))) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 502;BA.debugLine="Btn = abLeftStack.Get(i)";
Debug.ShouldStop(2097152);
_btn.setObject((android.view.View)(_ableftstack.Get(_i)));
 BA.debugLineNum = 503;BA.debugLine="If Btn = abPlaceHolder Then Continue";
Debug.ShouldStop(4194304);
if ((_btn).equals((android.view.View)(_abplaceholder.getObject()))) { 
if (true) continue;};
 BA.debugLineNum = 505;BA.debugLine="If (abDraggedBtn.Left + abDraggedBtn.Width > Btn.Left + Round(Btn.Width / 2) AND _ 				    abDraggedBtn.Left + abDraggedBtn.Width <= Btn.Left + Btn.Width) OR _ 					(abDraggedBtn.Left < Btn.Left + Round(Btn.Width / 2) AND _ 				    abDraggedBtn.Left >= Btn.Left) Then";
Debug.ShouldStop(16777216);
if ((_abdraggedbtn.getLeft()+_abdraggedbtn.getWidth()>_btn.getLeft()+__c.Round(_btn.getWidth()/(double)2) && _abdraggedbtn.getLeft()+_abdraggedbtn.getWidth()<=_btn.getLeft()+_btn.getWidth()) || (_abdraggedbtn.getLeft()<_btn.getLeft()+__c.Round(_btn.getWidth()/(double)2) && _abdraggedbtn.getLeft()>=_btn.getLeft())) { 
 BA.debugLineNum = 509;BA.debugLine="MoveButtonTo(abPlaceHolder, i + 1)";
Debug.ShouldStop(268435456);
_movebuttonto((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_abplaceholder.getObject())),(int) (_i+1));
 BA.debugLineNum = 510;BA.debugLine="Return True";
Debug.ShouldStop(536870912);
if (true) return __c.True;
 };
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 513;BA.debugLine="For i = 0 To GetRightCount - 1";
Debug.ShouldStop(1);
{
final int step420 = 1;
final int limit420 = (int) (_getrightcount()-1);
for (_i = (int) (0); (step420 > 0 && _i <= limit420) || (step420 < 0 && _i >= limit420); _i = ((int)(0 + _i + step420))) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 514;BA.debugLine="Btn = abRightStack.Get(i)";
Debug.ShouldStop(2);
_btn.setObject((android.view.View)(_abrightstack.Get(_i)));
 BA.debugLineNum = 515;BA.debugLine="If Btn = abPlaceHolder Then Continue";
Debug.ShouldStop(4);
if ((_btn).equals((android.view.View)(_abplaceholder.getObject()))) { 
if (true) continue;};
 BA.debugLineNum = 517;BA.debugLine="If (abDraggedBtn.Left + abDraggedBtn.Width > Btn.Left + Round(Btn.Width / 2) AND _ 				    abDraggedBtn.Left + abDraggedBtn.Width <= Btn.Left + Btn.Width) OR _ 					(abDraggedBtn.Left < Btn.Left + Round(Btn.Width / 2) AND _ 				    abDraggedBtn.Left >= Btn.Left) Then";
Debug.ShouldStop(16);
if ((_abdraggedbtn.getLeft()+_abdraggedbtn.getWidth()>_btn.getLeft()+__c.Round(_btn.getWidth()/(double)2) && _abdraggedbtn.getLeft()+_abdraggedbtn.getWidth()<=_btn.getLeft()+_btn.getWidth()) || (_abdraggedbtn.getLeft()<_btn.getLeft()+__c.Round(_btn.getWidth()/(double)2) && _abdraggedbtn.getLeft()>=_btn.getLeft())) { 
 BA.debugLineNum = 521;BA.debugLine="MoveButtonTo(abPlaceHolder, -(i + 1))";
Debug.ShouldStop(256);
_movebuttonto((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_abplaceholder.getObject())),(int) (-(_i+1)));
 BA.debugLineNum = 522;BA.debugLine="Return True";
Debug.ShouldStop(512);
if (true) return __c.True;
 };
 }
}Debug.locals.put("i", _i);
;
 };
 }}};
 BA.debugLineNum = 527;BA.debugLine="Return abPlaceHolder.IsInitialized";
Debug.ShouldStop(16384);
if (true) return _abplaceholder.IsInitialized();
 BA.debugLineNum = 528;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
return false;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _button_click() throws Exception{
try {
		Debug.PushSubsStack("Button_Click (clsactionbar) ","clsactionbar",11,ba,this,658);
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
newmoviebay.mncplaymedia.com.clsactionbar._typaction _action = null;
 BA.debugLineNum = 658;BA.debugLine="Private Sub Button_Click";
Debug.ShouldStop(131072);
 BA.debugLineNum = 660;BA.debugLine="Dim v As View, Action As typAction";
Debug.ShouldStop(524288);
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();Debug.locals.put("v", _v);
_action = new newmoviebay.mncplaymedia.com.clsactionbar._typaction();Debug.locals.put("Action", _action);
 BA.debugLineNum = 661;BA.debugLine="v = Sender";
Debug.ShouldStop(1048576);
_v.setObject((android.view.View)(__c.Sender(ba)));
 BA.debugLineNum = 662;BA.debugLine="Action = v.Tag";
Debug.ShouldStop(2097152);
_action = (newmoviebay.mncplaymedia.com.clsactionbar._typaction)(_v.getTag());Debug.locals.put("Action", _action);
 BA.debugLineNum = 663;BA.debugLine="If SubExists(Action.Module, Action.OnClickSub) Then CallSub3(Action.Module, Action.OnClickSub, Me, v)";
Debug.ShouldStop(4194304);
if (__c.SubExists(ba,_action.Module,_action.OnClickSub)) { 
__c.CallSubNew3(ba,_action.Module,_action.OnClickSub,this,(Object)(_v));};
 BA.debugLineNum = 664;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _button_longclick() throws Exception{
try {
		Debug.PushSubsStack("Button_LongClick (clsactionbar) ","clsactionbar",11,ba,this,666);
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
newmoviebay.mncplaymedia.com.clsactionbar._typaction _action = null;
 BA.debugLineNum = 666;BA.debugLine="Private Sub Button_LongClick";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 668;BA.debugLine="Dim v As View, Action As typAction";
Debug.ShouldStop(134217728);
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();Debug.locals.put("v", _v);
_action = new newmoviebay.mncplaymedia.com.clsactionbar._typaction();Debug.locals.put("Action", _action);
 BA.debugLineNum = 669;BA.debugLine="v = Sender";
Debug.ShouldStop(268435456);
_v.setObject((android.view.View)(__c.Sender(ba)));
 BA.debugLineNum = 670;BA.debugLine="Action = v.Tag";
Debug.ShouldStop(536870912);
_action = (newmoviebay.mncplaymedia.com.clsactionbar._typaction)(_v.getTag());Debug.locals.put("Action", _action);
 BA.debugLineNum = 671;BA.debugLine="If SubExists(Action.Module, Action.OnLongClickSub) Then CallSub3(Action.Module, Action.OnLongClickSub, Me, v)";
Debug.ShouldStop(1073741824);
if (__c.SubExists(ba,_action.Module,_action.OnLongClickSub)) { 
__c.CallSubNew3(ba,_action.Module,_action.OnLongClickSub,this,(Object)(_v));};
 BA.debugLineNum = 672;BA.debugLine="End Sub";
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
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Public Title As Label";
_title = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 5;BA.debugLine="Private ActionBar 			As Panel";
_actionbar = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 6;BA.debugLine="Private abParent 			As Panel";
_abparent = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 7;BA.debugLine="Private abDarkTheme 		As Boolean";
_abdarktheme = false;
 //BA.debugLineNum = 8;BA.debugLine="Private abLeftStack 		As List";
_ableftstack = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 9;BA.debugLine="Private abRightStack 		As List";
_abrightstack = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 10;BA.debugLine="Private abModule 			As Object";
_abmodule = new Object();
 //BA.debugLineNum = 11;BA.debugLine="Private abCanvas 			As Canvas";
_abcanvas = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Private abDividerWidth 		As Byte";
_abdividerwidth = (byte)0;
 //BA.debugLineNum = 13;BA.debugLine="Private abSameWidthForAll 	As Int";
_absamewidthforall = 0;
 //BA.debugLineNum = 14;BA.debugLine="Private abIconAsWideAsText 	As Boolean";
_abiconaswideastext = false;
 //BA.debugLineNum = 17;BA.debugLine="Private abPressedDrawable 		 As Object";
_abpresseddrawable = new Object();
 //BA.debugLineNum = 18;BA.debugLine="Private abSelectedDrawable 		 As Object";
_abselecteddrawable = new Object();
 //BA.debugLineNum = 19;BA.debugLine="Private abDefaultDividerDrawable As Object";
_abdefaultdividerdrawable = new Object();
 //BA.debugLineNum = 20;BA.debugLine="Private abDividerDrawable 		 As Object";
_abdividerdrawable = new Object();
 //BA.debugLineNum = 23;BA.debugLine="Private abStartX 		 As Int";
_abstartx = 0;
 //BA.debugLineNum = 24;BA.debugLine="Private abPlaceHolder 	 As Panel";
_abplaceholder = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private abDraggedBtn 	 As View";
_abdraggedbtn = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private abViewToBlock 	 As Object";
_abviewtoblock = new Object();
 //BA.debugLineNum = 27;BA.debugLine="Private abOnAfterDropSub As String";
_abonafterdropsub = "";
 //BA.debugLineNum = 29;BA.debugLine="Type typAction(Module As Object, OnClickSub As String, OnLongClickSub As String, Tag As Object)";
;
 //BA.debugLineNum = 30;BA.debugLine="End Sub";
return "";
}
public String  _fillparent() throws Exception{
try {
		Debug.PushSubsStack("FillParent (clsactionbar) ","clsactionbar",11,ba,this,690);
 BA.debugLineNum = 690;BA.debugLine="Public Sub FillParent";
Debug.ShouldStop(131072);
 BA.debugLineNum = 691;BA.debugLine="ActionBar.Width = abParent.Width";
Debug.ShouldStop(262144);
_actionbar.setWidth(_abparent.getWidth());
 BA.debugLineNum = 692;BA.debugLine="ActionBar.Height = abParent.Height";
Debug.ShouldStop(524288);
_actionbar.setHeight(_abparent.getHeight());
 BA.debugLineNum = 693;BA.debugLine="Invalidate";
Debug.ShouldStop(1048576);
_invalidate();
 BA.debugLineNum = 694;BA.debugLine="End Sub";
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
public int  _getleftcount() throws Exception{
try {
		Debug.PushSubsStack("GetLeftCount (clsactionbar) ","clsactionbar",11,ba,this,680);
 BA.debugLineNum = 680;BA.debugLine="Public Sub GetLeftCount As Int";
Debug.ShouldStop(128);
 BA.debugLineNum = 681;BA.debugLine="Return abLeftStack.Size";
Debug.ShouldStop(256);
if (true) return _ableftstack.getSize();
 BA.debugLineNum = 682;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return 0;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public int  _getleftposition(anywheresoftware.b4a.objects.ConcreteViewWrapper _btn) throws Exception{
try {
		Debug.PushSubsStack("getLeftPosition (clsactionbar) ","clsactionbar",11,ba,this,408);
Debug.locals.put("Btn", _btn);
 BA.debugLineNum = 408;BA.debugLine="Public Sub getLeftPosition(Btn As View) As Int";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 409;BA.debugLine="Return LeftPosition(Btn) + 1";
Debug.ShouldStop(16777216);
if (true) return (int) (_leftposition((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_btn.getObject())))+1);
 BA.debugLineNum = 410;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
return 0;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public int  _getrightcount() throws Exception{
try {
		Debug.PushSubsStack("GetRightCount (clsactionbar) ","clsactionbar",11,ba,this,675);
 BA.debugLineNum = 675;BA.debugLine="Public Sub GetRightCount As Int";
Debug.ShouldStop(4);
 BA.debugLineNum = 676;BA.debugLine="Return abRightStack.Size";
Debug.ShouldStop(8);
if (true) return _abrightstack.getSize();
 BA.debugLineNum = 677;BA.debugLine="End Sub";
Debug.ShouldStop(16);
return 0;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public int  _getrightposition(anywheresoftware.b4a.objects.ConcreteViewWrapper _btn) throws Exception{
try {
		Debug.PushSubsStack("getRightPosition (clsactionbar) ","clsactionbar",11,ba,this,421);
Debug.locals.put("Btn", _btn);
 BA.debugLineNum = 421;BA.debugLine="Public Sub getRightPosition(Btn As View) As Int";
Debug.ShouldStop(16);
 BA.debugLineNum = 422;BA.debugLine="Return -(RightPosition(Btn) + 1)";
Debug.ShouldStop(32);
if (true) return (int) (-(_rightposition((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_btn.getObject())))+1));
 BA.debugLineNum = 423;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return 0;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public int  _gettotalcount() throws Exception{
try {
		Debug.PushSubsStack("GetTotalCount (clsactionbar) ","clsactionbar",11,ba,this,685);
 BA.debugLineNum = 685;BA.debugLine="Public Sub GetTotalCount As Int";
Debug.ShouldStop(4096);
 BA.debugLineNum = 686;BA.debugLine="Return abLeftStack.Size + abRightStack.Size";
Debug.ShouldStop(8192);
if (true) return (int) (_ableftstack.getSize()+_abrightstack.getSize());
 BA.debugLineNum = 687;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return 0;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _initialize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.PanelWrapper _parent,boolean _topmost,boolean _darktheme,int _height,Object _module) throws Exception{
innerInitialize(_ba);
try {
		Debug.PushSubsStack("Initialize (clsactionbar) ","clsactionbar",11,ba,this,33);
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
int _w = 0;
int _h = 0;
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
Debug.locals.put("ba", _ba);
Debug.locals.put("Parent", _parent);
Debug.locals.put("TopMost", _topmost);
Debug.locals.put("DarkTheme", _darktheme);
Debug.locals.put("Height", _height);
Debug.locals.put("Module", _module);
 BA.debugLineNum = 33;BA.debugLine="Public Sub Initialize (Parent As Panel, TopMost As Boolean, DarkTheme As Boolean, Height As Int, Module As Object)";
Debug.ShouldStop(1);
 BA.debugLineNum = 34;BA.debugLine="Dim pnl As Panel";
Debug.ShouldStop(2);
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();Debug.locals.put("pnl", _pnl);
 BA.debugLineNum = 35;BA.debugLine="pnl = Parent";
Debug.ShouldStop(4);
_pnl = _parent;Debug.locals.put("pnl", _pnl);
 BA.debugLineNum = 36;BA.debugLine="If Not(pnl.IsInitialized) Then";
Debug.ShouldStop(8);
if (__c.Not(_pnl.IsInitialized())) { 
 BA.debugLineNum = 37;BA.debugLine="Log(\"'Parent' must be the current activity or an initialized panel\")";
Debug.ShouldStop(16);
__c.Log("'Parent' must be the current activity or an initialized panel");
 BA.debugLineNum = 38;BA.debugLine="Return";
Debug.ShouldStop(32);
if (true) return "";
 };
 BA.debugLineNum = 40;BA.debugLine="abParent = Parent";
Debug.ShouldStop(128);
_abparent = _parent;
 BA.debugLineNum = 42;BA.debugLine="ActionBar.Initialize(\"\")";
Debug.ShouldStop(512);
_actionbar.Initialize(ba,"");
 BA.debugLineNum = 43;BA.debugLine="Dim w, h As Int";
Debug.ShouldStop(1024);
_w = 0;Debug.locals.put("w", _w);
_h = 0;Debug.locals.put("h", _h);
 BA.debugLineNum = 44;BA.debugLine="w = pnl.Width";
Debug.ShouldStop(2048);
_w = _pnl.getWidth();Debug.locals.put("w", _w);
 BA.debugLineNum = 45;BA.debugLine="h = pnl.Height";
Debug.ShouldStop(4096);
_h = _pnl.getHeight();Debug.locals.put("h", _h);
 BA.debugLineNum = 46;BA.debugLine="Dim r As Reflector";
Debug.ShouldStop(8192);
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();Debug.locals.put("r", _r);
 BA.debugLineNum = 47;BA.debugLine="r.Target = pnl";
Debug.ShouldStop(16384);
_r.Target = (Object)(_pnl.getObject());Debug.locals.put("r", _r);
 BA.debugLineNum = 48;BA.debugLine="If w = -1 Then w = r.RunMethod(\"getWidth\")";
Debug.ShouldStop(32768);
if (_w==-1) { 
_w = (int)(BA.ObjectToNumber(_r.RunMethod("getWidth")));Debug.locals.put("w", _w);};
 BA.debugLineNum = 49;BA.debugLine="If h = -1 Then h =  r.RunMethod(\"getHeight\")";
Debug.ShouldStop(65536);
if (_h==-1) { 
_h = (int)(BA.ObjectToNumber(_r.RunMethod("getHeight")));Debug.locals.put("h", _h);};
 BA.debugLineNum = 50;BA.debugLine="If TopMost Then";
Debug.ShouldStop(131072);
if (_topmost) { 
 BA.debugLineNum = 51;BA.debugLine="pnl.AddView(ActionBar, 0, 0, w, Height)";
Debug.ShouldStop(262144);
_pnl.AddView((android.view.View)(_actionbar.getObject()),(int) (0),(int) (0),_w,_height);
 }else {
 BA.debugLineNum = 53;BA.debugLine="pnl.AddView(ActionBar, 0, h - Height, w, Height)";
Debug.ShouldStop(1048576);
_pnl.AddView((android.view.View)(_actionbar.getObject()),(int) (0),(int) (_h-_height),_w,_height);
 };
 BA.debugLineNum = 56;BA.debugLine="Title.Initialize(\"\")";
Debug.ShouldStop(8388608);
_title.Initialize(ba,"");
 BA.debugLineNum = 57;BA.debugLine="If DarkTheme Then";
Debug.ShouldStop(16777216);
if (_darktheme) { 
 BA.debugLineNum = 58;BA.debugLine="ActionBar.Background = LoadDrawable(\"dark_header\")";
Debug.ShouldStop(33554432);
_actionbar.setBackground((android.graphics.drawable.Drawable)(_loaddrawable("dark_header")));
 BA.debugLineNum = 59;BA.debugLine="Title.TextColor = Colors.White";
Debug.ShouldStop(67108864);
_title.setTextColor(__c.Colors.White);
 BA.debugLineNum = 60;BA.debugLine="abDefaultDividerDrawable = LoadDrawable(\"divider_vertical_dark\")";
Debug.ShouldStop(134217728);
_abdefaultdividerdrawable = _loaddrawable("divider_vertical_dark");
 }else {
 BA.debugLineNum = 62;BA.debugLine="ActionBar.Background = LoadDrawable(\"light_header\")";
Debug.ShouldStop(536870912);
_actionbar.setBackground((android.graphics.drawable.Drawable)(_loaddrawable("light_header")));
 BA.debugLineNum = 63;BA.debugLine="Title.TextColor = Colors.Black";
Debug.ShouldStop(1073741824);
_title.setTextColor(__c.Colors.Black);
 BA.debugLineNum = 64;BA.debugLine="abDefaultDividerDrawable = LoadDrawable(\"divider_vertical_bright\")";
Debug.ShouldStop(-2147483648);
_abdefaultdividerdrawable = _loaddrawable("divider_vertical_bright");
 };
 BA.debugLineNum = 66;BA.debugLine="abDarkTheme = DarkTheme";
Debug.ShouldStop(2);
_abdarktheme = _darktheme;
 BA.debugLineNum = 67;BA.debugLine="Title.Gravity = Gravity.LEFT + Gravity.CENTER_VERTICAL";
Debug.ShouldStop(4);
_title.setGravity((int) (__c.Gravity.LEFT+__c.Gravity.CENTER_VERTICAL));
 BA.debugLineNum = 68;BA.debugLine="Title.Typeface = Typeface.DEFAULT_BOLD";
Debug.ShouldStop(8);
_title.setTypeface(__c.Typeface.DEFAULT_BOLD);
 BA.debugLineNum = 69;BA.debugLine="ActionBar.AddView(Title, 0, 0, ActionBar.Width, Height)";
Debug.ShouldStop(16);
_actionbar.AddView((android.view.View)(_title.getObject()),(int) (0),(int) (0),_actionbar.getWidth(),_height);
 BA.debugLineNum = 71;BA.debugLine="abLeftStack.Initialize";
Debug.ShouldStop(64);
_ableftstack.Initialize();
 BA.debugLineNum = 72;BA.debugLine="abRightStack.Initialize";
Debug.ShouldStop(128);
_abrightstack.Initialize();
 BA.debugLineNum = 73;BA.debugLine="abModule = Module";
Debug.ShouldStop(256);
_abmodule = _module;
 BA.debugLineNum = 74;BA.debugLine="abDividerWidth = 0";
Debug.ShouldStop(512);
_abdividerwidth = (byte) (0);
 BA.debugLineNum = 75;BA.debugLine="abSameWidthForAll = 0";
Debug.ShouldStop(1024);
_absamewidthforall = (int) (0);
 BA.debugLineNum = 76;BA.debugLine="abIconAsWideAsText = False";
Debug.ShouldStop(2048);
_abiconaswideastext = __c.False;
 BA.debugLineNum = 77;BA.debugLine="abPressedDrawable = LoadDrawable(\"list_selector_background_pressed\")";
Debug.ShouldStop(4096);
_abpresseddrawable = _loaddrawable("list_selector_background_pressed");
 BA.debugLineNum = 78;BA.debugLine="abSelectedDrawable = LoadDrawable(\"list_selector_background_focus\")";
Debug.ShouldStop(8192);
_abselecteddrawable = _loaddrawable("list_selector_background_focus");
 BA.debugLineNum = 79;BA.debugLine="abDividerDrawable = Null";
Debug.ShouldStop(16384);
_abdividerdrawable = __c.Null;
 BA.debugLineNum = 80;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _invalidate() throws Exception{
try {
		Debug.PushSubsStack("Invalidate (clsactionbar) ","clsactionbar",11,ba,this,381);
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
int _p = 0;
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
anywheresoftware.b4a.objects.ImageViewWrapper _iv = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
int _i = 0;
 BA.debugLineNum = 381;BA.debugLine="Public Sub Invalidate";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 382;BA.debugLine="Dim pnl As Panel";
Debug.ShouldStop(536870912);
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();Debug.locals.put("pnl", _pnl);
 BA.debugLineNum = 383;BA.debugLine="For P = 0 To ActionBar.NumberOfViews - 1";
Debug.ShouldStop(1073741824);
{
final int step317 = 1;
final int limit317 = (int) (_actionbar.getNumberOfViews()-1);
for (_p = (int) (0); (step317 > 0 && _p <= limit317) || (step317 < 0 && _p >= limit317); _p = ((int)(0 + _p + step317))) {
Debug.locals.put("p", _p);
 BA.debugLineNum = 384;BA.debugLine="If ActionBar.GetView(P) Is Panel Then";
Debug.ShouldStop(-2147483648);
if (_actionbar.GetView(_p).getObjectOrNull() instanceof android.view.ViewGroup) { 
 BA.debugLineNum = 385;BA.debugLine="pnl = ActionBar.GetView(P)";
Debug.ShouldStop(1);
_pnl.setObject((android.view.ViewGroup)(_actionbar.GetView(_p).getObject()));
 BA.debugLineNum = 386;BA.debugLine="Dim v As View, IV As ImageView, lbl As Label";
Debug.ShouldStop(2);
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();Debug.locals.put("v", _v);
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();Debug.locals.put("IV", _iv);
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 387;BA.debugLine="For i = 0 To pnl.NumberOfViews - 1";
Debug.ShouldStop(4);
{
final int step321 = 1;
final int limit321 = (int) (_pnl.getNumberOfViews()-1);
for (_i = (int) (0); (step321 > 0 && _i <= limit321) || (step321 < 0 && _i >= limit321); _i = ((int)(0 + _i + step321))) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 388;BA.debugLine="v = pnl.GetView(i)";
Debug.ShouldStop(8);
_v = _pnl.GetView(_i);Debug.locals.put("v", _v);
 BA.debugLineNum = 389;BA.debugLine="If v Is ImageView Then IV = v";
Debug.ShouldStop(16);
if (_v.getObjectOrNull() instanceof android.widget.ImageView) { 
_iv.setObject((android.widget.ImageView)(_v.getObject()));};
 BA.debugLineNum = 390;BA.debugLine="If v Is Label Then lbl = v";
Debug.ShouldStop(32);
if (_v.getObjectOrNull() instanceof android.widget.TextView) { 
_lbl.setObject((android.widget.TextView)(_v.getObject()));};
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 392;BA.debugLine="pnl.Width = ResizeButton(IV, lbl, Abs(lbl.Tag), lbl.Tag < 0, abSameWidthForAll)";
Debug.ShouldStop(128);
_pnl.setWidth(_resizebutton(_iv,_lbl,(byte) (__c.Abs((double)(BA.ObjectToNumber(_lbl.getTag())))),(double)(BA.ObjectToNumber(_lbl.getTag()))<0,_absamewidthforall));
 BA.debugLineNum = 393;BA.debugLine="UpdateBackground(pnl, lbl.Tag < 0)";
Debug.ShouldStop(256);
_updatebackground(_pnl,(double)(BA.ObjectToNumber(_lbl.getTag()))<0);
 };
 }
}Debug.locals.put("p", _p);
;
 BA.debugLineNum = 396;BA.debugLine="ReorderViews";
Debug.ShouldStop(2048);
_reorderviews();
 BA.debugLineNum = 397;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public int  _leftposition(anywheresoftware.b4a.objects.PanelWrapper _btn) throws Exception{
try {
		Debug.PushSubsStack("LeftPosition (clsactionbar) ","clsactionbar",11,ba,this,399);
int _i = 0;
Debug.locals.put("Btn", _btn);
 BA.debugLineNum = 399;BA.debugLine="Private Sub LeftPosition(Btn As Panel) As Int";
Debug.ShouldStop(16384);
 BA.debugLineNum = 400;BA.debugLine="For i = 0 To abLeftStack.Size - 1";
Debug.ShouldStop(32768);
{
final int step333 = 1;
final int limit333 = (int) (_ableftstack.getSize()-1);
for (_i = (int) (0); (step333 > 0 && _i <= limit333) || (step333 < 0 && _i >= limit333); _i = ((int)(0 + _i + step333))) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 401;BA.debugLine="If abLeftStack.Get(i) = Btn Then Return i";
Debug.ShouldStop(65536);
if ((_ableftstack.Get(_i)).equals((Object)(_btn.getObject()))) { 
if (true) return _i;};
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 403;BA.debugLine="Return -1";
Debug.ShouldStop(262144);
if (true) return (int) (-1);
 BA.debugLineNum = 404;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return 0;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public Object  _loaddrawable(String _name) throws Exception{
try {
		Debug.PushSubsStack("LoadDrawable (clsactionbar) ","clsactionbar",11,ba,this,83);
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
int _id_drawable = 0;
Debug.locals.put("Name", _name);
 BA.debugLineNum = 83;BA.debugLine="Public Sub LoadDrawable(Name As String) As Object";
Debug.ShouldStop(262144);
 BA.debugLineNum = 84;BA.debugLine="Dim r As Reflector";
Debug.ShouldStop(524288);
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();Debug.locals.put("r", _r);
 BA.debugLineNum = 85;BA.debugLine="r.Target = r.GetContext";
Debug.ShouldStop(1048576);
_r.Target = (Object)(_r.GetContext(ba));Debug.locals.put("r", _r);
 BA.debugLineNum = 86;BA.debugLine="r.Target = r.RunMethod(\"getResources\")";
Debug.ShouldStop(2097152);
_r.Target = _r.RunMethod("getResources");Debug.locals.put("r", _r);
 BA.debugLineNum = 87;BA.debugLine="r.Target = r.RunMethod(\"getSystem\")";
Debug.ShouldStop(4194304);
_r.Target = _r.RunMethod("getSystem");Debug.locals.put("r", _r);
 BA.debugLineNum = 88;BA.debugLine="Dim ID_Drawable As Int";
Debug.ShouldStop(8388608);
_id_drawable = 0;Debug.locals.put("ID_Drawable", _id_drawable);
 BA.debugLineNum = 89;BA.debugLine="ID_Drawable = r.RunMethod4(\"getIdentifier\", Array As Object(Name, \"drawable\", \"android\"), _ 	                                            Array As String(\"java.lang.String\", \"java.lang.String\", \"java.lang.String\"))";
Debug.ShouldStop(16777216);
_id_drawable = (int)(BA.ObjectToNumber(_r.RunMethod4("getIdentifier",new Object[]{(Object)(_name),(Object)("drawable"),(Object)("android")},new String[]{"java.lang.String","java.lang.String","java.lang.String"})));Debug.locals.put("ID_Drawable", _id_drawable);
 BA.debugLineNum = 91;BA.debugLine="r.Target = r.GetContext";
Debug.ShouldStop(67108864);
_r.Target = (Object)(_r.GetContext(ba));Debug.locals.put("r", _r);
 BA.debugLineNum = 92;BA.debugLine="r.Target = r.RunMethod(\"getResources\")";
Debug.ShouldStop(134217728);
_r.Target = _r.RunMethod("getResources");Debug.locals.put("r", _r);
 BA.debugLineNum = 93;BA.debugLine="Return r.RunMethod2(\"getDrawable\", ID_Drawable, \"java.lang.int\")";
Debug.ShouldStop(268435456);
if (true) return _r.RunMethod2("getDrawable",BA.NumberToString(_id_drawable),"java.lang.int");
 BA.debugLineNum = 94;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return null;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _movebuttonto(anywheresoftware.b4a.objects.ConcreteViewWrapper _btn,int _newposition) throws Exception{
try {
		Debug.PushSubsStack("MoveButtonTo (clsactionbar) ","clsactionbar",11,ba,this,549);
boolean _stackchange = false;
int _p = 0;
Debug.locals.put("Btn", _btn);
Debug.locals.put("NewPosition", _newposition);
 BA.debugLineNum = 549;BA.debugLine="Public Sub MoveButtonTo(Btn As View, NewPosition As Int)";
Debug.ShouldStop(16);
 BA.debugLineNum = 550;BA.debugLine="Dim StackChange As Boolean";
Debug.ShouldStop(32);
_stackchange = false;Debug.locals.put("StackChange", _stackchange);
 BA.debugLineNum = 551;BA.debugLine="Dim P As Int";
Debug.ShouldStop(64);
_p = 0;Debug.locals.put("P", _p);
 BA.debugLineNum = 552;BA.debugLine="P = LeftPosition(Btn)";
Debug.ShouldStop(128);
_p = _leftposition((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_btn.getObject())));Debug.locals.put("P", _p);
 BA.debugLineNum = 553;BA.debugLine="If P >= 0 Then";
Debug.ShouldStop(256);
if (_p>=0) { 
 BA.debugLineNum = 554;BA.debugLine="abLeftStack.RemoveAt(P)";
Debug.ShouldStop(512);
_ableftstack.RemoveAt(_p);
 BA.debugLineNum = 555;BA.debugLine="StackChange = (NewPosition < 0)";
Debug.ShouldStop(1024);
_stackchange = (_newposition<0);Debug.locals.put("StackChange", _stackchange);
 }else {
 BA.debugLineNum = 557;BA.debugLine="P = RightPosition(Btn)";
Debug.ShouldStop(4096);
_p = _rightposition((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_btn.getObject())));Debug.locals.put("P", _p);
 BA.debugLineNum = 558;BA.debugLine="If P >= 0 Then";
Debug.ShouldStop(8192);
if (_p>=0) { 
 BA.debugLineNum = 559;BA.debugLine="abRightStack.RemoveAt(P)";
Debug.ShouldStop(16384);
_abrightstack.RemoveAt(_p);
 BA.debugLineNum = 560;BA.debugLine="StackChange = Not(NewPosition < 0)";
Debug.ShouldStop(32768);
_stackchange = __c.Not(_newposition<0);Debug.locals.put("StackChange", _stackchange);
 };
 };
 BA.debugLineNum = 563;BA.debugLine="If NewPosition < 0 Then";
Debug.ShouldStop(262144);
if (_newposition<0) { 
 BA.debugLineNum = 564;BA.debugLine="abRightStack.InsertAt(Min(Abs(NewPosition) - 1, GetRightCount), Btn)";
Debug.ShouldStop(524288);
_abrightstack.InsertAt((int) (__c.Min(__c.Abs(_newposition)-1,_getrightcount())),(Object)(_btn.getObject()));
 }else {
 BA.debugLineNum = 566;BA.debugLine="abLeftStack.InsertAt(Min(NewPosition - 1, GetLeftCount), Btn)";
Debug.ShouldStop(2097152);
_ableftstack.InsertAt((int) (__c.Min(_newposition-1,_getleftcount())),(Object)(_btn.getObject()));
 };
 BA.debugLineNum = 568;BA.debugLine="If StackChange Then";
Debug.ShouldStop(8388608);
if (_stackchange) { 
 BA.debugLineNum = 569;BA.debugLine="UpdateSignOfTag(Btn, NewPosition < 0)";
Debug.ShouldStop(16777216);
_updatesignoftag((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_btn.getObject())),_newposition<0);
 BA.debugLineNum = 570;BA.debugLine="If abDividerWidth > 0 Then UpdateBackground(Btn, NewPosition < 0)";
Debug.ShouldStop(33554432);
if (_abdividerwidth>0) { 
_updatebackground((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_btn.getObject())),_newposition<0);};
 };
 BA.debugLineNum = 572;BA.debugLine="ReorderViews";
Debug.ShouldStop(134217728);
_reorderviews();
 BA.debugLineNum = 573;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _removeallbuttons() throws Exception{
try {
		Debug.PushSubsStack("RemoveAllButtons (clsactionbar) ","clsactionbar",11,ba,this,594);
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
int _i = 0;
 BA.debugLineNum = 594;BA.debugLine="Public Sub RemoveAllButtons";
Debug.ShouldStop(131072);
 BA.debugLineNum = 595;BA.debugLine="abLeftStack.Clear";
Debug.ShouldStop(262144);
_ableftstack.Clear();
 BA.debugLineNum = 596;BA.debugLine="abRightStack.Clear";
Debug.ShouldStop(524288);
_abrightstack.Clear();
 BA.debugLineNum = 597;BA.debugLine="Dim v As View";
Debug.ShouldStop(1048576);
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();Debug.locals.put("v", _v);
 BA.debugLineNum = 598;BA.debugLine="For i = ActionBar.NumberOfViews - 1 To 0 Step -1";
Debug.ShouldStop(2097152);
{
final int step491 = (int) (-1);
final int limit491 = (int) (0);
for (_i = (int) (_actionbar.getNumberOfViews()-1); (step491 > 0 && _i <= limit491) || (step491 < 0 && _i >= limit491); _i = ((int)(0 + _i + step491))) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 599;BA.debugLine="v = ActionBar.GetView(i)";
Debug.ShouldStop(4194304);
_v = _actionbar.GetView(_i);Debug.locals.put("v", _v);
 BA.debugLineNum = 600;BA.debugLine="If Not(v Is Label) Then v.RemoveView";
Debug.ShouldStop(8388608);
if (__c.Not(_v.getObjectOrNull() instanceof android.widget.TextView)) { 
_v.RemoveView();};
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 602;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _removebutton(anywheresoftware.b4a.objects.ConcreteViewWrapper _btn) throws Exception{
try {
		Debug.PushSubsStack("RemoveButton (clsactionbar) ","clsactionbar",11,ba,this,576);
int _p = 0;
Debug.locals.put("Btn", _btn);
 BA.debugLineNum = 576;BA.debugLine="Public Sub RemoveButton(Btn As View)";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 577;BA.debugLine="Dim P As Int";
Debug.ShouldStop(1);
_p = 0;Debug.locals.put("P", _p);
 BA.debugLineNum = 578;BA.debugLine="P = LeftPosition(Btn)";
Debug.ShouldStop(2);
_p = _leftposition((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_btn.getObject())));Debug.locals.put("P", _p);
 BA.debugLineNum = 579;BA.debugLine="If P >= 0 Then";
Debug.ShouldStop(4);
if (_p>=0) { 
 BA.debugLineNum = 580;BA.debugLine="abLeftStack.RemoveAt(P)";
Debug.ShouldStop(8);
_ableftstack.RemoveAt(_p);
 }else {
 BA.debugLineNum = 582;BA.debugLine="P = RightPosition(Btn)";
Debug.ShouldStop(32);
_p = _rightposition((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_btn.getObject())));Debug.locals.put("P", _p);
 BA.debugLineNum = 583;BA.debugLine="If P >= 0 Then abRightStack.RemoveAt(P)";
Debug.ShouldStop(64);
if (_p>=0) { 
_abrightstack.RemoveAt(_p);};
 };
 BA.debugLineNum = 585;BA.debugLine="Btn.RemoveView";
Debug.ShouldStop(256);
_btn.RemoveView();
 BA.debugLineNum = 586;BA.debugLine="If abSameWidthForAll > 0 Then";
Debug.ShouldStop(512);
if (_absamewidthforall>0) { 
 BA.debugLineNum = 587;BA.debugLine="SameWidthForAll(True)";
Debug.ShouldStop(1024);
_samewidthforall(__c.True);
 }else {
 BA.debugLineNum = 589;BA.debugLine="ReorderViews";
Debug.ShouldStop(4096);
_reorderviews();
 };
 BA.debugLineNum = 591;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _reorderviews() throws Exception{
try {
		Debug.PushSubsStack("ReorderViews (clsactionbar) ","clsactionbar",11,ba,this,96);
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
int _left = 0;
int _i = 0;
 BA.debugLineNum = 96;BA.debugLine="Private Sub ReorderViews";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 98;BA.debugLine="Dim v As View, Left As Int";
Debug.ShouldStop(2);
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();Debug.locals.put("v", _v);
_left = 0;Debug.locals.put("Left", _left);
 BA.debugLineNum = 99;BA.debugLine="For i = 0 To abLeftStack.Size - 1";
Debug.ShouldStop(4);
{
final int step81 = 1;
final int limit81 = (int) (_ableftstack.getSize()-1);
for (_i = (int) (0); (step81 > 0 && _i <= limit81) || (step81 < 0 && _i >= limit81); _i = ((int)(0 + _i + step81))) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 100;BA.debugLine="v = abLeftStack.Get(i)";
Debug.ShouldStop(8);
_v.setObject((android.view.View)(_ableftstack.Get(_i)));
 BA.debugLineNum = 101;BA.debugLine="v.Left = Left";
Debug.ShouldStop(16);
_v.setLeft(_left);
 BA.debugLineNum = 102;BA.debugLine="Left = Left + v.Width";
Debug.ShouldStop(32);
_left = (int) (_left+_v.getWidth());Debug.locals.put("Left", _left);
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 106;BA.debugLine="Left = ActionBar.Width";
Debug.ShouldStop(512);
_left = _actionbar.getWidth();Debug.locals.put("Left", _left);
 BA.debugLineNum = 107;BA.debugLine="For i = 0 To abRightStack.Size - 1";
Debug.ShouldStop(1024);
{
final int step87 = 1;
final int limit87 = (int) (_abrightstack.getSize()-1);
for (_i = (int) (0); (step87 > 0 && _i <= limit87) || (step87 < 0 && _i >= limit87); _i = ((int)(0 + _i + step87))) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 108;BA.debugLine="v = abRightStack.Get(i)";
Debug.ShouldStop(2048);
_v.setObject((android.view.View)(_abrightstack.Get(_i)));
 BA.debugLineNum = 109;BA.debugLine="v.Left = Left - v.Width";
Debug.ShouldStop(4096);
_v.setLeft((int) (_left-_v.getWidth()));
 BA.debugLineNum = 110;BA.debugLine="Left = Left - v.Width";
Debug.ShouldStop(8192);
_left = (int) (_left-_v.getWidth());Debug.locals.put("Left", _left);
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 112;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _replacedividerdrawable(Object _drawable) throws Exception{
try {
		Debug.PushSubsStack("ReplaceDividerDrawable (clsactionbar) ","clsactionbar",11,ba,this,343);
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bd = null;
Debug.locals.put("Drawable", _drawable);
 BA.debugLineNum = 343;BA.debugLine="Public Sub ReplaceDividerDrawable(Drawable As Object)";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 344;BA.debugLine="If Drawable <> Null Then";
Debug.ShouldStop(8388608);
if (_drawable!= null) { 
 BA.debugLineNum = 345;BA.debugLine="If Drawable Is Bitmap Then";
Debug.ShouldStop(16777216);
if (_drawable instanceof android.graphics.Bitmap) { 
 BA.debugLineNum = 346;BA.debugLine="Dim bd As BitmapDrawable";
Debug.ShouldStop(33554432);
_bd = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();Debug.locals.put("bd", _bd);
 BA.debugLineNum = 347;BA.debugLine="bd.Initialize(Drawable)";
Debug.ShouldStop(67108864);
_bd.Initialize((android.graphics.Bitmap)(_drawable));
 BA.debugLineNum = 348;BA.debugLine="Drawable = bd";
Debug.ShouldStop(134217728);
_drawable = (Object)(_bd.getObject());Debug.locals.put("Drawable", _drawable);
 };
 };
 BA.debugLineNum = 351;BA.debugLine="abDividerDrawable = Drawable";
Debug.ShouldStop(1073741824);
_abdividerdrawable = _drawable;
 BA.debugLineNum = 352;BA.debugLine="Invalidate";
Debug.ShouldStop(-2147483648);
_invalidate();
 BA.debugLineNum = 353;BA.debugLine="End Sub";
Debug.ShouldStop(1);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _replaceicon(anywheresoftware.b4a.objects.ConcreteViewWrapper _btn,Object _icon) throws Exception{
try {
		Debug.PushSubsStack("ReplaceIcon (clsactionbar) ","clsactionbar",11,ba,this,298);
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
anywheresoftware.b4a.objects.ImageViewWrapper _iv = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
int _i = 0;
Debug.locals.put("Btn", _btn);
Debug.locals.put("Icon", _icon);
 BA.debugLineNum = 298;BA.debugLine="Public Sub ReplaceIcon(Btn As View, Icon As Object)";
Debug.ShouldStop(512);
 BA.debugLineNum = 299;BA.debugLine="Dim pnl As Panel, v As View, IV As ImageView, lbl As Label";
Debug.ShouldStop(1024);
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();Debug.locals.put("pnl", _pnl);
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();Debug.locals.put("v", _v);
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();Debug.locals.put("IV", _iv);
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 300;BA.debugLine="pnl = Btn";
Debug.ShouldStop(2048);
_pnl.setObject((android.view.ViewGroup)(_btn.getObject()));
 BA.debugLineNum = 301;BA.debugLine="For i = 0 To pnl.NumberOfViews - 1";
Debug.ShouldStop(4096);
{
final int step249 = 1;
final int limit249 = (int) (_pnl.getNumberOfViews()-1);
for (_i = (int) (0); (step249 > 0 && _i <= limit249) || (step249 < 0 && _i >= limit249); _i = ((int)(0 + _i + step249))) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 302;BA.debugLine="v = pnl.GetView(i)";
Debug.ShouldStop(8192);
_v = _pnl.GetView(_i);Debug.locals.put("v", _v);
 BA.debugLineNum = 303;BA.debugLine="If v Is ImageView Then IV = v";
Debug.ShouldStop(16384);
if (_v.getObjectOrNull() instanceof android.widget.ImageView) { 
_iv.setObject((android.widget.ImageView)(_v.getObject()));};
 BA.debugLineNum = 304;BA.debugLine="If v Is Label Then lbl = v";
Debug.ShouldStop(32768);
if (_v.getObjectOrNull() instanceof android.widget.TextView) { 
_lbl.setObject((android.widget.TextView)(_v.getObject()));};
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 306;BA.debugLine="If IV.IsInitialized Then";
Debug.ShouldStop(131072);
if (_iv.IsInitialized()) { 
 BA.debugLineNum = 307;BA.debugLine="If Icon = Null Then";
Debug.ShouldStop(262144);
if (_icon== null) { 
 BA.debugLineNum = 308;BA.debugLine="IV.RemoveView";
Debug.ShouldStop(524288);
_iv.RemoveView();
 BA.debugLineNum = 309;BA.debugLine="IV = Null";
Debug.ShouldStop(1048576);
_iv.setObject((android.widget.ImageView)(__c.Null));
 };
 }else 
{ BA.debugLineNum = 311;BA.debugLine="Else If Icon <> Null Then";
Debug.ShouldStop(4194304);
if (_icon!= null) { 
 BA.debugLineNum = 312;BA.debugLine="IV.Initialize(\"\")";
Debug.ShouldStop(8388608);
_iv.Initialize(ba,"");
 BA.debugLineNum = 313;BA.debugLine="pnl.AddView(IV, 0, 0, 0, 0)";
Debug.ShouldStop(16777216);
_pnl.AddView((android.view.View)(_iv.getObject()),(int) (0),(int) (0),(int) (0),(int) (0));
 }};
 BA.debugLineNum = 315;BA.debugLine="If Icon <> Null Then";
Debug.ShouldStop(67108864);
if (_icon!= null) { 
 BA.debugLineNum = 316;BA.debugLine="If Icon Is Bitmap Then";
Debug.ShouldStop(134217728);
if (_icon instanceof android.graphics.Bitmap) { 
 BA.debugLineNum = 317;BA.debugLine="IV.Bitmap = Icon";
Debug.ShouldStop(268435456);
_iv.setBitmap((android.graphics.Bitmap)(_icon));
 BA.debugLineNum = 318;BA.debugLine="IV.Gravity = Gravity.FILL";
Debug.ShouldStop(536870912);
_iv.setGravity(__c.Gravity.FILL);
 }else {
 BA.debugLineNum = 320;BA.debugLine="IV.Background = Icon";
Debug.ShouldStop(-2147483648);
_iv.setBackground((android.graphics.drawable.Drawable)(_icon));
 };
 };
 BA.debugLineNum = 323;BA.debugLine="pnl.Width = ResizeButton(IV, lbl, Abs(lbl.Tag), lbl.Tag < 0, abSameWidthForAll)";
Debug.ShouldStop(4);
_pnl.setWidth(_resizebutton(_iv,_lbl,(byte) (__c.Abs((double)(BA.ObjectToNumber(_lbl.getTag())))),(double)(BA.ObjectToNumber(_lbl.getTag()))<0,_absamewidthforall));
 BA.debugLineNum = 324;BA.debugLine="UpdateBackground(pnl, lbl.Tag < 0)";
Debug.ShouldStop(8);
_updatebackground(_pnl,(double)(BA.ObjectToNumber(_lbl.getTag()))<0);
 BA.debugLineNum = 325;BA.debugLine="ReorderViews";
Debug.ShouldStop(16);
_reorderviews();
 BA.debugLineNum = 326;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _replacepresseddrawable(Object _drawable) throws Exception{
try {
		Debug.PushSubsStack("ReplacePressedDrawable (clsactionbar) ","clsactionbar",11,ba,this,330);
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bd = null;
Debug.locals.put("Drawable", _drawable);
 BA.debugLineNum = 330;BA.debugLine="Public Sub ReplacePressedDrawable(Drawable As Object)";
Debug.ShouldStop(512);
 BA.debugLineNum = 331;BA.debugLine="If Drawable <> Null Then";
Debug.ShouldStop(1024);
if (_drawable!= null) { 
 BA.debugLineNum = 332;BA.debugLine="If Drawable Is Bitmap Then";
Debug.ShouldStop(2048);
if (_drawable instanceof android.graphics.Bitmap) { 
 BA.debugLineNum = 333;BA.debugLine="Dim bd As BitmapDrawable";
Debug.ShouldStop(4096);
_bd = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();Debug.locals.put("bd", _bd);
 BA.debugLineNum = 334;BA.debugLine="bd.Initialize(Drawable)";
Debug.ShouldStop(8192);
_bd.Initialize((android.graphics.Bitmap)(_drawable));
 BA.debugLineNum = 335;BA.debugLine="Drawable = bd";
Debug.ShouldStop(16384);
_drawable = (Object)(_bd.getObject());Debug.locals.put("Drawable", _drawable);
 };
 };
 BA.debugLineNum = 338;BA.debugLine="abPressedDrawable = Drawable";
Debug.ShouldStop(131072);
_abpresseddrawable = _drawable;
 BA.debugLineNum = 339;BA.debugLine="End Sub";
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
public int  _resizebutton(anywheresoftware.b4a.objects.ImageViewWrapper _ivicon,anywheresoftware.b4a.objects.LabelWrapper _lbltext,byte _textposition,boolean _rightstack,int _forcewidth) throws Exception{
try {
		Debug.PushSubsStack("ResizeButton (clsactionbar) ","clsactionbar",11,ba,this,114);
int _left = 0;
int _iconsize = 0;
int _textwidth = 0;
int _textheight = 0;
int _iconwidth = 0;
Debug.locals.put("ivIcon", _ivicon);
Debug.locals.put("lblText", _lbltext);
Debug.locals.put("TextPosition", _textposition);
Debug.locals.put("RightStack", _rightstack);
Debug.locals.put("ForceWidth", _forcewidth);
 BA.debugLineNum = 114;BA.debugLine="Private Sub ResizeButton(ivIcon As ImageView, lblText As Label, TextPosition As Byte, RightStack As Boolean, ForceWidth As Int) As Int";
Debug.ShouldStop(131072);
 BA.debugLineNum = 115;BA.debugLine="Dim Left, IconSize As Int";
Debug.ShouldStop(262144);
_left = 0;Debug.locals.put("Left", _left);
_iconsize = 0;Debug.locals.put("IconSize", _iconsize);
 BA.debugLineNum = 116;BA.debugLine="If RightStack Then Left = abDividerWidth";
Debug.ShouldStop(524288);
if (_rightstack) { 
_left = (int) (_abdividerwidth);Debug.locals.put("Left", _left);};
 BA.debugLineNum = 117;BA.debugLine="If ivIcon.IsInitialized Then IconSize = ActionBar.Height";
Debug.ShouldStop(1048576);
if (_ivicon.IsInitialized()) { 
_iconsize = _actionbar.getHeight();Debug.locals.put("IconSize", _iconsize);};
 BA.debugLineNum = 118;BA.debugLine="ForceWidth = ForceWidth - abDividerWidth";
Debug.ShouldStop(2097152);
_forcewidth = (int) (_forcewidth-_abdividerwidth);Debug.locals.put("ForceWidth", _forcewidth);
 BA.debugLineNum = 120;BA.debugLine="Dim TextWidth, TextHeight As Int";
Debug.ShouldStop(8388608);
_textwidth = 0;Debug.locals.put("TextWidth", _textwidth);
_textheight = 0;Debug.locals.put("TextHeight", _textheight);
 BA.debugLineNum = 121;BA.debugLine="If lblText.Text <> \"\" Then";
Debug.ShouldStop(16777216);
if ((_lbltext.getText()).equals("") == false) { 
 BA.debugLineNum = 122;BA.debugLine="abCanvas.Initialize(ActionBar)";
Debug.ShouldStop(33554432);
_abcanvas.Initialize((android.view.View)(_actionbar.getObject()));
 BA.debugLineNum = 123;BA.debugLine="TextWidth = abCanvas.MeasureStringWidth(\";\" & lblText.Text & \";\", lblText.Typeface, lblText.TextSize)";
Debug.ShouldStop(67108864);
_textwidth = (int) (_abcanvas.MeasureStringWidth(";"+_lbltext.getText()+";",_lbltext.getTypeface(),_lbltext.getTextSize()));Debug.locals.put("TextWidth", _textwidth);
 BA.debugLineNum = 124;BA.debugLine="TextHeight = abCanvas.MeasureStringHeight(lblText.Text & \"y_Î\", lblText.Typeface, lblText.TextSize) * 1.3";
Debug.ShouldStop(134217728);
_textheight = (int) (_abcanvas.MeasureStringHeight(_lbltext.getText()+"y_Î",_lbltext.getTypeface(),_lbltext.getTextSize())*1.3);Debug.locals.put("TextHeight", _textheight);
 };
 BA.debugLineNum = 126;BA.debugLine="If TextPosition < 3 AND ivIcon.IsInitialized Then 'Icon reduction";
Debug.ShouldStop(536870912);
if (_textposition<3 && _ivicon.IsInitialized()) { 
 BA.debugLineNum = 127;BA.debugLine="IconSize = ActionBar.Height - TextHeight";
Debug.ShouldStop(1073741824);
_iconsize = (int) (_actionbar.getHeight()-_textheight);Debug.locals.put("IconSize", _iconsize);
 };
 BA.debugLineNum = 129;BA.debugLine="If TextPosition < 6 Then";
Debug.ShouldStop(1);
if (_textposition<6) { 
 BA.debugLineNum = 130;BA.debugLine="If TextWidth < IconSize Then TextWidth = IconSize 'Text smaller than icon";
Debug.ShouldStop(2);
if (_textwidth<_iconsize) { 
_textwidth = _iconsize;Debug.locals.put("TextWidth", _textwidth);};
 BA.debugLineNum = 131;BA.debugLine="If ForceWidth > 0 Then";
Debug.ShouldStop(4);
if (_forcewidth>0) { 
 BA.debugLineNum = 132;BA.debugLine="TextWidth = ForceWidth";
Debug.ShouldStop(8);
_textwidth = _forcewidth;Debug.locals.put("TextWidth", _textwidth);
 BA.debugLineNum = 133;BA.debugLine="IconSize = Min(IconSize, ForceWidth)";
Debug.ShouldStop(16);
_iconsize = (int) (__c.Min(_iconsize,_forcewidth));Debug.locals.put("IconSize", _iconsize);
 };
 }else {
 BA.debugLineNum = 136;BA.debugLine="If ForceWidth > 0 Then";
Debug.ShouldStop(128);
if (_forcewidth>0) { 
 BA.debugLineNum = 137;BA.debugLine="TextWidth = ForceWidth - IconSize";
Debug.ShouldStop(256);
_textwidth = (int) (_forcewidth-_iconsize);Debug.locals.put("TextWidth", _textwidth);
 BA.debugLineNum = 138;BA.debugLine="If TextWidth < 0 Then";
Debug.ShouldStop(512);
if (_textwidth<0) { 
 BA.debugLineNum = 139;BA.debugLine="IconSize = IconSize + TextWidth";
Debug.ShouldStop(1024);
_iconsize = (int) (_iconsize+_textwidth);Debug.locals.put("IconSize", _iconsize);
 BA.debugLineNum = 140;BA.debugLine="TextWidth = 0";
Debug.ShouldStop(2048);
_textwidth = (int) (0);Debug.locals.put("TextWidth", _textwidth);
 };
 };
 };
 BA.debugLineNum = 144;BA.debugLine="Select TextPosition";
Debug.ShouldStop(32768);
switch (BA.switchObjectToInt(_textposition,(byte) (1),(byte) (3),(byte) (2),(byte) (4),(byte) (5),(byte) (6),(byte) (7))) {
case 0:
case 1:
 BA.debugLineNum = 146;BA.debugLine="lblText.SetLayout(Left, 0, TextWidth, TextHeight)";
Debug.ShouldStop(131072);
_lbltext.SetLayout(_left,(int) (0),_textwidth,_textheight);
 break;
case 2:
case 3:
 BA.debugLineNum = 148;BA.debugLine="lblText.SetLayout(Left, ActionBar.Height - TextHeight, TextWidth, TextHeight)";
Debug.ShouldStop(524288);
_lbltext.SetLayout(_left,(int) (_actionbar.getHeight()-_textheight),_textwidth,_textheight);
 break;
case 4:
case 5:
 BA.debugLineNum = 150;BA.debugLine="lblText.SetLayout(Left, (ActionBar.Height - TextHeight) / 2, TextWidth, TextHeight)";
Debug.ShouldStop(2097152);
_lbltext.SetLayout(_left,(int) ((_actionbar.getHeight()-_textheight)/(double)2),_textwidth,_textheight);
 break;
case 6:
 BA.debugLineNum = 152;BA.debugLine="lblText.SetLayout(Left + IconSize, (ActionBar.Height - TextHeight) / 2, TextWidth, TextHeight)";
Debug.ShouldStop(8388608);
_lbltext.SetLayout((int) (_left+_iconsize),(int) ((_actionbar.getHeight()-_textheight)/(double)2),_textwidth,_textheight);
 break;
}
;
 BA.debugLineNum = 155;BA.debugLine="If ivIcon.IsInitialized Then";
Debug.ShouldStop(67108864);
if (_ivicon.IsInitialized()) { 
 BA.debugLineNum = 156;BA.debugLine="Dim IconWidth As Int";
Debug.ShouldStop(134217728);
_iconwidth = 0;Debug.locals.put("IconWidth", _iconwidth);
 BA.debugLineNum = 157;BA.debugLine="If TextPosition < 6 AND abIconAsWideAsText AND TextWidth > 0 Then";
Debug.ShouldStop(268435456);
if (_textposition<6 && _abiconaswideastext && _textwidth>0) { 
 BA.debugLineNum = 158;BA.debugLine="IconWidth = TextWidth";
Debug.ShouldStop(536870912);
_iconwidth = _textwidth;Debug.locals.put("IconWidth", _iconwidth);
 }else {
 BA.debugLineNum = 160;BA.debugLine="IconWidth = IconSize";
Debug.ShouldStop(-2147483648);
_iconwidth = _iconsize;Debug.locals.put("IconWidth", _iconwidth);
 };
 BA.debugLineNum = 162;BA.debugLine="Select TextPosition";
Debug.ShouldStop(2);
switch (BA.switchObjectToInt(_textposition,(byte) (1),(byte) (6),(byte) (7))) {
case 0:
 BA.debugLineNum = 164;BA.debugLine="ivIcon.SetLayout(Left + Floor((TextWidth - IconWidth) / 2), TextHeight, IconWidth, IconSize)";
Debug.ShouldStop(8);
_ivicon.SetLayout((int) (_left+__c.Floor((_textwidth-_iconwidth)/(double)2)),_textheight,_iconwidth,_iconsize);
 break;
case 1:
 BA.debugLineNum = 166;BA.debugLine="ivIcon.SetLayout(Left + TextWidth, 0,IconWidth, IconSize)";
Debug.ShouldStop(32);
_ivicon.SetLayout((int) (_left+_textwidth),(int) (0),_iconwidth,_iconsize);
 break;
case 2:
 BA.debugLineNum = 168;BA.debugLine="ivIcon.SetLayout(Left, 0, IconWidth, IconSize)";
Debug.ShouldStop(128);
_ivicon.SetLayout(_left,(int) (0),_iconwidth,_iconsize);
 break;
default:
 BA.debugLineNum = 170;BA.debugLine="ivIcon.SetLayout(Left + Floor((TextWidth - IconWidth) / 2), 0, IconWidth, IconSize)";
Debug.ShouldStop(512);
_ivicon.SetLayout((int) (_left+__c.Floor((_textwidth-_iconwidth)/(double)2)),(int) (0),_iconwidth,_iconsize);
 break;
}
;
 };
 BA.debugLineNum = 174;BA.debugLine="If TextPosition < 6 Then";
Debug.ShouldStop(8192);
if (_textposition<6) { 
 BA.debugLineNum = 175;BA.debugLine="Return Max(TextWidth, IconSize) + abDividerWidth";
Debug.ShouldStop(16384);
if (true) return (int) (__c.Max(_textwidth,_iconsize)+_abdividerwidth);
 }else {
 BA.debugLineNum = 177;BA.debugLine="Return TextWidth + IconSize + abDividerWidth";
Debug.ShouldStop(65536);
if (true) return (int) (_textwidth+_iconsize+_abdividerwidth);
 };
 BA.debugLineNum = 179;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return 0;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public int  _rightposition(anywheresoftware.b4a.objects.PanelWrapper _btn) throws Exception{
try {
		Debug.PushSubsStack("RightPosition (clsactionbar) ","clsactionbar",11,ba,this,412);
int _i = 0;
Debug.locals.put("Btn", _btn);
 BA.debugLineNum = 412;BA.debugLine="Private Sub RightPosition(Btn As Panel) As Int";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 413;BA.debugLine="For i = 0 To abRightStack.Size - 1";
Debug.ShouldStop(268435456);
{
final int step342 = 1;
final int limit342 = (int) (_abrightstack.getSize()-1);
for (_i = (int) (0); (step342 > 0 && _i <= limit342) || (step342 < 0 && _i >= limit342); _i = ((int)(0 + _i + step342))) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 414;BA.debugLine="If abRightStack.Get(i) = Btn Then Return i";
Debug.ShouldStop(536870912);
if ((_abrightstack.Get(_i)).equals((Object)(_btn.getObject()))) { 
if (true) return _i;};
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 416;BA.debugLine="Return -1";
Debug.ShouldStop(-2147483648);
if (true) return (int) (-1);
 BA.debugLineNum = 417;BA.debugLine="End Sub";
Debug.ShouldStop(1);
return 0;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _samewidthforall(boolean _enabled) throws Exception{
try {
		Debug.PushSubsStack("SameWidthForAll (clsactionbar) ","clsactionbar",11,ba,this,627);
Debug.locals.put("Enabled", _enabled);
 BA.debugLineNum = 627;BA.debugLine="Public Sub SameWidthForAll(Enabled As Boolean)";
Debug.ShouldStop(262144);
 BA.debugLineNum = 628;BA.debugLine="If Enabled Then";
Debug.ShouldStop(524288);
if (_enabled) { 
 BA.debugLineNum = 629;BA.debugLine="If GetTotalCount = 0 Then";
Debug.ShouldStop(1048576);
if (_gettotalcount()==0) { 
 BA.debugLineNum = 630;BA.debugLine="abSameWidthForAll = 10dip";
Debug.ShouldStop(2097152);
_absamewidthforall = __c.DipToCurrent((int) (10));
 }else {
 BA.debugLineNum = 632;BA.debugLine="abSameWidthForAll = Max(Round((ActionBar.Width + abDividerWidth) / GetTotalCount), 10dip)";
Debug.ShouldStop(8388608);
_absamewidthforall = (int) (__c.Max(__c.Round((_actionbar.getWidth()+_abdividerwidth)/(double)_gettotalcount()),__c.DipToCurrent((int) (10))));
 };
 }else {
 BA.debugLineNum = 635;BA.debugLine="abSameWidthForAll = 0";
Debug.ShouldStop(67108864);
_absamewidthforall = (int) (0);
 };
 BA.debugLineNum = 637;BA.debugLine="Invalidate";
Debug.ShouldStop(268435456);
_invalidate();
 BA.debugLineNum = 638;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _setbackground(Object _newbackground) throws Exception{
try {
		Debug.PushSubsStack("SetBackground (clsactionbar) ","clsactionbar",11,ba,this,649);
Debug.locals.put("NewBackground", _newbackground);
 BA.debugLineNum = 649;BA.debugLine="Public Sub SetBackground(NewBackground As Object)";
Debug.ShouldStop(256);
 BA.debugLineNum = 650;BA.debugLine="ActionBar.Background = NewBackground";
Debug.ShouldStop(512);
_actionbar.setBackground((android.graphics.drawable.Drawable)(_newbackground));
 BA.debugLineNum = 651;BA.debugLine="End Sub";
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
public String  _setdividerwidth(byte _newwidth) throws Exception{
try {
		Debug.PushSubsStack("SetDividerWidth (clsactionbar) ","clsactionbar",11,ba,this,374);
Debug.locals.put("NewWidth", _newwidth);
 BA.debugLineNum = 374;BA.debugLine="Public Sub SetDividerWidth(NewWidth As Byte)";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 375;BA.debugLine="abDividerWidth = NewWidth";
Debug.ShouldStop(4194304);
_abdividerwidth = _newwidth;
 BA.debugLineNum = 376;BA.debugLine="Invalidate";
Debug.ShouldStop(8388608);
_invalidate();
 BA.debugLineNum = 377;BA.debugLine="End Sub";
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
public String  _setfixedwidth(anywheresoftware.b4a.objects.ConcreteViewWrapper _btn,int _newwidth) throws Exception{
try {
		Debug.PushSubsStack("SetFixedWidth (clsactionbar) ","clsactionbar",11,ba,this,607);
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
anywheresoftware.b4a.objects.ImageViewWrapper _iv = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
int _i = 0;
int _delta = 0;
Debug.locals.put("Btn", _btn);
Debug.locals.put("NewWidth", _newwidth);
 BA.debugLineNum = 607;BA.debugLine="Public Sub SetFixedWidth(Btn As View, NewWidth As Int)";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 608;BA.debugLine="If abSameWidthForAll > 0 OR NewWidth = 0 Then Return";
Debug.ShouldStop(-2147483648);
if (_absamewidthforall>0 || _newwidth==0) { 
if (true) return "";};
 BA.debugLineNum = 609;BA.debugLine="Dim pnl As Panel, v As View, IV As ImageView, lbl As Label";
Debug.ShouldStop(1);
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();Debug.locals.put("pnl", _pnl);
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();Debug.locals.put("v", _v);
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();Debug.locals.put("IV", _iv);
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 610;BA.debugLine="pnl = Btn";
Debug.ShouldStop(2);
_pnl.setObject((android.view.ViewGroup)(_btn.getObject()));
 BA.debugLineNum = 611;BA.debugLine="For i = 0 To pnl.NumberOfViews - 1";
Debug.ShouldStop(4);
{
final int step500 = 1;
final int limit500 = (int) (_pnl.getNumberOfViews()-1);
for (_i = (int) (0); (step500 > 0 && _i <= limit500) || (step500 < 0 && _i >= limit500); _i = ((int)(0 + _i + step500))) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 612;BA.debugLine="v = pnl.GetView(i)";
Debug.ShouldStop(8);
_v = _pnl.GetView(_i);Debug.locals.put("v", _v);
 BA.debugLineNum = 613;BA.debugLine="If v Is ImageView Then IV = v";
Debug.ShouldStop(16);
if (_v.getObjectOrNull() instanceof android.widget.ImageView) { 
_iv.setObject((android.widget.ImageView)(_v.getObject()));};
 BA.debugLineNum = 614;BA.debugLine="If v Is Label Then lbl = v";
Debug.ShouldStop(32);
if (_v.getObjectOrNull() instanceof android.widget.TextView) { 
_lbl.setObject((android.widget.TextView)(_v.getObject()));};
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 616;BA.debugLine="Dim Delta As Int";
Debug.ShouldStop(128);
_delta = 0;Debug.locals.put("Delta", _delta);
 BA.debugLineNum = 617;BA.debugLine="Delta = NewWidth - pnl.Width";
Debug.ShouldStop(256);
_delta = (int) (_newwidth-_pnl.getWidth());Debug.locals.put("Delta", _delta);
 BA.debugLineNum = 618;BA.debugLine="If IV.IsInitialized Then IV.Left = IV.Left + Floor(Delta / 2)";
Debug.ShouldStop(512);
if (_iv.IsInitialized()) { 
_iv.setLeft((int) (_iv.getLeft()+__c.Floor(_delta/(double)2)));};
 BA.debugLineNum = 619;BA.debugLine="lbl.Left = lbl.Left + Floor(Delta / 2)";
Debug.ShouldStop(1024);
_lbl.setLeft((int) (_lbl.getLeft()+__c.Floor(_delta/(double)2)));
 BA.debugLineNum = 620;BA.debugLine="pnl.Width = pnl.Width + Delta";
Debug.ShouldStop(2048);
_pnl.setWidth((int) (_pnl.getWidth()+_delta));
 BA.debugLineNum = 621;BA.debugLine="UpdateBackground(pnl, lbl.Tag < 0)";
Debug.ShouldStop(4096);
_updatebackground(_pnl,(double)(BA.ObjectToNumber(_lbl.getTag()))<0);
 BA.debugLineNum = 622;BA.debugLine="ReorderViews";
Debug.ShouldStop(8192);
_reorderviews();
 BA.debugLineNum = 623;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _seticonaswideastext(boolean _enabled) throws Exception{
try {
		Debug.PushSubsStack("SetIconAsWideAsText (clsactionbar) ","clsactionbar",11,ba,this,642);
Debug.locals.put("Enabled", _enabled);
 BA.debugLineNum = 642;BA.debugLine="Public Sub SetIconAsWideAsText(Enabled As Boolean)";
Debug.ShouldStop(2);
 BA.debugLineNum = 643;BA.debugLine="abIconAsWideAsText = Enabled";
Debug.ShouldStop(4);
_abiconaswideastext = _enabled;
 BA.debugLineNum = 644;BA.debugLine="Invalidate";
Debug.ShouldStop(8);
_invalidate();
 BA.debugLineNum = 645;BA.debugLine="End Sub";
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
public String  _settext(anywheresoftware.b4a.objects.ConcreteViewWrapper _btn,String _text,int _textcolor,int _textsize) throws Exception{
try {
		Debug.PushSubsStack("SetText (clsactionbar) ","clsactionbar",11,ba,this,357);
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
anywheresoftware.b4a.objects.ImageViewWrapper _iv = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
int _i = 0;
Debug.locals.put("Btn", _btn);
Debug.locals.put("Text", _text);
Debug.locals.put("TextColor", _textcolor);
Debug.locals.put("TextSize", _textsize);
 BA.debugLineNum = 357;BA.debugLine="Public Sub SetText(Btn As View, Text As String, TextColor As Int, TextSize As Int)";
Debug.ShouldStop(16);
 BA.debugLineNum = 358;BA.debugLine="Dim pnl As Panel, v As View, IV As ImageView, lbl As Label";
Debug.ShouldStop(32);
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();Debug.locals.put("pnl", _pnl);
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();Debug.locals.put("v", _v);
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();Debug.locals.put("IV", _iv);
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 359;BA.debugLine="pnl = Btn";
Debug.ShouldStop(64);
_pnl.setObject((android.view.ViewGroup)(_btn.getObject()));
 BA.debugLineNum = 360;BA.debugLine="For i = 0 To pnl.NumberOfViews - 1";
Debug.ShouldStop(128);
{
final int step299 = 1;
final int limit299 = (int) (_pnl.getNumberOfViews()-1);
for (_i = (int) (0); (step299 > 0 && _i <= limit299) || (step299 < 0 && _i >= limit299); _i = ((int)(0 + _i + step299))) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 361;BA.debugLine="v = pnl.GetView(i)";
Debug.ShouldStop(256);
_v = _pnl.GetView(_i);Debug.locals.put("v", _v);
 BA.debugLineNum = 362;BA.debugLine="If v Is ImageView Then IV = v";
Debug.ShouldStop(512);
if (_v.getObjectOrNull() instanceof android.widget.ImageView) { 
_iv.setObject((android.widget.ImageView)(_v.getObject()));};
 BA.debugLineNum = 363;BA.debugLine="If v Is Label Then lbl = v";
Debug.ShouldStop(1024);
if (_v.getObjectOrNull() instanceof android.widget.TextView) { 
_lbl.setObject((android.widget.TextView)(_v.getObject()));};
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 365;BA.debugLine="lbl.Text = Text";
Debug.ShouldStop(4096);
_lbl.setText((Object)(_text));
 BA.debugLineNum = 366;BA.debugLine="If TextColor <> -1 Then lbl.TextColor = TextColor";
Debug.ShouldStop(8192);
if (_textcolor!=-1) { 
_lbl.setTextColor(_textcolor);};
 BA.debugLineNum = 367;BA.debugLine="If TextSize <> -1 Then lbl.TextSize = TextSize";
Debug.ShouldStop(16384);
if (_textsize!=-1) { 
_lbl.setTextSize((float) (_textsize));};
 BA.debugLineNum = 368;BA.debugLine="pnl.Width = ResizeButton(IV, lbl, Abs(lbl.Tag), lbl.Tag < 0, abSameWidthForAll)";
Debug.ShouldStop(32768);
_pnl.setWidth(_resizebutton(_iv,_lbl,(byte) (__c.Abs((double)(BA.ObjectToNumber(_lbl.getTag())))),(double)(BA.ObjectToNumber(_lbl.getTag()))<0,_absamewidthforall));
 BA.debugLineNum = 369;BA.debugLine="UpdateBackground(pnl, lbl.Tag < 0)";
Debug.ShouldStop(65536);
_updatebackground(_pnl,(double)(BA.ObjectToNumber(_lbl.getTag()))<0);
 BA.debugLineNum = 370;BA.debugLine="ReorderViews";
Debug.ShouldStop(131072);
_reorderviews();
 BA.debugLineNum = 371;BA.debugLine="End Sub";
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
public String  _startdraganddrop(anywheresoftware.b4a.objects.ConcreteViewWrapper _btn,Object _viewtoblock,String _onafterdropsub) throws Exception{
try {
		Debug.PushSubsStack("StartDragAndDrop (clsactionbar) ","clsactionbar",11,ba,this,431);
int _p = 0;
Debug.locals.put("Btn", _btn);
Debug.locals.put("ViewToBlock", _viewtoblock);
Debug.locals.put("OnAfterDropSub", _onafterdropsub);
 BA.debugLineNum = 431;BA.debugLine="Public Sub StartDragAndDrop(Btn As View, ViewToBlock As Object, OnAfterDropSub As String)";
Debug.ShouldStop(16384);
 BA.debugLineNum = 432;BA.debugLine="If Not(abPlaceHolder.IsInitialized) Then";
Debug.ShouldStop(32768);
if (__c.Not(_abplaceholder.IsInitialized())) { 
 BA.debugLineNum = 433;BA.debugLine="abDraggedBtn = Btn";
Debug.ShouldStop(65536);
_abdraggedbtn = _btn;
 BA.debugLineNum = 434;BA.debugLine="abViewToBlock = ViewToBlock";
Debug.ShouldStop(131072);
_abviewtoblock = _viewtoblock;
 BA.debugLineNum = 435;BA.debugLine="abOnAfterDropSub = OnAfterDropSub";
Debug.ShouldStop(262144);
_abonafterdropsub = _onafterdropsub;
 BA.debugLineNum = 438;BA.debugLine="abPlaceHolder.Initialize(\"\")";
Debug.ShouldStop(2097152);
_abplaceholder.Initialize(ba,"");
 BA.debugLineNum = 439;BA.debugLine="ActionBar.AddView(abPlaceHolder, Btn.Left, Btn.Top, Btn.Width, Btn.Height)";
Debug.ShouldStop(4194304);
_actionbar.AddView((android.view.View)(_abplaceholder.getObject()),_btn.getLeft(),_btn.getTop(),_btn.getWidth(),_btn.getHeight());
 BA.debugLineNum = 440;BA.debugLine="Dim P As Int";
Debug.ShouldStop(8388608);
_p = 0;Debug.locals.put("P", _p);
 BA.debugLineNum = 441;BA.debugLine="P = LeftPosition(Btn)";
Debug.ShouldStop(16777216);
_p = _leftposition((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_btn.getObject())));Debug.locals.put("P", _p);
 BA.debugLineNum = 442;BA.debugLine="If P >= 0 Then";
Debug.ShouldStop(33554432);
if (_p>=0) { 
 BA.debugLineNum = 443;BA.debugLine="abLeftStack.Set(P, abPlaceHolder)";
Debug.ShouldStop(67108864);
_ableftstack.Set(_p,(Object)(_abplaceholder.getObject()));
 BA.debugLineNum = 444;BA.debugLine="UpdateBackground(abPlaceHolder, False)";
Debug.ShouldStop(134217728);
_updatebackground(_abplaceholder,__c.False);
 }else {
 BA.debugLineNum = 446;BA.debugLine="P = RightPosition(Btn)";
Debug.ShouldStop(536870912);
_p = _rightposition((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_btn.getObject())));Debug.locals.put("P", _p);
 BA.debugLineNum = 447;BA.debugLine="If P >= 0 Then";
Debug.ShouldStop(1073741824);
if (_p>=0) { 
 BA.debugLineNum = 448;BA.debugLine="abRightStack.Set(P, abPlaceHolder)";
Debug.ShouldStop(-2147483648);
_abrightstack.Set(_p,(Object)(_abplaceholder.getObject()));
 BA.debugLineNum = 449;BA.debugLine="UpdateBackground(abPlaceHolder, True)";
Debug.ShouldStop(1);
_updatebackground(_abplaceholder,__c.True);
 };
 };
 };
 BA.debugLineNum = 453;BA.debugLine="End Sub";
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
public String  _updatebackground(anywheresoftware.b4a.objects.PanelWrapper _pnlbtn,boolean _rightstack) throws Exception{
try {
		Debug.PushSubsStack("UpdateBackground (clsactionbar) ","clsactionbar",11,ba,this,181);
Object _divdrawable = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _divrect = null;
anywheresoftware.b4a.objects.drawable.StateListDrawable _sd = null;
Debug.locals.put("pnlBtn", _pnlbtn);
Debug.locals.put("RightStack", _rightstack);
 BA.debugLineNum = 181;BA.debugLine="Private Sub UpdateBackground(pnlBtn As Panel, RightStack As Boolean)";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 182;BA.debugLine="abCanvas.Initialize(pnlBtn)";
Debug.ShouldStop(2097152);
_abcanvas.Initialize((android.view.View)(_pnlbtn.getObject()));
 BA.debugLineNum = 183;BA.debugLine="abCanvas.DrawColor(Colors.Transparent)";
Debug.ShouldStop(4194304);
_abcanvas.DrawColor(__c.Colors.Transparent);
 BA.debugLineNum = 184;BA.debugLine="If abDividerWidth > 0 Then";
Debug.ShouldStop(8388608);
if (_abdividerwidth>0) { 
 BA.debugLineNum = 185;BA.debugLine="Dim divDrawable As Object";
Debug.ShouldStop(16777216);
_divdrawable = new Object();Debug.locals.put("divDrawable", _divdrawable);
 BA.debugLineNum = 186;BA.debugLine="If abDividerDrawable = Null Then";
Debug.ShouldStop(33554432);
if (_abdividerdrawable== null) { 
 BA.debugLineNum = 187;BA.debugLine="divDrawable = abDefaultDividerDrawable";
Debug.ShouldStop(67108864);
_divdrawable = _abdefaultdividerdrawable;Debug.locals.put("divDrawable", _divdrawable);
 }else {
 BA.debugLineNum = 189;BA.debugLine="divDrawable = abDividerDrawable";
Debug.ShouldStop(268435456);
_divdrawable = _abdividerdrawable;Debug.locals.put("divDrawable", _divdrawable);
 };
 BA.debugLineNum = 191;BA.debugLine="Dim divRect As Rect";
Debug.ShouldStop(1073741824);
_divrect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();Debug.locals.put("divRect", _divrect);
 BA.debugLineNum = 192;BA.debugLine="If RightStack Then";
Debug.ShouldStop(-2147483648);
if (_rightstack) { 
 BA.debugLineNum = 193;BA.debugLine="divRect.Initialize(0, 0, abDividerWidth, pnlBtn.Height)";
Debug.ShouldStop(1);
_divrect.Initialize((int) (0),(int) (0),(int) (_abdividerwidth),_pnlbtn.getHeight());
 }else {
 BA.debugLineNum = 195;BA.debugLine="divRect.Initialize(pnlBtn.Width - abDividerWidth, 0, pnlBtn.Width, pnlBtn.Height)";
Debug.ShouldStop(4);
_divrect.Initialize((int) (_pnlbtn.getWidth()-_abdividerwidth),(int) (0),_pnlbtn.getWidth(),_pnlbtn.getHeight());
 };
 BA.debugLineNum = 197;BA.debugLine="abCanvas.DrawDrawable(divDrawable, divRect)";
Debug.ShouldStop(16);
_abcanvas.DrawDrawable((android.graphics.drawable.Drawable)(_divdrawable),(android.graphics.Rect)(_divrect.getObject()));
 };
 BA.debugLineNum = 200;BA.debugLine="Dim sd As StateListDrawable";
Debug.ShouldStop(128);
_sd = new anywheresoftware.b4a.objects.drawable.StateListDrawable();Debug.locals.put("sd", _sd);
 BA.debugLineNum = 201;BA.debugLine="sd.Initialize";
Debug.ShouldStop(256);
_sd.Initialize();
 BA.debugLineNum = 202;BA.debugLine="sd.AddState(sd.State_Pressed, abPressedDrawable)";
Debug.ShouldStop(512);
_sd.AddState(_sd.State_Pressed,(android.graphics.drawable.Drawable)(_abpresseddrawable));
 BA.debugLineNum = 203;BA.debugLine="sd.AddState(sd.State_Selected, abSelectedDrawable)";
Debug.ShouldStop(1024);
_sd.AddState(_sd.State_Selected,(android.graphics.drawable.Drawable)(_abselecteddrawable));
 BA.debugLineNum = 204;BA.debugLine="sd.AddCatchAllState(pnlBtn.Background)";
Debug.ShouldStop(2048);
_sd.AddCatchAllState(_pnlbtn.getBackground());
 BA.debugLineNum = 205;BA.debugLine="pnlBtn.Background = sd";
Debug.ShouldStop(4096);
_pnlbtn.setBackground((android.graphics.drawable.Drawable)(_sd.getObject()));
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
public String  _updatesignoftag(anywheresoftware.b4a.objects.PanelWrapper _pnlbtn,boolean _rightstack) throws Exception{
try {
		Debug.PushSubsStack("UpdateSignOfTag (clsactionbar) ","clsactionbar",11,ba,this,531);
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
int _i = 0;
Debug.locals.put("pnlBtn", _pnlbtn);
Debug.locals.put("RightStack", _rightstack);
 BA.debugLineNum = 531;BA.debugLine="Private Sub UpdateSignOfTag(pnlBtn As Panel, RightStack As Boolean)";
Debug.ShouldStop(262144);
 BA.debugLineNum = 533;BA.debugLine="Dim v As View";
Debug.ShouldStop(1048576);
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();Debug.locals.put("v", _v);
 BA.debugLineNum = 534;BA.debugLine="For i = 0 To pnlBtn.NumberOfViews - 1";
Debug.ShouldStop(2097152);
{
final int step434 = 1;
final int limit434 = (int) (_pnlbtn.getNumberOfViews()-1);
for (_i = (int) (0); (step434 > 0 && _i <= limit434) || (step434 < 0 && _i >= limit434); _i = ((int)(0 + _i + step434))) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 535;BA.debugLine="v = pnlBtn.GetView(i)";
Debug.ShouldStop(4194304);
_v = _pnlbtn.GetView(_i);Debug.locals.put("v", _v);
 BA.debugLineNum = 536;BA.debugLine="If v Is Label Then";
Debug.ShouldStop(8388608);
if (_v.getObjectOrNull() instanceof android.widget.TextView) { 
 BA.debugLineNum = 537;BA.debugLine="If RightStack Then";
Debug.ShouldStop(16777216);
if (_rightstack) { 
 BA.debugLineNum = 538;BA.debugLine="v.Tag = -Abs(v.Tag)";
Debug.ShouldStop(33554432);
_v.setTag((Object)(-__c.Abs((double)(BA.ObjectToNumber(_v.getTag())))));
 }else {
 BA.debugLineNum = 540;BA.debugLine="v.Tag = Abs(v.Tag)";
Debug.ShouldStop(134217728);
_v.setTag((Object)(__c.Abs((double)(BA.ObjectToNumber(_v.getTag())))));
 };
 BA.debugLineNum = 542;BA.debugLine="Return";
Debug.ShouldStop(536870912);
if (true) return "";
 };
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 545;BA.debugLine="End Sub";
Debug.ShouldStop(1);
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
return BA.SubDelegator.SubNotFound;
}
}
