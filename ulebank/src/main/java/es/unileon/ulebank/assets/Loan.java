package es.unileon.ulebank.assets;

import java.util.ArrayList;
import java.util.Date;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.history.LoanHistory;
import es.unileon.ulebank.assets.iterator.LoanIterator;
import es.unileon.ulebank.assets.iterator.LoanIteratorDates;
import es.unileon.ulebank.assets.strategy.loan.FrenchMethod;
import es.unileon.ulebank.assets.strategy.loan.ScheduledPayment;
import es.unileon.ulebank.assets.strategy.loan.StrategyLoan;
import es.unileon.ulebank.assets.support.PaymentPeriod;
import es.unileon.ulebank.exceptions.LoanException;
import es.unileon.ulebank.fees.FeeStrategy;
import es.unileon.ulebank.fees.InvalidFeeException;
import es.unileon.ulebank.fees.LinearFee;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.history.GenericTransaction;
import es.unileon.ulebank.history.Transaction;
import es.unileon.ulebank.history.TransactionException;
import es.unileon.ulebank.history.TransactionType;

// TODO PREGUNTAR A CAMINO COMO ACTUALIZAR DEBT CUANDO PASIVOS REALIZA EL PAGO DE LA CUOTA

public class Loan implements FinancialProduct {
	/**
	 * Type of time period used for the effective interest
	 */
	private PaymentPeriod paymentPeriod;

	/**
	 * Interest applicated to the loan
	 */
	private double interest;

	/**
	 * Number of fees to resolve the loan
	 */
	private int amortizationTime;

	/**
	 * Amount of money required for the user
	 */
	private double initialCapital;

	/**
	 * Amount of money that the user have not payed yet
	 */
	private double debt;

	/**
	 * Commission applicated in the case of the client do not pay the fee in the
	 * correct time
	 */
	private double delayedPaymentInterest;

	/**
	 * Unique identificator for the loan
	 */
	private Handler idLoan;

	/**
	 * Says the strategy used for the loan
	 */
	private StrategyLoan strategy;

	/**
	 * Money that you have already payed
	 */
	private double amortized;

	/**
	 * Arraylist where you store the fees with all data
	 */
	private ArrayList<ScheduledPayment> payments;

	/**
	 * all commisions that you have in the contract
	 */
	private FeeStrategy cancelCommission;
	private FeeStrategy studyCommission;
	private FeeStrategy openningCommission;
	private FeeStrategy modifyCommission;

	private Account account;

	private LoanHistory loanHistory;

	/*
	 * internal index used to have the possibility to change the arraylist of
	 * the payments
	 */
	private int arrayListIndex;

	/*
	 * Fixed fee that you have to pay every month
	 */
	private double periodFee;

	/**
	 * Constructor of Loan class
	 * 
	 * @param idFinancialProduct
	 *            Unique identificator for the loan
	 * @param initialCapital
	 *            double Amount of money requested for the client
	 * @param interest
	 *            double Interest applicated to the loan
	 * @param paymentPeriod
	 *            Type of time period used for the effective interest
	 * @param month
	 *            int Number of months that the loan is open
	 * @param commissions
	 *            Type of commissions applicated to the loan
	 * 
	 */
	public Loan(Handler idLoan, double initialCapital,
			double interest, PaymentPeriod paymentPeriod, int amortizationTime,
			Account account) throws LoanException, InvalidFeeException {
		StringBuffer exceptionMessage = new StringBuffer();

		this.loanHistory = new LoanHistory();
		this.idLoan = idLoan;
		this.debt = initialCapital;

		if (interest >= 0 && interest <= 1) {
			this.interest = interest;
		} else {
			exceptionMessage
					.append("The interest value must be a value between 0 and 1\n");
		}
                
        this.cancelCommission = new LinearFee(interest,0);
		this.studyCommission = new LinearFee(interest,0);
		this.cancelCommission = new LinearFee(interest,0);
		this.modifyCommission = new LinearFee(interest,0);
		this.openningCommission = new LinearFee(interest,0);

		if (!this.openningCommission.getClass().equals(LinearFee.class)) {
			this.debt += openningCommission.getFee(debt);//calculateCommission();
		} else {
			this.debt += this.debt * (openningCommission.getFee(0));//calculateCommission());
		}

		if (!this.studyCommission.getClass().equals(LinearFee.class)) {
			this.debt += studyCommission.getFee(0);//calculateCommission();
		} else {
			this.debt += this.debt * (studyCommission.getFee(debt));//calculateCommission());
		}

		this.paymentPeriod = paymentPeriod;
		this.amortizationTime = amortizationTime;
		this.payments = new ArrayList<ScheduledPayment>();
		this.initialCapital = this.debt;
		this.strategy = new FrenchMethod(this);
		this.payments = this.strategy.doCalculationOfPayments();
		this.loanHistory.addAllPayments(this.payments);
		this.account = account;
		this.arrayListIndex = 0;
		if (exceptionMessage.length() > 1)
			throw new LoanException(exceptionMessage.toString());

	}

