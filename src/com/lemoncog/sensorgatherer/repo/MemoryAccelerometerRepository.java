package com.lemoncog.sensorgatherer.repo;

import java.util.ArrayList;
import java.util.List;

import com.lemoncog.sensorgatherer.sensors.AccelerometerModel;

public class MemoryAccelerometerRepository implements
		SensorRepository<AccelerometerModel> {
	private List<AccelerometerModel> mMemoryStorage;

	// Static to avoid multiple classes causing a clash in IDs
	private static long mUniqueID = 0;

	public MemoryAccelerometerRepository() {
		super();

		mMemoryStorage = new ArrayList<AccelerometerModel>();
	}

	@Override
	public long[] storeSensorDataBatch(List<AccelerometerModel> dataBatch) {

		long[] generatedIDs = new long[dataBatch.size()];
		
		for (int i = 0; i < dataBatch.size(); i++) {
			long id = storeSensorData(dataBatch.get(i));
			
			generatedIDs[i] = id;
		}

		return generatedIDs;
	}

	@Override
	public long storeSensorData(AccelerometerModel data) {
		mUniqueID++;
		data.setmID(mUniqueID);

		mMemoryStorage.add(data);

		return mUniqueID;
	}

	@Override
	public List<AccelerometerModel> getStoredData() {
		return mMemoryStorage;
	}
}
