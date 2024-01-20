Feature: Delete Pet Api Tests

  Scenario: Pet can be deleted by id
    Given user creates pets owner
    And user creates pet with owner
    When user deletes pet by id
    Then pet is not present in owners list

  Scenario: 404 error in case delete pet by nonexistent id
    When user deletes pet with nonexistent id
    Then 404 status code is returned
