package Ecurie;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import Commons.Colors;
import Commons.ErrorMessage;
import Commons.JButtonYellow;
import Commons.JPanelBackground;
import code.Equipe;
import code.ErreurBD;
import code.Tournoi;

import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Timestamp;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PopUp_ConfirmDeleteTeam {

	private JFrame frame;
	private AccueilEcurie parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopUp_ConfirmDeleteTeam window = new PopUp_ConfirmDeleteTeam(new Equipe("Best OF The Best"), new AccueilEcurie());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void mainWithValues(Equipe eq, AccueilEcurie p) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopUp_ConfirmDeleteTeam window = new PopUp_ConfirmDeleteTeam(eq, p);
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
	public PopUp_ConfirmDeleteTeam(Equipe equipe, AccueilEcurie p) {
		initialize(equipe, p);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Equipe equipe, AccueilEcurie parent) {
		this.parent = parent;
		frame = new JFrame();
		frame.setBounds(100, 100, 250, 175);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Confirmation de suppression ?");
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanelBackground panelHeader = new JPanelBackground();
		frame.getContentPane().add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelLabelConfirmer = new JPanelBackground();
		panelHeader.add(panelLabelConfirmer);
		
		JLabel labelConfirmez = new JLabel("\u00CAtes-vous s\u00FBr de vouloir");
		labelConfirmez.setForeground(Colors.lightText);
		labelConfirmez.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLabelConfirmer.add(labelConfirmez);
		
		JPanelBackground panelLabelEquipe = new JPanelBackground();
		panelHeader.add(panelLabelEquipe);
		
		JLabel labelEquipe = new JLabel("Supprimer "+equipe.getNom());
		labelEquipe.setForeground(Colors.lightText);
		labelEquipe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLabelEquipe.add(labelEquipe);
		
		JPanelBackground panelLabelDate = new JPanelBackground();
		panelHeader.add(panelLabelDate);
		
		JPanelBackground panelMenu = new JPanelBackground();
		frame.getContentPane().add(panelMenu, BorderLayout.CENTER);
		panelMenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanelBackground panelButtonNo = new JPanelBackground();
		FlowLayout fl_panelButtonNo = (FlowLayout) panelButtonNo.getLayout();
		panelMenu.add(panelButtonNo);
		
		JButtonYellow buttonNo = new JButtonYellow("Non");
		buttonNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					submitNon();
				}
			}
		});
		buttonNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitNon();
			}
		});
		buttonNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelButtonNo.add(buttonNo);
		
		JPanelBackground panelButtonYes = new JPanelBackground();
		FlowLayout fl_panelButtonYes = (FlowLayout) panelButtonYes.getLayout();
		panelMenu.add(panelButtonYes);
		
		JButtonYellow buttonYes = new JButtonYellow("Oui");
		buttonYes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					submitOui(equipe);
				}
			}
		});
		buttonYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitOui(equipe);
			}
		});
		panelButtonYes.add(buttonYes);
	}
	private void submitNon() {
		frame.dispose();
	}
	private void submitOui(Equipe equipe) {
		try {
			equipe.delete();
		} catch (ErreurBD e) {
			// TODO Auto-generated catch block
			ErrorMessage.ErrorMessage(e.getMessage());
		};
		parent.setListEquipes();
		frame.dispose();
	}
}