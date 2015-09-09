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

public class content extends Activity implements B4AActivity{
	public static content mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "newmoviebay.mncplaymedia.com", "newmoviebay.mncplaymedia.com.content");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (content).");
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
		activityBA = new BA(this, layout, processBA, "newmoviebay.mncplaymedia.com", "newmoviebay.mncplaymedia.com.content");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "newmoviebay.mncplaymedia.com.content", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (content) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (content) Resume **");
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
		return content.class;
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
        BA.LogInfo("** Activity (content) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (content) Resume **");
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
public static int _i = 0;
public static Object _a = null;
public static anywheresoftware.b4a.objects.collections.Map _gambar = null;
public static anywheresoftware.b4a.phone.PackageManagerWrapper _pm = null;
public static anywheresoftware.b4a.objects.IntentWrapper _ups = null;
public static int _ipack = 0;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivmovies = null;
public flm.b4a.betterdialogs.BetterDialogs.CustomDlgParams _params = null;
public flm.b4a.betterdialogs.BetterDialogs _bd = null;
public anywheresoftware.b4a.objects.PanelWrapper _pshare = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivshare = null;
public static int _ishare = 0;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivplay = null;
public anywheresoftware.b4a.objects.WebViewWrapper _wvorder = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcerita = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblfilm = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcate = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbldirec = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblnama = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblgenre = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbljudul = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltitle = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcast = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblnmcast = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltake = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblallsyn = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivmovie = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblatas = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblbawah = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltrailer = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivtrailer = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcomment = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivcomment = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etdesc = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcomments = null;
public anywheresoftware.b4a.objects.ButtonWrapper _bshare = null;
public anywheresoftware.b4a.objects.ButtonWrapper _bcancel = null;
public anywheresoftware.b4a.objects.PanelWrapper _putama = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivcancel = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivaction = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblaction = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblnmwrite = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblnmwriter = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivcomments = null;
public b4a.example.dateutils _dateutils = null;
public newmoviebay.mncplaymedia.com.main _main = null;
public newmoviebay.mncplaymedia.com.utama _utama = null;
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
		return new Object[] {"a",_a,"Activity",mostCurrent._activity,"bCancel",mostCurrent._bcancel,"BD",mostCurrent._bd,"bShare",mostCurrent._bshare,"Date",Debug.moduleToString(newmoviebay.mncplaymedia.com.date.class),"DateUtils",mostCurrent._dateutils,"etDesc",mostCurrent._etdesc,"ForgotPass",Debug.moduleToString(newmoviebay.mncplaymedia.com.forgotpass.class),"Fungsi",Debug.moduleToString(newmoviebay.mncplaymedia.com.fungsi.class),"gambar",_gambar,"i",_i,"ipack",_ipack,"iShare",_ishare,"ivAction",mostCurrent._ivaction,"ivCancel",mostCurrent._ivcancel,"ivComment",mostCurrent._ivcomment,"ivComments",mostCurrent._ivcomments,"ivMovie",mostCurrent._ivmovie,"ivMovies",mostCurrent._ivmovies,"ivPlay",mostCurrent._ivplay,"ivShare",mostCurrent._ivshare,"ivTrailer",mostCurrent._ivtrailer,"lblAction",mostCurrent._lblaction,"lblAllSyn",mostCurrent._lblallsyn,"lblAtas",mostCurrent._lblatas,"lblBawah",mostCurrent._lblbawah,"lblCast",mostCurrent._lblcast,"lblCate",mostCurrent._lblcate,"lblCerita",mostCurrent._lblcerita,"lblComment",mostCurrent._lblcomment,"lblComments",mostCurrent._lblcomments,"lblDirec",mostCurrent._lbldirec,"lblFilm",mostCurrent._lblfilm,"lblGenre",mostCurrent._lblgenre,"lblJudul",mostCurrent._lbljudul,"lblNama",mostCurrent._lblnama,"lblNmCast",mostCurrent._lblnmcast,"lblNmWrite",mostCurrent._lblnmwrite,"lblNmWriter",mostCurrent._lblnmwriter,"lblTake",mostCurrent._lbltake,"lblTitle",mostCurrent._lbltitle,"lblTrailer",mostCurrent._lbltrailer,"Main",Debug.moduleToString(newmoviebay.mncplaymedia.com.main.class),"MenuSearch",Debug.moduleToString(newmoviebay.mncplaymedia.com.menusearch.class),"MNCUtils2Service",Debug.moduleToString(newmoviebay.mncplaymedia.com.mncutils2service.class),"Params",mostCurrent._params,"Pemutar",Debug.moduleToString(newmoviebay.mncplaymedia.com.pemutar.class),"Play",Debug.moduleToString(newmoviebay.mncplaymedia.com.play.class),"pm",_pm,"Profile",Debug.moduleToString(newmoviebay.mncplaymedia.com.profile.class),"pShare",mostCurrent._pshare,"pUtama",mostCurrent._putama,"Register",Debug.moduleToString(newmoviebay.mncplaymedia.com.register.class),"Result",Debug.moduleToString(newmoviebay.mncplaymedia.com.result.class),"Share",Debug.moduleToString(newmoviebay.mncplaymedia.com.share.class),"ups",_ups,"Utama",Debug.moduleToString(newmoviebay.mncplaymedia.com.utama.class),"WebView",Debug.moduleToString(newmoviebay.mncplaymedia.com.webview.class),"wvOrder",mostCurrent._wvorder};
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
		Debug.PushSubsStack("Activity_Create (content) ","content",2,mostCurrent.activityBA,mostCurrent,65);
