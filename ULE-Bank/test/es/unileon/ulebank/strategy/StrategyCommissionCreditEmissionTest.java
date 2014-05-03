package es.unileon.ulebank.strategy;

import static org.junit.Assert.*; 

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.exceptions.CommissionException;

/**
 * 
 * @author Marta
 *
 */

public class StrategyCommissionCreditEmissionTest {

	StrategyCommissionCreditEmission emission;
	float quantity;

	/**
	 * Comprobamos que la comision es siempre cero
	 * @throws CommissionException 
	 */
	
	@Before
	public void SetUp() throws CommissionException{

		quantity = 15;
		emission = new StrategyCommissionCreditEmission(quantity);
		
	}
	
	
	/**
	 * Comprueba que la comision se calcula correctamente
	 */
	@Test
	public void testCalculateCommission() {
		
		assertTrue(emission.calculateCommission()==quantity);
	}

	
	/**
	 * Comprueba que la comision se calcula correctamente
	 */
	@Test
	public void testCalculateCommissionFalse() {

		assertFalse(emission.calculateCommission()==0);
	}
	
	
	/**
	 * Comprueba que se lanza la excepcion de la comision correctamente por el metodo try/catch
	 */
	@Test
	public void testCalculateNegativeCommission() {
		
		quantity = -10;
		try {
			emission = new StrategyCommissionCreditEmission(quantity);
		} catch (CommissionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Comprueba que la comision se actualiza correctamente
	 * @throws CommissionException
	 */
	@Test
	public void testCalculateCommissionActualize() throws CommissionException {

		assertTrue(emission.calculateCommission()==15);
		quantity = 3;
		emission = new StrategyCommissionCreditEmission(quantity);
		assertTrue(emission.calculateCommission()==3);
	}	

	
}


