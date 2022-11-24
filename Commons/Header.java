package Commons;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Header {

	private static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JFrame();
					frame.setBounds(100, 100, 643, 408);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.getContentPane().setLayout(new BorderLayout(0, 0));
					
					Header window = new Header(frame);
					Header.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private JPanel panelHeader;
	
	public Header(JFrame frame) {
		panelHeader = new JPanel();
		frame.getContentPane().add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelHeaderTop = new JPanel();
		panelHeaderTop.setBorder(null);
		panelHeaderTop.setBackground(Color.WHITE);
		panelHeader.add(panelHeaderTop);
		
		JPanel panelLogo = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panelLogo.getLayout();
		flowLayout_2.setHgap(200);
		panelLogo.setBackground(Color.LIGHT_GRAY);
		
		JPanel panelButton = new JPanel();
		panelButton.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panelButton.getLayout();
		flowLayout.setVgap(15);
		flowLayout.setHgap(10);
		
		JButton DisconnectButton = new JButton("D\u00E9connexion");
		DisconnectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				ConnexionWindow.main(null);
			}
		});
		DisconnectButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelButton.add(DisconnectButton);
		GroupLayout gl_panelHeaderTop = new GroupLayout(panelHeaderTop);
		gl_panelHeaderTop.setHorizontalGroup(
			gl_panelHeaderTop.createParallelGroup(Alignment.LEADING)
				.addGap(0, 619, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panelHeaderTop.createSequentialGroup()
					.addGap(26)
					.addComponent(panelLogo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
					.addComponent(panelButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_panelHeaderTop.setVerticalGroup(
			gl_panelHeaderTop.createParallelGroup(Alignment.LEADING)
				.addGap(0, 79, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panelHeaderTop.createSequentialGroup()
					.addContainerGap(12, Short.MAX_VALUE)
					.addGroup(gl_panelHeaderTop.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panelLogo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(14))
		);
		panelHeaderTop.setLayout(gl_panelHeaderTop);
		
		JPanel panelHeaderBottom = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelHeaderBottom.getLayout();
		flowLayout_1.setVgap(25);
		flowLayout_1.setHgap(50);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelHeader.add(panelHeaderBottom);
		
		JPanel panelTitle = new JPanel();
		panelHeaderBottom.add(panelTitle);
		
		JLabel labelTitle = new JLabel("Bienvenue [Nom] [Pr\u00E9nom]");
		labelTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelTitle.add(labelTitle);
	}
	
	public JPanel getPanelHeader() {
		return panelHeader;
	}

}
