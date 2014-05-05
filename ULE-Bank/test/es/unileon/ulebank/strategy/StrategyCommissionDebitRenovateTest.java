package es.unileon.ulebank.strategy;

import static org.junit.Assert.*; 

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.exceptions.CommissionException;

/**
 * @author Marta
 * 
 */

public class StrategyCommissionDebitRenovateTest {
	
	StrategyCommissionDebitRenovate comission;	
	private float quantity;

	
	/**
	 * Inicializamos los valores para la realización de los tests
	 * @throws CommissionException 
	 */
	@Before
	public void SetUp() throws CommissionException{
		
		quantity = 15;
		comission = new StrategyCommissionDebitRenovate(quantity);
	}
	
	
	/**
	 * Comprueba que la comision se calcula correctamente
	 */
	@Test
	public void testCalculateCommissionTrue() {
		
		assertTrue(comission.calculateCommission()==quantity);
	}

	
	/**
	 * Comprueba que la comision se calcula correctamente
	 */
	@Test
	public void testCalculateCommissionFalse() {

		assertFalse(comission.calculateCommission()==0);
	}
	
	
	/**
	 * Comprueba que se lanza la excepcion de la comision correctamente por el metodo try/catch
	 * @throws CommissionException 
	 */
	@Test (expected = CommissionException.class)
	public void testCalculateNegativeCommission() throws CommissionException {
		
		quantity = -10;
		comission = new StrategyCommissionDebitRenovate(quantity);
	}
	
	
	
	/**
	 * Comprueba que la comision se actualiza correctamente
	 * @throws CommissionException
	 */
	@Test
	public void testCalculateCommissionActualize() throws CommissionException {

		assertTrue(comission.calculateCommission()==15);
		quantity = 3;
		comission = new StrategyCommissionDebitRenovate(quantity);
		assertTrue(comission.calculateCommission()==3);
	}	
}
