package es.unileon.ulebank.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.Card;

/**
 * @author Israel
 */
public class ModifyCashLimitCommand implements Command {
	private Handler id;
	private Card card;
	private float newAmount;
	private float oldAmount;
	private String type;
	
	public ModifyCashLimitCommand(Card card, float amount, String type) {
		this.card = card;
		this.newAmount = amount;
		this.type = type;
	}
	
	@Override
	public void execute() {
		if (type.equalsIgnoreCase("diary")) {
			try {
				this.oldAmount = this.card.getBuyLimitDiary();
				this.card.setCashLimitDiary(newAmount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyCashLimitCommand.class.toString()).log(Level.SEVERE, null, e);
			}
		} else if (type.equalsIgnoreCase("monthly")) {
			try {
				this.oldAmount = this.card.getBuyLimitMonthly();
				this.card.setCashLimitMonthly(newAmount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Monthly limit cannot be smaller than diary", e);
			}
		} else {
			Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Limit type not defined");
		}
	}

	@Override
	public void undo() {
		if (type.equalsIgnoreCase("diary")) {
			try {
				this.card.setCashLimitDiary(oldAmount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyCashLimitCommand.class.toString()).log(Level.SEVERE, null, e);
			}
		} else if (type.equalsIgnoreCase("monthly")) {
			try {
				this.card.setCashLimitMonthly(oldAmount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Monthly limit cannot be smaller than diary", e);
			}
		} else {
			Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Limit type not defined");
		}
	}

	@Override
	public void redo() {
		if (type.equalsIgnoreCase("diary")) {
			try {
				this.card.setCashLimitDiary(newAmount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyCashLimitCommand.class.toString()).log(Level.SEVERE, null, e);
			}
		} else if (type.equalsIgnoreCase("monthly")) {
			try {
				this.card.setCashLimitMonthly(newAmount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Monthly limit cannot be smaller than diary", e);
			}
		} else {
			Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Limit type not defined");
		}
	}

	@Override
	public Handler getId() {
		return this.id;
	}
}
