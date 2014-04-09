package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Marta
 * 
 * Comprobamos que la comision para esta tarjeta siempre es 30
 */

public class StrategyCommissionCreditRenovateTest {

	StrategyCommissionCreditRenovate renovate;
	
	@Before 
	public void SetUp(){
		
		renovate = new StrategyCommissionCreditRenovate();
	}

	
	@Test
	public void testCalculateCommission() {
		
		assertEquals(30, renovate.calculateCommission(),0);
	}

}
