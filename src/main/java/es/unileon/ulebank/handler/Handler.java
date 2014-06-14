/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.handler;

/**
 * Handler pattern.
 * 
 * @author runix
 */
public interface Handler extends Comparable<Handler> {

    /**
     * Compare the actual handler with another
     *
     * @param another
     *            ( Handler to compare )
     * @return (0 if are equals, != 0 otherwise )
     */
    @Override
    public int compareTo(Handler another);
    
    /**
     * Compare two handler and determine if they are equals or not
     * @param another
     * @return (true if are equals, false if aren't equals)
     */
    boolean equals(Handler another);

    /**
     *
     * @return
     */
    @Override
    public String toString();
    
    @Override
    public int hashCode();
	
}
