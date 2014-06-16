package es.unileon.ulebank.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import es.unileon.ulebank.history.HistoryTransaction;


@Stateless
@Repository (value = "historyTransactionsHome")
public class HistoryTransactionsHome {
    private static final Log log = LogFactory.getLog(HistoryTransactionsHome.class);
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public void persist(HistoryTransaction transientInstance) {
        log.debug("persisting History Transaction instance");
        
        try {
//            entityManager.persist(transientInstance.getHistory());
//            entityManager.persist(transientInstance.getTransaction());
            entityManager.persist(transientInstance);
            log.debug("persist successfull");
        } catch (RuntimeException e) {
            log.error("perist failed", e);
        }
        
    }
    
    public HistoryTransaction merge(HistoryTransaction detachedInstance) {
        log.debug("merging History Transaction instance");
        
        try {
            HistoryTransaction result = entityManager.merge(detachedInstance);
            log.debug("merge successfull");
            return result;
        } catch (RuntimeException e) {
            log.error("merge failed", e);
            throw e;
        }
    }
    
}
