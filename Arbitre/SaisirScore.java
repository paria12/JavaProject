package Arbitre;

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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SaisirScore {

	private JFrame frame;
	private JTextField outputTurnamentDate;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton buttonValidation;

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
		
		JPanel panelTitle = new JPanel();
		frame.getContentPane().add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelSpacing_TopTitle = new JPanel();
		panelTitle.add(panelSpacing_TopTitle);
		
		JLabel labelSelectScore = new JLabel("S\u00E9lectionner le score");
		labelSelectScore.setHorizontalAlignment(SwingConstants.CENTER);
		labelSelectScore.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelTitle.add(labelSelectScore);
		
		JPanel panelTurnamentDate = new JPanel();
		panelTitle.add(panelTurnamentDate);
		
		JLabel labelTurnamentDate = new JLabel("Du ");
		labelTurnamentDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelTurnamentDate.add(labelTurnamentDate);
		
		outputTurnamentDate = new JTextField();
		outputTurnamentDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		outputTurnamentDate.setHorizontalAlignment(SwingConstants.CENTER);
		outputTurnamentDate.setText("26/10/22");
		outputTurnamentDate.setEditable(false);
		outputTurnamentDate.setColumns(8);
		panelTurnamentDate.add(outputTurnamentDate);
		
		JPanel panelSpacing_BottomTitle = new JPanel();
		panelTitle.add(panelSpacing_BottomTitle);
		
		JPanel panelForm = new JPanel();
		frame.getContentPane().add(panelForm, BorderLayout.CENTER);
		panelForm.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelLabelSelectWinner = new JPanel();
		FlowLayout fl_panelLabelSelectWinner = (FlowLayout) panelLabelSelectWinner.getLayout();
		fl_panelLabelSelectWinner.setVgap(10);
		fl_panelLabelSelectWinner.setAlignment(FlowLayout.LEFT);
		fl_panelLabelSelectWinner.setHgap(100);
		panelForm.add(panelLabelSelectWinner);
		
		JLabel labelWinner = new JLabel("Gagnant :");
		labelWinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelLabelSelectWinner.add(labelWinner);
		
		JPanel panelRadioTeam1 = new JPanel();
		FlowLayout fl_panelRadioTeam1 = (FlowLayout) panelRadioTeam1.getLayout();
		fl_panelRadioTeam1.setVgap(10);
		fl_panelRadioTeam1.setAlignment(FlowLayout.LEFT);
		fl_panelRadioTeam1.setHgap(125);
		panelForm.add(panelRadioTeam1);
		
		JRadioButton radioTeam1 = new JRadioButton("Equipe 1");
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
		
		JPanel panelRadioTeam2 = new JPanel();
		FlowLayout fl_panelRadioTeam2 = (FlowLayout) panelRadioTeam2.getLayout();
		fl_panelRadioTeam2.setAlignment(FlowLayout.LEFT);
		fl_panelRadioTeam2.setHgap(125);
		panelForm.add(panelRadioTeam2);
		
		JRadioButton radioTeam2 = new JRadioButton("Equipe 2");
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
		
		JPanel panelFormButtons = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelFormButtons.getLayout();
		flowLayout.setHgap(20);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		frame.getContentPane().add(panelFormButtons, BorderLayout.SOUTH);
		
		JPanel panelFormButtonsInner = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelFormButtonsInner.getLayout();
		flowLayout_1.setVgap(0);
		panelFormButtons.add(panelFormButtonsInner);
		
		JButton buttonCancel = new JButton("Annuler");
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		panelFormButtonsInner.add(buttonCancel);
		
		buttonValidation = new JButton("Valider");
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
