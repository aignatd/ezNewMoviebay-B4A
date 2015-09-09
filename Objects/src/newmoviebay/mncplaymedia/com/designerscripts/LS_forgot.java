package newmoviebay.mncplaymedia.com.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_forgot{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("wvforgot").vw.setLeft((int)(0d));
views.get("wvforgot").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("wvforgot").vw.setTop((int)(0d));
views.get("wvforgot").vw.setHeight((int)((100d / 100 * height) - (0d)));

}
}