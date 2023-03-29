package vueGerant;

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
import javax.swing.ListModel;

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

import controleur.ControleurGerant;
import modele.Equipe;
import modele.ErreurBD;
import modele.Tournoi;
import vue.Colors;
import vue.ErrorMessage;
import vue.Header;
import vue.JButtonDark;
import vue.JButtonYellow;
import vue.JPanelBackground;
import vue.JPanelDarkest;
import vue.PanelPresentationTournoi;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AccueilGerant {

	private JFrame frame;
	private JPanelBackground panelMenuRight;
	private JList<String> listClassement;
	private JPanelBackground panelListTournoi;
	private Tournoi t;
	private JScrollPane scrollTournoi;
	private AccueilGerant thisInstance;
	private JLabel labelClassement;
	private Equipe eq;
	private ControleurGerant controleur = new ControleurGerant();
	private PanelPresentationTournoi[] panelsPresentationTournoi;
	private PanelPresentationTournoi presentTournoi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccueilGerant window = new AccueilGerant();
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
	public AccueilGerant() throws ErreurBD {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ErreurBD 
	 */
	private void initialize() throws ErreurBD {
		controleur.setFenetreAccueil(this);
		thisInstance = this;
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("E-Sporter | Accueil");
		frame.setLocationRelativeTo(null);
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
		buttonAjouterJeu.addActionListener(ControleurGerant.getInstance());

		
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
		
		scrollTournoi = new JScrollPane();
		scrollTournoi.setBorder(new LineBorder(Color.BLACK));
		panelListTournoi.add(scrollTournoi, BorderLayout.CENTER);
		
		listClassement = new JList<String>();
		listClassement.setBackground(Colors.darkestBlue);
		listClassement.setForeground(Colors.lightText);
		listClassement.setEnabled(false);
		listClassement.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		setListTournois();
		
		JButtonDark buttonRefreshTournois = new JButtonDark("rafraîchir");
		buttonRefreshTournois.addActionListener(ControleurGerant.getInstance());
		
		panelButtonTournoi.add(buttonRefreshTournois);
		
		JButtonYellow buttonTournoi = new JButtonYellow("Nouveau Tournoi");
		buttonTournoi.addActionListener(ControleurGerant.getInstance());
		panelButtonTournoi.add(buttonTournoi);
		panelMenuLeft.setLayout(gl_panelMenuLeft);
		
		panelMenuRight = new JPanelBackground();
		panelMenu.add(panelMenuRight);
		labelClassement = new JLabel("");
		JPanelBackground panelListClassment = new JPanelBackground();
		
		JPanelBackground panelButtonTournoi_1 = new JPanelBackground();
		
		JButtonYellow buttonGeneralClassement = new JButtonYellow("Classement Annuel");
		buttonGeneralClassement.addActionListener(ControleurGerant.getInstance());
		
		panelButtonTournoi_1.add(buttonGeneralClassement);
		GroupLayout gl_panelMenuRight = new GroupLayout(panelMenuRight);
		gl_panelMenuRight.setHorizontalGroup(
			gl_panelMenuRight.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMenuRight.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelMenuRight.createParallelGroup(Alignment.LEADING)
						.addComponent(panelListClassment, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
						.addGroup(gl_panelMenuRight.createSequentialGroup()
							.addComponent(labelClassement)
							.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
							.addComponent(panelButtonTournoi_1, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panelMenuRight.setVerticalGroup(
			gl_panelMenuRight.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMenuRight.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelMenuRight.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelMenuRight.createSequentialGroup()
							.addComponent(labelClassement)
							.addGap(14))
						.addGroup(gl_panelMenuRight.createSequentialGroup()
							.addComponent(panelButtonTournoi_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(panelListClassment, GroupLayout.PREFERRED_SIZE, 108, Short.MAX_VALUE)
					.addGap(105))
		);
		panelListClassment.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollClassement = new JScrollPane();
		scrollClassement.setBorder(new LineBorder(Color.BLACK));
		panelListClassment.add(scrollClassement, BorderLayout.CENTER);
		
		scrollClassement.setViewportView(listClassement);
		
		panelMenuRight.setLayout(gl_panelMenuRight);
		
		Header headerGerant = new Header(frame);
		
		frame.addWindowListener(ControleurGerant.getInstance());
	}
	public void submitNouveauTournoi() {
		CreerTournoi.mainWithValues(thisInstance);
	}
	public void submitAjouterJeu() {
		AjouterJeu.main(null);
	}
	public void setListTournois() {
		listClassement.setModel(new AbstractListModel() {
			String[] values = {"Veuillez sélectionner un tournoi pour en obtenir le classement spécifique."};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		int sizeTournoi;
		panelListTournoi = new JPanelBackground();
        panelListTournoi.setLayout(new GridLayout(0, 1, 0, 0));
		try {
			sizeTournoi = Tournoi.getAll().length;
			panelsPresentationTournoi = new PanelPresentationTournoi[sizeTournoi];
	        for (int i = 0; i < sizeTournoi; i++) {
		        t = Tournoi.getAll()[i];
		            JLabel labelTournoi = new JLabel();
		            labelTournoi.setText(t.getNom());
		            presentTournoi = new PanelPresentationTournoi(t, true, thisInstance);
		            presentTournoi.getPanel().setName("pT"+i);
		            presentTournoi.getPanel().addMouseListener(ControleurGerant.getInstance());
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
        
        scrollTournoi.setViewportView(panelListTournoi);
	}
	public void dispose() {
		frame.dispose();
	}
	public void selectionTournoi() {
		t = presentTournoi.getTournoi();
		try {
			if(t.getDate().getTime() > System.currentTimeMillis()) {
    			labelClassement.setText("Liste des équipes inscrites");
    			labelClassement.setForeground(Colors.lightText);
    		}else{
    			labelClassement.setText("Classement");
    			labelClassement.setForeground(Colors.lightText);
    		}
			String[] v = t.getClassement()[0];
			for (int i = 0; i < v.length; i++) {
				if (v[i] != null) {
					v[i] = v[i]+" ("+t.getClassement()[1][i]+")";
				}
			}
			listClassement.setModel(new AbstractListModel() {
				String[] values = v;
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
		} catch (ErreurBD e1) {
			// TODO Auto-generated catch block
			ErrorMessage.ErrorMessage(e1.getMessage());
		}
		for (PanelPresentationTournoi pe : panelsPresentationTournoi) {
    		 pe.changeBorderColor(Color.black, 1);
    	 }
    	 presentTournoi.changeBorderColor(Colors.lightText, 2);
	}
	public void fermerAccueilGerant() {
		frame.dispose();
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
}
