package es.unileon.ulebank.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.handler.AccountHandler;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.IdDNI;
import es.unileon.ulebank.handler.IdOffice;
import es.unileon.ulebank.payments.Account;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Client;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.payments.DebitCard;
import es.unileon.ulebank.payments.Office;

public class ReplacementCardCommandTest {
	private CardHandler handler1;
	private CardHandler handler2;
	private Office office;
	private IdDNI dni;
	private Client client;
	private AccountHandler accountHandler;
	private Account account;
	private Card card1;
	private Card card2;
	private ReplacementCardCommand test;
	
	@Before
	public void setUp() throws NumberFormatException, CommissionException, IOException {
		handler1 = new CardHandler();
		handler2 = new CardHandler();
		this.office = new Office();
		this.dni = new IdDNI("71557005A");
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
		test = new ReplacementCardCommand(handler1, office, dni, accountHandler);
		CommandHandler handler = (CommandHandler) test.getId();
		assertTrue(handler1.compareTo(handler.getId()) == 0);
	}
	
	@Test
	public void testReplacementCreditCard() {
		test = new ReplacementCardCommand(handler2, office, dni, accountHandler);
		assertEquals("123", card2.getCvv());
		assertEquals("0000", card2.getPin());
		test.execute();
		assertTrue(!card2.getCvv().equals("123"));
		assertTrue(!card2.getPin().equals("0000"));
	}
	
	@Test
	public void testUndoReplacementCreditCardOk() {
		test = new ReplacementCardCommand(handler2, office, dni, accountHandler);
		assertEquals("123", card2.getCvv());
		assertEquals("0000", card2.getPin());
		test.execute();
		assertTrue(!card2.getCvv().equals("123"));
		assertTrue(!card2.getPin().equals("0000"));
		test.undo();
		assertEquals("123", card2.getCvv());
		assertEquals("0000", card2.getPin());
	}
	
	@Test (expected = NullPointerException.class)
	public void testUndoReplacementCreditCardFail() {
		test = new ReplacementCardCommand(handler2, office, dni, accountHandler);
		assertEquals("123", card2.getCvv());
		assertEquals("0000", card2.getPin());
		test.undo();
	}
	
	@Test
	public void testRedoReplacementCreditCardOk() {
		test = new ReplacementCardCommand(handler2, office, dni, accountHandler);
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
	
	@Test (expected = NullPointerException.class)
	public void testRedoReplacementCreditCardFail() {
		test = new ReplacementCardCommand(handler2, office, dni, accountHandler);
		assertEquals("123", card2.getCvv());
		assertEquals("0000", card2.getPin());
		test.redo();
	}
	
	@Test
	public void testReplacementDebitCard() {
		test = new ReplacementCardCommand(handler1, office, dni, accountHandler);
		assertEquals("213", card1.getCvv());
		assertEquals("1234", card1.getPin());
		test.execute();
		assertTrue(!card1.getCvv().equals("213"));
		assertTrue(!card1.getPin().equals("1234"));
	}
	
	@Test
	public void testUndoReplacementDebitCardOk() {
		test = new ReplacementCardCommand(handler1, office, dni, accountHandler);
		assertEquals("213", card1.getCvv());
		assertEquals("1234", card1.getPin());
		test.execute();
		assertTrue(!card1.getCvv().equals("213"));
		assertTrue(!card1.getPin().equals("1234"));
		test.undo();
		assertEquals("213", card1.getCvv());
		assertEquals("1234", card1.getPin());
	}
	
	@Test (expected = NullPointerException.class)
	public void testUndoReplacementDebitCardFail() {
		test = new ReplacementCardCommand(handler1, office, dni, accountHandler);
		assertEquals("213", card1.getCvv());
		assertEquals("1234", card1.getPin());
		test.undo();
	}
	
	@Test
	public void testRedoReplacementDebitCardOk() {
		test = new ReplacementCardCommand(handler1, office, dni, accountHandler);
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
	
	@Test (expected = NullPointerException.class)
	public void testRedoReplacementDebitCardFail() {
		test = new ReplacementCardCommand(handler1, office, dni, accountHandler);
		assertEquals("213", card1.getCvv());
		assertEquals("1234", card1.getPin());
		test.redo();
	}
}
