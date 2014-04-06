package es.unileon.ulebank.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CreditCard;

public class ModifyCashLimitCommandTest {
	private Card card;
	private ModifyCashLimitCommand test;
	private ModifyCashLimitCommand test2;
	
	@Before
	public void setUp() {
		this.card = new CreditCard();
		this.card.create();
	}
	
	@Test
	public void testCommandNotNull() {
		test = new ModifyCashLimitCommand(card, 100, "diary");
		assertTrue(test != null);
	}
	
	@Test
	public void testLimitDiaryModified() {
		test = new ModifyCashLimitCommand(card, 200, "Diary");
		assertEquals(400, this.card.getCashLimitDiary());
		test.execute();
		assertEquals(200, card.getCashLimitDiary());
	}
	
	@Test
	public void testLimitDiaryNotModified() {
		test = new ModifyCashLimitCommand(card, 1100, "Diary");
		assertEquals(400, this.card.getCashLimitDiary());
		test.execute();
		//The limit wont be changed and will be 400 (default)
		assertEquals(400, this.card.getCashLimitDiary());
	}
	
	@Test
	public void testLimitMonthlyModified() {
		test = new ModifyCashLimitCommand(card, 2000, "Monthly");
		assertEquals(1000, this.card.getCashLimitMonthly());
		test.execute();
		assertEquals(2000, this.card.getCashLimitMonthly());
	}
	
	@Test
	public void testLimitMonthlyNotModified() {
		test = new ModifyCashLimitCommand(card, 300, "Monthly");
		assertEquals(1000, this.card.getCashLimitMonthly());
		test.execute();
		assertEquals(1000, this.card.getCashLimitMonthly());
	}
	
	@Test
	public void testTypeOK() {
		test = new ModifyCashLimitCommand(card, 300, "DIARY");
		test.execute();
		assertTrue(this.card != null);
		assertEquals(300, this.card.getCashLimitDiary());
	}
	
	@Test
	public void testTypeNotOK() {
		test = new ModifyCashLimitCommand(card, 300, "");
		test.execute();
		//Any changes in both limits
		assertEquals(400, card.getCashLimitDiary());
		assertEquals(1000, card.getCashLimitMonthly());
		test2 = new ModifyCashLimitCommand(card, 500, "123");
		test2.execute();
		assertEquals(400, card.getCashLimitDiary());
		assertEquals(1000, card.getCashLimitMonthly());
	}
}
