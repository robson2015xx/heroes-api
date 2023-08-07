package br.com.gubee.interview.core.ports;

import java.util.List;

import br.com.gubee.interview.core.domain.Hero;

public interface GetHeroByNameServicePort {
	
	List<Hero> getHeroByName(String name);
}
