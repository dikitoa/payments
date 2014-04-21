package es.unileon.ulebank.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.payments.Account;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Client;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.strategy.StrategyCommission;
import es.unileon.ulebank.strategy.StrategyCommissionCreditEmission;
import es.unileon.ulebank.strategy.StrategyCommissionCreditMaintenance;
import es.unileon.ulebank.strategy.StrategyCommissionCreditRenovate;

public class ModifyCashLimitCommandTest {
	private Card testCard;
	private CardHandler handler;
	private Client client;
	private Account account;
	private ModifyCashLimitCommand test;
	private ModifyCashLimitCommand test2;
	
	@Before
	public void setUp() {
		handler = new CardHandler();
		client = new Client();
		account = new Account();
		StrategyCommission commissionEmission = new StrategyCommissionCreditEmission(client, testCard, 25);
		StrategyCommission commissionMaintenance = new StrategyCommissionCreditMaintenance(client, testCard, 0);
		StrategyCommission commissionRenovate = new StrategyCommissionCreditRenovate(client, testCard, 0);
		testCard = new CreditCard(handler, client, account, 400.0, 1000.0, 400.0, 1000.0, commissionEmission, commissionMaintenance, commissionRenovate, 3000.0);
		account.addCard(testCard);
	}
	
	@Test
	public void testCommandNotNull() {
		test = new ModifyCashLimitCommand(handler, account, 100.0, "diary");
		assertTrue(test != null);
	}
	
	@Test
	public void testCommandId() {
		test = new ModifyCashLimitCommand(this.handler, this.account, 200.0, "diary");
		assertTrue(test.getId().compareTo(handler) == 0);
	}
	
	@Test
	public void testLimitDiaryModified() {
		test = new ModifyCashLimitCommand(handler, account, 200.0, "Diary");
		assertEquals(400.0, this.testCard.getCashLimitDiary(), 0.0001);
		test.execute();
		assertEquals(200.0, testCard.getCashLimitDiary(), 0.0001);
	}
	
	@Test
	public void testLimitDiaryNotModified() {
		test = new ModifyCashLimitCommand(handler, account, 1100.0, "Diary");
		assertEquals(400.0, this.testCard.getCashLimitDiary(), 0.0001);
		test.execute();
		//The limit wont be changed and will be 400 (default)
		assertEquals(400.0, this.testCard.getCashLimitDiary(), 0.0001);
	}
	
	@Test
	public void testLimitMonthlyModified() {
		test = new ModifyCashLimitCommand(handler, account, 2000.0, "Monthly");
		assertEquals(1000.0, this.testCard.getCashLimitMonthly(), 0.0001);
		test.execute();
		assertEquals(2000.0, this.testCard.getCashLimitMonthly(), 0.0001);
	}
	
	@Test
	public void testLimitMonthlyNotModified() {
		test = new ModifyCashLimitCommand(handler, account, 300.0, "Monthly");
		assertEquals(1000.0, this.testCard.getCashLimitMonthly(), 0.0001);
		test.execute();
		assertEquals(1000.0, this.testCard.getCashLimitMonthly(), 0.0001);
	}
	
	@Test
	public void testTypeOK() {
		test = new ModifyCashLimitCommand(handler, account, 300.0, "DIARY");
		test.execute();
		assertTrue(this.testCard != null);
		assertEquals(300.0, this.testCard.getCashLimitDiary(), 0.0001);
	}
	
	@Test
	public void testTypeNotOK() {
		test = new ModifyCashLimitCommand(handler, account, 300.0, "");
		test.execute();
		//Any changes in both limits
		assertEquals(400, testCard.getCashLimitDiary(), 0.0001);
		assertEquals(1000, testCard.getCashLimitMonthly(), 0.0001);
		test2 = new ModifyCashLimitCommand(handler, account, 500.0, "123");
		test2.execute();
		assertEquals(400.0, testCard.getCashLimitDiary(), 0.0001);
		assertEquals(1000.0, testCard.getCashLimitMonthly(), 0.0001);
	}
	
	@Test
	public void undoTest() {
		test = new ModifyCashLimitCommand(this.handler, this.account, 300.0, "diary");
		test.execute();
		assertEquals(300.0, testCard.getCashLimitDiary(), 0.0001);
		test.undo();
		assertEquals(400.0, testCard.getCashLimitDiary(), 0.0001);
	}
	
	@Test
	public void redoTest() {
		test = new ModifyCashLimitCommand(this.handler, this.account, 300.0, "diary");
		test.execute();
		assertEquals(300.0, testCard.getCashLimitDiary(), 0.0001);
		test.undo();
		assertEquals(400.0, testCard.getCashLimitDiary(), 0.0001);
		test.redo();
		assertEquals(300.0, testCard.getCashLimitDiary(), 0.0001);
	}
}
