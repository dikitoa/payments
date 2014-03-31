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
	
}