anywheresoftware.b4a.objects.collections.Map _cate0 = null;
anywheresoftware.b4a.objects.collections.List _cate1 = null;
anywheresoftware.b4a.objects.collections.Map _cate2 = null;
anywheresoftware.b4a.objects.collections.Map _genre0 = null;
anywheresoftware.b4a.objects.collections.List _genre1 = null;
anywheresoftware.b4a.objects.collections.Map _gender2 = null;
anywheresoftware.b4a.objects.collections.Map _cast0 = null;
anywheresoftware.b4a.objects.collections.List _cast1 = null;
anywheresoftware.b4a.objects.collections.Map _cast2 = null;
anywheresoftware.b4a.objects.collections.Map _writer0 = null;
anywheresoftware.b4a.objects.collections.List _writer1 = null;
anywheresoftware.b4a.objects.collections.Map _writer2 = null;
int _x2 = 0;
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 65;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(1);
 BA.debugLineNum = 70;BA.debugLine="Activity.LoadLayout(\"Content\")";
Debug.ShouldStop(32);
mostCurrent._activity.LoadLayout("Content",mostCurrent.activityBA);
 BA.debugLineNum = 74;BA.debugLine="ivMovies.Bitmap = a";
Debug.ShouldStop(512);
mostCurrent._ivmovies.setBitmap((android.graphics.Bitmap)(_a));
 BA.debugLineNum = 78;BA.debugLine="Dim Cate0 As Map";
Debug.ShouldStop(8192);
_cate0 = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("Cate0", _cate0);
 BA.debugLineNum = 79;BA.debugLine="Cate0.Initialize";
Debug.ShouldStop(16384);
_cate0.Initialize();
 BA.debugLineNum = 80;BA.debugLine="gambar.Get(\"contentDirectors\")";
Debug.ShouldStop(32768);
_gambar.Get((Object)("contentDirectors"));
 BA.debugLineNum = 82;BA.debugLine="Dim Cate1 As List = gambar.Get(\"contentDirectors\")";
Debug.ShouldStop(131072);
_cate1 = new anywheresoftware.b4a.objects.collections.List();
_cate1.setObject((java.util.List)(_gambar.Get((Object)("contentDirectors"))));Debug.locals.put("Cate1", _cate1);
 BA.debugLineNum = 83;BA.debugLine="For Each Cate2 As Map In Cate1";
Debug.ShouldStop(262144);
_cate2 = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group55 = _cate1;
final int groupLen55 = group55.getSize();
for (int index55 = 0;index55 < groupLen55 ;index55++){
_cate2.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group55.Get(index55)));
Debug.locals.put("cate2", _cate2);
 BA.debugLineNum = 84;BA.debugLine="Cate0 = Cate2.Get(\"idDirector\")";
Debug.ShouldStop(524288);
_cate0.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_cate2.Get((Object)("idDirector"))));
 }
Debug.locals.put("cate2", _cate2);
;
 BA.debugLineNum = 86;BA.debugLine="Cate0.Get(\"name\")";
Debug.ShouldStop(2097152);
_cate0.Get((Object)("name"));
 BA.debugLineNum = 87;BA.debugLine="lblDirec.Text = Cate0.Get(\"name\")";
Debug.ShouldStop(4194304);
mostCurrent._lbldirec.setText(_cate0.Get((Object)("name")));
 BA.debugLineNum = 91;BA.debugLine="Dim Genre0 As Map";
