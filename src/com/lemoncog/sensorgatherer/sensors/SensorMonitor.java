package com.lemoncog.sensorgatherer.sensors;

public interface SensorMonitor<T>
{
	public void newDataAcquired(T newData);
}
