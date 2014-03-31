package es.unileon.ulebank.command;

import es.unileon.ulebank.payments.Card;

public class CancelCardCommand implements Command {
	private Card card;
//	private Account account;
	
	public CancelCardCommand(Card card) {
		this.card = card;
	}
	
	@Override
	public void execute() {
//		account.removeCard(this.card);
	}
}
