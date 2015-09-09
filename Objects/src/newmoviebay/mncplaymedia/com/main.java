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

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "newmoviebay.mncplaymedia.com", "newmoviebay.mncplaymedia.com.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
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
		activityBA = new BA(this, layout, processBA, "newmoviebay.mncplaymedia.com", "newmoviebay.mncplaymedia.com.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "newmoviebay.mncplaymedia.com.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
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
		return main.class;
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
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (main) Resume **");
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
public adr.splashfadelibrary.splashfade _sfmain = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivlogin = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _iv1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etemail = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _iv2 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etpass = null;
public anywheresoftware.b4a.objects.ButtonWrapper _bsubmit = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _iv3 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _iv4 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivbg = null;
public flm.b4a.betterdialogs.BetterDialogs _bdlogin = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivloginfb = null;
public static int _ipack = 0;
public anywheresoftware.b4a.phone.PackageManagerWrapper _pm = null;
public anywheresoftware.b4a.objects.IntentWrapper _ups = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivuser = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbluser = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblnamafilm = null;
public anywheresoftware.b4a.objects.collections.Map _user = null;
public static boolean _pertama = false;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public b4a.example.dateutils _dateutils = null;
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
		return new Object[] {"Activity",mostCurrent._activity,"BDLogin",mostCurrent._bdlogin,"bSubmit",mostCurrent._bsubmit,"Content",Debug.moduleToString(newmoviebay.mncplaymedia.com.content.class),"Date",Debug.moduleToString(newmoviebay.mncplaymedia.com.date.class),"DateUtils",mostCurrent._dateutils,"etEmail",mostCurrent._etemail,"etPass",mostCurrent._etpass,"ForgotPass",Debug.moduleToString(newmoviebay.mncplaymedia.com.forgotpass.class),"Fungsi",Debug.moduleToString(newmoviebay.mncplaymedia.com.fungsi.class),"ipack",_ipack,"iv1",mostCurrent._iv1,"iv2",mostCurrent._iv2,"iv3",mostCurrent._iv3,"iv4",mostCurrent._iv4,"ivBg",mostCurrent._ivbg,"ivLogin",mostCurrent._ivlogin,"ivLoginFB",mostCurrent._ivloginfb,"ivUser",mostCurrent._ivuser,"Label1",mostCurrent._label1,"lblNamaFilm",mostCurrent._lblnamafilm,"lblUser",mostCurrent._lbluser,"MenuSearch",Debug.moduleToString(newmoviebay.mncplaymedia.com.menusearch.class),"MNCUtils2Service",Debug.moduleToString(newmoviebay.mncplaymedia.com.mncutils2service.class),"Pemutar",Debug.moduleToString(newmoviebay.mncplaymedia.com.pemutar.class),"Pertama",_pertama,"Play",Debug.moduleToString(newmoviebay.mncplaymedia.com.play.class),"pm",mostCurrent._pm,"Profile",Debug.moduleToString(newmoviebay.mncplaymedia.com.profile.class),"Register",Debug.moduleToString(newmoviebay.mncplaymedia.com.register.class),"Result",Debug.moduleToString(newmoviebay.mncplaymedia.com.result.class),"sfMain",mostCurrent._sfmain,"Share",Debug.moduleToString(newmoviebay.mncplaymedia.com.share.class),"ups",mostCurrent._ups,"user",mostCurrent._user,"Utama",Debug.moduleToString(newmoviebay.mncplaymedia.com.utama.class),"WebView",Debug.moduleToString(newmoviebay.mncplaymedia.com.webview.class)};
}

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (utama.mostCurrent != null);
vis = vis | (content.mostCurrent != null);
vis = vis | (register.mostCurrent != null);
vis = vis | (menusearch.mostCurrent != null);
vis = vis | (profile.mostCurrent != null);
vis = vis | (forgotpass.mostCurrent != null);
vis = vis | (share.mostCurrent != null);
vis = vis | (pemutar.mostCurrent != null);
vis = vis | (webview.mostCurrent != null);
vis = vis | (play.mostCurrent != null);
vis = vis | (result.mostCurrent != null);
vis = vis | (date.mostCurrent != null);
return vis;}

