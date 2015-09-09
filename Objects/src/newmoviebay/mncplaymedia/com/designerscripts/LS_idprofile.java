package newmoviebay.mncplaymedia.com.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_idprofile{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("psemua").vw.setLeft((int)(0d));
views.get("psemua").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("psemua").vw.setTop((int)(0d));
views.get("psemua").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("putama").vw.setLeft((int)(0d));
views.get("putama").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("putama").vw.setTop((int)(0d));
views.get("putama").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("patas").vw.setLeft((int)(0d));
views.get("patas").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("patas").vw.setTop((int)(0d));
views.get("patas").vw.setHeight((int)((10d / 100 * height) - (0d)));
views.get("lvmenu").vw.setLeft((int)(0d));
views.get("lvmenu").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("lvmenu").vw.setTop((int)((views.get("patas").vw.getTop() + views.get("patas").vw.getHeight())));
views.get("lvmenu").vw.setHeight((int)((100d / 100 * height) - ((views.get("patas").vw.getTop() + views.get("patas").vw.getHeight()))));
views.get("lblatas").vw.setLeft((int)((50d / 100 * width) - (views.get("lblatas").vw.getWidth() / 2)));

}
}