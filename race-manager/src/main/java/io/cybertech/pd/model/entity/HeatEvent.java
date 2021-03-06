package io.cybertech.pd.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.cybertech.pd.sensor.model.HeatResult;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Builder
@Getter
@JsonDeserialize(builder = HeatEvent.HeatEventBuilder.class)
public class HeatEvent {
    @Builder.Default
    private String uuid = UUID.randomUUID().toString();

    @Builder.Default
    private Date created = new Date();

    @NonNull
    private Racer lane1;

    @NonNull
    private Racer lane2;

    @NonNull
    private Racer lane3;

    @Setter
    private HeatResult result;

    @JsonPOJOBuilder(withPrefix = "")
    public static class HeatEventBuilder {

    }
}
