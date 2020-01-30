package io.cybertech.pd.endpoint;

import io.cybertech.pd.model.entity.Racer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/racer")
@Slf4j
public class RacerEndpoint {
    private Map<String, Racer> racers = new TreeMap<String, Racer> ();
    // private List<Racer> racers = new ArrayList<Racer>();

    public RacerEndpoint() {
        createRacer("John");
        createRacer("Peter");
        createRacer("Emma", true);
        createRacer("Daniela");
        createRacer("Isabella", true);
        createRacer("Alejandra");
        createRacer("Giovanna", true);
        createRacer("Gina", true);
        createRacer("Donna");
        createRacer("Gaby P.");
        createRacer("Jordan", true);
        createRacer("Alexa");
        createRacer("Monica");

    }

    private void createRacer(String racerName) {
        Racer racer = Racer.builder().name(racerName).build();
        racers.put(racer.getUuid(), racer);
    }

    private void createRacer(String racerName, boolean enabled) {
        Racer racer = Racer.builder().name(racerName).enabled(enabled).build();
        racers.put(racer.getUuid(), racer);
    }

    @GetMapping(path = "/", produces = "application/json")
    public List<Racer> getRacers() {
        log.info("Returning {} racers", racers.size());
        List<Racer> allRacers = new ArrayList<>(racers.values());
        Collections.sort(allRacers, new RacerComparator());
        return allRacers;
    }

    public class RacerComparator implements Comparator<Racer> {
        public int compare(Racer a, Racer b) {
            return a.getCreated().compareTo(b.getCreated());
        }

    }

    @PostMapping(path = "/", consumes = "application/json")
    public void addRacer(@RequestBody Racer racer) {
        log.info("Creating Racer: {}", racer);
        // strictly copying name and enable status
        Racer newRacer = Racer.builder().name(racer.getName()).enabled(racer.isEnabled()).build();
        racers.put(newRacer.getUuid(), newRacer);
    }

    @PutMapping(path = "/{uuid}")
    public void updateRacer(@PathVariable(name = "uuid") String uuid, @RequestBody Racer racer) {
        log.info("Updating racer '{}'", uuid);

        if (racers.containsKey(uuid)) {
            Racer existingRacer = racers.get(uuid);

            // recreate racer
            Racer newRacer = Racer.builder()
                    .uuid(existingRacer.getUuid())
                    .name(racer.getName())
                    .enabled(racer.isEnabled())
                    .results(existingRacer.getResults())
                    .created(existingRacer.getCreated())
                    .updated(new Date())
                    .build();

            // update racer
            racers.put(uuid, newRacer);

        } else {
            throw new RacerNotFoundException();
        }
    }
}
