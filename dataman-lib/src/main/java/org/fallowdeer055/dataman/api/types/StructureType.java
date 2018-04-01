package org.fallowdeer055.dataman.api.types;

import java.util.List;

import org.fallowdeer055.dataman.api.FieldDefinition;
import org.fallowdeer055.dataman.api.TypeDefinition;

public interface StructureType extends TypeDefinition{
	public List<FieldDefinition> getFieldDefinitions();
}
