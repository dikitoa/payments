package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StrategyCommissionShopBuyTest {

	private StrategyCommissionShop comision;
	private float interest, quantity;
	
	@Before
	public void SetUp(){

	}
	
	@Test
	public void testCalculateCommissionInt() {
		this.interest = (float) 0.03; //Equals 3%
		this.quantity = 25;
		this.comision = new StrategyCommissionShopBuy(quantity, interest);
		assertEquals(this.comision.calculateCommission(), 0.75, 0.001);
	}

	//REVISAR
	@Test
	public void testCalculateCommissionFloatFloat() {
		assertEquals(0.75, comision.calculateCommission(),0);
	}

}
