package vueEcurie;


import javax.swing.JFrame;
import java.awt.BorderLayout;

import modele.Equipe;
import modele.Tournoi;
import vue.Colors;
import vue.JButtonYellow;
import vue.JPanelBackground;

import javax.swing.JLabel;

import controleur.ControleurEcurie;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;

public class PopUp_ConfirmInscription {

	private JFrame frame;

	
	/**
	 * Create the application.
	 */
	public PopUp_ConfirmInscription(Equipe equipe,Tournoi tournoi) {
		initialize(equipe,tournoi);
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Equipe equipe,Tournoi tournoi) {
		frame = new JFrame();
		frame.setBounds(100, 100, 250, 250);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Confirmation Inscription ?");
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanelBackground panelHeader = new JPanelBackground();
		frame.getContentPane().add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new GridLayout(0, 1, 0, 0));

		JPanelBackground panelSpacing_HeaderTop = new JPanelBackground();
		panelHeader.add(panelSpacing_HeaderTop);
		
		JPanelBackground panelLabelSouhaitez = new JPanelBackground();
		panelHeader.add(panelLabelSouhaitez);
		
		JLabel labelSouhaitez = new JLabel("Souhaitez-vous inscrire");
		labelSouhaitez.setForeground(Colors.lightText);
		labelSouhaitez.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLabelSouhaitez.add(labelSouhaitez);
		
		JPanelBackground panelLabelEquipe = new JPanelBackground();
		panelHeader.add(panelLabelEquipe);
		
		JLabel labelEquipe = new JLabel(equipe.getNom()+" au tournoi ");
		labelEquipe.setForeground(Colors.lightText);
		labelEquipe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLabelEquipe.add(labelEquipe);
		
		JPanelBackground panelLabelNom = new JPanelBackground();
		panelHeader.add(panelLabelNom);
		
		JLabel labelNom = new JLabel(tournoi.getNom());
		labelNom.setForeground(Colors.lightText);
		labelNom.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelLabelNom.add(labelNom);
		
		JPanelBackground panelLabelDate = new JPanelBackground();
		panelHeader.add(panelLabelDate);
		
		JLabel labelDate = new JLabel("du "+tournoi.getDate()+" ?");
		labelDate.setForeground(Colors.lightText);
		labelDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLabelDate.add(labelDate);
		
		JPanelBackground panelMenu = new JPanelBackground();
		frame.getContentPane().add(panelMenu, BorderLayout.CENTER);
		panelMenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanelBackground panelButtonNo = new JPanelBackground();
		FlowLayout fl_panelButtonNo = (FlowLayout) panelButtonNo.getLayout();
		fl_panelButtonNo.setVgap(20);
		panelMenu.add(panelButtonNo);
		
		JButtonYellow buttonNo = new JButtonYellow("Non");
		buttonNo.addActionListener(ControleurEcurie.getInstance());
		panelButtonNo.add(buttonNo);
		
		JPanelBackground panelButtonYes = new JPanelBackground();
		FlowLayout fl_panelButtonYes = (FlowLayout) panelButtonYes.getLayout();
		fl_panelButtonYes.setVgap(20);
		panelMenu.add(panelButtonYes);
		
		JButtonYellow buttonYes = new JButtonYellow("Oui");
		buttonYes.addActionListener(ControleurEcurie.getInstance());
		panelButtonYes.add(buttonYes);
		

		frame.addWindowListener(ControleurEcurie.getInstance());
	}
	
	public void dispose() {
		frame.dispose();
	}
	
}
