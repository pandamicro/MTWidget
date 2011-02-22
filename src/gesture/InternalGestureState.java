package gesture;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import mygeom.Point2;
import mygeom.Vector2;

/**
 * Cette classe sert a calcule la translation, la rotation, le scale 
 * qui doivent etre effectue au component par apport a la gestion des doigts.
 * La calcule est limite pour au maximum deux doigts
 * 
 * @author LING Huabin
 */
public class InternalGestureState {
    private Point2 initPA, currPA, initPB, currPB;
    
    public Point2 getInitPA(){
    	return initPA;
    }
    public Point2 getInitPB(){
    	return initPB;
    }
    public Point2 getCurrPA(){
    	return currPA;
    }
    public Point2 getCurrPB(){
    	return currPB;
    }
    public double calculeAngle(Point2 a, Point2 b){
    	double x1 = a.getX();	double y1 = a.getY();
		double x2 = b.getX();	double y2 = b.getY();
		double distance = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
		double angle = Math.acos(Math.abs(x2-x1) / distance);
		return angle;
    }
    public Vector2 normaliseInit(){
    	Vector2 v = new Vector2(initPB.getX()-initPA.getX(), initPB.getY()-initPA.getY());
    	double lv = Math.sqrt(v.getX()*v.getX() + v.getY()*v.getY());
    	return new Vector2(v.getX()/lv, v.getY()/lv);
    }
    public Vector2 normaliseCurr(){
    	Vector2 v = new Vector2(currPB.getX()-currPA.getX(), currPB.getY()-currPA.getY());
    	double lv = Math.sqrt(v.getX()*v.getX() + v.getY()*v.getY());
    	return new Vector2(v.getX()/lv, v.getY()/lv);
    }
    
    public void motionTranslateBegin(Point2 initial){
    	initPA = initial;
    }
    
    public void motionTranslateUpdate(Point2 current){
    	currPA = current;
    }
    
    public void motionTRSBegin(Point2 initA, Point2 initB){
    	initPA = initA.copy();
    	initPB = initB.copy();
    }
    
    public void motionTRSUpdate(Point2 currA, Point2 currB){
    	currPA = currA.copy();
    	currPB = currB.copy();
    }
    
    public void motionTranslateEnd(){
    	//rien a faire
    }
    public void motionTRSEnd(){
    	initPA = currPA.copy();
    	initPB = currPB.copy();
    }

    public Vector2 computeTranslation(){
    	return new Vector2(currPA.getX()-initPA.getX(), currPA.getY()-initPA.getY());
    }
    public Vector2 computeTranslation(Point2 p){// pour deux doigts
		Point2D pf = new Point2D.Double();
		Point2D tem = new Point2D.Double();
		Vector2 v;
		// M 0-1
		AffineTransform trans01 = new AffineTransform();
		v = normaliseInit(); // vecteur AB normalise
		trans01.setTransform(v.getX(), v.getY(), 
							-v.getY(), v.getX(), 
							initPA.getX(), initPA.getY());
		// M 1-0
		AffineTransform trans10 = trans01;
		try {
			trans10 = trans01.createInverse();
		} catch (NoninvertibleTransformException e1) {
			e1.printStackTrace();
		}
		// M 0-2
		AffineTransform trans02 = new AffineTransform();
		v = normaliseCurr(); // vecteur A'B' normalise
		trans02.setTransform(v.getX(), v.getY(), 
							-v.getY(), v.getX(),
							currPA.getX(), currPA.getY());
		// appliquer la transformation 0->1
		trans10.transform(p.getPoint2D(), tem); // tem = point origin(dans repere1) = nouveau point origin(dans repere2)
		// prend en compte l'echelle
		double scale = computeScale();
		tem.setLocation(tem.getX()*scale, tem.getY()*scale);
		// appliquer la transformation 2->0
		trans02.transform(tem, pf); // pf = nouveau point origin dans repere0
		v.set(pf.getX()-p.getX(), pf.getY()-p.getY());
		return v;
	}
    
    public double computeRotation(){
    	Vector2 a = new Vector2(initPB.getX()-initPA.getX(), initPB.getY()-initPA.getY());
    	Vector2 b = new Vector2(currPB.getX()-currPA.getX(), currPB.getY()-currPA.getY());
    	return calculAngle(a, b);
    }
    
    public double computeScale(){
    	Vector2 a = new Vector2(initPB.getX()-initPA.getX(), initPB.getY()-initPA.getY());
    	Vector2 b = new Vector2(currPB.getX()-currPA.getX(), currPB.getY()-currPA.getY());
    	double la = Math.sqrt(a.getX()*a.getX() + a.getY()*a.getY());
    	double lb = Math.sqrt(b.getX()*b.getX() + b.getY()*b.getY());
    	return lb/la;
    }
    
    public static double calculAngle(Vector2 a, Vector2 b){
    	double m = Math.sqrt(a.getX()*a.getX() + a.getY()*a.getY());
    	a.setX(a.getX()/m);
    	a.setY(a.getY()/m);
    	m = Math.sqrt(b.getX()*b.getX() + b.getY()*b.getY());
    	b.setX(b.getX()/m);
    	b.setY(b.getY()/m);
    	m = a.getX()*b.getX() + a.getY()*b.getY();  // a.b
    	m = Math.acos(m); // Calcule l'angle = acos(a.b)
    	double det = a.getX()*b.getY() - a.getY()*b.getX();
    	if(det > 0) return m;
    	else if(det < 0) return -m;
    	else return 0;
    }
}