/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.handler;

import es.unileon.ulebank.exceptions.MalformedHandlerException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author runix
 */
public class AccountHandler implements Handler {

    /**
     * The account number, the number of digits of this number is given by {
     *
     * @see ACCOUNT_NUMBER_LENGHT}
     */
    private final String accountNumber;

    /**
     * The office identifier
     */
    private final Handler officeHandler;
    /**
     * The bank identifier
     */
    private final Handler bankHandler;
    /**
     * the number of digits ( account number )
     */
    private static final int ACCOUNT_NUMBER_LENGTH = 10;
    /**
     * the number of digits ( office number )
     */
    private static final int OFFICE_NUMBER_LENGTH = 4;
    /**
     * the number of digits ( bank number )
     */
    private static final int BANK_NUMBER_LENGTH = 4;

    /**
     * The control digits
     */
    private final String dc;

    /**
     * Create a new AccountHandler
     *
     * @param office ( the office id )
     * @param bank ( the bank id )
     * @param accountNumber ( the account number )
     * @author runix
     * @throws MalformedHandlerException ( If the account number, office
     * handler, or bank handler aren't correct )
     */
    public AccountHandler(Handler office, Handler bank, String accountNumber) throws MalformedHandlerException {
        StringBuilder errors = new StringBuilder();
        Pattern numberPattern = Pattern.compile("^[0-9]*$");
        
        
        Matcher matcher = numberPattern.matcher(accountNumber);
        if(!matcher.find()) {
            errors.append("The accountNumber can only have numbers\n");
        }
        
        if (accountNumber.length() != ACCOUNT_NUMBER_LENGTH) {
            errors.append("The accountNumber length must be " + ACCOUNT_NUMBER_LENGTH + "\n");
        }

        matcher = numberPattern.matcher(office.toString());
        if (!matcher.find()) {
            errors.append("The office id can only have numbers\n");
        }
        if (office.toString().length() != OFFICE_NUMBER_LENGTH) {
            errors.append("The office id length must be " + OFFICE_NUMBER_LENGTH + " \n");
        }

        matcher = numberPattern.matcher(bank.toString());
        if (!matcher.find()) {
            errors.append("The bank id can only have numbers\n");
        }

        if (bank.toString().length() != BANK_NUMBER_LENGTH) {
            errors.append("The bank id length must be " + BANK_NUMBER_LENGTH + " \n");
        }

        if (errors.length() > 1) {
            throw new MalformedHandlerException(errors.toString());
        }
        this.officeHandler = office;
        this.bankHandler = bank;
        this.accountNumber = accountNumber;
        this.dc = calculateDC(office.toString(), bank.toString(), accountNumber + "");
    }

    /**
     * Calculate control digits of the account
     *
     * @param office ( office number in String format )
     * @param bank ( bank number in String format )
     * @param accountNumber ( accountNumber in String format)
     * @return ( control digits of the account )
     * @author runix
     */
    private static String calculateDC(String office, String bank, String accountNumber) {
        return String.valueOf(calculateDigit("00" + office.toString()) + String.valueOf(bank.toString()) + calculateDigit(accountNumber + ""));
    }

    /**
     * Calculate the control digit. The length of the string must be 10
     *
     * @param number ( The string with the numbers )
     *
     * @return ( the control digits )
     * @author runix
     */
    private static int calculateDigit(String number) {
        final int[] weights = new int[]{1, 2, 4, 8, 5, 10, 9, 7, 3, 6};
        final int length = number.length();
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += Integer.valueOf(number.charAt(i) + "") * weights[i];
        }
        int digit = 11 - sum % 11;
        if (digit == 11) {
            digit = 0;
        } else if (digit == 10) {
            digit = 1;
        }
        return digit;
    }

    @Override
    public int compareTo(Handler another) {
        return this.toString().compareTo(another.toString());
    }

    @Override
    public String toString() {
        return bankHandler.toString() + "-" + officeHandler.toString() + "-" + dc + "-" + accountNumber;
    }
}
