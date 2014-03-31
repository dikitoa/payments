package es.unileon.ulebank.command;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.strategy.StrategyCommissionEmissionCredit;
import es.unileon.ulebank.strategy.StrategyCommissionEmissionDebit;

public class NewCardCommand implements Command {

	private Card card;
//	private Handler owner;
//	private Account account;
	
	public NewCardCommand(Card card) {
		this.card = card;
	}
	
	@Override
	public void execute() {
		this.card.create();
//		account.addCard(this.card);
		switch (card.getCardType()) {
		case DEBIT:
			this.card.setStrategy(new StrategyCommissionEmissionDebit());
			break;
		case CREDIT:
			this.card.setStrategy(new StrategyCommissionEmissionCredit());
			break;
		case PURSE:
			
			break;
		case REVOLVING:
			
			break;
		default:
			break;
		}
	}
	
	public void generateContract(Card card) {
		switch (card.getCardType()) {
		case DEBIT:
			
			break;
		case CREDIT:
			
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
