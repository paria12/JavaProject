package Ecurie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import Commons.ColorArrowUI;
import Commons.Colors;
import Commons.JButtonDark;
import Commons.JButtonYellow;
import Commons.JPanelBackground;
import Commons.JTextFieldDark;
import code.Equipe;
import code.ErreurBD;
import code.Jeu;
import code.Joueur;

public class CreerEquipe {

	private JFrame frame;
	private JTextFieldDark inputTeamName;
	private JTextFieldDark outputPlayerName;
	private JButtonYellow buttonValidation;
	private Joueur J1;
	private Joueur J2;
	private Joueur J3;
	private Joueur J4;
	private CreerEquipe teamWindow = this;
	private JButtonDark buttonAddPlayer;
	private JComboBox<String> comboGame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreerEquipe window = new CreerEquipe(1);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void MainWithValue(int IdEcurie) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreerEquipe window = new CreerEquipe(IdEcurie);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreerEquipe(int Ecurie) {
		initialize(Ecurie);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int Ecurie) {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanelBackground panelTitle = new JPanelBackground();
		frame.getContentPane().add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelSpacing_TopTitle = new JPanelBackground();
		panelTitle.add(panelSpacing_TopTitle);
		
		JLabel lblCrerUnequipe = new JLabel("Cr\u00E9er une \u00E9quipe");
		lblCrerUnequipe.setForeground(Colors.lightText);
		lblCrerUnequipe.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrerUnequipe.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelTitle.add(lblCrerUnequipe);
		
		JPanelBackground panelInputTeamName = new JPanelBackground();
		panelTitle.add(panelInputTeamName);
		
		JLabel labelInputTeamName = new JLabel("Nom de l'\u00E9quipe :");
		labelInputTeamName.setForeground(Colors.lightText);
		labelInputTeamName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelInputTeamName.add(labelInputTeamName);
		
		inputTeamName = new JTextFieldDark();
		inputTeamName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				buttonValidation.setEnabled(teamWindow.isFilled());
			}
		});
		inputTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		panelInputTeamName.add(inputTeamName);
		inputTeamName.setColumns(15);
		
		JPanelBackground panelSpacing_BottomTitle = new JPanelBackground();
		panelTitle.add(panelSpacing_BottomTitle);
		
		JPanelBackground panelForm = new JPanelBackground();
		frame.getContentPane().add(panelForm, BorderLayout.CENTER);
		panelForm.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelPlayer1 = new JPanelBackground();
		FlowLayout fl_panelPlayer1 = (FlowLayout) panelPlayer1.getLayout();
		fl_panelPlayer1.setAlignment(FlowLayout.LEFT);
		fl_panelPlayer1.setHgap(50);
		panelForm.add(panelPlayer1);
		
		JPanelBackground panelPlayer1Inner = new JPanelBackground();
		panelPlayer1.add(panelPlayer1Inner);
		
		this.setJoueurWindow(J1, "1", panelPlayer1Inner);
		
		JPanelBackground panelPlayer2 = new JPanelBackground();
		FlowLayout fl_panelPlayer2 = (FlowLayout) panelPlayer2.getLayout();
		fl_panelPlayer2.setAlignment(FlowLayout.LEFT);
		fl_panelPlayer2.setHgap(50);
		panelForm.add(panelPlayer2);
		
		JPanelBackground panelPlayer2Inner = new JPanelBackground();
		panelPlayer2.add(panelPlayer2Inner);
		
		this.setJoueurWindow(J2, "2", panelPlayer2Inner);
		
		JPanelBackground panelPlayer3 = new JPanelBackground();
		FlowLayout fl_panelPlayer3 = (FlowLayout) panelPlayer3.getLayout();
		fl_panelPlayer3.setAlignment(FlowLayout.LEFT);
		fl_panelPlayer3.setHgap(50);
		panelForm.add(panelPlayer3);
		
		JPanelBackground panelPlayer3Inner = new JPanelBackground();
		panelPlayer3.add(panelPlayer3Inner);
		
		this.setJoueurWindow(J3, "3", panelPlayer3Inner);
		
		JPanelBackground panelPlayer4 = new JPanelBackground();
		FlowLayout fl_panelPlayer4 = (FlowLayout) panelPlayer4.getLayout();
		fl_panelPlayer4.setAlignment(FlowLayout.LEFT);
		fl_panelPlayer4.setHgap(50);
		panelForm.add(panelPlayer4);
		
		JPanelBackground panelPlayer4Inner = new JPanelBackground();
		panelPlayer4.add(panelPlayer4Inner);
		
		this.setJoueurWindow(J4, "4", panelPlayer4Inner);
		
		JPanelBackground panelGame = new JPanelBackground();
		FlowLayout fl_panelGame = (FlowLayout) panelGame.getLayout();
		fl_panelGame.setAlignment(FlowLayout.LEFT);
		fl_panelGame.setHgap(50);
		panelForm.add(panelGame);
		
		JPanelBackground panelFormGame = new JPanelBackground();
		panelGame.add(panelFormGame);
		panelFormGame.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelComboGame = new JPanelBackground();
		panelFormGame.add(panelComboGame);
		
		JLabel labelGame = new JLabel("Jeu : ");
		labelGame.setForeground(Colors.lightText);
		panelComboGame.add(labelGame);
		
		try {
			comboGame = new JComboBox<String>();
			comboGame.setBackground(Colors.darkestBlue);
			comboGame.setForeground(Colors.lightText);
			comboGame.setUI(ColorArrowUI.createUI(comboGame));
			comboGame.setModel(new DefaultComboBoxModel(Jeu.getAll()));
		} catch (ErreurBD e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		panelComboGame.add(comboGame);
		
		JPanelBackground panelFormButtons = new JPanelBackground();
		FlowLayout flowLayout = (FlowLayout) panelFormButtons.getLayout();
		flowLayout.setHgap(20);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		frame.getContentPane().add(panelFormButtons, BorderLayout.SOUTH);
		
		JPanelBackground panelFormButtonsInner = new JPanelBackground();
		panelFormButtons.add(panelFormButtonsInner);
		
		JButtonDark buttonCancel = new JButtonDark("Annuler");
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		panelFormButtonsInner.add(buttonCancel);
		
		buttonValidation = new JButtonYellow("Cr\u00E9er");
		buttonValidation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (buttonValidation.isEnabled()) {
					Equipe equipe;
					try {
						equipe = new Equipe(inputTeamName.getText(), 0, Jeu.getID(new Jeu(comboGame.getSelectedItem().toString())));
						equipe.addJoueur(J1);
						equipe.addJoueur(J2);
						equipe.addJoueur(J3);
						equipe.addJoueur(J4);
						equipe.insert(Ecurie);
						J1.insert(equipe.getID());
						J2.insert(equipe.getID());
						J3.insert(equipe.getID());
						J4.insert(equipe.getID());
						frame.dispose();
					} catch (IllegalArgumentException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (ErreurBD e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					
				}
			}
		});
		buttonValidation.setEnabled(isFilled());
		buttonValidation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelFormButtonsInner.add(buttonValidation);
		teamWindow = this;
	}
	
	public boolean isFilled() {
		if ((inputTeamName.getText().length() != 0) && (J1 != null) && (J2 != null) && (J3 != null) && (J4 != null)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setJoueur(Joueur newJ, String nbJ, JPanelBackground panelPlayerInner) {
		switch (nbJ) {
		case "1" :
			J1 = newJ;
			break;
		case "2" :
			J2 = newJ;
			break;
		case "3" :
			J3 = newJ;
		case "4" :
			J4 = newJ;
			break;
		}
		this.setJoueurWindow(newJ, nbJ, panelPlayerInner);
		buttonValidation.setEnabled(isFilled());
	}
	
	public void setJoueurWindow(Joueur J, String nbJ, JPanelBackground panelPlayerInner) {
		if (J != null) {
			panelPlayerInner.removeAll();
			JLabel labelPlayer = new JLabel("Joueur "+nbJ+" :");
			labelPlayer.setForeground(Colors.lightText);
			panelPlayerInner.add(labelPlayer);
			outputPlayerName = new JTextFieldDark();
			Border line = BorderFactory.createLineBorder(Color.black);
			Border empty = new EmptyBorder(3, 2, 3, 2);
			CompoundBorder border = new CompoundBorder(line, empty);
			outputPlayerName.setBorder(border);
			outputPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
			outputPlayerName.setText(J.getPrenom() + " " + J.getNom());
			panelPlayerInner.add(outputPlayerName);
			outputPlayerName.setEditable(false);
			outputPlayerName.setColumns(15);
			
			
			JButtonDark buttonChangePlayer = new JButtonDark("Changer");
			buttonChangePlayer.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						AjouterJoueur.MainWithValues(J.getNom(), J.getPrenom(), J.getDateNaissance(), J.getSexe(), J.getTel(), J.getEmail(), teamWindow, nbJ, panelPlayerInner);
					} catch (ErreurBD e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			buttonChangePlayer.setFont(new Font("Tahoma", Font.PLAIN, 10));
			panelPlayerInner.add(buttonChangePlayer);
			frame.revalidate();
			frame.repaint();
		} else { 
			panelPlayerInner.removeAll();
			JLabel labelPlayer = new JLabel("Joueur "+nbJ+" :");
			labelPlayer.setForeground(Colors.lightText);
			panelPlayerInner.add(labelPlayer);
			buttonAddPlayer = new JButtonDark("Nouveau Joueur");
			buttonAddPlayer.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					AjouterJoueur.MainWithValues(null, null, null, ' ', null, null, teamWindow, nbJ, panelPlayerInner);
				}
			});
			panelPlayerInner.add(buttonAddPlayer);
		}
	}
		
}