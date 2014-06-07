package es.unileon.ulebank.command;

import static org.junit.Assert.*;

import javax.security.auth.login.AccountNotFoundException;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.bank.BankHandler;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.CardNotFoundException;
import es.unileon.ulebank.exceptions.CommandException;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.exceptions.MalformedHandlerException;
import es.unileon.ulebank.exceptions.WrongArgsException;
import es.unileon.ulebank.fees.InvalidFeeException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CreditCard;

public class ModifyBuyLimitCommandTest {
	private Card testCard;
	private Handler handler;
	private Office office;
	private Handler dni;
	private Client client;
	private Account account;
	private ModifyBuyLimitCommand test;
	private ModifyBuyLimitCommand test2;
    private Bank bank;

    private String accountNumber = "0000000000";
	
	@Before
	public void setUp() throws CommissionException, InvalidFeeException, MalformedHandlerException, WrongArgsException {
		Handler bankHandler = new BankHandler("1234");
        this.bank = new Bank(bankHandler);
        this.handler = new CardHandler(bankHandler, "01", "123456789");
		this.office = new Office(new GenericHandler("1234"), this.bank);
		this.dni = new DNIHandler("71557005A");
		this.client = new Client(dni);
		this.office.addClient(client);
		this.account = new Account(office, bank, accountNumber, client);
		this.client.add(account);
		testCard = new CreditCard(handler, client, account, 400.0, 1000.0, 400.0, 1000.0, 25, 0, 0);
		account.addCard(testCard);
	}
	
	@Test
	public void testCommandNotNull() throws AccountNotFoundException  {
		test = new ModifyBuyLimitCommand(this.handler, this.testCard, 100.0, "diary");
		assertTrue(test != null);
	}
	
	@Test
	public void testCommandId() throws AccountNotFoundException  {
		test = new ModifyBuyLimitCommand(this.handler, this.testCard, 200.0, "diary");
		CommandHandler commandId = (CommandHandler) test.getId();
		String date = commandId.getDate();
		assertTrue(test.getId().toString().compareTo(handler.toString() + " " + date) == 0);
	}
	
	@Test
	public void testLimitDiaryModified() throws AccountNotFoundException, CardNotFoundException, IncorrectLimitException {
		test = new ModifyBuyLimitCommand(this.handler, this.testCard, 200.0, "diary");
		assertEquals(400.0, this.testCard.getBuyLimitDiary(), 0.0001);
		test.execute();
		assertEquals(200.0, testCard.getBuyLimitDiary(), 0.0001);
	}
	
	@Test (expected = IncorrectLimitException.class)
	public void testLimitDiaryNotModified() throws  IncorrectLimitException, CommandException, AccountNotFoundException, CardNotFoundException {
		test = new ModifyBuyLimitCommand(this.handler, this.testCard, 1100.0, "Diary");
		assertEquals(400.0, this.testCard.getBuyLimitDiary(), 0.0001);
		test.execute();
	}
	
	@Test
	public void testLimitMonthlyModified() throws IncorrectLimitException, CommandException, AccountNotFoundException, CardNotFoundException {
		test = new ModifyBuyLimitCommand(this.handler, this.testCard, 2000.0, "Monthly");
		assertEquals(1000.0, this.testCard.getBuyLimitMonthly(), 0.0001);
		test.execute();
		assertEquals(2000.0, this.testCard.getBuyLimitMonthly(), 0.0001);
	}
	
	@Test (expected = IncorrectLimitException.class)
	public void testLimitMonthlyNotModified() throws IncorrectLimitException, CommandException, AccountNotFoundException, CardNotFoundException {
		test = new ModifyBuyLimitCommand(this.handler, this.testCard, 300.0, "Monthly");
		assertEquals(1000.0, this.testCard.getBuyLimitMonthly(), 0.0001);
		test.execute();
	}
	
	@Test
	public void testTypeOK() throws IncorrectLimitException, CommandException, AccountNotFoundException, CardNotFoundException {
		test = new ModifyBuyLimitCommand(this.handler, this.testCard, 300.0, "DIARY");
		test.execute();
		assertTrue(this.testCard != null);
		assertEquals(300.0, this.testCard.getBuyLimitDiary(), 0.0001);
	}
	
