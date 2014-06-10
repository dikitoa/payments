package es.unileon.ulebank.payments.web;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.bank.BankHandler;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.controller.ChangeBuyLimitsFormController;
import es.unileon.ulebank.controller.ChangeCashLimitsFormController;
import es.unileon.ulebank.fees.FeeStrategy;
import es.unileon.ulebank.fees.LinearFee;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.service.ChangeLimit;
import es.unileon.ulebank.service.SimpleCardManager;

public class ChangeCashLimitsFormControllerTest {

	private SimpleCardManager productManager;
	private ChangeCashLimitsFormController controller;
	private Card testCard;
	private CardHandler handler;
	private Office office;
	private Bank bank;
	private String accountNumber = "0000000000";

	@Before
	public void setUp() throws Exception {
		productManager = new SimpleCardManager();
		this.bank = new Bank(new BankHandler("1234"));
		this.office = new Office(new GenericHandler("1234"), this.bank);
		handler = new CardHandler(new BankHandler("1234"), "01", "123456789");
		Client client = new Client(new DNIHandler("71451559N"));
		Account account = new Account(office, bank, "3456789213", client);
		FeeStrategy commissionEmission = new LinearFee(0, 25);
		FeeStrategy commissionMaintenance = new LinearFee(0, 0);
		FeeStrategy commissionRenovate = new LinearFee(0, 0);
		testCard = new CreditCard(handler, client, account, 400.0, 1000.0, 400.0, 1000.0, commissionEmission.getFee(0), commissionMaintenance.getFee(0), commissionRenovate.getFee(0));
		this.productManager.setCard(this.testCard);
	}

	@Test
	public void controllerNotNull() throws Exception{
		controller = new ChangeCashLimitsFormController();
		assertNotNull(controller);
	}

	@Test
	public void controllerNull() throws Exception{
		assertNull(controller);
	}

	@Test
	public void testFormBackingObject() throws Exception{		
		controller = new ChangeCashLimitsFormController();		
		controller.setProductManager(this.productManager);	
		//assertEquals(controller.formBackingObject(null).getDiaryLimit(),this.productManager.getLastCard().getBuyLimitDiary(),0.01);
		//assertEquals(controller.formBackingObject(null).getMonthlyLimit(),this.productManager.getLastCard().getBuyLimitMonthly(),0.01);
	}

	@Test
	public void testOnSubmit() throws Exception{		
		controller = new ChangeCashLimitsFormController();		
		controller.setProductManager(this.productManager);	
		ChangeLimit limit = new ChangeLimit();
		limit.setDiaryLimit(150);
		limit.setMonthlyLimit(300);
		/*controller.onSubmit(limit, new BindingResult());
		assertEquals(150,this.productManager.getCard().getBuyLimitDiary(),0.01);
		assertEquals(300,this.productManager.getCard().getBuyLimitDiary(),0.01);*/
	}

}
