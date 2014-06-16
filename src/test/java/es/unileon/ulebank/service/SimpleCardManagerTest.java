package es.unileon.ulebank.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.bank.BankHandler;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.client.Person;
import es.unileon.ulebank.domain.Cards;
import es.unileon.ulebank.fees.FeeStrategy;
import es.unileon.ulebank.fees.LinearFee;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.payments.exceptions.IncorrectLimitException;
import es.unileon.ulebank.payments.handler.CardHandler;
import es.unileon.ulebank.service.impl.SimpleCardManager;
import es.unileon.ulebank.utils.CardProperties;

public class SimpleCardManagerTest {

    private SimpleCardManager cardManager;
    
    Cards testCard;
	CardHandler handler;
	private Office office;
	private Bank bank;
    private String accountNumber = "0000000000";
    
    @Before
    public void setUp() throws Exception {
    	this.cardManager = new SimpleCardManager();
		CardProperties properties = new CardProperties();
		properties.setCvvSize(3);
		properties.setPinSize(4);
		properties.setMinimumLimit(200.0);
		properties.setExpirationYear(3);
		this.bank = new Bank(new BankHandler("1234"));
		this.office = new Office(new GenericHandler("1234"), this.bank);
		handler = new CardHandler(new BankHandler("1234"), "01", "123456789");
		Client client = new Person(71451559, 'N');
		Account account = new Account(office, bank, accountNumber, client);
		FeeStrategy commissionEmission = new LinearFee(0, 25);
		FeeStrategy commissionMaintenance = new LinearFee(0, 0);
		FeeStrategy commissionRenovate = new LinearFee(0, 0);
		testCard = new CreditCard(handler, client, account);
		testCard.setBuyLimitMonthly(1000.0);
		testCard.setBuyLimitDiary(400.0);
		testCard.setCashLimitMonthly(1000.0);
		testCard.setCashLimitDiary(400.0);
		testCard.setCommissionEmission(commissionEmission);
		testCard.setCommissionMaintenance(commissionMaintenance);
		testCard.setCommissionRenovate(commissionRenovate);
		this.cardManager.setCard(this.testCard);
	}

    @Test
    public void testGetProductsWithNoProducts() {
        cardManager = new SimpleCardManager();
        assertNull(cardManager.getCard());
    }

    @Test
    public void testGetCard() {
        assertNotNull(cardManager.getCard());        
        assertEquals(cardManager.getCard(), this.testCard);
    }


    @Test(expected = IncorrectLimitException.class)
    public void testChangeBuyLimitsWithIncorrectLimits() throws Exception {
    	this.cardManager.changeBuyLimits(300, 100);       
    }
    
    @Test
    public void testChangeBuyLimitsWithCorrectLimits() throws Exception {
    	this.cardManager.changeBuyLimits(300, 1000);
    	assertEquals(this.testCard.getBuyLimitDiary(),300,0.01);
    	assertEquals(this.testCard.getBuyLimitMonthly(),1000,0.01);
    }
    
    @Test(expected = IncorrectLimitException.class)
    public void testChangeBuyLimitsWithEqualstLimits() throws Exception {
    	this.cardManager.changeBuyLimits(1000, 1000);
    	assertEquals(this.testCard.getBuyLimitDiary(),1000,0.01);
    	assertEquals(this.testCard.getBuyLimitMonthly(),1000,0.01);
    }
    
    @Test(expected = IncorrectLimitException.class)
    public void testChangeCahsLimitsWithIncorrectLimits() throws Exception {
    	this.cardManager.changeCashLimits(300, 100);       
    }
    
    @Test
    public void testChangeCashLimitsWithCorrectLimits() throws Exception {
    	this.cardManager.changeCashLimits(300, 1000);
    	assertEquals(this.testCard.getCashLimitDiary(),300,0.01);
    	assertEquals(this.testCard.getCashLimitMonthly(),1000,0.01);
    }
    
    @Test(expected = IncorrectLimitException.class)
    public void testChangeCashLimitsWithEqualstLimits() throws Exception {
    	this.cardManager.changeBuyLimits(1000, 1000);
    	assertEquals(this.testCard.getBuyLimitDiary(),1000,0.01);
    	assertEquals(this.testCard.getBuyLimitMonthly(),1000,0.01);
    }
}