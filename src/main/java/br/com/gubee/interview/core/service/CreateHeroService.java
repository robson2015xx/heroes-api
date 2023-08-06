package br.com.gubee.interview.core.service;

import br.com.gubee.interview.core.domain.Hero;
import br.com.gubee.interview.core.ports.HeroRepositoryPort;
import br.com.gubee.interview.core.ports.HeroServicePort;
import br.com.gubee.interview.core.ports.PowerStatsRepositoryPort;

public class CreateHeroService implements HeroServicePort {

	private HeroRepositoryPort heroRepository;
	
	private PowerStatsRepositoryPort powerStatsRepository;
	
	public CreateHeroService(HeroRepositoryPort heroRepository, PowerStatsRepositoryPort powerStatsRepository) {
		this.heroRepository = heroRepository;
		this.powerStatsRepository = powerStatsRepository;
	}

	@Override
	public Hero createHero(Hero hero) {
		powerStatsRepository.save(hero.getStats());
		return heroRepository.save(hero);
	}
	
	public Hero execute(Hero hero) {
		return createHero(hero);
	}
}
