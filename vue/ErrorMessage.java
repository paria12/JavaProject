package vue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorMessage {

    public static void ErrorMessage(String erreurS) {
        JFrame fenetre = null;
        JOptionPane fenetreErreur = new JOptionPane();
        fenetreErreur.showMessageDialog(fenetre, erreurS,"Erreur",JOptionPane.ERROR_MESSAGE);
    }
            
    
}