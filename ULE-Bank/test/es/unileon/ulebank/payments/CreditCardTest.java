package es.unileon.ulebank.payments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.Office;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.AccountHandler;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.OfficeHandler;
import es.unileon.ulebank.payments.CardType;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.strategy.StrategyCommission;
import es.unileon.ulebank.strategy.StrategyCommissionCreditEmission;
import es.unileon.ulebank.strategy.StrategyCommissionCreditMaintenance;
import es.unileon.ulebank.strategy.StrategyCommissionCreditRenovate;
import es.unileon.ulebank.transacionManager.TransactionManager;

public class CreditCardTest {

	CreditCard testCard;
	CardHandler handler;
	private Office office;
	private Bank bank;
	private TransactionManager manager;
    private String accountNumber = "0000000000";

	@Before
	public void setUp() throws Exception {
		this.manager = new TransactionManager();
        this.bank = new Bank(manager, new GenericHandler("1234"));
        this.office = new Office(new GenericHandler("1234"), this.bank);
		handler = new CardHandler();
		Client client = new Client(new DNIHandler("71451559N"), 27);
		Account account = new Account(office, bank, accountNumber);
		StrategyCommission commissionEmission = new StrategyCommissionCreditEmission(25);
		StrategyCommission commissionMaintenance = new StrategyCommissionCreditMaintenance(0);
		StrategyCommission commissionRenovate = new StrategyCommissionCreditRenovate(0);
		testCard = new CreditCard(handler, client, account, 400.0, 1000.0, 400.0, 1000.0, commissionEmission.calculateCommission(), commissionMaintenance.calculateCommission(), commissionRenovate.calculateCommission());
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
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String today = dateFormat.format(new Date());
		assertTrue(testCard.generateEmissionDate().equals(today));
	}
	
	@Test
	public void testGenerateExpirationDate() {
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat yearFormat = new SimpleDateFormat("YY");
		String currentMonth = monthFormat.format(new Date());
		String currentYear = String.valueOf(Integer.parseInt(yearFormat.format(new Date()))+3);
		System.out.println(currentMonth+"/"+currentYear);
		assertTrue(testCard.generateExpirationDate().equals(currentMonth+"/"+currentYear));
	}

	@Test
	public void testGenerateCVV() {
		assertTrue(testCard.generateCVV().length() == 3);
	}

	@Test
	public void testGetCardId() {
		assertTrue(testCard.getCardId().length() == handler.getCardLength() + 3); //add +3 because the cardId have 3 white spaces
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
		assertEquals(400.0, testCard.getBuyLimitDiary(), 0.0001);
	}

	@Test
	public void testSetBuyLimitDiaryOKUpLimit() throws IncorrectLimitException {
		assertEquals(testCard.getBuyLimitMonthly(), 1000.0, 0.0001);
		
		testCard.setBuyLimitDiary(800.0);
		assertEquals(800.0, testCard.getBuyLimitDiary(), 0.0001);
	}
	
	@Test
	public void testSetBuyLimitDiaryOKDownLimit() throws IncorrectLimitException{
		testCard.setBuyLimitDiary(200); //Ok because buy limit diary is 200
		assertEquals(200.0, testCard.getBuyLimitDiary(), 0.0001);
	}
	
	@Test (expected = IncorrectLimitException.class)
	public void testSetBuyLimitDiaryFAILUp() throws IncorrectLimitException{
		testCard.setBuyLimitDiary(2000); //fail because buy limit diary is greater than buy limit monthly
		assertEquals(2000.0, testCard.getBuyLimitDiary(), 0.0001);
	}
	
	@Test (expected = IncorrectLimitException.class)
	public void testSetBuyLimitDiaryFAILDownMinimumLimit() throws IncorrectLimitException{
		testCard.setBuyLimitDiary(199); //fail because buy limit diary under 200
		assertEquals(199.0, testCard.getBuyLimitDiary(), 0.0001);
	}

	@Test
	public void testCheckBuyLimitDiaryTRUE() throws IncorrectLimitException {
		testCard.setBuyLimitDiary(500.0);
		assertTrue(testCard.checkBuyLimitDiary(500.0));
	}
	
	@Test
	public void testCheckBuyLimitDiaryFALSE() throws IncorrectLimitException {
		testCard.setBuyLimitDiary(500.0);
		assertTrue(!testCard.checkBuyLimitDiary(600.0));
	}

