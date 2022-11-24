package Gerant;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ButtonGroup;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreerTournoi {

	private JFrame frmCrerTournois;
	private JTextField inputCoord;
	private JTextField inputCountry;
	private JTextField inputCity;
	private JTextField inputAdress;
	private JTextField inputPostalCode;
	private JPanel panelSpacing_BottomFormCity;
	private JTextField inputTurnamentName;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton buttonValidation;

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
	 */
	public CreerTournoi() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrerTournois = new JFrame();
		frmCrerTournois.setTitle("Cr\u00E9er Tournois");
		//frmCrerTournois.setSize(1000, 800);
		frmCrerTournois.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmCrerTournois.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCrerTournois.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitle = new JPanel();
		frmCrerTournois.getContentPane().add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelSpacing_TopTitle = new JPanel();
		panelTitle.add(panelSpacing_TopTitle);
		
		JLabel lblCrerUnTournoi = new JLabel("Cr\u00E9er un nouveau tournoi");
		lblCrerUnTournoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrerUnTournoi.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelTitle.add(lblCrerUnTournoi);
		
		JPanel panelSpacing_BottomTitle = new JPanel();
		panelTitle.add(panelSpacing_BottomTitle);
		
		JPanel panelForm = new JPanel();
		frmCrerTournois.getContentPane().add(panelForm, BorderLayout.CENTER);
		panelForm.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelFormLocation = new JPanel();
		panelFormLocation.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelForm.add(panelFormLocation);
		panelFormLocation.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitleLocation = new JPanel();
		panelFormLocation.add(panelTitleLocation, BorderLayout.NORTH);
		panelTitleLocation.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblLieu = new JLabel("Lieu :");
		lblLieu.setHorizontalAlignment(SwingConstants.CENTER);
		lblLieu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelTitleLocation.add(lblLieu);
		
		JPanel panelSpacing_BottomTitleLieu = new JPanel();
		panelTitleLocation.add(panelSpacing_BottomTitleLieu);
		
		JPanel panelMap = new JPanel();
		panelMap.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFormLocation.add(panelMap, BorderLayout.CENTER);
		
		JPanel panelCoord = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelCoord.getLayout();
		flowLayout.setHgap(25);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelFormLocation.add(panelCoord, BorderLayout.SOUTH);
		
		JPanel panelLabelCoord = new JPanel();
		panelCoord.add(panelLabelCoord);
		
		JLabel labelCoord = new JLabel("Coordonn\u00E9e :");
		panelLabelCoord.add(labelCoord);
		
		inputCoord = new JTextField();
		panelLabelCoord.add(inputCoord);
		inputCoord.setColumns(10);
		
		JPanel panelFormLocationInputs = new JPanel();
		panelFormLocation.add(panelFormLocationInputs, BorderLayout.EAST);
		panelFormLocationInputs.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelFormCountry = new JPanel();
		panelFormLocationInputs.add(panelFormCountry);
		panelFormCountry.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelLabelCountry = new JPanel();
		FlowLayout fl_panelLabelCountry = (FlowLayout) panelLabelCountry.getLayout();
		fl_panelLabelCountry.setHgap(25);
		fl_panelLabelCountry.setAlignment(FlowLayout.LEFT);
		panelFormCountry.add(panelLabelCountry);
		
		JLabel labelCountry = new JLabel("Pays :");
		labelCountry.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelCountry.add(labelCountry);
		
		JPanel panelInputCountry = new JPanel();
		panelFormCountry.add(panelInputCountry);
		
		inputCountry = new JTextField();
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
		
		JPanel panelSpacing_BottomFormCountry = new JPanel();
		panelFormCountry.add(panelSpacing_BottomFormCountry);
		
		JPanel panelFormCity = new JPanel();
		panelFormLocationInputs.add(panelFormCity);
		panelFormCity.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelLabelCity = new JPanel();
		FlowLayout fl_panelLabelCity = (FlowLayout) panelLabelCity.getLayout();
		fl_panelLabelCity.setHgap(25);
		fl_panelLabelCity.setAlignment(FlowLayout.LEFT);
		panelFormCity.add(panelLabelCity);
		
		JLabel labelCity = new JLabel("Ville :");
		labelCity.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelCity.add(labelCity);
		
		JPanel panelInputCity = new JPanel();
		panelFormCity.add(panelInputCity);
		
		inputCity = new JTextField();
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
		
		panelSpacing_BottomFormCity = new JPanel();
		panelFormCity.add(panelSpacing_BottomFormCity);
		
		JPanel panelFormAdress = new JPanel();
		panelFormLocationInputs.add(panelFormAdress);
		panelFormAdress.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelLabelAdress = new JPanel();
		FlowLayout fl_panelLabelAdress = (FlowLayout) panelLabelAdress.getLayout();
		fl_panelLabelAdress.setAlignment(FlowLayout.LEFT);
		fl_panelLabelAdress.setHgap(25);
		panelFormAdress.add(panelLabelAdress);
		
		JLabel labelAdress = new JLabel("Adresse :");
		labelAdress.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelAdress.add(labelAdress);
		
		JPanel panelInputAdress = new JPanel();
		panelFormAdress.add(panelInputAdress);
		
		inputAdress = new JTextField();
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
		
		JPanel panelSpacing_BottomFormAdress = new JPanel();
		panelFormAdress.add(panelSpacing_BottomFormAdress);
		
		JPanel panelFormPostalCode = new JPanel();
		panelFormLocationInputs.add(panelFormPostalCode);
		panelFormPostalCode.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelLabelPostalCode = new JPanel();
		FlowLayout fl_panelLabelPostalCode = (FlowLayout) panelLabelPostalCode.getLayout();
		fl_panelLabelPostalCode.setAlignment(FlowLayout.LEFT);
		fl_panelLabelPostalCode.setHgap(25);
		panelFormPostalCode.add(panelLabelPostalCode);
		
		JLabel labelPostalCode = new JLabel("Code Postal :");
		labelPostalCode.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelPostalCode.add(labelPostalCode);
		
		JPanel panelInputPostalCode = new JPanel();
		panelFormPostalCode.add(panelInputPostalCode);
		
		inputPostalCode = new JTextField();
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
		
		JPanel panelSpacing_BottomFormPostalCode = new JPanel();
		panelFormPostalCode.add(panelSpacing_BottomFormPostalCode);
		
		JPanel panel = new JPanel();
		panelFormLocationInputs.add(panel);
		
		JPanel panelSpacing_LeftLocation = new JPanel();
		panelFormLocation.add(panelSpacing_LeftLocation, BorderLayout.WEST);
		
		JPanel panelFormTurnament = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panelFormTurnament.getLayout();
		flowLayout_2.setHgap(25);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panelForm.add(panelFormTurnament);
		
		JPanel panelFormTurnamentInner = new JPanel();
		panelFormTurnament.add(panelFormTurnamentInner);
		panelFormTurnamentInner.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelFormTurnamentName = new JPanel();
		panelFormTurnamentInner.add(panelFormTurnamentName);
		panelFormTurnamentName.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelLabelTurnamentName = new JPanel();
		FlowLayout fl_panelLabelTurnamentName = (FlowLayout) panelLabelTurnamentName.getLayout();
		fl_panelLabelTurnamentName.setAlignment(FlowLayout.LEFT);
		panelFormTurnamentName.add(panelLabelTurnamentName);
		
		JLabel labelTurnamentName = new JLabel("Nom du tournoi :");
		panelLabelTurnamentName.add(labelTurnamentName);
		
		JPanel panelInputTurnamentName = new JPanel();
		FlowLayout fl_panelInputTurnamentName = (FlowLayout) panelInputTurnamentName.getLayout();
		fl_panelInputTurnamentName.setVgap(0);
		fl_panelInputTurnamentName.setHgap(25);
		panelFormTurnamentName.add(panelInputTurnamentName);
		
		inputTurnamentName = new JTextField();
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
		
		JPanel panelFormDate = new JPanel();
		panelFormTurnamentInner.add(panelFormDate);
		panelFormDate.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelLabelDate = new JPanel();
		FlowLayout fl_panelLabelDate = (FlowLayout) panelLabelDate.getLayout();
		fl_panelLabelDate.setAlignment(FlowLayout.LEFT);
		panelFormDate.add(panelLabelDate);
		
		JLabel labelDate = new JLabel("Date du tournoi :");
		panelLabelDate.add(labelDate);
		
		JPanel panelDateInput = new JPanel();
		FlowLayout fl_panelDateInput = (FlowLayout) panelDateInput.getLayout();
		fl_panelDateInput.setHgap(25);
		fl_panelDateInput.setVgap(0);
		fl_panelDateInput.setAlignment(FlowLayout.LEFT);
		panelFormDate.add(panelDateInput);
		
		JPanel panelInputDateInner = new JPanel();
		panelDateInput.add(panelInputDateInner);
		
		JSpinner spinnerDateDay = new JSpinner();
		panelInputDateInner.add(spinnerDateDay);
		spinnerDateDay.setModel(new SpinnerNumberModel(1, 1, 32, 1));
		
		JLabel labelSeparatorDateLeft = new JLabel("/");
		panelInputDateInner.add(labelSeparatorDateLeft);
		
		JSpinner spinnerDateMonth = new JSpinner();
		panelInputDateInner.add(spinnerDateMonth);
		spinnerDateMonth.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		
		JLabel labelSeparatorDateRight = new JLabel("/");
		panelInputDateInner.add(labelSeparatorDateRight);
		
		JSpinner spinnerDateYear = new JSpinner();
		panelInputDateInner.add(spinnerDateYear);
		spinnerDateYear.setModel(new SpinnerNumberModel(new Integer(2022), new Integer(2022), null, new Integer(1)));
		
		JPanel panelFormTurnamentType = new JPanel();
		panelFormTurnamentInner.add(panelFormTurnamentType);
		panelFormTurnamentType.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelLabelTurnamentType = new JPanel();
		FlowLayout fl_panelLabelTurnamentType = (FlowLayout) panelLabelTurnamentType.getLayout();
		fl_panelLabelTurnamentType.setAlignment(FlowLayout.LEFT);
		panelFormTurnamentType.add(panelLabelTurnamentType);
		
		JLabel labelTurnamentType = new JLabel("Type de tournoi :");
		labelTurnamentType.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelTurnamentType.add(labelTurnamentType);
		
		JPanel panelInputTurnamentType = new JPanel();
		FlowLayout fl_panelInputTurnamentType = (FlowLayout) panelInputTurnamentType.getLayout();
		fl_panelInputTurnamentType.setAlignment(FlowLayout.LEFT);
		fl_panelInputTurnamentType.setVgap(0);
		fl_panelInputTurnamentType.setHgap(25);
		panelFormTurnamentType.add(panelInputTurnamentType);
		
		JPanel panelRadios = new JPanel();
		panelInputTurnamentType.add(panelRadios);
		
		JRadioButton radioTurnamentTypeLocal = new JRadioButton("Local");
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
		
		JRadioButton radioTurnamentTypeNationnal = new JRadioButton("Nationnal");
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
		
		JRadioButton radioTurnamentTypeInternationnal = new JRadioButton("Internationnal");
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
		
		JPanel panelFormGame = new JPanel();
		panelFormTurnamentInner.add(panelFormGame);
		panelFormGame.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelLabelGame = new JPanel();
		FlowLayout fl_panelLabelGame = (FlowLayout) panelLabelGame.getLayout();
		fl_panelLabelGame.setAlignment(FlowLayout.LEFT);
		panelFormGame.add(panelLabelGame);
		
		JLabel labelGame = new JLabel("Jeu :");
		labelGame.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelGame.add(labelGame);
		
		JPanel panelInputGame = new JPanel();
		FlowLayout fl_panelInputGame = (FlowLayout) panelInputGame.getLayout();
		fl_panelInputGame.setAlignment(FlowLayout.LEFT);
		fl_panelInputGame.setVgap(0);
		fl_panelInputGame.setHgap(25);
		panelFormGame.add(panelInputGame);
		
		JPanel panelComboGame = new JPanel();
		panelInputGame.add(panelComboGame);
		
		JComboBox<String> comboGame = new JComboBox<String>();
		comboGame.setModel(new DefaultComboBoxModel(new String[] {"League Of Legends", "Rocket League"}));
		panelComboGame.add(comboGame);
		
		JButton buttonAddGame = new JButton("+");
		buttonAddGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AjouterJeu.main(null);
			}
		});
		buttonAddGame.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelComboGame.add(buttonAddGame);
		
		JPanel panelFormButtons = new JPanel();
		FlowLayout fl_panelFormButtons = (FlowLayout) panelFormButtons.getLayout();
		fl_panelFormButtons.setHgap(25);
		fl_panelFormButtons.setAlignment(FlowLayout.RIGHT);
		frmCrerTournois.getContentPane().add(panelFormButtons, BorderLayout.SOUTH);
		
		JPanel panelFormButtonsInner = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panelFormButtonsInner.getLayout();
		flowLayout_3.setAlignOnBaseline(true);
		panelFormButtons.add(panelFormButtonsInner);
		
		JButton buttonCancel = new JButton("Annuler");
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmCrerTournois.dispose();
			}
		});
		panelFormButtonsInner.add(buttonCancel);
		
		buttonValidation = new JButton("Cr\u00E9er");
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
		
		JPanel panelSpacing_Left = new JPanel();
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
