package es.unileon.ulebank.repository;

import java.util.List;

import es.unileon.ulebank.domain.Accounts;

public interface AccountDao {

    public void persist(Accounts transientInstance);

    public void remove(Accounts account);

    public Accounts merge(Accounts detachedInstance);

    public Accounts findById(String id);
    
    public List<Accounts> getAccountList(String id);
}
