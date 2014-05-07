package es.unileon.ulebank.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.AccountHandler;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.OfficeHandler;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Client;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.payments.Office;

public class ModifyBuyLimitCommandTest {
	private Card testCard;
	private CardHandler handler;
	private Office office;
	private DNIHandler dni;
	private AccountHandler accountHandler;
	private Client client;
	private Account account;
	private ModifyBuyLimitCommand test;
	private ModifyBuyLimitCommand test2;
	
	@Before
	public void setUp() throws CommissionException {
		handler = new CardHandler();
		this.office = new Office();
		this.dni = new DNIHandler("71557005A");
		client = new Client(dni, 20);
		this.office.addClient(client);
		this.accountHandler = new AccountHandler(new OfficeHandler("0001"), new GenericHandler("1234"), "9876543210");
		account = new Account(accountHandler);
		this.client.addAccount(account);
		testCard = new CreditCard(handler, client, account, 400.0, 1000.0, 400.0, 1000.0, 25, 0, 0);
		account.addCard(testCard);
	}
	
	@Test
	public void testCommandNotNull() {
		test = new ModifyBuyLimitCommand(this.handler, office, dni, accountHandler, 100.0, "diary");
		assertTrue(test != null);
	}
	
	@Test
	public void testCommandId() {
		test = new ModifyBuyLimitCommand(this.handler, office, dni, accountHandler, 200.0, "diary");
		CommandHandler commandId = (CommandHandler) test.getId();
		String date = commandId.getDate();
		assertTrue(test.getId().toString().compareTo(handler.toString() + " " + date) == 0);
	}
	
	@Test
	public void testLimitDiaryModified() {
		test = new ModifyBuyLimitCommand(this.handler, office, dni, accountHandler, 200.0, "Diary");
		assertEquals(400.0, this.testCard.getBuyLimitDiary(), 0.0001);
		test.execute();
		assertEquals(200.0, testCard.getBuyLimitDiary(), 0.0001);
	}
	
	@Test
	public void testLimitDiaryNotModified() {
		test = new ModifyBuyLimitCommand(handler, office, dni, accountHandler, 1100.0, "Diary");
		assertEquals(400.0, this.testCard.getBuyLimitDiary(), 0.0001);
		test.execute();
		//The limit wont be changed and will be 400 (default)
		assertEquals(400.0, this.testCard.getBuyLimitDiary(), 0.0001);
	}
	
	@Test
	public void testLimitMonthlyModified() {
		test = new ModifyBuyLimitCommand(handler, office, dni, accountHandler, 2000.0, "Monthly");
		assertEquals(1000.0, this.testCard.getBuyLimitMonthly(), 0.0001);
		test.execute();
		assertEquals(2000.0, this.testCard.getBuyLimitMonthly(), 0.0001);
	}
	
	@Test
	public void testLimitMonthlyNotModified() {
		test = new ModifyBuyLimitCommand(handler, office, dni, accountHandler, 300.0, "Monthly");
		assertEquals(1000.0, this.testCard.getBuyLimitMonthly(), 0.0001);
		test.execute();
		assertEquals(1000.0, this.testCard.getBuyLimitMonthly(), 0.0001);
	}
	
	@Test
	public void testTypeOK() {
		test = new ModifyBuyLimitCommand(handler, office, dni, accountHandler, 300.0, "DIARY");
		test.execute();
		assertTrue(this.testCard != null);
		assertEquals(300.0, this.testCard.getBuyLimitDiary(), 0.0001);
	}
	
	@Test
	public void testTypeNotOK() {
		test = new ModifyBuyLimitCommand(handler, office, dni, accountHandler, 300.0, "");
		test.execute();
		//Any changes in both limits
		assertEquals(400.0, testCard.getBuyLimitDiary(), 0.0001);
		assertEquals(1000.0, testCard.getBuyLimitMonthly(), 0.0001);
		test2 = new ModifyBuyLimitCommand(handler, office, dni, accountHandler, 500.0, "123");
		test2.execute();
		assertEquals(400.0, testCard.getBuyLimitDiary(), 0.0001);
		assertEquals(1000.0, testCard.getBuyLimitMonthly(), 0.0001);
	}
	
	@Test
	public void undoTest() {
		test = new ModifyBuyLimitCommand(this.handler, office, dni, accountHandler, 300.0, "diary");
		test.execute();
		assertEquals(300.0, testCard.getBuyLimitDiary(), 0.0001);
		test.undo();
		assertEquals(400.0, testCard.getBuyLimitDiary(), 0.0001);
	}
	
	@Test
	public void redoTest() {
		test = new ModifyBuyLimitCommand(this.handler, office, dni, accountHandler, 300.0, "diary");
		test.execute();
		assertEquals(300.0, testCard.getBuyLimitDiary(), 0.0001);
		test.undo();
		assertEquals(400.0, testCard.getBuyLimitDiary(), 0.0001);
		test.redo();
		assertEquals(300.0, testCard.getBuyLimitDiary(), 0.0001);
	}
}
