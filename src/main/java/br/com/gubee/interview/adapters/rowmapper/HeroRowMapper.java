package br.com.gubee.interview.adapters.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;

import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.domain.PowerStats;

public class HeroRowMapper implements RowMapper<Hero>{

	@Override
	public Hero mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PowerStats stats = new PowerStats(
				UUID.fromString(rs.getString(1)), 
				rs.getInt(2), 
				rs.getInt(3), 
				rs.getInt(4), 
				rs.getInt(5), 
				rs.getTimestamp(6), 
				rs.getTimestamp(7));
		
		Hero hero = new Hero(
				UUID.fromString(rs.getString(8)), 
				rs.getString(9), 
				rs.getString(10),
				stats, 
				rs.getBoolean(11), 
				rs.getTimestamp(12), 
				rs.getTimestamp(13),
				rs.getInt(14));

		return hero;
	}

}
