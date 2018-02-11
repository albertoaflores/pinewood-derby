package io.cybertech.pd.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@JsonDeserialize(builder = Racer.RacerInformationBuilder.class)
@Document(collection = "racers")
public class Racer {
	@Id
	private Long id;
	private String racerName;
	private String carName;
	private String groupName;
	private Double bestTime;
	private Date created;
	private Date updated;
	
	@Override
	public String toString() {
		return "Name: " + racerName + ", Car: " + carName + ", Group: " + groupName;
	}
	
	@JsonPOJOBuilder(withPrefix="")
	public static class RacerInformationBuilder {
	}
}
