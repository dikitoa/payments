package es.unileon.ulebank.payments;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.exceptions.PaymentException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.history.TransactionException;
import es.unileon.ulebank.utils.CardProperties;

/**
 * @author Israel
 */
@MappedSuperclass
public abstract class Card implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Identificador de la tarjeta
	 */
	@Id
	@Column (name = "id")
	private String id;
	/**
	 * Codigo PIN de la tarjeta
	 */
	@Column (name = "pin")
	private String pin;
	/**
	 * Limite de compra diario de la tarjeta
	 */
	@Column (name = "buyLimitDiary")
	private double buyLimitDiary;
	/**
	 * Limite de compra mensual de la tarjeta
	 */
	@Column (name = "buyLimitMonthly")
	private double buyLimitMonthly;
	/**
	 * Limite de extraccion en cajero diario de la tarjeta
	 */
	@Column (name = "cashLimitDiary")
	private double cashLimitDiary;
	/**
	 * Limite de extraccion en cajero mensual de la tarjeta
	 */
	@Column (name = "cashLimitMonthly")
	private double cashLimitMonthly;
	/**
	 * Fecha de emision de la tarjeta
	 */
	@Column (name = "emissionDate")
	private String emissionDate;
	/**
	 * Fecha de caducidad de la tarjeta
	 */
	@Column (name = "expirationDate")
	private String expirationDate;
	/**
	 * Tipo de tarjeta
	 */
	@Column (name = "type")
	private String cardType;
	/**
	 * Codigo CVV de la tarjeta
	 */
	@Column (name = "cvv")
	private String cvv;
	/**
	 * Comision de emision de la tarjeta
	 */
//	@OneToOne (cascade = CascadeType.ALL, targetEntity = LinearFee.class, fetch = FetchType.EAGER)
//	@JoinColumn (name = "commissionEmission", referencedColumnName = "id")
	@Column (name = "commissionEmission")
	private double commissionEmission;
	/**
	 * Comision de mantenimiento de la tarjeta
	 */
//	@OneToOne (cascade = CascadeType.ALL, targetEntity = LinearFee.class, fetch = FetchType.EAGER)
//	@JoinColumn (name = "commissionMaintenance", referencedColumnName = "id")
	@Column (name = "commissionMaintenance")
	private double commissionMaintenance;
	/**
	 * Comision de renovacion de la tarjeta
	 */
//	@OneToOne (cascade = CascadeType.ALL, targetEntity = LinearFee.class, fetch = FetchType.EAGER)
//	@JoinColumn (name = "commissionRenovate", referencedColumnName = "id")
	@Column (name = "commissionRenovate")
	private double commissionRenovate;
	/**
	 * Cuenta a la que esta asociada la tarjeta
	 */
	@OneToOne (cascade = CascadeType.ALL, targetEntity = Account.class)
	@JoinColumn (name = "account_id", referencedColumnName = "account_number")
	private Account account;
	/**
	 * Duegno de la tarjeta
	 */
	@OneToOne (cascade = CascadeType.ALL, targetEntity = Client.class)
	@JoinColumn (name = "client_id", referencedColumnName = "client_id")
	private Client owner;
	/**
	 * Historia de las transacciones realizadas con la tarjeta
	 */
