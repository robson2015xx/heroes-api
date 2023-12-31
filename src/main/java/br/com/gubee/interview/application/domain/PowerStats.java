package br.com.gubee.interview.application.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PowerStats implements Serializable {

	private static final long serialVersionUID = 5677576154366798405L;

	private UUID id;
	
	private int strength;
	
	private int agility;
	
	private int dexterity;
	
	private int intelligence;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date createdAt;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date updatedAt;
	
	public PowerStats() {}

	public PowerStats(int strength, int agility, int dexterity, int intelligence) {
		super();
		this.id = Optional.ofNullable(id).orElse(UUID.randomUUID());
		this.strength = strength;
		this.agility = agility;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		this.createdAt = Optional.ofNullable(createdAt).orElse(new Date());
		this.updatedAt = Optional.ofNullable(updatedAt).orElse(new Date());
	}
	
	public PowerStats(UUID id, int strength, int agility, int dexterity, int intelligence, Date createdAt,
			Date updatedAt) {
		super();
		this.id = Optional.ofNullable(id).orElse(UUID.randomUUID());
		this.strength = strength;
		this.agility = agility;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		this.createdAt = Optional.ofNullable(createdAt).orElse(new Date());
		this.updatedAt = Optional.ofNullable(updatedAt).orElse(new Date());
	}
	
	public int weightedSumPowerStats() {
		return strength + agility + dexterity + intelligence;
	}

	public PowerStats updateOnlyMappedFields(PowerStats statsUpdated) {
		
		strength = statsUpdated.getStrength();
		agility = statsUpdated.getAgility();
		dexterity = statsUpdated.getDexterity();
		intelligence = statsUpdated.getIntelligence();
		updatedAt = statsUpdated.getUpdatedAt();
		
		return this;
	}
	
	public UUID getId() {
		return id;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
}
