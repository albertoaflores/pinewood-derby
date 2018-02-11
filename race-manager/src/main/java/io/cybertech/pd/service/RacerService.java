package io.cybertech.pd.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.cybertech.pd.model.Racer;
import io.cybertech.pd.model.repository.RacerRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RacerService {
	@Autowired private RacerRepository racerRepository;
	
	public void addRacer(Racer racer) {
		if (racer.getId() == null) {
			racer.setId(System.currentTimeMillis());
		}
		
		Racer existingRecord = getRacer(racer.getId());
		log.info("Racer Id: {}", racer.getId());
		
		if (existingRecord == null) {
			log.info("New racer record created: {}", racer);
			racer.setCreated(new Date());
			racerRepository.save(racer);
		} else {
			log.info("Racer record update: {}", racer);
			existingRecord.setRacerName(racer.getRacerName());
			existingRecord.setCarName(racer.getCarName());
			existingRecord.setGroupName(racer.getGroupName());
			existingRecord.setUpdated(new Date());
			racerRepository.save(existingRecord);
		}
	}
	
	public Racer getRacer(Long racerId) {
		return racerRepository.findOne(racerId);
	}
	
	public void removeRacer(Long racerId) {
		racerRepository.delete(racerId);
	}
	
	public Collection<Racer> getAllRacers() {
		return racerRepository.findAll();
	}
}
