package io.cybertech.pd.sensor.usb;

import static org.junit.Assert.*;

import com.google.common.primitives.Doubles;
import io.cybertech.pd.sensor.model.HeatRank;
import io.cybertech.pd.sensor.model.HeatResult;
import io.cybertech.pd.sensor.model.LaneResult;
import io.cybertech.pd.sensor.model.parser.HeatResultParser;
import org.junit.Test;

public class HeatResultParserTest {

    @Test
    public void testThreeLaneBufferParsing() {
        String buffer = "3=4.1837! 2=4.3242\" 1=4.5176#";
        HeatResult results = HeatResultParser.buildResultFromThreeLaneEvent(buffer);

        assertNotNull("Results should not have been null!", results);

        assertLaneResult(results.getLane1(), "4.5176", HeatRank.THIRD_PLACE);
        assertLaneResult(results.getLane2(), "4.3242", HeatRank.SECOND_PLACE);
        assertLaneResult(results.getLane3(), "4.1837", HeatRank.FIRST_PLACE);

    }

    private void assertLaneResult(LaneResult laneResult, String expectedTime, HeatRank expectedRank) {
        assertEquals(Doubles.tryParse(expectedTime), laneResult.getTime());
        assertEquals(expectedRank, laneResult.getRank());
    }
}
