package io.cybertech.pd.endpoint;

import io.cybertech.pd.model.entity.Racer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/racer")
@Slf4j
public class RacerEndpoint {
    private List<Racer> racers = new LinkedList<>();

    public RacerEndpoint() {
        racers.add(Racer.builder().name("John").build());
        racers.add(Racer.builder().name("Peter").build());
        racers.add(Racer.builder().name("Emma").enabled(true).build());
        racers.add(Racer.builder().name("Daniela").build());
        racers.add(Racer.builder().name("Isabella").enabled(true).build());
        racers.add(Racer.builder().name("Alejandra").build());
        racers.add(Racer.builder().name("Giovanna").enabled(true).build());
        racers.add(Racer.builder().name("Gina").enabled(true).build());
        racers.add(Racer.builder().name("Donna").build());
        racers.add(Racer.builder().name("Gaby P.").build());
        racers.add(Racer.builder().name("Jordan").enabled(true).build());
        racers.add(Racer.builder().name("Alexa").build());
        racers.add(Racer.builder().name("Monica").build());
    }

    @GetMapping(path = "/", produces = "application/json")
    public List<Racer> getRacers() {
        log.info("Returning {} racers", racers.size());
        return racers;
    }

    @PostMapping(path = "/", consumes = "application/json")
    public void addRacer(@RequestBody Racer racer) {
        log.info("Creating Racer: {}", racer);
        // strictly copying name and enable status
        Racer newRacer = Racer.builder().name(racer.getName()).enabled(racer.isEnabled()).build();
        racers.add(newRacer);
    }

    @PutMapping(path = "/{uuid}")
    public void updateRacer(@PathVariable(name = "uuid") String uuid, Racer racer) {
        log.info("Updating racer '{}'", uuid);
        boolean found = false;
        for (Racer existingRacer : racers) {
            if (existingRacer.getUuid().equals(uuid)) {
                racers.remove(existingRacer);

                // recreate racer
                Racer newRacer = Racer.builder()
                                        .uuid(existingRacer.getUuid())
                                        .name(racer.getName())
                                        .enabled(racer.isEnabled())
                                        .results(existingRacer.getResults())
                                        .created(existingRacer.getCreated())
                                        .updated(new Date())
                                    .build();
                racers.add(newRacer);
                found = true;
            }
        }
        if (!found) {
            throw new RacerNotFoundException();
        }
    }
}
