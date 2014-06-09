package es.unileon.ulebank.payments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.bank.BankHandler;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.MalformedHandlerException;
import es.unileon.ulebank.exceptions.TransferException;
import es.unileon.ulebank.exceptions.WrongArgsException;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.OfficeHandler;
import es.unileon.ulebank.history.TransactionException;
import es.unileon.ulebank.office.Office;

/**
 * Test about Transfer Class
 * @author Rober dCR
 * @date 02/04/2014
 */
public class TransferTest {

	public Account senderAccount;
	public Account receiverAccount;
	public float quantity;
	Transfer transfer;
	Transfer transfer2;
    private Office office;
    private Bank bank;
    private Client client1;
    private Client client2;

    private String accountNumber = "0000000000";
	
	@Before
	public void setUp() throws MalformedHandlerException, WrongArgsException{
        this.bank = new Bank(new BankHandler("1234"));
        this.office = new Office(new OfficeHandler("1234"), this.bank);
        this.client1 = new Client(new DNIHandler("71557005A"));
        this.client2 = new Client(new DNIHandler("71560136Y"));
		this.senderAccount = new Account(office, bank, accountNumber, client1);
		this.receiverAccount = new Account(office, bank, accountNumber, client2);
		this.quantity = (float) 20.5;
	}
	
	@Test
	public void transferOk() throws TransferException {
		this.transfer = new Transfer(this.senderAccount, this.receiverAccount, this.quantity);
		assertNotNull(this.transfer);
	}
	
	@Test
	public void transferNull() throws TransferException {
		assertNull(this.transfer2);
	}
	
	@Test
	public void transferMoneyWithBalanceTest() throws TransferException, TransactionException {
		this.senderAccount.setBalance(100);
		this.receiverAccount.setBalance(0);
		double beforeMoneyReceiver = this.receiverAccount.getBalance();
		double beforeMoneySender = this.senderAccount.getBalance();
		this.transfer = new Transfer(this.senderAccount, this.receiverAccount, this.quantity);
		this.transfer.make("Concepto");
		double afterMoneyReceiver = this.receiverAccount.getBalance();
		double afterMoneySender = this.senderAccount.getBalance();
		
		assertEquals(afterMoneyReceiver - beforeMoneyReceiver, this.quantity, 0.01);
		assertEquals(afterMoneyReceiver, this.quantity + beforeMoneyReceiver, 0.01);
		assertEquals(beforeMoneySender - afterMoneySender, this.quantity, 0.01);
		assertEquals(afterMoneySender, beforeMoneySender - this.quantity, 0.01);
	}

	@Test (expected = TransferException.class)
	public void transferMoneyWithOutBalanceTest()throws TransferException, TransactionException {
		this.senderAccount.setBalance(0);
		this.receiverAccount.setBalance(0);
		this.transfer = new Transfer(this.senderAccount, this.receiverAccount, this.quantity);
		this.transfer.make("Concepto");
	}
	
	@Test (expected = TransferException.class)
	public void transferMoneyEqualsAccountTest()throws TransferException, MalformedHandlerException, WrongArgsException {
		Account exAccount = new Account(office, bank, accountNumber, client1);
		this.transfer = new Transfer(exAccount, exAccount, this.quantity);
	}
}