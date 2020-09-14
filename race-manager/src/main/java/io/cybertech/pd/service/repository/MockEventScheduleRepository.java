package io.cybertech.pd.service.repository;

import io.cybertech.pd.model.entity.EventSchedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class MockEventScheduleRepository implements EventScheduleRepository {
    private Map<String, EventSchedule> scheduleRepository = new TreeMap<>();

    @Override
    public List<EventSchedule> getSchedules() {
        List<EventSchedule> allSchedules = new ArrayList<>(scheduleRepository.values());
        Collections.sort(allSchedules, Comparator.comparing(EventSchedule::getCreated));
        return allSchedules;
    }

    @Override
    public void createSchedule(EventSchedule schedule) {
        scheduleRepository.put(schedule.getUuid(), schedule);
    }
}
