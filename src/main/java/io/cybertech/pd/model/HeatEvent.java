package io.cybertech.pd.model;

import lombok.Data;

/**
 * A heat event reflects a specific heat run for the 3 racers. It also contains the result of this event.
 */
@Data
public class HeatEvent {
	private RacerInformation lane1;
	private RacerInformation lane2;
	private RacerInformation lane3;
	private HeatResults result;
}
