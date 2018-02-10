package io.cybertech.pd.sensor.handler.mongodb;


import io.cybertech.pd.sensor.model.HeatResult;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HeatResultRepository extends MongoRepository<HeatResult, String> {

    HeatResult findTopByOrderByTimestampDesc(); // findFirstByOrOrderByTimestampDesc();
}
