package br.com.gubee.interview.glues;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;

import br.com.gubee.interview.application.business.RaceEnum;
import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.domain.PowerStats;
import br.com.gubee.interview.application.exceptions.BusinessValidationException;
import br.com.gubee.interview.application.ports.HeroRepositoryPort;
import br.com.gubee.interview.application.ports.PowerStatsRepositoryPort;
import br.com.gubee.interview.application.services.CreateHeroService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateHeroStepDeinitions {

    private HeroRepositoryPort heroRepository;
    private PowerStatsRepositoryPort powerStatsRepository;
    private CreateHeroService createHeroService;
    private Hero createdHero;
    private Exception exception;
    private String heroNameSaved;

    @Given("^the power stats repository is available$")
    public void thePowerStatsRepositoryIsAvailable() {
        // Set up mocks or necessary instances for PowerStatsRepository
        powerStatsRepository = Mockito.mock(PowerStatsRepositoryPort.class);
    }

    @Given("^the hero repository is available$")
    public void theHeroRepositoryIsAvailable() {
        // Set up mocks or necessary instances for HeroRepository
        heroRepository = Mockito.mock(HeroRepositoryPort.class);
        createHeroService = new CreateHeroService(heroRepository, powerStatsRepository);
    }

    @When("^I create a new hero with name \"([^\"]*)\" and the required attributes$")
    public void iCreateANewHeroWithNameAndTheRequiredAttributes(String heroName) {
        Hero newHero = new Hero(heroName, "HUMAN", new PowerStats(40, 60, 80, 90), true); // Using the required attributes from the example payload
        try {
        	when(heroRepository.save(Mockito.any())).thenReturn(newHero);
            createdHero = createHeroService.execute(newHero);
            heroNameSaved = heroName;
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("^I create a new hero with empty name and power stats valid$")
    public void iCreateANewHeroWithEmptyNameAndPowerStatsValid() {
        try {
        	Hero newHero = new Hero("", "", new PowerStats(40, 60, 80, 90), null); // Invalid name and Valid power stats
            createdHero = createHeroService.execute(newHero);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("^I create a new hero with name \"([^\"]*)\" and invalid power stats$")
    public void iCreateANewHeroWithNameAndInvalidPowerStats(String heroName) {
        try {
        	Hero newHero = new Hero(heroName, "ALIEN", null, null);
            createdHero = createHeroService.execute(newHero);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("^the hero should be successfully saved$")
    public void theHeroShouldBeSuccessfullySaved() {
        assertNotNull(createdHero);
        assertEquals(heroNameSaved, createdHero.getName());
        assertEquals(RaceEnum.HUMAN, createdHero.getRace());
        assertNotNull(createdHero.getStats());
    }

    @Then("^the system should display an error message indicating invalid power stats$")
    public void theSystemShouldDisplayAnErrorMessageIndicatingInvalidPowerStats() {
        assertNull(createdHero);
        assertNotNull(exception);
        assertEquals(BusinessValidationException.class, exception.getClass());
    }

    @Then("^the system should display an error message indicating the hero name is required$")
    public void theSystemShouldDisplayAnErrorMessageIndicatingTheHeroNameIsRequired() {
        assertNull(createdHero);
        assertNotNull(exception);
        assertEquals(BusinessValidationException.class, exception.getClass());
    }
}