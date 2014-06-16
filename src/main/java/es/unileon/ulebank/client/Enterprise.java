/* Application developed for AW subject, belonging to passive operations
 group.*/

package es.unileon.ulebank.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.MalformedHandlerException;

/**
 * Class that implements the data of an enterprise
 * 
 * @author Gonzalo Nicol��s Barreales
 */
@Entity
@Table(name = "CLIENTS", catalog = "ULEBANK_FINAL")
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "Enterprise")
public class Enterprise extends Client {

    /**
     * Serial version uid
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private List<Person> authorizedPersons;

    /**
     * 
     */
    private String enterpriseName;

    /**
     * 
     */
    private String address;

    /**
     * 
     * @param cifLetter
     * @param cifNumber
     */
    public Enterprise(char cifLetter, int cifNumber, char cifControl)
            throws MalformedHandlerException {
        super(new EnterpriseHandler(cifLetter, cifNumber, cifControl));
        this.authorizedPersons = new ArrayList<Person>();
    }

    /**
     * 
     * @param cifLetter
     * @param cifNumber
     * @param enterpriseName
     * @param address
     */
    public Enterprise(char cifLetter, int cifNumber, char cifControl,
            String enterpriseName, String address)
            throws MalformedHandlerException {
        super(new EnterpriseHandler(cifLetter, cifNumber, cifControl));
        this.authorizedPersons = new ArrayList<Person>();
        this.enterpriseName = enterpriseName;
        this.address = address;
    }

    public Enterprise() {

    }

    /**
     * 
     * @param person
     */
    public void addAuthorizedPerson(Person person) {
        if (!this.existsAuthorizedPerson(person.getGenericHandler())) {
            this.authorizedPersons.add(person);
        }
    }

    /**
     * 
     * @param personHandler
     * @return
     */
    public Person removeAuthorizedPerson(Handler personHandler) {
        Person removed = null;
        final Iterator<Person> iterator = this.authorizedPersons.iterator();
        while ((removed == null) && iterator.hasNext()) {
            final Person person = iterator.next();
            if (person.getGenericHandler().compareTo(personHandler) == 0) {
                removed = person;
                iterator.remove();
            }
        }

        return removed;
    }

    /**
     * 
     * @param personHandler
     * @return
     */
    public boolean existsAuthorizedPerson(Handler personHandler) {
        boolean result = false;
        final Iterator<Person> iterator = this.authorizedPersons.iterator();
        while (iterator.hasNext()) {
            final Person person = iterator.next();
            if (person.getGenericHandler().compareTo(personHandler) == 0) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 
     * @return
     */
    @Column(name = "enterprise_name", length = 32)
    public String getEnterpriseName() {
        return this.enterpriseName;
    }

    /**
     * 
     * @param enterpriseName
     */
    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    /**
     * 
     * @return
     */
    @Column(name = "address", nullable = false, length = 256)
    public String getAddress() {
        return this.address;
    }
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "ACCOUNTS_CLIENTS", catalog = "ULEBANK_FINAL", joinColumns = { @JoinColumn(name = "account_number", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "id", nullable = false, updatable = false) })
//    public List<Person> getAuthorizedPersons() {
//        return authorizedPersons;
//    }
//
//    public void setAuthorizedPersons(List<Person> authorizedPersons) {
//        this.authorizedPersons = authorizedPersons;
//    }

    /**
     * 
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
