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

public class RenovateCardCommandTest {
	private CardHandler handler1;
	private CardHandler handler2;
	private Office office;
	private DNIHandler dni;
	private Client client;
	private AccountHandler accountHandler;
	private Account account;
	private Card card1;
	private Card card2;
	private RenovateCardCommand test;
	
	@Before
	public void setUp() throws NumberFormatException, CommissionException, IOException {
		handler1 = new CardHandler();
		handler2 = new CardHandler();
		this.office = new Office();
		this.dni = new DNIHandler("71557005A");
		client = new Client(dni, 20);
		this.office.addClient(client);
		this.accountHandler = new AccountHandler(new IdOffice("0001"), new GenericHandler("1234"), "9876543210");
		account = new Account(accountHandler);
		this.client.addAccount(account);
		this.card1 = new DebitCard(handler1, client, account, 400.0, 1000.0, 400.0, 1000.0, 25, 0, 0, 0);
		this.card2 = new CreditCard(handler2, client, account, 400.0, 1000.0, 400.0, 1000.0, 25, 0, 0, 3000.0);
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
		test = new RenovateCardCommand(handler1, office, dni, accountHandler);
		CommandHandler handler = (CommandHandler) test.getId();
		assertTrue(this.handler1.compareTo(handler.getId()) == 0);
	}
	
	@Test
	public void testRenovateCreditCard() {
		test = new RenovateCardCommand(handler2, office, dni, accountHandler);
		assertEquals("04/14", this.card2.getExpirationDate());
		assertEquals("123", this.card2.getCvv());
		test.execute();
		assertTrue(!this.card2.getExpirationDate().equals("04/14"));
		assertTrue(!this.card2.getCvv().equals("123"));
	}
	
	@Test
	public void testUndoRenovateCreditCardOk() {
		test = new RenovateCardCommand(handler2, office, dni, accountHandler);
		assertEquals("04/14", this.card2.getExpirationDate());
		assertEquals("123", this.card2.getCvv());
		test.execute();
		assertTrue(!this.card2.getExpirationDate().equals("04/14"));
		assertTrue(!this.card2.getCvv().equals("123"));
		test.undo();
		assertEquals("04/14", this.card2.getExpirationDate());
		assertEquals("123", this.card2.getCvv());
	}
	
	@Test (expected = NullPointerException.class)
	public void testUndoRenovateCreditCardFail() {
		test = new RenovateCardCommand(handler2, office, dni, accountHandler);
		assertEquals("04/14", this.card2.getExpirationDate());
		assertEquals("123", this.card2.getCvv());
		test.undo();
	}
	
	@Test
	public void testRedoRenovateCreditCardOk() {
		test = new RenovateCardCommand(handler2, office, dni, accountHandler);
		assertEquals("04/14", this.card2.getExpirationDate());
		assertEquals("123", this.card2.getCvv());
		test.execute();
		assertTrue(!this.card2.getExpirationDate().equals("04/14"));
		assertTrue(!this.card2.getCvv().equals("123"));
		test.undo();
		assertEquals("04/14", this.card2.getExpirationDate());
		assertEquals("123", this.card2.getCvv());
		test.redo();
		assertTrue(!this.card2.getExpirationDate().equals("04/14"));
		assertTrue(!this.card2.getCvv().equals("123"));
	}
	
	@Test (expected = NullPointerException.class)
	public void testRedoRenovateCreditCardFail() {
		test = new RenovateCardCommand(handler2, office, dni, accountHandler);
		assertEquals("04/14", this.card2.getExpirationDate());
		assertEquals("123", this.card2.getCvv());
		test.redo();
	}
	
	@Test
	public void testRenovateDebitCard() {
		test = new RenovateCardCommand(handler1, office, dni, accountHandler);
		assertEquals("04/14", this.card1.getExpirationDate());
		assertEquals("213", this.card1.getCvv());
		test.execute();
		assertTrue(!this.card1.getExpirationDate().equals("04/14"));
		assertTrue(!this.card1.getCvv().equals("213"));
	}
	
	@Test
	public void testUndoRenovateDebitCardOk() {
		test = new RenovateCardCommand(handler1, office, dni, accountHandler);
		assertEquals("04/14", this.card1.getExpirationDate());
		assertEquals("213", this.card1.getCvv());
		test.execute();
		assertTrue(!this.card1.getExpirationDate().equals("04/14"));
		assertTrue(!this.card1.getCvv().equals("213"));
		test.undo();
		assertEquals("04/14", this.card1.getExpirationDate());
		assertEquals("213", this.card1.getCvv());
	}
	
	@Test (expected = NullPointerException.class)
	public void testUndoRenovateDebitCardFail() {
		test = new RenovateCardCommand(handler1, office, dni, accountHandler);
		assertEquals("04/14", this.card1.getExpirationDate());
		assertEquals("213", this.card1.getCvv());
		test.undo();
	}
	
	@Test
	public void testRedoRenovateDebitCardOk() {
		test = new RenovateCardCommand(handler1, office, dni, accountHandler);
		assertEquals("04/14", this.card1.getExpirationDate());
		assertEquals("213", this.card1.getCvv());
		test.execute();
		assertTrue(!this.card1.getExpirationDate().equals("04/14"));
		assertTrue(!this.card1.getCvv().equals("213"));
		test.undo();
		assertEquals("04/14", this.card1.getExpirationDate());
		assertEquals("213", this.card1.getCvv());
		test.redo();
		assertTrue(!this.card1.getExpirationDate().equals("04/14"));
		assertTrue(!this.card1.getCvv().equals("213"));
	}
	
	@Test (expected = NullPointerException.class)
	public void testRedoRenovateDebitCardFail() {
		test = new RenovateCardCommand(handler1, office, dni, accountHandler);
		assertEquals("04/14", this.card1.getExpirationDate());
		assertEquals("213", this.card1.getCvv());
		test.redo();
	}
}
