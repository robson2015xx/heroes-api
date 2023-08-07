package br.com.gubee.interview.adapters.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.gubee.interview.adapters.repository.HeroRepository;
import br.com.gubee.interview.adapters.repository.PowerStatsRepository;
import br.com.gubee.interview.core.domain.Hero;
import br.com.gubee.interview.core.service.CreateHeroService;
import br.com.gubee.interview.core.service.DeleteHeroService;
import br.com.gubee.interview.core.service.GetHeroByIdService;
import br.com.gubee.interview.core.service.GetHeroByNameService;
import br.com.gubee.interview.core.service.UpdateHeroService;

@Controller
@RequestMapping(path = "/heroes")
public class HeroController {

	@Autowired
	private HeroRepository heroRepository;

	@Autowired
	private PowerStatsRepository powerStatsRepository;

	@PostMapping("/")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Hero> createHero(@RequestBody Hero hero) {
		CreateHeroService createHeroService = new CreateHeroService(heroRepository, powerStatsRepository);
		return ResponseEntity.ok().body(createHeroService.execute(hero));
	}

	@GetMapping
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> getHeroByName(@RequestParam String name) {
		GetHeroByNameService getHeroByNameService = new GetHeroByNameService(heroRepository);
		List<Hero> heroes = getHeroByNameService.execute(name);
		return ResponseEntity.ok().body(heroes.isEmpty() ? null : heroes);
	}

	@DeleteMapping("/{id}")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<String> deleteHero(@PathVariable("id") UUID id) {
		DeleteHeroService deleteHeroService = new DeleteHeroService(heroRepository, powerStatsRepository);
		deleteHeroService.execute(id);
		return ResponseEntity.ok().body("OK");
	}
	
	@PutMapping("/{id}")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Hero> updateHero(@PathVariable("id") UUID id, @RequestBody Hero hero) {
		UpdateHeroService updateHeroService = new UpdateHeroService(heroRepository, powerStatsRepository);
		return ResponseEntity.ok().body(updateHeroService.execute(id, hero));
	}

	@GetMapping("/{id}")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Hero> getHeroById(@PathVariable("id") UUID id) {
		GetHeroByIdService getHeroByIdService = new GetHeroByIdService(heroRepository);
		return ResponseEntity.ok().body(getHeroByIdService.execute(id));
	}
}
