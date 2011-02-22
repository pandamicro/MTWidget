package gesture;

import mygeom.BlobQueue;
import mygeom.OBB;
import mygeom.Path;
import mygeom.Point2;
import mygeom.Vector2;
import oneDollarRecognizer.*;
import widget.MTComponent;
import widget.MTChooser;
import widget.MainContainer;
import event.*;

/**
 * Cette classe definir les analyseurs du MTComponent
 * <p>
 * Il existe 5 analyseurs qui sont distingues par mode: <p>
 * 1. "One Dollar" analyseur - Mode OneDollar<p>
 * 2. Single Click analyseur - Mode SingleClick<p>
 * 3. Gestion analyseur - Mode Shape<p>
 * 4. Modifier analyseur - Mode Modify<p>
 * 5. Glissement analyseur - Mode Slide<p>
 * <p>
 * Chaque MTComponent garde son propre instance de GestureAnalyzer,
 * il peut choisir aux quels modes qu'il veut appliquer.
 * Par contre, seule les instances de MainContainer peut appliquer la mode "Modifiy", 
 * de plus, la mode "Shape" ne peut pas etre applique ensemble avec mode "OneDollar" et "Slide".
 * Les analyseurs appliques peuvent etre actives ou desactives lors de l'execution du programme.
 * <p>
 * L'analyseur ne s'occupe pas des changement au MTComponent,
 * Tout simplement, il l'informe comme les evenements qui sont definis dans le package "event",
 * tous les evenements peuvent etre ecoutes par MTComponent
 * <p>
 * L'analyseur recois les evenements des doigts depuis "widget.ComponentMap"
 * Trois evenement du doigt existent: ajoute, enleve, update
 * 
 * @author LING Huabin
 * @version 05/01/2010
 */
public class GestureAnalyzer {
	/**
	 * Les analyseurs sont initialement desactives.
	 * 
	 * @param c	l'instance de MTComponent qui possede cet analyseur
	 */
	public GestureAnalyzer(MTComponent c){
		comp = c;
		oneDollarReco = new OneDollarRecognizer();
		modeOneDollarEnabled = false;
		modeSClickEnabled = false;
		modeShapeEnabled = false;
		modeModifyEnabled = false;
		modeSlideEnabled = false;
	}
	
	public void setModeOneDollar(boolean enable){
		modeOneDollarEnabled = enable;
	}
	public void setModeShape(boolean enable){
		modeShapeEnabled = enable;
	}
	public void setModeSingleClick(boolean enable){
		modeSClickEnabled = enable;
	}
	public void setModeModify(boolean enable){
		modeModifyEnabled = enable;
	}
	public void setModeSlide(boolean enable){
		modeSlideEnabled = enable;
	}
	public void desactiveAll(){
		desactiveAll = true;
	}
	public void reactive(){
		desactiveAll = false;
	}
	
	public void analyze(BlobQueue b, int state, int id, Point2 p){
		if(desactiveAll)
			return;
		if(modeModifyEnabled){
			//mode Modify ne peut pas etre applique en meme temps avec des autres modes
			anaModeModify(b, state, id, p);
			return;
		}
		if(modeShapeEnabled)
			anaModeShape(b, state, id, p);
		else {
			if(modeOneDollarEnabled)
				anaModeOneDollar(b, state, id, p);
			if(modeSlideEnabled)
				anaModeSlide(b, state, id, p);
		}
		if(modeSClickEnabled)
			anaModeSingleClick(b, state, id, p);
	}

	/**
	 * Le component qui garde cet analyseur
	 */
	private MTComponent comp;
	/**
	 * Il sert a la mode OneDollar
	 */
	private OneDollarRecognizer oneDollarReco;
	/**
	 * Etiquette pour desactiver tous les modes
	 */
	private boolean desactiveAll;
	private boolean modeOneDollarEnabled;
	private boolean modeShapeEnabled;
	private boolean modeSClickEnabled;
	private boolean modeModifyEnabled;
	private boolean modeSlideEnabled;
	public static final int ST_ADD = 1;
	public static final int ST_UPDATE = 2;
	public static final int ST_REMOVE = 3;
	
