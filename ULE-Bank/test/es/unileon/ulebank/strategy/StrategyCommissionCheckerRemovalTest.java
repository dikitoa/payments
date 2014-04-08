package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StrategyCommissionCheckerRemovalTest {

	StrategyCommissionRenovateRevolving comision = new StrategyCommissionRenovateRevolving();
	int owner;
	float interest, quantity;
	
	@Before
	public void SetUp(){
		interest = 5;
		quantity = 25;
		owner = 20;
	}
	
	@Test
	public void testCalculateCommissionInt() {
		
		assertEquals(0, comision.calculateCommission(owner));
	}

	
	@Test
	public void testCalculateCommissionFloatFloat() {
		
		assertEquals(0, comision.calculateCommission(interest, quantity));
	}

}
