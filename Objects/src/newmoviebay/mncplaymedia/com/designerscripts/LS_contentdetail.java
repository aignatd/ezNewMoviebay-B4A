package newmoviebay.mncplaymedia.com.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_contentdetail{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("patas").vw.setLeft((int)(0d));
views.get("patas").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("patas").vw.setTop((int)(0d));
views.get("patas").vw.setHeight((int)((views.get("ivmovies").vw.getTop()) - (0d)));
views.get("ivback").vw.setLeft((int)(0d));
views.get("ivback").vw.setTop((int)((views.get("patas").vw.getHeight())/2d - (views.get("ivback").vw.getHeight() / 2)));
views.get("ivshare").vw.setLeft((int)((views.get("patas").vw.getWidth()) - (views.get("ivshare").vw.getWidth())));
views.get("ivshare").vw.setTop((int)((views.get("patas").vw.getHeight())/2d - (views.get("ivshare").vw.getHeight() / 2)));
views.get("ivmovies").vw.setLeft((int)(0d));
views.get("ivmovies").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("ivmovies").vw.setTop((int)((views.get("patas").vw.getTop() + views.get("patas").vw.getHeight())));
views.get("ivmovies").vw.setHeight((int)((views.get("pdetails").vw.getTop())+(5d / 100 * height) - ((views.get("patas").vw.getTop() + views.get("patas").vw.getHeight()))));
views.get("lblnama").vw.setLeft((int)((views.get("ivback").vw.getLeft() + views.get("ivback").vw.getWidth())));
views.get("lblnama").vw.setWidth((int)((views.get("ivshare").vw.getLeft()) - ((views.get("ivback").vw.getLeft() + views.get("ivback").vw.getWidth()))));
views.get("lblgenre").vw.setLeft((int)((views.get("ivback").vw.getLeft() + views.get("ivback").vw.getWidth())));
views.get("lblgenre").vw.setWidth((int)((views.get("ivshare").vw.getLeft()) - ((views.get("ivback").vw.getLeft() + views.get("ivback").vw.getWidth()))));
views.get("ivplay").vw.setLeft((int)((50d / 100 * width) - (views.get("ivplay").vw.getWidth() / 2)));
views.get("pdetails").vw.setLeft((int)(0d));
views.get("pdetails").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("pdetails").vw.setTop((int)((views.get("ivmovies").vw.getTop() + views.get("ivmovies").vw.getHeight())));
views.get("pdetails").vw.setHeight((int)((views.get("psynopsis").vw.getTop())+(4d / 100 * height) - ((views.get("ivmovies").vw.getTop() + views.get("ivmovies").vw.getHeight()))));
views.get("lblfilm").vw.setLeft((int)((views.get("ivaction").vw.getLeft())));
views.get("ivaction").vw.setLeft((int)((3d / 100 * width)));
views.get("ivaction").vw.setTop((int)((7d / 100 * height)));
views.get("lblcate").vw.setLeft((int)((10d / 100 * width)));
views.get("lblcate").vw.setTop((int)((7d / 100 * height)));
views.get("ivjam").vw.setLeft((int)((3d / 100 * width)));
views.get("ivjam").vw.setTop((int)((11d / 100 * height)));
views.get("lbljam").vw.setLeft((int)((10d / 100 * width)));
views.get("lbljam").vw.setTop((int)((11d / 100 * height)));
views.get("lblrating").vw.setLeft((int)((95d / 100 * width) - (views.get("lblrating").vw.getWidth())));
views.get("lblrating").vw.setTop((int)((7d / 100 * height)));
views.get("psynopsis").vw.setLeft((int)(0d));
views.get("psynopsis").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("psynopsis").vw.setTop((int)((views.get("pdetails").vw.getTop() + views.get("pdetails").vw.getHeight())));
views.get("psynopsis").vw.setHeight((int)((100d / 100 * height) - ((views.get("pdetails").vw.getTop() + views.get("pdetails").vw.getHeight()))));
views.get("lbldirector").vw.setLeft((int)((2d / 100 * width)));
views.get("lbldirector").vw.setTop((int)((3d / 100 * height)));
views.get("lbldirec").vw.setLeft((int)((20d / 100 * width)));
views.get("lbldirec").vw.setTop((int)((3d / 100 * height)));
views.get("lblcast").vw.setLeft((int)((2d / 100 * width)));
views.get("lblcast").vw.setTop((int)((7.5d / 100 * height)));
views.get("lblnamacast").vw.setLeft((int)((20d / 100 * width)));
views.get("lblnamacast").vw.setTop((int)((7.5d / 100 * height)));
views.get("lblwrite").vw.setLeft((int)((2d / 100 * width)));
views.get("lblwrite").vw.setTop((int)((12d / 100 * height)));
views.get("lblnamawrt").vw.setLeft((int)((20d / 100 * width)));
views.get("lblnamawrt").vw.setTop((int)((12d / 100 * height)));
views.get("lblsynopsis").vw.setTop((int)((19d / 100 * height)));
views.get("lblsynopsis").vw.setHeight((int)((views.get("lblcerita").vw.getTop()) - ((19d / 100 * height))));
views.get("lblsynopsis").vw.setLeft((int)((2d / 100 * width)));
views.get("lblcerita").vw.setLeft((int)((views.get("lblsynopsis").vw.getLeft())));
views.get("lblcerita").vw.setWidth((int)((views.get("psynopsis").vw.getWidth()) - ((views.get("lblsynopsis").vw.getLeft()))));
views.get("lblcerita").vw.setTop((int)((views.get("lblsynopsis").vw.getTop() + views.get("lblsynopsis").vw.getHeight())));
views.get("lblcerita").vw.setHeight((int)((views.get("psynopsis").vw.getHeight()) - ((views.get("lblsynopsis").vw.getTop() + views.get("lblsynopsis").vw.getHeight()))));
views.get("lblcerita").vw.setLeft((int)((2d / 100 * width)));

}
}