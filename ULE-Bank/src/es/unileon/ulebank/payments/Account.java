package es.unileon.ulebank.payments;

import java.util.ArrayList;
import java.util.Iterator;

import es.unileon.ulebank.handler.AccountHandler;
import es.unileon.ulebank.handler.CardHandler;

public class Account {
	
	private ArrayList<Card> cards;
	private AccountHandler id;
	private float balance;
	
	public Account() {
		this.cards = new ArrayList<Card>();
	}

	public AccountHandler getId() {
		return id;
	}
	
	public void addCard(Card card) {
		this.cards.add(card);
	}

	public boolean removeCard(CardHandler cardId) {
		Card card = searchCard(cardId);
		return this.cards.remove(card);
	}
	
	public Card searchCard(CardHandler cardId) {
		Iterator<Card> iterator = cards.iterator();
		Card card = null;
		
		if (cards.isEmpty()) {
			throw new NullPointerException("Card list is empty.");
		}
		
		while (iterator.hasNext()) {
			card = iterator.next();
			
			if (card.getCardNumber().compareTo(cardId) == 0) {
				break;
			}
		}
		
		return card;
	}
	
	public int getCardAmount() {
		return this.cards.size();
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
}
