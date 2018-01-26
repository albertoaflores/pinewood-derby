package io.cybertech.pd.sensor.usb;

import java.util.List;

import com.google.common.collect.Lists;
import io.cybertech.pd.sensor.handler.HeatResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.cybertech.pd.sensor.model.HeatResults;
import lombok.extern.slf4j.Slf4j;

/**
 *
 *  2=1.4096! 1=2.0929" 3=2.7618#
 *  3=1.8457! 1=3.0306" 2=3.5478#
 *  3=4.1837! 2=4.3242" 1=4.5176#
 *  2=0.8806! 1=1.4680" 3=2.1106#
 *  2=0.6265! 1=1.1862" 3=2.2561#
 */
@Component
@Slf4j
@Profile({"mock"})
public class MockSerialPortScanner {
    @Autowired private HeatResultHandler heatResultHandler;
	private List<String> mockResults = Lists.newArrayList("2=1.4096! 1=2.0929\" 3=2.7618#",
											"3=1.8457! 1=3.0306\" 2=3.5478#",
											"3=4.1837! 2=4.3242\" 1=4.5176#",
											"2=0.8806! 1=1.4680\" 3=2.1106#",
											"2=0.6265! 1=1.1862\" 3=2.2561#");
	private int lastResult = 0;
	
	@Scheduled(fixedRate=15000, initialDelay = 5000)
	public void fakeRun() {
	    // get mock result
		String result = mockResults.get(lastResult);
        log.debug("<<<<<<<<<<<  Mock Result >>>>>>>>>>");
        log.debug("Raw: {}", result);

        // build and handle heat results
        HeatResults heatResults = TimerSensorEventMessageParser.buildResultFromThreeLaneEvent(result);
		heatResultHandler.handleHeatResult(heatResults);

		// update mock counter
		lastResult++;
		if (lastResult == mockResults.size()) {
			lastResult = 0;
		}
	}
}
