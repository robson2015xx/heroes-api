package br.com.gubee.interview.core.domain;

import java.util.Date;
import java.util.UUID;

public class Hero {

	private UUID id;
	
	private String name;
	
	private String race;
	
	private PowerStats stats;
	
	private boolean enabled;
	
	private Date createdAt;
	
	private Date updatedAt;
	

	public Hero(UUID id, String name, String race, PowerStats stats) {
		super();
		this.id = id;
		this.name = name;
		this.race = race;
		this.stats = stats;
		this.enabled = true;
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getRace() {
		return race;
	}

	public PowerStats getStats() {
		return stats;
	}
	
	public void setStats(PowerStats stats) {
		this.stats = stats;
	}
	
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
