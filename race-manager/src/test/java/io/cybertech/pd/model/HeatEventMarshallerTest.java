package io.cybertech.pd.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cybertech.pd.model.entity.Racer;
import io.cybertech.pd.sensor.model.HeatRank;
import io.cybertech.pd.sensor.model.LaneResult;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HeatEventMarshallerTest {

    @Test
    public void foo() throws Exception {
        List<LaneResult> results = new ArrayList<>();
        results.add(LaneResult.builder().laneNumber(1).rank(HeatRank.SECOND_PLACE).time(2.3456).build());
        results.add(LaneResult.builder().laneNumber(3).rank(HeatRank.FIRST_PLACE).time(1.0441).build());
        results.add(LaneResult.builder().laneNumber(2).rank(HeatRank.THIRD_PLACE).time(2.5431).build());

        Racer racer = Racer.builder().name("Christian").results(results).uuid(UUID.randomUUID().toString()).build();

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(racer));
    }
}
