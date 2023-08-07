package br.com.gubee.interview.core.service;

import java.util.UUID;

import br.com.gubee.interview.core.ports.DeleteHeroServicePort;
import br.com.gubee.interview.core.ports.HeroRepositoryPort;
import br.com.gubee.interview.core.ports.PowerStatsRepositoryPort;

public class DeleteHeroService implements DeleteHeroServicePort {

	private HeroRepositoryPort heroRepository;
	
	private PowerStatsRepositoryPort powerStatsRepository;
	
	public DeleteHeroService(HeroRepositoryPort heroRepository, PowerStatsRepositoryPort powerStatsRepository) {
		this.heroRepository = heroRepository;
		this.powerStatsRepository = powerStatsRepository;
	}

	@Override
	public void deleteHero(UUID id) {
		heroRepository.delete(id);
		powerStatsRepository.delete(id);
	}
	
	public void execute(UUID id) {
		deleteHero(id);
	}
}
