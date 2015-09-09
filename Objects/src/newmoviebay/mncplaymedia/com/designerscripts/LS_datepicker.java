package newmoviebay.mncplaymedia.com.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_datepicker{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
String _w="";
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("base").vw.setHeight((int)(Math.min((100d / 100 * height),(views.get("base").vw.getHeight()))));
views.get("toppanel").vw.setLeft((int)(0d));
views.get("toppanel").vw.setWidth((int)((views.get("base").vw.getWidth()) - (0d)));
views.get("months").vw.setTop((int)((views.get("toppanel").vw.getTop() + views.get("toppanel").vw.getHeight()/2) - (views.get("months").vw.getHeight() / 2)));
views.get("years").vw.setTop((int)((views.get("months").vw.getTop() + views.get("months").vw.getHeight()/2) - (views.get("years").vw.getHeight() / 2)));
views.get("lblselectedday").vw.setLeft((int)((views.get("toppanel").vw.getLeft() + views.get("toppanel").vw.getWidth()/2) - (views.get("lblselectedday").vw.getWidth() / 2)));
views.get("months").vw.setLeft((int)((2d * scale)));
views.get("months").vw.setWidth((int)((views.get("lblselectedday").vw.getLeft()) - ((2d * scale))));
views.get("years").vw.setLeft((int)((views.get("lblselectedday").vw.getLeft() + views.get("lblselectedday").vw.getWidth())));
views.get("years").vw.setWidth((int)((views.get("toppanel").vw.getWidth())-(2d * scale) - ((views.get("lblselectedday").vw.getLeft() + views.get("lblselectedday").vw.getWidth()))));
_w = BA.NumberToString((views.get("base").vw.getWidth())/7d);
views.get("label1").vw.setLeft((int)(0d));
views.get("label1").vw.setWidth((int)(Double.parseDouble(_w) - (0d)));
views.get("label2").vw.setLeft((int)(Double.parseDouble(_w)));
views.get("label2").vw.setWidth((int)(2d*Double.parseDouble(_w) - (Double.parseDouble(_w))));
views.get("label3").vw.setLeft((int)(2d*Double.parseDouble(_w)));
views.get("label3").vw.setWidth((int)(3d*Double.parseDouble(_w) - (2d*Double.parseDouble(_w))));
views.get("label4").vw.setLeft((int)(3d*Double.parseDouble(_w)));
views.get("label4").vw.setWidth((int)(4d*Double.parseDouble(_w) - (3d*Double.parseDouble(_w))));
views.get("label5").vw.setLeft((int)(4d*Double.parseDouble(_w)));
views.get("label5").vw.setWidth((int)(5d*Double.parseDouble(_w) - (4d*Double.parseDouble(_w))));
views.get("label6").vw.setLeft((int)(5d*Double.parseDouble(_w)));
views.get("label6").vw.setWidth((int)(6d*Double.parseDouble(_w) - (5d*Double.parseDouble(_w))));
views.get("label7").vw.setLeft((int)(6d*Double.parseDouble(_w)));
views.get("label7").vw.setWidth((int)(7d*Double.parseDouble(_w) - (6d*Double.parseDouble(_w))));
views.get("btntoday").vw.setTop((int)((views.get("base").vw.getHeight())-(2d * scale) - (views.get("btntoday").vw.getHeight())));
views.get("btncancel").vw.setTop((int)((views.get("base").vw.getHeight())-(2d * scale) - (views.get("btncancel").vw.getHeight())));
views.get("btntoday").vw.setLeft((int)((views.get("base").vw.getWidth())/4d - (views.get("btntoday").vw.getWidth() / 2)));
views.get("btncancel").vw.setLeft((int)(3d*(views.get("base").vw.getWidth())/4d - (views.get("btncancel").vw.getWidth() / 2)));
views.get("dayspanel").vw.setLeft((int)(0d));
views.get("dayspanel").vw.setWidth((int)((views.get("base").vw.getWidth()) - (0d)));
views.get("dayspanel").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight())));
views.get("dayspanel").vw.setHeight((int)((views.get("btntoday").vw.getTop()) - ((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight()))));
views.get("dayspanelbackground").vw.setLeft((int)((views.get("dayspanel").vw.getLeft())));
views.get("dayspanelbackground").vw.setWidth((int)((views.get("dayspanel").vw.getLeft() + views.get("dayspanel").vw.getWidth()) - ((views.get("dayspanel").vw.getLeft()))));
views.get("dayspanelbackground").vw.setTop((int)((views.get("dayspanel").vw.getTop())));
views.get("dayspanelbackground").vw.setHeight((int)((views.get("dayspanel").vw.getTop() + views.get("dayspanel").vw.getHeight()) - ((views.get("dayspanel").vw.getTop()))));
views.get("base").vw.setLeft((int)((50d / 100 * width) - (views.get("base").vw.getWidth() / 2)));
views.get("base").vw.setTop((int)((50d / 100 * height) - (views.get("base").vw.getHeight() / 2)));

}
}