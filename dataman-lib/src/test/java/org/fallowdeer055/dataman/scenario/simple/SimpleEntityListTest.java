package org.fallowdeer055.dataman.scenario.simple;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.List;

import org.fallowdeer055.dataman.api.EntityList;
import org.fallowdeer055.dataman.api.EntityListFactory;
import org.fallowdeer055.dataman.testmodel.simple.Employee;
import org.junit.Before;
import org.junit.Test;

public class SimpleEntityListTest {

	private File fileWithJsonData;
	private static final String FILE_PATH = "testdata/simplest/employee.json";

	@Before
	public void setup() {
		String path = this.getClass().getClassLoader().getResource(FILE_PATH).getFile();
		fileWithJsonData = new File(path);
	}

	@Test
	public void preRequisites() {
		assertThat(fileWithJsonData, notNullValue());
		assertThat(fileWithJsonData.exists(), is(true));
	}

	@Test
	public void simple() {
		EntityList<Employee> employeeList = EntityListFactory.createEntityList(fileWithJsonData, Employee.class);
		assertThat(employeeList, notNullValue());

		List<Employee> employees = employeeList.getEntities();

		assertThat(employees, not(empty()));
		assertThat(employees, hasItem(new Employee("John", "Deer", 5)));
		assertThat (employees, hasSize(5));

	}

}
