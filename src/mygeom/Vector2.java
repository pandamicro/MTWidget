package mygeom;

public class Vector2 extends Tuple2 {
	
	public Vector2() {
		super();
	}
	
	public Vector2(Vector2 a) {
		super(a);
	}
	
	public Vector2(double x,double y) {
		super(x,y);
	}
	
	public Vector2 getOppose(){
		return new Vector2(-getX(), -getY());
	}
}
