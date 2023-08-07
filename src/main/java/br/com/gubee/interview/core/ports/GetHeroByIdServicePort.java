package br.com.gubee.interview.core.ports;

import java.util.UUID;

import br.com.gubee.interview.core.domain.Hero;

public interface GetHeroByIdServicePort {
	
	Hero getHeroById(UUID id);
}
