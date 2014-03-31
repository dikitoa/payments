package es.unileon.ulebank.command;

import es.unileon.ulebank.payments.Card;

public class ModifyBuyLimitCommand implements Command {
	private Card card;
	private int amount;
	
	public ModifyBuyLimitCommand(Card card, int amount) {
		this.card = card;
		this.amount = amount;
	}
	
	@Override
	public void execute() {
		this.card.setBuyLimitDiary(amount);
	}
}
