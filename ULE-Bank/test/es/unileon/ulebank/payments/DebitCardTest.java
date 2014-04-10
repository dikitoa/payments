package es.unileon.ulebank.payments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.strategy.StrategyCommission;
import es.unileon.ulebank.strategy.StrategyCommissionDebitEmission;
import es.unileon.ulebank.strategy.StrategyCommissionDebitMaintenance;
import es.unileon.ulebank.strategy.StrategyCommissionDebitRenovate;

public class DebitCardTest {

	DebitCard testCard;
	
	@Before
	public void setUp() throws Exception {
		CardHandler handler = new CardHandler();
		Client client = new Client();
		Account account = new Account();
		StrategyCommission commissionEmission = new StrategyCommissionDebitEmission(client, testCard, 25);
		StrategyCommission commissionMaintenance = new StrategyCommissionDebitMaintenance(client, testCard, 0);
		StrategyCommission commissionRenovate = new StrategyCommissionDebitRenovate(client, testCard, 0);
		testCard = new DebitCard(handler, client, account, 400F, 1000F, 400F, 1000F, commissionEmission, commissionMaintenance, commissionRenovate, 0);
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
	}

	@Test
	public void testGetPin() throws IOException {
		assertTrue(testCard.getPin().length() == 4);
		
		testCard.setPin("5647");
		assertTrue(testCard.getPin().equals("5647"));
	}

	@Test
	public void testSetPin() throws IOException {
		testCard.setPin("9876");
		assertTrue(testCard.getPin().equals("9876"));
	}

	@Test
	public void testCheckPin() throws IOException {
		testCard.setPin("1245");
		assertTrue(testCard.checkPin("1245"));
	}

	@Test
	public void testGetBuyLimitDiary() {
		assertEquals("400.0", Float.toString(testCard.getBuyLimitDiary()));
	}

	@Test
	public void testSetBuyLimitDiary() throws IncorrectLimitException {
		testCard.setBuyLimitDiary(800F);
		assertEquals("800.0", Float.toString(testCard.getBuyLimitDiary()));
	}

	@Test
	public void testCheckBuyLimitDiary() throws IncorrectLimitException {
		testCard.setBuyLimitDiary(500F);
		assertTrue(testCard.checkBuyLimitDiary(500F));
	}

	@Test
	public void testGetBuyLimitMonthly() {
		assertEquals("1000.0", Float.toString(testCard.getBuyLimitMonthly()));
	}

	@Test
	public void testSetBuyLimitMonthly() throws IncorrectLimitException {
		testCard.setBuyLimitMonthly(1500);
		assertEquals("1500.0", Float.toString(testCard.getBuyLimitMonthly()));
	}

	@Test
	public void testGetCashLimitDiary() {
		assertEquals("400.0", Float.toString(testCard.getCashLimitDiary()));
	}

	@Test
	public void testSetCashLimitDiary() throws IncorrectLimitException {
		testCard.setCashLimitDiary(800F);
		assertEquals("800.0", Float.toString(testCard.getCashLimitDiary()));
	}

	@Test
	public void testCheckCashLimitDiary() throws IncorrectLimitException {
		testCard.setBuyLimitDiary(500F);
		assertTrue(testCard.checkBuyLimitDiary(500F));
	}

	@Test
	public void testGetCashLimitMonthly() {
		assertEquals("1000.0", Float.toString(testCard.getCashLimitMonthly()));
	}

	@Test
	public void testSetCashLimitMonthly() throws IncorrectLimitException {
		testCard.setCashLimitMonthly(1200);
		assertEquals("1200.0", Float.toString(testCard.getCashLimitMonthly()));
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
		try {
			testCard.setCvv("246");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(testCard.getCvv().equals("246"));
	}

	@Test
	public void testSetCommission() {
		//TODO pendiente de hacer
	}

}
