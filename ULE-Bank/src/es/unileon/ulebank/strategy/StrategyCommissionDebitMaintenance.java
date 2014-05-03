package es.unileon.ulebank.strategy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.payments.Client;

/**
 * @class StrategyCommissionDebitMaintenance
 * @author Rober dCR
 * @date 19/03/2014
 * @brief Class that obtain Commision of Maintenance in Debit Cards
 */
public class StrategyCommissionDebitMaintenance implements StrategyCommissionDebit {

	private Client owner; //Card owner
	private float quantity; //Commission establish by the employee 
	private int maximum_age; //Maximum age to differentiate commissions
	private float default_commission; //Quantity of the default commission
	//Strings for obtain the maximum age & default commission
	private final String AGE = "debit_age";
	private final String COMMISSION = "debit_maintenance";
	
	/**
	 * Class constructor
	 * @param owner
	 * @param quantity
	 * @throws CommissionException 
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public StrategyCommissionDebitMaintenance(Client owner, float quantity) throws CommissionException, NumberFormatException, IOException{
		this.owner = owner;
		this.setDefaultCommission();
		this.setMaximumAge();
		
		if (quantity >= 0)
			this.quantity = quantity;
		else
			throw new CommissionException("Commission can't been negative.");
	}

	@Override
	public float calculateCommission() {
		if (this.owner.getAge() > this.maximum_age)
			return this.default_commission;
		else
			return this.quantity;
	}

	/**
	 * Setter of maximum_age
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	private void setMaximumAge() throws NumberFormatException, IOException{
		FileReader fileReader = null;
		BufferedReader bufferReader = null;

		try {
			// Open file
			fileReader = new FileReader ("etc/commissions/maximum_ages.txt");
			bufferReader = new BufferedReader(fileReader);

			// Read file
			String line;
			while((line = bufferReader.readLine())!=null){
				StringTokenizer stringTokenizer = new StringTokenizer(line);
				String[] aux = new String[2];
				int i = 0;
				
				//Obtains the line's elements separately in the aux array 
				while (stringTokenizer.hasMoreElements()) {
					aux[i] = stringTokenizer.nextToken();
					i++;
				}
				
				//Check if the line is the correct one
				if (aux[0].compareTo(this.AGE) == 0){
					this.maximum_age = Integer.parseInt(aux[1]);
					break;
				}
			}
			
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}finally{
			//Close file
			try{                    
				if( fileReader != null ){   
					fileReader.close();     
				}                  
			}catch (IOException e2){ 
				e2.printStackTrace();
			}
		}
	}

	/**
	 * Setter of default_commission
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	private void setDefaultCommission() throws NumberFormatException, IOException{
		FileReader fileReader = null;
		BufferedReader bufferReader = null;

		try {
			// Open file
			fileReader = new FileReader ("etc/commissions/card_commissions.txt");
			bufferReader = new BufferedReader(fileReader);

			// Read file
			String line;
			while((line = bufferReader.readLine())!=null){
				StringTokenizer stringTokenizer = new StringTokenizer(line);
				String[] aux = new String[2];
				int i = 0;
				
				//Obtains the line's elements separately in the aux array 
				while (stringTokenizer.hasMoreElements()) {
					aux[i] = stringTokenizer.nextToken();
					i++;
				}
				
				//Check if the line is the correct one
				if (aux[0].compareTo(this.COMMISSION) == 0){
					this.default_commission = Float.parseFloat(aux[1]);
					break;
				}
			}
			
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}finally{
			//Close file
			try{                    
				if( fileReader != null ){   
					fileReader.close();     
				}                  
			}catch (IOException e2){ 
				e2.printStackTrace();
			}
		}
	}
	
}
