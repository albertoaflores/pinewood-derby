package io.cybertech.pd.service.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.springframework.util.CollectionUtils;

import io.cybertech.pd.model.HeatEvent;
import io.cybertech.pd.model.RacerInformation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NaiveSheetGeneratorImpl implements SheetGeneratorAlgorithm {

	@Override
	public List<HeatEvent> generateEvents(Collection<RacerInformation> racers, int numberOfRunsPerRacer) {
		List<HeatEvent> events = new ArrayList<>();
		
		Random random = new Random();
		for (int run=0; run < numberOfRunsPerRacer; run++) {
			// create copy of the racer list
			List<RacerInformation> toBeAssigned = new ArrayList<>(racers);
			List<RacerInformation> assigned = new ArrayList<>(racers);
			
			int numberOfHeats = toBeAssigned.size() / 3;
			for (int heatNumber = 0; heatNumber < numberOfHeats; heatNumber++) {
				RacerInformation racerInLane1 = removeRandomRacer(toBeAssigned, random);
				RacerInformation racerInLane2 = removeRandomRacer(toBeAssigned, random);
				RacerInformation racerInLane3 = removeRandomRacer(toBeAssigned, random);
				
				HeatEvent event = new HeatEvent();
				event.setLane1(racerInLane1);
				event.setLane2(racerInLane2);	
				event.setLane3(racerInLane3);
				events.add(event);
				
				// save assigned racers (in case we need to use them for the remainder
				assigned.add(racerInLane1);
				assigned.add(racerInLane2);
				assigned.add(racerInLane3);
			}
			
			// trying to handle the remainder (if any)
			if (!toBeAssigned.isEmpty()) {
				log.info("Handling last heat with {} racer.", toBeAssigned.size());
				// remainder has to be of size 1 or 2
				RacerInformation racerInLane1 = removeRandomRacer(toBeAssigned, random);
				RacerInformation racerInLane2 = toBeAssigned.isEmpty() ? removeRandomRacer(assigned, random) : removeRandomRacer(toBeAssigned, random);
				RacerInformation racerInLane3 = toBeAssigned.isEmpty() ? removeRandomRacer(assigned, random) : removeRandomRacer(toBeAssigned, random);
				
				if (!toBeAssigned.isEmpty()) {
					log.warn("We are missing racers in heats!");					
				}

				HeatEvent event = new HeatEvent();
				event.setLane1(racerInLane1);
				event.setLane2(racerInLane2);	
				event.setLane3(racerInLane3);
				events.add(event);
			}		
		}
		log.info("Generated {} heats", events.size());
		return events;
	}
	
	private RacerInformation removeRandomRacer(List<RacerInformation> copy, Random random) {
		if (CollectionUtils.isEmpty(copy)) {
			return null;
		}
		if (copy.size() == 1) {
			return copy.remove(0);
		}
		return copy.remove(random.nextInt(copy.size()));
	}

}
