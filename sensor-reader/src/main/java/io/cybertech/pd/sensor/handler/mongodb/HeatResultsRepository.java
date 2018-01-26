package io.cybertech.pd.sensor.handler.mongodb;


import io.cybertech.pd.sensor.model.HeatResults;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HeatResultsRepository extends MongoRepository<HeatResults, String> {

}
