package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.Match;
import vueArbitre.AccueilArbitre;
import vueArbitre.SaisirScore;

public class ControleurArbitre implements ActionListener, ListSelectionListener {

	private enum Etats {ACCUEIL,MATCH_SELEC,CHOIX_EQUIPE,EQUIPE_CHOISIE};

	private Etats etat;
	private AccueilArbitre accueilArbitre;
	private SaisirScore saisirScore;

	public static ControleurArbitre instance;
	
	public void setFenetreAccueil(AccueilArbitre accueil) {
		getInstance().accueilArbitre = accueil;
	}
	public void setFenetreSaisir(SaisirScore saisir) {
		getInstance().saisirScore = saisir;
	}
    
    public static synchronized ControleurArbitre getInstance() {
        if (instance == null) {
            instance = new ControleurArbitre();
        }
        return instance;
    }
	public ControleurArbitre() {
		this.etat = Etats.ACCUEIL;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//KeyEvent a = (KeyEvent) e.getSource();
		switch(this.etat) {
		case MATCH_SELEC:
			JButton b = (JButton) e.getSource();
			if(b.getText().equals("Saisir Score")) {
				accueilArbitre.ouvrirSaisirScore();
				etat=Etats.CHOIX_EQUIPE;
			}
			break;
		case CHOIX_EQUIPE:
			if(e.getSource() instanceof JRadioButton) {
			JRadioButton r = (JRadioButton) e.getSource();
			if((r.getText().equals(saisirScore.getEquipe1EnString()))|| (r.getText().equals(saisirScore.getEquipe2EnString()))) {
				saisirScore.EnabledButton(true);
				etat=Etats.EQUIPE_CHOISIE;
			}
			}else if(e.getSource() instanceof JButton){
				JButton j= (JButton) e.getSource();
				if(j.getText().equals("Annuler")) {
					saisirScore.submitAnnuler();
					etat=Etats.ACCUEIL;
				}
			}
			
			break;
		case EQUIPE_CHOISIE:
			if(e.getSource() instanceof JRadioButton) {
				JRadioButton r = (JRadioButton) e.getSource();
				if((r.getText().equals(saisirScore.getEquipe1EnString()))|| (r.getText().equals(saisirScore.getEquipe2EnString()))) {
					etat=Etats.EQUIPE_CHOISIE;
				}
				}else if(e.getSource() instanceof JButton){
					JButton jb = (JButton) e.getSource();
					if(jb.getText().equals("Valider")) {
						saisirScore.submitValider();
						etat=Etats.ACCUEIL;
					}
					if(jb.getText().equals("Annuler")) {
						saisirScore.submitAnnuler();
						etat=Etats.ACCUEIL;
					}
				}
			
			break;
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		switch(this.etat) {
		case ACCUEIL:
			accueilArbitre.EnabledButtonFromList();
			etat=Etats.MATCH_SELEC;
			break;
		}
	}
	

}