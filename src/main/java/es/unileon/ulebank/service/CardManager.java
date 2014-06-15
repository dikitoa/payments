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
	 * Method that changes the buy limits using the command with the limits from buyLimits.jsp
	 * @param diary amount limit
	 * @param monthly amount limit
	 * @throws Exception 
	 */
    public void changeBuyLimits(double diary, double monthly) throws Exception;
    
    /**
     * Method that changes the cash limits using the command with the limits from cashLimits.jsp
     * @param diary amount limit
     * @param monthly amount limit
     * @throws Exception 
     */
    public void changeCashLimits(double diary, double monthly) throws Exception;
    
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