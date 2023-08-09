package br.com.gubee.interview.application.services;

import java.util.UUID;

import br.com.gubee.interview.application.business.ErrorMessagesConstants;
import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.exceptions.BusinessValidationException;
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
		Hero actualHero = heroRepository.findById(id).orElseThrow(() 
				-> new BusinessValidationException(ErrorMessagesConstants.INVALID_HERO_ID, id.toString()));
		hero = actualHero.updateOnlyMappedFields(hero);
		powerStatsRepository.update(hero.getStats());
		heroRepository.update(hero);
		return hero;
	}
}
