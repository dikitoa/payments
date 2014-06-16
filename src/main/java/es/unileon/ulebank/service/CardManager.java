package es.unileon.ulebank.service;

import java.io.Serializable;

import es.unileon.ulebank.exceptions.CommandException;
import es.unileon.ulebank.payments.Card;

/**
 * Card Manager Interface
 * @author Rober dCR
 * @date 10/05/2014
 * @brief Interface for the managers used to modify card attributes
 */
public interface CardManager extends Serializable {
    
    /**
     * Method that returns the card of the management
     * @return
     */
    public Card getCard();
    /**
     * 
     * @param feeChange
     * @throws CommandException
     */
    public void changeFee(int feeChange) throws CommandException;
    
    /**
     * 
     * @param card
     */
    public void modifyPin(Card card);

}