	@Test
	public void testGetBuyLimitMonthly() {
		assertEquals(1000.0, testCard.getBuyLimitMonthly(), 0.0001);
	}

	@Test
	public void testSetBuyLimitMonthly() throws IncorrectLimitException {
		assertEquals(1000.0, testCard.getBuyLimitMonthly(), 0.0001);
		
		testCard.setBuyLimitMonthly(1500.0);
		assertEquals(1500.0, testCard.getBuyLimitMonthly(), 0.0001);
	}
	
	@Test
	public void testSetBuyLimitMonthlyOKDownLimit() throws IncorrectLimitException{
		testCard.setBuyLimitMonthly(400.0); //Ok because buy limit diary is 400
		assertEquals(400.0, testCard.getBuyLimitMonthly(), 0.0001);
	}
	
	@Test  (expected = IncorrectLimitException.class)
	public void testSetBuyLimitMonthlyFAILDownMinimumLimit() throws IncorrectLimitException{
		testCard.setBuyLimitMonthly(399); //fail because buy limit diary is 400
		assertEquals(399.0, testCard.getBuyLimitMonthly(), 0.0001);
	}

	@Test
	public void testGetCashLimitDiary() {
		assertEquals(400.0, testCard.getCashLimitDiary(), 0.0001);
	}

	@Test
	public void testSetCashLimitDiaryOKUpLimit() throws IncorrectLimitException {
		assertEquals(testCard.getCashLimitMonthly(), 1000.0, 0.0001);
		
		testCard.setCashLimitDiary(800.0);
		assertEquals(800.0, testCard.getCashLimitDiary(), 0.0001);
	}
	
	@Test
	public void testSetCashLimitDiaryOKDownLimit() throws IncorrectLimitException{
		testCard.setCashLimitDiary(200); //Ok because cash limit diary is 200
		assertEquals(200.0, testCard.getCashLimitDiary(), 0.0001);
	}
	
	@Test (expected = IncorrectLimitException.class)
	public void testCashLimitDiaryFAILUp() throws IncorrectLimitException{
		testCard.setCashLimitDiary(2000); //fail because cash limit diary is greater than cash limit monthly
		assertEquals(2000.0, testCard.getCashLimitDiary(), 0.0001);
	}
	
	@Test (expected = IncorrectLimitException.class)
	public void testSetCashLimitDiaryFAILDownMinimumLimit() throws IncorrectLimitException{
		testCard.setCashLimitDiary(199); //fail because cash limit diary under 200
		assertEquals(199.0, testCard.getCashLimitDiary(), 0.0001);
	}

	@Test
	public void testCheckCashLimitDiaryTRUE() throws IncorrectLimitException {
		testCard.setCashLimitDiary(500.0);
		assertTrue(testCard.checkCashLimitDiary(500.0));
	}
	
	@Test
	public void testCheckCashLimitDiaryFALSE() throws IncorrectLimitException {
		testCard.setCashLimitDiary(500.0);
		assertTrue(!testCard.checkCashLimitDiary(600.0));
	}

	@Test
	public void testGetCashLimitMonthly() {
		assertEquals(1000.0, testCard.getCashLimitMonthly(), 0.0001);
	}

	@Test
	public void testSetCashLimitMonthlyOKDownLimit() throws IncorrectLimitException{
		testCard.setCashLimitMonthly(500.0); //Ok because cash limit diary is 500
		assertEquals(500.0, testCard.getCashLimitMonthly(), 0.0001);
	}
	
	@Test  (expected = IncorrectLimitException.class)
	public void testSetCashLimitMonthlyFAILDownMinimumLimit() throws IncorrectLimitException{
		testCard.setCashLimitMonthly(399); //fail because cash limit diary is 400
		assertEquals(399.0, testCard.getCashLimitMonthly(), 0.0001);
	}

	@Test
	public void testGetExpirationDate() {
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat yearFormat = new SimpleDateFormat("YY");
		String currentMonth = monthFormat.format(new Date());
		String currentYear = String.valueOf(Integer.parseInt(yearFormat.format(new Date()))+3);
		System.out.println(currentMonth+"/"+currentYear);
		
		assertTrue(testCard.getExpirationDate().equals(currentMonth+"/"+currentYear));
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
}
