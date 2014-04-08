package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StrategyCommissionShopTest {

	StrategyCommissionShop comision = new StrategyCommissionShop();
	int ownerage;
	float interest, quantity;
	@Before
	public void SetUp(){
		ownerage = 18;
		interest = 3;
		quantity = 25;
	}
	
	@Test
	public void testCalculateCommissionInt() {
		
		assertEquals(0, comision.calculateCommission(ownerage));
	}

	//REVISAR
	@Test
	public void testCalculateCommissionFloatFloat() {
		assertEquals(0, comision.calculateCommission(interest, quantity));
	}

}
