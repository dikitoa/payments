/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.handler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.command.handler.CommandHandler;
import es.unileon.ulebank.payments.handler.CardHandler;

/**
 *
 * @author Revellado
 */
public class GenericHandlerTest {

    private GenericHandler genericHandler1, genericHandler2;

    @Before
    public void setUp() {

        this.genericHandler1 = new GenericHandler("0000");
        this.genericHandler2 = new GenericHandler("0001");
    }

    /**
     * Test of compareTo method, of class GenericHandler.
     */
    @Test
    public void testCompareToOk() {
        Assert.assertEquals(
                this.genericHandler1.compareTo(this.genericHandler1), 0);
        Assert.assertEquals(
                this.genericHandler1.compareTo(new GenericHandler("0000")), 0);
        Assert.assertEquals(this.genericHandler1.compareTo(new GenericHandler(
                this.genericHandler1.toString())), 0);
    }

    @Test
    public void testCompareNotOk() {
        Assert.assertFalse(this.genericHandler1.compareTo(this.genericHandler2) == 0);
        Assert.assertFalse(this.genericHandler2.compareTo(this.genericHandler1) == 0);
    }
    
	@Test
	public void testEquals(){
		assertTrue(genericHandler1.equals(genericHandler1));
	}
	
	@Test
	public void testNotEquals() throws MalformedHandlerException{
		assertFalse(genericHandler1.equals(genericHandler2));
	}
	
	@Test
	public void testHashCode(){
		assertNotNull(genericHandler1.hashCode());
	}

    /**
     * Test of toString method, of class GenericHandler.
     */
    @Test
    public void testToString() {
        Assert.assertEquals(this.genericHandler1.toString(), "0000");
    }
}
