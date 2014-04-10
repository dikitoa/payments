package es.unileon.ulebank.command;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.Account;
import es.unileon.ulebank.payments.Card;

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
	
	public ModifyPinCommand(CardHandler cardId, Account account, String newPin) {
		this.cardId = cardId;
		this.account = account;
		this.newPin = newPin;
	}
	
	@Override
	public void execute() {
		try {
			this.card = account.searchCard(cardId);
			this.oldPin = card.getPin();
			card.setPin(newPin);
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
