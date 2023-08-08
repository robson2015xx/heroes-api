package br.com.gubee.interview.adapters.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.gubee.interview.adapters.rowmapper.HeroRowMapper;
import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.ports.HeroRepositoryPort;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class HeroRepository implements HeroRepositoryPort{

	@Autowired private JdbcTemplate jdbcTemplate;
		
	@Override
	@CacheEvict(value = "hero", allEntries = true)
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
	@CacheEvict(value = "hero", allEntries = true)
	public void delete(UUID id) {
		jdbcTemplate.update("delete from hero where id = ?", id);
	}

	@Override
	@Cacheable(value = "hero", key = "#id")
	public Optional<Hero> findById(UUID id) {
		
		Hero hero = null; 
		
		try { 
			hero = jdbcTemplate.queryForObject("select "
					+ "	* "
					+ "from "
					+ "	hero h "
					+ "join  "
					+ "	power_stats ps "
					+ "on h.power_stats_id = ps.id "
					+ "where h.id = ?", new HeroRowMapper(), id);
		} catch (Exception ex) {
			log.debug("Problems in get hero by id: ", ex);
		}
		return Optional.ofNullable(hero);
	}

	@Override
	@Cacheable(value = "hero", key = "#name")
	public List<Hero> findByNameLike(String name) {
		List<Hero> heroes = jdbcTemplate.query("select "
				+ "	* "
				+ "from "
				+ "	hero h "
				+ "join  "
				+ "	power_stats ps "
				+ "on h.power_stats_id = ps.id "
				+ "where h.name like '%" + name + "%'", new HeroRowMapper());
		return heroes;
	}

	@Override
	@CacheEvict(value = "hero", allEntries = true)
	public Hero update(Hero hero) {
		jdbcTemplate.update("UPDATE hero set name = ?, race = ?, power_stats_id = ?, enabled = ?, "
				+ "updated_at = ? where id = ?",
		          hero.getName(),
		          hero.getRace().name(),
		          hero.getStatsId(),
		          hero.isEnabled(),
		          hero.getUpdatedAt(),
				  hero.getId());
		
	return hero;
	}
}
