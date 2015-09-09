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

public class play extends Activity implements B4AActivity{
	public static play mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "newmoviebay.mncplaymedia.com", "newmoviebay.mncplaymedia.com.play");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (play).");
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
		activityBA = new BA(this, layout, processBA, "newmoviebay.mncplaymedia.com", "newmoviebay.mncplaymedia.com.play");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "newmoviebay.mncplaymedia.com.play", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (play) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (play) Resume **");
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
		return play.class;
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
        BA.LogInfo("** Activity (play) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (play) Resume **");
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
public anywheresoftware.giuseppe.salvi.toast.ToastMessageShowWrapper _ctoast = null;
public uk.co.martinpearman.b4a.vitamio.widget.MediaController _vimediacontrol = null;
public uk.co.martinpearman.b4a.vitamio.widget.VideoView _vivideoview = null;
public anywheresoftware.b4a.phone.Phone _phplayer = null;
public anywheresoftware.b4a.objects.PanelWrapper _pvideo = null;
public anywheresoftware.b4a.objects.Timer _tvideo = null;
public com.datasteam.b4a.xtraviews.MovieViewControl _gifplayer = null;
public flm.b4a.betterdialogs.BetterDialogs _bd = null;
public flm.b4a.betterdialogs.BetterDialogs.CustomDlgParams _params = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivyes = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivno = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivinfo = null;
public static int _itekan = 0;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivmenu = null;
public static int _spasi = 0;
public static int _lebar = 0;
public static int _tinggi = 0;
public static int _durasi = 0;
public static int _jeday = 0;
public static int _device = 0;
public anywheresoftware.b4a.phone.Phone.PhoneCalls _pcmncshop = null;
public anywheresoftware.b4a.objects.ButtonWrapper _bcall = null;
public anywheresoftware.b4a.objects.ButtonWrapper _bbeli = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etnama = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etemail = null;
public anywheresoftware.b4a.objects.EditTextWrapper _ettelp = null;
public anywheresoftware.b4a.objects.PanelWrapper _ppesan = null;
public anywheresoftware.b4a.objects.collections.Map _mdaftar = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etname = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etmail = null;
public anywheresoftware.b4a.objects.EditTextWrapper _ethp = null;
public anywheresoftware.b4a.objects.ButtonWrapper _breset = null;
public anywheresoftware.b4a.objects.ButtonWrapper _bcacl = null;
public anywheresoftware.b4a.objects.WebViewWrapper _wvorder = null;
public anywheresoftware.b4a.objects.Timer _tmrload = null;
public uk.co.martinpearman.b4a.webviewextras.WebViewExtras _wve = null;
public static int _iinitweb = 0;
public thalmy.webviewxtended.xtender _wvs = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblprog = null;
public static String _page = "";
public anywheresoftware.b4a.phone.PhoneEvents _pe = null;
public anywheresoftware.b4a.objects.PanelWrapper _pinfo = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivlogo = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivweb = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivcall = null;
public static int _apiver = 0;
public static String _szandroidid = "";
public static String _szrefserial = "";
public static String _szimei = "";
public static String _szimsi = "";
public static String _szmanufacturer = "";
public static String _szmodel = "";
public static String _szproduct = "";
public static String _szsubsid = "";
public anywheresoftware.b4a.agraham.reflection.Reflection _refl = null;
public anywheresoftware.b4a.phone.Phone _myphone = null;
public anywheresoftware.b4a.phone.Phone.PhoneId _thisphone = null;
public anywheresoftware.b4a.objects.ButtonWrapper _bsms = null;
public anywheresoftware.b4a.phone.Phone.PhoneSms _smsmncshop = null;
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
public newmoviebay.mncplaymedia.com.result _result = null;
public newmoviebay.mncplaymedia.com.date _date = null;
  public Object[] GetGlobals() {
		return new Object[] {"Activity",mostCurrent._activity,"APIver",_apiver,"bBeli",mostCurrent._bbeli,"bCacl",mostCurrent._bcacl,"bCall",mostCurrent._bcall,"BD",mostCurrent._bd,"bReset",mostCurrent._breset,"bSMS",mostCurrent._bsms,"Content",Debug.moduleToString(newmoviebay.mncplaymedia.com.content.class),"ctoast",mostCurrent._ctoast,"Date",Debug.moduleToString(newmoviebay.mncplaymedia.com.date.class),"DateUtils",mostCurrent._dateutils,"Device",_device,"Durasi",_durasi,"etEmail",mostCurrent._etemail,"etHp",mostCurrent._ethp,"etMail",mostCurrent._etmail,"etNama",mostCurrent._etnama,"etName",mostCurrent._etname,"etTelp",mostCurrent._ettelp,"ForgotPass",Debug.moduleToString(newmoviebay.mncplaymedia.com.forgotpass.class),"Fungsi",Debug.moduleToString(newmoviebay.mncplaymedia.com.fungsi.class),"GifPlayer",mostCurrent._gifplayer,"iInitWeb",_iinitweb,"iTekan",_itekan,"ivCall",mostCurrent._ivcall,"ivInfo",mostCurrent._ivinfo,"ivLogo",mostCurrent._ivlogo,"ivMenu",mostCurrent._ivmenu,"ivNo",mostCurrent._ivno,"ivWeb",mostCurrent._ivweb,"ivYes",mostCurrent._ivyes,"jeday",_jeday,"lblProg",mostCurrent._lblprog,"Lebar",_lebar,"Main",Debug.moduleToString(newmoviebay.mncplaymedia.com.main.class),"mDaftar",mostCurrent._mdaftar,"MenuSearch",Debug.moduleToString(newmoviebay.mncplaymedia.com.menusearch.class),"MNCUtils2Service",Debug.moduleToString(newmoviebay.mncplaymedia.com.mncutils2service.class),"myPhone",mostCurrent._myphone,"Page",mostCurrent._page,"Params",mostCurrent._params,"pcMNCShop",mostCurrent._pcmncshop,"PE",mostCurrent._pe,"Pemutar",Debug.moduleToString(newmoviebay.mncplaymedia.com.pemutar.class),"PhPlayer",mostCurrent._phplayer,"pInfo",mostCurrent._pinfo,"pPesan",mostCurrent._ppesan,"Profile",Debug.moduleToString(newmoviebay.mncplaymedia.com.profile.class),"pVideo",mostCurrent._pvideo,"refl",mostCurrent._refl,"Register",Debug.moduleToString(newmoviebay.mncplaymedia.com.register.class),"Result",Debug.moduleToString(newmoviebay.mncplaymedia.com.result.class),"Share",Debug.moduleToString(newmoviebay.mncplaymedia.com.share.class),"smsMNCshop",mostCurrent._smsmncshop,"Spasi",_spasi,"szAndroidID",mostCurrent._szandroidid,"szIMEI",mostCurrent._szimei,"szIMSI",mostCurrent._szimsi,"szManufacturer",mostCurrent._szmanufacturer,"szModel",mostCurrent._szmodel,"szProduct",mostCurrent._szproduct,"szRefSerial",mostCurrent._szrefserial,"szSubsID",mostCurrent._szsubsid,"thisPhone",mostCurrent._thisphone,"Tinggi",_tinggi,"tmrLoad",mostCurrent._tmrload,"tVideo",mostCurrent._tvideo,"Utama",Debug.moduleToString(newmoviebay.mncplaymedia.com.utama.class),"ViMediaControl",mostCurrent._vimediacontrol,"ViVideoView",mostCurrent._vivideoview,"WebView",Debug.moduleToString(newmoviebay.mncplaymedia.com.webview.class),"wve",mostCurrent._wve,"wvOrder",mostCurrent._wvorder,"wvs",mostCurrent._wvs};
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
		Debug.PushSubsStack("Activity_Create (play) ","play",16,mostCurrent.activityBA,mostCurrent,82);
anywheresoftware.b4a.phone.Phone.PhoneId _phoneid = null;
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 82;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(131072);
 BA.debugLineNum = 85;BA.debugLine="If GetDeviceLayoutValues.ApproximateScreenSize < 5 Then";
Debug.ShouldStop(1048576);
if (anywheresoftware.b4a.keywords.Common.GetDeviceLayoutValues(mostCurrent.activityBA).getApproximateScreenSize()<5) { 
 BA.debugLineNum = 87;BA.debugLine="If 100%x > 100%y Then";
Debug.ShouldStop(4194304);
if (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)>anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)) { 
 BA.debugLineNum = 88;BA.debugLine="Spasi = -22";
Debug.ShouldStop(8388608);
_spasi = (int) (-22);
 BA.debugLineNum = 89;BA.debugLine="Lebar = 45%x";
Debug.ShouldStop(16777216);
_lebar = anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (45),mostCurrent.activityBA);
 BA.debugLineNum = 90;BA.debugLine="Tinggi = 35%y";
