package oneDollarRecognizer;

import java.util.Vector;
import mygeom.*;

public class OneDollarRecognizer {
	private Vector<Template> templates;
	
	public OneDollarRecognizer(){
		TemplateManager tempManage = new TemplateManager();
		templates = tempManage.getTemplates();
	}
	
	public RecognizeResult recognize(Point2[] points, double size){
		double b = Double.MAX_VALUE;
		double d;
		Template t = null;
		for(int i = 0; i < templates.size(); i++){
			d = distanceAtBestAngle(points, templates.get(i), -theta, theta, delta);
			if(d < b){
				b = d;
				t = templates.get(i);
			}
		}
		//double score = 1 - b/(Math.sqrt(2)*Math.abs(size)/2);
		double score = 1 - b/(0.5 * (Math.sqrt(Math.pow(size, 2)+Math.pow(size, 2))));
		return new RecognizeResult(t, score);
	}

	public static Point2[] resample(Point2[] ps){
		double dAve = pathLength(ps)/(NB_ECHANTILLON-1);
		double dt = 0, d = 0;
		Point2 tem = new Point2();
		Vector<Point2> newPoints = new Vector<Point2>(NB_ECHANTILLON);
		int i;
		//initialiser
		Vector<Point2> points = new Vector<Point2>(ps.length+NB_ECHANTILLON);
		for(i = 0; i < ps.length; i++){
			points.add(ps[i]);
		}
		//reechantillonage
		newPoints.add(points.get(0));
		for(i = 1; i < points.size(); i++){
			d = distance(points.get(i-1), points.get(i));
			if((dt + d) >= dAve){
				tem.setX(points.get(i-1).getX()
						+ ((dAve-dt)/d) * (points.get(i).getX() - points.get(i-1).getX()));
				tem.setY(points.get(i-1).getY()
						+ ((dAve-dt)/d) * (points.get(i).getY() - points.get(i-1).getY()));
				newPoints.add(tem.copy());
				points.add(i, tem);
				dt = 0;
			}
			else dt += d;
		}
		return newPoints.toArray(new Point2[0]);
	}
	
	public static Point2[] rotateToZero(Point2[] points){
		Point2 c = centroid(points);
		double angle = Math.atan2(c.getY()-points[0].getY(), c.getX()-points[0].getX());
		Point2[] newPoints = rotateBy(points, -angle);
		return newPoints;
	}
	
	public static Point2[] scaleToSquare(Point2[] points, double size){
		Point2[] newPoints = new Point2[points.length];
		Box b = boundingBox(points);
		for(int i = 0; i < points.length; i++){
			newPoints[i] = new Point2(
					points[i].getX() * (size/b.getWidth()),
					points[i].getY() * (size/b.getHeight()));
		}
		return newPoints;
	}
	
	public static Point2[] translateToOrigin(Point2[] points){
		Point2[] newPoints = new Point2[points.length];
		Point2 c = centroid(points);
		for(int i = 0; i < points.length; i++){
			newPoints[i] = new Point2(points[i].getX() - c.getX(), 
									  points[i].getY() - c.getY());
		}
		return newPoints;
	}
	
	//================================================================
	/**
	 * Calcule le longeur de la cursus
	 * 
	 * @param	points L'ensemble de points qui represente la cursus
	 * @return	la longeur
	 */
	public static double pathLength(Point2[] points){
		double d = 0;
		for(int i = 1; i < points.length; i++){
			d += distance(points[i-1], points[i]);
		}
		return d;
	}
	
	/**
	 * @param	p1 le permier point
	 * @param	p2 le deuxieme point
	 * @return	la distance entre deux points
	 */
	public static double distance(Point2 p1, Point2 p2){
		return Math.sqrt(Math.pow(p2.getX()-p1.getX(), 2) + Math.pow(p2.getY()-p1.getY(), 2));
	}
	
