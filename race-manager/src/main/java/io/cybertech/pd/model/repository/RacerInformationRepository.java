package io.cybertech.pd.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.cybertech.pd.model.RacerInformation;

public interface RacerInformationRepository extends MongoRepository<RacerInformation, Long>{

}
