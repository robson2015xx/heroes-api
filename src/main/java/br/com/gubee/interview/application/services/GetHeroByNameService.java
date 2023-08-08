package br.com.gubee.interview.application.services;

import java.util.List;

import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.ports.HeroRepositoryPort;

public class GetHeroByNameService  {

	private HeroRepositoryPort heroRepository;

	public GetHeroByNameService(HeroRepositoryPort heroRepository) {
		this.heroRepository = heroRepository;
	}

	private List<Hero> getHeroByName(String name) {
		return heroRepository.findByNameLike(name);
	}
	
	public List<Hero> execute(String name) {
		return getHeroByName(name);
	}
}
