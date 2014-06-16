package es.unileon.ulebank.repository;

// Generated Jun 14, 2014 12:29:04 AM by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.unileon.ulebank.history.Transaction;

/**
 * Home object for domain model class Transactions.
 * 
 * @see es.unileon.ulebank.repository.Transactions
 * @author Hibernate Tools
 */
@Stateless
@Repository(value = "TransactionDao")
public class TransactionsHome {

    private static final Log log = LogFactory.getLog(TransactionsHome.class);

    @PersistenceContext
    private EntityManager entityManager;
//
//    @Transactional(readOnly = false)
//    public void persist(Transaction transientInstance) {
//        log.debug("persisting Transactions instance");
//        try {
//            entityManager.persist(transientInstance.getId());
//            entityManager.persist(transientInstance);
//            log.debug("persist successful");
//        } catch (RuntimeException re) {
//            log.error("persist failed", re);
//            throw re;
//        }
//    }

    @Transactional(readOnly = false)
    public void persist(Transaction transientInstance) {
        log.debug("persisting Transactions instance");
        try {
            entityManager.persist(transientInstance.getId());
            entityManager.persist(transientInstance);
            log.debug("persist successful");
        } catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }

//    @Transactional(readOnly = false)
//    public void persist(DirectDebitTransaction transientInstance) {
//        log.debug("persisting Transactions instance");
//        try {
//            entityManager.persist(transientInstance.getDirectDebitId());
//            entityManager.persist(transientInstance.getId());
//            entityManager.persist(transientInstance);
//            log.debug("persist successful");
//        } catch (RuntimeException re) {
//            log.error("persist failed", re);
//            throw re;
//        }
//    }

    public void remove(Transaction persistentInstance) {
        log.debug("removing Transactions instance");
        try {
            entityManager.remove(persistentInstance);
            log.debug("remove successful");
        } catch (RuntimeException re) {
            log.error("remove failed", re);
            throw re;
        }
    }

    public Transaction merge(Transaction detachedInstance) {
        log.debug("merging Transactions instance");
        try {
            Transaction result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    @Transactional(readOnly=true)
    public Transaction findById(String id) {
        log.debug("getting Transactions instance with id: " + id);
        try {
            Transaction instance = entityManager.find(Transaction.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}
