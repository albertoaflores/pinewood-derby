package io.cybertech.pd.endpoint;

import io.cybertech.pd.model.entity.Racer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/racer")
@Slf4j
public class RacerEndpoint {
    private List<Racer> racers = new ArrayList<>();

    public RacerEndpoint() {
        racers.add(Racer.builder().name("John").build());
        racers.add(Racer.builder().name("Peter").build());
        racers.add(Racer.builder().name("Emma").build());
        racers.add(Racer.builder().name("Daniela").build());
        racers.add(Racer.builder().name("Isabella").build());
        racers.add(Racer.builder().name("Alejandra").build());
        racers.add(Racer.builder().name("Giovanna").build());
        racers.add(Racer.builder().name("Gina").build());
        racers.add(Racer.builder().name("Donna").build());
        racers.add(Racer.builder().name("Gaby P.").build());
        racers.add(Racer.builder().name("Jordan").build());
        racers.add(Racer.builder().name("Alexa").build());
        racers.add(Racer.builder().name("Monica").build());
    }

    @GetMapping(path = "/", produces = "application/json")
    public List<Racer> getRacers() {
        log.info("Returning {} racers", racers.size());
        return racers;
    }
}
