package newmoviebay.mncplaymedia.com.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_mainpage{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
String _x="";
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
_x = BA.NumberToString(0-(80d / 100 * width));
views.get("putama").vw.setLeft((int)(Double.parseDouble(_x)));
views.get("putama").vw.setWidth((int)((100d / 100 * width) - (Double.parseDouble(_x))));
views.get("putama").vw.setTop((int)(0d));
views.get("putama").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("pmenu").vw.setLeft((int)(0d));
views.get("pmenu").vw.setWidth((int)(0d-Double.parseDouble(_x) - (0d)));
views.get("pmenu").vw.setTop((int)(0d));
views.get("pmenu").vw.setHeight((int)((views.get("putama").vw.getHeight()) - (0d)));
views.get("pmainatas").vw.setLeft((int)(0d-Double.parseDouble(_x)));
views.get("pmainatas").vw.setWidth((int)((100d / 100 * width)-Double.parseDouble(_x) - (0d-Double.parseDouble(_x))));
views.get("pmainatas").vw.setTop((int)(0d));
views.get("pisitab").vw.setLeft((int)((views.get("pmainatas").vw.getLeft())));
views.get("pisitab").vw.setWidth((int)((views.get("pmainatas").vw.getLeft() + views.get("pmainatas").vw.getWidth()) - ((views.get("pmainatas").vw.getLeft()))));
views.get("pisitab").vw.setTop((int)((views.get("pmainatas").vw.getTop() + views.get("pmainatas").vw.getHeight())));
views.get("pmenumain").vw.setLeft((int)((views.get("pmainatas").vw.getLeft())));
views.get("pmenumain").vw.setWidth((int)((views.get("pmainatas").vw.getLeft() + views.get("pmainatas").vw.getWidth()) - ((views.get("pmainatas").vw.getLeft()))));
views.get("pmenumain").vw.setTop((int)((views.get("pisitab").vw.getTop() + views.get("pisitab").vw.getHeight())));
views.get("pmenumain").vw.setHeight((int)((views.get("putama").vw.getTop() + views.get("putama").vw.getHeight()) - ((views.get("pisitab").vw.getTop() + views.get("pisitab").vw.getHeight()))));
views.get("plogo").vw.setLeft((int)((views.get("ivmenu").vw.getLeft() + views.get("ivmenu").vw.getWidth())+(15d / 100 * width)));
views.get("plogo").vw.setWidth((int)((views.get("ivsearch").vw.getLeft())+(15d / 100 * width) - ((views.get("ivmenu").vw.getLeft() + views.get("ivmenu").vw.getWidth())+(15d / 100 * width))));
views.get("plogo").vw.setTop((int)((2d / 100 * height)));
views.get("ivmenu").vw.setTop((int)(((views.get("pmainatas").vw.getHeight())-(views.get("ivmenu").vw.getHeight()))/2d));
views.get("ivmenu").vw.setLeft((int)((views.get("ivmenu").vw.getWidth())/2d));
views.get("ivsearch").vw.setTop((int)(((views.get("pmainatas").vw.getHeight())-(views.get("ivmenu").vw.getHeight()))/2d));
views.get("ivsearch").vw.setLeft((int)((100d / 100 * width)-((views.get("ivsearch").vw.getWidth())/1.9d) - (views.get("ivsearch").vw.getWidth())));
views.get("ivmainlogo").vw.setTop((int)((4d * scale)));
views.get("ivmainlogo").vw.setLeft((int)((22d / 100 * width) - (views.get("ivmainlogo").vw.getWidth() / 2)));
views.get("ivmainlogo").vw.setWidth((int)((40d / 100 * width)));
views.get("lblnamafilm").vw.setTop((int)((views.get("ivmainlogo").vw.getTop() + views.get("ivmainlogo").vw.getHeight())-(0.5d / 100 * height)));
views.get("lblnamafilm").vw.setLeft((int)((24.5d / 100 * width) - (views.get("lblnamafilm").vw.getWidth() / 2)));
views.get("spmain").vw.setTop((int)((1d / 100 * height)));
views.get("spmain").vw.setLeft((int)((25d / 100 * width) - (views.get("spmain").vw.getWidth() / 2)));
views.get("spmain").vw.setTop((int)((views.get("ivmainlogo").vw.getTop() + views.get("ivmainlogo").vw.getHeight())-((views.get("plogo").vw.getHeight())/3d)));
views.get("spmain").vw.setHeight((int)((views.get("pmainatas").vw.getTop() + views.get("pmainatas").vw.getHeight()) - ((views.get("ivmainlogo").vw.getTop() + views.get("ivmainlogo").vw.getHeight())-((views.get("plogo").vw.getHeight())/3d))));
views.get("ivdropdown").vw.setTop((int)((views.get("ivmainlogo").vw.getTop() + views.get("ivmainlogo").vw.getHeight())));
views.get("ivdropdown").vw.setLeft((int)((views.get("lblnamafilm").vw.getLeft() + views.get("lblnamafilm").vw.getWidth())-(2d / 100 * width)));
views.get("ivuser").vw.setTop((int)((views.get("putama").vw.getHeight())-((views.get("ivuser").vw.getHeight())/5d) - (views.get("ivuser").vw.getHeight())));
views.get("lbluser").vw.setLeft((int)((views.get("ivuser").vw.getLeft() + views.get("ivuser").vw.getWidth())+(10d / 100 * width)));
views.get("lbluser").vw.setWidth((int)((views.get("pmenu").vw.getLeft() + views.get("pmenu").vw.getWidth()) - ((views.get("ivuser").vw.getLeft() + views.get("ivuser").vw.getWidth())+(10d / 100 * width))));
views.get("lbluser").vw.setHeight((int)((views.get("ivuser").vw.getHeight())));
views.get("lbluser").vw.setTop((int)((views.get("ivuser").vw.getTop())));
views.get("lvutama").vw.setLeft((int)(0d));
views.get("lvutama").vw.setWidth((int)((views.get("pmenu").vw.getWidth()) - (0d)));
views.get("lvutama").vw.setTop((int)((views.get("pmainatas").vw.getHeight())));
views.get("lvutama").vw.setHeight((int)((views.get("lbluser").vw.getTop())-(views.get("ivuser").vw.getHeight()) - ((views.get("pmainatas").vw.getHeight()))));
views.get("lvcatefilm").vw.setTop((int)((views.get("pmainatas").vw.getTop() + views.get("pmainatas").vw.getHeight())));
views.get("lvcatefilm").vw.setHeight((int)((views.get("pmainatas").vw.getTop() + views.get("pmainatas").vw.getHeight()) - ((views.get("pmainatas").vw.getTop() + views.get("pmainatas").vw.getHeight()))));
views.get("lvcatefilm").vw.setLeft((int)((views.get("pmainatas").vw.getLeft())));
views.get("lvcatefilm").vw.setWidth((int)((views.get("pmenumain").vw.getLeft() + views.get("pmenumain").vw.getWidth()) - ((views.get("pmainatas").vw.getLeft()))));

}
}