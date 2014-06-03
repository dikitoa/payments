/* Application developed for AW subject, belonging to passive operations
 group.*/

package es.unileon.ulebank.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import es.unileon.ulebank.account.Account;

/**
 *Class tha provides the basic gestion data of a client in a bank
 * 
 * @author Gonzalo Nicolas Barreales
 */
@Entity
@Table (name = "client")
public class Client {
    
    /**
     * Identifier of the client
     */
	@Id
	@Column (name = "client_id")
    private String id;
    
    /**
     * Accounts where the client appear
     */
	@Column
	@ElementCollection (targetClass = Account.class)
    private List<Account> accounts;
    
    /**
     * Client age
     */
    @Column (name = "age")
    private int age;
    /**
     * Client name
     */
    @Column (name = "name")
    private String name;
    /**
     * Client surname
     */
    @Column (name = "surname")
    private String surname;
    
    /**
     * Constructor of client. Receive the id and initilize the list of accounts
     * 
     * @param clientHandler 
     */
    public Client(String clientHandler){
        accounts = new ArrayList<Account>();
        this.id=clientHandler;
    }
    
    public Client(){
    }
    
    /**
     * Adds an account to the list of clients. If the account exists, it won't be added
     * 
     * @param account 
     */
    public void add(Account account){
        if(!accounts.contains(account)){
            accounts.add(account);
        }
    }
    
    /**
     * Remove the account identified with acountHandler
     * 
     * @param accountHandler
     * @return true if account is deleted, false if account doesn't exists
     */
    public boolean removeAccount(String accountHandler){
        boolean result = false;
        Iterator<Account> iterator = accounts.iterator();
        while(iterator.hasNext()){
            Account account = iterator.next();
            if(account.getId().compareTo(accountHandler)==0){
                result = accounts.remove(account);
            }
        }
        return result;
    }
    
    /**
     * Check if the account idientified with account Handler exists
     * @param accountHandler
     * @return true if the account exists, false if it doesn't exists
     */
    public boolean existsAccount(String accountHandler){
        boolean result = false;
        Iterator<Account> iterator = accounts.iterator();
        while(iterator.hasNext()){
            Account account = iterator.next();
            if(account.getId().compareTo(accountHandler)==0){
                result = true;
            }
        }
        return result;
    }
    
    public Account searchAccount(String handler) {
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
    
    public List<Account> getAccounts() {
    	return this.accounts;
    }

    /**
     * @return id of the client
     */
    public String getId() {
        return id;
    }
    
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
