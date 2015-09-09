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

public class menusearch extends Activity implements B4AActivity{
	public static menusearch mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "newmoviebay.mncplaymedia.com", "newmoviebay.mncplaymedia.com.menusearch");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (menusearch).");
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
		activityBA = new BA(this, layout, processBA, "newmoviebay.mncplaymedia.com", "newmoviebay.mncplaymedia.com.menusearch");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "newmoviebay.mncplaymedia.com.menusearch", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (menusearch) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (menusearch) Resume **");
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
		return menusearch.class;
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
        BA.LogInfo("** Activity (menusearch) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (menusearch) Resume **");
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
public static Object _index = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lvsearch = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivclose = null;
public b4a.example.dateutils _dateutils = null;
public newmoviebay.mncplaymedia.com.main _main = null;
public newmoviebay.mncplaymedia.com.utama _utama = null;
public newmoviebay.mncplaymedia.com.content _content = null;
public newmoviebay.mncplaymedia.com.register _register = null;
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
		return new Object[] {"Activity",mostCurrent._activity,"Content",Debug.moduleToString(newmoviebay.mncplaymedia.com.content.class),"Date",Debug.moduleToString(newmoviebay.mncplaymedia.com.date.class),"DateUtils",mostCurrent._dateutils,"ForgotPass",Debug.moduleToString(newmoviebay.mncplaymedia.com.forgotpass.class),"Fungsi",Debug.moduleToString(newmoviebay.mncplaymedia.com.fungsi.class),"index",_index,"ivClose",mostCurrent._ivclose,"lvSearch",mostCurrent._lvsearch,"Main",Debug.moduleToString(newmoviebay.mncplaymedia.com.main.class),"MNCUtils2Service",Debug.moduleToString(newmoviebay.mncplaymedia.com.mncutils2service.class),"Pemutar",Debug.moduleToString(newmoviebay.mncplaymedia.com.pemutar.class),"Play",Debug.moduleToString(newmoviebay.mncplaymedia.com.play.class),"Profile",Debug.moduleToString(newmoviebay.mncplaymedia.com.profile.class),"Register",Debug.moduleToString(newmoviebay.mncplaymedia.com.register.class),"Result",Debug.moduleToString(newmoviebay.mncplaymedia.com.result.class),"Share",Debug.moduleToString(newmoviebay.mncplaymedia.com.share.class),"Utama",Debug.moduleToString(newmoviebay.mncplaymedia.com.utama.class),"WebView",Debug.moduleToString(newmoviebay.mncplaymedia.com.webview.class)};
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
		Debug.PushSubsStack("Activity_Create (menusearch) ","menusearch",4,mostCurrent.activityBA,mostCurrent,20);
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 20;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(524288);
 BA.debugLineNum = 22;BA.debugLine="Activity.LoadLayout(\"Search\")";
Debug.ShouldStop(2097152);
mostCurrent._activity.LoadLayout("Search",mostCurrent.activityBA);
 BA.debugLineNum = 24;BA.debugLine="init_Search";
Debug.ShouldStop(8388608);
_init_search();
 BA.debugLineNum = 25;BA.debugLine="DoEvents";
