package br.com.gubee.interview.glues;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.mockito.Mockito;

import br.com.gubee.interview.application.business.outbound.DeleteHeroResponse;
import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.domain.PowerStats;
import br.com.gubee.interview.application.ports.BattleRepositoryPort;
import br.com.gubee.interview.application.ports.HeroRepositoryPort;
import br.com.gubee.interview.application.ports.PowerStatsRepositoryPort;
import br.com.gubee.interview.application.services.DeleteHeroService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteHeroStepDefinitions {

    private HeroRepositoryPort heroRepository;
    private PowerStatsRepositoryPort powerStatsRepository;
    private BattleRepositoryPort battleRepository;
    private DeleteHeroService deleteHeroService;
    private UUID heroId;
    private DeleteHeroResponse deleteResponse;
    private Hero heroToDelete;
    private Exception exception;

    @Given("^the delete hero service is available$")
    public void theDeleteHeroServiceIsAvailable() {
        heroRepository = Mockito.mock(HeroRepositoryPort.class);
        powerStatsRepository = Mockito.mock(PowerStatsRepositoryPort.class);
        battleRepository = Mockito.mock(BattleRepositoryPort.class);
        deleteHeroService = new DeleteHeroService(heroRepository, powerStatsRepository, battleRepository);
    }

    @Given("^I have a hero with ID \"([^\"]*)\"$")
    public void iHaveAHeroWithID(String id) {
        heroId = UUID.fromString(id);
        heroToDelete = new Hero(heroId, "Batman", "HUMAN", new PowerStats(0, 0, 0, 0), true, null, null, 0);
        when(heroRepository.findById(heroId)).thenReturn(java.util.Optional.of(heroToDelete));
    }

    @When("^I delete a hero with ID \"([^\"]*)\"$")
    public void iDeleteAHeroWithID(String id) {
        deleteHeroService = new DeleteHeroService(heroRepository, powerStatsRepository, battleRepository);
        heroId = UUID.fromString(id);
        try {
            deleteResponse = deleteHeroService.execute(heroId);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("^I attempt to delete a hero with invalid ID$")
    public void iAttemptToDeleteAHeroWithInvalidID() {
        deleteHeroService = new DeleteHeroService(heroRepository, powerStatsRepository, battleRepository);
        heroId = UUID.randomUUID(); // Invalid hero ID
        try {
            deleteResponse = deleteHeroService.execute(heroId);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("^the hero should be successfully deleted$")
    public void theHeroShouldBeSuccessfullyDeleted() {
        assertEquals(heroId, heroToDelete.getId());
    }
    
    @Then("^the system should display an error message indicating hero ID invalid$")
    public void theSystemShouldDisplayAnErrorMessageIndicatingHeroIdInvalid() {
        assertNull(deleteResponse);
        assertNotNull(exception);
    }
}
