package es.unileon.ulebank.handler;

import static org.junit.Assert.*;

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
		
		this.sender = sender.substring(sender.length()/2);
		this.receiver = receiver.substring(receiver.length()/2);
		this.id = this.sender + this.receiver + this.date;
		transferencia = new TransferHandler(sender, receiver);
	}
	
	


	@Test
	public void testCompareTo() {
		String a = "3";
		String b = "3";
		assertTrue(a.compareTo(b)==0);
	}

	
	@Test
	public void testGetId() {
		this.sender = sender.substring(sender.length()/2);
		this.receiver = receiver.substring(receiver.length()/2);
		this.id = this.sender + this.receiver + this.date;
		transferencia = new TransferHandler(sender, receiver);
		
		assertTrue(transferencia.getId()==id);
	}

}
