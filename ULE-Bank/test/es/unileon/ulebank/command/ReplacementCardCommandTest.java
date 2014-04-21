package es.unileon.ulebank.command;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
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

public class ReplacementCardCommandTest {
	private CardHandler handler1;
	private CardHandler handler2;
	private Client client;
	private Account account;
	private Card card1;
	private Card card2;
	private ReplacementCardCommand test;
	
	@Before
	public void setUp() {
		handler1 = new CardHandler();
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
		try {
			card1.setPin("1234");
			card2.setPin("0000");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = NullPointerException.class)
	public void testCommandNull() {
		test = null;
		test.getId();
	}
	
	@Test
	public void testCommandId() {
		test = new ReplacementCardCommand(handler1, account);
		CommandHandler handler = (CommandHandler) test.getId();
		assertTrue(handler1.compareTo(handler.getId()) == 0);
	}
	
	@Test
	public void testReplacementCreditCard() {
		test = new ReplacementCardCommand(handler2, account);
		assertEquals("123", card2.getCvv());
		assertEquals("0000", card2.getPin());
		test.execute();
		assertTrue(!card2.getCvv().equals("123"));
		assertTrue(!card2.getPin().equals("0000"));
	}
	
	@Test
	public void testUndoReplacementCreditCard() {
		test = new ReplacementCardCommand(handler2, account);
		assertEquals("123", card2.getCvv());
		assertEquals("0000", card2.getPin());
		test.execute();
		assertTrue(!card2.getCvv().equals("123"));
		assertTrue(!card2.getPin().equals("0000"));
		test.undo();
		assertEquals("123", card2.getCvv());
		assertEquals("0000", card2.getPin());
	}
	
	@Test
	public void testRedoReplacementCreditCard() {
		test = new ReplacementCardCommand(handler2, account);
		assertEquals("123", card2.getCvv());
		assertEquals("0000", card2.getPin());
		test.execute();
		assertTrue(!card2.getCvv().equals("123"));
		assertTrue(!card2.getPin().equals("0000"));
		test.undo();
		assertEquals("123", card2.getCvv());
		assertEquals("0000", card2.getPin());
		test.redo();
		assertTrue(!card2.getCvv().equals("123"));
		assertTrue(!card2.getPin().equals("0000"));
	}
	
	@Test
	public void testReplacementDebitCard() {
		test = new ReplacementCardCommand(handler1, account);
		assertEquals("213", card1.getCvv());
		assertEquals("1234", card1.getPin());
		test.execute();
		assertTrue(!card1.getCvv().equals("213"));
		assertTrue(!card1.getPin().equals("1234"));
	}
	
	@Test
	public void testUndoReplacementDebitCard() {
		test = new ReplacementCardCommand(handler1, account);
		assertEquals("213", card1.getCvv());
		assertEquals("1234", card1.getPin());
		test.execute();
		assertTrue(!card1.getCvv().equals("213"));
		assertTrue(!card1.getPin().equals("1234"));
		test.undo();
		assertEquals("213", card1.getCvv());
		assertEquals("1234", card1.getPin());
	}
	
	@Test
	public void testRedoReplacementDebitCard() {
		test = new ReplacementCardCommand(handler1, account);
		assertEquals("213", card1.getCvv());
		assertEquals("1234", card1.getPin());
		test.execute();
		assertTrue(!card1.getCvv().equals("213"));
		assertTrue(!card1.getPin().equals("1234"));
		test.undo();
		assertEquals("213", card1.getCvv());
		assertEquals("1234", card1.getPin());
		test.redo();
		assertTrue(!card1.getCvv().equals("213"));
		assertTrue(!card1.getPin().equals("1234"));
	}
}
