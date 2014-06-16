package es.unileon.ulebank.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.domain.AccountTitular;
import es.unileon.ulebank.domain.AccountTitularId;
import es.unileon.ulebank.domain.Accounts;
import es.unileon.ulebank.repository.AccountClientDao;

@Repository(value="AccountClientDao")
public class JPAAccountClientDao implements AccountClientDao{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional(readOnly=false)
    @Override
    public boolean persistRelation(Client c, Accounts a) {
        AccountTitular aa = new AccountTitular();
        aa.setAccount(a);
        aa.setAuthorized(c);
        aa.setAccountId(new AccountTitularId(a.getAccountNumber(), c.getId()));
        entityManager.persist(aa);
        return true;
    }

    @Transactional(readOnly=false)
    @Override
    public boolean mergeRelation(Client c, Accounts a) {
        AccountTitular aa = new AccountTitular();
        aa.setAccount(a);
        aa.setAuthorized(c);
        aa.setAccountId(new AccountTitularId(a.getAccountNumber(), c.getId()));
        entityManager.merge(aa);
        return true;
    }

    @Override
    public boolean removeRelation(Client c, Accounts a) {
        return false;
    }

}
