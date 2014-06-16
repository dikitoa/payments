package es.unileon.ulebank.web;

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
import es.unileon.ulebank.payments.handler.CardHandler;
import es.unileon.ulebank.service.ChangeLimit;
import es.unileon.ulebank.service.impl.SimpleCardManager;
import es.unileon.ulebank.utils.CardProperties;

public class ChangeCashLimitsFormControllerTest {

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
	public void testFormBackingObject() throws Exception{		
		ChangeCashLimitsFormController controller = new ChangeCashLimitsFormController();		
		controller.setCardManager(this.cardManager);	
		assertEquals(controller.formBackingObject(null).getDiaryLimit(),this.cardManager.getCard().getBuyLimitDiary(),0.01);
		assertEquals(controller.formBackingObject(null).getMonthlyLimit(),this.cardManager.getCard().getBuyLimitMonthly(),0.01);
	}
	
	@Test
	public void testOnSubmit() throws Exception{		
		ChangeCashLimitsFormController controller = new ChangeCashLimitsFormController();		
		controller.setCardManager(this.cardManager);	
		ChangeLimit limit = new ChangeLimit();
		limit.setDiaryLimit(150);
		limit.setMonthlyLimit(300);
		/*controller.onSubmit(limit, new BindingResult());
		assertEquals(150,this.productManager.getCard().getBuyLimitDiary(),0.01);
		assertEquals(300,this.productManager.getCard().getBuyLimitDiary(),0.01);*/
	}
	
	@Test
	public void testGetCardManager() {
		ChangeCashLimitsFormController controller = new ChangeCashLimitsFormController();		
		controller.setCardManager(this.cardManager);	
		assertEquals(this.cardManager, controller.getCardManager());
	}

}
