package es.unileon.ulebank.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.payments.Card;

/**
 * @author Israel
 */
public class ModifyCashLimitCommand implements Command {
	private Card card;
	private int amount;
	private String type;
	
	public ModifyCashLimitCommand(Card card, int amount, String type) {
		this.card = card;
		this.amount = amount;
		this.type = type;
	}
	
	@Override
	public void execute() {
		if (type.equalsIgnoreCase("diary")) {
			try {
				this.card.setCashLimitDiary(amount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyCashLimitCommand.class.toString()).log(Level.SEVERE, null, e);
			}
		} else if (type.equalsIgnoreCase("monthly")) {
			try {
				this.card.setCashLimitMonthly(amount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Monthly limit cannot be smaller than diary", e);
			}
		} else {
			Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Limit type not defined");
		}
	}

}
