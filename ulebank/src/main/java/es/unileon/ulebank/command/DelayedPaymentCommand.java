package es.unileon.ulebank.command;

import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.handler.Handler;

public class DelayedPaymentCommand implements Command {
	private Handler idCommand;
	private Loan loan;
	private double debt;

	public DelayedPaymentCommand(Handler idCom, Loan loan) {
		this.idCommand = idCom;
		this.loan = loan;
		this.debt = loan.getDebt();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.loan.delayedPayment();

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		this.loan.setDebt(debt);

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		this.loan.delayedPayment();

	}

	@Override
	public Handler getId() {
		// TODO Auto-generated method stub
		return idCommand;
	}

}
