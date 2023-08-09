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

import br.com.gubee.interview.adapters.repository.BattleRepository;
import br.com.gubee.interview.adapters.repository.HeroRepository;
import br.com.gubee.interview.adapters.repository.PowerStatsRepository;
import br.com.gubee.interview.application.business.inbound.HeroDTO;
import br.com.gubee.interview.application.business.outbound.DeleteHeroResponse;
import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.services.CreateHeroService;
import br.com.gubee.interview.application.services.DeleteHeroService;
import br.com.gubee.interview.application.services.GetHeroByIdService;
import br.com.gubee.interview.application.services.GetHeroByNameService;
import br.com.gubee.interview.application.services.UpdateHeroService;

@Controller
@RequestMapping(path = "/heroes")
public class HeroController {

	@Autowired
	private HeroRepository heroRepository;

	@Autowired
	private PowerStatsRepository powerStatsRepository;

	@Autowired
	private BattleRepository battleRepository;
	
	@PostMapping("/")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Hero> createHero(@RequestBody HeroDTO heroDTO) {
		CreateHeroService createHeroService = new CreateHeroService(heroRepository, powerStatsRepository);
		return ResponseEntity.ok().body(createHeroService.execute(heroDTO.toHero()));
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
	public ResponseEntity<DeleteHeroResponse> deleteHero(@PathVariable("id") UUID id) {
		DeleteHeroService deleteHeroService = new DeleteHeroService(heroRepository, powerStatsRepository, battleRepository);
		return ResponseEntity.ok().body(deleteHeroService.execute(id));
	}
	
	@PutMapping("/{id}")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Hero> updateHero(@PathVariable("id") UUID id, @RequestBody HeroDTO heroDTO) {
		UpdateHeroService updateHeroService = new UpdateHeroService(heroRepository, powerStatsRepository);
		return ResponseEntity.ok().body(updateHeroService.execute(id, heroDTO.toHero()));
	}

	@GetMapping("/{id}")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Hero> getHeroById(@PathVariable("id") UUID id) {
		GetHeroByIdService getHeroByIdService = new GetHeroByIdService(heroRepository);
		return ResponseEntity.ok().body(getHeroByIdService.execute(id));
	}
}
