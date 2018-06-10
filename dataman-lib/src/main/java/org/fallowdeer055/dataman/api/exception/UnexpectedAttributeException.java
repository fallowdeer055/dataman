package org.fallowdeer055.dataman.api.exception;

public class UnexpectedAttributeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String attributeName;
	private String attributeLocation;

	public String getAttributeName() {
		return attributeName;
	}

	public String getAttributeLocation() {
		return attributeLocation;
	}

	public UnexpectedAttributeException(String attributeName, String attributeLocation) {
		super();
		this.attributeName = attributeName;
		this.attributeLocation = attributeLocation;
	}

	@Override
	public String getMessage() {
		return "Unexpected attribute " + attributeName + " in " + attributeLocation;
	}

}
