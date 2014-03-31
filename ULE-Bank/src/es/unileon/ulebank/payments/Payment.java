package es.unileon.ulebank.payments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Payment Class
 * @author Rober dCR
 * @date 26/03/2014
 * @brief Aggregation of the payment done by card
 */
public class Payment {

	private float quantity;
	private String concept;
	private Calendar paymentDate;
	private Card payCard;
	
	/**
	 * Class constructor
	 * @param card
	 * @param payQuantity
	 * @param payConcept
	 */
	Payment(Card card, float payQuantity, String payConcept){
		this.setPayCard(card);
		this.setQuantity(payQuantity);
		this.setConcept(payConcept);
		this.setPaymentDate(Calendar.getInstance());
	}

	/**
	 * Getter of Quantity
	 * @return payment quantity 
	 */
	public float getQuantity() {
		return quantity;
	}

	/**
	 * Setter of Quantity
	 * @param quantity
	 */
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	/**
	 * Getter Concept
	 * @return payment concept
	 */
	public String getConcept() {
		return concept;
	}

	/**
	 * Setter Concept
	 * @param concept
	 */
	public void setConcept(String concept) {
		this.concept = concept;
	}

	/**
	 * Getter date of payment in the format
	 * "dd/MM/yyyy HH:mm:ss"
	 * @return date
	 */
	public String getPaymentDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return dateFormat.format(paymentDate);
	}

	/**
	 * Setter payment date
	 * @param paymentDate
	 */
	public void setPaymentDate(Calendar paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * Getter Card which make the payment
	 * @return card
	 */
	public Card getPayCard() {
		return payCard;
	}

	/**
	 * Setter Card which make the payment
	 * @param payCard
	 */
	public void setPayCard(Card payCard) {
		this.payCard = payCard;
	}
}
