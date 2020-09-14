package io.cybertech.pd.service;


import io.cybertech.pd.model.dto.ScheduleRequest;
import io.cybertech.pd.model.entity.EventSchedule;
import io.cybertech.pd.model.entity.HeatEvent;
import io.cybertech.pd.model.entity.Racer;
import io.cybertech.pd.service.repository.RacerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


@Component
@Slf4j
public class MockEventScheduleGenerator implements EventScheduleGenerator {
    public RacerRepository racerRepository;

    @Autowired
    public MockEventScheduleGenerator(RacerRepository racerRepository) {
        this.racerRepository = racerRepository;
    }

    @Override
    public EventSchedule generateSchedule(ScheduleRequest scheduleRequest) {
        List<HeatEvent> heatEvents = new ArrayList<>();
        List<Racer> enabledRacers = racerRepository.getAllRacers().stream().filter(racer -> racer.isEnabled()).collect(Collectors.toList());
        log.info("Generating schedule for {} racers", enabledRacers.size());

        for (int i=0; i < enabledRacers.size(); i = i + 3) {
            HeatEvent heatEvent = HeatEvent.builder()
                                        .lane1(enabledRacers.get(i))
                                        .lane2(enabledRacers.get(i + 1))
                                        .lane3(enabledRacers.get(i + 2))
                                    .build();
            heatEvents.add(heatEvent);
        }

        EventSchedule schedule = EventSchedule.builder().events(heatEvents).build();
        log.info("Generated {} heats for event '{}'", heatEvents.size(), schedule.getUuid());

        return schedule;
    }
}
