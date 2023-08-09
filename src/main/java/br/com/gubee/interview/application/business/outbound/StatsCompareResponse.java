package br.com.gubee.interview.application.business.outbound;

import br.com.gubee.interview.application.business.inbound.PowerStatsDTO;

public class StatsCompareResponse {

	private PowerStatsDTO statsLose;
	
	private PowerStatsDTO statsWin;

	public StatsCompareResponse(PowerStatsDTO statsLose, PowerStatsDTO statsWin) {
		this.statsLose =  new PowerStatsDTO(
				statsCompare(statsLose.getStrength(),statsWin.getStrength()), 
				statsCompare(statsLose.getAgility(),statsWin.getAgility()), 
				statsCompare(statsLose.getDexterity(),statsWin.getDexterity()),  
				statsCompare(statsLose.getIntelligence(),statsWin.getIntelligence()));
		
		this.statsWin =  new PowerStatsDTO(
				statsCompare(statsWin.getStrength(),statsWin.getStrength()), 
				statsCompare(statsWin.getAgility(),statsWin.getAgility()), 
				statsCompare(statsWin.getDexterity(),statsWin.getDexterity()),  
				statsCompare(statsWin.getIntelligence(),statsWin.getIntelligence()));
	}
	
	private int statsCompare(int heroValue, int opponentValue) {
		return heroValue > 0 && heroValue < opponentValue ? - heroValue : opponentValue;
	}
	
	public PowerStatsDTO getStatsLose() {
		return statsLose;
	}

	public PowerStatsDTO getStatsWin() {
		return statsWin;
	}
}
