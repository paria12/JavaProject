package Ecurie;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import Commons.Colors;
import Commons.JButtonDark;
import Commons.JButtonYellow;
import Commons.JPanelBackground;
import Commons.JRadioDark;
import Commons.JSpinnerDark;
import Commons.JTextFieldDark;
import Commons.StaticValues;
import modele.ErreurBD;
import modele.Joueur;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
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
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class AjouterJoueur {

	private JFrame frmAjouterUnJoueur;
	private JTextFieldDark inputLastName;
	private JTextFieldDark inputFirstName;
	private JTextFieldDark inputMail;
	private JTextFieldDark inputPhone;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButtonYellow buttonValidation;
	private Calendar cal;
	private int day;
	private Month month;
	private int year;
	private boolean leapYear;
	private int maxDay;
	private int maxMonth;
	private int maxYear = LocalDate.now().getYear() - StaticValues.AGE_MIN;
	private JSpinnerDark spinnerBirthDateDay;
	private JSpinnerDark spinnerBirthDateMonth;
	private AjouterJoueur currentInstance = this;
	private CreerEquipe equipe;
	private String nbJ;
	private JPanelBackground panelPlayerInner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterJoueur window = new AjouterJoueur("", "", null, ' ', "", "", null, null, null);
					window.frmAjouterUnJoueur.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void MainWithValues(String nom, String prenom, Date date, char sexe, String tel, String email, CreerEquipe equipe, String nbJ, JPanelBackground panelPlayerInner) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterJoueur window = new AjouterJoueur(nom, prenom, date, sexe, tel, email, equipe, nbJ, panelPlayerInner);
					window.frmAjouterUnJoueur.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AjouterJoueur(String nom, String prenom, Date date, char sexe, String tel, String email, CreerEquipe equipe, String nbJ, JPanelBackground panelPlayerInner) {
		initialize(nom, prenom, date, sexe, tel, email, equipe, nbJ, panelPlayerInner);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nom, String prenom, Date date, char sexe, String tel, String email, CreerEquipe equipe, String nbJ, JPanelBackground panelPlayerInner) {
		this.equipe = equipe;
		this.nbJ = nbJ;
		this.panelPlayerInner = panelPlayerInner;
		frmAjouterUnJoueur = new JFrame();
		frmAjouterUnJoueur.setTitle("E-Sporter | Ajouter un joueur");
		frmAjouterUnJoueur.setLocationRelativeTo(null);
		frmAjouterUnJoueur.setBounds(100, 100, 400, 400);
		frmAjouterUnJoueur.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanelBackground panelTitle = new JPanelBackground();
		frmAjouterUnJoueur.getContentPane().add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelSpacing_TopTitle = new JPanelBackground();
		panelTitle.add(panelSpacing_TopTitle);
		
		JLabel labelTitle = new JLabel("Ajouter un joueur");
		labelTitle.setForeground(Colors.lightText);
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelTitle.add(labelTitle);
		
		JPanelBackground panelForm = new JPanelBackground();
		FlowLayout fl_panelForm = (FlowLayout) panelForm.getLayout();
		fl_panelForm.setVgap(0);
		frmAjouterUnJoueur.getContentPane().add(panelForm, BorderLayout.CENTER);
		
		JPanelBackground panelFormInner = new JPanelBackground();
		panelForm.add(panelFormInner);
		panelFormInner.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelSpacing_TopForm = new JPanelBackground();
		panelFormInner.add(panelSpacing_TopForm);
		
		JPanelBackground panelLastName = new JPanelBackground();
		panelFormInner.add(panelLastName);
		
		JPanelBackground panelLabelLastName = new JPanelBackground();
		
		JLabel labelLastName = new JLabel("Nom du joueur :");
		labelLastName.setForeground(Colors.lightText);
		panelLabelLastName.add(labelLastName);
		
		JPanelBackground panelInputLastName = new JPanelBackground();
		
		inputLastName = new JTextFieldDark();
		inputLastName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (isFormFilled()) {
					buttonValidation.setEnabled(true);
				} else {
					buttonValidation.setEnabled(false);
				}
			}
		});
		inputLastName.setText(nom);
		panelInputLastName.add(inputLastName);
		inputLastName.setColumns(15);
		GroupLayout gl_panelLastName = new GroupLayout(panelLastName);
		gl_panelLastName.setHorizontalGroup(
			gl_panelLastName.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLastName.createSequentialGroup()
					.addGap(12)
					.addComponent(panelLabelLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
					.addComponent(panelInputLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		gl_panelLastName.setVerticalGroup(
			gl_panelLastName.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLastName.createSequentialGroup()
					.addGap(3)
					.addComponent(panelLabelLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addComponent(panelInputLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		);
		panelLastName.setLayout(gl_panelLastName);
		
		JPanelBackground panelFirstName = new JPanelBackground();
		panelFormInner.add(panelFirstName);
		
		JPanelBackground panelLabelFirstName = new JPanelBackground();
		
		JLabel labelFirstName = new JLabel("Pr\u00E9nom du joueur :");
		labelFirstName.setForeground(Colors.lightText);
		panelLabelFirstName.add(labelFirstName);
		
		JPanelBackground panelInputFirstName = new JPanelBackground();
		
		inputFirstName = new JTextFieldDark();
		inputFirstName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (isFormFilled()) {
					buttonValidation.setEnabled(true);
				} else {
					buttonValidation.setEnabled(false);
				}
			}
		});
		inputFirstName.setText(prenom);
		panelInputFirstName.add(inputFirstName);
		inputFirstName.setColumns(15);
		GroupLayout gl_panelFirstName = new GroupLayout(panelFirstName);
		gl_panelFirstName.setHorizontalGroup(
			gl_panelFirstName.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFirstName.createSequentialGroup()
					.addGap(12)
					.addComponent(panelLabelFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addComponent(panelInputFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		gl_panelFirstName.setVerticalGroup(
			gl_panelFirstName.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFirstName.createSequentialGroup()
					.addGap(3)
					.addComponent(panelLabelFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addComponent(panelInputFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		);
		panelFirstName.setLayout(gl_panelFirstName);
		
		JPanelBackground panelBirthDate = new JPanelBackground();
		panelFormInner.add(panelBirthDate);
		
		JPanelBackground panelLabelBirthDate = new JPanelBackground();
		
		JLabel labelBirthDate = new JLabel("Date de naissance :");
		labelBirthDate.setForeground(Colors.lightText);
		panelLabelBirthDate.add(labelBirthDate);
		
		JPanelBackground panelInputBirthDate = new JPanelBackground();
		FlowLayout fl_panelInputBirthDate = (FlowLayout) panelInputBirthDate.getLayout();
		fl_panelInputBirthDate.setHgap(12);
		fl_panelInputBirthDate.setAlignment(FlowLayout.RIGHT);
		
		if (date != null) {
			cal = Calendar.getInstance();
			cal.setTime(date);
			day = cal.get(Calendar.DAY_OF_MONTH);
			month = Month.of(cal.get(Calendar.MONTH) + 1);
			year = cal.get(Calendar.YEAR);
			leapYear = (year % 4 == 0);
			if (year >= maxYear) {
				maxMonth = LocalDate.now().getMonthValue();
			} else {
				maxMonth = 12;
			}
			if ((year >= maxYear) && (month.getValue() >= maxMonth)) {
				maxDay = LocalDate.now().getDayOfMonth();
			} else {
				maxDay = month.length(leapYear);
			}
		} else {
			day = LocalDate.now().getDayOfMonth();
			month = LocalDate.now().getMonth();
			year = 2000;
			leapYear = (year % 4 == 0);
			maxMonth = 12;
			maxDay = month.length(leapYear);
		}
		
		JPanelBackground panelSpinnerBirthDate = new JPanelBackground();
		FlowLayout fl_panelSpinnerBirthDate = (FlowLayout) panelSpinnerBirthDate.getLayout();
		fl_panelSpinnerBirthDate.setVgap(0);
		panelInputBirthDate.add(panelSpinnerBirthDate);
		
		spinnerBirthDateDay = new JSpinnerDark(day, 1, maxDay,1);
		spinnerBirthDateDay.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				day = (int) spinnerBirthDateDay.getValue();
			}
		});
		panelSpinnerBirthDate.add(spinnerBirthDateDay);
		JLabel labelSeparatorDateLeft = new JLabel("/");
		labelSeparatorDateLeft.setForeground(Colors.lightText);
		panelSpinnerBirthDate.add(labelSeparatorDateLeft);
		
		spinnerBirthDateMonth = new JSpinnerDark(month.getValue(),1,maxMonth,1);
		spinnerBirthDateMonth.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				month = Month.of((int) spinnerBirthDateMonth.getValue());
				currentInstance.resetSpinnersModel();
			}
		});
		panelSpinnerBirthDate.add(spinnerBirthDateMonth);
		JLabel labelSeparatorDateRight = new JLabel("/");
		labelSeparatorDateRight.setForeground(Colors.lightText);
		panelSpinnerBirthDate.add(labelSeparatorDateRight);
		
		JSpinnerDark spinnerBirthDateYear = new JSpinnerDark(year, 1856, maxYear, 1);
		spinnerBirthDateYear.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				year = (int) spinnerBirthDateYear.getValue();
				currentInstance.resetSpinnersModel();
			}
		});
		panelSpinnerBirthDate.add(spinnerBirthDateYear);
		GroupLayout gl_panelBirthDate = new GroupLayout(panelBirthDate);
		gl_panelBirthDate.setHorizontalGroup(
			gl_panelBirthDate.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBirthDate.createSequentialGroup()
					.addGap(12)
					.addComponent(panelLabelBirthDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addComponent(panelInputBirthDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_panelBirthDate.setVerticalGroup(
			gl_panelBirthDate.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBirthDate.createSequentialGroup()
					.addGap(3)
					.addComponent(panelLabelBirthDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addComponent(panelInputBirthDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		);
		panelBirthDate.setLayout(gl_panelBirthDate);
		
		JPanelBackground panelSex = new JPanelBackground();
		panelFormInner.add(panelSex);
		
		JPanelBackground panelLabelSex = new JPanelBackground();
		
		JLabel labelSex = new JLabel("Sexe du joueur :");
		labelSex.setForeground(Colors.lightText);
		panelLabelSex.add(labelSex);
		
		JPanelBackground panelInputSex = new JPanelBackground();
		
		JRadioDark radioSexF = new JRadioDark("F");
		radioSexF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isFormFilled()) {
					buttonValidation.setEnabled(true);
				} else {
					buttonValidation.setEnabled(false);
				}
			}
		});
		if (sexe == 'F') {
			radioSexF.setSelected(true);
		}
		buttonGroup.add(radioSexF);
		panelInputSex.add(radioSexF);
		
		JRadioDark radioSexM = new JRadioDark("M");
		radioSexM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isFormFilled()) {
					buttonValidation.setEnabled(true);
				} else {
					buttonValidation.setEnabled(false);
				}
			}
		});
		if (sexe == 'M') {
			radioSexM.setSelected(true);
		}
		buttonGroup.add(radioSexM);
		panelInputSex.add(radioSexM);
		
		JRadioDark radioSexX = new JRadioDark("X");
		radioSexF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isFormFilled()) {
					buttonValidation.setEnabled(true);
				} else {
					buttonValidation.setEnabled(false);
				}
			}
		});
		if (sexe == 'X') {
			radioSexX.setSelected(true);
		}
		buttonGroup.add(radioSexX);
		panelInputSex.add(radioSexX);
		GroupLayout gl_panelSex = new GroupLayout(panelSex);
		gl_panelSex.setHorizontalGroup(
			gl_panelSex.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSex.createSequentialGroup()
					.addGap(12)
					.addComponent(panelLabelSex, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addComponent(panelInputSex, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		gl_panelSex.setVerticalGroup(
			gl_panelSex.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSex.createSequentialGroup()
					.addGap(4)
					.addComponent(panelLabelSex, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addComponent(panelInputSex, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		);
		panelSex.setLayout(gl_panelSex);
		
		JPanelBackground panelLabelMailPhone = new JPanelBackground();
		panelFormInner.add(panelLabelMailPhone);
		
		JPanelBackground panelMail = new JPanelBackground();
		FlowLayout fl_panelMail = (FlowLayout) panelMail.getLayout();
		fl_panelMail.setVgap(10);
		
		JLabel labelMail = new JLabel("Adresse Mail :");
		labelMail.setForeground(Colors.lightText);
		panelMail.add(labelMail);
		
		JPanelBackground panelPhone = new JPanelBackground();
		FlowLayout flowLayout = (FlowLayout) panelPhone.getLayout();
		flowLayout.setVgap(10);
		
		JLabel labelPhone = new JLabel("Num\u00E9ro de T\u00E9l\u00E9phone :");
		labelPhone.setForeground(Colors.lightText);
		panelPhone.add(labelPhone);
		GroupLayout gl_panelLabelMailPhone = new GroupLayout(panelLabelMailPhone);
		gl_panelLabelMailPhone.setHorizontalGroup(
			gl_panelLabelMailPhone.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLabelMailPhone.createSequentialGroup()
					.addGap(12)
					.addComponent(panelMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addComponent(panelPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		gl_panelLabelMailPhone.setVerticalGroup(
			gl_panelLabelMailPhone.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLabelMailPhone.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panelLabelMailPhone.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panelMail, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelPhone, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(1))
		);
		panelLabelMailPhone.setLayout(gl_panelLabelMailPhone);
		
		JPanelBackground panelInputMailPhone = new JPanelBackground();
		panelFormInner.add(panelInputMailPhone);
		
		JPanelBackground panelInputMail = new JPanelBackground();
		FlowLayout fl_panelInputMail = (FlowLayout) panelInputMail.getLayout();
		fl_panelInputMail.setVgap(0);
		
		inputMail = new JTextFieldDark();
		inputMail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (isFormFilled()) {
					buttonValidation.setEnabled(true);
				} else {
					buttonValidation.setEnabled(false);
				}
			}
		});
		inputMail.setText(email);
		inputMail.setColumns(15);
		panelInputMail.add(inputMail);
		
		JPanelBackground panelInputPhone = new JPanelBackground();
		FlowLayout fl_panelInputPhone = (FlowLayout) panelInputPhone.getLayout();
		fl_panelInputPhone.setHgap(17);
		fl_panelInputPhone.setAlignment(FlowLayout.RIGHT);
		fl_panelInputPhone.setVgap(0);
		
		inputPhone = new JTextFieldDark();
		inputPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (isFormFilled()) {
					buttonValidation.setEnabled(true);
				} else {
					buttonValidation.setEnabled(false);
				}
			}
		});
		inputPhone.setText(tel);
		inputPhone.setColumns(10);
		panelInputPhone.add(inputPhone);
		GroupLayout gl_panelInputMailPhone = new GroupLayout(panelInputMailPhone);
		gl_panelInputMailPhone.setHorizontalGroup(
			gl_panelInputMailPhone.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInputMailPhone.createSequentialGroup()
					.addGap(12)
					.addComponent(panelInputMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 0, Short.MAX_VALUE)
					.addComponent(panelInputPhone, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
		);
		gl_panelInputMailPhone.setVerticalGroup(
			gl_panelInputMailPhone.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInputMailPhone.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_panelInputMailPhone.createParallelGroup(Alignment.LEADING)
						.addComponent(panelInputMail, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
						.addGroup(gl_panelInputMailPhone.createSequentialGroup()
							.addComponent(panelInputPhone, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
							.addGap(4)))
					.addGap(1))
		);
		panelInputMailPhone.setLayout(gl_panelInputMailPhone);
		
		JPanelBackground panelFormButtons = new JPanelBackground();
		FlowLayout fl_panelFormButtons = (FlowLayout) panelFormButtons.getLayout();
		fl_panelFormButtons.setHgap(20);
		fl_panelFormButtons.setAlignment(FlowLayout.RIGHT);
		frmAjouterUnJoueur.getContentPane().add(panelFormButtons, BorderLayout.SOUTH);
		
		JPanelBackground panelFormButtonsInner = new JPanelBackground();
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
		
		buttonValidation = new JButtonYellow("Ajouter");
		buttonValidation.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				submitValider();
			}
		});
		buttonValidation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitValider();
			}
		});
		buttonValidation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		if (isFormFilled()) { 
			buttonValidation.setEnabled(true);
		} else {
			buttonValidation.setEnabled(false);
		}
		panelFormButtonsInner.add(buttonValidation);
	}
	
	public char getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText().charAt(0);
            }
        }
        
        return ' ';
    }
	private Boolean isFormatGood() {
        if((inputMail.getText().contains("@"))&& (inputPhone.getText().matches("[0-9]+") == true)&& (inputPhone.getText().length() == 10)){
            return true;
        }else {
            return false;
        }

    }
	private Boolean isFormFilled() {
		if ((inputLastName.getText().length() != 0) 
		&& (inputFirstName.getText().length() != 0) 
		&& (inputMail.getText().length() != 0) 
		&& (inputPhone.getText().length() != 0) 
		&& (buttonGroup.getSelection() != null)) {
			return true;
		} else {
			return false;
		}
	}
	
	private void resetSpinnersModel() {
		leapYear = (year % 4 == 0);
		if (year >= maxYear) {
			maxMonth = LocalDate.now().getMonthValue();
		} else {
			maxMonth = 12;
		}
		if (month.getValue() > maxMonth) {
			month = Month.of(maxMonth);
		}
		if ((year >= maxYear) && (month.getValue() >= maxMonth)) {
			maxDay = LocalDate.now().getDayOfMonth();
		} else {
			maxDay = month.length(leapYear);
		}
		if (day > maxDay) {
			day = maxDay;
		}
		spinnerBirthDateDay.setModel(new SpinnerNumberModel(day, 1, maxDay, 1));
		spinnerBirthDateMonth.setModel(new SpinnerNumberModel(month.getValue(), 1, maxMonth, 1));
	}
	private void submitAnnuler() {
		frmAjouterUnJoueur.dispose();
	}
	private void submitValider() {
		if (buttonValidation.isEnabled()) {
			//Create calandar from inputs
			Calendar cal = Calendar.getInstance();
			cal.set( Calendar.DAY_OF_MONTH, day);
			cal.set( Calendar.MONTH, month.getValue() - 1);
			cal.set( Calendar.YEAR, year);
			//Create and return player from inputs
			if(isFormatGood()) {
				equipe.setJoueur(new Joueur(inputLastName.getText(), 
								 inputFirstName.getText(), 
								 new Date(cal.getTimeInMillis()), 
								 getSelectedButtonText(buttonGroup),
								 inputPhone.getText(), 
								 inputMail.getText()), nbJ, panelPlayerInner);
				frmAjouterUnJoueur.dispose();
			}
		}
		
	}

}