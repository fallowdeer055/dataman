package org.fallowdeer055.dataman.api.exception;

public class UnexpectedAttributeException extends EntityListCreationException {
	private static final long serialVersionUID = 1L;
	private String attributeName;

	public UnexpectedAttributeException(String attributeName, String attributeLocation) {
		super(attributeLocation);
		this.attributeName = attributeName;

	}

	public String getAttributeName() {
		return attributeName;
	}

	@Override
	public String getMessage() {
		return "Unexpected attribute " + attributeName + " in " + getErrorLocation();
	}

}
