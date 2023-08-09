package br.com.gubee.interview.glues;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.ports.HeroRepositoryPort;
import br.com.gubee.interview.application.ports.PowerStatsRepositoryPort;
import br.com.gubee.interview.application.services.CreateHeroService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class CreateHeroStepDefinitions {

    private HeroRepositoryPort heroRepository;
    private PowerStatsRepositoryPort powerStatsRepository;
    private CreateHeroService createHeroService;
    private Hero createdHero;

    @Given("a hero repository and power stats repository")
    public void aHeroRepositoryAndPowerStatsRepository() {
        heroRepository = mock(HeroRepositoryPort.class);
        powerStatsRepository = mock(PowerStatsRepositoryPort.class);
        createHeroService = new CreateHeroService(heroRepository, powerStatsRepository);
    }

    @When("I create a hero with name {string}")
    public void iCreateAHeroWithName(String heroName) {
        Hero hero = new Hero();
        createdHero = new Hero();
        when(heroRepository.save(any())).thenReturn(createdHero);

        createHeroService.execute(hero);
    }

    @Then("the hero should be saved successfully")
    public void theHeroShouldBeSavedSuccessfully() {
        verify(powerStatsRepository).save(any());
        verify(heroRepository).save(any());

        assertNotNull(createdHero);
    }
}
