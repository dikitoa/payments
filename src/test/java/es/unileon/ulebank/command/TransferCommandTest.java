package es.unileon.ulebank.command;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.bank.BankHandler;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.client.Person;
import es.unileon.ulebank.client.PersonHandler;
import es.unileon.ulebank.command.handler.CommandHandler;
import es.unileon.ulebank.exceptions.CommandException;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.fees.InvalidFeeException;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.MalformedHandlerException;
import es.unileon.ulebank.history.conditions.WrongArgsException;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.office.OfficeHandler;

public class TransferCommandTest {

	private Office office;
	private Handler dni;
	private Client client;
	private Account accountEmmissior;
	private Account accountReceiver;
	private TransferCommand test;
	private Bank bank;


	@Before
	public void setUp() throws NumberFormatException, CommissionException,
	IOException, InvalidFeeException, MalformedHandlerException,
	WrongArgsException {
		final Handler bankHandler = new BankHandler("1234");
		this.bank = new Bank(bankHandler);
		this.office = new Office(new OfficeHandler("1234"), this.bank);
		this.dni = new PersonHandler(71557005, 'A');
		this.client = new Person(71557005, 'A');
		this.office.addClient(this.client);
		this.accountEmmissior = new Account(this.office, this.bank, "0000000000",
				this.client);
		this.accountReceiver = new Account(this.office, this.bank, "0000000001",
				this.client);
		this.client.add(this.accountEmmissior);
		this.client.add(this.accountReceiver);
	}

	@Test
	public void testCommandNotNull() throws CommandException {
		this.test = new TransferCommand(this.office, this.accountEmmissior.getID(), this.dni,
				this.accountReceiver.getID(), this.dni, 20.00, "Concept");
		Assert.assertNotNull(this.test);
	}

	@Test
	public void testCommandNull() {
		Assert.assertNull(this.test);
	}

	@Test
	public void testCommandId() throws CommandException {
		this.test = new TransferCommand(this.office, this.accountEmmissior.getID(), this.dni,
				this.accountReceiver.getID(), this.dni, 20.00, "Concept");
		final CommandHandler handler = (CommandHandler) this.test.getID();
		Assert.assertTrue(handler.getId().compareTo(this.accountEmmissior.getID()) == 0);
	}
	
	@Test (expected = CommandException.class)
	public void testExecuteCommandEqualAccounts() throws CommandException {
		this.accountEmmissior.setBalance(100.00);
		this.test = new TransferCommand(this.office, this.accountEmmissior.getID(), this.dni,
				this.accountEmmissior.getID(), this.dni, 20.00, "Concept");
		this.test.execute();
	}

	@Test
	public void testExecuteCommandOk() throws CommandException {
		this.accountEmmissior.setBalance(100.00);
		this.test = new TransferCommand(this.office, this.accountEmmissior.getID(), this.dni,
				this.accountReceiver.getID(), this.dni, 20.00, "Concept");
		this.test.execute();
		Assert.assertEquals(80.00, this.accountEmmissior.getBalance(),0.0);
		Assert.assertEquals(20.00, this.accountReceiver.getBalance(),0.0);
	}
	
	@Test (expected = CommandException.class)
	public void testExecuteCommandWithoutBalance() throws CommandException {
		this.accountEmmissior.setBalance(0.00);
		this.test = new TransferCommand(this.office, this.accountEmmissior.getID(), this.dni,
				this.accountReceiver.getID(), this.dni, 20.00, "Concept");
		this.test.execute();
	}

	@Test (expected = CommandException.class)
	public void testExecuteCommandWithoutConcept() throws CommandException {
		this.accountEmmissior.setBalance(100.00);
		this.test = new TransferCommand(this.office, this.accountEmmissior.getID(), this.dni,
				this.accountReceiver.getID(), this.dni, 20.00, "");
		this.test.execute();
	}
	
	@Test (expected = CommandException.class)
	public void testExecuteCommandWithNeutralAmount() throws CommandException {
		this.accountEmmissior.setBalance(100.00);
		this.test = new TransferCommand(this.office, this.accountEmmissior.getID(), this.dni,
				this.accountReceiver.getID(), this.dni, 0.00, "Concpet");
		this.test.execute();
	}
	
	@Test (expected = CommandException.class)
	public void testExecuteCommandWithNegativeAmount() throws CommandException {
		this.accountEmmissior.setBalance(100.00);
		this.test = new TransferCommand(this.office, this.accountEmmissior.getID(), this.dni,
				this.accountReceiver.getID(), this.dni, -20.00, "Concpet");
		this.test.execute();
	}
	
	@Test
	public void testUndoCommandOk() throws CommandException {
		this.accountEmmissior.setBalance(100.00);
		this.test = new TransferCommand(this.office, this.accountEmmissior.getID(), this.dni,
				this.accountReceiver.getID(), this.dni, 20.00, "Concept");
		this.test.execute();
		Assert.assertEquals(80.00, this.accountEmmissior.getBalance(),0.0);
		Assert.assertEquals(20.00, this.accountReceiver.getBalance(),0.0);
		this.test.undo();
		Assert.assertEquals(100.00, this.accountEmmissior.getBalance(),0.0);
		Assert.assertEquals(0.00, this.accountReceiver.getBalance(),0.0);
	}
	
	@Test (expected = CommandException.class)
	public void testUndoCommandFail() throws CommandException {
		this.accountEmmissior.setBalance(100.00);
		this.test = new TransferCommand(this.office, this.accountEmmissior.getID(), this.dni,
				this.accountReceiver.getID(), this.dni, 20.00, "Concept");
		this.test.undo();
	}
	
	@Test
	public void testRedoCommandOk() throws CommandException {
		this.accountEmmissior.setBalance(100.00);
		this.test = new TransferCommand(this.office, this.accountEmmissior.getID(), this.dni,
				this.accountReceiver.getID(), this.dni, 20.00, "Concept");
		this.test.execute();
		Assert.assertEquals(80.00, this.accountEmmissior.getBalance(),0.0);
		Assert.assertEquals(20.00, this.accountReceiver.getBalance(),0.0);
		this.test.undo();
		Assert.assertEquals(100.00, this.accountEmmissior.getBalance(),0.0);
		Assert.assertEquals(0.00, this.accountReceiver.getBalance(),0.0);
		this.test.redo();
		Assert.assertEquals(80.00, this.accountEmmissior.getBalance(),0.0);
		Assert.assertEquals(20.00, this.accountReceiver.getBalance(),0.0);
	}
	
	@Test (expected = CommandException.class)
	public void testRedoCommandFail() throws CommandException {
		this.accountEmmissior.setBalance(100.00);
		this.test = new TransferCommand(this.office, this.accountEmmissior.getID(), this.dni,
				this.accountReceiver.getID(), this.dni, 20.00, "Concept");
		this.test.redo();
	}
}