	/**
	 * @param points	l'ensemble des points represente la forme
	 * @return			le point central de cette forme
	 */
	public static Point2 centroid(Point2[] points){
		Point2 centroid = new Point2();
		double x = 0, y = 0;
		for(int i = 0; i < points.length; i++){
			x += points[i].getX();
			y += points[i].getY();
		}
		centroid.setX(x/points.length);
		centroid.setY(y/points.length);
		return centroid;
	}
	
	/**
	 * Rotate la forme avec certain angle
	 * @param points	La forme
	 * @param angle		L'angle pour la rotation
	 * @return			Les points apres la rotation
	 */
	public static Point2[] rotateBy(Point2[] points, double angle){
		Point2[] newPoints = new Point2[points.length];
		Point2 c = centroid(points);
		Point2 q = new Point2();
		for(int i = 0; i < points.length; i++){
			q.setX((points[i].getX()-c.getX()) * Math.cos(angle)
				 - (points[i].getY()-c.getY()) * Math.sin(angle) + c.getX());
			q.setY((points[i].getX()-c.getX()) * Math.sin(angle)
				 + (points[i].getY()-c.getY()) * Math.cos(angle) + c.getY());
			newPoints[i] = q.copy();
		}
		return newPoints;
	}
	
	/**
	 * Calcul bounding box
	 * @param points	La forme
	 * @return			Bounding box
	 */
	public static Box boundingBox(Point2[] points){
		double x1 = Double.MAX_VALUE, y1 = Double.MAX_VALUE, 
			   x2 = 0, y2 = 0;
		for(int i = 0; i < points.length; i++){
			if(x1 > points[i].getX())	x1 = points[i].getX();
			if(x2 < points[i].getX())	x2 = points[i].getX();
			if(y1 > points[i].getY())	y1 = points[i].getY();
			if(y2 < points[i].getY())	y2 = points[i].getY();
		}
		Box box = new Box(x1, y1, (int)(x2-x1), (int)(y2-y1));
		return box;
	}
	/**
	 * Trouver la distance entre la forme et le Template sous meilleure condition
	 * @param points
	 * @param t
	 * @param angleA
	 * @param angleB
	 * @param delta
	 * @return	La distance
	 */
	public double distanceAtBestAngle(Point2[] points, Template t, double angleA, double angleB, double delta){
		double x1 = phi * angleA + (1-phi) * angleB;
		double f1 = distanceAtAngle(points, t, x1);
		double x2 = (1-phi) * angleA + phi * angleB;
		double f2 = distanceAtAngle(points, t, x2);
		while(Math.abs(angleB-angleA) > delta){
			if(f1 < f2){
				angleB = x2;
				x2 = x1;
				f2 = f1;
				x1 = phi * angleA + (1-phi) * angleB;
				f1 = distanceAtAngle(points, t, x1);
			}
			else{
				angleA = x1;
				x1 = x2;
				f1 = f2;
				x2 = (1-phi) * angleA + phi * angleB;
				f2 = distanceAtAngle(points, t, x2);
			}
		}
		return Math.min(f1, f2);
	}
	public double distanceAtAngle(Point2[] points, Template t, double angle){
		double d = 0;
		Point2[] newPoints = rotateBy(points, angle);
		d = pathDistance(newPoints, t.getPoints());
		return d;
	}
	/**
	 * La distance entre deux forme
	 * @param a
	 * @param t
	 * @return	La distance
	 */
	public double pathDistance(Point2[] a, Vector<Tuple2> t){
		double d = 0;
		for(int i = 0; i < a.length; i++){
			if(i < t.size())
				d += distance(a[i], new Point2(t.get(i).getX(), t.get(i).getY()));
		}
		return d/a.length;
	}
	
	public static final int NB_ECHANTILLON = 64;
	public static final double SEUIL = 0.9;
	private static final double phi = (Math.sqrt(5)-1)/2;
	private static final double theta = Math.PI * 45/180;
	private static final double delta = 2;
}