package com.corejsf;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

@Dependent 
public class Employee implements Serializable {

    private static final long serialVersionUID = 11L;
    /** The employee's name. */
    private String name;
    /** The employee's employee number. */
    private int empNumber;
    /** The employee's login ID. */
    private String userName;
    /** If user is admin. */
    private boolean admin;
    /** If user can edit. */
    private boolean edit;


    /**
     * The no-argument constructor. Used to create new employees from within the
     * application.
     */
    public Employee() {
    	admin = false;
    }

    /**
     * The argument-containing constructor. Used to create the initial employees
     * who have access as well as the administrator.
     *
     * @param empName the name of the employee.
     * @param number the empNumber of the user.
     * @param id the loginID of the user.
     */
    public Employee(final String empName, final String id, final int number, final boolean admin) {
        name = empName;
        empNumber = number;
        userName = id;
        this.admin = admin;
    }
    /**
     * 
     * @param empName
     * @param number
     * @param id
     */
    public Employee(final String empName, final int number, final String id) {
        name = empName;
        empNumber = number;
        userName = id;
    }
    /**
     * Copy Constructor
     * @param emp used
     */
    public Employee(Employee emp) {
    	name = emp.name;
    	empNumber = emp.empNumber;
    	userName = emp.userName;
    }

    /**
     * name getter.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * name setter.
     * @param empName the name to set
     */
    public void setName(final String empName) {
        name = empName;
    }

    /**
     * empNumber getter.
     * @return the empNumber
     */
    public int getEmpNumber() {
        return empNumber;
    }

    /**
     * empNumber setter.
     * @param number the empNumber to set
     */
    public void setEmpNumber(final int number) {
        empNumber = number;
    }

    /**
     * userName getter.
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * userName setter.
     * @param id the userName to set
     */
    public void setUserName(final String id) {
        userName = id;
    }
    /**
     * 
     * @return admin
     */
    public boolean isAdmin() {
    	return admin;
    }
    /**
     * 
     * @param admin used
     */
    public void setAdmin(boolean admin) {
    	this.admin = admin;
    }
    /**
     * 
     * @return edit
     */
    public boolean isEditable() {
    	return edit;
    }
    /**
     * 
     * @param edit used
     */
    public void setEditable(boolean edit) {
    	this.edit = edit;
    }


}
