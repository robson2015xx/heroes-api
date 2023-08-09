package br.com.gubee.interview.application.services;

import java.util.UUID;

import br.com.gubee.interview.application.business.ErrorMessagesConstants;
import br.com.gubee.interview.application.business.outbound.DeleteHeroResponse;
import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.exceptions.BusinessValidationException;
import br.com.gubee.interview.application.ports.BattleRepositoryPort;
import br.com.gubee.interview.application.ports.HeroRepositoryPort;
import br.com.gubee.interview.application.ports.PowerStatsRepositoryPort;

public class DeleteHeroService  {

	private HeroRepositoryPort heroRepository;
	
	private PowerStatsRepositoryPort powerStatsRepository;
	
	private BattleRepositoryPort battleRepository;
	
	public DeleteHeroService(HeroRepositoryPort heroRepository, PowerStatsRepositoryPort powerStatsRepository, BattleRepositoryPort battleRepository) {
		this.heroRepository = heroRepository;
		this.powerStatsRepository = powerStatsRepository;
		this.battleRepository = battleRepository;
	}

	private DeleteHeroResponse deleteHero(UUID id) {
		Hero hero = heroRepository.findById(id).orElseThrow(() 
				-> new BusinessValidationException(ErrorMessagesConstants.INVALID_HERO_ID, id.toString(), 404));
		
		
		powerStatsRepository.delete(hero.getStatsId());
		battleRepository.deleteByHeroId(id);
		heroRepository.delete(id);
		
		return new DeleteHeroResponse();
	}
	
	public DeleteHeroResponse execute(UUID id) {
		return deleteHero(id);
	}
}
