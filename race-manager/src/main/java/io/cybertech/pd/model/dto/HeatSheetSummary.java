package io.cybertech.pd.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class HeatSheetSummary {
	private Long id;
	private int numberOfRacers;
	private int numberOfRunsPerRacer;
	private int totalNumberOfEvents;
	private int numberOfEventsNotTimed;
	private Date created;
}