//	private History<Transaction> transactionHistory;

	/**
	 * Constructor de la clase que crea una nueva tarjeta
	 * @param properties
	 * @param cardId
	 * @param type
	 * @param buyLimitDiary
	 * @param buyLimitMonthly
	 * @param cashLimitDiary
	 * @param cashLimitMonthly
	 * @param commissionEmission
	 * @param commissionMaintenance
	 * @param commissionRenovate
	 */
	public Card(String cardId, String type, Account account, Client client, double buyLimitDiary,
			double buyLimitMonthly, double cashLimitDiary, double cashLimitMonthly, 
			double commissionEmission, double commissionMaintenance, 
			double commissionRenovate) {
		this.id = new CardHandler(cardId).toString();
		this.cardType = type;
		this.account = account;
		this.owner = client;
		this.pin = generatePinCode();
		this.buyLimitDiary = buyLimitDiary;
		this.buyLimitMonthly = buyLimitMonthly;
		this.cashLimitDiary = cashLimitDiary;
		this.cashLimitMonthly = cashLimitMonthly;
		this.emissionDate = generateEmissionDate();
		this.expirationDate = generateExpirationDate();
		this.cvv = generateCVV();
		this.commissionEmission = commissionEmission;
		this.commissionMaintenance = commissionMaintenance;
		this.commissionRenovate = commissionRenovate;
	}

	public Card(String type) {
		this.cardType = type;
	}

	/**
	 * Genera el codigo pin de la tarjeta
	 * @return String
	 */
	public String generatePinCode() {
		StringBuilder result = new StringBuilder();
		//Generamos tantos numeros aleatorios como indique la constante PIN_SIZE para formar el codigo PIN
		for (int i = 0; i < CardProperties.getPinSize(); i++) {
			result.append((int) (Math.random()*10));
		}

		return result.toString();
	}

	/**
	 * Genera la fecha de emision de la tarjeta
	 * @return String
	 */
	public String generateEmissionDate() {
		//Generamos la fecha dandole el formato estandar dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		return dateFormat.format(new Date());
	}

	/**
	 * Genera una fecha de caducidad para la tarjeta
	 * @return String
	 */
	public String generateExpirationDate() {
		//Obtenemos una instancia del calendario
		Calendar calendar = Calendar.getInstance();

		//Obtenemos el mes actual que sera devuelto en forma de int comenzando en 0 (Enero) por tanto debemos sumarle 1
		String month = Integer.toString(calendar.get(Calendar.MONTH) + 1); 
		//Si el mes esta entre enero y septiembre agnadimos un 0 delante al String para que tenga 2 cifras
		if (month.length() == 1) {
			month = "0" + month;
		}

		//Obtenemos el agno actual y cortamos el substring para coger unicamente las dos ultimas cifras
		String year = Integer.toString(CardProperties.getExpirationYear() + calendar.get(Calendar.YEAR)).substring(2);
		//Devolvemos el String con el formato MM/yy
		return month + "/" + year;
	}

	/**
	 * Genera el codigo de validacion CVV
	 * @return String
	 */
	public String generateCVV() {
		StringBuilder result = new StringBuilder();
		//Generamos tantos numeros aleatorios como indique la constante CVV_SIZE para formar el codigo CVV
		for (int i = 0; i < CardProperties.getCvvSize(); i++) {
			result.append((int) (Math.random()*10));
		}

		return result.toString();
	}

