
package br.com.gubee.interview.glues;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.mockito.Mockito;

import br.com.gubee.interview.application.domain.Battle;
import br.com.gubee.interview.application.exceptions.BusinessValidationException;
import br.com.gubee.interview.application.ports.BattleRepositoryPort;
import br.com.gubee.interview.application.services.GetBattleHistoryByHeroIdService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetBattleHistoryByHeroIdStepDefinitions {

    private GetBattleHistoryByHeroIdService getBattleHistoryService;
    private BattleRepositoryPort battleRepository;
    private UUID heroId;
    private List<Battle> battleHistory;
    private Exception exception;

    @Given("^the get battle history service is available$")
    public void theGetBattleHistoryServiceIsAvailable() {
        battleRepository = Mockito.mock(BattleRepositoryPort.class);
        getBattleHistoryService = new GetBattleHistoryByHeroIdService(battleRepository);
    }

    @When("^I retrieve battle history for hero with ID \"([^\"]*)\"$")
    public void iRetrieveBattleHistoryForHeroWithID(String id) {
        heroId = UUID.fromString(id);
        Battle battle = new Battle(UUID.randomUUID(), heroId, UUID.randomUUID(), heroId);
        when(battleRepository.findByHeroId(heroId)).thenReturn(List.of(battle));
        try {
            battleHistory = getBattleHistoryService.execute(heroId);
        } catch (Exception e) {
            exception = e;
        }
    }
    
    @When("^I attempt to retrieve battle history for invalid hero ID$")
    public void iAttemptToRetrieveBattleHistoryForInvalidHeroID() {
        heroId = UUID.randomUUID(); // Generate an invalid ID
        when(battleRepository.findByHeroId(heroId)).thenThrow(new BusinessValidationException("Invalid hero ID"));
        try {
            battleHistory = getBattleHistoryService.execute(heroId);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("^I should get the battle history list$")
    public void iShouldGetTheBattleHistoryList() {
        assertNotNull(battleHistory);
    }

    @Then("^the system display an error message indicating invalid hero ID$")
    public void theSystemDisplayAnErrorMessageIndicatingInvalidHeroID() {
        assertNotNull(exception);
    }
}
