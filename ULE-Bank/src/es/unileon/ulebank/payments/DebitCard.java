package es.unileon.ulebank.payments;

import java.io.IOException;
import java.util.Date;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.exceptions.PaymentException;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.history.GenericTransaction;
import es.unileon.ulebank.history.TransactionType;
import es.unileon.ulebank.strategy.StrategyCommissionDebitEmission;
import es.unileon.ulebank.strategy.StrategyCommissionDebitMaintenance;
import es.unileon.ulebank.strategy.StrategyCommissionDebitRenovate;

/**
 * @author Israel
 * Clase que representa una tarjeta de Debito
 */
public class DebitCard extends Card {
	
	private Account account;
	
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
	 */
	public DebitCard(Handler cardId, Client owner, Account account,
			double buyLimitDiary, double buyLimitMonthly, double cashLimitDiary, double cashLimitMonthly,
			float commissionEmission, float commissionMaintenance, float commissionRenovate) throws NumberFormatException, CommissionException, IOException {
		super(cardId, CardType.DEBIT,
				buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly,
				new StrategyCommissionDebitEmission(commissionEmission), 
				new StrategyCommissionDebitMaintenance(owner, commissionMaintenance), 
				new StrategyCommissionDebitRenovate(commissionRenovate));
		this.account = account;
	}
	
	/**
	 * Method that makes the payment
	 * @param receiverAccount Account which receives the money from the card
	 * @param quantity Amount of the payment
	 * @param payConcept Concept of the payment
	 * @throws PaymentException
	 * @throws TransactionException 
	 */
	public void makeTransaction(Account receiverAccount, double quantity, String payConcept) throws PaymentException, TransactionException{
		//TODO - Actualizar con las nuevas transacciones
		if (this.account.getBalance() >= quantity){
			this.account.setBalance(this.account.getBalance() - quantity);
			receiverAccount.setBalance(receiverAccount.getBalance() + quantity);
			this.addTransaction(new GenericTransaction(quantity, new Date(), payConcept, TransactionType.PAYMENT));
		}
		else throw new PaymentException("Credit Card has not the balance necessary.");
	}
}
