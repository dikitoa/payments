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
	private Handler id;
	private Card card;
	private float newAmount;
	private float oldAmount;
	private String type;
	
	public ModifyBuyLimitCommand(Card card, float amount, String type) {
		this.card = card;
		this.newAmount = amount;
		this.type = type;
	}
	
	@Override
	public void execute() {
		if (type.equalsIgnoreCase("diary")) {
			try {
				this.card.setBuyLimitDiary(newAmount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Diary limit cannot be greater than monthly", e);
			}
		} else if (type.equalsIgnoreCase("monthly")) {
			try {
				this.card.setBuyLimitMonthly(newAmount);
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
				this.card.setBuyLimitDiary(oldAmount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Diary limit cannot be greater than monthly", e);
			}
		} else if (type.equalsIgnoreCase("monthly")) {
			try {
				this.card.setBuyLimitMonthly(oldAmount);
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
				this.card.setBuyLimitDiary(newAmount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Diary limit cannot be greater than monthly", e);
			}
		} else if (type.equalsIgnoreCase("monthly")) {
			try {
				this.card.setBuyLimitMonthly(newAmount);
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
