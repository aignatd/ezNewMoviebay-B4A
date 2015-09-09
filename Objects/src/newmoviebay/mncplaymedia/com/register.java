package newmoviebay.mncplaymedia.com;

import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class register extends Activity implements B4AActivity{
	public static register mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "newmoviebay.mncplaymedia.com", "newmoviebay.mncplaymedia.com.register");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (register).");
				p.finish();
			}
		}
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
		BA.handler.postDelayed(new WaitForLayout(), 5);

	}
	private static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "newmoviebay.mncplaymedia.com", "newmoviebay.mncplaymedia.com.register");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "newmoviebay.mncplaymedia.com.register", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (register) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (register) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEvent(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return register.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (register) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (register) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}

public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivmov = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etpass = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etcfpass = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblpass = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etfirst = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etlast = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etmail = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etphone = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etgender = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etbirth = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etconfirm = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivbackrg = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _ck1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _breg = null;
public flm.b4a.betterdialogs.BetterDialogs _bdlogin = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spgender = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivreg = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spbirth = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcek = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _ckbox = null;
public anywheresoftware.b4a.objects.collections.List _lstcate = null;
public anywheresoftware.b4a.objects.collections.Map _coba = null;
public newmoviebay.mncplaymedia.com.anotherdatepicker _cvbirth = null;
public b4a.example.dateutils _dateutils = null;
public newmoviebay.mncplaymedia.com.main _main = null;
public newmoviebay.mncplaymedia.com.utama _utama = null;
public newmoviebay.mncplaymedia.com.content _content = null;
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
		return new Object[] {"Activity",mostCurrent._activity,"BDLogin",mostCurrent._bdlogin,"bReg",mostCurrent._breg,"ck1",mostCurrent._ck1,"ckBox",mostCurrent._ckbox,"coba",mostCurrent._coba,"Content",Debug.moduleToString(newmoviebay.mncplaymedia.com.content.class),"cvBirth",mostCurrent._cvbirth,"Date",Debug.moduleToString(newmoviebay.mncplaymedia.com.date.class),"DateUtils",mostCurrent._dateutils,"etBirth",mostCurrent._etbirth,"etCfpass",mostCurrent._etcfpass,"etConfirm",mostCurrent._etconfirm,"etFirst",mostCurrent._etfirst,"etGender",mostCurrent._etgender,"etLast",mostCurrent._etlast,"etMail",mostCurrent._etmail,"etPass",mostCurrent._etpass,"etPhone",mostCurrent._etphone,"ForgotPass",Debug.moduleToString(newmoviebay.mncplaymedia.com.forgotpass.class),"Fungsi",Debug.moduleToString(newmoviebay.mncplaymedia.com.fungsi.class),"ivBackrg",mostCurrent._ivbackrg,"ivMov",mostCurrent._ivmov,"ivReg",mostCurrent._ivreg,"lblCek",mostCurrent._lblcek,"lblPass",mostCurrent._lblpass,"lstCate",mostCurrent._lstcate,"Main",Debug.moduleToString(newmoviebay.mncplaymedia.com.main.class),"MenuSearch",Debug.moduleToString(newmoviebay.mncplaymedia.com.menusearch.class),"MNCUtils2Service",Debug.moduleToString(newmoviebay.mncplaymedia.com.mncutils2service.class),"Pemutar",Debug.moduleToString(newmoviebay.mncplaymedia.com.pemutar.class),"Play",Debug.moduleToString(newmoviebay.mncplaymedia.com.play.class),"Profile",Debug.moduleToString(newmoviebay.mncplaymedia.com.profile.class),"Result",Debug.moduleToString(newmoviebay.mncplaymedia.com.result.class),"Share",Debug.moduleToString(newmoviebay.mncplaymedia.com.share.class),"spBirth",mostCurrent._spbirth,"spGender",mostCurrent._spgender,"Utama",Debug.moduleToString(newmoviebay.mncplaymedia.com.utama.class),"WebView",Debug.moduleToString(newmoviebay.mncplaymedia.com.webview.class)};
}

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (register) ","register",3,mostCurrent.activityBA,mostCurrent,42);
anywheresoftware.b4a.objects.drawable.ColorDrawable _cdback = null;
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 42;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(512);
 BA.debugLineNum = 47;BA.debugLine="Fungsi.SetDirProg(\"\")";
