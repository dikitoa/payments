package es.unileon.ulebank.repository;

import es.unileon.ulebank.client.Client;

public interface ClientDao {

    public void persist(Client transientInstance);

    public void remove(Client persistentInstance);

    public Client merge(Client detachedInstance);

    public Client findById(String id);
}
