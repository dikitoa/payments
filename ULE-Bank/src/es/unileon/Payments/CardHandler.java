package es.unileon.Payments;

public class CardHandler implements Handler {
	
	private final int CARD_LENGTH = 16;
	private int bankId;
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
	 * Genera el identificador de la tarjeta
	 * @return
	 */
	private long generateCardNumber() {
		int[] digits = new int[CARD_LENGTH];
		int index = 4;
		int[] bank = intToArray(this.bankId);
		int[] office = intToArray(this.officeId);
		StringBuilder result = new StringBuilder();
		
		//Agnadimos el numero identificador de nuestro banco
		for (int i = 0; i < 4; i++) {
			digits[i] = bank[i];
		}
		
		//Agnadimos la oficina actual
		for (int i = 0; i < office.length; i++) {
			if (office.length == 1) {
				digits[index] = 0;
				digits[index+1] = office[i];
				index++;
			} else {
				digits[index] = office[i];
			}
			index++;
		}
		
		//Generamos 9 numeros aleatorios que seran el identificador de la tarjeta
		while (index < 15) {
			digits[index] = (int)(Math.random()*10);
			index++;
		}
		
		//Para terminar se genera el digito de control
		this.controlDigit = generateControlDigit(digits);
		
		digits[index] = controlDigit;
		
		for (int i = 0; i < digits.length; i++) {
			result.append(digits[i]);
		}
		
		return Long.parseLong(result.toString());
	}
	
	/**
	 * Convierte el int que recibe en un array
	 * @param number
	 * @return
	 */
	private int[] intToArray(int number) {
		String numberString = Integer.toString(number);
		String substring;
		int[] result = new int[numberString.length()];
		
		for (int i = 0; i < numberString.length(); i++) {
			if (i != numberString.length()) {
				substring = numberString.substring(i, i+1);
			} else {
				substring = numberString.substring(i);
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
