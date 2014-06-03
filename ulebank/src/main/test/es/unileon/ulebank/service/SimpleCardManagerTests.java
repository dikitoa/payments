package es.unileon.ulebank.service;



import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.bank.BankHandler;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.fees.FeeStrategy;
import es.unileon.ulebank.fees.LinearFee;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.service.SimpleCardManager;
import es.unileon.ulebank.transactionManager.TransactionManager;


public class SimpleCardManagerTests {

    private SimpleCardManager productManager;
    
    Card testCard;
	CardHandler handler;
	private Office office;
	private Bank bank;
	private TransactionManager manager;
    private String accountNumber = "0000000000";
    
    @Before
    public void setUp() throws Exception {
        productManager = new SimpleCardManager();
        this.manager = new TransactionManager();
        this.bank = new Bank(manager, "1234");
        this.office = new Office(new GenericHandler("1234"), this.bank);
		handler = new CardHandler(new BankHandler("1234"), "01", "123456789");
		Client client = new Client("71451559N");
		Account account = new Account("123401123456789");
		FeeStrategy commissionEmission = new LinearFee(0, 25);
		FeeStrategy commissionMaintenance = new LinearFee(0, 0);
		FeeStrategy commissionRenovate = new LinearFee(0, 0);
		testCard = new CreditCard(handler.toString(), client, account, 400.0, 1000.0, 400.0, 1000.0, commissionEmission.getFee(0), commissionMaintenance.getFee(0), commissionRenovate.getFee(0));
		this.productManager.setCard(this.testCard);
	}

    @Test
    public void testGetProductsWithNoProducts() {
        productManager = new SimpleCardManager();
        assertNull(productManager.getLastCard());
    }

    @Test
    public void testGetCard() {
        assertNotNull(productManager.getLastCard());        
        assertEquals(productManager.getLastCard(), this.testCard);
    }


    @Test(expected = IncorrectLimitException.class)
    public void testChangeBuyLimitsWithIncorrectLimits() throws Exception {
    	this.productManager.changeBuyLimits(300, 100);       
    }
    
    @Test
    public void testChangeBuyLimitsWithCorrectLimits() throws Exception {
    	this.productManager.changeBuyLimits(300, 1000);
    	assertEquals(this.testCard.getBuyLimitDiary(),300,0.01);
    	assertEquals(this.testCard.getBuyLimitMonthly(),1000,0.01);
    }
    
    @Test(expected = IncorrectLimitException.class)
    public void testChangeBuyLimitsWithEqualstLimits() throws Exception {
    	this.productManager.changeBuyLimits(1000, 1000);
    	assertEquals(this.testCard.getBuyLimitDiary(),1000,0.01);
    	assertEquals(this.testCard.getBuyLimitMonthly(),1000,0.01);
    }
    
    
    @Test(expected = IncorrectLimitException.class)
    public void testChangeCahsLimitsWithIncorrectLimits() throws Exception {
    	this.productManager.changeCashLimits(300, 100);       
    }
    
    @Test
    public void testChangeCashLimitsWithCorrectLimits() throws Exception {
    	this.productManager.changeCashLimits(300, 1000);
    	assertEquals(this.testCard.getCashLimitDiary(),300,0.01);
    	assertEquals(this.testCard.getCashLimitMonthly(),1000,0.01);
    }
    
    @Test(expected = IncorrectLimitException.class)
    public void testChangeCashLimitsWithEqualstLimits() throws Exception {
    	this.productManager.changeBuyLimits(1000, 1000);
    	assertEquals(this.testCard.getBuyLimitDiary(),1000,0.01);
    	assertEquals(this.testCard.getBuyLimitMonthly(),1000,0.01);
    }
}