package es.unileon.ulebank.payments;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.PaymentException;

/**
 * Purse Card Class
 * @author Israel
 * @date 14/05/2014
 * @brief Class which implements the features of the purse card
 */
public class PurseCard extends Card {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Class constructor
	 * @param cardId
	 * @param owner
	 * @param account
	 * @param type
	 * @param buyLimitDiary
	 * @param buyLimitMonthly
	 * @param cashLimitDiary
	 * @param cashLimitMonthly
	 * @param commissionEmission
	 * @param commissionMaintenance
	 * @param commissionRenovate
	 */
	public PurseCard(String cardId, Client owner, Account account,
			double buyLimitDiary, double buyLimitMonthly,
			double cashLimitDiary, double cashLimitMonthly,
			double commissionEmission,
			double commissionMaintenance,
			double commissionRenovate) {
		super(cardId, CardType.PURSE.toString(), account, owner, buyLimitDiary, buyLimitMonthly,
				cashLimitDiary, cashLimitMonthly, commissionEmission,
				commissionMaintenance, commissionRenovate);
	}
	
	/**
	 * Method that makes the payment
	 * @param quantity Amount of the payment
	 * @param payConcept Concept of the payment
	 * @throws PaymentException
	 */
	@Override
	public void makeTransaction(double quantity, String payConcept) throws PaymentException{
		//TODO
	}
}
