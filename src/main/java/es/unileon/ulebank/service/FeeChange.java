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
	private int feeChange;

	/**
	 * 
	 * @return
	 */
	public int getFeeChange() {
		return feeChange;
	}

	/**
	 * 
	 * @param feeChange
	 */
	public void setFeeChange(int feeChange) {
		this.feeChange = feeChange;
	}

}