package es.unileon.ulebank.service;

import java.util.List;

import es.unileon.ulebank.client.Client;

public interface ClientManager {
	public List<Client> getClients();
	public Client searchClient(String dni);
}
