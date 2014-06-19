package es.unileon.ulebank.service;

import java.util.List;

import es.unileon.ulebank.domain.Accounts;

public interface AccountManager {
	public void save(Accounts account);
	public void delete(Accounts account);
	public Accounts search(String accountHandler);
	public List<Accounts> getAccounts(String id);
}
