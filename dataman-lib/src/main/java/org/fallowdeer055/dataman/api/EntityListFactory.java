package org.fallowdeer055.dataman.api;

import java.io.File;
import java.util.List;

import org.fallowdeer055.dataman.api.exception.UnexpectedAttributeException;
import org.fallowdeer055.dataman.impl.EntityListImpl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

public class EntityListFactory {

	public static <T> EntityList<T> createEntityList(File jsonFile, Class<T> clazz) {
		EntityListImpl<T> result = new EntityListImpl<T>();

		
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		

		try {
			JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
			
			List<T> l = mapper.readValue(jsonFile, listType);
			l.forEach(result::add);
		} catch (UnrecognizedPropertyException upe) {
			int lineNumber = upe.getLocation().getLineNr();
			String fileName = jsonFile.getName();
			String errorLocation = fileName+", line " + lineNumber;
			throw new UnexpectedAttributeException(upe.getPropertyName(), errorLocation);
		} catch (Exception e){
			throw new RuntimeException(e);
		}

		return result;
	}
}
