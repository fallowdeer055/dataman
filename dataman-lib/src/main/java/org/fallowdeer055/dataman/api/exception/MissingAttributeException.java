package org.fallowdeer055.dataman.api.exception;

public class MissingAttributeException extends EntityListCreationException {

	private static final long serialVersionUID = 1L;
	private String attributeName;

	public MissingAttributeException(String attributeName, String dataSourceDescription) {
		super(dataSourceDescription);
		this.attributeName = attributeName;
	}

	public String getAttributeName() {
		return attributeName;
	}

}
