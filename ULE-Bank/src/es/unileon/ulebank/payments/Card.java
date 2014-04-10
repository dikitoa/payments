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
	private final float BUY_LIMIT_DIARY_DEFAULT = 400F;
	private final float BUY_LIMIT_MONTHLY_DEFAULT = 1000F;
	private final float CASH_LIMIT_DIARY_DEFAULT = 400F;
	private final float CASH_LIMIT_MONTHLY_DEFAULT = 1000F;
	private final int MINIMUM_LIMIT = 200;
	private final int EXPIRATION_YEAR = 3;
	private final int CVV_SIZE = 3;
	private final int PIN_SIZE = 4;
	
	private CardHandler cardId;
	private String pin;
	private float buyLimitDiary;
	private float buyLimitMonthly;
	private float cashLimitDiary;
	private float cashLimitMonthly;
	private Calendar emissionDate;
	private String expirationDate;
	private CardType cardType;
	private String cvv;
	private StrategyCommission commissionEmission;
	private StrategyCommission commissionMaintenance;
	private StrategyCommission commissionRenovate;
	private Client owner;
	private Account account;
	private float limitDebit;
	
	/**
	 * Crea una nueva tarjeta con los parametros indicados
	 * @param cardId
	 * @param owner
	 * @param account
	 * @param type
	 * @param buyLimitDiary
	 * @param buyLimitMonthly
	 * @param cashLimitDiary
	 * @param cashLimitMonthly
	 * @param commissionEmission
	 * @param commissionMaintenance
	 * @param commissionRenovate
	 * @param limitDebit
	 */
	public Card(CardHandler cardId, Client owner, Account account, CardType type,
			int buyLimitDiary, int buyLimitMonthly, int cashLimitDiary, int cashLimitMonthly,
			StrategyCommission commissionEmission, StrategyCommission commissionMaintenance, 
			StrategyCommission commissionRenovate, float limitDebit) {
		this.cardId = cardId;
		this.owner = owner;
		this.account = account;
		this.cardType = type;
		this.pin = generatePinCode();
		this.buyLimitDiary = buyLimitDiary;
		this.buyLimitMonthly = buyLimitMonthly;
		this.cashLimitDiary = cashLimitDiary;
		this.cashLimitMonthly = cashLimitMonthly;
		this.emissionDate = generateEmissionDate();
		this.expirationDate = generateExpirationDate();
		this.cvv = this.generateCVV();
		this.commissionEmission = commissionEmission;
		this.commissionMaintenance = commissionMaintenance;
		this.commissionRenovate = commissionRenovate;
		this.limitDebit = limitDebit;
	}
	
	public Card(CardType type) {
		this.cardType = type;
	}

	/**
	 * Genera el codigo pin de la tarjeta
	 * @return
	 */
	public String generatePinCode() {
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < PIN_SIZE; i++) {
			result.append((int) (Math.random()*10));
		}
		
		return result.toString();
	}
	
	/**
	 * Genera la fecha de emision de la tarjeta
	 * @return
	 */
	private Calendar generateEmissionDate() {
		return Calendar.getInstance();
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
		
		String year = Integer.toString(EXPIRATION_YEAR + calendar.get(Calendar.YEAR)).substring(2);
		
		return month + "/" + year;
	}
	
	/**
	 * Genera el codigo de validacion CVV
	 * @return
	 */
	public String generateCVV() {
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < CVV_SIZE; i++) {
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
		if (pin.length() == PIN_SIZE) {
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
	public float getBuyLimitDiary() {
		return this.buyLimitDiary;
	}
	
	/**
	 * Cambia el linmite de la tarjeta diario para compras
	 * @param newAmount
	 * @throws IncorrectLimitException 
	 */
	public void setBuyLimitDiary(float newAmount) throws IncorrectLimitException {
		if (this.buyLimitMonthly > newAmount && newAmount >= MINIMUM_LIMIT) {
			this.buyLimitDiary = newAmount;
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
	public float getBuyLimitMonthly() {
		return buyLimitMonthly;
	}

	/**
	 * Cambia el linmite de la tarjeta mensual para compras
	 * @param buyLimit
	 * @throws IncorrectLimitException 
	 */
	public void setBuyLimitMonthly(float newAmount) throws IncorrectLimitException {
		if (newAmount >= this.buyLimitDiary && newAmount >= MINIMUM_LIMIT) {
			this.buyLimitMonthly = newAmount;
		} else {
			throw new IncorrectLimitException("Monthly limit must be greater than diary limit");
		}
	}

	/**
	 * Devuelve el limite de la tarjeta para extracciones en cajeros
	 * @return
	 */
	public float getCashLimitDiary() {
		return this.cashLimitDiary;
	}
	
	/**
	 * Cambia el limite de la tarjeta para extracciones en cajeros
	 * @throws IncorrectLimitException 
	 */
	public void setCashLimitDiary(float newAmount) throws IncorrectLimitException {
		if (this.cashLimitMonthly >= newAmount && newAmount >= MINIMUM_LIMIT) {
			this.cashLimitDiary = newAmount;
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
	public float getCashLimitMonthly() {
		return cashLimitMonthly;
	}

	/**
	 * Cambia la cantidad maxima para extraer en un cajero al mes
	 * @param newAmount
	 * @throws IncorrectLimitException 
	 */
	public void setCashLimitMonthly(float newAmount) throws IncorrectLimitException {
		if (newAmount >= this.cashLimitDiary && newAmount >= MINIMUM_LIMIT) {
			this.cashLimitMonthly = newAmount;
		} else {
			throw new IncorrectLimitException("Monthly limit must be greater than diary limit");
		}
	}
	
	/**
	 * Devuelve la fecha de emision de la tarjeta
	 * @return
	 */
	public Calendar getEmissionDate() {
		return emissionDate;
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
	 * @throws IOException 
	 */
	public void setCvv(String cvv) throws IOException {
		if (cvv.length() == CVV_SIZE) {
			this.cvv = cvv;
		} else {
			throw new IOException("Incorrect length");
		}
	}

	/**
	 * Devuelve el numero de la tarjeta
	 * @return
	 */
	public CardHandler getCardNumber() {
		return cardId;
	}

	/**
	 * Devuelve el limite diario de compra por defecto
	 * @return
	 */
	public float getBuyLimitDiaryDefault() {
		return BUY_LIMIT_DIARY_DEFAULT;
	}

	/**
	 * Devuelve el limite mensual de compra por defecto
	 * @return
	 */
	public float getBuyLimitMonthlyDefault() {
		return BUY_LIMIT_MONTHLY_DEFAULT;
	}

	/**
	 * Devuelve el limite diario de extraccion en cajero por defecto
	 * @return
	 */
	public float getCashLimitDiaryDefault() {
		return CASH_LIMIT_DIARY_DEFAULT;
	}

	/**
	 * Devuelve el limite mensual de extraccion en cajero por defecto
	 * @return
	 */
	public float getCashLimitMonthlyDefault() {
		return CASH_LIMIT_MONTHLY_DEFAULT;
	}

	/**
	 * Devuelve la comision de emision de la tarjeta
	 * @return
	 */
	public StrategyCommission getCommissionEmission() {
		return commissionEmission;
	}

	/**
	 * Cambia la comision de emision por la que recibe
	 * @param commissionEmission
	 */
	public void setCommissionEmission(StrategyCommission commissionEmission) {
		this.commissionEmission = commissionEmission;
	}

	/**
	 * Devuelve la comisionde mantenimiento de la tarjeta
	 * @return
	 */
	public StrategyCommission getCommissionMaintenance() {
		return commissionMaintenance;
	}

	/**
	 * Cambia la comision de mantenimiento por la que se indica
	 * @param commissionMaintenance
	 */
	public void setCommissionMaintenance(StrategyCommission commissionMaintenance) {
		this.commissionMaintenance = commissionMaintenance;
	}

	/**
	 * Devuelve la comision de renovacion de la tarjeta
	 * @return
	 */
	public StrategyCommission getCommissionRenovate() {
		return commissionRenovate;
	}

	/**
	 * Cambia la comision de renovacion por la que se recibe
	 * @param commissionRenovate
	 */
	public void setCommissionRenovate(StrategyCommission commissionRenovate) {
		this.commissionRenovate = commissionRenovate;
	}

	public Client getOwner() {
		return owner;
	}

	public Account getAccount() {
		return account;
	}

	public float getLimitDebit() {
		return limitDebit;
	}

	public void setLimitDebit(float limitDebt) {
		this.limitDebit = limitDebt;
	}
}
