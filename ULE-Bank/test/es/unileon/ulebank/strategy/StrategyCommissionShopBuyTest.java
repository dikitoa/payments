package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.exceptions.CommissionException;

/**
 * 
 * @author Marta
 *
 */
public class StrategyCommissionShopBuyTest {

	private StrategyCommissionShop commission;
	private float interest, quantity;
	
	@Before
	public void SetUp(){

	}
	
	/**
	 * Comprobamos cuando los valores son int
	 * @throws CommissionException
	 */
	@Test
	public void testCalculateCommissionInt() throws CommissionException {
		this.interest = (float) 0.03; //Equals 3%
		this.quantity = 25;
		this.commission = new StrategyCommissionShopBuy(quantity, interest);
		assertEquals(this.commission.calculateCommission(), 0.75, 0.001);
	}

	
	/**
	 * Comprobamos que funciona para los valores float
	 * @throws CommissionException
	 */
	@Test
	public void testCalculateCommissionFloat() throws CommissionException {
		
		this.interest = (float) 0.025; //Equals 2.5%
		this.quantity = 30F;
		this.commission = new StrategyCommissionShopBuy(quantity, interest);
		assertEquals(this.commission.calculateCommission(), 0.75,0);
	}


	/**
	 * Comprobamos que no acepta un tipo de interés negativo
	 */
	@Test
	public void testComprobeInterestNegative(){
		this.interest = (float)-0.1;
		this.quantity = 20F;
		try {
			this.commission = new StrategyCommissionShopBuy(quantity, interest);
		} catch (CommissionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Comprobamos que no acepta quantity negativo.
	 */
	@Test
	public void testComprobeQuantityNegative(){
		this.interest = (float)0.1;
		this.quantity = -20;
		try {
			this.commission = new StrategyCommissionShopBuy(quantity, interest);
		} catch (CommissionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Comprobamos que no acepta quantity ni interest negativo
	 */
	@Test
	public void testComprobeBothNegative(){
		this.interest = (float)-0.1;
		this.quantity = -20F;
		try {
			this.commission = new StrategyCommissionShopBuy(quantity, interest);
		} catch (CommissionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
