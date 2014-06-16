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
import es.unileon.ulebank.utils.CifControl;

/**
 * Identifier of enterprises
 * 
 * @author Gonzalo Nicolas Barreales
 */

// TODO modify cif with the corresponding data
@Entity
@Table(name = "GENERIC_HANDLER", catalog = "ULEBANK_FINAL")
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "EnterpriseHandler")
public class EnterpriseHandler extends Handler {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public EnterpriseHandler(){
        
    }

    /**
     * 
     * @param entityLetter
     * @param cifNumber
     * @param cifFinalDigit
     */
    public EnterpriseHandler(char entityLetter, int cifNumber,
            char cifFinalDigit) throws MalformedHandlerException {
        if ((Integer.toString(cifNumber).length() <= 7)
                && (Integer.toString(cifNumber).length() >= 6)) {
            int provinceCode = cifNumber / 100000;
            int registrationCode = cifNumber - (provinceCode * 100000);
            char controlCode = cifFinalDigit;
            if (!CifControl.instance().isCifValid(entityLetter, provinceCode,
                    registrationCode, controlCode)) {
                throw new MalformedHandlerException("Invalid control code");
            }
            this.setId(entityLetter + Integer.toString(provinceCode)
                    + Integer.toString(registrationCode) + controlCode);
        } else {
            throw new MalformedHandlerException("Invalid CIF");
        }
    }
    

    public EnterpriseHandler(String ticker) throws MalformedHandlerException {
        if(ticker.length() < 6){
            this.setId(ticker);
        }else{
            throw new MalformedHandlerException("Invalid ticker");
        }
    }
}
