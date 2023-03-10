package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ControleurGerant implements ActionListener {

	private enum Etats {ACCUEIL,CREATION_TOURNOI,CREATION_JEU,DETAILS_TOURNOI,CHOIX_JEU_CLASSEMENT,AFFICHAGE_CLASSEMENT};

	private Etats etat;
	public static ControleurGerant instance;
	
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
				case ://deconexion
					
					break;
				case ://nouveau tournoi
					
					break;
				case ://nouveau jeu
					
					break;
				case ://cassement annuel
					
					break;
				}
			}
			break;
		case CREATION_TOURNOI:
			//afficher fenetre
			switch (b.getText()) {
			case ://annuler
				
				break;
			case ://validé
				
				break;
			case ://nouveau jeu
				
				break;
			}
			break;
		case CREATION_JEU: //Attention, il faut savoir si l'etat precedant est CREATION_TOURNOI ou ACCUEIL
			//afficher fenetre
			switch (b.getText()) {
			case ://annuler
				
				break;
			case ://validé
				
				break;
			}
			break;
		case DETAILS_TOURNOI://je crois que c'est la même chose que acceuil mais faut juste afficher les info du tournoi avent

			break;
		case CHOIX_JEU_CLASSEMENT://Affichage du classement annuel

			break;
		case AFFICHAGE_CLASSEMENT:// Version si jeux choisie

			break;
		}

	}

}