package listener;

import widget.MTComponent;
import widget.MainContainer;
import event.SClickEventListener;
import event.SingleClickEvent;

/**
 * Cet ecouteur peut ecouter "SingleClickEvent"
 * Il sert a informer MTContainer que un component est selectionne.
 * 
 * @author LING Huabin
 * @version 07/01/2010
 */
public class SelectListener implements SClickEventListener {
	public void gesturePerformed(SingleClickEvent evt) {
		MTComponent c = (MTComponent)evt.getSource();
		if(c.getMTContainer() != null){
			c.getMTContainer().chooseComp(c);
		}
		else if(c.getClass() == MainContainer.class)
			((MainContainer)c).chooseComp(c);	//pour deselectionner une image
	}
}