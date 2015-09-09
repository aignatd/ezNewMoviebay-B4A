package newmoviebay.mncplaymedia.com.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_pagesearch{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("pmainatas").vw.setLeft((int)(0d));
views.get("pmainatas").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("pmainatas").vw.setTop((int)(0d));
views.get("ivsearch").vw.setTop((int)(((views.get("pmainatas").vw.getHeight())-(views.get("ivsearch").vw.getHeight()))/2d));
views.get("ivsearch").vw.setLeft((int)((views.get("ivsearch").vw.getWidth())/2d));
views.get("ivclose").vw.setTop((int)(((views.get("pmainatas").vw.getHeight())-(views.get("ivclose").vw.getHeight()))/2d));
views.get("ivclose").vw.setLeft((int)((100d / 100 * width)-((views.get("ivclose").vw.getWidth())/5d) - (views.get("ivclose").vw.getWidth())));
views.get("etsearch").vw.setLeft((int)((views.get("ivsearch").vw.getLeft() + views.get("ivsearch").vw.getWidth())));
views.get("etsearch").vw.setWidth((int)((views.get("ivclose").vw.getLeft())/1.1d - ((views.get("ivsearch").vw.getLeft() + views.get("ivsearch").vw.getWidth()))));
views.get("etsearch").vw.setTop((int)(15d));
views.get("svsearch").vw.setTop((int)((views.get("pmainatas").vw.getTop() + views.get("pmainatas").vw.getHeight())));
views.get("svsearch").vw.setHeight((int)((100d / 100 * height) - ((views.get("pmainatas").vw.getTop() + views.get("pmainatas").vw.getHeight()))));
views.get("svsearch").vw.setLeft((int)(0d));
views.get("svsearch").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("plist1").vw.setLeft((int)(0d));
views.get("plist1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("plist1").vw.setTop((int)((views.get("pmainatas").vw.getTop() + views.get("pmainatas").vw.getHeight())));
views.get("plist1").vw.setHeight((int)((views.get("plist2").vw.getTop()) - ((views.get("pmainatas").vw.getTop() + views.get("pmainatas").vw.getHeight()))));
views.get("lblfilm1").vw.setTop((int)((views.get("pmainatas").vw.getTop())));
views.get("lblfilm1").vw.setHeight((int)((views.get("lbltype1").vw.getTop()) - ((views.get("pmainatas").vw.getTop()))));
views.get("lbltype1").vw.setTop((int)((views.get("lblfilm1").vw.getTop() + views.get("lblfilm1").vw.getHeight())));
views.get("lblrating").vw.setLeft((int)((80d / 100 * width)));
views.get("lblrating").vw.setWidth((int)((100d / 100 * width) - ((80d / 100 * width))));
views.get("plist2").vw.setLeft((int)(0d));
views.get("plist2").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("plist2").vw.setTop((int)((views.get("plist1").vw.getTop() + views.get("plist1").vw.getHeight())));
views.get("plist2").vw.setHeight((int)((views.get("plist3").vw.getTop()) - ((views.get("plist1").vw.getTop() + views.get("plist1").vw.getHeight()))));
views.get("lblfilm2").vw.setTop((int)((views.get("pmainatas").vw.getTop())));
views.get("lblfilm2").vw.setHeight((int)((views.get("lbltype2").vw.getTop()) - ((views.get("pmainatas").vw.getTop()))));
views.get("lbltype2").vw.setTop((int)((views.get("lblfilm2").vw.getTop() + views.get("lblfilm2").vw.getHeight())));
views.get("lblrating2").vw.setLeft((int)((80d / 100 * width)));
views.get("lblrating2").vw.setWidth((int)((100d / 100 * width) - ((80d / 100 * width))));
views.get("plist3").vw.setLeft((int)(0d));
views.get("plist3").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("lblfilm3").vw.setTop((int)((views.get("pmainatas").vw.getTop())));
views.get("lblfilm3").vw.setHeight((int)((views.get("lbltype3").vw.getTop()) - ((views.get("pmainatas").vw.getTop()))));
views.get("lbltype3").vw.setTop((int)((views.get("lblfilm3").vw.getTop() + views.get("lblfilm3").vw.getHeight())));
views.get("lblrating3").vw.setLeft((int)((80d / 100 * width)));
views.get("lblrating3").vw.setWidth((int)((100d / 100 * width) - ((80d / 100 * width))));
views.get("plist4").vw.setLeft((int)(0d));
views.get("plist4").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("lblfilm4").vw.setTop((int)((views.get("pmainatas").vw.getTop())));
views.get("lblfilm4").vw.setHeight((int)((views.get("lbltype3").vw.getTop()) - ((views.get("pmainatas").vw.getTop()))));
views.get("lbltype4").vw.setTop((int)((views.get("lblfilm4").vw.getTop() + views.get("lblfilm4").vw.getHeight())));
views.get("lblrating4").vw.setLeft((int)((80d / 100 * width)));
views.get("lblrating4").vw.setWidth((int)((100d / 100 * width) - ((80d / 100 * width))));
views.get("plist5").vw.setLeft((int)(0d));
views.get("plist5").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("plist5").vw.setTop((int)((views.get("plist4").vw.getTop() + views.get("plist4").vw.getHeight())));
views.get("lblfilm5").vw.setTop((int)((views.get("pmainatas").vw.getTop())));
views.get("lblfilm5").vw.setHeight((int)((views.get("lbltype4").vw.getTop()) - ((views.get("pmainatas").vw.getTop()))));
views.get("lbltype5").vw.setTop((int)((views.get("lblfilm5").vw.getTop() + views.get("lblfilm5").vw.getHeight())));
views.get("lblrating5").vw.setLeft((int)((80d / 100 * width)));
views.get("lblrating5").vw.setWidth((int)((100d / 100 * width) - ((80d / 100 * width))));

}
}