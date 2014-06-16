/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.handler;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Handler pattern.
 * 
 * @author runix
 */

@Entity
@Table(name = "GENERIC_HANDLER", catalog = "ULEBANK_FINAL")
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
public abstract class Handler implements Comparable<Handler>, Serializable {

    // Camino, don't kill us for do that. JPA doesn't support interface and
    // it's the only way

    /**
     * Serial version uid
     */
    private static final long serialVersionUID = 1L;
    /**
     * Id of the Handler
     */
    private String id;

    public Handler() {

    }

    /**
     * Compare the actual handler with another
     *
     * @param another
     *            ( Handler to compare )
     * @return (0 if are equals, != 0 otherwise )
     */
    public int compareTo(Handler another) {
        return this.toString().compareTo(another.toString());
    }

    @Id
    @Column(name = "id", unique = true, nullable = false, length = 64)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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