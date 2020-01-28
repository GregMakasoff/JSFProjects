package com.corejsf;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Dependent
@Entity
@Table(name = "suppliers")
public class Supplier implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="SupplierID")
	public int supID;
	
	@Column(name="SupplierName")
	public String supName;
	
	@Column(name="ContactName")
	public String contName;
	
	@Column(name="ContactTitle")
	public String contTitle;
	
	@Column(name="Address")
	public String address;
	
	@Column(name="City")
	public String city;
	
	@Column(name="PostalCode")
	public String postCode;
	
	@Column(name="StateOrProvince")
	public String stateOrProv;
	
	@Column(name="Country")
	public String country;
	
	@Column(name="PhoneNumber")
	public String phoneNum;
	
	@Column(name="FaxNumber")
	public String faxNum;
	
	@Column(name="PaymentTerms")
	public String payTerms;
	
	@Column(name="EmailAddress")
	public String email;
	
	@Column(name="Notes")
	public String notes;

	public int getSupID() {
		return supID;
	}

	public void setSupID(int supID) {
		this.supID = supID;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	public String getContName() {
		return contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public String getContTitle() {
		return contTitle;
	}

	public void setContTitle(String contTitle) {
		this.contTitle = contTitle;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getStateOrProv() {
		return stateOrProv;
	}

	public void setStateOrProv(String stateOrProv) {
		this.stateOrProv = stateOrProv;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getFaxNum() {
		return faxNum;
	}

	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}

	public String getPayTerms() {
		return payTerms;
	}

	public void setPayTerms(String payTerms) {
		this.payTerms = payTerms;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
