package es.unileon.ulebank.command;

import es.unileon.ulebank.payments.Card;

/**
 * @author Israel
 */
public class CancelCardCommand implements Command {
	private Card card;
//	private Account account;
	
	public CancelCardCommand(Card card/*, Account account*/) {
		this.card = card;
//		this.account = account;
	}
	
	@Override
	public void execute() {
//		account.removeCard(this.card);
	}
}
