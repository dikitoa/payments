package es.unileon.ulebank.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.handler.AccountHandler;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.IdDNI;
import es.unileon.ulebank.payments.Account;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Office;

/**
 * @author Israel
 */
public class ModifyCashLimitCommand implements Command {
	private Handler id;
	private Card card;
	private Account account;
	private CardHandler cardId;
	private double newAmount;
	private double oldAmount;
	private String type;
	
	public ModifyCashLimitCommand(CardHandler cardId, Office office, IdDNI dni, AccountHandler accountHandler, double amount, String type) {
		this.id = new CommandHandler(cardId);
		this.cardId = cardId;
		this.account = office.searchClient(dni).searchAccount(accountHandler);
		this.newAmount = amount;
		this.type = type;
	}
	
	@Override
	public void execute() {
		this.card = account.searchCard(cardId);
		
		if (type.equalsIgnoreCase("diary")) {
			try {
				this.oldAmount = this.card.getCashLimitDiary();
				this.card.setCashLimitDiary(newAmount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyCashLimitCommand.class.toString()).log(Level.SEVERE, null, e);
			}
		} else if (type.equalsIgnoreCase("monthly")) {
			try {
				this.oldAmount = this.card.getCashLimitMonthly();
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
