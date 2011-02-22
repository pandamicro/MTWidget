package event;

import java.util.EventListener;

/**
 * @author LING Huabin
 * @version 02/01/2010
 */
public interface DiscreteEventListener extends EventListener {
	public void gesturePerformed(DiscreteEvent evt);
}