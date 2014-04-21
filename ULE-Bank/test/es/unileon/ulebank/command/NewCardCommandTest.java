package es.unileon.ulebank.command;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.payments.Account;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CardType;
import es.unileon.ulebank.payments.Client;
import es.unileon.ulebank.strategy.StrategyCommission;
import es.unileon.ulebank.strategy.StrategyCommissionCreditEmission;
import es.unileon.ulebank.strategy.StrategyCommissionCreditMaintenance;
import es.unileon.ulebank.strategy.StrategyCommissionCreditRenovate;

public class NewCardCommandTest {
	private NewCardCommand test;
	private Client client;
	private Account account;
	private CardType cardTypeCredit;
	private CardType cardTypeDebit;
	private double buyLimitDiary;
	private double buyLimitMonthly;
	private double cashLimitDiary;
	private double cashLimitMonthly;
	private StrategyCommission commissionEmission;
	private StrategyCommission commissionMaintenance;
	private StrategyCommission commissionRenovate;
	private double limitDebit;
	
	@Before
	public void setUp() {
		this.client = new Client();
		this.account = new Account();
		this.cardTypeCredit = CardType.CREDIT;
		this.cardTypeDebit = CardType.DEBIT;
		this.buyLimitDiary = 400.0;
		this.buyLimitMonthly = 1000.0;
		this.cashLimitDiary = 400.0;
		this.cashLimitMonthly = 1000.0;
		this.commissionEmission = new StrategyCommissionCreditEmission(client, null, 25);
		this.commissionMaintenance = new StrategyCommissionCreditMaintenance(client, null, 0);
		this.commissionRenovate = new StrategyCommissionCreditRenovate(client, null, 0);
		this.limitDebit = 3000.0;
	}
	
	@Test (expected = NullPointerException.class)
	public void testCommandNull() {
		test = null;
		test.getId();
	}
	
	@Test
	public void testCommandId() {
		this.test = new NewCardCommand(client, account, cardTypeCredit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, 
				commissionMaintenance, commissionRenovate, limitDebit);
		assertTrue(test.getId() != null);
	}
	
	@Test
	public void testCreateCreditCard() {
		this.test = new NewCardCommand(client, account, cardTypeCredit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, 
				commissionMaintenance, commissionRenovate, limitDebit);
		this.test.execute();
		CommandHandler id = (CommandHandler) test.getId();
		CardHandler cardHandler = (CardHandler) id.getId();
		Card card = account.searchCard(cardHandler);
		assertEquals(account, card.getAccount());
		assertEquals(cardTypeCredit, card.getCardType());
		assertEquals(buyLimitDiary, card.getBuyLimitDiary(), 0.0001);
		assertEquals(buyLimitMonthly, card.getBuyLimitMonthly(), 0.0001);
		assertEquals(cashLimitDiary, card.getCashLimitDiary(), 0.0001);
		assertEquals(cashLimitMonthly, card.getCashLimitMonthly(), 0.0001);
		assertEquals(commissionEmission, card.getCommissionEmission());
		assertEquals(commissionMaintenance, card.getCommissionMaintenance());
		assertEquals(commissionRenovate, card.getCommissionRenovate());
		assertEquals(limitDebit, card.getLimitDebit(), 0.0001);
	}
	
	@Test
	public void testCreateDebitCard() {
		this.test = new NewCardCommand(client, account, cardTypeDebit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, 
				commissionMaintenance, commissionRenovate, 0.0);
		this.test.execute();
		CommandHandler id = (CommandHandler) test.getId();
		CardHandler cardHandler = (CardHandler) id.getId();
		Card card = account.searchCard(cardHandler);
		assertEquals(account, card.getAccount());
		assertEquals(cardTypeDebit, card.getCardType());
		assertEquals(buyLimitDiary, card.getBuyLimitDiary(), 0.0001);
		assertEquals(buyLimitMonthly, card.getBuyLimitMonthly(), 0.0001);
		assertEquals(cashLimitDiary, card.getCashLimitDiary(), 0.0001);
		assertEquals(cashLimitMonthly, card.getCashLimitMonthly(), 0.0001);
		assertEquals(commissionEmission, card.getCommissionEmission());
		assertEquals(commissionMaintenance, card.getCommissionMaintenance());
		assertEquals(commissionRenovate, card.getCommissionRenovate());
		assertEquals(0.0, card.getLimitDebit(), 0.0001);
	}
	
	@Test
	public void testUndoNewCreditCardCommand() {
		this.test = new NewCardCommand(client, account, cardTypeCredit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, 
				commissionMaintenance, commissionRenovate, limitDebit);
		this.test.execute();
		CommandHandler id = (CommandHandler) test.getId();
		CardHandler cardHandler = (CardHandler) id.getId();
		assertEquals(1, this.account.getCardAmount());
		this.test.undo();
		assertEquals(0, this.account.getCardAmount());
		Card card = account.searchCard(cardHandler);
		assertTrue(card == null);
	}
	
	@Test
	public void testUndoNewDebitCardCommand() {
		this.test = new NewCardCommand(client, account, cardTypeDebit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, 
				commissionMaintenance, commissionRenovate, 0.0);
		this.test.execute();
		CommandHandler id = (CommandHandler) test.getId();
		CardHandler cardHandler = (CardHandler) id.getId();
		assertEquals(1, this.account.getCardAmount());
		this.test.undo();
		assertEquals(0, this.account.getCardAmount());
		Card card = account.searchCard(cardHandler);
		assertTrue(card == null);
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testRedoNewCreditCardCommand() {
		this.test = new NewCardCommand(client, account, cardTypeCredit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, 
				commissionMaintenance, commissionRenovate, limitDebit);
		this.test.execute();
		this.test.undo();
		this.test.redo();
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testRedoNewDebitCardCommand() {
		this.test = new NewCardCommand(client, account, cardTypeDebit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, 
				commissionMaintenance, commissionRenovate, 0.0);
		this.test.execute();
		this.test.undo();
		this.test.redo();
	}
}
