package io.cybertech.pd.service;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.stereotype.Component;

import io.cybertech.pd.model.HeatResults;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HeatResultServiceBean {
	private LinkedList<HeatResults> runs = new LinkedList<HeatResults>();
	private static final Object lock = new Object();

	public void processHeatResults(HeatResults result) {
		log.info("Recording heat results: {}", result);
		synchronized (lock) {
			runs.add(result);			
		}

	}
	
	public Collection<HeatResults> getRuns() {
		synchronized (lock) {
			return runs;			
		}
	}
	
	public HeatResults getLastRun() {
		synchronized (lock) {
			if (runs.isEmpty()) {
				return null;
			}
			return runs.getLast();
		}
	}
	
	public void clear() {
		synchronized (lock) {
			runs.clear();
		}
	}
}
