package tuio;

import TUIO.*;
import mygeom.Point2;
import widget.*;

public class MTedt implements TuioListener {

	private TuioClient client=null;
	private MTSurface surface; // la surface qui recevra les messages des curseurs.
	private Point2 p;
	private final static int PORT = 3333;
	
	private void initConnexion() {
		p = new Point2();
		client=new TuioClient(PORT);
		client.connect();
		if (!client.isConnected()) {
			System.exit(1);
		}
		System.out.println("connexion");
		
		// l’instance écoute le client TUIO
		client.addTuioListener(this);
	}
	
	public MTedt() {
		initConnexion();
	}
	
	public MTedt(MTSurface surface) {
		initConnexion();
		this.surface = surface;
	}
	
	public void stop() {
		client.disconnect();
		System.out.println("deconnexion");
	}
	
		
	/** Listeners **/
	
	public void addTuioObject(TuioObject tobj) {
	}

	public void updateTuioObject(TuioObject tobj) {
	}
	
	public void removeTuioObject(TuioObject tobj) {
	}

	public void addTuioCursor(TuioCursor tcur) {
		if(surface != null) {
			p.set(tcur.getX(), tcur.getY());
			surface.addCursor(tcur.getCursorID(), p);
		}
	}

	public void updateTuioCursor(TuioCursor tcur) {
		if(surface != null) {
			p.set(tcur.getX(), tcur.getY());
			surface.updateCursor(tcur.getCursorID(), p);
		}
	}
	
	public void removeTuioCursor(TuioCursor tcur) {
		if(surface != null) {
			p.set(tcur.getX(), tcur.getY());
			surface.removeCursor(tcur.getCursorID(), p);
		}
	}
	
	public void refresh(TuioTime frameTime) {
	}

}
