package com.lemoncog.sensorgatherer.repo;

import java.util.List;

import com.lemoncog.sensorgatherer.sensors.SensorModel;

public interface SensorRepository<T extends SensorModel> {

	public long[] storeSensorDataBatch(List<T> dataBatch);
	public long storeSensorData(T data);
	public List<T> getStoredData();
	
}
