package es.unileon.ulebank.assets.history;

import java.util.ArrayList;
import java.util.Date;

import es.unileon.ulebank.assets.iterator.LoanIterator;
import es.unileon.ulebank.assets.iterator.LoanIteratorDates;
import es.unileon.ulebank.assets.strategy.loan.ScheduledPayment;

public class LoanHistory {
	
	private ArrayList<ScheduledPayment> payments;
	
	public LoanHistory() {
		this.payments = new ArrayList<>();
	}
	
	public boolean addPayment(ScheduledPayment payment) {
		return this.payments.add(payment);
	}
	
	public boolean addAllPayments(ArrayList<ScheduledPayment> payments){
		return this.payments.addAll(payments);
	}
	 
	public boolean removePayment(ScheduledPayment payment) {
		return this.payments.remove(payment);
	}
	
	public LoanIteratorDates iterator(Date startDate, Date endDate) {
		return new LoanIteratorDates(this.payments, startDate, endDate);
	}
	
	public LoanIterator iterator() {
		return new LoanIterator(this.payments);
	}
}
