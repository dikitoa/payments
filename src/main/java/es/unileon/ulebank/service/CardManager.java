package es.unileon.ulebank.service;

import es.unileon.ulebank.exceptions.CommandException;
import es.unileon.ulebank.payments.Card;

/**
 * Interface that provides Card data access
 * @author isra
 */
public interface CardManager {

	Card getCard();

	void changeFee(int feeChange) throws CommandException;

	void modifyPin(Card card);
    
}