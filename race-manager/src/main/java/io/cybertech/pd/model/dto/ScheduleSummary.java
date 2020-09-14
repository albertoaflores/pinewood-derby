package io.cybertech.pd.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
@JsonDeserialize(builder = ScheduleRequest.ScheduleRequestBuilder.class)
public class ScheduleSummary {
    final private String uuid;
    final private Date created;
    final private Date updated;
    final private int totalNumberOfHeats;
    final private int pendingHeatsToRun;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ScheduleSummaryBuilder {

    }
}
