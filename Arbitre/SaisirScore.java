package Arbitre;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Commons.Colors;
import Commons.JButtonYellow;
import Commons.JPanelBackground;
import Commons.JTextFieldDark;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SaisirScore {

	private JFrame frame;
	private JTextFieldDark outputTurnamentDate;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButtonYellow buttonValidation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaisirScore window = new SaisirScore();
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
	public SaisirScore() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 350, 350);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanelBackground panelTitle = new JPanelBackground();
		frame.getContentPane().add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelSpacing_TopTitle = new JPanelBackground();
		panelTitle.add(panelSpacing_TopTitle);
		
		JLabel labelSelectScore = new JLabel("S\u00E9lectionner le score");
		labelSelectScore.setForeground(Colors.lightText);
		labelSelectScore.setHorizontalAlignment(SwingConstants.CENTER);
		labelSelectScore.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelTitle.add(labelSelectScore);
		
		JPanelBackground panelTurnamentDate = new JPanelBackground();
		panelTitle.add(panelTurnamentDate);
		
		JLabel labelTurnamentDate = new JLabel("Du ");
		labelTurnamentDate.setForeground(Colors.lightText);
		labelTurnamentDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelTurnamentDate.add(labelTurnamentDate);
		
		outputTurnamentDate = new JTextFieldDark();
		outputTurnamentDate.setBackground(Colors.backBlue);
		outputTurnamentDate.setBorder(new LineBorder(Colors.lightText));
		outputTurnamentDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		outputTurnamentDate.setHorizontalAlignment(SwingConstants.CENTER);
		outputTurnamentDate.setText("26/10/22");
		outputTurnamentDate.setEditable(false);
		outputTurnamentDate.setColumns(8);
		panelTurnamentDate.add(outputTurnamentDate);
		
		JPanelBackground panelSpacing_BottomTitle = new JPanelBackground();
		panelTitle.add(panelSpacing_BottomTitle);
		
		JPanelBackground panelForm = new JPanelBackground();
		frame.getContentPane().add(panelForm, BorderLayout.CENTER);
		panelForm.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelLabelSelectWinner = new JPanelBackground();
		FlowLayout fl_panelLabelSelectWinner = (FlowLayout) panelLabelSelectWinner.getLayout();
		fl_panelLabelSelectWinner.setVgap(10);
		fl_panelLabelSelectWinner.setAlignment(FlowLayout.LEFT);
		fl_panelLabelSelectWinner.setHgap(100);
		panelForm.add(panelLabelSelectWinner);
		
		JLabel labelWinner = new JLabel("Gagnant :");
		labelWinner.setForeground(Colors.lightText);
		labelWinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelLabelSelectWinner.add(labelWinner);
		
		JPanelBackground panelRadioTeam1 = new JPanelBackground();
		FlowLayout fl_panelRadioTeam1 = (FlowLayout) panelRadioTeam1.getLayout();
		fl_panelRadioTeam1.setVgap(10);
		fl_panelRadioTeam1.setAlignment(FlowLayout.LEFT);
		fl_panelRadioTeam1.setHgap(125);
		panelForm.add(panelRadioTeam1);
		
		JRadioButton radioTeam1 = new JRadioButton("Equipe 1");
		radioTeam1.setBackground(Colors.backBlue);
		radioTeam1.setForeground(Colors.lightText);
		radioTeam1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (buttonGroup.getSelection() != null) {
					buttonValidation.setEnabled(true);
				} else {
					buttonValidation.setEnabled(false);
				}
			}
		});
		buttonGroup.add(radioTeam1);
		panelRadioTeam1.add(radioTeam1);
		
		JPanelBackground panelRadioTeam2 = new JPanelBackground();
		FlowLayout fl_panelRadioTeam2 = (FlowLayout) panelRadioTeam2.getLayout();
		fl_panelRadioTeam2.setAlignment(FlowLayout.LEFT);
		fl_panelRadioTeam2.setHgap(125);
		panelForm.add(panelRadioTeam2);
		
		JRadioButton radioTeam2 = new JRadioButton("Equipe 2");
		radioTeam2.setBackground(Colors.backBlue);
		radioTeam2.setForeground(Colors.lightText);
		radioTeam2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (buttonGroup.getSelection() != null) {
					buttonValidation.setEnabled(true);
				} else {
					buttonValidation.setEnabled(false);
				}
			}
		});
		buttonGroup.add(radioTeam2);
		panelRadioTeam2.add(radioTeam2);
		
		JPanelBackground panelFormButtons = new JPanelBackground();
		FlowLayout flowLayout = (FlowLayout) panelFormButtons.getLayout();
		flowLayout.setHgap(20);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		frame.getContentPane().add(panelFormButtons, BorderLayout.SOUTH);
		
		JPanelBackground panelFormButtonsInner = new JPanelBackground();
		FlowLayout flowLayout_1 = (FlowLayout) panelFormButtonsInner.getLayout();
		flowLayout_1.setVgap(0);
		panelFormButtons.add(panelFormButtonsInner);
		
		JButton buttonCancel = new JButtonYellow("Annuler");
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		panelFormButtonsInner.add(buttonCancel);
		
		buttonValidation = new JButtonYellow("Valider");
		buttonValidation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (buttonValidation.isEnabled()) {
					frame.dispose();
				}
			}
		});
		buttonValidation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelFormButtonsInner.add(buttonValidation);
		buttonValidation.setEnabled(false);
	}

}
