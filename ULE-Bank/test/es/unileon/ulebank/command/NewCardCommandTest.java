package es.unileon.ulebank.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.handler.AccountHandler;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.IdDNI;
import es.unileon.ulebank.handler.IdOffice;
import es.unileon.ulebank.payments.Account;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CardType;
import es.unileon.ulebank.payments.Client;
import es.unileon.ulebank.payments.Office;

public class NewCardCommandTest {
	private NewCardCommand test;
	private IdDNI dni;
	private Office office;
	private AccountHandler accountHandler;
	private CardType cardTypeCredit;
	private CardType cardTypeDebit;
	private double buyLimitDiary;
	private double buyLimitMonthly;
	private double cashLimitDiary;
	private double cashLimitMonthly;
	private float commissionEmission;
	private float commissionMaintenance;
	private float commissionRenovate;
	private double limitDebit;
	
	@Before
	public void setUp() {
		this.office = new Office();
		this.dni = new IdDNI("71557005A");
		Client client = new Client(dni, 20);
		this.office.addClient(client);
		this.accountHandler = new AccountHandler(new IdOffice("0001"), new GenericHandler("1234"), "9876543210");
		client.addAccount(new Account(accountHandler));
		this.cardTypeCredit = CardType.CREDIT;
		this.cardTypeDebit = CardType.DEBIT;
		this.buyLimitDiary = 400.0;
		this.buyLimitMonthly = 1000.0;
		this.cashLimitDiary = 400.0;
		this.cashLimitMonthly = 1000.0;
		this.commissionEmission = 25;
		this.commissionMaintenance = 0;
		this.commissionRenovate = 0;
		this.limitDebit = 3000.0;
	}
	
	@Test (expected = NullPointerException.class)
	public void testCommandNull() {
		test = null;
		test.getId();
	}
	
	@Test
	public void testCommandId() {
		this.test = new NewCardCommand(office, dni, accountHandler, cardTypeCredit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, commissionMaintenance, commissionRenovate, limitDebit);
		assertTrue(test.getId() != null);
	}
	
	@Test
	public void testCreateCreditCard() {
		this.test = new NewCardCommand(office, dni, accountHandler, cardTypeCredit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, 
				commissionMaintenance, commissionRenovate, limitDebit);
		this.test.execute();
		CommandHandler id = (CommandHandler) test.getId();
		CardHandler cardHandler = (CardHandler) id.getId();
		Card card = office.searchClient(dni).searchAccount(accountHandler).searchCard(cardHandler);
		assertEquals(cardTypeCredit, card.getCardType());
		assertEquals(buyLimitDiary, card.getBuyLimitDiary(), 0.0001);
		assertEquals(buyLimitMonthly, card.getBuyLimitMonthly(), 0.0001);
		assertEquals(cashLimitDiary, card.getCashLimitDiary(), 0.0001);
		assertEquals(cashLimitMonthly, card.getCashLimitMonthly(), 0.0001);
		assertEquals(commissionEmission, card.getCommissionEmission(), 0.0001);
		assertEquals(commissionMaintenance, card.getCommissionMaintenance(), 0.0001);
		assertEquals(commissionRenovate, card.getCommissionRenovate(), 0.0001);
		assertEquals(limitDebit, card.getLimitDebit(), 0.0001);
	}
	
	@Test
	public void testCreateDebitCard() {
		this.test = new NewCardCommand(office, dni, accountHandler, cardTypeDebit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, 
				commissionMaintenance, commissionRenovate, 0.0);
		this.test.execute();
		CommandHandler id = (CommandHandler) test.getId();
		CardHandler cardHandler = (CardHandler) id.getId();
		Card card = office.searchClient(dni).searchAccount(accountHandler).searchCard(cardHandler);
		assertEquals(cardTypeDebit, card.getCardType());
		assertEquals(buyLimitDiary, card.getBuyLimitDiary(), 0.0001);
		assertEquals(buyLimitMonthly, card.getBuyLimitMonthly(), 0.0001);
		assertEquals(cashLimitDiary, card.getCashLimitDiary(), 0.0001);
		assertEquals(cashLimitMonthly, card.getCashLimitMonthly(), 0.0001);
		assertEquals(commissionEmission, card.getCommissionEmission(), 0.0001);
		assertEquals(commissionMaintenance, card.getCommissionMaintenance(), 0.0001);
		assertEquals(commissionRenovate, card.getCommissionRenovate(), 0.0001);
		assertEquals(0.0, card.getLimitDebit(), 0.0001);
	}
	
	@Test (expected = NullPointerException.class)
	public void testUndoNewCreditCardCommand() {
		this.test = new NewCardCommand(office, dni, accountHandler, cardTypeCredit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, 
				commissionMaintenance, commissionRenovate, limitDebit);
		this.test.execute();
		CommandHandler id = (CommandHandler) test.getId();
		CardHandler cardHandler = (CardHandler) id.getId();
		assertEquals(1, office.searchClient(dni).searchAccount(accountHandler).getCardAmount());
		this.test.undo();
		assertEquals(0, office.searchClient(dni).searchAccount(accountHandler).getCardAmount());
		//Como no hay tarjetas se espera una excepcion NullPointerException
		office.searchClient(dni).searchAccount(accountHandler).searchCard(cardHandler);
	}
	
	@Test (expected = NullPointerException.class)
	public void testUndoNewDebitCardCommand() {
		this.test = new NewCardCommand(office, dni, accountHandler, cardTypeDebit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, 
				commissionMaintenance, commissionRenovate, 0.0);
		this.test.execute();
		CommandHandler id = (CommandHandler) test.getId();
		CardHandler cardHandler = (CardHandler) id.getId();
		assertEquals(1, office.searchClient(dni).searchAccount(accountHandler).getCardAmount());
		this.test.undo();
		assertEquals(0, office.searchClient(dni).searchAccount(accountHandler).getCardAmount());
		office.searchClient(dni).searchAccount(accountHandler).searchCard(cardHandler);
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testRedoNewCreditCardCommand() {
		this.test = new NewCardCommand(office, dni, accountHandler, cardTypeCredit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, 
				commissionMaintenance, commissionRenovate, limitDebit);
		this.test.execute();
		this.test.undo();
		this.test.redo();
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testRedoNewDebitCardCommand() {
		this.test = new NewCardCommand(office, dni, accountHandler, cardTypeDebit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, 
				commissionMaintenance, commissionRenovate, 0.0);
		this.test.execute();
		this.test.undo();
		this.test.redo();
	}
}
