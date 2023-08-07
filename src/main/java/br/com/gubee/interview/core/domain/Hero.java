package br.com.gubee.interview.core.domain;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Hero {

	private UUID id;
	
	private String name;
	
	private RaceEnum race;
	
	private PowerStats stats;
	
	private boolean enabled;
	
	private Date createdAt;
	
	private Date updatedAt;

	public Hero(String name, String race, PowerStats stats) {
		super();
		this.id = UUID.randomUUID();
		this.name = setValidName(name);
		this.race = setValidRace(race);
		this.stats = stats;
		this.enabled = true;
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
	
	private String setValidName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new RuntimeException("Nome deve ser informado");
		} else if (name.trim().length() > 255) {
			throw new RuntimeException("Nome muito grande");
		} else {
			return name.trim();
		}
	}
	
	private RaceEnum setValidRace(String race) {
		if (race == null || race.trim().isEmpty()) {
			throw new RuntimeException("Raça deve ser informada");
		} 
		
		try {
			return RaceEnum.valueOf(race.trim().toUpperCase());
		} catch (Exception ex) {
			throw new RuntimeException("Deve-se informar uma raça válida: " + Arrays.toString(RaceEnum.values()));
		}
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public RaceEnum getRace() {
		return race;
	}

	public PowerStats getStats() {
		return stats;
	}
	
	@JsonIgnore
	public UUID getStatsId() {
		return stats.getId();
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
}
