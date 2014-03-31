package es.unileon.ulebank.command;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Payment;

public class PaymentCommand implements Command {
	private Card card;
	private Payment payment;
	
	public PaymentCommand(Card card, Payment payment) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
