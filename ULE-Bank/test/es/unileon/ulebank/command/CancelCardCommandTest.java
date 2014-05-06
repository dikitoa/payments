package es.unileon.ulebank.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.AccountHandler;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.IdOffice;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Client;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.payments.DebitCard;
import es.unileon.ulebank.payments.Office;

public class CancelCardCommandTest {
	private CardHandler handler1;
	private CardHandler handler2;
	private Office office;
	private DNIHandler dni;
	private AccountHandler accountHandler;
	private Client client;
	private Account account;
	private Card card1;
	private Card card2;
	private CancelCardCommand test;
	
	@Before
	public void setUp() throws NumberFormatException, CommissionException, IOException {
		handler1 = new CardHandler();
		handler2 = new CardHandler();
		this.office = new Office();
		this.dni = new DNIHandler("71557005A");
		this.client = new Client(dni, 20);
		this.office.addClient(client);
		this.accountHandler = new AccountHandler(new IdOffice("0001"), new GenericHandler("1234"), "9876543210");
		this.account = new Account(accountHandler);
		this.client.addAccount(account);
		this.card1 = new DebitCard(handler1, client, account, 400.0, 1000.0, 400.0, 1000.0, 25, 0, 0, 0);
		this.card2 = new CreditCard(handler2, client, account, 400.0, 1000.0, 400.0, 1000.0, 25, 0, 0, 3000.0);
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
		test = new CancelCardCommand(handler1, office, dni, accountHandler);
		CommandHandler handler = (CommandHandler) test.getId();
		assertTrue(handler.getId().compareTo(card1.getCardNumber()) == 0);
	}
	
	@Test
	public void testCancelDebitCard() {
		test = new CancelCardCommand(handler1, office, dni, accountHandler);
		assertEquals(2, account.getCardAmount());
		test.execute();
		assertEquals(1, account.getCardAmount());
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testUndoCancelDebitCard() {
		test = new CancelCardCommand(handler1, office, dni, accountHandler);
		test.execute();
		test.undo();
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testRedoCancelDebitCard() {
		test = new CancelCardCommand(handler1, office, dni, accountHandler);
		test.execute();
		test.redo();
	}
	
	@Test
	public void testCancelCreditCard() {
		test = new CancelCardCommand(handler2, office, dni, accountHandler);
		assertEquals(2, account.getCardAmount());
		test.execute();
		assertEquals(1, account.getCardAmount());
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testUndoCancelCreditCard() {
		test = new CancelCardCommand(handler2, office, dni, accountHandler);
		test.execute();
		test.undo();
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testRedoCancelCreditCard() {
		test = new CancelCardCommand(handler2, office, dni, accountHandler);
		test.execute();
		test.redo();
	}
}
