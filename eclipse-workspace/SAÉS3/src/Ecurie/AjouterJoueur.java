package Ecurie;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.ButtonGroup;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AjouterJoueur {

	private JFrame frmAjouterUnJoueur;
	private JTextField inputLastName;
	private JTextField inputFirstName;
	private JTextField inputMail;
	private JTextField inputPhone;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton buttonValidation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterJoueur window = new AjouterJoueur();
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
	public AjouterJoueur() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAjouterUnJoueur = new JFrame();
		frmAjouterUnJoueur.setTitle("Ajouter un joueur");
		frmAjouterUnJoueur.setBounds(100, 100, 400, 400);
		frmAjouterUnJoueur.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panelTitle = new JPanel();
		frmAjouterUnJoueur.getContentPane().add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelSpacing_TopTitle = new JPanel();
		panelTitle.add(panelSpacing_TopTitle);
		
		JLabel labelTitle = new JLabel("Ajouter un joueur");
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelTitle.add(labelTitle);
		
		JPanel panelForm = new JPanel();
		FlowLayout fl_panelForm = (FlowLayout) panelForm.getLayout();
		fl_panelForm.setVgap(0);
		frmAjouterUnJoueur.getContentPane().add(panelForm, BorderLayout.CENTER);
		
		JPanel panelFormInner = new JPanel();
		panelForm.add(panelFormInner);
		panelFormInner.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelSpacing_TopForm = new JPanel();
		panelFormInner.add(panelSpacing_TopForm);
		
		JPanel panelLastName = new JPanel();
		panelFormInner.add(panelLastName);
		
		JPanel panelLabelLastName = new JPanel();
		
		JLabel labelLastName = new JLabel("Nom du joueur :");
		panelLabelLastName.add(labelLastName);
		
		JPanel panelInputLastName = new JPanel();
		
		inputLastName = new JTextField();
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
		
		JPanel panelFirstName = new JPanel();
		panelFormInner.add(panelFirstName);
		
		JPanel panelLabelFirstName = new JPanel();
		
		JLabel labelFirstName = new JLabel("Pr\u00E9nom du joueur :");
		panelLabelFirstName.add(labelFirstName);
		
		JPanel panelInputFirstName = new JPanel();
		
		inputFirstName = new JTextField();
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
		
		JPanel panelBirthDate = new JPanel();
		panelFormInner.add(panelBirthDate);
		
		JPanel panelLabelBirthDate = new JPanel();
		
		JLabel labelBirthDate = new JLabel("Date de naissance :");
		panelLabelBirthDate.add(labelBirthDate);
		
		JPanel panelInputBirthDate = new JPanel();
		FlowLayout fl_panelInputBirthDate = (FlowLayout) panelInputBirthDate.getLayout();
		fl_panelInputBirthDate.setHgap(12);
		fl_panelInputBirthDate.setAlignment(FlowLayout.RIGHT);
		
		JPanel panelSpinnerBirthDate = new JPanel();
		FlowLayout fl_panelSpinnerBirthDate = (FlowLayout) panelSpinnerBirthDate.getLayout();
		fl_panelSpinnerBirthDate.setVgap(0);
		panelInputBirthDate.add(panelSpinnerBirthDate);
		
		JSpinner spinnerBirthDateDay = new JSpinner();
		spinnerBirthDateDay.setModel(new SpinnerNumberModel(1, 1, 32, 1));
		panelSpinnerBirthDate.add(spinnerBirthDateDay);
		
		JLabel labelSeparatorDateLeft = new JLabel("/");
		panelSpinnerBirthDate.add(labelSeparatorDateLeft);
		
		JSpinner spinnerBirthDateMonth = new JSpinner();
		spinnerBirthDateMonth.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		panelSpinnerBirthDate.add(spinnerBirthDateMonth);
		
		JLabel labelSeparatorDateRight = new JLabel("/");
		panelSpinnerBirthDate.add(labelSeparatorDateRight);
		
		JSpinner spinnerBirthDateYear = new JSpinner();
		spinnerBirthDateYear.setModel(new SpinnerNumberModel(2004, null, 2004, 1));
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
		
		JPanel panelSex = new JPanel();
		panelFormInner.add(panelSex);
		
		JPanel panelLabelSex = new JPanel();
		
		JLabel labelSex = new JLabel("Sexe du joueur :");
		panelLabelSex.add(labelSex);
		
		JPanel panelInputSex = new JPanel();
		
		JRadioButton radioSexF = new JRadioButton("F");
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
		buttonGroup.add(radioSexF);
		panelInputSex.add(radioSexF);
		
		JRadioButton radioSexM = new JRadioButton("M");
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
		buttonGroup.add(radioSexM);
		panelInputSex.add(radioSexM);
		
		JRadioButton radioSexX = new JRadioButton("X");
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
		
		JPanel panelLabelMailPhone = new JPanel();
		panelFormInner.add(panelLabelMailPhone);
		
		JPanel panelMail = new JPanel();
		FlowLayout fl_panelMail = (FlowLayout) panelMail.getLayout();
		fl_panelMail.setVgap(10);
		
		JLabel labelMail = new JLabel("Adresse Mail :");
		panelMail.add(labelMail);
		
		JPanel panelPhone = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelPhone.getLayout();
		flowLayout.setVgap(10);
		
		JLabel labelPhone = new JLabel("Num\u00E9ro de T\u00E9l\u00E9phone :");
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
		
		JPanel panelInputMailPhone = new JPanel();
		panelFormInner.add(panelInputMailPhone);
		
		JPanel panelInputMail = new JPanel();
		FlowLayout fl_panelInputMail = (FlowLayout) panelInputMail.getLayout();
		fl_panelInputMail.setVgap(0);
		
		inputMail = new JTextField();
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
		inputMail.setColumns(15);
		panelInputMail.add(inputMail);
		
		JPanel panelInputPhone = new JPanel();
		FlowLayout fl_panelInputPhone = (FlowLayout) panelInputPhone.getLayout();
		fl_panelInputPhone.setHgap(17);
		fl_panelInputPhone.setAlignment(FlowLayout.RIGHT);
		fl_panelInputPhone.setVgap(0);
		
		inputPhone = new JTextField();
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
		
		JPanel panelFormButtons = new JPanel();
		FlowLayout fl_panelFormButtons = (FlowLayout) panelFormButtons.getLayout();
		fl_panelFormButtons.setHgap(20);
		fl_panelFormButtons.setAlignment(FlowLayout.RIGHT);
		frmAjouterUnJoueur.getContentPane().add(panelFormButtons, BorderLayout.SOUTH);
		
		JPanel panelFormButtonsInner = new JPanel();
		panelFormButtons.add(panelFormButtonsInner);
		
		JButton buttonCancel = new JButton("Annuler");
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmAjouterUnJoueur.dispose();
			}
		});
		panelFormButtonsInner.add(buttonCancel);
		
		buttonValidation = new JButton("Ajouter");
		buttonValidation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (buttonValidation.isEnabled()) {
					frmAjouterUnJoueur.dispose();
				}
			}
		});
		buttonValidation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonValidation.setEnabled(false);
		panelFormButtonsInner.add(buttonValidation);
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

}
