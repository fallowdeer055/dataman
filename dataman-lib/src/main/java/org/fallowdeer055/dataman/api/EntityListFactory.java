package org.fallowdeer055.dataman.api;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.fallowdeer055.dataman.api.exception.MissingAttributeException;
import org.fallowdeer055.dataman.api.exception.UnexpectedAttributeException;
import org.fallowdeer055.dataman.api.types.StructureType;
import org.fallowdeer055.dataman.impl.EntityListImpl;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class EntityListFactory {

	public static <T> EntityList<T> createEntityList(File jsonFile, Class<T> clazz) {
		EntityListImpl<T> result = new EntityListImpl<T>();

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true);
		mapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, true);
		mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);

		try {
			JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);

			List<T> l = mapper.readValue(jsonFile, listType);
			l.forEach(result::add);
		} catch (UnrecognizedPropertyException upe) {
			String errorLocation = describeLocation(upe.getLocation(), jsonFile);
			throw new UnexpectedAttributeException(upe.getPropertyName(), errorLocation);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		verifyFileContainsAllAttributes(jsonFile, clazz);

		return result;
	}

	private static <T> void verifyFileContainsAllAttributes(File jsonFile, Class<T> clazz) {

		StructureType type = TypeDefinitionFactory.getInstance().fromJavaType(clazz);

		SimpleModule module = new SimpleModule();

		class DeserializerThatChecksPresenceOfAllAttributes<T> extends JsonDeserializer<T> {

			String missingAttributeName = null;
			JsonLocation elementLocation = null;

			public String getMissingAttributeName() {
				return missingAttributeName;
			}

			public JsonLocation getElementLocation() {
				return elementLocation;
			}

			@Override
			public T deserialize(JsonParser parser, DeserializationContext context)
					throws IOException, JsonProcessingException {

				if (missingAttributeName != null) {
					return null;
				}

				TreeNode node = parser.getCodec().readTree(parser);

				List<String> attributesToFind = type.getFieldDefinitions().stream().map(FieldDefinition::getFieldName)
						.collect(Collectors.toList());
				node.fieldNames().forEachRemaining(name -> {
					attributesToFind.remove(name);
				});

				if (!attributesToFind.isEmpty()) {
					missingAttributeName = attributesToFind.get(0);
					elementLocation = parser.getCurrentLocation();
				}
				return null;
			}

		}

		DeserializerThatChecksPresenceOfAllAttributes<T> deserializer = new DeserializerThatChecksPresenceOfAllAttributes<>();

		module.addDeserializer(clazz, deserializer);

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(module);

		JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);

		try {
			mapper.readValue(jsonFile, listType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		if (deserializer.getMissingAttributeName() != null) {
			throw new MissingAttributeException(deserializer.getMissingAttributeName(),
					describeLocation(deserializer.getElementLocation(), jsonFile));
		}
	}

	private static String describeLocation(JsonLocation location, File jsonFile) {
		int lineNumber = location.getLineNr();
		String fileName = jsonFile.getName();
		String errorLocation = fileName + ", line " + lineNumber;
		return errorLocation;
	}

}
