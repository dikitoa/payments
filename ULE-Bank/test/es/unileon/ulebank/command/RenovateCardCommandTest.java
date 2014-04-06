package es.unileon.ulebank.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.payments.DebitCard;

public class RenovateCardCommandTest {
	private Card card1;
	private Card card2;
	private RenovateCardCommand test;
	
	@Before
	public void setUp() {
		this.card1 = new DebitCard();
		this.card1.create();
		this.card2 = new CreditCard();
		this.card2.create();
		card1.setCvv("213");
		card2.setCvv("123");
		card1.setExpirationDate("03/14");
		card2.setExpirationDate("03/14");
	}
	
	@Test
	public void testCommandNotNull() {
		test = new RenovateCardCommand(card1);
		assertTrue(test != null);
	}
	
	@Test
	public void testRenovateCreditCardOK() {
		test = new RenovateCardCommand(card2);
		assertEquals("03/14", this.card2.getExpirationDate());
		assertEquals("123", this.card2.getCvv());
		test.execute();
		assertEquals("03/17", this.card2.getExpirationDate());
		assertTrue(!this.card2.getCvv().equals("123"));
	}
	
	@Test
	public void testRenovateDebitCardOK() {
		test = new RenovateCardCommand(card1);
		assertEquals("03/14", this.card1.getExpirationDate());
		assertEquals("213", this.card1.getCvv());
		test.execute();
		assertEquals("03/17", this.card1.getExpirationDate());
		assertTrue(!this.card1.getCvv().equals("213"));
	}
	
	
}
