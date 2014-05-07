package es.unileon.ulebank.GUI.variableAccount;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.Insets;

import javax.swing.JScrollBar;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class StockGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	public StockGUI() {
		JPanel auxPane = (JPanel) getContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1276, 840);
		contentPane = new JPanel();
				
		int width = this.getToolkit().getScreenSize().width;
        int height = this.getToolkit().getScreenSize().height;
        setSize( width , height );
		JScrollPane scrollpane = new JScrollPane(contentPane);

		GridBagLayout locatoionContentPane = new GridBagLayout();
		locatoionContentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		locatoionContentPane.rowHeights = new int[]{0, 0, 0, 0};
		GridBagConstraints location = new GridBagConstraints();

		location.gridx = 0;
		location.gridy = 0;
		location.fill = GridBagConstraints.BOTH ;
		location.weightx = 1.0 ;
		location.weighty = 1.0 ;
		locatoionContentPane.setConstraints(scrollpane,location) ;
		
		contentPane.setLayout(locatoionContentPane);
		auxPane.add(scrollpane);
		
		JTextPane stocks = new JTextPane();
		GridBagConstraints locationStocks = new GridBagConstraints();
		locationStocks.insets = new Insets(0, 0, 5, 5);
		locationStocks.gridx = 1;
		locationStocks.gridy = 0;
		contentPane.add(stocks, locationStocks);
		stocks.setText("ACCIONES DISPONIBLES");
		stocks.setFont(new Font("Lucida Grande", Font.BOLD, 37));
		
		JButton variableAccount = new JButton("Cuenta Variable");
		GridBagConstraints locationVariableAccount = new GridBagConstraints();
		locationVariableAccount.gridx = 15;
		locationVariableAccount.gridy = 3;
		contentPane.add(variableAccount, locationVariableAccount);
		
		variableAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VariableAccountGUI nextWindow = new VariableAccountGUI();
				nextWindow.setVisible(true);
				setVisible(false);			
			}
		});
		
		

	//TO-DO cambiaar
		for (int i=1; i<10;i++){
			GridBagConstraints locationPanel = new GridBagConstraints();
			locationPanel.insets = new Insets(0, 0, 10, 0);
			locationPanel.fill = GridBagConstraints.HORIZONTAL;
			locationPanel.gridx = 1;
			locationPanel.gridy = i;
			JPanel panelaux= newPanel();
			panelaux.setSize(100,50);
			contentPane.add(panelaux, locationPanel);
		}
		
	}
	
	public JPanel newPanel(){
		JPanel investmentPanel = new JPanel();
		investmentPanel.setBackground(Color.WHITE);	
			
		GridBagLayout locationInvestmentPanel = new GridBagLayout();
		locationInvestmentPanel.columnWidths = new int[]{130, 30, 300, 50, 50, 50, 50, 50, 50,50};
		locationInvestmentPanel.rowHeights = new int[]{34, 34, 34, 34};
		investmentPanel.setLayout(locationInvestmentPanel);
		
		JTextPane enterprise = new JTextPane();
		enterprise.setText("Empresa:");
		GridBagConstraints locationEnterprise = new GridBagConstraints();
		locationEnterprise.anchor = GridBagConstraints.NORTH;
		locationEnterprise.fill = GridBagConstraints.HORIZONTAL;
		locationEnterprise.insets = new Insets(0, 0, 5, 5);
		locationEnterprise.gridx = 0;
		locationEnterprise.gridy = 0;
		investmentPanel.add(enterprise, locationEnterprise);
		
		JTextPane dataEnterprise = new JTextPane();
		dataEnterprise.setText("");
		GridBagConstraints locationDataEnterprise = new GridBagConstraints();
		locationDataEnterprise.insets = new Insets(0, 0, 5, 5);
		locationDataEnterprise.fill = GridBagConstraints.BOTH;
		locationDataEnterprise.gridx = 2;
		locationDataEnterprise.gridy = 0;
		investmentPanel.add(dataEnterprise, locationDataEnterprise);
		
		JTextPane price = new JTextPane();
		price.setText("Precio");
		GridBagConstraints locationPrice = new GridBagConstraints();
		locationPrice.insets = new Insets(0, 0, 5, 5);
		locationPrice.anchor = GridBagConstraints.NORTHWEST;
		locationPrice.gridx = 0;
		locationPrice.gridy = 1;
		investmentPanel.add(price, locationPrice);
		
		JTextPane dataPrice = new JTextPane();
		dataPrice.setText("");
		GridBagConstraints locationDataPrice = new GridBagConstraints();
		locationDataPrice.insets = new Insets(0, 0, 5, 5);
		locationDataPrice.fill = GridBagConstraints.BOTH;
		locationDataPrice.gridx = 2;
		locationDataPrice.gridy = 1;
		investmentPanel.add(dataPrice, locationDataPrice);
		
		JTextPane count = new JTextPane();
		count.setText("Cantidad:");
		GridBagConstraints locationCount = new GridBagConstraints();
		locationCount.anchor = GridBagConstraints.NORTH;
		locationCount.fill = GridBagConstraints.HORIZONTAL;
		locationCount.insets = new Insets(0, 0, 5, 5);
		locationCount.gridx = 0;
		locationCount.gridy = 2;
		investmentPanel.add(count, locationCount);
		
		final JTextField dataCount = new JTextField();
		dataCount.setText("");
		GridBagConstraints locationDataCount = new GridBagConstraints();
		locationDataCount.insets = new Insets(0, 0, 5, 5);
		locationDataCount.fill = GridBagConstraints.BOTH;
		locationDataCount.gridx = 2;
		locationDataCount.gridy = 2;
		investmentPanel.add(dataCount, locationDataCount);	
		
		JButton buyStock = new JButton("Comprar acciones");
		buyStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buyCount = dataCount.getText();
				
				
			}
		});
		
		GridBagConstraints locationBuyStock = new GridBagConstraints();
		locationBuyStock.anchor = GridBagConstraints.NORTHWEST;
		locationBuyStock.insets = new Insets(0, 0, 5, 5);
		locationBuyStock.gridx = 11;
		locationBuyStock.gridy = 1;
		investmentPanel.add(buyStock, locationBuyStock);
		
		return investmentPanel;
	}
	

}
