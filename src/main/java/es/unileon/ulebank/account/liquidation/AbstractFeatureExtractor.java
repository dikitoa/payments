package es.unileon.ulebank.account.liquidation;

import java.util.Date;

import es.unileon.ulebank.account.Account;

public interface AbstractFeatureExtractor<T> {

	public String getFeatureName();

	public void updateFeature(Account account, Date min, Date max);

	public T getFeature();

	public T generateRandomFeature();
}