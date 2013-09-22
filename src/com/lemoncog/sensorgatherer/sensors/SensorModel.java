package com.lemoncog.sensorgatherer.sensors;

import java.util.Date;

import android.hardware.SensorEvent;

public class SensorModel {	
	//Until stored, ID is -1;
	private long mID = -1;

	public SensorModel() {
		super();
	}

	public void parseSensorEvent(SensorEvent event) {
	}
	
	public void setmID(long ID) {
		mID = ID;
	}
	
	public long getID() {
		return mID;
	}

}
