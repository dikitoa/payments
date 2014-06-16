package es.unileon.ulebank.history;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import es.unileon.ulebank.handler.Handler;

/**
 *
 * @author roobre
 */
@Entity
@Table(name = "GENERIC_HANDLER", catalog = "ULEBANK_FINAL")
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "TransactionHandler")
public class TransactionHandler extends Handler {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public TransactionHandler(){
        
    }

    /**
     *
     * @param id
     * @param timestamp
     */
    public TransactionHandler(long id, String timestamp) {
        this.setId(timestamp + "." + Long.toString(id));
    }

}