package listener;

import main.ProcessControler;
import widget.MTPicture;
import event.SRTEvent;
import event.SRTEventListener;

/**
 * Cet ecouteur peut ecouter "SRTEvent"
 * Il sert a faire bouger, agrandir, tourner un component
 * 
 * @author LING Huabin
 */
public class SRTListener implements SRTEventListener {
	public void gesturePerformed(SRTEvent evt) {
		MTPicture pic = (MTPicture)evt.getSource();
		pic.updatePosition(evt.getTranslation(), evt.getRotation(), evt.getScale());
		ProcessControler.surface.repaint();
	}
}
