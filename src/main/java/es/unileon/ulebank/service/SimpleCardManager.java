package es.unileon.ulebank.service;

import es.unileon.ulebank.command.Command;
import es.unileon.ulebank.command.ModifyBuyLimitCommand;
import es.unileon.ulebank.command.ModifyCashLimitCommand;
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
	 * Method that changes the buy limits using the command with the limits from buyLimits.jsp
	 * @param diary amount limit
	 * @param monthly amount limit
	 * @throws Exception 
	 */
	@Override
	public void changeBuyLimits(double diary, double monthly)
			throws Exception {
		Command buyLimitsDiary = new ModifyBuyLimitCommand(this.card.getId(), this.card, diary, "diary");
		Command buyLimitsMonthly = new ModifyBuyLimitCommand(this.card.getId(), this.card, monthly, "monthly");
		buyLimitsMonthly.execute();
		buyLimitsDiary.execute();
	}

	/**
     * Method that changes the cash limits using the command with the limits from cashLimits.jsp
     * @param diary amount limit
     * @param monthly amount limit
     * @throws Exception 
     */
	@Override
	public void changeCashLimits(double diary, double monthly)
			throws Exception {
		Command cashLimitsDiary = new ModifyCashLimitCommand(this.card.getId(), this.card, diary, "diary");
		Command cashLimitsMonthly = new ModifyCashLimitCommand(this.card.getId(), this.card, monthly, "monthly");
		cashLimitsMonthly.execute();
		cashLimitsDiary.execute();
	}

	/**
	 * Card setter
	 * @param card
	 */
	public void setCard(Card card) {
		this.card = card;
	}

}