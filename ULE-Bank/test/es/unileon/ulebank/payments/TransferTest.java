package es.unileon.ulebank.payments;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.AccountHandler;
import es.unileon.ulebank.exceptions.TransferException;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.IdOffice;

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
	
	@Before
	public void setUp(){	
		this.senderAccount = new Account(new AccountHandler(new IdOffice("0001"), new GenericHandler("1234"), "9876543210"));
		this.receiverAccount = new Account(new AccountHandler(new IdOffice("0001"), new GenericHandler("1234"), "9876543210"));
		this.quantity = (float) 20.5;
	}
	
	@Test
	public void transferMoneyWithBalanceTest() throws TransferException {
		this.senderAccount.setBalance(100);
		this.receiverAccount.setBalance(0);
		float beforeMoneyReceiver = this.receiverAccount.getBalance();
		float beforeMoneySender = this.senderAccount.getBalance();
		this.transfer = new Transfer(this.senderAccount, this.receiverAccount, this.quantity);
		this.transfer.transferMoney("Concepto");
		float afterMoneyReceiver = this.receiverAccount.getBalance();
		float afterMoneySender = this.senderAccount.getBalance();
		
		assertEquals(afterMoneyReceiver - beforeMoneyReceiver, this.quantity, 0.01);
		assertEquals(afterMoneyReceiver, this.quantity + beforeMoneyReceiver, 0.01);
		assertEquals(beforeMoneySender - afterMoneySender, this.quantity, 0.01);
		assertEquals(afterMoneySender, beforeMoneySender - this.quantity, 0.01);
	}

	@Test (expected = TransferException.class)
	public void transferMoneyWithOutBalanceTest()throws TransferException {
		this.senderAccount.setBalance(0);
		this.receiverAccount.setBalance(0);
		this.transfer = new Transfer(this.senderAccount, this.receiverAccount, this.quantity);
		this.transfer.transferMoney("Concepto");
	}
	
	@Test (expected = TransferException.class)
	public void transferMoneyEqualsAccountTest()throws TransferException {
		Account exAccount = new Account(new AccountHandler(new IdOffice("0001"), new GenericHandler("1234"), "9876543210"));
		this.transfer = new Transfer(exAccount, exAccount, this.quantity);
	}
}
