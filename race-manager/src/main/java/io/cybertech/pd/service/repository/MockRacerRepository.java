package io.cybertech.pd.service.repository;

import io.cybertech.pd.model.entity.Racer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class MockRacerRepository implements RacerRepository {
    private Map<String, Racer> racers = new TreeMap<String, Racer>();

    public MockRacerRepository() {
        createRacer("John");
        createRacer("Peter");
        createRacer("Emma", true);
        createRacer("Daniela");
        createRacer("Isabella", true);
        createRacer("Alejandra");
        createRacer("Giovanna", true);
        createRacer("Gina", true);
        createRacer("Donna", true);
        createRacer("Gaby P.");
        createRacer("Jordan", true);
        createRacer("Alexa");
        createRacer("Monica", true);
        createRacer("Katrina", true);
        createRacer("Jessica", true);
    }

    private void createRacer(String racerName) {
        Racer racer = Racer.builder().name(racerName).build();
        racers.put(racer.getUuid(), racer);
    }

    private void createRacer(String racerName, boolean enabled) {
        Racer racer = Racer.builder().name(racerName).enabled(enabled).build();
        racers.put(racer.getUuid(), racer);
    }

    @Override
    public List<Racer> getAllRacers() {
        log.info("Returning {} racers", racers.size());
        List<Racer> allRacers = new ArrayList<>(racers.values());
        Collections.sort(allRacers, Comparator.comparing(Racer::getCreated));
        return allRacers;
    }

    @Override
    public void createRacer(Racer racer) {
        // strictly copying name and enable status
        Racer newRacer = Racer.builder().name(racer.getName()).enabled(racer.isEnabled()).build();
        racers.put(newRacer.getUuid(), newRacer);
        log.info("Racer '{}' created", newRacer.getUuid());
    }

    @Override
    public void updateRacer(String uuid, Racer racer) {
        if (racers.containsKey(uuid)) {
            log.info("Racer found. Updating...");
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
            throw new IllegalArgumentException("Racer not found in Mock Repository!");
        }
    }

    @Override
    public void deleteRacer(String uuid) {
        if (racers.containsKey(uuid)) {
            racers.remove(uuid);
        } else {
            log.warn("Racer {} not found. Ignoring!", uuid);
        }
    }
}
