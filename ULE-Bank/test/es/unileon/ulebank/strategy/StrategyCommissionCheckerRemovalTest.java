package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.payments.Card;

public class StrategyCommissionCheckerRemovalTest {

	StrategyCommissionCheckerRemoval comision;
	private Card card;
	
	@Before
	public void SetUp(){

		comision = new StrategyCommissionCheckerRemoval(card, 0);
	}
	
	@Test
	public void testCalculateCommission() {
		
		assertTrue(comision.calculateCommission()==0);
	}

	@Test
	public void testCalculateCommission2() {
		
		assertFalse(comision.calculateCommission()!=0);
	}

}
