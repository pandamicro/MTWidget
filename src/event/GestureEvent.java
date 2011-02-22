package event;

import java.awt.AWTEvent;
import oneDollarRecognizer.RecognizeResult;
import mygeom.Point2;

/**
 * Cette classe s'agit d'evenement de gestion d'un doigt.
 * Il est produit quand il y a qu'un doigt sur la planche tactile.
 * Plus precisement, GestureAnalyzer analyse la gestion du doigt et generer le resultat - le nom de la forme que le doigt dessine.
 * Par exemple: Le doigt dessine un rectangle sur la planche, le resultat sera "rectangle"
 * 
 * @author LING Huabin
 * @version 02/01/2010
 */
public class GestureEvent extends AWTEvent {
	private RecognizeResult result;
	private Point2[] points;
	
	/**
	 * Constructeur
	 * @param source	La ressource qui genere cet evenement
	 * @param result	Le resultat de GestureAnalyzer, le nom de la forme reconnue est inclus
	 * @param points	Les points qui representent la forme
	 */
	public GestureEvent(Object source, RecognizeResult result, Point2[] points) {
		super(source, 0);
		this.result = result;
		this.points = points;
	}
	
	public RecognizeResult getRecognizeResult(){
		return result;
	}
	public Point2[] getPath(){
		return points;
	}

	private static final long serialVersionUID = 8069778434143099023L;

}
