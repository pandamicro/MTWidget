package widget;

import javax.imageio.ImageIO;
import javax.swing.*;
import event.*;
import listener.*;
import main.*;
import mygeom.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;
import tuio.*;

/**
 * Cette classe est l'interface de ce programme<p>
 * Il est compose d'une liste de MainContainer (differente pages) 
 * et un MTChooser comme menu et le selecteur des images<p>
 * @author LING Huabin
 * @see MTChooser
 * @see MainContainer
 * @see MTComponent
 * @see ComponentMap
 */
public class MTSurface extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur<p>
	 * 1. Initialiser le ComponentMap et connecter avec le serveur tuio<p>
	 * 2. Initialiser tous les pages d'Album et l'instance de MTChooser<p>
	 * 3. Ajouter les ecouteurs aux pages et le selecteur
	 */
	public MTSurface(URL fondfile) {
		super();
		if(edt == null)
			edt = new MTedt(this);
		for(int i = 0; i < pages.length; i++){
			pages[i] = new MainContainer(0, 0, WIDTH, CONTAINERHEIGHT);
		}
		mtChooser = new MTChooser(MTChooser.MAX, 0, CHOOSERY, WIDTH, CHOOSERHEIGHT, MTChooser.STATE_MENU);
		mtCompMap = new ComponentMap();
		
		try {
			fond = ImageIO.read(fondfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//ajouter les listener de Gesture evenement: Geste d'un doigt dehors des image
		for(int i = 0; i < pages.length; i++){
			pages[i].addGestureListener(new GestureListener());
			pages[i].addGestureListener(new NewPicListener());
			pages[i].addGestureListener(new DeleteListener());
			pages[i].addGestureListener(new NextPageListener());
			pages[i].addGestureListener(new PrevPageListener());
		}
		mtChooser.addGestureListener(new GestureListener());
		
		currPage = 0;
		newPage = -1;
	}
	
	/**
	 * Ajouter une image a la page courante en indiquant la position, 
	 * cette fonction utilisera apres "addPicture(MTPicture pic)"
	 * @param file	Le fichier d'image
	 * @param x
	 * @param y
	 */
	public void addPicture(File file, int x, int y){
		MTPicture pic = new MTPicture(getMainContainer(), file, x, y);
		addPicture(pic);
	}
	/**
	 * Ajouter une image a la page courante et initialiser tous les ecouteurs des evenements
	 * @param pic
	 */
	public void addPicture(MTPicture pic){
		//ajouter les listener de discrete evenement
		pic.addDiscreteListener(new DiscreteListener());
		//ajouter les listener de SRT evenement: Mouvement de doigt
		pic.addSRTListener(new SRTListener());
		//ajouter les listener de SingleClick evenement
		pic.addSingleClickListener(new SelectListener());
		//ajouter les images components dans surface
		add(pic);
	}
	/**
	 * Ajouter un component a la page courante<p>
	 * Il n'est pas encourage d'utiliser cette fonction directement, 
	 * il vaut mieux d'utiliser "addPicture()"
	 * @param c	Component concerne
	 */
	public void add(MTComponent c){
		pages[currPage].addComponent(c);
	}
	/**
	 * Ajouter un element dans le menu
	 * @param c	Component concerne
	 */
	public void addMenuElement(MTComponent c){
		mtChooser.addComponent(c);
	}
	
	public MainContainer getMainContainer(){
		return pages[currPage];
	}
	public MTChooser getMenu(){
		return mtChooser;
	}
	public void reinitMenu(int state){
		mtChooser = new MTChooser(MTChooser.MAX, 0, CHOOSERY, WIDTH, CHOOSERHEIGHT, state);
		mtChooser.addGestureListener(new GestureListener());
		if(state == MTChooser.STATE_CHOOSER){
			mtChooser.addSlideListener(new SlideEventListener(){
				public void gesturePerformed(SlideEvent evt) {
					mtChooser.slide(evt.getOffset());
				}
			});
		}
	}

	/**
	 * Changer a la page suivante avec une animation de mouvement
	 */
	public void nextPage(){
		Timer timer = new Timer();
		timer.schedule(new AnimeNextPage(), 0);
	}
	/**
	 * Changer a la page precedente avec une animation de mouvement
	 */
	public void prevPage(){
		Timer timer = new Timer();
		timer.schedule(new AnimePrevPage(), 0);
	}
	
	/**
	 * Dessiner le fonds, le mask d'alpha et tous les components dans les containers
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(fond, 0, 0, null);
		g.setColor(ALPHAMASK);
		g.fillRect(0, 0, WIDTH, CONTAINERHEIGHT+CHOOSERHEIGHT);
		pages[currPage].draw(g);
		if(newPage >= 0)
			pages[newPage].draw(g);
		mtChooser.draw(g);
		mtCompMap.drawBlobs(g);
	}
	
	/**
	 * Quand il y a un nouveau blob apparait dans le serveur Tuio
	 * cette fonction est appellée pour réagir dans l'interface de client
	 * d'abord, transferer la coordonnée pour adapter à notre coordonnée de la fênetre
	 * detecter ce nouveau blob apparait sur quel component
	 * ajouter dans le ComponentMap
	 * redessiner tous
	 * 
	 * @param id	L'identifiant de blob
	 * @param p		Le point apparu du blob
	 */
	public void addCursor(int id, Point2 p){
		p = Point2.transfer(p);
		MTComponent c;
		if(mtChooser.isInside(p)) {
			c = mtChooser.whichIs(p);
			mtCompMap.addBlob(mtChooser, id, p);
		}
		else {
			c = pages[currPage].whichIs(p);
		}
		mtCompMap.addBlob(c, id, p);
		this.repaint();
	}
	
	/**
	 * Cette fonction est appellée quand le blob est renouvé du côté serveur
	 * Mettre à jour dans le ComponentMap
	 * Redessiner tous
	 * 
	 * @param id	L'identifiant de blob
	 * @param p		Le nouveau point du blob
	 */
	public void updateCursor(int id, Point2 p){
		p = Point2.transfer(p);
		mtCompMap.updateBlob(id, p);
		this.repaint();
	}
	
	/**
	 * Cette fonction est appellée quand le blob effacé du côté serveur
	 * Effacer le dans le ComponentMap
	 * Redessiner tous
	 * 
	 * @param id	L'identifiant de blob
	 * @param p		Le point termine du blob
	 */
	public void removeCursor(int id, Point2 p){
		p = Point2.transfer(p);
		mtCompMap.removeBlob(id, p);
		this.repaint();
	}
	/**
	 * La liste des pages
	 */
	private MainContainer[] pages = new MainContainer[10];
	/**
	 * La page courante
	 */
	private int currPage;
	/**
	 * La nouvelle page, il sert a faire l'animation
	 */
	private int newPage;
	/**
	 * Le menu et le selecteur des images
	 */
	private MTChooser mtChooser;
	/**
	 * Les correspondances des components et les doigts
	 */
	private ComponentMap mtCompMap;
	/**
	 * La connection TUIO
	 */
	private MTedt edt;
	/**
	 * Le fonds d'ecran
	 */
	private Image fond;
	public static final Color ALPHAMASK = new Color(0xa0ffffff, true);
	public static final int WIDTH = MainWindow.WINDOWWIDTH;
	public static final int CONTAINERHEIGHT = 500;
	public static final int CHOOSERHEIGHT = 150;
	public static final int CHOOSERY = CONTAINERHEIGHT;
	
	//==========================Listener==========================
	/**
	 * L'ecouteur d'evenement OneDollar<p>
	 * Quand l'utilisateur dessine un rectangle, faire apparaitre une nouvelle image dans le rectangle
	 * @author LING Huabin
	 */
	class NewPicListener implements GestureEventListener{
		public void gesturePerformed(GestureEvent evt) {
			Random r = new Random();
			if(evt.getRecognizeResult().getTemplate().getName() != "rectangle")
				return;
			OBB obb = new InertiaMatrix(evt.getPath()).getOBB();
			MTPicture pic;
			if(mtChooser.focusOn().getClass() == MTPicture.class){
				pic = new MTPicture(getMainContainer(), 
						((MTPicture)mtChooser.focusOn()).getImage());
			}
			else {
				pic = new MTPicture(getMainContainer(), 
						ProcessControler.getRes()[r.nextInt(ProcessControler.getRes().length-1)]);
			}
			addPicture(pic);
			pic.initPosition(obb.getOrigin(), obb.getAngle(), obb.getWidth(), obb.getHeight());
		}
	}
	/**
	 * L'ecouteur d'evenement OneDollar<p>
	 * Si l'utilisateur dessine 'delete', supprime l'image selectionnee
	 * @author LING Huabin
	 */
	class DeleteListener implements GestureEventListener{
		public void gesturePerformed(GestureEvent evt) {
			if(evt.getRecognizeResult().getTemplate().getName() != "delete")
				return;
			if(getMainContainer().focusOn().getClass() == MTPicture.class){
				getMainContainer().remove(getMainContainer().focusOn());
			}
		}
	}
	/**
	 * L'ecouteur d'evenement OneDollar<p>
	 * Si l'utilisateur dessine 'caret', change a la page suivante
	 * @author LING Huabin
	 */
	class NextPageListener implements GestureEventListener{
		public void gesturePerformed(GestureEvent evt) {
			if(evt.getRecognizeResult().getTemplate().getName() != "caret")
				return;
			nextPage();
		}
	}
	/**
	 * L'ecouteur d'evenement OneDollar<p>
	 * Si l'utilisateur dessine 'v', change a la page precedente
	 * @author LING Huabin
	 */
	class PrevPageListener implements GestureEventListener{
		public void gesturePerformed(GestureEvent evt) {
			if(evt.getRecognizeResult().getTemplate().getName() != "v")
				return;
			prevPage();
		}
	}
	
	class GestureListener implements GestureEventListener{
		public void gesturePerformed(GestureEvent evt) {
			System.out.println("Source: " + evt.getSource().getClass() + ", "
					+ evt.getRecognizeResult().getTemplate().getName());
		}
	}
	
	//============================Animation==================================
	/**
	 * L'animation pour changer a la page suivante
	 * @author LING Huabin
	 */
	class AnimeNextPage extends TimerTask{
		public void run() {
			if(currPage < pages.length-1)
				newPage = currPage + 1;
			else newPage = 0;
			pages[newPage].setOrigin(WIDTH, 0);
			
			pages[currPage].disactiveListeners();
			Vector2 dis = new Vector2(-5, 0);
			while(pages[currPage].getOBBX() > -WIDTH){
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pages[currPage].move(dis);
				pages[newPage].move(dis);
				repaint();
			}
			pages[currPage].setOrigin(0, 0);
			pages[currPage].activeListeners();
			
			currPage = newPage;
			newPage = -1;
		}
	}
	/**
	 * L'animation pour changer a la page precedente
	 * @author LING Huabin
	 */
	class AnimePrevPage extends TimerTask{
		public void run() {
			if(currPage > 0)
				newPage = currPage-1;
			else newPage = pages.length-1;
			pages[newPage].setOrigin(-WIDTH, 0);
			
			pages[currPage].disactiveListeners();
			Vector2 dis = new Vector2(5, 0);
			while(pages[currPage].getOBBX() < WIDTH){
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pages[currPage].move(dis);
				pages[newPage].move(dis);
				repaint();
			}
			pages[currPage].setOrigin(0, 0);
			pages[currPage].activeListeners();
			
			currPage = newPage;
			newPage = -1;
		}
	}
}