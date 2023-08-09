package br.com.gubee.interview.application.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import br.com.gubee.interview.application.business.ErrorMessagesConstants;
import br.com.gubee.interview.application.business.inbound.BattleDTO;
import br.com.gubee.interview.application.business.inbound.PowerStatsDTO;
import br.com.gubee.interview.application.business.outbound.BattleResponse;
import br.com.gubee.interview.application.business.outbound.StatsCompareResponse;
import br.com.gubee.interview.application.domain.Battle;
import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.exceptions.BusinessValidationException;
import br.com.gubee.interview.application.ports.BattleRepositoryPort;
import br.com.gubee.interview.application.ports.HeroRepositoryPort;

public class CreateBattleByHeroesIdService {
	
	private HeroRepositoryPort heroRepository;
	private BattleRepositoryPort battleRepository;

	public CreateBattleByHeroesIdService(HeroRepositoryPort heroRepository, BattleRepositoryPort battleRepository) {
		this.heroRepository = heroRepository;
		this.battleRepository = battleRepository;
	}
	
	private Map<String, Object> battleByHeroesId(UUID heroOneId, UUID heroTwoId) {
		Hero heroOne = heroRepository.findById(heroOneId).orElseThrow(() 
				-> new BusinessValidationException(ErrorMessagesConstants.INVALID_HERO_ID, heroOneId.toString(), 404));
		Hero heroTwo = heroRepository.findById(heroTwoId).orElseThrow(() 
				-> new BusinessValidationException(ErrorMessagesConstants.INVALID_HERO_ID, heroTwoId.toString(), 404));
		
		Battle battle = new Battle(heroOne, heroTwo);
		
		Hero heroLose = null;
		Hero heroWin = null;
		if (battle.getHeroWinId().equals(heroOneId)) {
			heroWin = heroOne;
			heroLose = heroTwo;
		} else {
			heroWin = heroTwo;
			heroLose = heroOne;
		}

		battleRepository.save(battle);
		
		return buildResponse(heroLose, heroWin);
	}
	
	private Map<String, Object> buildResponse(Hero heroLose, Hero heroWin) {
		Map<String, Object> response = new HashMap<>();
		StatsCompareResponse compareResponse = new StatsCompareResponse(new PowerStatsDTO(heroLose.getStats()), new PowerStatsDTO(heroWin.getStats()));
		response.put(heroLose.getName() + "(" + heroLose.getId().toString() + ")", compareResponse.getStatsLose());
		response.put(heroWin.getName() + "(" + heroWin.getId().toString() + ")", compareResponse.getStatsWin());
		
		response.put("Result", new BattleResponse(heroLose.getName(), heroWin.getName(), heroLose.getTotalWins(), heroWin.getTotalWins() + 1));
		return response;
	}

	public Map<String, Object> execute(BattleDTO battleDTO) {
		return battleByHeroesId(battleDTO.getHeroOneId(), battleDTO.getHeroTwoId());
	}
}
