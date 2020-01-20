package io.cybertech.pd.sensor.handler;

import io.cybertech.pd.sensor.model.HeatResult;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class SimpleHeatResultHandler implements HeatResultHandler {

    /**
     * {@inheritDoc}
     *
     * This implementation simply logs the heat result to the logging framework.
     */
    @Override
    public void handleHeatResult(HeatResult results) {
        log.info(results.toString());
    }
}
