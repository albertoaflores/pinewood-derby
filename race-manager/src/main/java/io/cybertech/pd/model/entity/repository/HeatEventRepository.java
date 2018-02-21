package io.cybertech.pd.model.entity.repository;

import io.cybertech.pd.model.entity.HeatEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeatEventRepository extends JpaRepository<HeatEvent, Long> {
}
