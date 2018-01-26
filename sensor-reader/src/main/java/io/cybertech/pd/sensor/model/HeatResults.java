package io.cybertech.pd.sensor.model;

import lombok.Builder;
import lombok.Getter;

/**
 * A heat result is the summary of a given result as recorded by an external system (e.g. 
 * a sensor, usb port, etc).
 * 
 * <p>In general HeatResults are considered immutable since they are created once and expected
 * to never be updated.
 */
@Builder
@Getter
public class HeatResults {
	private LaneResult firstPlace;
	private LaneResult secondPlace;
	private LaneResult thirdPlace;
	
	@Override
	public String toString() {
		return "(1st) " + firstPlace + ", (2nd) " + secondPlace + ", (3rd) " + thirdPlace;
	}
	
	public LaneResult getLaneResult(String laneNumber) {
		if (firstPlace.getLaneNumber().equals(laneNumber)) {
			return firstPlace;
		} else if (secondPlace.getLaneNumber().equals(laneNumber)) {
			return secondPlace;
		} else if (thirdPlace.getLaneNumber().equals(laneNumber)) {
			return thirdPlace;
		}
		return null;
	}
	
}
