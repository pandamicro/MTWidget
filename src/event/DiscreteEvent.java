package event;

import java.awt.AWTEvent;

/**
 * Cette classe s'agit d'un evenement de mouvement de doigt.
 * Il est produit quand il y a qu'un doigt sur la planche tactile et quand il bouge.
 * 
 * @author LING Huabin
 * @version 02/01/2010
 */
public class DiscreteEvent extends AWTEvent {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 * 
	 * @param source	La ressource qui genere cet evenement
	 */
	public DiscreteEvent(Object source) {
		super(source, 0);
	}
}
