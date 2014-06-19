package es.unileon.ulebank.service;

import es.unileon.ulebank.handler.Handler;

public interface GenericHandlerManager {
	public void save(Handler transientInstance);

	public void remove(Handler persistentInstance);

	public Handler findById(String id);
}
