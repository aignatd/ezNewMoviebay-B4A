package newmoviebay.mncplaymedia.com;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class mncutils2service extends android.app.Service {
	public static class mncutils2service_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
			android.content.Intent in = new android.content.Intent(context, mncutils2service.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
			context.startService(in);
		}

	}
    static mncutils2service mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return mncutils2service.class;
	}
	@Override
	public void onCreate() {
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "newmoviebay.mncplaymedia.com", "newmoviebay.mncplaymedia.com.mncutils2service");
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        processBA.setActivityPaused(false);
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "newmoviebay.mncplaymedia.com.mncutils2service", processBA, _service);
		}
        BA.LogInfo("** Service (mncutils2service) Create **");
        processBA.raiseEvent(null, "service_create");
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		handleStart(intent);
    }
    @Override
    public int onStartCommand(android.content.Intent intent, int flags, int startId) {
    	handleStart(intent);
		return android.app.Service.START_NOT_STICKY;
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (mncutils2service) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = new anywheresoftware.b4a.objects.IntentWrapper();
    			if (intent != null) {
    				if (intent.hasExtra("b4a_internal_intent"))
    					iw.setObject((android.content.Intent) intent.getParcelableExtra("b4a_internal_intent"));
    				else
    					iw.setObject(intent);
    			}
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }
	@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}
	@Override
	public void onDestroy() {
        BA.LogInfo("** Service (mncutils2service) Destroy **");
		processBA.raiseEvent(null, "service_destroy");
        processBA.service = null;
		mostCurrent = null;
		processBA.setActivityPaused(true);
	}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.http.HttpClientWrapper _hc = null;
