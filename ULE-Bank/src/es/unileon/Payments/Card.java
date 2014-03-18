package es.unileon.Payments;

import java.util.Calendar;

public class Card {
	private CardHandler cardId;
	private String pin;
	private int buyLimitDiary;
	private int buyLimitMonthly;
	private int cashLimitDiary;
	private int cashLimitMonthly;
	private String expirationDate;
	private CardType cardType;
	private String cvv;
	private StrategyCommission commission;
//	private Handler ownerId;
//	private Account account;
	
	/**
	 * Crea una nueva tarjeta del tipo indicado
	 * @param type
	 */
	public Card(CardType type) {
		this.cardId = new CardHandler();
		this.pin = this.generatePinCode();
		this.buyLimitDiary = 400;
		this.buyLimitMonthly = 1000;
		this.cashLimitDiary = 400;
		this.cashLimitMonthly = 1000;
		this.expirationDate = generateExpirationDate();
		this.cardType = type;
		this.cvv = this.generateCVV();
	}
	
	/**
	 * Crea una nueva tarjeta asignando los limites que se indican
	 * @param type
	 */
	public Card(CardType type, int buyLimit, int cashLimit) {
		this.cardId = new CardHandler();
		this.pin = this.generatePinCode();
		this.buyLimitDiary = buyLimit;
		this.buyLimitMonthly = 1000;
		this.cashLimitDiary = cashLimit;
		this.cashLimitMonthly = 1000;
		this.expirationDate = generateExpirationDate();
		this.cardType = type;
		this.cvv = this.generateCVV();
	}
	
	/**
	 * Genera el codigo pin de la tarjeta
	 * @return
	 */
	public String generatePinCode() {
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < 4; i++) {
			result.append((int) (Math.random()*10));
		}
		
		return result.toString();
	}
	
	/**
	 * Genera una fecha de caducidad para la tarjeta
	 * @return
	 */
	public String generateExpirationDate() {
		Calendar calendar = Calendar.getInstance();
		
		String month = Integer.toString(calendar.get(Calendar.MONTH));
		String year = Integer.toString(3 + calendar.get(Calendar.YEAR)).substring(2);
		
		return month + "/" + year;
	}
	
	/**
	 * Genera el codigo de validacion CVV
	 * @return
	 */
	public String generateCVV() {
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < 3; i++) {
			result.append((int) (Math.random()*10));
		}
		
		return result.toString();
	}
	
	/**
	 * Devuelve el identificador de la tarjeta
	 * @return
	 */
	public String getCardId() {
		return this.cardId.toString();
	}
	
	/**
	 * Cambia el numero de la tarjeta por el que recibe
	 * @param cardNumner
	 */
	public void setCardId(String cardNumber) {
		this.cardId.setCardNumber(cardNumber);
	}
	
	/**
	 * Devuelve el codigo PIN de la tarjeta
	 * @return
	 */
	public String getPin() {
		return this.pin;
	}
	
	/**
	 * Cambia el codigo PIN de la tarjeta por el que recibe
	 * @param pin
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}
	
	/**
	 * Comprueba que el pin sea correcto
	 * @param pin
	 * @return
	 */
	public boolean checkPin(String pin) {
		if (pin.equals(this.pin)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Devuelve el limite de la tarjeta diario para compras
	 * @return
	 */
	public int getBuyLimitDiary() {
		return this.buyLimitDiary;
	}
	
	/**
	 * Cambia el linmite de la tarjeta diario para compras
	 * @param buyLimit
	 */
	public void setBuyLimitDiary(int buyLimit) {
		this.buyLimitDiary = buyLimit;
	}
	
	/**
	 * Comprueba que el precio no exceda el limite de la tarjeta
	 * @param price
	 * @return
	 */
	public boolean checkBuyLimitDiary(int price) {
		if (price > buyLimitDiary) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Devuelve el limite de la tarjeta mensual para compras
	 * @return
	 */
	public int getBuyLimitMonthly() {
		return buyLimitMonthly;
	}

	/**
	 * Cambia el linmite de la tarjeta mensual para compras
	 * @param buyLimit
	 */
	public void setBuyLimitMonthly(int buyLimitMonthly) {
		this.buyLimitMonthly = buyLimitMonthly;
	}

	/**
	 * Devuelve el limite de la tarjeta para extracciones en cajeros
	 * @return
	 */
	public int getCashLimitDiary() {
		return this.cashLimitDiary;
	}
	
	/**
	 * Cambia el limite de la tarjeta para extracciones en cajeros
	 */
	public void setCashLimitDiary(int cashLimit) {
		this.cashLimitDiary = cashLimit;
	}
	
	/**
	 * Comprueba que la cantidad solicitada para extraer en cajero no exceda el limite de la tarjeta
	 * @param cash
	 * @return
	 */
	public boolean checkCashLimitDiary(int cash) {
		if (cash > this.cashLimitDiary) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Devuelve la cantidad maxima para extraer en cajero mensual
	 * @return
	 */
	public int getCashLimitMonthly() {
		return cashLimitMonthly;
	}

	/**
	 * Cambia la cantidad maxima para extraer en un cajero al mes
	 * @param cashLimitMonthly
	 */
	public void setCashLimitMonthly(int cashLimitMonthly) {
		this.cashLimitMonthly = cashLimitMonthly;
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
	public String getCvv() {
		return this.cvv;
	}

	/**
	 * Cambia el CVV por el nuevo que ha recibido
	 * @param cvv
	 */
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	/**
	 * Devuelve la comision de la tarjeta
	 * @return
	 */
//	public float getCommission() {
//		return this.commission;
//	}
	
	/**
	 * Devuelve la cuenta de usuario actual
	 * @return
	 */
//	public Account getAccount() {
//		return this.account;
//	}
	
	/**
	 * Cambia la cuenta actual por la que recibe por parametro
	 * @param account
	 */
//	public void setAccount(Account account) {
//		this.account = account;
//	}

	/**
	 * Cambia la comision de la tarjeta por la que se indica
	 * @param commission
	 */
	public void setCommission(StrategyCommission commission) {
		this.commission = commission;
	}
	
	
}
