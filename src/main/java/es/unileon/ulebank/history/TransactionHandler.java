package es.unileon.ulebank.history;

import es.unileon.ulebank.handler.Handler;

/**
 *
 * @author roobre
 */
public class TransactionHandler implements Handler {

    private final long id;
    private final String timestamp;

    /**
     *
     * @param id
     * @param timestamp
     */
    public TransactionHandler(long id, String timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return this.timestamp + "." + Long.toString(this.id);
    }

    @Override
    public int compareTo(Handler another) {
        return this.toString().compareTo(another.toString());
    }

	/**
	 * Compare two identifiers and determine if are equals or not
	 * 
	 * @param another
	 * @return true if are equals
	 * @return false if aren't equals
	 */
	@Override
	public boolean equals(Object another) {
		if (another == null) {
			return false;
		}
		
		if (another.getClass() != getClass()) {
			return false;
		}
		
		Handler other = (Handler) another;
		
		if (this.toString().equals(other.toString())) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.hashCode();
	}

}