public static anywheresoftware.b4a.objects.collections.Map _taskidtojob = null;
public static String _tempfolder = "";
public static int _taskcounter = 0;
public static anywheresoftware.b4a.objects.collections.Map _tmpmap = null;
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
public newmoviebay.mncplaymedia.com.pemutar _pemutar = null;
public newmoviebay.mncplaymedia.com.webview _webview = null;
public newmoviebay.mncplaymedia.com.play _play = null;
public newmoviebay.mncplaymedia.com.result _result = null;
public newmoviebay.mncplaymedia.com.date _date = null;
  public Object[] GetGlobals() {
		return new Object[] {"Content",Debug.moduleToString(newmoviebay.mncplaymedia.com.content.class),"Date",Debug.moduleToString(newmoviebay.mncplaymedia.com.date.class),"DateUtils",mostCurrent._dateutils,"ForgotPass",Debug.moduleToString(newmoviebay.mncplaymedia.com.forgotpass.class),"Fungsi",Debug.moduleToString(newmoviebay.mncplaymedia.com.fungsi.class),"hc",_hc,"Main",Debug.moduleToString(newmoviebay.mncplaymedia.com.main.class),"MenuSearch",Debug.moduleToString(newmoviebay.mncplaymedia.com.menusearch.class),"Pemutar",Debug.moduleToString(newmoviebay.mncplaymedia.com.pemutar.class),"Play",Debug.moduleToString(newmoviebay.mncplaymedia.com.play.class),"Profile",Debug.moduleToString(newmoviebay.mncplaymedia.com.profile.class),"Register",Debug.moduleToString(newmoviebay.mncplaymedia.com.register.class),"Result",Debug.moduleToString(newmoviebay.mncplaymedia.com.result.class),"Service",mostCurrent._service,"Share",Debug.moduleToString(newmoviebay.mncplaymedia.com.share.class),"taskCounter",_taskcounter,"TaskIdToJob",_taskidtojob,"TempFolder",_tempfolder,"tmpMap",_tmpmap,"Utama",Debug.moduleToString(newmoviebay.mncplaymedia.com.utama.class),"WebView",Debug.moduleToString(newmoviebay.mncplaymedia.com.webview.class)};
}
public static String  _clearcookies() throws Exception{
try {
		Debug.PushSubsStack("ClearCookies (mncutils2service) ","mncutils2service",12,processBA,mostCurrent,126);
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
 BA.debugLineNum = 126;BA.debugLine="Sub ClearCookies";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 127;BA.debugLine="Dim r As Reflector";
Debug.ShouldStop(1073741824);
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();Debug.locals.put("r", _r);
 BA.debugLineNum = 128;BA.debugLine="r.Target = hc";
Debug.ShouldStop(-2147483648);
_r.Target = (Object)(_hc);Debug.locals.put("r", _r);
 BA.debugLineNum = 129;BA.debugLine="r.Target = r.GetField(\"client\")";
Debug.ShouldStop(1);
_r.Target = _r.GetField("client");Debug.locals.put("r", _r);
 BA.debugLineNum = 130;BA.debugLine="r.Target = r.RunMethod(\"getCookieStore\")";
Debug.ShouldStop(2);
_r.Target = _r.RunMethod("getCookieStore");Debug.locals.put("r", _r);
 BA.debugLineNum = 131;BA.debugLine="r.RunMethod(\"clear\")";
Debug.ShouldStop(4);
_r.RunMethod("clear");
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
public static String  _completejob(int _taskid,boolean _success,String _errormessage) throws Exception{
try {
		Debug.PushSubsStack("CompleteJob (mncutils2service) ","mncutils2service",12,processBA,mostCurrent,110);
newmoviebay.mncplaymedia.com.mnchttpjob _job = null;
Debug.locals.put("TaskId", _taskid);
Debug.locals.put("success", _success);
Debug.locals.put("errorMessage", _errormessage);
 BA.debugLineNum = 110;BA.debugLine="Sub CompleteJob(TaskId As Int, success As Boolean, errorMessage As String)";
Debug.ShouldStop(8192);
 BA.debugLineNum = 111;BA.debugLine="Dim job As MNChttpJob";
Debug.ShouldStop(16384);
_job = new newmoviebay.mncplaymedia.com.mnchttpjob();Debug.locals.put("job", _job);
 BA.debugLineNum = 113;BA.debugLine="Try";
Debug.ShouldStop(65536);
try { BA.debugLineNum = 114;BA.debugLine="job = TaskIdToJob.Get(TaskId)";
Debug.ShouldStop(131072);
_job = (newmoviebay.mncplaymedia.com.mnchttpjob)(_taskidtojob.Get((Object)(_taskid)));Debug.locals.put("job", _job);
 BA.debugLineNum = 115;BA.debugLine="TaskIdToJob.Remove(TaskId)";
Debug.ShouldStop(262144);
_taskidtojob.Remove((Object)(_taskid));
 BA.debugLineNum = 116;BA.debugLine="job.success = success";
Debug.ShouldStop(524288);
_job._success = _success;Debug.locals.put("job", _job);
 BA.debugLineNum = 117;BA.debugLine="job.errorMessage = errorMessage";
Debug.ShouldStop(1048576);
_job._errormessage = _errormessage;Debug.locals.put("job", _job);
 BA.debugLineNum = 118;BA.debugLine="job.Complete(TaskId)";
Debug.ShouldStop(2097152);
_job._complete(_taskid);
 BA.debugLineNum = 119;BA.debugLine="job.ResMap = tmpMap";
Debug.ShouldStop(4194304);
_job._resmap = _tmpmap;Debug.locals.put("job", _job);
 BA.debugLineNum = 120;BA.debugLine="If job.ResMap.Get(\"Cookies\") = \"Hapus\" Then ClearCookies";
Debug.ShouldStop(8388608);
if ((_job._resmap.Get((Object)("Cookies"))).equals((Object)("Hapus"))) { 
_clearcookies();};
 } 
       catch (Exception e92) {
			processBA.setLastException(e92); BA.debugLineNum = 122;BA.debugLine="Log(errorMessage)";
Debug.ShouldStop(33554432);
anywheresoftware.b4a.keywords.Common.Log(_errormessage);
 };
 BA.debugLineNum = 124;BA.debugLine="End Sub";
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
public static String  _hc_responseerror(anywheresoftware.b4a.http.HttpClientWrapper.HttpResponeWrapper _response,String _reason,int _statuscode,int _taskid) throws Exception{
try {
		Debug.PushSubsStack("hc_ResponseError (mncutils2service) ","mncutils2service",12,processBA,mostCurrent,73);
int _i = 0;
int _k = 0;
String[] _z = null;
String[] _z1 = null;
anywheresoftware.b4a.objects.collections.List _lstcookie = null;
Debug.locals.put("Response", _response);
Debug.locals.put("Reason", _reason);
Debug.locals.put("StatusCode", _statuscode);
Debug.locals.put("TaskId", _taskid);
 BA.debugLineNum = 73;BA.debugLine="Sub hc_ResponseError (Response As HttpResponse, Reason As String, StatusCode As Int, TaskId As Int)";
Debug.ShouldStop(256);
 BA.debugLineNum = 74;BA.debugLine="Dim i 		As Int = 0";
Debug.ShouldStop(512);
_i = (int) (0);Debug.locals.put("i", _i);Debug.locals.put("i", _i);
 BA.debugLineNum = 75;BA.debugLine="Dim k 		As Int = 0";
Debug.ShouldStop(1024);
_k = (int) (0);Debug.locals.put("k", _k);Debug.locals.put("k", _k);
 BA.debugLineNum = 76;BA.debugLine="Dim z() 	As String";
Debug.ShouldStop(2048);
_z = new String[(int) (0)];
java.util.Arrays.fill(_z,"");Debug.locals.put("z", _z);
 BA.debugLineNum = 77;BA.debugLine="Dim z1() 	As String";
Debug.ShouldStop(4096);
_z1 = new String[(int) (0)];
java.util.Arrays.fill(_z1,"");Debug.locals.put("z1", _z1);
 BA.debugLineNum = 78;BA.debugLine="Dim lstCookie As List";
Debug.ShouldStop(8192);
_lstcookie = new anywheresoftware.b4a.objects.collections.List();Debug.locals.put("lstCookie", _lstcookie);
 BA.debugLineNum = 80;BA.debugLine="If Response <> Null Then";
Debug.ShouldStop(32768);
if (_response!= null) { 
 BA.debugLineNum = 81;BA.debugLine="tmpMap.Clear";
Debug.ShouldStop(65536);
_tmpmap.Clear();
 BA.debugLineNum = 82;BA.debugLine="tmpMap = Response.GetHeaders";
Debug.ShouldStop(131072);
_tmpmap = _response.GetHeaders();
 BA.debugLineNum = 83;BA.debugLine="tmpMap.Put(\"StatusCode\", StatusCode)";
Debug.ShouldStop(262144);
_tmpmap.Put((Object)("StatusCode"),(Object)(_statuscode));
 BA.debugLineNum = 85;BA.debugLine="If tmpMap.ContainsKey(\"Set-Cookie\") Then";
Debug.ShouldStop(1048576);
if (_tmpmap.ContainsKey((Object)("Set-Cookie"))) { 
 BA.debugLineNum = 86;BA.debugLine="lstCookie = tmpMap.Get(\"Set-Cookie\")";
Debug.ShouldStop(2097152);
_lstcookie.setObject((java.util.List)(_tmpmap.Get((Object)("Set-Cookie"))));
 BA.debugLineNum = 87;BA.debugLine="z = Regex.Split(\";\", lstCookie.Get(0))";
Debug.ShouldStop(4194304);
_z = anywheresoftware.b4a.keywords.Common.Regex.Split(";",BA.ObjectToString(_lstcookie.Get((int) (0))));Debug.locals.put("z", _z);
 BA.debugLineNum = 88;BA.debugLine="z1 = Regex.Split(\"=\", z(0))";
Debug.ShouldStop(8388608);
_z1 = anywheresoftware.b4a.keywords.Common.Regex.Split("=",_z[(int) (0)]);Debug.locals.put("z1", _z1);
 BA.debugLineNum = 89;BA.debugLine="tmpMap.Put(\"JSESSIONID\", z1(1))";
Debug.ShouldStop(16777216);
_tmpmap.Put((Object)("JSESSIONID"),(Object)(_z1[(int) (1)]));
 };
 BA.debugLineNum = 92;BA.debugLine="Try";
Debug.ShouldStop(134217728);
try { BA.debugLineNum = 93;BA.debugLine="Log(Response.GetString(\"UTF8\"))";
Debug.ShouldStop(268435456);
anywheresoftware.b4a.keywords.Common.Log(_response.GetString("UTF8"));
 } 
       catch (Exception e69) {
			processBA.setLastException(e69); BA.debugLineNum = 95;BA.debugLine="Log(\"Failed to read error message.\")";
Debug.ShouldStop(1073741824);
anywheresoftware.b4a.keywords.Common.Log("Failed to read error message.");
 };
 BA.debugLineNum = 98;BA.debugLine="If Floor(StatusCode / 100) = 3 Then";
Debug.ShouldStop(2);
if (anywheresoftware.b4a.keywords.Common.Floor(_statuscode/(double)100)==3) { 
 BA.debugLineNum = 99;BA.debugLine="i = 1";
Debug.ShouldStop(4);
_i = (int) (1);Debug.locals.put("i", _i);
 };
 };
 BA.debugLineNum = 103;BA.debugLine="If i = 0 Then";
Debug.ShouldStop(64);
if (_i==0) { 
 BA.debugLineNum = 104;BA.debugLine="CompleteJob(TaskId, False, Reason)";
Debug.ShouldStop(128);
_completejob(_taskid,anywheresoftware.b4a.keywords.Common.False,_reason);
 }else {
 BA.debugLineNum = 106;BA.debugLine="CompleteJob(TaskId, True, \"\")";
Debug.ShouldStop(512);
_completejob(_taskid,anywheresoftware.b4a.keywords.Common.True,"");
 };
 BA.debugLineNum = 108;BA.debugLine="End Sub";
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
public static String  _hc_responsesuccess(anywheresoftware.b4a.http.HttpClientWrapper.HttpResponeWrapper _response,int _taskid) throws Exception{
try {
		Debug.PushSubsStack("hc_ResponseSuccess (mncutils2service) ","mncutils2service",12,processBA,mostCurrent,46);
String[] _z = null;
String[] _z1 = null;
anywheresoftware.b4a.objects.collections.List _lstcookie = null;
Debug.locals.put("Response", _response);
Debug.locals.put("TaskId", _taskid);
 BA.debugLineNum = 46;BA.debugLine="Sub hc_ResponseSuccess (Response As HttpResponse, TaskId As Int)";
Debug.ShouldStop(8192);
 BA.debugLineNum = 47;BA.debugLine="Dim z() 	As String";
Debug.ShouldStop(16384);
_z = new String[(int) (0)];
java.util.Arrays.fill(_z,"");Debug.locals.put("z", _z);
 BA.debugLineNum = 48;BA.debugLine="Dim z1() 	As String";
Debug.ShouldStop(32768);
_z1 = new String[(int) (0)];
java.util.Arrays.fill(_z1,"");Debug.locals.put("z1", _z1);
 BA.debugLineNum = 49;BA.debugLine="Dim lstCookie As List";
Debug.ShouldStop(65536);
_lstcookie = new anywheresoftware.b4a.objects.collections.List();Debug.locals.put("lstCookie", _lstcookie);
 BA.debugLineNum = 51;BA.debugLine="tmpMap.Clear";
Debug.ShouldStop(262144);
_tmpmap.Clear();
 BA.debugLineNum = 52;BA.debugLine="tmpMap = Response.GetHeaders";
Debug.ShouldStop(524288);
_tmpmap = _response.GetHeaders();
 BA.debugLineNum = 54;BA.debugLine="If tmpMap.ContainsKey(\"Set-Cookie\") Then";
Debug.ShouldStop(2097152);
if (_tmpmap.ContainsKey((Object)("Set-Cookie"))) { 
 BA.debugLineNum = 55;BA.debugLine="lstCookie = tmpMap.Get(\"Set-Cookie\")";
Debug.ShouldStop(4194304);
_lstcookie.setObject((java.util.List)(_tmpmap.Get((Object)("Set-Cookie"))));
 BA.debugLineNum = 56;BA.debugLine="z = Regex.Split(\";\", lstCookie.Get(0))";
Debug.ShouldStop(8388608);
_z = anywheresoftware.b4a.keywords.Common.Regex.Split(";",BA.ObjectToString(_lstcookie.Get((int) (0))));Debug.locals.put("z", _z);
 BA.debugLineNum = 57;BA.debugLine="z1 = Regex.Split(\"=\", z(0))";
Debug.ShouldStop(16777216);
_z1 = anywheresoftware.b4a.keywords.Common.Regex.Split("=",_z[(int) (0)]);Debug.locals.put("z1", _z1);
 BA.debugLineNum = 58;BA.debugLine="tmpMap.Put(\"JSESSIONID\", z1(1))";
Debug.ShouldStop(33554432);
_tmpmap.Put((Object)("JSESSIONID"),(Object)(_z1[(int) (1)]));
 };
 BA.debugLineNum = 61;BA.debugLine="If File.Exists(TempFolder, TaskId) Then File.Delete(TempFolder, TaskId)";
Debug.ShouldStop(268435456);
if (anywheresoftware.b4a.keywords.Common.File.Exists(_tempfolder,BA.NumberToString(_taskid))) { 
anywheresoftware.b4a.keywords.Common.File.Delete(_tempfolder,BA.NumberToString(_taskid));};
 BA.debugLineNum = 62;BA.debugLine="Response.GetAsynchronously(\"response\", File.OpenOutput(TempFolder, TaskId, False), True, TaskId)";
Debug.ShouldStop(536870912);
_response.GetAsynchronously(processBA,"response",(java.io.OutputStream)(anywheresoftware.b4a.keywords.Common.File.OpenOutput(_tempfolder,BA.NumberToString(_taskid),anywheresoftware.b4a.keywords.Common.False).getObject()),anywheresoftware.b4a.keywords.Common.True,_taskid);
 BA.debugLineNum = 63;BA.debugLine="End Sub";
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
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Private hc 			As HttpClient";
_hc = new anywheresoftware.b4a.http.HttpClientWrapper();
 //BA.debugLineNum = 9;BA.debugLine="Private TaskIdToJob As Map";
_taskidtojob = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 10;BA.debugLine="Public TempFolder 	As String";
_tempfolder = "";
 //BA.debugLineNum = 11;BA.debugLine="Private taskCounter As Int";
_taskcounter = 0;
 //BA.debugLineNum = 12;BA.debugLine="Private tmpMap 		As Map";
_tmpmap = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public static String  _response_streamfinish(boolean _success,int _taskid) throws Exception{
try {
		Debug.PushSubsStack("Response_StreamFinish (mncutils2service) ","mncutils2service",12,processBA,mostCurrent,65);
Debug.locals.put("Success", _success);
Debug.locals.put("TaskId", _taskid);
 BA.debugLineNum = 65;BA.debugLine="Sub Response_StreamFinish (Success As Boolean, TaskId As Int)";
Debug.ShouldStop(1);
 BA.debugLineNum = 66;BA.debugLine="If Success Then";
Debug.ShouldStop(2);
if (_success) { 
 BA.debugLineNum = 67;BA.debugLine="CompleteJob(TaskId, Success, \"\")";
Debug.ShouldStop(4);
_completejob(_taskid,_success,"");
 }else {
 BA.debugLineNum = 69;BA.debugLine="CompleteJob(TaskId, Success, LastException.Message)";
Debug.ShouldStop(16);
_completejob(_taskid,_success,anywheresoftware.b4a.keywords.Common.LastException(processBA).getMessage());
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
public static String  _service_create() throws Exception{
try {
		Debug.PushSubsStack("Service_Create (mncutils2service) ","mncutils2service",12,processBA,mostCurrent,15);
 BA.debugLineNum = 15;BA.debugLine="Sub Service_Create";
Debug.ShouldStop(16384);
 BA.debugLineNum = 16;BA.debugLine="TempFolder = File.DirInternalCache";
Debug.ShouldStop(32768);
_tempfolder = anywheresoftware.b4a.keywords.Common.File.getDirInternalCache();
 BA.debugLineNum = 17;BA.debugLine="If hc.IsInitialized = False Then hc.Initialize(\"hc\")";
Debug.ShouldStop(65536);
if (_hc.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
Debug.DebugWarningEngine.CheckInitialize(_hc);_hc.Initialize("hc");};
 BA.debugLineNum = 19;BA.debugLine="TaskIdToJob.Initialize";
Debug.ShouldStop(262144);
_taskidtojob.Initialize();
 BA.debugLineNum = 20;BA.debugLine="tmpMap.Initialize";
Debug.ShouldStop(524288);
_tmpmap.Initialize();
 BA.debugLineNum = 21;BA.debugLine="End Sub";
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
public static String  _service_destroy() throws Exception{
try {
		Debug.PushSubsStack("Service_Destroy (mncutils2service) ","mncutils2service",12,processBA,mostCurrent,27);
 BA.debugLineNum = 27;BA.debugLine="Sub Service_Destroy";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 29;BA.debugLine="End Sub";
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
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
try {
		Debug.PushSubsStack("Service_Start (mncutils2service) ","mncutils2service",12,processBA,mostCurrent,23);
Debug.locals.put("StartingIntent", _startingintent);
 BA.debugLineNum = 23;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 25;BA.debugLine="End Sub";
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
public static int  _submitjob(newmoviebay.mncplaymedia.com.mnchttpjob _job) throws Exception{
try {
		Debug.PushSubsStack("SubmitJob (mncutils2service) ","mncutils2service",12,processBA,mostCurrent,31);
Debug.locals.put("job", _job);
 BA.debugLineNum = 31;BA.debugLine="Public Sub SubmitJob(job As MNChttpJob) As Int";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 32;BA.debugLine="taskCounter = taskCounter + 1";
Debug.ShouldStop(-2147483648);
_taskcounter = (int) (_taskcounter+1);
 BA.debugLineNum = 33;BA.debugLine="TaskIdToJob.Put(taskCounter, job)";
Debug.ShouldStop(1);
_taskidtojob.Put((Object)(_taskcounter),(Object)(_job));
 BA.debugLineNum = 35;BA.debugLine="If job.Username <> \"\" AND job.Password <> \"\" Then";
Debug.ShouldStop(4);
if ((_job._username).equals("") == false && (_job._password).equals("") == false) { 
 BA.debugLineNum = 36;BA.debugLine="hc.ExecuteCredentials(job.GetRequest, taskCounter, job.Username, job.Password)";
Debug.ShouldStop(8);
_hc.ExecuteCredentials(processBA,_job._getrequest(),_taskcounter,_job._username,_job._password);
 }else {
 BA.debugLineNum = 38;BA.debugLine="hc.Execute(job.GetRequest, taskCounter)";
Debug.ShouldStop(32);
_hc.Execute(processBA,_job._getrequest(),_taskcounter);
 };
 BA.debugLineNum = 41;BA.debugLine="hc.SetHttpParameter(\"http.protocol.handle-redirects\", False)";
Debug.ShouldStop(256);
_hc.SetHttpParameter("http.protocol.handle-redirects",(Object)(anywheresoftware.b4a.keywords.Common.False));
 BA.debugLineNum = 43;BA.debugLine="Return taskCounter";
Debug.ShouldStop(1024);
if (true) return _taskcounter;
 BA.debugLineNum = 44;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return 0;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
}
