package br.com.gubee.interview.application.business.inbound;

import br.com.gubee.interview.application.domain.PowerStats;

public class PowerStatsDTO {

	private int strength;
	
	private int agility;
	
	private int dexterity;
	
	private int intelligence;
	
	public PowerStatsDTO() {}
		
	public PowerStatsDTO(int strength, int agility, int dexterity, int intelligence) {
		super();
		this.strength = strength;
		this.agility = agility;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
	}

	public PowerStatsDTO(PowerStats stats) {
		strength = stats.getStrength();
		
		agility = stats.getAgility();
		
		dexterity = stats.getDexterity();
		
		intelligence = stats.getIntelligence();
	}

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
