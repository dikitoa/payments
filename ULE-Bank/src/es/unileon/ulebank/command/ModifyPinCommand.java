package es.unileon.ulebank.command;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class ModifyPinCommand implements Command {
	private Handler id;
	private CardHandler cardId;
	private Card card;
	private Account account;
	private String newPin;
	private String oldPin;
	
	public ModifyPinCommand(CardHandler cardId, Office office, IdDNI dni, AccountHandler accountHandler, String newPin) {
		this.id = new CommandHandler(cardId);
		this.cardId = cardId;
		this.account = office.searchClient(dni).searchAccount(accountHandler);
		this.newPin = newPin;
	}
	
	@Override
	public void execute() {
		try {
			this.card = account.searchCard(cardId);
			this.oldPin = card.getPin();
			this.card.setPin(newPin);
		} catch (IOException e) {
			Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Incorrect Pin", e);
		}
	}

	@Override
	public void undo() {
		try {
			this.card.setPin(oldPin);
		} catch (IOException e) {
			Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Incorrect Pin", e);
		}
	}

	@Override
	public void redo() {
		try {
			this.card.setPin(newPin);
		} catch (IOException e) {
			Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Incorrect Pin", e);
		}
	}

	@Override
	public Handler getId() {
		return this.id;
	}
}