Debug.ShouldStop(16384);
mostCurrent._fungsi._setdirprog(mostCurrent.activityBA,"");
 BA.debugLineNum = 48;BA.debugLine="Fungsi.BacaFileConfig";
Debug.ShouldStop(32768);
mostCurrent._fungsi._bacafileconfig(mostCurrent.activityBA);
 BA.debugLineNum = 52;BA.debugLine="If File.Exists(File.DirAssets, \"config.json\") Then";
Debug.ShouldStop(524288);
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"config.json")) { 
 BA.debugLineNum = 53;BA.debugLine="If Fungsi.CacahJSON(File.ReadString(File.DirAssets, \"config.json\"), \"NextArray\") = False Then";
Debug.ShouldStop(1048576);
if (mostCurrent._fungsi._cacahjson(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"config.json"),"NextArray")==anywheresoftware.b4a.keywords.Common.False) { 
 BA.debugLineNum = 54;BA.debugLine="BDLogin.Msgbox(\"Error\", \"Reading config failed !\", \"OK\", \"\", \"\", Fungsi.GetDrawable(\"ic_action_warning\"))";
Debug.ShouldStop(2097152);
mostCurrent._bdlogin.MsgBox("Error","Reading config failed !","OK","","",mostCurrent._fungsi._getdrawable(mostCurrent.activityBA,"ic_action_warning"),mostCurrent.activityBA);
 BA.debugLineNum = 55;BA.debugLine="ExitApplication";
Debug.ShouldStop(4194304);
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 }else {
 BA.debugLineNum = 58;BA.debugLine="BDLogin.Msgbox(\"Error\", \"Load config failed !\", \"OK\", \"\", \"\", Fungsi.GetDrawable(\"ic_action_warning\"))";
Debug.ShouldStop(33554432);
mostCurrent._bdlogin.MsgBox("Error","Load config failed !","OK","","",mostCurrent._fungsi._getdrawable(mostCurrent.activityBA,"ic_action_warning"),mostCurrent.activityBA);
 BA.debugLineNum = 59;BA.debugLine="ExitApplication";
Debug.ShouldStop(67108864);
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 BA.debugLineNum = 63;BA.debugLine="Activity.LoadLayout(\"NewReg\")";
Debug.ShouldStop(1073741824);
mostCurrent._activity.LoadLayout("NewReg",mostCurrent.activityBA);
 BA.debugLineNum = 66;BA.debugLine="Dim cdBack 	As ColorDrawable";
Debug.ShouldStop(2);
_cdback = new anywheresoftware.b4a.objects.drawable.ColorDrawable();Debug.locals.put("cdBack", _cdback);
 BA.debugLineNum = 67;BA.debugLine="cdBack.Initialize(Colors.RGB(132, 31, 40), 5)";
Debug.ShouldStop(4);
_cdback.Initialize(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (132),(int) (31),(int) (40)),(int) (5));
 BA.debugLineNum = 68;BA.debugLine="etFirst.Background = cdBack";
