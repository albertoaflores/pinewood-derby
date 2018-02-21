package io.cybertech.pd.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.cybertech.pd.model.entity.HeatSheet;
import io.cybertech.pd.model.entity.Racer;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HeatSheetService {
	private Map<Long, HeatSheet> repository = new HashMap<>();
	
	public void createHeatSheet(Iterable<Racer> racers, int numberOfRunsPerRacer) {
		HeatSheet heatSheet = new HeatSheet();
		//heatSheet.createEvents(new NaiveSheetGeneratorImpl()); // TODO: Introduce IoC on the generator algorithm
		//repository.put(heatSheet.getId(), heatSheet);
	}

	public HeatSheet getHeatSheet(Long id) {
		return repository.get(id);
	}


}
