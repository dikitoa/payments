package es.unileon.ulebank.payments;

import java.io.IOException;
import java.util.Calendar;

import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.strategy.StrategyCommission;

/**
 * @author Israel
 */
public class Card {
	private final int MINIMUM_LIMIT = 200;
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
//	private Client owner;
//	private Account account;
	
	/**
	 * Crea una nueva tarjeta del tipo indicado
	 * @param type
	 */
	public Card(CardType type) {
		this.cardType = type;
	}
	
	/**
	 * Aniade los atributos a la nueva tarjeta
	 */
	public void create() {
		this.cardId = new CardHandler();
		this.pin = this.generatePinCode();
		this.buyLimitDiary = 400;
		this.buyLimitMonthly = 1000;
		this.cashLimitDiary = 400;
		this.cashLimitMonthly = 1000;
		this.expirationDate = generateExpirationDate();
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
		
		String month = Integer.toString(calendar.get(Calendar.MONTH) + 1); //+1 because month 0 = January
		if (month.length() == 1) {
			month = "0" + month;
		}
		
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
	 * @throws IOException 
	 */
	public void setPin(String pin) throws IOException {
		if (pin.length() == 4) {
			this.pin = pin;
		} else {
			throw new IOException("Incorrect length");
		}
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
	 * @throws IncorrectLimitException 
	 */
	public void setBuyLimitDiary(int buyLimit) throws IncorrectLimitException {
		if (this.buyLimitMonthly > buyLimit && buyLimit >= MINIMUM_LIMIT) {
			this.buyLimitDiary = buyLimit;
		} else {
			throw new IncorrectLimitException("The limit is bigger than current monthly limit");
		}
	}
	
	/**
	 * Comprueba que el precio no exceda el limite de la tarjeta
	 * @param price
	 * @return
	 */
	public boolean checkBuyLimitDiary(int price) {
		if (price >= buyLimitDiary) {
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
	 * @throws IncorrectLimitException 
	 */
	public void setBuyLimitMonthly(int buyLimitMonthly) throws IncorrectLimitException {
		if (buyLimitMonthly >= this.buyLimitDiary && buyLimitMonthly >= MINIMUM_LIMIT) {
			this.buyLimitMonthly = buyLimitMonthly;
		} else {
			throw new IncorrectLimitException("Monthly limit must be greater than diary limit");
		}
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
	 * @throws IncorrectLimitException 
	 */
	public void setCashLimitDiary(int cashLimit) throws IncorrectLimitException {
		if (this.cashLimitMonthly >= cashLimit && cashLimit >= MINIMUM_LIMIT) {
			this.cashLimitDiary = cashLimit;
		} else {
			throw new IncorrectLimitException("The limit is bigger than current monthly limit");
		}
	}
	
	/**
	 * Comprueba que la cantidad solicitada para extraer en cajero no exceda el limite de la tarjeta
	 * @param cash
	 * @return
	 */
	public boolean checkCashLimitDiary(int cash) {
		if (cash >= this.cashLimitDiary) {
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
	 * @throws IncorrectLimitException 
	 */
	public void setCashLimitMonthly(int cashLimitMonthly) throws IncorrectLimitException {
		if (cashLimitMonthly >= this.cashLimitDiary && cashLimitMonthly >= MINIMUM_LIMIT) {
			this.cashLimitMonthly = cashLimitMonthly;
		} else {
			throw new IncorrectLimitException("Monthly limit must be greater than diary limit");
		}
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
	public CardType getCardType() {
		return this.cardType;
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
	 * Devuelve la comision actual para la tarjeta de credito
	 * @return
	 */
	public StrategyCommission getCommission() {
		return commission;
	}

	/**
	 * Cambia la comision de la tarjeta empleando el patron Strategy.
	 * @param commission
	 */
	public void setStrategy(StrategyCommission commission) {
		this.commission = commission;
	}
	
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
}
