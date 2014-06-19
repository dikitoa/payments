package es.unileon.ulebank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.repository.GenericHandlerDao;
import es.unileon.ulebank.service.GenericHandlerManager;

@Component
public class SimleGenericHandlerManager implements GenericHandlerManager {
	@Autowired
	private GenericHandlerDao handlerDao;
	
	@Override
	public void save(Handler transientInstance) {
		 if (handlerDao.findById(transientInstance.getId()) == null) {
	            handlerDao.persist(transientInstance);
	        } else {
	            handlerDao.merge(transientInstance);
	        }
	}

	@Override
	public void remove(Handler persistentInstance) {
		this.handlerDao.remove(persistentInstance);
	}

	@Override
	public Handler findById(String id) {
		return this.handlerDao.findById(id);
	}

}
