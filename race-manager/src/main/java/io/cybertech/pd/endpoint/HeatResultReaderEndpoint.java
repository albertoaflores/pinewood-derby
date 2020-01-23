package io.cybertech.pd.endpoint;

import com.google.common.collect.Lists;
import io.cybertech.pd.sensor.model.HeatResult;
import io.cybertech.pd.sensor.model.parser.HeatResultParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/heat-results")
@Slf4j
public class HeatResultReaderEndpoint {
    private List<String> mockResults = Lists.newArrayList("1=1.4096! 2=2.0929\" 3=2.7618#",
            "3=1.8457! 1=3.0306\" 2=3.5478#",
            "3=4.1837! 2=4.3242\" 1=4.5176#",
            "2=0.8806! 1=1.4680\" 3=2.1106#",
            "2=0.6265! 1=1.1862\" 3=2.2561#");
    private int lastResult = 0;


    @GetMapping(path = "/recent", produces = "application/json")
    public HeatResult getRecentReading() {
        String result = mockResults.get(lastResult);
        log.info("<<<<<<<<<<<  Mock Result >>>>>>>>>>");
        log.info("Raw: {}", result);

        HeatResult heatResults = HeatResultParser.buildResultFromThreeLaneEvent(result);

        // update mock counter
        lastResult++;
        if (lastResult == mockResults.size()) {
            lastResult = 0;
        }

        return heatResults;
    }
}
