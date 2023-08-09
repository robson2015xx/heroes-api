package br.com.gubee.interview.glues;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.UUID;

import org.mockito.Mockito;

import br.com.gubee.interview.application.business.inbound.BattleDTO;
import br.com.gubee.interview.application.business.outbound.BattleResponse;
import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.domain.PowerStats;
import br.com.gubee.interview.application.ports.BattleRepositoryPort;
import br.com.gubee.interview.application.ports.HeroRepositoryPort;
import br.com.gubee.interview.application.services.CreateBattleByHeroesIdService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateBattleByHeroesIdStepDefinitions {

    private HeroRepositoryPort heroRepository;
    private BattleRepositoryPort battleRepository;
    private CreateBattleByHeroesIdService createBattleService;
    private BattleDTO battleDTO;
    private Map<String, Object> battleResult;
    private Exception exception;
    private Hero heroOne;
    private Hero heroTwo;

    @Given("^the create battle service is available$")
    public void theBattleServiceIsAvailable() {

    	heroRepository = Mockito.mock(HeroRepositoryPort.class);
    	battleRepository = Mockito.mock(BattleRepositoryPort.class);
        createBattleService = new CreateBattleByHeroesIdService(heroRepository, battleRepository);
        heroTwo = new Hero("Batman", "HUMAN", new PowerStats(40, 60, 80, 90), true); // Using the required attributes from the example payload
        heroOne = new Hero("Batman", "HUMAN", new PowerStats(40, 60, 80, 90), true); // Using the required attributes from the example payload
    }	

    @When("^I create a battle between hero with ID \"([^\"]*)\" and hero with ID \"([^\"]*)\"$")
    public void iCreateABattleBetweenHeroWithIDAndHeroWithID(String heroId1, String heroId2) {
        battleDTO = new BattleDTO(UUID.fromString(heroId1), UUID.fromString(heroId2));
        when(heroRepository.findById(UUID.fromString(heroId1))).thenReturn(java.util.Optional.of(heroOne));
        when(heroRepository.findById(UUID.fromString(heroId2))).thenReturn(java.util.Optional.of(heroTwo));
        try {
            battleResult = createBattleService.execute(battleDTO);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("^I attempt to create a battle between hero with ID \"([^\"]*)\" and hero with ID \"([^\"]*)\"$")
    public void iAttemptToCreateABattleBetweenHeroWithIDAndHeroWithID(String heroId1, String heroId2) {
        battleDTO = new BattleDTO(UUID.fromString(heroId1), UUID.fromString(heroId2));
        try {
            battleResult = createBattleService.execute(battleDTO);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("^the battle should be successfully saved$")
    public void theBattleShouldBeSuccessfullySaved() {
        assertNotNull(battleResult);
    }

    @Then("^the battle result should be as expected$")
    public void theBattleResultShouldBeAsExpected() {
        assertNotNull(battleResult);
        assertTrue(battleResult.containsKey("Result"));

        BattleResponse battleResponse = (BattleResponse) battleResult.get("Result");
        assertNotNull(battleResponse);
    }

    @Then("^the system should display an error message indicating invalid hero ID$")
    public void theSystemShouldDisplayAnErrorMessageIndicatingInvalidHeroID() {
        assertNotNull(exception);
    }

    @Then("^the system should display an error message indicating missing hero ID$")
    public void theSystemShouldDisplayAnErrorMessageIndicatingMissingHeroID() {
        assertNotNull(exception);
    }
}
