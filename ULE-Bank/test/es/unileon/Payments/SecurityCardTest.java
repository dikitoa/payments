package es.unileon.Payments;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test about SecurityCard Class
 * @author Rober dCR
 * @date 26/03/2014
 */
public class SecurityCardTest {

	Card card;
	SecurityCard secCard;

	@Before
	public void setUp(){
		this.card = new Card(CardType.CREDIT);
		this.card.setPin("0000");
		this.secCard = new SecurityCard(this.card);
	}
	
	@Test
	public void checkCoordinateTest() throws SecurityCardException {
		Integer[][] auxSec = this.secCard.deliverSecurityCard("0000");
		assertFalse(this.secCard.checkCoordinates(0, 0, auxSec[0][0]+1));
		assertTrue(this.secCard.checkCoordinates(0, 0, auxSec[0][0]));
	}
	
	@Test (expected = SecurityCardException.class)
	public void checkCoordinateRowOutOfRangeTest() throws SecurityCardException{
		this.secCard.checkCoordinates(4, 0, 20);
	}
	
	@Test (expected = SecurityCardException.class)
	public void checkCoordinateColumnOutOfRangeTest() throws SecurityCardException{
		this.secCard.checkCoordinates(0, 10, 20);
	}
	
	@Test
	public void deliverSecurityCardOkTest() throws SecurityCardException{
		assertNotNull(this.secCard.deliverSecurityCard("0000"));
	}
	
	@Test (expected = SecurityCardException.class)
	public void deliverSecurityCardNotOkTest() throws SecurityCardException{
		this.secCard.deliverSecurityCard("00");
	}

	@Test (expected = SecurityCardException.class)
	public void deliverSecurityCardTwiceTest() throws SecurityCardException{
		assertNotNull(this.secCard.deliverSecurityCard("0000"));
		this.secCard.deliverSecurityCard("0000");
	}
}
