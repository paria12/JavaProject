package Ecurie;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopUp_ConfirmInscription {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopUp_ConfirmInscription window = new PopUp_ConfirmInscription();
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
	public PopUp_ConfirmInscription() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 250, 250);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelHeader = new JPanel();
		frame.getContentPane().add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelSpacing_HeaderTop = new JPanel();
		panelHeader.add(panelSpacing_HeaderTop);
		
		JPanel panelLabelSouhaitez = new JPanel();
		panelHeader.add(panelLabelSouhaitez);
		
		JLabel labelSouhaitez = new JLabel("Souhaitez-vous inscrire");
		labelSouhaitez.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLabelSouhaitez.add(labelSouhaitez);
		
		JPanel panelLabelEquipe = new JPanel();
		panelHeader.add(panelLabelEquipe);
		
		JLabel labelEquipe = new JLabel("l'[Equipe] au tournois ");
		labelEquipe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLabelEquipe.add(labelEquipe);
		
		JPanel panelLabelNom = new JPanel();
		panelHeader.add(panelLabelNom);
		
		JLabel labelNom = new JLabel("[nom]");
		labelNom.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelLabelNom.add(labelNom);
		
		JPanel panelLabelDate = new JPanel();
		panelHeader.add(panelLabelDate);
		
		JLabel labelDate = new JLabel("du [date] ?");
		labelDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLabelDate.add(labelDate);
		
		JPanel panelMenu = new JPanel();
		frame.getContentPane().add(panelMenu, BorderLayout.CENTER);
		panelMenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelButtonNo = new JPanel();
		FlowLayout fl_panelButtonNo = (FlowLayout) panelButtonNo.getLayout();
		fl_panelButtonNo.setVgap(20);
		panelMenu.add(panelButtonNo);
		
		JButton buttonNo = new JButton("Non");
		buttonNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		buttonNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelButtonNo.add(buttonNo);
		
		JPanel panelButtonYes = new JPanel();
		FlowLayout fl_panelButtonYes = (FlowLayout) panelButtonYes.getLayout();
		fl_panelButtonYes.setVgap(20);
		panelMenu.add(panelButtonYes);
		
		JButton buttonYes = new JButton("Oui");
		buttonYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		panelButtonYes.add(buttonYes);
	}

}
