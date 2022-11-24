package Ecurie;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

import Commons.Header;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class AcceuilEcurie {

	private JFrame frame;
	private JPanel panelRight;
	private JButton buttonInscriptionTournois;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AcceuilEcurie window = new AcceuilEcurie();
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
	public AcceuilEcurie() {
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
		
		Header headerEcurie = new Header(frame);
		
		JPanel panelMenu = new JPanel();
		frame.getContentPane().add(panelMenu, BorderLayout.CENTER);
		panelMenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelMenuLeft = new JPanel();
		panelMenu.add(panelMenuLeft);
		panelMenuLeft.setLayout(new BorderLayout(0, 0));
		
		JPanel panelMenuLeftHeader = new JPanel();
		panelMenuLeftHeader.setAlignmentX(1.0f);
		panelMenuLeftHeader.setAlignmentY(0.0f);
		panelMenuLeft.add(panelMenuLeftHeader, BorderLayout.NORTH);
		panelMenuLeftHeader.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelLabelEquipes = new JPanel();
		FlowLayout fl_panelLabelEquipes = (FlowLayout) panelLabelEquipes.getLayout();
		fl_panelLabelEquipes.setVgap(10);
		fl_panelLabelEquipes.setHgap(25);
		fl_panelLabelEquipes.setAlignment(FlowLayout.LEFT);
		panelMenuLeftHeader.add(panelLabelEquipes);
		
		JLabel labelEquipes = new JLabel("Equipes :");
		panelLabelEquipes.add(labelEquipes);
		
		JPanel panelButtonAddEquipe = new JPanel();
		FlowLayout fl_panelButtonAddEquipe = (FlowLayout) panelButtonAddEquipe.getLayout();
		fl_panelButtonAddEquipe.setHgap(10);
		fl_panelButtonAddEquipe.setAlignment(FlowLayout.RIGHT);
		panelMenuLeftHeader.add(panelButtonAddEquipe);
		
		JButton buttonAddEquipe = new JButton("Nouvelle Equipe");
		buttonAddEquipe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CreerEquipe.main(null);
			}
		});
		panelButtonAddEquipe.add(buttonAddEquipe);
		
		JPanel panelScrollEquipe = new JPanel();
		panelScrollEquipe.setAlignmentX(0.0f);
		panelScrollEquipe.setAlignmentY(Component.TOP_ALIGNMENT);
		panelMenuLeft.add(panelScrollEquipe);
		panelScrollEquipe.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollEquipe = new JScrollPane();
		panelScrollEquipe.add(scrollEquipe);
		
		JList listEquipe = new JList();
		listEquipe.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listEquipe.getSelectedValue() != null) {
					panelRight.setVisible(true);
				} else {
					panelRight.setVisible(false);
				}
			}
		});
		listEquipe.setModel(new AbstractListModel() {
			String[] values = new String[] {"Equipe 1", "Equipe 2", "Equipe 3", "Equipe 4", "Equipe 5"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollEquipe.setViewportView(listEquipe);
		
		JPanel panelSpacing_EquipeBottom = new JPanel();
		FlowLayout fl_panelSpacing_EquipeBottom = (FlowLayout) panelSpacing_EquipeBottom.getLayout();
		fl_panelSpacing_EquipeBottom.setVgap(52);
		panelMenuLeft.add(panelSpacing_EquipeBottom, BorderLayout.SOUTH);
		
		JPanel panelSpacing_EquipeLeft = new JPanel();
		FlowLayout fl_panelSpacing_EquipeLeft = (FlowLayout) panelSpacing_EquipeLeft.getLayout();
		fl_panelSpacing_EquipeLeft.setHgap(12);
		panelMenuLeft.add(panelSpacing_EquipeLeft, BorderLayout.WEST);
		
		JPanel panelSpacing_EquipeRight = new JPanel();
		panelMenuLeft.add(panelSpacing_EquipeRight, BorderLayout.EAST);
		
		panelRight = new JPanel();
		panelMenu.add(panelRight);
		panelRight.setLayout(new BorderLayout(0, 0));
		panelRight.setVisible(false);
		
		JPanel panelRightHeader = new JPanel();
		panelRightHeader.setAlignmentY(0.0f);
		panelRightHeader.setAlignmentX(1.0f);
		panelRight.add(panelRightHeader, BorderLayout.NORTH);
		panelRightHeader.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelLabelTournois = new JPanel();
		FlowLayout fl_panelLabelTournois = (FlowLayout) panelLabelTournois.getLayout();
		fl_panelLabelTournois.setVgap(10);
		fl_panelLabelTournois.setAlignment(FlowLayout.LEFT);
		fl_panelLabelTournois.setHgap(10);
		panelRightHeader.add(panelLabelTournois);
		
		JLabel labelTournois = new JLabel("Tournois disponibles :");
		panelLabelTournois.add(labelTournois);
		
		JPanel panelButtonInscriptionTournois = new JPanel();
		FlowLayout fl_panelButtonInscriptionTournois = (FlowLayout) panelButtonInscriptionTournois.getLayout();
		fl_panelButtonInscriptionTournois.setHgap(25);
		fl_panelButtonInscriptionTournois.setAlignment(FlowLayout.RIGHT);
		panelRightHeader.add(panelButtonInscriptionTournois);
		
		buttonInscriptionTournois = new JButton("Inscrire");
		buttonInscriptionTournois.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (buttonInscriptionTournois.isEnabled()) {
					PopUp_ConfirmInscription.main(null);
				}
			}
		});
		buttonInscriptionTournois.setEnabled(false);
		panelButtonInscriptionTournois.add(buttonInscriptionTournois);
		
		JPanel panelScrollTournois = new JPanel();
		panelScrollTournois.setAlignmentY(0.0f);
		panelScrollTournois.setAlignmentX(0.0f);
		panelRight.add(panelScrollTournois);
		panelScrollTournois.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollTournois = new JScrollPane();
		panelScrollTournois.add(scrollTournois);
		
		JList listTournois = new JList();
		listTournois.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listTournois.getSelectedValue() != null) {
					buttonInscriptionTournois.setEnabled(true);
				} else {
					buttonInscriptionTournois.setEnabled(false);
				}
			}
		});
		listTournois.setModel(new AbstractListModel() {
			String[] values = new String[] {"23/02/2023 - Toulouse", "14/11/2023 - Perpignan", "05/12/2023 - Toulouse"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollTournois.setViewportView(listTournois);
		
		JPanel panelSpacing_TournoisBottom = new JPanel();
		FlowLayout fl_panelSpacing_TournoisBottom = (FlowLayout) panelSpacing_TournoisBottom.getLayout();
		fl_panelSpacing_TournoisBottom.setVgap(52);
		panelRight.add(panelSpacing_TournoisBottom, BorderLayout.SOUTH);
		
		JPanel panelSpacing_TournoisLeft = new JPanel();
		panelRight.add(panelSpacing_TournoisLeft, BorderLayout.WEST);
		
		JPanel panelSpacing_TournoisRight = new JPanel();
		FlowLayout fl_panelSpacing_TournoisRight = (FlowLayout) panelSpacing_TournoisRight.getLayout();
		fl_panelSpacing_TournoisRight.setHgap(12);
		panelRight.add(panelSpacing_TournoisRight, BorderLayout.EAST);
	}
}
