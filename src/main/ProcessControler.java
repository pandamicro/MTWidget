package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.JFrame;
import event.GestureEvent;
import event.GestureEventListener;
import event.SClickEventListener;
import event.SingleClickEvent;
import widget.*;

/**
 * Classe utilisant "Singleton Pattern" pour controller la processus du logiciel
 * <p>
 * Elle sert a importer tous les ressources de l'Album (dans dossier d'utilisateur/RessourceAlbum, cree automatiquement), 
 * d'initialiser le menu de programme 
 * et d'entrer dans l'editeur de l'Album depuis le menu du programme.
 * 
 * @author LING Huabin
 */
public class ProcessControler {
	public static void initialSurface(String title, int width, int height){
		frame = new MTFrame(title, width, height);

		surface = new MTSurface(ClassLoader.getSystemResource("res/Fond d'ecran/Evening Reflections.jpg"));
		surface.setLocation(0,0);
		surface.setSize(width, height);
		frame.getContentPane().add(surface);
		
		//Creer le dossier de ressource
		if(!resDir.exists() || !resDir.isDirectory()){
			resDir.mkdir();
		}
		//lire tous les ressources
		typeFilter = new FilenameFilter(){
			public boolean accept(File dir, String name) {
				if(name.endsWith("jpg") || name.endsWith("png") || 
				   name.endsWith("tiff") || name.endsWith("gif") || 
				   name.endsWith("jpeg"))
					return true;
				else return false;
			}
		};
		res = resDir.listFiles(typeFilter);
		
		initMenu();
	}
	
	/**
	 * Initialisation du programme, commence par le Menu, par contre, seule la fonction nouveau Album est fournie.
	 */
	public static void initMenu(){
		newIcon = new MTPicture(surface.getMenu(), ClassLoader.getSystemResource("res/menu/New.png"));
		viewIcon = new MTPicture(surface.getMenu(), ClassLoader.getSystemResource("res/menu/ViewAlbum.png"));
		newIcon.addSingleClickListener(new SClickEventListener(){
			public void gesturePerformed(SingleClickEvent evt) {
				surface.reinitMenu(MTChooser.STATE_CHOOSER);
				initAlbum();
			}
		});
		surface.addMenuElement(newIcon);
		surface.addMenuElement(viewIcon);
	}
	
	/**
	 * Initialisation de un nouveau Album
	 * <p>
	 * Remplir la barre menu avec tous les ressources dans le dossier RessourceAlbum
	 */
	public static void initAlbum(){
		MTPicture pic;
		for(int i = 0; i < res.length; i++){
			pic = new MTPicture(surface.getMenu(), res[i]);
			surface.addMenuElement(pic);
		}
		
		surface.getMenu().addGestureListener(new GestureEventListener(){
			public void gesturePerformed(GestureEvent evt) {
				if(evt.getRecognizeResult().getTemplate().getName() != "circle")
					return;
				res = resDir.listFiles(typeFilter);
				surface.getMenu().removeAll();
				MTPicture pic;
				for(int i = 0; i < res.length; i++){
					pic = new MTPicture(surface.getMenu(), res[i]);
					surface.addMenuElement(pic);
				}
			}
		});
		surface.getMenu().addGestureListener(new GestureEventListener(){
			public void gesturePerformed(GestureEvent evt) {
				if(evt.getRecognizeResult().getTemplate().getName() != "delete")
					return;
				if(surface.getMenu().focusOn().getClass() == MTPicture.class){
					surface.getMenu().remove(surface.getMenu().focusOn());
				}
			}
		});
	}
	
	public static File[] getRes(){
		return res;
	}

	public static MTFrame frame;
	public static MTSurface surface;
	/**
	 * Le parcour pour stocker toutes les ressources d'Album: dossier principal d'utilisateur/RessourceAlbum, creation automatique
	 */
	public static final String RES_PATH = System.getProperty("user.home") + System.getProperty("file.separator") + "RessourceAlbum";
	/**
	 * Le dossier des ressources
	 */
	private static File resDir = new File(RES_PATH);
	/**
	 * Les ressources photos
	 */
	private static File[] res;
	/**
	 * Le filtre de type de fichier, les types ".jpg" ".png" ".jpeg" ".tiff" ".gif" sont acceptes
	 */
	private static FilenameFilter typeFilter;
	/**
	 * Le button de menu pour "nouveau album" et "consulter album", "consulter album" sert a rien pour l'instant
	 */
	private static MTPicture newIcon, viewIcon;
}

class MTFrame extends JFrame{
	public MTFrame(String title, int width, int height){
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		
		setSize(width,height);
		setLocation((screenSize.width-width)/2, (screenSize.height-height)/2);
		setTitle(title);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private static final long serialVersionUID = 6832728881912965999L;
}