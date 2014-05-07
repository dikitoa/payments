package es.unileon.ulebank.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.Office;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.AccountHandler;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.ClientNotFoundException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.OfficeHandler;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CardType;
import es.unileon.ulebank.transacionManager.TransactionManager;

public class NewCardCommandTest {
	private NewCardCommand test;
	private DNIHandler dni;
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
<<<<<<< HEAD
	private double limitDebit;
	private Bank bank;
    private TransactionManager manager;

    private String accountNumber = "0000000000";
=======
>>>>>>> refs/remotes/origin/master
	
	@Before
	public void setUp() {
		this.manager = new TransactionManager();
        this.bank = new Bank(manager, new GenericHandler("1234"));
		this.office = new Office(new GenericHandler("1234"), this.bank);
		this.dni = new DNIHandler("71557005A");
		Client client = new Client(dni, 20);
		this.office.addClient(client);
		this.accountHandler = new AccountHandler(new OfficeHandler("0001"), new GenericHandler("1234"), "9876543210");
		client.add(new Account(office, bank, accountNumber));
		this.cardTypeCredit = CardType.CREDIT;
		this.cardTypeDebit = CardType.DEBIT;
		this.buyLimitDiary = 400.0;
		this.buyLimitMonthly = 1000.0;
		this.cashLimitDiary = 400.0;
		this.cashLimitMonthly = 1000.0;
		this.commissionEmission = 25;
		this.commissionMaintenance = 0;
		this.commissionRenovate = 0;
	}
	
	@Test (expected = NullPointerException.class)
	public void testCommandNull() {
		test = null;
		test.getId();
	}
	
	@Test
	public void testCommandId() {
		this.test = new NewCardCommand(office, dni, accountHandler, cardTypeCredit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, commissionMaintenance, commissionRenovate);
		assertTrue(test.getId() != null);
	}
	
	@Test
	public void testCreateCreditCard() throws ClientNotFoundException {
		this.test = new NewCardCommand(office, dni, accountHandler, cardTypeCredit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, 
				commissionMaintenance, commissionRenovate);
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
	}
	
	@Test
	public void testCreateDebitCard() throws ClientNotFoundException {
		this.test = new NewCardCommand(office, dni, accountHandler, cardTypeDebit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, 
				commissionMaintenance, commissionRenovate);
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
	}
	
	@Test (expected = NullPointerException.class)
	public void testUndoNewCreditCardCommand() throws ClientNotFoundException {
		this.test = new NewCardCommand(office, dni, accountHandler, cardTypeCredit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, 
				commissionMaintenance, commissionRenovate);
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
	public void testUndoNewDebitCardCommand() throws ClientNotFoundException {
		this.test = new NewCardCommand(office, dni, accountHandler, cardTypeDebit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, 
				commissionMaintenance, commissionRenovate);
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
				commissionMaintenance, commissionRenovate);
		this.test.execute();
		this.test.undo();
		this.test.redo();
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testRedoNewDebitCardCommand() {
		this.test = new NewCardCommand(office, dni, accountHandler, cardTypeDebit, buyLimitDiary, 
				buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, 
				commissionMaintenance, commissionRenovate);
		this.test.execute();
		this.test.undo();
		this.test.redo();
	}
}
