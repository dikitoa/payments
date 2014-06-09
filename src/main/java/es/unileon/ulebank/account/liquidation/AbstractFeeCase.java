package es.unileon.ulebank.account.liquidation;

import es.unileon.ulebank.history.Transaction;
import es.unileon.ulebank.history.TransactionException;

public interface AbstractFeeCase<T> {

	public boolean triggerCase();

	public Transaction calculateAmount() throws TransactionException;

	public Features<T> getFeatures();
}