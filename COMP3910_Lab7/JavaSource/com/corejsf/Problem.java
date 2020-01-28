package com.corejsf;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

@Dependent
public class Problem implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String title;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}
