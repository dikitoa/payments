package es.unileon.ulebank.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CreditCard;

public class ModifyBuyLimitCommandTest {
	private Card card;
	private ModifyBuyLimitCommand test;
	private ModifyBuyLimitCommand test2;
	
	@Before
	public void setUp() {
		this.card = new CreditCard();
		this.card.create();
	}
	
	@Test
	public void testCommandNotNull() {
		test = new ModifyBuyLimitCommand(card, 100, "diary");
		assertTrue(test != null);
	}
	
	@Test
	public void testLimitDiaryModified() {
		test = new ModifyBuyLimitCommand(card, 200, "Diary");
		assertEquals(400, this.card.getBuyLimitDiary());
		test.execute();
		assertEquals(200, card.getBuyLimitDiary());
	}
	
	@Test
	public void testLimitDiaryNotModified() {
		test = new ModifyBuyLimitCommand(card, 1100, "Diary");
		assertEquals(400, this.card.getBuyLimitDiary());
		test.execute();
		//The limit wont be changed and will be 400 (default)
		assertEquals(400, this.card.getBuyLimitDiary());
	}
	
	@Test
	public void testLimitMonthlyModified() {
		test = new ModifyBuyLimitCommand(card, 2000, "Monthly");
		assertEquals(1000, this.card.getBuyLimitMonthly());
		test.execute();
		assertEquals(2000, this.card.getBuyLimitMonthly());
	}
	
	@Test
	public void testLimitMonthlyNotModified() {
		test = new ModifyBuyLimitCommand(card, 300, "Monthly");
		assertEquals(1000, this.card.getBuyLimitMonthly());
		test.execute();
		assertEquals(1000, this.card.getBuyLimitMonthly());
	}
	
	@Test
	public void testTypeOK() {
		test = new ModifyBuyLimitCommand(card, 300, "DIARY");
		test.execute();
		assertTrue(this.card != null);
		assertEquals(300, this.card.getBuyLimitDiary());
	}
	
	@Test
	public void testTypeNotOK() {
		test = new ModifyBuyLimitCommand(card, 300, "");
		test.execute();
		//Any changes in both limits
		assertEquals(400, card.getBuyLimitDiary());
		assertEquals(1000, card.getBuyLimitMonthly());
		test2 = new ModifyBuyLimitCommand(card, 500, "123");
		test2.execute();
		assertEquals(400, card.getBuyLimitDiary());
		assertEquals(1000, card.getBuyLimitMonthly());
	}
}
