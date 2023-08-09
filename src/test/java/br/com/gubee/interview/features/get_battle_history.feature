Feature: Get Battle History By Hero ID

  Scenario: Successfully retrieve battle history for a hero
    Given the get battle history service is available
    When I retrieve battle history for hero with ID "65e9dae7-0a2e-4aca-98cf-10fcec952c7d"
    Then I should get the battle history list

  Scenario: Attempt to retrieve battle history for invalid hero
    Given the get battle history service is available
    When I attempt to retrieve battle history for invalid hero ID
    Then the system display an error message indicating invalid hero ID