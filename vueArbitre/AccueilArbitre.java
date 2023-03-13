package vueArbitre;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;

import modele.Arbitre;
import modele.ErreurBD;
import modele.Match;
import vue.Colors;
import vue.ErrorMessage;
import vue.Header;
import vue.JButtonYellow;
import vue.JPanelBackground;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AccueilArbitre {

	private JFrame frame;
	private JButtonYellow buttonInsertScore;
	private JList<String> listTournoi;
	private JScrollPane scrollTournoi;
	private AccueilArbitre thisInstance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccueilArbitre window = new AccueilArbitre();
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
	public AccueilArbitre() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		thisInstance = this;
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setBounds(100, 100, 643, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("E-Sporter | Accueil");
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		Header headerArbitre = new Header(frame);

		JPanelBackground panelSpacing_BottomTitle = new JPanelBackground();
		headerArbitre.getPanelHeader().add(panelSpacing_BottomTitle);

		JPanelBackground panelMenu = new JPanelBackground();
		frame.getContentPane().add(panelMenu, BorderLayout.CENTER);
		panelMenu.setLayout(new BorderLayout(0, 0));

		JPanelBackground panelLabelTitleMatch = new JPanelBackground();
		FlowLayout fl_panelLabelTitleMatch = (FlowLayout) panelLabelTitleMatch.getLayout();
		fl_panelLabelTitleMatch.setAlignment(FlowLayout.LEFT);
		fl_panelLabelTitleMatch.setHgap(325);
		panelMenu.add(panelLabelTitleMatch, BorderLayout.NORTH);

		JLabel labelTitleMatch = new JLabel("Matchs :");
		labelTitleMatch.setForeground(Colors.lightText);
		panelLabelTitleMatch.add(labelTitleMatch);

		JPanelBackground panelScrollTournoi = new JPanelBackground();
		panelMenu.add(panelScrollTournoi);
		panelScrollTournoi.setLayout(new BorderLayout(0, 0));

		JPanelBackground panelSpacing_ScrollTop = new JPanelBackground();
		panelScrollTournoi.add(panelSpacing_ScrollTop, BorderLayout.NORTH);
		panelSpacing_ScrollTop.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		scrollTournoi = new JScrollPane();
		scrollTournoi.setBorder(new LineBorder(Color.black));
		panelScrollTournoi.add(scrollTournoi);

		setListMatch();

		JPanelBackground panelSpacing_ScrollBottom = new JPanelBackground();
		panelScrollTournoi.add(panelSpacing_ScrollBottom, BorderLayout.SOUTH);
		panelSpacing_ScrollBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 52));

		JPanelBackground panelSpacing_ScrollLeft = new JPanelBackground();
		panelScrollTournoi.add(panelSpacing_ScrollLeft, BorderLayout.WEST);
		panelSpacing_ScrollLeft.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanelBackground panelSpacing_ScrollRight = new JPanelBackground();
		panelScrollTournoi.add(panelSpacing_ScrollRight, BorderLayout.EAST);
		panelSpacing_ScrollRight.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanelBackground panelSpacing_MenuLeft = new JPanelBackground();
		FlowLayout fl_panelSpacing_MenuLeft = (FlowLayout) panelSpacing_MenuLeft.getLayout();
		fl_panelSpacing_MenuLeft.setHgap(150);
		panelMenu.add(panelSpacing_MenuLeft, BorderLayout.WEST);

		JPanelBackground panelButtonInsertScore = new JPanelBackground();
		panelMenu.add(panelButtonInsertScore, BorderLayout.EAST);
		panelButtonInsertScore.setLayout(new GridLayout(0, 3, 0, 0));

		JPanelBackground panelButtonInsertScoreInner = new JPanelBackground();
		FlowLayout fl_panelButtonInsertScoreInner = (FlowLayout) panelButtonInsertScoreInner.getLayout();
		fl_panelButtonInsertScoreInner.setVgap(10);
		panelButtonInsertScore.add(panelButtonInsertScoreInner);

		buttonInsertScore = new JButtonYellow("Saisir Score");
		buttonInsertScore.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ouvrirSaisirScore();
				}
			}
		});
		buttonInsertScore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ouvrirSaisirScore();
			}
		});
		panelButtonInsertScoreInner.add(buttonInsertScore);
		buttonInsertScore.setEnabled(false);
		;

		JPanelBackground panelSpacing_ButtonCenter = new JPanelBackground();
		panelButtonInsertScore.add(panelSpacing_ButtonCenter);

		JPanelBackground panelSpacing_ButtonRight = new JPanelBackground();
		panelButtonInsertScore.add(panelSpacing_ButtonRight);
	}

	public void setListMatch() {
		this.listTournoi = new JList<String>();
		listTournoi.setBackground(Colors.darkestBlue);
		listTournoi.setForeground(Colors.lightText);
		listTournoi.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listTournoi.getSelectedValue() != null) {
					buttonInsertScore.setEnabled(true);
				} else {
					buttonInsertScore.setEnabled(false);
				}
			}
		});
		try {
			Match[] v = new Arbitre(Header.header).getMatch();
			if (v != null) {
				listTournoi.setModel(new AbstractListModel() {
					Match[] values = v;

					public int getSize() {
						return values.length;
					}

					public Object getElementAt(int index) {
						return values[index];
					}
				});
				listTournoi.setEnabled(true);
			} else {
				listTournoi.setModel(new AbstractListModel() {
					String[] values = { "Aucun match à arbitrer." };

					public int getSize() {
						return values.length;
					}

					public Object getElementAt(int index) {
						return values[index];
					}
				});
				listTournoi.setEnabled(false);
			}

		} catch (ErreurBD e1) {
			// TODO Auto-generated catch block
			ErrorMessage.ErrorMessage(e1.getMessage());
		}
		scrollTournoi.setViewportView(listTournoi);
	}

	private void ouvrirSaisirScore() {
		if (buttonInsertScore.isEnabled()) {
			try {
				SaisirScore.mainWithValues(new Arbitre(Header.header).getMatch()[this.listTournoi.getSelectedIndex()],
						thisInstance);
			} catch (ErreurBD e) {
				// TODO Auto-generated catch block
				ErrorMessage.ErrorMessage(e.getMessage());
			}
		}
	}
}