package mygeom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.util.ArrayList;

public class Path {
	public Path(){
		points = new ArrayList<Point2>(CAPACITY);
	}
	
	public Path(Point2 p){
		points = new ArrayList<Point2>(CAPACITY);
		add(p);
	}
	
	public void add(Point2 p){
		points.add(p);
	}
	
	public Point2 get(int index){
		return points.get(index);
	}
	
	public Point2 getLast(){
		return points.get(points.size()-1);
	}
	
	public Point2[] getAll(){
		return points.toArray(new Point2[0]);
	}
	
	public int size(){
		return points.size();
	}
	
	public void clear(){
		points.clear();
	}
	
	public void draw(Graphics2D g2){
		if(!points.isEmpty()) {
			Point2 center = points.get(points.size()-1);
			currPt = new Ellipse2D.Double(center.getX()-RADIUS-0.5, 
										  center.getY()-RADIUS-0.5, 
										  2*RADIUS, 2*RADIUS);
			g2.setColor(ELLIPSECOLOR);
			g2.draw(currPt);
			g2.setColor(LINECOLOR);
			g2.drawLine((int)center.getX(), (int)(center.getY())-HALFLINE, 
						(int)center.getX(), (int)(center.getY())+HALFLINE);
			g2.drawLine((int)center.getX()-HALFLINE, (int)center.getY(), 
						(int)center.getX()+HALFLINE, (int)center.getY());
		}
	}
	
	private static final int CAPACITY = 50;
	private static final int RADIUS = 9;
	private static final int HALFLINE = 3;
	private static final Color ELLIPSECOLOR = Color.GRAY.darker();
	private static final Color LINECOLOR = Color.BLUE.brighter();
	private ArrayList<Point2> points; 
	private Ellipse2D currPt;
}
