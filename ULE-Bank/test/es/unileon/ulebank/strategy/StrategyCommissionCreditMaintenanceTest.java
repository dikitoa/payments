package es.unileon.ulebank.strategy;

import static org.junit.Assert.*; 

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.exceptions.CommissionException;


/**
 * @author Marta
 * 
 * Comprobamos que la comision para esta tarjeta siempre es 25
 */

public class StrategyCommissionCreditMaintenanceTest {

	StrategyCommissionCreditMaintenance comision;
	private float quantity;
	
	
	/**
	 * Inicializamos los parametros en el Before
	 * @throws CommissionException 
	 */
	@Before
	public void setUp() throws CommissionException{
		
		quantity = 15;
		comision = new StrategyCommissionCreditMaintenance(quantity);
	}
	
	
	/**
	 * Comprueba que la comision se calcula correctamente
	 */
	@Test
	public void testCalculateCommission() {
		
		assertTrue(comision.calculateCommission()==quantity);
	}

	
	/**
	 * Comprueba que la comision se calcula correctamente
	 */
	@Test
	public void testCalculateCommissionFalse() {

		assertFalse(comision.calculateCommission()==0);
	}
	
	
	/**
	 * Comprueba que se lanza la excepcion de la comision correctamente por el metodo try/catch
	 * @throws CommissionException 
	 */
	@Test (expected = CommissionException.class)
	public void testCalculateNegativeCommission() throws CommissionException {
		
		quantity = -10;
		comision = new StrategyCommissionCreditMaintenance(quantity);
	}
	
	
	
	/**
	 * Comprueba que la comision se actualiza correctamente
	 * @throws CommissionException
	 */
	@Test
	public void testCalculateCommissionActualize() throws CommissionException {

		assertTrue(comision.calculateCommission()==15);
		quantity = 3;
		comision = new StrategyCommissionCreditMaintenance(quantity);
		assertTrue(comision.calculateCommission()==3);
	}	

	
}
