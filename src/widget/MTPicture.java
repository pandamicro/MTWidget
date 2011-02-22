package widget;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.border.LineBorder;
import mygeom.*;

/**
 * Un MTComponent d'image
 * <p>
 * L'image peut etre reorientee, agrandie, relocatee en utilisant la planche MultiTouch
 * 
 * @author  LING Huabin
 * @version 07/01/10
 * @see	BufferedImage
 * @see	File
 * @see	LineBorder
 */
public class MTPicture extends MTComponent{
	/**
	 * Constructeur selon la position et le fichier d'image en indiquant le possesseur
	 * @param file	Le fichier d'image
	 * @param x		Coordonné x
	 * @param y		Coordonné y
	 */
	public MTPicture(MTContainer container, File file, int x, int y){
		super(container);
		analyzer.setModeShape(true);
		analyzer.setModeSingleClick(true);
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setLocation(x, y);
		initPosition(new Point2(x,y), 0, img.getWidth(), img.getHeight());
		borderVisible = true;
	}
	/**
	 * Constructeur selon le fichier d'image en indiquant le possesseur
	 * @param file	Le fichier d'image
	 */
	public MTPicture(MTContainer container, File file){
		this(container, file, 0, 0);
	}
	/**
	 * Constructeur selon la position et la parcour du fichier d'image en indiquant le possesseur
	 * @param file	Le parcours du fichier d'image
	 * @param x		Coordonné x
	 * @param y		Coordonné y
	 */
	public MTPicture(MTContainer container, URL file, int x, int y){
		super(container);
		analyzer.setModeShape(true);
		analyzer.setModeSingleClick(true);
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLocation(x, y);
		initPosition(new Point2(x,y), 0, img.getWidth(), img.getHeight());
		borderVisible = true;
	}
	/**
	 * Constructeur selon la parcour du fichier d'image en indiquant le possesseur
	 * @param file	Le parcours du fichier d'image
	 */
	public MTPicture(MTContainer container, URL file){
		this(container, file, 0, 0);
	}
	/**
	 * Constructeur selon la position et une instance d'image en indiquant le possesseur
	 * @param img	L'image
	 * @param x		Coordonné x
	 * @param y		Coordonné y
	 */
	public MTPicture(MTContainer container, BufferedImage img, int x, int y){
		super(container);
		analyzer.setModeShape(true);
		analyzer.setModeSingleClick(true);
		this.img = img;
		setLocation(x, y);
		initPosition(new Point2(x,y), 0, img.getWidth(), img.getHeight());
		borderVisible = true;
	}
	/**
	 * Constructeur selon une instance d'image en indiquant le possesseur
	 * @param img	L'image
	 */
	public MTPicture(MTContainer container, BufferedImage img){
		this(container, img, 0, 0);
	}
	
	/**
	 * Faire visible ou invisible pour le cadre (qui est pour l'instant simplement un rectangle orange)
	 * @param visible	visibilite
	 */
	public void setBorderVisible(boolean visible){
		borderVisible = visible;
	}
	
	public BufferedImage getImage(){
		return img;
	}
	
	/**
	 * Dessiner l'image
	 */
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		AffineTransform save = g2.getTransform();
		g2.translate((int)getOBBX(), (int)getOBBY());
		//g2.translate(getWidth()/2, getHeight()/2);
		g2.rotate(currentOBB.getAngle());
		//g2.translate(-getWidth()/2, -getHeight()/2);
		
		g2.drawImage(img, 0, 0, 
					(int)currentOBB.getWidth(),
					(int)currentOBB.getHeight(),
					null);
		
		if(borderVisible){
			LineBorder border = new LineBorder(Color.ORANGE, 2, true);
			border.paintBorder(this, g, 0, 0, 
								(int)currentOBB.getWidth()+2,
								(int)currentOBB.getHeight()+2);
		}
		
		if(choosed){
			LineBorder chooseBorder = new LineBorder(Color.RED, 2, true);
			chooseBorder.paintBorder(this, g, -2, -2, 
									(int)currentOBB.getWidth()+4,
									(int)currentOBB.getHeight()+4);
		}
		
		g2.setTransform(save);
	}
	
	private BufferedImage img;
	private boolean borderVisible;
	private static final long serialVersionUID = 1L;
}