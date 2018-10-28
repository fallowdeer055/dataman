package org.fallowdeer055.dataman.api;

import java.io.File;

public interface DataSetFactory {
	public static DataSetFactory getInstance() {
		return null;
	}
	
	public DataSet createDataSet(File directory, Package pckage); 
}
