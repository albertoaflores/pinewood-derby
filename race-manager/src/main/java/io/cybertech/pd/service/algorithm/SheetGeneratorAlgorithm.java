package io.cybertech.pd.service.algorithm;

import java.util.Collection;
import java.util.List;

import io.cybertech.pd.model.HeatEvent;
import io.cybertech.pd.model.entity.Racer;

/**
 * Algorithm contract to provide the list of events from a collection of racers.
 * 
 * <p>Potential implementations include:
 * <ul>
 *   <li>Naive: just rotate across registered racers. Some may race in consecutive races (not optimal)
 *   <li>Perfect-N: Each car runs the same number of times in each lane and against each other
 * </ul> 
 * 
 * @see http://www.stanpope.net/pwraces.html#d
 */
public interface SheetGeneratorAlgorithm {

	List<HeatEvent> generateEvents(Collection<Racer> racers, int numberOfRunsPerRacer);
}
