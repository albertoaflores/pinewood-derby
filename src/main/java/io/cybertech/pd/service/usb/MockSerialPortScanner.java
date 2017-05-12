package io.cybertech.pd.service.usb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.cybertech.pd.model.HeatResults;
import io.cybertech.pd.service.HeatResultServiceBean;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Profile({"development"})
public class MockSerialPortScanner {
	@Autowired private HeatResultServiceBean resultsHandler;
	@Autowired private List<String> results;
	private int lastResult = 0;
	
	@Scheduled(fixedRate=30000)
	public void fakeRun() {
		log.info(">>>> Faking Heat runs...");
		String result = results.get(lastResult);
		HeatResults heatResult = SerialPortParser.buildResult(result);
		resultsHandler.processHeatResults(heatResult);
		lastResult++;
		if (lastResult == results.size()) {
			lastResult = 0;
		}
	}
}
