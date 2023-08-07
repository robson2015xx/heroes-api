package br.com.gubee.interview.core.service;

import java.util.List;

import br.com.gubee.interview.core.domain.Hero;
import br.com.gubee.interview.core.ports.GetHeroByNameServicePort;
import br.com.gubee.interview.core.ports.HeroRepositoryPort;

public class GetHeroByNameService implements GetHeroByNameServicePort {

	private HeroRepositoryPort heroRepository;

	public GetHeroByNameService(HeroRepositoryPort heroRepository) {
		this.heroRepository = heroRepository;
	}

	@Override
	public List<Hero> getHeroByName(String name) {
		return heroRepository.findByNameLike(name);
	}
	
	public List<Hero> execute(String name) {
		return getHeroByName(name);
	}
}
