package io.cybertech.pd.endpoint;

import io.cybertech.pd.model.dto.ScheduleRequest;
import io.cybertech.pd.model.dto.ScheduleSummary;
import io.cybertech.pd.model.entity.EventSchedule;
import io.cybertech.pd.service.EventScheduleGenerator;
import io.cybertech.pd.service.repository.EventScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/heat/schedule")
@Slf4j
public class HeatManagementEndpoint {
    private EventScheduleGenerator eventScheduleGenerator;
    private EventScheduleRepository eventScheduleRepository;

    @Autowired
    public HeatManagementEndpoint(EventScheduleGenerator eventScheduleGenerator, EventScheduleRepository eventScheduleRepository) {
        this.eventScheduleGenerator = eventScheduleGenerator;
        this.eventScheduleRepository = eventScheduleRepository;
    }

    @GetMapping(path="/", produces = "application/json")
    public List<ScheduleSummary> getSchedules() {
        List<EventSchedule> allSchedules = eventScheduleRepository.getSchedules();
        List<ScheduleSummary> summaries = new ArrayList<>();
        if (allSchedules != null && allSchedules.size() > 0) {
            // transform model to dto
            for (EventSchedule schedule : allSchedules) {
                ScheduleSummary summary = ScheduleSummary.builder()
                                                            .uuid(schedule.getUuid())
                                                            .created(schedule.getCreated())
                                                            .updated(schedule.getUpdated())
                                                            .totalNumberOfHeats(schedule.getEvents().size())
                                                            .pendingHeatsToRun(((int) schedule.getEvents().stream().filter(e -> e.getResult() == null).count()))
                                                        .build();

                summaries.add(summary);
            }
        }
        log.info("Retrieving {} schedule(s)", summaries.size());
        return summaries;
    }

    @PostMapping(path="/", consumes = "application/json")
    public void createEventSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        log.info("Creating Schedule with racers");
        EventSchedule schedule = eventScheduleGenerator.generateSchedule(scheduleRequest);
        eventScheduleRepository.createSchedule(schedule);
    }
}
