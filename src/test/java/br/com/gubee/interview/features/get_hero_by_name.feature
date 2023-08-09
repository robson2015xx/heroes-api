Feature: Get Heroes by Name

  Scenario: Get heroes by name
    Given a hero repository
    When I search for heroes with name "Superman"
    Then I should receive a list of heroes