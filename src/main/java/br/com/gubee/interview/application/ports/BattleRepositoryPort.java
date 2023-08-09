package br.com.gubee.interview.application.ports;

import java.util.List;
import java.util.UUID;

import br.com.gubee.interview.application.domain.Battle;

public interface BattleRepositoryPort {

	Battle save(Battle battle);
	
	void deleteByHeroId(UUID id);
	
	List<Battle> findByHeroId(UUID id);
}
