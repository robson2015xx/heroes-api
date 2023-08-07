package br.com.gubee.interview.core.ports;

import java.util.Optional;
import java.util.UUID;

import br.com.gubee.interview.core.domain.Hero;

public interface HeroRepositoryPort {

	Hero save(Hero hero);
	
	void delete(UUID id);
	
	Optional<Hero> findById(UUID id);
}
