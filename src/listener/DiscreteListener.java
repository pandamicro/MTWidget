package listener;

import main.ProcessControler;
import widget.MTPicture;
import event.DiscreteEvent;
import event.DiscreteEventListener;

/**
 * Cet ecouteur peut ecouter "DiscreteEvent"
 * Il sert a selectionner un component dans courant MainContainer
 * 
 * @author LING Huabin
 */
public class DiscreteListener implements DiscreteEventListener{
	public void gesturePerformed(DiscreteEvent evt) {
		MTPicture pic = (MTPicture)evt.getSource();
		ProcessControler.surface.getMainContainer().select(pic);
		ProcessControler.surface.repaint();
	}
}
