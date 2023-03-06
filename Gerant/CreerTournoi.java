package Gerant;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.FlowLayout;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Commons.Colors;
import Commons.ErrorMessage;
import Commons.JButtonDark;
import Commons.JButtonYellow;
import Commons.JComboBoxDark;
import Commons.JTextFieldDark;
import Commons.StaticValues;
import Ecurie.AjouterJoueur;
import modele.Arbitre;
import modele.ErreurBD;
import modele.Jeu;
import modele.Tournoi;
import Commons.JPanelBackground;
import Commons.JRadioDark;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import Commons.JSpinnerDark;

import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Enumeration;
import javax.swing.JPanel;

public class CreerTournoi {

	private JFrame frmCrerTournois;
	private JTextFieldDark inputCountry;
	private JTextFieldDark inputCity;
	private JTextFieldDark inputAdress;
	private JTextFieldDark inputPostalCode;
	private JTextFieldDark inputTurnamentName;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButtonYellow buttonValidation;
	private int day;
	private Month month;
	private int year;
	private boolean leapYear;
	private int maxDay;
	private int maxMonth;
	private int minDay;
	private int minMonth;
	private int minYear;
	private JSpinnerDark spinnerDay;
	private JSpinnerDark spinnerMonth;
	private CreerTournoi currentInstance = this;
	private JComboBoxDark<String> comboGame;
	private JComboBoxDark<String> comboArbitre;
	private String notoriete;
	private AccueilGerant parent;
	private JPanelBackground panelComboGame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreerTournoi window = new CreerTournoi(new AccueilGerant());
					window.frmCrerTournois.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void mainWithValues(AccueilGerant parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreerTournoi window = new CreerTournoi(parent);
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
	public CreerTournoi(AccueilGerant parent) throws ErreurBD {
		initialize(parent);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ErreurBD 
	 */
	private void initialize(AccueilGerant parent) throws ErreurBD {
		this.parent = parent;
		frmCrerTournois = new JFrame();
		frmCrerTournois.setTitle("E-Sporter | Créer Tournoi");
		frmCrerTournois.setLocationRelativeTo(null);
		frmCrerTournois.setSize(650, 650);
		//frmCrerTournois.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		panelTitleLocation.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 5));
		
		JLabel lblLieu = new JLabel("Lieu :");
		lblLieu.setForeground(Colors.lightText);
		lblLieu.setHorizontalAlignment(SwingConstants.LEFT);
		lblLieu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelTitleLocation.add(lblLieu);
		
		JPanelBackground panelFormLocationInputs = new JPanelBackground();
		panelFormLocation.add(panelFormLocationInputs, BorderLayout.CENTER);
		panelFormLocationInputs.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelFormCountry = new JPanelBackground();
		panelFormLocationInputs.add(panelFormCountry);
		panelFormCountry.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelLabelCountry = new JPanelBackground();
		FlowLayout fl_panelLabelCountry = (FlowLayout) panelLabelCountry.getLayout();
		fl_panelLabelCountry.setHgap(25);
		fl_panelLabelCountry.setAlignment(FlowLayout.RIGHT);
		panelFormCountry.add(panelLabelCountry);
		
		JLabel labelCountry = new JLabel("Pays :");
		labelCountry.setForeground(Colors.lightText);
		labelCountry.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelCountry.add(labelCountry);
		
		JPanelBackground panelInputCountry = new JPanelBackground();
		FlowLayout flowLayout = (FlowLayout) panelInputCountry.getLayout();
		flowLayout.setHgap(50);
		flowLayout.setAlignment(FlowLayout.RIGHT);
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
		
		JPanelBackground panelFormCity = new JPanelBackground();
		panelFormLocationInputs.add(panelFormCity);
		panelFormCity.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelLabelCity = new JPanelBackground();
		FlowLayout fl_panelLabelCity = (FlowLayout) panelLabelCity.getLayout();
		fl_panelLabelCity.setHgap(25);
		fl_panelLabelCity.setAlignment(FlowLayout.RIGHT);
		panelFormCity.add(panelLabelCity);
		
		JLabel labelCity = new JLabel("Ville :");
		labelCity.setForeground(Colors.lightText);
		labelCity.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelCity.add(labelCity);
		
		JPanelBackground panelInputCity = new JPanelBackground();
		FlowLayout flowLayout_6 = (FlowLayout) panelInputCity.getLayout();
		flowLayout_6.setHgap(50);
		flowLayout_6.setAlignment(FlowLayout.RIGHT);
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
		
		JPanelBackground panelFormAdress = new JPanelBackground();
		panelFormLocationInputs.add(panelFormAdress);
		panelFormAdress.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelLabelAdress = new JPanelBackground();
		FlowLayout fl_panelLabelAdress = (FlowLayout) panelLabelAdress.getLayout();
		fl_panelLabelAdress.setAlignment(FlowLayout.RIGHT);
		fl_panelLabelAdress.setHgap(25);
		panelFormAdress.add(panelLabelAdress);
		
		JLabel labelAdress = new JLabel("Adresse :");
		labelAdress.setForeground(Colors.lightText);
		labelAdress.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelAdress.add(labelAdress);
		
		JPanelBackground panelInputAdress = new JPanelBackground();
		FlowLayout flowLayout_7 = (FlowLayout) panelInputAdress.getLayout();
		flowLayout_7.setAlignment(FlowLayout.RIGHT);
		flowLayout_7.setHgap(50);
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
		inputAdress.setColumns(20);
		panelInputAdress.add(inputAdress);
		
		JPanelBackground panelFormPostalCode = new JPanelBackground();
		panelFormLocationInputs.add(panelFormPostalCode);
		panelFormPostalCode.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelLabelPostalCode = new JPanelBackground();
		FlowLayout fl_panelLabelPostalCode = (FlowLayout) panelLabelPostalCode.getLayout();
		fl_panelLabelPostalCode.setAlignment(FlowLayout.RIGHT);
		fl_panelLabelPostalCode.setHgap(25);
		panelFormPostalCode.add(panelLabelPostalCode);
		
		JLabel labelPostalCode = new JLabel("Code Postal :");
		labelPostalCode.setForeground(Colors.lightText);
		labelPostalCode.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelPostalCode.add(labelPostalCode);
		
		JPanelBackground panelInputPostalCode = new JPanelBackground();
		FlowLayout flowLayout_8 = (FlowLayout) panelInputPostalCode.getLayout();
		flowLayout_8.setHgap(50);
		flowLayout_8.setAlignment(FlowLayout.RIGHT);
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
		inputPostalCode.setColumns(10);
		panelInputPostalCode.add(inputPostalCode);
		
		JPanelBackground panel = new JPanelBackground();
		panelFormLocationInputs.add(panel);
		
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
		panelFormDate.add(panelDateInput);
		panelDateInput.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 5));
		
		JPanelBackground panelInputDateInner = new JPanelBackground();
		panelDateInput.add(panelInputDateInner);
		
		day = LocalDate.now().getDayOfMonth();
		month = LocalDate.now().getMonth();
		year = LocalDate.now().getYear();
		leapYear = (year % 4 == 0);
		maxMonth = 12;
		maxDay = month.length(leapYear);
		minDay = day;
		minMonth = month.getValue();
		minYear = year;
		
		spinnerDay = new JSpinnerDark(day, minDay, maxDay,1);
		spinnerDay.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				day = (int) spinnerDay.getValue();
				currentInstance.resetSpinnersModel();
			}
		});
		panelInputDateInner.add(spinnerDay);
		JLabel labelSeparatorDateLeft = new JLabel("/");
		labelSeparatorDateLeft.setForeground(Colors.lightText);
		panelInputDateInner.add(labelSeparatorDateLeft);
		
		spinnerMonth = new JSpinnerDark(month.getValue(),minMonth,maxMonth,1);
		spinnerMonth.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				month = Month.of((int) spinnerMonth.getValue());
				currentInstance.resetSpinnersModel();
			}
		});
		panelInputDateInner.add(spinnerMonth);
		JLabel labelSeparatorDateRight = new JLabel("/");
		labelSeparatorDateRight.setForeground(Colors.lightText);
		panelInputDateInner.add(labelSeparatorDateRight);
		
		JSpinnerDark spinnerYear = new JSpinnerDark(year, minYear, -1, 1);
		spinnerYear.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				year = (int) spinnerYear.getValue();
				currentInstance.resetSpinnersModel();
			}
		});
		panelInputDateInner.add(spinnerYear);
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
		
		JRadioDark radioTurnamentTypeLocal = new JRadioDark("LOCAL");
		radioTurnamentTypeLocal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER) {
					radioTurnamentTypeLocal.setFocusTraversalKeysEnabled(true);
					enterRadio();
				}
			}
		});
		
		radioTurnamentTypeLocal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				enterRadio();
			}
		});
		buttonGroup.add(radioTurnamentTypeLocal);
		panelRadios.add(radioTurnamentTypeLocal);
		
		JRadioDark radioTurnamentTypeNationnal = new JRadioDark("NATIONAL");
		radioTurnamentTypeNationnal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					radioTurnamentTypeNationnal.setFocusTraversalKeysEnabled(true);
					enterRadio();
				}
			}
		});
		radioTurnamentTypeNationnal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				enterRadio();
			}
		});
		buttonGroup.add(radioTurnamentTypeNationnal);
		panelRadios.add(radioTurnamentTypeNationnal);
		
		JRadioDark radioTurnamentTypeInternationnal = new JRadioDark("INTERNATIONAL");
		radioTurnamentTypeInternationnal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					radioTurnamentTypeInternationnal.setFocusTraversalKeysEnabled(true);
					enterRadio();
				}
			}
		});
		radioTurnamentTypeInternationnal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				enterRadio();
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
		
		panelComboGame = new JPanelBackground();
		panelInputGame.add(panelComboGame);
		
		comboGame = new JComboBoxDark<String>();
		comboGame.setModel(new DefaultComboBoxModel(Jeu.getAll()));
		panelComboGame.add(comboGame);
		
		JPanelBackground panelFormArbitre = new JPanelBackground();
		panelFormTurnamentInner.add(panelFormArbitre);
		panelFormArbitre.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelLabelArbitre = new JPanelBackground();
		FlowLayout fl_panelLabelArbitre = (FlowLayout) panelLabelArbitre.getLayout();
		fl_panelLabelArbitre.setAlignment(FlowLayout.LEFT);
		panelFormArbitre.add(panelLabelArbitre);
		
		JLabel labelArbitre = new JLabel("Arbitre :");
		labelArbitre.setForeground(Colors.lightText);
		labelArbitre.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelArbitre.add(labelArbitre);
		
		JPanelBackground panelInputArbitre = new JPanelBackground();
		FlowLayout flowLayout_4 = (FlowLayout) panelInputArbitre.getLayout();
		flowLayout_4.setHgap(25);
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		FlowLayout fl_panelInputArbitre = (FlowLayout) panelInputGame.getLayout();
		fl_panelInputArbitre.setAlignment(FlowLayout.LEFT);
		fl_panelInputArbitre.setVgap(0);
		fl_panelInputArbitre.setHgap(25);
		panelFormArbitre.add(panelInputArbitre);
		
		JPanelBackground panelComboArbitre = new JPanelBackground();
		panelInputArbitre.add(panelComboArbitre);
		
		comboArbitre = new JComboBoxDark<String>();
		comboArbitre.setModel(new DefaultComboBoxModel(Arbitre.getAll()));
		panelComboArbitre.add(comboArbitre);
		
		JButtonDark buttonAddGame = new JButtonDark("+");
		buttonAddGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AjouterJeu.mainWithValues(currentInstance);
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
		buttonValidation.setEnabled(false);
		panelFormButtonsInner.add(buttonValidation);
		buttonValidation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JPanelBackground panelSpacing_Left = new JPanelBackground();
		FlowLayout flowLayout_1 = (FlowLayout) panelSpacing_Left.getLayout();
		flowLayout_1.setHgap(10);
		frmCrerTournois.getContentPane().add(panelSpacing_Left, BorderLayout.WEST);
		
		JPanelBackground panelSpacing_Left_1 = new JPanelBackground();
		FlowLayout flowLayout_5 = (FlowLayout) panelSpacing_Left_1.getLayout();
		flowLayout_5.setHgap(10);
		frmCrerTournois.getContentPane().add(panelSpacing_Left_1, BorderLayout.EAST);
	}
	public Boolean isFormatGood() {
        if((inputPostalCode.getText().matches("[0-9]+")==true)&&(inputPostalCode.getText().length() == 5)) {
            return true;
        }
        return false;
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
	
	private void resetSpinnersModel() {
		leapYear = (year % 4 == 0);
		maxMonth = 12;
		if (year <= minYear) {
			minMonth = LocalDate.now().getMonthValue();
		} else {
			minMonth = 1;
		}
		if (month.getValue() > maxMonth) {
			month = Month.of(maxMonth);
		}
		if (month.getValue() < minMonth) {
			month= Month.of(minMonth);
		}
		maxDay = month.length(leapYear);
		if ((year <= minYear) && (month.getValue() <= minMonth)) {
			minDay = LocalDate.now().getDayOfMonth();
		} else {
			minDay = 1;
		}
		if (day > maxDay) {
			day = maxDay;
		}
		if (day < minDay) {
			day = minDay;
		}
		spinnerDay.changeModel(day, minDay, maxDay, 1);
		spinnerMonth.changeModel(month.getValue(), minMonth, maxMonth, 1);
	}
	private void submitAnnuler() {
		frmCrerTournois.dispose();
	}
	private void submitCreer() {
		if (buttonValidation.isEnabled()) {
            Calendar cal = Calendar.getInstance();
            cal.set( Calendar.DAY_OF_MONTH, day);
            cal.set( Calendar.MONTH, month.getValue() - 1);
            cal.set( Calendar.YEAR, year);
            for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();

                if (button.isSelected()) {
                    notoriete = button.getText().toUpperCase();
                }
            }
            if(isFormatGood()) {
				try {
					Tournoi t = new Tournoi(inputTurnamentName.getText(), 
											inputAdress.getText(), 
											inputCity.getText(), 
											inputCountry.getText(), 
											inputPostalCode.getText(), 
											new Date(cal.getTimeInMillis()), 
											notoriete, 
											Jeu.getID(new Jeu(comboGame.getSelectedItem().toString())));
					t.insert(new Arbitre(comboArbitre.getSelectedItem().toString()).getID());
				} catch (ErreurBD e1) {
					// TODO Auto-generated catch block
					ErrorMessage.ErrorMessage(e1.getMessage());
				}
				parent.setListTournois();
	            frmCrerTournois.dispose();
            }
        }
	}
	private void enterRadio() {
		if (isFormFilled()) {
			buttonValidation.setEnabled(true);
		} else {
			buttonValidation.setEnabled(false);
		}
	}
	
	public void refreshComboGame() {
		try {
			comboGame.setModel(new DefaultComboBoxModel(Jeu.getAll()));
		} catch (ErreurBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}