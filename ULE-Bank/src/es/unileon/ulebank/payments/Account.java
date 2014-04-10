package es.unileon.ulebank.payments;

import java.util.ArrayList;
import java.util.Iterator;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.Handler;

public class Account {
	
	private ArrayList<Card> cards;
	private Handler id;

	public String getId() {
		return id.toString();
	}
	
	public void addCard(Card card) {
		this.cards.add(card);
	}

	public void removeCard(CardHandler cardId) {
		this.cards.remove(searchCard(cardId));
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

}
