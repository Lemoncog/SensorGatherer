package com.lemoncog.sensorgatherer.sensors;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.hardware.SensorEventListener;

public abstract class SensorReciever<T> implements SensorEventListener
{	
	private boolean mTracking;
	//private List<T> mGatheredData;
	private List<SensorMonitor<T>> mMonitors;
	private Context mContext;

	public SensorReciever(Context aContext)
	{
		super();

		mContext = aContext;

		mMonitors = new ArrayList<SensorMonitor<T>>();
	}
	
	public void addMonitor(SensorMonitor<T> aMonitor)
	{
		mMonitors.add(aMonitor);
	}
	
	public boolean isTracking()
	{
		return mTracking;
	}
	
	protected void setIsTracking(boolean aTracking)
	{
		mTracking = aTracking;
	}

	public abstract void handleData(T data);
	public abstract void startTracking();

	public void notifyOfNewData(T data)
	{
		for(int i = 0; i < mMonitors.size(); i++)
		{
			mMonitors.get(i).newDataAcquired(data);
		}
	}

	
	public Context getContext()
	{
		return mContext;
	}
}