//	/**
//	 * Devuelve el identificador de la tarjeta
//	 * @return String
//	 */
//	public Handler getCardHandler() {
//		return this.id;
//	}

	/**
	 * Devuelve el codigo PIN de la tarjeta
	 * @return String
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
		//Comprobamos que el String recibido contiene solo numeros
		if (checkStringNumber(pin)) {
			//Si el pin tiene el tamagno adecuado lo cambiamos
			if (pin.length() == CardProperties.getPinSize()) {
				this.pin = pin;
				//Sino lanzamos una excepcion
			} else {
				throw new IOException("Incorrect length");
			}
			//Sino lanzamos una excepcion ya que solo puede haber numeros
		} else {
			throw new IOException("The pin must only contain numbers");
		}
	}

	/**
	 * Comprueba que el pin sea correcto
	 * @param pin
	 * @return boolean
	 */
	public boolean checkPin(String pin) {
		//Si el pin coincide devuelve true
		if (pin.equals(this.pin)) {
			return true;
			//Sino devuelve false
		} else {
			return false;
		}
	}

	/**
	 * Devuelve el limite de la tarjeta diario para compras
	 * @return double
	 */
	public double getBuyLimitDiary() {
		return this.buyLimitDiary;
	}

	/**
	 * Cambia el linmite de la tarjeta diario para compras
	 * @param newAmount
	 * @throws IncorrectLimitException 
	 */
	public void setBuyLimitDiary(double newAmount) throws IncorrectLimitException {
		//Si el limite mensual es mayor que el limite diario a cambiar y este ultimo es mayor 
		//o igual que el limite minimo, cambiamos el limite
		if (this.buyLimitMonthly > newAmount && newAmount >= CardProperties.getMinimumLimit()) {
			this.buyLimitDiary = newAmount;
			//en caso contrario lanzamos una excepcion
		} else {
			throw new IncorrectLimitException("The limit is bigger than current monthly limit or is too small.");
		}
	}

	/**
	 * Comprueba que el precio no exceda el limite de la tarjeta
	 * @param price
	 * @return boolean
	 */
	public boolean checkBuyLimitDiary(double price) {
		//Si el precio es mayor que el limite de compra diario devuelve false
		if (price > buyLimitDiary) {
			return false;
			//sino devuelve true
		} else {
			return true;
		}
	}

	/**
	 * Devuelve el limite de la tarjeta mensual para compras
	 * @return double
	 */
	public double getCashLimitMonthly() {
		return cashLimitMonthly;
	}

	/**
	 * Devuelve el limite de compra mensual
	 * @return double
	 */
	public double getBuyLimitMonthly() {
		return buyLimitMonthly;
	}

	/**
	 * Cambia el linmite de la tarjeta mensual para compras
	 * @param buyLimit
	 * @throws IncorrectLimitException 
	 */
	public void setBuyLimitMonthly(double newAmount) throws IncorrectLimitException {
		//Si el limite recibido es mayor o igual que el limite diario lo cambiamos
		if (newAmount >= this.buyLimitDiary) {
			this.buyLimitMonthly = newAmount;
			//sino se lanza una excepcion
		} else {
			throw new IncorrectLimitException("Monthly limit must be greater than diary limit");
		}
	}

	/**
	 * Devuelve el limite de la tarjeta para extracciones en cajeros
	 * @return double
	 */
	public double getCashLimitDiary() {
		return this.cashLimitDiary;
	}

	/**
	 * Cambia el limite de la tarjeta para extracciones en cajeros
	 * @throws IncorrectLimitException 
	 */
	public void setCashLimitDiary(double newAmount) throws IncorrectLimitException {
		//Si el limite mensual es mayor que el limite diario a cambiar y este ultimo es mayor 
		//o igual que el limite minimo, cambiamos el limite
		if (this.cashLimitMonthly >= newAmount && newAmount >= CardProperties.getMinimumLimit()) {
			this.cashLimitDiary = newAmount;
			//sino lanzamos una excepcion
		} else {
			throw new IncorrectLimitException("The limit is bigger than current monthly limit or is too small.");
		}
	}

	/**
	 * Comprueba que la cantidad solicitada para extraer en cajero no exceda el limite de la tarjeta
	 * @param cash
	 * @return boolean
	 */
	public boolean checkCashLimitDiary(double cash) {
		//Si la cantidad solicitada para extraer es mayor que la cantidad maxima diaria devuelve false
		if (cash > this.cashLimitDiary) {
			return false;
			//sino devuelve true
		} else {
			return true;
		}
	}

	/**
	 * Cambia la cantidad maxima para extraer en un cajero al mes
	 * @param newAmount
	 * @throws IncorrectLimitException 
	 */
	public void setCashLimitMonthly(double newAmount) throws IncorrectLimitException {
		//Si el limite recibido es mayor o igual que el limite diario lo cambiamos
		if (newAmount >= this.cashLimitDiary) {
			this.cashLimitMonthly = newAmount;
			//en caso contrario se lanza una excepcion
		} else {
			throw new IncorrectLimitException("Monthly limit must be greater than diary limit");
		}
	}

	/**
	 * Devuelve la fecha de emision de la tarjeta
	 * @return String
	 */
	public String getEmissionDate() {
		return emissionDate;
	}

	/**
	 * Devuelve la fecha de caducidad de la tarjeta
	 * @return String
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
	 * @return CardType
	 */
	public String getCardType() {
		return this.cardType.toString();
	}

	/**
	 * Devuelve el codigo de validacion CVV
	 * @return String
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
		//Comprueba que el String contenga solo numeros
		if (checkStringNumber(cvv)) {
			//Si el tamagno del cvv es correcto se cambia
			if (cvv.length() == CardProperties.getCvvSize()) {
				this.cvv = cvv;
				//sino se lanza una excepcion
			} else {
				throw new IOException("Incorrect length");
			}
			//sino se lanza una excepcion
		} else {
			throw new IOException("The cvv must only contains numbers");
		}
	}

	/**
	 * Devuelve el numero de la tarjeta
	 * @return String
	 */
	public String getId() {
		return id.toString();
	}

