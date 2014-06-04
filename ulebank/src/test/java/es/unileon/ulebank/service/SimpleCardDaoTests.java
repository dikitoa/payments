package es.unileon.ulebank.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.bank.BankHandler;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.fees.InvalidFeeException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.OfficeHandler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.payments.DebitCard;
import es.unileon.ulebank.repository.CardDao;
import es.unileon.ulebank.repository.InMemoryCardDao;

public class SimpleCardDaoTests {
	
	private SimpleCardManager cardManager;
	private List<Card> cards;
	private Office office;
	private Bank bank;
	private Client client;
	private Account account;
	private Card card1;
	private Card card2;
	
	@Before
	public void setUp() throws Exception {
		this.cardManager = new SimpleCardManager();
		this.cards = new ArrayList<Card>();
		
		this.bank = new Bank(new BankHandler("1234"));
		this.office = new Office(new OfficeHandler("0001"), bank);
		this.client = new Client(new DNIHandler("71557005A"));
		this.client.setName("Paco");
		this.client.setSurname("Ramirez Pinto");
		this.account = new Account(office, bank, "1234567890", client);
		this.card1 = new CreditCard(new CardHandler("987654321111110"), client, account, 300.0, 900.0, 400.0, 1000.0, 10.0, 25.0, 0);
		this.card2 = new DebitCard(new CardHandler("228383942749890"), client, account, 600.0, 2000.0, 500.0, 1000.0, 0.0, 30.0, 40.0);
		office.addClient(client);
		client.add(account);
		
		cards.add(card1);
		cards.add(card2);
		CardDao cardDao = new InMemoryCardDao(cards);
		cardManager.setCardDao(cardDao);
	}

	@Test
	public void testGetCardsWithNoCards() {
		cardManager = new SimpleCardManager();
		cardManager.setCardDao(new InMemoryCardDao(null));
		assertNull(cardManager.getCards());
	}
	
	@Test
	public void testGetCards() {
		List<Card> cards = cardManager.getCards();
		assertNotNull(cards);
		assertEquals(2, cardManager.getCards().size());
		
		Card card = cards.get(0);
		assertEquals("9876 5432 1111 1100", card.getId());
		assertEquals(300.0, card.getBuyLimitDiary(), 0.0001);
		assertEquals(900.0, card.getBuyLimitMonthly(), 0.0001);
		assertEquals(400.0, card.getCashLimitDiary(), 0.0001);
		assertEquals(1000.0, card.getCashLimitMonthly(), 0.0001);
		assertEquals("CREDIT", card.getCardType());
		
		card = cards.get(1);
		System.out.println(card.getId());
		assertEquals("2283 8394 2749 8907", card.getId());
		assertEquals(600.0, card.getBuyLimitDiary(), 0.0001);
		assertEquals(2000.0, card.getBuyLimitMonthly(), 0.0001);
		assertEquals(500.0, card.getCashLimitDiary(), 0.0001);
		assertEquals(1000.0, card.getCashLimitMonthly(), 0.0001);
		assertEquals("DEBIT", card.getCardType());
	}
	
	@Test
	public void testSaveCardWithEmptyList() {
		cardManager = new SimpleCardManager();
		cardManager.setCardDao(new InMemoryCardDao(new ArrayList<Card>()));
		assertEquals(0, cardManager.getCards().size());
	}
	
	@Test
	public void testSaveCardOk() throws CommissionException, InvalidFeeException {
		cardManager = new SimpleCardManager();
		cardManager.setCardDao(new InMemoryCardDao(this.cards));
		
		Card card = new CreditCard(new CardHandler("987654321111110"), client, account, 300.0, 900.0, 400.0, 1000.0, 10.0, 25.0, 0);
		cardManager.saveNewCard(card);
		assertEquals(3, cardManager.getCards().size());
		
		card = cardManager.getCards().get(2);
		assertEquals("9876 5432 1111 1100", card.getId());
		assertEquals(300.0, card.getBuyLimitDiary(), 0.0001);
		assertEquals(900.0, card.getBuyLimitMonthly(), 0.0001);
		assertEquals(400.0, card.getCashLimitDiary(), 0.0001);
		assertEquals(1000.0, card.getCashLimitMonthly(), 0.0001);
		assertEquals("CREDIT", card.getCardType());
	}
	
    @Test(expected = IncorrectLimitException.class)
    public void testChangeBuyLimitsWithIncorrectLimits() throws Exception {
    	cardManager.changeBuyLimits(200, 100);
    }
    
    @Test(expected = IncorrectLimitException.class)
    public void testChangeBuyLimitsWithEqualsLimits() throws Exception {
    	cardManager.changeBuyLimits(3000, 3000);
    }
    
    @Test
    public void testChangeBuyLimitsWithCorrectLimits() throws Exception {
    	cardManager.changeBuyLimits(200, 1000);
    	assertEquals(cardManager.getCards().get(0).getBuyLimitDiary(),200,0.01);
    	assertEquals(cardManager.getCards().get(0).getBuyLimitMonthly(),1000,0.01);
    }
    
    @Test(expected = IncorrectLimitException.class)
    public void testChangeCashLimitsWithIncorrectLimits() throws Exception {
    	cardManager.changeCashLimits(200, 100);
    }
    
    
    @Test
    public void testChangeCashLimitsWithCorrectLimits() throws Exception {
    	cardManager.changeCashLimits(200, 1200);
    	assertEquals(cardManager.getCards().get(0).getCashLimitDiary(),200,0.01);
    	assertEquals(cardManager.getCards().get(0).getCashLimitMonthly(),1200,0.01);
    }
}
