package Gerant;

import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Code.ErreurBD;
import Code.Jeu;

public class AjouterJeu {

	private JFrame frmAjouterUnJeu;
	private JTextField inputGameName;
	private JButton buttonValidation;
	private JSpinner spinnerGamelength;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterJeu window = new AjouterJeu();
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
	public AjouterJeu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAjouterUnJeu = new JFrame();
		frmAjouterUnJeu.setTitle("Ajouter un jeu");
		final int FRAMESIZE = 350;
		frmAjouterUnJeu.setBounds(100, 100, FRAMESIZE, FRAMESIZE);
		frmAjouterUnJeu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAjouterUnJeu.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitle = new JPanel();
		frmAjouterUnJeu.getContentPane().add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelSpacing_TopTitle = new JPanel();
		panelTitle.add(panelSpacing_TopTitle);
		
		JLabel lblAjouterUnJeu = new JLabel("Ajouter un jeu");
		lblAjouterUnJeu.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjouterUnJeu.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelTitle.add(lblAjouterUnJeu);
		
		JPanel panelForm = new JPanel();
		frmAjouterUnJeu.getContentPane().add(panelForm, BorderLayout.CENTER);
		panelForm.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelFormGameName = new JPanel();
		panelForm.add(panelFormGameName);
		panelFormGameName.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panelFormGameName.add(panel);
		
		JPanel panelLabelGameName = new JPanel();
		FlowLayout fl_panelLabelGameName = (FlowLayout) panelLabelGameName.getLayout();
		fl_panelLabelGameName.setVgap(10);
		fl_panelLabelGameName.setAlignment(FlowLayout.LEFT);
		fl_panelLabelGameName.setHgap(25);
		panelFormGameName.add(panelLabelGameName);
		
		JLabel labelGameName = new JLabel("Nom du jeu :");
		labelGameName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLabelGameName.add(labelGameName);
		
		JPanel panelInputGameName = new JPanel();
		FlowLayout fl_panelInputGameName = (FlowLayout) panelInputGameName.getLayout();
		fl_panelInputGameName.setAlignment(FlowLayout.LEFT);
		fl_panelInputGameName.setHgap(50);
		panelFormGameName.add(panelInputGameName);
		
		inputGameName = new JTextField();
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
		
		JPanel panelFormGameLengh = new JPanel();
		panelForm.add(panelFormGameLengh);
		panelFormGameLengh.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelLabelGameLengh = new JPanel();
		FlowLayout fl_panelLabelGameLengh = (FlowLayout) panelLabelGameLengh.getLayout();
		fl_panelLabelGameLengh.setVgap(10);
		fl_panelLabelGameLengh.setAlignment(FlowLayout.LEFT);
		fl_panelLabelGameLengh.setHgap(25);
		panelFormGameLengh.add(panelLabelGameLengh);
		
		JLabel labelGameLengh = new JLabel("Dur\u00E9e d'une partie :");
		labelGameLengh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLabelGameLengh.add(labelGameLengh);
		
		JPanel panelInputGameLengh = new JPanel();
		FlowLayout fl_panelInputGameLengh = (FlowLayout) panelInputGameLengh.getLayout();
		fl_panelInputGameLengh.setAlignment(FlowLayout.LEFT);
		fl_panelInputGameLengh.setHgap(50);
		panelFormGameLengh.add(panelInputGameLengh);
		
		JPanel panelSpinnerGameLengh = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelSpinnerGameLengh.getLayout();
		flowLayout_1.setVgap(0);
		panelInputGameLengh.add(panelSpinnerGameLengh);
		
		spinnerGamelength = new JSpinner();
		panelSpinnerGameLengh.add(spinnerGamelength);
		spinnerGamelength.setModel(new SpinnerNumberModel(0, 0, 300, 5));
		
		JLabel labelSpinnerGameLengh = new JLabel("Minutes");
		panelSpinnerGameLengh.add(labelSpinnerGameLengh);
		labelSpinnerGameLengh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JPanel panel_1 = new JPanel();
		panelFormGameLengh.add(panel_1);
		
		JPanel panelFormButtons = new JPanel();
		FlowLayout fl_panelFormButtons = (FlowLayout) panelFormButtons.getLayout();
		fl_panelFormButtons.setHgap(20);
		fl_panelFormButtons.setAlignment(FlowLayout.RIGHT);
		frmAjouterUnJeu.getContentPane().add(panelFormButtons, BorderLayout.SOUTH);
		
		JPanel panelFormButtonsInner = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelFormButtonsInner.getLayout();
		flowLayout.setAlignOnBaseline(true);
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelFormButtons.add(panelFormButtonsInner);
		
		JButton buttonCancel = new JButton("Annuler");
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmAjouterUnJeu.dispose();
			}
		});
		panelFormButtonsInner.add(buttonCancel);
		
		buttonValidation = new JButton("Cr\u00E9er");
		buttonValidation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (buttonValidation.isEnabled()) {
					Jeu gameAdded = new Jeu(inputGameName.getText(), Integer.valueOf(spinnerGamelength.getValue().toString()));
					try {
						gameAdded.insert();
					} catch (IllegalArgumentException | ErreurBD e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.printf("Erreur, ajout du jeu impossible, veuillez verifier votre saisie");
					}
					frmAjouterUnJeu.dispose();
				}
			}
		});
		buttonValidation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonValidation.setEnabled(false);
		panelFormButtonsInner.add(buttonValidation);
	}

}
