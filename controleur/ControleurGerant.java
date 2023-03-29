package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import vue.ConnexionWindow;
import vue.PanelPresentationTournoi;
import vueArbitre.AccueilArbitre;
import vueArbitre.SaisirScore;
import vueGerant.AccueilGerant;
import vueGerant.AjouterJeu;
import vueGerant.ClassementAnnuel;
import vueGerant.CreerTournoi;
import vueGerant.PopUp_ConfirmDeleteTournoi;

public class ControleurGerant implements ActionListener, MouseListener, WindowListener {

	private enum Etats {ACCUEIL,CREATION_TOURNOI,CREATION_JEU,DETAILS_TOURNOI,CHOIX_JEU_CLASSEMENT,AFFICHAGE_CLASSEMENT};

	private Etats etat;
	public static ControleurGerant instance;
	private AccueilGerant accueilGerant;
	private AjouterJeu ajouterJeu;
	private ClassementAnnuel classementAnnuel;
	private CreerTournoi creerTournoi;
	private PopUp_ConfirmDeleteTournoi confirmDelete;
	
	private int RevenirVersAccueilOuCreerTournoi;
	
	public void setFenetreAccueil(AccueilGerant accueil) {
		getInstance().accueilGerant = accueil;
	}
	public void setFenetreAjouterJeu(AjouterJeu jeu) {
		getInstance().ajouterJeu = jeu;
	}
	public void setFenetreClassementAnnuel(ClassementAnnuel classement) {
		getInstance().classementAnnuel = classement;
	}
	public void setFenetreCreerTournoi(CreerTournoi tournoi) {
		getInstance().creerTournoi = tournoi;
	}
	public void setFenetrePopUp_ConfirmDeleteTournoi(PopUp_ConfirmDeleteTournoi delete) {
		getInstance().confirmDelete = delete;
	}
	public static synchronized ControleurGerant getInstance() {
		if (instance == null) {
			instance = new ControleurGerant();
		}
		return instance;
	}

	public ControleurGerant() {
		this.etat = Etats.ACCUEIL;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		switch(this.etat) {
		case ACCUEIL:
			switch (b.getText()) {
				case "Déconnexion"://deconexion
					accueilGerant.fermerAccueilGerant();
					Main.main(null);
					break;
				case "Nouveau Tournoi"://nouveau tournoi
					accueilGerant.submitNouveauTournoi();
					etat = Etats.CREATION_TOURNOI;
					break;
				case "Ajouter jeu"://nouveau jeu
					accueilGerant.submitAjouterJeu();
					RevenirVersAccueilOuCreerTournoi = 1;
					etat=Etats.CREATION_JEU;
					break;
				case "Classement Annuel"://cassement annuel
					Main.main(null);
					etat=Etats.CHOIX_JEU_CLASSEMENT;
					break;
				case "rafraîchir":
					accueilGerant.setListTournois();
					etat=Etats.ACCUEIL;
					break;
				}
			break;
		case CREATION_TOURNOI:
			//doit-on gérer tous les inputs car ils servent tous à setEnabled le bouton en True ?
			//afficher fenetre
			switch (b.getText()) {
			case "Annuler"://annuler
				creerTournoi.submitAnnuler();
				etat = Etats.ACCUEIL;
				break;
			case "Créer"://validé
				creerTournoi.submitCreer();
				etat = Etats.ACCUEIL;
				break;
			case "+"://nouveau jeu
				creerTournoi.ouvrirAjouterJeu();
				RevenirVersAccueilOuCreerTournoi = 0;
				etat = Etats.CREATION_JEU;
				break;
			}
			break;
		case CREATION_JEU: //Attention, il faut savoir si l'etat precedant est CREATION_TOURNOI ou ACCUEIL
			//afficher fenetre
			switch (b.getText()) {
			case "Annuler"://annuler
				ajouterJeu.submitAnnuler();
				if(RevenirVersAccueilOuCreerTournoi == 0) {
					etat = Etats.CREATION_TOURNOI;
				}else {
					etat = Etats.ACCUEIL;
				}
				break;
			case "Créer"://validé
				ajouterJeu.submitCreer();
				if(RevenirVersAccueilOuCreerTournoi == 0) {
					etat = Etats.CREATION_TOURNOI;
				}else {
					etat = Etats.ACCUEIL;
				}
				break;
			}
			break;
		case CHOIX_JEU_CLASSEMENT://Affichage du classement annuel
			switch(b.getText()) {
			case "Choisir":
				etat=Etats.AFFICHAGE_CLASSEMENT;
				break;
			}
			break;
		case AFFICHAGE_CLASSEMENT:// Version si jeux choisie
			classementAnnuel.setVisibleClassementAnnuel();
			break;
		}	
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		switch(this.etat) {
		case ACCUEIL:
			accueilGerant.setPresentTournoiByName(((JPanel)e.getSource()).getName());
			accueilGerant.selectionTournoi();
			etat = Etats.ACCUEIL;
			break;
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		((JFrame) e.getSource()).dispose();
		if((etat == Etats.AFFICHAGE_CLASSEMENT)||(etat == Etats.CHOIX_JEU_CLASSEMENT)) {
			etat = Etats.ACCUEIL;
		}else if(etat == Etats.CREATION_TOURNOI) {
			etat = Etats.ACCUEIL;
		}else if((RevenirVersAccueilOuCreerTournoi == 0)&&(etat == Etats.CREATION_JEU)){
			etat = Etats.CREATION_TOURNOI;
		}else if((RevenirVersAccueilOuCreerTournoi == 1)&&(etat == Etats.CREATION_JEU)) {
			etat = Etats.ACCUEIL;
		}
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}