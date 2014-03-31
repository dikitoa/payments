package es.unileon.ulebank.command;

import es.unileon.ulebank.payments.Card;

public class ModifyPinCommand implements Command {
	private Card card;
	private String newPin;
	
	public ModifyPinCommand(Card card, String newPin) {
		this.card = card;
		this.newPin = newPin;
	}
	
	@Override
	public void execute() {
		this.card.setPin(newPin);
	}
}
