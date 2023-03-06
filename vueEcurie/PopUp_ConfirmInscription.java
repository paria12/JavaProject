package vueEcurie;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import modele.Equipe;
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

public class PopUp_ConfirmInscription {

	private JFrame frame;
	private AccueilEcurie parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopUp_ConfirmInscription window = new PopUp_ConfirmInscription(new Equipe("Best OF The Best"),new Tournoi("Esport world convention",new Date(2023-01-15),1), new AccueilEcurie());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void mainWithValues(Equipe eq, Tournoi t, AccueilEcurie p) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopUp_ConfirmInscription window = new PopUp_ConfirmInscription(eq,t,p);
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
	public PopUp_ConfirmInscription(Equipe equipe,Tournoi tournoi,AccueilEcurie parent) {
		initialize(equipe,tournoi,parent);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Equipe equipe,Tournoi tournoi, AccueilEcurie parent) {
		this.parent = parent;
		frame = new JFrame();
		frame.setBounds(100, 100, 250, 250);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Confirmation Inscription ?");
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanelBackground panelHeader = new JPanelBackground();
		frame.getContentPane().add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new GridLayout(0, 1, 0, 0));

		JPanelBackground panelSpacing_HeaderTop = new JPanelBackground();
		panelHeader.add(panelSpacing_HeaderTop);
		
		JPanelBackground panelLabelSouhaitez = new JPanelBackground();
		panelHeader.add(panelLabelSouhaitez);
		
		JLabel labelSouhaitez = new JLabel("Souhaitez-vous inscrire");
		labelSouhaitez.setForeground(Colors.lightText);
		labelSouhaitez.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLabelSouhaitez.add(labelSouhaitez);
		
		JPanelBackground panelLabelEquipe = new JPanelBackground();
		panelHeader.add(panelLabelEquipe);
		
		JLabel labelEquipe = new JLabel(equipe.getNom()+" au tournoi ");
		labelEquipe.setForeground(Colors.lightText);
		labelEquipe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLabelEquipe.add(labelEquipe);
		
		JPanelBackground panelLabelNom = new JPanelBackground();
		panelHeader.add(panelLabelNom);
		
		JLabel labelNom = new JLabel(tournoi.getNom());
		labelNom.setForeground(Colors.lightText);
		labelNom.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelLabelNom.add(labelNom);
		
		JPanelBackground panelLabelDate = new JPanelBackground();
		panelHeader.add(panelLabelDate);
		
		JLabel labelDate = new JLabel("du "+tournoi.getDate()+" ?");
		labelDate.setForeground(Colors.lightText);
		labelDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLabelDate.add(labelDate);
		
		JPanelBackground panelMenu = new JPanelBackground();
		frame.getContentPane().add(panelMenu, BorderLayout.CENTER);
		panelMenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanelBackground panelButtonNo = new JPanelBackground();
		FlowLayout fl_panelButtonNo = (FlowLayout) panelButtonNo.getLayout();
		fl_panelButtonNo.setVgap(20);
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
		fl_panelButtonYes.setVgap(20);
		panelMenu.add(panelButtonYes);
		
		JButtonYellow buttonYes = new JButtonYellow("Oui");
		buttonYes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					submitOui(tournoi,equipe);
				}
			}
		});
		buttonYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitOui(tournoi,equipe);
			}
		});
		panelButtonYes.add(buttonYes);
	}
	private void submitNon() {
		frame.dispose();
	}
	private void submitOui(Tournoi tournoi, Equipe equipe) {
		try {
			tournoi.addEquipe(equipe);
		} catch (ErreurBD e) {
			// TODO Auto-generated catch block
			ErrorMessage.ErrorMessage(e.getMessage());
		}
		parent.setListEquipes();
		frame.dispose();
	}
}
