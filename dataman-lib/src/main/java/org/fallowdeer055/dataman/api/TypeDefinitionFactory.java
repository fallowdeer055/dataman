package org.fallowdeer055.dataman.api;

import org.fallowdeer055.dataman.api.types.StructureType;
import org.fallowdeer055.dataman.impl.TypeDefinitionFactoryImpl;

public interface TypeDefinitionFactory {

	public static TypeDefinitionFactory getInstance() {
		return TypeDefinitionFactoryImpl.getInstance();
	}

	public <T> StructureType fromJavaType(Class<T> clazz);

}
