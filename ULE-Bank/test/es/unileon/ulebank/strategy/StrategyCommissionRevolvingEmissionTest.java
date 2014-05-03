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

public class StrategyCommissionRevolvingEmissionTest {

	private float quantity;
	StrategyCommissionRevolvingEmission commission;
	
	
	/**
	 * Inicializamos los valores para la realización de los tests
	 * @throws CommissionException
	 */
	@Before
	public void SetUp() throws CommissionException{
		
		quantity = 15;
		commission = new StrategyCommissionRevolvingEmission(quantity);
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
	 */
	@Test
	public void testCalculateNegativeCommission() {
		
		quantity = -10;
		try {
			commission = new StrategyCommissionRevolvingEmission(quantity);
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

		assertTrue(commission.calculateCommission()==15);
		quantity = 3;
		commission = new StrategyCommissionRevolvingEmission(quantity);
		assertTrue(commission.calculateCommission()==3);
	}	

	
}
