package es.unileon.ulebank.payments.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.bank.BankHandler;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.client.Person;
import es.unileon.ulebank.domain.Accounts;
import es.unileon.ulebank.domain.Offices;
import es.unileon.ulebank.handler.MalformedHandlerException;
import es.unileon.ulebank.office.OfficeHandler;
import es.unileon.ulebank.payments.handler.TransferHandler;

public class TransferHandlerTest {

	private Offices office;
	private Bank bank;
	private String accountNumber = "0000000000";
	private TransferHandler transfer;
	private TransferHandler auxTransfer;
	private Accounts account;
	private Accounts accountAux;
	private Calendar calendar;

	@Before
	public void setUp() throws Exception {
		this.calendar = new GregorianCalendar();
		this.bank = new Bank(new BankHandler("1234"));
		this.office = new Offices();
		Client client = new Person(71451559, 'N');
		account = new Accounts();
		accountAux = new Accounts();
		this.transfer = new TransferHandler(account.getAccountNumber(), accountAux.getAccountNumber());
	}

	@Test
	public void testTransferHandlerNotNull() {
		assertNotNull(this.transfer);
	}

	@Test
	public void testTransferHandlerNull() {
		assertNull(auxTransfer);
	}

	@Test
	public void testTransferHandlerCompareEquals() {
		assertEquals(0, this.transfer.compareTo(this.transfer));
	}

	@Test
	public void testTransferHandlerNotEquals() {
		this.auxTransfer = new TransferHandler(this.accountAux.getAccountNumber(), this.account.getAccountNumber());
		assertFalse(this.transfer.compareTo(auxTransfer)==0);
	}

	@Test
	public void testTransferHandlerGetId() {
		String date = this.calendar.get(Calendar.DAY_OF_MONTH) + Integer.toString(this.calendar.get(Calendar.MONTH) + 1) + this.calendar.get(Calendar.YEAR) + this.calendar.get(Calendar.HOUR_OF_DAY) + this.calendar.get(Calendar.MINUTE) + this.calendar.get(Calendar.SECOND);
		assertEquals("0-00000000005-0000000001"+date, this.transfer.getId());
	}

	@Test
	public void testTransferHandlerToString() {
		String date = this.calendar.get(Calendar.DAY_OF_MONTH) + Integer.toString(this.calendar.get(Calendar.MONTH) + 1) + this.calendar.get(Calendar.YEAR) + this.calendar.get(Calendar.HOUR_OF_DAY) + this.calendar.get(Calendar.MINUTE) + this.calendar.get(Calendar.SECOND);
		assertEquals("0-00000000005-0000000001"+date, this.transfer.toString());
	}
	
	@Test
	public void testEquals(){
		assertTrue(transfer.equals(transfer));
	}
	
	@Test
	public void testNotEquals() throws MalformedHandlerException{
		this.auxTransfer = new TransferHandler(this.accountAux.getAccountNumber(), this.account.getAccountNumber());
		assertFalse(auxTransfer.equals(transfer));
		assertFalse(auxTransfer.equals(null));
		assertFalse(auxTransfer.equals(this.account));
	}
	
	@Test
	public void testHashCode(){
		assertNotNull(transfer.hashCode());
	}
}
