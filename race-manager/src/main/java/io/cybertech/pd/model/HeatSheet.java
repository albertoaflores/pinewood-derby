package io.cybertech.pd.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import io.cybertech.pd.model.dto.external.HeatResult;
import org.springframework.data.annotation.Id;

import io.cybertech.pd.service.algorithm.SheetGeneratorAlgorithm;
import lombok.Data;

@Data
public class HeatSheet {
	@Id
	private final Long id;
	private final Collection<Racer> racers;
	private final int numberOfRunsPerRacer;
	private List<HeatEvent> events = new ArrayList<>();
	private Date created;
	private Date startTime;
	
	public HeatSheet(Collection<Racer> racers, int numberOfRunsPerRacer) {
		// setup 
		this.id = System.currentTimeMillis();
		this.created = new Date();
		this.racers = racers;
		this.numberOfRunsPerRacer = numberOfRunsPerRacer;
	}
	
	public void createEvents(SheetGeneratorAlgorithm sheetGenerator) {
		events = sheetGenerator.generateEvents(racers, numberOfRunsPerRacer);
	}
	
	public void updateHeatResult(int heatIndex, HeatResult heatResults) {
		if (events.size() >= heatIndex) {
			throw new IllegalArgumentException("Unable to update invalid heat!");
		}
		
		HeatEvent heat = events.get(heatIndex);
		heat.setResult(heatResults);
		
		// TODO: update racer ranking when saving heat result
		//collections.sort
	}
	
	private final SortedSet<Racer> ranking = new TreeSet<Racer>(new Comparator<Racer>() {

		@Override
		public int compare(Racer racer1, Racer racer2) {
			return racer1.getBestTime().compareTo(racer2.getBestTime());
		}
	});
}