package org.fallowdeer055.dataman.impl.types;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.fallowdeer055.dataman.api.FieldDefinition;
import org.fallowdeer055.dataman.api.TypeDefinition;
import org.fallowdeer055.dataman.api.types.StructureType;
import org.fallowdeer055.dataman.impl.FieldDefinitionImpl;

public class StructureTypeImpl implements StructureType {

	private Map<String, TypeDefinition> fields = new LinkedHashMap<>();

	public StructureTypeImpl() {
	}

	public void addField(String name, TypeDefinition typedef) {
		fields.put(name, typedef);
	}

	@Override
	public List<FieldDefinition> getFieldDefinitions() {
		return fields.entrySet().stream().map(entry -> new FieldDefinitionImpl(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
	}

}
