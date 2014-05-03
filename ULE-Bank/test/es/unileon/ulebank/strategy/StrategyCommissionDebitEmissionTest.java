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

public class StrategyCommissionDebitEmissionTest {

	StrategyCommissionDebitEmission comission;
	private float quantity;
	

	/**
	 * Inicialiamos las variables
	 * @throws CommissionException 
	 */
	@Before
	public void SetUp() throws CommissionException{
	
		quantity = 15;
		comission = new StrategyCommissionDebitEmission(quantity);
	}
	
	
	/**
	 * Comprueba que la comision se calcula correctamente
	 */
	@Test
	public void testCalculateCommission() {
		
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
	 */
	@Test
	public void testCalculateNegativeCommission() {
		
		quantity = -10;
		try {
			comission = new StrategyCommissionDebitEmission(quantity);
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

		assertTrue(comission.calculateCommission()==15);
		quantity = 3;
		comission = new StrategyCommissionDebitEmission(quantity);
		assertTrue(comission.calculateCommission()==3);
	}	

	
}
