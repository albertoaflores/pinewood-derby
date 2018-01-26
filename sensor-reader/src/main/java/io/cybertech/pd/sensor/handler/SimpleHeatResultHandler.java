package io.cybertech.pd.sensor.handler;

import io.cybertech.pd.sensor.model.HeatResults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("simple")
@Slf4j
public class SimpleHeatResultHandler implements HeatResultHandler {

    /**
     * {@inheritDoc}
     *
     * This implementation simply logs the heat result to the logging framework.
     */
    @Override
    public void handleHeatResult(HeatResults results) {
        log.info(results.toString());
    }
}
