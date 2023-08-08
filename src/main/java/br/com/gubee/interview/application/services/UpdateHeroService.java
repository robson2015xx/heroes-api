package br.com.gubee.interview.application.services;

import java.util.UUID;

import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.ports.HeroRepositoryPort;
import br.com.gubee.interview.application.ports.PowerStatsRepositoryPort;

public class UpdateHeroService  {

	private HeroRepositoryPort heroRepository;
	
	private PowerStatsRepositoryPort powerStatsRepository;
	
	public UpdateHeroService(HeroRepositoryPort heroRepository, PowerStatsRepositoryPort powerStatsRepository) {
		this.heroRepository = heroRepository;
		this.powerStatsRepository = powerStatsRepository;
	}
	
	public Hero execute(UUID id, Hero hero) {
		return updateHero(id, hero);
	}

	private Hero updateHero(UUID id, Hero hero) {
		Hero actualHero = heroRepository.findById(id).orElseThrow(() -> new RuntimeException("Heroi não encontrado"));
		hero.setId(id);
		hero.setStatsId(actualHero.getStatsId());
		powerStatsRepository.update(hero.getStats());
		heroRepository.update(hero);
		return heroRepository.findById(id).get();
	}
}
