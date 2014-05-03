package es.unileon.ulebank.strategy;

import static org.junit.Assert.*; 

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.payments.Client;


/**
 * 
 * @author Marta
 *
 */
public class StrategyCommissionDebitMaintenanceTest {

	private Client owner;
	private float quantity;
	private float default_commission = 15;
	StrategyCommissionDebitMaintenance commission;
	
	/**
	 * Inicializamos las variables
	 * @throws IOException 
	 * @throws CommissionException 
	 * @throws NumberFormatException 
	 */
	
	@Before
	public void SetUp() throws NumberFormatException, CommissionException, IOException{
	
		quantity = 15;
		owner.setAge(20);
		commission = new StrategyCommissionDebitMaintenance(owner, quantity);
	}
	

	/**
	 * Comprobamos que si tiene menos de 25 anos, la comision es quantity
	 */
	@Test
	public void testCalculateCommissionOwnerLessThanMaximunAge() {
		
		assertTrue(commission.calculateCommission()==quantity);
	}
	

	/**
	 * Comprobamos que si tiene menos de 25 anos, la comision es quantity
	 */
	@Test
	public void testCalculateCommissionOwnerLessThanMaximunAgeFalse() {
		
		assertFalse(commission.calculateCommission()!=quantity);
	}
	
	
	/**
	 * Comprobamos que si tiene más de 25 anos, la comision es la estipulada por defecto
	 * @throws IOException 
	 * @throws CommissionException 
	 * @throws NumberFormatException 
	 */
	@Test
	public void testCalculateCommissionOwnerMoreThanMaximunAge() throws NumberFormatException, CommissionException, IOException {
		owner.setAge(30);
		commission = new StrategyCommissionDebitMaintenance(owner, quantity);
		assertTrue(commission.calculateCommission()==default_commission);
	}
		
	
	/**
	 * Comprobamos que si tiene más de 25 anos, la comision es la estipulada por defecto
	 * @throws IOException 
	 * @throws CommissionException 
	 * @throws NumberFormatException 
	 */
	@Test
	public void testCalculateCommissionOwnerMoreThanMaximunAgeFalse() throws NumberFormatException, CommissionException, IOException {
		owner.setAge(30);
		commission = new StrategyCommissionDebitMaintenance(owner, quantity);
		assertFalse(commission.calculateCommission()!=default_commission);
	}
	
	
	/**
	 * Comprueba que se lanza la excepcion de la comision correctamente por el metodo try/catch
	 */
	@Test
	public void testCalculateNegativeCommission() {
		
		quantity = -10;
		try {
			commission = new StrategyCommissionDebitMaintenance(owner, quantity);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommissionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
