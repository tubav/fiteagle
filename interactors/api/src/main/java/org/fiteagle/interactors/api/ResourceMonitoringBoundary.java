package org.fiteagle.interactors.api;

import java.util.Collection;

import org.fiteagle.core.monitoring.StatusTable;

public interface ResourceMonitoringBoundary {

	public Collection<StatusTable> getMonitoringData();

	public StatusTable getMonitoringDataById(String id);
}
