package io.cybertech.pd.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@JsonDeserialize(builder = EventSchedule.EventScheduleBuilder.class)
public class EventSchedule {
    @Builder.Default
    private String uuid = UUID.randomUUID().toString();

    @Builder.Default
    private Date created = new Date();

    @Builder.Default
    private Date updated = new Date();

    @Builder.Default
    private List<HeatEvent> events = new ArrayList<>();

    @JsonPOJOBuilder(withPrefix = "")
    public static class EventScheduleBuilder {}
}
