package es.unileon.ulebank.GUI.variableAccount;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JTextPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class VariableAccountRequestGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VariableAccountRequestGUI frame = new VariableAccountRequestGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public VariableAccountRequestGUI() {
		JPanel auxPane = (JPanel) getContentPane();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1276, 840);
		contentPane = new JPanel();
		
		int width = this.getToolkit().getScreenSize().width;
        int height = this.getToolkit().getScreenSize().height;

        setSize( width , height );
		JScrollPane scrollpane = new JScrollPane(contentPane);	
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 150, 0};
		gbl_contentPane.rowHeights = new int[] {30, 10, 30, 30, 150, 0, 30};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		auxPane.add(scrollpane);
		
		JTextPane introduction = new JTextPane();
		introduction.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		GridBagConstraints gbc_introduction = new GridBagConstraints();
		gbc_introduction.insets = new Insets(0, 0, 5, 5);
		gbc_introduction.fill = GridBagConstraints.BOTH;
		gbc_introduction.gridx = 5;
		gbc_introduction.gridy = 4;
		contentPane.add(introduction, gbc_introduction);
		introduction.setText("El cliente no dispone de una cuenta de inversi—n y esta es necesaria"
				+ "\npara poder comprar acciones o contratar un fondo de inversion"
				+ "\n\nPara abrir la cuenta el cliente ha de ser evaluado por "
				+ "el empleado \ny ha de responder a las siguientes preguntas:");
		introduction.setOpaque(false);
		
		JTextPane test = new JTextPane();
		GridBagConstraints gbc_test = new GridBagConstraints();
		gbc_test.anchor = GridBagConstraints.WEST;
		gbc_test.insets = new Insets(0, 0, 5, 5);
		gbc_test.fill = GridBagConstraints.VERTICAL;
		gbc_test.gridx = 5;
		gbc_test.gridy = 5;
		contentPane.add(test, gbc_test);
		
		JButton evaluate = new JButton("Evaluar");
		GridBagConstraints gbc_evaluate = new GridBagConstraints();
		gbc_evaluate.insets = new Insets(0, 0, 0, 5);
		gbc_evaluate.gridx = 5;
		gbc_evaluate.gridy = 6;
		contentPane.add(evaluate, gbc_evaluate);
		
		
		evaluate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TestEvaluation testE = new TestEvaluation();
				//String result = testE.testResult();
				String result = "suitable";
				if(result.compareTo("suitable") == 0){
					int aux = JOptionPane.showConfirmDialog(null,
							"Confirme para la creacion de la cuenta", "El cliente es apto", JOptionPane.YES_NO_OPTION);
					
					if(aux == JOptionPane.YES_OPTION){
				            JOptionPane.showMessageDialog(null, "La cuenta ha sido creada, rellene el contrato.");
				            //TO-DO Create variable account
				            VariableAccountContract nextWindow = new VariableAccountContract();
				            nextWindow.setVisible(true);
				            setVisible(false);
					}
				}else{
					JOptionPane.showMessageDialog(null, "Si este no esta de acuerdo, por favor consultelo con su superior", "El cliente no es apto", JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});


	}

}
