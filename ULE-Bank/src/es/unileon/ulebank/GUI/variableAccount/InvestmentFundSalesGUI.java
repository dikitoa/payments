
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


public class InvestmentFundSalesGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	public InvestmentFundSalesGUI() {
		JPanel auxiliar = (JPanel) getContentPane();
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
        auxiliar.add(scrollpane);

        JTextPane funds = new JTextPane();
        GridBagConstraints locationFunds = new GridBagConstraints();
        locationFunds.insets = new Insets(0, 0, 5, 5);
        locationFunds.gridx = 1;
        locationFunds.gridy = 0;
        contentPane.add(funds, locationFunds);
        funds.setText("FONDOS DISPONIBLES");
        funds.setFont(new Font("Lucida Grande", Font.BOLD, 37));

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
        		InvestmentFundClientGUI otherWindow = new InvestmentFundClientGUI();
        		otherWindow.setVisible(true);
        		setVisible(false);
        	}
        });
        
        GridBagConstraints gbc_declineSaleButton = new GridBagConstraints();
        gbc_declineSaleButton.insets = new Insets(0, 0, 5, 0);
        gbc_declineSaleButton.gridx = 15;
        gbc_declineSaleButton.gridy = 5;
        contentPane.add(declineSaleButton, gbc_declineSaleButton);

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
        	JPanel fundsPanel= newPanel();
        	fundsPanel.setSize(100,50);
        	contentPane.add(fundsPanel, locationPanel);
        }

	}

	public JPanel newPanel(){
		JPanel investmentPanel = new JPanel();
		investmentPanel.setBackground(Color.WHITE);	

		GridBagLayout locationInvestmentPanel = new GridBagLayout();
		locationInvestmentPanel.columnWidths = new int[]{130, 30, 300, 50, 50, 50, 50, 50, 50,50};
		locationInvestmentPanel.rowHeights = new int[]{34, 34, 34, 34};
		investmentPanel.setLayout(locationInvestmentPanel);

		JTextPane fundName = new JTextPane();
		fundName.setText("Nombre fondo:");
		GridBagConstraints locationEnterprise = new GridBagConstraints();
		locationEnterprise.anchor = GridBagConstraints.NORTH;
		locationEnterprise.fill = GridBagConstraints.HORIZONTAL;
		locationEnterprise.insets = new Insets(0, 0, 5, 5);
		locationEnterprise.gridx = 0;
		locationEnterprise.gridy = 0;
		investmentPanel.add(fundName, locationEnterprise);

		JTextPane fundNameData = new JTextPane();
		fundNameData.setText("");
		GridBagConstraints locationDataEnterprise = new GridBagConstraints();
		locationDataEnterprise.insets = new Insets(0, 0, 5, 5);
		locationDataEnterprise.fill = GridBagConstraints.BOTH;
		locationDataEnterprise.gridx = 2;
		locationDataEnterprise.gridy = 0;
		investmentPanel.add(fundNameData, locationDataEnterprise);

		JTextPane fundPrice = new JTextPane();
		fundPrice.setText("Precio");
		GridBagConstraints locationPrice = new GridBagConstraints();
		locationPrice.insets = new Insets(0, 0, 5, 5);
		locationPrice.anchor = GridBagConstraints.NORTHWEST;
		locationPrice.gridx = 0;
		locationPrice.gridy = 1;
		investmentPanel.add(fundPrice, locationPrice);

		JTextPane fundPriceData = new JTextPane();
		fundPriceData.setText("");
		GridBagConstraints locationDataPrice = new GridBagConstraints();
		locationDataPrice.insets = new Insets(0, 0, 5, 5);
		locationDataPrice.fill = GridBagConstraints.BOTH;
		locationDataPrice.gridx = 2;
		locationDataPrice.gridy = 1;
		investmentPanel.add(fundPriceData, locationDataPrice);

		JTextPane fundNumber = new JTextPane();
		fundNumber.setText("Cantidad:");
		GridBagConstraints locationCount = new GridBagConstraints();
		locationCount.anchor = GridBagConstraints.NORTH;
		locationCount.fill = GridBagConstraints.HORIZONTAL;
		locationCount.insets = new Insets(0, 0, 5, 5);
		locationCount.gridx = 0;
		locationCount.gridy = 2;
		investmentPanel.add(fundNumber, locationCount);

		final JTextField fundNumberData = new JTextField();
		fundNumberData.setText("");
		GridBagConstraints locationDataCount = new GridBagConstraints();
		locationDataCount.insets = new Insets(0, 0, 5, 5);
		locationDataCount.fill = GridBagConstraints.BOTH;
		locationDataCount.gridx = 2;
		locationDataCount.gridy = 2;
		investmentPanel.add(fundNumberData, locationDataCount);	
		
		JButton sellFundButton = new JButton("Vender fondo");
		sellFundButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buyCount = fundNumberData.getText();

			}
		});
		
		JTextPane fundRentability = new JTextPane();
		fundRentability.setText("Rentabilidad:");
		GridBagConstraints locationRentability = new GridBagConstraints();
		locationCount.anchor = GridBagConstraints.NORTH;
		locationCount.fill = GridBagConstraints.HORIZONTAL;
		locationCount.insets = new Insets(0, 0, 5, 5);
		locationCount.gridx = 0;
		locationCount.gridy = 3;
		investmentPanel.add(fundRentability, locationCount);
		
		JTextPane fundRentabilityData = new JTextPane();
		fundPriceData.setText("");
		GridBagConstraints locationFundRentabilityData = new GridBagConstraints();
		locationFundRentabilityData.insets = new Insets(0, 0, 5, 5);
		locationFundRentabilityData.fill = GridBagConstraints.BOTH;
		locationFundRentabilityData.gridx = 2;
		locationFundRentabilityData.gridy = 3;
		investmentPanel.add(fundPriceData, locationDataPrice);	

		

		GridBagConstraints locationBuyStock = new GridBagConstraints();
		locationBuyStock.anchor = GridBagConstraints.NORTHWEST;
		locationBuyStock.insets = new Insets(0, 0, 5, 5);
		locationBuyStock.gridx = 11;
		locationBuyStock.gridy = 1;
		investmentPanel.add(sellFundButton, locationBuyStock);

		return investmentPanel;
	}

}
