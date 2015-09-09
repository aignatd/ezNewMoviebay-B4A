package newmoviebay.mncplaymedia.com;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class mnchttpjob extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "newmoviebay.mncplaymedia.com.mnchttpjob");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            if (BA.isShellModeRuntimeCheck(ba)) {
			    ba.raiseEvent2(null, true, "CREATE", true, "newmoviebay.mncplaymedia.com.mnchttpjob",
                    ba);
                return;
		    }
        }
        ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public String _jobname = "";
public boolean _success = false;
public String _username = "";
public String _password = "";
public String _errormessage = "";
public Object _target = null;
public String _taskid = "";
public anywheresoftware.b4a.http.HttpClientWrapper.HttpUriRequestWrapper _req = null;
public Object _tag = null;
public anywheresoftware.b4a.objects.collections.Map _resmap = null;
public anywheresoftware.b4a.objects.collections.Map _promap = null;
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
		return new Object[] {"Content",Debug.moduleToString(newmoviebay.mncplaymedia.com.content.class),"Date",Debug.moduleToString(newmoviebay.mncplaymedia.com.date.class),"DateUtils",_dateutils,"ErrorMessage",_errormessage,"ForgotPass",Debug.moduleToString(newmoviebay.mncplaymedia.com.forgotpass.class),"Fungsi",Debug.moduleToString(newmoviebay.mncplaymedia.com.fungsi.class),"JobName",_jobname,"Main",Debug.moduleToString(newmoviebay.mncplaymedia.com.main.class),"MenuSearch",Debug.moduleToString(newmoviebay.mncplaymedia.com.menusearch.class),"MNCUtils2Service",Debug.moduleToString(newmoviebay.mncplaymedia.com.mncutils2service.class),"Password",_password,"Pemutar",Debug.moduleToString(newmoviebay.mncplaymedia.com.pemutar.class),"Play",Debug.moduleToString(newmoviebay.mncplaymedia.com.play.class),"Profile",Debug.moduleToString(newmoviebay.mncplaymedia.com.profile.class),"ProMap",_promap,"Register",Debug.moduleToString(newmoviebay.mncplaymedia.com.register.class),"req",_req,"ResMap",_resmap,"Result",Debug.moduleToString(newmoviebay.mncplaymedia.com.result.class),"Share",Debug.moduleToString(newmoviebay.mncplaymedia.com.share.class),"Success",_success,"Tag",_tag,"target",_target,"taskId",_taskid,"Username",_username,"Utama",Debug.moduleToString(newmoviebay.mncplaymedia.com.utama.class),"WebView",Debug.moduleToString(newmoviebay.mncplaymedia.com.webview.class)};
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 2;BA.debugLine="Public JobName 			As String";
_jobname = "";
 //BA.debugLineNum = 3;BA.debugLine="Public Success 			As Boolean";
_success = false;
 //BA.debugLineNum = 4;BA.debugLine="Public Username 		As String";
_username = "";
 //BA.debugLineNum = 5;BA.debugLine="Public Password 		As String";
_password = "";
 //BA.debugLineNum = 6;BA.debugLine="Public ErrorMessage 	As String";
_errormessage = "";
 //BA.debugLineNum = 7;BA.debugLine="Private target 			As Object";
_target = new Object();
 //BA.debugLineNum = 8;BA.debugLine="Private taskId 			As String";
_taskid = "";
 //BA.debugLineNum = 9;BA.debugLine="Private req 			As HttpRequest";
_req = new anywheresoftware.b4a.http.HttpClientWrapper.HttpUriRequestWrapper();
 //BA.debugLineNum = 10;BA.debugLine="Public Tag 				As Object";
_tag = new Object();
 //BA.debugLineNum = 11;BA.debugLine="Public ResMap			As Map";
