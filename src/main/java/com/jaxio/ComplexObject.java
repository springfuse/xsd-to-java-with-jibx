package com.jaxio;

import java.util.Date;

public class ComplexObject {
	/**
	 * only string are allowed
	 */
	private String stringAttribute;
	/**
	 * This should be a date
	 */
	private Date dateAttribute;
	/**
	 * boolean are so over
	 */
	private boolean thisIsABool = false;
	/**
	 * enums are much better than booleans
	 */
	private MyEnum myEnum;

	public String getStringAttribute() {
		return stringAttribute;
	}

	public void setStringAttribute(String stringAttribute) {
		this.stringAttribute = stringAttribute;
	}

	public Date getDateAttribute() {
		return dateAttribute;
	}

	public void setDateAttribute(Date dateAttribute) {
		this.dateAttribute = dateAttribute;
	}

	public boolean isThisIsABool() {
		return thisIsABool;
	}

	public void setThisIsABool(boolean thisIsABool) {
		this.thisIsABool = thisIsABool;
	}

	public MyEnum getMyEnum() {
		return myEnum;
	}

	public void setMyEnum(MyEnum myEnum) {
		this.myEnum = myEnum;
	}
}
