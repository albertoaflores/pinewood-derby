package io.cybertech.pd.service.repository;

import io.cybertech.pd.model.entity.EventSchedule;

import java.util.List;

public interface EventScheduleRepository {

    List<EventSchedule> getSchedules();

    void createSchedule(EventSchedule schedule);
}
