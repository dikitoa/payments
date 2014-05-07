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


public class StockSalesGUI extends JFrame {

private JPanel contentPane;
private JTextField textField;


public StockSalesGUI() {
	JPanel auxPane = (JPanel) getContentPane();
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 1276, 840);
	contentPane = new JPanel();

	int width = this.getToolkit().getScreenSize().width;
        int height = this.getToolkit().getScreenSize().height;
        setSize( width , height );
        JScrollPane scrollpane = new JScrollPane(contentPane);

        GridBagLayout locatoionContentPane = new GridBagLayout();
        locatoionContentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0};
        locatoionContentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
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
        locationVariableAccount.insets = new Insets(0, 0, 5, 0);
        locationVariableAccount.gridx = 15;
        locationVariableAccount.gridy = 3;
        contentPane.add(variableAccount, locationVariableAccount);

        JButton confirmSaleButton = new JButton("Aceptar");
        
        GridBagConstraints gbc_confirmSaleButton = new GridBagConstraints();
        gbc_confirmSaleButton.insets = new Insets(0, 0, 5, 0);
        gbc_confirmSaleButton.gridx = 15;
        gbc_confirmSaleButton.gridy = 4;
        contentPane.add(confirmSaleButton, gbc_confirmSaleButton);

        JButton declineSaleButton = new JButton("Declinar");
        	declineSaleButton.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			StockManagerGUI otherWindow = new StockManagerGUI();
            		otherWindow.setVisible(true);
            		setVisible(false);
        		}
        		});
        	
        	GridBagConstraints gbc_declineStockSaleButton = new GridBagConstraints();
        	gbc_declineStockSaleButton.insets = new Insets(0, 0, 5, 0);
        	gbc_declineStockSaleButton.gridx = 15;
        	gbc_declineStockSaleButton.gridy = 5;
        	contentPane.add(declineSaleButton, gbc_declineStockSaleButton);

        	variableAccount.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			VariableAccountGUI nextWindow = new VariableAccountGUI();
        			nextWindow.setVisible(true);
        			setVisible(false);	
        		}
        	});



//TO-DO cambiaar
        	for (int i=1; i<5;i++){
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
		JPanel stockPanel = new JPanel();
		stockPanel.setBackground(Color.WHITE);	

		GridBagLayout locationInvestmentPanel = new GridBagLayout();
		locationInvestmentPanel.columnWidths = new int[]{130, 30, 300, 50, 50, 50, 50, 50, 50,50};
		locationInvestmentPanel.rowHeights = new int[]{34, 34, 34, 34};
		stockPanel.setLayout(locationInvestmentPanel);

		JTextPane enterpriseName = new JTextPane();
		enterpriseName.setText("Empresa:");
		GridBagConstraints locationEnterprise = new GridBagConstraints();
		locationEnterprise.anchor = GridBagConstraints.NORTH;
		locationEnterprise.fill = GridBagConstraints.HORIZONTAL;
		locationEnterprise.insets = new Insets(0, 0, 5, 5);
		locationEnterprise.gridx = 0;
		locationEnterprise.gridy = 0;
		stockPanel.add(enterpriseName, locationEnterprise);

		JTextPane enterpriseNameData = new JTextPane();
		enterpriseNameData.setText("");
		GridBagConstraints enterpriseNameDataLocation = new GridBagConstraints();
		enterpriseNameDataLocation.insets = new Insets(0, 0, 5, 5);
		enterpriseNameDataLocation.fill = GridBagConstraints.BOTH;
		enterpriseNameDataLocation.gridx = 2;
		enterpriseNameDataLocation.gridy = 0;
		stockPanel.add(enterpriseNameData, enterpriseNameDataLocation);

		JTextPane stockPrice = new JTextPane();
		stockPrice.setText("Precio:");
		GridBagConstraints stockPriceLocation = new GridBagConstraints();
		stockPriceLocation.insets = new Insets(0, 0, 5, 5);
		stockPriceLocation.anchor = GridBagConstraints.NORTHWEST;
		stockPriceLocation.gridx = 0;
		stockPriceLocation.gridy = 1;
		stockPanel.add(stockPrice, stockPriceLocation);

		JTextPane stockPriceData = new JTextPane();
		stockPriceData.setText("");
		GridBagConstraints stockPriceDataLocation = new GridBagConstraints();
		stockPriceDataLocation.insets = new Insets(0, 0, 5, 5);
		stockPriceDataLocation.fill = GridBagConstraints.BOTH;
		stockPriceDataLocation.gridx = 2;
		stockPriceDataLocation.gridy = 1;
		stockPanel.add(stockPriceData, stockPriceDataLocation);

		JTextPane stockNumber = new JTextPane();
		stockNumber.setText("Cantidad:");
		GridBagConstraints stockNumberLocation = new GridBagConstraints();
		stockNumberLocation.anchor = GridBagConstraints.NORTH;
		stockNumberLocation.fill = GridBagConstraints.HORIZONTAL;
		stockNumberLocation.insets = new Insets(0, 0, 5, 5);
		stockNumberLocation.gridx = 0;
		stockNumberLocation.gridy = 2;
		stockPanel.add(stockNumber, stockNumberLocation);

		final JTextField stockNumberData = new JTextField();
		stockNumberData.setText("");
		GridBagConstraints stockNumberDataLocation = new GridBagConstraints();
		stockNumberDataLocation.insets = new Insets(0, 0, 5, 5);
		stockNumberDataLocation.fill = GridBagConstraints.BOTH;
		stockNumberDataLocation.gridx = 2;
		stockNumberDataLocation.gridy = 2;
		stockPanel.add(stockNumberData, stockNumberDataLocation);	

		JButton buyStockButton = new JButton("Vender acciones");
		buyStockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buyCount = stockNumberData.getText();
			}
		});

		GridBagConstraints locationBuyStock = new GridBagConstraints();
		locationBuyStock.anchor = GridBagConstraints.NORTHWEST;
		locationBuyStock.insets = new Insets(0, 0, 5, 5);
		locationBuyStock.gridx = 11;
		locationBuyStock.gridy = 1;
		stockPanel.add(buyStockButton, locationBuyStock);

		return stockPanel;
	}

}
