Feature: Get Hero By Name

  Scenario: Retrieve heroes with partial name
    Given the get hero by name service is available
    When I retrieve heroes with partial name "Bat"
    Then I should get the list of matching heroes