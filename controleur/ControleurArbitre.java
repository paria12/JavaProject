package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ControleurArbitre implements ActionListener {

	private enum Etats {ACCUEIL,MATCH_SELEC,CHOIX_EQUIPE,EQUIPE_CHOISIE};

	private Etats etat;

	public ControleurArbitre() {
		this.etat = Etats.ACCUEIL;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		switch(this.etat) {
		case ACCUEIL:

			break;
		case MATCH_SELEC:

			break;
		case CHOIX_EQUIPE:

			break;
		case EQUIPE_CHOISIE:

			break;
		}

	}

}