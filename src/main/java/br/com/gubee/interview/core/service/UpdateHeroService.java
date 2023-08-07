package br.com.gubee.interview.core.service;

import java.util.UUID;

import br.com.gubee.interview.core.domain.Hero;
import br.com.gubee.interview.core.ports.HeroRepositoryPort;
import br.com.gubee.interview.core.ports.PowerStatsRepositoryPort;
import br.com.gubee.interview.core.ports.UpdateHeroServicePort;

public class UpdateHeroService implements UpdateHeroServicePort {

	private HeroRepositoryPort heroRepository;
	
	private PowerStatsRepositoryPort powerStatsRepository;
	
	public UpdateHeroService(HeroRepositoryPort heroRepository, PowerStatsRepositoryPort powerStatsRepository) {
		this.heroRepository = heroRepository;
		this.powerStatsRepository = powerStatsRepository;
	}
	
	public Hero execute(UUID id, Hero hero) {
		return updateHero(id, hero);
	}

	@Override
	public Hero updateHero(UUID id, Hero hero) {
		Hero actualHero = heroRepository.findById(id).orElseThrow(() -> new RuntimeException("Heroi não encontrado"));
		hero.setId(id);
		hero.setStatsId(actualHero.getStatsId());
		powerStatsRepository.update(hero.getStats());
		heroRepository.update(hero);
		return heroRepository.findById(id).get();
	}
}
