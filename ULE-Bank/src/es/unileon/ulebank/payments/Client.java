package es.unileon.ulebank.payments;

import java.util.ArrayList;
import java.util.Iterator;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.Handler;

public class Client {
	private int age;
	private DNIHandler dni;
	private ArrayList<Account> accounts = new ArrayList<Account>();
	
	public Client(DNIHandler dni, int age) {
		this.dni = dni;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public DNIHandler getDni() {
		return dni;
	}

	public void setDni(DNIHandler dni) {
		this.dni = dni;
	}
	
	public void addAccount(Account account) {
		this.accounts.add(account);
	}
	
	public boolean removeAccount(Handler accountHandler) {
		if (this.accounts.isEmpty()) {
			throw new NullPointerException("Account list is empty.");
		} else {
			Account account = searchAccount(accountHandler);
			return this.accounts.remove(account);
		}
	}
	
	public Account searchAccount(Handler accountHandler) {
		Iterator<Account> iterator = accounts.iterator();
		Account account = null;
		
		if (this.accounts.isEmpty()) {
			throw new NullPointerException("Account list is empty.");
		}
		
		while (iterator.hasNext()) {
			account = iterator.next();
			
			if (account.getID().compareTo(accountHandler) == 0) {
				break;
			}
		}
		
		return account;
	}
}
