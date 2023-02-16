package Ecurie;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Commons.Colors;
import Commons.ErrorMessage;
import Commons.Header;
import Commons.JButtonDark;
import Commons.JButtonYellow;
import Commons.JPanelBackground;
import Commons.JPanelDarkest;
import Commons.PanelPresentationEquipe;
import Commons.PanelPresentationTournoi;
import code.Ecurie;
import code.Equipe;
import code.ErreurBD;
import code.Tournoi;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AccueilEcurie {

	private JFrame frame;
	private JPanelBackground panelRight;
	private JButtonYellow buttonInscriptionTournois;
	private JList<String> listTournois;
	private Equipe eq;
	private Tournoi t;
	private JPanelBackground panelListTournoi;
	private JScrollPane scrollTournois;
	private JScrollPane scrollEquipe;
	private AccueilEcurie thisInstance;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccueilEcurie window = new AccueilEcurie();
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
	public AccueilEcurie() throws ErreurBD {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ErreurBD 
	 */
	private void initialize() throws ErreurBD {
		thisInstance = this;
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("E-Sporter | Accueil");
		frame.setLocationRelativeTo(null);
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
		
		JButtonDark buttonRefreshEquipes = new JButtonDark("rafraîchir");
		buttonRefreshEquipes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		buttonRefreshEquipes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					setListEquipes();
			}
			}
		});
		buttonRefreshEquipes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setListEquipes();
			}
		});
		panelButtonAddEquipe.add(buttonRefreshEquipes);
		
		JButtonYellow buttonAddEquipe = new JButtonYellow("Nouvelle Equipe");
		buttonAddEquipe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					submitNouvelleEquipe();
			}
			}
		});
		buttonAddEquipe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitNouvelleEquipe();
			}
		});
		panelButtonAddEquipe.add(buttonAddEquipe);
		
		JPanelBackground panelScrollEquipe = new JPanelBackground();
		panelScrollEquipe.setAlignmentX(0.0f);
		panelScrollEquipe.setAlignmentY(Component.TOP_ALIGNMENT);
		panelMenuLeft.add(panelScrollEquipe);
		panelScrollEquipe.setLayout(new BorderLayout(0, 0));
		
		scrollEquipe = new JScrollPane();
		scrollEquipe.setBorder(new LineBorder(Color.BLACK));
		panelScrollEquipe.add(scrollEquipe);
		
		setListEquipes();
        
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
		buttonInscriptionTournois.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					submitInscrire();
			}
			}
		});
		buttonInscriptionTournois.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitInscrire();
			}
		});
		buttonInscriptionTournois.setEnabled(false);
		panelButtonInscriptionTournois.add(buttonInscriptionTournois);
		
		JPanelBackground panelScrollTournois = new JPanelBackground();
		panelScrollTournois.setAlignmentY(0.0f);
		panelScrollTournois.setAlignmentX(0.0f);
		panelRight.add(panelScrollTournois);
		panelScrollTournois.setLayout(new BorderLayout(0, 0));
		
		scrollTournois = new JScrollPane();
		scrollTournois.setBorder(new LineBorder(Color.BLACK));
		panelScrollTournois.add(scrollTournois);
		
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
	
	public void setListEquipes() {
		panelRight = new JPanelBackground();
		panelRight.setVisible(false);
		JPanelBackground panelListEquipe = new JPanelBackground();
        panelListEquipe.setLayout(new GridLayout(0, 1, 0, 0));
        

        int sizeEcurie;
		try {
			sizeEcurie = new Ecurie(Header.header).getEquipe().size();
		PanelPresentationEquipe[] panelsPresentationEquipe = new PanelPresentationEquipe[sizeEcurie];
        for (int i = 0; i < sizeEcurie; i++) {
        	eq = new Ecurie(Header.header).getEquipe().get(i);
            JLabel labelEquipe = new JLabel();
            labelEquipe.setText(eq.getNom());
            PanelPresentationEquipe presentEquipe = new PanelPresentationEquipe(eq);
            presentEquipe.getPanel().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	eq = presentEquipe.getEquipe();
                	setListTournois();
                	for (PanelPresentationEquipe pe : panelsPresentationEquipe) {
                		pe.changeBorderColor(Color.black, 1);
                	}
                	presentEquipe.changeBorderColor(Colors.lightText, 2);
                	panelRight.setVisible(true);
                }
            });
            panelsPresentationEquipe[i] = presentEquipe;
            panelListEquipe.add(presentEquipe.getPanel());
        }

        for (int i = new Ecurie(Header.header).getEquipe().size(); i < 4; i++) {
            panelListEquipe.add(new JPanelDarkest());
        }
		} catch (ErreurBD e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        scrollEquipe.setViewportView(panelListEquipe);
	}
	
	private void setListTournois() {
		buttonInscriptionTournois.setEnabled(false);
		int sizeTournoi;
		panelListTournoi = new JPanelBackground();
        panelListTournoi.setLayout(new GridLayout(0, 1, 0, 0));
		try {
			sizeTournoi = Tournoi.getAvailableEquipe(eq).length;
			PanelPresentationTournoi[] panelsPresentationTournoi = new PanelPresentationTournoi[sizeTournoi];
	        for (int i = 0; i < sizeTournoi; i++) {
	        	t = Tournoi.getAvailableEquipe(eq)[i];
	            JLabel labelTournoi = new JLabel();
	            labelTournoi.setText(t.getNom());
	            PanelPresentationTournoi presentTournoi = new PanelPresentationTournoi(t, false);
	            presentTournoi.getPanel().addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                	 t = presentTournoi.getTournoi();
	                	 for (PanelPresentationTournoi pe : panelsPresentationTournoi) {
	                		 pe.changeBorderColor(Color.black, 1);
	                	 }
	                	 presentTournoi.changeBorderColor(Colors.lightText, 2);
	                	 buttonInscriptionTournois.setEnabled(true);
	                }
	            });
	            panelsPresentationTournoi[i] = presentTournoi;
	            panelListTournoi.add(presentTournoi.getPanel());
	        }
	
	        for (int i = sizeTournoi; i < 4; i++) {
	            panelListTournoi.add(new JPanelDarkest());
	        }
		} catch (ErreurBD e1) {
			// TODO Auto-generated catch block
			ErrorMessage.ErrorMessage(e1.getMessage());
		}
        
        scrollTournois.setViewportView(panelListTournoi);
	}
	
	private void submitNouvelleEquipe() {
		try {
			CreerEquipe.MainWithValue(Ecurie.getID(new Ecurie(Header.header)), thisInstance);
		} catch (ErreurBD e1) {
			// TODO Auto-generated catch block
			ErrorMessage.ErrorMessage(e1.getMessage());
		};
	}
	private void submitInscrire() {
		if (buttonInscriptionTournois.isEnabled()) {
			PopUp_ConfirmInscription.mainWithValues(eq,t,thisInstance);
		}
	}
}

