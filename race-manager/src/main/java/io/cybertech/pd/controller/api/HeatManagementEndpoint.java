package io.cybertech.pd.controller.api;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cybertech.pd.model.dto.external.HeatResult;
import io.cybertech.pd.model.HeatSheet;
import io.cybertech.pd.model.dto.HeatSheetSummary;
import io.cybertech.pd.model.Racer;
import io.cybertech.pd.service.HeatRunService;
import io.cybertech.pd.service.RacerService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j 
@RequestMapping("/api/heat/management")
public class HeatManagementEndpoint {
	@Autowired private HeatRunService heatRunService;
	@Autowired private RacerService racerService;
	
	@RequestMapping(path="/sheet/build/{numberOfRunsPerRacer}", method={POST})
	public void buildHeatSheet(@PathVariable(required=true) int numberOfRunsPerRacer, HttpServletResponse response) {
		Collection<Racer> racers = racerService.getAllRacers();
		if (racers.size() < 3) {
			log.warn("Can't build a heat sheet with {} racers.", racers.size());
			try {
				response.sendError(400, String.format("Can't build heat sheet with %d racers.", racers.size()));
			} catch (IOException e) {
				log.error("Unable to send 400 error code!", e);
			}
		} else {
			log.info("Building heat sheet with {} races per racer for {} racers.", numberOfRunsPerRacer, racers.size());
			heatRunService.createHeatSheet(racers, numberOfRunsPerRacer);
		}
	}
	
	@RequestMapping(path="/sheet", method={GET})
	public Collection<HeatSheetSummary> getAllHeatSheets() {
		log.info("Retrieving heat sheet summaries...");
		return heatRunService.getHeatSheets();
	}
	
	@RequestMapping(path="/sheet/{sheetId}", method={GET})
	public HeatSheet getHeatSheet(@PathVariable(required=true) Long sheetId, HttpServletResponse response) {
		HeatSheet heatSheet = heatRunService.getHeatSheet(sheetId);
		if (heatSheet == null) {
			log.info("Heatsheet '{}' not found!", sheetId);
			response.setStatus(404);
		}
		return heatSheet;
	}
	
	@RequestMapping(path="/sheet/{sheetId}/start", method={POST})
	public void startHeatSheet(@PathVariable Long sheetId) {
		HeatSheet heatSheet = heatRunService.getHeatSheet(sheetId);
		heatSheet.setStartTime(new Date());
		heatRunService.updateHeatSheet(heatSheet);
	}
	
	@RequestMapping(path="/sheet/{sheetId}/heat/{heatIndex}/result", method={POST})
	public void recordHeat(@PathVariable(required=true) Long sheetId, 
						   @PathVariable(required=true) int heatIndex, 
						   HeatResult heatResults,
						   HttpServletResponse response) {
		HeatSheet heatSheet = heatRunService.getHeatSheet(sheetId);
		if (heatSheet == null) {
			log.info("Heatsheet '{}' not found!", sheetId);
			response.setStatus(404);
		}
		
		try {
			// update heat result
			heatSheet.updateHeatResult(heatIndex, heatResults);
			
			// save heat sheet
			heatRunService.updateHeatSheet(heatSheet);
		} catch (Exception e) {
			log.error("Failed to update heat results", e);
		}
	}
}
