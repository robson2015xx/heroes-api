Feature: Create Hero

  Scenario: Create a hero
    Given a hero repository and power stats repository
    When I create a hero with name "Superman"
    Then the hero should be saved successfully