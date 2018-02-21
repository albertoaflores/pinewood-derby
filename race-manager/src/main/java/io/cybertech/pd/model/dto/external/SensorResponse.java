package io.cybertech.pd.model.dto.external;

import lombok.Builder;
import lombok.Getter;

/**
 * A heat result is the summary of a given result as recorded by an external system (e.g. 
 * a sensor, usb port, etc).
 * 
 * <p>In general SensorResponse are considered immutable since they are created once and expected
 * to never be updated.
 */
@Builder
@Getter
public class SensorResponse {

	private LaneTimerReadout firstPlace;
	private LaneTimerReadout secondPlace;
	private LaneTimerReadout thirdPlace;
	
	@Override
	public String toString() {
		return "(1st) " + firstPlace + ", (2nd) " + secondPlace + ", (3rd) " + thirdPlace;
	}
	
	public LaneTimerReadout getLaneResult(int laneNumber) {
		if (firstPlace.getLaneNumber() == laneNumber) {
			return firstPlace;
		} else if (secondPlace.getLaneNumber() == laneNumber) {
			return secondPlace;
		} else if (thirdPlace.getLaneNumber() == laneNumber) {
			return thirdPlace;
		}
		return null;
	}

	public boolean isFirstPlace(int laneNumber) {
	    return firstPlace.equals(getLaneResult(laneNumber));
    }
}
