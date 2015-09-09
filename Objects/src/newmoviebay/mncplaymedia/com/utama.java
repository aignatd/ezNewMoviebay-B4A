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

public class utama extends Activity implements B4AActivity{
	public static utama mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "newmoviebay.mncplaymedia.com", "newmoviebay.mncplaymedia.com.utama");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (utama).");
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
		activityBA = new BA(this, layout, processBA, "newmoviebay.mncplaymedia.com", "newmoviebay.mncplaymedia.com.utama");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "newmoviebay.mncplaymedia.com.utama", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (utama) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (utama) Resume **");
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
		return utama.class;
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
        BA.LogInfo("** Activity (utama) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (utama) Resume **");
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
public static String _damn = "";
public static String _sial = "";
public de.amberhome.viewpager.AHPageContainer _container = null;
public de.amberhome.viewpager.AHViewPager _pager = null;
public de.amberhome.viewpager.AHViewPagerTabs _tabs = null;
public static int _fill_parent = 0;
public static int _wrap_content = 0;
public anywheresoftware.b4a.objects.PanelWrapper _pmainatas = null;
public anywheresoftware.b4a.objects.PanelWrapper _pmenumain = null;
public flm.b4a.betterdialogs.BetterDialogs _bdutama = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivmenu = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivsearch = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivmainlogo = null;
public anywheresoftware.b4a.objects.PanelWrapper _plogo = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblnamafilm = null;
public newmoviebay.mncplaymedia.com.clsactionbar _ab = null;
public anywheresoftware.b4a.objects.HorizontalScrollViewWrapper _hsv = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbluser = null;
public anywheresoftware.b4a.objects.PanelWrapper _pmenu = null;
public static int _itampil1 = 0;
public static int _itampil2 = 0;
public anywheresoftware.b4a.objects.PanelWrapper _putama = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lvutama = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lvcatefilm = null;
public anywheresoftware.b4a.objects.PanelWrapper _pisitab = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivclose = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivmovies = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivback = null;
public anywheresoftware.b4a.objects.collections.Map _mplist = null;
public adr.stringfunctions.stringfunctions _streext = null;
public anywheresoftware.giuseppe.salvi.toast.ToastMessageShowWrapper _ctoast = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etemail = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etpass = null;
public anywheresoftware.b4a.objects.collections.List _lstcate = null;
public anywheresoftware.b4a.objects.ButtonWrapper _breg = null;
public static String _dirfile = "";
public static String _namafile = "";
public static String _boundary = "";
public static boolean _iawalcate = false;
public static String _nilai = "";
public anywheresoftware.b4a.objects.collections.Map _mappager = null;
public anywheresoftware.b4a.objects.collections.Map _mapgrid = null;
public anywheresoftware.b4a.objects.collections.Map _picture = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivuser = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblaction = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbllast = null;
public flm.b4a.betterdialogs.BetterDialogs.CustomDlgParams _params = null;
public flm.b4a.betterdialogs.BetterDialogs _bd = null;
public anywheresoftware.b4a.objects.WebViewWrapper _wvpay = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivpremium = null;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbllg = null;
public b4a.example.dateutils _dateutils = null;
public newmoviebay.mncplaymedia.com.main _main = null;
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
		return new Object[] {"AB",mostCurrent._ab,"Activity",mostCurrent._activity,"BD",mostCurrent._bd,"BDUtama",mostCurrent._bdutama,"boundary",mostCurrent._boundary,"bReg",mostCurrent._breg,"container",mostCurrent._container,"Content",Debug.moduleToString(newmoviebay.mncplaymedia.com.content.class),"ctoast",mostCurrent._ctoast,"damn",_damn,"Date",Debug.moduleToString(newmoviebay.mncplaymedia.com.date.class),"DateUtils",mostCurrent._dateutils,"DirFile",mostCurrent._dirfile,"etEmail",mostCurrent._etemail,"etPass",mostCurrent._etpass,"FILL_PARENT",_fill_parent,"ForgotPass",Debug.moduleToString(newmoviebay.mncplaymedia.com.forgotpass.class),"Fungsi",Debug.moduleToString(newmoviebay.mncplaymedia.com.fungsi.class),"HSV",mostCurrent._hsv,"iAwalCate",_iawalcate,"iTampil1",_itampil1,"iTampil2",_itampil2,"ivBack",mostCurrent._ivback,"ivClose",mostCurrent._ivclose,"ivMainLogo",mostCurrent._ivmainlogo,"ivMenu",mostCurrent._ivmenu,"ivMovies",mostCurrent._ivmovies,"ivPremium",mostCurrent._ivpremium,"ivSearch",mostCurrent._ivsearch,"ivUser",mostCurrent._ivuser,"Label1",mostCurrent._label1,"lblAction",mostCurrent._lblaction,"lblLast",mostCurrent._lbllast,"lblLg",mostCurrent._lbllg,"lblNamaFilm",mostCurrent._lblnamafilm,"lblUser",mostCurrent._lbluser,"lstCate",mostCurrent._lstcate,"lvCateFilm",mostCurrent._lvcatefilm,"lvUtama",mostCurrent._lvutama,"Main",Debug.moduleToString(newmoviebay.mncplaymedia.com.main.class),"mapGrid",mostCurrent._mapgrid,"mapPager",mostCurrent._mappager,"MenuSearch",Debug.moduleToString(newmoviebay.mncplaymedia.com.menusearch.class),"MNCUtils2Service",Debug.moduleToString(newmoviebay.mncplaymedia.com.mncutils2service.class),"mpList",mostCurrent._mplist,"Namafile",mostCurrent._namafile,"nilai",mostCurrent._nilai,"pager",mostCurrent._pager,"Params",mostCurrent._params,"Pemutar",Debug.moduleToString(newmoviebay.mncplaymedia.com.pemutar.class),"picture",mostCurrent._picture,"pIsiTab",mostCurrent._pisitab,"Play",Debug.moduleToString(newmoviebay.mncplaymedia.com.play.class),"pLogo",mostCurrent._plogo,"pMainAtas",mostCurrent._pmainatas,"pMenu",mostCurrent._pmenu,"pMenuMain",mostCurrent._pmenumain,"Profile",Debug.moduleToString(newmoviebay.mncplaymedia.com.profile.class),"pUtama",mostCurrent._putama,"Register",Debug.moduleToString(newmoviebay.mncplaymedia.com.register.class),"Result",Debug.moduleToString(newmoviebay.mncplaymedia.com.result.class),"Share",Debug.moduleToString(newmoviebay.mncplaymedia.com.share.class),"sial",_sial,"streExt",mostCurrent._streext,"tabs",mostCurrent._tabs,"WebView",Debug.moduleToString(newmoviebay.mncplaymedia.com.webview.class),"WRAP_CONTENT",_wrap_content,"wvPay",mostCurrent._wvpay};
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
		Debug.PushSubsStack("Activity_Create (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,70);
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 70;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(32);
 BA.debugLineNum = 73;BA.debugLine="Activity.LoadLayout(\"mainpage\")";
Debug.ShouldStop(256);
mostCurrent._activity.LoadLayout("mainpage",mostCurrent.activityBA);
 BA.debugLineNum = 75;BA.debugLine="mapPager.Initialize";
Debug.ShouldStop(1024);
mostCurrent._mappager.Initialize();
 BA.debugLineNum = 76;BA.debugLine="mapGrid.Initialize";
Debug.ShouldStop(2048);
mostCurrent._mapgrid.Initialize();
 BA.debugLineNum = 78;BA.debugLine="Fungsi.SetDirProg(\"\")";
Debug.ShouldStop(8192);
mostCurrent._fungsi._setdirprog(mostCurrent.activityBA,"");
 BA.debugLineNum = 79;BA.debugLine="Fungsi.DataConfig.FolderDBS = Fungsi.DirDBS";
Debug.ShouldStop(16384);
mostCurrent._fungsi._dataconfig.FolderDBS = mostCurrent._fungsi._dirdbs;
 BA.debugLineNum = 80;BA.debugLine="Fungsi.BacaFileConfig";
Debug.ShouldStop(32768);
mostCurrent._fungsi._bacafileconfig(mostCurrent.activityBA);
 BA.debugLineNum = 82;BA.debugLine="picture.Initialize";
Debug.ShouldStop(131072);
mostCurrent._picture.Initialize();
 BA.debugLineNum = 84;BA.debugLine="If Fungsi.DataConfig.Username <> \"Guest\" Then";
Debug.ShouldStop(524288);
if ((mostCurrent._fungsi._dataconfig.Username).equals("Guest") == false) { 
 BA.debugLineNum = 85;BA.debugLine="lblUser.Text = Fungsi.DataConfig.Username";
Debug.ShouldStop(1048576);
mostCurrent._lbluser.setText((Object)(mostCurrent._fungsi._dataconfig.Username));
 }else {
 BA.debugLineNum = 87;BA.debugLine="lblUser.Text = \"Login Here\"";
Debug.ShouldStop(4194304);
mostCurrent._lbluser.setText((Object)("Login Here"));
 };
 BA.debugLineNum = 90;BA.debugLine="If Fungsi.CacahJSON(File.ReadString(File.DirAssets, \"config.Json\"), \"NextArray\") = False Then 'Cacah JSON yang ada didalam file urutan.json";
Debug.ShouldStop(33554432);
if (mostCurrent._fungsi._cacahjson(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"config.Json"),"NextArray")==anywheresoftware.b4a.keywords.Common.False) { 
 BA.debugLineNum = 91;BA.debugLine="Msgbox2(\"Load init failed !\", \"MNC Playmedia\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"Warning.png\"))";
Debug.ShouldStop(67108864);
anywheresoftware.b4a.keywords.Common.Msgbox2("Load init failed !","MNC Playmedia","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Warning.png").getObject()),mostCurrent.activityBA);
 BA.debugLineNum = 92;BA.debugLine="ExitApplication";
Debug.ShouldStop(134217728);
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 BA.debugLineNum = 95;BA.debugLine="lblUser.Text = damn";
Debug.ShouldStop(1073741824);
mostCurrent._lbluser.setText((Object)(_damn));
 BA.debugLineNum = 96;BA.debugLine="Label1.Text = sial";
Debug.ShouldStop(-2147483648);
mostCurrent._label1.setText((Object)(_sial));
 BA.debugLineNum = 101;BA.debugLine="ivMenu.Background = Fungsi.GetDrawable(\"menu_icon\")";
Debug.ShouldStop(16);
mostCurrent._ivmenu.setBackground((android.graphics.drawable.Drawable)(mostCurrent._fungsi._getdrawable(mostCurrent.activityBA,"menu_icon")));
 BA.debugLineNum = 102;BA.debugLine="ivSearch.Background = Fungsi.GetDrawable(\"search_icon\")";
Debug.ShouldStop(32);
mostCurrent._ivsearch.setBackground((android.graphics.drawable.Drawable)(mostCurrent._fungsi._getdrawable(mostCurrent.activityBA,"search_icon")));
 BA.debugLineNum = 103;BA.debugLine="ivMainLogo.Background = Fungsi.GetDrawable(\"logo_top\")";
Debug.ShouldStop(64);
mostCurrent._ivmainlogo.setBackground((android.graphics.drawable.Drawable)(mostCurrent._fungsi._getdrawable(mostCurrent.activityBA,"logo_top")));
 BA.debugLineNum = 113;BA.debugLine="init_category";
Debug.ShouldStop(65536);
_init_category();
 BA.debugLineNum = 114;BA.debugLine="DoEvents";
Debug.ShouldStop(131072);
anywheresoftware.b4a.keywords.Common.DoEvents();
 BA.debugLineNum = 115;BA.debugLine="init_ListCategory";
Debug.ShouldStop(262144);
_init_listcategory();
 BA.debugLineNum = 116;BA.debugLine="DoEvents";
