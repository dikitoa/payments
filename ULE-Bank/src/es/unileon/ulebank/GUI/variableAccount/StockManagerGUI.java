package es.unileon.ulebank.GUI.variableAccount;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Font;

public class StockManagerGUI extends JFrame {

	private JPanel contentPane;
		
	public StockManagerGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1276, 840);
		contentPane = new JPanel();
		setContentPane(contentPane);


		int width = this.getToolkit().getScreenSize().width;
        int height = this.getToolkit().getScreenSize().height;
        setSize( width , height );
        
        JPanel panel = new JPanel();
        JTable tableMovements = new JTable(new DefaultTableModel(
        		new Object[][] {
        				{"Ejemplo1", "Ejemplo1", "Ejemplo1", "Ejemplo1"},
        				{"Ejemplo2", "Ejemplo2", "Ejemplo2", "Ejemplo2"},
        				{"Ejemplo3", "Ejemplo3", "Ejemplo3", "Ejemplo3"},
        				{"Ejemplo4", "Ejemplo4", "Ejemplo4", "Ejemplo4"},
        				{"Ejemplo5", "Ejemplo5", "Ejemplo5", "Ejemplo5"},
        				{"Ejemplo6", "Ejemplo6", "Ejemplo6", "Ejemplo6"},
        				{"Ejemplo7", "Ejemplo7", "Ejemplo7", "Ejemplo7"},
        				{"Ejemplo8", "Ejemplo8", "Ejemplo8", "Ejemplo8"},
        		},
        		new String[] {
        				"Empresa", "ID", "Cantidad", "Precio de Compra","Precio Actual","Empleado","Estado"
        		}
        		));
        
        tableMovements.setSurrendersFocusOnKeystroke(true);
        tableMovements.setColumnSelectionAllowed(true);
        tableMovements.setCellSelectionEnabled(true);
        tableMovements.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableMovements.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableMovements.setPreferredScrollableViewportSize(new Dimension(1000, 300));
        JScrollPane scrollPane = new JScrollPane(tableMovements);

        panel.add(scrollPane, BorderLayout.CENTER);

        JButton buyStockButton = new JButton("Comprar Acciones");
        buyStockButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		StockGUI nextWindow = new StockGUI();
        		nextWindow.setVisible(true);
        		setVisible(false);
        	}
        });

        JButton sellStockButton = new JButton("Vender Acciones");
        sellStockButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		StockSalesGUI window = new StockSalesGUI();
        		window.setVisible(true);
        		setVisible(false);
        	}
        });
	
        JButton variableAccount = new JButton("Cuenta Variable");
        variableAccount.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		VariableAccountGUI nextWindow = new VariableAccountGUI();
        		nextWindow.setVisible(true);
        		setVisible(false);
        	}
        });
        
        JTextPane txtpnAccionesDelCliente = new JTextPane();
        txtpnAccionesDelCliente.setText("Acciones del Cliente");
        txtpnAccionesDelCliente.setFont(new Font("DialogInput", Font.BOLD, 24));
        
        

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap(132, Short.MAX_VALUE)
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(206))
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(371)
        			.addComponent(buyStockButton)
        			.addGap(250)
        			.addComponent(sellStockButton)
        			.addContainerGap(499, Short.MAX_VALUE))
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap(1201, Short.MAX_VALUE)
        			.addComponent(variableAccount)
        			.addGap(40))
        		.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(txtpnAccionesDelCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(1054, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(txtpnAccionesDelCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(56)
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
        			.addGap(124)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(buyStockButton)
        				.addComponent(sellStockButton))
        			.addGap(47)
        			.addComponent(variableAccount)
        			.addContainerGap(12, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
	}
}
