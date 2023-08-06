package br.com.gubee.interview.adapters.repository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.gubee.interview.core.domain.PowerStats;
import br.com.gubee.interview.core.ports.PowerStatsRepositoryPort;

@Repository
public class PowerStatsRepository implements PowerStatsRepositoryPort {

	@Autowired private JdbcTemplate jdbcTemplate;
	
	@Override
	public PowerStats save(PowerStats stats) {
				
			UUID id = UUID.randomUUID();
			jdbcTemplate.update("INSERT INTO power_stats (id, strength, agility, dexterity, intelligence, created_at, updated_at) " +
			          "VALUES (?, ?, ?, ?, ?, ?, ?)",
			          id,
			          stats.getStrength(),
			          stats.getAgility(),
			          stats.getDexterity(),
			          stats.getIntelligence(),
			          stats.getCreatedAt(),
			          stats.getUpdatedAt());
			stats.setId(id);
		
		return stats;
	}
}
