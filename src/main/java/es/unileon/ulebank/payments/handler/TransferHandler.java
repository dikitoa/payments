package es.unileon.ulebank.payments.handler;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import es.unileon.ulebank.handler.Handler;

/**
 * Class of Transfer Handler
 * 
 * @author Rober dCR
 * @date 9/04/2014
 * @brief Identifier of Transfer
 */
@Entity
@Table(name = "GENERIC_HANDLER", catalog = "ULEBANK_FINAL")
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "TransferHandler")
public class TransferHandler extends Handler {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Class constructor
     * 
     * @param sender
     * @param receiver
     */
    public TransferHandler(String sender, String receiver) {
        Calendar calendar = new GregorianCalendar();
        String date = this.setDateCode(calendar);
        this.setId(sender.substring(sender.length() / 2)
                + receiver.substring(receiver.length() / 2) + date);
    }

    public TransferHandler() {
    }

    /**
     * Compares one handler with other
     * 
     * @return 0 if both are equals any other number if not equals
     */
    @Override
    public int compareTo(Handler another) {
        return this.toString().compareTo(another.toString());
    }

    /**
     * Method that obtains the code from the date
     */
    private String setDateCode(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH)
                + Integer.toString(calendar.get(Calendar.MONTH) + 1)
                + calendar.get(Calendar.YEAR)
                + calendar.get(Calendar.HOUR_OF_DAY)
                + calendar.get(Calendar.MINUTE)
                + calendar.get(Calendar.SECOND);
    }
}
