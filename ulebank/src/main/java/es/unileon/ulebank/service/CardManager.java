package es.unileon.ulebank.service;

import java.io.Serializable;
import java.util.List;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.payments.Card;

/**
 * @author Israel
 * Gestiona las operaciones que se realizan con la tarjeta
 */
public interface CardManager extends Serializable {
	/**
	 * Devuelve la lista de tarjetas
	 * @return
	 */
	public List<Card> getCards();
	/**
	 * Guarda la tarjeta creada
	 */
	public void saveNewCard(Card card);
	/**
	 * Obtiene el cliente de la memoria o la base de datos
	 * @return
	 */
	public Client getClient(String dni);
	/**
	 * Obtiene la cuenta de la memoria o la base de datos
	 * @return
	 */
	public Account getAccount(String accountNumber);
}
