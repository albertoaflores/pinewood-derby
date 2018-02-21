package io.cybertech.pd.controller.api;

import io.cybertech.pd.model.dto.external.SensorResponse;
import io.cybertech.pd.model.entity.HeatEvent;
import io.cybertech.pd.model.entity.repository.HeatEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/heat")
@Slf4j
public class HeatEventEndpoint {
    @Autowired private HeatEventRepository heatEventRepository;

    @RequestMapping(path="/{heatId}", method={RequestMethod.POST})
    public void updateHeatWithTimerResponse(@PathVariable Long heatId,
                                            @RequestBody(required=true) SensorResponse sensorResponse,
                                            HttpServletResponse response) {
        // retrieve heat event
        HeatEvent heatEvent = heatEventRepository.getOne(heatId);
        if (heatEvent == null) {
            log.info("Event '{}' not found!", heatId);
            response.setStatus(404);
        } else {
            // populate time from sensor response
            heatEvent.setTimeInLane1(sensorResponse.getLaneResult(1).getTime());
            heatEvent.setTimeInLane2(sensorResponse.getLaneResult(2).getTime());
            heatEvent.setTimeInLane3(sensorResponse.getLaneResult(3).getTime());

            // update heat event
            heatEventRepository.save(heatEvent);
        }
    }
}
