package br.com.gubee.interview.glues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.UUID;

import br.com.gubee.interview.application.business.ErrorMessagesConstants;
import br.com.gubee.interview.application.domain.Hero;
import br.com.gubee.interview.application.domain.PowerStats;
import br.com.gubee.interview.application.exceptions.BusinessValidationException;
import br.com.gubee.interview.application.ports.HeroRepositoryPort;
import br.com.gubee.interview.application.ports.PowerStatsRepositoryPort;
import br.com.gubee.interview.application.services.UpdateHeroService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateHeroStepDefinitions {

    private HeroRepositoryPort heroRepository = mock(HeroRepositoryPort.class);
    private PowerStatsRepositoryPort powerStatsRepository = mock(PowerStatsRepositoryPort.class);
    private UpdateHeroService updateHeroService = new UpdateHeroService(heroRepository, powerStatsRepository);
    private UUID heroId;
    private Hero updatedHero;
    private Exception exception;

    @Given("an existing hero with ID {string}")
    public void anExistingHeroWithID(String id) {
        heroId = UUID.fromString(id);
    }
    
    @Given("an non-existing hero with ID {string}")
    public void anNonExistingHeroWithID(String id) {
        heroId = UUID.fromString(id);
    }

    @When("I update the hero with ID {string}")
    public void iUpdateTheHeroWithID(String id, io.cucumber.datatable.DataTable dataTable) {
        if ("725730a9-f6af-4546-a954-c6c11fa3ca0a".equals(heroId.toString())) {
            when(heroRepository.findById(heroId)).thenReturn(java.util.Optional.empty());
        } else {
        	Hero existingHero = new Hero(heroId, "Superman", "Human", new PowerStats(0, 0, 0, 0), true, new Date(), new Date(), 0);
        	when(heroRepository.findById(heroId)).thenReturn(java.util.Optional.of(existingHero));
        }
        Hero updatedFields = new Hero("Batman", "HUMAN", new PowerStats(0, 0, 0, 0), true);        	

        try {
            updatedHero = updateHeroService.execute(heroId, updatedFields);
        } catch (BusinessValidationException e) {
            exception = e;
        }
    }

    @Then("the hero should be updated successfully")
    public void theHeroShouldBeUpdatedSuccessfully() {
        assertNull(exception);
        if (!"725730a9-f6af-4546-a954-c6c11fa3ca0a".equals(heroId.toString())) {
            verify(heroRepository).update(updatedHero);
            verify(powerStatsRepository).update(updatedHero.getStats());
        }
    }

    @Then("I should receive an error that hero is not found")
    public void iShouldReceiveAnErrorThatHeroIsNotFound() {
        assertNotNull(exception);
        assertTrue(exception instanceof BusinessValidationException);
        assertEquals(ErrorMessagesConstants.INVALID_HERO_ID, exception.getMessage());
    }
}
