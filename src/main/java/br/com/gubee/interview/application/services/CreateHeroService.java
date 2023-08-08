package br.com.gubee.interview.application.services;

import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.ports.HeroRepositoryPort;
import br.com.gubee.interview.application.ports.PowerStatsRepositoryPort;

public class CreateHeroService  {

	private HeroRepositoryPort heroRepository;
	
	private PowerStatsRepositoryPort powerStatsRepository;
	
	public CreateHeroService(HeroRepositoryPort heroRepository, PowerStatsRepositoryPort powerStatsRepository) {
		this.heroRepository = heroRepository;
		this.powerStatsRepository = powerStatsRepository;
	}

	private Hero createHero(Hero hero) {
		powerStatsRepository.save(hero.getStats());
		return heroRepository.save(hero);
	}
	
	public Hero execute(Hero hero) {
		return createHero(hero);
	}
}
