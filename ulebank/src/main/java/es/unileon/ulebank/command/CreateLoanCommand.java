package es.unileon.ulebank.command;

import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.exceptions.LoanException;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.assets.support.LoanList;
import es.unileon.ulebank.assets.support.PaymentPeriod;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.fees.FeeStrategy;
import es.unileon.ulebank.fees.InvalidFeeException;
import es.unileon.ulebank.fees.LinearFee;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateLoanCommand implements Command {
	
	
	private Handler idCommand;
	private Handler idLoan;
	private FeeStrategy cancelCommission;
	private FeeStrategy studyCommission;
	private FeeStrategy modifyCommission;
	private FeeStrategy openningCommission;
	private double initialCapital;
	private double interest;
	private PaymentPeriod paymentPeriod;
	private int amortizationTime;
	private Account account;
	
	private Loan loan;

	private LoanList<Loan> loanList;

	public CreateLoanCommand(Handler idCommand, Handler idLoan, double initialCapital,
			double interest, PaymentPeriod paymentPeriod, int amortizationTime, LoanList<Loan> loanList) throws InvalidFeeException {
		
		this.idCommand = idCommand;
		this.idLoan = idLoan;
		this.cancelCommission = new LinearFee(0,0);
		this.studyCommission = new LinearFee(0,0);
		this.modifyCommission = new LinearFee(0,0);
		this.openningCommission = new LinearFee(0,0);
		this.initialCapital = initialCapital;
		this.interest = interest;
		this.paymentPeriod = paymentPeriod;
		this.amortizationTime = amortizationTime;
		this.loanList = loanList;
	}

	@Override
	public void execute() {
		try {
			this.loan = new Loan(this.idLoan, this.initialCapital, this.interest,
					this.paymentPeriod, this.amortizationTime, this.account);
		} catch (LoanException e) {
			e.printStackTrace();
		} catch (InvalidFeeException ex) {
                Logger.getLogger(CreateLoanCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
		
		this.loanList.addLoan(this.loan);

	}

	@Override
	public void undo() {
		this.loanList.removeLoan(this.loan);
	}

	@Override
	public void redo() {
		this.loanList.addLoan(this.loan);
	}

	@Override
	public Handler getId() {
		return this.idCommand;
	}

}
