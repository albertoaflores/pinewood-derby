package io.cybertech.pd.sensor.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "heatResults")
public class HeatResult {
	@Id
    private String id;

	@Indexed(expireAfterSeconds = 36000) // TTL to 10 hours
    private Date timestamp;
	private LaneResult firstPlace;
	private LaneResult secondPlace;
	private LaneResult thirdPlace;
	
	@Override
	public String toString() {
		return "(1st) " + firstPlace + ", (2nd) " + secondPlace + ", (3rd) " + thirdPlace;
	}

	/**
     * Returns the {@link LaneResult}
     */
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
