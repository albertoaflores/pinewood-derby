package io.cybertech.pd.model.entity;

import java.util.Date;
import java.util.List;

import io.cybertech.pd.sensor.model.LaneResult;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class Racer {
	private String uuid;

	@NonNull
	private String name;
	private String carName;
	private List<LaneResult> results;

	private Date created;
	private Date updated;
	
	@Override
	public String toString() {
		return "Name: " + name + ", Car: " + carName;
	}
}
