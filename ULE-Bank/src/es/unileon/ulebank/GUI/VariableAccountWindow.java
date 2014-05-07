package es.unileon.ulebank.GUI;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VariableAccountWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private static VariableAccountWindow window;
	String[][] columnData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		window = new VariableAccountWindow();

		window.setVisible(true);

		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.setLocation(0, 0);

	}

	/**
	 * Create the frame.
	 */
	public VariableAccountWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1276, 840);
		contentPane = new JPanel();

		setContentPane(contentPane);

		int width = this.getToolkit().getScreenSize().width;
		int height = this.getToolkit().getScreenSize().height;

		setSize(width, height);

		JTextPane name = new JTextPane();
		name.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		name.setText("Nombre");
		name.setOpaque(false);

		JTextPane surname = new JTextPane();
		surname.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		surname.setText("Apellidos");
		surname.setOpaque(false);

		JTextPane id = new JTextPane();
		id.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		id.setText("NIF");
		id.setOpaque(false);

		JTextPane address = new JTextPane();
		address.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		address.setText("Direccion");
		address.setOpaque(false);

		JTextPane accountNumber = new JTextPane();
		accountNumber.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		accountNumber.setText("Numero de cuenta");
		accountNumber.setOpaque(false);

		JTextPane nameInfo = new JTextPane();
		nameInfo.setOpaque(false);
		nameInfo.setText("Ejemplo");

		JTextPane accountNumberInfo = new JTextPane();
		accountNumberInfo.setOpaque(false);
		accountNumberInfo.setText("Ejemplo");

		JTextPane surnameInfo = new JTextPane();
		surnameInfo.setOpaque(false);
		surnameInfo.setText("Ejemplo");

		JTextPane idInfo = new JTextPane();
		idInfo.setOpaque(false);
		idInfo.setText("Ejemplo");

		JTextPane addressInfo = new JTextPane();
		addressInfo.setOpaque(false);
		addressInfo.setText("Ejemplo");

		JButton btnAcceder = new JButton("Acceder al historial de acciones");
		btnAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				InvestmentsWindow investments = new InvestmentsWindow();
				investments.setVisible(true);

			}
		});

		JButton btnComprarAcciones = new JButton("Comprar acciones");

		JButton btnVenderAccionesActuales = new JButton("Vender acciones");
		btnVenderAccionesActuales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FoundsWindow abrir = new FoundsWindow();
				abrir.setVisible(true);

			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(202)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																name,
																GroupLayout.PREFERRED_SIZE,
																84,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																accountNumber,
																GroupLayout.PREFERRED_SIZE,
																172,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																surname,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																id,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																address,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(113)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								nameInfo,
																								GroupLayout.DEFAULT_SIZE,
																								214,
																								Short.MAX_VALUE)
																						.addComponent(
																								accountNumberInfo,
																								GroupLayout.PREFERRED_SIZE,
																								197,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								surnameInfo,
																								GroupLayout.PREFERRED_SIZE,
																								211,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								idInfo,
																								GroupLayout.PREFERRED_SIZE,
																								211,
																								GroupLayout.PREFERRED_SIZE))
																		.addGap(739))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				addressInfo,
																				GroupLayout.PREFERRED_SIZE,
																				211,
																				GroupLayout.PREFERRED_SIZE)
																		.addContainerGap())))
						.addGroup(
								gl_contentPane.createSequentialGroup()
										.addContainerGap(836, Short.MAX_VALUE)
										.addComponent(btnAcceder).addGap(361))
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap(875, Short.MAX_VALUE)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																btnVenderAccionesActuales)
														.addComponent(
																btnComprarAcciones))
										.addGap(408)));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(122)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																accountNumber,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																accountNumberInfo,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(25)
										.addComponent(btnAcceder)
										.addPreferredGap(
												ComponentPlacement.RELATED, 7,
												Short.MAX_VALUE)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																Alignment.TRAILING,
																gl_contentPane
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								name,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								nameInfo,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))
																		.addGap(48)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								surname,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addGap(13)
																										.addComponent(
																												surnameInfo,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE)))
																		.addGap(48)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								id,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								idInfo,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))
																		.addGap(51)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								address,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								addressInfo,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))
																		.addGap(22))
														.addGroup(
																Alignment.TRAILING,
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				btnComprarAcciones)
																		.addGap(113)))
										.addGap(1)
										.addComponent(btnVenderAccionesActuales)
										.addGap(389)));
		contentPane.setLayout(gl_contentPane);

	}
}