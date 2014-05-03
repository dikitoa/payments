package es.unileon.ulebank.payments;

import java.util.ArrayList;
import java.util.Iterator;

import es.unileon.ulebank.handler.AccountHandler;
import es.unileon.ulebank.handler.IdDNI;

public class Client {
	private int age;
	private IdDNI dni;
	private ArrayList<Account> accounts = new ArrayList<Account>();
	
	public Client(IdDNI dni, int age) {
		this.dni = dni;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public IdDNI getDni() {
		return dni;
	}

	public void setDni(IdDNI dni) {
		this.dni = dni;
	}
	
	public void addAccount(Account account) {
		this.accounts.add(account);
	}
	
	public boolean removeAccount(AccountHandler accountHandler) {
		if (this.accounts.isEmpty()) {
			throw new NullPointerException("Account list is empty.");
		} else {
			Account account = searchAccount(accountHandler);
			return this.accounts.remove(account);
		}
	}
	
	public Account searchAccount(AccountHandler handler) {
		Iterator<Account> iterator = accounts.iterator();
		Account account = null;
		
		if (this.accounts.isEmpty()) {
			throw new NullPointerException("Account list is empty.");
		}
		
		while (iterator.hasNext()) {
			account = iterator.next();
			
			if (account.getId().compareTo(handler) == 0) {
				break;
			}
		}
		
		return account;
	}
}
