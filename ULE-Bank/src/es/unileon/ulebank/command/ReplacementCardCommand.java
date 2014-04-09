package es.unileon.ulebank.command;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.strategy.StrategyCommissionCreditRenovate;
import es.unileon.ulebank.strategy.StrategyCommissionDebitRenovate;

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
		try {
			this.card.setPin(card.generatePinCode());
		} catch (IOException e) {
			Logger.getLogger(ReplacementCardCommand.class.toString()).log(Level.SEVERE, "Incorrect Pin");
		}
		this.card.setCvv(card.generateCVV());
		
		switch (card.getCardType()) {
		case DEBIT:
			this.card.setStrategy(new StrategyCommissionDebitRenovate());
			break;
		case CREDIT:
			this.card.setStrategy(new StrategyCommissionCreditRenovate());
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
