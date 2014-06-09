package es.unileon.ulebank.handler;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.bank.BankHandler;


public class CommandHandlerTest {

	private CommandHandler test;
	private CommandHandler test2;
	private Handler handler;
	private Handler bankHandler;

	@Before
	public void setUp() throws Exception {
		bankHandler = new BankHandler("1234");
		this.handler = new CardHandler(this.bankHandler, "01", "123456789");
		test = new CommandHandler(this.handler);
	}

	@Test
	public void testCommandHandlerNotNull() {
		assertNotNull(test);
	}

	@Test
	public void testCommandHandlerNull() {
		assertNull(test2);
	}

	@Test
	public void testCommandHandlerCompareEquals() {
		assertEquals(test.compareTo(test),0);
	}

	@Test
	public void testCommandHandlerCompareNotEquals() {
		Handler handlerAux = new CardHandler(this.bankHandler, "02", "123456789");
		this.test2 = new CommandHandler(handlerAux);
		assertFalse(test2.compareTo(test)==0);
	}

	@Test
	public void testCommandGetId() {
		assertEquals(test.getId(), this.handler);
	}

	@Test
	public void testCommandGetDate() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmssss");
		String date = dateFormat.format(new Date());
		assertEquals(test.getDate(), date);
	}

	@Test
	public void testCommandToString() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmssss");
		String date = dateFormat.format(new Date());
		assertEquals(test.toString(), this.handler.toString() + " " + date.toString());
	}
}
