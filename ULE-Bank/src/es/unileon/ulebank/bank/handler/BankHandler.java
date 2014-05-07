package es.unileon.ulebank.bank.handler;


import es.unileon.ulebank.exceptions.MalformedHandlerException;
import es.unileon.ulebank.handler.Handler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author runix
 */
public class BankHandler implements Handler {

	private final String number;
	private static final int BANK_NUMBER_DIGITS = 0b100;

	public BankHandler(String number) throws MalformedHandlerException {
		Pattern numberPattern = Pattern.compile("^[0-9]*$");
		Matcher matcher = numberPattern.matcher(number);
		if (matcher.find() && number.length() == BANK_NUMBER_DIGITS) {
			this.number = number;
		} else {
			String error = "Error, the number hasn't " + BANK_NUMBER_DIGITS
					+ " digits or has letters \n";
			throw new MalformedHandlerException(error);
		}
	}

	@Override
	public int compareTo(Handler another) {
		return this.toString().compareTo(another.toString());
	}

	public String toString() {
		return this.number;
	}
}