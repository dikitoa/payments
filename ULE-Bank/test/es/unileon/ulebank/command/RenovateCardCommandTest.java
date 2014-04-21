package es.unileon.ulebank.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.payments.Account;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Client;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.payments.DebitCard;
import es.unileon.ulebank.strategy.StrategyCommission;
import es.unileon.ulebank.strategy.StrategyCommissionCreditEmission;
import es.unileon.ulebank.strategy.StrategyCommissionCreditMaintenance;
import es.unileon.ulebank.strategy.StrategyCommissionCreditRenovate;
import es.unileon.ulebank.strategy.StrategyCommissionDebitEmission;
import es.unileon.ulebank.strategy.StrategyCommissionDebitMaintenance;
import es.unileon.ulebank.strategy.StrategyCommissionDebitRenovate;

public class RenovateCardCommandTest {
	private CardHandler handler1;
	private CardHandler handler2;
	private Client client;
	private Account account;
	private Card card1;
	private Card card2;
	private RenovateCardCommand test;
	
	@Before
	public void setUp() {
		handler1= new CardHandler();
		handler2 = new CardHandler();
		client = new Client();
		account = new Account();
		StrategyCommission commissionEmission = new StrategyCommissionDebitEmission(client, card1, 25);
		StrategyCommission commissionMaintenance = new StrategyCommissionDebitMaintenance(client, card1, 0);
		StrategyCommission commissionRenovate = new StrategyCommissionDebitRenovate(client, card1, 0);
		this.card1 = new DebitCard(handler1, client, account, 400.0, 1000.0, 400.0, 1000.0, commissionEmission, commissionMaintenance, commissionRenovate, 0);
		commissionEmission = new StrategyCommissionCreditEmission(client, card2, 25);
		commissionMaintenance = new StrategyCommissionCreditMaintenance(client, card2, 0);
		commissionRenovate = new StrategyCommissionCreditRenovate(client, card2, 0);
		this.card2 = new CreditCard(handler2, client, account, 400.0, 1000.0, 400.0, 1000.0, commissionEmission, commissionMaintenance, commissionRenovate, 3000.0);
		account.addCard(card1);
		account.addCard(card2);
		try {
			card1.setCvv("213");
			card2.setCvv("123");
		} catch (IOException e) {
			e.printStackTrace();
		}
		card1.setExpirationDate("04/14");
		card2.setExpirationDate("04/14");
	}
	
	@Test (expected = NullPointerException.class)
	public void testCommandNull() {
		test = null;
		test.getId();
	}
	
	@Test
	public void testCommandId() {
		test = new RenovateCardCommand(handler1, account);
		assertTrue(this.handler1.compareTo(card1.getCardNumber()) == 0);
	}
	
	@Test
	public void testRenovateCreditCardOK() {
		test = new RenovateCardCommand(handler2, account);
		assertEquals("04/14", this.card2.getExpirationDate());
		assertEquals("123", this.card2.getCvv());
		test.execute();
		assertTrue(!this.card2.getExpirationDate().equals("04/14"));
		assertTrue(!this.card2.getCvv().equals("123"));
	}
	
	@Test
	public void testRenovateDebitCardOK() {
		test = new RenovateCardCommand(handler1, account);
		assertEquals("04/14", this.card1.getExpirationDate());
		assertEquals("213", this.card1.getCvv());
		test.execute();
		assertTrue(!this.card1.getExpirationDate().equals("04/14"));
		assertTrue(!this.card1.getCvv().equals("213"));
	}
	
	
}
