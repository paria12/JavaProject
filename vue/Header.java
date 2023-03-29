package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import controleur.ControleurArbitre;
import controleur.ControleurEcurie;
import controleur.ControleurGerant;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class Header {

	private static JFrame frame;
	public static String header;

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
		
		JPanelDarkest panelHeaderTop = new JPanelDarkest();
		panelHeaderTop.setBorder(null);
		panelHeader.add(panelHeaderTop);
		
		JPanel panelLogo = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panelLogo.getLayout();
		flowLayout_2.setVgap(0);
		flowLayout_2.setHgap(0);
		panelLogo.setBackground(Color.yellow);
		
		JPanelDarkest panelButton = new JPanelDarkest();
		FlowLayout flowLayout = (FlowLayout) panelButton.getLayout();
		flowLayout.setVgap(15);
		flowLayout.setHgap(10);
		
		JButtonYellow DisconnectButton = new JButtonYellow("Déconnexion");
		DisconnectButton.addActionListener(ControleurArbitre.getInstance());
		//DisconnectButton.addActionListener(ControleurGerant.getInstance());
		DisconnectButton.addActionListener(ControleurEcurie.getInstance());
		
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
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(Header.class.getResource("/IMG/Logholder.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image logo = img.getScaledInstance(300, 65, Image.SCALE_SMOOTH);
		JLabel labelLogo = new JLabel("");
		labelLogo.setIcon(new ImageIcon(logo));
		panelLogo.add(labelLogo);
		panelHeaderTop.setLayout(gl_panelHeaderTop);
		
		JPanelBackground panelHeaderBottom = new JPanelBackground();
		FlowLayout flowLayout_1 = (FlowLayout) panelHeaderBottom.getLayout();
		flowLayout_1.setVgap(25);
		flowLayout_1.setHgap(50);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelHeader.add(panelHeaderBottom);
		
		JPanelBackground panelTitle = new JPanelBackground();
		panelHeaderBottom.add(panelTitle);
		
		JLabel labelTitle = new JLabel("Bienvenue "+header);
		labelTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		labelTitle.setForeground(Colors.lightText);
		panelTitle.add(labelTitle);
	}
	
	public JPanel getPanelHeader() {
		return panelHeader;
	}
}