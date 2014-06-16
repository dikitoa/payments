package es.unileon.ulebank.history;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import es.unileon.ulebank.domain.Accounts;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.payments.exceptions.TransferException;

/**
 * Transaction for the Transfer
 * 
 * @author Rober dCR
 * @date 8/05/2014
 * @brief Class that allows all monetary transactions with accounts
 */
@Entity
@Table(name = "TRANSACTIONS", catalog = "ULEBANK_FINAL")
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "TransferTransaction")
public class TransferTransaction extends GenericTransaction {

	/**
	 * Serial version uid
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Account from transfer the money
	 */
	private Accounts senderAccount;

	/**
	 * Class constructor
	 * 
	 * @param amount
	 * @param date
	 * @param subject
	 * @param senderAccount
	 * @param receiverAccount
	 * @throws TransferException
	 * @throws TransactionException
	 */
	public TransferTransaction(double amount, Date date, String subject,
			Accounts senderAccount) throws TransferException,
			TransactionException {
		super(amount, date, subject);
	}

	/**
	 * Getter Sender Account
	 * 
	 * @return
	 */
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	// @JoinColumn(name = "account_number", nullable = false)
	@PrimaryKeyJoinColumn
	public Accounts getSenderAccount() {
		return this.senderAccount;
	}

	public void setSenderAccount(Accounts senderAccount) {
		this.senderAccount = senderAccount;
	}
}
