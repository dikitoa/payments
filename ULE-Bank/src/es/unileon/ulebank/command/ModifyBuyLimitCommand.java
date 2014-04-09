package es.unileon.ulebank.command;

import java.util.logging.Level;
import java.util.logging.Logger;
import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.Card;

/**
 * @author Israel
 */
public class ModifyBuyLimitCommand implements Command {
	private Card card;
	private int amount;
	private String type;
	
	public ModifyBuyLimitCommand(Card card, int amount, String type) {
		this.card = card;
		this.amount = amount;
		this.type = type;
	}
	
	@Override
	public void execute() {
		if (type.equalsIgnoreCase("diary")) {
			try {
				this.card.setBuyLimitDiary(amount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Diary limit cannot be greater than monthly", e);
			}
		} else if (type.equalsIgnoreCase("monthly")) {
			try {
				this.card.setBuyLimitMonthly(amount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Monthly limit cannot be smaller than diary", e);
			}
		} else {
			Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Limit type not defined");
		}
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Handler getId() {
		// TODO Auto-generated method stub
		return null;
	}
}
