package Commons;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Ecurie.PopUp_ConfirmDeleteTeam;
import Gerant.AccueilGerant;
import Gerant.PopUp_ConfirmDeleteTournoi;
import code.ErreurBD;
import code.Jeu;
import code.Joueur;
import code.Tournoi;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelPresentationTournoi extends JPanel {

	private Tournoi t;
	
	private static JFrame frame;
	private JPanelDarkest panelTournoi;
	private JPanelDarkest panelInfoTournois;
	private JPanelDarkest panelOperationsOnPlayer;
	private JPanelDarkest panelSpacingCenterInfos;
	private JPanelDarkest[] panelsJoueur;
	private JTextFieldDark[] textFieldsJoueur;
	private JLabel labelNomTournoi;
	private JButtonDark buttonDelete;
	private JTextFieldDark txtfldrkquipes;
	private JPanelDarkest panelLabelNomAndDate;
	private JPanelDarkest panelButtonDelete;
	private JPanelDarkest panelNumberEquipe;
	private JPanelDarkest panelSpacingCenterInfos_1;
	private JLabel labelDateTournoi;
	private JPanelDarkest panelGameInner;
	private JLabel labelJeu;
	private JPanelDarkest panelLabelJoueurs;
	private JPanelDarkest panelLabelNotoriete;
	private JLabel labelNotoriete;
	private JPanelDarkest panelLabelLieu;
	private JLabel labelLieu;
	private AccueilGerant parent;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelPresentationTournoi window1 = new PanelPresentationTournoi(Tournoi.getAll()[0], true, new AccueilGerant());
					PanelPresentationTournoi window2 = new PanelPresentationTournoi(Tournoi.getAll()[1], false, new AccueilGerant());
					frame = new JFrame();
					frame.setBounds(100, 100, 600, 400);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					JPanelDarkest panel1 = new JPanelDarkest();
					panel1.setLayout(new GridLayout(0, 1, 0, 0));
					panel1.add(window1);
					panel1.add(window2);
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
	public PanelPresentationTournoi(Tournoi t, boolean authDelete, AccueilGerant parent) {
		this.parent = parent;
		this.t = t;
		panelTournoi = new JPanelDarkest();
		panelTournoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelTournoi.setBackground(Colors.backBlue);
				panelInfoTournois.setBackground(Colors.backBlue);
				panelOperationsOnPlayer.setBackground(Colors.backBlue);
				panelSpacingCenterInfos.setBackground(Colors.backBlue);
				panelLabelNomAndDate.setBackground(Colors.backBlue);
				panelButtonDelete.setBackground(Colors.backBlue);
				panelNumberEquipe.setBackground(Colors.backBlue);
				panelSpacingCenterInfos_1.setBackground(Colors.backBlue);
				panelGameInner.setBackground(Colors.backBlue);
				panelLabelJoueurs.setBackground(Colors.backBlue);
				panelLabelNotoriete.setBackground(Colors.backBlue);
				panelLabelLieu.setBackground(Colors.backBlue);
				txtfldrkquipes.setBackground(Colors.darkestBlue);
				if (authDelete) {
					buttonDelete.setBackground(Colors.darkestBlue);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelTournoi.setBackground(Colors.darkestBlue);
				panelInfoTournois.setBackground(Colors.darkestBlue);
				panelOperationsOnPlayer.setBackground(Colors.darkestBlue);
				panelSpacingCenterInfos.setBackground(Colors.darkestBlue);
				panelLabelNomAndDate.setBackground(Colors.darkestBlue);
				panelButtonDelete.setBackground(Colors.darkestBlue);
				panelNumberEquipe.setBackground(Colors.darkestBlue);
				panelSpacingCenterInfos_1.setBackground(Colors.darkestBlue);
				panelGameInner.setBackground(Colors.darkestBlue);
				panelLabelJoueurs.setBackground(Colors.darkestBlue);
				panelLabelNotoriete.setBackground(Colors.darkestBlue);
				panelLabelLieu.setBackground(Colors.darkestBlue);
				txtfldrkquipes.setBackground(Colors.backBlue);
				if (authDelete) {
					buttonDelete.setBackground(Colors.backBlue);
				}
			}
		});
		panelTournoi.setBorder(new LineBorder(Color.black));
		
		panelTournoi.setLayout(new BorderLayout(0, 0));
		
		JPanelDarkest panelJoueurs = new JPanelDarkest();
		panelTournoi.add(panelJoueurs, BorderLayout.SOUTH);
		panelJoueurs.setLayout(new BorderLayout(0, 0));
		
		
		
		panelInfoTournois = new JPanelDarkest();
		panelTournoi.add(panelInfoTournois, BorderLayout.CENTER);
		panelInfoTournois.setLayout(new BorderLayout(0, 0));
		
		panelLabelNomAndDate = new JPanelDarkest();
		FlowLayout flowLayout_3 = (FlowLayout) panelLabelNomAndDate.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEADING);
		panelInfoTournois.add(panelLabelNomAndDate);
		
		labelNomTournoi = new JLabel(t.getNom());
		panelLabelNomAndDate.add(labelNomTournoi);
		labelNomTournoi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelNomTournoi.setForeground(Colors.lightText);
		labelNomTournoi.setHorizontalAlignment(SwingConstants.LEFT);
		
		panelSpacingCenterInfos = new JPanelDarkest();
		panelLabelNomAndDate.add(panelSpacingCenterInfos);
		
		labelDateTournoi = new JLabel(t.getDate().toString());
		labelDateTournoi.setForeground(Colors.lightText);
		panelLabelNomAndDate.add(labelDateTournoi);
		
		panelLabelJoueurs = new JPanelDarkest();
		panelInfoTournois.add(panelLabelJoueurs, BorderLayout.SOUTH);
		panelLabelJoueurs.setLayout(new GridLayout(0, 1, 0, 0));
		
		panelLabelNotoriete = new JPanelDarkest();
		FlowLayout flowLayout_1 = (FlowLayout) panelLabelNotoriete.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelLabelJoueurs.add(panelLabelNotoriete);
		
		try {
			labelNotoriete = new JLabel("Tournoi "+t.getNotoriete().toLowerCase());
		} catch (ErreurBD e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		labelNotoriete.setForeground(Colors.lightText);
		panelLabelNotoriete.add(labelNotoriete);
		
		panelLabelLieu = new JPanelDarkest();
		FlowLayout flowLayout_4 = (FlowLayout) panelLabelLieu.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panelLabelJoueurs.add(panelLabelLieu);
		
		try {
			labelLieu = new JLabel(t.getPays().toUpperCase()+" - "+t.getCodePostal().toUpperCase()+" "+t.getVille()+", "+t.getAdresse());
		} catch (ErreurBD e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		labelLieu.setForeground(Colors.lightText);
		panelLabelLieu.add(labelLieu);
		
		panelOperationsOnPlayer = new JPanelDarkest();
		panelTournoi.add(panelOperationsOnPlayer, BorderLayout.EAST);
		panelOperationsOnPlayer.setLayout(new BorderLayout(0, 0));
		
		panelNumberEquipe = new JPanelDarkest();
		FlowLayout flowLayout_2 = (FlowLayout) panelNumberEquipe.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panelOperationsOnPlayer.add(panelNumberEquipe, BorderLayout.NORTH);
		
		txtfldrkquipes = new JTextFieldDark();
		panelNumberEquipe.add(txtfldrkquipes);
		try {
			txtfldrkquipes.setText(t.selectEquipe().size()+"/16 \u00E9quipes");
		} catch (ErreurBD e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtfldrkquipes.setEditable(false);
		txtfldrkquipes.setColumns(10);
		txtfldrkquipes.setBackground(new Color(63, 60, 98));
		
		panelButtonDelete = new JPanelDarkest();
		FlowLayout flowLayout = (FlowLayout) panelButtonDelete.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelOperationsOnPlayer.add(panelButtonDelete);
		if (authDelete) {
			buttonDelete = new JButtonDark("Supprimer");
			buttonDelete.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					PopUp_ConfirmDeleteTournoi.mainWithValues(t, parent);
				}
			});
			panelButtonDelete.add(buttonDelete);
			buttonDelete.setBackground(Colors.backBlue);
			buttonDelete.setForeground(Colors.lightText);
		}
		panelGameInner = new JPanelDarkest();
		FlowLayout flowLayout_5 = (FlowLayout) panelGameInner.getLayout();
		flowLayout_5.setAlignment(FlowLayout.RIGHT);
		panelOperationsOnPlayer.add(panelGameInner, BorderLayout.SOUTH);
		
		try {
			labelJeu = new JLabel("Tournoi de "+ Jeu.getNomFromID(t.getJeu()));
		labelJeu.setForeground(new Color(150, 180, 180));
		panelGameInner.add(labelJeu);
		} catch (ErreurBD e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		panelSpacingCenterInfos_1 = new JPanelDarkest();
		panelTournoi.add(panelSpacingCenterInfos_1, BorderLayout.SOUTH);
		panelSpacingCenterInfos_1.setLayout(new BorderLayout(0, 0));
		
	}
	
	public JPanelDarkest getPanel() {
		return this.panelTournoi;
	}
	
	public Tournoi getTournoi() {
		return this.t;
	}
	
	public void changeBorderColor(Color c, int width) {
		panelTournoi.setBorder(new LineBorder(c, width));
	}

}
