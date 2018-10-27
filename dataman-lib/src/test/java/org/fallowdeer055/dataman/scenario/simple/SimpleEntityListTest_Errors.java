package org.fallowdeer055.dataman.scenario.simple;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;

import org.fallowdeer055.dataman.api.EntityListFactory;
import org.fallowdeer055.dataman.api.exception.MissingAttributeException;
import org.fallowdeer055.dataman.api.exception.UnexpectedAttributeException;
import org.fallowdeer055.dataman.testmodel.simple.Employee;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SimpleEntityListTest_Errors {

	private static final String FILE_UNKNOWN_ATTR_PATH = "testdata/simplest/employee_unknown_attr.json";
	private static final String FILE_MISSING_ATTR_PATH = "testdata/simplest/employee_attr_missing.json";

	private static EntityListFactory entityListFactory;
	private static File missingAttrFile;
	private static File unknownAttrFile;

	@BeforeClass
	public static void setup() {

		missingAttrFile = new File(
				SimpleEntityListTest_Errors.class.getClassLoader().getResource(FILE_MISSING_ATTR_PATH).getFile());
		unknownAttrFile = new File(
				SimpleEntityListTest_Errors.class.getClassLoader().getResource(FILE_UNKNOWN_ATTR_PATH).getFile());
		entityListFactory = EntityListFactory.getInstance();
	}

	@Test
	public void preRequisites() {
		assertThat(missingAttrFile.exists(), is(true));
		assertThat(unknownAttrFile.exists(), is(true));
	}

	@Test
	public void missingAttributeReporting() {
		try {
			entityListFactory.createEntityList(missingAttrFile, Employee.class);
			fail("missing or wrong exception");
		} catch (MissingAttributeException mae) {
			assertThat(mae.getAttributeName(), is("lastname"));
			assertThat(mae.getErrorLocation(), is("employee_attr_missing.json, line 20"));
			assertThat(mae.getMessage(), is("Missing attribute lastname in employee_attr_missing.json, line 20"));
		}

	}

	@Test
	public void unexpectedAttributeReporting() {
		try {
			entityListFactory.createEntityList(unknownAttrFile, Employee.class);
			fail("missing or wrong exception");
		} catch (UnexpectedAttributeException uae) {
			assertThat(uae.getErrorLocation(), is("employee_unknown_attr.json, line 21"));
			assertThat(uae.getAttributeName(), is("unknownattribute"));
			assertThat(uae.getMessage(),
					is("Unexpected attribute unknownattribute in employee_unknown_attr.json, line 21"));
		}

	}

}
