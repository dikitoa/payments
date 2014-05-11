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
public class FrenchMethod implements StrategyLoan {
	
	/**
	 * Object reference to the loan that wait calculating the fees
	 */
	private Loan loan;
	
	private ArrayList<ScheduledPayment> payments;
	
	/**
	 * Constructor of FrenchMethod
	 * 
	 * @param loan Loan that wait calculating the fees
	 */
	public FrenchMethod(Loan loan) {
		this.loan = loan;
		this.setPayments(new ArrayList<ScheduledPayment>());
	}

	/**
	 * Method used to calculating the effective interest of do the fees
	 * 
	 * @return Double with the value of this effective interest
	 */
	public double calculateInterestEf() {
		double interestEf = 0;
		interestEf = (this.loan.getInterest() / 100)
				/ this.loan.getPaymentPeriod().getPeriod();
		return interestEf;
	}

	/**
	 * Method used to calculating the fees of loan
	 * 
	 * @return Double with the value of this fees of loan
	 */
	public double calculateMonthlyFee() {
		double fee = 0;
		double interesEf = this.calculateInterestEf();
		int numFee = (this.loan.getAmortizationTime() / this.calculatePayment());
		double fracc = ((Math.pow((1 + interesEf), numFee)) * interesEf)
				/ (Math.pow(1 + interesEf, numFee) - 1);
		fee = this.loan.getAmountOfMoney() * fracc;
		return fee;
	}

	/**
	 * Method used to invert the payment period for calculated the total number
	 * of payments for repayment the loan
	 * 
	 * @return Integer with the value of number of payments in one year
	 */
	private int calculatePayment() {
		int num = this.loan.getPaymentPeriod().getPeriod();
		if (num == 12)
			return 1;
		else if (num == 6)
			return 2;
		else if (num == 4)
			return 3;
		else if (num == 2)
			return 6;
		else if (num == 1)
			return 12;
		return 0;
	}

	/**
	 * Method used to calculating the fees of loan and give dates for perform
	 * this payments
	 * @return 
	 */
	@Override
	public ArrayList<ScheduledPayment> doCalculationOfPayments() {
		ArrayList<ScheduledPayment> paymentsFrench = 
				new ArrayList<>();
				
		int monthToAdd = 12 / this.loan.getPaymentPeriod().getPeriod();
		Calendar date = Calendar.getInstance();
		double fee = this.calculateMonthlyFee();
		double interestEf = this.calculateInterestEf();
		double interest = 0;
		double amortized = 0;
		double totalLoan = this.loan.getAmountOfMoney();
		double totalCapital = fee * this.loan.getAmortizationTime();
		for (int i = 0; i < this.loan.getAmortizationTime(); i++) {
			interest = totalLoan * interestEf;
			amortized = fee - interest;
			totalLoan -= amortized;

			totalCapital = round(totalLoan, 100);
			fee = round(fee, 100);
			amortized = round(amortized, 100);
			interest = round(interest, 100);
			//
			for (int j = 0; j < this.calculatePayment(); j++) {
				date.add(Calendar.MONTH, monthToAdd);
			}
			paymentsFrench.add(new ScheduledPayment(date.getTime(), fee,
					amortized, interest, totalCapital));
		}
		
		this.setPayments(paymentsFrench);
		return paymentsFrench;
	}

	/**
	 * Metodo utilizado para redondear un numero. Se deben de pasa el numero a
	 * redondear y el factor.
	 * 
	 * @param num
	 *            Numero que se quiere redondear
	 * @param factor
	 *            Numero de decimales a los que se quiere redondear
	 * @return Numero redondeado
	 */
	public double round(double num, int factor) {
		num = num * factor;
		num = Math.round(num);
		num = num / factor;
		return num;
	}

	public ArrayList<ScheduledPayment> getPayments() {
		return payments;
	}

	public void setPayments(ArrayList<ScheduledPayment> payments) {
		this.payments = payments;
	}
	

}
