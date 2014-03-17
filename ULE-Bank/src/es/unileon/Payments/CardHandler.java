package es.unileon.Payments;

public class CardHandler implements Handler {
	
	private final int CARD_LENGTH = 16;
	private int bankId;
	private int cardId;
	private long cardNumber;
	private int officeId;
	private int controlDigit;
	
	public CardHandler() {
		this.bankId = 1234;
		this.officeId = 01;
		this.cardNumber = generateCardNumber();
	}
	
	public CardHandler(int bankId, int officeId) {
		this.bankId = bankId;
		this.officeId = officeId;
		this.cardNumber = generateCardNumber();
	}
	
	/**
	 * Genera el numero completo de la tarjeta
	 * @return
	 */
	private long generateCardNumber() {
		StringBuilder result = new StringBuilder();
		
		//Agnadimos el identificador de nuestro banco
		result.append(String.valueOf(this.bankId));
		//Agnadimos la oficina actual asegurandonos de que tiene 2 digitos
		if (this.officeId < 10) {
			result.append(0);
			result.append(this.officeId);
		} else {
			result.append(this.officeId);
		}
		//Generamos el identificador de la tarjeta rellenando los digitos restantes menos 
		//el ultimo que es el digito de control
		this.cardId = generateCardId(CARD_LENGTH - (result.toString().length()+1));
		result.append(String.valueOf(this.cardId));
		//Por ultimo generamos el digito de control
		this.controlDigit = generateControlDigit(stringToIntArray(result.toString()));
		result.append(String.valueOf(this.controlDigit));
		
		return Long.parseLong(result.toString());
	}
	
	/**
	 * Genera el identificador de la tarjeta rellenandolo con n numeros aleatorios
	 * @param n
	 * @return
	 */
	private int generateCardId(int n) {
		int index = 0;
		StringBuilder result = new StringBuilder();
		
		while (index < n) {
			result.append((int)(Math.random()*10));
			index++;
		}
		
		return Integer.parseInt(result.toString());
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
	 * Genera el digito de control
	 * @param digits
	 * @return
	 */
	private int generateControlDigit(int[] digits) {
		return 10 - ((sumOddPlaces(digits) + sumEvenPlaces(digits)) % 10);
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
	 * Devuelve el identificador del banco
	 * @return
	 */
	public int getBankId() {
		return bankId;
	}

	/**
	 * Cambia el identificador del banco
	 * @param bankId
	 */
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	/**
	 * Devuelve el numero de la tarjeta completo
	 * @return
	 */
	public long getCardNumber() {
		return cardNumber;
	}

	/**
	 * Cambia el numero de la tarjeta por el que se indica
	 * @param cardNumber
	 */
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * Devuelve el identificador de la sucursal
	 * @return
	 */
	public int getOfficeId() {
		return officeId;
	}

	/**
	 * Cambia el identificador de la sucursal por el que se recibe
	 * @param officeId
	 */
	public void setOfficeId(int officeId) {
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
	public int getCardId() {
		return cardId;
	}
	
	/**
	 * Cambia el identificador de la tarjeta por el que recibe
	 * @param cardId
	 */
	public void setCardId(int cardId) {
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
	 * Devuelve el identificador en forma de String
	 */
	public String toString() {
		return String.valueOf(this.cardNumber);
	}
}
