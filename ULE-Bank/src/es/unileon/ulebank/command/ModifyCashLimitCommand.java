package es.unileon.ulebank.command;

import es.unileon.ulebank.payments.Card;

public class ModifyCashLimitCommand implements Command {
	private Card card;
	private int amount;
	
	public ModifyCashLimitCommand(Card card, int amount) {
		this.card = card;
		this.amount = amount;
	}
	
	@Override
	public void execute() {
		this.card.setCashLimitDiary(amount);
	}

}
