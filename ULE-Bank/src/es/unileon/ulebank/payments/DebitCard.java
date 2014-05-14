package es.unileon.ulebank.payments;

import java.io.IOException;
import java.util.Date;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.exceptions.PaymentException;
import es.unileon.ulebank.fees.DebitMaintenanceFee;
import es.unileon.ulebank.fees.InvalidFeeException;
import es.unileon.ulebank.fees.LinearFee;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.history.CardTransaction;
import es.unileon.ulebank.history.TransactionException;

/**
 * @author Israel, Rober dCR
 * Clase que representa una tarjeta de Debito
 */
public class DebitCard extends Card {
	
	/**
	 * Account associated to the Card
	 */
	private Account account;
	/**
	 * Card owner
	 */
	private Client owner;
	
	/**
	 * Constructor de la clase
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
	public DebitCard(Handler cardId, Client owner, Account account,
			double buyLimitDiary, double buyLimitMonthly, double cashLimitDiary, double cashLimitMonthly,
			double commissionEmission, double commissionMaintenance, double commissionRenovate) throws NumberFormatException, CommissionException, IOException, InvalidFeeException {
		super(cardId, CardType.DEBIT, buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly,
				new LinearFee(0,commissionEmission), 
				new DebitMaintenanceFee(owner, commissionMaintenance), 
				new LinearFee(0,commissionRenovate));
		this.account = account;
		this.owner = owner;
	}
	
	/**
	 * Method that makes the payment
	 * @param receiverAccount Account which receives the money from the card
	 * @param quantity Amount of the payment
	 * @param payConcept Concept of the payment
	 * @throws PaymentException
	 * @throws TransactionException 
	 */
	@Override
	public void makeTransaction(Account receiverAccount, double quantity, String payConcept) throws PaymentException, TransactionException {

		try{
			//Discount the quantity from sender account
			this.account.doWithdrawal(new CardTransaction(quantity, new Date(), payConcept, this.account));
			//Add the money to receiver account
			receiverAccount.doDeposit(new CardTransaction(quantity, new Date(), payConcept, receiverAccount));
		}catch(TransactionException e){
			throw new PaymentException("Denegate Transaction");
		}
		
	}

	/**
	 * Devuelve el duegno de la tarjeta
	 * @return Client
	 */
	public Client getOwner() {
		return owner;
	}
}
