package es.unileon.ulebank.handler;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.bank.BankHandler;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.office.Office;

public class TransferHandlerTest {
	
	private Office office;
	private Bank bank;
    private String accountNumber = "0000000000";
    private TransferHandler transfer;
    private TransferHandler auxTransfer;
    private Account account;
    private Account accountAux;
	private Calendar calendar;
    
	@Before
	public void setUp() throws Exception {
		this.calendar = new GregorianCalendar();
		this.bank = new Bank(new BankHandler("1234"));
		this.office = new Office(new GenericHandler("1234"), this.bank);
		Client client = new Client(new DNIHandler("71451559N"));
		account = new Account(office, bank, accountNumber, client);
		accountAux = new Account(office, bank, "0000000001", client);
		this.transfer = new TransferHandler(account.getID().toString(), accountAux.getID().toString());
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
		assertEquals(this.transfer.compareTo(this.transfer),0);
	}
	
	@Test
	public void testTransferHandlerNotEquals() {
		this.auxTransfer = new TransferHandler(this.accountAux.getID().toString(), this.account.getID().toString());
		assertFalse(this.transfer.compareTo(auxTransfer)==0);
	}
	
	@Test
	public void testTransferHandlerGetId() {
		String date = this.calendar.get(Calendar.DAY_OF_MONTH) + Integer.toString(this.calendar.get(Calendar.MONTH) + 1) + this.calendar.get(Calendar.YEAR) + this.calendar.get(Calendar.HOUR_OF_DAY) + this.calendar.get(Calendar.MINUTE) + this.calendar.get(Calendar.SECOND);
		assertEquals(this.transfer.getId(), "0-00000000005-0000000001"+date);
	}
	
	@Test
	public void testTransferHandlerToString() {
		String date = this.calendar.get(Calendar.DAY_OF_MONTH) + Integer.toString(this.calendar.get(Calendar.MONTH) + 1) + this.calendar.get(Calendar.YEAR) + this.calendar.get(Calendar.HOUR_OF_DAY) + this.calendar.get(Calendar.MINUTE) + this.calendar.get(Calendar.SECOND);
		assertEquals(this.transfer.toString(), "0-00000000005-0000000001"+date);
	}
}
