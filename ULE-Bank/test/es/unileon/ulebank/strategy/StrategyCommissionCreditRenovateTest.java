package es.unileon.ulebank.strategy;

import static org.junit.Assert.*; 

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.exceptions.CommissionException;


/**
 * @author Marta
 * 
 * Comprobamos que la comision para esta tarjeta siempre es 30
 */

public class StrategyCommissionCreditRenovateTest {


	StrategyCommissionCreditRenovate renovate;
	private float quantity;
	
	
	@Before 
	public void SetUp() throws CommissionException{
		
		quantity = 15;
		renovate = new StrategyCommissionCreditRenovate(quantity);
	}

	
	/**
	 * Comprueba que la comision se calcula correctamente
	 */
	@Test
	public void testCalculateCommission() {
		
		assertTrue(renovate.calculateCommission()==quantity);
	}

	
	/**
	 * Comprueba que la comision se calcula correctamente
	 */
	@Test
	public void testCalculateCommissionFalse() {

		assertFalse(renovate.calculateCommission()==0);
	}
	
	
	/**
	 * Comprueba que se lanza la excepcion de la comision correctamente por el metodo try/catch
	 */
	@Test
	public void testCalculateNegativeCommission() {
		
		quantity = -10;
		try {
			renovate = new StrategyCommissionCreditRenovate(quantity);
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

		assertTrue(renovate.calculateCommission()==15);
		quantity = 3;
		renovate = new StrategyCommissionCreditRenovate(quantity);
		assertTrue(renovate.calculateCommission()==3);
	}	

	
}
