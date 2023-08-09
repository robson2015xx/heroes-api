package br.com.gubee.interview.application.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.gubee.interview.application.business.ErrorMessagesConstants;
import br.com.gubee.interview.application.business.RaceEnum;
import br.com.gubee.interview.application.exceptions.BusinessValidationException;

public class Hero implements Serializable{

	private static final long serialVersionUID = 5013284719485248083L;

	private UUID id;
	
	private String name;
	
	private RaceEnum race;
	
	private PowerStats stats;
	
	private int totalWins;
	
	private boolean enabled;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date createdAt;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date updatedAt;
	
	public Hero() {}
	
	public Hero(UUID id, String name, String race, PowerStats stats, Boolean enabled, Date createdAt,
			Date updatedAt, int totalWins) {
		super();
		this.id = Optional.ofNullable(id).orElse(UUID.randomUUID());
		this.name = setValidName(name);
		this.race = setValidRace(race);
		this.stats = stats;
		this.enabled = Optional.ofNullable(enabled).orElse(true);
		this.createdAt = Optional.ofNullable(createdAt).orElse(new Date());
		this.updatedAt = Optional.ofNullable(updatedAt).orElse(new Date());
		this.totalWins = totalWins;
	}
	
	public Hero(String name, String race, PowerStats stats, Boolean enabled) {
		super();
		this.id = Optional.ofNullable(id).orElse(UUID.randomUUID());
		this.name = setValidName(name);
		this.race = setValidRace(race);
		
		if (stats == null) {
			throw new BusinessValidationException(ErrorMessagesConstants.POWER_STATS_IS_MANDATORY, "field [stats]");
		}
		
		this.stats = stats;
		this.enabled = Optional.ofNullable(enabled).orElse(true);
		this.createdAt = Optional.ofNullable(createdAt).orElse(new Date());
		this.updatedAt = Optional.ofNullable(updatedAt).orElse(new Date());
	}

	private String setValidName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new BusinessValidationException(ErrorMessagesConstants.NAME_IS_MANDATORY, "field [name]");
		} else if (name.trim().length() > 255) {
			throw new BusinessValidationException(ErrorMessagesConstants.NAME_IS_TOO_LONG, "field [name]");
		} else {
			return name.trim();
		}
	}
	
	private RaceEnum setValidRace(String race) {
		if (race == null || race.trim().isEmpty()) {
			throw new BusinessValidationException(ErrorMessagesConstants.RACE_IS_MANDATORY, "field [race]");
		} 
		
		try {
			return RaceEnum.valueOf(race.trim().toUpperCase());
		} catch (Exception ex) {
			throw new BusinessValidationException(ErrorMessagesConstants.RACE_IS_INVALID, Arrays.toString(RaceEnum.values()));
		}
	}

	public Hero updateOnlyMappedFields(Hero heroUpdated) {
		
		name = heroUpdated.getName();
		race = heroUpdated.getRace();
		updatedAt = heroUpdated.getUpdatedAt();
		stats = stats.updateOnlyMappedFields(heroUpdated.getStats());
		
		return this;
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

	public RaceEnum getRace() {
		return race;
	}

	public PowerStats getStats() {
		return stats;
	}
	
	public int getTotalWins() {
		return totalWins;
	}
	
	@JsonIgnore
	public UUID getStatsId() {
		return stats.getId();
	}
	
	@JsonIgnore
	public int getTotalPower() {
		return stats.weightedSumPowerStats();
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

	@Override
	public String toString() {
		return "Hero [id=" + id + ", name=" + name + "]";
	}
	
}
