package org.fallowdeer055.dataman.api;

import java.util.Arrays;

import org.fallowdeer055.dataman.api.types.StructureType;
import org.fallowdeer055.dataman.api.types.UndefinedType;
import org.fallowdeer055.dataman.impl.types.StructureTypeImpl;

public class TypeDefinitionFactory {
	private static TypeDefinitionFactory instance = null;

	private TypeDefinitionFactory() {
	}

	public static TypeDefinitionFactory getInstance() {
		if (instance == null) {
			instance = new TypeDefinitionFactory();
		}
		return instance;
	}

	public <T> StructureType fromJavaType(Class<T> clazz) {
		final StructureTypeImpl typeDef = new StructureTypeImpl();
		Arrays.stream(clazz.getDeclaredFields()).forEachOrdered(field -> {
				typeDef.addField(field.getName(), UndefinedType.getInstance());
		});
		return typeDef;
	}

}
