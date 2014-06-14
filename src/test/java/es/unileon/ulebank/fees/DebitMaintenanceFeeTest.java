package es.unileon.ulebank.fees;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.client.Person;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.MalformedHandlerException;

public class DebitMaintenanceFeeTest {
	
	private DebitMaintenanceFee fee;
	
    @Before
    public void setUp() throws CommissionException, MalformedHandlerException {

    	
        fee = new DebitMaintenanceFee(new Person(71451559, 'N'), 20.00);
    }

	@Test
	public void testDebitMaintenanceFee() {
		assertNotNull(fee);
	}

	@Test
	public void testGetFee() {
		assertEquals(20.00, fee.getFee(20.00), 0.0001);
	}

}
