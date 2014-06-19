package es.unileon.ulebank.repository;

import es.unileon.ulebank.handler.Handler;

public interface GenericHandlerDao {
	public void persist(Handler transientInstance);

	public void remove(Handler persistentInstance);

	public Handler merge(Handler detachedInstance);

	public Handler findById(String id);
}
