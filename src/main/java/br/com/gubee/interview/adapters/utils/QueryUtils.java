package br.com.gubee.interview.adapters.utils;

public class QueryUtils {

	private static final String INITIAL_FIND_HERO_QUERY = "select "
		 	+ "ps.*, "
		 	+ "h.*, "
		 	+ "bh.cnt "
		 	+ "from "
		 	+ "hero h "
		 	+ "join "
		 	+ "power_stats ps "
		 	+ "on "
		 	+ "h.power_stats_id = ps.id "
		 	+ "join "
		 	+ "("
		 	+ "select "
		 	+ "h.id as hid, "
		 	+ "count(bh.id) as cnt "
		 	+ "from "
		 	+ "hero h "
		 	+ "left join "
		 	+ "battle_history bh "
		 	+ "on "
		 	+ "bh.hero_win_id = h.id "
		 	+ "where ";
	
	private static final String END_FIND_HERO_QUERY = "group by "
		 	+ "h.id) bh "
		 	+ "on "
		 	+ "bh.hid = h.id "
		 	+ "where "
		 	+ "h.id = bh.hid";
	
	public static final String FIND_HERO_BY_ID_WITH_COUNT_WINS = new StringBuilder()
			.append(INITIAL_FIND_HERO_QUERY)
			.append("h.id = ? ")
			.append(END_FIND_HERO_QUERY)
			.toString();

	public static String findHeroesByNameLikeWithCounter(String name) {
		return new StringBuilder()
			.append(INITIAL_FIND_HERO_QUERY)
			.append("h.name like '%" + name + "%' ")
			.append(END_FIND_HERO_QUERY)
			.toString();
	}
}
