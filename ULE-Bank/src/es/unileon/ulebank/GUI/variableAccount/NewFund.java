package es.unileon.ulebank.GUI.variableAccount;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import es.unileon.ulebank.brokerage.buyable.InvestmentFund;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.FundsHandler;

public class NewFund extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewFund frame = new NewFund();
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
	public NewFund() {
		JPanel auxPane = (JPanel) getContentPane();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1276, 840);
		contentPane = new JPanel();
		
		int width = this.getToolkit().getScreenSize().width;
        int height = this.getToolkit().getScreenSize().height;

        setSize( width , height );
		JScrollPane scrollpane = new JScrollPane(contentPane);	
		auxPane.add(scrollpane);

		contentPane.setBackground(Color.WHITE);	
		
		GridBagLayout locationContentPane = new GridBagLayout();
		locationContentPane.columnWidths = new int[]{34, 166, 402, 50, 50, 50, 50, 50, 50,50};
		locationContentPane.rowHeights = new int[] {0, 44, 44, 44, 44, 44, 44, 44, 44, 44};
		contentPane.setLayout(locationContentPane);
		
		JTextPane contract = new JTextPane();
		contract.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
		contract.setText("Nuevo Fondo");
		GridBagConstraints gbc_contract = new GridBagConstraints();
		gbc_contract.insets = new Insets(0, 0, 5, 5);
		gbc_contract.fill = GridBagConstraints.BOTH;
		gbc_contract.gridx = 1;
		gbc_contract.gridy = 0;
		contentPane.add(contract, gbc_contract);
		
		JTextPane investmentFound = new JTextPane();
		investmentFound.setText("Datos de la empresa:");
		GridBagConstraints locationInvestmentFound = new GridBagConstraints();
		locationInvestmentFound.fill = GridBagConstraints.HORIZONTAL;
		locationInvestmentFound.insets = new Insets(0, 0, 5, 5);
		locationInvestmentFound.gridx = 1;
		locationInvestmentFound.gridy = 2;
		contentPane.add(investmentFound, locationInvestmentFound);
		
		final JTextField dataID = new JTextField();
		dataID.setText("");
		GridBagConstraints locationDataID = new GridBagConstraints();
		locationDataID.insets = new Insets(0, 0, 5, 5);
		locationDataID.fill = GridBagConstraints.BOTH;
		locationDataID.gridx = 2;
		locationDataID.gridy = 2;
		contentPane.add(dataID, locationDataID);
		
		JTextPane participations = new JTextPane();
		participations.setText("Participaciones:");
		GridBagConstraints locationParticipations = new GridBagConstraints();
		locationParticipations.fill = GridBagConstraints.HORIZONTAL;
		locationParticipations.insets = new Insets(0, 0, 5, 5);
		locationParticipations.gridx = 1;
		locationParticipations.gridy = 4;
		contentPane.add(participations, locationParticipations);
		
		final JTextField dataParticipations = new JTextField();
		dataParticipations.setText("");
		GridBagConstraints locationDataParticipations = new GridBagConstraints();
		locationDataParticipations.insets = new Insets(0, 0, 5, 5);
		locationDataParticipations.fill = GridBagConstraints.BOTH;
		locationDataParticipations.gridx = 2;
		locationDataParticipations.gridy = 4;
		contentPane.add(dataParticipations, locationDataParticipations);	
		
		JButton createFound = new JButton("Crear fondo");
		GridBagConstraints locationCreateFound = new GridBagConstraints();
		locationCreateFound.anchor = GridBagConstraints.NORTHWEST;
		locationCreateFound.insets = new Insets(0, 0, 5, 5);
		locationCreateFound.gridx = 7;
		locationCreateFound.gridy = 5;
		contentPane.add(createFound, locationCreateFound);
		
		JTextPane price = new JTextPane();
		price.setText("Precio");
		GridBagConstraints locationPrice = new GridBagConstraints();
		locationPrice.insets = new Insets(0, 0, 5, 5);
		locationPrice.anchor = GridBagConstraints.WEST;
		locationPrice.gridx = 1;
		locationPrice.gridy = 6;
		contentPane.add(price, locationPrice);
		
		final JTextField dataPrice = new JTextField();
		dataPrice.setText("");
		GridBagConstraints locationDataPrice = new GridBagConstraints();
		locationDataPrice.insets = new Insets(0, 0, 5, 5);
		locationDataPrice.fill = GridBagConstraints.BOTH;
		locationDataPrice.gridx = 2;
		locationDataPrice.gridy = 6;
		contentPane.add(dataPrice, locationDataPrice);
		
		JTextPane fee = new JTextPane();
		fee.setText("Tasa por anulacion: ");
		GridBagConstraints locationFee = new GridBagConstraints();
		locationFee.insets = new Insets(0, 0, 5, 5);
		locationFee.fill = GridBagConstraints.HORIZONTAL;
		locationFee.gridx = 1;
		locationFee.gridy = 8;
		contentPane.add(fee, locationFee);
		
		final JTextField dataFee = new JTextField();
		dataFee.setText(" ");
		GridBagConstraints locationDataFee = new GridBagConstraints();
		locationDataFee.insets = new Insets(0, 0, 5, 5);
		locationDataFee.fill = GridBagConstraints.BOTH;
		locationDataFee.gridx = 2;
		locationDataFee.gridy = 8;
		contentPane.add(dataFee, locationDataFee);
		
		createFound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*Handler handler = new HandlerFunds();
				InvestmentFund newFund = new InvestmentFund(null, height, securityWarningPointX, null, null, securityWarningPointX);
				dataID.getText();
				dataParticipations.getText();
				dataFee.getText();
				dataPrice.getText();*/
			}
		});
	}

}
