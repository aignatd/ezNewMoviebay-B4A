package newmoviebay.mncplaymedia.com.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_content{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("patas").vw.setLeft((int)(0d));
views.get("patas").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("patas").vw.setTop((int)(0d));
views.get("patas").vw.setHeight((int)((views.get("putama").vw.getTop()) - (0d)));
views.get("putama").vw.setLeft((int)(0d));
views.get("putama").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("putama").vw.setTop((int)((views.get("patas").vw.getTop() + views.get("patas").vw.getHeight())));
views.get("putama").vw.setHeight((int)((100d / 100 * height) - ((views.get("patas").vw.getTop() + views.get("patas").vw.getHeight()))));
views.get("ivback").vw.setLeft((int)(0d));
views.get("ivback").vw.setWidth((int)((10d / 100 * width) - (0d)));
views.get("ivback").vw.setTop((int)(0d));
views.get("ivback").vw.setHeight((int)((10d / 100 * height) - (0d)));
views.get("ivshare").vw.setLeft((int)((90d / 100 * width)));
views.get("ivshare").vw.setWidth((int)((100d / 100 * width) - ((90d / 100 * width))));
views.get("ivshare").vw.setTop((int)(0d));
views.get("ivshare").vw.setHeight((int)((10d / 100 * height) - (0d)));
views.get("plabel").vw.setLeft((int)((views.get("ivback").vw.getLeft() + views.get("ivback").vw.getWidth())));
views.get("plabel").vw.setWidth((int)((views.get("ivshare").vw.getLeft()) - ((views.get("ivback").vw.getLeft() + views.get("ivback").vw.getWidth()))));
views.get("plabel").vw.setTop((int)((1d / 100 * height)));
views.get("plabel").vw.setHeight((int)((10d / 100 * height) - ((1d / 100 * height))));
views.get("lblatas").vw.setLeft((int)(0d));
views.get("lblatas").vw.setWidth((int)((80d / 100 * width) - (0d)));
//BA.debugLineNum = 20;BA.debugLine="lblAtas.SetTopAndBottom(0, 4%y)"[content/General script]
views.get("lblatas").vw.setTop((int)(0d));
views.get("lblatas").vw.setHeight((int)((4d / 100 * height) - (0d)));
//BA.debugLineNum = 22;BA.debugLine="lblBawah.SetLeftAndRight(0,80%x)"[content/General script]
views.get("lblbawah").vw.setLeft((int)(0d));
views.get("lblbawah").vw.setWidth((int)((80d / 100 * width) - (0d)));
//BA.debugLineNum = 23;BA.debugLine="lblBawah.SetTopAndBottom(lblAtas.Bottom, 8%y)"[content/General script]
views.get("lblbawah").vw.setTop((int)((views.get("lblatas").vw.getTop() + views.get("lblatas").vw.getHeight())));
views.get("lblbawah").vw.setHeight((int)((8d / 100 * height) - ((views.get("lblatas").vw.getTop() + views.get("lblatas").vw.getHeight()))));
//BA.debugLineNum = 25;BA.debugLine="pMovies.SetLeftAndRight(0,100%x)"[content/General script]
views.get("pmovies").vw.setLeft((int)(0d));
views.get("pmovies").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 26;BA.debugLine="PMovies.SetTopAndBottom(0, 35%y)"[content/General script]
views.get("pmovies").vw.setTop((int)(0d));
views.get("pmovies").vw.setHeight((int)((35d / 100 * height) - (0d)));
//BA.debugLineNum = 28;BA.debugLine="ivMovies.SetLeftAndRight(0,100%x)"[content/General script]
views.get("ivmovies").vw.setLeft((int)(0d));
views.get("ivmovies").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 29;BA.debugLine="ivMovies.SetTopAndBottom(0,35%y)"[content/General script]
views.get("ivmovies").vw.setTop((int)(0d));
views.get("ivmovies").vw.setHeight((int)((35d / 100 * height) - (0d)));
//BA.debugLineNum = 31;BA.debugLine="ivPlay.SetLeftAndRight(40%x, 60%x)"[content/General script]
views.get("ivplay").vw.setLeft((int)((40d / 100 * width)));
views.get("ivplay").vw.setWidth((int)((60d / 100 * width) - ((40d / 100 * width))));
//BA.debugLineNum = 32;BA.debugLine="ivPlay.SetTopAndBottom(8%y, 18%y)"[content/General script]
views.get("ivplay").vw.setTop((int)((8d / 100 * height)));
views.get("ivplay").vw.setHeight((int)((18d / 100 * height) - ((8d / 100 * height))));
//BA.debugLineNum = 34;BA.debugLine="pMenu.SetLeftAndRight(0,100%x)"[content/General script]
views.get("pmenu").vw.setLeft((int)(0d));
views.get("pmenu").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 35;BA.debugLine="pMenu.SetTopAndBottom(27%y,35%y)"[content/General script]
views.get("pmenu").vw.setTop((int)((27d / 100 * height)));
views.get("pmenu").vw.setHeight((int)((35d / 100 * height) - ((27d / 100 * height))));
//BA.debugLineNum = 37;BA.debugLine="ivTrailer.SetLeftAndRight(4%x, 12%x)"[content/General script]
views.get("ivtrailer").vw.setLeft((int)((4d / 100 * width)));
views.get("ivtrailer").vw.setWidth((int)((12d / 100 * width) - ((4d / 100 * width))));
//BA.debugLineNum = 38;BA.debugLine="ivTrailer.SetTopAndBottom(0, 8%y)"[content/General script]
views.get("ivtrailer").vw.setTop((int)(0d));
views.get("ivtrailer").vw.setHeight((int)((8d / 100 * height) - (0d)));
//BA.debugLineNum = 40;BA.debugLine="lblTrailer.SetLeftAndRight(ivTrailer.Right, 28%x)"[content/General script]
views.get("lbltrailer").vw.setLeft((int)((views.get("ivtrailer").vw.getLeft() + views.get("ivtrailer").vw.getWidth())));
views.get("lbltrailer").vw.setWidth((int)((28d / 100 * width) - ((views.get("ivtrailer").vw.getLeft() + views.get("ivtrailer").vw.getWidth()))));
//BA.debugLineNum = 41;BA.debugLine="lblTrailer.SetTopAndBottom(0, 8%y)"[content/General script]
views.get("lbltrailer").vw.setTop((int)(0d));
views.get("lbltrailer").vw.setHeight((int)((8d / 100 * height) - (0d)));
//BA.debugLineNum = 43;BA.debugLine="ivRate.SetLeftAndRight(38%x, 44%x)"[content/General script]
views.get("ivrate").vw.setLeft((int)((38d / 100 * width)));
views.get("ivrate").vw.setWidth((int)((44d / 100 * width) - ((38d / 100 * width))));
//BA.debugLineNum = 44;BA.debugLine="ivRate.SetTopAndBottom(0, 8%y)"[content/General script]
views.get("ivrate").vw.setTop((int)(0d));
views.get("ivrate").vw.setHeight((int)((8d / 100 * height) - (0d)));
//BA.debugLineNum = 46;BA.debugLine="lblRate.SetLeftAndRight(ivRate.Right, 56%x)"[content/General script]
views.get("lblrate").vw.setLeft((int)((views.get("ivrate").vw.getLeft() + views.get("ivrate").vw.getWidth())));
views.get("lblrate").vw.setWidth((int)((56d / 100 * width) - ((views.get("ivrate").vw.getLeft() + views.get("ivrate").vw.getWidth()))));
//BA.debugLineNum = 47;BA.debugLine="lblRate.SetTopAndBottom(0,8%y)"[content/General script]
views.get("lblrate").vw.setTop((int)(0d));
views.get("lblrate").vw.setHeight((int)((8d / 100 * height) - (0d)));
//BA.debugLineNum = 49;BA.debugLine="ivComments.SetLeftAndRight(64%x, 74%x)"[content/General script]
views.get("ivcomments").vw.setLeft((int)((64d / 100 * width)));
views.get("ivcomments").vw.setWidth((int)((74d / 100 * width) - ((64d / 100 * width))));
//BA.debugLineNum = 50;BA.debugLine="ivComments.SetTopAndBottom(0,8%y)"[content/General script]
views.get("ivcomments").vw.setTop((int)(0d));
views.get("ivcomments").vw.setHeight((int)((8d / 100 * height) - (0d)));
//BA.debugLineNum = 52;BA.debugLine="lblComments.SetLeftAndRight(ivComments.Right, 100%x)"[content/General script]
views.get("lblcomments").vw.setLeft((int)((views.get("ivcomments").vw.getLeft() + views.get("ivcomments").vw.getWidth())));
views.get("lblcomments").vw.setWidth((int)((100d / 100 * width) - ((views.get("ivcomments").vw.getLeft() + views.get("ivcomments").vw.getWidth()))));
//BA.debugLineNum = 53;BA.debugLine="lblComments.SetTopAndBottom(0,8%y)"[content/General script]
views.get("lblcomments").vw.setTop((int)(0d));
views.get("lblcomments").vw.setHeight((int)((8d / 100 * height) - (0d)));
//BA.debugLineNum = 55;BA.debugLine="pBawah.SetLeftAndRight(0,100%x)"[content/General script]
views.get("pbawah").vw.setLeft((int)(0d));
views.get("pbawah").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 56;BA.debugLine="pBawah.SetTopAndBottom(PMovies.Bottom, 100%y)"[content/General script]
views.get("pbawah").vw.setTop((int)((views.get("pmovies").vw.getTop() + views.get("pmovies").vw.getHeight())));
views.get("pbawah").vw.setHeight((int)((100d / 100 * height) - ((views.get("pmovies").vw.getTop() + views.get("pmovies").vw.getHeight()))));
//BA.debugLineNum = 58;BA.debugLine="lblTitle.SetLeftAndRight(2%x,40%x)"[content/General script]
views.get("lbltitle").vw.setLeft((int)((2d / 100 * width)));
views.get("lbltitle").vw.setWidth((int)((40d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 59;BA.debugLine="lblTitle.SetTopAndBottom(1%x,5%y)"[content/General script]
views.get("lbltitle").vw.setTop((int)((1d / 100 * width)));
views.get("lbltitle").vw.setHeight((int)((5d / 100 * height) - ((1d / 100 * width))));
//BA.debugLineNum = 61;BA.debugLine="ivGenre.SetLeftAndRight(2%x, 8%x)"[content/General script]
views.get("ivgenre").vw.setLeft((int)((2d / 100 * width)));
views.get("ivgenre").vw.setWidth((int)((8d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 62;BA.debugLine="ivGenre.SetTopAndBottom(lblTitle.Bottom, 8%y)"[content/General script]
views.get("ivgenre").vw.setTop((int)((views.get("lbltitle").vw.getTop() + views.get("lbltitle").vw.getHeight())));
views.get("ivgenre").vw.setHeight((int)((8d / 100 * height) - ((views.get("lbltitle").vw.getTop() + views.get("lbltitle").vw.getHeight()))));
//BA.debugLineNum = 64;BA.debugLine="lblGenre.SetLeftAndRight(ivGenre.Right, 30%x)"[content/General script]
views.get("lblgenre").vw.setLeft((int)((views.get("ivgenre").vw.getLeft() + views.get("ivgenre").vw.getWidth())));
views.get("lblgenre").vw.setWidth((int)((30d / 100 * width) - ((views.get("ivgenre").vw.getLeft() + views.get("ivgenre").vw.getWidth()))));
//BA.debugLineNum = 65;BA.debugLine="lblGenre.SetTopAndBottom(lblTitle.Bottom, 8%y)"[content/General script]
views.get("lblgenre").vw.setTop((int)((views.get("lbltitle").vw.getTop() + views.get("lbltitle").vw.getHeight())));
views.get("lblgenre").vw.setHeight((int)((8d / 100 * height) - ((views.get("lbltitle").vw.getTop() + views.get("lbltitle").vw.getHeight()))));
//BA.debugLineNum = 67;BA.debugLine="ivDuration.SetLeftAndRight(2%x, 8%x)"[content/General script]
views.get("ivduration").vw.setLeft((int)((2d / 100 * width)));
views.get("ivduration").vw.setWidth((int)((8d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 68;BA.debugLine="ivDuration.SetTopAndBottom(ivGenre.Bottom, 12%y)"[content/General script]
views.get("ivduration").vw.setTop((int)((views.get("ivgenre").vw.getTop() + views.get("ivgenre").vw.getHeight())));
views.get("ivduration").vw.setHeight((int)((12d / 100 * height) - ((views.get("ivgenre").vw.getTop() + views.get("ivgenre").vw.getHeight()))));
//BA.debugLineNum = 70;BA.debugLine="lblDuration.SetLeftAndRight(ivDuration.Right, 30%x)"[content/General script]
views.get("lblduration").vw.setLeft((int)((views.get("ivduration").vw.getLeft() + views.get("ivduration").vw.getWidth())));
views.get("lblduration").vw.setWidth((int)((30d / 100 * width) - ((views.get("ivduration").vw.getLeft() + views.get("ivduration").vw.getWidth()))));
//BA.debugLineNum = 71;BA.debugLine="lblDuration.SetTopAndBottom(lblGenre.Bottom, 12%y)"[content/General script]
views.get("lblduration").vw.setTop((int)((views.get("lblgenre").vw.getTop() + views.get("lblgenre").vw.getHeight())));
views.get("lblduration").vw.setHeight((int)((12d / 100 * height) - ((views.get("lblgenre").vw.getTop() + views.get("lblgenre").vw.getHeight()))));
//BA.debugLineNum = 73;BA.debugLine="ivGaris.SetLeftAndRight(2%x,92%x)"[content/General script]
views.get("ivgaris").vw.setLeft((int)((2d / 100 * width)));
views.get("ivgaris").vw.setWidth((int)((92d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 74;BA.debugLine="ivGaris.SetTopAndBottom(lblDuration.Bottom, 15%y)"[content/General script]
views.get("ivgaris").vw.setTop((int)((views.get("lblduration").vw.getTop() + views.get("lblduration").vw.getHeight())));
views.get("ivgaris").vw.setHeight((int)((15d / 100 * height) - ((views.get("lblduration").vw.getTop() + views.get("lblduration").vw.getHeight()))));
//BA.debugLineNum = 76;BA.debugLine="lblRating.SetLeftAndRight(80%x, 90%x)"[content/General script]
views.get("lblrating").vw.setLeft((int)((80d / 100 * width)));
views.get("lblrating").vw.setWidth((int)((90d / 100 * width) - ((80d / 100 * width))));
//BA.debugLineNum = 77;BA.debugLine="lblRating.SetTopAndBottom(5%y, ivGaris.Top)"[content/General script]
views.get("lblrating").vw.setTop((int)((5d / 100 * height)));
views.get("lblrating").vw.setHeight((int)((views.get("ivgaris").vw.getTop()) - ((5d / 100 * height))));
//BA.debugLineNum = 79;BA.debugLine="lblDirector.SetLeftAndRight(2%x, 30%x)"[content/General script]
views.get("lbldirector").vw.setLeft((int)((2d / 100 * width)));
views.get("lbldirector").vw.setWidth((int)((30d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 80;BA.debugLine="lblDirector.SetTopAndBottom(ivGaris.Bottom, 18%y)"[content/General script]
views.get("lbldirector").vw.setTop((int)((views.get("ivgaris").vw.getTop() + views.get("ivgaris").vw.getHeight())));
views.get("lbldirector").vw.setHeight((int)((18d / 100 * height) - ((views.get("ivgaris").vw.getTop() + views.get("ivgaris").vw.getHeight()))));
//BA.debugLineNum = 82;BA.debugLine="lblCast.SetLeftAndRight(2%x, 30%x)"[content/General script]
views.get("lblcast").vw.setLeft((int)((2d / 100 * width)));
views.get("lblcast").vw.setWidth((int)((30d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 83;BA.debugLine="lblCast.SetTopAndBottom(lblDirector.Bottom, 22%y)"[content/General script]
views.get("lblcast").vw.setTop((int)((views.get("lbldirector").vw.getTop() + views.get("lbldirector").vw.getHeight())));
views.get("lblcast").vw.setHeight((int)((22d / 100 * height) - ((views.get("lbldirector").vw.getTop() + views.get("lbldirector").vw.getHeight()))));
//BA.debugLineNum = 85;BA.debugLine="lblWriter.SetLeftAndRight(2%x, 30%x)"[content/General script]
views.get("lblwriter").vw.setLeft((int)((2d / 100 * width)));
views.get("lblwriter").vw.setWidth((int)((30d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 86;BA.debugLine="lblWriter.SetTopAndBottom(lblCast.Bottom, 25%y)"[content/General script]
views.get("lblwriter").vw.setTop((int)((views.get("lblcast").vw.getTop() + views.get("lblcast").vw.getHeight())));
views.get("lblwriter").vw.setHeight((int)((25d / 100 * height) - ((views.get("lblcast").vw.getTop() + views.get("lblcast").vw.getHeight()))));
//BA.debugLineNum = 88;BA.debugLine="lblDirec.SetLeftAndRight(lblDirector.Right, 80%x)"[content/General script]
views.get("lbldirec").vw.setLeft((int)((views.get("lbldirector").vw.getLeft() + views.get("lbldirector").vw.getWidth())));
views.get("lbldirec").vw.setWidth((int)((80d / 100 * width) - ((views.get("lbldirector").vw.getLeft() + views.get("lbldirector").vw.getWidth()))));
//BA.debugLineNum = 89;BA.debugLine="lblDirec.SetTopAndBottom(ivGaris.Bottom, 18%y)"[content/General script]
views.get("lbldirec").vw.setTop((int)((views.get("ivgaris").vw.getTop() + views.get("ivgaris").vw.getHeight())));
views.get("lbldirec").vw.setHeight((int)((18d / 100 * height) - ((views.get("ivgaris").vw.getTop() + views.get("ivgaris").vw.getHeight()))));
//BA.debugLineNum = 91;BA.debugLine="lblNmCast.SetLeftAndRight(lblCast.Right, 80%x)"[content/General script]
views.get("lblnmcast").vw.setLeft((int)((views.get("lblcast").vw.getLeft() + views.get("lblcast").vw.getWidth())));
views.get("lblnmcast").vw.setWidth((int)((80d / 100 * width) - ((views.get("lblcast").vw.getLeft() + views.get("lblcast").vw.getWidth()))));
//BA.debugLineNum = 92;BA.debugLine="lblNmCast.SetTopAndBottom(lblDirec.Bottom, 22%y)"[content/General script]
views.get("lblnmcast").vw.setTop((int)((views.get("lbldirec").vw.getTop() + views.get("lbldirec").vw.getHeight())));
views.get("lblnmcast").vw.setHeight((int)((22d / 100 * height) - ((views.get("lbldirec").vw.getTop() + views.get("lbldirec").vw.getHeight()))));
//BA.debugLineNum = 94;BA.debugLine="lblNmWriter.SetLeftAndRight(lblWriter.Right, 80%x)"[content/General script]
views.get("lblnmwriter").vw.setLeft((int)((views.get("lblwriter").vw.getLeft() + views.get("lblwriter").vw.getWidth())));
views.get("lblnmwriter").vw.setWidth((int)((80d / 100 * width) - ((views.get("lblwriter").vw.getLeft() + views.get("lblwriter").vw.getWidth()))));
//BA.debugLineNum = 95;BA.debugLine="lblNmWriter.SetTopAndBottom(lblNmCast.Bottom, 25%y)"[content/General script]
views.get("lblnmwriter").vw.setTop((int)((views.get("lblnmcast").vw.getTop() + views.get("lblnmcast").vw.getHeight())));
views.get("lblnmwriter").vw.setHeight((int)((25d / 100 * height) - ((views.get("lblnmcast").vw.getTop() + views.get("lblnmcast").vw.getHeight()))));
//BA.debugLineNum = 97;BA.debugLine="lblSynopsis.SetLeftAndRight(2%x, 30%x)"[content/General script]
views.get("lblsynopsis").vw.setLeft((int)((2d / 100 * width)));
views.get("lblsynopsis").vw.setWidth((int)((30d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 98;BA.debugLine="lblSynopsis.SetTopAndBottom(lblWriter.Bottom + 1%y, 30%y)"[content/General script]
views.get("lblsynopsis").vw.setTop((int)((views.get("lblwriter").vw.getTop() + views.get("lblwriter").vw.getHeight())+(1d / 100 * height)));
views.get("lblsynopsis").vw.setHeight((int)((30d / 100 * height) - ((views.get("lblwriter").vw.getTop() + views.get("lblwriter").vw.getHeight())+(1d / 100 * height))));
//BA.debugLineNum = 100;BA.debugLine="lblCerita.SetLeftAndRight(2%x,100%x)"[content/General script]
views.get("lblcerita").vw.setLeft((int)((2d / 100 * width)));
views.get("lblcerita").vw.setWidth((int)((100d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 101;BA.debugLine="lblCerita.SetTopAndBottom(lblSynopsis.Bottom + 1%y, 100%y)"[content/General script]
views.get("lblcerita").vw.setTop((int)((views.get("lblsynopsis").vw.getTop() + views.get("lblsynopsis").vw.getHeight())+(1d / 100 * height)));
views.get("lblcerita").vw.setHeight((int)((100d / 100 * height) - ((views.get("lblsynopsis").vw.getTop() + views.get("lblsynopsis").vw.getHeight())+(1d / 100 * height))));

}
}