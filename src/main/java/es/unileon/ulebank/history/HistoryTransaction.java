package es.unileon.ulebank.history;

import java.io.Serializable;

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

import es.unileon.ulebank.domain.History;
@Entity
@Table(name = "HISTORY_TRANSACTIONS", catalog = "ULEBANK_FINAL")
public class HistoryTransaction implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private HistoryTransactionId id;
    private History history;
    private Transaction transaction;

    public HistoryTransaction() {
    }

    public HistoryTransaction(HistoryTransactionId id,
            History history, Transaction transaction) {
        this.id = id;
        this.history = history;
        this.transaction = transaction;
    }

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "historyId", column = @Column(name = "history_id", nullable = false, length = 64)),
            @AttributeOverride(name = "transactionId", column = @Column(name = "transaction_id", nullable = false, length = 64)) })
    public HistoryTransactionId getId() {
        return id;
    }

    public void setId(HistoryTransactionId id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "history_id", nullable = false, insertable = false, updatable = false)
    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    @OneToOne(targetEntity=Transaction.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id", nullable = false, insertable = false, updatable = false)
    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

}