Debug.ShouldStop(8);
mostCurrent._etfirst.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 BA.debugLineNum = 69;BA.debugLine="etLast.Background = cdBack";
Debug.ShouldStop(16);
mostCurrent._etlast.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 BA.debugLineNum = 70;BA.debugLine="etMail.Background = cdBack";
Debug.ShouldStop(32);
mostCurrent._etmail.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 BA.debugLineNum = 71;BA.debugLine="etPhone.Background = cdBack";
Debug.ShouldStop(64);
mostCurrent._etphone.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 BA.debugLineNum = 74;BA.debugLine="etPass.Background = cdBack";
Debug.ShouldStop(512);
mostCurrent._etpass.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 BA.debugLineNum = 75;BA.debugLine="etConfirm.Background = cdBack";
Debug.ShouldStop(1024);
mostCurrent._etconfirm.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 BA.debugLineNum = 82;BA.debugLine="spGender.Add(\"\")";
Debug.ShouldStop(131072);
mostCurrent._spgender.Add("");
 BA.debugLineNum = 83;BA.debugLine="spGender.Add(\"Male\")";
Debug.ShouldStop(262144);
mostCurrent._spgender.Add("Male");
 BA.debugLineNum = 84;BA.debugLine="spGender.Add(\"Female\")";
Debug.ShouldStop(524288);
mostCurrent._spgender.Add("Female");
 BA.debugLineNum = 93;BA.debugLine="End Sub";
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
public static boolean  _activity_keypress(int _keycode) throws Exception{
try {
		Debug.PushSubsStack("Activity_KeyPress (register) ","register",3,mostCurrent.activityBA,mostCurrent,94);
Debug.locals.put("KeyCode", _keycode);
 BA.debugLineNum = 94;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 95;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
Debug.ShouldStop(1073741824);
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 BA.debugLineNum = 96;BA.debugLine="If cvBirth.IsVisible Then";
Debug.ShouldStop(-2147483648);
if (mostCurrent._cvbirth._isvisible()) { 
 BA.debugLineNum = 97;BA.debugLine="cvBirth.btnCancel_Click 'emulate a click on Cancel button";
Debug.ShouldStop(1);
mostCurrent._cvbirth._btncancel_click();
 BA.debugLineNum = 98;BA.debugLine="Return True";
Debug.ShouldStop(2);
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 };
 BA.debugLineNum = 101;BA.debugLine="Return False";
Debug.ShouldStop(16);
if (true) return anywheresoftware.b4a.keywords.Common.False;
 BA.debugLineNum = 102;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return false;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static String  _activity_pause(boolean _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (register) ","register",3,mostCurrent.activityBA,mostCurrent,108);
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 108;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(2048);
 BA.debugLineNum = 110;BA.debugLine="End Sub";
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
public static String  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (register) ","register",3,mostCurrent.activityBA,mostCurrent,104);
 BA.debugLineNum = 104;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(128);
 BA.debugLineNum = 106;BA.debugLine="End Sub";
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
public static String  _breg_click() throws Exception{
try {
		Debug.PushSubsStack("bReg_Click (register) ","register",3,mostCurrent.activityBA,mostCurrent,119);
newmoviebay.mncplaymedia.com.mnchttpjob _homejob = null;
String _strenc = "";
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _gen = null;
anywheresoftware.b4a.objects.collections.Map _mregister = null;
 BA.debugLineNum = 119;BA.debugLine="Sub bReg_Click";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 120;BA.debugLine="Dim HomeJob 	As MNChttpJob";
Debug.ShouldStop(8388608);
_homejob = new newmoviebay.mncplaymedia.com.mnchttpjob();Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 121;BA.debugLine="Dim	strEnc		As String";
Debug.ShouldStop(16777216);
_strenc = "";Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 122;BA.debugLine="Dim Gen 		As JSONGenerator";
Debug.ShouldStop(33554432);
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();Debug.locals.put("Gen", _gen);
 BA.debugLineNum = 123;BA.debugLine="Dim mregister 	As Map";
Debug.ShouldStop(67108864);
_mregister = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("mregister", _mregister);
 BA.debugLineNum = 126;BA.debugLine="mregister.Initialize";
Debug.ShouldStop(536870912);
_mregister.Initialize();
 BA.debugLineNum = 127;BA.debugLine="mregister.Clear";
Debug.ShouldStop(1073741824);
_mregister.Clear();
 BA.debugLineNum = 128;BA.debugLine="mregister.Put(Fungsi.mpList.Get(\"firstname\"), etFirst.text)";
Debug.ShouldStop(-2147483648);
_mregister.Put(mostCurrent._fungsi._mplist.Get((Object)("firstname")),(Object)(mostCurrent._etfirst.getText()));
 BA.debugLineNum = 129;BA.debugLine="mregister.Put(Fungsi.mpList.Get(\"lastname\"), etLast.text)";
Debug.ShouldStop(1);
_mregister.Put(mostCurrent._fungsi._mplist.Get((Object)("lastname")),(Object)(mostCurrent._etlast.getText()));
 BA.debugLineNum = 130;BA.debugLine="mregister.Put(Fungsi.mpList.Get(\"regemail\"), etMail.Text)";
Debug.ShouldStop(2);
_mregister.Put(mostCurrent._fungsi._mplist.Get((Object)("regemail")),(Object)(mostCurrent._etmail.getText()));
 BA.debugLineNum = 131;BA.debugLine="mregister.Put(Fungsi.mpList.Get(\"handphone\"), etPhone.Text)";
Debug.ShouldStop(4);
_mregister.Put(mostCurrent._fungsi._mplist.Get((Object)("handphone")),(Object)(mostCurrent._etphone.getText()));
 BA.debugLineNum = 132;BA.debugLine="mregister.Put(Fungsi.mpList.Get(\"regpass\"), etPass.text)";
Debug.ShouldStop(8);
_mregister.Put(mostCurrent._fungsi._mplist.Get((Object)("regpass")),(Object)(mostCurrent._etpass.getText()));
 BA.debugLineNum = 133;BA.debugLine="mregister.Put(Fungsi.mpList.Get(\"gender\"),  spGender.SelectedItem)";
Debug.ShouldStop(16);
_mregister.Put(mostCurrent._fungsi._mplist.Get((Object)("gender")),(Object)(mostCurrent._spgender.getSelectedItem()));
 BA.debugLineNum = 134;BA.debugLine="mregister.Put(Fungsi.mpList.Get(\"homephone\"), \"0\")";
Debug.ShouldStop(32);
_mregister.Put(mostCurrent._fungsi._mplist.Get((Object)("homephone")),(Object)("0"));
 BA.debugLineNum = 135;BA.debugLine="mregister.Put(Fungsi.mpList.Get(\"id_city\"), \"3173\")";
Debug.ShouldStop(64);
_mregister.Put(mostCurrent._fungsi._mplist.Get((Object)("id_city")),(Object)("3173"));
 BA.debugLineNum = 136;BA.debugLine="mregister.Put(Fungsi.mpList.Get(\"zipcode\"), \"13720\")";
Debug.ShouldStop(128);
_mregister.Put(mostCurrent._fungsi._mplist.Get((Object)("zipcode")),(Object)("13720"));
 BA.debugLineNum = 137;BA.debugLine="Gen.Initialize(mregister)";
Debug.ShouldStop(256);
_gen.Initialize(_mregister);
 BA.debugLineNum = 141;BA.debugLine="HomeJob.Initialize(\"Register\", Me)";
Debug.ShouldStop(4096);
_homejob._initialize(processBA,"Register",register.getObject());
 BA.debugLineNum = 144;BA.debugLine="Try";
Debug.ShouldStop(32768);
try { BA.debugLineNum = 145;BA.debugLine="HomeJob.Tag = \"Register\"";
Debug.ShouldStop(65536);
_homejob._tag = (Object)("Register");Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 146;BA.debugLine="strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(18))";
Debug.ShouldStop(131072);
_strenc = mostCurrent._fungsi._prosesenkripsi(mostCurrent.activityBA,_gen.ToPrettyString((int) (18)));Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 147;BA.debugLine="If strEnc = \"Enkripsi Failed\" Then Return";
Debug.ShouldStop(262144);
if ((_strenc).equals("Enkripsi Failed")) { 
if (true) return "";};
 BA.debugLineNum = 148;BA.debugLine="HomeJob.PostString(Fungsi.mpList.Get(\"RegisterURL\"), \"teks=\" & strEnc)";
Debug.ShouldStop(524288);
_homejob._poststring(BA.ObjectToString(mostCurrent._fungsi._mplist.Get((Object)("RegisterURL"))),"teks="+_strenc);
 BA.debugLineNum = 149;BA.debugLine="HomeJob.GetRequest.SetHeader(\"Cache-Control\", \"no-cache, must-revalidate\")";
Debug.ShouldStop(1048576);
_homejob._getrequest().SetHeader("Cache-Control","no-cache, must-revalidate");
 BA.debugLineNum = 150;BA.debugLine="HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60";
Debug.ShouldStop(2097152);
_homejob._getrequest().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 } 
       catch (Exception e97) {
			processBA.setLastException(e97); BA.debugLineNum = 152;BA.debugLine="Msgbox2(\"Register Failure !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(8388608);
anywheresoftware.b4a.keywords.Common.Msgbox2("Register Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 };
 BA.debugLineNum = 155;BA.debugLine="End Sub";
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
public static String  _ck1_checkedchange(boolean _checked) throws Exception{
try {
		Debug.PushSubsStack("ck1_CheckedChange (register) ","register",3,mostCurrent.activityBA,mostCurrent,114);
Debug.locals.put("Checked", _checked);
 BA.debugLineNum = 114;BA.debugLine="Sub  ck1_CheckedChange(Checked As Boolean)";
Debug.ShouldStop(131072);
 BA.debugLineNum = 115;BA.debugLine="If Checked = True Then bReg.Enabled = True Else bReg.Enabled = False";
Debug.ShouldStop(262144);
if (_checked==anywheresoftware.b4a.keywords.Common.True) { 
mostCurrent._breg.setEnabled(anywheresoftware.b4a.keywords.Common.True);}
else {
mostCurrent._breg.setEnabled(anywheresoftware.b4a.keywords.Common.False);};
 BA.debugLineNum = 118;BA.debugLine="End Sub";
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
public static String  _ckbox_checkedchange(boolean _checked) throws Exception{
try {
		Debug.PushSubsStack("ckBox_CheckedChange (register) ","register",3,mostCurrent.activityBA,mostCurrent,246);
Debug.locals.put("Checked", _checked);
 BA.debugLineNum = 246;BA.debugLine="Sub ckBox_CheckedChange(Checked As Boolean)";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 248;BA.debugLine="End Sub";
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
public static String  _cvbirth_closed(boolean _cancelled,long _date) throws Exception{
try {
		Debug.PushSubsStack("cvBirth_Closed (register) ","register",3,mostCurrent.activityBA,mostCurrent,249);
Debug.locals.put("Cancelled", _cancelled);
Debug.locals.put("Date", _date);
 BA.debugLineNum = 249;BA.debugLine="Sub cvBirth_Closed (Cancelled As Boolean, Date As Long)";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 251;BA.debugLine="End Sub";
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
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 17;BA.debugLine="Private ivMov 		As ImageView";
mostCurrent._ivmov = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private etPass 		As EditText";
mostCurrent._etpass = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private etCfpass	As EditText";
mostCurrent._etcfpass = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private lblPass 	As Label";
mostCurrent._lblpass = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private etFirst 	As EditText";
mostCurrent._etfirst = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private etLast 		As EditText";
mostCurrent._etlast = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private etMail 		As EditText";
mostCurrent._etmail = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private etPhone 	As EditText";
mostCurrent._etphone = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private etGender 	As EditText";
mostCurrent._etgender = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private etBirth 	As EditText";
mostCurrent._etbirth = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private etConfirm 	As EditText";
mostCurrent._etconfirm = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private ivBackrg 	As ImageView";
mostCurrent._ivbackrg = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private ck1 		As CheckBox";
mostCurrent._ck1 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private bReg 		As Button";
mostCurrent._breg = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private BDLogin		As BetterDialogs";
mostCurrent._bdlogin = new flm.b4a.betterdialogs.BetterDialogs();
 //BA.debugLineNum = 32;BA.debugLine="Private spGender 	As Spinner";
mostCurrent._spgender = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private ivReg 		As ImageView";
mostCurrent._ivreg = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private spBirth 	As Spinner";
mostCurrent._spbirth = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private lblCek 		As Label";
mostCurrent._lblcek = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private ckBox 		As CheckBox";
mostCurrent._ckbox = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private lstCate 	As List";
mostCurrent._lstcate = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 38;BA.debugLine="Private coba 		As Map";
mostCurrent._coba = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 39;BA.debugLine="Private cvBirth 	As AnotherDatePicker";
mostCurrent._cvbirth = new newmoviebay.mncplaymedia.com.anotherdatepicker();
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return "";
}
public static String  _ivbackrg_click() throws Exception{
try {
		Debug.PushSubsStack("ivBackrg_Click (register) ","register",3,mostCurrent.activityBA,mostCurrent,111);
 BA.debugLineNum = 111;BA.debugLine="Sub ivBackrg_Click";
Debug.ShouldStop(16384);
 BA.debugLineNum = 112;BA.debugLine="Activity.Finish";
Debug.ShouldStop(32768);
mostCurrent._activity.Finish();
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
public static String  _jobdone(newmoviebay.mncplaymedia.com.mnchttpjob _mnchttp) throws Exception{
try {
		Debug.PushSubsStack("JobDone (register) ","register",3,mostCurrent.activityBA,mostCurrent,156);
adr.stringfunctions.stringfunctions _sf = null;
String _stmp = "";
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.Map _mresult = null;
byte[] _btmp = null;
anywheresoftware.b4a.agraham.byteconverter.ByteConverter _bctmp = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
Debug.locals.put("MNChttp", _mnchttp);
 BA.debugLineNum = 156;BA.debugLine="Sub JobDone (MNChttp As MNChttpJob)";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 158;BA.debugLine="Dim sf			As StringFunctions";
Debug.ShouldStop(536870912);
_sf = new adr.stringfunctions.stringfunctions();Debug.locals.put("sf", _sf);
 BA.debugLineNum = 159;BA.debugLine="Dim sTmp 		As String";
Debug.ShouldStop(1073741824);
_stmp = "";Debug.locals.put("sTmp", _stmp);
 BA.debugLineNum = 160;BA.debugLine="Dim parser 		As JSONParser";
Debug.ShouldStop(-2147483648);
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();Debug.locals.put("parser", _parser);
 BA.debugLineNum = 161;BA.debugLine="Dim mresult		As Map";
Debug.ShouldStop(1);
_mresult = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("mresult", _mresult);
 BA.debugLineNum = 162;BA.debugLine="Dim bTmp()		As Byte";
Debug.ShouldStop(2);
_btmp = new byte[(int) (0)];
;Debug.locals.put("bTmp", _btmp);
 BA.debugLineNum = 163;BA.debugLine="Dim bcTmp		As ByteConverter";
Debug.ShouldStop(4);
_bctmp = new anywheresoftware.b4a.agraham.byteconverter.ByteConverter();Debug.locals.put("bcTmp", _bctmp);
 BA.debugLineNum = 164;BA.debugLine="Dim su 			As StringUtils";
Debug.ShouldStop(8);
_su = new anywheresoftware.b4a.objects.StringUtils();Debug.locals.put("su", _su);
 BA.debugLineNum = 168;BA.debugLine="If MNChttp.Success = False Then";
Debug.ShouldStop(128);
if (_mnchttp._success==anywheresoftware.b4a.keywords.Common.False) { 
 BA.debugLineNum = 169;BA.debugLine="If (sf.InString(MNChttp.ErrorMessage, \"timed out\") > 0) OR (sf.InString(MNChttp.ErrorMessage, \"refused\") > 0) OR _ 			 (sf.InString(MNChttp.ErrorMessage, \"Unable to resolve host\") > 0) OR (sf.InString(MNChttp.ErrorMessage, \"UnknownHostException\") > 0) OR _ 			 (sf.InString(MNChttp.ErrorMessage, \"FileNotFound\") > 0) Then";
Debug.ShouldStop(256);
if ((_sf._vvv4(_mnchttp._errormessage,"timed out")>0) || (_sf._vvv4(_mnchttp._errormessage,"refused")>0) || (_sf._vvv4(_mnchttp._errormessage,"Unable to resolve host")>0) || (_sf._vvv4(_mnchttp._errormessage,"UnknownHostException")>0) || (_sf._vvv4(_mnchttp._errormessage,"FileNotFound")>0)) { 
 BA.debugLineNum = 173;BA.debugLine="Msgbox2(\"Offline Connection or Server Down\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(4096);
anywheresoftware.b4a.keywords.Common.Msgbox2("Offline Connection or Server Down","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 175;BA.debugLine="If sf.InString(MNChttp.ErrorMessage, \"Not Found\") < 0 Then";
Debug.ShouldStop(16384);
if (_sf._vvv4(_mnchttp._errormessage,"Not Found")<0) { 
 BA.debugLineNum = 177;BA.debugLine="Msgbox2(\"Unknown Error\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(65536);
anywheresoftware.b4a.keywords.Common.Msgbox2("Unknown Error","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 };
 };
 BA.debugLineNum = 181;BA.debugLine="Fungsi.mpList.Put(\"Koneksi\", \"Offline\")";
Debug.ShouldStop(1048576);
mostCurrent._fungsi._mplist.Put((Object)("Koneksi"),(Object)("Offline"));
 BA.debugLineNum = 182;BA.debugLine="MNChttp.Release";
Debug.ShouldStop(2097152);
_mnchttp._release();
 }else {
 BA.debugLineNum = 186;BA.debugLine="Try";
Debug.ShouldStop(33554432);
try { BA.debugLineNum = 187;BA.debugLine="bTmp = su.DecodeBase64(MNChttp.GetString)";
Debug.ShouldStop(67108864);
_btmp = _su.DecodeBase64(_mnchttp._getstring());Debug.locals.put("bTmp", _btmp);
 BA.debugLineNum = 188;BA.debugLine="sTmp = bcTmp.StringFromBytes(bTmp, \"UTF8\")";
Debug.ShouldStop(134217728);
_stmp = _bctmp.StringFromBytes(_btmp,"UTF8");Debug.locals.put("sTmp", _stmp);
 BA.debugLineNum = 189;BA.debugLine="parser.Initialize(Fungsi.UnWrap(sTmp))";
Debug.ShouldStop(268435456);
_parser.Initialize(mostCurrent._fungsi._unwrap(mostCurrent.activityBA,_stmp));
 BA.debugLineNum = 190;BA.debugLine="MNChttp.Release";
Debug.ShouldStop(536870912);
_mnchttp._release();
 } 
       catch (Exception e125) {
			processBA.setLastException(e125); BA.debugLineNum = 192;BA.debugLine="Msgbox2(\"Process Failure !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(-2147483648);
anywheresoftware.b4a.keywords.Common.Msgbox2("Process Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 BA.debugLineNum = 193;BA.debugLine="Return";
Debug.ShouldStop(1);
if (true) return "";
 };
 BA.debugLineNum = 196;BA.debugLine="Try";
Debug.ShouldStop(8);
try { BA.debugLineNum = 197;BA.debugLine="mresult.Initialize";
Debug.ShouldStop(16);
_mresult.Initialize();
 BA.debugLineNum = 198;BA.debugLine="mresult = parser.NextObject";
Debug.ShouldStop(32);
_mresult = _parser.NextObject();Debug.locals.put("mresult", _mresult);
 } 
       catch (Exception e132) {
			processBA.setLastException(e132); BA.debugLineNum = 200;BA.debugLine="Msgbox2(\"Process Failure !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(128);
anywheresoftware.b4a.keywords.Common.Msgbox2("Process Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 BA.debugLineNum = 201;BA.debugLine="Return";
Debug.ShouldStop(256);
if (true) return "";
 };
 BA.debugLineNum = 204;BA.debugLine="If mresult.Get(\"Result\") <> \"0\" Then";
Debug.ShouldStop(2048);
if ((_mresult.Get((Object)("Result"))).equals((Object)("0")) == false) { 
 BA.debugLineNum = 205;BA.debugLine="Msgbox2(mresult.Get(\"Message\"), \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(4096);
anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToString(_mresult.Get((Object)("Message"))),"Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 207;BA.debugLine="Fungsi.mpList.Put(\"Koneksi\", \"Online\")";
Debug.ShouldStop(16384);
mostCurrent._fungsi._mplist.Put((Object)("Koneksi"),(Object)("Online"));
 BA.debugLineNum = 208;BA.debugLine="MNChttp.Release";
Debug.ShouldStop(32768);
_mnchttp._release();
 BA.debugLineNum = 210;BA.debugLine="Select MNChttp.JobName";
Debug.ShouldStop(131072);
switch (BA.switchObjectToInt(_mnchttp._jobname,"Register","Login")) {
case 0:
 BA.debugLineNum = 214;BA.debugLine="Msgbox2(mresult.Get(\"Message\"), \"Movibay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(2097152);
anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToString(_mresult.Get((Object)("Message"))),"Movibay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 break;
case 1:
 BA.debugLineNum = 219;BA.debugLine="Log(mresult.Get(\"Message\"))";
Debug.ShouldStop(67108864);
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(_mresult.Get((Object)("Message"))));
 BA.debugLineNum = 220;BA.debugLine="Msgbox2(mresult.Get(\"Message\"), \"Movibay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(134217728);
anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToString(_mresult.Get((Object)("Message"))),"Movibay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 BA.debugLineNum = 223;BA.debugLine="If lstCate.IsInitialized = False Then lstCate.Initialize";
Debug.ShouldStop(1073741824);
if (mostCurrent._lstcate.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._lstcate.Initialize();};
 BA.debugLineNum = 224;BA.debugLine="lstCate.Clear";
Debug.ShouldStop(-2147483648);
mostCurrent._lstcate.Clear();
 break;
}
;
 };
 };
 BA.debugLineNum = 231;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _spbirth_itemclick(int _position,Object _value) throws Exception{
try {
		Debug.PushSubsStack("spBirth_ItemClick (register) ","register",3,mostCurrent.activityBA,mostCurrent,243);
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 243;BA.debugLine="Sub spBirth_ItemClick (Position As Int, Value As Object )";
Debug.ShouldStop(262144);
 BA.debugLineNum = 245;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static String  _spgender_itemclick(int _position,Object _value) throws Exception{
try {
		Debug.PushSubsStack("spGender_ItemClick (register) ","register",3,mostCurrent.activityBA,mostCurrent,232);
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 232;BA.debugLine="Sub spGender_ItemClick (Position As Int, Value As Object)";
Debug.ShouldStop(128);
 BA.debugLineNum = 234;BA.debugLine="If Value = \"Male\" Then";
Debug.ShouldStop(512);
if ((_value).equals((Object)("Male"))) { 
 }else 
{ BA.debugLineNum = 236;BA.debugLine="Else If Value = \"Female\" Then";
Debug.ShouldStop(2048);
if ((_value).equals((Object)("Female"))) { 
 }else {
 }};
 BA.debugLineNum = 242;BA.debugLine="End Sub";
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
}
