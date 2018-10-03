package org.fallowdeer055.dataman.api.exception;

public class MissingAttributeException extends EntityListCreationException {

	private static final long serialVersionUID = 1L;
	private String attributeName;

	public MissingAttributeException(String attributeName, String attributeLocation) {
		super(attributeLocation);
		this.attributeName = attributeName;
	}

	public String getAttributeName() {
		return attributeName;
	}

	@Override
	public String getMessage() {
		return "Missing attribute " + attributeName + " in " + getErrorLocation();
	}
	
}
