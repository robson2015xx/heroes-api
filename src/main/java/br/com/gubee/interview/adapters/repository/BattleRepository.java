package br.com.gubee.interview.adapters.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.gubee.interview.adapters.rowmapper.BattleRowMapper;
import br.com.gubee.interview.application.domain.Battle;
import br.com.gubee.interview.application.ports.BattleRepositoryPort;

@Repository
public class BattleRepository implements BattleRepositoryPort {

	@Autowired private JdbcTemplate jdbcTemplate;

	@Override
	public Battle save(Battle battle) {
		
		jdbcTemplate.update("INSERT INTO battle_history (id, hero_one_id, hero_two_id, hero_win_id) " +
		          "VALUES (?, ?, ?, ?)",
		          battle.getId(),
		          battle.getHeroOneId(),
		          battle.getHeroTwoId(),
		          battle.getHeroWinId());
		
		return battle;
	}

	@Override
	public void deleteByHeroId(UUID id) {
		jdbcTemplate.update("delete\r\n"
				+ "from\r\n"
				+ "	battle_history bh\r\n"
				+ "where\r\n"
				+ "	(bh.hero_one_id = ? and not exists (\r\n"
				+ "	select\r\n"
				+ "		1\r\n"
				+ "	from\r\n"
				+ "		hero h\r\n"
				+ "	where\r\n"
				+ "		h.id = bh.hero_two_id))\r\n"
				+ "	or (bh.hero_two_id = ? and not exists (\r\n"
				+ "	select\r\n"
				+ "		1\r\n"
				+ "	from\r\n"
				+ "		hero h\r\n"
				+ "	where\r\n"
				+ "		h.id = bh.hero_one_id))", id, id);
	}

	@Override
	public List<Battle> findByHeroId(UUID id) {
		
		return jdbcTemplate.query("select\r\n"
				+ "	bh.*\r\n"
				+ "from\r\n"
				+ "	battle_history bh\r\n"
				+ "where\r\n"
				+ "	bh.hero_one_id = ?"
				+ "	or bh.hero_two_id = ?", new BattleRowMapper(), id, id);
	}
}
