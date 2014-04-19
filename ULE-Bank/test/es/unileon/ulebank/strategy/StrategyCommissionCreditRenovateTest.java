package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Client;

/**
 * @author Marta
 * 
 * Comprobamos que la comision para esta tarjeta siempre es 30
 */

public class StrategyCommissionCreditRenovateTest {

	private Client owner;
	private Card card;
	private float quantity;
	private final float DEFAULT_COMMISSION = 30;
	StrategyCommissionCreditRenovate renovate;
	
	@Before 
	public void SetUp(){
		
		owner = new Client();
		quantity = 1500;
		renovate = new StrategyCommissionCreditRenovate(owner, card, quantity);
	}

	
	@Test
	public void testCalculateCommission() {
		
		assertEquals(DEFAULT_COMMISSION + quantity, renovate.calculateCommission(),0);
	}

}
