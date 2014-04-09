package es.unileon.ulebank.payments;

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

	/**
	 * Class constructor
	 * @param sender
	 * @param receiver
	 * @param quantity
	 */
	public Transfer(Account sender, Account receiver, float quantity){
		this.senderAccount = sender;
		this.receiverAccount = receiver;
		this.quantity = quantity;
		this.id = new TransferHandler(sender.getId(), receiver.getId());
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
	 */
	public void transferMoney(Account sender, Account receiver, float quantity){
		
		//TODO Implementar cuando este la clase conectada con Account
	}

	/**
	 * Getter id
	 * @return id
	 */
	public String getId() {
		return id.toString();
	}

}
