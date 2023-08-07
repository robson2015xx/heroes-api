package br.com.gubee.interview.core.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.gubee.interview.core.domain.Hero;

public interface HeroRepositoryPort {

	Hero save(Hero hero);
	
	void delete(UUID id);
	
	Optional<Hero> findById(UUID id);
	
	List<Hero> findByNameLike(String name);
	
	Hero update(Hero hero);
}
