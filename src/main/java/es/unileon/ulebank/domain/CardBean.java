package es.unileon.ulebank.domain;


/**
 * @author Israel
 * Clase que representa un POJO para poder manejar los datos facilmente desde los controladores
 */
/**
 * @author isra
 *
 */
public class CardBean {
	/**
	 * Numero de tarjeta
	 */
	private String cardNumber;
	/**
	 * Limite de compra diario
	 */
	private double buyLimitDiary;
	/**
	 * Limite de compra mensual
	 */
	private double buyLimitMonthly;
	/**
	 * Limite de extraccion en cajero diario
	 */
	private double cashLimitDiary;
	/**
	 * Limite de extraccion en cajero mensual
	 */
	private double cashLimitMonthly;
	/**
	 * Comision de emision de la tarjeta
	 */
	private int fee;
	/**
	 * Booleano que comprueba que se acepte el contrato de la tarjeta
	 */
	private boolean contract;

	private String accountNumber;

	private String dni;
	
	private Offices office;
	
	private String cardType;

	/**
	 * Devuelve el numero de tarjeta en String
	 * @return
	 */
	public String getCardNumber() {
		return this.cardNumber;
	}

	/**
	 * Cambia el numero de tarjeta por el que recibe por parametro
	 * @param cardNumber
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * Devuelve el limite de compra diario de la tarjeta
	 * @return
	 */
	public double getBuyLimitDiary() {
		return this.buyLimitDiary;
	}

	/**
	 * Cambia el limite de compra diario por el que recibe
	 * @param buyLimitDiary
	 */
	public void setBuyLimitDiary(double buyLimitDiary) {
		this.buyLimitDiary = buyLimitDiary;
	}

	/**
	 * Devuelve el limite de compra mensual de la tarjeta
	 * @return
	 */
	public double getBuyLimitMonthly() {
		return this.buyLimitMonthly;
	}

	/**
	 * Cambia el limite de compra mensual por el indicado
	 * @param buyLimitMonthly
	 */
	public void setBuyLimitMonthly(double buyLimitMonthly) {
		this.buyLimitMonthly = buyLimitMonthly;
	}

	/**
	 * Devuelve el limite de extraccion en cajero diario de la tarjeta
	 * @return
	 */
	public double getCashLimitDiary() {
		return this.cashLimitDiary;
	}

	/**
	 * Cambia el limite de extraccion en cajero diario por el que recibe
	 * @param cashLimitDiary
	 */
	public void setCashLimitDiary(double cashLimitDiary) {
		this.cashLimitDiary = cashLimitDiary;
	}

	/**
	 * Devuelve el limite de extraccion en cajero mensual de la tarjeta
	 * @return
	 */
	public double getCashLimitMonthly() {
		return this.cashLimitMonthly;
	}

	/**
	 * Cambia el limite de extraccion en cajero mensual por el que se indica
	 * @param cashLimitMonthly
	 */
	public void setCashLimitMonthly(double cashLimitMonthly) {
		this.cashLimitMonthly = cashLimitMonthly;
	}

	/**
	 * Devuelve la comision de emision de la tarjeta
	 * @return
	 */
	public int getFee() {
		return this.fee;
	}

	/**
	 * Cambia la comision de emision por la que se indica
	 * @param commissionEmission
	 */
	public void setFee(int commissionEmission) {
		this.fee = commissionEmission;
	}



	/**
	 * Devuelve si se ha aceptado el contrato o no
	 * @return
	 */
	public boolean isContract() {
		return this.contract;
	}

	/**
	 * Cambia el booleano que comprueba si se acepta el contrato por el valor recibido
	 * @param contract
	 */
	public void setContract(boolean contract) {
		this.contract = contract;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Offices getOffice() {
		return office;
	}

	public void setOffice(Offices office) {
		this.office = office;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
}