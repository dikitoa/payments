package es.unileon.ulebank.payments;

import java.io.IOException;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.domain.Accounts;
import es.unileon.ulebank.domain.Cards;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.fees.InvalidFeeException;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.exceptions.PaymentException;

/**
 * @author Israel, Rober dCR Clase que representa una tarjeta de Debito
 */
@Entity
@Table(name = "CARDS", catalog = "ULEBANK_FINAL")
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "DebitCard")
public class DebitCard extends Cards {

    /**
     * Version
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param properties
     * @param cardId
     * @param owner
     * @param account
     * @param buyLimitDiary
     * @param buyLimitMonthly
     * @param cashLimitDiary
     * @param cashLimitMonthly
     * @param commissionEmission
     * @param commissionMaintenance
     * @param commissionRenovate
     * @throws NumberFormatException
     * @throws CommissionException
     * @throws IOException
     * @throws InvalidFeeException
     */
    public DebitCard(Handler cardId, Client owner, Accounts account)
            throws PaymentException {
        super(cardId, account, owner);
    }

    /**
     * Class constructor
     */
    public DebitCard() {
    }

    // /**
    // * Method that makes the payment
    // * @param quantity Amount of the payment
    // * @param payConcept Concept of the payment
    // * @throws PaymentException
    // * @throws TransactionException
    // */
    // @Override
    // public void makeTransaction(double quantity, String payConcept) throws
    // PaymentException, TransactionException {
    //
    // try{
    // //Discount the quantity from sender account
    // this.getAccounts().doTransaction(new CardTransaction(-quantity, new
    // Date(), payConcept));
    // }catch(TransactionException e){
    // throw new PaymentException("Denegate Transaction");
    // }
    //
    // }
}
