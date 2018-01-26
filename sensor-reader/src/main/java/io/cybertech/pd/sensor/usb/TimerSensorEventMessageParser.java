package io.cybertech.pd.sensor.usb;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import io.cybertech.pd.sensor.model.HeatResults;
import io.cybertech.pd.sensor.model.LaneResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class TimeSensorEventMessageParser {

	// Sample result from the serial port is the following buffer:   1=1.5871! 3=2.4534" 2=2.8777#
	private static final String HEAT_RESULT_REGEX = "(.*)=(.*!?) (.*)=(.*\"?) (.*)=(.*#?)";
	private static Pattern HEAT_RESULT_PATTERN = Pattern.compile(HEAT_RESULT_REGEX);

	public static HeatResults buildResult(String result) {
		Matcher matcher = HEAT_RESULT_PATTERN.matcher(result);
		if (matcher.matches()) {
			log.debug("Building heat results from '{}'", result);
			String firstPlaceLaneNumber = matcher.group(1);
			String firstPlaceTime = matcher.group(2);
			if (StringUtils.endsWith(firstPlaceTime, "!")) {
				firstPlaceTime = StringUtils.removeEnd(firstPlaceTime, "!");
			}
			
			LaneResult firstPlace = LaneResult.builder()
											  .laneNumber(firstPlaceLaneNumber)
											  .time(Double.parseDouble(firstPlaceTime))
											  .build();

			String secondPlaceLaneNumber = matcher.group(3);
			String secondPlaceTime = matcher.group(4);
			if (StringUtils.endsWith(secondPlaceTime, "\"")) {
				secondPlaceTime = StringUtils.removeEnd(secondPlaceTime, "\"");
			}
			LaneResult secondPlace = LaneResult.builder()
											   .laneNumber(secondPlaceLaneNumber)
											   .time(Double.parseDouble(secondPlaceTime))
											   .build();

			String thirdPlaceLaneNumber = matcher.group(5);
			String thirdPlaceTime = matcher.group(6);
			if (StringUtils.endsWith(thirdPlaceTime, "#")) {
				thirdPlaceTime = StringUtils.removeEnd(thirdPlaceTime, "#");
			}
			LaneResult thirdPlace = LaneResult.builder()
											  .laneNumber(thirdPlaceLaneNumber)
											  .time(Double.parseDouble(thirdPlaceTime))
											  .build();

			return HeatResults.builder().firstPlace(firstPlace).secondPlace(secondPlace).thirdPlace(thirdPlace).build();

		} else {
			log.warn("Unable to parse heat results '{}'", result);
		}
		return null;
	}
}
