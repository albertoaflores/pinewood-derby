package io.cybertech.pd.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.cybertech.pd.model.HeatEvent;
import io.cybertech.pd.model.HeatSheet;
import io.cybertech.pd.model.dto.HeatSheetSummary;
import io.cybertech.pd.model.entity.Racer;
import io.cybertech.pd.service.algorithm.NaiveSheetGeneratorImpl;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HeatRunService {
	private Map<Long, HeatSheet> repository = new HashMap<>();
	
	public void createHeatSheet(Iterable<Racer> racers, int numberOfRunsPerRacer) {
		HeatSheet heatSheet = new HeatSheet(racers, numberOfRunsPerRacer);
		heatSheet.createEvents(new NaiveSheetGeneratorImpl()); // TODO: Introduce IoC on the generator algorithm
		repository.put(heatSheet.getId(), heatSheet);
	}
	
	public void updateHeatSheet(HeatSheet heatSheet) {
		repository.put(heatSheet.getId(), heatSheet);
	}
	
	public HeatSheet getHeatSheet(Long id) {
		return repository.get(id);
	}
	
	public Collection<HeatSheetSummary> getHeatSheets() {
		List<HeatSheetSummary> sheets = new ArrayList<>();
		for (Long sheetId : repository.keySet()) {
			HeatSheetSummary summary = getSummary(sheetId);
			sheets.add(summary);
		}
		return sheets;
	}
	
	public HeatSheetSummary getSummary(Long id) {
		HeatSheet heatSheet = getHeatSheet(id);
		HeatSheetSummary summary = new HeatSheetSummary();
		summary.setId(heatSheet.getId());
		summary.setNumberOfRunsPerRacer(heatSheet.getNumberOfRunsPerRacer());
		summary.setTotalNumberOfEvents(heatSheet.getEvents().size());
		int count = 0;
		for (HeatEvent event : heatSheet.getEvents()) {
			if (event.getResult() == null) {
				count++;
			}
		}
		summary.setNumberOfEventsNotTimed(count);
		summary.setCreated(heatSheet.getCreated());
		summary.setNumberOfRacers(heatSheet.getRacers().size());
		return summary;
	}
}
