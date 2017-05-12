package io.cybertech.pd.controller.api;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cybertech.pd.model.HeatResults;
import io.cybertech.pd.service.HeatResultServiceBean;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j 
@RequestMapping("/api/heat/results")
public class HeatResultsEndpoint {
	@Autowired private HeatResultServiceBean heatResultServiceBean;

	@RequestMapping(path="/", method={GET})
	public Collection<HeatResults> getSummary() {
		return heatResultServiceBean.getRuns();
	}
	
	@RequestMapping(path="/latest", method={GET})
	public HeatResults getLatest( HttpServletResponse response) {
		HeatResults lastRun = heatResultServiceBean.getLastRun();
		if (lastRun == null) {
			log.info("No recent heat detected!");
			response.setStatus(404);
		}
		return lastRun;
	}
	
	@RequestMapping(path="/clear", method={PUT})
	public void flushHeatResults() {
		heatResultServiceBean.clear();
	}
}