Debug.ShouldStop(33554432);
_tinggi = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA);
 BA.debugLineNum = 91;BA.debugLine="Durasi = 800";
Debug.ShouldStop(67108864);
_durasi = (int) (800);
 BA.debugLineNum = 92;BA.debugLine="jeday = 0.2%y";
Debug.ShouldStop(134217728);
_jeday = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (0.2),mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 94;BA.debugLine="Spasi = -17";
Debug.ShouldStop(536870912);
_spasi = (int) (-17);
 BA.debugLineNum = 95;BA.debugLine="Lebar = 45%x";
Debug.ShouldStop(1073741824);
_lebar = anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (45),mostCurrent.activityBA);
 BA.debugLineNum = 96;BA.debugLine="Tinggi = 35%y";
Debug.ShouldStop(-2147483648);
_tinggi = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA);
 BA.debugLineNum = 97;BA.debugLine="Durasi = 800";
Debug.ShouldStop(1);
_durasi = (int) (800);
 BA.debugLineNum = 98;BA.debugLine="jeday = 0.5%y";
Debug.ShouldStop(2);
_jeday = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (0.5),mostCurrent.activityBA);
 };
 BA.debugLineNum = 101;BA.debugLine="Device = 0";
Debug.ShouldStop(16);
_device = (int) (0);
 }else {
 BA.debugLineNum = 104;BA.debugLine="Spasi = -75";
Debug.ShouldStop(128);
_spasi = (int) (-75);
 BA.debugLineNum = 105;BA.debugLine="Lebar = 50%x";
Debug.ShouldStop(256);
_lebar = anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (50),mostCurrent.activityBA);
 BA.debugLineNum = 106;BA.debugLine="Tinggi = 40%y";
Debug.ShouldStop(512);
_tinggi = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (40),mostCurrent.activityBA);
 BA.debugLineNum = 107;BA.debugLine="Durasi = 700";
Debug.ShouldStop(1024);
_durasi = (int) (700);
 BA.debugLineNum = 108;BA.debugLine="jeday = 0.5%y";
Debug.ShouldStop(2048);
_jeday = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (0.5),mostCurrent.activityBA);
 BA.debugLineNum = 110;BA.debugLine="Device = 1";
Debug.ShouldStop(8192);
_device = (int) (1);
 };
 BA.debugLineNum = 114;BA.debugLine="Activity.LoadLayout(\"VideoPlay\")";
Debug.ShouldStop(131072);
mostCurrent._activity.LoadLayout("VideoPlay",mostCurrent.activityBA);
 BA.debugLineNum = 128;BA.debugLine="Activity.AddMenuItem2(\"Low Quality\", \"Low\", LoadBitmap(File.DirAssets, \"128_ic.png\"))";
Debug.ShouldStop(-2147483648);
mostCurrent._activity.AddMenuItem2("Low Quality","Low",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"128_ic.png").getObject()));
 BA.debugLineNum = 129;BA.debugLine="Activity.AddMenuItem2(\"Medium Quality\", \"Medium\", LoadBitmap(File.DirAssets, \"256_ic.png\"))";
Debug.ShouldStop(1);
mostCurrent._activity.AddMenuItem2("Medium Quality","Medium",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"256_ic.png").getObject()));
 BA.debugLineNum = 130;BA.debugLine="Activity.AddMenuItem2(\"High Quality\", \"High\", LoadBitmap(File.DirAssets, \"512_ic.png\"))";
Debug.ShouldStop(2);
mostCurrent._activity.AddMenuItem2("High Quality","High",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"512_ic.png").getObject()));
 BA.debugLineNum = 131;BA.debugLine="Activity.AddMenuItem2(\"Very High Quality\", \"Very\", LoadBitmap(File.DirAssets, \"1024_ic.png\"))";
Debug.ShouldStop(4);
mostCurrent._activity.AddMenuItem2("Very High Quality","Very",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"1024_ic.png").getObject()));
 BA.debugLineNum = 132;BA.debugLine="Activity.AddMenuItem2(\"Exit Application\", \"Exit\", LoadBitmap(File.DirAssets, \"exit_menutop.png\"))";
Debug.ShouldStop(8);
mostCurrent._activity.AddMenuItem2("Exit Application","Exit",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"exit_menutop.png").getObject()));
 BA.debugLineNum = 134;BA.debugLine="ctoast.Initialize(\"\")";
Debug.ShouldStop(32);
mostCurrent._ctoast.Initialize(processBA,"");
 BA.debugLineNum = 135;BA.debugLine="ctoast.Location = ctoast.Gravity.CENTER_BOTTOM";
Debug.ShouldStop(64);
mostCurrent._ctoast.setLocation(mostCurrent._ctoast.getGravity().CENTER_BOTTOM);
 BA.debugLineNum = 136;BA.debugLine="ctoast.IconSize48x48";
Debug.ShouldStop(128);
mostCurrent._ctoast.IconSize48x48();
 BA.debugLineNum = 137;BA.debugLine="ctoast.TextLocation = ctoast.Gravity.TLeft";
Debug.ShouldStop(256);
mostCurrent._ctoast.setTextLocation(mostCurrent._ctoast.getGravity().TLeft);
 BA.debugLineNum = 138;BA.debugLine="ctoast.TextSize = 16";
Debug.ShouldStop(512);
mostCurrent._ctoast.setTextSize((int) (16));
 BA.debugLineNum = 139;BA.debugLine="ctoast.Duration = 3000";
Debug.ShouldStop(1024);
mostCurrent._ctoast.setDuration((long) (3000));
 BA.debugLineNum = 140;BA.debugLine="ctoast.RoundedRect = 8";
