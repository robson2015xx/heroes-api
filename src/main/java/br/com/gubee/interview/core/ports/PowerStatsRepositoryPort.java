package br.com.gubee.interview.core.ports;

import br.com.gubee.interview.core.domain.PowerStats;

public interface PowerStatsRepositoryPort {

	PowerStats save(PowerStats stats);
}
