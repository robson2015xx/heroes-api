Feature: Hero Creation

  Scenario: Successfully create a hero
    Given the power stats repository is available
    And the hero repository is available
    When I create a new hero with name "Batman" and the required attributes
    Then the hero should be successfully saved

  Scenario: Attempt to create hero with invalid power stats
    Given the power stats repository is available
    And the hero repository is available
    When I create a new hero with name "Batman" and invalid power stats
    Then the system should display an error message indicating invalid power stats

  Scenario: Attempt to create hero without a name
    Given the power stats repository is available
    And the hero repository is available
    When I create a new hero with empty name and power stats valid
    Then the system should display an error message indicating the hero name is required