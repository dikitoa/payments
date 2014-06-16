package es.unileon.ulebank.repository;

import java.util.List;

import es.unileon.ulebank.domain.Cards;

public interface CardDao {

    public void persist(Cards transientInstance);

    public void remove(Cards persistentInstance);

    public Cards merge(Cards detachedInstance);

    public Cards findById(String id);

    public List<Cards> getCardClientList(String dni);

    public List<Cards> getCardAccountList(String accountNumber);
}
