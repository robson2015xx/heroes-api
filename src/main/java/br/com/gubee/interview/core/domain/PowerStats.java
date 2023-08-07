package br.com.gubee.interview.core.domain;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class PowerStats {

	private UUID id;
	
	private int strength;
	
	private int agility;
	
	private int dexterity;
	
	private int intelligence;
	
	private Date createdAt;
	
	private Date updatedAt;

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

	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
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
