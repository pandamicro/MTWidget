package mygeom;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import main.MainWindow;

public class Point2 extends Tuple2 {

		public Point2() {
			super();
		}
	
		public Point2(Point2 a) {
			super(a);
		}
		
		public Point2(Point2 a,Point2 b) {
			super(b);
			this.sub(a);
		}
		
		public Point2(double x,double y) {
			super(x,y);
		}
		
		public Point2(Point2D.Double p){
			super(p.getX(), p.getY());
		}
		
		public static Point2 transfer(Point2 edt){
			Point2 tmp = new Point2();
			tmp.setX(edt.getX()*MainWindow.WINDOWWIDTH);
			tmp.setY(edt.getY()*MainWindow.WINDOWHEIGHT);
			return tmp;
		}
		
		public void set(Point2D.Double p){
			super.set(p.getX(), p.getY());
		}
		
		public void copy(Point2 a) {
	    	a.set(getX(), getY());
	    }
		
		public Point2 copy(){
			return new Point2(getX(),getY());
		}
		
		public Point2D.Double getPoint2D(){
			return new Point2D.Double(super.getX(), super.getY());
		}
		
		public void transform(AffineTransform t){
			Point2D.Double src = getPoint2D();
			Point2D.Double dst = new Point2D.Double();
			t.transform(src, dst);
			set(dst);
		}
		
		public static final int w = 1;
}
