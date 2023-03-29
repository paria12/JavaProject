package controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Ecurie;
import modele.Equipe;
import modele.ErreurBD;
import modele.Jeu;
import modele.Joueur;
import modele.Tournoi;
import vue.Colors;
import vue.ConnexionWindow;
import vue.ErrorMessage;
import vue.Header;
import vue.JPanelDarkest;
import vue.PanelPresentationTournoi;
import vueEcurie.AccueilEcurie;
import vueEcurie.AjouterJoueur;
import vueEcurie.CreerEquipe;
import vueEcurie.PopUp_ConfirmDeleteTeam;
import vueEcurie.PopUp_ConfirmInscription;

public class ControleurEcurie implements ActionListener, MouseListener, WindowListener {

	private enum Etats {ACCUEIL, CREATION_EQUIPE, AJOUT_JOUEUR, SUPRESSION_EQUIPE, EQUIPE_SELEC, TOURNOI_SELEC, INSCRIPTION_TOURNOI};

	private Etats etat;
	private AccueilEcurie mainWindow;
	private CreerEquipe teamFormWindow;
	private AjouterJoueur addPlayerWindow;
	private PopUp_ConfirmInscription confirmInscriptionPopUp;
	private PopUp_ConfirmDeleteTeam confirmDeleteTeamPopUp;
	public static ControleurEcurie instance;
	private String nbJ;

	public static synchronized ControleurEcurie getInstance() {
		if (instance == null) {
			instance = new ControleurEcurie();
		}
		return instance;
	}
	
	public void setMainWindow(AccueilEcurie w) {
		getInstance().mainWindow = w;
	}
	
