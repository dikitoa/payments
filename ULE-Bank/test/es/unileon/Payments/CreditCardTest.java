package es.unileon.Payments;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CreditCardTest {

	CreditCard testCard;

	@Before
	public void setUp() throws Exception {
		testCard = new CreditCard();
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
		System.out.println(testCard.generateExpirationDate()); //corregir mes, devuelve 2/17
		assertTrue(testCard.generateExpirationDate().equals("03/17"));
	}

	@Test
	public void testGenerateCVV() {
		assertTrue(testCard.generateCVV().length() == 3);
	}

	@Test
	public void testGetCardId() {
		assertTrue(testCard.getCardId().length() == 16 + 3); // añadir trim al devolver el cardID
		
		testCard.setCardId("1234019876543210");
		assertTrue(testCard.getCardId().equals("1234 0198 7654 3210 "));
	}

	@Test
	public void testSetCardId() {
		testCard.setCardId("1234019876543210");
		assertTrue(testCard.getCardId().equals("1234 0198 7654 3210 "));
	}

	@Test
	public void testGetPin() {
		assertTrue(testCard.getPin().length() == 4);
		
		testCard.setPin("9182");
		assertTrue(testCard.getPin().equals("9182"));
	}

	@Test
	public void testSetPin() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckPin() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBuyLimitDiary() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetBuyLimitDiary() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckBuyLimitDiary() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBuyLimitMonthly() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetBuyLimitMonthly() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCashLimitDiary() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCashLimitDiary() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckCashLimitDiary() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCashLimitMonthly() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCashLimitMonthly() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetExpirationDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetExpirationDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCardType() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCardType() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCvv() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCvv() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCommission() {
		fail("Not yet implemented");
	}

}
