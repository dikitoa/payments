package es.unileon.Payments;

import java.util.Calendar;

public class Card {
	private CardHandler cardId;
	private int pin;
	private int buyLimit;
	private int cashLimit;
	private String expirationDate;
	private CardType cardType;
	private int cvv;
	private int commission;
	
	/**
	 * Crea una nueva tarjeta del tipo indicado
	 * @param type
	 */
	public Card(CardType type) {
		this.cardId = new CardHandler();
		this.pin = this.generatePinCode();
		this.buyLimit = 400;
		this.cashLimit = 400;
		this.expirationDate = generateExpirationDate();
		this.cardType = type;
		this.cvv = this.generateCVV();
		this.commission = 0;
	}
	
	/**
	 * Genera el codigo pin de la tarjeta
	 * @return
	 */
	private int generatePinCode() {
		return (int)(Math.random()*(10000-1000+1) + 1000);
	}
	
	/**
	 * Genera una fecha de caducidad para la tarjeta
	 * @return
	 */
	private String generateExpirationDate() {
		Calendar calendar = Calendar.getInstance();
		
		String month = Integer.toString(calendar.get(Calendar.MONTH));
		String year = Integer.toString(3 + calendar.get(Calendar.YEAR)).substring(2);
		
		return month + "/" + year;
	}
	
	/**
	 * Genera el codigo de validacion CVV
	 * @return
	 */
	private int generateCVV() {
		return (int)(Math.random()*1000);
	}
	
	/**
	 * Devuelve el identificador de la tarjeta
	 * @return
	 */
	public long getCardId() {
		return Long.parseLong(this.cardId.toString());
	}
	
	/**
	 * Cambia el numero de la tarjeta por el que recibe
	 * @param cardNumner
	 */
	public void setCardId(long cardNumber) {
		this.cardId.setCardNumber(cardNumber);;
	}
	
	/**
	 * Devuelve el codigo PIN de la tarjeta
	 * @return
	 */
	public int getPin() {
		return this.pin;
	}
	
	/**
	 * Cambia el codigo PIN de la tarjeta por el que recibe
	 * @param pin
	 */
	public void setPin(int pin) {
		this.pin = pin;
	}
	
	/**
	 * Devuelve el limite de la tarjeta para compras
	 * @return
	 */
	public int getBuyLimit() {
		return this.buyLimit;
	}
	
	/**
	 * Cambia el linmite de la tarjeta para compras
	 * @param buyLimit
	 */
	public void setBuyLimit(int buyLimit) {
		this.buyLimit = buyLimit;
	}
	
	/**
	 * Devuelve el limite de la tarjeta para extracciones en cajeros
	 * @return
	 */
	public int getCashLimit() {
		return this.cashLimit;
	}
	
	/**
	 * Cambia el limite de la tarjeta para extracciones en cajeros
	 */
	public void setCashLimit(int cashLimit) {
		this.cashLimit = cashLimit;
	}
	
	/**
	 * Devuelve la fecha de caducidad de la tarjeta
	 * @return
	 */
	public String getExpirationDate() {
		return this.expirationDate;
	}
	
	/**
	 * Cambia la fecha de caducidad por una nueva
	 * @param expirationDate
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * Devuelve el tipo de tarjeta
	 * @return
	 */
	public String getCardType() {
		return this.cardType.toString();
	}

	/**
	 * Cambia el tipo de tarjeta por el indicado
	 * @param cardType
	 */
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	/**
	 * Devuelve el codigo de validacion CVV
	 * @return
	 */
	public int getCvv() {
		return this.cvv;
	}

	/**
	 * Cambia el CVV por el nuevo que ha recibido
	 * @param cvv
	 */
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	/**
	 * Devuelve la comision de la tarjeta
	 * @return
	 */
	public int getCommission() {
		return this.commission;
	}

	/**
	 * Cambia la comision de la tarjeta por la que se indica
	 * @param commission
	 */
	public void setCommission(int commission) {
		this.commission = commission;
	}
	
	
}
