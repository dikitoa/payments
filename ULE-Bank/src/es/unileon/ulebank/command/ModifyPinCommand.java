package es.unileon.ulebank.command;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.Card;

/**
 * @author Israel
 */
public class ModifyPinCommand implements Command {
	private Card card;
	private String newPin;
	
	public ModifyPinCommand(Card card, String newPin) {
		this.card = card;
		this.newPin = newPin;
	}
	
	@Override
	public void execute() {
		try {
			this.card.setPin(newPin);
		} catch (IOException e) {
			Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Incorrect Pin");
		}
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Handler getId() {
		// TODO Auto-generated method stub
		return null;
	}
}
