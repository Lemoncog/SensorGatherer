package com.lemoncog.sensorgatherer.sensors;

import android.hardware.SensorEvent;

public class AccelerometerModel extends SensorModel
{

	private float[] mXYZ = new float[3];
	private float mEventTimestamp;

	public void parseSensorEvent(SensorEvent event)
	{
		setAxisValues(event.values);
		mEventTimestamp = event.timestamp;
	}

	public void setAxisValues(float[] XYZ)
	{
		mXYZ[0] = XYZ[0];
		mXYZ[1] = XYZ[1];
		mXYZ[2] = XYZ[2];
	}

}
