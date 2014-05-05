package es.unileon.ulebank.strategy;

import static org.junit.Assert.*; 

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.exceptions.CommissionException;


public class StrategyCommissionCheckerConsultationTest {

	/**
	 * @author Marta
	 * 
	 * Comprobamos que la comision para la consulta es 0
	 */
	
	StrategyCommissionCheckerConsultation consulta;
	private float comission;
	
	@Before
	public void SetUp() throws CommissionException{
		comission = 5;
		consulta = new StrategyCommissionCheckerConsultation(comission);
	}
	
	
	/**
	 * Comprueba que la comision se calcula correctamente
	 */
	@Test
	public void testCalculateCommission() {
		
		assertTrue(5==consulta.calculateCommission());
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
	 * @throws CommissionException 
	 */
	@Test (expected = CommissionException.class)
	public void testCalculateNegativeCommission() throws CommissionException {
		
		comission = -3;
		consulta = new StrategyCommissionCheckerConsultation(comission);
	}
	
	
	
	/**
	 * Comprueba que la comision se actualiza correctamente
	 * @throws CommissionException
	 */
	@Test
	public void testCalculateCommissionActualize() throws CommissionException {

		assertTrue(5==consulta.calculateCommission());
		comission = 3;
		consulta = new StrategyCommissionCheckerConsultation(comission);
		assertTrue(consulta.calculateCommission()==3);
	}	

	
}
