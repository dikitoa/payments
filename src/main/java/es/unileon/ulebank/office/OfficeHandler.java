package es.unileon.ulebank.office;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.MalformedHandlerException;

/**
 * 
 * @author Patricia
 * 
 */
@Entity
@Table(name = "GENERIC_HANDLER", catalog = "ULEBANK_FINAL")
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "OfficeHandler")
public class OfficeHandler extends Handler {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;


    public OfficeHandler(int number) throws MalformedHandlerException {
        String id = "";
        if (number >= 0) {

            if (Integer.toString(number).length() == 4) {
                id = Integer.toString(number);
            } else {
                if (Integer.toString(number).length() < 4) {
                    id = Integer.toString(number);
                    while (id.length() <= 4) {
                        id = 0 + id;
                    }
                } else {
                    throw new MalformedHandlerException(
                            "The idOffice is malformed");
                }
            }
            this.setId(id);
        } else {
            throw new MalformedHandlerException(
                    "The idOffice has to be a positive number");
        }
    }

    public OfficeHandler(String numberOffice) throws MalformedHandlerException {
        try {
            Integer.parseInt(numberOffice);
        } catch (final NumberFormatException e) {
            throw new MalformedHandlerException(
                    "The idOffice has to be a number");
        }

        if (Integer.parseInt(numberOffice) >= 0) {
            if (numberOffice.length() == 4) {
                this.setId(numberOffice);
            } else {
                if (numberOffice.length() < 4) {
                    while (numberOffice.length() < 4) {
                        numberOffice = 0 + numberOffice;
                    }
                    this.setId(numberOffice);
                } else {
                    throw new MalformedHandlerException(
                            "The idOffice is malformed");
                }
            }
        } else {
            throw new MalformedHandlerException(
                    "The idOffice has to be a positive number");
        }
    }

    public OfficeHandler() {
    }
}
