/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.ulebank.history;

import java.util.Date;

import es.unileon.ulebank.account.DetailedInformation;
import es.unileon.ulebank.handler.Handler;

/**
 *
 * @author roobre
 */
public abstract class Transaction {

    private final Handler id;
    private final double amount;
    private final Date date;
    private Date effectiveDate;
    private final String subject;

    /**
     *
     * @param amount
     * @param date
     * @param subject
     * @param info 
     * @throws es.unileon.ulebank.history.TransactionException
     */
    public Transaction(double amount, Date date, String subject, DetailedInformation info) throws TransactionException {
        this.id = TransactionHandlerProvider.getTransactionHandler();
        this.subject = subject;
        this.amount = amount;
        this.date = date;
        
        if (amount == 0) {
            throw new TransactionException("Amount can't be 0");
        }
        
        if (this.subject.isEmpty()) {
            throw new TransactionException("Subject can't be empty");
        }
    }

    public Transaction(double amount, Date date, String subject) throws TransactionException {
    	this.id = TransactionHandlerProvider.getTransactionHandler();
        this.subject = subject;
        this.amount = amount;
        this.date = date;
        
        if (amount == 0) {
            throw new TransactionException("Amount can't be 0");
        }
        
        if (this.subject.isEmpty()) {
            throw new TransactionException("Subject can't be empty");
        }
	}

	/**
     * @return the id
     */
    public Handler getId() {
        return id;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the effectiveDate
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @return the type
     * @deprecated This method is no longer supported. Compare the sign of getBalance instead.
     */
    @Deprecated
    public Enum<TransactionType> getType() {
        if (this.amount < 0) {
            return TransactionType.OUT;
        } else {
            return TransactionType.IN;
        }
    }

    /**
     *
     * @param effectiveDate
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Transaction " + "id=" + id + ", amount=" + amount + ", date=" + date + ", effectiveDate=" + effectiveDate + ", subject=" + subject;
    }
}
