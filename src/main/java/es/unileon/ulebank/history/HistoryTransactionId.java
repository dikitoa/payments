package es.unileon.ulebank.history;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class HistoryTransactionId implements Serializable {

    /**
     * Serial version uid
     */
    private static final long serialVersionUID = 1L;
    private String historyId;
    private String transactionId;

    public HistoryTransactionId(String historyId, String transactionId) {
        this.historyId = historyId;
        this.transactionId = transactionId;
    }

    public HistoryTransactionId() {
        // TODO Auto-generated constructor stub
    }

    @Column(name = "history_id", nullable = false, length = 64)
    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    @Column(name = "transaction_id", nullable = false, length = 64)
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

}
