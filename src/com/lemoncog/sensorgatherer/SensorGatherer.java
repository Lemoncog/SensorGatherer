package com.lemoncog.sensorgatherer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lemoncog.sensorgatherer.repo.MemoryAccelerometerRepository;
import com.lemoncog.sensorgatherer.repo.SensorRepository;
import com.lemoncog.sensorgatherer.sensors.AccelerometerModel;
import com.lemoncog.sensorgatherer.sensors.AccelerometerReciever;
import com.lemoncog.sensorgatherer.sensors.SensorMonitor;
import com.lemoncog.sensorgatherer.sensors.SensorReciever;

import android.content.Context;

public class SensorGatherer implements SensorMonitor<AccelerometerModel>
{
	public static String SENSOR_TYPE_ACCEL = "ACCELEROMETER";
	public static final int REPO_MEMORY = 0;
	private Context mContext;

	private HashMap<String, SensorReciever<?>> mRunningServices;
	private List<SensorRepository<AccelerometerModel>> mAccelRepos;
	private List<SensorGathererListener> mDataListeners;

	private static SensorGatherer mSingleton;

	public static SensorGatherer getSingleton(Context aContext)
	{
		if (mSingleton == null)
		{
			mSingleton = new SensorGatherer(aContext);
		}
		return mSingleton;
	}

	public void trackAccelemeter(int aRepoType, SensorGathererListener aListener)
	{
		mDataListeners.add(aListener);
		// Kick off the accelemeter service and tell it to store the results in
		// the repo
		SensorReciever<?> runningSensor = mRunningServices.get(SENSOR_TYPE_ACCEL);

		if (runningSensor == null)
		{
			runningSensor = new AccelerometerReciever(mContext);
			runningSensor.startTracking();

			mRunningServices.put(SENSOR_TYPE_ACCEL, runningSensor);
		}

		// Hook up with its upates.
		runningSensor.addMonitor((SensorMonitor) this);

		switch (aRepoType)
		{
		case REPO_MEMORY:
			mAccelRepos.add(new MemoryAccelerometerRepository());
			break;
		}
	}
	
	public SensorRepository<?> getRepo(String aSensor, int aRepoType)
	{
		return mAccelRepos.get(0);
	}

	private SensorGatherer(Context aContext)
	{
		super();
		mRunningServices = new HashMap<String, SensorReciever<?>>();
		mAccelRepos = new ArrayList<SensorRepository<AccelerometerModel>>();
		mDataListeners = new ArrayList<SensorGathererListener>();
		mContext = aContext;
	}

	@Override
	public void newDataAcquired(AccelerometerModel newData)
	{
		for (int i = 0; i < mAccelRepos.size(); i++)
		{
			SensorRepository<AccelerometerModel> repo = mAccelRepos.get(i);
			repo.storeSensorData(newData);
		}
		
		for(int i = 0; i < mDataListeners.size(); i++)
		{
			SensorGathererListener listener = mDataListeners.get(i);
			
			listener.newDataAvaliable(SENSOR_TYPE_ACCEL, REPO_MEMORY);
		}
	}

}
