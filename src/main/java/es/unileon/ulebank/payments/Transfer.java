package es.unileon.ulebank.payments;

import java.util.Date;

import es.unileon.ulebank.domain.Accounts;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.history.TransferTransaction;
import es.unileon.ulebank.payments.exceptions.TransferException;

/**
 * Transfer Class
 * 
 * @author Rober dCR
 * @date 01/04/2014
 * @brief Class about transfers between two accounts
 */
public class Transfer {

    /**
     * Accounts from transfer the money
     */
    private Accounts senderAccount;
    /**
     * Accounts which receives the money
     */
    private Accounts receiverAccount;
    /**
     * Quantity of the transfer
     */
    private double quantity;
    /**
     * Transaction of the transfer
     */
    private TransferTransaction transaction;

    /**
     * Class constructor
     * 
     * @param sender
     * @param receiver
     * @param quantity
     * @throws TransferException
     */
    public Transfer(Accounts sender, Accounts receiver, double quantity)
            throws TransferException {
        if (!sender.equals(receiver)) {
            this.senderAccount = sender;
            this.receiverAccount = receiver;
            this.quantity = quantity;
        } else {
            throw new TransferException(
                    "Sender Accounts number and Receiver Accounts number are the same.");
        }
    }

    /**
     * Getter senderAccount
     * 
     * @return senderAccount
     */
    public Accounts getSenderAccount() {
        return this.senderAccount;
    }

    /**
     * Getter receiverAccount
     * 
     * @return Accounts
     */
    public Accounts getReceiverAccount() {
        return this.receiverAccount;
    }

    /**
     * Getter quantity
     * 
     * @return double
     */
    public double getQuantity() {
        return this.quantity;
    }

    /**
     * Method that transfers money from sender to receiver
     * 
     * @param sender
     * @param receiver
     * @param quantity
     * @throws es.unileon.ulebank.history.TransactionException
     */
    public void make(String concept) throws TransferException,
            TransactionException {
        try {
            // Discount the quantity from sender Accounts
            this.senderAccount.doTransaction(new TransferTransaction(
                    -this.quantity, new Date(), concept, this.receiverAccount/*,
                    this.senderAccount*/));
            // Add the money to receiver Accounts
            this.receiverAccount.doTransaction(new TransferTransaction(
                    this.quantity, new Date(), concept, this.receiverAccount/*,
                    this.senderAccount*/));
        } catch (TransactionException e) {
            throw new TransferException("Denegate Transaction");
        }
    }

    /**
     * Getter transaction
     * 
     * @return TransferTransaction
     */
    public TransferTransaction getTransaction() {
        return this.transaction;
    }

    /**
     * Setter transaction
     * 
     * @param transaction
     */
    public void setTransaction(TransferTransaction transaction) {
        this.transaction = transaction;
    }

}
