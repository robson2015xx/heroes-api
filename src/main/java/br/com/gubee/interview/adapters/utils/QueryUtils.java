package br.com.gubee.interview.adapters.utils;

public class QueryUtils {

	public static final String FIND_HERO_BY_ID_WITH_COUNT_WINS = "select  "
			+ "	ps.*, "
			+ "	h.*, "
			+ "	bh.cnt "
			+ "from  "
			+ "	hero h "
			+ "join   "
			+ "	power_stats ps  "
			+ "on "
			+ "	h.power_stats_id = ps.id "
			+ "join  "
			+ "    ( "
			+ "	select "
			+ "		h.id as hid, "
			+ "		count(bh.id) as cnt "
			+ "	from "
			+ "		hero h "
			+ "	left join  "
			+ "    battle_history bh "
			+ "on "
			+ "		bh.hero_win_id = h.id "
			+ "	where "
			+ "		h.id = ? "
			+ "	group by "
			+ "		h.id) bh "
			+ "on "
			+ "	bh.hid = h.id "
			+ "where "
			+ "	h.id = bh.hid";
}
