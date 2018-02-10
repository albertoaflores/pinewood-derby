package io.cybertech.pd.sensor.endpoint;

import io.cybertech.pd.sensor.handler.mongodb.HeatResultRepository;
import io.cybertech.pd.sensor.model.HeatResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@Profile("mongodb")
@Slf4j
public class HeatResultsEndpoint {
    @Autowired private HeatResultRepository heatResultsRepository;

    @RequestMapping(path = "/sensor/result/latest", method = RequestMethod.GET)
    public HeatResult getLastResult(HttpServletResponse response) {
        HeatResult latestResult = heatResultsRepository.findTopByOrderByTimestampDesc();
        if (latestResult == null) {
            log.warn("No recent heat result found in repository. Check your sensor connection!");
            response.setStatus(404);
        }
        return latestResult;
    }

}
