package vueEcurie;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import modele.Equipe;
import modele.ErreurBD;
import modele.Tournoi;
import vue.Colors;
import vue.ErrorMessage;
import vue.JButtonYellow;
import vue.JPanelBackground;

import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Timestamp;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PopUp_ConfirmDeleteTeam {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public PopUp_ConfirmDeleteTeam(Equipe equipe) {
		initialize(equipe);
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Equipe equipe) {
		frame = new JFrame();
		frame.setBounds(100, 100, 250, 175);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Confirmation de suppression ?");
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanelBackground panelHeader = new JPanelBackground();
		frame.getContentPane().add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelLabelConfirmer = new JPanelBackground();
		panelHeader.add(panelLabelConfirmer);
		
		JLabel labelConfirmez = new JLabel("\u00CAtes-vous s\u00FBr de vouloir");
		labelConfirmez.setForeground(Colors.lightText);
		labelConfirmez.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLabelConfirmer.add(labelConfirmez);
		
		JPanelBackground panelLabelEquipe = new JPanelBackground();
		panelHeader.add(panelLabelEquipe);
		
		JLabel labelEquipe = new JLabel("Supprimer "+equipe.getNom());
		labelEquipe.setForeground(Colors.lightText);
		labelEquipe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLabelEquipe.add(labelEquipe);
		
		JPanelBackground panelLabelDate = new JPanelBackground();
		panelHeader.add(panelLabelDate);
		
		JPanelBackground panelMenu = new JPanelBackground();
		frame.getContentPane().add(panelMenu, BorderLayout.CENTER);
		panelMenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanelBackground panelButtonNo = new JPanelBackground();
		FlowLayout fl_panelButtonNo = (FlowLayout) panelButtonNo.getLayout();
		panelMenu.add(panelButtonNo);
		
		JButtonYellow buttonNo = new JButtonYellow("Non");
		panelButtonNo.add(buttonNo);
		
		JPanelBackground panelButtonYes = new JPanelBackground();
		FlowLayout fl_panelButtonYes = (FlowLayout) panelButtonYes.getLayout();
		panelMenu.add(panelButtonYes);
		
		JButtonYellow buttonYes = new JButtonYellow("Oui");
		panelButtonYes.add(buttonYes);
	}
	
	public void dispose() {
		frame.dispose();
	}
	
}