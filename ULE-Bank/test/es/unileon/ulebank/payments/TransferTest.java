package es.unileon.ulebank.payments;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.exceptions.TransferException;
import es.unileon.ulebank.payments.Transfer;

/**
 * Test about Transfer Class
 * @author Rober dCR
 * @date 02/04/2014
 */
public class TransferTest {

	//TODO Implementar cuando esté conectada con Account
	public Account senderAccount;
	public Account receiverAccount;
	public float quantity;
	Transfer transfer;
	
	@Before
	public void setUp(){	
		this.senderAccount = new Account();
		this.receiverAccount = new Account();
		this.quantity = (float) 20.5;
	}
	
	@Test
	public void test() throws TransferException {
		float beforeMoney = this.receiverAccount.getBalance();
		this.transfer = new Transfer(this.senderAccount, this.receiverAccount, this.quantity);
		float afterMoney = this.receiverAccount.getBalance();
		assertEquals(beforeMoney - afterMoney, this.quantity, 0.001);
	}

}
