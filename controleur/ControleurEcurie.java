package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.JButton;

import modele.Equipe;
import modele.ErreurBD;
import modele.Jeu;
import modele.Joueur;
import modele.Tournoi;
import vue.ErrorMessage;
import vueEcurie.AccueilEcurie;
import vueEcurie.AjouterJoueur;
import vueEcurie.CreerEquipe;
import vueEcurie.PopUp_ConfirmDeleteTeam;
import vueEcurie.PopUp_ConfirmInscription;

public class ControleurEcurie implements ActionListener {

	private enum Etats {ACCUEIL, CREATION_EQUIPE, AJOUT_JOUEUR, SUPRESSION_EQUIPE, EQUIPE_SELEC, TOURNOI_SELEC, INSCRIPTION_TOURNOI};

	private Etats etat;
	private AccueilEcurie mainWindow;
	private CreerEquipe teamFormWindow;
	private AjouterJoueur addPlayerWindow;
	private PopUp_ConfirmInscription confirmInscriptionPopUp;
	private PopUp_ConfirmDeleteTeam confirmDeleteTeamPopUp;
	private Joueur J1;
	private Joueur J2;
	private Joueur J3;
	private Joueur J4;
	private Joueur J;
	private Equipe equipe;
	private int ecurie;
	private Tournoi tournoi;

	public ControleurEcurie() {
		this.etat = Etats.ACCUEIL;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(this.etat) {
		case ACCUEIL:
			if (e.getSource() instanceof JButton) {
				JButton b = (JButton) e.getSource();
				switch (b.getText()) {
				case "rafraîchir" :
					mainWindow.setListEquipes();
					break;
				case "Nouvelle Equipe" :
					new CreerEquipe();
					this.etat = Etats.CREATION_EQUIPE;
					break;
				}
			}
		case CREATION_EQUIPE:
			if (e.getSource() instanceof JButton) {
				JButton b = (JButton) e.getSource();
				switch (b.getText()) {
				case "Annuler" :
					teamFormWindow.dispose();
					this.etat = Etats.ACCUEIL; //Or Equipe_selec or Tournois_selec ?
					break;
				case "Créer" :
					if (teamFormWindow.getEnabled()) {
						Equipe equipe;
						try {
							equipe = new Equipe(teamFormWindow.getInputTeamName(), 0, Jeu.getID(new Jeu(teamFormWindow.getGameID())));
							equipe.addJoueur(J1);
							equipe.addJoueur(J2);
							equipe.addJoueur(J3);
							equipe.addJoueur(J4);
							equipe.insert(ecurie);
							J1.insert(equipe.getID());
							J2.insert(equipe.getID());
							J3.insert(equipe.getID());
							J4.insert(equipe.getID());
							mainWindow.setListEquipes();
							teamFormWindow.dispose();
						} catch (IllegalArgumentException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (ErreurBD e2) {
							// TODO Auto-generated catch block
							ErrorMessage.ErrorMessage(e2.getMessage());
						}
						
						
					}
					this.etat = Etats.ACCUEIL;
					break;
				case "Changer" :
					try {
						new AjouterJoueur(J.getNom(), J.getPrenom(), J.getDateNaissance(), J.getSexe(), J.getTel(), J.getEmail());
					} catch (ErreurBD e1) {
						// TODO Auto-generated catch block
						ErrorMessage.ErrorMessage(e1.getMessage());
					}
					this.etat = Etats.AJOUT_JOUEUR;
					break;
				case "Nouveau Joueur" :
					new AjouterJoueur(null, null, null, ' ', null, null);
					this.etat = Etats.AJOUT_JOUEUR;
					break;
				}
			}
			break;
		case AJOUT_JOUEUR:
			if (e.getSource() instanceof JButton) {
				JButton b = (JButton) e.getSource();
				switch (b.getText()) {
				case "Annuler" :
					submitAnnulerAjoutJoueur();
					this.etat = Etats.CREATION_EQUIPE;
					break;
				case "Ajouter" :
					submitValiderAjoutJoueur();
					this.etat = Etats.CREATION_EQUIPE;
				}
			}
			break;
		case SUPRESSION_EQUIPE:
			if (e.getSource() instanceof JButton) {
				JButton b = (JButton) e.getSource();
				switch (b.getText()) {
				case "Non" :
					submitNonDelete();
					this.etat = Etats.EQUIPE_SELEC;
					break;
				case "oui" :
					submitOuiDelete(equipe);
					this.etat = Etats.ACCUEIL;
					break;
				}
			}
		case EQUIPE_SELEC:
			if (e.getSource() instanceof JButton) {
				JButton b = (JButton) e.getSource();
				switch (b.getText()) {
					case "Supprimer" :
						new PopUp_ConfirmDeleteTeam(equipe);
						break;
				}
			}
			break;
		case TOURNOI_SELEC:
			if (e.getSource() instanceof JButton) {
				JButton b = (JButton) e.getSource();
				switch (b.getText()) {
				case "Inscrire" :
					new PopUp_ConfirmInscription(equipe, tournoi);
					this.etat = Etats.INSCRIPTION_TOURNOI;
					break;
				}
			}
			break;
		case INSCRIPTION_TOURNOI:
			if (e.getSource() instanceof JButton) {
				JButton b = (JButton) e.getSource();
				switch (b.getText()) {
				case "Non" :
					submitNonInscription();
					this.etat = Etats.TOURNOI_SELEC;
					break;
				case "Oui" :
					submitOuiInscription(tournoi,equipe);
					this.etat = Etats.ACCUEIL;
					break;
				}
			}
			break;
		}
	}
	
	//AjouterJoueur's functions
	private void submitAnnulerAjoutJoueur() {
		addPlayerWindow.dispose();;
	}
	private void submitValiderAjoutJoueur() {
		if (addPlayerWindow.isValidationEnabled()) {
			//Create calandar from inputs
			Calendar cal = Calendar.getInstance();
			cal.set( Calendar.DAY_OF_MONTH, addPlayerWindow.getDay());
			cal.set( Calendar.MONTH, addPlayerWindow.getMonth());
			cal.set( Calendar.YEAR, addPlayerWindow.getYear());
			//Create and return player from inputs
			if(addPlayerWindow.isFormatGood()) {
				equipe.addJoueur(new Joueur(
					addPlayerWindow.getLastName(), 
				 	addPlayerWindow.getFirstName(), 
				 	new Date(cal.getTimeInMillis()), 
				 	addPlayerWindow.getSexe(),
				 	addPlayerWindow.getPhoneNumber(), 
				 	addPlayerWindow.getEMail()
				));
				addPlayerWindow.dispose();
			}
		}
	}

	//PopUp_ConfirmDeleteTeam
	private void submitNonDelete() {
		confirmDeleteTeamPopUp.dispose();
	}
	private void submitOuiDelete(Equipe equipe) {
		try {
			equipe.delete();
		} catch (ErreurBD e) {
			// TODO Auto-generated catch block
			ErrorMessage.ErrorMessage(e.getMessage());
		};
		mainWindow.setListEquipes();
		confirmDeleteTeamPopUp.dispose();
	}
	
	//PopUp_ConfirmInscription
	private void submitNonInscription() {
		confirmInscriptionPopUp.dispose();
	}
	private void submitOuiInscription(Tournoi tournoi, Equipe equipe) {
		try {
			tournoi.addEquipe(equipe);
		} catch (ErreurBD e) {
			// TODO Auto-generated catch block
			ErrorMessage.ErrorMessage(e.getMessage());
		}
		mainWindow.setListEquipes();
		confirmInscriptionPopUp.dispose();
	}
}