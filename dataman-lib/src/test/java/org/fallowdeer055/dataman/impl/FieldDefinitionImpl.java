package org.fallowdeer055.dataman.impl;

import org.fallowdeer055.dataman.api.FieldDefinition;
import org.fallowdeer055.dataman.api.TypeDefinition;

public class FieldDefinitionImpl implements FieldDefinition {

	private String fieldName;
	private TypeDefinition typeDefinition;

	public FieldDefinitionImpl(String fieldName, TypeDefinition typeDefinition) {
		super();
		this.fieldName = fieldName;
		this.typeDefinition = typeDefinition;
	}

	public void setTypeDefinition(TypeDefinition typeDefinition) {
		this.typeDefinition = typeDefinition;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public TypeDefinition getTypeDefinition() {
		return typeDefinition;
	}

	@Override
	public String getFieldName() {
		return fieldName;
	}

}