	/**
	 * Analyseur de mode OneDollar
	 * Cet analyseur utilise l'algorithme "One Dollar" pour analyser la gestion d'un doigt
	 * Cet algorithme peut reconnaitre une forme
	 * Il compare la forme avec les modeles pour distinguer a lequel la forme resemble.
	 * Cet analyseur genere l'event "GestureEvent" et l'informe a MTComponent.
	 * 
	 * @param b		Les paths de tous les doigts qui commencent sur ce MTComponent
	 * @param state	Le type d'evenement du doigt: Ajoute, Enleve, Update
	 * @param id	L'identifiant de doigt qui vient de generer un evenement
	 * @param p		Le point interesse de cet evenement
	 */
	private void anaModeOneDollar(BlobQueue b, int state, int id, Point2 p) {
		switch(state){
		case ST_ADD:
			if(b.nbBlobs() == 1){
				//Clique: un doigt qui vient de poser sur l'interface
			}
			break;
		case ST_UPDATE:
			if(b.nbBlobs() == 1){
				//Mouvement: un doigt qui bouge sur l'interface
			}
			break;
		case ST_REMOVE:
			if(b.nbBlobs() == 1){
				//l'action est termine, il faut informer le listener
				Point2[] points = OneDollarRecognizer.resample(b.get(id).getAll());
				points = OneDollarRecognizer.rotateToZero(points);
				points = OneDollarRecognizer.scaleToSquare(points, TemplateManager.TEMPLATE_SIZE);
				points = OneDollarRecognizer.translateToOrigin(points);
				RecognizeResult result = oneDollarReco.recognize(points, TemplateManager.TEMPLATE_SIZE);
				if(Math.abs(result.getScore()) < OneDollarRecognizer.SEUIL){
					GestureEvent evt = new GestureEvent(comp, result, b.get(id).getAll());
					comp.fireGesturePerformed(evt);
				}
			}
			break;
		}
	}
	
	/**
	 * Analyseur de mode Shape
	 * <p>
	 * Cet analyseur analyse le glissement d'un doigt et la rotation et l'agrandissement de deux doigts.<p>
	 * Le glissement d'un doigt est regard comme l'utilisateur veut bouger le component,
	 * il va generer l'evenement "DiscreteEvent" qui indique la translation.<p>
	 * La gestion de deux doigts est analysee en prennant en compte en plus la rotation et l'agrandissement.<p>
	 * L'analyseur va generer l'evenement "SRTEvent" qui indique le Scale, la Rotation et la Translation.
	 * 
	 * @param b		Les paths de tous les doigts qui commencent sur ce MTComponent
	 * @param state	Le type d'evenement du doigt: Ajoute, Enleve, Update
	 * @param id	L'identifiant de doigt qui vient de generer un evenement
	 * @param p		Le point interesse de cet evenement
	 */
	private void anaModeShape(BlobQueue b, int state, int id, Point2 p){
		switch(state){
		case ST_ADD:
			if(b.nbBlobs() == 1){
				//Clique: un doigt qui vient de poser sur le component
				comp.getGestureState().motionTranslateBegin(p);
				DiscreteEvent evt = new DiscreteEvent(comp);
				comp.fireDiscretePerformed(evt);
			}
			else if(b.nbBlobs() == 2){
				//L'ajoute de deuxieme doigt sur le component
				comp.getGestureState().motionTRSBegin(b.atIndex(0).getLast(), b.atIndex(1).getLast());
			}
			break;
		case ST_UPDATE:
			if(b.nbBlobs() == 1){
				//Mouvement: un doigt qui bouge sur le component
				comp.getGestureState().motionTranslateUpdate(p);
				SRTEvent evt = new SRTEvent(comp, comp.getGestureState().computeTranslation(), 0, OBB.SCALE_NOCHANGE);
				comp.fireSRTPerformed(evt);
			}
			else if(b.nbBlobs() == 2){
				//le mouvement de deux doigt
				comp.getGestureState().motionTRSUpdate(b.atIndex(0).getLast(), b.atIndex(1).getLast());
				SRTEvent evt = new SRTEvent(comp, 
						comp.getGestureState().computeTranslation(comp.getInitOrigin()), 
						comp.getGestureState().computeRotation(), 
						comp.getGestureState().computeScale());
				comp.fireSRTPerformed(evt);
			}
			break;
		case ST_REMOVE:
			if(b.nbBlobs() == 1){
				comp.getGestureState().motionTranslateBegin(p);
				comp.fixPosition();
			}
			else if(b.nbBlobs() == 2){
				comp.getGestureState().motionTRSEnd();
				comp.fixPosition();
			}
			break;
		}
	}
	
