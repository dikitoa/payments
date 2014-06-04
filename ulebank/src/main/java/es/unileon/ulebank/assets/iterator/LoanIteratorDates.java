package es.unileon.ulebank.assets.iterator;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import es.unileon.ulebank.assets.strategy.loan.ScheduledPayment;

public class LoanIteratorDates implements Iterator<ScheduledPayment> {

	private List<ScheduledPayment> loanPayments;
	private int position;
	private Date startDate;
	private Date endDate;

	public LoanIteratorDates(List<ScheduledPayment> loanPayments, Date startDate,
			Date endDate) {
		this.loanPayments = loanPayments;
		this.startDate = startDate;
		this.endDate = endDate;
		this.position = 0;
	}

	@Override
	public boolean hasNext() {
		while(this.position < this.loanPayments.size()){
			ScheduledPayment payment = this.loanPayments.get(this.position);
			if(isWithinRange(payment.getExpiration())){
				return true;
			}else{
				this.position++;
			}
		}
		
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
		//TODO not implemented!
	}

	private boolean isWithinRange(Date testDate) {
		return ((testDate.getTime() >= this.startDate.getTime())
				&& (testDate.getTime() <= this.endDate.getTime()));
	}

}