	/**
	 * This method returns an ArrayList with all fees of the loan
	 * 
	 * @return payments The arraylist with fees
	 */
	public ArrayList<ScheduledPayment> calcPayments() {
		this.payments = this.strategy.doCalculationOfPayments();
		return this.payments;
	}

	/**
	 * This method allows to know what is the price that one person must pay if
	 * he decide cancel the loan. This amount of money is the total amount of
	 * money for cancel the loan
	 * 
	 * @return double with amount of money to pay
	 * @throws LoanException
	 */
	public double cancelLoan() throws LoanException {
		StringBuffer msgException = new StringBuffer();
		double feeCancel = 0;
		if (this.cancelCommission.getClass().equals(LinearFee.class)) {
			feeCancel = this.cancelCommission.getFee(debt);
		} else {
			feeCancel += this.cancelCommission.getFee(debt)//calculateCommission()
					+ this.debt;
		}

		// We carry out the transaction to discount the money from the account
		// of
		// the customer
		try {
			Transaction transactionCharge = new GenericTransaction(feeCancel,
					new Date(), "liquidate", TransactionType.CHARGE);
			transactionCharge.setEffectiveDate(new Date(System
					.currentTimeMillis()));
			this.account.doWithdrawal(transactionCharge);

		} catch (TransactionException transactionException) {
			msgException.append("Transaction error.\n");
			msgException.append(transactionException.getMessage());
		}

		if (msgException.length() > 0) {
			throw new LoanException(msgException.toString());
		}

		// If the exception is not launched the payment is made and will zero
		// debt.
		this.debt = 0;

		return feeCancel;

	}

	public void delayedPayment() {
		boolean isPaid = isNotPaid();
		if (isPaid && this.debt > 0)
			this.debt = this.debt + this.debt * delayedPaymentInterest;

	}

	/**
	 * Method that is necesary when the interest change
	 */
	@Override
	public void update() {
		this.payments = this.strategy.doCalculationOfPayments();
		this.loanHistory.addAllPayments(this.payments);
	}

	public double liquidate(double quantity) throws LoanException {
		StringBuffer exceptionMessage = new StringBuffer();
		double comission = 0;

		if (quantity <= this.debt) {
			exceptionMessage
					.append("The money to liquidate is more than the debt!");
		}

		if (exceptionMessage.length() > 0) {
			throw new LoanException(exceptionMessage.toString());
		}

		// We carry out the transaction to discount the money from the account
		// of
		// the customer.
		try {
			Transaction transactionCharge = new GenericTransaction(quantity,
					new Date(), "liquidate", TransactionType.CHARGE);
			transactionCharge.setEffectiveDate(new Date(System
					.currentTimeMillis()));
			this.account.doWithdrawal(transactionCharge);

		} catch (TransactionException transactionException) {
			exceptionMessage.append("Transaction error.\n");
			exceptionMessage.append(transactionException.getMessage());
		}

		// If the transaction is unsuccessful we launched the exception.
		if (exceptionMessage.length() > 0) {
			throw new LoanException(exceptionMessage.toString());
		}

		// Si la transaccion se realizo con exito descontamos el dinero de la
		// deuda
		
		comission = this.modifyCommission.getFee(quantity);//quantity * this.modifyCommission.calculateCommission();
		quantity -= comission;
		this.debt -= quantity;
		this.setAmortized(this.initialCapital - this.debt);
		update();

		return comission;
	}
	
	public PaymentPeriod getPaymentPeriod() {
		return paymentPeriod;
	}

	public void setPaymentPeriod(PaymentPeriod paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
	}

