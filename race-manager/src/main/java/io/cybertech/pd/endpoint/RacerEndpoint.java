package io.cybertech.pd.endpoint;

import io.cybertech.pd.model.entity.Racer;
import io.cybertech.pd.service.repository.RacerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/racer")
@Slf4j
public class RacerEndpoint {
    private RacerRepository racerRepository;

    @Autowired
    public RacerEndpoint(RacerRepository racerRepository) {
        this.racerRepository = racerRepository;
    }


    @GetMapping(path = "/", produces = "application/json")
    public List<Racer> getRacers() {
        return racerRepository.getAllRacers();
    }

    @PostMapping(path = "/", consumes = "application/json")
    public void addRacer(@RequestBody Racer racer) {
        log.info("Creating Racer: {}", racer);
        racerRepository.createRacer(racer);
    }

    @PutMapping(path = "/{uuid}")
    public void updateRacer(@PathVariable(name = "uuid") String uuid, @RequestBody Racer racer) {
        log.info("Updating racer '{}'", uuid);

        try {
            racerRepository.updateRacer(uuid, racer);
        } catch (IllegalArgumentException e) {
            log.error("Unable to Update Racer: ", e);
            throw new RacerNotFoundException();
        }

    }

    @DeleteMapping(path = "/{uuid}")
    public void deleteRacer(@PathVariable(name = "uuid") String uuid) {
        log.info("Deleting Racer: {}", uuid);
        racerRepository.deleteRacer(uuid);
    }
}
