package org.fallowdeer055.dataman.api;

import java.util.List;

public interface DataSet {
	public List<String> getEntityListNames();
	public EntityList getEntityList(String entityListName);
}
