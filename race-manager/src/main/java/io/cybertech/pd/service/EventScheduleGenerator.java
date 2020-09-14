package io.cybertech.pd.service;

import io.cybertech.pd.model.dto.ScheduleRequest;
import io.cybertech.pd.model.entity.EventSchedule;

public interface EventScheduleGenerator {

    EventSchedule generateSchedule(ScheduleRequest scheduleRequest);
}
