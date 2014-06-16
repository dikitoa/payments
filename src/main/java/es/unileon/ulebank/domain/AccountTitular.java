package es.unileon.ulebank.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.unileon.ulebank.client.Client;

@Entity
@Table(name = "ACCOUNTS_CLIENTS", catalog = "ULEBANK_FINAL")
public class AccountTitular {

    private Accounts account;
    private Client authorized;
    private AccountTitularId accountId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_number", nullable = false, insertable = false, updatable = false)
    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false, insertable = false, updatable = false)
    public Client getAuthorized() {
        return authorized;
    }

    public void setAuthorized(Client authorized) {
        this.authorized = authorized;
    }

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "account", column = @Column(name = "account_number", nullable = false, length = 64)),
            @AttributeOverride(name = "client", column = @Column(name = "client_id", nullable = false, length = 64)) })
    public AccountTitularId getAccountId() {
        return accountId;
    }

    public void setAccountId(AccountTitularId accountId) {
        this.accountId = accountId;
    }

}
