package com.corejsf;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
/**
 * The connector class between my two interfaces and works as a functional database 
 * for our purposes.
 * @author Greg Makasoff & Kris Chan
 *	@version 1.0
 */
@Named("db")
@SessionScoped
public class Connector implements Serializable, EmployeeList, TimesheetCollection {
    /**
     * List of employees
     */
    private List<Credentials> credentials = new ArrayList<Credentials>();
    /**
     * List of employees
     */
    private List<Employee> employees = new ArrayList<Employee>();
    /**
     * List of timesheets
     */
    private List<Timesheet> timesheets = new ArrayList<Timesheet>();
    /**
     * The current credentials being viewed
     */
    @Inject
    private Credentials currentCredentials;
    /**
     * The current employee in the the system
     */
    @Inject
    private Employee currentEmployee;
    /**
     * The current timesheet being viewed
     */
    @Inject
    private Timesheet currentTimesheet;  
    /**
     * An employee object
     */
    @Inject
    private Employee newEmployee;
    /**
     * A password object
     */
    private String newPassword;
    private String newPassword2;
    /**
     * Index
     */
    private int newIndex;
    /**
     * Initializes admin user and password
     */
    @PostConstruct
    public void init() {
        employees.add(new Employee("Administrator", "admin", 0000, true));
        credentials.add(new Credentials("admin", "password", true));
    }
    /*
     * Does the user verification
     */
    public String login() {
        for (Credentials creds : credentials) {
            if (currentCredentials.getUserName().equals(creds.getUserName())
                    && currentCredentials.getPassword().equals(creds.getPassword())) {
                for (Employee emp : employees) {
                    if (emp.getUserName().equals(creds.getUserName())) {
                        currentEmployee = emp;
                        return "viewUser";
                    }
                }
            }
        }
        return "loginPageFail";
    }
    /**
     * Logs user out
     */
    @Override
    public String logout() {
        currentEmployee = new Employee();
        currentCredentials = new Credentials();
        return "loginPage";
    }  
    /**
     * 
     */
    @Override
    public boolean verifyUser(Credentials credential) {
        return false;
    }
    /**
     * Deletes employee from the database
     */
    @Override
    public String deleteEmployee(Employee userToDelete) {
        employees.remove(userToDelete);
        
        return null;
    }
    /**
     * Adds an employee to the database
     * @return a string that leads to the employee list
     */
    public String addEmployee() {
        employees.add(new Employee(newEmployee.getName(), newEmployee.getUserName(), newEmployee.getEmpNumber(), false));
        credentials.add(new Credentials(newEmployee.getUserName(), newPassword, false));
        newEmployee = new Employee();
        newPassword = "";
        return "employeeList";
    }
    /**
     * Adds an employee to the emplyee list
     */
    @Override
    public void addEmployee(Employee newEmployee) { 
    	employees.add(newEmployee); 
    	}
    /**
     * Getter by index
     * @param emp
     * @return index of employee
     */
    public int getEmployeeNum(Employee emp) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).equals(emp)) { 
            	return i; 
            }
        }
        return 0;
    }
    /**
     * Goes to the edit page with the employee passed in
     * @param emp
     * @return a string that goes to the edit user page
     */
    public String editEmployee(Employee emp) {
        newEmployee = emp;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).equals(emp)) {
                newIndex = i;
                newPassword = credentials.get(i).getPassword();
            }
        }
        return "editUser";
    }
    /**
     * Saves the user in the database
     * @return a string that returns to the view user page
     */
    public String saveEmployee() {
        employees.set(newIndex, newEmployee);
        Credentials temp = new Credentials();
        temp.setUserName(newEmployee.getUserName());
        temp.setPassword(newPassword);
        credentials.set(newIndex, temp);
        newEmployee = new Employee();
        newPassword = "";
        return "viewUser";
    }
    /**
     * Cancels current employee from being added
     * @return a string that returns to the view user page
     */
    public String cancelUserForm() {
        newEmployee = new Employee();
        newPassword = "";
        return "viewUser";
    }
    /**
     * Getter by employee
     * @param e
     * @return
     */
    public String getPasswordByUser(Employee emp) {
        for (Credentials cred : credentials) {
            if (cred.getUserName().equals(emp.getUserName())) { 
            	return cred.getPassword(); 
            }
        }
        return null;
    }
    /**
     * Saves the new password to the database or goes the error page
     * @returns a string to either the user page or the error page
     */
    public String savePass() {
        if (newPassword != null) {
            for (int i = 0; i < credentials.size(); i++) {
                if (credentials.get(i).getUserName().equals(currentEmployee.getUserName())) {
                    if (newPassword.equals(credentials.get(i).getPassword())) {
                        Credentials temp = new Credentials();
                        temp.setUserName(currentEmployee.getUserName());
                        temp.setPassword(newPassword2);
                        credentials.set(i, temp);
                        return "viewUser";
                    }
                }
            }
        }
        return "editPassFail";
    }
    /**
     * Ends the current password change
     * @return a string to the user page
     */
    public String cancelPasswordForm() {
        newPassword = "";
        return "viewUser";
    }
    /**
     * Adds a row the current timesheet
     * @return an empty string to stay at the current page
     */
    public String addTimesheetRow() {
        currentTimesheet.addRow();
        return "";
    }
    /**
     * Getter for time sheet list
     */
    @Override
    public List<Timesheet> getTimesheets() { 
    	return timesheets; 
    }
    /**
     * Getter for all the time sheet for a given employee
     */
    public List<Timesheet> getTimesheets(Employee emp) {
        List<Timesheet> sheets = new ArrayList<Timesheet>();
        for (Timesheet sheet : timesheets) {
            if (sheet.getEmployee().getEmpNumber() == currentEmployee.getEmpNumber()) { 
            	sheets.add(sheet); 
            }
        }
        return sheets;
    }
    /**
     * Returns list of employees
     */
    @Override
    public List<Employee> getEmployees() { 
    	return employees; 
    }
    /**
     * Returns a map of the login combos
     */
    @Override
    public Map<String, String> getLoginCombos() {
        return null;
    }
    /**
     * Getter by index
     * @param i used
     * @return password
     */
    public String getPasswordByIndex(int i) { 
    	return credentials.get(i).getPassword(); 
    }
    /**
     * Setter by index 
     * @param i used
     * @param pass used
     */
    public void setPasswordByIndex(int i, String pass) { 
    	credentials.get(i).setPassword(pass); 
    }
    /**
     * Returns employee from database
     */
    @Override
    public Employee getEmployee(String name) {
        for (Employee emp : employees) {
            if (emp.getUserName().equals(name))
                return emp;
        }
        return null;
    }
    /**
     * Adds a time sheet to an employee
     */
    public String addTimesheet() {
        Timesheet sheet = new Timesheet();
        sheet.setEmployee(currentEmployee);
        currentTimesheet = sheet;
        timesheets.add(currentTimesheet);
        return null;
    }
    /**
     * Deletes a time sheet when the button is pressed
     * @param sheet
     * @return
     */
    public String deleteTimesheet(Timesheet sheet) {
        timesheets.remove(sheet);
        return null;
    }
    /**
     * Views a chosen time sheet
     * @param sheet
     * @return
     */
    public String viewTimesheet(Timesheet sheet) {
        currentTimesheet = sheet;
        return "timesheet";
    }
    /**
     * Returns the current employee in the system
     */
    @Override
    public Employee getCurrentEmployee() { 
    	return currentEmployee; 
    }
    /**
     * Returns the name of the current employee
     * @return employee
     */
    public String getCurrentEmployeeName() { 
    	return currentEmployee.getName(); 
    }    /**
     * Returns the administrator of the system
     */
    @Override
    public Employee getAdministrator() {
        return null;
    }
    /**
     * Returns current time sheet
     */
    public Timesheet getCurrentTimesheet(Employee emp) { 
    	return currentTimesheet; 
    }
    /**
     * Getter
     * @return currentTimesheet
     */
    public Timesheet getCurrentTimesheet() { 
    	return currentTimesheet; 
    	}
    /**
     * Setter
     * @param currentTimsheet used
     */
    public void setCurrentTimesheet(Timesheet currentTimsheet) { 
    	this.currentTimesheet = currentTimsheet; 
    }
    /**
     * Getter
     * @return currentCCredentials 
     */
    public Credentials getCurrentCredentials() { 
    	return currentCredentials; 
    }
    /**
     * Setter
     * @param currentCredential used
     */
    public void setCurrentCredentials(Credentials currentCredential) { 
    	this.currentCredentials = currentCredential; 
    }
    /**
     * Getter
     * @return newEmployee
     */
    public Employee getEditableEmployee() { 
    	return newEmployee; 
    }
    /**
     * Setter
     * @param editableEmployee used
     */
    public void setEditableEmployee(Employee editableEmployee) { 
    	this.newEmployee = editableEmployee; 
    }
    /**
     * Getter
     * @return editablePassword
     */
    public String getEditablePassword() { 
    	return newPassword; 
    }
    /**
     * Setter
     * @param editablePassword
     */
    public void setEditablePassword(String editablePassword) { 
    	this.newPassword = editablePassword; 
    }
    /**
     * Getter
     * @return
     */
    public String getEditablePassword2() { 
    	return newPassword2; 
    }
    /**
     * Getter
     * @param editablePassword2
     */
    public void setEditablePassword2(String editablePassword2) { 
    	this.newPassword2 = editablePassword2; 
    }
    public BigDecimal getTotalSat() { 
    	return currentTimesheet.getDailyHours()[0]; 
    }
    public BigDecimal getTotalSun() { 
    	return currentTimesheet.getDailyHours()[1]; 
   	}
    public BigDecimal getTotalMon() { 
    	return currentTimesheet.getDailyHours()[2]; 
    }
    public BigDecimal getTotalTue() { 
    	return currentTimesheet.getDailyHours()[3]; 
    }
    public BigDecimal getTotalWed() { 
    	return currentTimesheet.getDailyHours()[4]; 
    }
    public BigDecimal getTotalThu() { 
    	return currentTimesheet.getDailyHours()[5]; 
    }
    public BigDecimal getTotalFri() { 
    	return currentTimesheet.getDailyHours()[6]; 
    }
}

