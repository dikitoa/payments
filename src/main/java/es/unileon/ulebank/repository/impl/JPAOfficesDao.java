package es.unileon.ulebank.repository.impl;

import java.util.List;

import es.unileon.ulebank.domain.Offices;
import es.unileon.ulebank.repository.OfficeDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "officeDao")
public class JPAOfficesDao implements OfficeDao {

    /**
     * EntityManager
     */
    private EntityManager entityManager = null;
    /**
     * Office
     */
    private Offices office;

    /**
     * Sets the entity manager
     * 
     * @param em
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.entityManager = em;
    }

    /**
     * Returns the list of offices in the database
     * 
     * @return list of offices
     */
    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Offices> getOfficeList() {
        return entityManager.createQuery(
                "select o from Offices o order by o.officeId").getResultList();
    }

    /**
     * Saves an office in the database
     * 
     * @param off
     */
    @Transactional(readOnly = false)
    public void saveOffice(Offices off) {
        entityManager.merge(off);
    }

    /**
     * Returns an office
     * 
     * @return office
     */
    public Offices getOffice() {
        return office;
    }

    /**
     * Sets an office
     * 
     * @param office
     */
    public void setOffice(Offices office) {
        this.office = office;
    }

    /**
     * Finds the office
     * 
     * @param id
     * @return office
     */
    @Transactional(readOnly = true)
    public Offices findOffice(String id) {

        return (Offices) entityManager
                .createQuery("select o from Offices o where o.officeId=" + id)
                .getResultList().get(0);
    }

    // /**
    // * Returns the list of accounts of an office
    // *
    // * @param officeID
    // * @return the list of accounts
    // */
    // @Transactional(readOnly = true)
    // @SuppressWarnings("unchecked")
    // public List<Account> getAccountListOfOffice(String officeID) {
    //
    // return entityManager.createQuery(
    // "select a from Account a where a.officeID=" + officeID)
    // .getResultList();
    // }
    //
    // /**
    // * Returns the list of employees of an office
    // *
    // * @param officeID
    // * @return the list of employees
    // */
    // @Transactional(readOnly = true)
    // @SuppressWarnings("unchecked")
    // public List<Employee> getEmployeeList(String officeID) {
    // return
    // entityManager.createQuery("select e from Employee e where e.officeID=" +
    // officeID).getResultList();
    // }
    //
    // /**
    // *
    // * @return the list of all employees in the database.
    // */
    // @Transactional(readOnly = true)
    // @SuppressWarnings("unchecked")
    // public List<Employee> getAllEmployees(){
    // return
    // entityManager.createQuery("select e from Employee e order by e.officeID").getResultList();
    // }
}
