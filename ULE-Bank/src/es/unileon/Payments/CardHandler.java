package es.unileon.Payments;

public class CardHandler implements Handler {
	
	private final int CARD_LENGTH = 16;
	//Identificador de nuestro banco
	private String bankId;
	//Identificador de la tarjeta
	private String cardId;
	//Numero completo de la tarjeta
	private String cardNumber;
	//Identificador de la oficina
	private String officeId;
	//Digito de control
	private int controlDigit;
	
	public CardHandler() {
		this.bankId = "1234";
		this.officeId = "01";
		this.cardNumber = generateCardNumber();
	}
	
	public CardHandler(String bankId, String officeId) {
		this.bankId = bankId;
		this.officeId = officeId;
		this.cardNumber = generateCardNumber();
	}
	
	/**
	 * Genera el numero completo de la tarjeta
	 * @return
	 */
	protected String generateCardNumber() {
		StringBuilder result = new StringBuilder();
		
		//Agnadimos el identificador de nuestro banco
		result.append(this.bankId);
		//Agnadimos la oficina actual asegurandonos de que tiene 2 digitos
		if (this.officeId.length() < 2) {
			result.append(0);
			result.append(this.officeId);
		} else {
			result.append(this.officeId);
		}
		//Generamos el identificador de la tarjeta rellenando los digitos restantes menos 
		//el ultimo que es el digito de control
		this.cardId = generateCardId(CARD_LENGTH - (result.toString().length()+1));
		result.append(this.cardId);
		//Por ultimo generamos el digito de control
		this.controlDigit = generateControlDigit(stringToIntArray(result.toString()));
		result.append(this.controlDigit);
		
		return result.toString();
	}
	
	/**
	 * Genera el identificador de la tarjeta rellenandolo con n numeros aleatorios
	 * @param n
	 * @return
	 */
	protected String generateCardId(int n) {
		int index = 0;
		StringBuilder result = new StringBuilder();
		
		while (index < n) {
			result.append((int)(Math.random()*10));
			index++;
		}
		
		return result.toString();
	}
	
	/**
	 * Convierte el String que recibe a un array de enteros
	 * @param string
	 * @return
	 */
	private int[] stringToIntArray(String string) {
		int[] result = new int[string.length()];
		String substring;
		
		for (int i = 0; i < string.length(); i++) {
			if (i != string.length()) {
				substring = string.substring(i, i+1);
			} else {
				substring = string.substring(i);
			}
			result[i] = Integer.parseInt(substring);
		}
		
		return result;
	}
	
	/**
	 * Genera el digito de control empleando el algoritmo de Luhn
	 * @param digits
	 * @return
	 */
	protected int generateControlDigit(int[] digits) {
		return 10 - verifyCardNumber(digits);
	}
	
	/**
	 * Verifica que el n�mero de tarjeta es correcto si el resultado es 0,
	 * tambien se puede emplear para generar el d�gito de control realizando 
	 * la operaci�n 10-verifyCardNumber(digits[])
	 * @param digits
	 * @return
	 */
	protected int verifyCardNumber(int[] digits) {
		return ((sumOddPlaces(digits) + sumEvenPlaces(digits)) % 10);
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
			//Doblamos el digito y lo guardamos
			int aux = digits[i] << 1;
			//Si el numero tiene 2 d�gitos los sumamos juntos
			if (aux >= 10) {
				sum += 1 + aux - 10;
			} else {
				sum += aux;
			}
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
		//Recorremos las posiciones pares y sumamos los d�gitos
		for (int i = 1; i < digits.length; i+=2) {
			sum += digits[i];
		}
		
		return sum;
	}
	
	/**
	 * Devuelve el identificador del banco
	 * @return
	 */
	public String getBankId() {
		return bankId;
	}

	/**
	 * Cambia el identificador del banco
	 * @param bankId
	 */
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	/**
	 * Devuelve el numero de la tarjeta completo
	 * @return
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * Cambia el numero de la tarjeta por el que se indica
	 * @param cardNumber
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * Devuelve el identificador de la sucursal
	 * @return
	 */
	public String getOfficeId() {
		return officeId;
	}

	/**
	 * Cambia el identificador de la sucursal por el que se recibe
	 * @param officeId
	 */
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	/**
	 * Devuelve el digito de control de la tarjeta
	 * @return
	 */
	public int getControlDigit() {
		return controlDigit;
	}

	/**
	 * Cambia el digito de control por el recibido
	 * @param controlDigit
	 */
	public void setControlDigit(int controlDigit) {
		this.controlDigit = controlDigit;
	}
	
	/**
	 * Devuelve el identificador de la tarjeta
	 * @return
	 */
	public String getCardId() {
		return cardId;
	}
	
	/**
	 * Cambia el identificador de la tarjeta por el que recibe
	 * @param cardId
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	/**
	 * Compara el identificador actual con el que se indica
	 * @return devuelve un 0 si son iguales
	 * @return devuelve otro numero si son distintos
	 */
	@Override
	public int compareTo(Handler another) {
		return this.toString().compareTo(another.toString());
	}
	
	/**
	 * Devuelve el identificador con el formato de la tarjeta dividido en bloques de 4
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		String substring;
		
		for (int i = 0; i < cardNumber.length(); i++) {
			if (i != cardNumber.length()) {
				substring = cardNumber.substring(i, i+1);
			} else {
				substring = cardNumber.substring(i);
			}
			result.append(substring);
			
			if ((i+1)%4 == 0) {
				result.append(" ");
			}
		}
		
		return result.toString();
	}
}
