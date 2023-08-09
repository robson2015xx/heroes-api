package br.com.gubee.interview.glues;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.mockito.Mockito;

import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.domain.PowerStats;
import br.com.gubee.interview.application.ports.HeroRepositoryPort;
import br.com.gubee.interview.application.services.GetHeroByNameService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetHeroByNameStepDefinitions {

    private GetHeroByNameService getHeroByNameService;
    private HeroRepositoryPort heroRepository;
    private String heroName;
    private List<Hero> retrievedHeroes;

    @Given("^the get hero by name service is available$")
    public void theGetHeroByNameServiceIsAvailable() {
        heroRepository = Mockito.mock(HeroRepositoryPort.class);
        getHeroByNameService = new GetHeroByNameService(heroRepository);
    }


    @When("^I retrieve heroes with partial name \"([^\"]*)\"$")
    public void iRetrieveHeroesWithPartialName(String partialName) {
        heroName = partialName;
        when(heroRepository.findByNameLike(heroName)).thenReturn(List.of(new Hero(partialName + "test", "HUMAN", new PowerStats(0, 0, 0, 0), null)));
        retrievedHeroes = getHeroByNameService.execute(heroName);
    }

    @Then("^I should get the list of matching heroes$")
    public void iShouldGetTheListOfMatchingHeroes() {
        assertNotNull(retrievedHeroes);
    }
}
