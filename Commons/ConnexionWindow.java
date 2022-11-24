package Commons;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import code.Connexion;
import Gerant.AcceuilGerant;
import Ecurie.AcceuilEcurie;
import Arbitre.AcceuilArbitre;

public class ConnexionWindow {

	private JFrame frmConnexion;
	private JTextField inputUserName;
	private JPasswordField inputPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnexionWindow window = new ConnexionWindow();
					window.frmConnexion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConnexionWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConnexion = new JFrame();
		frmConnexion.setTitle("Connexion");
		final int FRAMESIZE = 350;
		frmConnexion.setBounds(100, 100, FRAMESIZE, FRAMESIZE);
		frmConnexion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConnexion.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitle = new JPanel();
		frmConnexion.getContentPane().add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelSpacing_TopTitle = new JPanel();
		panelTitle.add(panelSpacing_TopTitle);
		
		JLabel labelTitle = new JLabel("Connexion");
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelTitle.add(labelTitle);
		
		JPanel panelSpacing_BottomTitle = new JPanel();
		panelTitle.add(panelSpacing_BottomTitle);
		
		JPanel panelForm = new JPanel();
		frmConnexion.getContentPane().add(panelForm, BorderLayout.CENTER);
		panelForm.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelUserNameForm = new JPanel();
		panelForm.add(panelUserNameForm);
		panelUserNameForm.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelUserNameLabel = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panelUserNameLabel.getLayout();
		flowLayout_3.setHgap(25);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		flowLayout_3.setVgap(2);
		panelUserNameForm.add(panelUserNameLabel);
		
		JLabel labelUserNameInput = new JLabel("Nom d'utilisateur :");
		labelUserNameInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelUserNameLabel.add(labelUserNameInput);
		
		JPanel panelUserNameInput = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panelUserNameInput.getLayout();
		flowLayout_2.setHgap(25);
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		flowLayout_2.setVgap(2);
		panelUserNameForm.add(panelUserNameInput);
		
		inputUserName = new JTextField();
		inputUserName.setColumns(FRAMESIZE/15);
		panelUserNameInput.add(inputUserName);
		
		JPanel panelPasswordForm = new JPanel();
		panelForm.add(panelPasswordForm);
		panelPasswordForm.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelPasswordLabel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelPasswordLabel.getLayout();
		flowLayout_1.setHgap(25);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		flowLayout_1.setVgap(2);
		panelPasswordForm.add(panelPasswordLabel);
		
		JLabel labelPasswordInput = new JLabel("Mot de passe :");
		labelPasswordInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelPasswordLabel.add(labelPasswordInput);
		
		JPanel panelPasswordInput = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelPasswordInput.getLayout();
		flowLayout.setHgap(25);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setVgap(2);
		panelPasswordForm.add(panelPasswordInput);
		
		inputPassword = new JPasswordField();
		inputPassword.setColumns(FRAMESIZE/15);
		inputPassword.setEchoChar('*');
		panelPasswordInput.add(inputPassword);
		
		JPanel panelValidationButton = new JPanel();
		frmConnexion.getContentPane().add(panelValidationButton, BorderLayout.SOUTH);
		panelValidationButton.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelSpacing_2 = new JPanel();
		panelValidationButton.add(panelSpacing_2);
		
		JPanel panelButton = new JPanel();
		FlowLayout fl_panelButton = (FlowLayout) panelButton.getLayout();
		fl_panelButton.setHgap(12);
		fl_panelButton.setAlignment(FlowLayout.RIGHT);
		fl_panelButton.setVgap(10);
		panelValidationButton.add(panelButton);
		
		JButton buttonValidation = new JButton("Se connecter");
		buttonValidation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					switch (Connexion.connexion(inputUserName.getText(), String.valueOf(inputPassword.getPassword()))) {
						case 0 : //Owner
							AcceuilGerant.main(null);
							frmConnexion.dispose();
							break;
						case 1 : //Ecurie
							AcceuilEcurie.main(null);
							frmConnexion.dispose();
							break;
						case 2 : //Arbitre
							AcceuilArbitre.main(null);
							frmConnexion.dispose();
							break;
						case -1 : //Wrong Password;
							System.out.printf("Erreur : L'identifiant ou le Mot de passe est éronné, réessayez.");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panelButton.add(buttonValidation);
	}

}