public static void killProgram() {
     {
            Activity __a = null;
            if (main.previousOne != null) {
				__a = main.previousOne.get();
			}
            else {
                BA ba = main.mostCurrent.processBA.sharedProcessBA.activityBA.get();
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

 {
            Activity __a = null;
            if (utama.previousOne != null) {
				__a = utama.previousOne.get();
			}
            else {
                BA ba = utama.mostCurrent.processBA.sharedProcessBA.activityBA.get();
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

 {
            Activity __a = null;
            if (content.previousOne != null) {
				__a = content.previousOne.get();
			}
            else {
                BA ba = content.mostCurrent.processBA.sharedProcessBA.activityBA.get();
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

 {
            Activity __a = null;
            if (register.previousOne != null) {
				__a = register.previousOne.get();
			}
            else {
                BA ba = register.mostCurrent.processBA.sharedProcessBA.activityBA.get();
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

 {
            Activity __a = null;
            if (menusearch.previousOne != null) {
				__a = menusearch.previousOne.get();
			}
            else {
                BA ba = menusearch.mostCurrent.processBA.sharedProcessBA.activityBA.get();
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

 {
            Activity __a = null;
            if (profile.previousOne != null) {
				__a = profile.previousOne.get();
			}
            else {
                BA ba = profile.mostCurrent.processBA.sharedProcessBA.activityBA.get();
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

 {
            Activity __a = null;
            if (forgotpass.previousOne != null) {
				__a = forgotpass.previousOne.get();
			}
            else {
                BA ba = forgotpass.mostCurrent.processBA.sharedProcessBA.activityBA.get();
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

 {
            Activity __a = null;
            if (share.previousOne != null) {
				__a = share.previousOne.get();
			}
            else {
                BA ba = share.mostCurrent.processBA.sharedProcessBA.activityBA.get();
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

BA.applicationContext.stopService(new android.content.Intent(BA.applicationContext, mncutils2service.class));
 {
            Activity __a = null;
            if (pemutar.previousOne != null) {
				__a = pemutar.previousOne.get();
			}
            else {
                BA ba = pemutar.mostCurrent.processBA.sharedProcessBA.activityBA.get();
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

 {
            Activity __a = null;
            if (webview.previousOne != null) {
				__a = webview.previousOne.get();
			}
            else {
                BA ba = webview.mostCurrent.processBA.sharedProcessBA.activityBA.get();
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

 {
            Activity __a = null;
            if (play.previousOne != null) {
				__a = play.previousOne.get();
			}
            else {
                BA ba = play.mostCurrent.processBA.sharedProcessBA.activityBA.get();
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

 {
            Activity __a = null;
            if (result.previousOne != null) {
				__a = result.previousOne.get();
			}
            else {
                BA ba = result.mostCurrent.processBA.sharedProcessBA.activityBA.get();
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

 {
            Activity __a = null;
            if (date.previousOne != null) {
				__a = date.previousOne.get();
			}
            else {
                BA ba = date.mostCurrent.processBA.sharedProcessBA.activityBA.get();
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

}
public static String  _activity_create(boolean _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,mostCurrent.activityBA,mostCurrent,56);
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 56;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 60;BA.debugLine="Activity.Color = Colors.ARGB(255, 50, 50, 50)";
Debug.ShouldStop(134217728);
mostCurrent._activity.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (50),(int) (50),(int) (50)));
 BA.debugLineNum = 63;BA.debugLine="If FirstTime Then";
Debug.ShouldStop(1073741824);
if (_firsttime) { 
 BA.debugLineNum = 64;BA.debugLine="Fungsi.SetDirProg(\"\")";
Debug.ShouldStop(-2147483648);
mostCurrent._fungsi._setdirprog(mostCurrent.activityBA,"");
 BA.debugLineNum = 65;BA.debugLine="Fungsi.BacaFileConfig";
Debug.ShouldStop(1);
mostCurrent._fungsi._bacafileconfig(mostCurrent.activityBA);
 BA.debugLineNum = 66;BA.debugLine="Fungsi.mpList.Put(\"DirDBS\", Fungsi.DirDBS)";
Debug.ShouldStop(2);
mostCurrent._fungsi._mplist.Put((Object)("DirDBS"),(Object)(mostCurrent._fungsi._dirdbs));
 };
 BA.debugLineNum = 71;BA.debugLine="If GetDeviceLayoutValues.ApproximateScreenSize < 5 Then";
Debug.ShouldStop(64);
if (anywheresoftware.b4a.keywords.Common.GetDeviceLayoutValues(mostCurrent.activityBA).getApproximateScreenSize()<5) { 
 BA.debugLineNum = 73;BA.debugLine="sfMain.Initialize(Activity, Me, \"mockupsplashscreen.jpg\", Gravity.CENTER, 2000, 100, \"Down\", True)";
Debug.ShouldStop(256);
mostCurrent._sfmain._initialize(mostCurrent.activityBA,mostCurrent._activity,main.getObject(),"mockupsplashscreen.jpg",anywheresoftware.b4a.keywords.Common.Gravity.CENTER,(int) (2000),(int) (100),"Down",anywheresoftware.b4a.keywords.Common.True);
 }else {
 BA.debugLineNum = 76;BA.debugLine="sfMain.Initialize(Activity, Me, \"mockupsplashscreen.jpg\", Gravity.CENTER, 2000, 100, \"Down\", True)";
Debug.ShouldStop(2048);
mostCurrent._sfmain._initialize(mostCurrent.activityBA,mostCurrent._activity,main.getObject(),"mockupsplashscreen.jpg",anywheresoftware.b4a.keywords.Common.Gravity.CENTER,(int) (2000),(int) (100),"Down",anywheresoftware.b4a.keywords.Common.True);
 };
 BA.debugLineNum = 80;BA.debugLine="Pertama = FirstTime";
Debug.ShouldStop(32768);
_pertama = _firsttime;
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
public static String  _activity_pause(boolean _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,mostCurrent.activityBA,mostCurrent,87);
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 87;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 89;BA.debugLine="End Sub";
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
public static String  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,mostCurrent.activityBA,mostCurrent,83);
 BA.debugLineNum = 83;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(262144);
 BA.debugLineNum = 84;BA.debugLine="Fungsi.DirDBS = Fungsi.mpList.Get(\"DirDBS\")";
Debug.ShouldStop(524288);
mostCurrent._fungsi._dirdbs = BA.ObjectToString(mostCurrent._fungsi._mplist.Get((Object)("DirDBS")));
 BA.debugLineNum = 85;BA.debugLine="End Sub";
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
public static String  _breg_click() throws Exception{
try {
		Debug.PushSubsStack("bReg_Click (main) ","main",0,mostCurrent.activityBA,mostCurrent,177);
 BA.debugLineNum = 177;BA.debugLine="Sub bReg_Click";
Debug.ShouldStop(65536);
 BA.debugLineNum = 178;BA.debugLine="StartActivity(\"Register\")";
Debug.ShouldStop(131072);
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)("Register"));
 BA.debugLineNum = 179;BA.debugLine="End Sub";
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
public static String  _bsubmit_click() throws Exception{
try {
		Debug.PushSubsStack("bSubmit_Click (main) ","main",0,mostCurrent.activityBA,mostCurrent,145);
newmoviebay.mncplaymedia.com.mnchttpjob _homejob = null;
String _strenc = "";
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _gen = null;
anywheresoftware.b4a.objects.collections.Map _mlogin = null;
 BA.debugLineNum = 145;BA.debugLine="Sub bSubmit_Click";
Debug.ShouldStop(65536);
 BA.debugLineNum = 148;BA.debugLine="Dim HomeJob As MNChttpJob";
Debug.ShouldStop(524288);
_homejob = new newmoviebay.mncplaymedia.com.mnchttpjob();Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 149;BA.debugLine="Dim	strEnc	As String";
Debug.ShouldStop(1048576);
_strenc = "";Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 150;BA.debugLine="Dim Gen		As JSONGenerator";
Debug.ShouldStop(2097152);
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();Debug.locals.put("Gen", _gen);
 BA.debugLineNum = 151;BA.debugLine="Dim mlogin	As Map";
Debug.ShouldStop(4194304);
_mlogin = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("mlogin", _mlogin);
 BA.debugLineNum = 153;BA.debugLine="mlogin.Initialize";
Debug.ShouldStop(16777216);
_mlogin.Initialize();
 BA.debugLineNum = 154;BA.debugLine="mlogin.Clear";
Debug.ShouldStop(33554432);
_mlogin.Clear();
 BA.debugLineNum = 157;BA.debugLine="mlogin.Put(Fungsi.mpList.Get(\"username\"), etEmail.text)";
Debug.ShouldStop(268435456);
_mlogin.Put(mostCurrent._fungsi._mplist.Get((Object)("username")),(Object)(mostCurrent._etemail.getText()));
 BA.debugLineNum = 158;BA.debugLine="mlogin.Put(Fungsi.mpList.Get(\"loginpass\"), etPass.Text)";
Debug.ShouldStop(536870912);
_mlogin.Put(mostCurrent._fungsi._mplist.Get((Object)("loginpass")),(Object)(mostCurrent._etpass.getText()));
 BA.debugLineNum = 159;BA.debugLine="Gen.Initialize(mlogin)";
Debug.ShouldStop(1073741824);
_gen.Initialize(_mlogin);
 BA.debugLineNum = 163;BA.debugLine="HomeJob.Initialize(\"Login\", Me)";
Debug.ShouldStop(4);
_homejob._initialize(processBA,"Login",main.getObject());
 BA.debugLineNum = 165;BA.debugLine="Try";
Debug.ShouldStop(16);
try { BA.debugLineNum = 166;BA.debugLine="HomeJob.Tag = \"Login\"";
Debug.ShouldStop(32);
_homejob._tag = (Object)("Login");Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 167;BA.debugLine="strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(14))";
Debug.ShouldStop(64);
_strenc = mostCurrent._fungsi._prosesenkripsi(mostCurrent.activityBA,_gen.ToPrettyString((int) (14)));Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 168;BA.debugLine="If strEnc = \"Enkripsi Failed\" Then Return";
Debug.ShouldStop(128);
if ((_strenc).equals("Enkripsi Failed")) { 
if (true) return "";};
 BA.debugLineNum = 169;BA.debugLine="HomeJob.PostString(Fungsi.mpList.Get (\"LoginURL\"), \"teks=\" & strEnc)";
Debug.ShouldStop(256);
_homejob._poststring(BA.ObjectToString(mostCurrent._fungsi._mplist.Get((Object)("LoginURL"))),"teks="+_strenc);
 BA.debugLineNum = 170;BA.debugLine="HomeJob.GetRequest.SetHeader(\"Cache-Control\", \"no-cache, must-revalidate\")";
Debug.ShouldStop(512);
_homejob._getrequest().SetHeader("Cache-Control","no-cache, must-revalidate");
 BA.debugLineNum = 171;BA.debugLine="HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60";
Debug.ShouldStop(1024);
_homejob._getrequest().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 } 
       catch (Exception e100) {
			processBA.setLastException(e100); BA.debugLineNum = 173;BA.debugLine="Msgbox2(\"Login Failure !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(4096);
anywheresoftware.b4a.keywords.Common.Msgbox2("Login Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 };
 BA.debugLineNum = 175;BA.debugLine="End Sub";
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

public static void initializeProcessGlobals() {
    if (mostCurrent != null && mostCurrent.activityBA != null) {
Debug.StartDebugging(mostCurrent.activityBA, 48641, new int[] {9, 27, 8, 8, 8, 21, 3, 2, 4, 7, 5, 22, 5, 4, 5, 2, 14, 2, 2, 1}, "ad35b4c5-deba-462a-ac5b-a57266bc5c3d");}

    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        b4a.example.dateutils._process_globals();
main._process_globals();
utama._process_globals();
content._process_globals();
register._process_globals();
menusearch._process_globals();
fungsi._process_globals();
profile._process_globals();
forgotpass._process_globals();
share._process_globals();
mncutils2service._process_globals();
pemutar._process_globals();
webview._process_globals();
play._process_globals();
result._process_globals();
date._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _globals() throws Exception{
 //BA.debugLineNum = 29;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 33;BA.debugLine="Private sfMain			As SplashFade";
mostCurrent._sfmain = new adr.splashfadelibrary.splashfade();
 //BA.debugLineNum = 34;BA.debugLine="Private ivLogin 		As ImageView";
mostCurrent._ivlogin = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private iv1 			As ImageView";
mostCurrent._iv1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private etEmail 		As EditText";
mostCurrent._etemail = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private iv2 			As ImageView";
mostCurrent._iv2 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private etPass 			As EditText";
mostCurrent._etpass = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private bSubmit 		As Button";
mostCurrent._bsubmit = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private iv3 			As ImageView";
mostCurrent._iv3 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private iv4 			As ImageView";
mostCurrent._iv4 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private ivBg			As ImageView";
mostCurrent._ivbg = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private BDLogin			As BetterDialogs";
mostCurrent._bdlogin = new flm.b4a.betterdialogs.BetterDialogs();
 //BA.debugLineNum = 44;BA.debugLine="Private ivLoginFB 		As ImageView";
mostCurrent._ivloginfb = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private ipack			As Int";
_ipack = 0;
 //BA.debugLineNum = 46;BA.debugLine="Private pm 				As PackageManager";
mostCurrent._pm = new anywheresoftware.b4a.phone.PackageManagerWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private ups 			As Intent";
mostCurrent._ups = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private ivUser 			As ImageView";
mostCurrent._ivuser = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private lblUser 		As Label";
mostCurrent._lbluser = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private lblNamaFilm 	As Label";
mostCurrent._lblnamafilm = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private user 			As Map";
mostCurrent._user = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 52;BA.debugLine="Private Pertama			As Boolean";
_pertama = false;
 //BA.debugLineNum = 53;BA.debugLine="Private Label1 			As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 54;BA.debugLine="End Sub";
return "";
}
public static String  _ivloginfb_click() throws Exception{
try {
		Debug.PushSubsStack("ivLoginFB_Click (main) ","main",0,mostCurrent.activityBA,mostCurrent,266);
anywheresoftware.b4a.phone.Phone.PhoneIntents _p = null;
 BA.debugLineNum = 266;BA.debugLine="Sub ivLoginFB_Click";
Debug.ShouldStop(512);
 BA.debugLineNum = 267;BA.debugLine="Dim p As PhoneIntents";
Debug.ShouldStop(1024);
_p = new anywheresoftware.b4a.phone.Phone.PhoneIntents();Debug.locals.put("p", _p);
 BA.debugLineNum = 268;BA.debugLine="StartActivity(p.OpenBrowser(\"http://www.facebook.com/\"))";
Debug.ShouldStop(2048);
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_p.OpenBrowser("http://www.facebook.com/")));
 BA.debugLineNum = 269;BA.debugLine="End Sub";
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
public static String  _jobdone(newmoviebay.mncplaymedia.com.mnchttpjob _mnchttp) throws Exception{
try {
		Debug.PushSubsStack("JobDone (main) ","main",0,mostCurrent.activityBA,mostCurrent,181);
adr.stringfunctions.stringfunctions _sf = null;
String _stmp = "";
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.Map _mresult = null;
byte[] _btmp = null;
anywheresoftware.b4a.agraham.byteconverter.ByteConverter _bctmp = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
anywheresoftware.b4a.objects.collections.List _test1 = null;
String _test2 = "";
anywheresoftware.b4a.objects.collections.Map _collogin = null;
Debug.locals.put("MNChttp", _mnchttp);
 BA.debugLineNum = 181;BA.debugLine="Sub JobDone (MNChttp As MNChttpJob)";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 182;BA.debugLine="Dim sf			As StringFunctions";
Debug.ShouldStop(2097152);
_sf = new adr.stringfunctions.stringfunctions();Debug.locals.put("sf", _sf);
 BA.debugLineNum = 183;BA.debugLine="Dim sTmp 		As String";
Debug.ShouldStop(4194304);
_stmp = "";Debug.locals.put("sTmp", _stmp);
 BA.debugLineNum = 184;BA.debugLine="Dim parser 		As JSONParser";
Debug.ShouldStop(8388608);
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();Debug.locals.put("parser", _parser);
 BA.debugLineNum = 185;BA.debugLine="Dim mresult		As Map";
Debug.ShouldStop(16777216);
_mresult = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("mresult", _mresult);
 BA.debugLineNum = 186;BA.debugLine="Dim bTmp()		As Byte";
Debug.ShouldStop(33554432);
_btmp = new byte[(int) (0)];
;Debug.locals.put("bTmp", _btmp);
 BA.debugLineNum = 187;BA.debugLine="Dim bcTmp		As ByteConverter";
Debug.ShouldStop(67108864);
_bctmp = new anywheresoftware.b4a.agraham.byteconverter.ByteConverter();Debug.locals.put("bcTmp", _bctmp);
 BA.debugLineNum = 188;BA.debugLine="Dim su 			As StringUtils";
Debug.ShouldStop(134217728);
_su = new anywheresoftware.b4a.objects.StringUtils();Debug.locals.put("su", _su);
 BA.debugLineNum = 192;BA.debugLine="If MNChttp.Success = False Then";
Debug.ShouldStop(-2147483648);
if (_mnchttp._success==anywheresoftware.b4a.keywords.Common.False) { 
 BA.debugLineNum = 193;BA.debugLine="If (sf.InString(MNChttp.ErrorMessage, \"timed out\") > 0) OR (sf.InString(MNChttp.ErrorMessage, \"refused\") > 0) OR _ 			 (sf.InString(MNChttp.ErrorMessage, \"Unable to resolve host\") > 0) OR (sf.InString(MNChttp.ErrorMessage, \"UnknownHostException\") > 0) OR _ 			 (sf.InString(MNChttp.ErrorMessage, \"FileNotFound\") > 0) Then";
Debug.ShouldStop(1);
if ((_sf._vvv4(_mnchttp._errormessage,"timed out")>0) || (_sf._vvv4(_mnchttp._errormessage,"refused")>0) || (_sf._vvv4(_mnchttp._errormessage,"Unable to resolve host")>0) || (_sf._vvv4(_mnchttp._errormessage,"UnknownHostException")>0) || (_sf._vvv4(_mnchttp._errormessage,"FileNotFound")>0)) { 
 BA.debugLineNum = 197;BA.debugLine="Msgbox2(\"Offline Connection or Server Down\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(16);
anywheresoftware.b4a.keywords.Common.Msgbox2("Offline Connection or Server Down","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 199;BA.debugLine="If sf.InString(MNChttp.ErrorMessage, \"Not Found\") < 0 Then";
Debug.ShouldStop(64);
if (_sf._vvv4(_mnchttp._errormessage,"Not Found")<0) { 
 BA.debugLineNum = 201;BA.debugLine="Msgbox2(\"Unknown Error\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(256);
anywheresoftware.b4a.keywords.Common.Msgbox2("Unknown Error","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 };
 };
 BA.debugLineNum = 205;BA.debugLine="Fungsi.mpList.Put(\"Koneksi\", \"Offline\")";
Debug.ShouldStop(4096);
mostCurrent._fungsi._mplist.Put((Object)("Koneksi"),(Object)("Offline"));
 BA.debugLineNum = 206;BA.debugLine="MNChttp.Release";
Debug.ShouldStop(8192);
_mnchttp._release();
 }else {
 BA.debugLineNum = 210;BA.debugLine="Try";
Debug.ShouldStop(131072);
try { BA.debugLineNum = 211;BA.debugLine="bTmp = su.DecodeBase64(MNChttp.GetString)";
Debug.ShouldStop(262144);
_btmp = _su.DecodeBase64(_mnchttp._getstring());Debug.locals.put("bTmp", _btmp);
 BA.debugLineNum = 212;BA.debugLine="sTmp = bcTmp.StringFromBytes(bTmp, \"UTF8\")";
Debug.ShouldStop(524288);
_stmp = _bctmp.StringFromBytes(_btmp,"UTF8");Debug.locals.put("sTmp", _stmp);
 BA.debugLineNum = 213;BA.debugLine="parser.Initialize(Fungsi.UnWrap(sTmp))";
Debug.ShouldStop(1048576);
_parser.Initialize(mostCurrent._fungsi._unwrap(mostCurrent.activityBA,_stmp));
 BA.debugLineNum = 214;BA.debugLine="MNChttp.Release";
Debug.ShouldStop(2097152);
_mnchttp._release();
 } 
       catch (Exception e131) {
			processBA.setLastException(e131); BA.debugLineNum = 216;BA.debugLine="Msgbox2(\"Process Failure !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(8388608);
anywheresoftware.b4a.keywords.Common.Msgbox2("Process Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 BA.debugLineNum = 217;BA.debugLine="Return";
Debug.ShouldStop(16777216);
if (true) return "";
 };
 BA.debugLineNum = 220;BA.debugLine="Try";
Debug.ShouldStop(134217728);
try { BA.debugLineNum = 221;BA.debugLine="mresult.Initialize";
Debug.ShouldStop(268435456);
_mresult.Initialize();
 BA.debugLineNum = 222;BA.debugLine="mresult = parser.NextObject";
Debug.ShouldStop(536870912);
_mresult = _parser.NextObject();Debug.locals.put("mresult", _mresult);
 } 
       catch (Exception e138) {
			processBA.setLastException(e138); BA.debugLineNum = 224;BA.debugLine="Msgbox2(\"Process Failure !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(-2147483648);
anywheresoftware.b4a.keywords.Common.Msgbox2("Process Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 BA.debugLineNum = 225;BA.debugLine="Return";
Debug.ShouldStop(1);
if (true) return "";
 };
 BA.debugLineNum = 228;BA.debugLine="If mresult.Get(\"Result\") <> \"0\" Then";
Debug.ShouldStop(8);
if ((_mresult.Get((Object)("Result"))).equals((Object)("0")) == false) { 
 BA.debugLineNum = 229;BA.debugLine="Msgbox2(mresult.Get(\"Message\"), \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(16);
anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToString(_mresult.Get((Object)("Message"))),"Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 231;BA.debugLine="Fungsi.mpList.Put(\"Koneksi\", \"Online\")";
Debug.ShouldStop(64);
mostCurrent._fungsi._mplist.Put((Object)("Koneksi"),(Object)("Online"));
 BA.debugLineNum = 232;BA.debugLine="MNChttp.Release";
Debug.ShouldStop(128);
_mnchttp._release();
 BA.debugLineNum = 234;BA.debugLine="Select MNChttp.JobName";
Debug.ShouldStop(512);
switch (BA.switchObjectToInt(_mnchttp._jobname,"Login")) {
case 0:
 BA.debugLineNum = 243;BA.debugLine="Dim Test1  As List";
Debug.ShouldStop(262144);
_test1 = new anywheresoftware.b4a.objects.collections.List();Debug.locals.put("Test1", _test1);
 BA.debugLineNum = 244;BA.debugLine="Dim Test2  As String";
Debug.ShouldStop(524288);
_test2 = "";Debug.locals.put("Test2", _test2);
 BA.debugLineNum = 247;BA.debugLine="Test1.Initialize";
Debug.ShouldStop(4194304);
_test1.Initialize();
 BA.debugLineNum = 248;BA.debugLine="Test1 = mresult.Get(\"Data\")";
Debug.ShouldStop(8388608);
_test1.setObject((java.util.List)(_mresult.Get((Object)("Data"))));
 BA.debugLineNum = 250;BA.debugLine="For Each colLogin As Map In Test1";
Debug.ShouldStop(33554432);
_collogin = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group152 = _test1;
final int groupLen152 = group152.getSize();
for (int index152 = 0;index152 < groupLen152 ;index152++){
_collogin.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group152.Get(index152)));
Debug.locals.put("collogin", _collogin);
 BA.debugLineNum = 251;BA.debugLine="Test2 = colLogin.Get(\"firstname\")";
Debug.ShouldStop(67108864);
_test2 = BA.ObjectToString(_collogin.Get((Object)("firstname")));Debug.locals.put("Test2", _test2);
 }
Debug.locals.put("collogin", _collogin);
;
 BA.debugLineNum = 254;BA.debugLine="Utama.damn = Test2";
Debug.ShouldStop(536870912);
mostCurrent._utama._damn = _test2;
 BA.debugLineNum = 256;BA.debugLine="Activity.Finish";
Debug.ShouldStop(-2147483648);
mostCurrent._activity.Finish();
 BA.debugLineNum = 257;BA.debugLine="StartActivity(Utama)";
Debug.ShouldStop(1);
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._utama.getObject()));
 break;
}
;
 };
 };
 BA.debugLineNum = 264;BA.debugLine="End Sub";
Debug.ShouldStop(128);
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
 //BA.debugLineNum = 23;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
public static String  _splash_complete() throws Exception{
try {
		Debug.PushSubsStack("Splash_Complete (main) ","main",0,mostCurrent.activityBA,mostCurrent,91);
anywheresoftware.b4a.objects.collections.List _paclist = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cdback = null;
 BA.debugLineNum = 91;BA.debugLine="Sub Splash_Complete";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 93;BA.debugLine="If File.Exists(File.DirAssets, \"config.json\") Then";
Debug.ShouldStop(268435456);
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"config.json")) { 
 BA.debugLineNum = 94;BA.debugLine="If Fungsi.CacahJSON(File.ReadString(File.DirAssets, \"config.json\"), \"NextArray\") = False Then";
Debug.ShouldStop(536870912);
if (mostCurrent._fungsi._cacahjson(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"config.json"),"NextArray")==anywheresoftware.b4a.keywords.Common.False) { 
 BA.debugLineNum = 95;BA.debugLine="BDLogin.Msgbox(\"Error\", \"Reading config failed !\", \"OK\", \"\", \"\", Fungsi.GetDrawable(\"ic_action_warning\"))";
Debug.ShouldStop(1073741824);
mostCurrent._bdlogin.MsgBox("Error","Reading config failed !","OK","","",mostCurrent._fungsi._getdrawable(mostCurrent.activityBA,"ic_action_warning"),mostCurrent.activityBA);
 BA.debugLineNum = 96;BA.debugLine="ExitApplication";
Debug.ShouldStop(-2147483648);
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 }else {
 BA.debugLineNum = 99;BA.debugLine="BDLogin.Msgbox(\"Error\", \"Load config failed !\", \"OK\", \"\", \"\", Fungsi.GetDrawable(\"ic_action_warning\"))";
Debug.ShouldStop(4);
mostCurrent._bdlogin.MsgBox("Error","Load config failed !","OK","","",mostCurrent._fungsi._getdrawable(mostCurrent.activityBA,"ic_action_warning"),mostCurrent.activityBA);
 BA.debugLineNum = 100;BA.debugLine="ExitApplication";
Debug.ShouldStop(8);
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 BA.debugLineNum = 105;BA.debugLine="If Pertama Then";
Debug.ShouldStop(256);
if (_pertama) { 
 BA.debugLineNum = 106;BA.debugLine="If File.Exists(Fungsi.DirDBS, \"nexplayersdklite.apk\") = False Then";
Debug.ShouldStop(512);
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._fungsi._dirdbs,"nexplayersdklite.apk")==anywheresoftware.b4a.keywords.Common.False) { 
 BA.debugLineNum = 107;BA.debugLine="ProgressDialogShow2(\"Please wait...\", False)";
Debug.ShouldStop(1024);
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,"Please wait...",anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 108;BA.debugLine="DoEvents";
Debug.ShouldStop(2048);
anywheresoftware.b4a.keywords.Common.DoEvents();
 BA.debugLineNum = 109;BA.debugLine="File.Copy(File.DirAssets, \"nexplayersdklite.apk\", Fungsi.DirDBS, \"nexplayersdklite.apk\")";
Debug.ShouldStop(4096);
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"nexplayersdklite.apk",mostCurrent._fungsi._dirdbs,"nexplayersdklite.apk");
 BA.debugLineNum = 110;BA.debugLine="ProgressDialogHide";
Debug.ShouldStop(8192);
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 BA.debugLineNum = 111;BA.debugLine="DoEvents";
Debug.ShouldStop(16384);
anywheresoftware.b4a.keywords.Common.DoEvents();
 };
 BA.debugLineNum = 114;BA.debugLine="Dim pacList As List = pm.GetInstalledPackages";
Debug.ShouldStop(131072);
_paclist = new anywheresoftware.b4a.objects.collections.List();
_paclist = mostCurrent._pm.GetInstalledPackages();Debug.locals.put("pacList", _paclist);Debug.locals.put("pacList", _paclist);
 BA.debugLineNum = 115;BA.debugLine="ipack = pacList.IndexOf(\"com.nexstreaming.app.nexplayersamplelitevm\")";
Debug.ShouldStop(262144);
_ipack = _paclist.IndexOf((Object)("com.nexstreaming.app.nexplayersamplelitevm"));
 BA.debugLineNum = 117;BA.debugLine="If ipack < 0 Then";
Debug.ShouldStop(1048576);
if (_ipack<0) { 
 BA.debugLineNum = 118;BA.debugLine="ups.Initialize(ups.ACTION_VIEW, \"file://\" & File.Combine(Fungsi.DirDBS, \"nexplayersdklitevm.apk\"))";
Debug.ShouldStop(2097152);
mostCurrent._ups.Initialize(mostCurrent._ups.ACTION_VIEW,"file://"+anywheresoftware.b4a.keywords.Common.File.Combine(mostCurrent._fungsi._dirdbs,"nexplayersdklitevm.apk"));
 BA.debugLineNum = 119;BA.debugLine="ups.SetType(\"application/vnd.android.package-archive\")";
Debug.ShouldStop(4194304);
mostCurrent._ups.SetType("application/vnd.android.package-archive");
 BA.debugLineNum = 120;BA.debugLine="StartActivity(ups)";
Debug.ShouldStop(8388608);
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._ups.getObject()));
 };
 };
 BA.debugLineNum = 126;BA.debugLine="Activity.LoadLayout(\"Login\")";
Debug.ShouldStop(536870912);
mostCurrent._activity.LoadLayout("Login",mostCurrent.activityBA);
 BA.debugLineNum = 130;BA.debugLine="Dim cdBack 	As ColorDrawable";
Debug.ShouldStop(2);
_cdback = new anywheresoftware.b4a.objects.drawable.ColorDrawable();Debug.locals.put("cdBack", _cdback);
 BA.debugLineNum = 131;BA.debugLine="cdBack.Initialize(Colors.RGB(132, 31, 40), 5)";
Debug.ShouldStop(4);
_cdback.Initialize(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (132),(int) (31),(int) (40)),(int) (5));
 BA.debugLineNum = 132;BA.debugLine="iv1.Background = Fungsi.GetDrawable(\"mail_icon\")";
Debug.ShouldStop(8);
mostCurrent._iv1.setBackground((android.graphics.drawable.Drawable)(mostCurrent._fungsi._getdrawable(mostCurrent.activityBA,"mail_icon")));
 BA.debugLineNum = 133;BA.debugLine="iv2.Background = cdBack";
Debug.ShouldStop(16);
mostCurrent._iv2.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 BA.debugLineNum = 134;BA.debugLine="iv3.Background = Fungsi.GetDrawable(\"password_icon\")";
Debug.ShouldStop(32);
mostCurrent._iv3.setBackground((android.graphics.drawable.Drawable)(mostCurrent._fungsi._getdrawable(mostCurrent.activityBA,"password_icon")));
 BA.debugLineNum = 135;BA.debugLine="iv4.Background = cdBack";
Debug.ShouldStop(64);
mostCurrent._iv4.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 BA.debugLineNum = 136;BA.debugLine="etEmail.Background = cdBack";
Debug.ShouldStop(128);
mostCurrent._etemail.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 BA.debugLineNum = 137;BA.debugLine="etPass.Background = cdBack";
Debug.ShouldStop(256);
mostCurrent._etpass.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 BA.debugLineNum = 138;BA.debugLine="ivBg.Background = Fungsi.GetDrawable(\"logo_login\")";
Debug.ShouldStop(512);
mostCurrent._ivbg.setBackground((android.graphics.drawable.Drawable)(mostCurrent._fungsi._getdrawable(mostCurrent.activityBA,"logo_login")));
 BA.debugLineNum = 143;BA.debugLine="End Sub";
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
}
