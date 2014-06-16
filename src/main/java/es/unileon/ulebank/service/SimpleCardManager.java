package es.unileon.ulebank.service;

import es.unileon.ulebank.exceptions.CommandException;
import es.unileon.ulebank.payments.Card;

/**
 * Simple Card Manager Class
 * @author Rober dCR
 * @date 14/05/2014
 * @brief Simple Class which manages the card for change buy and cash limits
 */
public class SimpleCardManager implements CardManager {

	private static final long serialVersionUID = 1L;

	/**
	 * Card which modifies from changeLimits.jsp
	 */
	private Card card; //Change for CardDao when persistence will be OK

	/**
     * Method that returns the card of the management
     * @return card
     */
	@Override
	public Card getCard() {
		return card; 
	}

	/**
	 * Card setter
	 * @param card
	 */
	public void setCard(Card card) {
		this.card = card;
	}

	@Override
	public void changeFee(int feeChange) throws CommandException {
		//Change the total fee.
		
	}

	@Override
	public void modifyPin(Card card) {
		// TODO Auto-generated method stub
		
	}

}