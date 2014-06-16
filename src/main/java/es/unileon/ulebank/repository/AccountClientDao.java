package es.unileon.ulebank.repository;

import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.domain.Accounts;

public interface AccountClientDao {

    public boolean persistRelation(Client c, Accounts a);

    public boolean mergeRelation(Client c, Accounts a);

    public boolean removeRelation(Client c, Accounts a);
}
