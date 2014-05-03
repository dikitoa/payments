package es.unileon.ulebank.payments;

import java.util.ArrayList;
import java.util.Iterator;

import es.unileon.ulebank.handler.IdDNI;

public class Office {
	private ArrayList<Client> clients = new ArrayList<Client>();

	public ArrayList<Client> getClients() {
		return clients;
	}

	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}
	
	public void addClient(Client client) {
		this.clients.add(client);
	}
	
	public boolean removeClient(IdDNI dni) {
		if (clients.isEmpty()) {
			throw new NullPointerException("Client list is empty.");
		} else {
			Client client = searchClient(dni);
			return this.clients.remove(client);
		}
	}
	
	public Client searchClient(IdDNI dni) {
		Iterator<Client> iterator = clients.iterator();
		Client client = null;
		
		if (clients.isEmpty()) {
			throw new NullPointerException("Client list is empty.");
		}
		
		while (iterator.hasNext()) {
			client = iterator.next();
			
			if (client.getDni().compareTo(dni) == 0) {
				break;
			}
		}
		
		return client;
	}
}