Debug.ShouldStop(2048);
mostCurrent._ctoast.setRoundedRect((float) (8));
 BA.debugLineNum = 141;BA.debugLine="ctoast.Height = 10";
Debug.ShouldStop(4096);
mostCurrent._ctoast.setHeight((int) (10));
 BA.debugLineNum = 143;BA.debugLine="ViVideoView.Initialize(\"ViVideoView\")";
Debug.ShouldStop(16384);
mostCurrent._vivideoview.Initialize(mostCurrent.activityBA,"ViVideoView");
 BA.debugLineNum = 144;BA.debugLine="ViMediaControl.Initialize(\"ViMediaControl\")";
Debug.ShouldStop(32768);
mostCurrent._vimediacontrol.Initialize(mostCurrent.activityBA,"ViMediaControl");
 BA.debugLineNum = 146;BA.debugLine="PhPlayer.SetScreenOrientation(0)";
Debug.ShouldStop(131072);
mostCurrent._phplayer.SetScreenOrientation(processBA,(int) (0));
 BA.debugLineNum = 147;BA.debugLine="GifPlayer.Load(File.DirAssets, \"hourglass.gif\", True)";
Debug.ShouldStop(262144);
mostCurrent._gifplayer.Load(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"hourglass.gif",anywheresoftware.b4a.keywords.Common.True);
 BA.debugLineNum = 148;BA.debugLine="GifPlayer.Paused = False";
