package com.corejsf;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

/**
 * Login Credentials.
 * @author blink & Kris Chan
 * @version 1.2
 */
@Dependent
public class Credentials implements Serializable {

    private static final long serialVersionUID = 11L;
    /** The login ID. */
    private String userName;
    /** The password. */
    private String password;
    
    private boolean admin;
    
    public Credentials() {
    	admin = false;
    }
    
    public Credentials(String userName, String pw, boolean admin) {
    	this.userName = userName;
    	this.password = pw;
    	this.admin = admin;
    }
    /**
     * userName getter.
     * @return the loginID
     */
    public String getUserName() {
        return userName;
    }
    /**
     * userName setter.
     * @param id the loginID to set
     */
    public void setUserName(final String id) {
        userName = id;
    }
    /**
     * password getter.
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * password setter.
     * @param pw the password to set
     */
    public void setPassword(final String pw) {
        password = pw;
    }
    
    public boolean isAdmin() {
    	return admin;
    }
    
    public void setAdmin(boolean admin) {
    	this.admin = admin;
    }

}

