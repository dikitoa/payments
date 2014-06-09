package es.unileon.ulebank.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.bank.BankHandler;
import es.unileon.ulebank.exceptions.MalformedHandlerException;

public class CardHandlerTest {

	CardHandler test;
	CardHandler test2;

	@Before
	public void setUp() throws Exception {
		test = new CardHandler(new BankHandler("1234"), "01", "123456789");
	}

	@Test
	public void testCardHandlerNotNull() {
		assertNotNull(test);
	}

	@Test
	public void testCardHandlerNull() {
		assertNull(test2);
	}

	@Test
	public void testCardHandlerIntInt() {
		test2 = new CardHandler(new BankHandler("1234"),"01", "123456789");
		assertTrue(test2 != null);
	}

	@Test
	public void testGetBankIdOK() {
		assertTrue(test.getBankHandler().toString().length() == test.getBankIdLength());
		assertEquals("1234", test.getBankHandler().toString());
	}

	@Test 
	public void testGetBankIdDifferentFAIL() {
		test2 = new CardHandler(new BankHandler("1235"), "01", "123456789");
		assertTrue(!test2.getBankHandler().toString().equals("1234")); //bankID is diferent
	}

	@Test (expected = MalformedHandlerException.class)
	public void testGetBankIdLenghtFAIL() {
		test2 = new CardHandler(new BankHandler("12345"), "01", "123456789");
		assertTrue(test2.getBankHandler().toString().length() == test.getBankIdLength()); //bankID is too long

		test2 = new CardHandler(new BankHandler("123"), "01", "123456789");
		assertTrue(test2.getBankHandler().toString().length() == test.getBankIdLength());//bankID is too short
	}

	@Test (expected = MalformedHandlerException.class)
	public void testGetCardNumberLenghtFAIL() {
		test = new CardHandler(new BankHandler("1234"), "01", "1234567890");
		assertTrue(test.toString().length() == test.getCardLength()); //too long

		test = new CardHandler(new BankHandler("1234"), "01", "12345678");
		assertTrue(test.toString().length() == test.getCardLength()); //too short
	}

	@Test
	public void testGetCardNumberOK() {
		test2 = new CardHandler(new BankHandler("1234"), "01", "123456789");
		assertTrue(test2.getBankHandler().toString().length()+test2.getCardId().length()+test2.getOfficeId().length()+1 == test2.getCardLength());
	}

	@Test
	public void testGetOfficeIdOK() {
		assertTrue(test.getOfficeId().length() == test.getOfficeIdLength());
		assertEquals("01", test.getOfficeId());
	}

	@Test 
	public void testGetOfficeIdDifferentFAIL() {
		test2 = new CardHandler(new BankHandler("1234"), "05", "123456789");
		assertTrue(!test2.getOfficeId().equals("01")); //officeID is diferent
	}

	@Test (expected = MalformedHandlerException.class)
	public void testGetOfficeIdLenghtFAIL() {
		test2 = new CardHandler(new BankHandler("1234"), "012", "123456789");
		assertTrue(test2.getOfficeId().length() == test.getOfficeIdLength()); //officeID is too long

		test2 = new CardHandler(new BankHandler("1234"), "2", "123456789");
		assertTrue(test2.getOfficeId().length() == test.getOfficeIdLength()); //officeID is too short
	}

	@Test
	public void testGetCardIdOK() {
		assertTrue(test.getCardId().length() == test.getCardLength()-test.getBankIdLength()-test.getOfficeIdLength()-1); //lenght of cardId is equals to cardLength-bankIdLength-officeIdLength-digitControlLength
	}

	@Test
	public void testGetControlDigit() {
		test2 = new CardHandler(new BankHandler("4918"), "47", "963243801");
		assertEquals(5, test2.getControlDigit());
	}

	@Test
	public void testCompareTo() {
		test = new CardHandler(new BankHandler("1234"), "01", "789012345");
		test2 = new CardHandler(new BankHandler("1234"), "01", "321012345");

		assertTrue(test.compareTo(test2) != 0);
	}

	@Test
	public void testToString() {
		test = new CardHandler(new BankHandler("1234"), "01", "123456789");
		assertEquals("1234 0112 3456 7892", test.toString());
	}

}
