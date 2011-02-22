package widget;

import java.awt.AWTEvent;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.event.EventListenerList;
import mygeom.*;
import event.*;
import gesture.*;

/**
 * La classe de base de tous les components<p>
 * Elle defini tous les evenements MultiTouch, elle permet d'ajouter les ecouteurs, 
 * d'activer et de desactiver tous les evenements MultiTouch.<p>
 * Elle permet de gerer le bounding box de component, 
 * l'initialisation, le changement, et l'application du changement.<p>
 * Elle contient aussi l'analyseur et l'instance de InternalGestureState
 * @author LING Huabin
 * @see GestureAnalyzer
 * @see InternalGestureState
 */
public abstract class MTComponent extends JComponent{
	public MTComponent(MTContainer container){
		gestureState = new InternalGestureState();
		analyzer = new GestureAnalyzer(this);
		this.container = container;
		choosed = false;
		enableListeners = true;
	}
	
	/**
	 * Obligatoir a realiser dans les classes qui heritent cette classe
	 */
	public abstract void draw(Graphics g);
	
	public GestureAnalyzer getAnalyzer(){
		return analyzer;
	}
	
	/**
	 * Determiner si le point p est dans la bounding box
	 * @param p	Le point concerne
	 * @return	Le resultat
	 */
	public boolean isInside(Point2 p){
		return currentOBB.contains(p);
	}
	
	public int getOBBX(){
		return (int)currentOBB.getOrigin().getX();
	}
	public int getOBBY(){
		return (int)currentOBB.getOrigin().getY();
	}
	public int getOBBWidth(){
		return (int)currentOBB.getWidth();
	}
	public int getOBBHeight(){
		return (int)currentOBB.getHeight();
	}
	public OBB getPosition(){
		return currentOBB;
	}
	public Point2 getInitOrigin(){
		return initialOBB.getOrigin();
	}
	public void move(Vector2 v){
		currentOBB.getOrigin().add(v);
	}
	public InternalGestureState getGestureState(){
		return gestureState;
	}
	
	public MTContainer getMTContainer(){
		return container;
	}
	
	public void setChoosed(boolean choosed){
		this.choosed = choosed;
	}
	
	public void setDimension(double width, double height){
		this.setSize((int)width, (int)height);
		initialOBB.setDimension(width, height);
		currentOBB.setDimension(width, height);
	}
	public void setOrigin(double x, double y){
		this.setLocation((int)x, (int)y);
		initialOBB.setOrigin(new Point2(x, y));
		currentOBB.setOrigin(new Point2(x, y));
	}
	
	//=====================================================
	
	public void initPosition(Point2 origin, double angle, double width, double height){
		initialOBB = new OBB(origin, angle, width, height);
		currentOBB = initialOBB.copy();
	}
	
	public void updatePosition(Vector2 t, double a, double k){
		currentOBB.update(initialOBB, t, a, k);
	}
	
	public void fixPosition(){
		initialOBB.setOrigin(currentOBB.getOrigin());
		initialOBB.setAngle(currentOBB.getAngle());
		initialOBB.setDimension(currentOBB.getWidth(), currentOBB.getHeight());
	}
	
	//=====================================================
	
	public void addDiscreteListener(DiscreteEventListener e){
		listenerList.add(DiscreteEventListener.class, e);
	}
	public void addSRTListener(SRTEventListener e){
		listenerList.add(SRTEventListener.class, e);
	}
	public void addGestureListener(GestureEventListener e){
		listenerList.add(GestureEventListener.class, e);
	}
	public void addSingleClickListener(SClickEventListener e){
		listenerList.add(SClickEventListener.class, e);
	}
	
	public void fireDiscretePerformed(AWTEvent event) {
		if(enableListeners){
			Object[] listeners = listenerList.getListenerList();
			for (int i = listeners.length-2; i >= 0; i -= 2) {
				if(listeners[i] == DiscreteEventListener.class) {
					((DiscreteEventListener)listeners[i+1]).gesturePerformed((DiscreteEvent)event);
				}
			}
		}
	}
	
	public void fireSRTPerformed(AWTEvent event) {
		if(enableListeners){
			Object[] listeners = listenerList.getListenerList();
			for (int i = listeners.length-2; i >= 0; i -= 2) {
				if(listeners[i]==SRTEventListener.class) {
					((SRTEventListener)listeners[i+1]).gesturePerformed((SRTEvent)event);
				}
			}
		}
	}
	
	public void fireGesturePerformed(AWTEvent event) {
		if(enableListeners){
			Object[] listeners = listenerList.getListenerList();
			for (int i = listeners.length-2; i >= 0; i -= 2) {
				if(listeners[i]==GestureEventListener.class) {
					((GestureEventListener)listeners[i+1]).gesturePerformed((GestureEvent)event);
				}
			}
		}
	}
	
	public void fireSingleClickPerformed(AWTEvent event){
		if(enableListeners){
			Object[] listeners = listenerList.getListenerList();
			for (int i = listeners.length-2; i >= 0; i -= 2) {
				if(listeners[i]==SClickEventListener.class) {
					((SClickEventListener)listeners[i+1]).gesturePerformed((SingleClickEvent)event);
				}
			}
		}
	}
	
	public void activeListeners(){
		enableListeners = true;
	}
	public void disactiveListeners(){
		enableListeners = false;
	}
	
	protected EventListenerList listeners;
	protected boolean enableListeners;
	protected GestureAnalyzer analyzer;
	protected MTContainer container;
	protected boolean choosed;
	protected OBB initialOBB;
	protected OBB currentOBB;
	protected InternalGestureState gestureState;
	private static final long serialVersionUID = 7308116910022385152L;
}