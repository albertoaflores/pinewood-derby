package io.cybertech.pd.model.dto.external;

import lombok.Builder;
import lombok.Getter;

/**
 * A Lane result is the specific results of a given lane as received from an 
 * external source (e.g. sensor, usb port, etc)
 * 
 * <p>Notice that this is expected to be an immutable object. 
 */
@Builder
@Getter
public class LaneResult {
	private int laneNumber;
	private Double time;
	
	@Override
	public String toString() {
		return "Lane: " + laneNumber + " Time: " + time;
	}
}
