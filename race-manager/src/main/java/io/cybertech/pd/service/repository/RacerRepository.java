package io.cybertech.pd.service.repository;

import io.cybertech.pd.model.entity.Racer;

import java.util.List;

public interface RacerRepository {

    List<Racer> getAllRacers();

    void createRacer(Racer racer);

    void updateRacer(String uuid, Racer racer);

    void deleteRacer(String uuid);
}
