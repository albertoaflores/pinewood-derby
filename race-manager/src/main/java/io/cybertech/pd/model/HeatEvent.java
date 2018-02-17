package io.cybertech.pd.model;

import io.cybertech.pd.model.dto.external.HeatResult;
import io.cybertech.pd.model.entity.Racer;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * A heat event reflects a specific heat run for the 3 racers. It also contains the result of this event.
 */
@Data
public class HeatEvent {
    @Id
	private String id;
	private Racer lane1;
	private Racer lane2;
	private Racer lane3;
	private HeatResult result;


}
