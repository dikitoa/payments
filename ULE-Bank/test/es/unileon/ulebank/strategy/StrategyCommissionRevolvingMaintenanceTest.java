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

public class StrategyCommissionRevolvingMaintenanceTest {

	private float quantity;
	StrategyCommissionRevolvingMaintenance commission;
	
	@Before
	public void SetUp() throws CommissionException{
		
		quantity = 15;
		commission  = new StrategyCommissionRevolvingMaintenance(quantity);
	}
	
	
	/**
	 * Comprueba que la comision se calcula correctamente
	 */
	@Test
	public void testCalculateCommission() {
		
		assertTrue(commission.calculateCommission()==quantity);
	}

	
	/**
	 * Comprueba que la comision se calcula correctamente
	 */
	@Test
	public void testCalculateCommissionFalse() {

		assertFalse(commission.calculateCommission()==0);
	}
	
	
	/**
	 * Comprueba que se lanza la excepcion de la comision correctamente por el metodo try/catch
	 * @throws CommissionException 
	 */
	@Test (expected = CommissionException.class)
	public void testCalculateNegativeCommission() throws CommissionException {
		
		quantity = -10;
		commission = new StrategyCommissionRevolvingMaintenance(quantity);
	}
	
	
	
	/**
	 * Comprueba que la comision se actualiza correctamente
	 * @throws CommissionException
	 */
	@Test
	public void testCalculateCommissionActualize() throws CommissionException {

		assertTrue(commission.calculateCommission()==15);
		quantity = 3;
		commission = new StrategyCommissionRevolvingMaintenance(quantity);
		assertTrue(commission.calculateCommission()==3);
	}	

	
}
