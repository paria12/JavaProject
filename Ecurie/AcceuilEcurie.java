package Ecurie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Commons.Colors;
import Commons.Header;
import Commons.JButtonDark;
import Commons.JButtonYellow;
import Commons.JPanelBackground;
import code.Ecurie;
import code.Equipe;
import code.ErreurBD;

public class AcceuilEcurie {

	private JFrame frame;
	private JPanelBackground panelRight;
	private JButtonYellow buttonInscriptionTournois;
	private JList<String> listEquipe;
	private AcceuilEcurie thisInstance = this;
	
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
	 * @throws ErreurBD 
	 */
	public AcceuilEcurie() throws ErreurBD {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ErreurBD 
	 */
	private void initialize() throws ErreurBD {
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		Header headerEcurie = new Header(frame);
		
		JPanelBackground panelMenu = new JPanelBackground();
		frame.getContentPane().add(panelMenu, BorderLayout.CENTER);
		panelMenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanelBackground panelMenuLeft = new JPanelBackground();
		panelMenu.add(panelMenuLeft);
		panelMenuLeft.setLayout(new BorderLayout(0, 0));
		
		JPanelBackground panelMenuLeftHeader = new JPanelBackground();
		panelMenuLeftHeader.setAlignmentX(1.0f);
		panelMenuLeftHeader.setAlignmentY(0.0f);
		panelMenuLeft.add(panelMenuLeftHeader, BorderLayout.NORTH);
		panelMenuLeftHeader.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanelBackground panelLabelEquipes = new JPanelBackground();
		FlowLayout fl_panelLabelEquipes = (FlowLayout) panelLabelEquipes.getLayout();
		fl_panelLabelEquipes.setVgap(10);
		fl_panelLabelEquipes.setHgap(25);
		fl_panelLabelEquipes.setAlignment(FlowLayout.LEFT);
		panelMenuLeftHeader.add(panelLabelEquipes);
		
		JLabel labelEquipes = new JLabel("Equipes :");
		labelEquipes.setForeground(Colors.lightText);
		panelLabelEquipes.add(labelEquipes);
		
		JPanelBackground panelButtonAddEquipe = new JPanelBackground();
		FlowLayout fl_panelButtonAddEquipe = (FlowLayout) panelButtonAddEquipe.getLayout();
		fl_panelButtonAddEquipe.setHgap(10);
		fl_panelButtonAddEquipe.setAlignment(FlowLayout.RIGHT);
		panelMenuLeftHeader.add(panelButtonAddEquipe);
		
		JButtonDark buttonRefreshEquipe = new JButtonDark("refresh");
		buttonRefreshEquipe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refreshEquipe();
			}
		});
		panelButtonAddEquipe.add(buttonRefreshEquipe);
		
		JButtonYellow buttonAddEquipe = new JButtonYellow("Nouvelle Equipe");
		buttonAddEquipe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					System.out.println(Header.header);
					CreerEquipe.MainWithValue(Ecurie.getID(new Ecurie(Header.header)), thisInstance);
				} catch (ErreurBD e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				};
			}
		});
		panelButtonAddEquipe.add(buttonAddEquipe);
		
		JPanelBackground panelScrollEquipe = new JPanelBackground();
		panelScrollEquipe.setAlignmentX(0.0f);
		panelScrollEquipe.setAlignmentY(Component.TOP_ALIGNMENT);
		panelMenuLeft.add(panelScrollEquipe);
		panelScrollEquipe.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollEquipe = new JScrollPane();
		scrollEquipe.setBorder(new LineBorder(Color.BLACK));
		panelScrollEquipe.add(scrollEquipe);
		
		listEquipe = new JList<String>();
		listEquipe.setBackground(Colors.darkestBlue);
		listEquipe.setForeground(Colors.lightText);
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
			String[] values = Equipe.getNomEquipe(Ecurie.getID(new Ecurie(Header.header)));
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollEquipe.setViewportView(listEquipe);
		
		JPanelBackground panelSpacing_EquipeBottom = new JPanelBackground();
		FlowLayout fl_panelSpacing_EquipeBottom = (FlowLayout) panelSpacing_EquipeBottom.getLayout();
		fl_panelSpacing_EquipeBottom.setVgap(52);
		panelMenuLeft.add(panelSpacing_EquipeBottom, BorderLayout.SOUTH);
		
		JPanelBackground panelSpacing_EquipeLeft = new JPanelBackground();
		FlowLayout fl_panelSpacing_EquipeLeft = (FlowLayout) panelSpacing_EquipeLeft.getLayout();
		fl_panelSpacing_EquipeLeft.setHgap(12);
		panelMenuLeft.add(panelSpacing_EquipeLeft, BorderLayout.WEST);
		
		JPanelBackground panelSpacing_EquipeRight = new JPanelBackground();
		panelMenuLeft.add(panelSpacing_EquipeRight, BorderLayout.EAST);
		
		panelRight = new JPanelBackground();
		panelMenu.add(panelRight);
		panelRight.setLayout(new BorderLayout(0, 0));
		panelRight.setVisible(false);
		
		JPanelBackground panelRightHeader = new JPanelBackground();
		panelRightHeader.setAlignmentY(0.0f);
		panelRightHeader.setAlignmentX(1.0f);
		panelRight.add(panelRightHeader, BorderLayout.NORTH);
		panelRightHeader.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanelBackground panelLabelTournois = new JPanelBackground();
		FlowLayout fl_panelLabelTournois = (FlowLayout) panelLabelTournois.getLayout();
		fl_panelLabelTournois.setVgap(10);
		fl_panelLabelTournois.setAlignment(FlowLayout.LEFT);
		fl_panelLabelTournois.setHgap(10);
		panelRightHeader.add(panelLabelTournois);
		
		JLabel labelTournois = new JLabel("Tournois disponibles :");
		labelTournois.setForeground(Colors.lightText);
		panelLabelTournois.add(labelTournois);
		
		JPanelBackground panelButtonInscriptionTournois = new JPanelBackground();
		FlowLayout fl_panelButtonInscriptionTournois = (FlowLayout) panelButtonInscriptionTournois.getLayout();
		fl_panelButtonInscriptionTournois.setHgap(25);
		fl_panelButtonInscriptionTournois.setAlignment(FlowLayout.RIGHT);
		panelRightHeader.add(panelButtonInscriptionTournois);
		
		buttonInscriptionTournois = new JButtonYellow("Inscrire");
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
		
		JPanelBackground panelScrollTournois = new JPanelBackground();
		panelScrollTournois.setAlignmentY(0.0f);
		panelScrollTournois.setAlignmentX(0.0f);
		panelRight.add(panelScrollTournois);
		panelScrollTournois.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollTournois = new JScrollPane();
		panelScrollTournois.add(scrollTournois);
		
		JList<String> listTournois = new JList<String>();
		listTournois.setBackground(Colors.darkestBlue);
		listTournois.setForeground(Colors.lightText);
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
		
		JPanelBackground panelSpacing_TournoisBottom = new JPanelBackground();
		FlowLayout fl_panelSpacing_TournoisBottom = (FlowLayout) panelSpacing_TournoisBottom.getLayout();
		fl_panelSpacing_TournoisBottom.setVgap(52);
		panelRight.add(panelSpacing_TournoisBottom, BorderLayout.SOUTH);
		
		JPanelBackground panelSpacing_TournoisLeft = new JPanelBackground();
		panelRight.add(panelSpacing_TournoisLeft, BorderLayout.WEST);
		
		JPanelBackground panelSpacing_TournoisRight = new JPanelBackground();
		FlowLayout fl_panelSpacing_TournoisRight = (FlowLayout) panelSpacing_TournoisRight.getLayout();
		fl_panelSpacing_TournoisRight.setHgap(12);
		panelRight.add(panelSpacing_TournoisRight, BorderLayout.EAST);
	}
	
	public void refreshEquipe() {
		try {
			listEquipe.setModel(new AbstractListModel() {
				String[] values = Equipe.getNomEquipe(Ecurie.getID(new Ecurie(Header.header)));
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
		} catch (ErreurBD e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
