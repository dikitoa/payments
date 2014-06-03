package es.unileon.ulebank.repository;

import java.util.List;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.fees.InvalidFeeException;
import es.unileon.ulebank.payments.Card;

public class InMemoryCardDao implements CardDao {
	/**
	 * Lista de tarjetas en memoria
	 */
	private List<Card> cards;
	
	/**
	 * Crea un nuevo dao en memoria con la lista de tarjetas que recibe
	 * @param cards
	 */
	public InMemoryCardDao(List<Card> cards) {
		this.cards = cards;
	}
	
	/**
	 * Devuelve la lista de tarjetas almacenada en memoria
	 */
	@Override
	public List<Card> getCardList() {
		return this.cards;
	}

	/**
	 * Agnade la tarjeta recibida a la lista de tarjetas en memoria
	 */
	@Override
	public void addCard(Card card) {
		this.cards.add(card);
	}

	@Override
	public Client getClient(String dni) {
		return null;
	}

	@Override
	public Account getAccount(String accountNumber) {
		return null;
	}

	@Override
	public Card getCardDAO(String card_id) {
		// TODO Auto-generated method stub
		return null;
	}
}
