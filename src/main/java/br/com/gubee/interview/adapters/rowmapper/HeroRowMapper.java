package br.com.gubee.interview.adapters.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;

import br.com.gubee.interview.core.domain.Hero;
import br.com.gubee.interview.core.domain.PowerStats;
import br.com.gubee.interview.core.domain.RaceEnum;

public class HeroRowMapper implements RowMapper<Hero>{

	@Override
	public Hero mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PowerStats stats = new PowerStats(UUID.fromString(rs.getString(8)), rs.getInt(9), 
				rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getDate(13), rs.getDate(14));
		Hero hero = new Hero(UUID.fromString(rs.getString(1)), rs.getString(2), RaceEnum.valueOf(rs.getString(3)),
				stats, rs.getBoolean(5), rs.getDate(6), rs.getDate(7));

		return hero;
	}

}
