package Gerant;

import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Commons.Colors;
import Commons.ErrorMessage;
import Commons.JButtonYellow;
import Commons.JPanelBackground;
import Commons.JSpinnerDark;
import Commons.JTextFieldDark;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.ErreurBD;
import code.Jeu;

public class AjouterJeu {

	private JFrame frmAjouterUnJeu;
	private JTextFieldDark inputGameName;
	private JButtonYellow buttonValidation;
	private JSpinnerDark spinnerGamelength;
	private CreerTournoi parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterJeu window = new AjouterJeu(null);
					window.frmAjouterUnJeu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void mainWithValues(CreerTournoi parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterJeu window = new AjouterJeu(parent);
					window.frmAjouterUnJeu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AjouterJeu(CreerTournoi parent) {
		initialize(parent);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CreerTournoi parent) {
		this.parent = parent;
		frmAjouterUnJeu = new JFrame();
		frmAjouterUnJeu.setTitle("E-Sporter | Ajouter un jeu");
		frmAjouterUnJeu.setLocationRelativeTo(null);
		final int FRAMESIZE = 350;
		frmAjouterUnJeu.setBounds(100, 100, FRAMESIZE, FRAMESIZE);
		frmAjouterUnJeu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAjouterUnJeu.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanelBackground panelTitle = new JPanelBackground();
		frmAjouterUnJeu.getContentPane().add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelSpacing_TopTitle = new JPanelBackground();
		panelTitle.add(panelSpacing_TopTitle);
		
		JLabel lblAjouterUnJeu = new JLabel("Ajouter un jeu");
		lblAjouterUnJeu.setForeground(Colors.lightText);
		lblAjouterUnJeu.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjouterUnJeu.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelTitle.add(lblAjouterUnJeu);
		
		JPanelBackground panelForm = new JPanelBackground();
		frmAjouterUnJeu.getContentPane().add(panelForm, BorderLayout.CENTER);
		panelForm.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelFormGameName = new JPanelBackground();
		panelForm.add(panelFormGameName);
		panelFormGameName.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panel = new JPanelBackground();
		panelFormGameName.add(panel);
		
		JPanelBackground panelLabelGameName = new JPanelBackground();
		FlowLayout fl_panelLabelGameName = (FlowLayout) panelLabelGameName.getLayout();
		fl_panelLabelGameName.setVgap(10);
		fl_panelLabelGameName.setAlignment(FlowLayout.LEFT);
		fl_panelLabelGameName.setHgap(25);
		panelFormGameName.add(panelLabelGameName);
		
		JLabel labelGameName = new JLabel("Nom du jeu :");
		labelGameName.setForeground(Colors.lightText);
		labelGameName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLabelGameName.add(labelGameName);
		
		JPanelBackground panelInputGameName = new JPanelBackground();
		FlowLayout fl_panelInputGameName = (FlowLayout) panelInputGameName.getLayout();
		fl_panelInputGameName.setAlignment(FlowLayout.LEFT);
		fl_panelInputGameName.setHgap(50);
		panelFormGameName.add(panelInputGameName);
		
		inputGameName = new JTextFieldDark();
		inputGameName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (inputGameName.getText().length() != 0) {
					buttonValidation.setEnabled(true);
				} else {
					buttonValidation.setEnabled(false);
				}
			}
		});
		panelInputGameName.add(inputGameName);
		inputGameName.setColumns(20);
		
		JPanelBackground panelFormGameLength = new JPanelBackground();
		panelForm.add(panelFormGameLength);
		panelFormGameLength.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelLabelGameLength = new JPanelBackground();
		FlowLayout fl_panelLabelGameLength = (FlowLayout) panelLabelGameLength.getLayout();
		fl_panelLabelGameLength.setVgap(10);
		fl_panelLabelGameLength.setAlignment(FlowLayout.LEFT);
		fl_panelLabelGameLength.setHgap(25);
		panelFormGameLength.add(panelLabelGameLength);
		
		JLabel labelGameLength = new JLabel("Dur\u00E9e d'une partie :");
		labelGameLength.setForeground(Colors.lightText);
		labelGameLength.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLabelGameLength.add(labelGameLength);
		
		JPanelBackground panelInputGameLength = new JPanelBackground();
		FlowLayout fl_panelInputGameLength = (FlowLayout) panelInputGameLength.getLayout();
		fl_panelInputGameLength.setAlignment(FlowLayout.LEFT);
		fl_panelInputGameLength.setHgap(50);
		panelFormGameLength.add(panelInputGameLength);
		
		JPanelBackground panelSpinnerGameLength = new JPanelBackground();
		FlowLayout flowLayout_1 = (FlowLayout) panelSpinnerGameLength.getLayout();
		flowLayout_1.setVgap(0);
		panelInputGameLength.add(panelSpinnerGameLength);
		
		spinnerGamelength = new JSpinnerDark(0,0,300,5);
		panelSpinnerGameLength.add(spinnerGamelength);
		
		
		
		JLabel labelSpinnerGameLength = new JLabel("Minutes");
		labelSpinnerGameLength.setForeground(Colors.lightText);
		panelSpinnerGameLength.add(labelSpinnerGameLength);
		labelSpinnerGameLength.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JPanelBackground panel_1 = new JPanelBackground();
		panelFormGameLength.add(panel_1);
		
		JPanelBackground panelFormButtons = new JPanelBackground();
		FlowLayout fl_panelFormButtons = (FlowLayout) panelFormButtons.getLayout();
		fl_panelFormButtons.setHgap(20);
		fl_panelFormButtons.setAlignment(FlowLayout.RIGHT);
		frmAjouterUnJeu.getContentPane().add(panelFormButtons, BorderLayout.SOUTH);
		
		JPanelBackground panelFormButtonsInner = new JPanelBackground();
		FlowLayout flowLayout = (FlowLayout) panelFormButtonsInner.getLayout();
		flowLayout.setAlignOnBaseline(true);
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelFormButtons.add(panelFormButtonsInner);
		JButtonYellow buttonCancel = new JButtonYellow("Annuler");
		buttonCancel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					submitAnnuler();
				}
			}
		});
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitAnnuler();
			}
		});
		panelFormButtonsInner.add(buttonCancel);
		
		buttonValidation = new JButtonYellow("Cr\u00E9er");
		buttonValidation.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					submitCreer();
				}
			}
		});
		buttonValidation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitCreer();
			}
		});
		buttonValidation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonValidation.setEnabled(false);
		panelFormButtonsInner.add(buttonValidation);
	}
	private void submitAnnuler() {
		frmAjouterUnJeu.dispose();
	}
	private void submitCreer() {
		if (buttonValidation.isEnabled()) {
			Jeu gameAdded = new Jeu(inputGameName.getText(), Integer.valueOf(spinnerGamelength.getValue().toString()));
			try {
				gameAdded.insert();
			} catch (IllegalArgumentException | ErreurBD e1) {
				// TODO Auto-generated catch block
				ErrorMessage.ErrorMessage(e1.getMessage());
			}
			parent.refreshComboGame();
			frmAjouterUnJeu.dispose();
		}
	}
}
