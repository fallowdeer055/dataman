package org.fallowdeer055.dataman.api.types;

import org.fallowdeer055.dataman.api.TypeDefinition;

public final class UndefinedType implements TypeDefinition {
	private static UndefinedType instance = null;

	public static UndefinedType getInstance() {
		if (instance == null) {
			instance = new UndefinedType();
		}
		return instance;
	}

	private UndefinedType() {
	}

}
