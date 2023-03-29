package vueEcurie;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import controleur.ControleurEcurie;
import modele.Ecurie;
import modele.Equipe;
import modele.ErreurBD;
import modele.Joueur;
import modele.Tournoi;
import vue.Colors;
import vue.ErrorMessage;
import vue.Header;
import vue.JButtonDark;
import vue.JButtonYellow;
import vue.JPanelBackground;
import vue.JPanelDarkest;
import vue.PanelPresentationEquipe;
import vue.PanelPresentationTournoi;
import vueGerant.AccueilGerant;


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
	private JPanelBackground panelMenu;
	private PanelPresentationTournoi presentTournoi;
	private PanelPresentationTournoi[] panelsPresentationTournoi;
	private PanelPresentationEquipe[] panelsPresentationEquipe;
	private PanelPresentationEquipe presentEquipe;
	
	/**
	 * Create the application.
	 * @throws ErreurBD 
	 */
	public AccueilEcurie() throws ErreurBD {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ErreurBD 
	 */
	private void initialize() throws ErreurBD {
		thisInstance = this;
		ControleurEcurie.getInstance().setMainWindow(thisInstance);
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("E-Sporter | Accueil");
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		Header headerEcurie = new Header(frame);
		
		panelMenu = new JPanelBackground();
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
		buttonRefreshEquipes.addActionListener(ControleurEcurie.getInstance());
		panelButtonAddEquipe.add(buttonRefreshEquipes);
		
		JButtonYellow buttonAddEquipe = new JButtonYellow("Nouvelle Equipe");
		buttonAddEquipe.addActionListener(ControleurEcurie.getInstance());
		panelButtonAddEquipe.add(buttonAddEquipe);
		
		JPanelBackground panelScrollEquipe = new JPanelBackground();
		panelScrollEquipe.setAlignmentX(0.0f);
		panelScrollEquipe.setAlignmentY(Component.TOP_ALIGNMENT);
		panelMenuLeft.add(panelScrollEquipe);
		panelScrollEquipe.setLayout(new BorderLayout(0, 0));
		
		scrollEquipe = new JScrollPane();
		scrollEquipe.setBorder(new LineBorder(Color.BLACK));
		panelScrollEquipe.add(scrollEquipe);
		
		//Initialize Equipe list
		setListEquipes();
        
		//Spacing
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
		
		//Initialize Tournois list in no team selected mode
		eq = null;
		setListTournois();
		panelMenu.add(panelRight);
		

		frame.addWindowListener(ControleurEcurie.getInstance());
		
	}
	
	public void setListEquipes() {
		//Reinitialize Tournois list in no team selected mode
		eq = null;
		thisInstance.setListTournois();

		//Create list Equipe
		JPanelBackground panelListEquipe = new JPanelBackground();
        panelListEquipe.setLayout(new GridLayout(0, 1, 0, 0));
        

        int sizeEcurie;
		try {
			sizeEcurie = new Ecurie(Header.header).getEquipe().size();
		panelsPresentationEquipe = new PanelPresentationEquipe[sizeEcurie];
        for (int i = 0; i < sizeEcurie; i++) {
        	eq = new Ecurie(Header.header).getEquipe().get(i);
            JLabel labelEquipe = new JLabel();
            labelEquipe.setText(eq.getNom());
            presentEquipe = new PanelPresentationEquipe(eq);
            presentEquipe.getPanel().setName("pE"+i);
            presentEquipe.getPanel().addMouseListener(ControleurEcurie.getInstance());
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
		//If no equipe selected
		if (eq == null) {
			if (panelRight != null) {
				//setVisibility to false
				panelRight.setVisible(false);
			} else {
				//remove content from panelRight
				panelRight = new JPanelBackground();
				panelRight.setVisible(false);
			}
			panelRight.setLayout(new BorderLayout(0, 0));
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
			buttonInscriptionTournois.setEnabled(false);
			buttonInscriptionTournois.addActionListener(ControleurEcurie.getInstance());
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
		} else {
			buttonInscriptionTournois.setEnabled(false);
			int sizeTournoi;
			panelListTournoi = new JPanelBackground();
	        panelListTournoi.setLayout(new GridLayout(0, 1, 0, 0));
			try {
				System.out.println("resetContent");
				sizeTournoi = Tournoi.getAvailableEquipe(eq).length;
				panelsPresentationTournoi = new PanelPresentationTournoi[sizeTournoi];
		        for (int i = 0; i < sizeTournoi; i++) {
		        	t = Tournoi.getAvailableEquipe(eq)[i];
		            JLabel labelTournoi = new JLabel();
		            labelTournoi.setText(t.getNom());
		            presentTournoi = new PanelPresentationTournoi(t, false, new AccueilGerant());
		            presentTournoi.getPanel().setName("pT"+i);
		            presentTournoi.getPanel().addMouseListener(ControleurEcurie.getInstance());
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
        	panelRight.setVisible(true);
	        scrollTournois.setViewportView(panelListTournoi);
		}
	}
	
	public void showTurnamentChoosen() {
		t = presentTournoi.getTournoi();
        for (PanelPresentationTournoi pe : panelsPresentationTournoi) {
           	pe.changeBorderColor(Color.black, 1);
        }
        presentTournoi.changeBorderColor(Colors.lightText, 2);
        buttonInscriptionTournois.setEnabled(true);
	}
	
	public void showTeamChoosen() {
		eq = presentEquipe.getEquipe();
    	setListTournois();
    	for (PanelPresentationEquipe pe : panelsPresentationEquipe) {
    		pe.changeBorderColor(Color.black, 1);
    	}
    	presentEquipe.changeBorderColor(Colors.lightText, 2);
    	panelRight.setVisible(true);
	}
	
	public Equipe getEquipe() {
		return eq;
	}
	
	public Tournoi getTournoi() {
		return t;
	}
	
	public void setPresentEquipeByName(String name) {
		presentEquipe = null;
		for (PanelPresentationEquipe pe : panelsPresentationEquipe) {
			if (pe.getPanel().getName() == name) {
				presentEquipe = pe;
				return;
			}
		}
	}
	
	public void setPresentTournoiByName(String name) {
		presentTournoi = null;
		for (PanelPresentationTournoi pt : panelsPresentationTournoi) {
			if (pt.getPanel().getName() == name) {
				presentTournoi = pt;
				return;
			}
		}
	}

	public void dispose() {
		frame.dispose();
	}
	
}

