package mygeom;

import gesture.InternalGestureState;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import oneDollarRecognizer.OneDollarRecognizer;

public class InertiaMatrix {
	/**
	 * Cette classe sert a calcule la bounding box d'une path
	 * <p>
	 * La path represente plutot une forme que l'utilisateur a dessine, 
	 * pour l'instant, cette classe est utilisee pour faire apparaitre 
	 * une nouvelle image dans le rectangle que l'utilisateur a dessinee
	 * @param points	Les points seraient la path que l'utilisateur a dessinee
	 */
	public InertiaMatrix(Point2[] points){
		this.points = OneDollarRecognizer.resample(points);
		origin = this.points[0].copy();
		centroid = OneDollarRecognizer.centroid(this.points);
	}
	
	/**
	 * Retourner la bounding box de cette path
	 * @return	L'OBB de la path, le point origin, la dimension et l'angle sont inclus
	 */
	public OBB getOBB(){
		//Trouver les vecteurs propres
		momentsInertie();
		calculVecteurPropre();
		
		//Calcule l'angle
		double angle;
		Vector2 vecteur;
		if(Math.abs(v1.getY()) > Math.abs(v2.getY()))
			vecteur = v2;
		else vecteur = v1;
		Vector2 det = new Vector2(origin.getX()-centroid.getX(), origin.getY()-centroid.getY());
		double a1 = InternalGestureState.calculAngle(vecteur, det);
		double a2 = InternalGestureState.calculAngle(vecteur.getOppose(), det);
		if(a1 <= a2)
			angle = InternalGestureState.calculAngle(VECTOR_X, vecteur);
		else
			angle = InternalGestureState.calculAngle(VECTOR_X, vecteur.getOppose());
		
		//Calcule la dimension
		AffineTransform trans01 = new AffineTransform();
		AffineTransform trans10 = new AffineTransform();
		trans01.setTransform(vecteur.getX(), vecteur.getY(), -vecteur.getY(), vecteur.getX(), 
				centroid.getX(), centroid.getY());
		try {
			trans10 = trans01.createInverse();
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}
		transform(trans10);
		Box box = OneDollarRecognizer.boundingBox(points);
		
		return new OBB(origin, angle, box.getWidth(), box.getHeight());
	}
	
	private void momentsInertie(){
		A = moinsF = B = 0;
		for(Point2 p: points){
			A += Math.pow(p.getY()-centroid.getY(), 2); // Jxx = somme(mi * yi^2)
			B += Math.pow(p.getX()-centroid.getX(), 2); // Jyy = somme(mi * xi^2)
			moinsF -= (p.getX()-centroid.getX()) * (p.getY()-centroid.getY()); // Jxy = Jyx = somme(mi * xi * yi)
		}
	}
	
	private void calculVecteurPropre(){
		// r = (A+B +/- sqrt((A-B)^2 + 4F^2))/2
		double tmp1 = A + B;
		double tmp2 = Math.sqrt(Math.pow(A-B, 2) + 4*Math.pow(moinsF, 2));
		double r1 = (tmp1 + tmp2)/2;
		double r2 = (tmp1 - tmp2)/2;
		// v = (-F, -A+r)
		v1 = new Vector2(moinsF, r1-A);
		v2 = new Vector2(moinsF, r2-A);
	}
	
	/**
	 * Transformer tous les points dans un nouveau repere
	 * @param trans	La transformation
	 */
	private void transform(AffineTransform trans){
		if(trans == null)
			return;
		for(Point2 p: points)
			p.transform(trans);
	}
	
	/**
	 * La path de la forme
	 */
	private Point2[] points;
	private Point2 origin;
	private Point2 centroid;
	/**
	 * A: momInertieXX<p>
	 * B: momInertieYY<p>
	 * moinsF: momInertieXY
	 */
	private double A, moinsF, B;
	/**
	 * Les vecteurs propres
	 */
	private Vector2 v1, v2;
	/**
	 * Le vecteur du x Axis
	 */
	private static final Vector2 VECTOR_X = new Vector2(1,0);
}
