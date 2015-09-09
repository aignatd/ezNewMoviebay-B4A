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

public class pemutar extends Activity implements B4AActivity{
	public static pemutar mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "newmoviebay.mncplaymedia.com", "newmoviebay.mncplaymedia.com.pemutar");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (pemutar).");
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
		activityBA = new BA(this, layout, processBA, "newmoviebay.mncplaymedia.com", "newmoviebay.mncplaymedia.com.pemutar");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "newmoviebay.mncplaymedia.com.pemutar", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (pemutar) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (pemutar) Resume **");
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
		return pemutar.class;
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
        BA.LogInfo("** Activity (pemutar) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (pemutar) Resume **");
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
public static String _urlchannel = "";
public anywheresoftware.b4a.objects.PanelWrapper _pvideo = null;
public anywheresoftware.b4a.objects.Timer _tvideo = null;
public uk.co.martinpearman.b4a.vitamio.widget.MediaController _vimediacontrol = null;
public uk.co.martinpearman.b4a.vitamio.widget.VideoView _vivideoview = null;
public anywheresoftware.b4a.phone.Phone _pphone = null;
public anywheresoftware.b4a.phone.PhoneEvents _pe = null;
public static String _asas = "";
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
public newmoviebay.mncplaymedia.com.webview _webview = null;
public newmoviebay.mncplaymedia.com.play _play = null;
public newmoviebay.mncplaymedia.com.result _result = null;
public newmoviebay.mncplaymedia.com.date _date = null;
  public Object[] GetGlobals() {
		return new Object[] {"Activity",mostCurrent._activity,"asas",mostCurrent._asas,"Content",Debug.moduleToString(newmoviebay.mncplaymedia.com.content.class),"Date",Debug.moduleToString(newmoviebay.mncplaymedia.com.date.class),"DateUtils",mostCurrent._dateutils,"ForgotPass",Debug.moduleToString(newmoviebay.mncplaymedia.com.forgotpass.class),"Fungsi",Debug.moduleToString(newmoviebay.mncplaymedia.com.fungsi.class),"Main",Debug.moduleToString(newmoviebay.mncplaymedia.com.main.class),"MenuSearch",Debug.moduleToString(newmoviebay.mncplaymedia.com.menusearch.class),"MNCUtils2Service",Debug.moduleToString(newmoviebay.mncplaymedia.com.mncutils2service.class),"PE",mostCurrent._pe,"Play",Debug.moduleToString(newmoviebay.mncplaymedia.com.play.class),"pPhone",mostCurrent._pphone,"Profile",Debug.moduleToString(newmoviebay.mncplaymedia.com.profile.class),"pVideo",mostCurrent._pvideo,"Register",Debug.moduleToString(newmoviebay.mncplaymedia.com.register.class),"Result",Debug.moduleToString(newmoviebay.mncplaymedia.com.result.class),"Share",Debug.moduleToString(newmoviebay.mncplaymedia.com.share.class),"tVideo",mostCurrent._tvideo,"URLChannel",_urlchannel,"Utama",Debug.moduleToString(newmoviebay.mncplaymedia.com.utama.class),"ViMediaControl",mostCurrent._vimediacontrol,"ViVideoView",mostCurrent._vivideoview,"WebView",Debug.moduleToString(newmoviebay.mncplaymedia.com.webview.class)};
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
		Debug.PushSubsStack("Activity_Create (pemutar) ","pemutar",13,mostCurrent.activityBA,mostCurrent,26);
anywheresoftware.b4a.phone.Phone.PhoneId _phoneid = null;
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 26;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 30;BA.debugLine="Activity.LoadLayout(\"VideoPlay\")";
Debug.ShouldStop(536870912);
mostCurrent._activity.LoadLayout("VideoPlay",mostCurrent.activityBA);
 BA.debugLineNum = 33;BA.debugLine="ViVideoView.Initialize(\"ViVideoView\")";
Debug.ShouldStop(1);
mostCurrent._vivideoview.Initialize(mostCurrent.activityBA,"ViVideoView");
 BA.debugLineNum = 34;BA.debugLine="ViMediaControl.Initialize(\"ViMediaControl\")";
Debug.ShouldStop(2);
mostCurrent._vimediacontrol.Initialize(mostCurrent.activityBA,"ViMediaControl");
 BA.debugLineNum = 36;BA.debugLine="pPhone.SetScreenOrientation(0)";
Debug.ShouldStop(8);
mostCurrent._pphone.SetScreenOrientation(processBA,(int) (0));
 BA.debugLineNum = 41;BA.debugLine="Dim PhoneId As PhoneId";
Debug.ShouldStop(256);
_phoneid = new anywheresoftware.b4a.phone.Phone.PhoneId();Debug.locals.put("PhoneId", _phoneid);
 BA.debugLineNum = 42;BA.debugLine="PE.InitializeWithPhoneState(\"PE\",PhoneId)";
Debug.ShouldStop(512);
mostCurrent._pe.InitializeWithPhoneState(processBA,"PE",_phoneid);
 BA.debugLineNum = 44;BA.debugLine="pVideo.RemoveAllViews";
Debug.ShouldStop(2048);
mostCurrent._pvideo.RemoveAllViews();
 BA.debugLineNum = 45;BA.debugLine="pVideo.AddView(ViVideoView, 0, 0, pVideo.Width, pVideo.Height)";
Debug.ShouldStop(4096);
mostCurrent._pvideo.AddView((android.view.View)(mostCurrent._vivideoview.getObject()),(int) (0),(int) (0),mostCurrent._pvideo.getWidth(),mostCurrent._pvideo.getHeight());
 BA.debugLineNum = 47;BA.debugLine="ViVideoView.SetVideoPath(URLChannel)";
Debug.ShouldStop(16384);
mostCurrent._vivideoview.SetVideoPath(_urlchannel);
 BA.debugLineNum = 48;BA.debugLine="PlayVideo";
Debug.ShouldStop(32768);
_playvideo();
 BA.debugLineNum = 49;BA.debugLine="End Sub";
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
public static boolean  _activity_keypress(int _keycode) throws Exception{
try {
		Debug.PushSubsStack("Activity_KeyPress (pemutar) ","pemutar",13,mostCurrent.activityBA,mostCurrent,117);
Debug.locals.put("KeyCode", _keycode);
 BA.debugLineNum = 117;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean 'return true if you want to consume the event";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 118;BA.debugLine="If KeyCode = 4 Then ' Back Key";
Debug.ShouldStop(2097152);
if (_keycode==4) { 
 BA.debugLineNum = 119;BA.debugLine="Activity.Finish";
Debug.ShouldStop(4194304);
mostCurrent._activity.Finish();
 }else {
 BA.debugLineNum = 121;BA.debugLine="Return True";
Debug.ShouldStop(16777216);
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 BA.debugLineNum = 123;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
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
		Debug.PushSubsStack("Activity_Pause (pemutar) ","pemutar",13,mostCurrent.activityBA,mostCurrent,55);
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 55;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 57;BA.debugLine="End Sub";
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
		Debug.PushSubsStack("Activity_Resume (pemutar) ","pemutar",13,mostCurrent.activityBA,mostCurrent,51);
 BA.debugLineNum = 51;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(262144);
 BA.debugLineNum = 53;BA.debugLine="End Sub";
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
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Private pVideo 				As Panel";
mostCurrent._pvideo = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private tVideo				As Timer";
mostCurrent._tvideo = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 18;BA.debugLine="Private ViMediaControl		As Vitamio_MediaController";
mostCurrent._vimediacontrol = new uk.co.martinpearman.b4a.vitamio.widget.MediaController();
 //BA.debugLineNum = 19;BA.debugLine="Private ViVideoView 		As Vitamio_VideoView";
mostCurrent._vivideoview = new uk.co.martinpearman.b4a.vitamio.widget.VideoView();
 //BA.debugLineNum = 20;BA.debugLine="Private pPhone				As Phone";
mostCurrent._pphone = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 22;BA.debugLine="Private PE					As PhoneEvents";
mostCurrent._pe = new anywheresoftware.b4a.phone.PhoneEvents();
 //BA.debugLineNum = 23;BA.debugLine="Private asas				As String";
mostCurrent._asas = "";
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return "";
}
public static String  _playvideo() throws Exception{
try {
		Debug.PushSubsStack("PlayVideo (pemutar) ","pemutar",13,mostCurrent.activityBA,mostCurrent,59);
 BA.debugLineNum = 59;BA.debugLine="Sub PlayVideo";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 60;BA.debugLine="If ViVideoView.CheckVitamioLibs Then";
Debug.ShouldStop(134217728);
if (mostCurrent._vivideoview.CheckVitamioLibs(mostCurrent.activityBA)) { 
 BA.debugLineNum = 63;BA.debugLine="ViMediaControl.Show2(3000)";
Debug.ShouldStop(1073741824);
mostCurrent._vimediacontrol.Show2((int) (3000));
 BA.debugLineNum = 64;BA.debugLine="ViVideoView.SetMediaController(ViMediaControl)";
Debug.ShouldStop(-2147483648);
mostCurrent._vivideoview.SetMediaController((io.vov.vitamio.widget.MediaController)(mostCurrent._vimediacontrol.getObject()));
 BA.debugLineNum = 65;BA.debugLine="ViVideoView.SetVideoLayout(ViVideoView.VIDEO_LAYOUT_STRETCH, 0)";
Debug.ShouldStop(1);
mostCurrent._vivideoview.SetVideoLayout(mostCurrent._vivideoview.VIDEO_LAYOUT_STRETCH,(float) (0));
 BA.debugLineNum = 66;BA.debugLine="ViVideoView.Start";
Debug.ShouldStop(2);
mostCurrent._vivideoview.Start();
 BA.debugLineNum = 68;BA.debugLine="tVideo.Initialize(\"tVideo\", 45000)";
Debug.ShouldStop(8);
Debug.DebugWarningEngine.CheckInitialize(mostCurrent._tvideo);mostCurrent._tvideo.Initialize(processBA,"tVideo",(long) (45000));
 BA.debugLineNum = 69;BA.debugLine="tVideo.Enabled = True";
Debug.ShouldStop(16);
mostCurrent._tvideo.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 BA.debugLineNum = 71;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 9;BA.debugLine="Public URLChannel	As String";
_urlchannel = "";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _tvideo_tick() throws Exception{
try {
		Debug.PushSubsStack("tVideo_Tick (pemutar) ","pemutar",13,mostCurrent.activityBA,mostCurrent,73);
 BA.debugLineNum = 73;BA.debugLine="Sub tVideo_Tick";
Debug.ShouldStop(256);
 BA.debugLineNum = 74;BA.debugLine="tVideo.Enabled = False";
Debug.ShouldStop(512);
mostCurrent._tvideo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 75;BA.debugLine="End Sub";
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
public static String  _vimediacontrol_hidden() throws Exception{
try {
		Debug.PushSubsStack("ViMediaControl_Hidden (pemutar) ","pemutar",13,mostCurrent.activityBA,mostCurrent,77);
 BA.debugLineNum = 77;BA.debugLine="Sub ViMediaControl_Hidden";
Debug.ShouldStop(4096);
 BA.debugLineNum = 78;BA.debugLine="tVideo.Enabled = False";
Debug.ShouldStop(8192);
mostCurrent._tvideo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 79;BA.debugLine="End Sub";
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
public static String  _vimediacontrol_shown() throws Exception{
try {
		Debug.PushSubsStack("ViMediaControl_Shown (pemutar) ","pemutar",13,mostCurrent.activityBA,mostCurrent,81);
 BA.debugLineNum = 81;BA.debugLine="Sub ViMediaControl_Shown";
Debug.ShouldStop(65536);
 BA.debugLineNum = 82;BA.debugLine="tVideo.Enabled = False";
Debug.ShouldStop(131072);
mostCurrent._tvideo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 83;BA.debugLine="End Sub";
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
public static boolean  _vitavideoview_info(int _what,int _extra) throws Exception{
try {
		Debug.PushSubsStack("VitaVideoView_Info (pemutar) ","pemutar",13,mostCurrent.activityBA,mostCurrent,102);
Debug.locals.put("What", _what);
Debug.locals.put("Extra", _extra);
 BA.debugLineNum = 102;BA.debugLine="Sub VitaVideoView_Info(What As Int, Extra As Int) As Boolean";
Debug.ShouldStop(32);
 BA.debugLineNum = 103;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return false;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static String  _vivideoview_bitmapsubtitleupdated(byte[] _bitmap1,int _width,int _height) throws Exception{
try {
		Debug.PushSubsStack("ViVideoView_BitmapSubtitleUpdated (pemutar) ","pemutar",13,mostCurrent.activityBA,mostCurrent,85);
Debug.locals.put("Bitmap1", _bitmap1);
Debug.locals.put("Width", _width);
Debug.locals.put("Height", _height);
 BA.debugLineNum = 85;BA.debugLine="Sub ViVideoView_BitmapSubtitleUpdated(Bitmap1() As Byte, Width As Int, Height As Int)";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 86;BA.debugLine="End Sub";
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
public static String  _vivideoview_buffering(int _percent) throws Exception{
try {
		Debug.PushSubsStack("ViVideoView_Buffering (pemutar) ","pemutar",13,mostCurrent.activityBA,mostCurrent,88);
Debug.locals.put("Percent", _percent);
 BA.debugLineNum = 88;BA.debugLine="Sub ViVideoView_Buffering(Percent As Int)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 89;BA.debugLine="tVideo.Enabled = False";
Debug.ShouldStop(16777216);
mostCurrent._tvideo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 90;BA.debugLine="End Sub";
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
public static String  _vivideoview_complete() throws Exception{
try {
		Debug.PushSubsStack("ViVideoView_Complete (pemutar) ","pemutar",13,mostCurrent.activityBA,mostCurrent,92);
 BA.debugLineNum = 92;BA.debugLine="Sub ViVideoView_Complete";
Debug.ShouldStop(134217728);
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
public static boolean  _vivideoview_error(int _media_error) throws Exception{
try {
		Debug.PushSubsStack("ViVideoView_Error (pemutar) ","pemutar",13,mostCurrent.activityBA,mostCurrent,95);
Debug.locals.put("MEDIA_ERROR", _media_error);
 BA.debugLineNum = 95;BA.debugLine="Sub ViVideoView_Error(MEDIA_ERROR As Int) As Boolean";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 96;BA.debugLine="tVideo.Enabled = False";
Debug.ShouldStop(-2147483648);
mostCurrent._tvideo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 97;BA.debugLine="ViVideoView.StopPlayback";
Debug.ShouldStop(1);
mostCurrent._vivideoview.StopPlayback();
 BA.debugLineNum = 98;BA.debugLine="Msgbox2(\"Code of error : \" & MEDIA_ERROR, \"Warning\" , \"OK\" , \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(2);
anywheresoftware.b4a.keywords.Common.Msgbox2("Code of error : "+BA.NumberToString(_media_error),"Warning","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 BA.debugLineNum = 99;BA.debugLine="Activity.Finish";
Debug.ShouldStop(4);
mostCurrent._activity.Finish();
 BA.debugLineNum = 100;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return false;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static String  _vivideoview_prepared() throws Exception{
try {
		Debug.PushSubsStack("ViVideoView_Prepared (pemutar) ","pemutar",13,mostCurrent.activityBA,mostCurrent,105);
 BA.debugLineNum = 105;BA.debugLine="Sub ViVideoView_Prepared";
Debug.ShouldStop(256);
 BA.debugLineNum = 106;BA.debugLine="tVideo.Enabled = False";
Debug.ShouldStop(512);
mostCurrent._tvideo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 107;BA.debugLine="End Sub";
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
public static String  _vivideoview_seekcomplete() throws Exception{
try {
		Debug.PushSubsStack("ViVideoView_SeekComplete (pemutar) ","pemutar",13,mostCurrent.activityBA,mostCurrent,109);
 BA.debugLineNum = 109;BA.debugLine="Sub ViVideoView_SeekComplete";
Debug.ShouldStop(4096);
 BA.debugLineNum = 111;BA.debugLine="End Sub";
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
public static String  _vivideoview_textsubtitleupdated(String _subtext) throws Exception{
try {
		Debug.PushSubsStack("ViVideoView_TextSubtitleUpdated (pemutar) ","pemutar",13,mostCurrent.activityBA,mostCurrent,113);
Debug.locals.put("SubText", _subtext);
 BA.debugLineNum = 113;BA.debugLine="Sub ViVideoView_TextSubtitleUpdated(SubText As String)";
Debug.ShouldStop(65536);
 BA.debugLineNum = 115;BA.debugLine="End Sub";
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
}
