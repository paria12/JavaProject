package Commons;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Enumeration;

import Gerant.AccueilGerant;
import modele.Arbitre;
import modele.Ecurie;
import modele.ErreurBD;
import Ecurie.AccueilEcurie;
import Arbitre.AccueilArbitre;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

public class Inscription{

	private JFrame frmInscription;
	private JTextFieldDark inputUserName;
	private JPasswordField inputPassword;
	private JButtonYellow buttonValidation;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inscription window = new Inscription(null);
					window.frmInscription.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void mainWithValues(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inscription window = new Inscription(login);
					window.frmInscription.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Inscription(String login) {
		initialize(login);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String login) {
		frmInscription = new JFrame();
		frmInscription.setTitle("E-Sporter | Inscription");
		frmInscription.setLocationRelativeTo(null);
		final int FRAMESIZE = 350;
		frmInscription.setBounds(100, 100, 450, 350);
		frmInscription.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInscription.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanelBackground panelTitle = new JPanelBackground();
		frmInscription.getContentPane().add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelSpacing_TopTitle = new JPanelBackground();
		panelTitle.add(panelSpacing_TopTitle);
		
		JLabel labelTitle = new JLabel("Inscription");
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		labelTitle.setForeground(Colors.lightText);
		panelTitle.add(labelTitle);
		
		JPanelBackground panelSpacing_BottomTitle = new JPanelBackground();
		panelTitle.add(panelSpacing_BottomTitle);
		
		JPanelBackground panelForm = new JPanelBackground();
		frmInscription.getContentPane().add(panelForm, BorderLayout.CENTER);
		panelForm.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelUserType = new JPanelBackground();
		panelForm.add(panelUserType);
		panelUserType.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelUserTypeLabel = new JPanelBackground();
		FlowLayout fl_panelUserTypeLabel = (FlowLayout) panelUserTypeLabel.getLayout();
		fl_panelUserTypeLabel.setHgap(25);
		fl_panelUserTypeLabel.setAlignment(FlowLayout.LEFT);
		panelUserType.add(panelUserTypeLabel);
		
		JLabel labelUserType = new JLabel("Vous êtes :");
		labelUserType.setForeground(new Color(150, 180, 180));
		labelUserType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelUserTypeLabel.add(labelUserType);
		
		JPanelBackground panelUserTypeInput = new JPanelBackground();
		FlowLayout fl_panelUserTypeInput = (FlowLayout) panelUserTypeInput.getLayout();
		fl_panelUserTypeInput.setHgap(25);
		panelUserType.add(panelUserTypeInput);
		
		JRadioDark rdbtnNewRadioButton_1 = new JRadioDark("Une écurie pro.");
		rdbtnNewRadioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonValidation.setEnabled(isFormFilled());
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_1);
		panelUserTypeInput.add(rdbtnNewRadioButton_1);
		
		JRadioDark rdbtnNewRadioButton_2 = new JRadioDark("Une écurie asso.");
		rdbtnNewRadioButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonValidation.setEnabled(isFormFilled());
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_2);
		panelUserTypeInput.add(rdbtnNewRadioButton_2);
		
		JRadioDark rdbtnNewRadioButton = new JRadioDark("Un.e arbitre");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonValidation.setEnabled(isFormFilled());
			}
		});
		buttonGroup.add(rdbtnNewRadioButton);
		panelUserTypeInput.add(rdbtnNewRadioButton);
		
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
		inputUserName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					submit();
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				buttonValidation.setEnabled(isFormFilled());
			}
		});
		inputUserName.setColumns(FRAMESIZE/15);
		inputUserName.setText(login);
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
		inputPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					submit();
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				buttonValidation.setEnabled(isFormFilled());
			}
		});
		inputPassword.setColumns(FRAMESIZE/15);
		inputPassword.setForeground(Colors.lightText);
		inputPassword.setBackground(Colors.darkestBlue);
		inputPassword.setBorder(new LineBorder(Color.black));
		inputPassword.setEchoChar('✦');
		inputPassword.setCaretColor(Colors.lightText);
		panelPasswordInput.add(inputPassword);
		
		JPanelBackground panelValidationButton = new JPanelBackground();
		frmInscription.getContentPane().add(panelValidationButton, BorderLayout.SOUTH);
		panelValidationButton.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelButton = new JPanelBackground();
		FlowLayout fl_panelButton = (FlowLayout) panelButton.getLayout();
		fl_panelButton.setHgap(12);
		fl_panelButton.setAlignment(FlowLayout.RIGHT);
		fl_panelButton.setVgap(10);
		panelValidationButton.add(panelButton);
		
		buttonValidation = new JButtonYellow("Se connecter");
		buttonValidation.setEnabled(false);
		buttonValidation.setText("S'inscrire");
		buttonValidation.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					submit();
				}
			}
		});
		buttonValidation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submit();
			}
		});
		
		JButtonDark buttonConnexion = new JButtonDark("se connecter");
		buttonConnexion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					frmInscription.dispose();
					ConnexionWindow.mainWithValues(inputUserName.getText());
				}
			}
		});
		buttonConnexion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmInscription.dispose();
				ConnexionWindow.mainWithValues(inputUserName.getText());
			}
		});
		panelButton.add(buttonConnexion);
		panelButton.add(buttonValidation);
	}
	
	private boolean isFormFilled() {
		if (inputUserName.getText().length() != 0) {
			if (inputPassword.getPassword().length != 0) {
				for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
		            AbstractButton button = buttons.nextElement();
		            if (button.isSelected()) {
		            	return true;
		            }
		        }
			}
		}
		return false;
	}
	
	private void submit() {
		if (buttonValidation.isEnabled()) {
			String selectedButton = null;
			for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
	            AbstractButton button = buttons.nextElement();
	            if (button.isSelected()) {
	                selectedButton = button.getText();
	            }
	        }
			switch(selectedButton) {
				case "Un.e arbitre" :
					try {
						new Arbitre(inputUserName.getText()).insert(String.valueOf(inputPassword.getPassword()));
					} catch (ErreurBD e) {
						// TODO Auto-generated catch block
						ErrorMessage.ErrorMessage(e.getMessage());
					};
					break;
				case "Une écurie pro." :
					try {
						new Ecurie(inputUserName.getText(), "Professionnelle").insert(String.valueOf(inputPassword.getPassword()));
					} catch (ErreurBD e) {
						// TODO Auto-generated catch block
						ErrorMessage.ErrorMessage(e.getMessage());
					};
					break;
				case "Une écurie asso." :
					try {
						new Ecurie(inputUserName.getText(), "Associatif").insert(String.valueOf(inputPassword.getPassword()));
					} catch (ErreurBD e) {
						// TODO Auto-generated catch block
						ErrorMessage.ErrorMessage(e.getMessage());
					};
					break;
			}
			frmInscription.dispose();
			ConnexionWindow.mainWithValues(inputUserName.getText());
		}
	}
 
}