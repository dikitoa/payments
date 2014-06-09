package es.unileon.ulebank.account.liquidation;

import java.util.Date;

import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.history.Transaction;
import es.unileon.ulebank.history.TransactionException;

public interface AbstractLiquidationFee<T> {

	public boolean addFeeCase(AbstractFeeCase<T> feeCase);

	public Transaction calculateFee(Date min, Date max)
			throws TransactionException;

	public Handler getId();
}