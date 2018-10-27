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

import java.io.File;
import java.util.List;

import org.fallowdeer055.dataman.api.EntityList;
import org.fallowdeer055.dataman.api.EntityListFactory;
import org.fallowdeer055.dataman.impl.EntityListFactoryImpl;
import org.fallowdeer055.dataman.testmodel.simple.Employee;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SimpleEntityListTest {

	private static File fileWithJsonData;
	private static EntityListFactory entityListFactory;
	
	private static final String FILE_PATH = "testdata/simplest/employee.json";

	@BeforeClass
	public static void setup() {
		String path = SimpleEntityListTest.class.getClassLoader().getResource(FILE_PATH).getFile();
		fileWithJsonData = new File(path);
		entityListFactory = EntityListFactory.getInstance();
	}

	@Test
	public void preRequisites() {
		assertThat(fileWithJsonData, notNullValue());
		assertThat(fileWithJsonData.exists(), is(true));
	}

	@Test
	public void simple() {
		EntityList<Employee> employeeList = entityListFactory.createEntityList(fileWithJsonData, Employee.class);
		assertThat(employeeList, notNullValue());

		List<Employee> employees = employeeList.getEntities();

		assertThat(employees, not(empty()));
		assertThat(employees.get(0), instanceOf(Employee.class));
		assertThat(employees, hasItem(equalTo(new Employee("John", "Deer", 5))));
		assertThat(employees, hasSize(5));

	}

}
