Feature: Update Hero

  Scenario: Update an existing hero
    Given an existing hero with ID "f82b446a-4e35-42d2-976a-f9e07aca8326"
    When I update the hero with ID "f82b446a-4e35-42d2-976a-f9e07aca8326"
      | Field | Value      |
      | Name  | Superman   |
    Then the hero should be updated successfully

  Scenario: Attempt to update a non-existing hero
    Given an non-existing hero with ID "725730a9-f6af-4546-a954-c6c11fa3ca0a"
    When I update the hero with ID "725730a9-f6af-4546-a954-c6c11fa3ca0a"
      | Field | Value      |
      | Name  | Batman     |
    Then I should receive an error that hero is not found