package event;

import java.util.EventListener;

/**
 * @author LING Huabin
 * @version 02/01/2010
 */
public interface SClickEventListener extends EventListener {
	public void gesturePerformed(SingleClickEvent evt);
}