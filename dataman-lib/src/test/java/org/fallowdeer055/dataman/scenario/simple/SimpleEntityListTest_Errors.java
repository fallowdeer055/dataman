package org.fallowdeer055.dataman.scenario.simple;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;

import org.fallowdeer055.dataman.api.EntityList;
import org.fallowdeer055.dataman.api.EntityListFactory;
import org.fallowdeer055.dataman.api.exception.MissingAttributeException;
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
		} catch ( MissingAttributeException mae) {
			
		}
		fail("missing or wrong exception");
	}
	
	@Test
	public void unknownAttributeReporting() {
		try {
		EntityListFactory.createEntityList(unknownAttrFile, Employee.class);
		} catch ( MissingAttributeException mae) {
			
		}
		fail("missing or wrong exception");
	}

}
