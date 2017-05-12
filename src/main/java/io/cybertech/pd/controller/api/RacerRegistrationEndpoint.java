package io.cybertech.pd.controller.api;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;


import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cybertech.pd.model.RacerInformation;
import io.cybertech.pd.service.RacerService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/racer")
public class RacerRegistrationEndpoint {
	@Autowired private RacerService racerService;

	@RequestMapping(method={PUT, POST})
	public void saveRacerRegistration(@RequestBody(required=true) RacerInformation racer) {
		log.info("Saving racer {}", racer);
		racerService.addRacer(racer);
	}
	
	@RequestMapping(method={GET})
	public Collection<RacerInformation> getAllRacers() {
		return racerService.getAllRacers();
	}
	
	@RequestMapping(path="/{racerId}", method={GET})
	public RacerInformation getRacer(@PathVariable Long racerId, HttpServletResponse response) {
		RacerInformation racer = racerService.getRacer(racerId);
		if (racer == null) {
			log.info("Racer '{}' not found!", racerId);
			response.setStatus(404);
		}
		return racer;
	}
	
	@RequestMapping(path="/{racerId}", method={DELETE})
	public void deleteRacer(@PathVariable Long racerId, HttpServletResponse response) {
		RacerInformation racer = racerService.getRacer(racerId);
		if (racer == null) {
			log.info("Racer '{}' not found!", racerId);
			response.setStatus(404);
		} else {
			racerService.removeRacer(racerId);
		}
	}
}