Debug.ShouldStop(67108864);
_genre0 = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("Genre0", _genre0);
 BA.debugLineNum = 92;BA.debugLine="Genre0.Initialize";
Debug.ShouldStop(134217728);
_genre0.Initialize();
 BA.debugLineNum = 93;BA.debugLine="gambar.Get(\"contentGenres\")";
Debug.ShouldStop(268435456);
_gambar.Get((Object)("contentGenres"));
 BA.debugLineNum = 95;BA.debugLine="Dim Genre1 As List = gambar.Get(\"contentGenres\")";
Debug.ShouldStop(1073741824);
_genre1 = new anywheresoftware.b4a.objects.collections.List();
_genre1.setObject((java.util.List)(_gambar.Get((Object)("contentGenres"))));Debug.locals.put("Genre1", _genre1);
 BA.debugLineNum = 96;BA.debugLine="For Each Gender2 As Map In Genre1";
Debug.ShouldStop(-2147483648);
_gender2 = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group64 = _genre1;
final int groupLen64 = group64.getSize();
for (int index64 = 0;index64 < groupLen64 ;index64++){
_gender2.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group64.Get(index64)));
Debug.locals.put("gender2", _gender2);
 BA.debugLineNum = 97;BA.debugLine="Genre0 = Gender2.Get(\"idGenre\")";
Debug.ShouldStop(1);
_genre0.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_gender2.Get((Object)("idGenre"))));
 }
Debug.locals.put("gender2", _gender2);
;
 BA.debugLineNum = 100;BA.debugLine="Genre0.Get(\"name\")";
Debug.ShouldStop(8);
_genre0.Get((Object)("name"));
 BA.debugLineNum = 102;BA.debugLine="lblGenre.Text = Genre0.Get(\"name\")";
Debug.ShouldStop(32);
mostCurrent._lblgenre.setText(_genre0.Get((Object)("name")));
 BA.debugLineNum = 105;BA.debugLine="Dim Cast0 As Map";
Debug.ShouldStop(256);
_cast0 = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("Cast0", _cast0);
 BA.debugLineNum = 106;BA.debugLine="Cast0.Initialize";
Debug.ShouldStop(512);
_cast0.Initialize();
 BA.debugLineNum = 107;BA.debugLine="gambar.Get(\"contentCasts\")";
Debug.ShouldStop(1024);
_gambar.Get((Object)("contentCasts"));
 BA.debugLineNum = 108;BA.debugLine="Dim Cast1 As List = gambar.Get(\"contentCasts\")";
Debug.ShouldStop(2048);
_cast1 = new anywheresoftware.b4a.objects.collections.List();
_cast1.setObject((java.util.List)(_gambar.Get((Object)("contentCasts"))));Debug.locals.put("Cast1", _cast1);
 BA.debugLineNum = 109;BA.debugLine="For Each Cast2 As Map In Cast1";
Debug.ShouldStop(4096);
_cast2 = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group73 = _cast1;
final int groupLen73 = group73.getSize();
for (int index73 = 0;index73 < groupLen73 ;index73++){
_cast2.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group73.Get(index73)));
Debug.locals.put("cast2", _cast2);
 BA.debugLineNum = 110;BA.debugLine="Cast0 = Cast2.Get(\"idCast\")";
Debug.ShouldStop(8192);
_cast0.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_cast2.Get((Object)("idCast"))));
 }
Debug.locals.put("cast2", _cast2);
;
 BA.debugLineNum = 113;BA.debugLine="Cast0.Get(\"name\")";
Debug.ShouldStop(65536);
_cast0.Get((Object)("name"));
 BA.debugLineNum = 115;BA.debugLine="lblNmCast.Text = Cast0.Get(\"name\")";
Debug.ShouldStop(262144);
mostCurrent._lblnmcast.setText(_cast0.Get((Object)("name")));
 BA.debugLineNum = 118;BA.debugLine="Dim Writer0 As Map";
Debug.ShouldStop(2097152);
_writer0 = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("Writer0", _writer0);
 BA.debugLineNum = 119;BA.debugLine="Writer0.Initialize";
Debug.ShouldStop(4194304);
_writer0.Initialize();
 BA.debugLineNum = 120;BA.debugLine="gambar.Get(\"contentProducers\")";
Debug.ShouldStop(8388608);
_gambar.Get((Object)("contentProducers"));
 BA.debugLineNum = 122;BA.debugLine="Dim Writer1 As List = gambar.Get(\"contentProducers\")";
