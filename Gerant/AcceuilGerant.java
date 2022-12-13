package Gerant;

import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Commons.Colors;
import Commons.Header;
import Commons.JButtonYellow;
import Commons.JPanelBackground;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AcceuilGerant {

	private JFrame frame;
	private JPanelBackground panelMenuRight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AcceuilGerant window = new AcceuilGerant();
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
	public AcceuilGerant() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanelBackground panelMenu = new JPanelBackground();
		frame.getContentPane().add(panelMenu, BorderLayout.CENTER);
		panelMenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanelBackground panelMenuLeft = new JPanelBackground();
		panelMenu.add(panelMenuLeft);
		
		JLabel labelTournoi = new JLabel("Tournoi :");
		labelTournoi.setForeground(Colors.lightText);
		
		JPanelBackground panelListTournoi = new JPanelBackground();
		panelListTournoi.setLayout(new BorderLayout(0, 0));
		
		JPanelBackground panelButtonTournoi = new JPanelBackground();
		
		JButtonYellow buttonAjouterJeu = new JButtonYellow("Ajouter jeu");
		buttonAjouterJeu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AjouterJeu.main(null);
			}
		});
		buttonAjouterJeu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panelMenuLeft = new GroupLayout(panelMenuLeft);
		gl_panelMenuLeft.setHorizontalGroup(
			gl_panelMenuLeft.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelMenuLeft.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelMenuLeft.createParallelGroup(Alignment.LEADING)
						.addComponent(panelListTournoi, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panelMenuLeft.createSequentialGroup()
							.addComponent(labelTournoi)
							.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
							.addComponent(panelButtonTournoi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(buttonAjouterJeu))
					.addContainerGap())
		);
		gl_panelMenuLeft.setVerticalGroup(
			gl_panelMenuLeft.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMenuLeft.createSequentialGroup()
					.addGroup(gl_panelMenuLeft.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelMenuLeft.createSequentialGroup()
							.addGap(9)
							.addComponent(panelButtonTournoi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelMenuLeft.createSequentialGroup()
							.addGap(20)
							.addComponent(labelTournoi)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelListTournoi, GroupLayout.PREFERRED_SIZE, 108, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(buttonAjouterJeu)
					.addGap(58))
		);
		
		JScrollPane scrollTournoi = new JScrollPane();
		scrollTournoi.setBorder(new LineBorder(Color.BLACK));
		panelListTournoi.add(scrollTournoi, BorderLayout.CENTER);
		
		JList<String> listTournoi = new JList<String>();
		listTournoi.setBackground(Colors.darkestBlue);
		listTournoi.setForeground(Colors.lightText);
		listTournoi.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listTournoi.getSelectedValue() != null) {
					panelMenuRight.setVisible(true);
				} else {
					panelMenuRight.setVisible(false);
				}
			}
		});
		scrollTournoi.setViewportView(listTournoi);
		listTournoi.setModel(new AbstractListModel() {
			String[] values = new String[] {"18/28/24 Toulouse"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JButtonYellow buttonTournoi = new JButtonYellow("Nouveau Tournoi");
		buttonTournoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CreerTournoi.main(null);
			}
		});
		panelButtonTournoi.add(buttonTournoi);
		panelMenuLeft.setLayout(gl_panelMenuLeft);
		
		panelMenuRight = new JPanelBackground();
		panelMenu.add(panelMenuRight);
		
		JLabel labelClassement = new JLabel("Classement");
		labelClassement.setForeground(Colors.lightText);
		
		JPanelBackground panelListClassment = new JPanelBackground();
		GroupLayout gl_panelMenuRight = new GroupLayout(panelMenuRight);
		gl_panelMenuRight.setHorizontalGroup(
			gl_panelMenuRight.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMenuRight.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelMenuRight.createParallelGroup(Alignment.LEADING)
						.addComponent(panelListClassment, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
						.addComponent(labelClassement))
					.addContainerGap())
		);
		gl_panelMenuRight.setVerticalGroup(
			gl_panelMenuRight.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMenuRight.createSequentialGroup()
					.addGap(20)
					.addComponent(labelClassement)
					.addGap(14)
					.addComponent(panelListClassment, GroupLayout.PREFERRED_SIZE, 108, Short.MAX_VALUE)
					.addGap(105))
		);
		panelListClassment.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollClassement = new JScrollPane();
		scrollClassement.setBorder(new LineBorder(Color.BLACK));
		panelListClassment.add(scrollClassement, BorderLayout.CENTER);
		
		JList<String> listClassement = new JList<String>();
		listClassement.setBackground(Colors.darkestBlue);
		listClassement.setForeground(Colors.lightText);
		listClassement.setEnabled(false);
		scrollClassement.setViewportView(listClassement);
		listClassement.setModel(new AbstractListModel() {
			String[] values = new String[] {"Equipe 1", "Equipe 2 ", "Equipe 3", "Equipe 4", "Equipe 5", "Equipe 6", "Equipe 7", "Equipe 8", "Equipe 9", "Equipe 10", "Equipe 11", "Equipe 12", "Equipe 13", "Equipe 14", "Equipe 15", "Equipe 16"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		panelMenuRight.setLayout(gl_panelMenuRight);
		panelMenuRight.setVisible(false);
		
		Header headerGerant = new Header(frame);
	}
}
