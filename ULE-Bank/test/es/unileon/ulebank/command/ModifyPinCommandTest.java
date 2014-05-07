package es.unileon.ulebank.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.Office;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.DebitCard;
import es.unileon.ulebank.transacionManager.TransactionManager;

public class ModifyPinCommandTest {
	private Handler handler;
	private Office office;
	private Handler dni;
	private Handler accountHandler;
	private Client client;
	private Account account;
	private Card card;
	private ModifyPinCommand test;
	private String newPin;
	private Bank bank;
    private TransactionManager manager;

    private String accountNumber = "0000000000";
	
	@Before
	public void setUp() throws NumberFormatException, CommissionException, IOException {
		this.manager = new TransactionManager();
        this.bank = new Bank(manager, new GenericHandler("1234"));
		handler = new CardHandler();
		this.office = new Office(new GenericHandler("1234"), this.bank);
		this.dni = new DNIHandler("71557005A");
		client = new Client(dni, 20);
		this.office.addClient(client);
		account = new Account(office, bank, accountNumber);
		this.accountHandler = account.getID();
		this.client.add(account);
		this.card = new DebitCard(handler, client, account, 400.0, 1000.0, 400.0, 1000.0, 25, 0, 0);
		this.account.addCard(card);
		try {
			this.card.setPin("1234");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.newPin = "9876";
	}
	
	@Test (expected = NullPointerException.class)
	public void testCommandNull() {
		this.test = null;
		this.test.getId();
	}
	
	@Test
	public void testCommandId() {
		test = new ModifyPinCommand(this.handler, office, dni, accountHandler, newPin);
		CommandHandler commandId = (CommandHandler) test.getId();
		String date = commandId.getDate();
		assertTrue(test.getId().toString().compareTo(handler.toString() + " " + date) == 0);
	}
	
	@Test
	public void testModifyPinOk() {
		test = new ModifyPinCommand(handler, office, dni, accountHandler, newPin);
		assertEquals("1234", card.getPin());
		test.execute();
		assertEquals("9876", card.getPin());
	}
	
	@Test
	public void testModifyPinFail() {
		test = new ModifyPinCommand(handler, office, dni, accountHandler, "4s22");
		test.execute();
		assertEquals("1234", card.getPin());
		test = new ModifyPinCommand(handler, office, dni, accountHandler, "34433");
		test.execute();
		assertEquals("1234", card.getPin());
	}
	
	@Test
	public void testUndoMofifyPinOk() {
		test = new ModifyPinCommand(handler, office, dni, accountHandler, newPin);
		assertEquals("1234", card.getPin());
		test.execute();
		assertEquals("9876", card.getPin());
		test.undo();
		assertEquals("1234", card.getPin());
	}
	
	@Test (expected = NullPointerException.class)
	public void testUndoMofifyPinFail() {
		test = new ModifyPinCommand(handler, office, dni, accountHandler, newPin);
		assertEquals("1234", card.getPin());
		test.undo();
	}
	
	@Test
	public void testRedoModifyPinOk() {
		test = new ModifyPinCommand(handler, office, dni, accountHandler, newPin);
		assertEquals("1234", card.getPin());
		test.execute();
		assertEquals("9876", card.getPin());
		test.undo();
		assertEquals("1234", card.getPin());
		test.redo();
		assertEquals("9876", card.getPin());
	}
	
	@Test (expected = NullPointerException.class)
	public void testRedoModifyPinFail() {
		test = new ModifyPinCommand(handler, office, dni, accountHandler, newPin);
		assertEquals("1234", card.getPin());
		test.redo();
	}
}
