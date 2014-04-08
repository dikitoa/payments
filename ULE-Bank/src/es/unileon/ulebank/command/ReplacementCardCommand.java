package es.unileon.ulebank.command;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.strategy.StrategyCommissionRenovateCredit;
import es.unileon.ulebank.strategy.StrategyCommissionRenovateDebit;

/**
 * @author Israel
 */
public class ReplacementCardCommand implements Command {
	private Card card;
	
	public ReplacementCardCommand(Card card) {
		this.card = card;
	}
	
	@Override
	public void execute() {
		CardHandler newCard = new CardHandler();
		this.card.setCardId(newCard.getCardNumber());
		try {
			this.card.setPin(card.generatePinCode());
		} catch (IOException e) {
			Logger.getLogger(ReplacementCardCommand.class.toString()).log(Level.SEVERE, "Incorrect Pin");
		}
		this.card.setCvv(card.generateCVV());
		
		switch (card.getCardType()) {
		case DEBIT:
			this.card.setStrategy(new StrategyCommissionRenovateDebit());
			break;
		case CREDIT:
			this.card.setStrategy(new StrategyCommissionRenovateCredit());
			break;
		case PURSE:
			
			break;
		case REVOLVING:
			
			break;
		default:
			break;
		}
	}
}
