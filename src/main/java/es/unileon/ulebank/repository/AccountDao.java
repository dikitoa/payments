package es.unileon.ulebank.repository;

import java.util.List;

import es.unileon.ulebank.domain.Accounts;
import es.unileon.ulebank.handler.Handler;

public interface AccountDao {

    public void persist(Accounts transientInstance);

    public void remove(Handler id);

    public Accounts merge(Accounts detachedInstance);

    public Accounts findById(String id);
    
    public List<Accounts> getAccountList();
}
