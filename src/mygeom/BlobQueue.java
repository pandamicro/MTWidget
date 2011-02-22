package mygeom;

import java.awt.Graphics2D;
import java.util.HashMap;

/**
 * Cette classe sert a garder les paths des doigts
 * Chaque component est lie avec une instance de BlobQueue,
 * il indique tous les doigts qui sont posses initialement sur le component
 * 
 * @author LING Huabin
 * @see widget.ComponentMap
 */
public class BlobQueue {
	public BlobQueue(){
		blobs = new HashMap<Integer, Path>(CAPACITY);
	}
	
	public void add(int id, Path p){
		blobs.put(Integer.valueOf(id), p);
	}
	
	public Path get(int id){
		return blobs.get(Integer.valueOf(id));
	}
	
	public Path atIndex(int index){
		if(!blobs.isEmpty()){
			values = blobs.values().toArray(new Path[0]);
			return values[index];
		}
		else return null;
	}
	
	public void remove(int id){
		blobs.remove(Integer.valueOf(id));
	}
	
	public int nbBlobs(){
		return blobs.size();
	}
	
	public boolean checkid(int id){
		if(blobs.containsKey(id))
			return true;
		else return false;
	}
	
	public void draw(Graphics2D g2){
		if(!blobs.isEmpty()){
			values = blobs.values().toArray(new Path[0]);
			for(int i = 0; i < values.length; i++){
				values[i].draw(g2);
			}
		}
	}
	
	private static final int CAPACITY = 20;
	private HashMap<Integer, Path> blobs;
	private Path[] values;
}
