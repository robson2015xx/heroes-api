package br.com.gubee.interview.glues;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.mockito.Mockito;

import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.domain.PowerStats;
import br.com.gubee.interview.application.exceptions.BusinessValidationException;
import br.com.gubee.interview.application.ports.HeroRepositoryPort;
import br.com.gubee.interview.application.services.GetHeroByIdService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetHeroByIdStepDefinitions {

    private GetHeroByIdService getHeroByIdService;
    private HeroRepositoryPort heroRepository;
    private UUID heroId;
    private Hero retrievedHero;
    Exception exception = null;

    @Given("^the get hero by ID service is available$")
    public void theGetHeroByIdServiceIsAvailable() {
        heroRepository = Mockito.mock(HeroRepositoryPort.class);
        getHeroByIdService = new GetHeroByIdService(heroRepository);
    }

    @When("^I retrieve a hero with ID \"([^\"]*)\"$")
    public void iRetrieveAHeroWithID(String id) {
        heroId = UUID.fromString(id);
        Hero mockHero = new Hero("Batman", "HUMAN", new PowerStats(40, 60, 80, 90), true); // Using the required attributes from the example payload
        when(heroRepository.findById(heroId)).thenReturn(java.util.Optional.of(mockHero));
        retrievedHero = getHeroByIdService.execute(heroId);
    }

    @Then("^I should get the hero details$")
    public void iShouldGetTheHeroDetails() {
        assertNotNull(retrievedHero);
    }
    
    @When("^I attempt to retrieve a hero with invalid ID$")
    public void iAttemptToRetrieveAHeroWithInvalidID() {
        heroId = UUID.randomUUID(); // Generate an invalid ID
        when(heroRepository.findById(heroId)).thenThrow(new BusinessValidationException("Invalid hero ID"));
        try {
            retrievedHero = getHeroByIdService.execute(heroId);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("^the system should display an error message invalid hero ID$")
    public void theSystemShouldDisplayAnErrorMessageInvalidHeroID() {
        assertNotNull(exception);
    }
}
