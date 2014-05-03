package es.unileon.ulebank.command;

import es.unileon.ulebank.handler.AccountHandler;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.IdDNI;
import es.unileon.ulebank.payments.Account;
import es.unileon.ulebank.payments.Office;

/**
 * @author Israel
 */
public class CancelCardCommand implements Command {
	private Handler id;
	private CardHandler cardId;
	private Account account;
	
	public CancelCardCommand(CardHandler cardId, Office office, IdDNI dni, AccountHandler account) {
		this.id = new CommandHandler(cardId);
		this.cardId = cardId;
		this.account = office.searchClient(dni).searchAccount(account);
	}
	
	@Override
	public void execute() {
		account.removeCard(this.cardId);
	}

	@Override
	public void undo() {
		throw new UnsupportedOperationException();
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
