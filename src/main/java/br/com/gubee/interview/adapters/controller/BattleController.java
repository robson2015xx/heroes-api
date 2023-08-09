package br.com.gubee.interview.adapters.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.gubee.interview.adapters.repository.BattleRepository;
import br.com.gubee.interview.adapters.repository.HeroRepository;
import br.com.gubee.interview.application.business.inbound.BattleDTO;
import br.com.gubee.interview.application.domain.Battle;
import br.com.gubee.interview.application.services.CreateBattleByHeroesIdService;
import br.com.gubee.interview.application.services.GetBattleHistoryByHeroIdService;

@Controller
@RequestMapping(path = "/battles")
public class BattleController {

	@Autowired
	private HeroRepository heroRepository;
	
	@Autowired 
	private BattleRepository battleRepository;
	
	@PostMapping
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Map<String, Object>> createBattleByHeroesId(@RequestBody BattleDTO battleDTO) {
		CreateBattleByHeroesIdService createBattleByHeroesIdService = new CreateBattleByHeroesIdService(heroRepository, battleRepository);
		return ResponseEntity.ok().body(createBattleByHeroesIdService.execute(battleDTO));
	}
	
	@GetMapping
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<List<Battle>> getBattleHistoryByHeroId(@RequestParam UUID heroId) {
		GetBattleHistoryByHeroIdService getBattleHistoryByHeroIdService = new GetBattleHistoryByHeroIdService(battleRepository);
		
		List<Battle> battles = getBattleHistoryByHeroIdService.execute(heroId);
		return ResponseEntity.ok().body(battles.isEmpty() ? null : battles);
	}
}
