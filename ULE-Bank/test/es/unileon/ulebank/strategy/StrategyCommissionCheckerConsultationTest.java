package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.payments.Card;

public class StrategyCommissionCheckerConsultationTest {

	StrategyCommissionCheckerConsultation consulta;
	private Card card;
	
	
	@Before
	public void SetUp(){
		
		consulta = new StrategyCommissionCheckerConsultation(card);
	}
	
	@Test
	public void testCalculateCommission() {
		assertTrue(0==consulta.calculateCommission());
	}

}
