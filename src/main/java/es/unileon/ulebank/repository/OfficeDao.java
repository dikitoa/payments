package es.unileon.ulebank.repository;

import java.util.List;

import es.unileon.ulebank.domain.Accounts;
import es.unileon.ulebank.domain.Employee;
import es.unileon.ulebank.domain.Offices;

public interface OfficeDao {
    /**
     * Returns the office list
     * 
     * @return the office list
     */
    public List<Offices> getOfficeList();

    /**
     * Saves an office in the database
     * 
     * @param off
     */
    // public void saveOffice(Office off);

    /**
     * Sets an office
     * 
     * @param office
     */
    public void setOffice(Offices office);

    /**
     * Returns an office
     * 
     * @return office
     */
    public Offices getOffice();

    /**
     * Finds an office
     * 
     * @param id
     * @return
     */
    public Offices findOffice(String id);

//    /**
//     * Returns the list of accounts of an office
//     * 
//     * @param officeID
//     * @return the list of accounts
//     */
//    public List<Accounts> getAccountListOfOffice(String officeID);
//
//    /**
//     * Returns the employee list
//     * 
//     * @return the employee list
//     */
//    public List<Employee> getEmployeeList(String officeID);
//
//    /**
//     * 
//     * @return a list with all employees in the database.
//     */
//    public List<Employee> getAllEmployees();
}
