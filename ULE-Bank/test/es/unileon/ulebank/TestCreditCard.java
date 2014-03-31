package es.unileon.ulebank;

import org.junit.Before;

import es.unileon.ulebank.payments.CreditCard;

public class TestCreditCard {
    
	CreditCard creditCard;
	
	@Before
	public void setUp() throws Exception {
        creditCard = new CreditCard(); //generate a new credit card
	}
    
//	@Test
//	public void testNumberIsShort() {
//		creditCard.setNumber(1234567890);
////        assert.t;
//	}
//	
//	@Test
//	public void testNumberIsLong() {
//		creditCard.setNumber(1234567890);
//	}
//    
//    @Test
//	public void testNumberOK() {
//		creditCard.setNumber(1234567890);
//	}
    
}
