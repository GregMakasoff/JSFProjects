package com.corejsf;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;

/**
 * interface to back end to access Employees and verify login credentials.
 *
 * @author blink
 * @version 1.1
 *
 */
@Dependent
public interface EmployeeList extends Serializable {

    /**
     * employees getter.
     * @return the ArrayList of users.
     */
    List<Employee> getEmployees();

    /**
     * Returns employee with a given name.
     * @param name the name field of the employee
     * @return the employees.
     */
    Employee getEmployee(String name);

    /**
     * Return map of valid passwords for userNames.
     * @return the Map containing the valid (userName, password) combinations.
     */
    Map<String, String> getLoginCombos();

    /**
     * getter for currentEmployee property.  
     * @return the current user.
     */
    Employee getCurrentEmployee();

    /**
     * Assumes single administrator and returns the employee object
     * of that administrator.
     * @return the administrator user object.
     */
    Employee getAdministrator();

    /**
     * Verifies that the loginID and password is a valid combination.
     *
     * @param credential (userName, Password) pair
     * @return true if it is, false if it is not.
     */
    boolean verifyUser(Credentials credential);

    /**
     * Logs the user out of the system.
     *
     * @return a String representing the login page.
     */
    String logout();

    /**
     * Deletes the specified user from the collection of Users.
     *
     * @param userToDelete the user to delete.
     */
    String deleteEmployee(Employee userToDelete);

    /**
     * Adds a new Employee to the collection of Employees.
     * @param newEmployee the employee to add to the collection
     */
    void addEmployee(Employee newEmployee);
}
