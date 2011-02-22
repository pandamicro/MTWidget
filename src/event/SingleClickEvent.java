package event;

import java.awt.AWTEvent;

/**
 * Cette classe s'agit d'evenement de single clique
 * Il est produit quand un element sur l'ecran est clique
 * 
 * @author LING Huabin
 * @version 02/01/2010
 */
public class SingleClickEvent extends AWTEvent {
	private static final long serialVersionUID = 3688338203472124604L;
	
	public SingleClickEvent(Object source) {
		super(source, 0);
	}
}
