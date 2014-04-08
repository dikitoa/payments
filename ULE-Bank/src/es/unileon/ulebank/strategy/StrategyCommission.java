package es.unileon.ulebank.strategy;

/**
 * Interface for Strategy Pattern
 * @author Rober dCR
 * @date 19/03/2014
 * @brief Interface which establish the methods for calculate commission in cards.
 */

public interface StrategyCommission {
	
	/**
	 * Method that calculate the commission
	 * @param ownerAge
	 * @return Commission for the card 
	 */
	public int calculateCommission(int ownerAge);
	
	/**
	 * Method that calculate the commission over a quantity 
	 * @param interest in percentage (example: 0.04 -> 4%)
	 * @param quantity
	 * @return
	 */
	public float calculateCommission(float interest, float quantity);
}
