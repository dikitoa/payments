package es.unileon.ulebank.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.bank.BankHandler;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.fees.InvalidFeeException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.OfficeHandler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.payments.DebitCard;
import es.unileon.ulebank.transactionManager.TransactionManager;

public class ReplacementCardCommandTest {
	private Handler handler1;
	private Handler handler2;
	private Office office;
	private Handler dni;
	private Client client;
	private Handler accountHandler;
	private Account account;
	private Card card1;
	private Card card2;
	private ReplacementCardCommand test;
	private Bank bank;
    private TransactionManager manager;

    private String accountNumber = "0000000000";
	
	@Before
	public void setUp() throws NumberFormatException, CommissionException, IOException, InvalidFeeException {
		this.manager = new TransactionManager();
		BankHandler bankHandler = new BankHandler("1234");
        this.bank = new Bank(manager, bankHandler);
		this.handler1 = new CardHandler(bankHandler, "01", "123456789");
		this.handler2 = new CardHandler(bankHandler, "01", "987654321");
		this.office =  new Office(new OfficeHandler("1234"), this.bank);
		this.dni = new DNIHandler("71557005A");
		this.client = new Client(dni, 20);
		this.office.addClient(client);
		this.account = new Account(office, bank, accountNumber);
		this.accountHandler = account.getID();
		this.client.add(account);
		this.card1 = new DebitCard(handler1, client, account, 400.0, 1000.0, 400.0, 1000.0, 25, 0, 0);
		this.card2 = new CreditCard(handler2, client, account, 400.0, 1000.0, 400.0, 1000.0, 25, 0, 0);
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
