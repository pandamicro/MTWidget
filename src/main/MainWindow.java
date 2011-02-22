package main;

import javax.swing.SwingUtilities;

/**
 * @author LING Huabin
 */
public class MainWindow {
	public static void shoWindow(){
		ProcessControler.initialSurface("MultiTouch Surface", WINDOWWIDTH, WINDOWHEIGHT);
	}

	public static final int WINDOWWIDTH = 800;
	public static final int WINDOWHEIGHT = 650;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				shoWindow();
			}
		});
	}

}
