package es.unileon.ulebank.payments;

import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.Table;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.fees.InvalidFeeException;

/**
 * @author Israel, Rober dCR
 * Clase que representa una tarjeta de Debito
 */
@Entity
@Table (name = "cards")
public class DebitCard extends Card {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * @param properties
	 * @param cardId
	 * @param owner
	 * @param account
	 * @param buyLimitDiary
	 * @param buyLimitMonthly
	 * @param cashLimitDiary
	 * @param cashLimitMonthly
	 * @param commissionEmission
	 * @param commissionMaintenance
	 * @param commissionRenovate
	 * @throws NumberFormatException
	 * @throws CommissionException
	 * @throws IOException
	 * @throws InvalidFeeException
	 */
	public DebitCard(String cardId, Client owner, Account account, double buyLimitDiary, double buyLimitMonthly, double cashLimitDiary, double cashLimitMonthly, double commissionEmission, double commissionMaintenance, double commissionRenovate)
			throws NumberFormatException, CommissionException, IOException, InvalidFeeException {
		super(cardId, CardType.DEBIT.toString(), account, owner, buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly, 
				/*new LinearFee(0.0D, commissionEmission), new LinearFee(0.0D, commissionMaintenance), 
				new LinearFee(0.0D, commissionRenovate));*/
				commissionEmission, commissionMaintenance, commissionRenovate);
	}
	
	public DebitCard() {
		super(CardType.DEBIT.toString());
	}

//	/**
//	 * Method that makes the payment
//	 * @param quantity Amount of the payment
//	 * @param payConcept Concept of the payment
//	 * @throws PaymentException
//	 * @throws TransactionException 
//	 */
//	@Override
//	public void makeTransaction(double quantity, String payConcept) throws PaymentException, TransactionException {
//
//		try{
//			//Discount the quantity from sender account
//			this.account.doWithdrawal(new CardTransaction(quantity, new Date(), payConcept));
//			//Add the money to receiver account
//			//receiverAccount.doDeposit(new CardTransaction(quantity, new Date(), payConcept, receiverAccount));
//		}catch(TransactionException e){
//			throw new PaymentException("Denegate Transaction");
//		}
//
//	}
}
