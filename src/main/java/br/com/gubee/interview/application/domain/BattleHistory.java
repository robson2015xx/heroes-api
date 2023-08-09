package br.com.gubee.interview.application.domain;

import java.util.UUID;

public class BattleHistory {
	
	private UUID id;
	
	private UUID heroOneId;
	
	private UUID heroTwoId;
	
	private UUID heroWinId;

	public BattleHistory(UUID heroOneId, UUID heroTwoId) {
		super();
		this.id = UUID.randomUUID();
		this.heroOneId = heroOneId;
		this.heroTwoId = heroTwoId;
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
