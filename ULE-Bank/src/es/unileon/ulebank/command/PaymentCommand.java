package es.unileon.ulebank.command;

import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.Card;

public class PaymentCommand implements Command {
	private Card card;
	
	public PaymentCommand(Card card) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
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
