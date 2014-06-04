package es.unileon.ulebank.assets.iterator;

import java.util.Iterator;
import java.util.List;

import es.unileon.ulebank.assets.strategy.loan.ScheduledPayment;

public class LoanIterator implements Iterator<ScheduledPayment> {

	private List<ScheduledPayment> loanPayments;
	private int position;
	
	public LoanIterator(List<ScheduledPayment> loanPayments) {
		this.loanPayments = loanPayments;
		this.position = 0;
	}

	@Override
	public boolean hasNext() {
		if(this.position < this.loanPayments.size()) return true;
		return false;
	}

	@Override
	public ScheduledPayment next() {
		ScheduledPayment payment = this.loanPayments.get(this.position);
		this.position++;
		return payment;
	}

	@Override
	public void remove() {
		
	}

}
