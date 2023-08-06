package br.com.gubee.interview.adapters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gubee.interview.adapters.repository.HeroRepository;
import br.com.gubee.interview.adapters.repository.PowerStatsRepository;
import br.com.gubee.interview.core.domain.Hero;
import br.com.gubee.interview.core.service.CreateHeroService;

@Controller
@RequestMapping(path = "/hero")
public class HeroController {
	
	@Autowired private HeroRepository heroRepository;
	
	@Autowired private PowerStatsRepository powerStatsRepository;
	
	@PostMapping("/")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Hero> createHero(@RequestBody Hero hero) {
		CreateHeroService createHeroService = new CreateHeroService(heroRepository, powerStatsRepository);
		return ResponseEntity.ok().body(createHeroService.execute(hero));
	}
}
