Feature: Search Owner Api Tests

  Scenario: Owners can be searched by last name (exact search)
    Given user creates owner with lastName Smith
    When user request pets owner by lastName Smith
    Then only owners with Smith last name are returned

  Scenario: Owners can be searched by last name (partial search)
    Given user creates owner with lastName Smith
    When user request pets owner by lastName Smi
    Then owners whose last name contains Smi are returned

  Scenario: Empty response in case search owner by nonexistent last name
    When user request pets owner by nonexistent lastName
    Then 404 status code is returned
    And response is empty