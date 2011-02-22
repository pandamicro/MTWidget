package widget;

import gesture.GestureAnalyzer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;
import mygeom.*;

/**
 * Cette classe sert a etablir des correspondances entre les doigts et les components<p>
 * si une trace d'utilisateur commence dans un component, 
 * on defini que cette trace est contient par ce component.
 * 
 * @author LING Huabin
 * @see HashMap
 * @see	BlobQueue
 * @see MTComponent
 */
public class ComponentMap {
	
	/**
	 * Ajouter un nouveau blob a MTComponent 'c'<p>
	 * Si le map contient deja ce component, on ajoute le blob directement. 
	 * Sinon, on construit un nouveau et ajoute le blob. 
	 * A la fin, appeller la fonction 'analyze' pour analyser cette action
	 * 
	 * @param c		Le component qui contient ce nouveau blob
	 * @param id	L'identifiant du blob
	 * @param p		Le point centre du blob
	 */
	public void addBlob(MTComponent c, int id, Point2 p){
		if(!cMap.containsKey(c)){
			BlobQueue bq = new BlobQueue();
			bq.add(id, new Path(p));
			cMap.put(c, bq);
		}
		else {
			cMap.get(c).add(id, new Path(p));
		}
		c.getAnalyzer().analyze(cMap.get(c), GestureAnalyzer.ST_ADD, id, p);
	}
	
	/**
	 * Mettre a jour le BlobQueue(le path du blob) par un nouveau point de path<p>
	 * On cherche d'abord dans le cMap quel component contient ce blob 
	 * et ajoute le nouveau point dans le path. 
	 * A la fin, appeller la fonction 'analyze' pour analyser cette action
	 * 
	 * @param id	L'identifiant du blob
	 * @param p		Le point centre du blob
	 */
	public void updateBlob(int id, Point2 p){
		if(!cMap.isEmpty()){
			values = cMap.values().toArray(new BlobQueue[0]);
			keys = cMap.keySet().toArray(new MTComponent[0]);
			for(int i = 0; i < values.length; i++){
				if(values[i].checkid(id)){
					values[i].get(id).add(p);
					keys[i].getAnalyzer().analyze(values[i], GestureAnalyzer.ST_UPDATE, id, p);
				}
			}
		}
	}

	/**
	 * Effacer un path<p>
	 * On cherche dans le cMap quel component contient ce blob et l'efface. 
	 * A la fin, appeller la fonction 'analyze' pour analyser cette action
	 * 
	 * @param id	L'identifiant du blob
	 * @param p		Le point centre du blob
	 */
	public void removeBlob(int id, Point2 p){
		if(!cMap.isEmpty()){
			values = cMap.values().toArray(new BlobQueue[0]);
			keys = cMap.keySet().toArray(new MTComponent[0]);
			for(int i = 0; i < values.length; i++){
				if(values[i].checkid(id)){
					keys[i].getAnalyzer().analyze(values[i], GestureAnalyzer.ST_REMOVE, id, p);
					values[i].remove(id);
				}
			}
		}
	}
	
	/**
	 * Dessiner tous les doigts concerne tous les components
	 */
	public void drawBlobs(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		if(!cMap.isEmpty()){
			values = cMap.values().toArray(new BlobQueue[0]);
			for(int i = 0; i < values.length; i++){
				values[i].draw(g2);
			}
		}
	}

	/**
	 * Les correspondances sont stockees dans un HashMap
	 */
	private HashMap<MTComponent, BlobQueue> cMap = new HashMap<MTComponent, BlobQueue>();
	private BlobQueue[] values;
	private MTComponent[] keys;
}
