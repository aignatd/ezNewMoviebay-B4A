package newmoviebay.mncplaymedia.com.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_comments{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="AutoScaleAll"[comments/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 4;BA.debugLine="pUtama.SetLeftAndRight(0%x, 88%x)"[comments/General script]
views.get("putama").vw.setLeft((int)((0d / 100 * width)));
views.get("putama").vw.setWidth((int)((88d / 100 * width) - ((0d / 100 * width))));
//BA.debugLineNum = 5;BA.debugLine="pUtama.SetTopAndBottom(0%y, 50%y)"[comments/General script]
views.get("putama").vw.setTop((int)((0d / 100 * height)));
views.get("putama").vw.setHeight((int)((50d / 100 * height) - ((0d / 100 * height))));
//BA.debugLineNum = 7;BA.debugLine="lblComments.SetLeftAndRight(2%x, 50%x)"[comments/General script]
views.get("lblcomments").vw.setLeft((int)((2d / 100 * width)));
views.get("lblcomments").vw.setWidth((int)((50d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 8;BA.debugLine="lblComments.SetTopAndBottom(1%y, etDesc.Top - 1%y)"[comments/General script]
views.get("lblcomments").vw.setTop((int)((1d / 100 * height)));
views.get("lblcomments").vw.setHeight((int)((views.get("etdesc").vw.getTop())-(1d / 100 * height) - ((1d / 100 * height))));
//BA.debugLineNum = 10;BA.debugLine="etDesc.SetLeftAndRight(2%x, 85%x)"[comments/General script]
views.get("etdesc").vw.setLeft((int)((2d / 100 * width)));
views.get("etdesc").vw.setWidth((int)((85d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 11;BA.debugLine="etDesc.SetTopAndBottom(lblComments.Bottom + 1%y, pButton.Top - 1%y)"[comments/General script]
views.get("etdesc").vw.setTop((int)((views.get("lblcomments").vw.getTop() + views.get("lblcomments").vw.getHeight())+(1d / 100 * height)));
views.get("etdesc").vw.setHeight((int)((views.get("pbutton").vw.getTop())-(1d / 100 * height) - ((views.get("lblcomments").vw.getTop() + views.get("lblcomments").vw.getHeight())+(1d / 100 * height))));
//BA.debugLineNum = 13;BA.debugLine="pButton.SetLeftAndRight(0,100%x)"[comments/General script]
views.get("pbutton").vw.setLeft((int)(0d));
views.get("pbutton").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 14;BA.debugLine="pButton.SetTopAndBottom(etDesc.Bottom, 55%y)"[comments/General script]
views.get("pbutton").vw.setTop((int)((views.get("etdesc").vw.getTop() + views.get("etdesc").vw.getHeight())));
views.get("pbutton").vw.setHeight((int)((55d / 100 * height) - ((views.get("etdesc").vw.getTop() + views.get("etdesc").vw.getHeight()))));
//BA.debugLineNum = 16;BA.debugLine="bShare.SetTopAndBottom(2%y, 8%y)"[comments/General script]
views.get("bshare").vw.setTop((int)((2d / 100 * height)));
views.get("bshare").vw.setHeight((int)((8d / 100 * height) - ((2d / 100 * height))));
//BA.debugLineNum = 17;BA.debugLine="bShare.SetLeftAndRight(4%x, ivCancel.Left + 2%x)"[comments/General script]
views.get("bshare").vw.setLeft((int)((4d / 100 * width)));
views.get("bshare").vw.setWidth((int)((views.get("ivcancel").vw.getLeft())+(2d / 100 * width) - ((4d / 100 * width))));
//BA.debugLineNum = 19;BA.debugLine="ivCancel.SetTopAndBottom(2%y, 8%y)"[comments/General script]
views.get("ivcancel").vw.setTop((int)((2d / 100 * height)));
views.get("ivcancel").vw.setHeight((int)((8d / 100 * height) - ((2d / 100 * height))));
//BA.debugLineNum = 20;BA.debugLine="ivCancel.SetLeftAndRight(bShare.Right + 10%x, 82%x)"[comments/General script]
views.get("ivcancel").vw.setLeft((int)((views.get("bshare").vw.getLeft() + views.get("bshare").vw.getWidth())+(10d / 100 * width)));
views.get("ivcancel").vw.setWidth((int)((82d / 100 * width) - ((views.get("bshare").vw.getLeft() + views.get("bshare").vw.getWidth())+(10d / 100 * width))));

}
}