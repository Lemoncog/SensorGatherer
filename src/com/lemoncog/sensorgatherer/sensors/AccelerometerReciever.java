package com.lemoncog.sensorgatherer.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

public class AccelerometerReciever extends SensorReciever<AccelerometerModel>
{
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;

	public AccelerometerReciever(Context aContext)
	{
		super(aContext);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		AccelerometerModel accelerometerEvent = new AccelerometerModel();
		accelerometerEvent.parseSensorEvent(event);

		handleData(accelerometerEvent);
	}

	@Override
	public void startTracking()
	{
		if (!isTracking())
		{
			mSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
			mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
			mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

			setIsTracking(true);
		}
	}

	@Override
	public void handleData(AccelerometerModel data)
	{
		notifyOfNewData(data);
	}
}
