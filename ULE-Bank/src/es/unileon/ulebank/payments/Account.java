package es.unileon.ulebank.payments;

import java.util.ArrayList;
import java.util.Iterator;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.Handler;

public class Account {
	
	private ArrayList<Card> cards;
	private Handler id;
	
	public Account() {
		this.cards = new ArrayList<Card>();
	}

	public String getId() {
		return id.toString();
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
}
