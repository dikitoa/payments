package es.unileon.ulebank.payments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.bank.BankHandler;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.fees.FeeStrategy;
import es.unileon.ulebank.fees.LinearFee;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.office.Office;

public class DebitCardTest {
	//TODO cuando un test espera excepcion quitar los assert
	DebitCard testCard;
	Handler handler;
	private Office office;
	private Bank bank;
    private String accountNumber = "0000000000";
	
	@Before
	public void setUp() throws Exception {
        this.bank = new Bank(new BankHandler("1234"));
        this.office = new Office(new GenericHandler("1234"), this.bank);
        this.handler = new CardHandler(new BankHandler("1234"), "01", "987654321");
		Client client = new Client(new DNIHandler("71451559N"));
		Account account = new Account(office, bank, accountNumber, client);
		FeeStrategy commissionEmission = new LinearFee(0, 25);
		FeeStrategy commissionMaintenance = new LinearFee(0, 0);
		FeeStrategy commissionRenovate = new LinearFee(0, 0);
		testCard = new DebitCard(handler, client, account, 400F, 1000F, 400F, 1000F, commissionEmission.getFee(0), commissionMaintenance.getFee(0), commissionRenovate.getFee(0));
	}

	@Test (expected = NullPointerException.class)
	public void cardNull() {
		testCard = null;
		testCard.getId();
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
		assertTrue(testCard.generateExpirationDate().equals(currentMonth+"/"+currentYear));
	}

	@Test
	public void testGenerateCVV() {
		assertTrue(testCard.generateCVV().length() == 3);
	}

	@Test
	public void testGetCardId() {
		assertTrue(((CardHandler)testCard.getId()).toString().length() == 16 + 3); //add +3 because the cardId have 3 white spaces
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
	public void testSetCashLimitDiaryFAILUp() throws IncorrectLimitException{
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
	public void testGetEmissionDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String today = dateFormat.format(new Date());
		assertTrue(testCard.getEmissionDate().equals(today));
	}

	@Test
	public void testGetExpirationDate() {
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat yearFormat = new SimpleDateFormat("YY");
		String currentMonth = monthFormat.format(new Date());
		String currentYear = String.valueOf(Integer.parseInt(yearFormat.format(new Date()))+3);
		assertTrue(testCard.getExpirationDate().equals(currentMonth+"/"+currentYear));
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
	public void testGetCvv() {
		assertTrue(testCard.generateCVV().length() == 3);
	}

	@Test (expected = IOException.class)
	public void testSetCvvFAILLenght() throws IOException {
			testCard.setCvv("5245");
		assertTrue(testCard.getCvv().equals("5245"));
	}
	
	@Test (expected = IOException.class)
	public void testSetCvvFAILLetter() throws IOException {
			testCard.setCvv("r34");
		assertTrue(testCard.getCvv().equals("r34"));
	}
	
	@Test
	public void testSetCvvOK() throws IOException{
			testCard.setCvv("863");
		assertTrue(testCard.getCvv().equals("863"));
	}

	@Test
	public void testGetCardNumber(){
		assertTrue(testCard.getId().toString().equals("1234 0198 7654 3212"));
	}

}
