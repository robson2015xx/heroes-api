package br.com.gubee.interview.core.ports;

import java.util.UUID;

import br.com.gubee.interview.core.domain.PowerStats;

public interface PowerStatsRepositoryPort {

	PowerStats save(PowerStats stats);
	
	void delete(UUID id);
}
