/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.ulebank.command;

import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.history.GenericTransaction;
import es.unileon.ulebank.history.Transaction;
import es.unileon.ulebank.history.TransactionException;
import es.unileon.ulebank.transactionManager.TransactionManager;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paula
 */
public class DoTransactionCommand implements Command {

    private final Handler commandID;
    private final double amount;
    private final Date date;
    private Date effectiveDate;
    private final String subject;
    private Transaction transaction;
    private TransactionManager trans;

    /**
     *
     * @param amount
     * @param date
     * @param subject
     * @param commandId
     */

    public DoTransactionCommand(double amount, Date date, String subject, Handler commandId) {
        this.amount = amount;
        this.date = date;
        this.subject = subject;
        this.commandID = commandId;
    }

    /**
     *
     */
    @Override
    public void execute() {
        try {
            this.transaction = new GenericTransaction(this.amount, this.date, this.subject);
            // this.trans.doTransaction(transaction, );
        } catch (TransactionException ex) {
            Logger.getLogger(DoTransactionCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
//    @Override
//    public Date getEffectiveDate() {
//        return this.effectiveDate;
//    }

    /**
     *
     * @return
     */
    @Override
    public Handler getId() {
        return this.commandID;
    }

    /**
     *
     */
    @Override
    public void undo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void redo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
