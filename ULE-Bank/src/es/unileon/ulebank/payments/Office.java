package es.unileon.ulebank.payments;

import java.util.ArrayList;
import java.util.Iterator;

import es.unileon.ulebank.exceptions.ClientNotFoundException;
import es.unileon.ulebank.handler.IdDNI;

/**
 * @author Israel Garcia Centeno
 * Clase que representa la oficina del banco
 */
public class Office {
	//Lista de clientes de la sucursal
	private ArrayList<Client> clients = new ArrayList<Client>();

	/**
	 * Devuelve los clientes de la sucursal
	 * @return
	 */
	public ArrayList<Client> getClients() {
		return clients;
	}
	
	/**
	 * Agnade un nuevo cliente a la sucursal
	 * @param client
	 */
	public void addClient(Client client) {
		this.clients.add(client);
	}
	
	/**
	 * Elimina un cliente de la sucursal
	 * @param dni
	 * @return
	 * @throws ClientNotFoundException 
	 */
	public boolean removeClient(IdDNI dni) throws ClientNotFoundException {
		//Comprueba si la lista de clientes esta vacia y lanza una excepcion en caso de que lo este
		if (clients.isEmpty()) {
			throw new NullPointerException("Client list is empty.");
		} else {
			//Busca el cliente en la sucursal con el DNI
			Client client = searchClient(dni);
			//Elimina el cliente de la lista y devuelve true o false si se ha borrado o no
			return this.clients.remove(client);
		}
	}
	
	/**
	 * Busca el cliente cuyo DNI coincida con el recibido
	 * @param dni
	 * @return
	 * @throws ClientNotFoundException 
	 */
	public Client searchClient(IdDNI dni) throws ClientNotFoundException {
		//Creamos un iterador para recorrer la lista
		Iterator<Client> iterator = clients.iterator();
		Client client = null;
		boolean found = false;
		
		//Comprobamos que la lista no este vacia
		if (clients.isEmpty()) {
			throw new NullPointerException("Client list is empty.");
		}
		
		//Mientras haya clientes recorremos la lista
		while (iterator.hasNext()) {
			//Guardamos el cliente actual
			client = iterator.next();
			
			//Si el DNI del cliente actual coincide con el indicado salimos del bucle
			if (client.getDni().compareTo(dni) == 0) {
				found = true;
				break;
			}
		}
		//Devolvemos el cliente encontrado
		if (found) {
			return client;
		//si no se encuentra lanzamos una excepcion
		} else {
			throw new ClientNotFoundException("Client not found");
		}
	}
}
