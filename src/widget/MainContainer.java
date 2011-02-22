package widget;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import event.SClickEventListener;
import event.SingleClickEvent;
import mygeom.*;

/**
 * Le container principal du programme, il represente la surface principale pour ranger les photos
 * <p>
 * Il y en existe plusieurs instances dans MTSurface, 
 * ils servent a realiser plusieurs pages dans l'Album. 
 * Chaque instance contient des outils comme button "modify" (pour changer a mode modifier) et les components.
 * <p>
 * Il y a deux mode de fonctionnement, mode Modify et mode Resize<p> 
 * Dans la mode Resize, il peut reagir les evenements OneDollar pour plusieurs fonctionnement: <p>
 * 1. Ajouter une image<p>
 * 2. Supprimer une image<p>
 * 3. Changer a la page suivante ou precedente<p>
 * Dans la mode Modify, l'utilisateur peut dessiner sur le container, 
 * et les traces seraient enregistres sur cette page. 
 * En ce moment, tous les autres manipulations seront ignores sauf le button pour retourner a la mode Resize<p>
 * 
 * @author  LING Huabin
 * @version 07/01/10
 * @see	    MTComponent
 * @see		ArrayList
 */

public class MainContainer extends MTContainer{
	/**
     * Constructeur de MTContainer<p>
     * 1. Localiser et ajuster la taille<p>
     * 2. Ajouter les deux buttons pour deux modes<p>
     * 3. Initialiser a la mode Resize
     */
	public MainContainer(int x, int y, int width, int height){
		super();
		setLocation(x, y);
		setPreferredSize(new Dimension(width, height));
		initPosition(new Point2(x, y), 0, width, height);
		
		modifyBut = new MTPicture(this, 
				ClassLoader.getSystemResource("res/menu/Modify.png"), 
				MTSurface.WIDTH - 300, 20);
		resizeBut = new MTPicture(this, 
				ClassLoader.getSystemResource("res/menu/Resize.png"), 
				MTSurface.WIDTH - 150, 20);
		addOutil(modifyBut);
		addOutil(resizeBut);
		modifyBut.addSingleClickListener(new SClickEventListener(){
			public void gesturePerformed(SingleClickEvent evt) {
				analyzer.setModeModify(true);
				for(MTComponent i: components){
					i.getAnalyzer().desactiveAll();
				}
				resizeBut.getAnalyzer().reactive();
				setMode(MODE_MODIFY);
			}
		});
		resizeBut.addSingleClickListener(new SClickEventListener(){
			public void gesturePerformed(SingleClickEvent evt) {
				analyzer.setModeModify(false);
				for(MTComponent i: components){
					i.getAnalyzer().reactive();
				}
				setMode(MODE_RESIZE);
			}
		});
		
		mode = MODE_RESIZE;
	}
	
	/**
	 * Mettre en avance un component
	 * 
	 * @param c	Le nouveau component en avance
	 */
	public void select(MTComponent c){
		int i;
		if(!components.contains(c))
			return;
		for(i = components.indexOf(c)+1; i < components.size(); i++){
			components.set(i-1, components.get(i));
		}
		components.set(i-1, c);
	}
	
	/**
	 * Ajouter un button d'outil, il sera localise automatiquement
	 * @param outil	L'image du button
	 */
	public void addOutil(MTPicture outil){
		outil.initPosition(new Point2(getOBBX() + OUTIL_X + outils.size()*(OUTIL_SIZE+OUTIL_INTERVAL), getOBBY() + OUTIL_Y), 
						   0, OUTIL_SIZE, OUTIL_SIZE);
		outil.setBorderVisible(false);
		outil.getAnalyzer().setModeShape(false);
		components.add(outil);
		outils.add(outil);
	}
	
	/**
	 * Commencer une nouvelle trace sur cette page
	 * @param p	Le premier point de cette trace
	 */
	public void newPolygon(Point2 p){
		currPoly = new GeneralPath();
		currPoly.moveTo(p.getX(), p.getY());
	}
	/**
	 * Update la trace
	 * @param p	Le nouveau point pour ajouter
	 */
	public void updatePolygon(Point2 p){
		if(currPoly != null)
			currPoly.lineTo(p.getX(), p.getY());
	}
	/**
	 * La trace termine, l'ajouter sur cette page
	 */
	public void addPolygon(){
		if(currPoly != null){
			polygons.add((GeneralPath)currPoly.clone());
			currPoly = null;
		}
	}
	/**
	 * Supprimer une trace
	 * @param p	La trace a supprimer
	 */
	public void removePolgon(GeneralPath p){
		if(!polygons.contains(p)) return;
		polygons.remove(p);
		p = null;
	}

	/**
	 * Changer a une nouvelle mode
	 * @param m	La mode a changer
	 */
	public void setMode(int m){
		mode = m;
	}
	/**
	 * @return	La mode courante
	 */
	public int getMode(){
		return mode;
	}
	
	/**
	 * Determiner un point sur la planche MultiTouch est relativement sur quel component de cette page
	 */
	public MTComponent whichIs(Point2 p){
		if(mode == MODE_MODIFY){
			if(resizeBut.isInside(p)) return resizeBut;
			else return this;
		}
		else return super.whichIs(p);
	}
	
	/**
	 * Dessiner tous les components dans la liste, tous les outils et tous les traces enregistrees<p>
	 * Dans la mode Modify, dessiner en plus une masque d'Alpha et la trace d'utilisateur
	 */
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		AffineTransform save = g2.getTransform();
		g2.translate(getOBBX(), getOBBY());
		for(MTComponent i: components){
			i.draw(g);
		}
		
		g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
		g2.setColor(POLY_COLOR);
		for(GeneralPath i: polygons){
			g2.draw(i);
		}
		if(mode == MODE_MODIFY){
			g.setColor(MTSurface.ALPHAMASK);
			g.fillRect(0, 0, MTSurface.WIDTH, MTSurface.CONTAINERHEIGHT);
			if(currPoly != null) {
				g2.setColor(POLY_COLOR);
				g2.draw(currPoly);
			}
		}
		g2.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		for(MTComponent i: outils){
			i.draw(g);
		}
		g2.setTransform(save);
	}

	/**
	 * Taille de button, 50*50
	 */
	public static final int OUTIL_SIZE = 50;
	/**
	 * Location X du premier button
	 */
	public static final int OUTIL_X = MTSurface.WIDTH - 200;
	/**
	 * Location Y des buttons
	 */
	public static final int OUTIL_Y = 20;
	/**
	 * La distance entre deux buttons
	 */
	public static final int OUTIL_INTERVAL = 10;
	/**
	 * La liste des outils (buttons)
	 */
	private ArrayList<MTComponent> outils = new ArrayList<MTComponent>(5);
	/**
	 * La trace courante
	 */
	private GeneralPath currPoly;
	/**
	 * La liste des traces
	 */
	private ArrayList<GeneralPath> polygons = new ArrayList<GeneralPath>();
	private MTPicture modifyBut, resizeBut;
	private int mode;
	public static final int MODE_RESIZE = 1;
	public static final int MODE_MODIFY = 2;
	/**
	 * La couleur de la trace
	 */
	private static final Color POLY_COLOR = new Color(0x97F315); //8ED6E6	F9958D	00CCFF	97F315	32F4C9
	private static final long serialVersionUID = 3337456719993280782L;

	@Override
	public void slide(Vector2 t) {
		
	}
}
