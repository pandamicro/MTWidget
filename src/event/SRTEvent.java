package event;

import java.awt.AWTEvent;
import mygeom.*;

/**
 * Cette classe s'agit d'evenement de gestion de deux doigts.
 * Le resultat est propose par GestureAnalyzer
 * GestureAnalyzer analyse la gestion des deux doigts et obtenir la translation, la rotation et le scale corresponds
 * 
 * @author LING Huabin
 * @version 02/01/2010
 */
public class SRTEvent extends AWTEvent {
	private static final long serialVersionUID = 1L;
	private Vector2 translation;
	private double rotation;
	private double scale = OBB.SCALE_NOCHANGE;

	public SRTEvent(Object source, Vector2 translation, double rotation, double scale) {
		super(source, 0);
		this.translation = translation;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public Vector2 getTranslation(){
		return translation;
	}
	public double getRotation(){
		return rotation;
	}
	public double getScale(){
		return scale;
	}
}

