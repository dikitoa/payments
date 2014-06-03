package es.unileon.ulebank.repository;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.payments.Card;

public class JPACardDaoTests {
	private ApplicationContext context;
	private CardDao cardDao;
	
	@Before
	public void setUp() throws Exception {
		this.context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
		this.cardDao = (CardDao) context.getBean("cardDao");
	}

	@Test
	public void testGetCardList() {
		List<Card> cards = cardDao.getCardList();
		assertEquals(cards.size(), 2, 0);
	}
	
	@Test
	public void testAddCard() throws IOException, IncorrectLimitException {
		List<Card> cards = cardDao.getCardList();
		
		Card card = cards.get(0);
		double buyLimitMonthly = card.getBuyLimitMonthly();
		card.setBuyLimitMonthly(2000.0);
		cardDao.addCard(card);
		
		List<Card> updatedCards = cardDao.getCardList();
		Card card2 = updatedCards.get(0);
		assertEquals(card2.getBuyLimitMonthly(), 2000.0, 0);
		
		card2.setBuyLimitMonthly(buyLimitMonthly);
		cardDao.addCard(card2);
	}
}
