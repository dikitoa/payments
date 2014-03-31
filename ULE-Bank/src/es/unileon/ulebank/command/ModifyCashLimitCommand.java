package es.unileon.ulebank.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.exceptions.ExcesiveLimitException;
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
		try {
			this.card.setCashLimitDiary(amount);
		} catch (ExcesiveLimitException e) {
			Logger.getLogger(ModifyCashLimitCommand.class.toString()).log(Level.SEVERE, null);
		}
	}

}
