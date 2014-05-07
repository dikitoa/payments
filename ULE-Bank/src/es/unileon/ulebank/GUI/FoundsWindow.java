package es.unileon.ulebank.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class FoundsWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public FoundsWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1276, 840);
		contentPane = new JPanel();

		setContentPane(contentPane);
		
		//Comprobar si el cliente no tiene fondo de inversion
		final JTextPane withoutFounds = new JTextPane();
		withoutFounds.setFont(new Font("Lucida Grande", Font.PLAIN, 67));
		withoutFounds.setText("El cliente no cuenta con ningun fondo de inversion.");
		withoutFounds.setOpaque(false);
		
		
		final JButton getAFound = new JButton("Contratar un fondo de inversion");
		getAFound.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
		getAFound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withoutFounds.setVisible(false);
				getAFound.setVisible(false);

			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(292)
					.addComponent(withoutFounds, GroupLayout.PREFERRED_SIZE, 954, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(194, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(542, Short.MAX_VALUE)
					.addComponent(getAFound)
					.addGap(522))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(169)
					.addComponent(withoutFounds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(173)
					.addComponent(getAFound)
					.addContainerGap(342, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		int width = this.getToolkit().getScreenSize().width;
        int height = this.getToolkit().getScreenSize().height;

        setSize( width , height );
		
	}
	
	

}
