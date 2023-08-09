Feature: Delete Hero

  Scenario: Successfully delete a hero
    Given the delete hero service is available
    And I have a hero with ID "65e9dae7-0a2e-4aca-98cf-10fcec952c7d"
    When I delete a hero with ID "65e9dae7-0a2e-4aca-98cf-10fcec952c7d"
    Then the hero should be successfully deleted
    
  Scenario: Attempt to delete invalid hero
    Given the delete hero service is available
		And I have a hero with ID "65e9dae7-0a2e-4aca-98cf-10fcec952c7d"
    When I attempt to delete a hero with invalid ID
    Then the system should display an error message indicating hero ID invalid