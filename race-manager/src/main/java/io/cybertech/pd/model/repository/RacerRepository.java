package io.cybertech.pd.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.cybertech.pd.model.Racer;

public interface RacerRepository extends MongoRepository<Racer, Long>{

}
