package vueGerant;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import modele.ErreurBD;
import modele.Tournoi;
import vue.Colors;
import vue.ErrorMessage;
import vue.JButtonYellow;
import vue.JPanelBackground;

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

public class PopUp_ConfirmDeleteTournoi {

	private JFrame frame;
	private AccueilGerant parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopUp_ConfirmDeleteTournoi window = new PopUp_ConfirmDeleteTournoi(Tournoi.getAll()[0], new AccueilGerant());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void mainWithValues(Tournoi t, AccueilGerant p) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopUp_ConfirmDeleteTournoi window = new PopUp_ConfirmDeleteTournoi(t, p);
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
	public PopUp_ConfirmDeleteTournoi(Tournoi t, AccueilGerant p) {
		initialize(t, p);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Tournoi t, AccueilGerant parent) {
		this.parent = parent;
		frame = new JFrame();
		frame.setBounds(100, 100, 250, 175);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Confirmer suppression ?");
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
		
		JPanelBackground panelLabelt = new JPanelBackground();
		panelHeader.add(panelLabelt);
		
		JLabel labelt = new JLabel("Supprimer "+t.getNom());
		labelt.setForeground(Colors.lightText);
		labelt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLabelt.add(labelt);
		
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
					submitOui(t);
				}
			}
		});
		buttonYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitOui(t);
			}
		});
		panelButtonYes.add(buttonYes);
	}
	private void submitNon() {
		frame.dispose();
	}
	private void submitOui(Tournoi t) {
		try {
			t.delete();
		} catch (ErreurBD e) {
			// TODO Auto-generated catch block
			ErrorMessage.ErrorMessage(e.getMessage());
		};
		parent.setListTournois();
		frame.dispose();
	}
}