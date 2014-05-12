package es.unileon.ulebank.payments;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.exceptions.PaymentException;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.fees.InvalidFeeException;
import es.unileon.ulebank.fees.LinearFee;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.taskList.TaskList;
import es.unileon.ulebank.history.CardTransaction;

/**
 * @author Israel, Rober dCR
 * Clase que representa la tarjeta de credito
 */
public class CreditCard extends Card {
	
	private Account account;
	private Client owner;
	private List<CardTransaction> transactionList;
	private TaskList transactionTask;
	
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
	 * @throws CommissionException
	 * @throws InvalidFeeException 
	 */
	public CreditCard(Handler cardId, Client owner, Account account, double buyLimitDiary, double buyLimitMonthly, 
			double cashLimitDiary, double cashLimitMonthly, double commissionEmission, 
			double commissionMaintenance, double commissionRenovate) throws CommissionException, InvalidFeeException {
		super(cardId, CardType.CREDIT, buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly,
				new LinearFee(0,commissionEmission),
				new LinearFee(0,commissionMaintenance),
				new LinearFee(0,commissionRenovate));
		this.account = account;
		this.owner = owner;
		this.transactionList = new ArrayList<CardTransaction>();
	}
		
	/**
	 * Method that makes the payment
	 * @param receiverAccount Account which receives the money from the card
	 * @param quantity Amount of the payment
	 * @param payConcept Concept of the payment
	 * @throws PaymentException 
	 * @throws es.unileon.ulebank.history.TransactionException 
	 */
	public void makeTransaction(Account receiverAccount, double quantity, String payConcept) throws PaymentException, TransactionException, es.unileon.ulebank.history.TransactionException{
		//TODO - Actualizar con las nuevas transacciones
		//A�adimos la transaccion a la lista
		this.transactionList.add(new CardTransaction(quantity, new Date(), payConcept, receiverAccount, this.account));
		//LLegada la fecha hay que descontar el dinero de la cuenta
		//Pagar los importes a la cuenta
	}
	
	/**
	 * Devuelve el duegno de la tarjeta
	 * @return Client
	 */
	public Client getOwner() {
		return owner;
	}
}
