package es.unileon.ulebank.fees;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.client.Person;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.MalformedHandlerException;
import es.unileon.ulebank.utils.CardProperties;

public class DebitMaintenanceFeeTest {
	
	private DebitMaintenanceFee fee;
	
    @Before
    public void setUp() throws CommissionException, MalformedHandlerException {

    	
        fee = new DebitMaintenanceFee(new Person(71451559, 'N'), 20.00);
    }

	@Test
	public void testDebitMaintenanceFeeOk() {
		assertNotNull(fee);
	}
	
	@Test (expected = CommissionException.class)
	public void testDebitMaintenanceFeeFail() throws CommissionException, MalformedHandlerException {
		DebitMaintenanceFee fee2 = new DebitMaintenanceFee(new Person(71451559, 'N'), -1.00);
	}

	@Test
	public void testGetFee() {
		assertEquals(20.00, fee.getFee(20.00), 0.0001);
	}
	
	@Test
	public void testGetFeeB() throws CommissionException, MalformedHandlerException {
		CardProperties properties = new CardProperties();
		properties.setMaximumAge(30);
		properties.setDefaultFee(10.00);
		Person person = new Person(71451559, 'N');
		person.setAge(35);
		DebitMaintenanceFee fee2 = new DebitMaintenanceFee(person, 20.00);

		assertEquals(10.00, fee2.getFee(20.00), 0.0001);
	}

}
