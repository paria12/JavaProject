package controleur;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import vue.ConnexionWindow;
import vueArbitre.AccueilArbitre;
import vueArbitre.SaisirScore;
import vueGerant.AccueilGerant;
import vueGerant.AjouterJeu;
import vueGerant.ClassementAnnuel;
import vueGerant.CreerTournoi;
import vueGerant.PopUp_ConfirmDeleteTournoi;

public class ControleurGerant implements ActionListener {

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
			if () {//clic tournoi
				
			} else {
				switch (b.getText()) {
				case "Déconnexion"://deconexion
					accueilGerant.dispose();
					ConnexionWindow.main(null);
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
					ClassementAnnuel.main(null);
					etat=Etats.CHOIX_JEU_CLASSEMENT;
					break;
				case "rafraîchir":
					accueilGerant.setListTournois();
					etat=Etats.ACCUEIL;
					break;
				}
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
		case DETAILS_TOURNOI://je crois que c'est la même chose que acceuil mais faut juste afficher les info du tournoi avent

			break;
		case CHOIX_JEU_CLASSEMENT://Affichage du classement annuel
			switch(b.getText()) {
			case "Choisir":
				classementAnnuel.setVisibleClassementAnnuel();
				break;
			}
			break;
		case AFFICHAGE_CLASSEMENT:// Version si jeux choisie

			break;
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		switch(this.etat) {
		case ACCUEIL:
			
			
			break;
		}
	}

}