Debug.ShouldStop(33554432);
_writer1 = new anywheresoftware.b4a.objects.collections.List();
_writer1.setObject((java.util.List)(_gambar.Get((Object)("contentProducers"))));Debug.locals.put("Writer1", _writer1);
 BA.debugLineNum = 123;BA.debugLine="For Each Writer2 As Map In Writer1";
Debug.ShouldStop(67108864);
_writer2 = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group82 = _writer1;
final int groupLen82 = group82.getSize();
for (int index82 = 0;index82 < groupLen82 ;index82++){
_writer2.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group82.Get(index82)));
Debug.locals.put("writer2", _writer2);
 BA.debugLineNum = 124;BA.debugLine="Writer0 = Writer2.Get(\"idProducer\")";
Debug.ShouldStop(134217728);
_writer0.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_writer2.Get((Object)("idProducer"))));
 }
Debug.locals.put("writer2", _writer2);
;
 BA.debugLineNum = 127;BA.debugLine="Writer0.Get(\"name\")";
Debug.ShouldStop(1073741824);
_writer0.Get((Object)("name"));
 BA.debugLineNum = 129;BA.debugLine="lblNmWriter.Text = Writer0.Get(\"name\")";
Debug.ShouldStop(1);
mostCurrent._lblnmwriter.setText(_writer0.Get((Object)("name")));
 BA.debugLineNum = 131;BA.debugLine="Log(gambar)";
Debug.ShouldStop(4);
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(_gambar));
 BA.debugLineNum = 132;BA.debugLine="For x2=0 To gambar.Size-1";
Debug.ShouldStop(8);
{
final int step88 = 1;
final int limit88 = (int) (_gambar.getSize()-1);
for (_x2 = (int) (0); (step88 > 0 && _x2 <= limit88) || (step88 < 0 && _x2 >= limit88); _x2 = ((int)(0 + _x2 + step88))) {
Debug.locals.put("x2", _x2);
 BA.debugLineNum = 133;BA.debugLine="Select gambar.GetKeyAt(x2)";
Debug.ShouldStop(16);
switch (BA.switchObjectToInt(_gambar.GetKeyAt(_x2),(Object)("synopsis"),(Object)("type"),(Object)("title"))) {
case 0:
 BA.debugLineNum = 135;BA.debugLine="lblCerita.Text = gambar.Get(\"synopsis\")";
Debug.ShouldStop(64);
mostCurrent._lblcerita.setText(_gambar.Get((Object)("synopsis")));
 break;
case 1:
 BA.debugLineNum = 137;BA.debugLine="lblBawah.Text = gambar.Get(\"type\")";
Debug.ShouldStop(256);
mostCurrent._lblbawah.setText(_gambar.Get((Object)("type")));
 break;
case 2:
 BA.debugLineNum = 139;BA.debugLine="lblAtas.Text = gambar.Get(\"title\")";
Debug.ShouldStop(1024);
mostCurrent._lblatas.setText(_gambar.Get((Object)("title")));
 BA.debugLineNum = 140;BA.debugLine="lblTitle.Text = gambar.Get(\"title\")";
Debug.ShouldStop(2048);
mostCurrent._lbltitle.setText(_gambar.Get((Object)("title")));
 break;
}
;
 }
}Debug.locals.put("x2", _x2);
;
 BA.debugLineNum = 145;BA.debugLine="End Sub";
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
		Debug.PushSubsStack("Activity_Pause (content) ","content",2,mostCurrent.activityBA,mostCurrent,151);
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 151;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 153;BA.debugLine="End Sub";
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
		Debug.PushSubsStack("Activity_Resume (content) ","content",2,mostCurrent.activityBA,mostCurrent,147);
 BA.debugLineNum = 147;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(262144);
 BA.debugLineNum = 149;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 19;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 23;BA.debugLine="Dim ivMovies 		As ImageView";
mostCurrent._ivmovies = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private Params		As BD_CustomDlgParams";
mostCurrent._params = new flm.b4a.betterdialogs.BetterDialogs.CustomDlgParams();
 //BA.debugLineNum = 25;BA.debugLine="Private BD			As BetterDialogs";
mostCurrent._bd = new flm.b4a.betterdialogs.BetterDialogs();
 //BA.debugLineNum = 26;BA.debugLine="Private pShare		As Panel";
mostCurrent._pshare = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private ivShare 	As ImageView";
mostCurrent._ivshare = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Dim iShare 			As Int=1";
_ishare = (int) (1);
 //BA.debugLineNum = 29;BA.debugLine="Private ivPlay 		As ImageView";
