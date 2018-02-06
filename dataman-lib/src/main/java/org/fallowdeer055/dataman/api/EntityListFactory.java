package org.fallowdeer055.dataman.api;

import java.io.File;
import java.util.List;

import org.fallowdeer055.dataman.impl.EntityListImpl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EntityListFactory {

	public static <T> EntityList<T> createEntityList(File jsonFile, Class<T> clazz) {
		EntityListImpl<T> result = new EntityListImpl<T>();

		ObjectMapper mapper = new ObjectMapper();

		try {
			JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);

			List<T> l = mapper.readValue(jsonFile, listType);
			l.forEach(result::add);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return result;
	}
}
