package mygeom;

public class Box {
	private double x, y;
	private int width, height;
	
	public Box(){
		this.x = 0;
		this.y = 0;
		this.height = 0;
		this.width = 0;
	}

	public Box(double x, double y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public Point2 getOrigin(){
		return new Point2(x, y);
	}
	
	public Point2[] getSommets(){
		Point2[] sommets = new Point2[4];
		sommets[0] = new Point2(x, y);
		sommets[1] = new Point2(x+width, y);
		sommets[2] = new Point2(x, y+height);
		sommets[3] = new Point2(x+width, y+height);
		return sommets;
	}
	
}
