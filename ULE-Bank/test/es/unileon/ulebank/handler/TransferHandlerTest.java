package es.unileon.ulebank.handler;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class TransferHandlerTest {

	private String id;
	private String date;
	private String sender;
	private String receiver;
	TransferHandler transferencia;
	
	
	@Before
	public void SetUp(){
		
		transferencia = new TransferHandler(sender, receiver);
	}
	

	@Test
	public void testCompareTo1() {
		
		TransferHandler transferencia1 = new TransferHandler(sender, receiver);
		TransferHandler transferencia2 = new TransferHandler(sender, receiver);
		
		assertTrue(transferencia1.compareTo(transferencia2)==0);
	}

	
	@Test
	public void testCompareTo2() {
		
		TransferHandler transferencia1 = new TransferHandler(sender, sender);
		TransferHandler transferencia2 = new TransferHandler(sender, receiver);
		
		assertFalse(transferencia1.compareTo(transferencia2)==0);
	}
	
	
	@Test
	public void testGetId() {
		
		assertTrue(transferencia.getId()==id);
	}

}
