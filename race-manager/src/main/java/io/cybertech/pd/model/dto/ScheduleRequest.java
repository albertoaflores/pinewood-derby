package io.cybertech.pd.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonDeserialize(builder = ScheduleRequest.ScheduleRequestBuilder.class)
public class ScheduleRequest {
    private final int numberOfHeatsPerRacer;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ScheduleRequestBuilder {

    }
}
