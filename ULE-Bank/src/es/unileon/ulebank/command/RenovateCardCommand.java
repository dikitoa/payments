package es.unileon.ulebank.command;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.strategy.StrategyCommissionCreditRenovate;
import es.unileon.ulebank.strategy.StrategyCommissionDebitRenovate;

/**
 * @author Israel
 */
public class RenovateCardCommand implements Command {
	private Card card;
	
	public RenovateCardCommand(Card card) {
		this.card = card;
	}
	
	@Override
	public void execute() {
		this.card.setExpirationDate(card.generateExpirationDate());
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