_resmap = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 12;BA.debugLine="Public ProMap			As Map";
_promap = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public String  _complete(int _id) throws Exception{
try {
		Debug.PushSubsStack("Complete (mnchttpjob) ","mnchttpjob",10,ba,this,88);
Debug.locals.put("id", _id);
 BA.debugLineNum = 88;BA.debugLine="Public Sub Complete (id As Int)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 89;BA.debugLine="taskId = id";
Debug.ShouldStop(16777216);
_taskid = BA.NumberToString(_id);
 BA.debugLineNum = 90;BA.debugLine="CallSubDelayed2(target, \"JobDone\", Me)";
Debug.ShouldStop(33554432);
__c.CallSubDelayed2(getActivityBA(),_target,"JobDone",this);
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
public String  _download(String _link) throws Exception{
try {
		Debug.PushSubsStack("Download (mnchttpjob) ","mnchttpjob",10,ba,this,58);
Debug.locals.put("Link", _link);
 BA.debugLineNum = 58;BA.debugLine="Public Sub Download(Link As String)";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 59;BA.debugLine="req.InitializeGet(Link)";
Debug.ShouldStop(67108864);
_req.InitializeGet(_link);
 BA.debugLineNum = 60;BA.debugLine="CallSubDelayed2(MNCUtils2Service, \"SubmitJob\", Me)";
Debug.ShouldStop(134217728);
__c.CallSubDelayed2(getActivityBA(),(Object)(_mncutils2service.getObject()),"SubmitJob",this);
 BA.debugLineNum = 61;BA.debugLine="End Sub";
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
public String  _download2(String _link,String[] _parameters) throws Exception{
try {
		Debug.PushSubsStack("Download2 (mnchttpjob) ","mnchttpjob",10,ba,this,67);
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
int _i = 0;
Debug.locals.put("Link", _link);
Debug.locals.put("Parameters", _parameters);
 BA.debugLineNum = 67;BA.debugLine="Public Sub Download2(Link As String, Parameters() As String)";
Debug.ShouldStop(4);
 BA.debugLineNum = 68;BA.debugLine="Dim sb As StringBuilder";
Debug.ShouldStop(8);
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();Debug.locals.put("sb", _sb);
 BA.debugLineNum = 69;BA.debugLine="sb.Initialize";
Debug.ShouldStop(16);
_sb.Initialize();
 BA.debugLineNum = 70;BA.debugLine="sb.Append(Link)";
Debug.ShouldStop(32);
_sb.Append(_link);
 BA.debugLineNum = 71;BA.debugLine="If Parameters.Length > 0 Then sb.Append(\"?\")";
Debug.ShouldStop(64);
if (_parameters.length>0) { 
_sb.Append("?");};
 BA.debugLineNum = 72;BA.debugLine="Dim su As StringUtils";
Debug.ShouldStop(128);
_su = new anywheresoftware.b4a.objects.StringUtils();Debug.locals.put("su", _su);
 BA.debugLineNum = 73;BA.debugLine="For i = 0 To Parameters.Length - 1 Step 2";
Debug.ShouldStop(256);
{
final int step53 = (int) (2);
final int limit53 = (int) (_parameters.length-1);
for (_i = (int) (0); (step53 > 0 && _i <= limit53) || (step53 < 0 && _i >= limit53); _i = ((int)(0 + _i + step53))) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 74;BA.debugLine="If i > 0 Then sb.Append(\"&\")";
Debug.ShouldStop(512);
if (_i>0) { 
_sb.Append("&");};
 BA.debugLineNum = 75;BA.debugLine="sb.Append(su.EncodeUrl(Parameters(i), \"UTF8\")).Append(\"=\")";
Debug.ShouldStop(1024);
_sb.Append(_su.EncodeUrl(_parameters[_i],"UTF8")).Append("=");
 BA.debugLineNum = 76;BA.debugLine="sb.Append(su.EncodeUrl(Parameters(i + 1), \"UTF8\"))";
Debug.ShouldStop(2048);
_sb.Append(_su.EncodeUrl(_parameters[(int) (_i+1)],"UTF8"));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 78;BA.debugLine="req.InitializeGet(sb.ToString)";
Debug.ShouldStop(8192);
_req.InitializeGet(_sb.ToString());
 BA.debugLineNum = 79;BA.debugLine="CallSubDelayed2(MNCUtils2Service, \"SubmitJob\", Me)";
Debug.ShouldStop(16384);
__c.CallSubDelayed2(getActivityBA(),(Object)(_mncutils2service.getObject()),"SubmitJob",this);
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
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _getbitmap() throws Exception{
try {
		Debug.PushSubsStack("GetBitmap (mnchttpjob) ","mnchttpjob",10,ba,this,136);
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _b = null;
 BA.debugLineNum = 136;BA.debugLine="Public Sub GetBitmap As Bitmap";
Debug.ShouldStop(128);
 BA.debugLineNum = 137;BA.debugLine="Dim b As Bitmap";
Debug.ShouldStop(256);
_b = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();Debug.locals.put("b", _b);
 BA.debugLineNum = 139;BA.debugLine="b.Initialize(MNCUtils2Service.TempFolder, taskId)";
Debug.ShouldStop(1024);
_b.Initialize(_mncutils2service._tempfolder,_taskid);
 BA.debugLineNum = 140;BA.debugLine="Return b";
Debug.ShouldStop(2048);
if (true) return _b;
 BA.debugLineNum = 141;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return null;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _getbitmapsampled(int _width,int _height) throws Exception{
try {
		Debug.PushSubsStack("GetBitmapSampled (mnchttpjob) ","mnchttpjob",10,ba,this,149);
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _b = null;
Debug.locals.put("Width", _width);
Debug.locals.put("Height", _height);
 BA.debugLineNum = 149;BA.debugLine="Public Sub GetBitmapSampled(Width As Int, Height As Int) As Bitmap";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 150;BA.debugLine="Dim b As Bitmap";
Debug.ShouldStop(2097152);
_b = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();Debug.locals.put("b", _b);
 BA.debugLineNum = 151;BA.debugLine="b = LoadBitmapSample(MNCUtils2Service.TempFolder, taskId, Width, Height)";
Debug.ShouldStop(4194304);
_b = __c.LoadBitmapSample(_mncutils2service._tempfolder,_taskid,_width,_height);Debug.locals.put("b", _b);
 BA.debugLineNum = 152;BA.debugLine="Return b";
Debug.ShouldStop(8388608);
if (true) return _b;
 BA.debugLineNum = 153;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
return null;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public anywheresoftware.b4a.objects.streams.File.InputStreamWrapper  _getinputstream() throws Exception{
try {
		Debug.PushSubsStack("GetInputStream (mnchttpjob) ","mnchttpjob",10,ba,this,143);
anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _in = null;
 BA.debugLineNum = 143;BA.debugLine="Public Sub GetInputStream As InputStream";
Debug.ShouldStop(16384);
 BA.debugLineNum = 144;BA.debugLine="Dim In As InputStream";
Debug.ShouldStop(32768);
_in = new anywheresoftware.b4a.objects.streams.File.InputStreamWrapper();Debug.locals.put("In", _in);
 BA.debugLineNum = 145;BA.debugLine="In = File.OpenInput(MNCUtils2Service.TempFolder, taskId)";
Debug.ShouldStop(65536);
_in = __c.File.OpenInput(_mncutils2service._tempfolder,_taskid);Debug.locals.put("In", _in);
 BA.debugLineNum = 146;BA.debugLine="Return In";
Debug.ShouldStop(131072);
if (true) return _in;
 BA.debugLineNum = 147;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return null;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public anywheresoftware.b4a.http.HttpClientWrapper.HttpUriRequestWrapper  _getrequest() throws Exception{
try {
		Debug.PushSubsStack("GetRequest (mnchttpjob) ","mnchttpjob",10,ba,this,83);
 BA.debugLineNum = 83;BA.debugLine="Public Sub GetRequest As HttpRequest";
Debug.ShouldStop(262144);
 BA.debugLineNum = 84;BA.debugLine="Return req";
Debug.ShouldStop(524288);
if (true) return _req;
 BA.debugLineNum = 85;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return null;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _getstring() throws Exception{
try {
		Debug.PushSubsStack("GetString (mnchttpjob) ","mnchttpjob",10,ba,this,99);
 BA.debugLineNum = 99;BA.debugLine="Public Sub GetString As String";
Debug.ShouldStop(4);
 BA.debugLineNum = 100;BA.debugLine="Return GetString2(\"UTF8\")";
Debug.ShouldStop(8);
if (true) return _getstring2("UTF8");
 BA.debugLineNum = 101;BA.debugLine="End Sub";
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
public String  _getstring2(String _encoding) throws Exception{
try {
		Debug.PushSubsStack("GetString2 (mnchttpjob) ","mnchttpjob",10,ba,this,104);
anywheresoftware.b4a.objects.streams.File.TextReaderWrapper _tr = null;
String _res = "";
Debug.locals.put("Encoding", _encoding);
 BA.debugLineNum = 104;BA.debugLine="Public Sub GetString2(Encoding As String) As String";
Debug.ShouldStop(128);
 BA.debugLineNum = 105;BA.debugLine="Dim tr As TextReader";
Debug.ShouldStop(256);
_tr = new anywheresoftware.b4a.objects.streams.File.TextReaderWrapper();Debug.locals.put("tr", _tr);
 BA.debugLineNum = 107;BA.debugLine="Try";
Debug.ShouldStop(1024);
try { BA.debugLineNum = 108;BA.debugLine="tr.Initialize2(File.OpenInput(MNCUtils2Service.TempFolder, taskId), Encoding)";
Debug.ShouldStop(2048);
_tr.Initialize2((java.io.InputStream)(__c.File.OpenInput(_mncutils2service._tempfolder,_taskid).getObject()),_encoding);
 } 
       catch (Exception e79) {
			ba.setLastException(e79); BA.debugLineNum = 110;BA.debugLine="Return Null";
Debug.ShouldStop(8192);
if (true) return BA.ObjectToString(__c.Null);
 };
 BA.debugLineNum = 113;BA.debugLine="Dim res As String";
Debug.ShouldStop(65536);
_res = "";Debug.locals.put("res", _res);
 BA.debugLineNum = 114;BA.debugLine="res = tr.ReadAll";
Debug.ShouldStop(131072);
_res = _tr.ReadAll();Debug.locals.put("res", _res);
 BA.debugLineNum = 115;BA.debugLine="tr.Close";
Debug.ShouldStop(262144);
_tr.Close();
 BA.debugLineNum = 116;BA.debugLine="Return res";
Debug.ShouldStop(524288);
if (true) return _res;
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
public boolean  _getstring3(String _namadir,String _namafile) throws Exception{
try {
		Debug.PushSubsStack("GetString3 (mnchttpjob) ","mnchttpjob",10,ba,this,119);
anywheresoftware.b4a.objects.streams.File.TextReaderWrapper _tr = null;
String _res = "";
Debug.locals.put("NamaDir", _namadir);
Debug.locals.put("Namafile", _namafile);
 BA.debugLineNum = 119;BA.debugLine="Public Sub GetString3(NamaDir As String, Namafile As String) As Boolean";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 120;BA.debugLine="Dim tr As TextReader";
Debug.ShouldStop(8388608);
_tr = new anywheresoftware.b4a.objects.streams.File.TextReaderWrapper();Debug.locals.put("tr", _tr);
 BA.debugLineNum = 122;BA.debugLine="Try";
Debug.ShouldStop(33554432);
try { BA.debugLineNum = 123;BA.debugLine="tr.Initialize(File.OpenInput(MNCUtils2Service.TempFolder, taskId))";
Debug.ShouldStop(67108864);
_tr.Initialize((java.io.InputStream)(__c.File.OpenInput(_mncutils2service._tempfolder,_taskid).getObject()));
 } 
       catch (Exception e91) {
			ba.setLastException(e91); BA.debugLineNum = 125;BA.debugLine="Return False";
Debug.ShouldStop(268435456);
if (true) return __c.False;
 };
 BA.debugLineNum = 128;BA.debugLine="Dim res As String";
Debug.ShouldStop(-2147483648);
_res = "";Debug.locals.put("res", _res);
 BA.debugLineNum = 129;BA.debugLine="res = tr.ReadAll";
Debug.ShouldStop(1);
_res = _tr.ReadAll();Debug.locals.put("res", _res);
 BA.debugLineNum = 130;BA.debugLine="tr.Close";
Debug.ShouldStop(2);
_tr.Close();
 BA.debugLineNum = 131;BA.debugLine="File.WriteString(NamaDir, Namafile, res)";
Debug.ShouldStop(4);
__c.File.WriteString(_namadir,_namafile,_res);
 BA.debugLineNum = 132;BA.debugLine="Return True";
Debug.ShouldStop(8);
if (true) return __c.True;
 BA.debugLineNum = 133;BA.debugLine="End Sub";
Debug.ShouldStop(16);
return false;
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public String  _initialize(anywheresoftware.b4a.BA _ba,String _name,Object _targetmodule) throws Exception{
innerInitialize(_ba);
try {
		Debug.PushSubsStack("Initialize (mnchttpjob) ","mnchttpjob",10,ba,this,18);
Debug.locals.put("ba", _ba);
Debug.locals.put("Name", _name);
Debug.locals.put("TargetModule", _targetmodule);
 BA.debugLineNum = 18;BA.debugLine="Public Sub Initialize (Name As String, TargetModule As Object)";
Debug.ShouldStop(131072);
 BA.debugLineNum = 19;BA.debugLine="JobName = Name";
Debug.ShouldStop(262144);
_jobname = _name;
 BA.debugLineNum = 20;BA.debugLine="target = TargetModule";
Debug.ShouldStop(524288);
_target = _targetmodule;
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
public String  _postbytes(String _link,byte[] _data) throws Exception{
try {
		Debug.PushSubsStack("PostBytes (mnchttpjob) ","mnchttpjob",10,ba,this,28);
Debug.locals.put("Link", _link);
Debug.locals.put("Data", _data);
 BA.debugLineNum = 28;BA.debugLine="Public Sub PostBytes(Link As String, Data() As Byte)";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 29;BA.debugLine="req.InitializePost2(Link, Data)";
Debug.ShouldStop(268435456);
_req.InitializePost2(_link,_data);
 BA.debugLineNum = 30;BA.debugLine="CallSubDelayed2(MNCUtils2Service, \"SubmitJob\", Me)";
Debug.ShouldStop(536870912);
__c.CallSubDelayed2(getActivityBA(),(Object)(_mncutils2service.getObject()),"SubmitJob",this);
 BA.debugLineNum = 31;BA.debugLine="End Sub";
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
public String  _postfile(String _link,String _dir,String _filename) throws Exception{
try {
		Debug.PushSubsStack("PostFile (mnchttpjob) ","mnchttpjob",10,ba,this,35);
int _length = 0;
anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _in = null;
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;
Debug.locals.put("Link", _link);
Debug.locals.put("Dir", _dir);
Debug.locals.put("FileName", _filename);
 BA.debugLineNum = 35;BA.debugLine="Public Sub PostFile(Link As String, Dir As String, FileName As String)";
Debug.ShouldStop(4);
 BA.debugLineNum = 36;BA.debugLine="Dim length As Int";
Debug.ShouldStop(8);
_length = 0;Debug.locals.put("length", _length);
 BA.debugLineNum = 37;BA.debugLine="If Dir = File.DirAssets Then";
Debug.ShouldStop(16);
if ((_dir).equals(__c.File.getDirAssets())) { 
 BA.debugLineNum = 38;BA.debugLine="Log(\"Cannot send files from the assets folder.\")";
Debug.ShouldStop(32);
__c.Log("Cannot send files from the assets folder.");
 BA.debugLineNum = 39;BA.debugLine="Return";
Debug.ShouldStop(64);
if (true) return "";
 };
 BA.debugLineNum = 41;BA.debugLine="length = File.Size(Dir, FileName)";
Debug.ShouldStop(256);
_length = (int) (__c.File.Size(_dir,_filename));Debug.locals.put("length", _length);
 BA.debugLineNum = 42;BA.debugLine="Dim In As InputStream";
Debug.ShouldStop(512);
_in = new anywheresoftware.b4a.objects.streams.File.InputStreamWrapper();Debug.locals.put("In", _in);
 BA.debugLineNum = 43;BA.debugLine="In = File.OpenInput(Dir, FileName)";
Debug.ShouldStop(1024);
_in = __c.File.OpenInput(_dir,_filename);Debug.locals.put("In", _in);
 BA.debugLineNum = 44;BA.debugLine="If length < 1000000 Then '1mb";
Debug.ShouldStop(2048);
if (_length<1000000) { 
 BA.debugLineNum = 47;BA.debugLine="Dim out As OutputStream";
Debug.ShouldStop(16384);
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();Debug.locals.put("out", _out);
 BA.debugLineNum = 48;BA.debugLine="out.InitializeToBytesArray(length)";
Debug.ShouldStop(32768);
_out.InitializeToBytesArray(_length);
 BA.debugLineNum = 49;BA.debugLine="File.Copy2(In, out)";
Debug.ShouldStop(65536);
__c.File.Copy2((java.io.InputStream)(_in.getObject()),(java.io.OutputStream)(_out.getObject()));
 BA.debugLineNum = 50;BA.debugLine="PostBytes(Link, out.ToBytesArray)";
Debug.ShouldStop(131072);
_postbytes(_link,_out.ToBytesArray());
 }else {
 BA.debugLineNum = 52;BA.debugLine="req.InitializePost(Link, In, length)";
Debug.ShouldStop(524288);
_req.InitializePost(_link,(java.io.InputStream)(_in.getObject()),_length);
 BA.debugLineNum = 53;BA.debugLine="CallSubDelayed2(MNCUtils2Service, \"SubmitJob\", Me)";
Debug.ShouldStop(1048576);
__c.CallSubDelayed2(getActivityBA(),(Object)(_mncutils2service.getObject()),"SubmitJob",this);
 };
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
public String  _poststring(String _link,String _text) throws Exception{
try {
		Debug.PushSubsStack("PostString (mnchttpjob) ","mnchttpjob",10,ba,this,23);
Debug.locals.put("Link", _link);
Debug.locals.put("Text", _text);
 BA.debugLineNum = 23;BA.debugLine="Public Sub PostString(Link As String, Text As String)";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 24;BA.debugLine="PostBytes(Link, Text.GetBytes(\"UTF8\"))";
Debug.ShouldStop(8388608);
_postbytes(_link,_text.getBytes("UTF8"));
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
public String  _release() throws Exception{
try {
		Debug.PushSubsStack("Release (mnchttpjob) ","mnchttpjob",10,ba,this,94);
 BA.debugLineNum = 94;BA.debugLine="Public Sub Release";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 95;BA.debugLine="File.Delete(MNCUtils2Service.TempFolder, taskId)";
Debug.ShouldStop(1073741824);
__c.File.Delete(_mncutils2service._tempfolder,_taskid);
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
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
