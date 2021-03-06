package io.cybertech.pd.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.cybertech.pd.sensor.model.LaneResult;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
@JsonDeserialize(builder = Racer.RacerBuilder.class)
public class Racer {
    @Builder.Default
	private String uuid = UUID.randomUUID().toString();

	private final String name;

	@Builder.Default
	private boolean enabled = false;

	@Builder.Default
	private List<LaneResult> results = new ArrayList<>();

	@Builder.Default
	private Date created = new Date();

	@Builder.Default
	private Date updated = new Date();
	
	@Override
	public String toString() {
		return "UUID: " + uuid + " Name: " + name;
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class RacerBuilder {
    }
}
