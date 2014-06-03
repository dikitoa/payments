package es.unileon.ulebank.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.fees.InvalidFeeException;
import es.unileon.ulebank.handler.OfficeHandler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.payments.DebitCard;
import es.unileon.ulebank.repository.CardDao;
import es.unileon.ulebank.repository.InMemoryCardDao;
import es.unileon.ulebank.transactionManager.TransactionManager;

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
		
		TransactionManager manager = new TransactionManager();
		this.bank = new Bank(manager, "1234");
		this.office = new Office(new OfficeHandler("0001"), bank);
		this.client = new Client("71557005A");
		this.client.setName("Paco");
		this.client.setSurname("Ramirez Pinto");
		this.account = new Account("123400001234567890");
		this.card1 = new CreditCard("987654321111110", client, account, 300.0, 900.0, 400.0, 1000.0, 10.0, 25.0, 0);
		this.card2 = new DebitCard("228383942749890", client, account, 600.0, 2000.0, 500.0, 1000.0, 0.0, 30.0, 40.0);
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
		assertEquals("987654321111110", card.getId());
		assertEquals(300.0, card.getBuyLimitDiary(), 0.0001);
		assertEquals(900.0, card.getBuyLimitMonthly(), 0.0001);
		assertEquals(400.0, card.getCashLimitDiary(), 0.0001);
		assertEquals(1000.0, card.getCashLimitMonthly(), 0.0001);
		assertEquals("CREDIT", card.getCardType());
		
		card = cards.get(1);
		assertEquals("228383942749890", card.getId());
		assertEquals(600.0, card.getBuyLimitDiary(), 0.0001);
		assertEquals(2000.0, card.getBuyLimitMonthly(), 0.0001);
		assertEquals(500.0, card.getCashLimitDiary(), 0.0001);
		assertEquals(1000.0, card.getCashLimitMonthly(), 0.0001);
		assertEquals("DEBIT", card.getCardType());
	}
	
	@Test
	public void testSaveCardWithNullList() {
		try {
			cardManager = new SimpleCardManager();
			cardManager.setCardDao(new InMemoryCardDao(null));
			cardManager.saveNewCard(card1);
		} catch (NullPointerException ex) {
			fail("Card list is null");
		}
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
		
		Card card = new CreditCard("987654321111110", client, account, 300.0, 900.0, 400.0, 1000.0, 10.0, 25.0, 0);
		cardManager.saveNewCard(card);
		assertEquals(3, cardManager.getCards().size());
		
		card = cardManager.getCards().get(2);
		assertEquals("987654321111110", card.getId());
		assertEquals(300.0, card.getBuyLimitDiary(), 0.0001);
		assertEquals(900.0, card.getBuyLimitMonthly(), 0.0001);
		assertEquals(400.0, card.getCashLimitDiary(), 0.0001);
		assertEquals(1000.0, card.getCashLimitMonthly(), 0.0001);
		assertEquals("CREDIT", card.getCardType());
	}
}
