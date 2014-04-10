package es.unileon.ulebank.command;

import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.Account;

/**
 * @author Israel
 */
public class CancelCardCommand implements Command {
	private Handler id;
	private CardHandler cardId;
	private Account account;
	
	public CancelCardCommand(CardHandler cardId, Account account) {
		this.id = new CommandHandler(cardId);
		this.cardId = cardId;
		this.account = account;
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
