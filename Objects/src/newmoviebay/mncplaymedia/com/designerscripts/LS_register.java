package newmoviebay.mncplaymedia.com.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_register{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("pall").vw.setLeft((int)(0d));
views.get("pall").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("pall").vw.setTop((int)(0d));
views.get("pall").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("patasreg").vw.setLeft((int)(0d));
views.get("patasreg").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("patasreg").vw.setTop((int)(0d));
views.get("ivback").vw.setTop((int)(((views.get("patasreg").vw.getHeight())-(views.get("ivback").vw.getHeight()))/2d));
views.get("ivback").vw.setLeft((int)((views.get("ivback").vw.getWidth())/2d));
views.get("lblreg").vw.setLeft((int)((views.get("ivback").vw.getLeft() + views.get("ivback").vw.getWidth())));
views.get("lblreg").vw.setWidth((int)((views.get("pall").vw.getLeft() + views.get("pall").vw.getWidth())-(10d / 100 * width) - ((views.get("ivback").vw.getLeft() + views.get("ivback").vw.getWidth()))));
views.get("pmov").vw.setLeft((int)(0d));
views.get("pmov").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("pmov").vw.setTop((int)((views.get("patasreg").vw.getTop() + views.get("patasreg").vw.getHeight())));
views.get("pmov").vw.setHeight((int)((24d / 100 * height) - ((views.get("patasreg").vw.getTop() + views.get("patasreg").vw.getHeight()))));
views.get("ivmov").vw.setLeft((int)((views.get("pmov").vw.getLeft())+(26d / 100 * width)));
views.get("ivmov").vw.setWidth((int)((views.get("pmov").vw.getLeft() + views.get("pmov").vw.getWidth())-(19d / 100 * width) - ((views.get("pmov").vw.getLeft())+(26d / 100 * width))));
views.get("ivmov").vw.setLeft((int)((51d / 100 * width) - (views.get("ivmov").vw.getWidth() / 2)));
views.get("ivmov").vw.setHeight((int)((8d / 100 * height)));
views.get("preg").vw.setLeft((int)(0d));
views.get("preg").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("preg").vw.setTop((int)((views.get("pmov").vw.getTop() + views.get("pmov").vw.getHeight())));
views.get("preg").vw.setHeight((int)((100d / 100 * height) - ((views.get("pmov").vw.getTop() + views.get("pmov").vw.getHeight()))));
views.get("ptext").vw.setLeft((int)(0d));
views.get("ptext").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("ptext").vw.setTop((int)((views.get("pmov").vw.getTop() + views.get("pmov").vw.getHeight())));
views.get("ptext").vw.setHeight((int)((4d / 100 * height)));
views.get("ivtext").vw.setLeft((int)((10d / 100 * width)));
views.get("ivtext").vw.setWidth((int)((90d / 100 * width) - ((10d / 100 * width))));
views.get("ivtext").vw.setTop((int)(0d));
views.get("plist").vw.setLeft((int)(0d));
views.get("plist").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("plist").vw.setTop((int)((views.get("ptext").vw.getTop() + views.get("ptext").vw.getHeight())));
views.get("plist").vw.setHeight((int)((100d / 100 * height) - ((views.get("ptext").vw.getTop() + views.get("ptext").vw.getHeight()))));
views.get("etfirst").vw.setLeft((int)((10d / 100 * width)));
views.get("etfirst").vw.setWidth((int)((50d / 100 * width) - ((10d / 100 * width))));
views.get("etfirst").vw.setTop((int)(2d));
views.get("etlast").vw.setLeft((int)((views.get("etfirst").vw.getLeft() + views.get("etfirst").vw.getWidth())+(1d / 100 * width)));
views.get("etlast").vw.setWidth((int)((94d / 100 * width) - ((views.get("etfirst").vw.getLeft() + views.get("etfirst").vw.getWidth())+(1d / 100 * width))));
views.get("etlast").vw.setTop((int)(2d));
views.get("etmail").vw.setLeft((int)((10d / 100 * width)));
views.get("etmail").vw.setWidth((int)((94d / 100 * width) - ((10d / 100 * width))));
views.get("etmail").vw.setTop((int)((views.get("etfirst").vw.getTop() + views.get("etfirst").vw.getHeight())+(1d / 100 * height)));
views.get("etphone").vw.setLeft((int)((10d / 100 * width)));
views.get("etphone").vw.setWidth((int)((94d / 100 * width) - ((10d / 100 * width))));
views.get("etphone").vw.setTop((int)((views.get("etmail").vw.getTop() + views.get("etmail").vw.getHeight())+(1d / 100 * height)));
views.get("etgender").vw.setLeft((int)((10d / 100 * width)));
views.get("etgender").vw.setWidth((int)((50d / 100 * width) - ((10d / 100 * width))));
views.get("etgender").vw.setTop((int)((views.get("etphone").vw.getTop() + views.get("etphone").vw.getHeight())+(1d / 100 * height)));
views.get("etbirth").vw.setLeft((int)((views.get("etgender").vw.getLeft() + views.get("etgender").vw.getWidth())+(1d / 100 * width)));
views.get("etbirth").vw.setWidth((int)((94d / 100 * width) - ((views.get("etgender").vw.getLeft() + views.get("etgender").vw.getWidth())+(1d / 100 * width))));
views.get("etbirth").vw.setTop((int)((views.get("etphone").vw.getTop() + views.get("etphone").vw.getHeight())+(1d / 100 * height)));
views.get("lblpass").vw.setLeft((int)((10d / 100 * width)));
views.get("lblpass").vw.setWidth((int)((60d / 100 * width) - ((10d / 100 * width))));
views.get("lblpass").vw.setTop((int)((views.get("etgender").vw.getTop() + views.get("etgender").vw.getHeight())+(1d / 100 * height)));
views.get("lblpass").vw.setWidth((int)((80d / 100 * width)));
views.get("etpass").vw.setLeft((int)((10d / 100 * width)));
views.get("etpass").vw.setWidth((int)((94d / 100 * width) - ((10d / 100 * width))));
views.get("etpass").vw.setTop((int)((views.get("lblpass").vw.getTop() + views.get("lblpass").vw.getHeight())+(1d / 100 * height)));
views.get("etconfirm").vw.setLeft((int)((10d / 100 * width)));
views.get("etconfirm").vw.setWidth((int)((94d / 100 * width) - ((10d / 100 * width))));
views.get("etconfirm").vw.setTop((int)((views.get("etpass").vw.getTop() + views.get("etpass").vw.getHeight())+(1d / 100 * height)));

}
}