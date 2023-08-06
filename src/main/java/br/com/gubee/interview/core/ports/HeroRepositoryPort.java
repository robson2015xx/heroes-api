package br.com.gubee.interview.core.ports;

import br.com.gubee.interview.core.domain.Hero;

public interface HeroRepositoryPort {

	Hero save(Hero hero);
}
