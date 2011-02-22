package event;

import java.util.EventListener;

/**
 * @author LING Huabin
 * @version 02/01/2010
 */
public interface GestureEventListener extends EventListener {
	public void gesturePerformed(GestureEvent evt);
}