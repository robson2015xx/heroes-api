package br.com.gubee.interview.application.services;

import java.util.UUID;

import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.ports.HeroRepositoryPort;

public class GetHeroByIdService  {

	private HeroRepositoryPort heroRepository;

	public GetHeroByIdService(HeroRepositoryPort heroRepository) {
		this.heroRepository = heroRepository;
	}

	private Hero getHeroById(UUID id) {

		System.out.println(id.toString());
		return heroRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Heroi nao encontrado"));
	}

	public Hero execute(UUID id) {
		return getHeroById(id);
	}
}
