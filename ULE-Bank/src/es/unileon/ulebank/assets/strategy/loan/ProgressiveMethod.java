package es.unileon.ulebank.assets.strategy.loan;

import java.util.ArrayList;
import java.util.Calendar;

import es.unileon.ulebank.assets.Loan;

/**
 * Implementation of strategy for calculated all fee of the loan following the
 * progressive method
 * 
 * *************************
 * Formulate of the reason * 
 * *************************
 * 
 * v1.0 Initial version
 **/
public class ProgressiveMethod implements StrategyLoan
{
	/**
	 * Object reference to the loan that wait calculating the fees
	 */
	private Loan loan;
	
	private ArrayList<ScheduledPayment> payments;
	
	/**
	 * Reason of the geometric progression
	 * **/
	private double reason;

	/**
	 * Constructor of ProgressiveMethod
	 * @param loan Loan that wait calculating the fees 
	 **/
	public ProgressiveMethod(Loan loan, double reason) {
		this.reason = reason;
		this.loan = loan;
		this.payments = new ArrayList<ScheduledPayment>();
	}
	
	/**
	 * Method used to calculating the effective interest of do the fees
	 * 
	 * @return Double with the value of this effective interest
	 **/
	public double calculateInterestEf() {
		double interestEf = 0;
		interestEf = (this.loan.getInterest() / 100) / (365 / 30 * this.loan.getPaymentPeriod().getPeriod());
		return interestEf;
	}
	
	/**
	 * Method used to calculating the fees of loan
	 * 
	 **/
	public double calculateMonthlyFee() {
		double fee = 0;
		double interesEf = this.calculateInterestEf();
		int numFee = (this.loan.getAmortizationTime() / this.loan.getPaymentPeriod().getTime());

		double fracc = this.loan.getAmountOfMoney()*((1+interesEf-this.reason)/1-(Math.pow((this.reason/1+interesEf), numFee)));
		fee = this.loan.getAmountOfMoney() * fracc;
		return fee;
	}
	
	/**
	 * Method used to calculating the effective interest of do the fees
	 * 
	 * @return Double with the value of this effective interest
	 **/
	public void calculateInterest() {
		int monthToAdd = 12/this.loan.getPaymentPeriod().getPeriod();
		Calendar date = Calendar.getInstance();
		double fee = this.calculateMonthlyFee();
		double interestEf = this.calculateInterestEf();
		double interest = 0;
		double amortized = 0;
		double totalCapital = fee * this.loan.getAmortizationTime();
		for (int i = 0; i < this.loan.getAmortizationTime(); i++) {
			totalCapital -= fee;
			interest = totalCapital * interestEf;
			amortized = fee - interest;
			totalCapital = round(totalCapital, 100);
			fee = round(fee, 100);
			amortized = round(amortized, 100);
			interest = round(interest, 100);
			//
			for (int j = 0; j < this.loan.getPaymentPeriod().getTime(); j++) {
				date.add(Calendar.MONTH, monthToAdd);
			}
			this.payments.add(new ScheduledPayment(date.getTime(), totalCapital, fee, interest, amortized));
		}
	}

	/**
	 * Used method to round.
	 * 
	 * @param num :- Number to round
	 * @param factor :- Number in decimal
	 * @return Numero :- Round number
	 **/
	public double round(double num, int factor) {
		num = num * factor;
		num = Math.round(num);
		num = num / factor;
		return num;
	}

	/**
	 * Method used to calculating the fees of loan and give dates for perform
	 * this payments
	 **/
	@Override
	public ArrayList<ScheduledPayment> doCalculationOfPayments() {
		ArrayList<ScheduledPayment> paymentsProgressive = new ArrayList<ScheduledPayment>();
		
		for(ScheduledPayment payment : this.payments){
			paymentsProgressive.add(payment);
		}
		
		
		int monthToAdd = 12 / this.loan.getPaymentPeriod().getPeriod();
		Calendar date = Calendar.getInstance();
		double fee = this.calculateMonthlyFee();
		double interestEf = this.calculateInterestEf();
		double interest = 0;
		double amortized = 0;
		double totalLoan = this.loan.getAmountOfMoney();
		double totalCapital = fee * this.loan.getAmortizationTime();
		for (int i = 0; i < this.loan.getAmortizationTime(); i++) {
			interest = totalLoan * (this.loan.getInterest()/100);	
			amortized = fee - interest;
			totalLoan -= amortized;
			
			totalCapital = round(totalLoan, 100);
			fee = round(fee, 100);
			amortized = round(amortized, 100);
			interest = round(interest, 100);
			//
			for (int j = 0; j < this.loan.getPaymentPeriod().getTime(); j++) {
				date.add(Calendar.MONTH, monthToAdd);
			}
			paymentsProgressive.add(new ScheduledPayment(date.getTime(),
					fee, amortized, interest, totalCapital));
		}
		
		
		this.payments = paymentsProgressive;
		return paymentsProgressive;
	}
	
	public ArrayList<ScheduledPayment> getPayments() {
		return this.payments;
	}
	
}