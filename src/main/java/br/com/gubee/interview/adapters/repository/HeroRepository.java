package br.com.gubee.interview.adapters.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.gubee.interview.adapters.rowmapper.HeroRowMapper;
import br.com.gubee.interview.core.domain.Hero;
import br.com.gubee.interview.core.ports.HeroRepositoryPort;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class HeroRepository implements HeroRepositoryPort{

	@Autowired private JdbcTemplate jdbcTemplate;
		
	@Override
	public Hero save(Hero hero) {
				
			jdbcTemplate.update("INSERT INTO hero (id, name, race, power_stats_id, enabled, created_at, updated_at) " +
			          "VALUES (?, ?, ?, ?, ?, ?, ?)",
			          hero.getId(),
			          hero.getName(),
			          hero.getRace().name(),
			          hero.getStatsId(),
			          hero.isEnabled(),
			          hero.getCreatedAt(),
			          hero.getUpdatedAt());
			
		return hero;
	}

	@Override
	public void delete(UUID id) {
		jdbcTemplate.update("delete from hero where id = ?", id);
	}

	@Override
	public Optional<Hero> findById(UUID id) {
		
		Hero hero = null; 
		
		try { 
			hero = jdbcTemplate.queryForObject("select "
					+ "	* "
					+ "from "
					+ "	hero h "
					+ "join  "
					+ "	power_stats ps "
					+ "on h.power_stats_id = ps.id", new HeroRowMapper());
		} catch (Exception ex) {
			log.debug("Problems in get hero by id: ", ex);
		}
		return Optional.ofNullable(hero);
	}
}
