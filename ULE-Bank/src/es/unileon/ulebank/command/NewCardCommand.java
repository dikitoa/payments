package es.unileon.ulebank.command;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.strategy.StrategyCommissionEmissionCredit;
import es.unileon.ulebank.strategy.StrategyCommissionEmissionDebit;

/**
 * @author Israel
 */
public class NewCardCommand implements Command {

	private Card card;
//	private Client owner;
//	private Account account;
	
	public NewCardCommand(/*Client owner, */Card card/*, Account account*/) {
		this.card = card;
//		this.account = account;
//		this.owner = owner;
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
		File file = null;
		Desktop desktop = Desktop.getDesktop();
		try {
			switch (card.getCardType()) {
			case DEBIT:
				file = new File ("contratos/debitcontract.pdf");
				desktop.open(file);
				break;
			case CREDIT:
				file = new File ("contratos/creditcontract.pdf");
				desktop.open(file);
				break;
			case PURSE:
				
				break;
			case REVOLVING:
				
				break;
			default:
				break;
			}
		} catch (IOException e) {
			Logger.getLogger(NewCardCommand.class.toString()).log(Level.SEVERE, null, e);
		}
		
	}
}
