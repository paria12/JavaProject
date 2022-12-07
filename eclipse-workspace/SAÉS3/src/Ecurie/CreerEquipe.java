package Ecurie;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreerEquipe {

	private JFrame frame;
	private JTextField inputTeamName;
	private JTextField outputPlayerName;
	private JButton buttonValidation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreerEquipe window = new CreerEquipe();
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
	public CreerEquipe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitle = new JPanel();
		frame.getContentPane().add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelSpacing_TopTitle = new JPanel();
		panelTitle.add(panelSpacing_TopTitle);
		
		JLabel lblCrerUnequipe = new JLabel("Cr\u00E9er une \u00E9quipe");
		lblCrerUnequipe.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrerUnequipe.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelTitle.add(lblCrerUnequipe);
		
		JPanel panelInputTeamName = new JPanel();
		panelTitle.add(panelInputTeamName);
		
		JLabel labelInputTeamName = new JLabel("Nom de l'\u00E9quipe :");
		labelInputTeamName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelInputTeamName.add(labelInputTeamName);
		
		inputTeamName = new JTextField();
		inputTeamName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (inputTeamName.getText().length() != 0) {
					buttonValidation.setEnabled(true);
				} else {
					buttonValidation.setEnabled(false);
				}
			}
		});
		inputTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		panelInputTeamName.add(inputTeamName);
		inputTeamName.setColumns(15);
		
		JPanel panelSpacing_BottomTitle = new JPanel();
		panelTitle.add(panelSpacing_BottomTitle);
		
		JPanel panelForm = new JPanel();
		frame.getContentPane().add(panelForm, BorderLayout.CENTER);
		panelForm.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelPlayer1 = new JPanel();
		FlowLayout fl_panelPlayer1 = (FlowLayout) panelPlayer1.getLayout();
		fl_panelPlayer1.setAlignment(FlowLayout.LEFT);
		fl_panelPlayer1.setHgap(50);
		panelForm.add(panelPlayer1);
		
		JPanel panelPlayer1Inner = new JPanel();
		panelPlayer1.add(panelPlayer1Inner);
		
		JLabel labelPlayer1 = new JLabel("Joueur 1 :");
		panelPlayer1Inner.add(labelPlayer1);
		
		outputPlayerName = new JTextField();
		outputPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
		outputPlayerName.setText("John Doe");
		panelPlayer1Inner.add(outputPlayerName);
		outputPlayerName.setEditable(false);
		outputPlayerName.setColumns(15);
		
		JButton buttonChangePlayer = new JButton("Changer");
		buttonChangePlayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AjouterJoueur.main(null);
			}
		});
		buttonChangePlayer.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panelPlayer1Inner.add(buttonChangePlayer);
		
		JPanel panelPlayer2 = new JPanel();
		FlowLayout fl_panelPlayer2 = (FlowLayout) panelPlayer2.getLayout();
		fl_panelPlayer2.setAlignment(FlowLayout.LEFT);
		fl_panelPlayer2.setHgap(50);
		panelForm.add(panelPlayer2);
		
		JPanel panelPlayer2Inner = new JPanel();
		panelPlayer2.add(panelPlayer2Inner);
		
		JLabel labelPlayer2 = new JLabel("Joueur 2 :");
		panelPlayer2Inner.add(labelPlayer2);
		
		JButton buttonAddPlayer2 = new JButton("Nouveau Joueur");
		buttonAddPlayer2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AjouterJoueur.main(null);
			}
		});
		panelPlayer2Inner.add(buttonAddPlayer2);
		
		JPanel panelPlayer3 = new JPanel();
		FlowLayout fl_panelPlayer3 = (FlowLayout) panelPlayer3.getLayout();
		fl_panelPlayer3.setAlignment(FlowLayout.LEFT);
		fl_panelPlayer3.setHgap(50);
		panelForm.add(panelPlayer3);
		
		JPanel panelPlayer3Inner = new JPanel();
		panelPlayer3.add(panelPlayer3Inner);
		
		JLabel labelPlayer3 = new JLabel("Joueur 3 :");
		panelPlayer3Inner.add(labelPlayer3);
		
		JButton buttonAddPlayer3 = new JButton("Nouveau Joueur");
		buttonAddPlayer3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AjouterJoueur.main(null);
			}
		});
		panelPlayer3Inner.add(buttonAddPlayer3);
		
		JPanel panelPlayer4 = new JPanel();
		FlowLayout fl_panelPlayer4 = (FlowLayout) panelPlayer4.getLayout();
		fl_panelPlayer4.setAlignment(FlowLayout.LEFT);
		fl_panelPlayer4.setHgap(50);
		panelForm.add(panelPlayer4);
		
		JPanel panelPlayer4Inner = new JPanel();
		panelPlayer4.add(panelPlayer4Inner);
		
		JLabel labelPlayer4 = new JLabel("Joueur 4 :");
		panelPlayer4Inner.add(labelPlayer4);
		
		JButton buttonAddPlayer4 = new JButton("Nouveau Joueur");
		buttonAddPlayer4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AjouterJoueur.main(null);
			}
		});
		panelPlayer4Inner.add(buttonAddPlayer4);
		
		JPanel panelGame = new JPanel();
		FlowLayout fl_panelGame = (FlowLayout) panelGame.getLayout();
		fl_panelGame.setAlignment(FlowLayout.LEFT);
		fl_panelGame.setHgap(50);
		panelForm.add(panelGame);
		
		JPanel panelFormGame = new JPanel();
		panelGame.add(panelFormGame);
		panelFormGame.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelComboGame = new JPanel();
		panelFormGame.add(panelComboGame);
		
		JLabel labelGame = new JLabel("Jeu : ");
		panelComboGame.add(labelGame);
		
		JComboBox<String> comboGame = new JComboBox<String>();
		comboGame.setModel(new DefaultComboBoxModel(new String[] {"League of Legends", "Rocket League"}));
		panelComboGame.add(comboGame);
		
		JPanel panelFormButtons = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelFormButtons.getLayout();
		flowLayout.setHgap(20);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		frame.getContentPane().add(panelFormButtons, BorderLayout.SOUTH);
		
		JPanel panelFormButtonsInner = new JPanel();
		panelFormButtons.add(panelFormButtonsInner);
		
		JButton buttonCancel = new JButton("Annuler");
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		panelFormButtonsInner.add(buttonCancel);
		
		buttonValidation = new JButton("Cr\u00E9er");
		buttonValidation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (buttonValidation.isEnabled()) {
					frame.dispose();
				}
			}
		});
		buttonValidation.setEnabled(false);
		buttonValidation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelFormButtonsInner.add(buttonValidation);
	}

}
