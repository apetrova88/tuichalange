Feature: Delete Owner Api Tests

  Scenario: Owner can be deleted by id
    Given user creates pets owner
    When user deletes pets owner by owner id
    Then owner is not present in owners list

  Scenario: 404 error in case delete owner by nonexistent id
    When user deletes owner with nonexistent id
    Then 404 status code is returned