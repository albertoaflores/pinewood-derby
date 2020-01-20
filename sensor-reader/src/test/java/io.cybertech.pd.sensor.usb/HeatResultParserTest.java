package io.cybertech.pd.sensor.usb;

import static org.junit.Assert.*;

import com.google.common.primitives.Doubles;
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

        LaneResult firstPlace = results.getFirstPlace();
        assertEquals("3", firstPlace.getLaneNumber());
        assertEquals(Doubles.tryParse("4.1837"), firstPlace.getTime());

        LaneResult secondPlace = results.getSecondPlace();
        assertEquals("2", secondPlace.getLaneNumber());
        assertEquals(Doubles.tryParse("4.3242"), secondPlace.getTime());

        LaneResult thirdPlace = results.getThirdPlace();
        assertEquals("1", thirdPlace.getLaneNumber());
        assertEquals(Doubles.tryParse("4.5176"), thirdPlace.getTime());

    }
}
