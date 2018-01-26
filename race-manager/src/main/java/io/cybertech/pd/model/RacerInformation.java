package io.cybertech.pd.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = RacerInformation.RacerInformationBuilder.class)
public class RacerInformation {
	@Id
	private Long id;
	private String racerName;
	private String carNumber;
	private String groupName;
	private Double bestTime;
	private Date created;
	private Date updated;
	
	@Override
	public String toString() {
		return "Name: " + racerName + ", Car#: " + carNumber + ", Group: " + groupName;
	}
	
	@JsonPOJOBuilder(withPrefix="")
	public static class RacerInformationBuilder {
	}
}
