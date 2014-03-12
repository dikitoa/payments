package es.unileon.Payments;

import java.util.Calendar;

public abstract class Card {
	private long number;
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
		this.number = this.generateCardNumber();
		this.pin = this.generatePinCode();
		this.buyLimit = 400;
		this.cashLimit = 400;
		this.expirationDate = generateExpirationDate();
		this.cardType = type;
		this.cvv = this.generateCVV();
		this.commission = 0;
	}

	/**
	 * Genera el identificador de la tarjeta
	 * @return
	 */
	private long generateCardNumber() {
		int[] digits = new int[16];
		int index = 5;
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < 5; i++) {
			digits[i] = 4;
		}
		
		while (index < 15) {
			digits[index] = (int)(Math.random()*10);
			index++;
		}
		
		int controlDigit = 10 - ((sumOddPlaces(digits) + sumEvenPlaces(digits)) % 10);
		
		digits[index] = controlDigit;
		
		for (int i = 0; i < digits.length; i++) {
			result.append(digits[i]);
		}
		
		return Long.parseLong(result.toString());
	}
	
	/**
	 * Realiza la suma de las posiciones impares de izquierda a derecha del numero de la tarjeta
	 * @param digits
	 * @return
	 */
	private int sumOddPlaces(int[] digits) {
		int sum = 0;
		//Recorremos las posiciones impares
		for (int i = 0; i < digits.length; i+=2) {
			int aux = digits[i]*2;
			if (aux > 9) {
				aux = aux%9;
			}
			sum += aux;
		}
		
		return sum;
	}
	
	/**
	 * Realiza la suma de las posiciones pares de izquierda a derecha del numero de la tarjeta
	 * @param digits
	 * @return
	 */
	private int sumEvenPlaces(int[] digits) {
		int sum = 0;
		//Recorremos las posiciones pares
		for (int i = 1; i < digits.length; i+=2) {
			sum += digits[i];
		}
		
		return sum;
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
		// TODO Auto-generated method stub
		return (int)(Math.random()*1000);
	}
	
	/**
	 * Devuelve el identificador de la tarjeta
	 * @return
	 */
	public long getNumber() {
		return this.number;
	}
	
	/**
	 * Cambia el numero de la tarjeta por el que recibe
	 * @param cardNumner
	 */
	public void setNumber(long cardNumner) {
		this.number = cardNumner;
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
