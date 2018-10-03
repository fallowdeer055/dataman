package org.fallowdeer055.dataman.api.exception;

public class EntityListCreationException extends RuntimeException {

	public String getErrorLocation() {
		return errorLocation;
	}

	private static final long serialVersionUID = 1L;
	private String errorLocation;

	public EntityListCreationException(String errorLocation) {
		super();
		this.errorLocation = errorLocation;
	}


}
