package newmoviebay.mncplaymedia.com.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_search{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("psearch").vw.setLeft((int)(0d));
views.get("psearch").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("psearch").vw.setTop((int)(0d));
views.get("psearch").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("putama").vw.setLeft((int)(0d));
views.get("putama").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("putama").vw.setTop((int)(0d));
views.get("putama").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("patas").vw.setLeft((int)(0d));
views.get("patas").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("patas").vw.setTop((int)(0d));
views.get("patas").vw.setHeight((int)((12d / 100 * height) - (0d)));
views.get("ivsearch").vw.setLeft((int)((2d / 100 * width)));
views.get("ivsearch").vw.setWidth((int)((12d / 100 * width) - ((2d / 100 * width))));
views.get("ivsearch").vw.setTop((int)(0d));
views.get("ivsearch").vw.setHeight((int)((12d / 100 * height) - (0d)));
views.get("etsearch").vw.setLeft((int)((views.get("ivsearch").vw.getLeft() + views.get("ivsearch").vw.getWidth())+(1d / 100 * width)));
views.get("etsearch").vw.setWidth((int)((85d / 100 * width) - ((views.get("ivsearch").vw.getLeft() + views.get("ivsearch").vw.getWidth())+(1d / 100 * width))));
views.get("etsearch").vw.setTop((int)((2d / 100 * height)));
views.get("etsearch").vw.setHeight((int)((11d / 100 * height) - ((2d / 100 * height))));
views.get("ivclose").vw.setLeft((int)((views.get("etsearch").vw.getLeft() + views.get("etsearch").vw.getWidth())+(1d / 100 * width)));
views.get("ivclose").vw.setWidth((int)((100d / 100 * width) - ((views.get("etsearch").vw.getLeft() + views.get("etsearch").vw.getWidth())+(1d / 100 * width))));
views.get("ivclose").vw.setTop((int)(0d));
views.get("ivclose").vw.setHeight((int)((12d / 100 * height) - (0d)));
views.get("lvsearch").vw.setLeft((int)(0d));
views.get("lvsearch").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("lvsearch").vw.setTop((int)((views.get("patas").vw.getTop() + views.get("patas").vw.getHeight())));
views.get("lvsearch").vw.setHeight((int)((100d / 100 * height) - ((views.get("patas").vw.getTop() + views.get("patas").vw.getHeight()))));

}
}