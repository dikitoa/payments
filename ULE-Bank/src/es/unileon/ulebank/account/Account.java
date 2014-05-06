package es.unileon.ulebank.account;

import java.util.ArrayList;
import java.util.Iterator;

import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.payments.Card;

public class Account {
	private ArrayList<Card> cards;
	private AccountHandler id;
	private double balance;
	
	public Account(AccountHandler handler) {
		this.id = handler;
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
	

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
