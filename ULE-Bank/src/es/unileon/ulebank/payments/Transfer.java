package es.unileon.ulebank.payments;

/**
 * Transfer Class
 * @author Rober dCR
 * @date 01/04/2014
 * @brief Class about transfers between two accounts
 */
public class Transfer {

	//private Account senderAccount;
	//private Account receiverAccount;
	private float quantity;

	/**
	 * Class constructor
	 * @param sender
	 * @param receiver
	 * @param quantity
	 */
	/*public Transfer(Account sender, Account receiver, float quantity){
		this.setSenderAccount(sender);
		this.setReceiverAccount(receiver);
		this.setQuantity(quantity);
	}*/
	
	/**
	 * Getter senderAccount
	 * @return senderAccount
	 */
	/*public Account getSenderAccount() {
		return senderAccount;
	}*/
	
	/**
	 * Setter senderAccount
	 * @param senderAccount
	 */
	/*public void setSenderAccount(Account senderAccount) {
		this.senderAccount = senderAccount;
	}*/
	
	/**
	 * Getter receiverAccount
	 * @return receiverAccount
	 */
	/*public Account getReceiverAccount() {
		return receiverAccount;
	}*/
	
	/**
	 * Setter receiverAccount
	 * @param receiverAccount
	 */
	/*public void setReceiverAccount(Account receiverAccount) {
		this.receiverAccount = receiverAccount;
	}*/

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
}
