/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.handler;

/**
 *
 * @author runix
 *
 */
public class GenericHandler implements Handler {

    /**
     * Generic id
     */
    private final String id;

    /**
     * Create a new Generic Handler
     *
     * @param id
     *            (The id)
     * @author runix
     */
    public GenericHandler(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Handler another) {
        return this.id.compareTo(another.toString());
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
		return 1 * 17 + id.hashCode();
	}

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return this.id;
    }

}
