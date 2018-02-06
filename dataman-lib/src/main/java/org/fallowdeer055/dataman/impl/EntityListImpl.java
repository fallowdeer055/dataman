package org.fallowdeer055.dataman.impl;

import java.util.ArrayList;
import java.util.List;

import org.fallowdeer055.dataman.api.EntityList;

public class EntityListImpl<T> implements EntityList<T> {

	List<T> entities = new ArrayList<T>();

	public void add(T entity) {
		entities.add(entity);
	}

	@Override
	public List<T> getEntities() {
		// defensive clone
		return new ArrayList<T>(entities);
	}

}
