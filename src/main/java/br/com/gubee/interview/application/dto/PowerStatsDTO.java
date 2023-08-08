package br.com.gubee.interview.application.dto;

import br.com.gubee.interview.application.domain.PowerStats;

public class PowerStatsDTO {

	private int strength;
	
	private int agility;
	
	private int dexterity;
	
	private int intelligence;
	
	public PowerStatsDTO() {}

	public PowerStats toPowerStats() {
		return new PowerStats(strength, agility, dexterity, intelligence);
	}
	
	public int getStrength() {
		return strength;
	}

	public int getAgility() {
		return agility;
	}

	public int getDexterity() {
		return dexterity;
	}

	public int getIntelligence() {
		return intelligence;
	}
}
