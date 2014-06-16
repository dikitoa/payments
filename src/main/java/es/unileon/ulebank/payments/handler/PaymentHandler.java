package es.unileon.ulebank.payments.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.MalformedHandlerException;

/**
 * Class of Payment Handler
 * 
 * @author Rober dCR
 * @date 9/04/2014
 * @brief Identifier of Payment formed by the 4 last numbers of Card Handler,
 *        and 11 numbers of the month, year and complete hour.
 */
@Entity
@Table(name = "GENERIC_HANDLER", catalog = "ULEBANK_FINAL")
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "PaymentHandler")
public class PaymentHandler extends Handler {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Length of the command identifier
     */
    private static final int LENGTH = 15;
    /**
     * Posicion del numero de tarjeta a partir de la cual obtenemos la subcadena
     */
    private static final int POSITION_CARD = 15;
    /**
     * Longitud de los numeros obtenidos del handler de la tarjeta
     */
    private static final int NUMBER_INITIALS = 4;

    /**
     * Class constructor
     * 
     * @param handler
     * @throws MalformedHandlerException
     * @throws PaymentHandlerException
     */
    public PaymentHandler(Handler handler, Date date)
            throws MalformedHandlerException {
        this.setId(this.obtainInitials(handler) + this.obtainFinals(date));
        if (this.getId().length() != PaymentHandler.LENGTH) {
            throw new MalformedHandlerException(
                    "Length of payment handler incorrect.");
        }
    }

    public PaymentHandler() {
    }


    /**
     * @brief Method that obtains the first 4 numbers of the handler
     * @param cardNumber
     * @return 4 initials
     * @throws MalformedHandlerException
     */
    private String obtainInitials(Handler cardNumber)
            throws MalformedHandlerException {
        if (cardNumber.toString().substring(PaymentHandler.POSITION_CARD)
                .length() == PaymentHandler.NUMBER_INITIALS) {
            return cardNumber.toString()
                    .substring(PaymentHandler.POSITION_CARD);
        } else {
            throw new MalformedHandlerException(
                    "Longitud de los numeros iniciales incorrecta");
        }
    }

    /**
     * Method that obtains the 11 final numbers
     * 
     * @param date
     * @return
     */
    private String obtainFinals(Date date) {
        final SimpleDateFormat format = new SimpleDateFormat("MMyyHHmmSS");
        if (format.format(date).length() < 11) {
            return "0" + format.format(date);
        } else {
            return format.format(date);
        }
    }
}