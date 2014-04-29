package es.unileon.ulebank.command;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.CardType;

public class GenerateContractCommand implements Command {
	private Handler id;
	private CardType type;
	
	public GenerateContractCommand(CardType type) {
		this.type = type;
	}
	
	@Override
	public void execute() {
		File file = null;
		Desktop desktop = Desktop.getDesktop();
		try {
			switch (type) {
			case DEBIT:
				file = new File("contratos/debitcontract.pdf");
				desktop.open(file);
				break;
			case CREDIT:
				file = new File("contratos/creditcontract.pdf");
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
			Logger.getLogger(GenerateContractCommand.class.toString()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void undo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void redo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Handler getId() {
		return this.id;
	}
	
}