	@Test
	public void testTypeNotOK() throws IncorrectLimitException, CommandException, AccountNotFoundException, CardNotFoundException {
		test = new ModifyBuyLimitCommand(this.handler, this.testCard, 300.0, "");
		test.execute();
		//Any changes in both limits
		assertEquals(400.0, testCard.getBuyLimitDiary(), 0.0001);
		assertEquals(1000.0, testCard.getBuyLimitMonthly(), 0.0001);
		test2 = new ModifyBuyLimitCommand(this.handler, this.testCard, 500.0, "123");
		test2.execute();
		assertEquals(400.0, testCard.getBuyLimitDiary(), 0.0001);
		assertEquals(1000.0, testCard.getBuyLimitMonthly(), 0.0001);
	}
	
	@Test
	public void undoDiaryTest() throws IncorrectLimitException, CommandException, AccountNotFoundException, CardNotFoundException {
		test = new ModifyBuyLimitCommand(this.handler, this.testCard, 300.0, "diary");
		test.execute();
		assertEquals(300.0, testCard.getBuyLimitDiary(), 0.0001);
		test.undo();
		assertEquals(400.0, testCard.getBuyLimitDiary(), 0.0001);
	}
	
	@Test (expected = CommandException.class)
	public void canNotUndoDiaryTest() throws IncorrectLimitException, CommandException, AccountNotFoundException, CardNotFoundException {
		test = new ModifyBuyLimitCommand(this.handler, this.testCard, 300.0, "diary");
		test.undo();
	}
	
	@Test
	public void redoDiaryTest() throws IncorrectLimitException, CommandException, AccountNotFoundException, CardNotFoundException {
		test = new ModifyBuyLimitCommand(this.handler, this.testCard, 300.0, "diary");
		test.execute();
		assertEquals(300.0, testCard.getBuyLimitDiary(), 0.0001);
		test.undo();
		assertEquals(400.0, testCard.getBuyLimitDiary(), 0.0001);
		test.redo();
		assertEquals(300.0, testCard.getBuyLimitDiary(), 0.0001);
	}
	
	@Test (expected = CommandException.class)
	public void canNotRedoDiaryTest() throws IncorrectLimitException, CommandException, AccountNotFoundException, CardNotFoundException {
		test = new ModifyBuyLimitCommand(this.handler, this.testCard, 300.0, "diary");
		test.execute();
		test.redo();
	}
	
	@Test
	public void undoMonthlyTest() throws IncorrectLimitException, CommandException, AccountNotFoundException, CardNotFoundException {
		test = new ModifyBuyLimitCommand(this.handler, this.testCard, 3000.0, "monthly");
		test.execute();
		assertEquals(3000.0, testCard.getBuyLimitMonthly(), 0.0001);
		test.undo();
		assertEquals(1000.0, testCard.getBuyLimitMonthly(), 0.0001);
	}
	
	@Test (expected = CommandException.class)
	public void canNotUndoMonthlyTest() throws IncorrectLimitException, CommandException, AccountNotFoundException, CardNotFoundException {
		test = new ModifyBuyLimitCommand(this.handler, this.testCard, 300.0, "monthly");
		test.undo();
	}
	
	@Test
	public void redoMonthlyTest() throws IncorrectLimitException, CommandException, AccountNotFoundException, CardNotFoundException {
		test = new ModifyBuyLimitCommand(this.handler, this.testCard, 3000.0, "monthly");
		test.execute();
		assertEquals(3000.0, testCard.getBuyLimitMonthly(), 0.0001);
		test.undo();
		assertEquals(1000.0, testCard.getBuyLimitMonthly(), 0.0001);
		test.redo();
		assertEquals(3000.0, testCard.getBuyLimitMonthly(), 0.0001);
	}
	
	@Test (expected = CommandException.class)
	public void canNotRedoMonthlyTest() throws IncorrectLimitException, CommandException, AccountNotFoundException, CardNotFoundException {
		test = new ModifyBuyLimitCommand(this.handler, this.testCard, 3000.0, "monthly");
		test.execute();
		test.redo();
	}
}
