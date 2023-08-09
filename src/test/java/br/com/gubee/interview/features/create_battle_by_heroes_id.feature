Feature: Create Battle by Heroes ID

  Scenario: Successfully create a battle between two heroes
    Given the create battle service is available
    When I create a battle between hero with ID "b6d72f9f-6784-4a9b-a3b4-7f1958bfb275" and hero with ID "65e9dae7-0a2e-4aca-98cf-10fcec952c7d"
    Then the battle should be successfully saved
    And the battle result should be as expected

  Scenario: Attempt to create battle with invalid hero IDs
    Given the create battle service is available
    When I attempt to create a battle between hero with ID "5711cf74-ebfe-4c9c-81c7-bbba9e2ca2e1" and hero with ID "b6d72f9f-6784-4a9b-a3b4-7f1958bfb275"
    Then the system should display an error message indicating invalid hero ID