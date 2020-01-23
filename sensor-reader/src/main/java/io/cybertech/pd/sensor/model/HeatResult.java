package io.cybertech.pd.sensor.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * A heat result is the summary of a given result as recorded by an external system (e.g. 
 * a sensor, usb port, etc).
 * 
 * <p>In general HeatResult are considered immutable since they are created once and expected
 * to never be updated.
 */
@Builder
@Getter
public class HeatResult {
    private String uuid;
    private Date timestamp;
	private LaneResult lane1;
	private LaneResult lane2;
	private LaneResult lane3;
	
	@Override
	public String toString() {
		return "(1st) " + getLaneResult(HeatRank.FIRST_PLACE) + ", (2nd) " + getLaneResult(HeatRank.SECOND_PLACE) + ", (3rd) " + getLaneResult(HeatRank.THIRD_PLACE);
	}

	/**
     * Returns the {@link LaneResult}
     */
	public LaneResult getLaneResult(HeatRank rank) {
		if (lane1.getRank().equals(rank)) {
			return lane1;
		} else if (lane2.getRank().equals(rank)) {
			return lane2;
		} else if (lane3.getRank().equals(rank)) {
			return lane3;
		}
		return null;
	}

}