Debug.ShouldStop(524288);
anywheresoftware.b4a.keywords.Common.DoEvents();
 BA.debugLineNum = 117;BA.debugLine="End Sub";
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
public static boolean  _activity_keypress(int _keycode) throws Exception{
try {
		Debug.PushSubsStack("Activity_KeyPress (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,123);
Debug.locals.put("KeyCode", _keycode);
 BA.debugLineNum = 123;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean 'return true if you want To consume the event";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 124;BA.debugLine="If KeyCode = 4 Then";
Debug.ShouldStop(134217728);
if (_keycode==4) { 
 BA.debugLineNum = 125;BA.debugLine="If BDUtama.Msgbox(\"Moviebay\", \"Do you want to exit ?\", \"Yes\", \"No\", \"\", Fungsi.GetDrawable(\"ic_action_warning\")) = DialogResponse.POSITIVE Then";
Debug.ShouldStop(268435456);
if (mostCurrent._bdutama.MsgBox("Moviebay","Do you want to exit ?","Yes","No","",mostCurrent._fungsi._getdrawable(mostCurrent.activityBA,"ic_action_warning"),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 BA.debugLineNum = 126;BA.debugLine="ExitApplication";
Debug.ShouldStop(536870912);
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 BA.debugLineNum = 129;BA.debugLine="Return True";
Debug.ShouldStop(1);
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 BA.debugLineNum = 131;BA.debugLine="End Sub";
Debug.ShouldStop(4);
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
		Debug.PushSubsStack("Activity_Pause (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,133);
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 133;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(16);
 BA.debugLineNum = 135;BA.debugLine="End Sub";
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
public static String  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,119);
 BA.debugLineNum = 119;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(4194304);
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
public static String  _cek_menu() throws Exception{
try {
		Debug.PushSubsStack("Cek_Menu (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,302);
 BA.debugLineNum = 302;BA.debugLine="Sub Cek_Menu";
Debug.ShouldStop(8192);
 BA.debugLineNum = 303;BA.debugLine="If iTampil1=0 Then";
Debug.ShouldStop(16384);
if (_itampil1==0) { 
 BA.debugLineNum = 304;BA.debugLine="iTampil1 = 1";
Debug.ShouldStop(32768);
_itampil1 = (int) (1);
 BA.debugLineNum = 305;BA.debugLine="pUtama.SetLayoutAnimated(500, 0%x, 0, 180%x, 100%y)";
Debug.ShouldStop(65536);
mostCurrent._putama.SetLayoutAnimated((int) (500),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (0),mostCurrent.activityBA),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (180),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 }else {
 BA.debugLineNum = 307;BA.debugLine="iTampil1 = 0";
Debug.ShouldStop(262144);
_itampil1 = (int) (0);
 BA.debugLineNum = 308;BA.debugLine="pUtama.SetLayoutAnimated(500, -80%x, 0, 180%x, 100%y)";
Debug.ShouldStop(524288);
mostCurrent._putama.SetLayoutAnimated((int) (500),(int) (-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (80),mostCurrent.activityBA)),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (180),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 };
 BA.debugLineNum = 310;BA.debugLine="End Sub";
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
public static String  _createpanel(anywheresoftware.b4a.objects.collections.List _menutype) throws Exception{
try {
		Debug.PushSubsStack("CreatePanel (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,137);
int _ijumpanel = 0;
String[] _strpisah = null;
int _i = 0;
anywheresoftware.b4a.objects.PanelWrapper _pisifilm = null;
it.giuseppe.salvi.gridview.library.core.GridViewActivityWrapper _gvpanel = null;
Debug.locals.put("MenuType", _menutype);
 BA.debugLineNum = 137;BA.debugLine="Sub CreatePanel(MenuType As List)";
Debug.ShouldStop(256);
 BA.debugLineNum = 138;BA.debugLine="Dim iJumPanel		As Int=0";
Debug.ShouldStop(512);
_ijumpanel = (int) (0);Debug.locals.put("iJumPanel", _ijumpanel);Debug.locals.put("iJumPanel", _ijumpanel);
 BA.debugLineNum = 139;BA.debugLine="DoEvents";
Debug.ShouldStop(1024);
anywheresoftware.b4a.keywords.Common.DoEvents();
 BA.debugLineNum = 140;BA.debugLine="Dim strPisah()		As String";
Debug.ShouldStop(2048);
_strpisah = new String[(int) (0)];
java.util.Arrays.fill(_strpisah,"");Debug.locals.put("strPisah", _strpisah);
 BA.debugLineNum = 142;BA.debugLine="pMenuMain.RemoveAllViews";
Debug.ShouldStop(8192);
mostCurrent._pmenumain.RemoveAllViews();
 BA.debugLineNum = 143;BA.debugLine="pIsiTab.RemoveAllViews";
Debug.ShouldStop(16384);
mostCurrent._pisitab.RemoveAllViews();
 BA.debugLineNum = 147;BA.debugLine="iJumPanel = MenuType.Size";
Debug.ShouldStop(262144);
_ijumpanel = _menutype.getSize();Debug.locals.put("iJumPanel", _ijumpanel);
 BA.debugLineNum = 165;BA.debugLine="container.Initialize";
Debug.ShouldStop(16);
mostCurrent._container.Initialize(mostCurrent.activityBA);
 BA.debugLineNum = 167;BA.debugLine="mapPager.Clear";
Debug.ShouldStop(64);
mostCurrent._mappager.Clear();
 BA.debugLineNum = 168;BA.debugLine="mapGrid.Clear";
Debug.ShouldStop(128);
mostCurrent._mapgrid.Clear();
 BA.debugLineNum = 170;BA.debugLine="For i=0 To iJumPanel-1";
Debug.ShouldStop(512);
{
final int step107 = 1;
final int limit107 = (int) (_ijumpanel-1);
for (_i = (int) (0); (step107 > 0 && _i <= limit107) || (step107 < 0 && _i >= limit107); _i = ((int)(0 + _i + step107))) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 171;BA.debugLine="Dim pIsiFilm 	As Panel";
Debug.ShouldStop(1024);
_pisifilm = new anywheresoftware.b4a.objects.PanelWrapper();Debug.locals.put("pIsiFilm", _pisifilm);
 BA.debugLineNum = 172;BA.debugLine="Dim gvPanel 	As PhotoGridView";
Debug.ShouldStop(2048);
_gvpanel = new it.giuseppe.salvi.gridview.library.core.GridViewActivityWrapper();Debug.locals.put("gvPanel", _gvpanel);
 BA.debugLineNum = 174;BA.debugLine="pIsiFilm.Initialize(\"\")";
Debug.ShouldStop(8192);
_pisifilm.Initialize(mostCurrent.activityBA,"");
 BA.debugLineNum = 175;BA.debugLine="gvPanel.Initialize(\"\")";
Debug.ShouldStop(16384);
_gvpanel.Initialize(mostCurrent.activityBA,"");
 BA.debugLineNum = 177;BA.debugLine="container.AddPage(pIsiFilm, MenuType.Get(i))";
Debug.ShouldStop(65536);
mostCurrent._container.AddPage((android.view.View)(_pisifilm.getObject()),BA.ObjectToString(_menutype.Get(_i)));
 BA.debugLineNum = 178;BA.debugLine="mapPager.Put(i, pIsiFilm)";
Debug.ShouldStop(131072);
mostCurrent._mappager.Put((Object)(_i),(Object)(_pisifilm.getObject()));
 BA.debugLineNum = 179;BA.debugLine="mapGrid.Put(i, gvPanel)";
Debug.ShouldStop(262144);
mostCurrent._mapgrid.Put((Object)(_i),(Object)(_gvpanel.getObject()));
 BA.debugLineNum = 180;BA.debugLine="DoEvents";
Debug.ShouldStop(524288);
anywheresoftware.b4a.keywords.Common.DoEvents();
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 183;BA.debugLine="pager.Initialize(container, \"Pager\")";
Debug.ShouldStop(4194304);
mostCurrent._pager.Initialize(mostCurrent.activityBA,mostCurrent._container,"Pager");
 BA.debugLineNum = 191;BA.debugLine="tabs.Initialize(pager)";
Debug.ShouldStop(1073741824);
mostCurrent._tabs.Initialize(mostCurrent.activityBA,mostCurrent._pager);
 BA.debugLineNum = 192;BA.debugLine="tabs.TextSize = 14";
Debug.ShouldStop(-2147483648);
mostCurrent._tabs.setTextSize((float) (14));
 BA.debugLineNum = 193;BA.debugLine="tabs.TabPaddingBottom = 11dip";
Debug.ShouldStop(1);
mostCurrent._tabs.setTabPaddingBottom(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (11)));
 BA.debugLineNum = 194;BA.debugLine="tabs.TabPaddingTop = 13dip";
Debug.ShouldStop(2);
mostCurrent._tabs.setTabPaddingTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (13)));
 BA.debugLineNum = 195;BA.debugLine="tabs.LineHeight = 3dip";
Debug.ShouldStop(4);
mostCurrent._tabs.setLineHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 BA.debugLineNum = 196;BA.debugLine="tabs.UpperCaseTitle = True";
Debug.ShouldStop(8);
mostCurrent._tabs.setUpperCaseTitle(anywheresoftware.b4a.keywords.Common.True);
 BA.debugLineNum = 197;BA.debugLine="tabs.LineColorCenter = Colors.White";
Debug.ShouldStop(16);
mostCurrent._tabs.setLineColorCenter(anywheresoftware.b4a.keywords.Common.Colors.White);
 BA.debugLineNum = 198;BA.debugLine="tabs.Color = Colors.ARGB(232, 152, 20, 33)";
Debug.ShouldStop(32);
mostCurrent._tabs.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (232),(int) (152),(int) (20),(int) (33)));
 BA.debugLineNum = 199;BA.debugLine="pIsiTab.AddView(tabs, 0, 0, pIsiTab.Width, pIsiTab.Height)";
Debug.ShouldStop(64);
mostCurrent._pisitab.AddView((android.view.View)(mostCurrent._tabs.getObject()),(int) (0),(int) (0),mostCurrent._pisitab.getWidth(),mostCurrent._pisitab.getHeight());
 BA.debugLineNum = 200;BA.debugLine="pMenuMain.AddView(pager, 15dip, 15dip, pMenuMain.Width - 15dip, pMenuMain.Height - 15dip)";
Debug.ShouldStop(128);
mostCurrent._pmenumain.AddView((android.view.View)(mostCurrent._pager.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)),(int) (mostCurrent._pmenumain.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15))),(int) (mostCurrent._pmenumain.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15))));
 BA.debugLineNum = 202;BA.debugLine="If MenuType.Size > 0 Then";
Debug.ShouldStop(512);
if (_menutype.getSize()>0) { 
 BA.debugLineNum = 203;BA.debugLine="pager.CurrentPage = 0";
Debug.ShouldStop(1024);
mostCurrent._pager.setCurrentPage((int) (0));
 BA.debugLineNum = 204;BA.debugLine="pager.GotoPage(0, True)";
Debug.ShouldStop(2048);
mostCurrent._pager.GotoPage((int) (0),anywheresoftware.b4a.keywords.Common.True);
 BA.debugLineNum = 205;BA.debugLine="Pager_PageChanged(0)";
Debug.ShouldStop(4096);
_pager_pagechanged((int) (0));
 };
 BA.debugLineNum = 208;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 13;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 18;BA.debugLine="Private container 		As AHPageContainer";
mostCurrent._container = new de.amberhome.viewpager.AHPageContainer();
 //BA.debugLineNum = 19;BA.debugLine="Private pager 			As AHViewPager";
mostCurrent._pager = new de.amberhome.viewpager.AHViewPager();
 //BA.debugLineNum = 20;BA.debugLine="Private tabs 			As AHViewPagerTabs";
mostCurrent._tabs = new de.amberhome.viewpager.AHViewPagerTabs();
 //BA.debugLineNum = 21;BA.debugLine="Private FILL_PARENT 	As Int : FILL_PARENT = -1";
_fill_parent = 0;
 //BA.debugLineNum = 21;BA.debugLine="Private FILL_PARENT 	As Int : FILL_PARENT = -1";
_fill_parent = (int) (-1);
 //BA.debugLineNum = 22;BA.debugLine="Private WRAP_CONTENT 	As Int : WRAP_CONTENT = -2";
_wrap_content = 0;
 //BA.debugLineNum = 22;BA.debugLine="Private WRAP_CONTENT 	As Int : WRAP_CONTENT = -2";
_wrap_content = (int) (-2);
 //BA.debugLineNum = 23;BA.debugLine="Private pMainAtas 		As Panel";
mostCurrent._pmainatas = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private pMenuMain 		As Panel";
mostCurrent._pmenumain = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private BDUtama			As BetterDialogs";
mostCurrent._bdutama = new flm.b4a.betterdialogs.BetterDialogs();
 //BA.debugLineNum = 26;BA.debugLine="Private ivMenu 			As ImageView";
mostCurrent._ivmenu = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private ivSearch 		As ImageView";
mostCurrent._ivsearch = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private ivMainLogo	 	As ImageView";
mostCurrent._ivmainlogo = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private pLogo			As Panel";
mostCurrent._plogo = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private lblNamaFilm 	As Label";
mostCurrent._lblnamafilm = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private AB				As ClsActionBar";
mostCurrent._ab = new newmoviebay.mncplaymedia.com.clsactionbar();
 //BA.debugLineNum = 32;BA.debugLine="Private HSV				As HorizontalScrollView";
mostCurrent._hsv = new anywheresoftware.b4a.objects.HorizontalScrollViewWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private lblUser			As Label";
mostCurrent._lbluser = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private pMenu 			As Panel";
mostCurrent._pmenu = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private iTampil1 		As Int=0";
_itampil1 = (int) (0);
 //BA.debugLineNum = 36;BA.debugLine="Private iTampil2 		As Int=0";
_itampil2 = (int) (0);
 //BA.debugLineNum = 37;BA.debugLine="Private pUtama			As Panel";
mostCurrent._putama = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private lvUtama 		As ListView";
mostCurrent._lvutama = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private lvCateFilm 		As ListView";
mostCurrent._lvcatefilm = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private pIsiTab 		As Panel";
mostCurrent._pisitab = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private ivClose			As ImageView";
mostCurrent._ivclose = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private ivMovies 		As ImageView";
mostCurrent._ivmovies = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private ivBack 			As ImageView";
mostCurrent._ivback = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private mpList 			As Map";
mostCurrent._mplist = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 45;BA.debugLine="Private streExt 		As StringFunctions";
mostCurrent._streext = new adr.stringfunctions.stringfunctions();
 //BA.debugLineNum = 46;BA.debugLine="Private ctoast			As ToastMessageShow";
mostCurrent._ctoast = new anywheresoftware.giuseppe.salvi.toast.ToastMessageShowWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private etEmail 		As EditText";
mostCurrent._etemail = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private etPass 			As EditText";
mostCurrent._etpass = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private lstCate 		As List";
mostCurrent._lstcate = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 50;BA.debugLine="Private bReg			As Button";
mostCurrent._breg = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private DirFile			As String";
mostCurrent._dirfile = "";
 //BA.debugLineNum = 52;BA.debugLine="Private Namafile		As String";
mostCurrent._namafile = "";
 //BA.debugLineNum = 53;BA.debugLine="Private boundary 		As String";
mostCurrent._boundary = "";
 //BA.debugLineNum = 54;BA.debugLine="Private iAwalCate		As Boolean=True";
_iawalcate = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 55;BA.debugLine="Private nilai			As String";
mostCurrent._nilai = "";
 //BA.debugLineNum = 56;BA.debugLine="Private mapPager		As Map";
mostCurrent._mappager = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 57;BA.debugLine="Private mapGrid			As Map";
mostCurrent._mapgrid = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 58;BA.debugLine="Private picture			As Map";
mostCurrent._picture = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 59;BA.debugLine="Private ivUser 			As ImageView";
mostCurrent._ivuser = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 60;BA.debugLine="Private lblAction 		As Label";
mostCurrent._lblaction = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 61;BA.debugLine="Private lblLast 		As Label";
mostCurrent._lbllast = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 62;BA.debugLine="Private Params			As BD_CustomDlgParams";
mostCurrent._params = new flm.b4a.betterdialogs.BetterDialogs.CustomDlgParams();
 //BA.debugLineNum = 63;BA.debugLine="Private BD				As BetterDialogs";
mostCurrent._bd = new flm.b4a.betterdialogs.BetterDialogs();
 //BA.debugLineNum = 64;BA.debugLine="Private wvPay 			As WebView";
mostCurrent._wvpay = new anywheresoftware.b4a.objects.WebViewWrapper();
 //BA.debugLineNum = 65;BA.debugLine="Private ivPremium 		As ImageView";
mostCurrent._ivpremium = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 66;BA.debugLine="Private Label1 			As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 67;BA.debugLine="Private lblLg 			As Label";
mostCurrent._lbllg = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 68;BA.debugLine="End Sub";
return "";
}
public static String  _init_catefilm() throws Exception{
try {
		Debug.PushSubsStack("init_catefilm (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,330);
 BA.debugLineNum = 330;BA.debugLine="Sub init_catefilm";
Debug.ShouldStop(512);
 BA.debugLineNum = 351;BA.debugLine="End Sub";
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
public static String  _init_category() throws Exception{
try {
		Debug.PushSubsStack("init_category (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,275);
newmoviebay.mncplaymedia.com.mnchttpjob _homejob = null;
String _strenc = "";
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _gen = null;
anywheresoftware.b4a.objects.collections.Map _mcate = null;
 BA.debugLineNum = 275;BA.debugLine="Sub init_category";
Debug.ShouldStop(262144);
 BA.debugLineNum = 277;BA.debugLine="Dim HomeJob As MNChttpJob";
Debug.ShouldStop(1048576);
_homejob = new newmoviebay.mncplaymedia.com.mnchttpjob();Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 278;BA.debugLine="Dim	strEnc	As String";
Debug.ShouldStop(2097152);
_strenc = "";Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 279;BA.debugLine="Dim Gen		As JSONGenerator";
Debug.ShouldStop(4194304);
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();Debug.locals.put("Gen", _gen);
 BA.debugLineNum = 280;BA.debugLine="Dim mcate	As Map";
Debug.ShouldStop(8388608);
_mcate = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("mcate", _mcate);
 BA.debugLineNum = 282;BA.debugLine="mcate.Initialize";
Debug.ShouldStop(33554432);
_mcate.Initialize();
 BA.debugLineNum = 283;BA.debugLine="mcate.Clear";
Debug.ShouldStop(67108864);
_mcate.Clear();
 BA.debugLineNum = 285;BA.debugLine="mcate.Put(Fungsi.mpList.Get(\"sliding\"), \"1\")";
Debug.ShouldStop(268435456);
_mcate.Put(mostCurrent._fungsi._mplist.Get((Object)("sliding")),(Object)("1"));
 BA.debugLineNum = 286;BA.debugLine="Gen.Initialize(mcate)";
Debug.ShouldStop(536870912);
_gen.Initialize(_mcate);
 BA.debugLineNum = 288;BA.debugLine="HomeJob.Initialize(\"Category\", Me)";
Debug.ShouldStop(-2147483648);
_homejob._initialize(processBA,"Category",utama.getObject());
 BA.debugLineNum = 290;BA.debugLine="Try";
Debug.ShouldStop(2);
try { BA.debugLineNum = 291;BA.debugLine="HomeJob.Tag = \"Category\"";
Debug.ShouldStop(4);
_homejob._tag = (Object)("Category");Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 292;BA.debugLine="strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(2))";
Debug.ShouldStop(8);
_strenc = mostCurrent._fungsi._prosesenkripsi(mostCurrent.activityBA,_gen.ToPrettyString((int) (2)));Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 293;BA.debugLine="If strEnc = \"Enkripsi Failed\" Then Return";
Debug.ShouldStop(16);
if ((_strenc).equals("Enkripsi Failed")) { 
if (true) return "";};
 BA.debugLineNum = 295;BA.debugLine="HomeJob.PostString(Fungsi.mpList.Get (\"CateURL\"), \"teks=\" & strEnc)";
Debug.ShouldStop(64);
_homejob._poststring(BA.ObjectToString(mostCurrent._fungsi._mplist.Get((Object)("CateURL"))),"teks="+_strenc);
 BA.debugLineNum = 296;BA.debugLine="HomeJob.GetRequest.SetHeader(\"Cache-Control\", \"no-cache, must-revalidate\")";
Debug.ShouldStop(128);
_homejob._getrequest().SetHeader("Cache-Control","no-cache, must-revalidate");
 BA.debugLineNum = 297;BA.debugLine="HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60";
Debug.ShouldStop(256);
_homejob._getrequest().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 } 
       catch (Exception e166) {
			processBA.setLastException(e166); BA.debugLineNum = 299;BA.debugLine="Msgbox2(\"Cetgory Failure !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(1024);
anywheresoftware.b4a.keywords.Common.Msgbox2("Cetgory Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 };
 BA.debugLineNum = 301;BA.debugLine="End Sub";
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
public static String  _init_listcategory() throws Exception{
try {
		Debug.PushSubsStack("init_ListCategory (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,387);
newmoviebay.mncplaymedia.com.mnchttpjob _homejob = null;
String _strenc = "";
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _gen = null;
anywheresoftware.b4a.objects.collections.Map _mlist = null;
 BA.debugLineNum = 387;BA.debugLine="Sub init_ListCategory";
Debug.ShouldStop(4);
 BA.debugLineNum = 389;BA.debugLine="Dim HomeJob As MNChttpJob";
Debug.ShouldStop(16);
_homejob = new newmoviebay.mncplaymedia.com.mnchttpjob();Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 390;BA.debugLine="Dim	strEnc	As String";
Debug.ShouldStop(32);
_strenc = "";Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 391;BA.debugLine="Dim Gen		As JSONGenerator";
Debug.ShouldStop(64);
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();Debug.locals.put("Gen", _gen);
 BA.debugLineNum = 392;BA.debugLine="Dim mList	As Map";
Debug.ShouldStop(128);
_mlist = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("mList", _mlist);
 BA.debugLineNum = 394;BA.debugLine="mList.Initialize";
Debug.ShouldStop(512);
_mlist.Initialize();
 BA.debugLineNum = 395;BA.debugLine="mList.Clear";
Debug.ShouldStop(1024);
_mlist.Clear();
 BA.debugLineNum = 396;BA.debugLine="mList.Put(Fungsi.mpList.Get(\"sliding\"), \"0\")";
Debug.ShouldStop(2048);
_mlist.Put(mostCurrent._fungsi._mplist.Get((Object)("sliding")),(Object)("0"));
 BA.debugLineNum = 397;BA.debugLine="Gen.Initialize(mList)";
Debug.ShouldStop(4096);
_gen.Initialize(_mlist);
 BA.debugLineNum = 399;BA.debugLine="HomeJob.Initialize(\"PilihCategory\", Me)";
Debug.ShouldStop(16384);
_homejob._initialize(processBA,"PilihCategory",utama.getObject());
 BA.debugLineNum = 401;BA.debugLine="Try";
Debug.ShouldStop(65536);
try { BA.debugLineNum = 402;BA.debugLine="HomeJob.Tag = \"PilihCategory\"";
Debug.ShouldStop(131072);
_homejob._tag = (Object)("PilihCategory");Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 403;BA.debugLine="strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(2))";
Debug.ShouldStop(262144);
_strenc = mostCurrent._fungsi._prosesenkripsi(mostCurrent.activityBA,_gen.ToPrettyString((int) (2)));Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 404;BA.debugLine="If strEnc = \"Enkripsi Failed\" Then Return";
Debug.ShouldStop(524288);
if ((_strenc).equals("Enkripsi Failed")) { 
if (true) return "";};
 BA.debugLineNum = 406;BA.debugLine="HomeJob.PostString(Fungsi.mpList.Get (\"ListCateURL\"), \"teks=\" & strEnc)";
Debug.ShouldStop(2097152);
_homejob._poststring(BA.ObjectToString(mostCurrent._fungsi._mplist.Get((Object)("ListCateURL"))),"teks="+_strenc);
 BA.debugLineNum = 407;BA.debugLine="HomeJob.GetRequest.SetHeader(\"Cache-Control\", \"no-cache, must-revalidate\")";
Debug.ShouldStop(4194304);
_homejob._getrequest().SetHeader("Cache-Control","no-cache, must-revalidate");
 BA.debugLineNum = 408;BA.debugLine="HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60";
Debug.ShouldStop(8388608);
_homejob._getrequest().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 } 
       catch (Exception e241) {
			processBA.setLastException(e241); BA.debugLineNum = 410;BA.debugLine="Msgbox2(\"Cetgory Failure !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(33554432);
anywheresoftware.b4a.keywords.Common.Msgbox2("Cetgory Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 };
 BA.debugLineNum = 412;BA.debugLine="End Sub";
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
public static String  _init_menu() throws Exception{
try {
		Debug.PushSubsStack("init_menu (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,248);
 BA.debugLineNum = 248;BA.debugLine="Sub init_menu";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 274;BA.debugLine="End Sub";
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
public static String  _init_slug(String _movies) throws Exception{
try {
		Debug.PushSubsStack("Init_slug (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,790);
newmoviebay.mncplaymedia.com.mnchttpjob _homejob = null;
String _strenc = "";
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _gen = null;
anywheresoftware.b4a.objects.collections.Map _mcate = null;
Debug.locals.put("Movies", _movies);
 BA.debugLineNum = 790;BA.debugLine="Sub Init_slug (Movies As String)";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 792;BA.debugLine="Dim HomeJob As MNChttpJob";
Debug.ShouldStop(8388608);
_homejob = new newmoviebay.mncplaymedia.com.mnchttpjob();Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 793;BA.debugLine="Dim	strEnc	As String";
Debug.ShouldStop(16777216);
_strenc = "";Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 794;BA.debugLine="Dim Gen		As JSONGenerator";
Debug.ShouldStop(33554432);
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();Debug.locals.put("Gen", _gen);
 BA.debugLineNum = 795;BA.debugLine="Dim mCate	As Map";
Debug.ShouldStop(67108864);
_mcate = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("mCate", _mcate);
 BA.debugLineNum = 797;BA.debugLine="mCate.Initialize";
Debug.ShouldStop(268435456);
_mcate.Initialize();
 BA.debugLineNum = 798;BA.debugLine="mCate.Clear";
Debug.ShouldStop(536870912);
_mcate.Clear();
 BA.debugLineNum = 800;BA.debugLine="Gen.Initialize(mCate)";
Debug.ShouldStop(-2147483648);
_gen.Initialize(_mcate);
 BA.debugLineNum = 802;BA.debugLine="HomeJob.Initialize(\"SlugMovies\", Me)";
Debug.ShouldStop(2);
_homejob._initialize(processBA,"SlugMovies",utama.getObject());
 BA.debugLineNum = 804;BA.debugLine="Try";
Debug.ShouldStop(8);
try { BA.debugLineNum = 806;BA.debugLine="HomeJob.Tag = \"SlugMovies\"";
Debug.ShouldStop(32);
_homejob._tag = (Object)("SlugMovies");Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 807;BA.debugLine="strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(2))";
Debug.ShouldStop(64);
_strenc = mostCurrent._fungsi._prosesenkripsi(mostCurrent.activityBA,_gen.ToPrettyString((int) (2)));Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 808;BA.debugLine="If strEnc = \"Enkripsi Failed\" Then Return";
Debug.ShouldStop(128);
if ((_strenc).equals("Enkripsi Failed")) { 
if (true) return "";};
 BA.debugLineNum = 809;BA.debugLine="HomeJob.PostString(Fungsi.mpList.Get(\"SlugURL\"), \"teks=\" & strEnc)";
Debug.ShouldStop(256);
_homejob._poststring(BA.ObjectToString(mostCurrent._fungsi._mplist.Get((Object)("SlugURL"))),"teks="+_strenc);
 BA.debugLineNum = 810;BA.debugLine="HomeJob.GetRequest.SetHeader(\"Cache-Control\", \"no-cache, must-revalidate\")";
Debug.ShouldStop(512);
_homejob._getrequest().SetHeader("Cache-Control","no-cache, must-revalidate");
 BA.debugLineNum = 811;BA.debugLine="HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60";
Debug.ShouldStop(1024);
_homejob._getrequest().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 } 
       catch (Exception e498) {
			processBA.setLastException(e498); BA.debugLineNum = 813;BA.debugLine="Msgbox2(\"Cetgory Failure !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(4096);
anywheresoftware.b4a.keywords.Common.Msgbox2("Cetgory Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 };
 BA.debugLineNum = 815;BA.debugLine="End Sub";
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
public static String  _ivdropdown_click() throws Exception{
try {
		Debug.PushSubsStack("ivDropDown_Click (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,326);
 BA.debugLineNum = 326;BA.debugLine="Sub ivDropDown_Click";
Debug.ShouldStop(32);
 BA.debugLineNum = 327;BA.debugLine="lblNamaFilm_Click";
Debug.ShouldStop(64);
_lblnamafilm_click();
 BA.debugLineNum = 328;BA.debugLine="End Sub";
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
public static String  _ivmenu_click() throws Exception{
try {
		Debug.PushSubsStack("ivMenu_Click (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,312);
 BA.debugLineNum = 312;BA.debugLine="Sub ivMenu_Click";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 313;BA.debugLine="Cek_Menu";
Debug.ShouldStop(16777216);
_cek_menu();
 BA.debugLineNum = 314;BA.debugLine="End Sub";
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
public static String  _ivsearch_click() throws Exception{
try {
		Debug.PushSubsStack("ivSearch_Click (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,414);
 BA.debugLineNum = 414;BA.debugLine="Sub ivSearch_Click";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 415;BA.debugLine="StartActivity(\"MenuSearch\")";
Debug.ShouldStop(1073741824);
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)("MenuSearch"));
 BA.debugLineNum = 416;BA.debugLine="End Sub";
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
public static String  _jobdone(newmoviebay.mncplaymedia.com.mnchttpjob _mnchttp) throws Exception{
try {
		Debug.PushSubsStack("JobDone (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,450);
adr.stringfunctions.stringfunctions _sf = null;
String _stmp = "";
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.Map _mresult = null;
byte[] _btmp = null;
anywheresoftware.b4a.agraham.byteconverter.ByteConverter _bctmp = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
anywheresoftware.b4a.objects.collections.List _a1 = null;
anywheresoftware.b4a.objects.collections.List _b1 = null;
anywheresoftware.b4a.objects.collections.Map _colchannel = null;
anywheresoftware.b4a.keywords.constants.TypefaceWrapper _timefont = null;
anywheresoftware.b4a.objects.collections.Map _colname = null;
anywheresoftware.b4a.objects.collections.List _pilih1 = null;
anywheresoftware.b4a.objects.collections.List _pilih2 = null;
anywheresoftware.b4a.objects.collections.Map _colcategory = null;
anywheresoftware.b4a.keywords.constants.TypefaceWrapper _timefont1 = null;
anywheresoftware.b4a.objects.collections.List _cate1 = null;
anywheresoftware.b4a.objects.collections.List _cate2 = null;
String _cate3 = "";
anywheresoftware.b4a.objects.collections.Map _colfilm = null;
anywheresoftware.b4a.objects.collections.List _cateslug = null;
anywheresoftware.b4a.objects.collections.List _slide1 = null;
anywheresoftware.b4a.objects.collections.List _slide2 = null;
anywheresoftware.b4a.objects.collections.Map _colslide = null;
anywheresoftware.b4a.objects.collections.Map _page1 = null;
anywheresoftware.b4a.objects.collections.List _page2 = null;
String _subcate = "";
int _x1 = 0;
int _x2 = 0;
anywheresoftware.b4a.objects.PanelWrapper _plugpanel = null;
it.giuseppe.salvi.gridview.library.core.GridViewActivityWrapper _pluggrid = null;
String[] _strpisah = null;
anywheresoftware.b4a.objects.collections.Map _contentvideo = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _colhal = null;
anywheresoftware.b4a.objects.collections.Map _new = null;
anywheresoftware.b4a.objects.collections.List _top = null;
String _subkiri = "";
int _k1 = 0;
int _k2 = 0;
anywheresoftware.b4a.objects.collections.Map _colkiri = null;
Debug.locals.put("MNChttp", _mnchttp);
 BA.debugLineNum = 450;BA.debugLine="Sub JobDone (MNChttp As MNChttpJob)";
Debug.ShouldStop(2);
 BA.debugLineNum = 452;BA.debugLine="Dim sf			As StringFunctions";
Debug.ShouldStop(8);
_sf = new adr.stringfunctions.stringfunctions();Debug.locals.put("sf", _sf);
 BA.debugLineNum = 453;BA.debugLine="Dim sTmp 		As String";
Debug.ShouldStop(16);
_stmp = "";Debug.locals.put("sTmp", _stmp);
 BA.debugLineNum = 454;BA.debugLine="Dim parser 		As JSONParser";
Debug.ShouldStop(32);
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();Debug.locals.put("parser", _parser);
 BA.debugLineNum = 455;BA.debugLine="Dim mresult		As Map";
Debug.ShouldStop(64);
_mresult = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("mresult", _mresult);
 BA.debugLineNum = 456;BA.debugLine="Dim bTmp()		As Byte";
Debug.ShouldStop(128);
_btmp = new byte[(int) (0)];
;Debug.locals.put("bTmp", _btmp);
 BA.debugLineNum = 457;BA.debugLine="Dim bcTmp		As ByteConverter";
Debug.ShouldStop(256);
_bctmp = new anywheresoftware.b4a.agraham.byteconverter.ByteConverter();Debug.locals.put("bcTmp", _bctmp);
 BA.debugLineNum = 458;BA.debugLine="Dim su 			As StringUtils";
Debug.ShouldStop(512);
_su = new anywheresoftware.b4a.objects.StringUtils();Debug.locals.put("su", _su);
 BA.debugLineNum = 462;BA.debugLine="If MNChttp.Success = False Then";
Debug.ShouldStop(8192);
if (_mnchttp._success==anywheresoftware.b4a.keywords.Common.False) { 
 BA.debugLineNum = 463;BA.debugLine="If (sf.InString(MNChttp.ErrorMessage, \"timed out\") > 0) OR (sf.InString(MNChttp.ErrorMessage, \"refused\") > 0) OR _ 			 (sf.InString(MNChttp.ErrorMessage, \"Unable to resolve host\") > 0) OR (sf.InString(MNChttp.ErrorMessage, \"UnknownHostException\") > 0) OR _ 			 (sf.InString(MNChttp.ErrorMessage, \"FileNotFound\") > 0) Then";
Debug.ShouldStop(16384);
if ((_sf._vvv4(_mnchttp._errormessage,"timed out")>0) || (_sf._vvv4(_mnchttp._errormessage,"refused")>0) || (_sf._vvv4(_mnchttp._errormessage,"Unable to resolve host")>0) || (_sf._vvv4(_mnchttp._errormessage,"UnknownHostException")>0) || (_sf._vvv4(_mnchttp._errormessage,"FileNotFound")>0)) { 
 BA.debugLineNum = 467;BA.debugLine="Msgbox2(\"Offline Connection or Server Down\", \"MNC Video Record\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(262144);
anywheresoftware.b4a.keywords.Common.Msgbox2("Offline Connection or Server Down","MNC Video Record","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 469;BA.debugLine="If sf.InString(MNChttp.ErrorMessage, \"Not Found\") < 0 Then";
Debug.ShouldStop(1048576);
if (_sf._vvv4(_mnchttp._errormessage,"Not Found")<0) { 
 BA.debugLineNum = 471;BA.debugLine="Msgbox2(\"Unknown Error\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(4194304);
anywheresoftware.b4a.keywords.Common.Msgbox2("Unknown Error","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 };
 };
 BA.debugLineNum = 475;BA.debugLine="mpList.Put(\"Koneksi\", \"Offline\")";
Debug.ShouldStop(67108864);
mostCurrent._mplist.Put((Object)("Koneksi"),(Object)("Offline"));
 BA.debugLineNum = 476;BA.debugLine="MNChttp.Release";
Debug.ShouldStop(134217728);
_mnchttp._release();
 }else {
 BA.debugLineNum = 480;BA.debugLine="Try";
Debug.ShouldStop(-2147483648);
try { BA.debugLineNum = 481;BA.debugLine="bTmp = su.DecodeBase64(MNChttp.GetString)";
Debug.ShouldStop(1);
_btmp = _su.DecodeBase64(_mnchttp._getstring());Debug.locals.put("bTmp", _btmp);
 BA.debugLineNum = 482;BA.debugLine="sTmp = bcTmp.StringFromBytes(bTmp, \"UTF8\")";
Debug.ShouldStop(2);
_stmp = _bctmp.StringFromBytes(_btmp,"UTF8");Debug.locals.put("sTmp", _stmp);
 BA.debugLineNum = 484;BA.debugLine="parser.Initialize(Fungsi.UnWrap(sTmp))";
Debug.ShouldStop(8);
_parser.Initialize(mostCurrent._fungsi._unwrap(mostCurrent.activityBA,_stmp));
 BA.debugLineNum = 485;BA.debugLine="MNChttp.Release";
Debug.ShouldStop(16);
_mnchttp._release();
 } 
       catch (Exception e298) {
			processBA.setLastException(e298); BA.debugLineNum = 487;BA.debugLine="Msgbox2(\"Process Failure !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(64);
anywheresoftware.b4a.keywords.Common.Msgbox2("Process Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 BA.debugLineNum = 488;BA.debugLine="Return";
Debug.ShouldStop(128);
if (true) return "";
 };
 BA.debugLineNum = 491;BA.debugLine="Try";
Debug.ShouldStop(1024);
try { BA.debugLineNum = 492;BA.debugLine="mresult.Initialize";
Debug.ShouldStop(2048);
_mresult.Initialize();
 BA.debugLineNum = 493;BA.debugLine="mresult = parser.NextObject";
Debug.ShouldStop(4096);
_mresult = _parser.NextObject();Debug.locals.put("mresult", _mresult);
 } 
       catch (Exception e305) {
			processBA.setLastException(e305); BA.debugLineNum = 495;BA.debugLine="Msgbox2(\"Process Failure !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(16384);
anywheresoftware.b4a.keywords.Common.Msgbox2("Process Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 BA.debugLineNum = 496;BA.debugLine="Return";
Debug.ShouldStop(32768);
if (true) return "";
 };
 BA.debugLineNum = 499;BA.debugLine="If mresult.Get(\"Result\") <> \"0\" Then";
Debug.ShouldStop(262144);
if ((_mresult.Get((Object)("Result"))).equals((Object)("0")) == false) { 
 BA.debugLineNum = 500;BA.debugLine="Msgbox2(mresult.Get(\"Message\"), \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(524288);
anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToString(_mresult.Get((Object)("Message"))),"Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 503;BA.debugLine="MNChttp.Release";
Debug.ShouldStop(4194304);
_mnchttp._release();
 BA.debugLineNum = 505;BA.debugLine="Select MNChttp.JobName";
Debug.ShouldStop(16777216);
switch (BA.switchObjectToInt(_mnchttp._jobname,"Category","PilihCategory","CateMovies","SlideCate","SlugPager","MenuKiri")) {
case 0:
 BA.debugLineNum = 510;BA.debugLine="Dim a1,b1 As List";
Debug.ShouldStop(536870912);
_a1 = new anywheresoftware.b4a.objects.collections.List();Debug.locals.put("a1", _a1);
_b1 = new anywheresoftware.b4a.objects.collections.List();Debug.locals.put("b1", _b1);
 BA.debugLineNum = 513;BA.debugLine="a1.Initialize";
Debug.ShouldStop(1);
_a1.Initialize();
 BA.debugLineNum = 514;BA.debugLine="a1 = mresult.Get(\"Data\")";
Debug.ShouldStop(2);
_a1.setObject((java.util.List)(_mresult.Get((Object)("Data"))));
 BA.debugLineNum = 516;BA.debugLine="b1.Initialize";
Debug.ShouldStop(8);
_b1.Initialize();
 BA.debugLineNum = 518;BA.debugLine="For Each colChannel As Map In a1";
Debug.ShouldStop(32);
_colchannel = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group318 = _a1;
final int groupLen318 = group318.getSize();
for (int index318 = 0;index318 < groupLen318 ;index318++){
_colchannel.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group318.Get(index318)));
Debug.locals.put("colchannel", _colchannel);
 BA.debugLineNum = 519;BA.debugLine="b1 = colChannel.Get(\"mSubcategories\")";
Debug.ShouldStop(64);
_b1.setObject((java.util.List)(_colchannel.Get((Object)("mSubcategories"))));
 BA.debugLineNum = 520;BA.debugLine="Exit";
Debug.ShouldStop(128);
if (true) break;
 }
Debug.locals.put("colchannel", _colchannel);
;
 BA.debugLineNum = 523;BA.debugLine="Dim TimeFont As Typeface";
Debug.ShouldStop(1024);
_timefont = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();Debug.locals.put("TimeFont", _timefont);
 BA.debugLineNum = 524;BA.debugLine="TimeFont = Typeface.LoadFromAssets (\"gotham-light.ttf\")";
Debug.ShouldStop(2048);
_timefont.setObject((android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("gotham-light.ttf")));
 BA.debugLineNum = 526;BA.debugLine="lvUtama.Clear";
Debug.ShouldStop(8192);
mostCurrent._lvutama.Clear();
 BA.debugLineNum = 527;BA.debugLine="Fungsi.SetDivider(lvUtama, Colors.Transparent, 1dip)";
Debug.ShouldStop(16384);
mostCurrent._fungsi._setdivider(mostCurrent.activityBA,mostCurrent._lvutama,anywheresoftware.b4a.keywords.Common.Colors.Transparent,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)));
 BA.debugLineNum = 529;BA.debugLine="lvUtama.FastScrollEnabled = False";
Debug.ShouldStop(65536);
mostCurrent._lvutama.setFastScrollEnabled(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 530;BA.debugLine="lvUtama.TwoLinesLayout.SecondLabel.TextColor = Colors.Black";
Debug.ShouldStop(131072);
mostCurrent._lvutama.getTwoLinesLayout().SecondLabel.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 BA.debugLineNum = 533;BA.debugLine="For Each colname As Map In b1";
Debug.ShouldStop(1048576);
_colname = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group328 = _b1;
final int groupLen328 = group328.getSize();
for (int index328 = 0;index328 < groupLen328 ;index328++){
_colname.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group328.Get(index328)));
Debug.locals.put("colname", _colname);
 BA.debugLineNum = 534;BA.debugLine="lvUtama.AddTwoLinesAndBitmap(colname.Get(\"name\"),\"\",Null)";
Debug.ShouldStop(2097152);
mostCurrent._lvutama.AddTwoLinesAndBitmap(BA.ObjectToString(_colname.Get((Object)("name"))),"",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 }
Debug.locals.put("colname", _colname);
;
 BA.debugLineNum = 537;BA.debugLine="lvUtama.TwoLinesAndBitmap.SecondLabel.Visible = False";
Debug.ShouldStop(16777216);
mostCurrent._lvutama.getTwoLinesAndBitmap().SecondLabel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 538;BA.debugLine="lvUtama.TwoLinesAndBitmap.ImageView.Gravity = Gravity.CENTER";
Debug.ShouldStop(33554432);
mostCurrent._lvutama.getTwoLinesAndBitmap().ImageView.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 BA.debugLineNum = 539;BA.debugLine="lvUtama.TwoLinesAndBitmap.ImageView.Height = 60dip";
Debug.ShouldStop(67108864);
mostCurrent._lvutama.getTwoLinesAndBitmap().ImageView.setHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
 BA.debugLineNum = 540;BA.debugLine="lvUtama.TwoLinesAndBitmap.ImageView.Width = 35dip";
Debug.ShouldStop(134217728);
mostCurrent._lvutama.getTwoLinesAndBitmap().ImageView.setWidth(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (35)));
 BA.debugLineNum = 541;BA.debugLine="lvUtama.TwoLinesAndBitmap.ItemHeight = 60dip";
Debug.ShouldStop(268435456);
mostCurrent._lvutama.getTwoLinesAndBitmap().setItemHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
 BA.debugLineNum = 542;BA.debugLine="lvUtama.TwoLinesAndBitmap.Label.Typeface = TimeFont";
Debug.ShouldStop(536870912);
mostCurrent._lvutama.getTwoLinesAndBitmap().Label.setTypeface((android.graphics.Typeface)(_timefont.getObject()));
 BA.debugLineNum = 543;BA.debugLine="lvUtama.TwoLinesAndBitmap.Label.TextColor = Colors.White";
Debug.ShouldStop(1073741824);
mostCurrent._lvutama.getTwoLinesAndBitmap().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 BA.debugLineNum = 544;BA.debugLine="lvUtama.TwoLinesAndBitmap.Label.Height = lvUtama.TwoLinesAndBitmap.ImageView.Height";
Debug.ShouldStop(-2147483648);
mostCurrent._lvutama.getTwoLinesAndBitmap().Label.setHeight(mostCurrent._lvutama.getTwoLinesAndBitmap().ImageView.getHeight());
 BA.debugLineNum = 545;BA.debugLine="lvUtama.TwoLinesAndBitmap.Label.Gravity = Gravity.CENTER_VERTICAL";
Debug.ShouldStop(1);
mostCurrent._lvutama.getTwoLinesAndBitmap().Label.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
 BA.debugLineNum = 546;BA.debugLine="lvUtama.TwoLinesAndBitmap.Label.Left = lvUtama.TwoLinesAndBitmap.ImageView.Width + 7dip";
Debug.ShouldStop(2);
mostCurrent._lvutama.getTwoLinesAndBitmap().Label.setLeft((int) (mostCurrent._lvutama.getTwoLinesAndBitmap().ImageView.getWidth()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (7))));
 BA.debugLineNum = 547;BA.debugLine="lvUtama.Height = lvUtama.Size * 60dip";
Debug.ShouldStop(4);
mostCurrent._lvutama.setHeight((int) (mostCurrent._lvutama.getSize()*anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60))));
 break;
case 1:
 BA.debugLineNum = 553;BA.debugLine="Dim Pilih1,pilih2 As List";
Debug.ShouldStop(256);
_pilih1 = new anywheresoftware.b4a.objects.collections.List();Debug.locals.put("Pilih1", _pilih1);
_pilih2 = new anywheresoftware.b4a.objects.collections.List();Debug.locals.put("pilih2", _pilih2);
 BA.debugLineNum = 554;BA.debugLine="lvCateFilm.Clear";
Debug.ShouldStop(512);
mostCurrent._lvcatefilm.Clear();
 BA.debugLineNum = 557;BA.debugLine="Pilih1.Initialize";
Debug.ShouldStop(4096);
_pilih1.Initialize();
 BA.debugLineNum = 558;BA.debugLine="Pilih1 = mresult.Get(\"Data\")";
Debug.ShouldStop(8192);
_pilih1.setObject((java.util.List)(_mresult.Get((Object)("Data"))));
 BA.debugLineNum = 560;BA.debugLine="pilih2.Initialize";
Debug.ShouldStop(32768);
_pilih2.Initialize();
 BA.debugLineNum = 562;BA.debugLine="For Each colCategory As Map In Pilih1";
Debug.ShouldStop(131072);
_colcategory = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group348 = _pilih1;
final int groupLen348 = group348.getSize();
for (int index348 = 0;index348 < groupLen348 ;index348++){
_colcategory.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group348.Get(index348)));
Debug.locals.put("colcategory", _colcategory);
 BA.debugLineNum = 563;BA.debugLine="lvCateFilm.AddSingleLine(colCategory.Get(\"name\"))";
Debug.ShouldStop(262144);
mostCurrent._lvcatefilm.AddSingleLine(BA.ObjectToString(_colcategory.Get((Object)("name"))));
 }
Debug.locals.put("colcategory", _colcategory);
;
 BA.debugLineNum = 567;BA.debugLine="Dim TimeFont1 As Typeface";
Debug.ShouldStop(4194304);
_timefont1 = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();Debug.locals.put("TimeFont1", _timefont1);
 BA.debugLineNum = 571;BA.debugLine="lvCateFilm.SingleLineLayout.Label.Gravity = Gravity.CENTER";
Debug.ShouldStop(67108864);
mostCurrent._lvcatefilm.getSingleLineLayout().Label.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 BA.debugLineNum = 573;BA.debugLine="Fungsi.SetDivider(lvCateFilm, Colors.Transparent, 1dip)";
Debug.ShouldStop(268435456);
mostCurrent._fungsi._setdivider(mostCurrent.activityBA,mostCurrent._lvcatefilm,anywheresoftware.b4a.keywords.Common.Colors.Transparent,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)));
 BA.debugLineNum = 575;BA.debugLine="lvCateFilm.FastScrollEnabled = False";
Debug.ShouldStop(1073741824);
mostCurrent._lvcatefilm.setFastScrollEnabled(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 584;BA.debugLine="If lvCateFilm.Size > 0 Then";
Debug.ShouldStop(128);
if (mostCurrent._lvcatefilm.getSize()>0) { 
 BA.debugLineNum = 585;BA.debugLine="lblNamaFilm.Text = lvCateFilm.GetItem(0)";
Debug.ShouldStop(256);
mostCurrent._lblnamafilm.setText(mostCurrent._lvcatefilm.GetItem((int) (0)));
 BA.debugLineNum = 586;BA.debugLine="lvCateFilm_ItemClick(0, lvCateFilm.GetItem(0) & \"#Awal\")";
Debug.ShouldStop(512);
_lvcatefilm_itemclick((int) (0),(Object)(BA.ObjectToString(mostCurrent._lvcatefilm.GetItem((int) (0)))+"#Awal"));
 };
 break;
case 2:
 BA.debugLineNum = 593;BA.debugLine="Dim Cate1,Cate2 As List";
Debug.ShouldStop(65536);
_cate1 = new anywheresoftware.b4a.objects.collections.List();Debug.locals.put("Cate1", _cate1);
_cate2 = new anywheresoftware.b4a.objects.collections.List();Debug.locals.put("Cate2", _cate2);
 BA.debugLineNum = 594;BA.debugLine="Dim Cate3 As String";
Debug.ShouldStop(131072);
_cate3 = "";Debug.locals.put("Cate3", _cate3);
 BA.debugLineNum = 595;BA.debugLine="Cate1.Initialize";
Debug.ShouldStop(262144);
_cate1.Initialize();
 BA.debugLineNum = 596;BA.debugLine="Cate1 = mresult.Get(\"Data\")";
Debug.ShouldStop(524288);
_cate1.setObject((java.util.List)(_mresult.Get((Object)("Data"))));
 BA.debugLineNum = 598;BA.debugLine="Cate2.Initialize";
Debug.ShouldStop(2097152);
_cate2.Initialize();
 BA.debugLineNum = 600;BA.debugLine="For Each colFilm As Map In Cate1";
Debug.ShouldStop(8388608);
_colfilm = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group365 = _cate1;
final int groupLen365 = group365.getSize();
for (int index365 = 0;index365 < groupLen365 ;index365++){
_colfilm.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group365.Get(index365)));
Debug.locals.put("colfilm", _colfilm);
 BA.debugLineNum = 601;BA.debugLine="Cate2 = colFilm.Get(\"mSubcategories\")";
Debug.ShouldStop(16777216);
_cate2.setObject((java.util.List)(_colfilm.Get((Object)("mSubcategories"))));
 BA.debugLineNum = 602;BA.debugLine="Cate3 = colFilm.Get(\"name\")";
Debug.ShouldStop(33554432);
_cate3 = BA.ObjectToString(_colfilm.Get((Object)("name")));Debug.locals.put("Cate3", _cate3);
 BA.debugLineNum = 603;BA.debugLine="If MNChttp.Tag = colFilm.Get(\"name\") Then";
Debug.ShouldStop(67108864);
if ((_mnchttp._tag).equals(_colfilm.Get((Object)("name")))) { 
 BA.debugLineNum = 604;BA.debugLine="nilai = colFilm.Get(\"name\")";
Debug.ShouldStop(134217728);
mostCurrent._nilai = BA.ObjectToString(_colfilm.Get((Object)("name")));
 BA.debugLineNum = 605;BA.debugLine="Exit";
Debug.ShouldStop(268435456);
if (true) break;
 };
 }
Debug.locals.put("colfilm", _colfilm);
;
 BA.debugLineNum = 609;BA.debugLine="Dim CateSlug As List";
Debug.ShouldStop(1);
_cateslug = new anywheresoftware.b4a.objects.collections.List();Debug.locals.put("CateSlug", _cateslug);
 BA.debugLineNum = 611;BA.debugLine="CateSlug.Initialize";
Debug.ShouldStop(4);
_cateslug.Initialize();
 BA.debugLineNum = 613;BA.debugLine="For Each colname As Map In Cate2";
Debug.ShouldStop(16);
_colname = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group375 = _cate2;
final int groupLen375 = group375.getSize();
for (int index375 = 0;index375 < groupLen375 ;index375++){
_colname.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group375.Get(index375)));
Debug.locals.put("colname", _colname);
 BA.debugLineNum = 614;BA.debugLine="CateSlug.Add(colname.Get(\"name\"))";
Debug.ShouldStop(32);
_cateslug.Add(_colname.Get((Object)("name")));
 }
Debug.locals.put("colname", _colname);
;
 BA.debugLineNum = 617;BA.debugLine="CreatePanel(CateSlug)";
Debug.ShouldStop(256);
_createpanel(_cateslug);
 break;
case 3:
 BA.debugLineNum = 622;BA.debugLine="Dim Slide1,slide2 As List";
Debug.ShouldStop(8192);
_slide1 = new anywheresoftware.b4a.objects.collections.List();Debug.locals.put("Slide1", _slide1);
_slide2 = new anywheresoftware.b4a.objects.collections.List();Debug.locals.put("slide2", _slide2);
 BA.debugLineNum = 625;BA.debugLine="Slide1.Initialize";
Debug.ShouldStop(65536);
_slide1.Initialize();
 BA.debugLineNum = 626;BA.debugLine="Slide1 = mresult.Get(\"Data\")";
Debug.ShouldStop(131072);
_slide1.setObject((java.util.List)(_mresult.Get((Object)("Data"))));
 BA.debugLineNum = 628;BA.debugLine="slide2.Initialize";
Debug.ShouldStop(524288);
_slide2.Initialize();
 BA.debugLineNum = 630;BA.debugLine="For Each colSlide As Map In Slide1";
Debug.ShouldStop(2097152);
_colslide = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group384 = _slide1;
final int groupLen384 = group384.getSize();
for (int index384 = 0;index384 < groupLen384 ;index384++){
_colslide.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group384.Get(index384)));
Debug.locals.put("colslide", _colslide);
 }
Debug.locals.put("colslide", _colslide);
;
 break;
case 4:
 BA.debugLineNum = 643;BA.debugLine="Dim Page1 	As Map";
Debug.ShouldStop(4);
_page1 = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("Page1", _page1);
 BA.debugLineNum = 644;BA.debugLine="Dim Page2 	As List";
Debug.ShouldStop(8);
_page2 = new anywheresoftware.b4a.objects.collections.List();Debug.locals.put("Page2", _page2);
 BA.debugLineNum = 645;BA.debugLine="Dim SubCate As String";
Debug.ShouldStop(16);
_subcate = "";Debug.locals.put("SubCate", _subcate);
 BA.debugLineNum = 646;BA.debugLine="Private x1,x2	As Int";
Debug.ShouldStop(32);
_x1 = 0;Debug.locals.put("x1", _x1);
_x2 = 0;Debug.locals.put("x2", _x2);
 BA.debugLineNum = 648;BA.debugLine="Page1.Initialize";
Debug.ShouldStop(128);
_page1.Initialize();
 BA.debugLineNum = 649;BA.debugLine="Page1 = mresult.Get(\"Data\")";
Debug.ShouldStop(256);
_page1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_mresult.Get((Object)("Data"))));
 BA.debugLineNum = 651;BA.debugLine="Page2.Initialize";
Debug.ShouldStop(1024);
_page2.Initialize();
 BA.debugLineNum = 653;BA.debugLine="SubCate = Page1.Get(\"subcategory\")";
Debug.ShouldStop(4096);
_subcate = BA.ObjectToString(_page1.Get((Object)("subcategory")));Debug.locals.put("SubCate", _subcate);
 BA.debugLineNum = 654;BA.debugLine="Page2 = Page1.Get(\"data\")";
Debug.ShouldStop(8192);
_page2.setObject((java.util.List)(_page1.Get((Object)("data"))));
 BA.debugLineNum = 656;BA.debugLine="Dim PlugPanel 		As Panel = mapPager.GetValueAt(MNChttp.Tag)";
Debug.ShouldStop(32768);
_plugpanel = new anywheresoftware.b4a.objects.PanelWrapper();
_plugpanel.setObject((android.view.ViewGroup)(mostCurrent._mappager.GetValueAt((int)(BA.ObjectToNumber(_mnchttp._tag)))));Debug.locals.put("PlugPanel", _plugpanel);
 BA.debugLineNum = 657;BA.debugLine="Dim PlugGrid  		As PhotoGridView = mapGrid.GetValueAt(MNChttp.Tag)";
Debug.ShouldStop(65536);
_pluggrid = new it.giuseppe.salvi.gridview.library.core.GridViewActivityWrapper();
_pluggrid.setObject((android.widget.GridView)(mostCurrent._mapgrid.GetValueAt((int)(BA.ObjectToNumber(_mnchttp._tag)))));Debug.locals.put("PlugGrid", _pluggrid);
 BA.debugLineNum = 658;BA.debugLine="Dim strPisah()		As String";
Debug.ShouldStop(131072);
_strpisah = new String[(int) (0)];
java.util.Arrays.fill(_strpisah,"");Debug.locals.put("strPisah", _strpisah);
 BA.debugLineNum = 660;BA.debugLine="PlugPanel.RemoveAllViews";
Debug.ShouldStop(524288);
_plugpanel.RemoveAllViews();
 BA.debugLineNum = 662;BA.debugLine="PlugGrid.Initialize(\"Video\")";
Debug.ShouldStop(2097152);
_pluggrid.Initialize(mostCurrent.activityBA,"Video");
 BA.debugLineNum = 663;BA.debugLine="PlugGrid.CacheInMemory = True";
Debug.ShouldStop(4194304);
_pluggrid.setCacheInMemory(anywheresoftware.b4a.keywords.Common.True);
 BA.debugLineNum = 664;BA.debugLine="PlugGrid.CacheOnDisk = True";
Debug.ShouldStop(8388608);
_pluggrid.setCacheOnDisk(anywheresoftware.b4a.keywords.Common.True);
 BA.debugLineNum = 665;BA.debugLine="PlugGrid.CompressQuality = 90";
Debug.ShouldStop(16777216);
_pluggrid.setCompressQuality((int) (90));
 BA.debugLineNum = 666;BA.debugLine="PlugGrid.NumColumns = 2";
Debug.ShouldStop(33554432);
_pluggrid.setNumColumns((int) (2));
 BA.debugLineNum = 667;BA.debugLine="PlugGrid.RoundedBitmap = True";
Debug.ShouldStop(67108864);
_pluggrid.setRoundedBitmap(anywheresoftware.b4a.keywords.Common.True);
 BA.debugLineNum = 670;BA.debugLine="PlugGrid.Gravity = Gravity.FILL";
Debug.ShouldStop(536870912);
_pluggrid.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 BA.debugLineNum = 671;BA.debugLine="PlugGrid.ScaleType = PlugGrid.ScaleType.Fit_Center";
Debug.ShouldStop(1073741824);
_pluggrid.setScaleType(_pluggrid.getScaleType().Fit_Center);
 BA.debugLineNum = 672;BA.debugLine="PlugGrid.ItemWidth = -50dip";
Debug.ShouldStop(-2147483648);
_pluggrid.setItemWidth((int) (-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50))));
 BA.debugLineNum = 674;BA.debugLine="PlugGrid.ItemHeight = 30dip";
Debug.ShouldStop(2);
_pluggrid.setItemHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 BA.debugLineNum = 675;BA.debugLine="PlugGrid.HorizontalSpacing = 1%x";
Debug.ShouldStop(4);
_pluggrid.setHorizontalSpacing(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (1),mostCurrent.activityBA));
 BA.debugLineNum = 676;BA.debugLine="PlugGrid.VerticalSpacing = 1%y";
Debug.ShouldStop(8);
_pluggrid.setVerticalSpacing(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (1),mostCurrent.activityBA));
 BA.debugLineNum = 677;BA.debugLine="PlugGrid.Enabled = True";
Debug.ShouldStop(16);
_pluggrid.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 BA.debugLineNum = 678;BA.debugLine="PlugGrid.ProgressBarIndeterminate = False";
Debug.ShouldStop(32);
_pluggrid.setProgressBarIndeterminate(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 679;BA.debugLine="PlugGrid.ProgressBarVisible = True";
Debug.ShouldStop(64);
_pluggrid.setProgressBarVisible(anywheresoftware.b4a.keywords.Common.True);
 BA.debugLineNum = 683;BA.debugLine="Dim Contentvideo As Map";
Debug.ShouldStop(1024);
_contentvideo = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("Contentvideo", _contentvideo);
 BA.debugLineNum = 684;BA.debugLine="Dim i 			 As Int=0";
Debug.ShouldStop(2048);
_i = (int) (0);Debug.locals.put("i", _i);Debug.locals.put("i", _i);
 BA.debugLineNum = 686;BA.debugLine="Contentvideo.Initialize";
Debug.ShouldStop(8192);
_contentvideo.Initialize();
 BA.debugLineNum = 687;BA.debugLine="Contentvideo.Clear";
Debug.ShouldStop(16384);
_contentvideo.Clear();
 BA.debugLineNum = 689;BA.debugLine="picture.Clear";
Debug.ShouldStop(65536);
mostCurrent._picture.Clear();
 BA.debugLineNum = 690;BA.debugLine="For Each colHal As Map In Page2";
Debug.ShouldStop(131072);
_colhal = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group420 = _page2;
final int groupLen420 = group420.getSize();
for (int index420 = 0;index420 < groupLen420 ;index420++){
_colhal.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group420.Get(index420)));
Debug.locals.put("colhal", _colhal);
 BA.debugLineNum = 692;BA.debugLine="For x2=0 To colHal.Size-1";
Debug.ShouldStop(524288);
{
final int step421 = 1;
final int limit421 = (int) (_colhal.getSize()-1);
for (_x2 = (int) (0); (step421 > 0 && _x2 <= limit421) || (step421 < 0 && _x2 >= limit421); _x2 = ((int)(0 + _x2 + step421))) {
Debug.locals.put("x2", _x2);
 BA.debugLineNum = 693;BA.debugLine="Select colHal.GetKeyAt(x2)";
Debug.ShouldStop(1048576);
switch (BA.switchObjectToInt(_colhal.GetKeyAt(_x2),(Object)("slug"))) {
case 0:
 BA.debugLineNum = 695;BA.debugLine="PlugGrid.AddImageFromWeb(\"http://i.moviebay.com/content_images/\" & colHal.Get(\"slug\") & \"/poster.jpg\")";
Debug.ShouldStop(4194304);
_pluggrid.AddImageFromWeb("http://i.moviebay.com/content_images/"+BA.ObjectToString(_colhal.Get((Object)("slug")))+"/poster.jpg");
 break;
}
;
 }
}Debug.locals.put("x2", _x2);
;
 BA.debugLineNum = 704;BA.debugLine="picture.Put(i, colHal)";
Debug.ShouldStop(-2147483648);
mostCurrent._picture.Put((Object)(_i),(Object)(_colhal.getObject()));
 BA.debugLineNum = 705;BA.debugLine="i = i + 1";
Debug.ShouldStop(1);
_i = (int) (_i+1);Debug.locals.put("i", _i);
 }
Debug.locals.put("colhal", _colhal);
;
 BA.debugLineNum = 712;BA.debugLine="PlugPanel.AddView(PlugGrid, 0, 0, pMenuMain.Width - 30dip, pMenuMain.Height - 15dip)";
Debug.ShouldStop(128);
_plugpanel.AddView((android.view.View)(_pluggrid.getObject()),(int) (0),(int) (0),(int) (mostCurrent._pmenumain.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30))),(int) (mostCurrent._pmenumain.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15))));
 break;
case 5:
 BA.debugLineNum = 719;BA.debugLine="Log(mresult)";
Debug.ShouldStop(16384);
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(_mresult));
 BA.debugLineNum = 720;BA.debugLine="Dim New 	As Map";
Debug.ShouldStop(32768);
_new = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("New", _new);
 BA.debugLineNum = 721;BA.debugLine="Dim Top 	As List";
Debug.ShouldStop(65536);
_top = new anywheresoftware.b4a.objects.collections.List();Debug.locals.put("Top", _top);
 BA.debugLineNum = 722;BA.debugLine="Dim SubKiri As String";
Debug.ShouldStop(131072);
_subkiri = "";Debug.locals.put("SubKiri", _subkiri);
 BA.debugLineNum = 723;BA.debugLine="Private k1,k2	As Int";
Debug.ShouldStop(262144);
_k1 = 0;Debug.locals.put("k1", _k1);
_k2 = 0;Debug.locals.put("k2", _k2);
 BA.debugLineNum = 725;BA.debugLine="New.Initialize";
Debug.ShouldStop(1048576);
_new.Initialize();
 BA.debugLineNum = 726;BA.debugLine="New = mresult.Get(\"Data\")";
Debug.ShouldStop(2097152);
_new.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_mresult.Get((Object)("Data"))));
 BA.debugLineNum = 728;BA.debugLine="Top.Initialize";
Debug.ShouldStop(8388608);
_top.Initialize();
 BA.debugLineNum = 730;BA.debugLine="SubKiri = New.Get(\"subcategory\")";
Debug.ShouldStop(33554432);
_subkiri = BA.ObjectToString(_new.Get((Object)("subcategory")));Debug.locals.put("SubKiri", _subkiri);
 BA.debugLineNum = 731;BA.debugLine="Top = New.Get(\"data\")";
Debug.ShouldStop(67108864);
_top.setObject((java.util.List)(_new.Get((Object)("data"))));
 BA.debugLineNum = 733;BA.debugLine="Dim PlugPanel As Panel = mapPager.GetValueAt(MNChttp.Tag)";
Debug.ShouldStop(268435456);
_plugpanel = new anywheresoftware.b4a.objects.PanelWrapper();
_plugpanel.setObject((android.view.ViewGroup)(mostCurrent._mappager.GetValueAt((int)(BA.ObjectToNumber(_mnchttp._tag)))));Debug.locals.put("PlugPanel", _plugpanel);
 BA.debugLineNum = 734;BA.debugLine="Dim PlugGrid  As PhotoGridView = mapGrid.GetValueAt(MNChttp.Tag)";
Debug.ShouldStop(536870912);
_pluggrid = new it.giuseppe.salvi.gridview.library.core.GridViewActivityWrapper();
_pluggrid.setObject((android.widget.GridView)(mostCurrent._mapgrid.GetValueAt((int)(BA.ObjectToNumber(_mnchttp._tag)))));Debug.locals.put("PlugGrid", _pluggrid);
 BA.debugLineNum = 735;BA.debugLine="Dim strPisah()		As String";
Debug.ShouldStop(1073741824);
_strpisah = new String[(int) (0)];
java.util.Arrays.fill(_strpisah,"");Debug.locals.put("strPisah", _strpisah);
 BA.debugLineNum = 737;BA.debugLine="PlugPanel.RemoveAllViews";
Debug.ShouldStop(1);
_plugpanel.RemoveAllViews();
 BA.debugLineNum = 739;BA.debugLine="PlugGrid.Initialize(\"Video\")";
Debug.ShouldStop(4);
_pluggrid.Initialize(mostCurrent.activityBA,"Video");
 BA.debugLineNum = 740;BA.debugLine="PlugGrid.CacheInMemory = True";
Debug.ShouldStop(8);
_pluggrid.setCacheInMemory(anywheresoftware.b4a.keywords.Common.True);
 BA.debugLineNum = 741;BA.debugLine="PlugGrid.CacheOnDisk = True";
Debug.ShouldStop(16);
_pluggrid.setCacheOnDisk(anywheresoftware.b4a.keywords.Common.True);
 BA.debugLineNum = 742;BA.debugLine="PlugGrid.CompressQuality = 90";
Debug.ShouldStop(32);
_pluggrid.setCompressQuality((int) (90));
 BA.debugLineNum = 743;BA.debugLine="PlugGrid.NumColumns = 2";
Debug.ShouldStop(64);
_pluggrid.setNumColumns((int) (2));
 BA.debugLineNum = 744;BA.debugLine="PlugGrid.RoundedBitmap = False";
Debug.ShouldStop(128);
_pluggrid.setRoundedBitmap(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 745;BA.debugLine="PlugGrid.HorizontalSpacing = 0";
Debug.ShouldStop(256);
_pluggrid.setHorizontalSpacing((int) (0));
 BA.debugLineNum = 746;BA.debugLine="PlugGrid.VerticalSpacing = 15dip";
Debug.ShouldStop(512);
_pluggrid.setVerticalSpacing(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)));
 BA.debugLineNum = 747;BA.debugLine="PlugGrid.Gravity = Gravity.CENTER";
Debug.ShouldStop(1024);
_pluggrid.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 BA.debugLineNum = 748;BA.debugLine="PlugGrid.ScaleType = PlugGrid.ScaleType.Fit_Center";
Debug.ShouldStop(2048);
_pluggrid.setScaleType(_pluggrid.getScaleType().Fit_Center);
 BA.debugLineNum = 749;BA.debugLine="PlugGrid.ItemWidth = 20%x";
Debug.ShouldStop(4096);
_pluggrid.setItemWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA));
 BA.debugLineNum = 750;BA.debugLine="PlugGrid.ItemHeight = 60%y";
Debug.ShouldStop(8192);
_pluggrid.setItemHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (60),mostCurrent.activityBA));
 BA.debugLineNum = 751;BA.debugLine="PlugGrid.Enabled = True";
Debug.ShouldStop(16384);
_pluggrid.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 BA.debugLineNum = 752;BA.debugLine="PlugGrid.ProgressBarIndeterminate = False";
Debug.ShouldStop(32768);
_pluggrid.setProgressBarIndeterminate(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 753;BA.debugLine="PlugGrid.ProgressBarVisible = True";
Debug.ShouldStop(65536);
_pluggrid.setProgressBarVisible(anywheresoftware.b4a.keywords.Common.True);
 BA.debugLineNum = 757;BA.debugLine="Dim Contentvideo As Map";
Debug.ShouldStop(1048576);
_contentvideo = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("Contentvideo", _contentvideo);
 BA.debugLineNum = 758;BA.debugLine="Dim i 			 As Int=0";
Debug.ShouldStop(2097152);
_i = (int) (0);Debug.locals.put("i", _i);Debug.locals.put("i", _i);
 BA.debugLineNum = 760;BA.debugLine="Contentvideo.Initialize";
Debug.ShouldStop(8388608);
_contentvideo.Initialize();
 BA.debugLineNum = 761;BA.debugLine="Contentvideo.Clear";
Debug.ShouldStop(16777216);
_contentvideo.Clear();
 BA.debugLineNum = 763;BA.debugLine="picture.Clear";
Debug.ShouldStop(67108864);
mostCurrent._picture.Clear();
 BA.debugLineNum = 764;BA.debugLine="For Each colKiri As Map In Top";
Debug.ShouldStop(134217728);
_colkiri = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group466 = _top;
final int groupLen466 = group466.getSize();
for (int index466 = 0;index466 < groupLen466 ;index466++){
_colkiri.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group466.Get(index466)));
Debug.locals.put("colkiri", _colkiri);
 BA.debugLineNum = 766;BA.debugLine="For x2=0 To colKiri.Size-1";
Debug.ShouldStop(536870912);
{
final int step467 = 1;
final int limit467 = (int) (_colkiri.getSize()-1);
for (_x2 = (int) (0); (step467 > 0 && _x2 <= limit467) || (step467 < 0 && _x2 >= limit467); _x2 = ((int)(0 + _x2 + step467))) {
Debug.locals.put("x2", _x2);
 BA.debugLineNum = 767;BA.debugLine="Select colKiri.GetKeyAt(x2)";
Debug.ShouldStop(1073741824);
switch (BA.switchObjectToInt(_colkiri.GetKeyAt(_x2),(Object)("slug"))) {
case 0:
 BA.debugLineNum = 769;BA.debugLine="PlugGrid.AddImageFromWeb(\"http://i.moviebay.com/content_images/\" & colKiri.Get(\"slug\") & \"/poster.jpg\")";
Debug.ShouldStop(1);
_pluggrid.AddImageFromWeb("http://i.moviebay.com/content_images/"+BA.ObjectToString(_colkiri.Get((Object)("slug")))+"/poster.jpg");
 break;
}
;
 }
}Debug.locals.put("x2", _x2);
;
 BA.debugLineNum = 775;BA.debugLine="picture.Put(i, colKiri)";
Debug.ShouldStop(64);
mostCurrent._picture.Put((Object)(_i),(Object)(_colkiri.getObject()));
 BA.debugLineNum = 776;BA.debugLine="i = i + 1";
Debug.ShouldStop(128);
_i = (int) (_i+1);Debug.locals.put("i", _i);
 }
Debug.locals.put("colkiri", _colkiri);
;
 BA.debugLineNum = 783;BA.debugLine="PlugPanel.AddView(PlugGrid, 0, 0, pMenuMain.Width - 30dip, pMenuMain.Height - 15dip)";
Debug.ShouldStop(16384);
_plugpanel.AddView((android.view.View)(_pluggrid.getObject()),(int) (0),(int) (0),(int) (mostCurrent._pmenumain.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30))),(int) (mostCurrent._pmenumain.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15))));
 break;
}
;
 };
 };
 BA.debugLineNum = 788;BA.debugLine="End Sub";
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
public static String  _lbllg_click() throws Exception{
try {
		Debug.PushSubsStack("lblLg_Click (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,843);
 BA.debugLineNum = 843;BA.debugLine="Sub lblLg_Click";
Debug.ShouldStop(1024);
 BA.debugLineNum = 845;BA.debugLine="End Sub";
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
public static String  _lblnamafilm_click() throws Exception{
try {
		Debug.PushSubsStack("lblNamaFilm_Click (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,316);
 BA.debugLineNum = 316;BA.debugLine="Sub lblNamaFilm_Click";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 317;BA.debugLine="If iTampil2 = 0 Then";
Debug.ShouldStop(268435456);
if (_itampil2==0) { 
 BA.debugLineNum = 318;BA.debugLine="iTampil2 = 1";
Debug.ShouldStop(536870912);
_itampil2 = (int) (1);
 BA.debugLineNum = 319;BA.debugLine="lvCateFilm.SetLayoutAnimated(120, pMainAtas.Left, pMainAtas.Height, pMainAtas.Width, 100%y - pMainAtas.Height)";
Debug.ShouldStop(1073741824);
mostCurrent._lvcatefilm.SetLayoutAnimated((int) (120),mostCurrent._pmainatas.getLeft(),mostCurrent._pmainatas.getHeight(),mostCurrent._pmainatas.getWidth(),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-mostCurrent._pmainatas.getHeight()));
 }else {
 BA.debugLineNum = 321;BA.debugLine="iTampil2 = 0";
Debug.ShouldStop(1);
_itampil2 = (int) (0);
 BA.debugLineNum = 322;BA.debugLine="lvCateFilm.SetLayoutAnimated(120, pMainAtas.Left, pMainAtas.Height, pMenuMain.Width, 0)";
Debug.ShouldStop(2);
mostCurrent._lvcatefilm.SetLayoutAnimated((int) (120),mostCurrent._pmainatas.getLeft(),mostCurrent._pmainatas.getHeight(),mostCurrent._pmenumain.getWidth(),(int) (0));
 };
 BA.debugLineNum = 324;BA.debugLine="End Sub";
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
public static String  _lbluser_click() throws Exception{
try {
		Debug.PushSubsStack("lblUser_Click (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,840);
 BA.debugLineNum = 840;BA.debugLine="Sub lblUser_Click";
Debug.ShouldStop(128);
 BA.debugLineNum = 841;BA.debugLine="StartActivity(\"Profile\")";
Debug.ShouldStop(256);
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)("Profile"));
 BA.debugLineNum = 842;BA.debugLine="End Sub";
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
public static String  _lvcatefilm_itemclick(int _position,Object _value) throws Exception{
try {
		Debug.PushSubsStack("lvCateFilm_ItemClick (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,353);
newmoviebay.mncplaymedia.com.mnchttpjob _homejob = null;
String _strenc = "";
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _gen = null;
anywheresoftware.b4a.objects.collections.Map _mcate = null;
String[] _dipisah = null;
String _asli = "";
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 353;BA.debugLine="Sub lvCateFilm_ItemClick (Position As Int, Value As Object)";
Debug.ShouldStop(1);
 BA.debugLineNum = 354;BA.debugLine="Dim HomeJob 	As MNChttpJob";
Debug.ShouldStop(2);
_homejob = new newmoviebay.mncplaymedia.com.mnchttpjob();Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 355;BA.debugLine="Dim	strEnc		As String";
Debug.ShouldStop(4);
_strenc = "";Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 356;BA.debugLine="Dim Gen			As JSONGenerator";
Debug.ShouldStop(8);
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();Debug.locals.put("Gen", _gen);
 BA.debugLineNum = 357;BA.debugLine="Dim mCate		As Map";
Debug.ShouldStop(16);
_mcate = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("mCate", _mcate);
 BA.debugLineNum = 358;BA.debugLine="Dim dipisah()   As String";
Debug.ShouldStop(32);
_dipisah = new String[(int) (0)];
java.util.Arrays.fill(_dipisah,"");Debug.locals.put("dipisah", _dipisah);
 BA.debugLineNum = 359;BA.debugLine="Dim asli 		As String = Value";
Debug.ShouldStop(64);
_asli = BA.ObjectToString(_value);Debug.locals.put("asli", _asli);Debug.locals.put("asli", _asli);
 BA.debugLineNum = 361;BA.debugLine="dipisah = Regex.Split(\"#\", asli)";
Debug.ShouldStop(256);
_dipisah = anywheresoftware.b4a.keywords.Common.Regex.Split("#",_asli);Debug.locals.put("dipisah", _dipisah);
 BA.debugLineNum = 363;BA.debugLine="If asli.Contains(\"Awal\") = False Then";
Debug.ShouldStop(1024);
if (_asli.contains("Awal")==anywheresoftware.b4a.keywords.Common.False) { 
 BA.debugLineNum = 364;BA.debugLine="lblNamaFilm_Click";
Debug.ShouldStop(2048);
_lblnamafilm_click();
 };
 BA.debugLineNum = 368;BA.debugLine="mCate.Initialize";
Debug.ShouldStop(32768);
_mcate.Initialize();
 BA.debugLineNum = 369;BA.debugLine="mCate.Clear";
Debug.ShouldStop(65536);
_mcate.Clear();
 BA.debugLineNum = 370;BA.debugLine="mCate.Put(Fungsi.mpList.Get(\"slug\"),\"slug\")";
Debug.ShouldStop(131072);
_mcate.Put(mostCurrent._fungsi._mplist.Get((Object)("slug")),(Object)("slug"));
 BA.debugLineNum = 371;BA.debugLine="Gen.Initialize(mCate)";
Debug.ShouldStop(262144);
_gen.Initialize(_mcate);
 BA.debugLineNum = 372;BA.debugLine="HomeJob.Initialize(\"CateMovies\", Me)";
Debug.ShouldStop(524288);
_homejob._initialize(processBA,"CateMovies",utama.getObject());
 BA.debugLineNum = 374;BA.debugLine="Try";
Debug.ShouldStop(2097152);
try { BA.debugLineNum = 376;BA.debugLine="HomeJob.Tag = dipisah(0)";
Debug.ShouldStop(8388608);
_homejob._tag = (Object)(_dipisah[(int) (0)]);Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 377;BA.debugLine="strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(2))";
Debug.ShouldStop(16777216);
_strenc = mostCurrent._fungsi._prosesenkripsi(mostCurrent.activityBA,_gen.ToPrettyString((int) (2)));Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 378;BA.debugLine="If strEnc = \"Enkripsi Failed\" Then Return";
Debug.ShouldStop(33554432);
if ((_strenc).equals("Enkripsi Failed")) { 
if (true) return "";};
 BA.debugLineNum = 379;BA.debugLine="HomeJob.PostString(Fungsi.mpList.Get(\"MoviesURL\"), \"teks=\" & strEnc)";
Debug.ShouldStop(67108864);
_homejob._poststring(BA.ObjectToString(mostCurrent._fungsi._mplist.Get((Object)("MoviesURL"))),"teks="+_strenc);
 BA.debugLineNum = 380;BA.debugLine="HomeJob.GetRequest.SetHeader(\"Cache-Control\", \"no-cache, must-revalidate\")";
Debug.ShouldStop(134217728);
_homejob._getrequest().SetHeader("Cache-Control","no-cache, must-revalidate");
 BA.debugLineNum = 381;BA.debugLine="HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60";
Debug.ShouldStop(268435456);
_homejob._getrequest().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 BA.debugLineNum = 382;BA.debugLine="lblNamaFilm.Text = dipisah(0)";
Debug.ShouldStop(536870912);
mostCurrent._lblnamafilm.setText((Object)(_dipisah[(int) (0)]));
 } 
       catch (Exception e220) {
			processBA.setLastException(e220); BA.debugLineNum = 384;BA.debugLine="Msgbox2(\"Category Failure !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(-2147483648);
anywheresoftware.b4a.keywords.Common.Msgbox2("Category Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 };
 BA.debugLineNum = 386;BA.debugLine="End Sub";
Debug.ShouldStop(2);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static String  _lvutama_itemclick(int _position,Object _value) throws Exception{
try {
		Debug.PushSubsStack("lvUtama_ItemClick (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,418);
newmoviebay.mncplaymedia.com.mnchttpjob _homejob = null;
String _strenc = "";
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _gen = null;
anywheresoftware.b4a.objects.collections.Map _mpage = null;
String _coba = "";
String[] _dipisah = null;
String _asli = "";
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 418;BA.debugLine="Sub  lvUtama_ItemClick (Position As Int, Value As Object)";
Debug.ShouldStop(2);
 BA.debugLineNum = 420;BA.debugLine="Dim HomeJob As MNChttpJob";
Debug.ShouldStop(8);
_homejob = new newmoviebay.mncplaymedia.com.mnchttpjob();Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 421;BA.debugLine="Dim	strEnc	As String";
Debug.ShouldStop(16);
_strenc = "";Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 422;BA.debugLine="Dim Gen		As JSONGenerator";
Debug.ShouldStop(32);
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();Debug.locals.put("Gen", _gen);
 BA.debugLineNum = 423;BA.debugLine="Dim mPage	As Map";
Debug.ShouldStop(64);
_mpage = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("mPage", _mpage);
 BA.debugLineNum = 424;BA.debugLine="Dim coba 	As String=Value";
Debug.ShouldStop(128);
_coba = BA.ObjectToString(_value);Debug.locals.put("coba", _coba);Debug.locals.put("coba", _coba);
 BA.debugLineNum = 425;BA.debugLine="Dim dipisah()   As String";
Debug.ShouldStop(256);
_dipisah = new String[(int) (0)];
java.util.Arrays.fill(_dipisah,"");Debug.locals.put("dipisah", _dipisah);
 BA.debugLineNum = 426;BA.debugLine="Dim asli 		As String = Value";
Debug.ShouldStop(512);
_asli = BA.ObjectToString(_value);Debug.locals.put("asli", _asli);Debug.locals.put("asli", _asli);
 BA.debugLineNum = 428;BA.debugLine="Cek_Menu";
Debug.ShouldStop(2048);
_cek_menu();
 BA.debugLineNum = 431;BA.debugLine="mPage.Initialize";
Debug.ShouldStop(16384);
_mpage.Initialize();
 BA.debugLineNum = 432;BA.debugLine="mPage.Clear";
Debug.ShouldStop(32768);
_mpage.Clear();
 BA.debugLineNum = 433;BA.debugLine="mPage.Put(Fungsi.mpList.Get(\"Variabel\"), coba.Replace(\" \", \"-\").ToLowerCase)";
Debug.ShouldStop(65536);
_mpage.Put(mostCurrent._fungsi._mplist.Get((Object)("Variabel")),(Object)(_coba.replace(" ","-").toLowerCase()));
 BA.debugLineNum = 434;BA.debugLine="Gen.Initialize(mPage)";
Debug.ShouldStop(131072);
_gen.Initialize(_mpage);
 BA.debugLineNum = 435;BA.debugLine="HomeJob.Initialize(\"MenuKiri\", Me)";
Debug.ShouldStop(262144);
_homejob._initialize(processBA,"MenuKiri",utama.getObject());
 BA.debugLineNum = 437;BA.debugLine="Try";
Debug.ShouldStop(1048576);
try { BA.debugLineNum = 438;BA.debugLine="Log (Fungsi.mpList.Get(\"SlugURL\"))";
Debug.ShouldStop(2097152);
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(mostCurrent._fungsi._mplist.Get((Object)("SlugURL"))));
 BA.debugLineNum = 439;BA.debugLine="HomeJob.Tag = Position";
Debug.ShouldStop(4194304);
_homejob._tag = (Object)(_position);Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 440;BA.debugLine="strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(2))";
Debug.ShouldStop(8388608);
_strenc = mostCurrent._fungsi._prosesenkripsi(mostCurrent.activityBA,_gen.ToPrettyString((int) (2)));Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 441;BA.debugLine="If strEnc = \"Enkripsi Failed\" Then Return";
Debug.ShouldStop(16777216);
if ((_strenc).equals("Enkripsi Failed")) { 
if (true) return "";};
 BA.debugLineNum = 442;BA.debugLine="HomeJob.PostString(Fungsi.mpList.Get(\"SlugURL\"), \"teks=\" & strEnc)";
Debug.ShouldStop(33554432);
_homejob._poststring(BA.ObjectToString(mostCurrent._fungsi._mplist.Get((Object)("SlugURL"))),"teks="+_strenc);
 BA.debugLineNum = 443;BA.debugLine="HomeJob.GetRequest.SetHeader(\"Cache-Control\", \"no-cache, must-revalidate\")";
Debug.ShouldStop(67108864);
_homejob._getrequest().SetHeader("Cache-Control","no-cache, must-revalidate");
 BA.debugLineNum = 444;BA.debugLine="HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60";
Debug.ShouldStop(134217728);
_homejob._getrequest().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 } 
       catch (Exception e270) {
			processBA.setLastException(e270); BA.debugLineNum = 446;BA.debugLine="Msgbox2(\"Cetgory Failure !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(536870912);
anywheresoftware.b4a.keywords.Common.Msgbox2("Cetgory Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 };
 BA.debugLineNum = 448;BA.debugLine="End Sub";
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
public static String  _pager_pagechanged(int _position) throws Exception{
try {
		Debug.PushSubsStack("Pager_PageChanged (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,817);
newmoviebay.mncplaymedia.com.mnchttpjob _homejob = null;
String _strenc = "";
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _gen = null;
anywheresoftware.b4a.objects.collections.Map _mpage = null;
Debug.locals.put("Position", _position);
 BA.debugLineNum = 817;BA.debugLine="Sub Pager_PageChanged (Position As Int)";
Debug.ShouldStop(65536);
 BA.debugLineNum = 818;BA.debugLine="Dim HomeJob As MNChttpJob";
Debug.ShouldStop(131072);
_homejob = new newmoviebay.mncplaymedia.com.mnchttpjob();Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 819;BA.debugLine="Dim	strEnc	As String";
Debug.ShouldStop(262144);
_strenc = "";Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 820;BA.debugLine="Dim Gen		As JSONGenerator";
Debug.ShouldStop(524288);
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();Debug.locals.put("Gen", _gen);
 BA.debugLineNum = 821;BA.debugLine="Dim mPage	As Map";
Debug.ShouldStop(1048576);
_mpage = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("mPage", _mpage);
 BA.debugLineNum = 823;BA.debugLine="mPage.Initialize";
Debug.ShouldStop(4194304);
_mpage.Initialize();
 BA.debugLineNum = 824;BA.debugLine="mPage.Clear";
Debug.ShouldStop(8388608);
_mpage.Clear();
 BA.debugLineNum = 825;BA.debugLine="mPage.Put(Fungsi.mpList.Get(\"Variabel\"), container.GetTitle(Position).ToLowerCase)";
Debug.ShouldStop(16777216);
_mpage.Put(mostCurrent._fungsi._mplist.Get((Object)("Variabel")),(Object)(mostCurrent._container.GetTitle(_position).toLowerCase()));
 BA.debugLineNum = 826;BA.debugLine="Gen.Initialize(mPage)";
Debug.ShouldStop(33554432);
_gen.Initialize(_mpage);
 BA.debugLineNum = 827;BA.debugLine="HomeJob.Initialize(\"SlugPager\", Me)";
Debug.ShouldStop(67108864);
_homejob._initialize(processBA,"SlugPager",utama.getObject());
 BA.debugLineNum = 829;BA.debugLine="Try";
Debug.ShouldStop(268435456);
try { BA.debugLineNum = 830;BA.debugLine="HomeJob.Tag = Position";
Debug.ShouldStop(536870912);
_homejob._tag = (Object)(_position);Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 831;BA.debugLine="strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(2))";
Debug.ShouldStop(1073741824);
_strenc = mostCurrent._fungsi._prosesenkripsi(mostCurrent.activityBA,_gen.ToPrettyString((int) (2)));Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 832;BA.debugLine="If strEnc = \"Enkripsi Failed\" Then Return";
Debug.ShouldStop(-2147483648);
if ((_strenc).equals("Enkripsi Failed")) { 
if (true) return "";};
 BA.debugLineNum = 833;BA.debugLine="HomeJob.PostString(Fungsi.mpList.Get(\"SlugURL\"), \"teks=\" & strEnc)";
Debug.ShouldStop(1);
_homejob._poststring(BA.ObjectToString(mostCurrent._fungsi._mplist.Get((Object)("SlugURL"))),"teks="+_strenc);
 BA.debugLineNum = 834;BA.debugLine="HomeJob.GetRequest.SetHeader(\"Cache-Control\", \"no-cache, must-revalidate\")";
Debug.ShouldStop(2);
_homejob._getrequest().SetHeader("Cache-Control","no-cache, must-revalidate");
 BA.debugLineNum = 835;BA.debugLine="HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60";
Debug.ShouldStop(4);
_homejob._getrequest().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 } 
       catch (Exception e519) {
			processBA.setLastException(e519); BA.debugLineNum = 837;BA.debugLine="Msgbox2(\"Cetgory Failure !\", \"Moviebay\", \"OK\", \"\", \"\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(16);
anywheresoftware.b4a.keywords.Common.Msgbox2("Cetgory Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 };
 BA.debugLineNum = 839;BA.debugLine="End Sub";
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
public static String  _pmenu_click() throws Exception{
try {
		Debug.PushSubsStack("pMenu_Click (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,846);
 BA.debugLineNum = 846;BA.debugLine="Sub pMenu_Click";
Debug.ShouldStop(8192);
 BA.debugLineNum = 847;BA.debugLine="Cek_Menu";
Debug.ShouldStop(16384);
_cek_menu();
 BA.debugLineNum = 848;BA.debugLine="End Sub";
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
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim damn As String";
_damn = "";
 //BA.debugLineNum = 10;BA.debugLine="Dim sial As String";
_sial = "";
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public static String  _video_itemclick(int _position,Object _value) throws Exception{
try {
		Debug.PushSubsStack("Video_ItemClick (utama) ","utama",1,mostCurrent.activityBA,mostCurrent,210);
newmoviebay.mncplaymedia.com.mnchttpjob _homejob = null;
String _strenc = "";
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _gen = null;
anywheresoftware.b4a.objects.collections.Map _mcate = null;
String[] _dipisah = null;
Object _a = null;
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 210;BA.debugLine="Sub Video_ItemClick (Position As Int, Value As Object)";
Debug.ShouldStop(131072);
 BA.debugLineNum = 211;BA.debugLine="Dim HomeJob 	As MNChttpJob";
Debug.ShouldStop(262144);
_homejob = new newmoviebay.mncplaymedia.com.mnchttpjob();Debug.locals.put("HomeJob", _homejob);
 BA.debugLineNum = 212;BA.debugLine="Dim	strEnc		As String";
Debug.ShouldStop(524288);
_strenc = "";Debug.locals.put("strEnc", _strenc);
 BA.debugLineNum = 213;BA.debugLine="Dim Gen			As JSONGenerator";
Debug.ShouldStop(1048576);
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();Debug.locals.put("Gen", _gen);
 BA.debugLineNum = 214;BA.debugLine="Dim mcate		As Map";
Debug.ShouldStop(2097152);
_mcate = new anywheresoftware.b4a.objects.collections.Map();Debug.locals.put("mcate", _mcate);
 BA.debugLineNum = 215;BA.debugLine="Dim dipisah()   As String";
Debug.ShouldStop(4194304);
_dipisah = new String[(int) (0)];
java.util.Arrays.fill(_dipisah,"");Debug.locals.put("dipisah", _dipisah);
 BA.debugLineNum = 237;BA.debugLine="Dim A As Object";
Debug.ShouldStop(4096);
_a = new Object();Debug.locals.put("A", _a);
 BA.debugLineNum = 240;BA.debugLine="Content.A = Value";
Debug.ShouldStop(32768);
mostCurrent._content._a = _value;
 BA.debugLineNum = 241;BA.debugLine="Content.i = Position";
Debug.ShouldStop(65536);
mostCurrent._content._i = _position;
 BA.debugLineNum = 242;BA.debugLine="Content.gambar = picture.Get(Position)";
Debug.ShouldStop(131072);
mostCurrent._content._gambar.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(mostCurrent._picture.Get((Object)(_position))));
 BA.debugLineNum = 244;BA.debugLine="StartActivity(\"Content\")";
Debug.ShouldStop(524288);
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)("Content"));
 BA.debugLineNum = 247;BA.debugLine="End Sub";
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
}
