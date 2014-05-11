package es.unileon.ulebank.assets.strategy.loan;

import java.util.ArrayList;
import java.util.Calendar;

import es.unileon.ulebank.assets.Loan;

/**
 * Implementation of strategy for calculated all fee of the loan following the
 * french method
 * 
 * 
 * v1.0 Initial version
 */
public class DefaultLoanStrategy implements StrategyLoan {

	private ArrayList<ScheduledPayment> payments;

	/**
	 * Object reference to the loan that wait calculating the fees
	 */
	private Loan loan;

	/**
	 * Constructor of FrenchMethod
	 * 
	 * @param loan
	 *            Loan that wait calculating the fees
	 */
	public DefaultLoanStrategy(Loan loan) {
		this.loan = loan;
		this.payments = loan.getPayments();
	}

	@Override
	public ArrayList<ScheduledPayment> doCalculationOfPayments() {
		Calendar date = Calendar.getInstance();

		int monthToAdd = 12 / this.loan.getPaymentPeriod().getPeriod();

		if (this.payments.size() != 0) {
			// si el arraylist ya tiene elementos partimos de
			// la ultima fecha limite para pagar
			date.setTime(this.payments.get(this.payments.size() - 1)
					.getExpiration());
		}

		double fee = this.loan.getPeriodFee();
		fee = round(fee, 100);
		// double interestEf = this.calculateInterestEf();
		double interestEf = this.loan.getInterest();
		double interest = 0;
		double amortized = 0;
		double totalLoan = this.loan.getDebt();
		double totalCapital;
		boolean control = false;
		while (!control) {

			interest = totalLoan * interestEf;
			amortized = fee - interest;

			if (totalLoan > fee) {
				totalLoan -= fee;
			} else {
				fee = round(totalLoan, 100);
				totalLoan = 0;

				control = true;
			}

			totalCapital = round(totalLoan, 100);
			amortized = round(amortized, 100);
			interest = round(interest, 100);

			date.add(Calendar.MONTH, monthToAdd);
			ScheduledPayment payment = new ScheduledPayment(date.getTime(),
					fee, amortized, interest, totalCapital);
			this.payments.add(payment);
			return payments;

		}
		return payments;

	}

	public double round(double num, int factor) {
		num = num * factor;
		num = Math.round(num);
		num = num / factor;
		return num;
	}
}
