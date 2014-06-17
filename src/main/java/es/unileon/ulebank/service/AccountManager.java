package es.unileon.ulebank.service;

import java.util.List;

import es.unileon.ulebank.domain.Accounts;
import es.unileon.ulebank.handler.Handler;

public interface AccountManager {
	public void save(Accounts account);
	public void delete(Handler accountHandler);
	public Accounts search(String accountHandler);
	public List<Accounts> getAccounts();
}
