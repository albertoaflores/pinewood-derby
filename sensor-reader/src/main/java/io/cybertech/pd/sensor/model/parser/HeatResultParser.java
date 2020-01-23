package io.cybertech.pd.sensor.model.parser;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.cybertech.pd.sensor.model.HeatRank;
import io.cybertech.pd.sensor.model.HeatResult;
import org.apache.commons.lang3.StringUtils;

import io.cybertech.pd.sensor.model.LaneResult;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility class to parse messages received by a timer sensor. Example of these types of messages are
 * as follow:
 *
 * <pre>
 *     1=1.5871! 3=2.4534" 2=2.8777#
 *     3=4.1837! 2=4.3242" 1=4.5176#
 *     2=0.6265! 1=1.1862" 3=2.2561#
 * </pre>
 *
 * Each lane has a metric of it's own time measurement. These lanes are described in Key-Value pairs (K,V)
 * where the key is the lane number and the value is the detected elapsed time. The result is ordered by
 * value, hence the first (K,V) tuple is the first place, and so on.
 */
@Slf4j
public abstract class HeatResultParser {

	// Sample result from the serial port is the following buffer:   1=1.5871! 3=2.4534" 2=2.8777#
	private static final String HEAT_RESULT_REGEX = "(.*)=(.*!?) (.*)=(.*\"?) (.*)=(.*#?)";
	private static final Pattern HEAT_RESULT_PATTERN = Pattern.compile(HEAT_RESULT_REGEX);

	/**
     * Builds a {@link HeatResult} model object from a string buffer containing the results of
     * a heat with 3 lanes.
     * @param result the buffer containing the result of a heat. The format (regex) is described in {@link #HEAT_RESULT_REGEX}
     */
	public static HeatResult buildResultFromThreeLaneEvent(String result) {
        // TODO: improve regex so that we simply get groups for each lane
        Matcher matcher = HEAT_RESULT_PATTERN.matcher(result);
		if (matcher.matches()) {
			log.debug("Building heat results from '{}'", result);

			Map<String, LaneResult> laneReport = new HashMap<>();

            // extract first place from buffer
            String firstPlaceLaneNumber = matcher.group(1);
			String firstPlaceTime = matcher.group(2);
			if (StringUtils.endsWith(firstPlaceTime, "!")) {
				firstPlaceTime = StringUtils.removeEnd(firstPlaceTime, "!");
			}
			laneReport.put(firstPlaceLaneNumber, LaneResult.builder()
																.time(Double.parseDouble(firstPlaceTime))
																.rank(HeatRank.FIRST_PLACE)
                                                                .laneNumber(Integer.parseInt(firstPlaceLaneNumber))
															.build());

			// extract second place from buffer
			String secondPlaceLaneNumber = matcher.group(3);
			String secondPlaceTime = matcher.group(4);
			if (StringUtils.endsWith(secondPlaceTime, "\"")) {
				secondPlaceTime = StringUtils.removeEnd(secondPlaceTime, "\"");
			}
			laneReport.put(secondPlaceLaneNumber, LaneResult.builder()
																.time(Double.parseDouble(secondPlaceTime))
																.rank(HeatRank.SECOND_PLACE)
                                                                .laneNumber(Integer.parseInt(secondPlaceLaneNumber))
															.build());

            // extract third place from buffer
			String thirdPlaceLaneNumber = matcher.group(5);
			String thirdPlaceTime = matcher.group(6);
			if (StringUtils.endsWith(thirdPlaceTime, "#")) {
				thirdPlaceTime = StringUtils.removeEnd(thirdPlaceTime, "#");
			}
			laneReport.put(thirdPlaceLaneNumber, LaneResult.builder()
																.time(Double.parseDouble(thirdPlaceTime))
																.rank(HeatRank.THIRD_PLACE)
                                                                .laneNumber(Integer.parseInt(thirdPlaceLaneNumber))
															.build());

			// return result model object
			return HeatResult.builder()
                                .uuid(UUID.randomUUID().toString())
								.lane1(laneReport.get("1"))
								.lane2(laneReport.get("2"))
								.lane3(laneReport.get("3"))
                                .timestamp(new Date())
                              .build();
		} else {
		    // TODO: Need to throw some flag here!
			log.warn("Unable to parse heat results '{}'", result);
		}
		return null;
	}
}
