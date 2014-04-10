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
public class RenovateCardCommand implements Command {
	private Handler id;
	private CardHandler cardId;
	private Account account;
	private Card card;
	private String oldCvv;
	private String newCvv;
	private String oldExpirationDate;
	private String newExpirationDate;
	
	public RenovateCardCommand(CardHandler cardId, Account account) {
		this.cardId = cardId;
	}
	
	@Override
	public void execute() {
		try {
			this.account.searchCard(cardId);
			this.newCvv = this.card.getCvv();
			this.newExpirationDate = card.generateExpirationDate();
			this.card.setExpirationDate(newExpirationDate);
			this.newCvv = card.generateCVV();
			this.card.setCvv(newCvv);
		} catch (IOException e) {
			Logger.getLogger(RenovateCardCommand.class.toString()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void undo() {
		try {
			this.card.setCvv(oldCvv);
			this.card.setExpirationDate(oldExpirationDate);
		} catch (IOException e) {
			Logger.getLogger(RenovateCardCommand.class.toString()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void redo() {
		try {
			this.card.setCvv(newCvv);
			this.card.setExpirationDate(newExpirationDate);
		} catch (IOException e) {
			Logger.getLogger(RenovateCardCommand.class.toString()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public Handler getId() {
		return this.id;
	}
}