mostCurrent._ivplay = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private wvOrder 	As WebView";
mostCurrent._wvorder = new anywheresoftware.b4a.objects.WebViewWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private lblCerita   As Label";
mostCurrent._lblcerita = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private lblFilm 	As Label";
mostCurrent._lblfilm = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private lblCate 	As Label";
mostCurrent._lblcate = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private lblDirec 	As Label";
mostCurrent._lbldirec = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private lblNama 	As Label";
mostCurrent._lblnama = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private lblGenre 	As Label";
mostCurrent._lblgenre = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private lblJudul 	 As Label";
mostCurrent._lbljudul = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private lblTitle 	 As Label";
mostCurrent._lbltitle = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private lblCast		 As Label";
mostCurrent._lblcast = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private lblNmCast 	 As Label";
mostCurrent._lblnmcast = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private lblTake 	 As Label";
mostCurrent._lbltake = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private lblAllSyn 	 As Label";
mostCurrent._lblallsyn = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private ivMovie 	 As ImageView";
mostCurrent._ivmovie = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private lblAtas 	 As Label";
mostCurrent._lblatas = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private lblBawah 	 As Label";
mostCurrent._lblbawah = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private lblTrailer 	 As Label";
mostCurrent._lbltrailer = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private ivTrailer 	 As ImageView";
mostCurrent._ivtrailer = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private lblComment 	 As Label";
mostCurrent._lblcomment = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private ivComment 	 As ImageView";
mostCurrent._ivcomment = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private etDesc 		 As EditText";
mostCurrent._etdesc = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Private lblComments  As Label";
mostCurrent._lblcomments = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private bShare 		 As Button";
mostCurrent._bshare = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Private bCancel 	 As Button";
mostCurrent._bcancel = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Private pUtama 		 As Panel";
mostCurrent._putama = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Private ivCancel 	 As ImageView";
mostCurrent._ivcancel = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Private ivAction 	 As ImageView";
mostCurrent._ivaction = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 58;BA.debugLine="Private lblAction 	 As Label";
mostCurrent._lblaction = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 59;BA.debugLine="Private lblNmWrite 	 As Label";
mostCurrent._lblnmwrite = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 60;BA.debugLine="Private lblNmWriter  As Label";
mostCurrent._lblnmwriter = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 61;BA.debugLine="Private ivComments 	 As ImageView";
mostCurrent._ivcomments = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 63;BA.debugLine="End Sub";
return "";
}
public static String  _ivback_click() throws Exception{
try {
		Debug.PushSubsStack("ivBack_Click (content) ","content",2,mostCurrent.activityBA,mostCurrent,154);
 BA.debugLineNum = 154;BA.debugLine="Sub ivBack_Click";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 155;BA.debugLine="Activity.Finish";
Debug.ShouldStop(67108864);
mostCurrent._activity.Finish();
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
public static String  _ivcancel_click() throws Exception{
try {
		Debug.PushSubsStack("ivCancel_Click (content) ","content",2,mostCurrent.activityBA,mostCurrent,229);
 BA.debugLineNum = 229;BA.debugLine="Sub ivCancel_Click";
Debug.ShouldStop(16);
 BA.debugLineNum = 230;BA.debugLine="StartActivity(\"Content\")";
Debug.ShouldStop(32);
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)("Content"));
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
public static String  _ivcomments_click() throws Exception{
try {
		Debug.PushSubsStack("ivComments_Click (content) ","content",2,mostCurrent.activityBA,mostCurrent,247);
 BA.debugLineNum = 247;BA.debugLine="Sub ivComments_Click";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 248;BA.debugLine="lblComments_Click";
Debug.ShouldStop(8388608);
_lblcomments_click();
 BA.debugLineNum = 249;BA.debugLine="End Sub";
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
public static String  _ivmovies_click() throws Exception{
try {
		Debug.PushSubsStack("ivMovies_Click (content) ","content",2,mostCurrent.activityBA,mostCurrent,202);
 BA.debugLineNum = 202;BA.debugLine="Sub ivMovies_Click";
Debug.ShouldStop(512);
 BA.debugLineNum = 204;BA.debugLine="End Sub";
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
public static String  _ivplay_click() throws Exception{
try {
		Debug.PushSubsStack("ivPlay_Click (content) ","content",2,mostCurrent.activityBA,mostCurrent,160);
int _x2 = 0;
anywheresoftware.b4a.objects.collections.List _paclist = null;
 BA.debugLineNum = 160;BA.debugLine="Sub ivPlay_Click";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 163;BA.debugLine="For x2=0 To gambar.Size-1";
Debug.ShouldStop(4);
{
final int step111 = 1;
final int limit111 = (int) (_gambar.getSize()-1);
for (_x2 = (int) (0); (step111 > 0 && _x2 <= limit111) || (step111 < 0 && _x2 >= limit111); _x2 = ((int)(0 + _x2 + step111))) {
Debug.locals.put("x2", _x2);
 BA.debugLineNum = 164;BA.debugLine="Select gambar.GetKeyAt(x2)";
Debug.ShouldStop(8);
switch (BA.switchObjectToInt(_gambar.GetKeyAt(_x2),(Object)("pathVideo"))) {
case 0:
 BA.debugLineNum = 166;BA.debugLine="If gambar.Get(\"pathVideo\") = Null Then";
Debug.ShouldStop(32);
if (_gambar.Get((Object)("pathVideo"))== null) { 
 BA.debugLineNum = 167;BA.debugLine="Msgbox2(\"Video not found !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(64);
anywheresoftware.b4a.keywords.Common.Msgbox2("Video not found !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 170;BA.debugLine="If File.Exists(Fungsi.DirDBS, \"nexplayersdklite.apk\") = False Then";
Debug.ShouldStop(512);
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._fungsi._dirdbs,"nexplayersdklite.apk")==anywheresoftware.b4a.keywords.Common.False) { 
 BA.debugLineNum = 171;BA.debugLine="ProgressDialogShow2(\"Please wait...\", False)";
Debug.ShouldStop(1024);
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,"Please wait...",anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 172;BA.debugLine="DoEvents";
Debug.ShouldStop(2048);
anywheresoftware.b4a.keywords.Common.DoEvents();
 BA.debugLineNum = 173;BA.debugLine="File.Copy(File.DirAssets, \"nexplayersdklite.apk\", Fungsi.DirDBS, \"nexplayersdklite.apk\")";
Debug.ShouldStop(4096);
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"nexplayersdklite.apk",mostCurrent._fungsi._dirdbs,"nexplayersdklite.apk");
 BA.debugLineNum = 174;BA.debugLine="ProgressDialogHide";
Debug.ShouldStop(8192);
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 BA.debugLineNum = 175;BA.debugLine="DoEvents";
Debug.ShouldStop(16384);
anywheresoftware.b4a.keywords.Common.DoEvents();
 };
 BA.debugLineNum = 178;BA.debugLine="Dim pacList As List = pm.GetInstalledPackages";
Debug.ShouldStop(131072);
_paclist = new anywheresoftware.b4a.objects.collections.List();
_paclist = _pm.GetInstalledPackages();Debug.locals.put("pacList", _paclist);Debug.locals.put("pacList", _paclist);
 BA.debugLineNum = 179;BA.debugLine="ipack = pacList.IndexOf(\"com.nexstreaming.app.nexplayersamplelitevm\")";
Debug.ShouldStop(262144);
_ipack = _paclist.IndexOf((Object)("com.nexstreaming.app.nexplayersamplelitevm"));
 BA.debugLineNum = 181;BA.debugLine="If ipack < 0 Then";
Debug.ShouldStop(1048576);
if (_ipack<0) { 
 BA.debugLineNum = 182;BA.debugLine="ups.Initialize(ups.ACTION_VIEW, \"file://\" & File.Combine(Fungsi.DirDBS, \"nexplayersdklite.apk\"))";
Debug.ShouldStop(2097152);
_ups.Initialize(_ups.ACTION_VIEW,"file://"+anywheresoftware.b4a.keywords.Common.File.Combine(mostCurrent._fungsi._dirdbs,"nexplayersdklite.apk"));
 BA.debugLineNum = 183;BA.debugLine="ups.SetType(\"application/vnd.android.package-archive\")";
Debug.ShouldStop(4194304);
_ups.SetType("application/vnd.android.package-archive");
 BA.debugLineNum = 184;BA.debugLine="StartActivity(ups)";
Debug.ShouldStop(8388608);
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_ups.getObject()));
 };
 BA.debugLineNum = 188;BA.debugLine="ups.Initialize(ups.ACTION_MAIN, \"\")";
Debug.ShouldStop(134217728);
_ups.Initialize(_ups.ACTION_MAIN,"");
 BA.debugLineNum = 189;BA.debugLine="ups = pm.GetApplicationIntent(\"com.nexstreaming.app.nexplayersamplelitevm\")";
Debug.ShouldStop(268435456);
_ups = _pm.GetApplicationIntent("com.nexstreaming.app.nexplayersamplelitevm");
 BA.debugLineNum = 190;BA.debugLine="Log(gambar.Get(\"pathVideo\"))";
Debug.ShouldStop(536870912);
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(_gambar.Get((Object)("pathVideo"))));
 BA.debugLineNum = 191;BA.debugLine="ups.PutExtra(\"LinkURL\", gambar.Get(\"pathVideo\"))";
Debug.ShouldStop(1073741824);
_ups.PutExtra("LinkURL",_gambar.Get((Object)("pathVideo")));
 BA.debugLineNum = 192;BA.debugLine="ups.PutExtra(\"Company\", \"PT-Infokom\")";
Debug.ShouldStop(-2147483648);
_ups.PutExtra("Company",(Object)("PT-Infokom"));
 BA.debugLineNum = 193;BA.debugLine="ups.PutExtra(\"VCASAddr\", \"202.80.222.36:80\")";
Debug.ShouldStop(1);
_ups.PutExtra("VCASAddr",(Object)("202.80.222.36:80"));
 BA.debugLineNum = 195;BA.debugLine="If ups.IsInitialized Then StartActivity(ups)";
Debug.ShouldStop(4);
if (_ups.IsInitialized()) { 
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_ups.getObject()));};
 };
 break;
}
;
 }
}Debug.locals.put("x2", _x2);
;
 BA.debugLineNum = 200;BA.debugLine="End Sub";
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
public static String  _ivshare_click() throws Exception{
try {
		Debug.PushSubsStack("ivShare_Click (content) ","content",2,mostCurrent.activityBA,mostCurrent,157);
 BA.debugLineNum = 157;BA.debugLine="Sub ivShare_Click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 158;BA.debugLine="StartActivity(\"Share\")";
Debug.ShouldStop(536870912);
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)("Share"));
 BA.debugLineNum = 159;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static String  _ivtrailer_click() throws Exception{
try {
		Debug.PushSubsStack("ivTrailer_Click (content) ","content",2,mostCurrent.activityBA,mostCurrent,226);
 BA.debugLineNum = 226;BA.debugLine="Sub ivTrailer_Click";
Debug.ShouldStop(2);
 BA.debugLineNum = 227;BA.debugLine="lblTrailer_Click";
Debug.ShouldStop(4);
_lbltrailer_click();
 BA.debugLineNum = 228;BA.debugLine="End Sub";
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
public static String  _lblcomments_click() throws Exception{
try {
		Debug.PushSubsStack("lblComments_Click (content) ","content",2,mostCurrent.activityBA,mostCurrent,232);
anywheresoftware.b4a.objects.PanelWrapper _pnlbody = null;
 BA.debugLineNum = 232;BA.debugLine="Sub lblComments_Click";
Debug.ShouldStop(128);
 BA.debugLineNum = 233;BA.debugLine="Private pnlBody As Panel";
Debug.ShouldStop(256);
_pnlbody = new anywheresoftware.b4a.objects.PanelWrapper();Debug.locals.put("pnlBody", _pnlbody);
 BA.debugLineNum = 234;BA.debugLine="pnlBody.Initialize(\"\")";
Debug.ShouldStop(512);
_pnlbody.Initialize(mostCurrent.activityBA,"");
 BA.debugLineNum = 235;BA.debugLine="pnlBody.Color = Colors.Transparent";
Debug.ShouldStop(1024);
_pnlbody.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 BA.debugLineNum = 236;BA.debugLine="pnlBody.LoadLayout(\"comments\")";
Debug.ShouldStop(2048);
_pnlbody.LoadLayout("comments",mostCurrent.activityBA);
 BA.debugLineNum = 238;BA.debugLine="etDesc.Background = Fungsi.GetDrawable (\"pass_input_\")";
Debug.ShouldStop(8192);
mostCurrent._etdesc.setBackground((android.graphics.drawable.Drawable)(mostCurrent._fungsi._getdrawable(mostCurrent.activityBA,"pass_input_")));
 BA.debugLineNum = 240;BA.debugLine="Params.Initialize";
Debug.ShouldStop(32768);
mostCurrent._params.Initialize();
 BA.debugLineNum = 241;BA.debugLine="Params.DialogBody = pnlBody";
Debug.ShouldStop(65536);
mostCurrent._params.setDialogBody((Object)(_pnlbody.getObject()));
 BA.debugLineNum = 242;BA.debugLine="Params.BodyWidth = pUtama.Width";
Debug.ShouldStop(131072);
mostCurrent._params.setBodyWidth(mostCurrent._putama.getWidth());
 BA.debugLineNum = 243;BA.debugLine="Params.BodyHeight = pUtama.Height";
Debug.ShouldStop(262144);
mostCurrent._params.setBodyHeight(mostCurrent._putama.getHeight());
 BA.debugLineNum = 245;BA.debugLine="BD.CustomDialog(Params, \"CD2\")";
Debug.ShouldStop(1048576);
mostCurrent._bd.CustomDialog(mostCurrent._params,"CD2",mostCurrent.activityBA);
 BA.debugLineNum = 246;BA.debugLine="End Sub";
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
public static String  _lbltrailer_click() throws Exception{
try {
		Debug.PushSubsStack("lblTrailer_Click (content) ","content",2,mostCurrent.activityBA,mostCurrent,206);
int _x2 = 0;
 BA.debugLineNum = 206;BA.debugLine="Sub lblTrailer_Click";
Debug.ShouldStop(8192);
 BA.debugLineNum = 207;BA.debugLine="For x2=0 To gambar.Size-1";
Debug.ShouldStop(16384);
{
final int step145 = 1;
final int limit145 = (int) (_gambar.getSize()-1);
for (_x2 = (int) (0); (step145 > 0 && _x2 <= limit145) || (step145 < 0 && _x2 >= limit145); _x2 = ((int)(0 + _x2 + step145))) {
Debug.locals.put("x2", _x2);
 BA.debugLineNum = 208;BA.debugLine="Select gambar.GetKeyAt(x2)";
Debug.ShouldStop(32768);
switch (BA.switchObjectToInt(_gambar.GetKeyAt(_x2),(Object)("pathVideo"))) {
case 0:
 BA.debugLineNum = 210;BA.debugLine="If gambar.Get(\"pathVideo\") = Null Then";
Debug.ShouldStop(131072);
if (_gambar.Get((Object)("pathVideo"))== null) { 
 BA.debugLineNum = 211;BA.debugLine="Msgbox2(\"Video not found !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(262144);
anywheresoftware.b4a.keywords.Common.Msgbox2("Video not found !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 214;BA.debugLine="ups.Initialize(ups.ACTION_MAIN, \"\")";
Debug.ShouldStop(2097152);
_ups.Initialize(_ups.ACTION_MAIN,"");
 BA.debugLineNum = 215;BA.debugLine="ups = pm.GetApplicationIntent(\"com.nexstreaming.app.nexplayersamplelitevm\")";
Debug.ShouldStop(4194304);
_ups = _pm.GetApplicationIntent("com.nexstreaming.app.nexplayersamplelitevm");
 BA.debugLineNum = 216;BA.debugLine="ups.PutExtra(\"LinkURL\", gambar.Get(\"http://media.mncplaymedia.net/hls2/animalplanet/animalplanet.m3u8\"))";
Debug.ShouldStop(8388608);
_ups.PutExtra("LinkURL",_gambar.Get((Object)("http://media.mncplaymedia.net/hls2/animalplanet/animalplanet.m3u8")));
 BA.debugLineNum = 217;BA.debugLine="ups.PutExtra(\"Company\", \"PT-Infokom\")";
Debug.ShouldStop(16777216);
_ups.PutExtra("Company",(Object)("PT-Infokom"));
 BA.debugLineNum = 218;BA.debugLine="ups.PutExtra(\"VCASAddr\", \"202.80.222.36:80\")";
Debug.ShouldStop(33554432);
_ups.PutExtra("VCASAddr",(Object)("202.80.222.36:80"));
 BA.debugLineNum = 219;BA.debugLine="If ups.IsInitialized Then StartActivity(ups)";
Debug.ShouldStop(67108864);
if (_ups.IsInitialized()) { 
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_ups.getObject()));};
 BA.debugLineNum = 220;BA.debugLine="StartActivity(ups)";
Debug.ShouldStop(134217728);
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_ups.getObject()));
 };
 break;
}
;
 }
}Debug.locals.put("x2", _x2);
;
 BA.debugLineNum = 225;BA.debugLine="End Sub";
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
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="Dim i			As Int";
_i = 0;
 //BA.debugLineNum = 11;BA.debugLine="Dim a			As Object";
_a = new Object();
 //BA.debugLineNum = 12;BA.debugLine="Dim gambar 		As Map";
_gambar = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 13;BA.debugLine="Private pm 		As PackageManager";
_pm = new anywheresoftware.b4a.phone.PackageManagerWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private ups 	As Intent";
_ups = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private ipack	As Int";
_ipack = 0;
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return "";
}
}
