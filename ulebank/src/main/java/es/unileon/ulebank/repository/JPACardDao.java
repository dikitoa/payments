package es.unileon.ulebank.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.payments.Card;

@Repository (value = "cardDao")
public class JPACardDao implements CardDao {
	
	private EntityManager entityManager = null;
	
	/**
	 * Cambia el manager actual por el recibido
	 * @param manager
	 */
	@PersistenceContext
	public void setEntityManager(EntityManager manager) {
		this.entityManager = manager;
	}
	/**
	 * Devuelve una lista de tarjetas de la base de datos
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional (readOnly = true)
	public List<Card> getCardList() {
		List<Card> result = new ArrayList<Card>();
		result.addAll(entityManager.createQuery("select c from DebitCard c order by c.id").getResultList());
		entityManager.close();
		return result;
	}

	/**
	 * Agnade una tarjeta a la base de datos
	 */
	@Override
	@Transactional (readOnly = false)
	public void addCard(Card card) {
		entityManager.merge(card);
		entityManager.close();
	}
	
	/**
	 * Devuelve una instancia de la clase entity manager
	 * @return
	 */
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	/**
	 * Devuelve un cliente con el DNI que se indica de la base de datos
	 */
	@Override
	@Transactional
	public Client getClient(String dni) {
		Query query = entityManager.createQuery("select c from Client c where c.id = :dni");
		query.setParameter("dni", dni);
		entityManager.close();
		return (Client) query.getSingleResult();
	}

	/**
	 * Devuelve una cuenta con el numero de cuenta indicado de la base de datos
	 */
	@Override
	@Transactional
	public Account getAccount(String accountNumber) {
		Query query = entityManager.createQuery("select a from Account a where a.accountNumber = :accountNumber");
		query.setParameter("accountNumber", accountNumber);
		entityManager.close();
		return (Account) query.getSingleResult();
	}
}
