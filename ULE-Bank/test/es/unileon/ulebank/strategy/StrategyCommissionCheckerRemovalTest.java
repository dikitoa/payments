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


public class StrategyCommissionCheckerRemovalTest {

	StrategyCommissionCheckerRemoval consulta;
	float comission;
	
	@Before
	public void SetUp() throws CommissionException{
		comission = 5;
		consulta = new StrategyCommissionCheckerRemoval(comission);
	}


	
	/**
	 * Comprueba que la comision se calcula correctamente
	 */
	@Test
	public void testCalculateCommissionTrue() {
		
		assertTrue(consulta.calculateCommission()==5);
	}

	
	/**
	 * Comprueba que la comision se calcula correctamente
	 */
	@Test
	public void testCalculateCommissionFalse() {

		assertFalse(consulta.calculateCommission()==0);
	}
	
	
	/**
	 * Comprueba que se lanza la excepcion de la comision correctamente por el metodo try/catch
	 */
	@Test
	public void testCalculateNegativeCommission() {
		
		comission = -3;
		try {
			consulta = new StrategyCommissionCheckerRemoval(comission);
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

		assertTrue(consulta.calculateCommission()==5);
		comission = 3;
		consulta = new StrategyCommissionCheckerRemoval(comission);
		assertTrue(consulta.calculateCommission()==3);
	}	

	
}
