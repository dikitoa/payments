package es.unileon.ulebank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.repository.ClientDao;
import es.unileon.ulebank.service.ClientManager;

@Component
public class SimpleClientManager implements ClientManager {
	@Autowired
	private ClientDao clientDao;
	
	@Override
	public List<Client> getClients() {
		return clientDao.getClientList();
	}

	@Override
	public Client searchClient(String dni) {
		return clientDao.findById(dni);
	}
	
}
