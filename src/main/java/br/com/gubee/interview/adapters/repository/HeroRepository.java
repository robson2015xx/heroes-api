package br.com.gubee.interview.adapters.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.gubee.interview.core.domain.Hero;
import br.com.gubee.interview.core.ports.HeroRepositoryPort;

@Repository
public class HeroRepository implements HeroRepositoryPort{

	@Autowired private JdbcTemplate jdbcTemplate;
		
	@Override
	public Hero save(Hero hero) {
				
			jdbcTemplate.update("INSERT INTO hero (id, name, race, power_stats_id, enabled, created_at, updated_at) " +
			          "VALUES (?, ?, ?, ?, ?, ?, ?)",
			          hero.getId(),
			          hero.getName(),
			          hero.getRace(),
			          hero.getStatsId(),
			          hero.isEnabled(),
			          hero.getCreatedAt(),
			          hero.getUpdatedAt());
			
		return hero;
	}
}
