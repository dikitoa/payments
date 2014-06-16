package es.unileon.ulebank.web;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.bank.BankHandler;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.client.Person;
import es.unileon.ulebank.fees.FeeStrategy;
import es.unileon.ulebank.fees.LinearFee;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.payments.handler.CardHandler;
import es.unileon.ulebank.service.FeeChange;
import es.unileon.ulebank.service.SimpleCardManager;
import es.unileon.ulebank.utils.CardProperties;

public class ChangeFeesFormControllerTest {
    
	private SimpleCardManager cardManager;
    
    Card testCard;
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
		properties.setMinimumLimit(300.0);
		properties.setExpirationYear(3);
		this.bank = new Bank(new BankHandler("9876"));
		this.office = new Office(new GenericHandler("9876"), this.bank);
		handler = new CardHandler(new BankHandler("9876"), "06", "987654321");
		Client client = new Person(71449133, 'R');
		Account account = new Account(office, bank, accountNumber, client);
		FeeStrategy commissionEmission = new LinearFee(0, 26);
		FeeStrategy commissionMaintenance = new LinearFee(0, 0);
		FeeStrategy commissionRenovate = new LinearFee(0, 0);
		testCard = new CreditCard(handler, client, account);
		testCard.setBuyLimitMonthly(1500.0);
		testCard.setBuyLimitDiary(500.0);
		testCard.setCashLimitMonthly(1500.0);
		testCard.setCashLimitDiary(600.0);
		testCard.setCommissionEmission(commissionEmission);
		testCard.setCommissionMaintenance(commissionMaintenance);
		testCard.setCommissionRenovate(commissionRenovate);
		this.cardManager.setCard(this.testCard);
	}
    
	@Test
	public void testFormBackingObject() throws Exception{		
		ChangeFeesFormController controllerFee = new ChangeFeesFormController();		
		controllerFee.setCardManager(this.cardManager);	
		assertEquals(controllerFee.formBackingObject(null).getFeeChange(),this.cardManager.getCard().getFee(),0.01);
	}
	
	@Test
	public void testOnSubmit() throws Exception{		
		ChangeFeesFormController controllerFee = new ChangeFeesFormController();		
		controllerFee.setCardManager(this.cardManager);	
		FeeChange fee= new FeeChange();
		fee.setFeeChange(40);
	}
	
	@Test
	public void testGetCardManager() {
		ChangeFeesFormController controllerFee = new ChangeFeesFormController();		
		controllerFee.setCardManager(this.cardManager);	
		assertEquals(this.cardManager, controllerFee.getCardManager());
	}
}
