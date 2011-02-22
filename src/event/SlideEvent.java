package event;

import java.awt.AWTEvent;
import mygeom.Vector2;

/**
 * Cette classe s'agit d'evenement du glissement
 * Il est produit quand deux doigts posses sur la planche tactile glissent
 * 
 * @author LING Huabin
 * @version 02/01/2010
 */
public class SlideEvent extends AWTEvent {
	/**
	 * constructeur
	 * @param source	La ressource qui genere cet evenement
	 * @param offset	Le vecteur du glissement
	 */
	public SlideEvent(Object source, Vector2 offset){
		super(source, 0);
		this.offset = offset;
	}
	
	public Vector2 getOffset(){
		return offset;
	}

	private Vector2 offset;
	private static final long serialVersionUID = -1520757027457955364L;
}
