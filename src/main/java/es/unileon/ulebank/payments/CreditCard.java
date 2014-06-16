package es.unileon.ulebank.payments;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.domain.Accounts;
import es.unileon.ulebank.domain.Cards;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.history.CardTransaction;
import es.unileon.ulebank.payments.exceptions.PaymentException;

/**
 * @author Israel, Rober dCR Clase que representa la tarjeta de credito
 */
@Entity
@Table(name = "CARDS", catalog = "ULEBANK_FINAL")
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "CreditCard")
public class CreditCard extends Cards {

    /**
     * Version
     */
    private static final long serialVersionUID = 1L;
    /**
     * Dia del mes en el que se carga los gastos de la tarjeta
     */
    private Date monthDay;
    /**
     * Lista de transacciones de la tarjeta
     */
//    private List<CardTransaction> transactionList = new ArrayList<CardTransaction>();

	/**
     * Constructor de la clase que crea una tarjeta de Credito
     * 
     * @param cardId
     * @param owner
     * @param account
     * @throws PaymentException
     */
    public CreditCard(Handler cardId, Client owner, Accounts account) throws PaymentException {
        super(cardId, account, owner);
//        transactionList = new ArrayList<CardTransaction>();
    }

    /**
     * Constructor de la clase
     */
    public CreditCard() {
    }

    /**
     * Method that makes the payment
     * 
     * @param quantity
     *            Amount of the payment
     * @param payConcept
     *            Concept of the payment
     * @throws PaymentException
     * @throws TransactionException
     * @throws TransactionException
     */
    @Override
    public void makeTransaction(double quantity, String payConcept)
            throws PaymentException {
        // Agyadimos la transaccion a la lista
        final CardTransaction transaction = new CardTransaction(quantity,
                new Date(), payConcept);
        transaction.setEffectiveDate(this.obtainEffectiveDate());
//        this.transactionList.add(transaction);
    }

    /**
     * Method that obtain the day when the amount of the purchases is done.
     * 
     * @return
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "month_day", length = 19)
    public Date getMonthDay() {
        return this.monthDay;
    }

    /**
     * Method that sets the day when the amount of the purchases is done.
     * 
     * @param monthDay
     */
    public void setMonthDay(Date monthDay) {
        this.monthDay = monthDay;
    }

    /**
     * Method that calculate the effective day when the payment is taken
     * 
     * @return date
     */
    @SuppressWarnings("deprecation")
    private Date obtainEffectiveDate() {
        final Date effectiveDate = new Date();
        effectiveDate.setTime(this.monthDay.getTime());
        if (effectiveDate.getMonth() != 11) {
            effectiveDate.setMonth(effectiveDate.getMonth() + 1);
        } else {
            effectiveDate.setMonth(0);
            effectiveDate.setYear(effectiveDate.getYear() + 1);
        }

        return effectiveDate;
    }

//    /**
//     * Method that calculate the amount of all transaction which have the
//     * effectiveDate
//     * 
//     * @param effectiveDate
//     * @return amount of purchases
//     */
//    public double getAmount(Date effectiveDate) {
//        double amount = 0.0;
//
//        // Recorremos todas las transacciones de la lista acumulando las
//        // cantidades de dichas transacciones
//        for (int i = 0; i < this.transactionList.size(); i++) {
//            if (this.transactionList.get(i).getEffectiveDate()
//                    .compareTo(effectiveDate) == 0) {
//                amount += this.transactionList.get(i).getAmount();
//            }
//        }
//
//        return amount;
//    }
//    
//    @OneToMany (fetch = FetchType.EAGER, mappedBy = "transactions")
//    public List<CardTransaction> getTransactionList() {
//        return this.transactionList;
//    }
//
//    public void setTransactionList(List<CardTransaction> transactionList) {
//        this.transactionList = transactionList;
//    }
    
}
