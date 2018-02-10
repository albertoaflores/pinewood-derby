package io.cybertech.pd.sensor.handler.mongodb;

import io.cybertech.pd.sensor.handler.HeatResultHandler;
import io.cybertech.pd.sensor.model.HeatResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("mongodb")
@Slf4j
public class MongoDbPersistorHeatResultHandler implements HeatResultHandler {
    @Autowired
    HeatResultRepository heatResultsRepository;

    @Override
    public void handleHeatResult(HeatResult results) {
        heatResultsRepository.save(results);
        log.debug("Heat results '{}' saved!", results.getId());
    }
}
