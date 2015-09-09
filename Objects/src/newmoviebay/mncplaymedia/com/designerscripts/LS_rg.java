package newmoviebay.mncplaymedia.com.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_rg{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("prg").vw.setLeft((int)(0d));
views.get("prg").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("prg").vw.setTop((int)(0d));
views.get("prg").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("prgatas").vw.setLeft((int)(0d));
views.get("prgatas").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("prgatas").vw.setTop((int)(0d));
views.get("ivbackrg").vw.setTop((int)(((views.get("prgatas").vw.getHeight())-(views.get("ivbackrg").vw.getHeight()))/2d));
views.get("ivbackrg").vw.setLeft((int)((views.get("ivbackrg").vw.getWidth())));
views.get("lblrg").vw.setLeft((int)((views.get("ivbackrg").vw.getLeft() + views.get("ivbackrg").vw.getWidth())));
views.get("lblrg").vw.setWidth((int)((90d / 100 * width) - ((views.get("ivbackrg").vw.getLeft() + views.get("ivbackrg").vw.getWidth()))));
views.get("lblrg").vw.setTop((int)((1d / 100 * height)));
views.get("lblrg").vw.setHeight((int)((6d / 100 * height) - ((1d / 100 * height))));
views.get("ivmov").vw.setLeft((int)((25d / 100 * width)));
views.get("ivmov").vw.setWidth((int)((76d / 100 * width) - ((25d / 100 * width))));
views.get("ivmov").vw.setTop((int)((9d / 100 * height)));
views.get("ivmov").vw.setHeight((int)((17d / 100 * height) - ((9d / 100 * height))));
views.get("ivreg").vw.setLeft((int)((10d / 100 * width)));
views.get("ivreg").vw.setWidth((int)((88d / 100 * width) - ((10d / 100 * width))));
views.get("ivreg").vw.setTop((int)((1d / 100 * height)));
views.get("ivreg").vw.setHeight((int)((views.get("etfirst").vw.getTop()) - ((1d / 100 * height))));
views.get("pid").vw.setLeft((int)(0d));
views.get("pid").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("pid").vw.setTop((int)((views.get("ivmov").vw.getTop() + views.get("ivmov").vw.getHeight())));
views.get("pid").vw.setHeight((int)((100d / 100 * height) - ((views.get("ivmov").vw.getTop() + views.get("ivmov").vw.getHeight()))));
views.get("etfirst").vw.setLeft((int)((views.get("ivreg").vw.getLeft())));
views.get("etfirst").vw.setWidth((int)((views.get("etlast").vw.getLeft())-(1d / 100 * width) - ((views.get("ivreg").vw.getLeft()))));
views.get("etfirst").vw.setTop((int)((views.get("ivreg").vw.getTop() + views.get("ivreg").vw.getHeight())+(1d / 100 * height)));
views.get("etfirst").vw.setHeight((int)((views.get("etmail").vw.getTop())+(1d / 100 * height) - ((views.get("ivreg").vw.getTop() + views.get("ivreg").vw.getHeight())+(1d / 100 * height))));
views.get("etlast").vw.setLeft((int)((views.get("etfirst").vw.getLeft() + views.get("etfirst").vw.getWidth())+(2d / 100 * width)));
views.get("etlast").vw.setWidth((int)((views.get("ivreg").vw.getLeft() + views.get("ivreg").vw.getWidth())+(2d / 100 * width) - ((views.get("etfirst").vw.getLeft() + views.get("etfirst").vw.getWidth())+(2d / 100 * width))));
views.get("etlast").vw.setTop((int)((views.get("ivreg").vw.getTop() + views.get("ivreg").vw.getHeight())+(1d / 100 * height)));
views.get("etlast").vw.setHeight((int)((views.get("etmail").vw.getTop())+(1d / 100 * height) - ((views.get("ivreg").vw.getTop() + views.get("ivreg").vw.getHeight())+(1d / 100 * height))));
views.get("etmail").vw.setLeft((int)((views.get("etfirst").vw.getLeft())));
views.get("etmail").vw.setWidth((int)((views.get("etlast").vw.getLeft() + views.get("etlast").vw.getWidth()) - ((views.get("etfirst").vw.getLeft()))));
views.get("etmail").vw.setTop((int)((views.get("etfirst").vw.getTop() + views.get("etfirst").vw.getHeight())+(1d / 100 * height)));
views.get("etmail").vw.setHeight((int)((views.get("etphone").vw.getTop())+(1d / 100 * height) - ((views.get("etfirst").vw.getTop() + views.get("etfirst").vw.getHeight())+(1d / 100 * height))));
views.get("etphone").vw.setLeft((int)((views.get("etmail").vw.getLeft())));
views.get("etphone").vw.setWidth((int)((views.get("etmail").vw.getLeft() + views.get("etmail").vw.getWidth()) - ((views.get("etmail").vw.getLeft()))));
views.get("etphone").vw.setTop((int)((views.get("etmail").vw.getTop() + views.get("etmail").vw.getHeight())+(1d / 100 * height)));
views.get("etphone").vw.setHeight((int)((views.get("spgender").vw.getTop())+(1d / 100 * height) - ((views.get("etmail").vw.getTop() + views.get("etmail").vw.getHeight())+(1d / 100 * height))));
views.get("spgender").vw.setLeft((int)((views.get("etphone").vw.getLeft())));
views.get("spgender").vw.setWidth((int)((50d / 100 * width) - ((views.get("etphone").vw.getLeft()))));
views.get("spgender").vw.setTop((int)((views.get("etphone").vw.getTop() + views.get("etphone").vw.getHeight())+(1d / 100 * height)));
views.get("spgender").vw.setHeight((int)((views.get("panel1").vw.getTop())+(1d / 100 * height) - ((views.get("etphone").vw.getTop() + views.get("etphone").vw.getHeight())+(1d / 100 * height))));
views.get("cvbirth").vw.setLeft((int)((views.get("spgender").vw.getLeft() + views.get("spgender").vw.getWidth())+(1d / 100 * width)));
views.get("cvbirth").vw.setWidth((int)((views.get("etphone").vw.getLeft() + views.get("etphone").vw.getWidth()) - ((views.get("spgender").vw.getLeft() + views.get("spgender").vw.getWidth())+(1d / 100 * width))));
views.get("cvbirth").vw.setTop((int)((views.get("etphone").vw.getTop() + views.get("etphone").vw.getHeight())+(1d / 100 * height)));
views.get("cvbirth").vw.setHeight((int)((views.get("panel1").vw.getTop())+(1d / 100 * height) - ((views.get("etphone").vw.getTop() + views.get("etphone").vw.getHeight())+(1d / 100 * height))));
views.get("panel1").vw.setLeft((int)((views.get("spgender").vw.getLeft())));
views.get("panel1").vw.setWidth((int)((98d / 100 * width) - ((views.get("spgender").vw.getLeft()))));
views.get("panel1").vw.setTop((int)((views.get("spgender").vw.getTop() + views.get("spgender").vw.getHeight())+(1d / 100 * height)));
views.get("panel1").vw.setHeight((int)((views.get("etpass").vw.getTop())+(1d / 100 * height) - ((views.get("spgender").vw.getTop() + views.get("spgender").vw.getHeight())+(1d / 100 * height))));
views.get("etpass").vw.setLeft((int)((views.get("panel1").vw.getLeft())));
views.get("etpass").vw.setWidth((int)((views.get("cvbirth").vw.getLeft() + views.get("cvbirth").vw.getWidth()) - ((views.get("panel1").vw.getLeft()))));
views.get("etpass").vw.setTop((int)((views.get("panel1").vw.getTop() + views.get("panel1").vw.getHeight())+(1d / 100 * height)));
views.get("etpass").vw.setHeight((int)((views.get("etconfirm").vw.getTop())+(1d / 100 * height) - ((views.get("panel1").vw.getTop() + views.get("panel1").vw.getHeight())+(1d / 100 * height))));
views.get("etconfirm").vw.setLeft((int)((views.get("etpass").vw.getLeft())));
views.get("etconfirm").vw.setWidth((int)((views.get("etpass").vw.getLeft() + views.get("etpass").vw.getWidth()) - ((views.get("etpass").vw.getLeft()))));
views.get("etconfirm").vw.setTop((int)((views.get("etpass").vw.getTop() + views.get("etpass").vw.getHeight())+(1d / 100 * height)));
views.get("etconfirm").vw.setHeight((int)((views.get("lblck").vw.getTop())+(1d / 100 * height) - ((views.get("etpass").vw.getTop() + views.get("etpass").vw.getHeight())+(1d / 100 * height))));
views.get("ck1").vw.setLeft((int)((views.get("etconfirm").vw.getLeft())));
views.get("ck1").vw.setWidth((int)((views.get("lblck").vw.getLeft())-(1d / 100 * width) - ((views.get("etconfirm").vw.getLeft()))));
views.get("ck1").vw.setTop((int)((views.get("etconfirm").vw.getTop() + views.get("etconfirm").vw.getHeight())+(1d / 100 * height)));
views.get("ck1").vw.setHeight((int)((views.get("breg").vw.getTop())+(1d / 100 * height) - ((views.get("etconfirm").vw.getTop() + views.get("etconfirm").vw.getHeight())+(1d / 100 * height))));
views.get("lblck").vw.setLeft((int)((views.get("ck1").vw.getLeft() + views.get("ck1").vw.getWidth())));
views.get("lblck").vw.setWidth((int)((views.get("etconfirm").vw.getLeft() + views.get("etconfirm").vw.getWidth())-(10d / 100 * width) - ((views.get("ck1").vw.getLeft() + views.get("ck1").vw.getWidth()))));
views.get("lblck").vw.setTop((int)((views.get("etconfirm").vw.getTop() + views.get("etconfirm").vw.getHeight())+(1d / 100 * height)));
views.get("lblck").vw.setHeight((int)((views.get("breg").vw.getTop())+(1d / 100 * height) - ((views.get("etconfirm").vw.getTop() + views.get("etconfirm").vw.getHeight())+(1d / 100 * height))));
views.get("breg").vw.setLeft((int)((views.get("etconfirm").vw.getLeft())));
views.get("breg").vw.setWidth((int)((views.get("etconfirm").vw.getLeft() + views.get("etconfirm").vw.getWidth()) - ((views.get("etconfirm").vw.getLeft()))));
views.get("breg").vw.setTop((int)((views.get("lblck").vw.getTop() + views.get("lblck").vw.getHeight())+(6d / 100 * height)));
views.get("breg").vw.setHeight((int)((views.get("breg").vw.getTop() + views.get("breg").vw.getHeight())+(6d / 100 * height) - ((views.get("lblck").vw.getTop() + views.get("lblck").vw.getHeight())+(6d / 100 * height))));

}
}