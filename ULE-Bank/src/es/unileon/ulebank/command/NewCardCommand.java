package es.unileon.ulebank.command;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.handler.AccountHandler;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.IdDNI;
import es.unileon.ulebank.payments.Account;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CardType;
import es.unileon.ulebank.payments.Client;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.payments.DebitCard;
import es.unileon.ulebank.payments.Office;

/**
 * @author Israel
 */
public class NewCardCommand implements Command {
	private Card card;
	private Handler id;
	private Account account;
	private Office office;
	private IdDNI dni;
	private AccountHandler accountHandler;
	private CardType type;
	private CardHandler cardId;
	private double buyLimitDiary;
	private double buyLimitMonthly;
	private double cashLimitDiary;
	private double cashLimitMonthly;
	private float commissionEmission;
	private float commissionMaintenance;
	private float commissionRenovate;
	private double limitDebit;
	
	public NewCardCommand(Office office, IdDNI dni, AccountHandler accountHandler, CardType type, 
			double buyLimitDiary, double buyLimitMonthly, double cashLimitDiary, double cashLimitMonthly,
			float commissionEmission, float commissionMaintenance, 
			float commissionRenovate, double limitDebit) {
		cardId = new CardHandler();
		this.id = new CommandHandler(cardId);
		this.office = office;
		this.dni = dni;
		this.accountHandler = accountHandler;
		this.type = type;
		this.buyLimitDiary = buyLimitDiary;
		this.buyLimitMonthly = buyLimitMonthly;
		this.cashLimitDiary = cashLimitDiary;
		this.cashLimitMonthly = cashLimitMonthly;
		this.commissionEmission = commissionEmission;
		this.commissionMaintenance = commissionMaintenance;
		this.commissionRenovate = commissionRenovate;
		this.limitDebit = limitDebit;
	}
	
	@Override
	public void execute() {
		Client client = office.searchClient(dni);
		this.account = client.searchAccount(accountHandler);
		
		try {
			switch (type) {
			case CREDIT:
				this.card = new CreditCard(cardId, client, account, buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, commissionMaintenance, commissionRenovate, limitDebit);
				break;
			case DEBIT:
				this.card = new DebitCard(cardId, client, account, buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, commissionMaintenance, commissionRenovate, limitDebit);
				break;
			case REVOLVING:

				break;
			case PURSE:

				break;
			}
		} catch (CommissionException | NumberFormatException | IOException e) {
			Logger.getLogger(NewCardCommand.class.toString()).log(Level.SEVERE, null, e);
		}
			
		account.addCard(card);
	}

	@Override
	public void undo() {
		CancelCardCommand cancel = new CancelCardCommand(cardId, office, dni, accountHandler);
		cancel.execute();
	}

	@Override
	public void redo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Handler getId() {
		return this.id;
	}
}