Debug.ShouldStop(524288);
mostCurrent._gifplayer.setPaused(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 149;BA.debugLine="GifPlayer.Visible = False";
Debug.ShouldStop(1048576);
mostCurrent._gifplayer.setVisible(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 151;BA.debugLine="Dim PhoneId As PhoneId";
Debug.ShouldStop(4194304);
_phoneid = new anywheresoftware.b4a.phone.Phone.PhoneId();Debug.locals.put("PhoneId", _phoneid);
 BA.debugLineNum = 152;BA.debugLine="PE.InitializeWithPhoneState(\"PE\",PhoneId)";
Debug.ShouldStop(8388608);
mostCurrent._pe.InitializeWithPhoneState(processBA,"PE",_phoneid);
 BA.debugLineNum = 154;BA.debugLine="pVideo.RemoveAllViews";
Debug.ShouldStop(33554432);
mostCurrent._pvideo.RemoveAllViews();
 BA.debugLineNum = 155;BA.debugLine="pVideo.AddView(ViVideoView, 0, 0, pVideo.Width, pVideo.Height)";
Debug.ShouldStop(67108864);
mostCurrent._pvideo.AddView((android.view.View)(mostCurrent._vivideoview.getObject()),(int) (0),(int) (0),mostCurrent._pvideo.getWidth(),mostCurrent._pvideo.getHeight());
 BA.debugLineNum = 157;BA.debugLine="ViVideoView.SetVideoPath(\"http://livetv.mncplaymedia.com/cdn/iptv/Tvod/001/channel2000037/512.m3u8\")";
Debug.ShouldStop(268435456);
mostCurrent._vivideoview.SetVideoPath("http://livetv.mncplaymedia.com/cdn/iptv/Tvod/001/channel2000037/512.m3u8");
 BA.debugLineNum = 158;BA.debugLine="PlayVideo";
Debug.ShouldStop(536870912);
_playvideo();
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
public static boolean  _activity_keypress(int _keycode) throws Exception{
try {
		Debug.PushSubsStack("Activity_KeyPress (play) ","play",16,mostCurrent.activityBA,mostCurrent,250);
anywheresoftware.b4a.objects.ButtonWrapper _btnok = null;
anywheresoftware.b4a.objects.ButtonWrapper _btncancel = null;
anywheresoftware.b4a.objects.PanelWrapper _pnlbody = null;
int _itest = 0;
Debug.locals.put("KeyCode", _keycode);
 BA.debugLineNum = 250;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean 'return true if you want to consume the event";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 251;BA.debugLine="If KeyCode = 4 Then ' Back Key";
Debug.ShouldStop(67108864);
if (_keycode==4) { 
 BA.debugLineNum = 252;BA.debugLine="Dim Params		As BD_CustomDlgParams";
Debug.ShouldStop(134217728);
mostCurrent._params = new flm.b4a.betterdialogs.BetterDialogs.CustomDlgParams();
 BA.debugLineNum = 253;BA.debugLine="Dim btnOK 		As Button";
Debug.ShouldStop(268435456);
_btnok = new anywheresoftware.b4a.objects.ButtonWrapper();Debug.locals.put("btnOK", _btnok);
 BA.debugLineNum = 254;BA.debugLine="Dim btnCancel 	As Button";
Debug.ShouldStop(536870912);
_btncancel = new anywheresoftware.b4a.objects.ButtonWrapper();Debug.locals.put("btnCancel", _btncancel);
 BA.debugLineNum = 255;BA.debugLine="Dim pnlBody 	As Panel";
Debug.ShouldStop(1073741824);
_pnlbody = new anywheresoftware.b4a.objects.PanelWrapper();Debug.locals.put("pnlBody", _pnlbody);
 BA.debugLineNum = 256;BA.debugLine="Dim itest		As Int";
Debug.ShouldStop(-2147483648);
_itest = 0;Debug.locals.put("itest", _itest);
 BA.debugLineNum = 258;BA.debugLine="pnlBody.Initialize(\"\")";
Debug.ShouldStop(2);
_pnlbody.Initialize(mostCurrent.activityBA,"");
 BA.debugLineNum = 260;BA.debugLine="If Device = 1 Then";
Debug.ShouldStop(8);
if (_device==1) { 
 BA.debugLineNum = 261;BA.debugLine="pnlBody.LoadLayout(\"ivTablet\")";
Debug.ShouldStop(16);
_pnlbody.LoadLayout("ivTablet",mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 263;BA.debugLine="pnlBody.LoadLayout(\"IvSmart\")";
Debug.ShouldStop(64);
_pnlbody.LoadLayout("IvSmart",mostCurrent.activityBA);
 };
 BA.debugLineNum = 266;BA.debugLine="Dim btnOK As Button";
Debug.ShouldStop(512);
_btnok = new anywheresoftware.b4a.objects.ButtonWrapper();Debug.locals.put("btnOK", _btnok);
 BA.debugLineNum = 267;BA.debugLine="btnOK.Initialize(\"\")";
Debug.ShouldStop(1024);
_btnok.Initialize(mostCurrent.activityBA,"");
 BA.debugLineNum = 268;BA.debugLine="btnOK.SetBackgroundImage(LoadBitmap(File.DirAssets, \"bgbutton(1).png\"))";
Debug.ShouldStop(2048);
_btnok.SetBackgroundImage((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bgbutton(1).png").getObject()));
 BA.debugLineNum = 269;BA.debugLine="btnOK.TextSize = 12";
Debug.ShouldStop(4096);
_btnok.setTextSize((float) (12));
 BA.debugLineNum = 271;BA.debugLine="btnOK.Text = \"OK\"";
Debug.ShouldStop(16384);
_btnok.setText((Object)("OK"));
 BA.debugLineNum = 272;BA.debugLine="btnOK.Typeface = Typeface.DEFAULT";
Debug.ShouldStop(32768);
_btnok.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT);
 BA.debugLineNum = 273;BA.debugLine="btnOK.TextColor = Colors.White";
Debug.ShouldStop(65536);
_btnok.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 BA.debugLineNum = 276;BA.debugLine="Dim btnCancel As Button";
Debug.ShouldStop(524288);
_btncancel = new anywheresoftware.b4a.objects.ButtonWrapper();Debug.locals.put("btnCancel", _btncancel);
 BA.debugLineNum = 277;BA.debugLine="btnCancel.Initialize(\"\")";
Debug.ShouldStop(1048576);
_btncancel.Initialize(mostCurrent.activityBA,"");
 BA.debugLineNum = 278;BA.debugLine="btnCancel.SetBackgroundImage(LoadBitmap(File.DirAssets, \"bgbutton(1).png\"))";
Debug.ShouldStop(2097152);
_btncancel.SetBackgroundImage((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bgbutton(1).png").getObject()));
 BA.debugLineNum = 279;BA.debugLine="btnCancel.TextSize = 12";
Debug.ShouldStop(4194304);
_btncancel.setTextSize((float) (12));
 BA.debugLineNum = 281;BA.debugLine="btnCancel.Text = \"CANCEL\"";
Debug.ShouldStop(16777216);
_btncancel.setText((Object)("CANCEL"));
 BA.debugLineNum = 282;BA.debugLine="btnCancel.Typeface = Typeface.DEFAULT";
Debug.ShouldStop(33554432);
_btncancel.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT);
 BA.debugLineNum = 283;BA.debugLine="btnCancel.TextColor = Colors.White";
Debug.ShouldStop(67108864);
_btncancel.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 BA.debugLineNum = 285;BA.debugLine="Params.Initialize";
Debug.ShouldStop(268435456);
mostCurrent._params.Initialize();
 BA.debugLineNum = 286;BA.debugLine="Params.DialogBody = pnlBody";
Debug.ShouldStop(536870912);
mostCurrent._params.setDialogBody((Object)(_pnlbody.getObject()));
 BA.debugLineNum = 287;BA.debugLine="Params.BodyWidth = pInfo.Width";
Debug.ShouldStop(1073741824);
mostCurrent._params.setBodyWidth(mostCurrent._pinfo.getWidth());
 BA.debugLineNum = 288;BA.debugLine="Params.BodyHeight = pInfo.Height";
Debug.ShouldStop(-2147483648);
mostCurrent._params.setBodyHeight(mostCurrent._pinfo.getHeight());
 BA.debugLineNum = 289;BA.debugLine="Params.PositiveButton = btnOK";
Debug.ShouldStop(1);
mostCurrent._params.setPositiveButton((Object)(_btnok.getObject()));
 BA.debugLineNum = 290;BA.debugLine="Params.CancelButton = btnCancel";
Debug.ShouldStop(2);
mostCurrent._params.setCancelButton((Object)(_btncancel.getObject()));
 BA.debugLineNum = 292;BA.debugLine="If BD.CustomDialog(Params, \"CD2\")	= DialogResponse.POSITIVE Then";
Debug.ShouldStop(8);
if (mostCurrent._bd.CustomDialog(mostCurrent._params,"CD2",mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 BA.debugLineNum = 293;BA.debugLine="ExitApplication";
Debug.ShouldStop(16);
anywheresoftware.b4a.keywords.Common.ExitApplication();
 }else {
 BA.debugLineNum = 295;BA.debugLine="Return True";
Debug.ShouldStop(64);
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 };
 BA.debugLineNum = 303;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
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
		Debug.PushSubsStack("Activity_Pause (play) ","play",16,mostCurrent.activityBA,mostCurrent,188);
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 188;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 189;BA.debugLine="If UserClosed = False Then";
Debug.ShouldStop(268435456);
if (_userclosed==anywheresoftware.b4a.keywords.Common.False) { 
 BA.debugLineNum = 190;BA.debugLine="ViVideoView.StopPlayback";
Debug.ShouldStop(536870912);
mostCurrent._vivideoview.StopPlayback();
 BA.debugLineNum = 191;BA.debugLine="ViVideoView.Suspend";
Debug.ShouldStop(1073741824);
mostCurrent._vivideoview.Suspend();
 };
 BA.debugLineNum = 193;BA.debugLine="End Sub";
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
public static String  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (play) ","play",16,mostCurrent.activityBA,mostCurrent,185);
 BA.debugLineNum = 185;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 186;BA.debugLine="End Sub";
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
public static String  _bbeli_click() throws Exception{
try {
		Debug.PushSubsStack("bBeli_Click (play) ","play",16,mostCurrent.activityBA,mostCurrent,346);
 BA.debugLineNum = 346;BA.debugLine="Sub bBeli_Click";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 349;BA.debugLine="StartActivity(\"Webview\")";
Debug.ShouldStop(268435456);
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)("Webview"));
 BA.debugLineNum = 364;BA.debugLine="End Sub";
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
public static String  _bcacl_click() throws Exception{
try {
		Debug.PushSubsStack("bcacl_Click (play) ","play",16,mostCurrent.activityBA,mostCurrent,375);
 BA.debugLineNum = 375;BA.debugLine="Sub bcacl_Click";
Debug.ShouldStop(4194304);
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
public static String  _bcall_click() throws Exception{
try {
		Debug.PushSubsStack("bCall_Click (play) ","play",16,mostCurrent.activityBA,mostCurrent,334);
anywheresoftware.b4a.objects.IntentWrapper _i = null;
 BA.debugLineNum = 334;BA.debugLine="Sub bCall_Click";
Debug.ShouldStop(8192);
 BA.debugLineNum = 336;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(32768);
_i = new anywheresoftware.b4a.objects.IntentWrapper();Debug.locals.put("i", _i);
 BA.debugLineNum = 337;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"tel:021 500-887\")";
Debug.ShouldStop(65536);
_i.Initialize(_i.ACTION_VIEW,"tel:021 500-887");
 BA.debugLineNum = 338;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(131072);
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_i.getObject()));
 BA.debugLineNum = 340;BA.debugLine="End Sub";
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
public static String  _bcancel_click() throws Exception{
try {
		Debug.PushSubsStack("bCancel_Click (play) ","play",16,mostCurrent.activityBA,mostCurrent,365);
 BA.debugLineNum = 365;BA.debugLine="Sub bCancel_Click";
Debug.ShouldStop(4096);
 BA.debugLineNum = 366;BA.debugLine="BD.CloseDialog(DialogResponse.CANCEL)";
Debug.ShouldStop(8192);
mostCurrent._bd.CloseDialog(anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL);
 BA.debugLineNum = 367;BA.debugLine="End Sub";
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
public static String  _bsms_click() throws Exception{
try {
		Debug.PushSubsStack("bSMS_Click (play) ","play",16,mostCurrent.activityBA,mostCurrent,423);
anywheresoftware.b4a.objects.IntentWrapper _i = null;
 BA.debugLineNum = 423;BA.debugLine="Sub bSMS_Click";
Debug.ShouldStop(64);
 BA.debugLineNum = 424;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(128);
_i = new anywheresoftware.b4a.objects.IntentWrapper();Debug.locals.put("i", _i);
 BA.debugLineNum = 425;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"sms:089517807753\")";
Debug.ShouldStop(256);
_i.Initialize(_i.ACTION_VIEW,"sms:089517807753");
 BA.debugLineNum = 426;BA.debugLine="i.PutExtra(\"sms_body\", \"\")";
Debug.ShouldStop(512);
_i.PutExtra("sms_body",(Object)(""));
 BA.debugLineNum = 427;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(1024);
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_i.getObject()));
 BA.debugLineNum = 428;BA.debugLine="End Sub";
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
public static String  _exit_click() throws Exception{
try {
		Debug.PushSubsStack("Exit_Click (play) ","play",16,mostCurrent.activityBA,mostCurrent,181);
 BA.debugLineNum = 181;BA.debugLine="Sub Exit_Click";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 182;BA.debugLine="Activity_KeyPress(4)";
Debug.ShouldStop(2097152);
_activity_keypress((int) (4));
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
public static String  _globals() throws Exception{
 //BA.debugLineNum = 13;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 17;BA.debugLine="Private ctoast				As ToastMessageShow";
mostCurrent._ctoast = new anywheresoftware.giuseppe.salvi.toast.ToastMessageShowWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private ViMediaControl		As Vitamio_MediaController";
mostCurrent._vimediacontrol = new uk.co.martinpearman.b4a.vitamio.widget.MediaController();
 //BA.debugLineNum = 19;BA.debugLine="Private ViVideoView 		As Vitamio_VideoView";
mostCurrent._vivideoview = new uk.co.martinpearman.b4a.vitamio.widget.VideoView();
 //BA.debugLineNum = 20;BA.debugLine="Private PhPlayer			As Phone";
mostCurrent._phplayer = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 21;BA.debugLine="Private pVideo 				As Panel";
mostCurrent._pvideo = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private tVideo				As Timer";
mostCurrent._tvideo = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 23;BA.debugLine="Private GifPlayer 			As MovieViewControl";
mostCurrent._gifplayer = new com.datasteam.b4a.xtraviews.MovieViewControl();
 //BA.debugLineNum = 24;BA.debugLine="Private BD					As BetterDialogs";
mostCurrent._bd = new flm.b4a.betterdialogs.BetterDialogs();
 //BA.debugLineNum = 25;BA.debugLine="Private Params				As BD_CustomDlgParams";
mostCurrent._params = new flm.b4a.betterdialogs.BetterDialogs.CustomDlgParams();
 //BA.debugLineNum = 26;BA.debugLine="Private ivYes 				As ImageView";
mostCurrent._ivyes = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private ivNo 				As ImageView";
mostCurrent._ivno = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private ivInfo 				As ImageView";
mostCurrent._ivinfo = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private iTekan				As Int=0";
_itekan = (int) (0);
 //BA.debugLineNum = 30;BA.debugLine="Private ivMenu 				As ImageView";
mostCurrent._ivmenu = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private Spasi				As Int";
_spasi = 0;
 //BA.debugLineNum = 33;BA.debugLine="Private Lebar				As Int";
_lebar = 0;
 //BA.debugLineNum = 34;BA.debugLine="Private Tinggi				As Int";
_tinggi = 0;
 //BA.debugLineNum = 35;BA.debugLine="Private Durasi				As Int";
_durasi = 0;
 //BA.debugLineNum = 36;BA.debugLine="Private jeday				As Int";
_jeday = 0;
 //BA.debugLineNum = 37;BA.debugLine="Private Device				As Int";
_device = 0;
 //BA.debugLineNum = 38;BA.debugLine="Private pcMNCShop			As PhoneCalls";
mostCurrent._pcmncshop = new anywheresoftware.b4a.phone.Phone.PhoneCalls();
 //BA.debugLineNum = 39;BA.debugLine="Private bCall 				As Button";
mostCurrent._bcall = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private bBeli 				As Button";
mostCurrent._bbeli = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private etNama 				As EditText";
mostCurrent._etnama = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private etEmail 			As EditText";
mostCurrent._etemail = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private etTelp 				As EditText";
mostCurrent._ettelp = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private pPesan 				As Panel";
mostCurrent._ppesan = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private mDaftar				As Map";
mostCurrent._mdaftar = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 48;BA.debugLine="Private etName 				As EditText";
mostCurrent._etname = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private etMail 				As EditText";
mostCurrent._etmail = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private etHp 				As EditText";
mostCurrent._ethp = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private bReset 				As Button";
mostCurrent._breset = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Private bCacl 				As Button";
mostCurrent._bcacl = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private wvOrder 			As WebView";
mostCurrent._wvorder = new anywheresoftware.b4a.objects.WebViewWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Private tmrLoad 			As Timer";
mostCurrent._tmrload = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 55;BA.debugLine="Private wve 				As WebViewExtras";
mostCurrent._wve = new uk.co.martinpearman.b4a.webviewextras.WebViewExtras();
 //BA.debugLineNum = 56;BA.debugLine="Private iInitWeb			As Int=0";
_iinitweb = (int) (0);
 //BA.debugLineNum = 57;BA.debugLine="Private wvs					As WebViewXtender";
mostCurrent._wvs = new thalmy.webviewxtended.xtender();
 //BA.debugLineNum = 58;BA.debugLine="Private lblProg 			As Label";
mostCurrent._lblprog = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 59;BA.debugLine="Private Page				As String";
mostCurrent._page = "";
 //BA.debugLineNum = 60;BA.debugLine="Private PE					As PhoneEvents";
mostCurrent._pe = new anywheresoftware.b4a.phone.PhoneEvents();
 //BA.debugLineNum = 61;BA.debugLine="Private pInfo 				As Panel";
mostCurrent._pinfo = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 62;BA.debugLine="Private ivLogo 				As ImageView";
mostCurrent._ivlogo = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 63;BA.debugLine="Private ivWeb 				As ImageView";
mostCurrent._ivweb = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 64;BA.debugLine="Private ivCall 				As ImageView";
mostCurrent._ivcall = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 66;BA.debugLine="Dim APIver 					As Int";
_apiver = 0;
 //BA.debugLineNum = 67;BA.debugLine="Dim szAndroidID 			As String";
mostCurrent._szandroidid = "";
 //BA.debugLineNum = 68;BA.debugLine="Dim szRefSerial 			As String";
mostCurrent._szrefserial = "";
 //BA.debugLineNum = 69;BA.debugLine="Dim szIMEI					As String";
mostCurrent._szimei = "";
 //BA.debugLineNum = 70;BA.debugLine="Dim szIMSI					As String";
mostCurrent._szimsi = "";
 //BA.debugLineNum = 71;BA.debugLine="Dim szManufacturer			As String";
mostCurrent._szmanufacturer = "";
 //BA.debugLineNum = 72;BA.debugLine="Dim szModel					As String";
mostCurrent._szmodel = "";
 //BA.debugLineNum = 73;BA.debugLine="Dim	szProduct				As String";
mostCurrent._szproduct = "";
 //BA.debugLineNum = 74;BA.debugLine="Dim	szSubsID				As String";
mostCurrent._szsubsid = "";
 //BA.debugLineNum = 75;BA.debugLine="Dim refl 					As Reflector";
mostCurrent._refl = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 76;BA.debugLine="Dim myPhone 				As Phone";
mostCurrent._myphone = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 77;BA.debugLine="Dim thisPhone 				As PhoneId";
mostCurrent._thisphone = new anywheresoftware.b4a.phone.Phone.PhoneId();
 //BA.debugLineNum = 78;BA.debugLine="Private bSMS 				As Button";
mostCurrent._bsms = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 79;BA.debugLine="Private smsMNCshop 			As PhoneSms";
mostCurrent._smsmncshop = new anywheresoftware.b4a.phone.Phone.PhoneSms();
 //BA.debugLineNum = 80;BA.debugLine="End Sub";
return "";
}
public static String  _high_click() throws Exception{
try {
		Debug.PushSubsStack("High_Click (play) ","play",16,mostCurrent.activityBA,mostCurrent,171);
 BA.debugLineNum = 171;BA.debugLine="Sub High_Click";
Debug.ShouldStop(1024);
 BA.debugLineNum = 172;BA.debugLine="ViVideoView.SetVideoPath(\"http://livetv.mncplaymedia.com/cdn/iptv/Tvod/001/channel2000037/512.m3u8\")";
Debug.ShouldStop(2048);
mostCurrent._vivideoview.SetVideoPath("http://livetv.mncplaymedia.com/cdn/iptv/Tvod/001/channel2000037/512.m3u8");
 BA.debugLineNum = 173;BA.debugLine="PlayVideo";
Debug.ShouldStop(4096);
_playvideo();
 BA.debugLineNum = 174;BA.debugLine="End Sub";
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
public static String  _infodevice() throws Exception{
try {
		Debug.PushSubsStack("InfoDevice (play) ","play",16,mostCurrent.activityBA,mostCurrent,399);
 BA.debugLineNum = 399;BA.debugLine="Sub InfoDevice";
Debug.ShouldStop(16384);
 BA.debugLineNum = 401;BA.debugLine="APIver = refl.GetStaticField(\"android.os.Build$VERSION\", \"SDK_INT\")";
Debug.ShouldStop(65536);
_apiver = (int)(BA.ObjectToNumber(mostCurrent._refl.GetStaticField("android.os.Build$VERSION","SDK_INT")));
 BA.debugLineNum = 402;BA.debugLine="If APIver < 9 Then";
Debug.ShouldStop(131072);
if (_apiver<9) { 
 BA.debugLineNum = 403;BA.debugLine="szRefSerial = \"n/a\"";
Debug.ShouldStop(262144);
mostCurrent._szrefserial = "n/a";
 }else {
 BA.debugLineNum = 405;BA.debugLine="szRefSerial = refl.GetStaticField(\"android.os.Build\", \"SERIAL\")";
Debug.ShouldStop(1048576);
mostCurrent._szrefserial = BA.ObjectToString(mostCurrent._refl.GetStaticField("android.os.Build","SERIAL"));
 };
 BA.debugLineNum = 408;BA.debugLine="szAndroidID = myPhone.GetSettings(\"android_id\")";
Debug.ShouldStop(8388608);
mostCurrent._szandroidid = mostCurrent._myphone.GetSettings("android_id");
 BA.debugLineNum = 409;BA.debugLine="szManufacturer = myPhone.Manufacturer";
Debug.ShouldStop(16777216);
mostCurrent._szmanufacturer = mostCurrent._myphone.getManufacturer();
 BA.debugLineNum = 410;BA.debugLine="szModel = myPhone.Model";
Debug.ShouldStop(33554432);
mostCurrent._szmodel = mostCurrent._myphone.getModel();
 BA.debugLineNum = 411;BA.debugLine="szProduct = myPhone.Product";
Debug.ShouldStop(67108864);
mostCurrent._szproduct = mostCurrent._myphone.getProduct();
 BA.debugLineNum = 412;BA.debugLine="szIMEI = thisPhone.GetDeviceId";
Debug.ShouldStop(134217728);
mostCurrent._szimei = mostCurrent._thisphone.GetDeviceId();
 BA.debugLineNum = 413;BA.debugLine="szIMSI = thisPhone.GetSimSerialNumber";
Debug.ShouldStop(268435456);
mostCurrent._szimsi = mostCurrent._thisphone.GetSimSerialNumber();
 BA.debugLineNum = 414;BA.debugLine="szSubsID = thisPhone.GetSubscriberId";
Debug.ShouldStop(536870912);
mostCurrent._szsubsid = mostCurrent._thisphone.GetSubscriberId();
 BA.debugLineNum = 415;BA.debugLine="Log(szAndroidID)";
Debug.ShouldStop(1073741824);
anywheresoftware.b4a.keywords.Common.Log(mostCurrent._szandroidid);
 BA.debugLineNum = 416;BA.debugLine="Log(szManufacturer)";
Debug.ShouldStop(-2147483648);
anywheresoftware.b4a.keywords.Common.Log(mostCurrent._szmanufacturer);
 BA.debugLineNum = 417;BA.debugLine="Log(szModel)";
Debug.ShouldStop(1);
anywheresoftware.b4a.keywords.Common.Log(mostCurrent._szmodel);
 BA.debugLineNum = 418;BA.debugLine="Log(szProduct)";
Debug.ShouldStop(2);
anywheresoftware.b4a.keywords.Common.Log(mostCurrent._szproduct);
 BA.debugLineNum = 419;BA.debugLine="Log(szIMEI)";
Debug.ShouldStop(4);
anywheresoftware.b4a.keywords.Common.Log(mostCurrent._szimei);
 BA.debugLineNum = 420;BA.debugLine="Log(szIMSI)";
Debug.ShouldStop(8);
anywheresoftware.b4a.keywords.Common.Log(mostCurrent._szimsi);
 BA.debugLineNum = 421;BA.debugLine="Log(szSubsID)";
Debug.ShouldStop(16);
anywheresoftware.b4a.keywords.Common.Log(mostCurrent._szsubsid);
 BA.debugLineNum = 422;BA.debugLine="End Sub";
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
public static String  _ivcall_click() throws Exception{
try {
		Debug.PushSubsStack("ivCall_Click (play) ","play",16,mostCurrent.activityBA,mostCurrent,394);
anywheresoftware.b4a.objects.IntentWrapper _i = null;
 BA.debugLineNum = 394;BA.debugLine="Sub ivCall_Click";
Debug.ShouldStop(512);
 BA.debugLineNum = 395;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(1024);
_i = new anywheresoftware.b4a.objects.IntentWrapper();Debug.locals.put("i", _i);
 BA.debugLineNum = 396;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"tel:021 500-887\")";
Debug.ShouldStop(2048);
_i.Initialize(_i.ACTION_VIEW,"tel:021 500-887");
 BA.debugLineNum = 397;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(4096);
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_i.getObject()));
 BA.debugLineNum = 398;BA.debugLine="End Sub";
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
public static String  _ivinfo_click() throws Exception{
try {
		Debug.PushSubsStack("ivInfo_Click (play) ","play",16,mostCurrent.activityBA,mostCurrent,325);
 BA.debugLineNum = 325;BA.debugLine="Sub ivInfo_Click";
Debug.ShouldStop(16);
 BA.debugLineNum = 332;BA.debugLine="End Sub";
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
public static String  _ivlogo_click() throws Exception{
try {
		Debug.PushSubsStack("ivLogo_Click (play) ","play",16,mostCurrent.activityBA,mostCurrent,378);
 BA.debugLineNum = 378;BA.debugLine="Sub ivLogo_Click";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 385;BA.debugLine="End Sub";
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
public static String  _ivmenu_click() throws Exception{
try {
		Debug.PushSubsStack("ivMenu_Click (play) ","play",16,mostCurrent.activityBA,mostCurrent,321);
 BA.debugLineNum = 321;BA.debugLine="Sub ivMenu_Click";
Debug.ShouldStop(1);
 BA.debugLineNum = 322;BA.debugLine="ViMediaControl.Show2(3000)";
Debug.ShouldStop(2);
mostCurrent._vimediacontrol.Show2((int) (3000));
 BA.debugLineNum = 323;BA.debugLine="End Sub";
Debug.ShouldStop(4);
return "";
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static String  _ivno_click() throws Exception{
try {
		Debug.PushSubsStack("ivNo_Click (play) ","play",16,mostCurrent.activityBA,mostCurrent,316);
 BA.debugLineNum = 316;BA.debugLine="Sub ivNo_Click";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 317;BA.debugLine="BD.CloseDialog(DialogResponse.CANCEL)";
Debug.ShouldStop(268435456);
mostCurrent._bd.CloseDialog(anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL);
 BA.debugLineNum = 318;BA.debugLine="Return";
Debug.ShouldStop(536870912);
if (true) return "";
 BA.debugLineNum = 319;BA.debugLine="End Sub";
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
public static String  _ivweb_click() throws Exception{
try {
		Debug.PushSubsStack("ivWeb_Click (play) ","play",16,mostCurrent.activityBA,mostCurrent,386);
anywheresoftware.b4a.phone.Phone.PhoneIntents _p = null;
 BA.debugLineNum = 386;BA.debugLine="Sub ivWeb_Click";
Debug.ShouldStop(2);
 BA.debugLineNum = 387;BA.debugLine="Dim p As PhoneIntents";
Debug.ShouldStop(4);
_p = new anywheresoftware.b4a.phone.Phone.PhoneIntents();Debug.locals.put("p", _p);
 BA.debugLineNum = 389;BA.debugLine="BD.CloseDialog(DialogResponse.CANCEL)";
Debug.ShouldStop(16);
mostCurrent._bd.CloseDialog(anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL);
 BA.debugLineNum = 390;BA.debugLine="StartActivity(p.OpenBrowser(\"http://www.mncshop.co.id/\"))";
Debug.ShouldStop(32);
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_p.OpenBrowser("http://www.mncshop.co.id/")));
 BA.debugLineNum = 392;BA.debugLine="Return";
Debug.ShouldStop(128);
if (true) return "";
 BA.debugLineNum = 393;BA.debugLine="End Sub";
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
public static String  _ivyes_click() throws Exception{
try {
		Debug.PushSubsStack("ivYes_Click (play) ","play",16,mostCurrent.activityBA,mostCurrent,311);
 BA.debugLineNum = 311;BA.debugLine="Sub ivYes_Click";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 312;BA.debugLine="BD.CloseDialog(DialogResponse.POSITIVE)";
Debug.ShouldStop(8388608);
mostCurrent._bd.CloseDialog(anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE);
 BA.debugLineNum = 313;BA.debugLine="ExitApplication";
Debug.ShouldStop(16777216);
anywheresoftware.b4a.keywords.Common.ExitApplication();
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
public static String  _low_click() throws Exception{
try {
		Debug.PushSubsStack("Low_Click (play) ","play",16,mostCurrent.activityBA,mostCurrent,161);
 BA.debugLineNum = 161;BA.debugLine="Sub Low_Click";
Debug.ShouldStop(1);
 BA.debugLineNum = 162;BA.debugLine="ViVideoView.SetVideoPath(\"http://livetv.mncplaymedia.com/cdn/iptv/Tvod/001/channel2000037/512.m3u8\")";
Debug.ShouldStop(2);
mostCurrent._vivideoview.SetVideoPath("http://livetv.mncplaymedia.com/cdn/iptv/Tvod/001/channel2000037/512.m3u8");
 BA.debugLineNum = 163;BA.debugLine="PlayVideo";
Debug.ShouldStop(4);
_playvideo();
 BA.debugLineNum = 164;BA.debugLine="End Sub";
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
public static String  _medium_click() throws Exception{
try {
		Debug.PushSubsStack("Medium_Click (play) ","play",16,mostCurrent.activityBA,mostCurrent,166);
 BA.debugLineNum = 166;BA.debugLine="Sub Medium_Click";
Debug.ShouldStop(32);
 BA.debugLineNum = 167;BA.debugLine="ViVideoView.SetVideoPath(\"http://livetv.mncplaymedia.com/cdn/iptv/Tvod/001/channel2000037/512.m3u8\")";
Debug.ShouldStop(64);
mostCurrent._vivideoview.SetVideoPath("http://livetv.mncplaymedia.com/cdn/iptv/Tvod/001/channel2000037/512.m3u8");
 BA.debugLineNum = 168;BA.debugLine="PlayVideo";
Debug.ShouldStop(128);
_playvideo();
 BA.debugLineNum = 169;BA.debugLine="End Sub";
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
public static String  _pe_phonestatechanged(String _state,String _incomingnumber,anywheresoftware.b4a.objects.IntentWrapper _intent) throws Exception{
try {
		Debug.PushSubsStack("PE_PhoneStateChanged (play) ","play",16,mostCurrent.activityBA,mostCurrent,342);
Debug.locals.put("State", _state);
Debug.locals.put("IncomingNumber", _incomingnumber);
Debug.locals.put("Intent", _intent);
 BA.debugLineNum = 342;BA.debugLine="Sub PE_PhoneStateChanged (State As String, IncomingNumber As String, Intent As Intent)";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 343;BA.debugLine="If State = \"OFFHOOK\" Then StartActivity(bCall_Click)";
Debug.ShouldStop(4194304);
if ((_state).equals("OFFHOOK")) { 
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_bcall_click()));};
 BA.debugLineNum = 344;BA.debugLine="End Sub";
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
public static String  _playvideo() throws Exception{
try {
		Debug.PushSubsStack("PlayVideo (play) ","play",16,mostCurrent.activityBA,mostCurrent,195);
 BA.debugLineNum = 195;BA.debugLine="Sub PlayVideo";
Debug.ShouldStop(4);
 BA.debugLineNum = 207;BA.debugLine="End Sub";
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
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public static String  _tvideo_tick() throws Exception{
try {
		Debug.PushSubsStack("tVideo_Tick (play) ","play",16,mostCurrent.activityBA,mostCurrent,305);
 BA.debugLineNum = 305;BA.debugLine="Sub tVideo_Tick";
Debug.ShouldStop(65536);
 BA.debugLineNum = 306;BA.debugLine="ctoast.Show3(\"Offline Connection or Server Down\", LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(131072);
mostCurrent._ctoast.Show3((java.lang.CharSequence)("Offline Connection or Server Down"),(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()));
 BA.debugLineNum = 307;BA.debugLine="tVideo.Enabled = False";
Debug.ShouldStop(262144);
mostCurrent._tvideo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 308;BA.debugLine="GifPlayer.Visible = False";
Debug.ShouldStop(524288);
mostCurrent._gifplayer.setVisible(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 309;BA.debugLine="End Sub";
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
public static String  _very_click() throws Exception{
try {
		Debug.PushSubsStack("Very_Click (play) ","play",16,mostCurrent.activityBA,mostCurrent,176);
 BA.debugLineNum = 176;BA.debugLine="Sub Very_Click";
Debug.ShouldStop(32768);
 BA.debugLineNum = 177;BA.debugLine="ViVideoView.SetVideoPath(\"http://livetv.mncplaymedia.com/cdn/iptv/Tvod/001/channel2000037/512.m3u8\")";
Debug.ShouldStop(65536);
mostCurrent._vivideoview.SetVideoPath("http://livetv.mncplaymedia.com/cdn/iptv/Tvod/001/channel2000037/512.m3u8");
 BA.debugLineNum = 178;BA.debugLine="PlayVideo";
Debug.ShouldStop(131072);
_playvideo();
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
public static String  _vimediacontrol_hidden() throws Exception{
try {
		Debug.PushSubsStack("ViMediaControl_Hidden (play) ","play",16,mostCurrent.activityBA,mostCurrent,209);
 BA.debugLineNum = 209;BA.debugLine="Sub ViMediaControl_Hidden";
Debug.ShouldStop(65536);
 BA.debugLineNum = 210;BA.debugLine="tVideo.Enabled = False";
Debug.ShouldStop(131072);
mostCurrent._tvideo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
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
public static String  _vimediacontrol_shown() throws Exception{
try {
		Debug.PushSubsStack("ViMediaControl_Shown (play) ","play",16,mostCurrent.activityBA,mostCurrent,213);
 BA.debugLineNum = 213;BA.debugLine="Sub ViMediaControl_Shown";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 214;BA.debugLine="tVideo.Enabled = False";
Debug.ShouldStop(2097152);
mostCurrent._tvideo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 215;BA.debugLine="End Sub";
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
public static boolean  _vitavideoview_info(int _what,int _extra) throws Exception{
try {
		Debug.PushSubsStack("VitaVideoView_Info (play) ","play",16,mostCurrent.activityBA,mostCurrent,235);
Debug.locals.put("What", _what);
Debug.locals.put("Extra", _extra);
 BA.debugLineNum = 235;BA.debugLine="Sub VitaVideoView_Info(What As Int, Extra As Int) As Boolean";
Debug.ShouldStop(1024);
 BA.debugLineNum = 236;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
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
		Debug.PushSubsStack("ViVideoView_BitmapSubtitleUpdated (play) ","play",16,mostCurrent.activityBA,mostCurrent,217);
Debug.locals.put("Bitmap1", _bitmap1);
Debug.locals.put("Width", _width);
Debug.locals.put("Height", _height);
 BA.debugLineNum = 217;BA.debugLine="Sub ViVideoView_BitmapSubtitleUpdated(Bitmap1() As Byte, Width As Int, Height As Int)";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 218;BA.debugLine="End Sub";
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
public static String  _vivideoview_buffering(int _percent) throws Exception{
try {
		Debug.PushSubsStack("ViVideoView_Buffering (play) ","play",16,mostCurrent.activityBA,mostCurrent,220);
Debug.locals.put("Percent", _percent);
 BA.debugLineNum = 220;BA.debugLine="Sub ViVideoView_Buffering(Percent As Int)";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 221;BA.debugLine="GifPlayer.Visible = False";
Debug.ShouldStop(268435456);
mostCurrent._gifplayer.setVisible(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 222;BA.debugLine="tVideo.Enabled = False";
Debug.ShouldStop(536870912);
mostCurrent._tvideo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 223;BA.debugLine="End Sub";
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
public static String  _vivideoview_complete() throws Exception{
try {
		Debug.PushSubsStack("ViVideoView_Complete (play) ","play",16,mostCurrent.activityBA,mostCurrent,225);
 BA.debugLineNum = 225;BA.debugLine="Sub ViVideoView_Complete";
Debug.ShouldStop(1);
 BA.debugLineNum = 226;BA.debugLine="End Sub";
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
public static boolean  _vivideoview_error(int _media_error) throws Exception{
try {
		Debug.PushSubsStack("ViVideoView_Error (play) ","play",16,mostCurrent.activityBA,mostCurrent,228);
Debug.locals.put("MEDIA_ERROR", _media_error);
 BA.debugLineNum = 228;BA.debugLine="Sub ViVideoView_Error(MEDIA_ERROR As Int) As Boolean";
Debug.ShouldStop(8);
 BA.debugLineNum = 229;BA.debugLine="GifPlayer.Visible = False";
Debug.ShouldStop(16);
mostCurrent._gifplayer.setVisible(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 230;BA.debugLine="tVideo.Enabled = False";
Debug.ShouldStop(32);
mostCurrent._tvideo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 231;BA.debugLine="ViVideoView.StopPlayback";
Debug.ShouldStop(64);
mostCurrent._vivideoview.StopPlayback();
 BA.debugLineNum = 232;BA.debugLine="ctoast.Show3(\"Error code : \" & MEDIA_ERROR, LoadBitmap(File.DirAssets, \"warning.png\"))";
Debug.ShouldStop(128);
mostCurrent._ctoast.Show3((java.lang.CharSequence)("Error code : "+BA.NumberToString(_media_error)),(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()));
 BA.debugLineNum = 233;BA.debugLine="End Sub";
Debug.ShouldStop(256);
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
		Debug.PushSubsStack("ViVideoView_Prepared (play) ","play",16,mostCurrent.activityBA,mostCurrent,238);
 BA.debugLineNum = 238;BA.debugLine="Sub ViVideoView_Prepared";
Debug.ShouldStop(8192);
 BA.debugLineNum = 239;BA.debugLine="tVideo.Enabled = False";
Debug.ShouldStop(16384);
mostCurrent._tvideo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 BA.debugLineNum = 240;BA.debugLine="End Sub";
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
public static String  _vivideoview_seekcomplete() throws Exception{
try {
		Debug.PushSubsStack("ViVideoView_SeekComplete (play) ","play",16,mostCurrent.activityBA,mostCurrent,242);
 BA.debugLineNum = 242;BA.debugLine="Sub ViVideoView_SeekComplete";
Debug.ShouldStop(131072);
 BA.debugLineNum = 244;BA.debugLine="End Sub";
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
public static String  _vivideoview_textsubtitleupdated(String _subtext) throws Exception{
try {
		Debug.PushSubsStack("ViVideoView_TextSubtitleUpdated (play) ","play",16,mostCurrent.activityBA,mostCurrent,246);
Debug.locals.put("SubText", _subtext);
 BA.debugLineNum = 246;BA.debugLine="Sub ViVideoView_TextSubtitleUpdated(SubText As String)";
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
}
