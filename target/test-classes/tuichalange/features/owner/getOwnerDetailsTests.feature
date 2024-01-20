Feature: Owner Details Api Tests

  Scenario: Owner can be requested by id
    Given user creates pets owner
    When user request pets owner by owner id
    Then correct owner details are returned

  Scenario: Owner with pet can be requested by id
    Given user creates pets owner
    And user creates pet with owner
    When user request pets owner by owner id
    Then correct owner with pet details are returned

  Scenario: Empty response in case request owner by nonexistent id
    Given user creates pets owner
    When user request pets owner by nonexistent owner id
    Then 404 status code is returned
    And response is empty