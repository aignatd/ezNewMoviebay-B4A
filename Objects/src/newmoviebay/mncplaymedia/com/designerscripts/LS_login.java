package newmoviebay.mncplaymedia.com.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_login{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("plog").vw.setLeft((int)(0d));
views.get("plog").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("plog").vw.setTop((int)(0d));
views.get("plog").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("patas").vw.setLeft((int)(0d));
views.get("patas").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("patas").vw.setTop((int)(0d));
views.get("patas").vw.setHeight((int)((40d / 100 * height) - (0d)));
views.get("ivbg").vw.setLeft((int)((25d / 100 * width)));
views.get("ivbg").vw.setWidth((int)((76d / 100 * width) - ((25d / 100 * width))));
views.get("ivbg").vw.setTop((int)((5d / 100 * height)));
views.get("ivbg").vw.setHeight((int)((14d / 100 * height) - ((5d / 100 * height))));
views.get("ivloginfb").vw.setLeft((int)((10d / 100 * width)));
views.get("ivloginfb").vw.setWidth((int)((90d / 100 * width) - ((10d / 100 * width))));
views.get("ivloginfb").vw.setTop((int)((25d / 100 * height)));
views.get("ivloginfb").vw.setHeight((int)((32d / 100 * height) - ((25d / 100 * height))));
views.get("ivor").vw.setLeft((int)((8d / 100 * width)));
views.get("ivor").vw.setWidth((int)((90d / 100 * width) - ((8d / 100 * width))));
views.get("ivor").vw.setTop((int)((35d / 100 * height)));
views.get("ivor").vw.setHeight((int)((39d / 100 * height) - ((35d / 100 * height))));
views.get("plogin").vw.setLeft((int)(0d));
views.get("plogin").vw.setWidth((int)((80d / 100 * width) - (0d)));
views.get("plogin").vw.setLeft((int)((50d / 100 * width) - (views.get("plogin").vw.getWidth() / 2)));
views.get("plogin").vw.setTop((int)((views.get("patas").vw.getTop() + views.get("patas").vw.getHeight())));
views.get("iv1").vw.setTop((int)(0d));
views.get("iv1").vw.setLeft((int)(0d));
views.get("iv2").vw.setTop((int)(0d));
views.get("iv2").vw.setLeft((int)(0d));
views.get("etemail").vw.setLeft((int)((views.get("iv1").vw.getLeft() + views.get("iv1").vw.getWidth())-(1.5d / 100 * width)));
views.get("etemail").vw.setWidth((int)((views.get("plogin").vw.getWidth()) - ((views.get("iv1").vw.getLeft() + views.get("iv1").vw.getWidth())-(1.5d / 100 * width))));
views.get("etemail").vw.setTop((int)(0d));
views.get("etemail").vw.setHeight((int)((views.get("iv1").vw.getHeight())));
views.get("iv3").vw.setTop((int)((views.get("iv1").vw.getTop() + views.get("iv1").vw.getHeight())+(1.5d / 100 * height)));
views.get("iv3").vw.setLeft((int)(0d));
views.get("iv4").vw.setTop((int)((views.get("iv1").vw.getTop() + views.get("iv1").vw.getHeight())+(1.5d / 100 * height)));
views.get("iv4").vw.setLeft((int)(0d));
views.get("etpass").vw.setLeft((int)((views.get("iv3").vw.getLeft() + views.get("iv3").vw.getWidth())-(1.5d / 100 * width)));
views.get("etpass").vw.setWidth((int)((views.get("plogin").vw.getWidth()) - ((views.get("iv3").vw.getLeft() + views.get("iv3").vw.getWidth())-(1.5d / 100 * width))));
views.get("etpass").vw.setTop((int)((views.get("etemail").vw.getTop() + views.get("etemail").vw.getHeight())+(1.5d / 100 * height)));
views.get("etpass").vw.setHeight((int)((views.get("iv3").vw.getHeight())));
views.get("bsubmit").vw.setTop((int)((views.get("etpass").vw.getTop() + views.get("etpass").vw.getHeight())+(2d / 100 * height)));
views.get("bsubmit").vw.setHeight((int)((views.get("etpass").vw.getHeight())));
views.get("bsubmit").vw.setLeft((int)(0d));
views.get("bsubmit").vw.setWidth((int)((views.get("plogin").vw.getWidth()) - (0d)));
views.get("lbllg").vw.setLeft((int)((15d / 100 * width)));
views.get("lbllg").vw.setWidth((int)((67d / 100 * width) - ((15d / 100 * width))));
views.get("lbllg").vw.setTop((int)((views.get("bsubmit").vw.getTop() + views.get("bsubmit").vw.getHeight())+(2d / 100 * height)));
views.get("breg").vw.setTop((int)((50d / 100 * height)));
views.get("breg").vw.setLeft((int)((40d / 100 * width) - (views.get("breg").vw.getWidth() / 2)));
views.get("breg").vw.setLeft((int)(0d));
views.get("breg").vw.setWidth((int)((views.get("plogin").vw.getWidth()) - (0d)));
views.get("breg").vw.setHeight((int)((views.get("bsubmit").vw.getHeight())));
views.get("plogin").vw.setHeight((int)((60d / 100 * height)));

}
}