Debug.ShouldStop(16777216);
anywheresoftware.b4a.keywords.Common.DoEvents();
 BA.debugLineNum = 40;BA.debugLine="End Sub";
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
public static String  _activity_pause(boolean _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (menusearch) ","menusearch",4,mostCurrent.activityBA,mostCurrent,50);
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 50;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(131072);
 BA.debugLineNum = 52;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
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
		Debug.PushSubsStack("Activity_Resume (menusearch) ","menusearch",4,mostCurrent.activityBA,mostCurrent,46);
 BA.debugLineNum = 46;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(8192);
 BA.debugLineNum = 48;BA.debugLine="End Sub";
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
public static String  _globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Private lvSearch As ListView";
mostCurrent._lvsearch = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private ivClose  As ImageView";
mostCurrent._ivclose = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
public static String  _init_menu() throws Exception{
try {
		Debug.PushSubsStack("init_menu (menusearch) ","menusearch",4,mostCurrent.activityBA,mostCurrent,53);
anywheresoftware.b4a.keywords.constants.TypefaceWrapper _timefont = null;
 BA.debugLineNum = 53;BA.debugLine="Sub init_menu";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 55;BA.debugLine="Dim TimeFont As Typeface";
Debug.ShouldStop(4194304);
_timefont = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();Debug.locals.put("TimeFont", _timefont);
 BA.debugLineNum = 56;BA.debugLine="TimeFont = Typeface.LoadFromAssets (\"gotham-light.ttf\")";
Debug.ShouldStop(8388608);
_timefont.setObject((android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("gotham-light.ttf")));
 BA.debugLineNum = 58;BA.debugLine="lvSearch.Clear";
Debug.ShouldStop(33554432);
mostCurrent._lvsearch.Clear();
 BA.debugLineNum = 59;BA.debugLine="Fungsi.SetDivider(lvSearch, Colors.Transparent, 1dip)";
Debug.ShouldStop(67108864);
mostCurrent._fungsi._setdivider(mostCurrent.activityBA,mostCurrent._lvsearch,anywheresoftware.b4a.keywords.Common.Colors.Transparent,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)));
 BA.debugLineNum = 61;BA.debugLine="lvSearch.FastScrollEnabled = True";
Debug.ShouldStop(268435456);
mostCurrent._lvsearch.setFastScrollEnabled(anywheresoftware.b4a.keywords.Common.True);
 BA.debugLineNum = 62;BA.debugLine="lvSearch.TwoLinesLayout.SecondLabel.TextColor = Colors.Magenta";
Debug.ShouldStop(536870912);
mostCurrent._lvsearch.getTwoLinesLayout().SecondLabel.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 BA.debugLineNum = 64;BA.debugLine="lvSearch.AddTwoLinesAndBitmap(\"New Release\", \"\", Null)";
Debug.ShouldStop(-2147483648);
mostCurrent._lvsearch.AddTwoLinesAndBitmap("New Release","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 BA.debugLineNum = 79;BA.debugLine="lvSearch.TwoLinesAndBitmap.SecondLabel.Visible = False";
Debug.ShouldStop(16384);
mostCurrent._lvsearch.getTwoLinesAndBitmap().SecondLabel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 80;BA.debugLine="lvSearch.TwoLinesAndBitmap.ImageView.Gravity = Gravity.CENTER";
Debug.ShouldStop(32768);
mostCurrent._lvsearch.getTwoLinesAndBitmap().ImageView.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 BA.debugLineNum = 83;BA.debugLine="lvSearch.TwoLinesAndBitmap.Label.Typeface = TimeFont";
Debug.ShouldStop(262144);
mostCurrent._lvsearch.getTwoLinesAndBitmap().Label.setTypeface((android.graphics.Typeface)(_timefont.getObject()));
 BA.debugLineNum = 84;BA.debugLine="lvSearch.TwoLinesAndBitmap.Label.TextColor = Colors.White";
Debug.ShouldStop(524288);
mostCurrent._lvsearch.getTwoLinesAndBitmap().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 BA.debugLineNum = 91;BA.debugLine="End Sub";
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
public static String  _init_search() throws Exception{
try {
		Debug.PushSubsStack("init_Search (menusearch) ","menusearch",4,mostCurrent.activityBA,mostCurrent,122);
newmoviebay.mncplaymedia.com.mnchttpjob _homejob = null;
String _strenc = "";
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _gen = null;
anywheresoftware.b4a.objects.collections.Map _mcate = null;
 BA.debugLineNum = 122;BA.debugLine="Sub init_Search";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 124;BA.debugLine="Dim HomeJob As MNChttpJob";
Debug.ShouldStop(134217728);
_homejob = new newmoviebay.mncplaymedia.com.mnchttpjob();Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 125;BA.debugLine="Dim	strEnc	As String";
Debug.ShouldStop(268435456);
_strenc = "";Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 126;BA.debugLine="Dim Gen		As JSONGenerator";
Debug.ShouldStop(536870912);
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();Debug.locals.put("Gen", _gen);
 BA.debugLineNum = 127;BA.debugLine="Dim mcate	As Map";
Debug.ShouldStop(1073741824);
_mcate = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("mcate", _mcate);
 BA.debugLineNum = 129;BA.debugLine="mcate.Initialize";
Debug.ShouldStop(1);
_mcate.Initialize();
 BA.debugLineNum = 130;BA.debugLine="mcate.Clear";
Debug.ShouldStop(2);
_mcate.Clear();
 BA.debugLineNum = 133;BA.debugLine="Gen.Initialize(mcate)";
Debug.ShouldStop(16);
_gen.Initialize(_mcate);
 BA.debugLineNum = 135;BA.debugLine="HomeJob.Initialize(\"SearchPage\", Me)";
Debug.ShouldStop(64);
_homejob._initialize(processBA,"SearchPage",menusearch.getObject());
 BA.debugLineNum = 137;BA.debugLine="Try";
Debug.ShouldStop(256);
try { BA.debugLineNum = 138;BA.debugLine="Log(Fungsi.mpList.Get(\"SearchURL\"))";
Debug.ShouldStop(512);
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(mostCurrent._fungsi._mplist.Get((Object)("SearchURL"))));
 BA.debugLineNum = 139;BA.debugLine="HomeJob.Tag = \"SearchPage\"";
Debug.ShouldStop(1024);
_homejob._tag = (Object)("SearchPage");Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 140;BA.debugLine="strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(2))";
Debug.ShouldStop(2048);
_strenc = mostCurrent._fungsi._prosesenkripsi(mostCurrent.activityBA,_gen.ToPrettyString((int) (2)));Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 141;BA.debugLine="If strEnc = \"Enkripsi Failed\" Then Return";
Debug.ShouldStop(4096);
if ((_strenc).equals("Enkripsi Failed")) { 
if (true) return "";};
 BA.debugLineNum = 143;BA.debugLine="HomeJob.PostString(Fungsi.mpList.Get (\"SearchURL\"), \"teks=\" & strEnc)";
Debug.ShouldStop(16384);
_homejob._poststring(BA.ObjectToString(mostCurrent._fungsi._mplist.Get((Object)("SearchURL"))),"teks="+_strenc);
 BA.debugLineNum = 144;BA.debugLine="HomeJob.GetRequest.SetHeader(\"Cache-Control\", \"no-cache, must-revalidate\")";
Debug.ShouldStop(32768);
_homejob._getrequest().SetHeader("Cache-Control","no-cache, must-revalidate");
 BA.debugLineNum = 145;BA.debugLine="HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60";
Debug.ShouldStop(65536);
_homejob._getrequest().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 } 
       catch (Exception e72) {
			processBA.setLastException(e72); BA.debugLineNum = 147;BA.debugLine="Msgbox2(\"Cetgory Failure !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(262144);
anywheresoftware.b4a.keywords.Common.Msgbox2("Cetgory Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 };
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
public static String  _ivclose_click() throws Exception{
try {
		Debug.PushSubsStack("ivClose_Click (menusearch) ","menusearch",4,mostCurrent.activityBA,mostCurrent,243);
 BA.debugLineNum = 243;BA.debugLine="Sub ivClose_Click";
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
public static String  _jobdone(newmoviebay.mncplaymedia.com.mnchttpjob _mnchttp) throws Exception{
try {
		Debug.PushSubsStack("JobDone (menusearch) ","menusearch",4,mostCurrent.activityBA,mostCurrent,152);
adr.stringfunctions.stringfunctions _sf = null;
String _stmp = "";
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.Map _mresult = null;
byte[] _btmp = null;
anywheresoftware.b4a.agraham.byteconverter.ByteConverter _bctmp = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
anywheresoftware.b4a.objects.collections.List _cari1 = null;
anywheresoftware.b4a.objects.collections.List _cari2 = null;
anywheresoftware.b4a.objects.collections.Map _colcategory = null;
anywheresoftware.b4a.keywords.constants.TypefaceWrapper _timefont1 = null;
Debug.locals.put("MNChttp", _mnchttp);
 BA.debugLineNum = 152;BA.debugLine="Sub JobDone (MNChttp As MNChttpJob)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 154;BA.debugLine="Dim sf			As StringFunctions";
Debug.ShouldStop(33554432);
_sf = new adr.stringfunctions.stringfunctions();Debug.locals.put("sf", _sf);
 BA.debugLineNum = 155;BA.debugLine="Dim sTmp 		As String";
Debug.ShouldStop(67108864);
_stmp = "";Debug.locals.put("sTmp", _stmp);
 BA.debugLineNum = 156;BA.debugLine="Dim parser 		As JSONParser";
Debug.ShouldStop(134217728);
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();Debug.locals.put("parser", _parser);
 BA.debugLineNum = 157;BA.debugLine="Dim mresult		As Map";
Debug.ShouldStop(268435456);
_mresult = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("mresult", _mresult);
 BA.debugLineNum = 158;BA.debugLine="Dim bTmp()		As Byte";
Debug.ShouldStop(536870912);
_btmp = new byte[(int) (0)];
;Debug.locals.put("bTmp", _btmp);
 BA.debugLineNum = 159;BA.debugLine="Dim bcTmp		As ByteConverter";
Debug.ShouldStop(1073741824);
_bctmp = new anywheresoftware.b4a.agraham.byteconverter.ByteConverter();Debug.locals.put("bcTmp", _bctmp);
 BA.debugLineNum = 160;BA.debugLine="Dim su 			As StringUtils";
Debug.ShouldStop(-2147483648);
_su = new anywheresoftware.b4a.objects.StringUtils();Debug.locals.put("su", _su);
 BA.debugLineNum = 164;BA.debugLine="If MNChttp.Success = False Then";
Debug.ShouldStop(8);
if (_mnchttp._success==anywheresoftware.b4a.keywords.Common.False) { 
 BA.debugLineNum = 165;BA.debugLine="If (sf.InString(MNChttp.ErrorMessage, \"timed out\") > 0) OR (sf.InString(MNChttp.ErrorMessage, \"refused\") > 0) OR _ 			 (sf.InString(MNChttp.ErrorMessage, \"Unable to resolve host\") > 0) OR (sf.InString(MNChttp.ErrorMessage, \"UnknownHostException\") > 0) OR _ 			 (sf.InString(MNChttp.ErrorMessage, \"FileNotFound\") > 0) Then";
Debug.ShouldStop(16);
if ((_sf._vvv4(_mnchttp._errormessage,"timed out")>0) || (_sf._vvv4(_mnchttp._errormessage,"refused")>0) || (_sf._vvv4(_mnchttp._errormessage,"Unable to resolve host")>0) || (_sf._vvv4(_mnchttp._errormessage,"UnknownHostException")>0) || (_sf._vvv4(_mnchttp._errormessage,"FileNotFound")>0)) { 
 BA.debugLineNum = 169;BA.debugLine="Msgbox2(\"Offline Connection or Server Down\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(256);
anywheresoftware.b4a.keywords.Common.Msgbox2("Offline Connection or Server Down","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 171;BA.debugLine="If sf.InString(MNChttp.ErrorMessage, \"Not Found\") < 0 Then";
Debug.ShouldStop(1024);
if (_sf._vvv4(_mnchttp._errormessage,"Not Found")<0) { 
 BA.debugLineNum = 173;BA.debugLine="Msgbox2(\"Unknown Error\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(4096);
anywheresoftware.b4a.keywords.Common.Msgbox2("Unknown Error","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 };
 };
 BA.debugLineNum = 177;BA.debugLine="Fungsi.mpList.Put(\"Koneksi\", \"Offline\")";
Debug.ShouldStop(65536);
mostCurrent._fungsi._mplist.Put((Object)("Koneksi"),(Object)("Offline"));
 BA.debugLineNum = 178;BA.debugLine="MNChttp.Release";
Debug.ShouldStop(131072);
_mnchttp._release();
 }else {
 BA.debugLineNum = 182;BA.debugLine="Try";
Debug.ShouldStop(2097152);
try { BA.debugLineNum = 183;BA.debugLine="bTmp = su.DecodeBase64(MNChttp.GetString)";
Debug.ShouldStop(4194304);
_btmp = _su.DecodeBase64(_mnchttp._getstring());Debug.locals.put("bTmp", _btmp);
 BA.debugLineNum = 184;BA.debugLine="sTmp = bcTmp.StringFromBytes(bTmp, \"UTF8\")";
Debug.ShouldStop(8388608);
_stmp = _bctmp.StringFromBytes(_btmp,"UTF8");Debug.locals.put("sTmp", _stmp);
 BA.debugLineNum = 185;BA.debugLine="parser.Initialize(Fungsi.UnWrap(sTmp))";
Debug.ShouldStop(16777216);
_parser.Initialize(mostCurrent._fungsi._unwrap(mostCurrent.activityBA,_stmp));
 BA.debugLineNum = 186;BA.debugLine="MNChttp.Release";
Debug.ShouldStop(33554432);
_mnchttp._release();
 } 
       catch (Exception e100) {
			processBA.setLastException(e100); BA.debugLineNum = 188;BA.debugLine="Msgbox2(\"Process Failure !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(134217728);
anywheresoftware.b4a.keywords.Common.Msgbox2("Process Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 BA.debugLineNum = 189;BA.debugLine="Return";
Debug.ShouldStop(268435456);
if (true) return "";
 };
 BA.debugLineNum = 192;BA.debugLine="Try";
Debug.ShouldStop(-2147483648);
try { BA.debugLineNum = 193;BA.debugLine="mresult.Initialize";
Debug.ShouldStop(1);
_mresult.Initialize();
 BA.debugLineNum = 194;BA.debugLine="mresult = parser.NextObject";
Debug.ShouldStop(2);
_mresult = _parser.NextObject();Debug.locals.put("mresult", _mresult);
 } 
       catch (Exception e107) {
			processBA.setLastException(e107); BA.debugLineNum = 196;BA.debugLine="Msgbox2(\"Process Failure !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(8);
anywheresoftware.b4a.keywords.Common.Msgbox2("Process Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 BA.debugLineNum = 197;BA.debugLine="Return";
Debug.ShouldStop(16);
if (true) return "";
 };
 BA.debugLineNum = 200;BA.debugLine="If mresult.Get(\"Result\") <> \"0\" Then";
Debug.ShouldStop(128);
if ((_mresult.Get((Object)("Result"))).equals((Object)("0")) == false) { 
 BA.debugLineNum = 201;BA.debugLine="Msgbox2(mresult.Get(\"Message\"), \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(256);
anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToString(_mresult.Get((Object)("Message"))),"Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 203;BA.debugLine="Fungsi.mpList.Put(\"Koneksi\", \"Online\")";
Debug.ShouldStop(1024);
mostCurrent._fungsi._mplist.Put((Object)("Koneksi"),(Object)("Online"));
 BA.debugLineNum = 204;BA.debugLine="MNChttp.Release";
Debug.ShouldStop(2048);
_mnchttp._release();
 BA.debugLineNum = 206;BA.debugLine="Select MNChttp.JobName";
Debug.ShouldStop(8192);
switch (BA.switchObjectToInt(_mnchttp._jobname,"SearchPage")) {
case 0:
 BA.debugLineNum = 211;BA.debugLine="Dim cari1,cari2 As List";
Debug.ShouldStop(262144);
_cari1 = new anywheresoftware.b4a.objects.collections.List();Debug.locals.put("cari1", _cari1);
_cari2 = new anywheresoftware.b4a.objects.collections.List();Debug.locals.put("cari2", _cari2);
 BA.debugLineNum = 212;BA.debugLine="lvSearch.Clear";
Debug.ShouldStop(524288);
mostCurrent._lvsearch.Clear();
 BA.debugLineNum = 214;BA.debugLine="Log(cari1)";
Debug.ShouldStop(2097152);
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(_cari1));
 BA.debugLineNum = 215;BA.debugLine="cari1.Initialize";
Debug.ShouldStop(4194304);
_cari1.Initialize();
 BA.debugLineNum = 216;BA.debugLine="cari1 = mresult.Get(\"Data\")";
Debug.ShouldStop(8388608);
_cari1.setObject((java.util.List)(_mresult.Get((Object)("Data"))));
 BA.debugLineNum = 218;BA.debugLine="cari2.Initialize";
Debug.ShouldStop(33554432);
_cari2.Initialize();
 BA.debugLineNum = 220;BA.debugLine="For Each colCategory As Map In cari1";
Debug.ShouldStop(134217728);
_colcategory = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group123 = _cari1;
final int groupLen123 = group123.getSize();
for (int index123 = 0;index123 < groupLen123 ;index123++){
_colcategory.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group123.Get(index123)));
Debug.locals.put("colcategory", _colcategory);
 BA.debugLineNum = 221;BA.debugLine="lvSearch.AddSingleLine(colCategory.Get(\"title\"))";
Debug.ShouldStop(268435456);
mostCurrent._lvsearch.AddSingleLine(BA.ObjectToString(_colcategory.Get((Object)("title"))));
 }
Debug.locals.put("colcategory", _colcategory);
;
 BA.debugLineNum = 224;BA.debugLine="Dim TimeFont1 As Typeface";
Debug.ShouldStop(-2147483648);
_timefont1 = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();Debug.locals.put("TimeFont1", _timefont1);
 BA.debugLineNum = 227;BA.debugLine="lvSearch.SingleLineLayout.Label.Gravity = Gravity.LEFT";
Debug.ShouldStop(4);
mostCurrent._lvsearch.getSingleLineLayout().Label.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.LEFT);
 BA.debugLineNum = 230;BA.debugLine="lvSearch.FastScrollEnabled = False";
Debug.ShouldStop(32);
mostCurrent._lvsearch.setFastScrollEnabled(anywheresoftware.b4a.keywords.Common.False);
 break;
}
;
 };
 };
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
public static String  _lvsearch_itemclick(int _position,Object _value) throws Exception{
try {
		Debug.PushSubsStack("lvSearch_ItemClick (menusearch) ","menusearch",4,mostCurrent.activityBA,mostCurrent,92);
newmoviebay.mncplaymedia.com.mnchttpjob _homejob = null;
String _strenc = "";
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _gen = null;
anywheresoftware.b4a.objects.collections.Map _mpage = null;
String _coba = "";
String[] _dipisah = null;
String _asli = "";
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 92;BA.debugLine="Sub lvSearch_ItemClick (Position As Int, Value As Object)";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 94;BA.debugLine="Dim HomeJob As MNChttpJob";
Debug.ShouldStop(536870912);
_homejob = new newmoviebay.mncplaymedia.com.mnchttpjob();Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 95;BA.debugLine="Dim	strEnc	As String";
Debug.ShouldStop(1073741824);
_strenc = "";Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 96;BA.debugLine="Dim Gen		As JSONGenerator";
Debug.ShouldStop(-2147483648);
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();Debug.locals.put("Gen", _gen);
 BA.debugLineNum = 97;BA.debugLine="Dim mPage	As Map";
Debug.ShouldStop(1);
_mpage = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("mPage", _mpage);
 BA.debugLineNum = 98;BA.debugLine="Dim coba 	As String=Value";
Debug.ShouldStop(2);
_coba = BA.ObjectToString(_value);Debug.locals.put("coba", _coba);Debug.locals.put("coba", _coba);
 BA.debugLineNum = 99;BA.debugLine="Dim dipisah()   As String";
Debug.ShouldStop(4);
_dipisah = new String[(int) (0)];
java.util.Arrays.fill(_dipisah,"");Debug.locals.put("dipisah", _dipisah);
 BA.debugLineNum = 100;BA.debugLine="Dim asli 		As String = Value";
Debug.ShouldStop(8);
_asli = BA.ObjectToString(_value);Debug.locals.put("asli", _asli);Debug.locals.put("asli", _asli);
 BA.debugLineNum = 104;BA.debugLine="mPage.Initialize";
Debug.ShouldStop(128);
_mpage.Initialize();
 BA.debugLineNum = 105;BA.debugLine="mPage.Clear";
Debug.ShouldStop(256);
_mpage.Clear();
 BA.debugLineNum = 107;BA.debugLine="Gen.Initialize(mPage)";
Debug.ShouldStop(1024);
_gen.Initialize(_mpage);
 BA.debugLineNum = 108;BA.debugLine="HomeJob.Initialize(\"SearchPage\", Me)";
Debug.ShouldStop(2048);
_homejob._initialize(processBA,"SearchPage",menusearch.getObject());
 BA.debugLineNum = 110;BA.debugLine="Try";
Debug.ShouldStop(8192);
try { BA.debugLineNum = 112;BA.debugLine="HomeJob.Tag = Position '\"SearchPage\"";
Debug.ShouldStop(32768);
_homejob._tag = (Object)(_position);Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 113;BA.debugLine="strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(2))";
Debug.ShouldStop(65536);
_strenc = mostCurrent._fungsi._prosesenkripsi(mostCurrent.activityBA,_gen.ToPrettyString((int) (2)));Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 114;BA.debugLine="If strEnc = \"Enkripsi Failed\" Then Return";
Debug.ShouldStop(131072);
if ((_strenc).equals("Enkripsi Failed")) { 
if (true) return "";};
 BA.debugLineNum = 115;BA.debugLine="HomeJob.PostString(Fungsi.mpList.Get(\"SearchURL\"), \"teks=\" & strEnc)";
Debug.ShouldStop(262144);
_homejob._poststring(BA.ObjectToString(mostCurrent._fungsi._mplist.Get((Object)("SearchURL"))),"teks="+_strenc);
 BA.debugLineNum = 116;BA.debugLine="HomeJob.GetRequest.SetHeader(\"Cache-Control\", \"no-cache, must-revalidate\")";
Debug.ShouldStop(524288);
_homejob._getrequest().SetHeader("Cache-Control","no-cache, must-revalidate");
 BA.debugLineNum = 117;BA.debugLine="HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60";
Debug.ShouldStop(1048576);
_homejob._getrequest().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 } 
       catch (Exception e51) {
			processBA.setLastException(e51); BA.debugLineNum = 119;BA.debugLine="Msgbox2(\"Cetgory Failure !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(4194304);
anywheresoftware.b4a.keywords.Common.Msgbox2("Cetgory Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 };
 BA.debugLineNum = 121;BA.debugLine="End Sub";
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
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Dim index As Object";
_index = new Object();
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _sv_itemclick(String _value) throws Exception{
try {
		Debug.PushSubsStack("sv_ItemClick (menusearch) ","menusearch",4,mostCurrent.activityBA,mostCurrent,42);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 42;BA.debugLine="Sub sv_ItemClick(Value As String)";
Debug.ShouldStop(512);
 BA.debugLineNum = 44;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
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
