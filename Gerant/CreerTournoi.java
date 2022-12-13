package Gerant;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;

import Commons.Colors;
import Commons.JButtonDark;
import Commons.JButtonYellow;
import Commons.JTextFieldDark;
import code.ErreurBD;
import code.Jeu;
import Commons.JPanelBackground;
import Commons.JRadioDark;

import java.awt.Color;
import javax.swing.JButton;
import Commons.JSpinnerDark;

import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ButtonGroup;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreerTournoi {

	private JFrame frmCrerTournois;
	private JTextFieldDark inputCoord;
	private JTextFieldDark inputCountry;
	private JTextFieldDark inputCity;
	private JTextFieldDark inputAdress;
	private JTextFieldDark inputPostalCode;
	private JPanelBackground panelSpacing_BottomFormCity;
	private JTextFieldDark inputTurnamentName;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButtonYellow buttonValidation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreerTournoi window = new CreerTournoi();
					window.frmCrerTournois.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ErreurBD 
	 */
	public CreerTournoi() throws ErreurBD {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ErreurBD 
	 */
	private void initialize() throws ErreurBD {
		frmCrerTournois = new JFrame();
		frmCrerTournois.setTitle("Cr\u00E9er Tournois");
		//frmCrerTournois.setSize(1000, 800);
		frmCrerTournois.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmCrerTournois.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCrerTournois.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanelBackground panelTitle = new JPanelBackground();
		frmCrerTournois.getContentPane().add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelSpacing_TopTitle = new JPanelBackground();
		panelTitle.add(panelSpacing_TopTitle);
		
		JLabel lblCrerUnTournoi = new JLabel("Cr\u00E9er un nouveau tournoi");
		lblCrerUnTournoi.setForeground(Colors.lightText);
		lblCrerUnTournoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrerUnTournoi.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelTitle.add(lblCrerUnTournoi);
		
		JPanelBackground panelSpacing_BottomTitle = new JPanelBackground();
		panelTitle.add(panelSpacing_BottomTitle);
		
		JPanelBackground panelForm = new JPanelBackground();
		frmCrerTournois.getContentPane().add(panelForm, BorderLayout.CENTER);
		panelForm.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanelBackground panelFormLocation = new JPanelBackground();
		panelFormLocation.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelForm.add(panelFormLocation);
		panelFormLocation.setLayout(new BorderLayout(0, 0));
		
		JPanelBackground panelTitleLocation = new JPanelBackground();
		panelFormLocation.add(panelTitleLocation, BorderLayout.NORTH);
		panelTitleLocation.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblLieu = new JLabel("Lieu :");
		lblLieu.setForeground(Colors.lightText);
		lblLieu.setHorizontalAlignment(SwingConstants.CENTER);
		lblLieu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelTitleLocation.add(lblLieu);
		
		JPanelBackground panelSpacing_BottomTitleLieu = new JPanelBackground();
		panelTitleLocation.add(panelSpacing_BottomTitleLieu);
		
		JPanelBackground panelMap = new JPanelBackground();
		panelMap.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFormLocation.add(panelMap, BorderLayout.CENTER);
		
		JPanelBackground panelCoord = new JPanelBackground();
		FlowLayout flowLayout = (FlowLayout) panelCoord.getLayout();
		flowLayout.setHgap(25);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelFormLocation.add(panelCoord, BorderLayout.SOUTH);
		
		JPanelBackground panelLabelCoord = new JPanelBackground();
		panelCoord.add(panelLabelCoord);
		
		JLabel labelCoord = new JLabel("Coordonn\u00E9es :");
		labelCoord.setForeground(Colors.lightText);
		panelLabelCoord.add(labelCoord);
		
		inputCoord = new JTextFieldDark();
		panelLabelCoord.add(inputCoord);
		inputCoord.setColumns(10);
		
		JPanelBackground panelFormLocationInputs = new JPanelBackground();
		panelFormLocation.add(panelFormLocationInputs, BorderLayout.EAST);
		panelFormLocationInputs.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelFormCountry = new JPanelBackground();
		panelFormLocationInputs.add(panelFormCountry);
		panelFormCountry.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelLabelCountry = new JPanelBackground();
		FlowLayout fl_panelLabelCountry = (FlowLayout) panelLabelCountry.getLayout();
		fl_panelLabelCountry.setHgap(25);
		fl_panelLabelCountry.setAlignment(FlowLayout.LEFT);
		panelFormCountry.add(panelLabelCountry);
		
		JLabel labelCountry = new JLabel("Pays :");
		labelCountry.setForeground(Colors.lightText);
		labelCountry.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelCountry.add(labelCountry);
		
		JPanelBackground panelInputCountry = new JPanelBackground();
		panelFormCountry.add(panelInputCountry);
		
		inputCountry = new JTextFieldDark();
		inputCountry.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (isFormFilled()) {
					buttonValidation.setEnabled(true);
				} else {
					buttonValidation.setEnabled(false);
				}
			}
		});
		panelInputCountry.add(inputCountry);
		inputCountry.setColumns(15);
		
		JPanelBackground panelSpacing_BottomFormCountry = new JPanelBackground();
		panelFormCountry.add(panelSpacing_BottomFormCountry);
		
		JPanelBackground panelFormCity = new JPanelBackground();
		panelFormLocationInputs.add(panelFormCity);
		panelFormCity.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelLabelCity = new JPanelBackground();
		FlowLayout fl_panelLabelCity = (FlowLayout) panelLabelCity.getLayout();
		fl_panelLabelCity.setHgap(25);
		fl_panelLabelCity.setAlignment(FlowLayout.LEFT);
		panelFormCity.add(panelLabelCity);
		
		JLabel labelCity = new JLabel("Ville :");
		labelCity.setForeground(Colors.lightText);
		labelCity.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelCity.add(labelCity);
		
		JPanelBackground panelInputCity = new JPanelBackground();
		panelFormCity.add(panelInputCity);
		
		inputCity = new JTextFieldDark();
		inputCity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (isFormFilled()) {
					buttonValidation.setEnabled(true);
				} else {
					buttonValidation.setEnabled(false);
				}
			}
		});
		inputCity.setColumns(15);
		panelInputCity.add(inputCity);
		
		panelSpacing_BottomFormCity = new JPanelBackground();
		panelFormCity.add(panelSpacing_BottomFormCity);
		
		JPanelBackground panelFormAdress = new JPanelBackground();
		panelFormLocationInputs.add(panelFormAdress);
		panelFormAdress.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelLabelAdress = new JPanelBackground();
		FlowLayout fl_panelLabelAdress = (FlowLayout) panelLabelAdress.getLayout();
		fl_panelLabelAdress.setAlignment(FlowLayout.LEFT);
		fl_panelLabelAdress.setHgap(25);
		panelFormAdress.add(panelLabelAdress);
		
		JLabel labelAdress = new JLabel("Adresse :");
		labelAdress.setForeground(Colors.lightText);
		labelAdress.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelAdress.add(labelAdress);
		
		JPanelBackground panelInputAdress = new JPanelBackground();
		panelFormAdress.add(panelInputAdress);
		
		inputAdress = new JTextFieldDark();
		inputAdress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (isFormFilled()) {
					buttonValidation.setEnabled(true);
				} else {
					buttonValidation.setEnabled(false);
				}
			}
		});
		inputAdress.setColumns(15);
		panelInputAdress.add(inputAdress);
		
		JPanelBackground panelSpacing_BottomFormAdress = new JPanelBackground();
		panelFormAdress.add(panelSpacing_BottomFormAdress);
		
		JPanelBackground panelFormPostalCode = new JPanelBackground();
		panelFormLocationInputs.add(panelFormPostalCode);
		panelFormPostalCode.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelLabelPostalCode = new JPanelBackground();
		FlowLayout fl_panelLabelPostalCode = (FlowLayout) panelLabelPostalCode.getLayout();
		fl_panelLabelPostalCode.setAlignment(FlowLayout.LEFT);
		fl_panelLabelPostalCode.setHgap(25);
		panelFormPostalCode.add(panelLabelPostalCode);
		
		JLabel labelPostalCode = new JLabel("Code Postal :");
		labelPostalCode.setForeground(Colors.lightText);
		labelPostalCode.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelPostalCode.add(labelPostalCode);
		
		JPanelBackground panelInputPostalCode = new JPanelBackground();
		panelFormPostalCode.add(panelInputPostalCode);
		
		inputPostalCode = new JTextFieldDark();
		inputPostalCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (isFormFilled()) {
					buttonValidation.setEnabled(true);
				} else {
					buttonValidation.setEnabled(false);
				}
			}
		});
		inputPostalCode.setColumns(15);
		panelInputPostalCode.add(inputPostalCode);
		
		JPanelBackground panelSpacing_BottomFormPostalCode = new JPanelBackground();
		panelFormPostalCode.add(panelSpacing_BottomFormPostalCode);
		
		JPanelBackground panel = new JPanelBackground();
		panelFormLocationInputs.add(panel);
		
		JPanelBackground panelSpacing_LeftLocation = new JPanelBackground();
		panelFormLocation.add(panelSpacing_LeftLocation, BorderLayout.WEST);
		
		JPanelBackground panelFormTurnament = new JPanelBackground();
		FlowLayout flowLayout_2 = (FlowLayout) panelFormTurnament.getLayout();
		flowLayout_2.setHgap(25);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panelForm.add(panelFormTurnament);
		
		JPanelBackground panelFormTurnamentInner = new JPanelBackground();
		panelFormTurnament.add(panelFormTurnamentInner);
		panelFormTurnamentInner.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelFormTurnamentName = new JPanelBackground();
		panelFormTurnamentInner.add(panelFormTurnamentName);
		panelFormTurnamentName.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelLabelTurnamentName = new JPanelBackground();
		FlowLayout fl_panelLabelTurnamentName = (FlowLayout) panelLabelTurnamentName.getLayout();
		fl_panelLabelTurnamentName.setAlignment(FlowLayout.LEFT);
		panelFormTurnamentName.add(panelLabelTurnamentName);
		
		JLabel labelTurnamentName = new JLabel("Nom du tournoi :");
		labelTurnamentName.setForeground(Colors.lightText);
		panelLabelTurnamentName.add(labelTurnamentName);
		
		JPanelBackground panelInputTurnamentName = new JPanelBackground();
		FlowLayout fl_panelInputTurnamentName = (FlowLayout) panelInputTurnamentName.getLayout();
		fl_panelInputTurnamentName.setVgap(0);
		fl_panelInputTurnamentName.setHgap(25);
		panelFormTurnamentName.add(panelInputTurnamentName);
		
		inputTurnamentName = new JTextFieldDark();
		inputTurnamentName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (isFormFilled()) {
					buttonValidation.setEnabled(true);
				} else {
					buttonValidation.setEnabled(false);
				}
			}
		});
		panelInputTurnamentName.add(inputTurnamentName);
		inputTurnamentName.setColumns(25);
		
		JPanelBackground panelFormDate = new JPanelBackground();
		panelFormTurnamentInner.add(panelFormDate);
		panelFormDate.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelLabelDate = new JPanelBackground();
		FlowLayout fl_panelLabelDate = (FlowLayout) panelLabelDate.getLayout();
		fl_panelLabelDate.setAlignment(FlowLayout.LEFT);
		panelFormDate.add(panelLabelDate);
		
		JLabel labelDate = new JLabel("Date du tournoi :");
		labelDate.setForeground(Colors.lightText);
		panelLabelDate.add(labelDate);
		
		JPanelBackground panelDateInput = new JPanelBackground();
		FlowLayout fl_panelDateInput = (FlowLayout) panelDateInput.getLayout();
		fl_panelDateInput.setHgap(25);
		fl_panelDateInput.setVgap(0);
		fl_panelDateInput.setAlignment(FlowLayout.LEFT);
		panelFormDate.add(panelDateInput);
		
		JPanelBackground panelInputDateInner = new JPanelBackground();
		panelDateInput.add(panelInputDateInner);
		
		JSpinnerDark spinnerDateDay = new JSpinnerDark(1, 1, 32, 1);
		panelInputDateInner.add(spinnerDateDay);
		
		JLabel labelSeparatorDateLeft = new JLabel("/");
		labelSeparatorDateLeft.setForeground(Colors.lightText);
		panelInputDateInner.add(labelSeparatorDateLeft);
		
		JSpinnerDark spinnerDateMonth = new JSpinnerDark(1, 1, 12, 1);
		panelInputDateInner.add(spinnerDateMonth);
		
		JLabel labelSeparatorDateRight = new JLabel("/");
		labelSeparatorDateRight.setForeground(Colors.lightText);
		panelInputDateInner.add(labelSeparatorDateRight);
		
		JSpinner spinnerDateYear = new JSpinner();
		panelInputDateInner.add(spinnerDateYear);
		spinnerDateYear.setModel(new SpinnerNumberModel(new Integer(2022), new Integer(2022), null, new Integer(1)));
		
		JPanelBackground panelFormTurnamentType = new JPanelBackground();
		panelFormTurnamentInner.add(panelFormTurnamentType);
		panelFormTurnamentType.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelLabelTurnamentType = new JPanelBackground();
		FlowLayout fl_panelLabelTurnamentType = (FlowLayout) panelLabelTurnamentType.getLayout();
		fl_panelLabelTurnamentType.setAlignment(FlowLayout.LEFT);
		panelFormTurnamentType.add(panelLabelTurnamentType);
		
		JLabel labelTurnamentType = new JLabel("Type de tournoi :");
		labelTurnamentType.setForeground(Colors.lightText);
		labelTurnamentType.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelTurnamentType.add(labelTurnamentType);
		
		JPanelBackground panelInputTurnamentType = new JPanelBackground();
		FlowLayout fl_panelInputTurnamentType = (FlowLayout) panelInputTurnamentType.getLayout();
		fl_panelInputTurnamentType.setAlignment(FlowLayout.LEFT);
		fl_panelInputTurnamentType.setVgap(0);
		fl_panelInputTurnamentType.setHgap(25);
		panelFormTurnamentType.add(panelInputTurnamentType);
		
		JPanelBackground panelRadios = new JPanelBackground();
		panelInputTurnamentType.add(panelRadios);
		
		JRadioDark radioTurnamentTypeLocal = new JRadioDark("Local");
		radioTurnamentTypeLocal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isFormFilled()) {
					buttonValidation.setEnabled(true);
				} else {
					buttonValidation.setEnabled(false);
				}
			}
		});
		buttonGroup.add(radioTurnamentTypeLocal);
		panelRadios.add(radioTurnamentTypeLocal);
		
		JRadioDark radioTurnamentTypeNationnal = new JRadioDark("Nationnal");
		radioTurnamentTypeNationnal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isFormFilled()) {
					buttonValidation.setEnabled(true);
				} else {
					buttonValidation.setEnabled(false);
				}
			}
		});
		buttonGroup.add(radioTurnamentTypeNationnal);
		panelRadios.add(radioTurnamentTypeNationnal);
		
		JRadioDark radioTurnamentTypeInternationnal = new JRadioDark("Internationnal");
		radioTurnamentTypeInternationnal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isFormFilled()) {
					buttonValidation.setEnabled(true);
				} else {
					buttonValidation.setEnabled(false);
				}
			}
		});
		buttonGroup.add(radioTurnamentTypeInternationnal);
		panelRadios.add(radioTurnamentTypeInternationnal);
		
		JPanelBackground panelFormGame = new JPanelBackground();
		panelFormTurnamentInner.add(panelFormGame);
		panelFormGame.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelLabelGame = new JPanelBackground();
		FlowLayout fl_panelLabelGame = (FlowLayout) panelLabelGame.getLayout();
		fl_panelLabelGame.setAlignment(FlowLayout.LEFT);
		panelFormGame.add(panelLabelGame);
		
		JLabel labelGame = new JLabel("Jeu :");
		labelGame.setForeground(Colors.lightText);
		labelGame.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelGame.add(labelGame);
		
		JPanelBackground panelInputGame = new JPanelBackground();
		FlowLayout fl_panelInputGame = (FlowLayout) panelInputGame.getLayout();
		fl_panelInputGame.setAlignment(FlowLayout.LEFT);
		fl_panelInputGame.setVgap(0);
		fl_panelInputGame.setHgap(25);
		panelFormGame.add(panelInputGame);
		
		JPanelBackground panelComboGame = new JPanelBackground();
		panelInputGame.add(panelComboGame);
		
		JComboBox<String> comboGame = new JComboBox<String>();
		comboGame.setModel(new DefaultComboBoxModel(Jeu.getAll()));
		panelComboGame.add(comboGame);
		
		JButtonYellow buttonAddGame = new JButtonYellow("+");
		buttonAddGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AjouterJeu.main(null);
			}
		});
		buttonAddGame.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelComboGame.add(buttonAddGame);
		
		JPanelBackground panelFormButtons = new JPanelBackground();
		FlowLayout fl_panelFormButtons = (FlowLayout) panelFormButtons.getLayout();
		fl_panelFormButtons.setHgap(25);
		fl_panelFormButtons.setAlignment(FlowLayout.RIGHT);
		frmCrerTournois.getContentPane().add(panelFormButtons, BorderLayout.SOUTH);
		
		JPanelBackground panelFormButtonsInner = new JPanelBackground();
		FlowLayout flowLayout_3 = (FlowLayout) panelFormButtonsInner.getLayout();
		flowLayout_3.setAlignOnBaseline(true);
		panelFormButtons.add(panelFormButtonsInner);
		
		JButtonDark buttonCancel = new JButtonDark("Annuler");
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmCrerTournois.dispose();
			}
		});
		panelFormButtonsInner.add(buttonCancel);
		
		buttonValidation = new JButtonYellow("Cr\u00E9er");
		buttonValidation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (buttonValidation.isEnabled()) {
					frmCrerTournois.dispose();
				}
			}
		});
		buttonValidation.setEnabled(false);
		panelFormButtonsInner.add(buttonValidation);
		buttonValidation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JPanelBackground panelSpacing_Left = new JPanelBackground();
		FlowLayout flowLayout_1 = (FlowLayout) panelSpacing_Left.getLayout();
		flowLayout_1.setHgap(25);
		frmCrerTournois.getContentPane().add(panelSpacing_Left, BorderLayout.WEST);
	}
	
	private Boolean isFormFilled() {
		if ((inputCountry.getText().length() != 0) 
		&& (inputCity.getText().length() != 0) 
		&& (inputAdress.getText().length() != 0) 
		&& (inputPostalCode.getText().length() != 0) 
		&& (inputTurnamentName.getText().length() != 0) 
		&& (buttonGroup.getSelection() != null)) {
			return true;
		} else {
			return false;
		}
	}

}
