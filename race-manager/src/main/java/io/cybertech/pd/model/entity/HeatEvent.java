package io.cybertech.pd.model.entity;

import io.cybertech.pd.sensor.model.HeatResult;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class HeatEvent {
    private String uuid;
    private Integer eventNumber;
    private Date timestamp;
    private Racer lane1;
    private Racer lane2;
    private Racer lane3;
    private HeatResult result;
}
