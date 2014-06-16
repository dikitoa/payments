package es.unileon.ulebank.repository;

import org.springframework.transaction.annotation.Transactional;

import es.unileon.ulebank.domain.History;

public interface HistoryDao {

    public abstract void persist(History transientInstance);

    public abstract void remove(History persistentInstance);

    public abstract History merge(History detachedInstance);

    //TODO erratic behaviour
    public abstract History findById(String id);

}