package es.unileon.ulebank.GUI;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class GUI_MainWindow {
	
	private static final String[] nameButos = {"activo", "pasivo", "mediosPago", "intermediacion"};
	
	private JButton[] buttons;
	private JFrame frame;
	private ImagePanel panel;
	private java.net.URL url;
	private BufferedImage image;
	private ImageIcon imageIcon;
	private InteractionMenu click = new InteractionMenu();
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_MainWindow window = new GUI_MainWindow();
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
	public GUI_MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(200, 50, 955, 675);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new ImagePanel("/images/WebUle2_2");
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(null);
		frame.add(panel);
		panel.setVisible(true);
		
		this.createMainButtons();
	}
	
	public JFrame getFrame(){
		return this.frame;
	}
	
	private void createMainButtons(){
		this.buttons = new JButton[this.nameButos.length];
		
		
		for (int i = 0; i < this.buttons.length; i++) {
			final int aux = i;
			
			this.url = getClass().getResource("/images/" + this.nameButos[i] + ".png");
			
			try {
				this.image = ImageIO.read(this.url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.imageIcon = new ImageIcon(this.image);
			buttons[i] = new JButton(imageIcon);
//			buttons[0].setBounds(200, 200, 100, 100);
			buttons[i].setBounds(400+(i*75), 40, imageIcon.getIconWidth(), imageIcon.getIconHeight());
			buttons[i].setBackground(Color.WHITE);
			buttons[i].setVisible(true);
			buttons[i].setBorder(null);
			buttons[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					click.execute(aux);
				}
			});
			panel.add(buttons[i]);
		}
	}
}
