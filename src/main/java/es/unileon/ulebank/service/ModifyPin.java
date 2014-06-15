package es.unileon.ulebank.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ModifyPin {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    private String newPin;

    public void setNewPin(String nPin) {
        newPin = nPin;
        logger.info("Pin establecido a " + nPin);
    }

    public String getNewPin() {
        return newPin;
    }
}