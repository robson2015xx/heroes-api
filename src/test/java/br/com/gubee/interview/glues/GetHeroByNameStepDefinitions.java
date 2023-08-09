package br.com.gubee.interview.glues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.ports.HeroRepositoryPort;
import br.com.gubee.interview.application.services.GetHeroByNameService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetHeroByNameStepDefinitions {

    private HeroRepositoryPort heroRepository;
    private GetHeroByNameService getHeroByNameService;
    private List<Hero> resultHeroes;
    
    @Given("a hero repository")
    public void aHeroRepository() {
        heroRepository = mock(HeroRepositoryPort.class);
        getHeroByNameService = new GetHeroByNameService(heroRepository);
    }

    @When("I search for heroes with name {string}")
    public void iSearchForHeroesWithName(String heroName) {
        heroRepository = mock(HeroRepositoryPort.class);
        getHeroByNameService = new GetHeroByNameService(heroRepository);

        List<Hero> expectedHeroes = new ArrayList<>();
        expectedHeroes.add(new Hero());
        expectedHeroes.add(new Hero());

        when(heroRepository.findByNameLike(heroName)).thenReturn(expectedHeroes);

        resultHeroes = getHeroByNameService.execute(heroName);
    }

    @Then("I should receive a list of heroes")
    public void iShouldReceiveAListOfHeroes() {
        assertEquals(2, resultHeroes.size());
    }
}
