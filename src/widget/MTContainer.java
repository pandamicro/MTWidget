package widget;

import java.awt.AWTEvent;
import java.util.ArrayList;
import event.SlideEvent;
import event.SlideEventListener;
import listener.SelectListener;
import mygeom.Point2;
import mygeom.Vector2;

public abstract class MTContainer extends MTComponent {
	public MTContainer() {
		super(null);
		analyzer.setModeOneDollar(true);
		analyzer.setModeSingleClick(true);
		this.addSingleClickListener(new SelectListener());
	}
	
	/**
	 * Ajouter un objet dans ce MTContainer
	 * 
	 * @param c	Un objet de MTComponent
	 */
	public void addComponent(MTComponent c){
		components.add(c);
	}
	
	public void removeAll(){
		super.removeAll();
		components.clear();
	}
	
	public void remove(MTComponent c){
		if(!components.contains(c)) return;
		components.remove(c);
		c = null;
	}
	
	/**
	 * Obtenir quel component dans ce MTContainer contient le point d'intrt
	 * 
	 * @param p	Le point d'intrt
	 * @return	Le component que le point concentre
	 */
	public MTComponent whichIs(Point2 p){
		for(int i = components.size()-1; i >= 0 ; i--){
			if(components.get(i).isInside(p))
				return components.get(i);
		}
		return this;
	}

	/**
	 * Set choosed un component
	 * 
	 * @param c	Le nouveau component focal
	 */
	public void chooseComp(MTComponent c){
		for(MTComponent i: components){
			if(i != c)
				i.setChoosed(false);
			else i.setChoosed(true);
		}
	}
	
	public MTComponent focusOn(){
		for(MTComponent i: components){
			if(i.choosed) return i;
		}
		return this;
	}
	
	public void addSlideListener(SlideEventListener e){
		listenerList.add(SlideEventListener.class, e);
	}
	
	public void fireSlidePerformed(AWTEvent event) {
		if(enableListeners){
			Object[] listeners = listenerList.getListenerList();
			for (int i = listeners.length-2; i >= 0; i -= 2) {
				if(listeners[i] == SlideEventListener.class) {
					((SlideEventListener)listeners[i+1]).gesturePerformed((SlideEvent)event);
				}
			}
		}
	}
	
	public abstract void slide(Vector2 t);
	
	protected ArrayList<MTComponent> components = new ArrayList<MTComponent>();
}
