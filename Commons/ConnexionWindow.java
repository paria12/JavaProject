package Commons;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Arbitre.AcceuilArbitre;
import Ecurie.AcceuilEcurie;
import Gerant.AcceuilGerant;
import code.Connexion;

public class ConnexionWindow{

	private JFrame frmConnexion;
	private JTextFieldDark inputUserName;
	private JPasswordField inputPassword;
	private JDialog erreur;

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
		
		JPanelBackground panelTitle = new JPanelBackground();
		frmConnexion.getContentPane().add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelSpacing_TopTitle = new JPanelBackground();
		panelTitle.add(panelSpacing_TopTitle);
		
		JLabel labelTitle = new JLabel("Connexion");
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		labelTitle.setForeground(Colors.lightText);
		panelTitle.add(labelTitle);
		
		JPanelBackground panelSpacing_BottomTitle = new JPanelBackground();
		panelTitle.add(panelSpacing_BottomTitle);
		
		JPanelBackground panelForm = new JPanelBackground();
		frmConnexion.getContentPane().add(panelForm, BorderLayout.CENTER);
		panelForm.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelUserNameForm = new JPanelBackground();
		panelForm.add(panelUserNameForm);
		panelUserNameForm.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelUserNameLabel = new JPanelBackground();
		FlowLayout flowLayout_3 = (FlowLayout) panelUserNameLabel.getLayout();
		flowLayout_3.setHgap(25);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		flowLayout_3.setVgap(2);
		panelUserNameForm.add(panelUserNameLabel);
		
		JLabel labelUserNameInput = new JLabel("Nom d'utilisateur :");
		labelUserNameInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelUserNameLabel.add(labelUserNameInput);
		labelUserNameInput.setForeground(Colors.lightText);
		
		JPanelBackground panelUserNameInput = new JPanelBackground();
		FlowLayout flowLayout_2 = (FlowLayout) panelUserNameInput.getLayout();
		flowLayout_2.setHgap(25);
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		flowLayout_2.setVgap(2);
		panelUserNameForm.add(panelUserNameInput);
		
		inputUserName = new JTextFieldDark();
		inputUserName.setColumns(FRAMESIZE/15);
		panelUserNameInput.add(inputUserName);
		
		JPanelBackground panelPasswordForm = new JPanelBackground();
		panelForm.add(panelPasswordForm);
		panelPasswordForm.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelPasswordLabel = new JPanelBackground();
		FlowLayout flowLayout_1 = (FlowLayout) panelPasswordLabel.getLayout();
		flowLayout_1.setHgap(25);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		flowLayout_1.setVgap(2);
		panelPasswordForm.add(panelPasswordLabel);
		
		JLabel labelPasswordInput = new JLabel("Mot de passe :");
		labelPasswordInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelPasswordInput.setForeground(Colors.lightText);
		panelPasswordLabel.add(labelPasswordInput);
		
		JPanelBackground panelPasswordInput = new JPanelBackground();
		FlowLayout flowLayout = (FlowLayout) panelPasswordInput.getLayout();
		flowLayout.setHgap(25);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setVgap(2);
		panelPasswordForm.add(panelPasswordInput);
		
		inputPassword = new JPasswordField();
		inputPassword.setColumns(FRAMESIZE/15);
		inputPassword.setForeground(Colors.lightText);
		inputPassword.setBackground(Colors.darkestBlue);
		inputPassword.setBorder(new LineBorder(Color.black));
		inputPassword.setEchoChar('✦');
		inputPassword.setCaretColor(Colors.lightText);
		panelPasswordInput.add(inputPassword);
		
		JPanelBackground panelValidationButton = new JPanelBackground();
		frmConnexion.getContentPane().add(panelValidationButton, BorderLayout.SOUTH);
		panelValidationButton.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelSpacing_2 = new JPanelBackground();
		panelValidationButton.add(panelSpacing_2);
		
		JPanelBackground panelButton = new JPanelBackground();
		FlowLayout fl_panelButton = (FlowLayout) panelButton.getLayout();
		fl_panelButton.setHgap(12);
		fl_panelButton.setAlignment(FlowLayout.RIGHT);
		fl_panelButton.setVgap(10);
		panelValidationButton.add(panelButton);
		
		JButtonYellow buttonValidation = new JButtonYellow("Se connecter");
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
							JOptionPane.showMessageDialog(erreur, "Votre identifiant ou votre mot de passe est incorrect", "Erreur",JOptionPane.ERROR_MESSAGE);
							System.out.println("Erreur : L'identifiant ou le Mot de passe est éronné, réessayez." + inputUserName.getText() + String.valueOf(inputPassword.getPassword()));
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