package es.unileon.ulebank.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.domain.AccountAuthorized;
import es.unileon.ulebank.domain.AccountAuthorizedId;
import es.unileon.ulebank.domain.Accounts;
import es.unileon.ulebank.repository.AccountAuthorizedDao;

@Repository(value = "AccountAuthorizedDao")
public class JPAAccountAuthorizedDao implements AccountAuthorizedDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly=false)
    @Override
    public boolean persistRelation(Client c, Accounts a) {
        AccountAuthorized aa = new AccountAuthorized();
        aa.setAccount(a);
        aa.setAuthorized(c);
        aa.setAccountId(new AccountAuthorizedId(a.getAccountNumber(), c.getId()));
        entityManager.persist(aa);
        return true;
    }

    @Transactional(readOnly=false)
    @Override
    public boolean mergeRelation(Client c, Accounts a) {
        AccountAuthorized aa = new AccountAuthorized();
        aa.setAccount(a);
        aa.setAuthorized(c);
        aa.setAccountId(new AccountAuthorizedId(a.getAccountNumber(), c.getId()));
        entityManager.merge(aa);
        return true;
    }

    @Override
    public boolean removeRelation(Client c, Accounts a) {
        return false;
    }

}
