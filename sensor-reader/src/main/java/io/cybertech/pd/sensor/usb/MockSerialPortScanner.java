package io.cybertech.pd.sensor.usb;

import java.util.List;

import com.google.common.collect.Lists;
import io.cybertech.pd.sensor.handler.HeatResultHandler;
import io.cybertech.pd.sensor.model.HeatResult;
import io.cybertech.pd.sensor.model.parser.HeatResultParser;

import lombok.extern.slf4j.Slf4j;

/**
 *
 *  2=1.4096! 1=2.0929" 3=2.7618#
 *  3=1.8457! 1=3.0306" 2=3.5478#
 *  3=4.1837! 2=4.3242" 1=4.5176#
 *  2=0.8806! 1=1.4680" 3=2.1106#
 *  2=0.6265! 1=1.1862" 3=2.2561#
 */
@Slf4j
public class MockSerialPortScanner {
    private HeatResultHandler heatResultHandler;
	private List<String> mockResults = Lists.newArrayList("2=1.4096! 1=2.0929\" 3=2.7618#",
											"3=1.8457! 1=3.0306\" 2=3.5478#",
											"3=4.1837! 2=4.3242\" 1=4.5176#",
											"2=0.8806! 1=1.4680\" 3=2.1106#",
											"2=0.6265! 1=1.1862\" 3=2.2561#");
	private int lastResult = 0;

	public MockSerialPortScanner(HeatResultHandler heatResultHandler) {
		this.heatResultHandler = heatResultHandler;
	}

	public void fakeHeatResultEvent() {
	    // get mock result
		String result = mockResults.get(lastResult);
        log.debug("<<<<<<<<<<<  Mock Result >>>>>>>>>>");
        log.debug("Raw: {}", result);

        // build and handle heat results
        HeatResult heatResults = HeatResultParser.buildResultFromThreeLaneEvent(result);
		heatResultHandler.handleHeatResult(heatResults);

		// update mock counter
		lastResult++;
		if (lastResult == mockResults.size()) {
			lastResult = 0;
		}
	}
}
