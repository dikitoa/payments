package es.unileon.ulebank.payments;

import es.unileon.ulebank.exceptions.TransferException;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.TransferHandler;

/**
 * Transfer Class
 * @author Rober dCR
 * @date 01/04/2014
 * @brief Class about transfers between two accounts
 */
public class Transfer {

	private Account senderAccount;
	private Account receiverAccount;
	private float quantity;
	private Handler id;
	private Transference annotation;

	/**
	 * Class constructor
	 * @param sender
	 * @param receiver
	 * @param quantity
	 * @throws TransferException 
	 */
	public Transfer(Account sender, Account receiver, float quantity) throws TransferException{
		if (!sender.equals(receiver)){
			this.senderAccount = sender;
			this.receiverAccount = receiver;
			this.quantity = quantity;
			this.id = new TransferHandler(sender.getId(), receiver.getId());
		}
		else
			throw new TransferException("Sender Account number and Receiver Account number are the same.");
	}
	
	/**
	 * Getter senderAccount
	 * @return senderAccount
	 */
	public Account getSenderAccount() {
		return senderAccount;
	}
	
	/**
	 * Getter receiverAccount
	 * @return receiverAccount
	 */
	public Account getReceiverAccount() {
		return receiverAccount;
	}

	/**
	 * Getter quantity
	 * @return quantity
	 */
	public float getQuantity() {
		return quantity;
	}
	
	/**
	 * Setter quantity
	 * @param quantity
	 */
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Method that transfers money from sender to receiver
	 * @param sender
	 * @param receiver
	 * @param quantity
	 * @throws TransferException 
	 */
	public void transferMoney(Account sender, Account receiver, float quantity, String concept) throws TransferException{
		if (sender.getBalance() >= quantity){
			receiver.setBalance(receiver.getBalance() + quantity);
			this.setAnnotation(new Transference(this, concept));
		}
		else
			throw new TransferException("Sender Account has not the balance necessary.");
	}

	/**
	 * Getter id
	 * @return id
	 */
	public String getId() {
		return id.toString();
	}

	/**
	 * Getter annotation
	 * @return the annotation of the transfer
	 */
	public Transference getAnnotation() {
		return annotation;
	}

	/**
	 * Setter annotation
	 * @param annotation
	 */
	public void setAnnotation(Transference annotation) {
		this.annotation = annotation;
	}

}