	public ControleurEcurie() {
		this.etat = Etats.ACCUEIL;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(getInstance().etat) {
		case ACCUEIL:
			if (e.getSource() instanceof JButton) {
				JButton b = (JButton) e.getSource();
				switch (b.getText()) {
				case "rafraîchir" :
					mainWindow.setListEquipes();
				break;
				case "Nouvelle Equipe" :
					teamFormWindow = new CreerEquipe();
					getInstance().etat = Etats.CREATION_EQUIPE;
				break;
				case "Supprimer" :
					System.out.println("out");
					confirmDeleteTeamPopUp = new PopUp_ConfirmDeleteTeam(b.getName());
					getInstance().etat = Etats.SUPRESSION_EQUIPE;
				break;
				case "Déconnexion":
					mainWindow.dispose();
					Main.main(null);
				break;
				} 
			}
		break;
		case CREATION_EQUIPE:
			if (e.getSource() instanceof JButton) {
				JButton b = (JButton) e.getSource();
				switch (b.getText()) {
				case "Annuler" :
					teamFormWindow.dispose();
					getInstance().etat = Etats.ACCUEIL; //Or Equipe_selec or Tournois_selec ?
				break;
				case "Créer" :
					if (teamFormWindow.getEnabled()) {
						Equipe equipe;
						try {
							equipe = new Equipe(teamFormWindow.getInputTeamName(), 0, Jeu.getID(new Jeu(teamFormWindow.getGameID())));
							equipe.addJoueur(teamFormWindow.getJ1());
							equipe.addJoueur(teamFormWindow.getJ2());
							equipe.addJoueur(teamFormWindow.getJ3());
							equipe.addJoueur(teamFormWindow.getJ4());
							equipe.insert(Ecurie.getID(new Ecurie(Header.header)));
							teamFormWindow.getJ1().insert(equipe.getID());
							teamFormWindow.getJ2().insert(equipe.getID());
							teamFormWindow.getJ3().insert(equipe.getID());
							teamFormWindow.getJ4().insert(equipe.getID());
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
					getInstance().etat = Etats.ACCUEIL;
				break;
				case "Changer" :
					Joueur J = null;
					nbJ = b.getName().substring(1);
					switch (nbJ) {
					case "1":
						J = teamFormWindow.getJ1();
					break;
					case "2":
						J = teamFormWindow.getJ2();
					break;
					case "3":
						J = teamFormWindow.getJ3();
					break;
					case "4":
						J = teamFormWindow.getJ4();
					break;
					} 
					try {
						addPlayerWindow = new AjouterJoueur(J.getNom(), J.getPrenom(), J.getDateNaissance(), J.getSexe(), J.getTel(), J.getEmail());
					} catch (ErreurBD e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					getInstance().etat = Etats.AJOUT_JOUEUR;
				break;
				case "Nouveau Joueur" :
					nbJ = b.getName().substring(1);
					addPlayerWindow = new AjouterJoueur(null, null, null, ' ', null, null);
					getInstance().etat = Etats.AJOUT_JOUEUR;
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
					getInstance().etat = Etats.CREATION_EQUIPE;
				break;
				case "Ajouter" :
					submitValiderAjoutJoueur();
					getInstance().etat = Etats.CREATION_EQUIPE;
				break;
				}
			}
		break;
		case SUPRESSION_EQUIPE:
			if (e.getSource() instanceof JButton) {
				JButton b = (JButton) e.getSource();
				switch (b.getText()) {
				case "Non" :
					System.out.println("Nop");
					submitNonDelete();
					getInstance().etat = Etats.ACCUEIL;
				break;
				case "Oui" :
					submitOuiDelete(new Equipe(confirmDeleteTeamPopUp.getEquipeName()));
					getInstance().etat = Etats.ACCUEIL;
				break;
				}
			}
		break;
		case EQUIPE_SELEC:
			if (e.getSource() instanceof JButton) {
				JButton b = (JButton) e.getSource();
				switch (b.getText()) {
				case "Supprimer" :
					confirmDeleteTeamPopUp = new PopUp_ConfirmDeleteTeam(b.getName());
					getInstance().etat = Etats.SUPRESSION_EQUIPE;
				break;
				case "rafraîchir" :
					mainWindow.setListEquipes();
				break;
				case "Nouvelle Equipe" :
					teamFormWindow = new CreerEquipe();
					getInstance().etat = Etats.CREATION_EQUIPE;
				break;
				case "Déconnexion":
					mainWindow.dispose();
					Main.main(null);
					getInstance().etat = Etats.ACCUEIL;
				break;
				}
			} 
		break;
		case TOURNOI_SELEC:
			if (e.getSource() instanceof JButton) {
				JButton b = (JButton) e.getSource();
				switch (b.getText()) {
				case "Inscrire" :
					confirmInscriptionPopUp = new PopUp_ConfirmInscription(mainWindow.getEquipe(), mainWindow.getTournoi());
					getInstance().etat = Etats.INSCRIPTION_TOURNOI;
				break;
				case "rafraîchir" :
					mainWindow.setListEquipes();
				break;
				case "Nouvelle Equipe" :
					teamFormWindow = new CreerEquipe();
					getInstance().etat = Etats.CREATION_EQUIPE;
				break;
				case "Supprimer" :
					confirmDeleteTeamPopUp = new PopUp_ConfirmDeleteTeam(b.getName());
					getInstance().etat = Etats.SUPRESSION_EQUIPE;
				break;
				case "Déconnexion":
					mainWindow.dispose();
					Main.main(null);
					getInstance().etat = Etats.ACCUEIL;
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
					getInstance().etat = Etats.TOURNOI_SELEC;
				break;
				case "Oui" :
					submitOuiInscription(mainWindow.getTournoi(),mainWindow.getEquipe());
					getInstance().etat = Etats.ACCUEIL;
				break;
				}
			}
		break;
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		switch(getInstance().etat) {
		case ACCUEIL:
			mainWindow.setPresentEquipeByName(((JPanel)e.getSource()).getName());
			mainWindow.showTeamChoosen();
			getInstance().etat = Etats.EQUIPE_SELEC;
		break;
		case EQUIPE_SELEC:
			if (((JPanel)e.getSource()).getName().startsWith("pT")) {
				mainWindow.setPresentTournoiByName(((JPanel)e.getSource()).getName());
				mainWindow.showTurnamentChoosen();
				getInstance().etat = Etats.TOURNOI_SELEC;
			} else if ((((JPanel)e.getSource()).getName().startsWith("pE"))) {
				mainWindow.setPresentEquipeByName(((JPanel)e.getSource()).getName());
				mainWindow.showTeamChoosen();
				getInstance().etat = Etats.EQUIPE_SELEC;
			}	
		break;
		case AJOUT_JOUEUR:
		break;
		case CREATION_EQUIPE:
		break;
		case INSCRIPTION_TOURNOI:
		break;
		case SUPRESSION_EQUIPE:
		break;
		case TOURNOI_SELEC:
		break;
		default:
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
			Joueur j = new Joueur(
							addPlayerWindow.getLastName(), 
						 	addPlayerWindow.getFirstName(), 
						 	new Date(cal.getTimeInMillis()), 
						 	addPlayerWindow.getSexe(),
						 	addPlayerWindow.getPhoneNumber(), 
				 			addPlayerWindow.getEMail()
						);
			if(addPlayerWindow.isFormatGood()) {
				teamFormWindow.setJoueur(j, nbJ);
				addPlayerWindow.dispose();
			}
		}
	}

	//PopUp_ConfirmDeleteTeam
	private void submitNonDelete() {
		confirmDeleteTeamPopUp.dispose();
	}
	private void submitOuiDelete(Equipe equipe) {
		System.out.println("deleted "+equipe.getNom());
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
    public void windowClosing(WindowEvent e) {
        ((JFrame) e.getSource()).dispose();
        switch(etat) {
        case ACCUEIL:
		break;
		case EQUIPE_SELEC:
			getInstance().etat = Etats.ACCUEIL;
		break;
		case AJOUT_JOUEUR:
			getInstance().etat = Etats.CREATION_EQUIPE;
		break;
		case CREATION_EQUIPE:
			getInstance().etat = Etats.ACCUEIL;
		break;
		case INSCRIPTION_TOURNOI:
			getInstance().etat = Etats.TOURNOI_SELEC;
		break;
		case SUPRESSION_EQUIPE:
			getInstance().etat = Etats.EQUIPE_SELEC;
		break;
		case TOURNOI_SELEC:
			getInstance().etat = Etats.ACCUEIL;
		break;
		default:
		break;
        }

    }

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
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