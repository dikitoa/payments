package es.unileon.ulebank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.unileon.ulebank.domain.Cards;
import es.unileon.ulebank.repository.impl.JPACardsDao;
import es.unileon.ulebank.service.CardManager;

@Component
public class SimpleCardManager implements CardManager {
    @Autowired
    private JPACardsDao cardDao;

    public boolean saveCard(Cards Cards) {
        if (cardDao.findById(Cards.getId()) == null) {
            cardDao.persist(Cards);
            return true;
        } else {
            cardDao.merge(Cards);
            return false;
        }
    }

    public boolean removeCard(Cards Cards) {
        if (cardDao.findById(Cards.getId()) != null) {
            cardDao.remove(Cards);
            return true;
        }
        return false;
    }

    public Cards findCard(String cardId) {
        return cardDao.findById(cardId);
    }

    public List<Cards> getCardClientList(String dni) {
        return cardDao.getCardClientList(dni);
    }

    public List<Cards> getCardAccountList(String accountNumber) {
        return cardDao.getCardAccountList(accountNumber);
    }
}
