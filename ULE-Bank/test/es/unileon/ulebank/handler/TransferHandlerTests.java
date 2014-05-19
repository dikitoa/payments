package es.unileon.ulebank.handler;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author David Gómez Riesgo
 *
 */
public class TransferHandlerTests {
	
	TransferHandler transferHandler;
	TransferHandler compareTransferHandler;
	private String receiver="pepe";
	private String sender="juan";
	private String id="juanpepe02/05/2014";
	private Calendar calendar;
	
	@Before
	public void setUp() {
		 this.compareTransferHandler= new TransferHandler(this.sender,this.receiver);
		this.transferHandler = new TransferHandler(this.sender,this.receiver);
		
	}

	@Test
	public void testidNotEquals() {
		
		assertTrue(transferHandler!=null);
		assertNotEquals(this.id, transferHandler.getId());
		//assertEquals(transferHandler.compareTo(null), 0);
	}
	
	@Test
	public void testNotNull() {
		assertTrue(transferHandler!=null);
	}
	
	@Test
	public void testIdEquals() {
		calendar=new GregorianCalendar();
		id="an"+"pe"+this.calendar.get(Calendar.DAY_OF_MONTH) + Integer.toString(this.calendar.get(Calendar.MONTH) + 1) + this.calendar.get(Calendar.YEAR) + this.calendar.get(Calendar.HOUR_OF_DAY) + this.calendar.get(Calendar.MINUTE) + this.calendar.get(Calendar.SECOND);
		assertEquals(this.id, transferHandler.getId());
	}
	
	@Test
	public void testToString() {
		calendar=new GregorianCalendar();
		id="an"+"pe"+this.calendar.get(Calendar.DAY_OF_MONTH) + Integer.toString(this.calendar.get(Calendar.MONTH) + 1) + this.calendar.get(Calendar.YEAR) + this.calendar.get(Calendar.HOUR_OF_DAY) + this.calendar.get(Calendar.MINUTE) + this.calendar.get(Calendar.SECOND);
		assertTrue(transferHandler!=null);
		assertEquals(id.toString(), transferHandler.toString());
	}
	
	@Test
	public void testCompareToDifferents() {
		String senderFirst="victor";
		String receiverFirst="david";
		TransferHandler transferHandler1 = new TransferHandler(senderFirst,receiverFirst);
		assertNotNull(transferHandler1);
		assertNotEquals(transferHandler1.compareTo(transferHandler), 0);
	}
	
	@Test
	public void testCompareToEquals() {
		String senderFirst="victor";
		String receiverFirst="david";
		TransferHandler transferHandler1 = new TransferHandler(senderFirst,receiverFirst);
		assertNotNull(compareTransferHandler);
		assertEquals(transferHandler.compareTo(compareTransferHandler),0);
	}
	

}
