package es.unileon.ulebank.command;

import java.io.IOException;

import org.apache.log4j.Logger;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.AccountHandler;
import es.unileon.ulebank.exceptions.ClientNotFoundException;
import es.unileon.ulebank.exceptions.PaymentException;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CardType;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.payments.DebitCard;
import es.unileon.ulebank.payments.PurseCard;
import es.unileon.ulebank.payments.RevolvingCard;

public class PaymentCommand implements Command {
	/**
	 * Logger de la clase
	 */
	private static final Logger LOG = Logger.getLogger(ModifyPinCommand.class.getName());
	/**
	 * Identificador del comando
	 */
	private Handler id;
	/**
	 * Identificador de la tarjeta
	 */
	private Handler cardId;
	/**
	 * Tarjeta con la que realizamos el pago
	 */
	private Card card;
	/**
	 * Cuenta a la que esta asociada la tarjeta
	 */
	private Account accountSender;
	/**
	 * Cuenta que recibe el pago
	 */
	private Account accountReceiver;
	/**
	 * Cantidad del pago
	 */
	private double amount;
	/**
	 * Concepto del pago
	 */
	private String concept;
	/**
	 * Tipo de la tarjeta con la que realizamos el pago
	 */
	private CardType type;
	
	public PaymentCommand(Handler cardId, Office office, Handler dni, Handler accountHandler, Handler dniReceiver, Handler accountReceiver, double amount, String concept, CardType type) {
		try {
			this.id = new CommandHandler(cardId);
			this.cardId = cardId;
			this.accountSender = office.searchClient((DNIHandler) dni).searchAccount((AccountHandler) accountHandler);
			this.accountReceiver = office.searchClient((DNIHandler) dniReceiver).searchAccount((AccountHandler) accountReceiver);
			this.amount = amount;
			this.concept = concept;
			this.type = type;
		} catch (ClientNotFoundException e) {
			LOG.info("Client with dni " + dni.toString() + " is not found");
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
		}
	}
	
	@Override
	public void execute() throws PaymentException, TransactionException, es.unileon.ulebank.history.TransactionException {
		try {
			//Buscamos la tarjeta en la cuenta
			this.card = accountSender.searchCard((CardHandler) cardId);
			//Almacenamos el antiguo PIN
			switch (this.type){
			case CREDIT:
				((CreditCard) this.card).makeTransaction(this.accountReceiver, this.amount, this.concept);
				break;
			case DEBIT:
				((DebitCard) this.card).makeTransaction(this.accountReceiver, this.amount, this.concept);
				break;
			case PURSE:
				((PurseCard) this.card).makeTransaction(this.accountReceiver, this.amount, this.concept);
				break;
			case REVOLVING:
				((RevolvingCard) this.card).makeTransaction(this.accountReceiver, this.amount, this.concept);
				break;
			default:
				break;
			}
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
		}
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Handler getId() {
		return this.id;
	}

}