	/**
	 * Analyseur de mode SingleClick<p>
	 * Cet analyseur analyse l'evenement single clique.
	 * Il peut generer l'evenement "SingleClickEvent".
	 * 
	 * @param b		Les paths de tous les doigts qui commencent sur ce MTComponent
	 * @param state	Le type d'evenement du doigt: Ajoute, Enleve, Update
	 * @param id	L'identifiant de doigt qui vient de generer un evenement
	 * @param p		Le point interesse de cet evenement
	 */
	private void anaModeSingleClick(BlobQueue b, int state, int id, Point2 p){
		switch(state){
		case ST_REMOVE:
			if(b.nbBlobs() == 1){
				//Clique release: le seul doigt est enleve sur le component
				if(b.get(id).size() == 1){
					//single clique
					comp.fireSingleClickPerformed(new SingleClickEvent(comp));
				}
			}
			break;
		}
	}
	
	/**
	 * Analyseur de mode Modify<p>
	 * Cet analyseur peut etre applique qu'a MainContainer.<p>
	 * Il sert a activer la mode Modify de MainContainer, 
	 * dans cette mode, il permet a l'utilisateur de dessiner sur l'ecran 
	 * et ajouter enfin le dessin a MainContainer.
	 * 
	 * @param b		Les paths de tous les doigts qui commencent sur ce MTComponent
	 * @param state	Le type d'evenement du doigt: Ajoute, Enleve, Update
	 * @param id	L'identifiant de doigt qui vient de generer un evenement
	 * @param p		Le point interesse de cet evenement
	 */
	private void anaModeModify(BlobQueue b, int state, int id, Point2 p){
		if(comp.getClass() != MainContainer.class)
			return;
		MainContainer tmp = (MainContainer)comp;
		switch(state){
		case ST_ADD:
			if(b.nbBlobs() == 1){
				//une nouvelle path de dessin commence
				tmp.newPolygon(p);
			}
			break;
		case ST_UPDATE:
			if(b.nbBlobs() == 1){
				tmp.updatePolygon(p);
			}
			break;
		case ST_REMOVE:
			if(b.nbBlobs() == 1){
				//termine, ajoute la path dans MainContainer
				tmp.addPolygon();
			}
			break;
		}
	}
	
	/**
	 * Analyseur de mode Slide<p>
	 * Cet analyseur analyse le glissement de deux doigts, il peut etre applique par widget.MTContainer
	 * Il genere l'evenement "SlideEvent", il permet de bouger tous les components ensemble dans MTContainer
	 * 
	 * @param b		Les paths de tous les doigts qui commencent sur ce MTComponent
	 * @param state	Le type d'evenement du doigt: Ajoute, Enleve, Update
	 * @param id	L'identifiant de doigt qui vient de generer un evenement
	 * @param p		Le point interesse de cet evenement
	 */
	private void anaModeSlide(BlobQueue b, int state, int id, Point2 p) {
		switch(state){
		case ST_UPDATE:
			if(b.nbBlobs() == 2){
				Path slide;
				if(b.atIndex(1).size() >= 2)
					slide = b.atIndex(1);
				else
					slide = b.atIndex(0);
				Vector2 t = new Vector2(slide.getLast().getX() - slide.get(slide.size()-2).getX(), 
									    slide.getLast().getY() - slide.get(slide.size()-2).getY());
				SlideEvent evt = new SlideEvent(comp, t);
				if(comp.getClass() == MTChooser.class){
					((MTChooser)comp).fireSlidePerformed(evt);
				}
			}
			break;
		}
	}
}
