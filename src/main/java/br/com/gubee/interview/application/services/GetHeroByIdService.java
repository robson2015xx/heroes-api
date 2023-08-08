package br.com.gubee.interview.application.services;

import java.util.UUID;

import br.com.gubee.interview.application.domain.ErrorMessagesConstants;
import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.exceptions.BusinessValidationException;
import br.com.gubee.interview.application.ports.HeroRepositoryPort;

public class GetHeroByIdService  {

	private HeroRepositoryPort heroRepository;

	public GetHeroByIdService(HeroRepositoryPort heroRepository) {
		this.heroRepository = heroRepository;
	}

	private Hero getHeroById(UUID id) {

		return heroRepository.findById(id).orElseThrow(() 
				-> new BusinessValidationException(ErrorMessagesConstants.INVALID_HERO_ID, id.toString()));
	}

	public Hero execute(UUID id) {
		return getHeroById(id);
	}
}
