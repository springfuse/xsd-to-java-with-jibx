package com.jaxio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.jibx.runtime.JiBXException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.oxm.MarshallingFailureException;
import org.springframework.oxm.UnmarshallingFailureException;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jibx.JibxMarshaller;

public class XSDTest {

	private static final int COUNT_SIMPLE_OBJECT = 5;
	private JibxMarshaller marshaller;

	@Before
	public void setup() throws JiBXException {
		marshaller = new JibxMarshaller();
		marshaller.setTargetClass(XSD.class);
		marshaller.afterPropertiesSet();
	}

	@Test
	public void writeNormalObject() throws XmlMappingException, IOException {
		XSD xsd = buildXSDObject();

		StringWriter writer = new StringWriter();
		marshaller.marshal(xsd, new StreamResult(writer));
		System.out.println(writer.toString());
	}

	@Test(expected = MarshallingFailureException.class)
	public void writingObjectWithRequiredFieldNotSetThrowsException() throws XmlMappingException, IOException {
		XSD xsd = buildXSDObject();

		// reset required field
		xsd.getComplexObject().setStringAttribute(null);

		StringWriter writer = new StringWriter();
		marshaller.marshal(xsd, new StreamResult(writer));
		System.out.println(writer.toString());
	}

	@Test
	public void loadValidFile() throws XmlMappingException, IOException {
		XSD xsd = getXsdFromXml("src/test/resource/valid.xml");
		assertNotNull(xsd);
		assertEquals(MyEnum.A, xsd.getComplexObject().getMyEnum());
		assertEquals("expected", xsd.getComplexObject().getStringAttribute());
		assertEquals(5, xsd.getSimpleObject().size());
	}

	@Test(expected = UnmarshallingFailureException.class)
	public void missingRequiredAttributeThrowsException() throws XmlMappingException, IOException {
		getXsdFromXml("src/test/resource/missing-mandatory-field.xml");
	}

	private XSD getXsdFromXml(String filename) throws FileNotFoundException, IOException {
		FileInputStream is = null;
		try {
			is = new FileInputStream(filename);
			return (XSD) marshaller.unmarshal(new StreamSource(is));
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	private XSD buildXSDObject() {
		XSD xsd = new XSD();
		xsd.setComplexObject(buildComplexObject());
		for (int i = 0; i < COUNT_SIMPLE_OBJECT; i++) {
			xsd.addSimpleObject(buildSimpleObject(i));
		}
		return xsd;
	}

	private ComplexObject buildComplexObject() {
		ComplexObject complex = new ComplexObject();
		complex.setStringAttribute("my string attribute");
		complex.setDateAttribute(new Date());
		complex.setMyEnum(MyEnum.B);
		complex.setThisIsABool(false);
		return complex;
	}

	private SimpleObject buildSimpleObject(int i) {
		SimpleObject simpleObject = new SimpleObject();
		simpleObject.setSimpleNumeric(i);
		simpleObject.setSimpleString("string " + i);
		return simpleObject;
	}
}
