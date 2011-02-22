package mygeom;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

/**
 * Cette classe sert a definir la bounding box de MTComponent<p>
 * Il defini le point origin, l'angle et la dimension
 * 
 * @author LING Huabin
 */
public class OBB {
	public OBB(Point2 origin, double angle, double width, double height){
		this.origin = origin;
		this.angle = angle;
		this.width = width;
		this.height = height;
	}
	
	public Point2 getOrigin(){
		return origin;
	}
	public void setOrigin(Point2 p){
		origin = new Point2(p.getX(), p.getY());
	}
	
	public double getWidth(){
		return width;
	}
	public double getHeight(){
		return height;
	}
	public void setDimension(double w, double h){
		width = w;
		height = h;
	}
	
	public double getAngle(){
		return angle;
	}
	public void setAngle(double a){
		angle = a;
	}
	
	public void update(Vector2 t, double a, double k){
		origin.add(t);
		angle += a;
		width *= k;
		height *= k;
	}
	
	public void update(OBB src, Vector2 t, double a, double k){
		origin.set(src.getOrigin().getX()+t.getX(), src.getOrigin().getY()+t.getY());
		angle = src.getAngle() + a;
		width = src.getWidth()*k;
		height = src.getHeight()*k;
	}

	public boolean contains(Point2 p){
		Point2D p1 = new Point2D.Double();
		AffineTransform trans = AffineTransform.getTranslateInstance(origin.getX(), origin.getY());
		trans.rotate(angle);
		trans.scale(width, height);
		try {
			trans.inverseTransform(p.getPoint2D(), p1);
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}
		if(p1.getX() >= 0 && p1.getX() <= 1 && p1.getY() >= 0 && p1.getY() <= 1)
			return true;
		else return false;
	}
	
	public String toString(){
		return new String("OBB objet, origin: "+origin+"	Angle: "+angle+"	Width: "+width+" Height: "+height);
	}
	
	public OBB copy(){
		return new OBB(new Point2(origin.getX(),origin.getY()), angle, width, height);
	}
	
	private Point2 origin; //origin
	private double width,height; //scale
	private double angle; //rotationaroundorigin
	public static final double DEGREE = Math.PI / 180;
	public static final int SCALE_NOCHANGE = 1;
}
