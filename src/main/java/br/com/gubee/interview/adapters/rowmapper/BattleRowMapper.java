package br.com.gubee.interview.adapters.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;

import br.com.gubee.interview.application.domain.Battle;

public class BattleRowMapper implements RowMapper<Battle>{

	@Override
	public Battle mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return new Battle(UUID.fromString(rs.getString(1)), 
				UUID.fromString(rs.getString(2)), 
				UUID.fromString(rs.getString(3)), 
				UUID.fromString(rs.getString(4)));
	}

}
