package es.unileon.ulebank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.payments.CardType;
import es.unileon.ulebank.payments.DebitCard;

public class DebitCardTest {

	DebitCard testCard;
	
	@Before
	public void setUp() throws Exception {
		testCard = new DebitCard();
		testCard.create();
	}

	@Test (expected = NullPointerException.class)
	public void cardNull() {
		testCard = null;
		testCard.getCardId();
	}
	
	@Test
	public void cardOk() {
		assertTrue(testCard != null);
	}

	@Test
	public void testGeneratePinCode() {
		assertTrue(testCard.generatePinCode().length() == 4);
	}

	@Test
	public void testGenerateExpirationDate() {
		assertTrue(testCard.generateExpirationDate().equals("04/17"));
	}

	@Test
	public void testGenerateCVV() {
		assertTrue(testCard.generateCVV().length() == 3);
	}

	@Test
	public void testGetCardId() {
		System.out.println(testCard.getCardId());
		assertTrue(testCard.getCardId().length() == 16 + 3); 
		
		testCard.setCardId("1234011234567890");
		
		assertTrue(testCard.getCardId().equals("1234 0112 3456 7890"));
	}

	@Test
	public void testSetCardId() {
		testCard.setCardId("1234011029384756");
		assertTrue(testCard.getCardId().equals("1234 0110 2938 4756"));
	}

	@Test
	public void testGetPin() {
		assertTrue(testCard.getPin().length() == 4);
		
		testCard.setPin("5647");
		assertTrue(testCard.getPin().equals("5647"));
	}

	@Test
	public void testSetPin() {
		testCard.setPin("9876");
		assertTrue(testCard.getPin().equals("9876"));
	}

	@Test
	public void testCheckPin() {
		testCard.setPin("1245");
		assertTrue(testCard.checkPin("1245"));
	}

	@Test
	public void testGetBuyLimitDiary() {
		assertEquals(400, testCard.getBuyLimitDiary());
	}

	@Test
	public void testSetBuyLimitDiary() throws IncorrectLimitException {
		testCard.setBuyLimitDiary(800);
		assertEquals(800, testCard.getBuyLimitDiary());
	}

	@Test
	public void testCheckBuyLimitDiary() throws IncorrectLimitException {
		testCard.setBuyLimitDiary(500);
		assertTrue(testCard.checkBuyLimitDiary(500));
	}

	@Test
	public void testGetBuyLimitMonthly() {
		assertEquals(1000, testCard.getBuyLimitMonthly());
	}

	@Test
	public void testSetBuyLimitMonthly() throws IncorrectLimitException {
		testCard.setBuyLimitMonthly(1500);
		assertEquals(1500, testCard.getBuyLimitMonthly());
	}

	@Test
	public void testGetCashLimitDiary() {
		assertEquals(400, testCard.getCashLimitDiary());
	}

	@Test
	public void testSetCashLimitDiary() throws IncorrectLimitException {
		testCard.setCashLimitDiary(800);
		assertEquals(800, testCard.getCashLimitDiary());
	}

	@Test
	public void testCheckCashLimitDiary() throws IncorrectLimitException {
		testCard.setBuyLimitDiary(500);
		assertTrue(testCard.checkBuyLimitDiary(500));
	}

	@Test
	public void testGetCashLimitMonthly() {
		assertEquals(1000, testCard.getCashLimitMonthly());
	}

	@Test
	public void testSetCashLimitMonthly() throws IncorrectLimitException {
		testCard.setCashLimitMonthly(1200);
		assertEquals(1200, testCard.getCashLimitMonthly());
	}

	@Test
	public void testGetExpirationDate() {
		assertTrue(testCard.getExpirationDate().equals("04/17"));
	}

	@Test
	public void testSetExpirationDate() {
		testCard.setExpirationDate("07/18");
		assertTrue(testCard.getExpirationDate().equals("07/18"));
	}

	@Test
	public void testGetCardType() {
		assertTrue(testCard.getCardType().toString().equals(CardType.DEBIT.toString()));
	}

	@Test
	public void testSetCardType() {
		testCard.setCardType(CardType.REVOLVING);
		assertTrue(testCard.getCardType().toString().equals(CardType.REVOLVING.toString()));
	}

	@Test
	public void testGetCvv() {
		assertTrue(testCard.generateCVV().length() == 3);
	}

	@Test
	public void testSetCvv() {
		testCard.setCvv("246");
		assertTrue(testCard.getCvv().equals("246"));
	}

	@Test
	public void testSetCommission() {
		//TODO pendiente de hacer
	}

}
