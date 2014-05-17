package es.unileon.ulebank.handler;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.bank.BankHandler;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.exceptions.MalformedHandlerException;
import es.unileon.ulebank.exceptions.PaymentHandlerException;
import es.unileon.ulebank.fees.FeeStrategy;
import es.unileon.ulebank.fees.InvalidFeeException;
import es.unileon.ulebank.fees.LinearFee;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.transactionManager.TransactionManager;

public class PaymentHandlerTests {
	
	CreditCard testCard;
	CardHandler cardHandler;
	private Office office;
	private Bank bank;
	private TransactionManager manager;
    private String accountNumber = "0000000000";
	private PaymentHandler handler;
	private Date paymentDate;
	
	@Before
	public void setUp() throws PaymentHandlerException, InvalidFeeException, CommissionException{
		this.manager = new TransactionManager();
        this.bank = new Bank(manager, new GenericHandler("1234"));
        this.office = new Office(new GenericHandler("1234"), this.bank);
		cardHandler = new CardHandler(new BankHandler("1234"), "01", "123456789");
		Client client = new Client(new DNIHandler("71451559N"), 27);
		Account account = new Account(office, bank, accountNumber);
		FeeStrategy commissionEmission = new LinearFee(0, 25);
		FeeStrategy commissionMaintenance = new LinearFee(0, 0);
		FeeStrategy commissionRenovate = new LinearFee(0, 0);
		testCard = new CreditCard(cardHandler, client, account, 400.0, 1000.0, 400.0, 1000.0, commissionEmission.getFee(0), commissionMaintenance.getFee(0), commissionRenovate.getFee(0));
		this.paymentDate = new Date();
		this.handler = new PaymentHandler(this.testCard.getCardNumber(), this.paymentDate);
	}

	@Test
	public void correctHandlerTest() {
		assertTrue(this.handler != null);
		assertEquals(this.handler.toString().length(),15);
	}
	
	@Test 
	public void compareHandlerTest(){
		assertFalse(this.handler.compareTo(cardHandler)==0);
		assertTrue(this.handler.compareTo(handler)==0);
	}

	@Test (expected = MalformedHandlerException.class)
	public void incorrectHandlerTest() throws PaymentHandlerException{
		Handler card = new CardHandler(new BankHandler("1234"), "01", "1234567890");
		PaymentHandler test = new PaymentHandler(card ,new Date());
	}
}
