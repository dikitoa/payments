package es.unileon.ulebank.payments.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.bank.BankHandler;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.MalformedHandlerException;
import es.unileon.ulebank.payments.handler.CardHandler;

public class CardHandlerTest {

	CardHandler test;
	CardHandler test2;

	@Before
	public void setUp() throws Exception {
		test = new CardHandler("123401123456789");
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
	public void testCardHandlerIntInt() throws MalformedHandlerException {
		test2 = new CardHandler("123401123456789");
		assertNotNull(test2);
	}
	
	@Test (expected = MalformedHandlerException.class)
	public void testCardHandlerFailOffice() throws MalformedHandlerException {
		test2 = new CardHandler("12340f123456789");
	}
	
	@Test (expected = MalformedHandlerException.class)
	public void testCardHandlerFailCardId() throws MalformedHandlerException {
		test2 = new CardHandler("12340112345678g");
	}
	
	@Test (expected = MalformedHandlerException.class)
	public void testCardHandlerFailCardNumberFormat() throws MalformedHandlerException {
		test2 = new CardHandler("12345678g012345");
	}
	
	@Test (expected = MalformedHandlerException.class)
	public void testCardHandlerFailCardNumberSize() throws MalformedHandlerException {
		test2 = new CardHandler("12345678901234567");
	}
	
	@Test
	public void testCardHandlerString() throws MalformedHandlerException {
		test2 = new CardHandler("123400009876123");
		assertEquals("1234000098761238", test2.toString());
	}
	
	@Test (expected = MalformedHandlerException.class)
	public void testCardHandlerStringFailLength() throws MalformedHandlerException {
		test2 = new CardHandler("1232546546789052");
	}
	
	@Test (expected = MalformedHandlerException.class)
	public void testCardHandlerStringContainsLetter() throws MalformedHandlerException {
		test2 = new CardHandler("908423489j34242");
	}
	
	@Test (expected = MalformedHandlerException.class)
	public void testCardHandlerStringEmpty() throws MalformedHandlerException {
		test2 = new CardHandler("");
	}
	
	@Test
	public void testCardHandlerWithHandler() throws MalformedHandlerException {
		test2 = new CardHandler(new GenericHandler("123456789098765"));
		assertEquals("1234567890987658", test2.toString());
	}
	
	@Test (expected = MalformedHandlerException.class)
	public void testCardHandlerWithHandlerFailLength() throws MalformedHandlerException {
		test2 = new CardHandler(new GenericHandler("1234567890987651"));
	}
	
	@Test (expected = MalformedHandlerException.class)
	public void testCardHandlerWithHandlerContainsLetter() throws MalformedHandlerException {
		test2 = new CardHandler(new GenericHandler("1234567890T8765"));
	}
	
	@Test (expected = MalformedHandlerException.class)
	public void testCardHandlerWithHandlerEmpty() throws MalformedHandlerException {
		test2 = new CardHandler(new GenericHandler(""));
	}
	
	@Test
	public void testCompareTo() throws MalformedHandlerException {
		test = new CardHandler("123401789012345");
		test2 = new CardHandler("123401321012345");

		assertTrue(test.compareTo(test2) != 0);
	}
	
	@Test
	public void testEquals(){
		assertTrue(test.equals(test));
	}
	
	@Test
	public void testHashCode(){
		assertNotNull(test.hashCode());
	}
	
	@Test
	public void testNotEquals() throws MalformedHandlerException{
		test2 = new CardHandler("123401321012345");
		assertFalse(test.equals(test2));
		assertFalse(test.equals(null));
		assertFalse(test.equals(new BankHandler("1234")));
	}

	@Test
	public void testToString() throws MalformedHandlerException {
		test = new CardHandler("123401123456789");
		assertEquals("1234011234567892", test.toString());
	}

}
