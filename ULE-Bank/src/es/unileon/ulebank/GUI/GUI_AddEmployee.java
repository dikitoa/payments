package es.unileon.ulebank.GUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

import es.unileon.ulebank.Register;

public class GUI_AddEmployee {
	private JFrame frame;
//	private JPanel panel;
	private ImagePanel panel;
	private JTextField textFieldDni;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldSalary;
	private JTextField textFieldIdOffice;
	private JPasswordField passwordField;
	private JPasswordField passwordRepeatedField;
	private JTextField textFieldUserName;
	private JComboBox comboBoxRole;
	private JDialog d;
	private JLabel lblPassWrong;
	private final BufferedImage img = null;
	private java.net.URL url;
	private ImageIcon ic;
	private Register register;
	private GUI_Login windowLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
                        @Override
			public void run() {
				try {
					GUI_AddEmployee window = new GUI_AddEmployee();
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
	public GUI_AddEmployee() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setBounds(100, 100, 600, 475);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		panel = new ImagePanel("/images/WebUle2_2");
//		panel = new JPanel();
		panel.setLayout(null);
		panel.setVisible(true);
		frame.getContentPane().add(panel);

		JLabel lblTitle = new JLabel("A\u00F1adir Empleado");
		lblTitle.setForeground(new Color(168,38,130));
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		lblTitle.setBounds(150, 6, 340, 27);
		panel.add(lblTitle);


		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(50, 39, 29, 16);
		lblDni.setBackground(new Color(0, 0, 0, 255));
		panel.add(lblDni);

		JLabel lblName = new JLabel("Nombre");
		lblName.setBounds(50, 79, 61, 16);
		lblName.setBackground(new Color(0, 0, 0, 255));
		panel.add(lblName);

		JLabel lblLastName = new JLabel("Apellidos");
		lblLastName.setBounds(50, 119, 65, 16);
		panel.add(lblLastName);
		
		JLabel lblIdOffice = new JLabel("oficina");
		lblIdOffice.setBounds(50, 159, 90, 16);
		panel.add(lblIdOffice);
		
		JLabel lblSalary = new JLabel("salario");
		lblSalary.setBounds(50, 199, 90, 16);
		panel.add(lblSalary);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(50, 239, 61, 16);
		panel.add(lblUsuario);

		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setBounds(50, 279, 90, 16);
		panel.add(lblPassword);

		JLabel lblRepeatPassword = new JLabel("Repetir contrase\u00F1a");
		lblRepeatPassword.setBounds(50, 319, 140, 16);
		panel.add(lblRepeatPassword);;

		JLabel lblChooseRole = new JLabel("Elegir rol");
		lblChooseRole.setBounds(50, 364, 140, 16);
		panel.add(lblChooseRole);

		textFieldDni = new JTextField();
		textFieldDni.setBounds(399, 33, 87, 28);
		textFieldDni.setColumns(10);
//		textFieldDni.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyTyped(KeyEvent evt) {
//				// Ayuda para permitir unicamente la introduccion de numeros y
//				// que el maximo sea 8 digitos sin la letra.
//				char car = evt.getKeyChar();
//				if (textFieldDni.getText().length() >= 9)
//					evt.consume();
//			}
//		});
//		
		panel.add(textFieldDni);

		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(314, 73, 257, 28);
		panel.add(textFieldName);

		textFieldSurname = new JTextField();
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(314, 113, 257, 28);
		panel.add(textFieldSurname);
		
		textFieldIdOffice = new JTextField();
		textFieldIdOffice.setBounds(314, 153, 257, 28);
		panel.add(textFieldIdOffice);
		
		textFieldSalary = new JTextField();
		textFieldSalary.setBounds(314, 193, 257, 28);
		panel.add(textFieldSalary);
		
		
		textFieldUserName = new JTextField();
		textFieldUserName.setColumns(10);
		textFieldUserName.setBounds(314, 233, 257, 28);
		panel.add(textFieldUserName);

		passwordField = new JPasswordField();
		passwordField.setBounds(314, 273, 257, 28);
		panel.add(passwordField);

		passwordRepeatedField = new JPasswordField();
		passwordRepeatedField.setBounds(314, 313, 257, 28);
		panel.add(passwordRepeatedField);

		lblPassWrong = new JLabel("Las contrase\u00F1as no coinciden");
		lblPassWrong.setForeground(Color.RED);
		lblPassWrong.setBounds(320, 340, 240, 16);
		lblPassWrong.setVisible(false);
		panel.add(lblPassWrong);

		comboBoxRole = new JComboBox();
		comboBoxRole.addItem("Empleado");
		comboBoxRole.addItem("Administrador");
		comboBoxRole.setBounds(314, 358, 257, 27);
		panel.add(comboBoxRole);


		JButton btnAdd = new JButton("A\u00F1adir");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addEmployee();
			}
		});
		btnAdd.setBounds(60, 418, 120, 29);
		panel.add(btnAdd);

		JButton btnClean = new JButton("Limpiar");
		btnClean.setBounds(240, 418, 120, 29);
		btnClean.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textFieldDni.setText("");
				textFieldName.setText("");
				textFieldSurname.setText("");
				textFieldUserName.setText("");
				passwordField.setText("");
				passwordRepeatedField.setText("");
				comboBoxRole.setSelectedIndex(0);

			}
		});
		panel.add(btnClean);

		JButton btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(420, 418, 120, 29);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		panel.add(btnCancel);
	}
	
	private void addEmployee(){
		register = new Register(textFieldUserName.getText(), new String(passwordField.getPassword()));
		register.guardarDatos();
		JOptionPane.showMessageDialog(this.frame, "añadido correctamente", "añadido", JOptionPane.INFORMATION_MESSAGE);
		windowLogin = new GUI_Login();
		this.frame.dispose();
	}
}
