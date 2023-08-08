package br.com.gubee.interview.application.ports;

import java.util.UUID;

import br.com.gubee.interview.application.domain.PowerStats;

public interface PowerStatsRepositoryPort {

	PowerStats save(PowerStats stats);
	
	void delete(UUID id);
	
	PowerStats update(PowerStats stats);
}
