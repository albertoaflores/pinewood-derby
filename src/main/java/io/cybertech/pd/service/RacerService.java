package io.cybertech.pd.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.cybertech.pd.model.RacerInformation;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RacerService {
	private Map<Long, RacerInformation> racers = new HashMap<>();
	
	public void addRacer(RacerInformation racer) {
		if (racer.getId() == null) {
			racer.setId(System.currentTimeMillis());
		}
		RacerInformation existingRecord = racers.put(racer.getId(), racer);
		if (existingRecord == null) {
			log.info("New racer record created: {}", racer);
			racer.setCreated(new Date());
			racer.setUpdated(new Date());
		} else {
			log.info("Racer record update: {}", racer);
			racer.setUpdated(new Date());
		}
	}
	
	public RacerInformation getRacer(Long racerId) {
		return racers.get(racerId);
	}
	
	public void removeRacer(Long racerId) {
		racers.remove(racerId);
	}
	
	public Collection<RacerInformation> getAllRacers() {
		return racers.values();
	}
}
