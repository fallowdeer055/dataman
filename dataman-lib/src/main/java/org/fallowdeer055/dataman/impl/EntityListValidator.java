package org.fallowdeer055.dataman.impl;

import java.io.File;

public class EntityListValidator {

	private EntityListValidator instance = null;

	private EntityListValidator() {
	}

	public EntityListValidator getInstance() {
		if (instance == null) {
			instance = new EntityListValidator();
		}
		return instance;
	}

	public <T> void validateEntityListJsonByJavaType(File jsonFile, Class<T> entityClass) {

	}
}
