package es.unileon.ulebank.command;

import static org.junit.Assert.*;
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

public class CancelCardCommandTest {
	private CardHandler handler1;
	private CardHandler handler2;
	private Client client;
	private Account account;
	private Card card1;
	private Card card2;
	private CancelCardCommand test;
	
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
	}
	
	@Test (expected = NullPointerException.class)
	public void testCommandNull() {
		test = null;
		test.getId();
	}
	
	@Test
	public void testCommandId() {
		test = new CancelCardCommand(handler1, account);
		CommandHandler handler = (CommandHandler) test.getId();
		assertTrue(handler.getId().compareTo(card1.getCardNumber()) == 0);
	}
	
	@Test
	public void testCancelDebitCard() {
		test = new CancelCardCommand(handler1, account);
		assertEquals(2, account.getCardAmount());
		test.execute();
		assertEquals(1, account.getCardAmount());
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testUndoCancelDebitCard() {
		test = new CancelCardCommand(handler1, account);
		test.execute();
		test.undo();
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testRedoCancelDebitCard() {
		test = new CancelCardCommand(handler1, account);
		test.execute();
		test.redo();
	}
	
	@Test
	public void testCancelCreditCard() {
		test = new CancelCardCommand(handler2, account);
		assertEquals(2, account.getCardAmount());
		test.execute();
		assertEquals(1, account.getCardAmount());
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testUndoCancelCreditCard() {
		test = new CancelCardCommand(handler2, account);
		test.execute();
		test.undo();
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testRedoCancelCreditCard() {
		test = new CancelCardCommand(handler2, account);
		test.execute();
		test.redo();
	}
}
