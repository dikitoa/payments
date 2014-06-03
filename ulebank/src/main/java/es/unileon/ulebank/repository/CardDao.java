package es.unileon.ulebank.repository;

import java.util.List;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.payments.Card;

/**
 * @author Israel
 *	Interfaz que define la funcionalidad de nuestras clases de acceso a la base de datos
 */
public interface CardDao {
	/**
	 * Devuelve la lista de tarjetas de la base de datos
	 * @return
	 */
	public List<Card> getCardList();
	/**
	 * Agnade una tarjeta a la base de datos
	 * @param card
	 */
	public void addCard(Card card);
	/**
	 * Devuelve el cliente de la memoria o la base de datos
	 * @return
	 */
	public Client getClient(String dni);
	/**
	 * Devuelve la cuenta de la memoria o la base de datos
	 * @return
	 */
	public Account getAccount(String accountNumber);
	
	/**
	 * Method that obtains the card of the database;
	 * @param card_id Identifier of the card which get of the BBDD
	 * @return Card of the BBDD
	 */
	public Card getCardDAO(String card_id);
	
	public void saveCard(Card card);
}
