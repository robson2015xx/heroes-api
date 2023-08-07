package br.com.gubee.interview.core.service;

import java.util.UUID;

import br.com.gubee.interview.core.domain.Hero;
import br.com.gubee.interview.core.ports.GetHeroByIdServicePort;
import br.com.gubee.interview.core.ports.HeroRepositoryPort;

public class GetHeroByIdService implements GetHeroByIdServicePort {

	private HeroRepositoryPort heroRepository;

	public GetHeroByIdService(HeroRepositoryPort heroRepository) {
		this.heroRepository = heroRepository;
	}

	@Override
	public Hero getHeroById(UUID id) {

		return heroRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Heroi nao encontrado"));
	}

	public Hero execute(UUID id) {
		return getHeroById(id);
	}
}
