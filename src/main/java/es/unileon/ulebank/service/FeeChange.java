package es.unileon.ulebank.service;

import javax.validation.constraints.Min;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author David Gomez Riesgo
 *
 */
public class FeeChange {
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Min(0)
	private int fees;

	/**
	 * 
	 * @return
	 */
	public int getFees() {
		return fees;
	}

	/**
	 * 
	 * @param feeChange
	 */
	public void setFees(int fees) {
		this.fees = fees;
	}

}
