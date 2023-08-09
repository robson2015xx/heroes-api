package br.com.gubee.interview.application.business.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.gubee.interview.application.domain.Hero;

public class HeroDTO {

	private String name;
	
	private String race;
	
	@JsonProperty("stats")
	private PowerStatsDTO statsDTO;
	
	private Boolean enabled;
	
	public HeroDTO() {}
	
	public Hero toHero() {
		return new Hero(this.name, this.race, this.statsDTO.toPowerStats(), enabled);
	};

	public String getName() {
		return name;
	}

	public String getRace() {
		return race;
	}

	public PowerStatsDTO getStatsDTO() {
		return statsDTO;
	}

	public Boolean getEnabled() {
		return enabled;
	}
}
