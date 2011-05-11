package com.jaxio;

import java.util.ArrayList;
import java.util.List;

/**
 * this is my annotated xsd
 */
public class XSD {
	
	/** please define your complex object */
	private ComplexObject complexObject;
	/** you are allowed to set many simple objects */
	private List<SimpleObject> simpleObject = new ArrayList<SimpleObject>();

	
	public ComplexObject getComplexObject() {
		return complexObject;
	}
	public void setComplexObject(ComplexObject complexObject) {
		this.complexObject = complexObject;
	}
	public List<SimpleObject> getSimpleObject() {
		return simpleObject;
	}
	public void setSimpleObject(List<SimpleObject> simpleObject) {
		this.simpleObject = simpleObject;
	}
	public void addSimpleObject(SimpleObject simpleObject) {
		this.simpleObject.add(simpleObject);
	}
}