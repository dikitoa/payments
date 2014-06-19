package es.unileon.ulebank.repository.impl;

// Generated Jun 14, 2014 12:29:04 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.repository.GenericHandlerDao;

/**
 * Home object for domain model class GenericHandler.
 * @see es.unileon.ulebank.domain.GenericHandler
 * @author Hibernate Tools
 */
@Repository(value = "handlerDao")
public class JPAGenericHandlerDao implements GenericHandlerDao {

	private static final Log log = LogFactory.getLog(JPAGenericHandlerDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly=false)
	public void persist(Handler transientInstance) {
		log.debug("persisting GenericHandler instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Transactional (readOnly = false)
	public void remove(Handler persistentInstance) {
		log.debug("removing GenericHandler instance");
		try {
			entityManager
			.remove(entityManager.contains(persistentInstance) ? persistentInstance
					: entityManager.merge(persistentInstance));
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Handler merge(Handler detachedInstance) {
		log.debug("merging GenericHandler instance");
		try {
			Handler result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Handler findById(String id) {
		log.debug("getting GenericHandler instance with id: " + id);
		try {
			Handler instance = entityManager.find(Handler.class,
					id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
