package br.com.gubee.interview.application.services;

import java.util.UUID;

import br.com.gubee.interview.application.ports.HeroRepositoryPort;
import br.com.gubee.interview.application.ports.PowerStatsRepositoryPort;

public class DeleteHeroService  {

	private HeroRepositoryPort heroRepository;
	
	private PowerStatsRepositoryPort powerStatsRepository;
	
	public DeleteHeroService(HeroRepositoryPort heroRepository, PowerStatsRepositoryPort powerStatsRepository) {
		this.heroRepository = heroRepository;
		this.powerStatsRepository = powerStatsRepository;
	}

	private void deleteHero(UUID id) {
		heroRepository.delete(id);
		powerStatsRepository.delete(id);
	}
	
	public void execute(UUID id) {
		deleteHero(id);
	}
}
