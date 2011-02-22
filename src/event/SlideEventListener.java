package event;

import java.util.EventListener;

/**
 * @author LING Huabin
 * @version 02/01/2010
 */
public interface SlideEventListener extends EventListener {
	public void gesturePerformed(SlideEvent evt);
}