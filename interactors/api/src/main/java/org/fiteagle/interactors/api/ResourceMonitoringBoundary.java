package org.fiteagle.interactors.api;

import java.util.Collection;
import java.util.List;

import orgt.fiteagle.core.monitoring.StatusTable;

public interface ResourceMonitoringBoundary {
	public Collection<StatusTable> getMonitoringData();

}
