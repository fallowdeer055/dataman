package org.fallowdeer055.dataman.api;

import java.io.File;

import org.fallowdeer055.dataman.impl.EntityListFactoryImpl;

public interface EntityListFactory {
	public <T> EntityList<T> createEntityList(File jsonFile, Class<T> clazz);

	public static EntityListFactory getInstance() {
		return EntityListFactoryImpl.getInstance();
	}
}
