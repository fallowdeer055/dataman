package org.fallowdeer055.dataman.api.exception;

public class EntityListCreationException extends RuntimeException {

	public String getDataSourceDescription() {
		return dataSourceDescription;
	}

	private static final long serialVersionUID = 1L;
	private String dataSourceDescription;

	public EntityListCreationException(String dataSourceDescription) {
		super();
		this.dataSourceDescription = dataSourceDescription;
	}

	public EntityListCreationException(String dataSourceDescription, String message) {
		super(message);
		this.dataSourceDescription = dataSourceDescription;
	}

	public EntityListCreationException(String dataSourceDescription, Throwable cause) {
		super(cause);
		this.dataSourceDescription = dataSourceDescription;
	}

	public EntityListCreationException(String dataSourceDescription, String message, Throwable cause) {
		super(message, cause);
		this.dataSourceDescription = dataSourceDescription;
	}

}
