package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StrategyCommissionCheckerConsultationTest {

	StrategyCommissionCheckerConsultation consulta = new StrategyCommissionCheckerConsultation();
	int ownerage;
	float interest, quantity; 
	
	@Before
	public void SetUp(){
		
		ownerage = 18;
		interest = 5;
		quantity = 100;
	}
	
	@Test
	public void testCalculateCommissionInt() {
		assertEquals(0, consulta.calculateCommission(ownerage));
	}

	@Test
	public void testCalculateCommissionFloatFloat() {

		assertEquals(0, consulta.calculateCommission(interest, quantity));
	}

}
