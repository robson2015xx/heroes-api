package br.com.gubee.interview.application.services;

import java.util.List;
import java.util.UUID;

import br.com.gubee.interview.application.domain.Battle;
import br.com.gubee.interview.application.ports.BattleRepositoryPort;

public class GetBattleHistoryByHeroIdService {
	
	private BattleRepositoryPort battleRepository;

	public GetBattleHistoryByHeroIdService(BattleRepositoryPort battleRepository) {
		this.battleRepository = battleRepository;
	}
	
	private List<Battle> battleByHeroesId(UUID heroId) {
		
		return battleRepository.findByHeroId(heroId);
	}

	public List<Battle> execute(UUID heroId) {
		return battleByHeroesId(heroId);
	}
}
