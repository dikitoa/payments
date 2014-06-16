/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import es.unileon.ulebank.domain.Accounts;
import es.unileon.ulebank.handler.Handler;

/**
 * Class tha provides the basic gestion data of a client in a bank
 *
 * @author Gonzalo Nicolas Barreales
 */
@Entity
@Table(name = "CLIENTS", catalog = "ULEBANK_FINAL")
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
public class Client implements Serializable {

    /**
     * Serial version uid
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identifier of the client
     */
    private Handler genericHandler;

    private String id;
    /**
     * Accounts where the client appear
     */
    private List<Accounts> accounts;

    /**
     * Constructor of client. Receive the id and initilize the list of accounts
     *
     * @param clientHandler
     */
    protected Client(Handler clientHandler) {
        this.accounts = new ArrayList<Accounts>();
        this.genericHandler = clientHandler;
    }

    public Client() {
        this.accounts = new ArrayList<Accounts>();
    }

    /**
     * Adds an account to the list of clients. If the account exists, it won't
     * be added
     *
     * @param account
     */
    public void add(Accounts account) {
        if (!this.accounts.contains(account)) {
            this.accounts.add(account);
        }
    }

    /**
     * Remove the account identified with acountHandler
     *
     * @param accountHandler
     * @return true if account is deleted, false if account doesn't exists
     */
    public boolean removeAccount(Handler accountHandler) {
        boolean removed = false;
        final Iterator<Accounts> iterator = this.accounts.iterator();
        while (!removed && iterator.hasNext()) {
            final Accounts account = iterator.next();
            if (account.getHandler().compareTo(accountHandler) == 0) {
                removed = true;
                iterator.remove();
            }
        }

        return removed;
    }

    /**
     * Check if the account idientified with account Handler exists
     *
     * @param accountHandler
     * @return true if the account exists, false if it doesn't exists
     */
    public boolean existsAccount(Handler accountHandler) {
        boolean result = false;
        final Iterator<Accounts> iterator = this.accounts.iterator();
        while (iterator.hasNext()) {
            final Accounts account = iterator.next();
            if (account.getHandler().compareTo(accountHandler) == 0) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Search account with received handler into the list
     *
     * @param handler
     * @return
     */
    public Accounts searchAccount(Handler handler) {
        final Iterator<Accounts> iterator = this.accounts.iterator();
        Accounts account = null;

        if (this.accounts.isEmpty()) {
            throw new NullPointerException("Account list is empty.");
        }

        while (iterator.hasNext()) {
            account = iterator.next();

            if (account.getHandler().compareTo(handler) == 0) {
                break;
            }
        }

        return account;
    }

    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "genericHandler"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", unique = true, nullable = false, length = 12)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public Handler getGenericHandler() {
        return this.genericHandler;
    }

    public void setGenericHandler(Handler genericHandler) {
        this.genericHandler = genericHandler;
    }

    public String toString() {
        return this.genericHandler.toString();
    }

    public void setId(Handler id) {
        this.genericHandler = id;
    }

    public void setAccounts(List<Accounts> accounts) {
        this.accounts = accounts;
    }

}
