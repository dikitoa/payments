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
public class RenovateCardCommand implements Command {
	private Handler id;
	private CardHandler cardId;
	private Account account;
	private Card card;
	private String oldCvv;
	private String newCvv;
	private String oldExpirationDate;
	private String newExpirationDate;
	
	public RenovateCardCommand(CardHandler cardId, Office office, IdDNI dni, AccountHandler accountHandler) {
		this.id = new CommandHandler(cardId);
		this.cardId = cardId;
		this.account = office.searchClient(dni).searchAccount(accountHandler);
	}
	
	@Override
	public void execute() {
		try {
			this.card = this.account.searchCard(cardId);
			this.oldCvv = this.card.getCvv();
			this.oldExpirationDate = card.getExpirationDate();
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
