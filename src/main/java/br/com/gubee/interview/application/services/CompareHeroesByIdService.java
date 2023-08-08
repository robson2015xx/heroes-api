package br.com.gubee.interview.application.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.ports.HeroRepositoryPort;

public class CompareHeroesByIdService {
	
	private HeroRepositoryPort heroRepository;

	public CompareHeroesByIdService(HeroRepositoryPort heroRepository) {
		this.heroRepository = heroRepository;
	}
	
	private Map<String, Object> compareHeroesById(UUID heroOneId, UUID heroTwoId) {
		Hero heroOne = heroRepository.findById(heroOneId).orElseThrow(() -> new RuntimeException("Invalid Hero"));
		Hero heroTwo = heroRepository.findById(heroTwoId).orElseThrow(() -> new RuntimeException("Invalid Hero"));
		int heroOneTotalPower = heroOne.getTotalPower();
		int heroTwoTotalPower = heroTwo.getTotalPower();
		Map<String, Object> response = new HashMap<>();
		response.put("1- " + heroOne.getName() + "(" + heroOne.getId().toString() + ")", heroOne.getStats());
		response.put("2- " + heroTwo.getName() + "(" + heroTwo.getId().toString() + ")", heroTwo.getStats());
		response.put("Result", heroOneTotalPower > heroTwoTotalPower ? "Hero one venceu" : "Hero two venceu");
		return response;
	}
	
	public Map<String, Object> execute(UUID heroOneId, UUID heroTwoId) {
		return compareHeroesById(heroOneId, heroTwoId);
	}
}
