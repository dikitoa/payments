package es.unileon.ulebank.repository.impl;

// Generated Jun 15, 2014 4:26:11 AM by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.domain.Accounts;
import es.unileon.ulebank.domain.Cards;
import es.unileon.ulebank.repository.CardDao;

/**
 * Home object for domain model class Cards.
 * 
 * @see es.unileon.ulebank.domain.Cards
 * @author Hibernate Tools
 */
@Stateless
@Repository(value = "cardDao")
public class JPACardsDao implements CardDao {

	private static final Log log = LogFactory.getLog(JPACardsDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = false)
	public void persist(Cards transientInstance) {
		log.debug("persisting Cards instance");
		try {
			entityManager.persist(transientInstance.getGenericHandler());
			if (entityManager.find(Client.class, transientInstance.getClient()
					.getId()) == null) {
				entityManager.persist(transientInstance.getClient());
			}
			if (entityManager.find(Accounts.class, transientInstance
					.getAccounts().getAccountNumber()) == null) {
				entityManager.persist(transientInstance.getAccounts());
			}

			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Transactional(readOnly = false)
	public void remove(Cards persistentInstance) {
		log.debug("removing Cards instance");
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

	@Transactional(readOnly = false)
	public Cards merge(Cards detachedInstance) {
		log.debug("merging Cards instance");
		try {
			Cards result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Transactional(readOnly = true)
	public Cards findById(String id) {
		log.debug("getting Cards instance with id: " + id);
		try {
			Cards instance = entityManager.find(Cards.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Cards> getCardClientList(String dni) {
		Query query = entityManager
				.createQuery("select c from DebitCard c order by c.id");
		List<Cards> cards = query.getResultList();
		query = entityManager
				.createQuery("select c from CreditCard c order by c.id");
		cards.addAll(query.getResultList());
		List<Cards> result = new ArrayList<Cards>();
		for (Cards card : cards) {
			if (card.getClient().getId().compareTo(dni) == 0) {
				result.add(card);
			}
		}
		return result;
	}

	@Transactional(readOnly = true)
	public List<Cards> getCardAccountList(String accountNumber) {
		Query query = entityManager
				.createQuery("select c from DebitCard where account_number="
						+ accountNumber);
		@SuppressWarnings("unchecked")
		List<Cards> result = query.getResultList();
		return result;
	}
}
