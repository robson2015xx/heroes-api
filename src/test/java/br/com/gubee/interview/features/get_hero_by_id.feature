Feature: Get Hero By ID

  Scenario: Successfully retrieve a hero by ID
    Given the get hero by ID service is available
    When I retrieve a hero with ID "65e9dae7-0a2e-4aca-98cf-10fcec952c7d"
    Then I should get the hero details

  Scenario: Attempt to retrieve hero with invalid ID
    Given the get hero by ID service is available
    When I attempt to retrieve a hero with invalid ID
    Then the system should display an error message invalid hero ID