package es.unileon.ulebank.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.exceptions.MalformedHandlerException;
import es.unileon.ulebank.handler.CardHandler;

public class CardHandlerTest {
	
	CardHandler test;
	CardHandler test2;

	@Before
	public void setUp() throws Exception {
		test = new CardHandler("1234","01");
	}
	
	@Test
	public void testCardHandler() {
		assertTrue(test != null);
	}

	@Test
	public void testCardHandlerIntInt() {
		test2 = new CardHandler("1234","01");
		assertTrue(test2 != null);
	}

	@Test
	public void testGetBankIdOK() {
		assertTrue(test.getBankId().length() == test.getBankIdLength());
		assertEquals("1234", test.getBankId());
	}
	
	@Test 
	public void testGetBankIdDifferentFAIL() {
		test2 = new CardHandler("1235", "01");
		assertTrue(!test2.getBankId().equals("1234")); //bankID is diferent
	}
	
	@Test (expected = MalformedHandlerException.class)
	public void testGetBankIdLenghtFAIL() {
		test2 = new CardHandler("12345", "01");
		assertTrue(test2.getBankId().length() == test.getBankIdLength()); //bankID is too long
		
		test2.setBankId("123");
		assertTrue(test2.getBankId().length() == test.getBankIdLength()); //bankID is too short
	}

	@Test
	public void testSetBankId() {
		test2 = new CardHandler("1235", "01");
		assertTrue(test2.getBankId().equals("1235"));
		
		test2.setBankId("1234");
		assertTrue(test2.getBankId().equals("1234"));
	}
	
	@Test (expected = MalformedHandlerException.class)
	public void testGetCardNumberLenghtFAIL() {
		test.setCardNumber("12345678901234567"); 
		assertTrue(test.getCardNumber().length() == test.getCardLength()); //too long
		
		test.setCardNumber("123456789012345");
		assertTrue(test.getCardNumber().length() == test.getCardLength()); //too short
	}

	@Test
	public void testGetCardNumberOK() {
		test2 = new CardHandler("1234", "01");
		assertTrue(test2.getCardNumber().length() == test2.getCardLength());
		assertTrue(test2.getCardNumber().substring(0, test.getBankIdLength()).equals(test2.getBankId())); //first test.getBankIdLength() digits are equals to bankId
		assertTrue(test2.getCardNumber().substring(test.getBankIdLength(), test.getBankIdLength() + test.getOfficeIdLength()).equals(test2.getOfficeId())); //digits test.getBankIdLength() + test.getOfficeIdLength() are equals to officeId
	}

	@Test
	public void testSetCardNumber() {
		test.setCardNumber("1234567890987654");
		assertTrue(test.getCardNumber().equals("1234567890987654"));
		
		test.setCardNumber("9876543210123456");
		assertTrue(test.getCardNumber().equals("9876543210123456"));
	}

	@Test
	public void testGetOfficeIdOK() {
		assertTrue(test.getOfficeId().length() == test.getOfficeIdLength());
		assertEquals("01", test.getOfficeId());
	}
	
	@Test 
	public void testGetOfficeIdDifferentFAIL() {
		test2 = new CardHandler("1234", "05");
		assertTrue(!test2.getOfficeId().equals("01")); //officeID is diferent
	}
	
	@Test (expected = MalformedHandlerException.class)
	public void testGetOfficeIdLenghtFAIL() {
		test2 = new CardHandler("1234", "012");
		assertTrue(test2.getOfficeId().length() == test.getOfficeIdLength()); //officeID is too long
		
		test2.setBankId("1");
		assertTrue(test2.getOfficeId().length() == test.getOfficeIdLength()); //officeID is too short
	}

	@Test
	public void testSetOfficeId() {
		test2 = new CardHandler("1235", "01");
		assertTrue(test2.getOfficeId().equals("01"));
		
		test2.setOfficeId("03");
		assertTrue(test2.getOfficeId().equals("03"));
	}
	
	@Test
	public void testGetCardIdOK() {
		assertTrue(test.getCardId().length() == test.getCardLength()-test.getBankIdLength()-test.getOfficeIdLength()-1); //lenght of cardId is equals to cardLength-bankIdLength-officeIdLength-digitControlLength
	}

	@Test
	public void testSetCardId() {
		test.setCardId("123456789");
		assertTrue(test.getCardId().equals("123456789"));
		
		test.setCardId("987654321");
		assertTrue(test.getCardId().equals("987654321"));
	}

	@Test
	public void testGetControlDigit() {
		test2 = new CardHandler("4918", "47");
		test2.setCardId("963243801");
		assertEquals(5, test2.getControlDigit());
	}

	@Test
	public void testSetControlDigit() {
		test2 = new CardHandler();
		test2.setControlDigit(7);
		
		assertEquals(7, test2.getControlDigit());
	}

	@Test
	public void testCompareTo() {
		test = new CardHandler("1234", "01");
		test2 = new CardHandler("1234", "01");
		test.setCardNumber("1234017890123456");
		test2.setCardNumber("9876543210123456");
		
		assertTrue(test.compareTo(test2) != 0);
	}

	@Test
	public void testToString() {
		test = new CardHandler("1234", "01");
		test.setCardNumber("1234011234567890");
		assertEquals("1234 0112 3456 7890", test.toString());
	}

}
