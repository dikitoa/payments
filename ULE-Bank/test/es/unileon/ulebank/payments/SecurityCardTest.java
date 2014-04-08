package es.unileon.ulebank.payments;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.exceptions.SecurityCardException;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CardType;
import es.unileon.ulebank.payments.SecurityCard;

/**
 * Test about SecurityCard Class
 * @author Rober dCR
 * @date 26/03/2014
 */
public class SecurityCardTest {

	public Card card;
	public SecurityCard secCard;

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
