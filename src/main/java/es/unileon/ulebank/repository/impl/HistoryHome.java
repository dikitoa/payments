package es.unileon.ulebank.repository.impl;

// Generated Jun 15, 2014 1:06:41 AM by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.unileon.ulebank.domain.History;
import es.unileon.ulebank.history.HistoryTransaction;
import es.unileon.ulebank.history.HistoryTransactionId;
import es.unileon.ulebank.history.Transaction;

/**
 * Home object for domain model class History.
 * 
 * @see es.unileon.ulebank.domain.History
 * @author Hibernate Tools
 */
@Stateless
@Repository("historyHomeDao")
public class HistoryHome {

    private static final Log log = LogFactory.getLog(HistoryHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly=false)
    public void persist(History transientInstance) {
        log.debug("persisting History instance");
        try {
            entityManager.persist(transientInstance.getGenericHandler());
           
            List<Transaction> trans = new ArrayList<Transaction>();
            Iterator<Transaction> it = transientInstance.getTransactionses().iterator();
            while(it.hasNext()){
                trans.add(it.next());
            }
            transientInstance.getTransactionses().clear();
            Iterator<Transaction> iterator = trans.iterator();
            System.out.flush();
            entityManager.persist(transientInstance);
            while (iterator.hasNext()) {
                System.out.println("persistiendo..");
                Transaction t = iterator.next();
                HistoryTransaction ht = new HistoryTransaction();
                ht.setTransaction(t);
                ht.setId(new HistoryTransactionId(transientInstance.getGenericHandler().toString(), t.getId().toString()));
                entityManager.persist(ht);
                entityManager.persist(t.getId());
            }
            
            log.debug("persist successful");
        } catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }

    public void remove(History persistentInstance) {
        log.debug("removing History instance");
        try {
            entityManager.remove(persistentInstance);
            log.debug("remove successful");
        } catch (RuntimeException re) {
            log.error("remove failed", re);
            throw re;
        }
    }

    public History merge(History detachedInstance) {
        log.debug("merging History instance");
        try {
            History result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    //TODO erratic behaviour
    @Transactional(readOnly=false)
    public History findById(String id) {
        log.debug("getting History instance with id: " + id);
        try {
            History instance = entityManager.find(History.class, id);
////            Iterator<Transaction> it = instance.iterator();
//            while(it.hasNext()) {
//                //not remove this line, 
//                //or the stub list
//                //crash ( Hibernate's stuff)
//                System.out.println(it.next());
//            }
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}
