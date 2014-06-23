package es.unileon.ulebank.service;

/**
 * Clase que almacena los datos del formulario para hacer el pago.
 * @author Rober dCR
 *
 */
public class PaymentBean {
	/**
	 * Amount of the payment
	 */
    private double amount;
	/**
	 * Concept of the payment
	 */
	private String concept;
	
	/**
	 * Amount getter
	 * @return amount of the payment
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Amount setter
	 * @param amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Concept getter
	 * @return concept of the payment
	 */
	public String getConcept() {
		return concept;
	}

	/**
	 * Concept setter
	 * @param concept
	 */
	public void setConcept(String concept) {
		this.concept = concept;
	}

}
