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
public class ReplacementCardCommand implements Command {
	private Handler id;
	private Card card;
	private CardHandler cardId;
	private Account account;
	private String oldPin;
	private String newPin;
	private String oldCvv;
	private String newCvv;
	
	public ReplacementCardCommand(CardHandler cardId, Account account) {
		this.cardId = cardId;
		this.account = account;
	}
	
	@Override
	public void execute() {
		try {
			this.card = account.searchCard(cardId);
			this.oldPin = card.getPin();
			this.newPin = card.generatePinCode();
			this.card.setPin(newPin);
			this.oldCvv = card.getCvv();
			this.newCvv = card.generateCVV();
			this.card.setCvv(newCvv);
		} catch (IOException e) {
			Logger.getLogger(ReplacementCardCommand.class.toString()).log(Level.SEVERE, "Incorrect Pin");
		}
		
	}

	@Override
	public void undo() {
		try {
			this.card.setCvv(oldCvv);
			this.card.setPin(oldPin);
		} catch (IOException e) {
			Logger.getLogger(ReplacementCardCommand.class.toString()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void redo() {
		try {
			this.card.setCvv(newCvv);
			this.card.setPin(newPin);
		} catch (IOException e) {
			Logger.getLogger(ReplacementCardCommand.class.toString()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public Handler getId() {
		return this.id;
	}
}
