package io.cybertech.pd.endpoint;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

import javax.servlet.http.HttpServletResponse;

import io.cybertech.pd.model.entity.repository.RacerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cybertech.pd.model.entity.Racer;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/racer")
public class RacerRegistrationEndpoint {
	@Autowired private RacerRepository racerRepository;

	@RequestMapping(method={PUT, POST})
	public void saveRacerRegistration(@RequestBody(required=true) Racer racer) {
		log.info("Saving racer {}", racer);
		racerRepository.save(racer);
	}
	
	@RequestMapping(method={GET})
	public Iterable<Racer> getAllRacers() {
	    log.info("Retrieving racers...");
		return racerRepository.findAll();
	}
	
	@RequestMapping(path="/{racerId}", method={GET})
	public Racer getRacer(@PathVariable Long racerId, HttpServletResponse response) {
		Racer racer = racerRepository.findOne(racerId);
		if (racer == null) {
			log.info("Racer '{}' not found!", racerId);
			response.setStatus(404);
		}
		return racer;
	}
	
	@RequestMapping(path="/{racerId}", method={DELETE})
	public void deleteRacer(@PathVariable Long racerId, HttpServletResponse response) {
		Racer racer = racerRepository.findOne(racerId);
		if (racer == null) {
			log.info("Racer '{}' not found!", racerId);
			response.setStatus(404);
		} else {
			racerRepository.delete(racerId);
		}
	}
}
