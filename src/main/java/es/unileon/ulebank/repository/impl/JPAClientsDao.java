package es.unileon.ulebank.repository.impl;

// Generated Jun 15, 2014 1:06:41 AM by Hibernate Tools 3.4.0.CR1

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
import es.unileon.ulebank.client.Person;
import es.unileon.ulebank.repository.ClientDao;

/**
 * Home object for domain model class Clients.
 * @see es.unileon.ulebank.domain.Clients
 * @author Hibernate Tools
 */
@Stateless
@Repository(value = "clientsHome")
public class JPAClientsDao implements ClientDao{

	private static final Log log = LogFactory.getLog(JPAClientsDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly=false)
	public void persist(Client transientInstance) {
		log.debug("persisting Clients instance");
		try {
		    entityManager.persist(transientInstance.getGenericHandler());
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Client persistentInstance) {
		log.debug("removing Clients instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Client merge(Client detachedInstance) {
		log.debug("merging Clients instance");
		try {
			Client result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Transactional (readOnly = true)
	public Client findById(String id) {
		log.debug("getting Clients instance with id: " + id);
		try {
			Person instance = entityManager.find(Person.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<Client> getClientList(String office) {
		Query query = entityManager.createQuery("select c from Client c order by c.id");
        @SuppressWarnings("unchecked")
        List<Client> clients = query.getResultList();
        
        List<Client> result = new ArrayList<Client>();
        for (Client client : clients) {
			if (client.getOffice().equals(office)) {
				result.add(client);
			}
		}
        return result;
	}
}
