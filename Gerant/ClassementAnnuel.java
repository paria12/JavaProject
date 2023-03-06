package Gerant;

import java.awt.EventQueue;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import Commons.JPanelBackground;
import code.Equipe;
import code.ErreurBD;
import code.Jeu;

import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import Commons.JComboBoxDark;
import Commons.Colors;
import Commons.ErrorMessage;
import Commons.JButtonDark;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSpinner;
import java.awt.FlowLayout;

public class ClassementAnnuel {

	private JFrame frmClassementAnnuel;
	private JList listClassement;
	private JScrollPane scrollClassement;
	private JPanelBackground panelScrollClassement;
	private JComboBoxDark<String> comboGame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassementAnnuel window = new ClassementAnnuel();
					window.frmClassementAnnuel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClassementAnnuel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClassementAnnuel = new JFrame();
		frmClassementAnnuel.setTitle("E-Sporter | Classement annuel");
		frmClassementAnnuel.setLocationRelativeTo(null);
		frmClassementAnnuel.setBounds(100, 100, 350, 350);
		frmClassementAnnuel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanelBackground panelTitle = new JPanelBackground();
		frmClassementAnnuel.getContentPane().add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanelBackground panelSpacing_TopTitle = new JPanelBackground();
		panelTitle.add(panelSpacing_TopTitle);
		
		JLabel labelTitle = new JLabel("Classement annuel");
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setForeground(new Color(150, 180, 180));
		labelTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelTitle.add(labelTitle);
		
		JPanelBackground panelSpacing_BottomTitle = new JPanelBackground();
		panelTitle.add(panelSpacing_BottomTitle);
		
		JPanelBackground panelFormAndClassement = new JPanelBackground();
		frmClassementAnnuel.getContentPane().add(panelFormAndClassement, BorderLayout.CENTER);
		panelFormAndClassement.setLayout(new BorderLayout(0, 0));
		
		JPanelBackground panelSpacing_BottomTitle_1 = new JPanelBackground();
		FlowLayout flowLayout_2 = (FlowLayout) panelSpacing_BottomTitle_1.getLayout();
		flowLayout_2.setVgap(10);
		flowLayout_2.setHgap(20);
		panelFormAndClassement.add(panelSpacing_BottomTitle_1, BorderLayout.SOUTH);
		
		JPanelBackground panelComboGame = new JPanelBackground();
		panelFormAndClassement.add(panelComboGame, BorderLayout.NORTH);
		
		comboGame = new JComboBoxDark<String>();
		try {
			comboGame.setModel(new DefaultComboBoxModel(Jeu.getAll()));
		} catch (ErreurBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panelComboGame.add(comboGame);
		
		JButtonDark buttonChooseGame = new JButtonDark("+");
		buttonChooseGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setClassement();
				panelScrollClassement.setVisible(true);
			}
		});
		buttonChooseGame.setText("Choisir");
		buttonChooseGame.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelComboGame.add(buttonChooseGame);
		
		JPanelBackground panelListClassement = new JPanelBackground();
		panelFormAndClassement.add(panelListClassement);
		panelListClassement.setLayout(new BorderLayout(0, 0));
		
		panelScrollClassement = new JPanelBackground();
		panelListClassement.add(panelScrollClassement);
		panelScrollClassement.setLayout(new BorderLayout(20, 20));
		
		scrollClassement = new JScrollPane();
		scrollClassement.setBorder(new LineBorder(Color.BLACK));
		panelScrollClassement.add(scrollClassement);
		
		listClassement = new JList<String>();
		listClassement.setBackground(Colors.darkestBlue);
		listClassement.setForeground(Colors.lightText);
		listClassement.setModel(new AbstractListModel() {
			String[] values = new String[] {"lol", "mdr"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listClassement.setEnabled(false);
		scrollClassement.add(listClassement);
		
		JPanelBackground panelSpacing_BottomTitle_1_1 = new JPanelBackground();
		FlowLayout flowLayout = (FlowLayout) panelSpacing_BottomTitle_1_1.getLayout();
		flowLayout.setHgap(20);
		panelFormAndClassement.add(panelSpacing_BottomTitle_1_1, BorderLayout.WEST);
		
		JPanelBackground panelSpacing_BottomTitle_1_2 = new JPanelBackground();
		FlowLayout flowLayout_1 = (FlowLayout) panelSpacing_BottomTitle_1_2.getLayout();
		flowLayout_1.setHgap(20);
		panelFormAndClassement.add(panelSpacing_BottomTitle_1_2, BorderLayout.EAST);
		panelScrollClassement.setVisible(false);
	}
	
	public void setClassement() {
		scrollClassement.setViewportView(listClassement);
		try {
			String[] v = Equipe.getClassement(Jeu.getID(new Jeu(comboGame.getSelectedItem().toString())));
			for (int i = 0; i < v.length; i++) {
				if (v[i] != null) {
					Equipe e = new Equipe(v[i]);
					v[i] = v[i]+" ("+e.getNbPoints()+")";
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
		} catch (ErreurBD e) {
			// TODO Auto-generated catch block
			ErrorMessage.ErrorMessage(e.getMessage());
		}
	}

}