//	/**
//	 * Devuelve la comision de emision de la tarjeta
//	 * @return float
//	 */
//	public double getCommissionEmission(double value) {
//		return commissionEmission.getFee(value);
//	}
//
//	/**
//	 * Cambia la comision de emision por la que recibe
//	 * @param commissionEmission
//	 */
//	public void setCommissionEmission(FeeStrategy commissionEmission) {
//		this.commissionEmission = commissionEmission;
//	}
//
//	/**
//	 * Devuelve la comisionde mantenimiento de la tarjeta
//	 * @return float
//	 */
//	public double getCommissionMaintenance(double value) {
//		return commissionMaintenance.getFee(value);
//	}
//
//	/**
//	 * Cambia la comision de mantenimiento por la que se indica
//	 * @param commissionMaintenance
//	 */
//	public void setCommissionMaintenance(FeeStrategy commissionMaintenance) {
//		this.commissionMaintenance = commissionMaintenance;
//	}
//
//	/**
//	 * Devuelve la comision de renovacion de la tarjeta
//	 * @return float
//	 */
//	public double getCommissionRenovate(double value) {
//		return commissionRenovate.getFee(value);
//	}
//
//	/**
//	 * Cambia la comision de renovacion por la que se recibe
//	 * @param commissionRenovate
//	 */
//	public void setCommissionRenovate(FeeStrategy commissionRenovate) {
//		this.commissionRenovate = commissionRenovate;
//	}

	/**
	 * Comprueba que el String recibido sea solo numerico
	 * @param string
	 * @return boolean
	 */
	private boolean checkStringNumber(String string) {
		//Crea un patron para indicar que solo debe contener numeros
		Pattern pattern = Pattern.compile("^[0-9]*$");
		//Comprueba que el String recibido cumple el patron
		Matcher matcher = pattern.matcher(string);

		//Si se cumple el patron devuelve true
		if (matcher.find()) {
			return true;
			//sino devuelve false
		} else {
			return false;
		}
	}
	
	/**
	 * Devuelve el duegno de la tarjeta
	 * @return Client
	 */
	public Client getOwner() {
		return owner;
	}

	/**
	 * Method that get the account associated to the card
	 * @return account
	 */
	public Account getAccount() {
		return account;
	}

//	/**
//	 * Method that adds new transaction in the list
//	 * @param transaction
//	 * @throws TransactionException 
//	 */
//	public void addTransaction(Transaction transaction) throws TransactionException{
//		//Si devuelve false la transaccion ya esta incluida
//		if (!this.transactionHistory.add(transaction))
//			throw new TransactionException("Transacion already exists.");
//	}
	
	/**
	 * Devuelve la comision de emision
	 * @return
	 */
	public double getCommissionEmission() {
		return commissionEmission;
	}

	/**
	 * Cambia la comision de emision por la que se recibe
	 * @param commissionEmission
	 */
	public void setCommissionEmission(double commissionEmission) {
		this.commissionEmission = commissionEmission;
	}

	/**
	 * Devuelve la comision de mantenimiento
	 * @return
	 */
	public double getCommissionMaintenance() {
		return commissionMaintenance;
	}
	
	/**
	 * Cambia la comision de mantenimiento por la que se indica
	 * @param commissionMaintenance
	 */
	public void setCommissionMaintenance(double commissionMaintenance) {
		this.commissionMaintenance = commissionMaintenance;
	}

	/**
	 * Devuelve la comision de renovacion
	 * @return
	 */
	public double getCommissionRenovate() {
		return commissionRenovate;
	}

	/**
	 * Cambia la comision de renovacion por la indicada
	 * @param commissionRenovate
	 */
	public void setCommissionRenovate(double commissionRenovate) {
		this.commissionRenovate = commissionRenovate;
	}

	/**
	 * Method that makes the payment
	 * @param quantity Amount of the payment
	 * @param payConcept Concept of the payment
	 * @throws PaymentException 
	 * @throws TransactionException 
	 */
	public void makeTransaction(double quantity, String payConcept) throws PaymentException, TransactionException {

	}
}
