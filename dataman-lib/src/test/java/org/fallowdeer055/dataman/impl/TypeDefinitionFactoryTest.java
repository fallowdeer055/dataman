package org.fallowdeer055.dataman.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.fallowdeer055.dataman.api.TypeDefinitionFactory;
import org.fallowdeer055.dataman.api.types.StructureType;
import org.junit.Test;

public class TypeDefinitionFactoryTest {

	private TypeDefinitionFactory factory = TypeDefinitionFactory.getInstance();

	@Test
	public void checkSingleton() {
		assertNotNull(factory);
	}

	@Test
	public void test() {

		StructureType fromJavaType = factory.fromJavaType(TestClass.class);

		assertTrue(fromJavaType.getFieldDefinitions().size() == 3);

	}

}

class TestClass {
	private int a;
	private String b;
	private Object c;
}
