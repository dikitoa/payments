/* Application developed for AW subject, belonging to passive operations
 group.*/

package es.unileon.ulebank.client;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.MalformedHandlerException;
import es.unileon.ulebank.utils.DniLetters;

/**
 * Identifies a person with the dni
 * 
 * @author Gonzalo Nicolas Barreales
 */
@Entity
@Table(name = "GENERIC_HANDLER", catalog = "ULEBANK_FINAL")
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "PersonHandler")
public class PersonHandler extends Handler {


    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates the handler of the person with the dni data
     * 
     * @param dni
     * @param letter
     * @throws MalformedHandlerException
     *             if the letter doesn't match with the dni number
     */
    public PersonHandler(int dni, char letter) throws MalformedHandlerException {
        if (DniLetters.getInstance().isDniValid(dni,
                Character.toUpperCase(letter))) {
            this.setId(Integer.toString(dni) + letter);
        } else {
            throw new MalformedHandlerException("Incorrect DNI");
        }
    }
    
    public PersonHandler() {
    }

    /**
     *
     * @param foreingLetterRaw
     * @param dni
     * @param letter
     * @throws MalformedHandlerException
     */
    public PersonHandler(char foreingLetter, int dni, char letter)
            throws MalformedHandlerException {
        final char foreingLetterRaw = Character.toUpperCase(foreingLetter);
        final char letterRaw = Character.toUpperCase(letter);
        int addFactor = 0;
        String result = "";
        switch (foreingLetterRaw) {
            case 'X':
                break;
            case 'Y':
                addFactor = 10000000;
                break;
            case 'Z':
                addFactor = 20000000;
                break;
            default:
                throw new MalformedHandlerException("Incorrect NIE");
        }
        if (DniLetters.getInstance().isDniValid(addFactor + dni, letterRaw)) {
            // this.foreingLetter = foreingLetterRaw;
            // this.dni = dni;
            // this.letter = letterRaw;
            if (foreingLetterRaw != ' ') {
                result += foreingLetterRaw;
            }
            result += Integer.toString(dni) + letter;
            this.setId(result);
        } else {
            throw new MalformedHandlerException("Invalid NIE");
        }
    }
}
