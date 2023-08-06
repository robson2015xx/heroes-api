package br.com.gubee.interview.core.ports;

import br.com.gubee.interview.core.domain.Hero;

public interface HeroServicePort {
	
	Hero createHero(Hero hero);
}
