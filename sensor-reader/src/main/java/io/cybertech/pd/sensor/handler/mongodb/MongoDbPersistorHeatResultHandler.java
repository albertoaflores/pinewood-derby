package io.cybertech.pd.sensor.handler;

import io.cybertech.pd.sensor.model.HeatResults;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("mongodb")
public class MongoDbPersistorHeatResultHandler implements HeatResultHandler {


    @Override
    public void handleHeatResult(HeatResults results) {

    }
}
