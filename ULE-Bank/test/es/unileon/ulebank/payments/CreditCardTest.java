package es.unileon.ulebank.payments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.payments.CardType;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.strategy.StrategyCommission;
import es.unileon.ulebank.strategy.StrategyCommissionCreditEmission;
import es.unileon.ulebank.strategy.StrategyCommissionCreditMaintenance;
import es.unileon.ulebank.strategy.StrategyCommissionCreditRenovate;

public class CreditCardTest {

	CreditCard testCard;

	@Before
	public void setUp() throws Exception {
		CardHandler handler = new CardHandler();
		Client client = new Client();
		Account account = new Account();
		StrategyCommission commissionEmission = new StrategyCommissionCreditEmission(client, testCard, 25);
		StrategyCommission commissionMaintenance = new StrategyCommissionCreditMaintenance(client, testCard, 0);
		StrategyCommission commissionRenovate = new StrategyCommissionCreditRenovate(client, testCard, 0);
		testCard = new CreditCard(handler, client, account, 400F, 1000F, 400F, 1000F, commissionEmission, commissionMaintenance, commissionRenovate, 3000);
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
	public void testGenerateEmissionDate() {
		assertTrue(testCard.generateEmissionDate().equals("10/04/2014"));
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
		assertTrue(testCard.getCardId().length() == 16 + 3); //add +3 because the cardId have 3 white spaces
	}

	@Test
	public void testGetPin() throws IOException {
		assertTrue(testCard.getPin().length() == 4);
		
		testCard.setPin("9182");
		assertTrue(testCard.getPin().equals("9182"));
	}

	@Test
	public void testSetPin() throws IOException {
		testCard.setPin("1357");
		assertTrue(testCard.getPin().equals("1357"));
	}

	@Test
	public void testCheckPin() throws IOException {
		testCard.setPin("1357");
		assertTrue(testCard.checkPin("1357"));
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
		testCard.setCashLimitDiary(800);
		assertEquals("800.0", Float.toString(testCard.getCashLimitDiary()));
	}

	@Test
	public void testCheckCashLimitDiary() throws IncorrectLimitException {
		testCard.setCashLimitDiary(500F);
		assertTrue(testCard.checkCashLimitDiary(500F));
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
		testCard.setExpirationDate("05/17");
		assertTrue(testCard.getExpirationDate().equals("05/17"));
	}

	@Test
	public void testGetCardType() {
		assertTrue(testCard.getCardType().toString().equals(CardType.CREDIT.toString()));
	}

	@Test
	public void testSetCardType() {
		testCard.setCardType(CardType.DEBIT);
		assertTrue(testCard.getCardType().toString().equals(CardType.DEBIT.toString()));
	}

	@Test
	public void testGetCvv() {
		assertTrue(testCard.generateCVV().length() == 3);
	}

	@Test
	public void testSetCvv() {
		try {
			testCard.setCvv("195");
		} catch (IOException e) {
			
		}
		assertTrue(testCard.getCvv().equals("195"));
	}

	@Test
	public void testSetCommission() {
		//TODO pendiente de hacer
	}

}
