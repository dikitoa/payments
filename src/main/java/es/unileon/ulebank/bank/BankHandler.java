/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.bank;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.MalformedHandlerException;

/**
 *
 * @author runix
 */
@Entity
@Table(name = "GENERIC_HANDLER", catalog = "ULEBANK_FINAL")
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "BankHandler")
public class BankHandler extends Handler {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * The number of digits
     */
    private static final int BANK_NUMBER_DIGITS = 4;
    
    public BankHandler(){
        
    }

    /**
     * Create a new Bank handler
     *
     * @param number
     *            ( The number )
     * @throws MalformedHandlerException
     *             (If the bank isn't correct )
     */
    public BankHandler(String number) throws MalformedHandlerException {
        final Pattern numberPattern = Pattern.compile("^[0-9]*$");
        final Matcher matcher = numberPattern.matcher(number);
        if (matcher.find()
                && (number.length() == BankHandler.BANK_NUMBER_DIGITS)) {
            this.setId(number);
        } else {
            final String error = "Error, the number hasn't "
                    + BankHandler.BANK_NUMBER_DIGITS
                    + " digits or has letters \n";
            throw new MalformedHandlerException(error);
        }
    }
}
