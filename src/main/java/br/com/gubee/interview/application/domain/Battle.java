package br.com.gubee.interview.application.domain;

import java.util.UUID;

public class Battle {
	
	private UUID id;
	
	private UUID heroOneId;
	
	private UUID heroTwoId;
	
	private UUID heroWinId;

	public Battle(Hero heroOne, Hero heroTwo) {
		super();
		this.id = UUID.randomUUID();
		this.heroOneId = heroOne.getId();
		this.heroTwoId = heroTwo.getId();
		
		defineTheWinner(heroOne, heroTwo);
	}
	
	public Battle(UUID id, UUID heroOneId, UUID heroTwoId, UUID heroWinId) {
		super();
		this.id = id;
		this.heroOneId = heroOneId;
		this.heroTwoId = heroTwoId;
		this.heroWinId = heroWinId;
	}

	private void defineTheWinner(Hero heroOne, Hero heroTwo) {
		int heroOneTotalPower = heroOne.getTotalPower();
		int heroTwoTotalPower = heroTwo.getTotalPower();
		this.heroWinId = heroOneTotalPower > heroTwoTotalPower ? heroOneId : heroTwoId;
	}
	
	public UUID getId() {
		return id;
	}

	public UUID getHeroOneId() {
		return heroOneId;
	}

	public UUID getHeroTwoId() {
		return heroTwoId;
	}

	public UUID getHeroWinId() {
		return heroWinId;
	}
}
