package br.com.gubee.interview.core.ports;

import br.com.gubee.interview.core.domain.Hero;

public interface CreateHeroServicePort {
	
	Hero createHero(Hero hero);
}
