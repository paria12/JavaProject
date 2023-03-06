package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ControleurEcurie implements ActionListener {

	private enum Etats {ACCUEIL,CREATION_EQUIPE,SUPRESSION_EQUIPE,EQUIPE_SELEC,TOURNOI_SELEC,INSCRIPTION_TOURNOI};

	private Etats etat;

	public ControleurEcurie() {
		this.etat = Etats.ACCUEIL;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		switch(this.etat) {
		case ACCUEIL:

			break;
		case CREATION_EQUIPE:

			break;
		case SUPRESSION_EQUIPE:

			break;
		case EQUIPE_SELEC:

			break;
		case TOURNOI_SELEC:

			break;
		case INSCRIPTION_TOURNOI:

			break;
		}

	}

}