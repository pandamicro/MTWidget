package widget;

import java.awt.Color;
import java.awt.Graphics;
import listener.SelectListener;
import main.MainWindow;
import mygeom.Point2;
import mygeom.Vector2;

/**
 * Cette classe represente le Menu et le selecteur des images<p>
 * Il peut aussi contient les components comme MainContainer, 
 * par contre, il peut pas appliquer l'analyseur Resize, 
 * parce que les elements du menu ne peuvent pas etre modifies.<p>
 * Mais, il applique l'analyseur Slide, qui permet de glisser les images 
 * pour pouvoir choisir parmis tous les ressources.
 * @author LING Huabin
 * @version 07/01/10
 */
public class MTChooser extends MTContainer {
	public MTChooser(int column, int x, int y, int width, int height, int state) {
		super();
		analyzer.setModeSlide(true);
		
		this.column = column;
		this.state = state;
		count = 0;
		setLocation(x, y);
		setSize(width, height);
		initPosition(new Point2(x, y), 0, width, height);
	}
	
	/**
	 * Ajouter menu element, la difference entre celui la et celui de MTContainer, 
	 * c'est qu'il faut calculer les positions correctes des elements. 
	 * Les elements sont organises de certaine facon, ici, il y en a deux facon de rangement: 
	 * Une ligne ou plusieurs lignes, dans le programme, on utilise seulement le rangement d'une ligne
	 */
	public void addComponent(MTComponent c){
		super.addComponent(c);
		if(c.getClass() == MTPicture.class)
			((MTPicture)c).setBorderVisible(false);
		c.getAnalyzer().setModeShape(false);
		c.addSingleClickListener(new SelectListener());
		
		//redefinir la location et la dimension
		int offsetX = 0, offsetY = 0;
		//calcul la dimension
		if(c.getOBBWidth() > c.getOBBHeight()){
			if(c.getOBBWidth() <= BORDER){
				offsetX += (BORDER-c.getOBBWidth())/2;
				offsetY += (BORDER-c.getOBBHeight())/2;
			}
			else {
				c.setDimension(BORDER, c.getOBBHeight() * (BORDER/c.getOBBWidth()));
				offsetY += (BORDER-c.getOBBHeight())/2;
			}
		}
		else{
			if(c.getOBBHeight() <= BORDER){
				offsetX += (BORDER-c.getOBBWidth())/2;
				offsetY += (BORDER-c.getOBBHeight())/2;
			}
			else {
				c.setDimension(c.getOBBWidth() * (BORDER/c.getOBBHeight()), BORDER);
				offsetX += (BORDER-c.getOBBWidth())/2;
			}
		}
		
		//calcul le point origin
		if(state == STATE_MENU){
			offsetX += (MainWindow.WINDOWWIDTH - (count+1)*UNITLENGTH)/2;
			offsetX += (UNITLENGTH - BORDER)/2;
			offsetY += (UNITLENGTH - BORDER)/2;
			for(int i = 0; i < components.size(); i++){
				components.get(i).setOrigin(getX() + offsetX, getY() + offsetY);
				offsetX += UNITLENGTH;
			}
		}
		else{
			offsetX += (UNITLENGTH - BORDER)/2;
			offsetY += (UNITLENGTH - BORDER)/2;
			offsetX += UNITLENGTH * (count%column);
			offsetY += UNITLENGTH * (count/column);
			components.get(count).setOrigin(getX() + offsetX, getY() + offsetY);
		}
		count++;
	}
	
	public void removeAll(){
		super.removeAll();
		count = 0;
	}
	public void remove(MTComponent c){
		super.remove(c);
		count--;
	}
	
	/**
	 * Glisser tous les elements<p>
	 * En fait, il applique un mouvement par apport au parametre 't' a chaque element, 
	 * de cette maniere, le menu peut etre glisse
	 */
	public void slide(Vector2 t){
		MTComponent first = components.get(0);
		MTComponent last = components.get(components.size()-1);
		if(getHeight() == UNITLENGTH)
			t.setY(0);
		else if(last.getOBBY() <= getOBBY() && t.getY() < 0)
			t.setY(0);
		else if(first.getOBBY() >= getOBBY()+getHeight()/2 && t.getY() > 0)
			t.setY(0);
		if(getWidth() == UNITLENGTH)
			t.setX(0);
		else if(last.getOBBX() <= getOBBX() && t.getX() < 0)
			t.setX(0);
		else if(first.getOBBX() >= getOBBX()+getWidth()/2 && t.getX() > 0)
			t.setX(0);
		
		for(int i = 0; i < components.size(); i++){
			components.get(i).move(t);
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(BACK);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(BACK.darker());
		g.drawLine(getX(), getY(), getX()+getWidth(), getY());
		
		for(int i = 0; i < components.size(); i++){
			components.get(i).draw(g);
		}
	}

	/**
	 * La couleur de fonds de menu
	 */
	public static final Color BACK = new Color(0xE0E0E0);
	private static final long serialVersionUID = 7038024997328686643L;
	private int column, state, count;
	public static final int MAX = 100;
	/**
	 * Dans cet etat, il y a seulement une ligne
	 */
	public static final int STATE_MENU = 1;
	/**
	 * Dans cet etat, il permet d'avoir plusieurs lignes
	 */
	public static final int STATE_CHOOSER = 2;
	public static final int UNITLENGTH = MTSurface.CHOOSERHEIGHT;
	public static final double BORDER = (UNITLENGTH * 0.7);
}