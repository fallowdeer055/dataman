package org.fallowdeer055.dataman.scenario.simple;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;

import org.fallowdeer055.dataman.api.EntityListFactory;
import org.fallowdeer055.dataman.api.exception.MissingAttributeException;
import org.fallowdeer055.dataman.api.exception.UnexpectedAttributeException;
import org.fallowdeer055.dataman.testmodel.simple.Employee;
import org.junit.Before;
import org.junit.Test;

public class SimpleEntityListTest_Errors {

	private File missingAttrFile;
	private File unknownAttrFile;

	private static final String FILE_UNKNOWN_ATTR_PATH = "testdata/simplest/employee_unknown_attr.json";
	private static final String FILE_MISSING_ATTR_PATH = "testdata/simplest/employee_attr_missing.json";

	@Before
	public void setup() {
		missingAttrFile = new File(this.getClass().getClassLoader().getResource(FILE_MISSING_ATTR_PATH).getFile());
		unknownAttrFile = new File(this.getClass().getClassLoader().getResource(FILE_UNKNOWN_ATTR_PATH).getFile());

	}

	@Test
	public void preRequisites() {
		assertThat(missingAttrFile.exists(), is(true));
		assertThat(unknownAttrFile.exists(), is(true));
	}

	@Test
	public void missingAttributeReporting() {
		try {
			EntityListFactory.createEntityList(missingAttrFile, Employee.class);
			fail("missing or wrong exception");
		} catch (MissingAttributeException mae) {
			assertThat(mae.getAttributeName(), is("lastname"));
			assertThat(mae.getErrorLocation(), is("employee_attr_missing.json, line 20") );
			assertThat(mae.getMessage(), is("Missing attribute lastname in employee_attr_missing.json, line 20"));
		}

	}

	@Test
	public void unexpectedAttributeReporting() {
		try {
			EntityListFactory.createEntityList(unknownAttrFile, Employee.class);
			fail("missing or wrong exception");
		} catch (UnexpectedAttributeException uae) {
			assertThat(uae.getErrorLocation(), is("employee_unknown_attr.json, line 21"));
			assertThat(uae.getAttributeName(), is("unknownattribute"));
			assertThat(uae.getMessage(), is("Unexpected attribute unknownattribute in employee_unknown_attr.json, line 21"));
		}

	}

}
