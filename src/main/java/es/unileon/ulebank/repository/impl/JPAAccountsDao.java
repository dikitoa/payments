package es.unileon.ulebank.repository.impl;

// Generated Jun 15, 2014 1:06:41 AM by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.domain.AccountAuthorized;
import es.unileon.ulebank.domain.AccountAuthorizedId;
import es.unileon.ulebank.domain.AccountTitular;
import es.unileon.ulebank.domain.AccountTitularId;
import es.unileon.ulebank.domain.Accounts;
import es.unileon.ulebank.domain.History;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.history.HistoryTransaction;
import es.unileon.ulebank.history.HistoryTransactionId;
import es.unileon.ulebank.history.Transaction;
import es.unileon.ulebank.repository.AccountDao;

/**
 * Home object for domain model class Accounts.
 * 
 * @see es.unileon.ulebank.domain.Accounts
 * @author Hibernate Tools
 */
@Stateless
@Repository(value = "accountsHomeDao")
public class JPAAccountsDao implements AccountDao{

    private static final Log log = LogFactory.getLog(JPAAccountsDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = false)
    public void persist(Accounts transientInstance) {
        log.debug("persisting Accounts instance");
        try {

//            entityManager.persist(transientInstance.getHandler());
            entityManager.persist(transientInstance
                    .getHistoryByDirectDebitHistory().getGenericHandler());
            entityManager.persist(transientInstance.getHistoryByFailedHistory()
                    .getGenericHandler());
            entityManager.persist(transientInstance.getHistoryByHistoryId()
                    .getGenericHandler());

            // Save clients..
            Set<Client> clients = transientInstance.getAuthorizeds();
            List<Client> deepCopyClient = new ArrayList<Client>(clients);
            Set<Client> titulars = transientInstance.getClientes();
            List<Client> deepCopytitulars = new ArrayList<Client>(titulars);
            Iterator<Client> authorizedsIterator = deepCopytitulars.iterator();
            Iterator<Client> clientsIterator = deepCopyClient.iterator();

            Set<Transaction> transactionsHistory = transientInstance
                    .getHistoryByHistoryId().getTransactionses();
            Set<Transaction> transactionsHistoryFail = transientInstance
                    .getHistoryByFailedHistory().getTransactionses();
            Set<Transaction> transactionHistoryDebit = transientInstance
                    .getHistoryByDirectDebitHistory().getTransactionses();

            List<Transaction> deepCopyHistory = new ArrayList<Transaction>(
                    transactionsHistory);
            List<Transaction> deepCopyHistoryFail = new ArrayList<Transaction>(
                    transactionsHistoryFail);
            List<Transaction> deepCopyHistoryById = new ArrayList<Transaction>(
                    transactionHistoryDebit);

            transientInstance.getHistoryByDirectDebitHistory()
                    .getTransactionses().clear();
            transientInstance.getHistoryByFailedHistory().getTransactionses()
                    .clear();
            transientInstance.getHistoryByHistoryId().getTransactionses()
                    .clear();

            transientInstance.getAuthorizeds().clear();
            transientInstance.getClientes().clear();
            entityManager.persist(transientInstance);

            while (clientsIterator.hasNext()) {
                Client c = clientsIterator.next();
                entityManager.persist(c.getGenericHandler());
                entityManager.persist(c);
                AccountAuthorized aa = new AccountAuthorized();
                aa.setAccount(transientInstance);
                aa.setAuthorized(c);
                aa.setAccountId(new AccountAuthorizedId(transientInstance
                        .getAccountNumber(), c.getId()));
                entityManager.persist(aa);
            }

            // //Save titulars..
            while (authorizedsIterator.hasNext()) {
                Client c = authorizedsIterator.next();
                entityManager.persist(c.getGenericHandler());
                entityManager.persist(c);
                AccountTitular aa = new AccountTitular();
                aa.setAccount(transientInstance);
                aa.setAuthorized(c);
                aa.setAccountId(new AccountTitularId(transientInstance
                        .getAccountNumber(), c.getId()));
                entityManager.persist(aa);
            }

            Iterator<Transaction> it = deepCopyHistory.iterator();
            History actual = transientInstance.getHistoryByDirectDebitHistory();
            while (it.hasNext()) {
                HistoryTransaction ht = new HistoryTransaction();
                Transaction t = it.next();
                ht.setTransaction(t);
                System.out.println(actual.getGenericHandler().getId() + " "
                        + t.getId().toString());
                System.out.flush();

                ht.setId(new HistoryTransactionId(actual.getGenericHandler()
                        .toString(), t.getId().toString()));
                ht.setHistory(actual);
                ht.setTransaction(t);

                if (findHandlerById(t.getId().toString()) == null) {
                    entityManager.persist(t.getId());
                }
                entityManager.persist(t);
                entityManager.persist(ht);
            }

            it = deepCopyHistoryById.iterator();
            actual = transientInstance.getHistoryByHistoryId();
            while (it.hasNext()) {
                HistoryTransaction ht = new HistoryTransaction();
                Transaction t = it.next();
                ht.setTransaction(t);
                System.out.println(actual.getGenericHandler().getId() + " "
                        + t.getId().toString());
                System.out.flush();

                ht.setId(new HistoryTransactionId(actual.getGenericHandler()
                        .toString(), t.getId().toString()));
                ht.setHistory(actual);
                ht.setTransaction(t);
                if (findHandlerById(t.getId().toString()) == null) {
                    entityManager.persist(t.getId());
                }

                entityManager.persist(t);
                entityManager.persist(ht);
            }

            it = deepCopyHistoryFail.iterator();
            actual = transientInstance.getHistoryByFailedHistory();
            while (it.hasNext()) {
                HistoryTransaction ht = new HistoryTransaction();
                Transaction t = it.next();
                ht.setTransaction(t);
                System.out.println(actual.getGenericHandler().getId() + " "
                        + t.getId().toString());
                System.out.flush();

                ht.setId(new HistoryTransactionId(actual.getGenericHandler()
                        .toString(), t.getId().toString()));
                ht.setHistory(actual);
                ht.setTransaction(t);

                if (findHandlerById(t.getId().toString()) == null) {
                    entityManager.persist(t.getId());
                }
                entityManager.persist(t);
                entityManager.persist(ht);
            }

            log.debug("persist successful");
        } catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }

    public void remove(Accounts persistentInstance) {
        log.debug("removing Accounts instance");
        try {
            entityManager.remove(persistentInstance);
            log.debug("remove successful");
        } catch (RuntimeException re) {
            log.error("remove failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = false)
    public Accounts merge(Accounts detachedInstance) {
        log.debug("merging Accounts instance");
        try {
            Accounts result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = true)
    public Handler findHandlerById(String id) {
        log.debug("getting Accounts instance with id: " + id);
        try {
            Handler instance = entityManager.find(Handler.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    @Transactional(readOnly = true)
    public Accounts findById(String id) {
        log.debug("getting Accounts instance with id: " + id);
        try {
            Accounts instance = entityManager.find(Accounts.class, id);
            System.out.println(instance.getHistoryByDirectDebitHistory()
                    .getTransactionses());
            System.out.println(instance.getHistoryByFailedHistory()
                    .getTransactionses());
            System.out.println(instance.getHistoryByHistoryId()
                    .getTransactionses());
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    @Override
    public void remove(Handler id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Accounts> getAccountList() {
        // TODO Auto-generated method stub
        return null;
    }
}
