package es.unileon.ulebank.GUI.variableAccount;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class VariableAccountContract extends JFrame {

	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public VariableAccountContract() {
		JPanel auxPane = (JPanel) getContentPane();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1276, 840);
		contentPane = new JPanel();
		
		int width = this.getToolkit().getScreenSize().width;
        int height = this.getToolkit().getScreenSize().height;

        setSize( width , height );
		JScrollPane scrollpane = new JScrollPane(contentPane);	
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{30, 30, 30, 30, 30, 30, 30};
		gbl_contentPane.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		auxPane.add(scrollpane);
		
		
		JTextPane contract = new JTextPane();
		contract.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
		contract.setText("CONTRATO:");
		contract.setOpaque(false);
		GridBagConstraints gbc_contract = new GridBagConstraints();
		gbc_contract.insets = new Insets(0, 0, 5, 5);
		gbc_contract.fill = GridBagConstraints.BOTH;
		gbc_contract.gridx = 2;
		gbc_contract.gridy = 1;
		contentPane.add(contract, gbc_contract);


	}

}