	public double getInterest() {
		return this.interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public int getAmortizationTime() {
		return amortizationTime;
	}

	public void setAmortizationTime(int amortizationTime) {
		this.amortizationTime = amortizationTime;
	}

	public double getAmountOfMoney() {
		return initialCapital;
	}

	public void setAmountOfMoney(double amountOfMoney) {
		this.initialCapital = amountOfMoney;
	}

	public double getDebt() {
		return this.debt;
	}

	public void setDebt(double debt) {
		this.debt = debt;
		update();
	}

	public double getDelayedPaymentInterest() {
		return this.delayedPaymentInterest;
	}

	public void setDelayedPaymentInterest(double delayedPaymentInterest) {
		this.delayedPaymentInterest = delayedPaymentInterest;
	}

	public StrategyLoan getStrategy() {
		return this.strategy;
	}

	public void setStrategy(StrategyLoan strategy) {
		this.strategy = strategy;
		update();
	}

	@Override
	public Handler getIdFinancialProduct() {
		return this.idLoan;
	}

	public void setIdFinancialProduct(Handler idLoan) {
		this.idLoan = idLoan;
	}

	public ArrayList<ScheduledPayment> getPayments() {
		return this.payments;
	}

	/**
	 * Method used to paying a couta if payment is not made
	 * 
	 * @param index
	 *            indicates the number of payments to be amortized
	 */
	public void paid(int index) {
		if (index >= 0 && index < payments.size()) {
			ScheduledPayment payment = payments.get(index);
			if (!payment.isPaid()) {
				this.debt -= payment.getAmortization();
				payment.setPaid(true);
			}
		}
	}

	@Override
	public String toString() {
		StringBuffer output = new StringBuffer();
		output.append("ID: " + this.idLoan.toString() + "\n");
		output.append("Debt: " + this.debt + "\n");
		output.append("Payment period: " + this.paymentPeriod + "\n");
		if (!this.openningCommission.getClass().equals(LinearFee.class))
			output.append("Comision for opening "
					+ this.openningCommission.getFee(debt));//calculateCommission() + "\n");
		else
			output.append("Comision for opening "
					+ this.openningCommission.getFee(debt));//calculateCommission() + "\n");

		return output.toString();
	}

	/**
	 * Method that returns true if any month has not been paid, false if all
	 * were paid
	 * 
	 * @return true if it has not paid any month
	 */
	public boolean isNotPaid() {
		boolean isNotPaid = false;

		for (int i = 0; i < this.payments.size() && !isNotPaid; i++) {
			ScheduledPayment payment = this.payments.get(i);
			if (!payment.isPaid()) {
				isNotPaid = true;
			}
		}

		return isNotPaid;
	}

	public double getAmortized() {
		return amortized;
	}

	public void setAmortized(double amortized) {
		this.amortized = amortized;
	}

	public LoanIteratorDates iterator(Date startDate, Date endDate) {
		return new LoanIteratorDates(this.payments, startDate, endDate);
	}

	public LoanIterator iterator() {
		return new LoanIterator(this.payments);
	}

	public void makeNormalPayment(double amount) {
		// lanzo alguna excepcion o que?
		// pongo la condicion de que el pago se haga entre los meses indicados?
		if (amount == periodFee && payments.size() > 0
				&& arrayListIndex < payments.size()) {
			ScheduledPayment hesGonnaPay = this.payments.get(arrayListIndex);
			hesGonnaPay.setPaid(true);
			this.debt = debt - amount;
			// pongo la fecha de hoy, pero deberia dejar que se le pase por
			// parametro?
			hesGonnaPay.setPaymentDate(new Date());
			arrayListIndex++;
		}

	}

	// metodo de pago de cantidades diferentes a la mensual calculada
	public void makeAbnormalPayment(double amount) {
		// excepciones??
		// pongo la condicion de que el pago se haga entre los meses indicados?
		if (amount < this.debt && amount > 0) {
			ScheduledPayment hesGonnaPay = this.payments.get(arrayListIndex);

			double interest = 0;
			double amortized = 0;
			double totalLoan = this.debt;
			double totalCapital = this.debt;

			interest = amount * this.interest;
			amortized = amount - interest;
			if (totalLoan > amount) {
				totalLoan -= amount;
			} else {
				totalLoan = 0;
			}
			totalCapital = round(totalLoan, 100);
			amortized = round(amortized, 100);
			interest = round(interest, 100);
			hesGonnaPay.setAmortization(amortized);
			hesGonnaPay.setInterests(interest);
			hesGonnaPay.setOutstandingCapital(totalCapital);
			// Cambiar
			hesGonnaPay.setPaymentDate(new Date());

			hesGonnaPay.setPaid(true);
			// hesGonnaPay.setOutstandingCapital(outstandingCapital);
			this.debt = debt - amount;
			hesGonnaPay.setImportOfTerm(amount);
		}

		// borro todos los elementos en adelante porque hay que recalcular
		int auxSize = this.payments.size();
		for (int auxInd = arrayListIndex + 1; auxInd < auxSize; auxInd++) {
			this.payments.remove(this.payments.get(this.payments.size() - 1));
		}
		// se recalcula todo
		this.strategy.doCalculationOfPayments();
		// actualizo el indice del arrayList
		++arrayListIndex;

	}

	public double calculateMonthlyFee() {
		double fee = 0;
		double interesEf = this.calculateEffectiveInterestRate();
		int numFee = (this.getAmortizationTime() / this.calculatePayment());
		double fracc = ((Math.pow((1 + interesEf), numFee)) * interesEf)
				/ (Math.pow(1 + interesEf, numFee) - 1);
		fee = this.getAmountOfMoney() * fracc;
		return fee;
	}

	/**
	 * Method used to invert the payment period for calculated the total number
	 * of payments for repayment the loan
	 * 
	 * @return Integer with the value of number of payments in one year
	 */
	private int calculatePayment() {
		int num = this.getPaymentPeriod().getPeriod();
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
	 * Method used to calculating the effective interest of do the fees
	 * 
	 * @return Double with the value of this effective interest
	 */
	public double calculateEffectiveInterestRate() {
		return Math.pow(1 + (this.interest / this.paymentPeriod.getPeriod()),
				this.paymentPeriod.getPeriod()) - 1;
	}

	public double round(double num, int factor) {
		num = num * factor;
		num = Math.round(num);
		num = num / factor;
		return num;
	}

	public double getPeriodFee() {
		return periodFee;
	}
}
