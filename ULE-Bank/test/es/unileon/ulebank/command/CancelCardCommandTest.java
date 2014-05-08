package es.unileon.ulebank.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.Office;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.bank.BankHandler;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.payments.DebitCard;
import es.unileon.ulebank.transacionManager.TransactionManager;

public class CancelCardCommandTest {
	private Handler handler1;
	private Handler handler2;
	private Office office;
	private Handler dni;
	private Handler accountHandler;
	private Client client;
	private Account account;
	private Card card1;
	private Card card2;
	private CancelCardCommand test;
    private Bank bank;
    private TransactionManager manager;

    private String accountNumber = "0000000000";
	
	@Before
	public void setUp() throws NumberFormatException, CommissionException, IOException {
		BankHandler bankHandler = new BankHandler("1234");
		handler1 = new CardHandler(bankHandler, "01", "123456789");
		handler2 = new CardHandler(bankHandler, "01", "123456788");
		this.manager = new TransactionManager();
        this.bank = new Bank(manager, bankHandler);
        this.office = new Office(new GenericHandler("1234"), this.bank);
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
