package br.com.gubee.interview.application.business.inbound;

import java.util.UUID;

public class BattleDTO {

	private UUID heroOneId;
	
	private UUID heroTwoId;

	public BattleDTO(UUID heroOneId, UUID heroTwoId) {
		super();
		this.heroOneId = heroOneId;
		this.heroTwoId = heroTwoId;
	}

	public UUID getHeroOneId() {
		return heroOneId;
	}

	public UUID getHeroTwoId() {
		return heroTwoId;
	}
}
