package io.cybertech.pd.sensor.handler;

import io.cybertech.pd.sensor.model.HeatResults;

/**
 * Handler of {@link HeatResults} model objects. Typically, these are built from a buffer.
 */
public interface HeatResultHandler {

    /**
     * Handles the model object (argument).
     */
    void handleHeatResult(HeatResults results);
}
