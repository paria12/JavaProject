package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import modele.Equipe;
import modele.ErreurBD;
import modele.Joueur;
import vueEcurie.AccueilEcurie;
import vueEcurie.PopUp_ConfirmDeleteTeam;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelPresentationEquipe extends JPanel {

	private Equipe e;
	
	private static JFrame frame;
	private JPanelDarkest panelEquipe;
	private JTextFieldDark textFieldPoints;
	private JPanelDarkest panelLabelJoueurs;
	private JPanelDarkest panelNomsJoueurs;
	private JPanelDarkest panelInfoEquipes;
	private JPanelDarkest panelOperationsOnPlayer;
	private JPanelDarkest panelSpacingCenterInfos;
	private JPanelDarkest[] panelsJoueur;
	private JTextFieldDark[] textFieldsJoueur;
	private JLabel labelNomEquipe;
	private JButtonDark buttonDelete;
	private AccueilEcurie parent;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelPresentationEquipe window = new PanelPresentationEquipe(new Equipe(Equipe.getNomEquipe(1)[0]), new AccueilEcurie());
					PanelPresentationEquipe window2 = new PanelPresentationEquipe(new Equipe(Equipe.getNomEquipe(1)[1]), new AccueilEcurie());
					frame = new JFrame();
					frame.setBounds(100, 100, 600, 400);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					JPanelDarkest panel1 = new JPanelDarkest();
					panel1.setLayout(new GridLayout(0, 1, 0, 0));
					panel1.add(window.getPanel());
					panel1.add(window2.getPanel());
					frame.getContentPane().add(panel1, BorderLayout.CENTER);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PanelPresentationEquipe(Equipe e, AccueilEcurie parent) {
		this.parent = parent;
		this.e = e;
		panelEquipe = new JPanelDarkest();
		panelEquipe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelLabelJoueurs.setBackground(Colors.backBlue);
				panelNomsJoueurs.setBackground(Colors.backBlue);
				panelInfoEquipes.setBackground(Colors.backBlue);
				panelSpacingCenterInfos.setBackground(Colors.backBlue);
				for (JPanelDarkest panelJoueur : panelsJoueur) {
						panelJoueur.setBackground(Colors.backBlue);
				}
				textFieldPoints.setBackground(Colors.darkestBlue);
				for (JTextFieldDark textFieldJoueur : textFieldsJoueur) {
					textFieldJoueur.setBackground(Colors.darkestBlue);
				}
				panelOperationsOnPlayer.setBackground(Colors.backBlue);
				buttonDelete.setBackground(Colors.darkestBlue);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelLabelJoueurs.setBackground(Colors.darkestBlue);
				panelNomsJoueurs.setBackground(Colors.darkestBlue);
				panelInfoEquipes.setBackground(Colors.darkestBlue);
				panelSpacingCenterInfos.setBackground(Colors.darkestBlue);
				for (JPanelDarkest panelJoueur : panelsJoueur) {
						panelJoueur.setBackground(Colors.darkestBlue);
				}
				textFieldPoints.setBackground(Colors.backBlue);
				for (JTextFieldDark textFieldJoueur : textFieldsJoueur) {
					textFieldJoueur.setBackground(Colors.backBlue);
				}
				panelOperationsOnPlayer.setBackground(Colors.darkestBlue);
				buttonDelete.setBackground(Colors.backBlue);
			}
		});
		panelEquipe.setBorder(new LineBorder(Color.black));
		
		panelEquipe.setLayout(new BorderLayout(0, 0));
		
		JPanelDarkest panelJoueurs = new JPanelDarkest();
		panelEquipe.add(panelJoueurs, BorderLayout.SOUTH);
		panelJoueurs.setLayout(new BorderLayout(0, 0));
		
		panelLabelJoueurs = new JPanelDarkest();
		panelJoueurs.add(panelLabelJoueurs, BorderLayout.NORTH);
		panelLabelJoueurs.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel labelJoueurs = new JLabel("Joueurs :");
		labelJoueurs.setForeground(Colors.lightText);
		panelLabelJoueurs.add(labelJoueurs);
		
		panelNomsJoueurs = new JPanelDarkest();
		panelJoueurs.add(panelNomsJoueurs);
		
		try {
			panelsJoueur = new JPanelDarkest[e.getJoueur().size()];
			textFieldsJoueur = new JTextFieldDark[e.getJoueur().size()];
			for (int i = 0; i < e.getJoueur().size(); i++) {
				Joueur j = e.getJoueur().get(i);
				JPanelDarkest panelJoueur = new JPanelDarkest();
				panelNomsJoueurs.add(panelJoueur);
				panelsJoueur[i] = panelJoueur;
				JTextFieldDark textFieldJoueur = new JTextFieldDark();
				textFieldJoueur.setBackground(Colors.backBlue);
				textFieldJoueur.setEditable(false);
				textFieldJoueur.setText(j.getPrenom() + " " + j.getNom());
				panelJoueur.add(textFieldJoueur);
				textFieldJoueur.setColumns(10);
				textFieldsJoueur[i] = textFieldJoueur;
			}
		} catch (ErreurBD e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		panelInfoEquipes = new JPanelDarkest();
		FlowLayout fl_panelInfoEquipes = (FlowLayout) panelInfoEquipes.getLayout();
		fl_panelInfoEquipes.setAlignment(FlowLayout.LEFT);
		panelEquipe.add(panelInfoEquipes, BorderLayout.CENTER);
		
		labelNomEquipe = new JLabel(e.getNom());
		labelNomEquipe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelNomEquipe.setForeground(Colors.lightText);
		labelNomEquipe.setHorizontalAlignment(SwingConstants.LEFT);
		panelInfoEquipes.add(labelNomEquipe);
		
		panelSpacingCenterInfos = new JPanelDarkest();
		panelInfoEquipes.add(panelSpacingCenterInfos);
		
		textFieldPoints = new JTextFieldDark();
		try {
			textFieldPoints.setText(String.valueOf(e.getNbPoints()) + " points");
		} catch (ErreurBD e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		textFieldPoints.setEditable(false);
		textFieldPoints.setBackground(Colors.backBlue);
		panelInfoEquipes.add(textFieldPoints);
		textFieldPoints.setColumns(10);
		
		panelOperationsOnPlayer = new JPanelDarkest();
		panelEquipe.add(panelOperationsOnPlayer, BorderLayout.EAST);
		
		buttonDelete = new JButtonDark("Supprimer");
		buttonDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PopUp_ConfirmDeleteTeam.mainWithValues(getEquipe(), parent);
			}
		});
		buttonDelete.setBackground(Colors.backBlue);
		panelOperationsOnPlayer.add(buttonDelete);
		
	}
	
	public JPanelDarkest getPanel() {
		return this.panelEquipe;
	}
	
	public Equipe getEquipe() {
		return this.e;
	}
	
	public void changeBorderColor(Color c, int width) {
		panelEquipe.setBorder(new LineBorder(c, width));
	}

}
