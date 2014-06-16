/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.history;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.handler.Handler;

/**
 *
 * @author runix
 */
@Entity
@Table(name = "TRANSACTIONS", catalog = "ULEBANK_FINAL")
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "DDTransaction")
public class DirectDebitTransaction extends Transaction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * The direct debit transaction id (The same as direct debit id)
     */
    private Handler directDebitId;

    /**
     * Create a new DirectDebitTransaction
     *
     * @param amount
     * @param date
     * @param subject
     * @param directDebitId
     *            (The direct debit transaction (The same as direct debit))
     */
    public DirectDebitTransaction(double amount, Date date, String subject,
            Handler directDebitId) throws TransactionException {
        super(amount, date, subject);
        this.directDebitId = directDebitId;
    }

    public DirectDebitTransaction() {

    }

    public void setDirectDebitId(Handler directDebitId) {
        this.directDebitId = directDebitId;
    }

    /**
     * Get the direct debit id
     * 
     * @return
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "direct_debit_id")
    public Handler getDirectDebitId() {
        return this.directDebitId;
    }
}
