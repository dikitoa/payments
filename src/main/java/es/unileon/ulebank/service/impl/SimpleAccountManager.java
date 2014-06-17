package es.unileon.ulebank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.unileon.ulebank.domain.Accounts;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.repository.AccountDao;
import es.unileon.ulebank.service.AccountManager;

@Component
public class SimpleAccountManager implements AccountManager {
	@Autowired
	private AccountDao accountDao;

	@Override
	public void save(Accounts account) {
		this.accountDao.persist(account);
	}

	@Override
	public void delete(Handler accountHandler) {
		this.accountDao.remove(accountHandler);
	}

	@Override
	public Accounts search(String accountHandler) {
		return this.accountDao.findById(accountHandler);
	}

	@Override
	public List<Accounts> getAccounts() {
		return this.accountDao.getAccountList();
	}
	
	
}
