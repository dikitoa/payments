package es.unileon.ulebank.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.exceptions.ExcesiveLimitException;
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
		try {
			this.card.setBuyLimitDiary(amount);
		} catch (ExcesiveLimitException e) {
			Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, null, e);
		}
	}
}
